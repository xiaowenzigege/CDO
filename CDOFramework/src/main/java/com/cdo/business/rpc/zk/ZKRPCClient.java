package com.cdo.business.rpc.zk;

import java.util.Map;

import com.cdo.business.client.IClient;
import com.cdo.business.rpc.zk.ZkNodeData;
/**
 * 根据serviceName,通过权重轮询获取 提供该服务的remoteAddress
 * @author kenelLiu
 *
 */

public abstract class ZKRPCClient implements IClient{
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
}
