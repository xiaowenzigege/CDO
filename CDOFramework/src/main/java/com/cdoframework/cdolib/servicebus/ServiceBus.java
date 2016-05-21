/**

 *
 */

package com.cdoframework.cdolib.servicebus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.log4j.Logger;

import com.cdoframework.cdolib.base.CycleList;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.base.Utility;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.database.BigTableEngine;
import com.cdoframework.cdolib.database.IDataEngine;
import com.cdoframework.cdolib.database.INoSQLDataEngine;
import com.cdoframework.cdolib.database.NoSQLDataEngine;
import com.cdoframework.cdolib.database.dataservice.BigTable;
import com.cdoframework.cdolib.database.dataservice.BigTableGroup;
import com.cdoframework.cdolib.framework.CacheHandler;
import com.cdoframework.cdolib.framework.ClusterController;
import com.cdoframework.cdolib.servicebus.schema.DataGroup;
import com.cdoframework.cdolib.servicebus.schema.NoSQLDB;
import com.cdoframework.cdolib.servicebus.schema.Parameter;

/**
 * @author Frank
 */
public class ServiceBus implements IServiceBus
{

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------
	private Logger logger = Logger.getLogger(ServiceBus.class);
	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------
	private HashMap<String,com.cdoframework.cdolib.base.CycleList<IDataEngine>> hmDataGroup;
	private ServicePlugin[] plugins;
	private ClusterController clusterController;
	
	private HashMap<String,Object> hmSharedData;
	private ReentrantReadWriteLock lockSharedData;	
	private HashMap<String,NoSQLDataEngine> hmNoSQLDataEngine;
	private HashMap<String,IService> hmService;
	private ArrayList<IService> alService;
	
	private HashMap<String,BigTable[]> hmBigTableGroupConfig;
	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------
	private BigTableEngine btEngine;
	public BigTableEngine getBigTableEngine()
	{
		return this.btEngine;
	}
	
	private INoSQLDataEngine defaultNoSQLDataEngine;
	public INoSQLDataEngine getDefaultNoSQLDataEngine()
	{
		return this.defaultNoSQLDataEngine;
	}

	private HashMap<String,String> hmParameterMap;
	public String getParameterValue(String strParameterName)
	{
		return hmParameterMap.get(strParameterName);
	}
	private EventProcessor eventProcessor;
	public IEventHandler getEventHandler()
	{
		return this.eventProcessor;
	}
	
	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------
	private String strDefaultDataGroupId;

	public void setGroupId(String strGroupId)
	{
		this.strDefaultDataGroupId	= strGroupId;
	}
	public String getDefaultDataGroupId()
	{
		return this.strDefaultDataGroupId;
	}

	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------
	

	
	// 公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------
	/**
	 * 根据strBigTableConfigXML初始化对象
	 */
	private Return initBigTableConfig(String strBigTableConfigXML)
	{
		if(strBigTableConfigXML==null || strBigTableConfigXML.equals(""))
			return Return.OK;
		//将XML转换成对象
		com.cdoframework.cdolib.database.dataservice.BigTableConfig bigTableConfig=null;
		try
		{
			bigTableConfig=com.cdoframework.cdolib.database.dataservice.BigTableConfig.fromXML(strBigTableConfigXML);
		}
		catch(Exception e)
		{
			logger.error("When parse bigTableConfig.xml , caught exception: ",e);
			return Return.valueOf(-1,"Init bigTableConfig Failed: "+e.getLocalizedMessage());
		}
		BigTableGroup[] bigTableGroups = bigTableConfig.getBigTableGroup();
		Set<String> bigTablesSet=null;
		for(int i=0;i<bigTableGroups.length;i++)
		{	
			String strId=bigTableGroups[i].getId().trim();
			BigTable[] bigTables=bigTableGroups[i].getBigTable();
			if(this.hmBigTableGroupConfig.containsKey(strId))
			{//BigTableGroup  Id 
				logger.error("Init bigTableConfig Failed,duplicate BigTableGroup Id:"+strId);
				return Return.valueOf(-1,"Init bigTableConfig Failed,duplicate BigTableGroup Id:"+strId);
			}
			bigTablesSet=new HashSet<String>();
			for(int k=0;k<bigTables.length;k++)
			{
				if(bigTablesSet.contains(bigTables[k].getName().trim().toLowerCase()))
				{
					logger.error("Init bigTableConfig Failed,in BigTableGroup Id:"+strId+",duplicate BigTable Name:"+bigTables[k].getName());
					return Return.valueOf(-1,"Init bigTableConfig Failed,in BigTableGroup Id:"+strId+",duplicate BigTable Name:"+bigTables[k].getName());					
				}
				bigTablesSet.add(bigTables[k].getName().trim().toLowerCase());	
			}
			
			this.hmBigTableGroupConfig.put(strId,bigTables);
			if(i==0)
			{//设置默认 bigTable默认数组
				this.hmBigTableGroupConfig.put("_defaultBigTableArray",bigTableGroups[i].getBigTable());
			}
		}
		return Return.OK;
	}	
	/**
	 * 根据strServiceBusXML初始化对象,
	 */	
	public Return init(String strServiceBusXML)
	{
		return init(strServiceBusXML,null);
	}
	
	public Return init(String strServiceBusXML,String strBigTableConfigXML)
	{
		return init(strServiceBusXML, strBigTableConfigXML,null);
	}
	
	
	public Return init(String strServiceBusXML,String strBigTableConfigXML,String strFrameworkResourcePath)
	{
		return init(strServiceBusXML, strBigTableConfigXML,strFrameworkResourcePath,null);
	}
	/**
	 * 	
	 * 根据strServiceBusXML,strBigTableConfigXML初始化 ServiceBus对象,	
	 * @param strServiceBusXML  
	 * @param strBigTableConfigXML  大表
	 * @param strFrameworkResourcePath  frameworkResource.xml路径  可为null
	 * @param strFlitersConfigPath      filtersConfig.xml路径  可为null
	 * @return
	 */
	public Return init(String strServiceBusXML,String strBigTableConfigXML,
			String strFrameworkResourcePath,String strFlitersConfigPath){
		//将XML转换成对象
		com.cdoframework.cdolib.servicebus.schema.ServiceBus serviceBus=null;
		try
		{
			serviceBus=com.cdoframework.cdolib.servicebus.schema.ServiceBus.fromXML(strServiceBusXML);
		}
		catch(Exception e)
		{
			logger.error("When parse serviceBus.xml , caught exception: ",e);
			return Return.valueOf(-1,"Init ServiceBus Failed: "+e.getLocalizedMessage());
		}
		
		//处理默认配置
		this.strDefaultDataGroupId = serviceBus.getDataGroupId();		
		
		// 初始化插件参数
		int nParameterCount=serviceBus.getParameterCount();
		for(int i=0;i<nParameterCount;i++)
		{
			Parameter para=serviceBus.getParameter(i);
			this.hmParameterMap.put(para.getName(),para.getValue());
		}
		//读取外部文件传来的  frameworkResource.xml路径,filtersConfig.xml路径  
		this.hmParameterMap.put("frameworkResource",strFrameworkResourcePath);
		this.hmParameterMap.put("filtersConfig",strFlitersConfigPath);
		
		//加载和初始化全局DataGroup
		this.hmDataGroup=new HashMap<String,com.cdoframework.cdolib.base.CycleList<IDataEngine>>(20);
		DataGroup[] dgs=serviceBus.getDataGroup();
		try
		{
			for(int i=0;i<dgs.length;i++)
			{
				com.cdoframework.cdolib.base.CycleList<IDataEngine> clDataEngine=dgs[i].init();
	
				this.hmDataGroup.put(dgs[i].getId(),clDataEngine);
			}
		}
		catch(Exception e)
		{
			this.hmDataGroup.clear();
			this.hmDataGroup=null;
			logger.error("When parse DataGroup , caught exception: ",e);
			return Return.valueOf(-1,"Init ServiceBus Failed: "+e.getLocalizedMessage());
		}
		

		
		//初始化NoSQL NotSQLDataEngine
		NoSQLDB[] noSQLDBdefines = serviceBus.getNoSQLDB();
		//TODO
		if(noSQLDBdefines==null || noSQLDBdefines.length==0)
		{
			if(logger.isInfoEnabled()){logger.info("There is no NoSQLTransDataEngine Define ,that is OK....................");}
		}
		else
		{
			if(logger.isInfoEnabled()){logger.info("starting to load NoSQLTransDataEngine....................");}
			try
			{
				for(NoSQLDB noSQLDBdefine:noSQLDBdefines)
				{
					NoSQLDataEngine noSQLDataEngine = new NoSQLDataEngine();
					Return ret = noSQLDataEngine.initNoSQLDataEngine(noSQLDBdefine);
					if(ret.getCode()!=0)
					{
						logger.error("Faild to load NoSQLDB............... caught Exception: "+ret.getText());
						return Return.valueOf(-1,"Init ServiceBus Failed: "+ret.getText());
					}
					this.hmNoSQLDataEngine.put(noSQLDBdefine.getId(),noSQLDataEngine);
				}

			}
			catch(Exception e1)
			{
				this.hmDataGroup.clear();
				this.hmDataGroup=null;
				logger.error("when parse NoSQLDB ,caught Exception: ",e1);
				return Return.valueOf(-1,"Init ServiceBus Failed: "+e1.getLocalizedMessage());
			}		
			
			//处理默认noSQLDataEngine
			this.defaultNoSQLDataEngine = this.hmNoSQLDataEngine.get(serviceBus.getNoSQLDBId());
			if(logger.isInfoEnabled()){logger.info("Load NoSQLTransDataEngine successfully....................");		}
		}

		//初始化ClusterController
		com.cdoframework.cdolib.servicebus.schema.ClusterController ccDefine=serviceBus.getClusterController();
		if(ccDefine!=null)
		{
			if(logger.isInfoEnabled()){logger.info("Staring initialize  cluster controller ....................");}
			clusterController	=new ClusterController();
			CycleList<IDataEngine> clDataEngine=hmDataGroup.get(ccDefine.getDataGroupId());
			if(clDataEngine==null)
			{
				return Return.valueOf(-1,"Invalid DataGroupId: "+ccDefine.getDataGroupId());
			}
			
			clusterController.setDataGroup(clDataEngine);
			clusterController.setMaxDeadSecond(ccDefine.getMaxDeadSecond());
			clusterController.setPulseSecond(ccDefine.getPulseSecond());
			if(logger.isInfoEnabled()){logger.info("initialize  cluster controller successfully....................");}
		}
		//初始化事件处理器
		if(logger.isInfoEnabled()){logger.info("starting to init Event handler....................");}
		com.cdoframework.cdolib.servicebus.schema.EventProcessor eventProcessorDefine = serviceBus.getEventProcessor();
		if(eventProcessorDefine != null)
		{
			eventProcessor			= new EventProcessor();
			eventProcessor.setMaxFreeThreadCount(eventProcessorDefine.getMaxIdelTreadCount());
			eventProcessor.setMaxThreadCount(eventProcessorDefine.getMaxThreadCount());
			eventProcessor.setMinThreadCount(eventProcessorDefine.getMaxIdelTreadCount());
			eventProcessor.setMaxWaitEventCount(eventProcessorDefine.getMaxWaitEventCount());
		}
		
		//加载bigtable 配置文件
		Return ret=initBigTableConfig(strBigTableConfigXML);
		if(ret.getCode()!=0)
			return ret;
		
		//加载插件对象
		if(logger.isInfoEnabled()){logger.info("Staring load  plugins ....................");}
		int nPluginCount=serviceBus.getPluginXMLResourceCount();
		this.plugins=new ServicePlugin[nPluginCount];
		try
		{
			for(int i=0;i<nPluginCount;i++)
			{
				String strXMLResource=serviceBus.getPluginXMLResource(i);
				if(logger.isInfoEnabled()){logger.info("loading "+strXMLResource+"..............................");}
				String strXML=Utility.readTextResource(strXMLResource,"utf-8");
				if(strXML==null)
				{
					throw new Exception("Resource "+strXMLResource+" invalid");
				}
				com.cdoframework.cdolib.servicebus.schema.ServicePlugin servicePluginDefine=com.cdoframework.cdolib.servicebus.schema.ServicePlugin.fromXML(strXML);
				this.plugins[i]=new ServicePlugin();

				//初始化插件
				this.plugins[i].setServiceBus(this);
				this.plugins[i].setPublicBigTableGroupConfig(hmBigTableGroupConfig);
				this.plugins[i].setPublicDataGroup(hmDataGroup);
				this.plugins[i].setPublicNoSQLDataEngine(this.hmNoSQLDataEngine);
				this.plugins[i].init(i+"",this,servicePluginDefine);
			}
		}
		catch(Exception e)
		{
			plugins	=null;

			logger.error("when parse plugin ,caught Exception: ",e);
			return Return.valueOf(-1,"Init ServiceBus Failed: "+e.getLocalizedMessage());
		}
		if(logger.isDebugEnabled()){logger.debug("load  plugins successfully....................");}
		
		return Return.OK;
	}
	 	

	public void destroy()
	{
		for(int i=0;i<this.alService.size();i++)
		{
			alService.get(i).destroy();
		}
	}
	
	/**
	 * 启动Business服务
	 * @return
	 */
	public Return start()
	{
		Return ret=null;
		
		for(int i=0;i<this.alService.size();i++)
		{
			alService.get(i).start();
		}
		
		//Start ClusterController
		if(clusterController!=null)
		{
			ret=clusterController.start();
			if(ret.getCode()!=0)
			{
				stop();
			}
		}
		
		if(this.eventProcessor!=null)
		{
			ret = eventProcessor.start();
			if(ret.getCode()!=0)
			{
				logger.fatal(ret.getText());
			}
		}
		return Return.OK;
	}

	/**
	 * 停止Business服务
	 *
	 */
	public void stop()
	{
		//Stop ClusterController
		if(clusterController!=null)
		{
			clusterController.stop();
		}
		CacheHandler.getInstance().close();
		//Stop Plugin
		for(int i=0;i<this.alService.size();i++)
		{
			alService.get(i).stop();
		}
		
		// add by hill 2011年8月25日 stop eventProcessor
		if(eventProcessor != null ) {
			eventProcessor.stop();
		}
	}

	/**
	 * 调用事务处理的方法
	 * ServiceBus会根据Request内的数据和ServiceBus.xml的配置内容，自动查找到对应的插件，调用其对象提供的处理方法
	 * @param cdoRequest 事务请求对象
	 * @param cdoResponse 事务应答对象
	 * @return 事务处理结果
	 */
	public Return handleTrans(CDO cdoRequest,CDO cdoResponse)
	{
		String strServiceName = null;
		String strTransName=null;

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
		if(strTransName==null || strTransName.length()==0)
		{
			logger.error("strTransName can not be empty");
			return null;
		}
		onTransStarted(strServiceName,strTransName,cdoRequest);
		Return ret=null;
		// 正常处理
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
			return Return.valueOf(-1,"can not find trans handler named "+strServiceName+"."+strTransName);
		}
		onTransFinished(strServiceName,strTransName,cdoRequest,cdoResponse,ret);
		return ret;
	}

	public Object getSharedData(String strId)
	{
		if(strId==null)
		{
			return null;
		}
		
		Object objValue=null;
		
		ReentrantReadWriteLock.ReadLock lock=lockSharedData.readLock();
		lock.lock();
		objValue=hmSharedData.get(strId);
		lock.unlock();
		
		return objValue;
	}

	public void setSharedData(String strId,Object objValue)
	{
		if(strId==null)
		{
			return;
		}
		
		ReentrantReadWriteLock.WriteLock lock=lockSharedData.writeLock();
		lock.lock();
		hmSharedData.put(strId,objValue);
		lock.unlock();
	}
	
	public IService addService(IService service)
	{
		IService oldService = this.hmService.put(service.getServiceName(),service);
		if(oldService==null)
		{
			alService.add(service);
		}
		return oldService;
	}
 
	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------
	/**
	 * 调用事件处理的方法
	 * ServiceBus会根据Request内的数据和ServiceBus.xml的配置内容，自动查找到对应的插件，调用其对象提供的处理方法
	 * @param cdoEvent 事件对象
	 */
	public void handleEvent(CDO cdoEvent)
	{
		for(int i=0;i<this.alService.size();i++)
		{
			this.alService.get(i).handleEvent(cdoEvent);
		}

		onEventHandled(cdoEvent);
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

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------
	public void onTransStarted(String strServiceName,String strTransName,CDO cdoRequest)
	{
		try
		{
			if(logger.isInfoEnabled())
			{
				//日志太多的昨时解决方案
				String strTransName2 = strTransName.toUpperCase();
				if(strTransName2.startsWith("GET") || strTransName2.startsWith("FIND") || strTransName2.startsWith("RETRIEVE"))
				{
					return;
				}
				
				logger.info(new StringBuilder().append("Starting handle ").append(" ServceName=").append(strServiceName).append(" transName=").append(strTransName).append("\r\n").append(cdoRequest.toXMLLog()).toString());
				
			}
		}
		catch(Exception e)
		{
			
		}
	}
	public void onTransFinished(String strServiceName,String strTransName,CDO cdoRequest,CDO cdoResponse,Return retResult)
	{
			try
			{	
				if(logger.isInfoEnabled())
				{
					//日志太多的昨时解决方案
					String strTransName2 = strTransName.toUpperCase();
					if(strTransName2.startsWith("GET") || strTransName2.startsWith("FIND") || strTransName2.startsWith("RETRIEVE"))
					{
						return;
					}
					StringBuilder sb = new StringBuilder();
					sb.append("End handle ").append(" ServceName=").append(strServiceName).append(" transName=").append(strTransName).append("\r\n");
					if(retResult==null)
					{
						sb.append(" result is null ");
					}
					else
					{
						sb.append(" code=").append(retResult.getCode()).append(" text=").append(retResult.getText()).append(" info=").append(retResult.getInfo());
					}
					sb.append(" cdoResponse=").append(cdoResponse.toXMLLog());
					logger.info(sb.toString());
				}
			}
			catch(Exception e)
			{
				
			}
	}
	public void onTransUnsupported(CDO cdoRequest)
	{
		try
		{
			logger.error("Trans not supported:"+cdoRequest.getStringValue("strServiceName")+'.'+cdoRequest.getStringValue("strTransName"));
		}
		catch(Exception e)
		{
			
		}

	}
	
	public void onEventHandled(CDO cdoEvent)
	{
		try
		{
			if(logger.isDebugEnabled()){logger.debug(":Event handled:\r\n"+cdoEvent.toXML());}
		}
		catch(Exception e)
		{
			
		}
	}

	public void addClusterActiveService(String strActiveServiceId,IActiveService activeService)
	{
		if(clusterController!=null)
		{
			this.clusterController.addService(strActiveServiceId,activeService);
		}		
	}
	
	/**
	 * 服务检测信息集合
	 */
	public Map<Error, String> errorMap;

	/**
	 * @author:董超
	 * @author:lishiguang 重构
	 * @type: 追加
	 * @date: 2010-10-27
	 * @function: 验证serviceBus总线数据库连接池和各插件connection是否正常、getCategory
	 *            connection是否正常、mongodb和-memcache
	 */
	public boolean testServerState() throws RuntimeException {

		errorMap = new HashMap<Error, String>();

		// memcache验证
		if (!validateMemcache())
			return false;

		// mongodb验证
		if (!validateMongodb())
			return false;

		// 验证 getCategory
		if (!validateGetCategory())
			return false;

		// 验证总线mysql
		if (!validateMysql())
			return false;

		return true;
	}

	/**
	 * @author shiguang.li 2011-8-9 下午05:13:54
	 */
	public enum Error {
		memcache, mongodb, getcategory, mysql, non;
	}

	/**
	 * 探测服务状态
	 * 
	 * @return
	 */
	public String detectServerState() {

		final StringBuffer returnStr = new StringBuffer();
		if (errorMap != null) {
			Set<Map.Entry<Error, String>> set = errorMap.entrySet();
			for (Map.Entry<Error, String> entry : set) {
				returnStr.append(entry.getKey() + ":" + entry.getValue());
			}
		}
		return returnStr.toString();
	}

	/***
	 * 验证总线mysql是否正常
	 * 
	 * @return
	 */
	private boolean validateMysql() {
		try{
			// 正确性验证
			if (null != this.hmDataGroup) {
				// 遍历hmDataGroup MAP
				for (String key : this.hmDataGroup.keySet()) {
					// 取得hmDataGroup value
					com.cdoframework.cdolib.base.CycleList<IDataEngine> cycleList = this.hmDataGroup
							.get(key);
					// 正确性验证，防止报空指针
					if (null != cycleList) {
						// 取得IDataEngine
						IDataEngine ide = cycleList.get();
						try {
							// 获取数据库连接
							Connection connection = ide.getConnection();
							
							// 正确性验证，防止报空指针
							if (null != connection) {
								// 进行最大超时时间为3秒的验证
								if (!connection.isClosed()) {
									PreparedStatement ps = null;
									try {
										ps = connection
												.prepareStatement("select 1 from dual");
										if (null != ps) {
											ps.executeQuery();
											ps.close();
											return true;
										} else {
											errorMap.put(Error.mysql,
													"preparedStatement is null");
											return false;
										}
									} catch (Exception e) {
										logger.error(e.getMessage(), e);
										if (null != ps) {
											ps.close();
										}
										errorMap.put(Error.mysql, e.getMessage());
										return false;
									}finally{
										if(null!=connection){
											connection.close();
										}
									}
								}
							}
							// connection null
							else {
								errorMap.put(Error.mysql, "connection is null");
								return false;
							}
						} catch (SQLException e) {
							logger.error(e.getMessage(), e);
							errorMap.put(Error.mysql, e.getMessage());
							return false;
						}
						// cycleList null
					} else {
						errorMap.put(Error.mysql, "init DataGroup is fail");
						return false;
					}
				}
			}
			// hmDataGroup null
			else {
				return false;
			}
		}catch(Exception e){
			errorMap.put(Error.getcategory, e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * 验证 获取类目信息是否成功
	 * 
	 * @return
	 */
	private boolean validateGetCategory() {
		try {
			final String SCENE_COUNTER = "counter";
			CDO cdoRequest = new CDO();
			CDO cdoResponse = new CDO();
			cdoRequest.setStringValue("strServiceName", "CategoryService");
			cdoRequest.setStringValue("strTransName", "getChildCategoryCount");
			cdoRequest.setStringValue("strScene", SCENE_COUNTER);
			cdoRequest.setLongValue("lCategoryId", 0);
			Return ret = this.handleTrans(cdoRequest, cdoResponse);
			// 结果为0正常
			if (ret != null && ret.getCode() != 0) {
				errorMap.put(Error.getcategory, ret.getText());
				return false;
			}
		} catch (Exception e) {
			errorMap.put(Error.getcategory, e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * 验证mongodb 是否正常
	 * 
	 * @return
	 */
	private boolean validateMongodb() {
		try {
			if (this.hmNoSQLDataEngine.size() > 0) {
				Iterator<NoSQLDataEngine> iterator = this.hmNoSQLDataEngine
						.values().iterator();
				while (iterator.hasNext()) {
					boolean bOK = iterator.next().testConnection();
					if (bOK == false) {// TODO 输出日志
						errorMap.put(Error.mongodb, "test connection is fail");
						return false;
					}
				}
			}
		} catch (Exception e) {
			errorMap.put(Error.getcategory, e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * 验证 memcache 是否正常
	 */
	private boolean validateMemcache() {
		try {
			final StringBuffer errorInfo = new StringBuffer();
			List<String> list = CacheHandler.getInstance().getErrorServer();
			if (list != null && list.size() > 0) {
				logger.error("error cache server list: ");
				{
					for (String server : list) {
						logger.error(server);
						errorInfo.append(server);
						errorInfo.append("|");
					}
					if (errorInfo.length() > 0) {
						errorMap.put(Error.memcache, errorInfo.toString());
						return false;
					}
				}
			}
		}catch(Exception e){
			logger.error("testServerState:" + e.getMessage(), e);
			errorMap.put(Error.getcategory, e.getMessage());
			return false;
		}
		return true;
	}

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public ServiceBus()
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		clusterController		=null;
		hmSharedData			=new HashMap<String,Object>();
		lockSharedData			=new ReentrantReadWriteLock();
		btEngine				=new BigTableEngine();
//		hmCacheClient			=new HashMap<String,CacheClient>(3);
		hmNoSQLDataEngine		=new HashMap<String,NoSQLDataEngine>(3);
		hmService 				= new HashMap<String,IService>(20);
		alService				= new ArrayList<IService>(20) ;
		hmParameterMap			= new HashMap<String,String>(10);	
		hmBigTableGroupConfig	=new HashMap<String,BigTable[]>(2);
	}
	public ArrayList<IService> getAlService() {
		return alService;
	}
	public HashMap<String,com.cdoframework.cdolib.base.CycleList<IDataEngine>> getHMDataGroup(){		
		return hmDataGroup;		
	}
}
