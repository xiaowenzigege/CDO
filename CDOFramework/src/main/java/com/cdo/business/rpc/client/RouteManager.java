package com.cdo.business.rpc.client;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.log4j.Logger;
import com.cdo.util.server.Server;
import com.cdo.util.server.ServerScheduling;

public class RouteManager {

	private static Map<String, RPCClient> routeMap=new HashMap<String, RPCClient>();
	private static RouteManager instance=null;
	private static final Logger logger=Logger.getLogger(RouteManager.class); 
	private  ReadWriteLock routeLock = new ReentrantReadWriteLock();  
	public static  synchronized RouteManager getInstance()
	{//使用单列
		if(instance==null)
			instance=new RouteManager();
		return instance;
	}
	
	private RouteManager(){
		
	}
	
	public boolean containKey(String serverAddress){
		return routeMap.containsKey(serverAddress);
	}
	
	/**
	 * 
	 * @param serverAddress=ip:port
	 * @param rpcClient
	 */
	public void removeRPCClient(String serverAddress){
		RPCClient discardRPCClient=null;
		try{
			routeLock.writeLock().lock(); 
			discardRPCClient=routeMap.get(serverAddress);	
			routeMap.remove(serverAddress);
		}catch(Throwable ex){
			logger.error("remove rpclient from routeMap error:"+ex.getMessage(),ex);
		}finally{
			try{
				routeLock.writeLock().unlock();
				if(discardRPCClient!=null)
					discardRPCClient.close();
			}catch (Exception ex){}
		}
		
	}	
	
	/**
	 * 
	 * @param serverAddress=ip:port
	 * @param rpcClient
	 */
	public void addRPCClient(String serverAddress,RPCClient rpcClient){
		RPCClient discardRPCClient=null;
		try{
			routeLock.writeLock().lock(); 
			if(!routeMap.containsKey(serverAddress)){
				routeMap.put(serverAddress, rpcClient);
			}else{
				if(routeMap.get(serverAddress).getHandle()==null){
					discardRPCClient=routeMap.put(serverAddress, rpcClient);
				}else{
					discardRPCClient=rpcClient;
				}				
			}			
		}catch(Throwable ex){
			logger.error("add rpclient to routeMap error:"+ex.getMessage(),ex);
		}finally{
			try{
				routeLock.writeLock().unlock();
				if(discardRPCClient!=null)
					discardRPCClient.close();
			}catch (Exception ex){}
		}		
	}
	
	/**
	 * 获取分发的机器ip:port,根据ip:port获取长连接.
	 * @param serverAddress=ip:port
	 * @return
	 */
	public RPCClient route(ServerScheduling serverScheduling){
		Server server=serverScheduling.getServer();		
		String hostPort=server.getHostPost();			
		//获取数据
		RPCClient rpcClient=routeMap.get(server.getHostPost());
		if(rpcClient!=null)
			return rpcClient;		
		//本机还未与服务连接进行连接成功，尝试连接
		RPCClient rpClient=new RPCClient(hostPort.split(":")[0],Integer.parseInt(hostPort.split(":")[1]));
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
}
