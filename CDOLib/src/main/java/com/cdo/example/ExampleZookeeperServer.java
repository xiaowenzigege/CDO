package com.cdo.example;

import java.io.IOException;

import javax.sql.rowset.CachedRowSet;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class ExampleZookeeperServer {
	private String groupNode="Catalog";
	private String[] subNodes={"HD_ReplayDays","LT","OCN"};
	private String[] subNodesValus={"100","http://127.0.0.1:8080/sync/webService/AdDecisionServlet","123"};
	private String[] subNodesNewValus={"400","http://10.27.122.199:8080/sync/webService/AdDecisionServlet","345"};

	public void connZoookper() throws IOException, KeeperException, InterruptedException{
		ZooKeeper zk=new ZooKeeper("localhost:2181,localhost:2182,localhost:2183", 5000, new Watcher(){
			  public void process(WatchedEvent event) {  
	                //忽略 对节点的监听 				
				  System.out.println("eventType="+event.getType()+",eventState="+event.getState()+",eventPath="+event.getPath()); 
	            }  
		});
	 String node="/" + groupNode;
	 if(zk.exists(node, false)==null){
		 System.out.println("znode is not exits, start create"); 
		 zk.create(node,"".getBytes("utf-8"), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);    
	 }	 
	for(int i=0;i<subNodes.length;i++){
		  node="/" + groupNode + "/" + subNodes[i];
		 if(zk.exists(node, false)==null){
			 System.out.println("znode is not exits, start create"); 
			 zk.create(node, subNodesValus[i].getBytes("utf-8"), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);    
		 }else{
			 System.out.println("znode is  exits, start update");
			 zk.setData(node, subNodesNewValus[i].getBytes("utf-8"), -1);
		 }
	}	
	node="/" + groupNode;
	if(zk.exists(node, false)!=null){
		 zk.setData(node, "".getBytes("utf-8"), -1);
	 }
	
  }
	
    public void handle() throws InterruptedException {    
        Thread.sleep(Long.MAX_VALUE);  
    }  
    
    public static void main(String[] args) throws Exception {  
        ExampleZookeeperServer as = new ExampleZookeeperServer();  
        as.connZoookper();            
        as.handle();  
    }  
}
