package com.cdo.business.rpc.client.proto;

import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

import com.cdo.business.rpc.client.Call;
import com.cdo.business.rpc.client.CallsLinkedHashMap;
import com.cdo.business.rpc.client.ClientHandler;
import com.cdo.google.Header;
import com.cdo.google.fileHandle.CDOFileMessage;
import com.cdo.business.rpc.RPCFile;
import com.cdo.google.handle.ProtoProtocol;
import com.cdo.google.protocol.GoogleCDO;

import com.cdoframework.cdolib.data.cdo.CDO;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

public class RPCFileClientHandler extends  ClientHandler {
	private  Logger logger=Logger.getLogger(RPCFileClientHandler.class);

    private  Channel channel;
//    private ByteString clientId; 
    final CallsLinkedHashMap calls = new CallsLinkedHashMap();
    /** A counter for generating call IDs. */
    final AtomicInteger callIdCounter = new AtomicInteger();
    
    public RPCResponse handleTrans(CDO cdoRequest) {  
    	
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
    	}
    	
    	//CDO请求数据
    	int callId=callIdCounter.getAndIncrement() & Integer.MAX_VALUE;
        final Call call =new Call(callId);    
    	GoogleCDO.CDOProto.Builder proto=cdoRequest.toProtoBuilder();
    	proto.setCallId(callId);	
		//构造发送message 类型数据
		Header reqHeader=new Header();
		reqHeader.setType(ProtoProtocol.TYPE_CDO);
		CDOFileMessage reqMessage=new CDOFileMessage();
		reqMessage.setHeader(reqHeader);
		reqMessage.setBody(proto.build());
		reqMessage.setFiles(files);
		
		
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
//      clientId=ByteString.copyFrom(UUidGenerator.ClientId.getClientId());

    }
    
   @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {    
    	if(msg instanceof CDOFileMessage){ 
    		CDOFileMessage cdoMessage=(CDOFileMessage)msg;    	
		    switch(cdoMessage.getHeader().getType()){
		    	case ProtoProtocol.TYPE_CDO:
	        		GoogleCDO.CDOProto proto=(GoogleCDO.CDOProto)(cdoMessage.getBody());
	    			int callId=proto.getCallId();
	    			Call call = calls.get(callId);
	    	        calls.remove(callId);    	        
	    	        call.setRPCResponse(new RPCResponse(proto,cdoMessage.getFiles()));	
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
