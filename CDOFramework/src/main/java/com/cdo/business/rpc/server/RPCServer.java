package com.cdo.business.rpc.server;

import org.apache.log4j.Logger;

import com.cdo.business.BusinessService;
import com.cdo.util.constants.Constants;
import com.cdo.util.resource.GlobalResource;
import com.cdoframework.cdolib.base.Return;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import io.netty.util.internal.SystemPropertyUtil;

public class RPCServer {
    static final boolean SSL = System.getProperty("ssl") != null;
	private static Logger logger=Logger.getLogger(RPCServer.class);
	static EventLoopGroup bossGroup=null;
	static EventLoopGroup workerGroup=null;
	static BusinessService app=null;
    protected Thread shutdownHook = null;
    
	public void start(){
        final SslContext sslCtx;
        int numMainThread=Math.max(1, SystemPropertyUtil.getInt(Constants.Netty.THREAD_BOSS,Runtime.getRuntime().availableProcessors()));
        bossGroup = new NioEventLoopGroup(numMainThread);
        workerGroup = new NioEventLoopGroup(numMainThread*2);
        try {
            if (SSL) {
                SelfSignedCertificate ssc = new SelfSignedCertificate();
                sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
            } else {
                sslCtx = null;
            }            
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
             .option(ChannelOption.TCP_NODELAY, true)  
             .option(ChannelOption.SO_BACKLOG, 10240)
             .childOption(ChannelOption.SO_KEEPALIVE, true)
             .channel(NioServerSocketChannel.class)
             .handler(new LoggingHandler(LogLevel.INFO))
             .childHandler(new RPCServerInitializer(sslCtx));
                        	
            if(GlobalResource.cdoConfig==null)
            	GlobalResource.bundleInitCDOEnv();
            int port=GlobalResource.cdoConfig.getInt("netty.server.port");
            startService();
            logger.info("server start success ..........");
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
