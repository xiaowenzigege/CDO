package com.cdo.business.rpc.zk;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import com.cdo.util.exception.ZookeeperException;

/**
 * 监听zk上的服务
 * 获取服务
 * @author KenelLiu
 *
 */
public class ZookeeperClient {  
    private String groupNode = "CDO";  
    private ZooKeeper zk;  
    private Stat stat = new Stat();  
    private static volatile Map<String,ZkServerData> serviceMap;  
    private ClientWatch clientWatch;
    private String zkConnect;
    private Logger logger=Logger.getLogger(ZookeeperClient.class);
    private int Time_OUT=10000;
    private class ClientWatch implements Watcher{
    	 // 如果发生了"/CDOService"节点下的子节点变化事件, 更新配置server列表, 并重新注册监听  
		public void process(WatchedEvent event) {  
			if(logger.isDebugEnabled())
				logger.debug("client eventType="+event.getType()+",eventState="+event.getState()+",eventPath="+event.getPath());
             if ((event.getType() == EventType.NodeChildrenChanged || event.getType()==EventType.NodeDataChanged)   
                    &&event.getPath().startsWith("/" + groupNode)) {  
                    try {                      
                        updateServerList(clientWatch);                          
                    } catch (Exception e) {  
                        e.printStackTrace();  
                    }  
                }         
             if(event.getType()==EventType.None && event.getState()==Watcher.Event.KeeperState.Expired){
            	 try {
                	 if(zk!=null){
                		 zk.close();zk=null;
                	 }
                	 if(clientWatch==null)
                		 clientWatch=new ClientWatch(); 
					zk = new ZooKeeper(zkConnect, Time_OUT,clientWatch);
					updateServerList(clientWatch);  
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
             }
            }  				   	
    }
    /** 
     * 连接zookeeper 
     */  
    public void connectZookeeper() throws ZookeeperException {    	 
        try {
        	clientWatch=new ClientWatch();
			zk = new ZooKeeper(this.zkConnect,Time_OUT,clientWatch);
			updateServerList(clientWatch);
		} catch (Exception e) {
			throw new ZookeeperException(e.getMessage(), e);
		}  
        
    }  
    
    
    /** 
     * 更新配置server host列表 
     */  
    private void updateServerList(ClientWatch clientWatch){  
          
       Map<String, ZkServerData> newServiceMap=new HashMap<String, ZkServerData>();
       // 获取并监听CDOService的子节点变化             
      try{        
	    	List<String> serviceList = zk.getChildren("/" + groupNode,clientWatch); 
	        for (String serviceNode : serviceList) {  
	            // 获取每个子节点下关联的server地址  
	        	String service="/" + groupNode + "/" + serviceNode;
	            byte[] data = zk.getData(service, false, stat);  
	            String className=new String(data, "utf-8");
	            List<String> hostList = zk.getChildren(service,clientWatch);
	            
	            ZkServerData zkData=new ZkServerData();
	            zkData.setServiceName(serviceNode);
	            zkData.setClassName(className);
	            zkData.setHostList(hostList);
	            
	            newServiceMap.put(serviceNode, zkData);            
	        }  
      }catch(Throwable ex){
    	 logger.error("get node["+groupNode+"] faile,"+ex.getMessage(),ex);
    	  try {
			Thread.sleep(5000);
		} catch (Exception e) {			
			
		}
    	 updateServerList(clientWatch);
      }
        // 替换server列表  
        serviceMap = newServiceMap;  
        try {
        	zk.exists("/" + groupNode, clientWatch);
		} catch (Exception e) {
		}       
    }  
 
    public ZookeeperClient(String zkConect){
    	this.zkConnect=zkConect;
    }
    
    public static Map<String, ZkServerData> getZKServerData(){
    	return serviceMap;
    }
  
  
}   
