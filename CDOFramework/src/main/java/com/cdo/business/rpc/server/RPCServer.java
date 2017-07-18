package com.cdo.business.rpc.server;

import org.apache.log4j.Logger;

import com.cdo.business.BusinessService;
import com.cdo.util.constants.Constants;
import com.cdo.util.resource.GlobalResource;
import com.cdo.util.system.SystemUtil;
import com.cdoframework.cdolib.base.Return;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.RejectedExecutionHandler;
import io.netty.util.concurrent.SingleThreadEventExecutor;
import io.netty.util.internal.SystemPropertyUtil;

public class RPCServer {
    
	private static Logger logger=Logger.getLogger(RPCServer.class);
	static EventLoopGroup bossGroup=null;
	static EventLoopGroup workerGroup=null;
	static BusinessService app=null;
    protected Thread shutdownHook = null;
       
    
	public void start(){        
        int bossThread=Math.max(1,SystemPropertyUtil.getInt(Constants.Netty.THREAD_SERVER_BOSS,Runtime.getRuntime().availableProcessors()));
        int channelThread=Math.max(2,SystemPropertyUtil.getInt(Constants.Netty.THREAD_SERVER_WORK,Runtime.getRuntime().availableProcessors()*2));                
        
        if(SystemUtil.isLinux()){
            bossGroup = new EpollEventLoopGroup(bossThread);
            workerGroup = new EpollEventLoopGroup(channelThread);       	
        }else{
            bossGroup = new NioEventLoopGroup(bossThread);
            workerGroup = new NioEventLoopGroup(channelThread);  
        }
             
        try {        
     
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
             .option(ChannelOption.TCP_NODELAY, true)  
             .option(ChannelOption.SO_BACKLOG, 1024)
             .childOption(ChannelOption.SO_KEEPALIVE, true);
             if(SystemUtil.isLinux()){
            	 b.channel(EpollServerSocketChannel.class);
             }else{
            	 b.channel(NioServerSocketChannel.class);
             }
             b.handler(new LoggingHandler(LogLevel.INFO))
             .childHandler(new RPCServerInitializer());
                        	
            if(GlobalResource.cdoConfig==null)
            	GlobalResource.bundleInitCDOEnv();
            int port=GlobalResource.cdoConfig.getInt("netty.server.port");
            startService();
            logger.info("server start success ..........\r\n connection acceptor  threads="+bossThread+",channel threads="+channelThread+",serverBootStrap="+b);
            ChannelFuture f= b.bind(port).sync();
            f.channel().closeFuture().sync();
         }catch(Throwable ex){
        	 logger.fatal("RPCServer start fatal :"+ex.getMessage(),ex);
        	 System.exit(1);
         }
	}

	public static void stop(){
		if(app!=null)
			app.stop();		 
		if(bossGroup!=null){
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
		System.exit(1);
	}
	
	
	public  void stopLocalServer(){
		if(app!=null)
			app.stop();
		 Runtime.getRuntime().removeShutdownHook(shutdownHook);
		if(bossGroup!=null){
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
		System.exit(1);
	}
    protected class ShutdownHook extends Thread {
        @Override
        public void run() {
            try {
            	  RPCServer.this.stopLocalServer();
            } catch (Throwable ex) {
            	logger.error(ex.getMessage(), ex);            
            }
        }
    }
   
    private static void startService(){
    	Return ret =null;
		app = BusinessService.getInstance();		
		try{
			ret = app.start();
		}catch(Throwable e){
			ret	=Return.valueOf(-1,e.getLocalizedMessage());
			logger.error(e.getMessage(),e);
		}
		if(ret.getCode()!=0){
			logger.fatal(ret.getText());
			logger.fatal("||*****************************************||\r\n||*****************************************||\r\n||  started faild and will exit            ||\r\n||*****************************************||\r\n||*****************************************||");
			System.exit(-1);
			return;
		}
		if(logger.isInfoEnabled()){
			logger.info("business started-----------------");
		}
    }    

}
