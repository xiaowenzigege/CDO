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
		  NettyClient rpcClient=getRPCClient(strServiceName);
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
	
	private  NettyClient getRPCClient(String strServiceName) throws NotEstablishConnectException{
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

	
}
