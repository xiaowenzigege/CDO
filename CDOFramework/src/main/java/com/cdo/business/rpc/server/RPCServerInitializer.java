package com.cdo.business.rpc.server;


import com.cdo.business.rpc.client.HeartbeatClientHandler;
import com.cdo.google.handle.CDOProtobufDecoder;
import com.cdo.google.handle.CDOProtobufEncoder;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.EventExecutorGroup;

public class RPCServerInitializer extends ChannelInitializer<SocketChannel> {
    
   
    public RPCServerInitializer() {
             
    }

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline p = ch.pipeline();
        
        p.addLast("encoder",new CDOProtobufEncoder());
        p.addLast("decoder",new CDOProtobufDecoder());  
        p.addLast("ideaHandler",new IdleStateHandler(60,15,0));       
        p.addLast("heartbeat",new HeartbeatServerHandler());
        p.addLast("handle",new RPCServerHandler());  

    }

}
