package com.cdo.business.client.rpc;

import java.net.SocketAddress;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

import com.cdo.google.protocol.GoogleCDO;
import com.cdo.util.common.UUidGenerator;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.google.protobuf.ByteString;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoop;


public class RPCClientHandler extends  ChannelInboundHandlerAdapter {
	private static Logger logger=Logger.getLogger(RPCClientHandler.class);
	
    private volatile Channel channel;
    private final CallsLinkedHashMap calls = new CallsLinkedHashMap();
    /** A counter for generating call IDs. */
    private static final AtomicInteger callIdCounter = new AtomicInteger();
           
    public GoogleCDO.CDOProto handleTrans(CDO cdoRequest) {    	
    	//发送请求
    	int callId=callIdCounter.getAndIncrement() & Integer.MAX_VALUE;
        final Call call =new Call(callId);    
    	GoogleCDO.CDOProto.Builder proto=cdoRequest.toProtoBuilder();
    	proto.setCallId(callId);
		proto.setClientId(ByteString.copyFrom(UUidGenerator.ClientId.getClientId()));	    	
        channel.writeAndFlush(proto.build());    
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
			int callId=proto.getCallId();
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
