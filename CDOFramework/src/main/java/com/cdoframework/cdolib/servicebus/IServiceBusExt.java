package com.cdoframework.cdolib.servicebus;

import net.spy.memcached.MemcachedClient;

import com.cdoframework.cdolib.database.BigTableEngine;
import com.cdoframework.cdolib.database.INoSQLDataEngine;

public interface IServiceBusExt extends IServiceBus
{
	/**
	 * 
	 * @return 
	 */
	public BigTableEngine getBigTableEngine();
	
	/**
	 * 
	 * @return
	 */
	public MemcachedClient getMemCachedClient();
	
	/**
	 * 
	 * @return
	 */
	public INoSQLDataEngine getDefaultNoSQLDataEngine();
}
