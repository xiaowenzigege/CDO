package com.cdo.business.rpc.server.proto;

import java.util.concurrent.ExecutorService;

import com.cdo.business.rpc.server.HeartbeatServerHandler;
import com.cdo.google.handle.CDOProtobufDecoder;
import com.cdo.google.handle.CDOProtobufEncoder;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
//import io.netty.handler.ssl.SslContext;
import io.netty.handler.timeout.IdleStateHandler;
//import io.netty.util.concurrent.EventExecutorGroup;

public class RPCServerInitializer extends ChannelInitializer<SocketChannel> {
    
	ExecutorService exService;
    public RPCServerInitializer(ExecutorService exService) {
             this.exService=exService;
    }

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline p = ch.pipeline();
        
        p.addLast("encoder",new CDOProtobufEncoder());
        p.addLast("decoder",new CDOProtobufDecoder());  
        p.addLast("ideaHandler",new IdleStateHandler(60,15,0));       
        p.addLast("heartbeat",new HeartbeatServerHandler());
        p.addLast("handle",new RPCServerHandler(this.exService));  

    }

}
