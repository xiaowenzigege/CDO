package com.cdoframework.cdolib.servicebus;

import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;


/**
 * @author Administrator
 */
public class EventTask
{

	//内部类,所有内部类在此声明----------------------------------------------------------------------------------

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------
	public static int EVENT_SCENE_BEFORE 	=1;
	public static int EVENT_SCENE_ING 		=2;
	public static int EVENT_SCENE_AFTER 	=3;
	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------

	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------

	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------
	private int nEventScene;
	private CDO cdoTransRequest;
	private CDO cdoTransResponse;
	private String	strTransName;
	private String strServiceName;
	private Return transHandleResult;
	private Object OtherTask;

	//普通事件
	private CDO cdoEventRequest;
	private CDO cdoBusinessLogReqeust;
	
	public CDO getEventRequest()
	{
		return this.cdoEventRequest;
	}
	public void setEventRequest(CDO cdoEvent)
	{
		this.cdoEventRequest = cdoEvent;
	}

	public void setEventScene(int nEventScene)
	{
		this.nEventScene = nEventScene;
	}
	public int getEventScene()
	{
		return this.nEventScene;
	}

	public CDO getTransRequest()
	{
		return cdoTransRequest;
	}

	public void setTransRequest(CDO cdoRequest)
	{
		this.cdoTransRequest=cdoRequest;
	}

	public CDO getTransResponse()
	{
		return cdoTransResponse;
	}

	public void setTransResponse(CDO cdoResponse)
	{
		this.cdoTransResponse=cdoResponse;
	}

	public String getTransName()
	{
		return strTransName;
	}

	public void setTransName(String strTransName)
	{
		this.strTransName=strTransName;
	}
	
	public String getServiceName()
	{
		return this.strServiceName;
	}

	public void setServiceName(String strServiceName)
	{
		this.strServiceName=strServiceName;
	}
	public void setTransHanleResult(Return transHandleResult)
	{
		this.transHandleResult = transHandleResult;
	}
	public Return getTransHandleResult()
	{
		return this.transHandleResult;
	}
	
	public CDO getBusinessLogReqeust()
	{
		return this.cdoBusinessLogReqeust;
	}
	public void setBusinessLogReqeust(CDO cdoBusinessLogReqeust)
	{
		this.cdoBusinessLogReqeust = cdoBusinessLogReqeust;
	}
	public Object getOtherTask()
	{
		return OtherTask;
	}
	public void setOtherTask(Object otherTask)
	{
		OtherTask=otherTask;
	}

	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	//私有方法 所有仅在本类或派生类中使用的函数在此定义为private方法-------------------------------------------

	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------

	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------


	public EventTask(int nEventScene)
	{
		this.nEventScene = nEventScene;
	}
	public EventTask(int nEventScene,CDO cdoEvent,boolean bCommon)
	{
		this.nEventScene = nEventScene;
		this.cdoEventRequest = cdoEvent;
	}
	public EventTask(int nEventScene,CDO cdoBusinessLog)
	{
		this.nEventScene = nEventScene;
		this.cdoBusinessLogReqeust = cdoBusinessLog;
	}
	public EventTask(int nEventScene,CDO cdoRequest,CDO cdoResponse,Return returnResult)
	{
		this.nEventScene = nEventScene;
		this.cdoTransRequest = cdoRequest;
		this.cdoTransResponse = cdoResponse;
		this.transHandleResult = returnResult;
	}

}
