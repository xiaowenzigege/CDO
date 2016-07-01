package com.cdo.business.server;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.cdo.business.BusinessService;
import com.cdo.util.resource.GlobalResource;
import com.cdoframework.cdolib.base.Return;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

public class ProtoRPCServer {
    static final boolean SSL = System.getProperty("ssl") != null;
	private static Logger logger=Logger.getLogger(ProtoRPCServer.class);
	
    public static void main(String[] args) throws Exception {
        // Configure SSL.
        final SslContext sslCtx;
        if (SSL) {
            SelfSignedCertificate ssc = new SelfSignedCertificate();
            sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
        } else {
            sslCtx = null;
        }

        EventLoopGroup bossGroup = new NioEventLoopGroup(2);
        EventLoopGroup workerGroup = new NioEventLoopGroup(4);
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
//             .option(ChannelOption.TCP_NODELAY, true)       
             .childOption(ChannelOption.SO_KEEPALIVE, true)
             .channel(NioServerSocketChannel.class)
             .childHandler(new ProtoServerInitializer(sslCtx));
            
//            GlobalResource.bundleInitCDOEnv();	
//            int port=GlobalResource.cdoConfig.getInt("netty.server.port");
//            startService();
            int port=8090;
//            b.bind(port).sync().channel().closeFuture().sync();
//            b.bind(port).sync().channel().closeFuture().sync();;
            ChannelFuture f= b.bind(port).sync();
            f.channel().closeFuture().sync();
           
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
    
    private static void startService(){
		Return ret = Return.OK;
		BusinessService app = BusinessService.getInstance();		
		try
		{
			ret = app.start();
		}catch(Throwable e){
			ret	=Return.valueOf(-1,e.getLocalizedMessage());
			logger.error(e.getMessage(),e);
		}
		if(ret.getCode()!=0)
		{
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
