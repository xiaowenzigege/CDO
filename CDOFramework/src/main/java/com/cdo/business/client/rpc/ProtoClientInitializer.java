package com.cdo.business.client.rpc;

import com.cdo.google.handle.CDOProtobufDecoder;
import com.cdo.google.handle.CDOProtobufEncoder;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.SslContext;

public class ProtoClientInitializer extends ChannelInitializer<SocketChannel> {
    private final SslContext sslCtx;
    private final ProtoPRCClient client;
    public ProtoClientInitializer(SslContext sslCtx,ProtoPRCClient client) {
        this.sslCtx = sslCtx;
        this.client=client;
    }

    @Override
    public void initChannel(SocketChannel ch) {
        ChannelPipeline p = ch.pipeline();
//        if (sslCtx != null) {
//            p.addLast(sslCtx.newHandler(ch.alloc(), ProtoPRCClient.HOST, ProtoPRCClient.PORT));
//        }            
        p.addLast("decoder",new CDOProtobufDecoder());        
        p.addLast("encoder",new CDOProtobufEncoder());

        p.addLast(new ProtoClientHandler());
    }

}
