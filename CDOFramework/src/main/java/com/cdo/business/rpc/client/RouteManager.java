package com.cdo.business.rpc.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.cdo.util.algorithm.CircleQueue;

public class RouteManager {

	static Map<String, Integer> routeMap=new HashMap<String, Integer>();
	private static RouteManager instance=null;
	private static final Logger logger=Logger.getLogger(RouteManager.class); 
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
		String serverAddress=hostList.get(index);
		routeMap.put(serviceName, index);
	
		CircleQueue<RPCClient> rpcClients=RPCClient.getClients().get(serverAddress);
		RPCClient rpcClient;
		if(rpcClients!=null){
			rpcClient=getCircleQueue(rpcClients,serverAddress);
			if(rpcClient!=null){
				return rpcClient;
			}
		}
		//未找到 服务连接任何一台机器
		RPCClient rpClient=new RPCClient(serverAddress.split(":")[0],Integer.parseInt(serverAddress.split(":")[1]));
		rpClient.init();
		int retry=1;
		while ( retry<5) {
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
	/**
	 * 客户端主动建立多个tcp通道
	 * @param rpcClients
	 * @return
	 */
	private RPCClient getCircleQueue(CircleQueue<RPCClient> rpcClients,String serverAddress){
  	 
  	  RPCClient rpcClient=null;
  	  boolean isAddChannel=false;//客户端增加通道连接
  	  int circleNum=0;
  	  try{
      	  while(true){		        		 
      		  rpcClient=rpcClients.getCircleFront();	
      		  if(rpcClient!=null && rpcClient.getHandle()!=null)
      			  break;
      		  if(rpcClient==null){
      			isAddChannel=true;
      		  }		
      		  circleNum++;
      		  if(circleNum>RPCClient.channelNum)
      			  break;        			  
      	  }	
  	  }catch(Exception ex){
  		  logger.warn("get rpclient to circleQueue error:"+ex.getMessage());
  	  }
  	  if(rpcClient!=null && isAddChannel){
  		  //至少有一条可用通道，才可增加新的通道，否则进行同步增加通道
  		RPCClient rpClient=new RPCClient(serverAddress.split(":")[0],Integer.parseInt(serverAddress.split(":")[1]));
  		rpClient.init();
  	  }
  	  return rpcClient;
	}
	/**
	 * 提供serviceName服务有多少个地址
	 * @param serviceName
	 * @param hostList
	 * @return
	 */
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
