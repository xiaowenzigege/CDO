package com.cdo.business.rpc.zk;

import java.util.List;



public class ZkParameter {

	private String zkId;
	private String serviceName;
	private List<String> className;
	public String getZkId() {
		return zkId;
	}
	public void setZkId(String zkId) {
		this.zkId = zkId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public List<String> getClassName() {
		return className;
	}
	public void setClassName(List<String> className) {
		this.className = className;
	}
	
	
	
}
