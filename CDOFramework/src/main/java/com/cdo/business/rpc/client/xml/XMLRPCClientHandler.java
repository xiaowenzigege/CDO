package com.cdo.business.rpc.client.xml;


//import java.io.File;
//import java.net.InetSocketAddress;
//import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

import com.cdo.business.rpc.client.Call;
import com.cdo.business.rpc.client.CallsLinkedHashMap;
import com.cdo.business.rpc.client.ClientHandler;
//import com.cdo.business.rpc.RPCFile;
//import com.cdo.util.common.UUidGenerator;
//import com.google.protobuf.ByteString;
import com.cdo.google.handle.CDOMessage;
import com.cdo.google.handle.ProtoProtocol;
import com.cdo.google.protocol.GoogleCDO;
import com.cdo.xml.handle.XMLHeader;
import com.cdo.xml.handle.XMLMessage;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.servicebus.ITransService;


import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
//import io.netty.util.ReferenceCountUtil;

public class XMLRPCClientHandler extends  ClientHandler {
	private  Logger logger=Logger.getLogger(XMLRPCClientHandler.class);

    private  Channel channel;
//    private ByteString clientId; 
    final CallsLinkedHashMap calls = new CallsLinkedHashMap();
    /** A counter for generating call IDs. */
    final AtomicInteger callIdCounter = new AtomicInteger();
    
    public XMLResponse handleTrans(CDO cdoRequest) {  
    	//CDO请求数据
    	int callId=callIdCounter.getAndIncrement() & Integer.MAX_VALUE;
        final Call call =new Call(callId);    
		//构造发送message 类型数据		
        XMLHeader reqHeader=new XMLHeader();
		reqHeader.setType(ProtoProtocol.TYPE_CDO);
		XMLMessage reqMessage=new XMLMessage();
		reqMessage.setHeader(reqHeader);		
		reqMessage.setBody(cdoRequest.toXML());		
		reqMessage.setCallId(call.getCallId());
        channel.writeAndFlush(reqMessage);

        //同步调用
        calls.put(callId, call,new XMLResponse());        
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
         return (XMLResponse)call.getRPCResponse();
         
       }
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        channel = ctx.channel();
//        clientId=ByteString.copyFrom(UUidGenerator.ClientId.getClientId());

    }
    
   @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {    
    	if(msg instanceof XMLMessage){ 
    		XMLMessage cdoMessage=(XMLMessage)msg;    	
		    switch(cdoMessage.getHeader().getType()){
		    	case ProtoProtocol.TYPE_CDO:		    	
	    			int callId=cdoMessage.getCallId();
	    			Call call = calls.get(callId);
	    	        calls.remove(callId);    	        
	    	        call.setRPCResponse(new XMLResponse(callId, cdoMessage.getBody()));	
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
