package com.cdo.business.client.rpc;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

import com.cdo.google.protocol.GoogleCDO;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.google.protobuf.MessageLite;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ProtoClientHandle extends SimpleChannelInboundHandler<MessageLite> {
	private static Logger logger=Logger.getLogger(ProtoClientHandle.class);
	
    private volatile Channel channel;
    private final BlockingQueue<GoogleCDO.CDOProto> answer = new LinkedBlockingQueue<GoogleCDO.CDOProto>();
    
    public ProtoClientHandle() {
        super(false);
    }

    public GoogleCDO.CDOProto handleTrans(CDO cdoRequest) {

        channel.writeAndFlush(cdoRequest.toProto());
       
        boolean interrupted = false;
        GoogleCDO.CDOProto local;
        for (;;) {
            try {            	
            	local = answer.take();
                break;
            } catch (InterruptedException ignore) {
                interrupted = true;
            }
            
        }
        if (interrupted) {
            Thread.currentThread().interrupt();
        }        
        return local;               
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        channel = ctx.channel();
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, MessageLite msg) throws Exception {
    	if(msg instanceof GoogleCDO.CDOProto){
    		answer.add((GoogleCDO.CDOProto)msg);	
    	}        
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    	logger.error(cause.getMessage(),cause);
    }
}
