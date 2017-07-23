package com.cdo.business.rpc.zk;

import java.util.HashMap;
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
/**
 * 根据serviceName,通过权重轮询获取 提供该服务的remoteAddress
 * @author kenelLiu
 *
 */

public abstract class ZKRPCClient implements IClient{
	private Logger logger=Logger.getLogger(ZKRPCClient.class);
	/**<serviceName,ZkNodeData>**/
	static Map<String, ZkNodeData> serviceMap=new HashMap<String, ZkNodeData>();
	/**注册到zk上的连接**/
	protected String zkConnect;
	/**自定义的zkId**/
	protected String zkId;
	
	 void setServiceMap(Map<String, ZkNodeData> paramMap){
		serviceMap.clear();
		serviceMap.putAll(paramMap);
	}
	
	public Map<String, ZkNodeData> getServiceMap(){
		
		return serviceMap;
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
	 
	protected  ClientHandler getRPCClient(String strServiceName) throws NotEstablishConnectException{
			Map<String, ZkNodeData>  zkServerMap=getServiceMap();
			if(zkServerMap==null || zkServerMap.get(strServiceName)==null || zkServerMap.get(strServiceName).getRobinScheduling()==null){
				logger.warn("Service["+strServiceName+"] not registered on zk server");
				throw new NotEstablishConnectException("Service["+strServiceName+"] not registered on zk server");	
			}					
			RoundRobinScheduling roundSchedule=zkServerMap.get(strServiceName).getRobinScheduling();
			Server server=roundSchedule.getServer();		
			String remoteAddress=server.getHostPost();	
			CircleRPCQueue<ClientHandler> rpcClients=NettyClientFactory.getDefaultInstance().getRouteMap().get(server.getHostPost());
			if(rpcClients==null || rpcClients.getQueueSize()==0){
				//还未创立对象，或者队列里还没有长连接，创建连接
				NettyClientFactory.getDefaultInstance().createMutiClient(remoteAddress);
				return null;
			}
			ClientHandler handle=rpcClients.getCircleFront();
			if(handle!=null){
				return handle;
			}	
		    //若队列里的该连接 为null，即有可能连接中断被移除,需重新创建连接
			NettyClientFactory.getDefaultInstance().connect(remoteAddress);	
			return null; 
		}

}
