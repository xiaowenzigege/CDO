package com.cdoframework.cdolib.datasync.util;

import com.cdoframework.cdolib.servicebus.ServiceBus;


public class DataSynServiceBus {

	private static DataSynServiceBus instance = new DataSynServiceBus();
	
	private ServiceBus serviceBus;

	public ServiceBus getServiceBus() {
		return serviceBus;
	}

	public void setServiceBus(ServiceBus serviceBus) {
		this.serviceBus = serviceBus;
	}
	
	public synchronized static DataSynServiceBus newInstance(){
		if(instance == null){
			return new DataSynServiceBus();
		}
		return instance;
	}
}
