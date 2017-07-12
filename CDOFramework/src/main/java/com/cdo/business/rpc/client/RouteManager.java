package com.cdo.business.rpc.client;

import java.util.HashMap;
import java.util.Map;


import org.apache.log4j.Logger;

import com.cdo.util.constants.Constants;
import com.cdo.util.server.Server;
import com.cdo.util.server.ServerScheduling;

import io.netty.util.internal.SystemPropertyUtil;

public class RouteManager {

	private   Map<String, CircleRPCQueue<RPCClientHandler>> routeMap;
	private final static RouteManager instance=new RouteManager();
	private static final Logger logger=Logger.getLogger(RouteManager.class); 	
//	private  ReadWriteLock routeLock = new ReentrantReadWriteLock();  
	private int queueCapacity=1;
	public static  synchronized RouteManager getInstance()
	{//使用单列
		return instance;
	}
	/**
	 * 在一个jvm里开启  多个client  连接   同一个服务端
	 * serverAddress 作为key
	 */
	private RouteManager(){
		queueCapacity=Math.max(1, SystemPropertyUtil.getInt(Constants.Netty.THREAD_CLIENT_CONNECT_ConnCount,1));
		routeMap=new HashMap<String, CircleRPCQueue<RPCClientHandler>>();
	}
	
	public boolean containKey(String serverAddress){
		return routeMap.containsKey(serverAddress);
	}
	
	public boolean isFullCircleQueue(String serverAddress){
		return routeMap.get(serverAddress)==null?false:routeMap.get(serverAddress).isFull();
	}
	/**
	 * 在一个jvm里开启  多个client  连接   同一个服务端
	 * @param serverAddress=ip:port
	 * @param rpcClient
	 */
	public boolean removeRPCClient(String serverAddress,RPCClientHandler handle){
		synchronized (serverAddress){
			CircleRPCQueue<RPCClientHandler> circleQueue=routeMap.get(serverAddress);
			if(circleQueue!=null){
				return circleQueue.deleteRPCHandle(handle);		
			}
		}	
		return false;
		
	}	
	
	/**
	 * 在一个jvm里开启  多个client  连接   同一个服务端
	 * serverAddress 作为key,对应了多个client
	 * @param serverAddress
	 * @param handle
	 */
	public boolean addRPCClientHandler(String serverAddress,RPCClientHandler handle){	
		synchronized (serverAddress){
			CircleRPCQueue<RPCClientHandler> circleQueue=routeMap.get(serverAddress)==null?
						new CircleRPCQueue<RPCClientHandler>(queueCapacity):routeMap.get(serverAddress);
			boolean flag=circleQueue.addRPCHandle(handle);
			routeMap.put(serverAddress, circleQueue);
			if(!flag){
				logger.warn("add handle to CircleRPCQueue fail,queue size="+circleQueue.getQueueSize()+",queue capacity="+circleQueue.getCapacity());				
			}
			return flag;
		}			
	}
	
	/**
	 * 获取分发的机器ip:port,根据ip:port获取长连接.
	 * @param serverAddress=ip:port
	 * @return
	 */
	public RPCClientHandler route(ServerScheduling serverScheduling){
		Server server=serverScheduling.getServer();		
		String remoteAddress=server.getHostPost();			
		//获取数据
		CircleRPCQueue<RPCClientHandler> rpcClients=routeMap.get(server.getHostPost());
		if(rpcClients!=null){
			RPCClientHandler handle=rpcClients.getCircleFront();
			if(handle!=null){
				return handle;
			}			
		}
	    //本机还未与服务连接进行连接成功，尝试建立连接
		NettyClientFactory.getDefaultInstance().connect(remoteAddress);		
		return null;		
	
	}
}
