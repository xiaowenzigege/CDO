package com.cdoframework.cdolib.framework;

import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;

import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;


/**
 * @author Frank
 */
public abstract class RMIService extends UnicastRemoteObject implements IRMIService
{

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------

	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------

	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------

	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------

	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------
	protected String getClientIPAddress()
	{
		//获取客户端IP
		String strClientIP = "";
		try
		{
			strClientIP = RemoteServer.getClientHost();
		}
		catch(ServerNotActiveException e)
		{
		}
		
		return strClientIP;
	}

	public CDO handleTrans(CDO cdoRequest)
	{
		CDO cdoResponse=new CDO();
		Return ret=handleTrans(cdoRequest,cdoResponse);
		
		CDO cdoReturn=new CDO();
		CDO cdoOutput=new CDO();
		try
		{
			cdoReturn.setIntegerValue("nCode",ret.getCode());
			cdoReturn.setStringValue("strText",ret.getText());
			cdoReturn.setStringValue("strInfo",ret.getInfo());

			cdoOutput.setCDOValue("cdoResponse",cdoResponse);
			cdoOutput.setCDOValue("cdoReturn",cdoReturn);
		}
		catch(Exception e)
		{
		}
		
		return cdoOutput;
	}
	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------

	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------
	public abstract Return handleTrans(CDO cdoRequest,CDO cdoResponse);

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public RMIService() throws RemoteException 
	{
		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
	}

}
