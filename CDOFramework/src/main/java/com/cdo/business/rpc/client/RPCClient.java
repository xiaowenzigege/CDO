package com.cdo.business.rpc.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	
	private static final Map<String, RPCClient> clients;
	private static List<Map.Entry<String, RPCClient>> clientList=null;
	static final boolean SSL = System.getProperty("ssl") != null;
	private volatile EventLoopGroup workerGroup;
	private volatile Bootstrap bootstrap;
	private volatile boolean closed = false;
	private Channel channel;
	private  String remoteHost;
	private  int remotePort;  

	private  int retryTime=5;//若断开，每隔多长时间重试一次 单位为秒
	private  int totalRetryCount=5;//0表示无限次每隔retryTime时间的重试一次  大于0在表示重试 达到多次后，不再重试
	private  long retryCount=0;
    RPCClientHandler handle; 
    
    private String clientKey;
    private boolean closedServer=false;
    private volatile static int route=0;

    static{
    	clients=new HashMap<String, RPCClient>();
    }
    /**
     *  
     * nettyAddrss=ip:port:totalRetryCount:workgroup
     */
    public  void connectionNettyServer(String nettyAddress){
	    if(nettyAddress==null|| nettyAddress.trim().equals(""))
	    	return;
		String[] params=nettyAddress.split(":");
		String strWorkGroup="0";
		if(params.length<2)
			return;
		if(params.length>2)
			strWorkGroup=params[2].trim();		
		String hostName=params[0].trim();
		String strPort=params[1].trim();
		int port=Integer.parseInt(strPort);
		int workGroup=Integer.parseInt(strWorkGroup);
		RPCClient client=new RPCClient(hostName,port);
		client.init(workGroup);       
    }
    
    
    public RPCClientHandler getHandle() {
		return handle;
	}

	public boolean isOpen(){
    	if(channel==null)
    		return false;
    	return channel.isOpen();
    }
    
    public boolean isWritable(){
    	if(channel==null)
    		return false;
    	return channel.isWritable();
    }
    
    public RPCClient() {
	
	}
    
    RPCClient(String remoteHost, int remotePort) {
		    this.remoteHost = remoteHost;
		    this.remotePort = remotePort;
		    this.clientKey=remoteHost+":"+remotePort;
	}

    public void stopLocalNettyServer(int port){
	    this.remoteHost = "127.0.0.1";
	    this.remotePort = port;	    
	    this.closedServer=true;
    	this.retryCount=1;
    	this.retryTime=3;
    	init(0);
    	while (true) {
			if(handle!=null)
				break;
	    	try {
				Thread.sleep(500);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
    	handle.stopLocalServer();
   }
		
  void init(int workGroup){
	    final SslContext sslCtx;        
	    try {
	            if (SSL) {
	                sslCtx = SslContextBuilder.forClient()
	                    .trustManager(InsecureTrustManagerFactory.INSTANCE).build();
	            } else {
	                sslCtx = null;
	            }    
	    	    closed = false;
	    	    workerGroup = new NioEventLoopGroup(workGroup);
	    	    bootstrap = new Bootstrap();
	    	    bootstrap.group(workerGroup);
	    	    bootstrap.channel(NioSocketChannel.class);	
//	    	    bootstrap.option(ChannelOption.TCP_NODELAY,false);	    	       
	    	    bootstrap.handler(new ChannelInitializer<SocketChannel>(){
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
				        ChannelPipeline p = ch.pipeline();
				        if (sslCtx != null) {
				            p.addLast(sslCtx.newHandler(ch.alloc(),remoteHost,remotePort));
				        } 
				        p.addLast("ideaHandler",new IdleStateHandler(30,15,0));
				        p.addLast(new ChannelInboundHandlerAdapter() {
					          @Override
					          public void channelInactive(ChannelHandlerContext ctx) throws Exception {
					            super.channelInactive(ctx);	
					            //断开后  删除  到某台服务器连接					           
					            logger.warn("ctx is channelInactive:"+ctx);
					            clients.remove(clientKey);
					            clientList=new ArrayList<Map.Entry<String, RPCClient>>(clients.entrySet());
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
	          channel=f.channel();
	          handle=channel.pipeline().get(RPCClientHandler.class);	
	          clients.put(clientKey,rpcClient);
	          clientList=new ArrayList<Map.Entry<String, RPCClient>>(clients.entrySet());	
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
		  if(clientList.size()==0){
				return new Return(-1,"rpc client connection is null","rpc client connection is null");				
		  }
		  
		  RPCClient rpcClient=clientList.get(route).getValue();
		  if(rpcClient==null)
			  rpcClient=clientList.get(0).getValue();
		  if(route>=(clientList.size()-1))
			  route=0;
		  else
			  route++;
			 
		   RPCResponse response=clientList.get(0).getValue().getHandle().handleTrans(cdoRequest);
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
    public static void main(String[] args){
    	
//    	System.out.println("10:88".split(":").length);
//    	RPCClient rClient=new RPCClient("127.0.0.1",8080);    	
//    	rClient.init();
    	CDO cdoRequest=ExampleCDO.getCDO();
    	CDO cdoResponse=new CDO();
//    	
//    	
//    	try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	Return ret=rClient.handleTrans(cdoRequest, cdoResponse);
//		System.out.println("proto cdoResponse xml="+cdoResponse.toXMLWithIndent()+",cdo ret="+ret);
    	String nettyAddrsses="127.0.0.1:8080";
    	new RPCClient().connectionNettyServer(nettyAddrsses);
    
    	try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.print(new RPCClient().clientList.size());
    	try {
			Thread.sleep(Long.MAX_VALUE);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//    	System.out.println(RPCClient.clients.size());
//    	System.out.println(RPCClient.clientList.size());
//     	Return ret=clientList.get(0).getValue().handleTrans(cdoRequest, cdoResponse);
//     	System.out.println("proto cdoResponse xml="+cdoResponse.toXMLWithIndent()+",cdo ret="+ret);
//	 	RPCClient rClient=new RPCClient(); 
//	 	rClient.stopLocalNettyServer(8080);
//		HashMap map_Data=new HashMap();  
//	    map_Data.put("A", "98");  
//	    map_Data.put("B", "50");  
//	    map_Data.put("C", "50");  
//	    map_Data.put("D", "25");  
//	    map_Data.put("E", "85");  
//	    System.out.println(map_Data);  
//	    List<Map.Entry<String, String>> list_Data = new ArrayList<Map.Entry<String, String>>(map_Data.entrySet());  
//	    Collections.sort(list_Data, new Comparator<Map.Entry<String, String>>()  
//	      {   
//	          public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2)  
//	          {  
//	           if(o2.getValue()!=null&&o1.getValue()!=null&&o2.getValue().compareTo(o1.getValue())>0){  
//	            return 1;  
//	           }else{  
//	            return -1;  
//	           }  
//	              
//	          }  
//	      });  
//	    System.out.println(list_Data);  
//	    for(int i=1;i<10;i++)
//	    	 System.out.println(10%i);  

    }
}
