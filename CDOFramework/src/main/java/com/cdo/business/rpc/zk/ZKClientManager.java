package com.cdo.business.rpc.zk;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.cdo.business.client.IClient;
import com.cdo.business.rpc.client.proto.RPCClient;
import com.cdo.business.rpc.client.xml.XMLRPCClient;
import com.cdo.util.exception.ZookeeperException;

public class ZKClientManager {
	private static ZKClientManager instance=null;
	private Logger logger=Logger.getLogger(ZKClientManager.class);
	private Map<String, ZKRPCClient> zkClientMap=new HashMap<String, ZKRPCClient>();
	
	private ZKClientManager(){

	}
	
	public static  synchronized ZKClientManager getInstance()
	{//使用单列
		if(instance==null)
			instance=new ZKClientManager();
		return instance;
	}
	
	public  synchronized void addConnectZk(String zkConnect){
		addConnectZk(IClient.DEFAULT_ZKID, zkConnect);
	}
	/**
	 * 
	 * @param zkId 标识
	 * @param zkConnect  
     *            comma separated host:port pairs, each corresponding to a zk
     *            server. e.g. "127.0.0.1:3000,127.0.0.1:3001,127.0.0.1:3002" If
     *            the optional chroot suffix is used the example would look
     *            like: "127.0.0.1:3000,127.0.0.1:3001,127.0.0.1:3002/app/a"
     *            where the client would be rooted at "/app/a" and all paths
     *            would be relative to this root - ie getting/setting/etc...
     *            "/foo/bar" would result in operations being run on
     *            "/app/a/foo/bar" (from the server perspective).
	 */
	public  synchronized void addConnectZk(String zkId,String zkConnect){
		try{
			if(zkClientMap.containsKey(zkId)){
				logger.warn("zkId["+zkId+"] is alreay ");
				return ;
			}
			ZKRPCClient rpcClient=new RPCClient();
			ZookeeperClient zkClient=new ZookeeperClient(zkConnect,rpcClient);
			zkClient.connectZookeeper();			
			rpcClient.setZKId(zkId);
			rpcClient.setZKConnect(zkConnect);
			zkClientMap.put(zkId, rpcClient);

		}catch(ZookeeperException ex){
			logger.error("zkId ["+zkId+"],connect zk server ["+zkConnect+"] error:"+ex.getMessage(), ex);
			throw ex;
		}
	}
	
	public IClient getClient(){	
		return getClient(IClient.DEFAULT_ZKID);
	} 
	
	public IClient getClient(String zkId){	
		return zkClientMap.get(zkId);
	} 
	
	public   Set<Map.Entry<String, ZKRPCClient>> entrySetZKClient(){
		return zkClientMap.entrySet();
	}
}
