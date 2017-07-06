package com.cdo.business.rpc.client;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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

import org.apache.log4j.Logger;

import com.cdo.business.rpc.RPCFile;
import com.cdo.business.rpc.zk.ZKRPCClient;
import com.cdo.business.rpc.zk.ZkNodeData;
import com.cdo.google.handle.CDOProtobufDecoder;
import com.cdo.google.handle.CDOProtobufEncoder;
import com.cdo.google.protocol.GoogleCDO;
import com.cdo.util.constants.Constants;
import com.cdo.util.exception.NotEstablishConnectException;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.servicebus.ITransService;

/**
 * 
 * @author KenelLiu
 *
 */
public class RPCClient extends ZKRPCClient{
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
    public static RPCClient connectionServer(String serverHostPort){
		String[] params=serverHostPort.split(":");
		String host=params[0].trim();
		int port=Integer.parseInt(params[1].trim());		
		RPCClient client=new RPCClient(host,port);
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
    
    public RPCClient() {
	
	}
    
    RPCClient(String serverHost, int serverPort) {
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
						            routeManager.removeRPCClient(serverAddress);
						         			         
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
	    final RPCClient rpcClient=this;
	    ChannelFuture future = bootstrap.connect(remoteHost,remotePort);
	    future.addListener(new ChannelFutureListener() {
	      public void operationComplete(ChannelFuture f) throws Exception {
	        if (f.isSuccess()) {	                   
	          channel=f.channel();
	          handle=channel.pipeline().get(RPCClientHandler.class);
	          //添加到路由
	          routeManager.addRPCClient(serverAddress, rpcClient);
	          
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


	
	
	/**
	 * @see {@link com.cdo.business.rpc.server.RPCServer#handleTrans(CDO,CDO)}
	 * @param cdoRequest
	 * @param cdoResponse
	 * @return
	 */
	public Return handleTrans(CDO cdoRequest, CDO cdoResponse){
		if(!cdoRequest.exists(ITransService.SERVICENAME_KEY)){
			return new Return(-1,"Service Name is null,plealse check strServiceName value");	
		}
		String strServiceName=cdoRequest.getStringValue(ITransService.SERVICENAME_KEY);
		
	  try {	
			RPCClient rpcClient=getRPCClient(strServiceName);
			if(rpcClient.getHandle()==null){
				int retryCount=1;//重试3次
				while(retryCount<=3){
					rpcClient=getRPCClient(strServiceName);
					if(rpcClient.getHandle()!=null) //创建链接成功，退出重试
						break;
					try{Thread.sleep(1000+500*retryCount);}catch(Exception em){}
					retryCount++;
				}
			}	
			RPCResponse response=rpcClient.getHandle().handleTrans(cdoRequest);
		   
			//cdo 内容
		   GoogleCDO.CDOProto proto=response.getCdoProto();
		   CDO cdoReturn=new CDO();
			//解释google buffer
		   ParseRPCProtoCDO.ProtoRPCParse.parse(proto, cdoResponse, cdoReturn);
			//设置响应文件
		   RPCFile.setFile2CDO(cdoResponse, response.getListFile());			
		   return new Return(cdoReturn.getIntegerValue("nCode"),cdoReturn.getStringValue("strText"), cdoReturn.getStringValue("strInfo"));
	  	}catch(NotEstablishConnectException ex){
			logger.warn("Service["+strServiceName+"] not registered on zk server");
			return new Return(-99,"Service["+strServiceName+"] not registered on zk server");		  
		} catch (Throwable e) {			
			String strTransName=cdoRequest.exists(ITransService.TRANSNAME_KEY)?cdoRequest.getStringValue(ITransService.TRANSNAME_KEY):"null";					
			logger.error("Request method :strServiceName="+strServiceName+",strTransName="+strTransName+",error="+e.getMessage(),e);
			return new Return(-1,e.getMessage(),e.getMessage());
		}		  
	}	
	
	private  RPCClient getRPCClient(String strServiceName) throws NotEstablishConnectException{
		Map<String, ZkNodeData>  zkServerMap=getServiceMap();
		if(zkServerMap==null || zkServerMap.get(strServiceName)==null || zkServerMap.get(strServiceName).getRobinScheduling()==null){
			logger.warn("Service["+strServiceName+"] not registered on zk server");
			throw new NotEstablishConnectException("Service["+strServiceName+"] not registered on zk server");	
		}	
		return RouteManager.getInstance().route(zkServerMap.get(strServiceName).getRobinScheduling()); 
	}


	@Override
	public Return asyncHandleTrans(CDO cdoRequest,CDO cdoResponse){			
			cdoRequest.setBooleanValue(ITransService.ASYNCH_KEY, true);
			return this.handleTrans(cdoRequest,cdoResponse);		
	}
	
	@Override
	public String toString(){
		return "channel="+channel+",bootstrap:"+bootstrap.toString();
	}
	
}
