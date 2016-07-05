package com.cdo.business.client.rpc;

import java.util.concurrent.atomic.AtomicInteger;
import org.apache.log4j.Logger;
import com.cdo.google.protocol.GoogleCDO;
import com.cdoframework.cdolib.data.cdo.CDO;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


public class ProtoClientHandler extends  ChannelInboundHandlerAdapter {
	private static Logger logger=Logger.getLogger(ProtoClientHandler.class);
	
    private volatile Channel channel;
    private final CallsLinkedHashMap calls = new CallsLinkedHashMap();
    /** A counter for generating call IDs. */
    private static final AtomicInteger callIdCounter = new AtomicInteger();
       
    public GoogleCDO.CDOProto handleTrans(CDO cdoRequest) {
    	//发送请求
    	int callId=callIdCounter.getAndIncrement() & Integer.MAX_VALUE;
        final Call call =new Call(callId);    
    	GoogleCDO.CDOProto proto=cdoRequest.toProto();
//    	proto.setCallId(callId);
//		proto.setClientId(ClientId.getClientId());	    	
        channel.writeAndFlush(proto);    
        //获取response 参见netty4.1 官网example
        calls.put(callId, call);
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
         return call.getRpcResponse();
       }
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        channel = ctx.channel();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {    
    	if(msg instanceof GoogleCDO.CDOProto){    		
    		GoogleCDO.CDOProto proto=(GoogleCDO.CDOProto)msg;
			int callId=0;//proto.getCallId();
			Call call = calls.get(callId);
	        calls.remove(callId);
	        call.setRpcResponse(proto);			
    	}else{
    		ctx.fireChannelRead(msg);
    	}        
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    	logger.error(cause.getMessage(),cause);
    }
    
  
	
}
