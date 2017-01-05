package com.cdo.business.rpc.zk;

import java.util.List;

public class ZkServerData {

	private String serviceName;
	private String className;
	private List<String> hostList;
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
	public List<String> getHostList() {
		return hostList;
	}
	public void setHostList(List<String> hostList) {
		this.hostList = hostList;
	}

}
