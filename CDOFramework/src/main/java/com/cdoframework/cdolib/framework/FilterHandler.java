package com.cdoframework.cdolib.framework;

import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;

/**
 * @author Aaron
 */
public class FilterHandler implements IFilter
{

	// 内部类,所有内部类在此声明----------------------------------------------------------------------------------

	// 静态对象,所有static在此声明并初始化------------------------------------------------------------------------
	private static FilterHandler filterHandler=new FilterHandler();

	public static FilterHandler getInstance()
	{
		return filterHandler;
	}

	// 内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------

	// 属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------

	// 引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------
	private IFilter filterImpl;

	public void setFilter(IFilter filter)
	{
		filterImpl=filter;
	}

	public IFilter getFilter()
	{
		return filterImpl;
	}

	// 内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	// 私有方法 所有仅在本类或派生类中使用的函数在此定义为private方法-------------------------------------------

	// 公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------

	// 接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------
	public Return  handlePostEvent(String strServiceName,String strTransName,CDO cdoRequest,CDO cdoResponse,Return transReturn)
	{
		if(filterImpl!=null)
		{
			return filterImpl.handlePostEvent(strServiceName,strTransName,cdoRequest,cdoResponse,transReturn);
		}
		return null;
	}

	public Return handlePreEvent(String strServiceName,String strTransName,CDO cdoRequest)
	{
		if(filterImpl!=null)
		{
			return filterImpl.handlePreEvent(strServiceName,strTransName,cdoRequest);
		}
		return null;
	}

	public Return handlePreTransCache(String strServiceName,String strTransName,CDO cdoRequest,CDO cdoResponse)
	{
		if(filterImpl!=null)
		{
			return filterImpl.handlePreTransCache(strServiceName,strTransName,cdoRequest,cdoResponse);
		}
		return null;
	}

	public void handlePostTransCache(String strServiceName,String strTransName,CDO cdoRequest,CDO cdoResponse)
	{
		if(filterImpl!=null)
		{
			filterImpl.handlePostTransCache(strServiceName,strTransName,cdoRequest,cdoResponse);
		}
	}

	// 事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	// 事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	// 构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	private FilterHandler()
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

}
