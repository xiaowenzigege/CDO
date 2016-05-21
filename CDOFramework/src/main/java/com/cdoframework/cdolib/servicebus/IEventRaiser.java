/**
 * www.cdoforum.com 2007版权所有
 *
 * $Header: /CVSData/Frank/CVSROOT/CDOForum/CDOBusiness/Source/com/cdoframework/cdolib/servicebus/business/EventHandler.java,v 1.1 2008/03/05 01:35:53 Frank Exp $
 *
 * $Log: EventHandler.java,v $
 *
 *
 */

package com.cdoframework.cdolib.servicebus;


import com.cdoframework.cdolib.data.cdo.CDO;

/**
 * @author Frank
 */
public interface IEventRaiser 
{
	/**
	 * 生成普通事件,加任务
	 * @param cdoEvent
	 */
	boolean raiseEvent(CDO cdoEvent);
	
	/**
	 * 生成加业务日志事件,加任务
	 * @param nSecne 参考Event常量
	 * @param cdoBusinessLog
	 */
	boolean addBusinessLog(int nSecne, CDO cdoBusinessLog);
	
	boolean addEventTask(EventTask eventTask);
}
