package com.cdoframework.cdolib.servicebus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.cdoframework.cdolib.base.CycleList;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.database.BigTableEngine;
import com.cdoframework.cdolib.database.IDataEngine;
import com.cdoframework.cdolib.database.NoSQLUtil;
import com.cdoframework.cdolib.database.TransDefine;
import com.cdoframework.cdolib.database.dataservice.NoSQLTrans;
import com.cdoframework.cdolib.database.dataservice.SQLTrans;
import com.cdoframework.cdolib.framework.FilterHandler;

/**
 * @author Aaron
 */
public class Service implements IService
{

	//内部类,所有内部类在此声明----------------------------------------------------------------------------------

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------
	private static Logger logger = Logger.getLogger(Service.class);
	private static Logger loggerStatistics = Logger.getLogger("transStatistics");
	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------
	private ArrayList<ITransService> alTransService;//静态服务对象集合
	private ArrayList<IActiveService> alActiveService;//动态服务对象集合
	private Map<String,TransDefine> hmTransDefine;
	private HashMap<String,CycleList<IDataEngine>> hmAllDataGroup;//关系数据库引擎容器
//	private BusinessClient businessClient;//支持分布式部署时用
	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------

	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------
	private String strServiceName;
	private String strServiceURI;
	private BigTableEngine btEngin;
	private IServicePlugin servicePlugin;
	private IServiceBus serviceBus;
	// 存放所有的transService 和 activeService
	private Map<String, List<ITransService>> hmServiceMap;

	public void setServiceName(String strServiceName)
	{
		this.strServiceName = strServiceName;
	}
	public String getServiceName()
	{
		return this.strServiceName;
	}
	public String getServiceURI()
	{
		return strServiceURI;
	}
	public void setBigTableEngine(BigTableEngine btEngin)
	{
		this.btEngin = btEngin;
	}
	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	//私有方法 所有仅在本类或派生类中使用的函数在此定义为private方法-------------------------------------------
	

	public Return executeDataServiceTrans(String strTransName,CDO cdoRequest,CDO cdoResponse)
	{
		TransDefine transDefine = this.hmTransDefine.get(strTransName);
		if(transDefine==null)
		{
			return null;
		}
		NoSQLTrans noSqlTrans = transDefine.getNoSqlTrans();
		if(noSqlTrans!=null)
		{
			try
			{
				return NoSQLUtil.executeTrans(noSqlTrans,cdoRequest,cdoResponse);
			}
			catch(Exception e)
			{
				logger.error("executeDataServiceTrans:"+e.getMessage(),e);
				return Return.valueOf(-1,e.getLocalizedMessage());
			}
		}
		SQLTrans sqlTrans = transDefine.getSqlTrans();
		if(sqlTrans==null)
		{
			return null;
		}
		
		try
		{
			return this.btEngin.handleTrans(hmAllDataGroup,sqlTrans,cdoRequest,cdoResponse);
		}
		catch(Exception e)
		{
			logger.error("executeDataServiceTrans:"+e.getMessage(),e);
			return Return.valueOf(-1,e.getLocalizedMessage());
		}
	}
	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------
	public void setPublicDataGroup(HashMap<String,CycleList<IDataEngine>> hmPublicDataGroup)
	{
		hmAllDataGroup=new HashMap<String,CycleList<IDataEngine>>();
		hmAllDataGroup.putAll(hmPublicDataGroup);
	}
	public void addTransService(ITransService transService)
	{
		this.alTransService.add(transService);
		addService(transService);
	}
	public void addActiveService(IActiveService activeService)
	{
		this.alActiveService.add(activeService);
		addService(activeService);
		
	}
	private void addService(ITransService service) {
		List<ITransService> services = null;
		synchronized (hmServiceMap) {
			if(hmServiceMap.get(service.getServiceName()) == null){
				services = new ArrayList<ITransService>();
				hmServiceMap.put(service.getServiceName(), services);
			} else {
				services = hmServiceMap.get(service.getServiceName());
			}
		}
		services.add(service);
		service.inject(service);
	}
	public TransDefine putTransDefine(String transName,TransDefine transDefine)
	{
		return this.hmTransDefine.put(transName,transDefine);
		
	}

	public Return init(String strServiceName,String strURI,IServicePlugin servicePlugin,IServiceBus serviceBus)
	{
		this.strServiceName = strServiceName;
		this.strServiceURI = strURI;
		this.servicePlugin = servicePlugin;
		this.serviceBus = serviceBus;
		return Return.OK;
	}
	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	/* (non-Javadoc)
	 * @see com.cdoframework.cdolib.servicebus.IService#handleSQLTrans(com.cdoframework.cdolib.data.cdo.CDO, com.cdoframework.cdolib.data.cdo.CDO)
	 */
	public Return handleDataTrans(CDO cdoRequest,CDO cdoResponse)
	{
		String strTransName = cdoRequest.getStringValue(ITransService.TRANSNAME_KEY);
		return this.executeDataServiceTrans(strTransName,cdoRequest,cdoResponse);
	}

	/* (non-Javadoc)
	 * @see com.cdoframework.cdolib.servicebus.IService#handleTrans(com.cdoframework.cdolib.data.cdo.CDO, com.cdoframework.cdolib.data.cdo.CDO)
	 */
	public Return handleTrans(CDO cdoRequest,CDO cdoResponse)
	{
		long beginTime = System.currentTimeMillis();
		if(this.strServiceURI!=null)
		{//这种情况,说明本服务不在本机,需要远程调用. 注意在这种情况下,不能启用拦截器,因为对应的远程服务器上会启用
			//TODO
		}
		String strTransName = cdoRequest.getStringValue(ITransService.TRANSNAME_KEY);

		//处理同步任务
		Return ret = null;
		try
		{
			ret = FilterHandler.getInstance().handlePreEvent(strServiceName,strTransName,cdoRequest);
		}
		catch(Exception e)
		{
			logger.error("When handle pre filer of "+strServiceName+"."+strTransName,e);
//			return Return.valueOf(-1,e.getLocalizedMessage(),e);
		}
		if(ret!=null && ret.getCode()!=0)
		{//有同步事务,执行失败
			logger.error("有同步事务,执行失败 "+strServiceName+"."+strTransName);
			return ret;
		}
		
	
		//处理缓存cache
		ret = null;
		try{
			ret = FilterHandler.getInstance().handlePreTransCache(strServiceName,strTransName,cdoRequest,cdoResponse);
			if(ret!=null && ret.getCode()==0)
			{//取得缓存值,直接返回
				if(logger.isDebugEnabled())
				{
					logger.debug("取得缓存,直接返回 "+strServiceName+"."+strTransName);
				}

				return ret;
			}
		}
		catch(Exception e)
		{
			logger.error("When handle cache filer of "+strServiceName+"."+strTransName,e);
		}

		boolean bCacheable = false;
		if(ret!=null && ret.getCode()==2)
		{//需要缓存,但未从缓存中取得值,
			bCacheable = true;
		}
		
		//未取得缓存值, 执行Trans
		ret = null;
		
		// 根据serviceName直接定位Service
		List<ITransService> transServices = this.hmServiceMap.get(strServiceName);
		// 处理所有的TransService 和 ActiveService调用，ts==null 直接调用DataServiceTrans
		if(transServices != null) {
			for(ITransService transService : transServices){
				if(transService.containsTrans(strTransName)){
					Return validateReturn = transService.validate(cdoRequest);
					if (!Return.OK.equals(validateReturn)) {
						return validateReturn;
					}
					ret = transService.processTrans(cdoRequest, cdoResponse);
					if(logger.isDebugEnabled()){
						logger.debug("注解方式执行："+ transService.getServiceName()+ "."+ strTransName);
					}
					if(ret != null) {
						break;
					}
				}
			}
		}
		// 兼容之前的handletrans
		// 如果新的方式定位不到，改用老的方式调用，先支持老的调用方式。
		if(ret==null)
		{
			try
			{
				for(ITransService ts:alTransService)
				{
					ret = ts.handleTrans(cdoRequest,cdoResponse);
					if(ret!=null)
					{
						break;
					}
				}		
			}
			catch(Exception e)
			{
				logger.error("When handle trans service "+strServiceName+"."+strTransName,e);
				return Return.valueOf(-1,e.getLocalizedMessage(),e);
			}
		}
		if(ret==null)
		{
			try
			{
				for(IActiveService as:alActiveService)
				{
					ret = as.handleTrans(cdoRequest,cdoResponse);
					if(ret!=null)
					{
						break;
					}
				}
			}
			catch(Exception e)
			{
				logger.error("When handle active service "+strServiceName+"."+strTransName,e);
				return Return.valueOf(-1,e.getLocalizedMessage(),e);
			}
		}
		
		long beginTimeDataTrans = System.currentTimeMillis();
		if(ret==null)
		{
			try
			{
				ret = this.executeDataServiceTrans(strTransName,cdoRequest,cdoResponse);
			}
			catch(Exception e)
			{
				logger.error("When handle data service "+strServiceName+"."+strTransName,e);
				return Return.valueOf(-1,e.getLocalizedMessage(),e);
			}
		}
		long endTimeDataTrans = System.currentTimeMillis();
		
		if(ret !=null )
		{
			//处理缓存
			if(ret.getCode()==0 && bCacheable)
			{
				try
				{
					FilterHandler.getInstance().handlePostTransCache(strServiceName,strTransName,cdoRequest,cdoResponse);
				}
				catch(Exception e)
				{
					logger.error("When handle post filer of "+strServiceName+"."+strTransName,e);
				}
			}

			//处理事件
			try
			{
				FilterHandler.getInstance().handlePostEvent(strServiceName,strTransName,cdoRequest,cdoResponse,ret);	
			}
			catch(Exception e)
			{
				logger.error("When handle filer of "+strServiceName+"."+strTransName,e);
			}
		}	
		if(loggerStatistics.isInfoEnabled()) {
			long duration = System.currentTimeMillis() - beginTime;
			long durationData = endTimeDataTrans- beginTimeDataTrans;
			loggerStatistics.info("transDuration,"+duration+","+strServiceName+"."+strTransName+","+durationData);
		}
		return ret;
	}


	/* (non-Javadoc)
	 * @see com.cdoframework.cdolib.servicebus.IService#handleEvent(com.cdoframework.cdolib.data.cdo.CDO)
	 */
	public void handleEvent(CDO cdoEvent)
	{//此处需要在循环体中处理异常,以保证事件都能通知到
		for(ITransService ts:alTransService)
		{
			try
			{
				ts.handleEvent(cdoEvent);
			}catch(Exception e)
			{
				logger.error("handleEvent:"+e.getMessage(),e);
			}
		}
		for(IActiveService as:alActiveService)
		{
			try
			{
				as.handleEvent(cdoEvent);
			}catch(Exception e)
			{
				logger.error("handleEvent:"+e.getMessage(),e);
			}
		}

	}

	public void destroy()
	{
		// TODO Auto-generated method stub
		
	}

	/**
	 * 启动Business服务
	 * @return
	 */
	public Return start()
	{
		int nCount = this.alActiveService.size();
		for(int i=nCount-1;i>=0;i--)
		{
			IActiveService serv = this.alActiveService.get(i);
			if(!serv.isClusterd())
			{
				serv.start();
			}
		}
		return Return.OK;
	}

	public void stop()
	{
		int nCount = this.alActiveService.size();
		for(int i=nCount-1;i>=0;i--)
		{
			IActiveService serv = this.alActiveService.get(i);
			if(serv.isClusterd())
			{
				serv.stop();
			}
		}
	}
	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public Service()
	{
		alTransService	= new ArrayList<ITransService>(1);
		alActiveService	= new ArrayList<IActiveService>(1);
		hmTransDefine	= new HashMap<String,TransDefine>(30); 
		hmAllDataGroup	= new HashMap<String,CycleList<IDataEngine>>(1);	
		hmServiceMap = new HashMap<String, List<ITransService>>();
	}
	public ArrayList<IActiveService> getAlActiveService() {
		return alActiveService;
	}

}
