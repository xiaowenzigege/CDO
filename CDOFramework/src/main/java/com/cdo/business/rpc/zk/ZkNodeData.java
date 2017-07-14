package com.cdo.business.rpc.zk;

import com.cdo.util.algorithm.RoundRobinScheduling;
/**
 * serviceName 注册在zk上的 
 * className serviceName对应的class类
 *  通过权重轮询获取提供该[serviceName]服务的remoteAddress
 * @author KenelLiu
 *
 */
public class ZkNodeData {

	private String serviceName;
	private String className;	
	private RoundRobinScheduling robinScheduling;
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public RoundRobinScheduling getRobinScheduling() {
		return robinScheduling;
	}
	public void setRobinScheduling(RoundRobinScheduling robinScheduling) {
		this.robinScheduling = robinScheduling;
	}
	

}
