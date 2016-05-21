package com.cdoframework.cdolib.framework;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;

import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;


/**
 * @author Frank
 */
public class RMIClient
{

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------

	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------
	private HashMap hmServiceObject;

	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------
	protected String strHost;
	public void setHost(String strHost)
	{
		this.strHost=strHost;
	}
	public String getHost()
	{
		return strHost;
	}

	protected int nPort;
	public void setPort(int nPort)
	{
		this.nPort=nPort;
	}
	public int getPort()
	{
		return nPort;
	}

	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------

	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------
	private IRMIService getServiceObject(String strServiceName)
	{
		return getServiceObject(strServiceName,false);
	}

	private IRMIService getServiceObject(String strServiceName,boolean bRebind)
	{
		IRMIService transHandler=null;

		synchronized(hmServiceObject)
		{
			transHandler=(IRMIService)hmServiceObject.get(strServiceName.toUpperCase());
		}
		if(bRebind==false)
		{
			if(transHandler!=null)
			{
				return transHandler;
			}
		}
		
		try
		{
			Registry registry = LocateRegistry.getRegistry(strHost,nPort);
			transHandler=(IRMIService)registry.lookup(strServiceName.toUpperCase());
			synchronized(hmServiceObject)
			{
				hmServiceObject.put(strServiceName.toUpperCase(),transHandler);
			}
		}
		catch(Exception e)
		{
			onException("Get ServiceObject Exception: "+e.getMessage());
			return null;
		}
		
		return transHandler;
	}

	/**
	 * 交易处理
	 * @param cdoRequest	交易请求对象
	 * @return 交易应答对象，内置Return
	 */
	private CDO handleTrans(String strServiceName,CDO cdoRequest,boolean bRebind)
	{
		CDO cdoOutput = null;

		IRMIService transHandler	=this.getServiceObject(strServiceName,bRebind);
		if(transHandler==null)
		{
			cdoOutput=new CDO();

			CDO cdoReturn=new CDO();
			try
			{
				cdoReturn.setIntegerValue("nCode",-1);
				cdoReturn.setStringValue("strText","Cannot get service object");
				cdoReturn.setStringValue("strInfo","System.Error");
				cdoOutput.setCDOValue("cdoReturn",cdoReturn);
			}
			catch(Exception e)
			{
			}

			return cdoOutput;
		}

		try
		{
			cdoOutput = transHandler.handleTrans(cdoRequest);
		}
		catch(Exception e)
		{
			transHandler=this.getServiceObject(strServiceName,true);
			try
			{
				cdoOutput = transHandler.handleTrans(cdoRequest);
			}
			catch(Exception ex)
			{
				cdoOutput=new CDO();

				CDO cdoReturn=new CDO();
				try
				{
					cdoReturn.setIntegerValue("nCode",-1);
					cdoReturn.setStringValue("strText","Cannot get service object");
					cdoReturn.setStringValue("strInfo","System.Error");
					cdoOutput.setCDOValue("cdoReturn",cdoReturn);
				}
				catch(Exception ex2)
				{
				}

				return cdoOutput;
			}
		}

		return cdoOutput;
	}

	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------
	/**
	 * 交易处理
	 * @param cdoRequest	交易请求对象
	 * @return 交易应答对象，内置Return
	 */
	public Return handleTrans(String strServiceName,CDO cdoRequest,CDO cdoResponse,boolean bRebind)
	{
		CDO cdoOutput = null;
		Return ret = null;

		IRMIService transHandler	=this.getServiceObject(strServiceName,bRebind);
		if(transHandler==null)
		{
			ret = Return.valueOf(-1,"Cannot get service object","System.Error");
			return ret;
		}
		
		try
		{
			cdoOutput = transHandler.handleTrans(cdoRequest);
		}
		catch(Exception e)
		{
			transHandler=this.getServiceObject(strServiceName,true);
			if(transHandler==null)
			{
				ret = Return.valueOf(-1,"Cannot get service object","System.Error");
				return ret;
			}
			try
			{
				cdoOutput = transHandler.handleTrans(cdoRequest);
			}
			catch(Exception ex)
			{
				ret = Return.valueOf(-1,ex.getMessage(),"System.Error");
				ret.setThrowable(ex);
				return ret;			
			}
		}

		CDO cdoReturn=null;
		CDO cdoResponseTemp=null;
		try
		{
			cdoReturn=cdoOutput.getCDOValue("cdoReturn");
			ret=new Return(cdoReturn.getIntegerValue("nCode"),cdoReturn.getStringValue("strText"),cdoReturn.getStringValue("strInfo"));
			cdoResponseTemp=cdoOutput.getCDOValue("cdoResponse");
		}
		catch(Exception e)
		{
			ret = Return.valueOf(-1,e.getMessage(),"System.Error");
		}
		
		cdoResponse.copyFrom(cdoResponseTemp);

		return ret;
	}

	/**
	 * 交易处理
	 * @param cdoRequest	交易请求对象
	 * @return 交易应答对象，内置Return
	 */
	public Return handleTrans(String strServiceName,CDO cdoRequest,CDO cdoResponse)
	{
		return handleTrans(strServiceName,cdoRequest,cdoResponse,false);
	}

	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------
	public void onException(String strMessage)
	{
		
	}

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public RMIClient()
	{
		super();

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		hmServiceObject	=new HashMap();
		
		strHost		="localhost";
		nPort		=19000;
	}

}
