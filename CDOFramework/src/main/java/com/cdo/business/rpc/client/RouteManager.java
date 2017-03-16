package com.cdo.business.rpc.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class RouteManager {

	static Map<String, Integer> routeMap=new HashMap<String, Integer>();
	private static RouteManager instance=null;
//	private Logger logger=Logger.getLogger(RouteManager.class);
	public static  synchronized RouteManager getInstance()
	{//使用单列
		if(instance==null)
			instance=new RouteManager();
		return instance;
	}
	
	private RouteManager(){
		
	}
	/**
	 * 均匀使用连接
	 * @param serviceName
	 * @param hostList
	 * @return
	 */
	public  RPCClient route(String serviceName,List<String> hostList){
		int index=getIndex(serviceName, hostList);
		String address=hostList.get(index);
		routeMap.put(serviceName, index);
		
		RPCClient rpcClient=RPCClient.getClients().get(address);
		if(rpcClient!=null)
			return rpcClient;

		//未找到 服务连接任何一台机器
		RPCClient rpClient=new RPCClient(address.split(":")[0],Integer.parseInt(address.split(":")[1]));
		rpClient.init();
		int retry=1;
		while (retry<5) {
			try {
				if(retry==1)
					Thread.sleep(1500);
				if(rpClient.getHandle()!=null)
					break;
				Thread.sleep(1000+500*retry);
			} catch (InterruptedException e) {
			}
			retry++;
		}
		return rpClient;
	}
	
	private int getIndex(String serviceName,List<String> hostList){
		int total=hostList.size();
		int index=routeMap.get(serviceName)==null?0:routeMap.get(serviceName);
		if(index>=(total-1))
			index=0;
		else
			index++;
		return index;
	}	
}
