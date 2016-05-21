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

import com.cdoframework.cdolib.base.IActiveObject;
import com.cdoframework.cdolib.framework.ITaskExecutor;

/**
 * @author Frank
 */
public interface IEventHandler extends IEventRaiser,IActiveObject
{
	void setTaskExecutor(ITaskExecutor taskExecutor);
}
