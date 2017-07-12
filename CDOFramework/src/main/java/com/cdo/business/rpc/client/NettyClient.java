package com.cdo.business.rpc.client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.cdo.google.handle.CDOProtobufDecoder;
import com.cdo.google.handle.CDOProtobufEncoder;
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

public class NettyClient {
	private final static Logger logger=Logger.getLogger(RPCClient.class);
	private ExecutorService executor=Executors.newScheduledThreadPool(1);
	private RouteManager routeManager=RouteManager.getInstance();
//	private  static ReadWriteLock rpcClientLock = new ReentrantReadWriteLock();  
    int channelThread=Math.max(2,SystemPropertyUtil.getInt(Constants.Netty.THREAD_CLIENT_WORK,Runtime.getRuntime().availableProcessors()*2));
	final boolean SSL = System.getProperty("ssl") != null;
	private  EventLoopGroup workerGroup;
	//客户端连接多个服务端，共享一个工作线程组,避免  后台服务器多了，线程成倍膨胀
	private  Bootstrap bootstrap;
	private  boolean closed = false;
	private  Channel channel;
	private  String remoteHost;
	private  int remotePort;  
	
	private  int retryTime=5;//若断开，每隔多长时间重试一次 单位为秒
	private  int totalRetryCount=5;//0表示无限次每隔retryTime时间的重试一次  大于0在表示重试 达到多次后，不再重试
	private  long retryCount=0;

	
    RPCClientHandler handle; 
    //ip:port
    private String serverAddress;
    private boolean closedServer=false;

    /** server 服务器ip:port 地址
     * hostPort=ip:port
     * @param hostPort
     */
    public static NettyClient connectionServer(String serverHostPort){
		String[] params=serverHostPort.split(":");
		String host=params[0].trim();
		int port=Integer.parseInt(params[1].trim());		
		NettyClient client=new NettyClient(host,port);
		client.init();   
		return client;
    }
    
    
    public RPCClientHandler getHandle() {
		return handle;
	}

	public boolean isOpen(){
    	if(channel==null)
    		return false;
    	return channel.isOpen();
    }
    
	public boolean isRegistered(){
    	if(channel==null)
    		return false;
    	return channel.isRegistered();
    }
	
	public boolean isActive(){
    	if(channel==null)
    		return false;
    	return channel.isActive();
    }
	
    public boolean isWritable(){
    	if(channel==null)
    		return false;    	
    	return channel.isWritable();
    }
    
    public void closeChannel(){
    	if(channel==null)
    		return;    	
    	 channel.close();
    }
    
    public NettyClient() {
	
	}
    
    NettyClient(String serverHost, int serverPort) {
		    this.remoteHost = serverHost;
		    this.remotePort = serverPort;
		    this.serverAddress=serverHost+":"+serverPort;
	}

    public void stopLocalNettyServer(int port){
	    this.remoteHost = "127.0.0.1";
	    this.remotePort = port;	    
	    this.closedServer=true;
    	this.retryCount=1;
    	this.retryTime=2;
    	init(0);
    	while (true) {
	    	try {Thread.sleep(500);} catch (Exception e){};			
			if(handle!=null)
				break;		
		}
    	handle.stopLocalServer();
   }
	
	
  void init(){	  
    	init(channelThread);
    }

	
  void init(int channelThread){
	    final SslContext sslCtx;        
	    try {
	            if (SSL) {
	                sslCtx = SslContextBuilder.forClient()
	                    .trustManager(InsecureTrustManagerFactory.INSTANCE).build();
	            } else {
	                sslCtx = null;
	            }    
	    	    closed = false;
	    	    //还未建立连接时，开始建立连接 。创建锁,防止覆盖创建
		    	    workerGroup = new NioEventLoopGroup(channelThread);		    	    		    	    
		    	    bootstrap = new Bootstrap();
		    	    
		    	    bootstrap.group(workerGroup);
		    	    bootstrap.channel(NioSocketChannel.class);		    	    
		    	    bootstrap.option(ChannelOption.TCP_NODELAY,true);	    	
		    	    bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
		    	    bootstrap.handler(new ChannelInitializer<SocketChannel>(){
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
					        ChannelPipeline p = ch.pipeline();
					        if (sslCtx != null) {
					            p.addLast(sslCtx.newHandler(ch.alloc(),remoteHost,remotePort));
					        } 				       
					        p.addLast(new ChannelInboundHandlerAdapter() {
						          @Override
						          public void channelInactive(ChannelHandlerContext ctx) throws Exception {
						            super.channelInactive(ctx);	
						            //断开后  删除  到某台服务器连接					           
						            logger.warn("ctx is channelInactive:"+ctx);
//						            routeManager.removeRPCClient(serverAddress);
						         			         
							        executor.execute(new Runnable() {								
											@Override
											public void run() {
												try{
													TimeUnit.SECONDS.sleep(retryTime);
												}catch(Exception ex){}										
												doConnect();								
											}
										});	  
						          }
						        });			        
					        p.addLast("encoder",new CDOProtobufEncoder());
					        p.addLast("decoder",new CDOProtobufDecoder());  
					        p.addLast("ideaHandler",new IdleStateHandler(60,5,0));
					        p.addLast(new RPCClientHandler());				
						}            	 
		             });
	        }catch(Exception ex){
	        	logger.error(ex.getMessage(), ex);
	        }	    
	        doConnect();  
	    }	

   private void doConnect() {
	    if (closed) {
	      return;
	    }
	    if(totalRetryCount>0 && retryCount>=totalRetryCount){
	    	close();
	    	return; 
	    }
	    retryCount++;//记录重试次数
	    final NettyClient rpcClient=this;
	    ChannelFuture future = bootstrap.connect(remoteHost,remotePort);
	    future.addListener(new ChannelFutureListener() {
	      public void operationComplete(ChannelFuture f) throws Exception {
	        if (f.isSuccess()) {	                   
	          channel=f.channel();
	          handle=channel.pipeline().get(RPCClientHandler.class);
	          //添加到路由
//	          routeManager.addNettyClient(serverAddress, rpcClient);
	          
	          logger.info("Started  Client Success  connection  remote address: " + getServerInfo()+",channel="+channel);	
	        } else {
	           //正常的客户端连接
	          if(!closedServer)	
	        	  logger.error("Started Client Failed retry connection ["+retryCount+"] times : " + getServerInfo()+",channel="+channel);
	          
	          f.channel().close();
	          executor.execute(new Runnable() {								
					@Override
					public void run() {
						try{
							TimeUnit.SECONDS.sleep(retryTime);
						}catch(Exception ex){}										
						doConnect();								
					}
				});	          
	        }
	      }
	    });
	  }
   
	public void close() {
		    closed = true;
		    workerGroup.shutdownGracefully();
		    logger.info("Stopped Tcp Client: " + getServerInfo()+",channel="+channel);
		    if(executor!=null)
		    	executor.shutdownNow();
		    if(closedServer){
		    	//如果是关闭服务器,需要退出程序
		    	logger.info("exit system client " + getServerInfo()+",channel="+channel);
		    	System.exit(0);
		    }
		    
	}	

	private String getServerInfo() {
		    return String.format("RemoteAddress=%s:%d",remoteHost,remotePort);
		} 
	
	
	@Override
	public String toString(){
		return "channel="+channel+",bootstrap:"+bootstrap.toString();
	}	
}
