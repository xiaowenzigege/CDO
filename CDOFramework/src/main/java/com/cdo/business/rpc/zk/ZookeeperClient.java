package com.cdo.business.rpc.zk;

import io.netty.util.internal.SystemPropertyUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import com.cdo.business.rpc.client.NettyStop;
import com.cdo.business.rpc.client.NettyClientFactory;
import com.cdo.business.rpc.client.RPCClient;
import com.cdo.business.rpc.client.RouteManager;
import com.cdo.util.algorithm.RoundRobinScheduling;
import com.cdo.util.cache.LRUCache;
import com.cdo.util.constants.Constants;
import com.cdo.util.exception.ZookeeperException;
import com.cdo.util.server.Server;

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
    private ClientWatch clientWatch;
    private ZKRPCClient rpcClient;
    private String zkConnect;
    private Logger logger=Logger.getLogger(ZookeeperClient.class);
	
    private RouteManager routeManager=RouteManager.getInstance();
    
    private int Time_OUT=Math.max(10, SystemPropertyUtil.getInt("zk.sessionTimeout", 10))*1000;
    private class ClientWatch implements Watcher{
    	 // 如果发生了"/CDO"节点下的子节点变化事件, 更新配置server列表, 并重新注册监听  
		public void process(WatchedEvent event) {  
			if(logger.isDebugEnabled())
				logger.debug("client eventType="+event.getType()+",eventState="+event.getState()+",eventPath="+event.getPath());
             if ((event.getType() == EventType.NodeChildrenChanged || event.getType()==EventType.NodeDataChanged)   
                    &&event.getPath().startsWith("/" + groupNode)) {  
                    try {                      
                        updateServerList(clientWatch);                          
                    } catch (Exception e) {
                    	logger.error("zk eventType="+event.getType()+",path="+event.getPath()+",error:"+e.getMessage(),e);                        
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
					logger.error("zk eventType="+event.getType()+",path="+event.getPath()+",error:"+e.getMessage(),e);  
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
       //ServiceNode,ZkNodeData
       Map<String, ZkNodeData> newServiceMap=new HashMap<String, ZkNodeData>();       
       //更新列表 客户端调用多个服务   ，多个服务存在 使用同一台机器[ip:port]，因此根据服务权重轮询时，共享server数据.
       LRUCache<String,Server> serverMap=new LRUCache<String ,Server>(1000);
       //获取并监听CDOService的子节点变化             
      try{        
		   	String rootNode="/" + groupNode;
		   	if(zk.exists(rootNode, false)==null){   		  
		   		 zk.create(rootNode, "".getBytes("utf-8"), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);    		   	
		   	}	
	    	List<String> serviceList = zk.getChildren(rootNode,clientWatch); 
	        for (String serviceNode : serviceList) {  
	            // 获取每个子节点service下关联的server服务器地址 及权重  
	        	String service=rootNode+ "/" + serviceNode;
	            byte[] data = zk.getData(service, false, stat);  
	            String className=new String(data, "utf-8");
	            List<String> hostList = zk.getChildren(service,clientWatch);
	            
	            
	            ZkNodeData zkData=new ZkNodeData();
	            zkData.setServiceName(serviceNode);
	            zkData.setClassName(className);
	            if(hostList!=null && hostList.size()>0){
	            	//提供该接口的服务地址,使用权重轮询获取服务地址
	            	List<Server> serverList=new ArrayList<Server>();
	            	String[] array=null;
	            	String hostPort=null;
	            	Server server=null;
	            	for(int i=0;i<hostList.size();i++){
	            		array=hostList.get(i).split("w=");
	            		hostPort=array[0].trim();
	            		int weight=1;
	            		if(array.length>1)
	            			weight=array[1]==null?1:Integer.parseInt(array[1].trim());
	            		
	            		//还未建立长连接,可以预建立 多个连接
	            		if(!routeManager.containKey(hostPort) && !serverMap.containsKey(hostPort)){	
	            			//初始建立
	            			server=new Server(array[0].trim(),weight);
	            			serverMap.put(hostPort, server);
	            			
	            			NettyClientFactory.getDefaultInstance().createMutiClient(hostPort);
	            		}else{
		            		if(serverMap.containsKey(hostPort) &&
		            				serverMap.get(hostPort).getWeight()==weight){
		            			//服务器提供的 权重未发生变化，则使用原有的.
		            			server=serverMap.get(hostPort);
		            		}else{
		            			//初始建立，或者权重发生变化
		            			server=new Server(array[0].trim(),weight);
		            			serverMap.put(hostPort, server);
		            		}		            			
	            			
	            		}
	            		serverList.add(server);
	            	}	
	            	zkData.setRobinScheduling(new RoundRobinScheduling(serverList));
	            }       
	            newServiceMap.put(serviceNode, zkData);            
	        }  
      }catch(Throwable ex){
    	 logger.error("get node["+groupNode+"] fail,5 seconds reset.error message="+ex.getMessage(),ex);
    	try {
			Thread.sleep(5000);
		} catch (Exception e) {			
			
		}
    	 updateServerList(clientWatch);
      }
        // 替换server列表  
      	rpcClient.setServiceMap(newServiceMap);  
      	watchRootNode(clientWatch);    
    }  
 
    private void watchRootNode(ClientWatch clientWatch){
        try {
        	zk.exists("/" + groupNode, clientWatch);
		} catch (Throwable ex) {
			logger.error("listen node ["+groupNode+"] fail,10 seconds reset.error message="+ex.getMessage(),ex);
	    	try {
				Thread.sleep(10000);
			} catch (Exception e) {							
			}
		}    
    }
    
    public ZookeeperClient(String zkConect,ZKRPCClient rpcClient){
    	this.zkConnect=zkConect;
    	this.rpcClient=rpcClient;
    }
      

  
}   
