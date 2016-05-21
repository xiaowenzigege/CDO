package com.cdoframework.cdolib.base;

import com.cdoframework.cdolib.data.cdo.CDO;

/**
 * @author Administrator
 */
public class CDOThreadLocal extends ThreadLocal<CDO>
{

	//内部类,所有内部类在此声明----------------------------------------------------------------------------------

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------
	//common key in thread local
	public final static String THREADLOCAL_KEY_IP = "strIP";
	public final static String THREADLOCAL_KEY_USERID = "lUserId";
	public final static String THREADLOCAL_KEY_USERNAME = "strUserName";
	public final static String THREADLOCAL_KEY_REQUEST_SN = "strTransSN";
	
	private static CDOThreadLocal cdoThreadLocal = new CDOThreadLocal(); 
	 
	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------
	
	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------

	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------

	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	//私有方法 所有仅在本类或派生类中使用的函数在此定义为private方法-------------------------------------------

	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------
	@Override
	public CDO initialValue()
	{
		return new CDO();
	}
	public static CDOThreadLocal getInstance()
	{
		return cdoThreadLocal;
	}
	public static String getIP()
	{
		CDO cdo = cdoThreadLocal.get();
		if(!cdo.exists(CDOThreadLocal.THREADLOCAL_KEY_IP))
		{
			return null;
		}
		return cdo.getStringValue(CDOThreadLocal.THREADLOCAL_KEY_IP);
	}
	public static String getUserId()
	{
		CDO cdo = cdoThreadLocal.get();
		if(!cdo.exists(CDOThreadLocal.THREADLOCAL_KEY_USERID))
		{
			return null;
		}
		return cdo.getStringValue(CDOThreadLocal.THREADLOCAL_KEY_USERID);
	}
	public static String getUserName()
	{
		CDO cdo = cdoThreadLocal.get();
		if(!cdo.exists(CDOThreadLocal.THREADLOCAL_KEY_USERNAME))
		{
			return null;
		}
		return cdo.getStringValue(CDOThreadLocal.THREADLOCAL_KEY_USERNAME);
	}
	public static String getRequestSN()
	{
		CDO cdo = cdoThreadLocal.get();
		if(!cdo.exists(CDOThreadLocal.THREADLOCAL_KEY_REQUEST_SN))
		{
			return null;
		}
		return cdo.getStringValue(CDOThreadLocal.THREADLOCAL_KEY_REQUEST_SN);
	}
	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	private CDOThreadLocal()
	{
		super();
	}

}
