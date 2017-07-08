package com.cdo.business;

import java.io.File;

import org.apache.log4j.Logger;

import com.cdo.util.resource.GlobalResource;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.base.Utility;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.servicebus.IServiceBus;
import com.cdoframework.cdolib.servicebus.ServiceBus;

/**
 * 创建业务处理的实例
 * 在一个jvm里  只有一个serviceBus 实例，初始化框架所有配置，读取业务开发插件
 * @author KenelLiu
 */
public class BusinessService
{
	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------
	private static BusinessService instance=new BusinessService();
	private Logger log=Logger.getLogger(BusinessService.class);
	public static BusinessService getInstance()
	{//使用单列
		return instance;
	}

	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------
	private ServiceBus serviceBus;
	public IServiceBus getServiceBus()
	{
		return this.serviceBus;
	}
	private boolean bIsRunning;

	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------
	public boolean isRunning()
	{
		return this.bIsRunning;
	}

	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------

	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------
	/**
	 * 读取  启动时 的配置文件  -D
	 * CDO_CONFIG_FILE=${BASE_HOME}/conf/cdo.conf
	 * @return
	 */
	private Return getLocalEnvironment(){
		//绑定vm 启动时 参数资源文件
		try{
			GlobalResource.bundleInitCDOEnv();
			return Return.OK;
		}catch(Exception ex){
			log.error(ex.getMessage(),ex);			
			return Return.valueOf(-1, "init Environment failed!");
		}		
	}

	public Return start()
	{
		//可通过 外部初始化,若外部未初始化，使用默认初始化
		if(GlobalResource.cdoConfig==null){
			Return ret=getLocalEnvironment();
			if(ret.getCode()!=0)
				return ret;
		}

		String strBusResource=GlobalResource.cdoConfig.getString("servicebusResource.path");	
		String strFrameworkResource=GlobalResource.cdoConfig.getString("frameworkResource.path");	
		if(strBusResource==null){
			 String confPath=GlobalResource.getCDOEnvConfigPath();
			 String parent=new File(confPath).getParent();
			 strBusResource=parent+"/servicebusResource.xml";
			 log.info("使用配置文件:strBusResource="+strBusResource);
			 File file=new File(strBusResource);
			 if(!file.exists()||!file.isFile())
				 strBusResource=null;			 
		}
		if(strBusResource==null || strBusResource.equals("")){
			return Return.valueOf(-1, "init Environment failed! parameter[servicebusResource.path,frameworkResource.path] is not found");
		}		
		
		return start(strBusResource,"pluginsConfig.xml","bigTableConfig.xml",strFrameworkResource,"UTF-8");
	}
	
	public Return start(String strServiceBusResouceXMLFile,String strPluginsConfigXMLFile,String strBigTableConfigXMLFile,String strFrameworkResourcePath,String encoding)
	{
		if(this.bIsRunning==true)
		{
			return Return.OK;
		}

		//根据PluginCOnfig.xml和ServerbusResource.xml拼装 ServiceBus.xml
		StringBuilder strBusinessServiceBusXML= new StringBuilder();
		String strServiceBusResouceXML = Utility.readTextResource(strServiceBusResouceXMLFile,encoding,false);
		String strPluginsConfigXML = Utility.readTextResource(strPluginsConfigXMLFile,encoding);
		
		if (strPluginsConfigXML.indexOf("<PluginXMLResource>")!=-1)
		{
			strPluginsConfigXML = strPluginsConfigXML.substring(strPluginsConfigXML.indexOf("<PluginXMLResource>"), strPluginsConfigXML.lastIndexOf("</PluginXMLResource>")+"</PluginXMLResource>".length());
		}
		else
		{
			strPluginsConfigXML="";
		}
		strServiceBusResouceXML=strServiceBusResouceXML.replaceAll("ServiceBusResource", "ServiceBus");
		strBusinessServiceBusXML.append(strServiceBusResouceXML.replaceAll("</ServiceBus>", "")).append(strPluginsConfigXML).append("</ServiceBus>");
		
		String strBigTableConfigXML = Utility.readTextResource(strBigTableConfigXMLFile,encoding,true);
		Return ret=serviceBus.init(strBusinessServiceBusXML.toString(),strBigTableConfigXML,strFrameworkResourcePath);
		
		if(ret.getCode()!=0)
		{
			return ret;
		}
		
		ret=serviceBus.start();
		if(ret.getCode()!=0)
		{
			serviceBus.destroy();
			return ret;
		}
		
		this.bIsRunning=true;

		return Return.OK;
	}
	
	public Return start(String strServiceBusXMLFile,String encoding)
	{
		if(this.bIsRunning==true)
		{
			return Return.OK;
		}

		//ServiceBus
		String strBusinessServiceBusXML=Utility.readTextResource(strServiceBusXMLFile,encoding);
		Return ret=serviceBus.init(strBusinessServiceBusXML);
		if(ret.getCode()!=0)
		{
			return ret;
		}
		
		ret=serviceBus.start();
		if(ret.getCode()!=0)
		{
			serviceBus.destroy();
			return ret;
		}
		
		this.bIsRunning=true;

		return Return.OK;
	}
	public void stop()
	{
		if(this.bIsRunning==false)
		{
			return;
		}
		serviceBus.stop();
		serviceBus.destroy();
		
		this.bIsRunning=false;
	}

	public Return handleTrans(CDO cdoRequest,CDO cdoResponse)
	{
		Return ret=serviceBus.handleTrans(cdoRequest,cdoResponse);
		if(ret==null)
		{
			return Return.valueOf(-1,"Invalid request","System.Error");
		}
		
		return ret;
	}
	
	private BusinessService()
	{		
		serviceBus=new ServiceBus();
	}
	
}
