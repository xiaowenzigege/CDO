package com.cdoframework.cdolib.servicebus;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.cdoframework.cdolib.base.CycleList;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.base.Utility;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.database.IDataEngine;
import com.cdoframework.cdolib.database.INoSQLDataEngine;
import com.cdoframework.cdolib.database.NoSQLDataEngine;
import com.cdoframework.cdolib.database.dataservice.BigTable;
import com.cdoframework.cdolib.database.dataservice.DataService;
import com.cdoframework.cdolib.servicebus.schema.ActiveService;
import com.cdoframework.cdolib.servicebus.schema.DataGroup;
import com.cdoframework.cdolib.servicebus.schema.NoSQLDB;
import com.cdoframework.cdolib.servicebus.schema.Parameter;
import com.cdoframework.cdolib.servicebus.schema.ServiceConfig;
import com.cdoframework.cdolib.servicebus.schema.TransService;

/**
 * @author Aaron
 */
public class ServicePlugin implements IServicePlugin
{

	// 内部类,所有内部类在此声明----------------------------------------------------------------------------------

	// 静态对象,所有static在此声明并初始化------------------------------------------------------------------------
	static Logger logger=Logger.getLogger(ServicePlugin.class);
	// 内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------

	private HashMap<String,String> hmParameterMap;//配置参数
	private HashMap<String,CycleList<IDataEngine>> hmLocalDataGroup;//关系数据库引擎容器
	private HashMap<String,CycleList<IDataEngine>> hmAllDataGroup;//关系数据库引擎容器
	private HashMap<String,INoSQLDataEngine> hmLocalNoSQLDataEngine;
	private HashMap<String,INoSQLDataEngine> hmAllNoSQLDataEngine;
	private HashMap<String,Service> hmService;
	private HashMap<String,BigTable[]> hmBigTableGroupConfig;//bigTable  配置文件
	
	public void setParameterMap(HashMap<String,String> hmParameterMap)
	{
		this.hmParameterMap=hmParameterMap;
	}

	public void setPublicDataGroup(HashMap<String,CycleList<IDataEngine>> hmPublicDataGroup)
	{
		hmAllDataGroup.putAll(hmPublicDataGroup);
	}

	public void setPublicNoSQLDataEngine(HashMap<String,NoSQLDataEngine> hmPublicNoSQLDataEngine)
	{
		hmAllNoSQLDataEngine.putAll(hmPublicNoSQLDataEngine);
	}
	public void setPublicBigTableGroupConfig(HashMap<String,BigTable[]>  hmBigTableGroupConfig)
	{
		this.hmBigTableGroupConfig.putAll(hmBigTableGroupConfig);
	}
	// 属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------

	// 引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------
	private String strPluginName;
	private ServiceBus serviceBus;
	
	public String getPluginName()
	{
		return strPluginName;
	}

	public void setPluginName(String strPluginName)
	{
		this.strPluginName=strPluginName;
	}

	public void setServiceBus(ServiceBus _serviceBus)
	{
		serviceBus=_serviceBus;
	}

	// 内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	// 私有方法 所有仅在本类或派生类中使用的函数在此定义为private方法-------------------------------------------

	// 公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------
	
	public boolean testServerState()
	{
		//TODO
		return true;
	}
	
	public void init(String strPluginName,ServiceBus serviceBus,com.cdoframework.cdolib.servicebus.schema.ServicePlugin pluginDefine)
					throws Exception
	{
		this.serviceBus = serviceBus;
		this.strPluginName = strPluginName;
		// 初始化插件参数
		int nParameterCount=pluginDefine.getParameterCount();
		for(int i=0;i<nParameterCount;i++)
		{
			Parameter para=pluginDefine.getParameter(i);
			this.hmParameterMap.put(para.getName(),para.getValue());
		}

		// 加载和初始化插件DataGroup
		DataGroup[] dgs=pluginDefine.getDataGroup();
		for(int i=0;i<dgs.length;i++)
		{
			CycleList<IDataEngine> clDataGroup=new CycleList<IDataEngine>();
			clDataGroup=dgs[i].init();
			this.hmLocalDataGroup.put(dgs[i].getId(),clDataGroup);
			this.hmAllDataGroup.put(dgs[i].getId(),clDataGroup);
		}

	
		//初始化NoSQL NotSQLDataEngine
		NoSQLDB[] noSQLDBdefines = pluginDefine.getNoSQLDB();
		//TODO
		if(noSQLDBdefines!=null && noSQLDBdefines.length>0)
		{
			if(logger.isInfoEnabled()){logger.info(strPluginName+ " starting to load NoSQLTransDataEngine....................");}
			try
			{
				for(NoSQLDB noSQLDBdefine:noSQLDBdefines)
				{
					NoSQLDataEngine noSQLDataEngine = new NoSQLDataEngine();
					Return ret = noSQLDataEngine.initNoSQLDataEngine(noSQLDBdefine);
					if(ret.getCode()!=0)
					{
						//TODO 退出or继续执行
						logger.error("Faild to load NoSQLDB............... caught Exception: "+ret.getText());
						throw new Exception("Init ServiceBus Failed: "+ret.getText());
					}
					this.hmLocalNoSQLDataEngine.put(noSQLDBdefine.getId(),noSQLDataEngine);
					this.hmAllNoSQLDataEngine.put(noSQLDBdefine.getId(),noSQLDataEngine);
				}

			}
			catch(Exception e1)
			{
				logger.error("when parse NoSQLDB ,caught Exception: ",e1);
				throw new Exception ("Init ServiceBus Failed: "+e1.getLocalizedMessage());
			}		
			if(logger.isInfoEnabled()){logger.info(strPluginName+" Load NoSQLTransDataEngine successfully....................");}
		
		}
		
		//TODO 初始化Service
		int nServiceCount = pluginDefine.getServiceConfigCount();
		if(nServiceCount==0)
		{
			//TODO 清除对象
			throw new Exception("no server define");
		}
		ServiceConfig[] serviceConfigs = pluginDefine.getServiceConfig();
		for(ServiceConfig sc:serviceConfigs)
		{
			Service service = new Service();
			service.setBigTableEngine(serviceBus.getBigTableEngine());
			service.setPublicDataGroup(this.hmAllDataGroup);
			if(logger.isInfoEnabled()){logger.info("init service: "+ sc.getId());}
			Return ret = service.init(sc.getId(),sc.getServiceURI(),this,serviceBus);
			if(ret.getCode()!=0)
			{
				//TODO 清除对象
				throw new Exception("init service error : "+ret.getText());
			}
			IService oldService = this.hmService.put(sc.getId(),service);
			IService oldService2 = serviceBus.addService(service);
			if(oldService!=null || oldService2!=null)
			{
				//TODO 清除对象
				throw new Exception("duplicate serviceId :" + service.getServiceName());
			}			
		}
		
		// 初始化DataService
		int nDataServiceCount=pluginDefine.getDataServiceCount();
		try
		{
			for(int i=0;i<nDataServiceCount;i++)
			{
				com.cdoframework.cdolib.servicebus.schema.DataService dataServiceDefine=pluginDefine.getDataService(i);
				if(logger.isInfoEnabled()){logger.info("parse trans xml :"+dataServiceDefine.getResource());}
				String strSQLTransXML=Utility.readTextResource(dataServiceDefine.getResource(),"utf-8");
				if(strSQLTransXML==null)
				{
					throw new Exception("Invalid resource "+dataServiceDefine.getResource());
				}

				DataService dataService=null;
				try
				{
					dataService=DataService.fromXML(strSQLTransXML);
				}
				catch(Exception e)
				{
					logger.error(e.getMessage(), e); 
					throw new Exception("SQL XML parse error: "+dataServiceDefine.getId());
				}
				Service service = this.hmService.get(dataServiceDefine.getId());
				if(service==null)
				{
					throw new Exception("init service error : can not find service id "+dataServiceDefine.getId());
				}
				Return ret = dataService.init(dataServiceDefine.getId(),service,this,serviceBus,hmBigTableGroupConfig);
				if(ret.getCode()!=0)
				{
					//TODO 清除对象
					throw new Exception("init service error : "+ret.getText());
				}
			}
		}
		catch(Exception e)
		{
			//TODO 清除对象
			throw e;
		}

		// 初始化TransService
		int nTransServiceCount=pluginDefine.getTransServiceCount();
		try
		{
			for(int i=0;i<nTransServiceCount;i++)
			{
				
				TransService transService=pluginDefine.getTransService(i);
				Service service = this.hmService.get(transService.getId());
				if(service==null)
				{
					throw new Exception("init service error : can not find service id: "+transService.getId());
				}
				if(logger.isInfoEnabled()){logger.info("init trans service "+ transService.getClassPath());}
				ITransService transServiceObject=((ITransService)Class.forName(transService.getClassPath())
								.newInstance());
				transServiceObject.setServicePlugin(this);
				transServiceObject.setServiceBus(serviceBus);
				transServiceObject.setServiceName(transService.getId());
				transServiceObject.setService(service);
				Return ret=transServiceObject.init();
				if(ret.getCode()!=0)
				{
					throw new Exception("Init service object failed: "+transService.getId());
				}

				service.addTransService(transServiceObject);
			}
		}
		catch(Exception e)
		{
			//TODO 清除对象
			throw e;
		}

		// 初始化ActiveService
		int nActiveServiceCount=pluginDefine.getActiveServiceCount();
		try
		{
			for(int i=0;i<nActiveServiceCount;i++)
			{
				ActiveService activeService=pluginDefine.getActiveService(i);
				Service service = this.hmService.get(activeService.getId());
				if(service==null)
				{
					throw new Exception("init service error : can not find service id"+activeService.getId());
				}
				if(logger.isInfoEnabled()){logger.info("init active service "+ activeService.getClassPath());}
				IActiveService activeServiceObject=((IActiveService)Class.forName(activeService.getClassPath())
								.newInstance());
				activeServiceObject.setServicePlugin(this);				
				activeServiceObject.setServiceBus(serviceBus);
				activeServiceObject.setService(service);
				activeServiceObject.setServiceName(activeService.getId());
				activeServiceObject.setClustered(activeService.getClustered());

				Return ret=activeServiceObject.init();
				if(activeService.getClustered()==true)
				{
					this.serviceBus.addClusterActiveService(activeService.getId(),activeServiceObject);
				}
				if(ret.getCode()!=0)
				{
					throw new Exception("Init service object failed: "+activeService.getId());
				}

				service.addActiveService(activeServiceObject);
			}
		}
		catch(Exception e)
		{
			//TODO 清除对象
			throw e;
		}
	}


	// 接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------
	public Return handleTrans(CDO cdoRequest,CDO cdoResponse)
	{
		String strServiceName	= null;
		String strTransName		= null;
		try
		{
			strServiceName=cdoRequest.getStringValue("strServiceName");
		}
		catch(Exception e)
		{
			logger.error("handleTrans:"+e.getMessage(),e);
			return null;
		}
		try
		{
			strTransName=cdoRequest.getStringValue("strTransName");
		}
		catch(Exception e)
		{
			logger.error("handleTrans:"+e.getMessage(),e);
			return null;
		}
		return this.handleTrans(strServiceName,strTransName,cdoRequest,cdoResponse);
	}
	
	public Return handleTrans(String strServiceName,String strTransName,CDO cdoRequest,CDO cdoResponse)
	{
		Return ret = null;
		IService service = this.hmService.get(strServiceName);
		if(service==null)
		{
			try
			{
				onTransUnsupported(cdoRequest);
			}
			catch(Exception e)
			{
			}
			return Return.valueOf(-1,"can not find service named "+strServiceName);
		}
	
		ret = service.handleTrans(cdoRequest,cdoResponse);
		if(ret==null)
		{// Trans不支持
			try
			{
				onTransUnsupported(cdoRequest);
			}
			catch(Exception e)
			{
			}
			return Return.valueOf(-1,"can not find service named "+strServiceName+"."+strTransName);
		}


		return ret;
	}

	public Return handleDataTrans(CDO cdoRequest,CDO cdoResponse)
	{
		String strServiceName=null;
		try
		{
			strServiceName=cdoRequest.getStringValue("strServiceName");
		}
		catch(Exception e)
		{
		}
		if(strServiceName==null)
		{
			return Return.valueOf(-1,"There's no parameter strServiceName");
		}

		IService service = this.hmService.get(strServiceName);
		if(service==null)
		{
			this.onTransUnsupported(cdoRequest);
		}
		return service.handleDataTrans(cdoRequest,cdoResponse);
	}


	public Return handleDataTrans(String strServiceName,CDO cdoRequest,CDO cdoResponse)
	{
		IService service = this.hmService.get(strServiceName);
		if(service==null)
		{
			this.onTransUnsupported(cdoRequest);
		}
		return service.handleDataTrans(cdoRequest,cdoResponse);
	}

	public String getParameter(String strName,String strDefaultValue)
	{
		String strValue=hmParameterMap.get(strName);
		if(strValue==null)
		{
			return strDefaultValue;
		}
		return strValue;
	}

	public String getParameter(String strName)
	{
		return hmParameterMap.get(strName);
	}

	public INoSQLDataEngine getNoSQLDataEngine(String strNoSQLDBId)
	{
		if(strNoSQLDBId==null )
		{
			return this.serviceBus.getDefaultNoSQLDataEngine();
		}
		return this.hmAllNoSQLDataEngine.get(strNoSQLDBId);
	}

	// 事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	// 事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------
	public void onTransUnsupported(CDO cdoRequest)
	{
		try
		{
			logger.error(":Trans not supported:"+cdoRequest.getStringValue("strTransName"));
		}
		catch(Exception e)
		{
			
		}
	}
	// 构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public ServicePlugin()
	{
		hmLocalNoSQLDataEngine	= new HashMap<String,INoSQLDataEngine>(3);
		hmAllNoSQLDataEngine	= new HashMap<String,INoSQLDataEngine>(3);
		hmService 			= new HashMap<String,Service>(3);
		hmParameterMap			= new HashMap<String,String>(10);
		hmLocalDataGroup		= new HashMap<String,CycleList<IDataEngine>>(10);
		hmAllDataGroup			= new HashMap<String,CycleList<IDataEngine>>(10);
		hmBigTableGroupConfig   =new HashMap<String,BigTable[]>(2);
	}
}
