package com.cdo.business.rpc.zk;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import com.cdo.business.client.IClient;
import com.cdo.business.rpc.client.ClientHandler;
import com.cdo.business.rpc.client.NettyClientFactory;
import com.cdo.business.rpc.route.CircleRPCQueue;
import com.cdo.business.rpc.zk.ZkNodeData;
import com.cdo.util.algorithm.RoundRobinScheduling;
import com.cdo.util.exception.NotEstablishConnectException;
import com.cdo.util.server.Server;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
/**
 * 根据serviceName,通过权重轮询获取 提供该服务的remoteAddress
 * @author kenelLiu
 *
 */

public abstract class ZKRPCClient implements IClient{
	private Logger logger=Logger.getLogger(ZKRPCClient.class);
	/**<serviceName,ZkNodeData>**/
	protected Map<String, ZkNodeData> serviceMap;
	/**注册到zk上的连接**/
	protected String zkConnect;
	/**自定义的zkId**/
	protected String zkId;
	
	 void setServiceMap(Map<String, ZkNodeData> serviceMap){
		this.serviceMap=serviceMap;
	}
	
	public Map<String, ZkNodeData> getServiceMap(){
		
		return this.serviceMap;
	}
	
	public String getZKConnect() {
		return zkConnect;
	}
	
	 void setZKConnect(String zkConnect){
		this.zkConnect=zkConnect;
	}
	
	public String getZKId() {
		return zkId;
	}
	
	 void setZKId(String zkId){
		this.zkId=zkId;
	}
	 
	public abstract  Return asyncHandleTrans(CDO cdoRequest);
	
	protected  ClientHandler getRPCClient(String strServiceName) throws NotEstablishConnectException{
			Map<String, ZkNodeData>  zkServerMap=getServiceMap();
			if(zkServerMap==null || zkServerMap.get(strServiceName)==null || zkServerMap.get(strServiceName).getRobinScheduling()==null){
				logger.warn("Service["+strServiceName+"] not registered on zk server");
				throw new NotEstablishConnectException("Service["+strServiceName+"] not registered on zk server");	
			}					
			RoundRobinScheduling roundSchedule=zkServerMap.get(strServiceName).getRobinScheduling();
			NettyClientFactory nettyClientFactory=NettyClientFactory.getDefaultInstance();
			
			Server server=roundSchedule.getServer();		
			String remoteAddress=server.getHostPost();	
			CircleRPCQueue<ClientHandler> rpcClients=nettyClientFactory.getRouteMap().get(server.getHostPost());
			if(rpcClients==null || rpcClients.getQueueSize()==0){
				//还未创立对象，或者队列里还没有长连接，则一次性创建配置项里的连接
				nettyClientFactory.createMutiClient(remoteAddress);
				int retryCount=0;
				while(retryCount<5){
					//创建连接需要时间,因此等待尝试5次,
					try{Thread.sleep(1500+500*retryCount);}catch(Exception em){}
					rpcClients=nettyClientFactory.getRouteMap().get(server.getHostPost());					
					if(rpcClients!=null){
						return rpcClients.getCircleFront();
					}
					retryCount++;
				}
				return null;
			}
			ClientHandler handle=rpcClients.getCircleFront();
			if(handle!=null){
				return handle;
			}	
			//handle=null,表明连接不存在或已断开,则重新创建连接
			nettyClientFactory.connect(remoteAddress);	
			/**
			 * 同一个远程remote address,本地有多个连接 在循环队列里的连接 远程remote address. 若 连接为null,需要创建,
			 * 依次在队列获取 连接，若队列循环完毕，还未有可用连接,则返回			 * 
			 */
			int retryCount=0;
			while(retryCount<rpcClients.getCapacity()){
				handle=rpcClients.getCircleFront();
				if(handle!=null)
					return handle;
				nettyClientFactory.connect(remoteAddress);	
				retryCount++;
			}
			return null; 
		}

}
