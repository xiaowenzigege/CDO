package com.cdo.business.rpc.client;

import java.util.Map;

import com.cdo.business.client.IClient;
import com.cdo.business.rpc.zk.ZkServerData;
/**
 * RPC Client有额外需要实现的接口在此定义
 * @author kenelLiu
 *
 */

public abstract class AbstractRPCClient implements IClient{
	/**<serviceName,ZkServerData>**/
	protected Map<String, ZkServerData> serviceMap;
	
	public void setServiceMap(Map<String, ZkServerData> serviceMap){
		this.serviceMap=serviceMap;
	}
	
	protected Map<String, ZkServerData> getServiceMap(){
		return this.serviceMap;
	}
	
	
}
