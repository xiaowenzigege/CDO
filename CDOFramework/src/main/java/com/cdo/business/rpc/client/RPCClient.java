package com.cdo.business.rpc.client;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.IdleStateHandler;

import org.apache.log4j.Logger;

import com.cdo.business.rpc.RPCFile;
import com.cdo.example.ExampleCDO;
import com.cdo.google.handle.CDOProtobufDecoder;
import com.cdo.google.handle.CDOProtobufEncoder;
import com.cdo.google.protocol.GoogleCDO;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.servicebus.ITransService;

/**
 * 
 * @author KenelLiu
 *
 */
public class RPCClient implements IRPCClient{
	private final static Logger logger=Logger.getLogger(RPCClient.class);
	private ExecutorService executor=Executors.newScheduledThreadPool(1);
	
	private static final Map<String, RPCClient> clients=new HashMap<String, RPCClient>();
	static final boolean SSL = System.getProperty("ssl") != null;
	private volatile EventLoopGroup workerGroup;
	private volatile Bootstrap bootstrap;
	private volatile boolean closed = false;
	private final String remoteHost;
	private final int remotePort;  

	private  int retryTime=5;//若断开，每隔多长时间重试一次 单位为秒
	private  int totalRetryCount=5;//0表示无限次每隔retryTime时间的重试一次  大于0在表示重试 达到多次后，不再重试
	private  int retryCount=0;
    RPCClientHandler handle; 
    
    private String clientKey;
    private boolean closedServer=false;
    static {
    	//TODO 建立连接多个服务端的connection
//    	String[] address=GlobalResource.cdoConfig.getString("netty.client.conections").split(";");
//    	for(int i=0;i<address.length;i++){
//    		int index=address[i].lastIndexOf(":");
//    		String hostName=address[i].substring(0, index);
//    		int port=Integer.parseInt(address[i].substring(index+1));
//    		ProtoPRCClient client=new ProtoPRCClient(hostName,port);
//    		client.init();
//    	}
    	
    }
    
	public RPCClient(String remoteHost, int remotePort) {
		    this.remoteHost = remoteHost;
		    this.remotePort = remotePort;
		    this.clientKey=remoteHost+":"+remotePort;
	}

	public RPCClient(String remoteHost, int remotePort,boolean closedServer) {
	    this.remoteHost = remoteHost;
	    this.remotePort = remotePort;	    
	    this.closedServer=closedServer;
	    if(closedServer){
	    	this.retryCount=1;
	    	this.retryTime=3;
	    }
	    
	   
  }
	
	public void init(){
	    final SslContext sslCtx;        
	    try {
	            if (SSL) {
	                sslCtx = SslContextBuilder.forClient()
	                    .trustManager(InsecureTrustManagerFactory.INSTANCE).build();
	            } else {
	                sslCtx = null;
	            }    
	    	    closed = false;
	    	    workerGroup = new NioEventLoopGroup();
	    	    bootstrap = new Bootstrap();
	    	    bootstrap.group(workerGroup);
	    	    bootstrap.channel(NioSocketChannel.class);
	    	    bootstrap.handler(new ChannelInitializer<SocketChannel>(){
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
				        ChannelPipeline p = ch.pipeline();
				        if (sslCtx != null) {
				            p.addLast(sslCtx.newHandler(ch.alloc(),remoteHost,remotePort));
				        } 
				        p.addLast("ideaHandler",new IdleStateHandler(30,10,0));
				        p.addLast(new ChannelInboundHandlerAdapter() {
					          @Override
					          public void channelInactive(ChannelHandlerContext ctx) throws Exception {
					            super.channelInactive(ctx);	
					            //断开后  删除  到某台服务器连接					           
					            logger.warn("ctx is channelInactive:"+ctx);
					            clients.remove(clientKey);
					            ctx.channel().close();
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
				        p.addLast("decoder",new CDOProtobufDecoder());        
				        p.addLast("encoder",new CDOProtobufEncoder());
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
	          logger.info("Started  Client Success connection: " + getServerInfo());
	          handle=f.channel().pipeline().get(RPCClientHandler.class);	          
	          clients.put(clientKey,rpcClient);
	        } else {
	           //正常的客户端连接
	          if(!closedServer)	
	        	  logger.error("Started Client Failed retry connection ["+retryCount+"] times : " + getServerInfo());
	          
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
		    logger.info("Stopped Tcp Client: " + getServerInfo());
		    System.exit(-1);
	}	

	private String getServerInfo() {
		    return String.format("RemoteHost=%s RemotePort=%d",
		    		remoteHost,
		    		remotePort);
		} 	
	

 
	/**
	 * @see {@link com.cdo.business.rpc.server.RPCServer#handleTrans(CDO,CDO)}
	 * @param cdoRequest
	 * @param cdoResponse
	 * @return
	 */
	public Return handleTrans(CDO cdoRequest, CDO cdoResponse){		
	  try {		  
		  	//发送请求,并得到RPC响应
			RPCResponse response=handle.handleTrans(cdoRequest);
			//cdo 内容
			GoogleCDO.CDOProto proto=response.getCdoProto();
			CDO cdoReturn=new CDO();
			//解释google buffer
			ParseRPCProtoCDO.ProtoRPCParse.parse(proto, cdoResponse, cdoReturn);
			//设置响应文件
			RPCFile.setFile2CDO(cdoResponse, response.getListFile());			
		    return new Return(cdoReturn.getIntegerValue("nCode"),cdoReturn.getStringValue("strText"), cdoReturn.getStringValue("strInfo"));		
		} catch (Throwable e) {
			String strServiceName=cdoRequest.exists(ITransService.SERVICENAME_KEY)?cdoRequest.getStringValue(ITransService.SERVICENAME_KEY):"null";
			String strTransName=cdoRequest.exists(ITransService.TRANSNAME_KEY)?cdoRequest.getStringValue(ITransService.TRANSNAME_KEY):"null";					
			logger.error("Request method :strServiceName="+strServiceName+",strTransName="+strTransName+",error="+e.getMessage(),e);
			return new Return(-1,e.getMessage(),e.getMessage());
		}		  
	}	
	
	public void stopLocalServer(){
		handle.stopLocalServer();
	}
	
    public static void main(String[] args){
    	RPCClient rClient=new RPCClient("127.0.0.1",8080);    	
    	rClient.init();
    	CDO cdoRequest=ExampleCDO.getCDO();
    	CDO cdoResponse=new CDO();
    	try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Return ret=rClient.handleTrans(cdoRequest, cdoResponse);
		System.out.println("proto cdoResponse xml="+cdoResponse.toXMLWithIndent()+",cdo ret="+ret);

    }
}
