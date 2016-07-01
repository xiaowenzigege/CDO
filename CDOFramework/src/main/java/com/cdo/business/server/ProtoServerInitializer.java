package com.cdo.business.server;

import com.cdo.google.handle.CDOProtobufDecoder;
import com.cdo.google.handle.CDOProtobufEncoder;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.SslContext;

public class ProtoServerInitializer extends ChannelInitializer<SocketChannel> {
    private final SslContext sslCtx;

    public ProtoServerInitializer(SslContext sslCtx) {
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
        p.addLast(new ProtoServerHandler());
    }

}
