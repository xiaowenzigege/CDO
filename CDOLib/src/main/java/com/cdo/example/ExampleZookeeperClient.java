package com.cdo.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;


public class ExampleZookeeperClient {  
    private String groupNode = "Catalog";  
    private ZooKeeper zk;  
    private Stat stat = new Stat();  
    private volatile List<String> serverList;  
    private ClientWatch clientWatch;
    
    private class ClientWatch implements Watcher{    	
		public void process(WatchedEvent event) {
                // 如果发生了"/Catalog"节点下的子节点变化事件, 更新配置server列表, 并重新注册监听  
            System.out.println("client eventType="+event.getType()+",eventState="+event.getState()+",eventPath="+event.getPath());
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
					zk = new ZooKeeper("localhost:2182", 5000,clientWatch);
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
    public void connectZookeeper() throws Exception {
    	clientWatch=new ClientWatch(); 
        zk = new ZooKeeper("localhost:2182", 5000,clientWatch);  
        updateServerList(clientWatch);  
    }  
    /** 
     * 更新配置server列表 
     */  
    private void updateServerList(ClientWatch clientWatch){  
        List<String> newServerList = new ArrayList<String>();       
        // 获取并监听groupNode的子节点变化             
      try{        
    	 List<String> subList = zk.getChildren("/" + groupNode,clientWatch); 
        for (String subNode : subList) {  
            // 获取每个子节点下关联的server地址  
            byte[] data = zk.getData("/" + groupNode + "/" + subNode, false, stat);  
            newServerList.add("/" + groupNode + "/" + subNode+"="+new String(data, "utf-8"));  
        }  
      }catch(Throwable ex){
    	  System.out.println("get node["+groupNode+"] faile,"+ex.getMessage());
    	  try {
			Thread.sleep(5000);
		} catch (Exception e) {			
			
		}
    	 updateServerList(clientWatch);
      }
        // 替换server列表  
        serverList = newServerList;  
        try {
        	zk.exists("/" + groupNode, clientWatch);
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("server list updated: " + serverList);  
    }  
  
    /** 
     * client的工作逻辑写在这个方法中 
     * 此处不做任何处理, 只让client sleep 
     */  
    public void handle() throws InterruptedException {  
        Thread.sleep(Long.MAX_VALUE);  
    }  
  
    public static void main(String[] args) throws Exception {  
        ExampleZookeeperClient ac = new ExampleZookeeperClient();  
        ac.connectZookeeper();          
        ac.handle();  
    }  
}   
