package com.cdo.business.rpc.stop;


import java.io.File;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

import com.cdo.business.rpc.RPCFile;
import com.cdo.google.Header;
import com.cdo.google.handle.CDOMessage;
import com.cdo.google.handle.ProtoProtocol;
import com.cdo.google.protocol.GoogleCDO;
import com.cdo.util.common.UUidGenerator;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.servicebus.ITransService;
import com.google.protobuf.ByteString;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;

public class RPCStopHandler extends  ChannelInboundHandlerAdapter {
	private  Logger logger=Logger.getLogger(RPCStopHandler.class);

    private  Channel channel;
    private  NettyStop nettyStop;
    /**
     * 通过 tcp来关闭server
     */
    public void stopLocalServer(NettyStop nettyStop){
		CDOMessage stopServer=new CDOMessage();
		Header header=new Header();
		header.setType(ProtoProtocol.TYPE_STOPLOCALServer);	
		stopServer.setHeader(header);
		channel.writeAndFlush(stopServer);	
		this.nettyStop=nettyStop;
    }
    
  
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        channel = ctx.channel();
        
    }
    
   @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {    
	   ctx.fireChannelRead(msg);     
    }
   
   public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
       ctx.fireChannelUnregistered();
       close(ctx);
   }
   
   @Override
   public void channelInactive(ChannelHandlerContext ctx) throws Exception {
       ctx.fireChannelInactive();
       close(ctx);
   }
   
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    	close(ctx);
    }      
	@Override
	public String toString(){
		return "channel="+channel;
	}	
	
	private void close(ChannelHandlerContext ctx){
       logger.info("Shutting down......");
    	//出现channel异常 关闭channel连接    	
       try{if(channel!=null) channel.close();if(ctx!=null)ctx.close();}catch(Exception ex){};	
       this.nettyStop.close();
	}
}
