package com.cdoframework.cdolib.servicebus;

import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;

public interface IService
{
	/**
	 * 处理一个事务
	 * @param cdoRequest 事务请求对象，事务名用strTransName区分开
	 * @param cdoResponse 事务应答对象
	 * @return 事务处理结果，如果事务名不被支持，则返回null
	 */
	Return handleTrans(CDO cdoRequest,CDO cdoResponse);

	/**
	 * 调用SQL事务处理的方法
	 * ServiceBus会根据Request内的数据和ServiceBus.xml的配置内容，自动查找到对应的插件，调用其对象提供的处理方法
	 * @param cdoRequest 事务请求对象
	 * @param cdoResponse 事务应答对象
	 * @return 事务处理结果
	 */
	Return handleDataTrans(CDO cdoRequest,CDO cdoResponse);
	
	/**
	 * 处理一个事务
	 * @param cdoRequest 事务请求对象，事务名用strTransName区分开
	 * @param cdoResponse 事务应答对象
	 * @return 事务处理结果，如果事务名不被支持，则返回null
	 */
	void handleEvent(CDO cdoEvent);

	String getServiceName();
	
	Return start();
	void stop();
	void destroy();	
}
