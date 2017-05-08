package com.cdo.business.rpc.server;


import com.cdo.google.handle.CDOProtobufDecoder;
import com.cdo.google.handle.CDOProtobufEncoder;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.timeout.IdleStateHandler;

public class RPCServerInitializer extends ChannelInitializer<SocketChannel> {
    private final SslContext sslCtx;

    public RPCServerInitializer(SslContext sslCtx) {
        this.sslCtx = sslCtx;
    }

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline p = ch.pipeline();
        if (sslCtx != null) {
            p.addLast(sslCtx.newHandler(ch.alloc()));
        }   
        p.addLast("encoder",new CDOProtobufEncoder());
        p.addLast("decoder",new CDOProtobufDecoder());  
        p.addLast("ideaHandler",new IdleStateHandler(60,15,0));              
        p.addLast(new RPCServerHandler());
    }

}
