package com.cdo.business.rpc.stop;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.cdo.google.handle.CDOMessage;
import com.cdo.google.handle.CDOProtobufDecoder;
import com.cdo.google.handle.CDOProtobufEncoder;
import com.cdo.google.handle.ProtoProtocol;
import com.cdo.util.constants.Constants;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.internal.SystemPropertyUtil;

public class NettyStop {
	private static final Logger logger=Logger.getLogger(NettyStop.class);
	private ExecutorService executor=Executors.newScheduledThreadPool(1);
	private  Bootstrap bootstrap;
	NioEventLoopGroup  workerGroup; 
	private  Channel channel;
	private  String remoteHost;
	private  int remotePort;  
	
	private  int retryInterval=2;//若断开，每隔多长时间重试一次 单位为秒  默认 5
	private  int retryCount=3;//重试 多少次后.中断重试。默认3
	private  int retry=0;//记录重试次数
	
	RPCStopHandler handle;
    public NettyStop() {
	
	}
    
    public void stopLocalServer(int port){
	    
	    this.remoteHost = "127.0.0.1";
	    this.remotePort = port;	  
	    init();
    	while (true) {
	    	try {Thread.sleep(500);} catch (Exception e){};			
			if(handle!=null)
				break;		
		}
    	handle.stopLocalServer(this);
   }
			
  void init(){	        
	    try {
	    	        workerGroup = new NioEventLoopGroup(1);		    	    		    	    
		    	    bootstrap = new Bootstrap();		    	    
		    	    bootstrap.group(workerGroup);
		    	    bootstrap.channel(NioSocketChannel.class);		    	    
		    	    bootstrap.option(ChannelOption.TCP_NODELAY,true);	    	
		    	    bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
		    	    bootstrap.handler(new ChannelInitializer<SocketChannel>(){
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
					        ChannelPipeline p = ch.pipeline();
					      		        
					        p.addLast("encoder",new CDOProtobufEncoder());
					        p.addLast("decoder",new CDOProtobufDecoder());  	
					        p.addLast("stopHandle",new RPCStopHandler());
						}            	 
		             });
	        }catch(Exception ex){
	        	logger.error(ex.getMessage(), ex);
	        }	    
	        doConnect();  
	    }	

   private void doConnect() {
	    if(retry>=retryCount){
	    	close();
	    	return; 
	    }
	    retry++;//记录重试次数
//	    final NettyStop nettyStop=this;
	    ChannelFuture future = bootstrap.connect(remoteHost,remotePort);
	    future.addListener(new ChannelFutureListener() {
	      public void operationComplete(ChannelFuture f) throws Exception {
	        if (f.isSuccess()) {	                   
		          channel=f.channel();
		          handle=channel.pipeline().get(RPCStopHandler.class);		         
	        } else {
	          f.channel().close();
	          executor.execute(new Runnable() {								
					@Override
					public void run() {
						try{
							TimeUnit.SECONDS.sleep(retryInterval);
						}catch(Exception ex){}										
						doConnect();								
					}
				});	          
	        }
	      }
	    });
	  }
   
	public void close() {
		    workerGroup.shutdownGracefully();
		    logger.info("Stopped Tcp Client: " + getServerInfo()+",channel="+channel);
		    if(executor!=null)
		    	executor.shutdownNow();
	    	logger.info("exit system client " + getServerInfo()+",channel="+channel);
	    	System.exit(0);
	}	

	private String getServerInfo() {
		    return String.format("RemoteAddress=%s:%d",remoteHost,remotePort);
		} 
	
	
}
