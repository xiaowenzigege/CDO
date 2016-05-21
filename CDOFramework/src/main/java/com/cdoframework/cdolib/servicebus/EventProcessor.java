/**
 * www.cdoforum.com 2007版权所有
 *
 * $Header: /CVSData/Frank/CVSROOT/CDOForum/CDOBusiness/Source/com/cdoframework/cdolib/servicebus/business/EventHandler.java,v 1.1 2008/03/05 01:35:53 Frank Exp $
 *
 * $Log: EventHandler.java,v $
 * Revision 1.1  2008/03/05 01:35:53  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2008/01/22 12:23:45  Frank
 * *** empty log message ***
 *
 * Revision 1.2  2007/12/26 12:29:34  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/12/25 14:10:48  Frank
 * *** empty log message ***
 *
 * Revision 1.3  2007/12/15 09:06:55  Frank
 * *** empty log message ***
 *
 * Revision 1.2  2007/08/27 05:56:27  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/08/23 15:40:31  Frank
 * *** empty log message ***
 *
 *
 */

package com.cdoframework.cdolib.servicebus;

import org.apache.log4j.Logger;


import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.framework.ITaskExecutor;
import com.cdoframework.cdolib.framework.Task;

/**
 * @author Frank
 */
public class EventProcessor extends com.cdoframework.cdolib.framework.ThreadPool implements IEventHandler
{

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------
	private static Logger logger = Logger.getLogger(EventProcessor.class);
	
	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------

	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------
	
	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------
	private IServiceBus serviceBus;
	private ITaskExecutor taskExecutor;

	public void setServiceBus(IServiceBus serviceBus)
	{
		this.serviceBus = serviceBus;		
	}
	public void setTaskExecutor(ITaskExecutor taskExecutor)
	{
		this.taskExecutor = taskExecutor;
	}
	
	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------


	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------


	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------
	
	/**
	 * 生成普通事件,加任务
	 * @param cdoEvent
	 */
	public boolean raiseEvent(CDO cdoEvent)
	{
		EventTask eventTask = new EventTask(EventTask.EVENT_SCENE_ING,cdoEvent,true);
		Task task = new Task();
		task.setData(eventTask);
		int nResult = super.addTask(task);
		return nResult==0;
	}

	public void handleTask(Task task)
	{
		EventTask eventTask = (EventTask)task.getData();
		if(eventTask.getEventRequest()!=null)
		{
			this.handleEvent(eventTask.getEventRequest());
			return;
		}
		if(taskExecutor!=null)
		{
			taskExecutor.handleTask(task);
		}

	}

	public boolean addBusinessLog(int secne,CDO cdoBusinessLog)
	{
		if(taskExecutor==null)
		{
			return false;
		}
		EventTask eventTask = new EventTask(secne,cdoBusinessLog);
		Task task = new Task();
		task.setData(eventTask);
		int nResult = super.addTask(task);
		return nResult==0;
	}
	
	public boolean addEventTask(EventTask eventTask)
	{
		if(taskExecutor==null)
		{
			return false;
		}
		Task task = new Task();
		task.setData(eventTask);
		int nResult = super.addTask(task);
		return nResult==0;
	}

	/**
	 * 普通事件处理
	 * @param eventName
	 * @param eventTask
	 */
	protected void handleEvent(CDO cdoEvent)
	{
		try
		{
			this.serviceBus.handleEvent(cdoEvent);
		}
		catch(Exception e)
		{
			logger.error("handleEvent:"+e.getMessage(),e);
		}
	}


	
	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	protected EventProcessor()
	{
		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
	}

}
