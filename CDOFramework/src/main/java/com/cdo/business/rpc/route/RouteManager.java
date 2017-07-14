package com.cdo.business.rpc.route;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.log4j.Logger;

import com.cdo.business.rpc.client.NettyClientFactory;
import com.cdo.business.rpc.client.RPCClientHandler;
import com.cdo.util.constants.Constants;
import com.cdo.util.server.Server;
import com.cdo.util.server.ServerScheduling;

import io.netty.util.internal.SystemPropertyUtil;

public class RouteManager {
	/**
	 * String=remoteAddress   CircleRPCQueue=同一个客户端与同一个服务端，建立多个长连接
	 */
	private   Map<String, CircleRPCQueue<RPCClientHandler>> routeMap;
	private final static RouteManager instance=new RouteManager();
	private static final Logger logger=Logger.getLogger(RouteManager.class); 	
	private  ReadWriteLock routeLock = new ReentrantReadWriteLock();
	/**
	 * 在jvm里   client 同时开启多少个长连接  连接同一个remoteAddress,默认1.
	 * 与@see {@linkplain com.cdo.business.rpc.client.NettyClientFactory}
	 *     maxClientCount  是一样。将同一服务端[remoteAddress]   放入多个 长连接.
	 */
	private int maxClientCount=1;
	public static  synchronized RouteManager getInstance()
	{//使用单列
		return instance;
	}
	/**
	 * 在一个jvm里开启  多个client  连接   同一个服务端
	 * serverAddress 作为key
	 */
	private RouteManager(){
		maxClientCount=Math.max(1, SystemPropertyUtil.getInt(Constants.Netty.THREAD_CLIENT_CONNECT_ConnCount,1));
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
		try{
			routeLock.writeLock().lock();
			CircleRPCQueue<RPCClientHandler> circleQueue=routeMap.get(serverAddress);
			if(circleQueue!=null){
				return circleQueue.deleteRPCHandle(handle);		
			}
			return false;
		}finally{
			routeLock.writeLock().unlock();
		}
	}	
	
	/**
	 * 在一个jvm里开启  多个client  连接   同一个服务端
	 * serverAddress 作为key,对应了多个client
	 * @param serverAddress
	 * @param handle
	 */
	public boolean addRPCClientHandler(String serverAddress,RPCClientHandler handle){	
		try{
			routeLock.writeLock().lock();
			CircleRPCQueue<RPCClientHandler> circleQueue=routeMap.get(serverAddress)==null?
						new CircleRPCQueue<RPCClientHandler>(maxClientCount):routeMap.get(serverAddress);
			boolean flag=circleQueue.addRPCHandle(handle);
			routeMap.put(serverAddress, circleQueue);
			if(!flag){				
				logger.warn("add handle to CircleRPCQueue fail,queue size="+circleQueue.getQueueSize()+",queue capacity="+circleQueue.getCapacity());				
			}
			return flag;
		  }finally{
				routeLock.writeLock().unlock();
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
		if(rpcClients==null || rpcClients.getQueueSize()==0){
			//还未创立对象，或者队列里还没有长连接，创建连接
			NettyClientFactory.getDefaultInstance().createMutiClient(remoteAddress);
			return null;
		}
		//获取连接
		RPCClientHandler handle=rpcClients.getCircleFront();
		if(handle!=null){
			return handle;
		}	
	    //若队列里的该连接 为null，即有可能连接中断被移除,需重新创建连接
		NettyClientFactory.getDefaultInstance().connect(remoteAddress);		
		return null;		
	
	}
}
