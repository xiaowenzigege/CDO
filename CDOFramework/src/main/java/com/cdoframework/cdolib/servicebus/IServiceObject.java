
package com.cdoframework.cdolib.servicebus;



/**
 * @author Frank
 */
public interface IServiceObject
{
	/**
	 * 传入服务总线对象的引用
	 * @param serviceBus 服务总线对象
	 */
	void setServiceBus(IServiceBus serviceBus);

	/**
	 * 传入服务插件对象的引用
	 * @param servicePlugin 服务插件对象
	 */
	void setServicePlugin(IServicePlugin servicePlugin);
	
	void setService(IService service);
}
