package com.cdo.business.rpc.zk;

import io.netty.util.internal.SystemPropertyUtil;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

import com.cdo.util.exception.ZookeeperException;
import com.cdo.util.resource.GlobalResource;
import com.cdo.util.system.SystemUtil;


/**
 * 
 * @author KenelLiu
 * 将service服务注册到zk 集群上
 */
public class ZookeeperServer {
	//服务放置在 zk的 /CDO 目录下
	private String groupNode="CDO";
	private static Logger logger=Logger.getLogger(ZookeeperServer.class);
	
	private ZooKeeper zk;  
	private Watch clientWatch;
	
	private String address;//提供service的服务地址  host:port
	private int weight=1;
	private List<ZkParameter> zkParameterList;
	private String zkConnect;
	private int Time_OUT=Math.max(10, SystemPropertyUtil.getInt("zk.sessionTimeout", 10))*1000;
	/**
	 * 
	 * 检查zk是否已经断开，如果断开,则重新连接
	 *
	 */
    private class Watch implements Watcher{    	
		public void process(WatchedEvent event) {                                   
             if(event.getType()==EventType.None && event.getState()==Watcher.Event.KeeperState.Expired){
            	 try {
                	 if(zk!=null){
                		 zk.close();zk=null;
                	 }
                	 if(clientWatch==null)
                		 clientWatch=new Watch(); 
					zk = new ZooKeeper(zkConnect, Time_OUT,clientWatch);
					checkServerNode(clientWatch);  
				} catch (Exception e) {				
					logger.error("watch is error :"+e.getMessage(), e);
				} 
             }
            }  				   	
    }	
        
    /** 
     * 连接zookeeper 
     */  
    public  void connectZookeeper() throws ZookeeperException {
    	try{
	    	clientWatch=new Watch(); 
	        zk = new ZooKeeper(zkConnect, Time_OUT,clientWatch);  
	        checkServerNode(clientWatch);
	        String serviceName="";
			for(int i=0;i<zkParameterList.size();i++){
	    		ZkParameter zkParameter=zkParameterList.get(i);
	    		if(i>0)
	    			serviceName=serviceName+",";
	    		if(i>0&i%5==0)
	    			serviceName=serviceName+"\r\n";
	    		serviceName=serviceName+zkParameter.getServiceName();
	    	}
        	if(logger.isInfoEnabled())
        		logger.info("\r\n将["+this.address+"]上的服务["+serviceName+"] 注册到 ZK["+zkConnect+"]上,服务器权重值为="+this.weight);
    	}catch(Exception ex){
    		throw new ZookeeperException(ex.getMessage(),ex);
    	}
    }
 
    /** 
     * 更新配置server列表 
     */  
    private void checkServerNode(Watch clientWatch){     	
    	try{
    		//创建根节点
		   	String node="/" + groupNode;
		   	if(zk.exists(node, false)==null){   		  
		   		 zk.create(node, "".getBytes("utf-8"), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);    		   	
		   	}		    	  
		   	String checkServiceNode=null;
			for(int i=0;i<zkParameterList.size();i++){
		    		ZkParameter zkParameter=zkParameterList.get(i);
		    		String serviceName=zkParameter.getServiceName();
		    		String className="";
		    		List<String> listClassName=zkParameter.getClassName();
		    		if(listClassName!=null){
		    			for(int k=0;k<listClassName.size();k++){
		    				if(k>0)
		    					className=className+";";
		    				className=className+listClassName.get(k);
		    			}
		    		}		    		
		    	 	//服务
			  		 node="/" + groupNode + "/" +serviceName;
			  		 if(zk.exists(node, false)==null){	  			 
			  			 zk.create(node, className.getBytes("utf-8"), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);    
			  		 }else{
			  			 zk.setData(node, className.getBytes("utf-8"), -1);
			  		 }
			  		 //在zk上保存提供服务的地址及权重
			  		 node="/" + groupNode + "/" +serviceName+"/"+this.address+" w="+this.weight;
			  		 if(zk.exists(node, false)==null){	  			 
			  			 zk.create(node, "".getBytes("utf-8"), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);    
			  		 }	
			  		 
			  		checkServiceNode="/" + groupNode + "/" +serviceName;
		    	}    	
			  	node="/" + groupNode;
			  	if(zk.exists(node, false)!=null){
			  		 zk.setData(node, "".getBytes("utf-8"), -1);
			  	}
			  	//停留5秒检查一下 服务是否已经注册,若未正确注册 进行重试
			  	Thread.sleep(5000);
			  	if(checkServiceNode!=null){
			  		List<String> addressList = zk.getChildren(checkServiceNode,clientWatch);  
			  		if(addressList==null || addressList.size()==0)
			  			checkServerNode(clientWatch);
			  	}
	    	}catch(Throwable ex){	    		
	    		logger.error("zk connect is error"+ex.getMessage(),ex);
	    		try {
					Thread.sleep(8000);
				} catch (InterruptedException e) {				
				}
	    		checkServerNode(clientWatch);
    	}
    } 
    
    
    public ZookeeperServer(String zkConnect,List<ZkParameter> zkParameterList,int weight){
    	this.zkConnect=zkConnect;
    	this.zkParameterList=zkParameterList;
    	if(weight>0)
    		this.weight=weight;
    	this.address=getServerIp()+":"+GlobalResource.cdoConfig.getString("netty.server.port").trim();
    }
	
	
	public  String  getServerIp(){
		String SERVER_IP=GlobalResource.cdoConfig.getString("netty.server.ip");
		if(SERVER_IP!=null && SERVER_IP.trim().equals(""))
			return SERVER_IP;
		return SystemUtil.getLocalServerIP();
	}
}
