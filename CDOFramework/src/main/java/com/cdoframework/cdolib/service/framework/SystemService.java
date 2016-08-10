package com.cdoframework.cdolib.service.framework;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.base.Utility;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.framework.CacheHandler;
import com.cdoframework.cdolib.framework.FilterHandler;
import com.cdoframework.cdolib.parameter.GlobalConfiguration;
import com.cdoframework.cdolib.service.framework.xsd.FilterConfig;
import com.cdoframework.cdolib.service.framework.xsd.Framework;
import com.cdoframework.cdolib.service.framework.xsd.MemCacheServerGroup;
import com.cdoframework.cdolib.service.framework.xsd.Parameter;
import com.cdoframework.cdolib.service.framework.xsd.URLCacheServer;
import com.cdoframework.cdolib.servicebus.IEventHandler;
import com.cdoframework.cdolib.servicebus.TransService;

/**
 * @author Aaron
 */
public class SystemService extends TransService
{

	//内部类,所有内部类在此声明----------------------------------------------------------------------------------

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------
	Logger logger  = Logger.getLogger(SystemService.class);
	Logger businessLogger = Logger.getLogger("businessLogger");
	static char cTab = 9;
	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------


	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------
	private HashMap<String,String> hmParameterMap;
	private CacheManager cacheManager;
	private FilterManager filterManager;
	
	public String getParameterValue(String strParameterName)
	{
		return hmParameterMap.get(strParameterName);
	}
	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------

	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	protected Return addBusinessLog(CDO cdoRequest,CDO cdoResponse)
	{
//		return service.handleDataTrans(cdoRequest,cdoResponse);
//		businessLogger.info();
		StringBuilder sb = new StringBuilder();
		sb.append("事务名:").append(cdoRequest.getStringValue("strTransName2"));
		sb.append(cTab).append("操作名:").append(cdoRequest.getStringValue("strOperationName"));
		int nPhrase = cdoRequest.getIntegerValue("nPhrase");
		String strPhrease = " 执行事务后 ";
		switch (nPhrase)
		{
			case 1:
				{
					strPhrease = "执行事务前";
					break;
				}
			case 2:
				{
					strPhrease = "执行事务中";
					break;
				}
		}
		sb.append(cTab).append("操作者Id:").append(cdoRequest.getStringValue("strUserId"));
		sb.append(cTab).append("操作者:").append(cdoRequest.getStringValue("strUserName"));
		sb.append(cTab).append("操作者Ip:").append(cdoRequest.getStringValue("strIP"));
		sb.append(cTab).append(strPhrease).append(" 操作类型:").append(cdoRequest.getStringValue("strOperationType"));
		sb.append(cTab).append("相关标识:").append(cdoRequest.getStringValue("strRelationId"));
		sb.append(cTab).append("相关标题:").append(cdoRequest.getStringValue("strRelationTitle"));
		sb.append(cTab).append("操作结果").append(cdoRequest.getIntegerValue("nReturnResult"));
		sb.append(cTab).append("异常信息:").append(cdoRequest.getStringValue("strException"));
		sb.append(cTab).append("优先级").append(cdoRequest.getIntegerValue("nPriority"));
		sb.append(cTab).append("请求数据:").append(cdoRequest.getStringValue("strCDORequestXml"));
		sb.append(cTab).append("返回数据:").append(cdoRequest.getStringValue("strCDOResponseXml"));
		businessLogger.info(sb.toString());
		return Return.OK;
	}
	
	//私有方法 所有仅在本类或派生类中使用的函数在此定义为private方法-------------------------------------------
	
	

	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------
	
	/**
	 * 初始化framework
	 */
	public Return init()
	{
		super.init();
		
		/**
		 *  从parameter 读frameworkResource,filtersConfig文件名，不再配置默认，
		 *  若servicePlugin配置了参数frameworkResource，filtersConfig 则
		 *  覆盖 servicebus读取外部文件传来的  frameworkResource.xml路径,filtersConfig.xml路径
		 */
		boolean isResourceClassPath=true;//是否从classpath路径读取数据
		String strFrameworkResourceXMLFile = this.servicePlugin.getParameter("frameworkResource");
		if(strFrameworkResourceXMLFile==null ||strFrameworkResourceXMLFile.equals("")){
			strFrameworkResourceXMLFile=this.serviceBus.getParameter("frameworkResource");
			isResourceClassPath=false;
		}
		boolean isFliterConfigClassPath=true;//是否从classpath路径读取数据
		String strFlitersConfigXMLFile = this.servicePlugin.getParameter("filtersConfig");
		if(strFlitersConfigXMLFile==null ||strFlitersConfigXMLFile.equals("")){
			strFlitersConfigXMLFile=this.serviceBus.getParameter("filtersConfig");
			isResourceClassPath=false;
		}
		
		if(logger.isInfoEnabled()){
			logger.info("parsing xml file: "+strFrameworkResourceXMLFile);
			logger.info("parsing xml file: "+strFlitersConfigXMLFile);
		}		
		
		StringBuilder strFrameworkXML= new StringBuilder();
		String strFrameworkResourceXML = Utility.readTextResource(strFrameworkResourceXMLFile,"utf-8",isResourceClassPath);
		String strFlitersConfigXML = Utility.readTextResource(strFlitersConfigXMLFile,"utf-8",isFliterConfigClassPath);

		if (strFlitersConfigXML.indexOf("<FilterConfig")!=-1)
		{
			strFlitersConfigXML = strFlitersConfigXML.substring(strFlitersConfigXML.indexOf("<FilterConfig"), strFlitersConfigXML.lastIndexOf("</FiltersConfig>"));
		}
		else
		{
			strFlitersConfigXML="";
		}
		strFrameworkResourceXML=strFrameworkResourceXML.replaceAll("FrameworkResource", "Framework");
		strFrameworkXML.append(strFrameworkResourceXML.replaceAll("</Framework>", "")).append(strFlitersConfigXML).append("</Framework>");
		
		Framework framework = null;
		try
		{
				framework = Framework.fromXML(strFrameworkXML.toString());
		}
		catch(Exception e)
		{
			logger.error("can not parse strFrameworkResourceXMLFile:"+strFrameworkResourceXMLFile);
			logger.error("can not parse strFlitersConfigXMLFile:"+strFlitersConfigXMLFile);
			return Return.valueOf(-1,e.getLocalizedMessage());
		}
		
		//初始化parameter
		Parameter[] parameters = framework.getParameter();
		
		//获得全局参数配置map
		Map<String, String> globalConfigurationMap = GlobalConfiguration.getInstance().getHmParameterMap();
		
		if(parameters!=null && parameters.length>0)
		{
			for(Parameter para:parameters)
			{
				this.hmParameterMap.put(para.getName(),para.getValue());
				globalConfigurationMap.put(para.getName(),para.getValue());
				
			}
		}		
		//init memcache server group
		MemCacheServerGroup[] memCacheServerGroups = framework.getMemCacheServerGroup();	
		Return ret  = this.cacheManager.initMemCacheServer(memCacheServerGroups);
		if(ret.getCode()!=0)
		{
			logger.error(ret.getText());
			return ret;
		}
		
		//init squid URL cache server
		URLCacheServer[] urlCacheServers = framework.getURLCacheServer();
		this.cacheManager.intSquidCacheServer(urlCacheServers);
		CacheHandler.getInstance().setCacher(cacheManager);
		//初始化 cluster controller
		
		//初始化Id生成器		
		if(framework.getIdNodeProcessor() == true)
		{
			if(logger.isInfoEnabled()){
				logger.info("starting to init id generator....................");
			}
			IdNodeProcessor nodeIdProecessor = new IdNodeProcessor();
			ret = nodeIdProecessor.init(this.service);
			if(ret==null || ret.getCode()!=0)
			{
				return ret;
			}
			if(logger.isInfoEnabled()){
				logger.info("init id generator successfully....................");
			}
		}
		
		
		filterManager.setServiceBus(serviceBus);
		filterManager.setCacheManager(cacheManager);
		//解析filter	
		// 初始化事件定义
		int nFilterConfigCount=framework.getFilterConfigCount();
		try
		{
			for(int i=0;i<nFilterConfigCount;i++)
			{
				FilterConfig config=framework.getFilterConfig(i);
				if(logger.isInfoEnabled()){
					logger.info("parse event config xml file :"+config.getResource());
				}
				String strFilterConfigXML=Utility.readTextResource(config.getResource(),"utf-8");
				if(strFilterConfigXML==null)
				{
					throw new Exception("Invalid resource "+config.getResource());
				}
				ret = this.filterManager.append(strFilterConfigXML);
				if(ret.getCode()!=0)
				{
					logger.error(ret.getText());
					return ret;
				}
			}

		}
		catch(Exception e)
		{
			logger.error("init:"+e.getMessage(),e);
			//TODO 清除对象			
		}
		FilterHandler.getInstance().setFilter(filterManager);
		IEventHandler it = serviceBus.getEventHandler();
		if(it!=null)
		{
			it.setTaskExecutor(filterManager);
		}
		return Return.OK;
	}

	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------
	public Return handleTrans(CDO cdoRequest,CDO cdoResponse)
	{
		String strTransName = cdoRequest.getStringValue("strTransName");
		if (strTransName.equalsIgnoreCase("addBusinessLog")) {// 处理保存订单
			return addBusinessLog(cdoRequest, cdoResponse);
		}
		return null;
	}

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public SystemService()
	{

		hmParameterMap			= new HashMap<String,String>(10);
		cacheManager = new CacheManager();
		filterManager = new FilterManager();
		filterManager.setCacheManager(cacheManager);
	}
}
