package com.cdo.business.rpc.client.proto;


//import java.io.File;
//import java.net.InetSocketAddress;
//import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

import com.cdo.business.rpc.client.Call;
import com.cdo.business.rpc.client.CallsLinkedHashMap;
import com.cdo.business.rpc.client.ClientHandler;
import com.cdo.google.Header;
//import com.cdo.business.rpc.RPCFile;
//import com.cdo.util.common.UUidGenerator;
//import com.google.protobuf.ByteString;
import com.cdo.google.handle.CDOMessage;
import com.cdo.google.handle.ProtoProtocol;
import com.cdo.google.protocol.GoogleCDO;

import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.servicebus.ITransService;


import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
//import io.netty.util.ReferenceCountUtil;

public class RPCClientHandler extends  ClientHandler {
	private  Logger logger=Logger.getLogger(RPCClientHandler.class);

    private  Channel channel;
//    private ByteString clientId; 
    final CallsLinkedHashMap calls = new CallsLinkedHashMap();
    /** A counter for generating call IDs. */
    final AtomicInteger callIdCounter = new AtomicInteger();
    
    public RPCResponse handleTrans(CDO cdoRequest) {  

    	/**优化数据，去掉 考虑文件传输
    	List<File> files=null;
    	try{
    		files=RPCFile.readFileFromCDO(cdoRequest);    		
    	}catch(Exception ex){
		    //文件不存在直接返回 ,跟RPCServerHandler 返回给客户端保持一致风格
    		logger.error(ex.getMessage(), ex);
    		CDO cdoOutput=new CDO();
			CDO cdoResponse=new CDO();
			CDO cdoReturn=new CDO();
			cdoReturn.setIntegerValue("nCode",-1);
			cdoReturn.setStringValue("strText","RPCClient "+ex.getMessage());
			cdoReturn.setStringValue("strInfo","RPCClient "+ex.getMessage());
			cdoOutput.setCDOValue("cdoReturn",cdoReturn);
			cdoOutput.setCDOValue("cdoResponse", cdoResponse);	    					 
			return new RPCResponse(cdoOutput.toProtoBuilder().build());
    	}**/
    	
    	//CDO请求数据
    	int callId=callIdCounter.getAndIncrement() & Integer.MAX_VALUE;
        final Call call =new Call(callId);    
    	GoogleCDO.CDOProto.Builder proto=cdoRequest.toProtoBuilder();
    	proto.setCallId(callId);
//		proto.setClientId(clientId);	
		//构造发送message 类型数据
		Header reqHeader=new Header();
		reqHeader.setType(ProtoProtocol.TYPE_CDO);
		CDOMessage reqMessage=new CDOMessage();
		reqMessage.setHeader(reqHeader);
		reqMessage.setBody(proto.build());
//		reqMessage.setFiles(files);
		
		
        channel.writeAndFlush(reqMessage);

        //同步调用
        calls.put(callId, call,new RPCResponse());        
        boolean interrupted = false;
        synchronized (call) {
          while (!call.done()) {
            try {
              call.wait();                 
            } catch (InterruptedException ie) {
              // save the fact that we were interrupted
              interrupted = true;
            }
          }
          if (interrupted) {
            // set the interrupt flag now that we are done waiting
            Thread.currentThread().interrupt();
          }          
         reqMessage=null;
         return (RPCResponse)call.getRPCResponse();
         
       }
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        channel = ctx.channel();
//        clientId=ByteString.copyFrom(UUidGenerator.ClientId.getClientId());

    }
    
   @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {    
    	if(msg instanceof CDOMessage){ 
    		CDOMessage cdoMessage=(CDOMessage)msg;    	
		    switch(cdoMessage.getHeader().getType()){
		    	case ProtoProtocol.TYPE_CDO:
	        		GoogleCDO.CDOProto proto=(GoogleCDO.CDOProto)(cdoMessage.getBody());
	    			int callId=proto.getCallId();
	    			Call call = calls.get(callId);
	    	        calls.remove(callId);    	        
	    	        call.setRPCResponse(new RPCResponse(proto, null));	
	    	      break;
	    	    default:
	    	    	ctx.fireChannelRead(msg);
		    }	
    	}else{
    		ctx.fireChannelRead(msg);
    	}        
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
       logger.warn("channel ["+ctx.channel()+"] error:"+cause.getMessage()+",channel will close",cause);
    	//出现channel异常 关闭channel连接    	
       try{if(channel!=null) channel.close();if(ctx!=null)ctx.close();}catch(Exception ex){};	
    }
      
	@Override
	public String toString(){
		return "channel="+channel;
	}	
}
