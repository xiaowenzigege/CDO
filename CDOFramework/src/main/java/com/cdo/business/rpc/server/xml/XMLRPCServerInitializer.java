package com.cdo.business.rpc.server.xml;

import com.cdo.business.rpc.server.HeartbeatServerHandler;
import com.cdo.google.handle.CDOProtobufDecoder;
import com.cdo.google.handle.CDOProtobufEncoder;
import com.cdo.xml.handle.CDOXmlDecoder;
import com.cdo.xml.handle.CDOXmlEncoder;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
//import io.netty.handler.ssl.SslContext;
import io.netty.handler.timeout.IdleStateHandler;
//import io.netty.util.concurrent.EventExecutorGroup;

public class XMLRPCServerInitializer extends ChannelInitializer<SocketChannel> {
    
   
    public XMLRPCServerInitializer() {
             
    }

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline p = ch.pipeline();
        
        p.addLast("encoder",new CDOXmlEncoder());
        p.addLast("decoder",new CDOXmlDecoder());  
        p.addLast("ideaHandler",new IdleStateHandler(60,15,0));       
        p.addLast("heartbeat",new HeartbeatServerHandler());
        p.addLast("handle",new XMLServerHandler());  

    }

}
