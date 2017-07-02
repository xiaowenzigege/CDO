package com.cdo.business.rpc.zk;

import com.cdo.util.algorithm.RoundRobinScheduling;

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
