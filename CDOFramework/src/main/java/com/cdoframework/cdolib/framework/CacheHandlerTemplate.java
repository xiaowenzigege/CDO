package com.cdoframework.cdolib.framework;

import java.util.List;

import net.spy.memcached.MemcachedNode;

import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.datasync.Task;
import com.cdoframework.cdolib.datasync.TaskFuture;
import com.cdoframework.cdolib.datasync.exception.FactoryException;
import com.cdoframework.cdolib.datasync.exception.HasNoParameterException;
import com.cdoframework.cdolib.datasync.facade.DatasyncFacade;
import com.cdoframework.cdolib.parameter.GlobalConfiguration;

/**
 * @author Aaron
 */
public class CacheHandlerTemplate
{

	//内部类,所有内部类在此声明----------------------------------------------------------------------------------

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------

	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------
	
	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------

	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------
	private String strCacheServerId;
	private String strURLCacheServerId;
	
	public void setURLCacheServerId(String strURLCacheServerId)
	{
		this.strURLCacheServerId = strURLCacheServerId;
	}
	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	//私有方法 所有仅在本类或派生类中使用的函数在此定义为private方法-------------------------------------------

	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------
	public boolean delete(String strKey) throws Exception
	{
		try{
			return CacheHandler.getInstance().delete(strCacheServerId,strKey);
		}catch(Exception e){
			if(GlobalConfiguration.getInstance().isMemcacheFailedRetry())
				return this.dataSyncDelete(strKey).isDone();
			return false;
		}
	}
	
	/**
	 * CDO中包含：strCacheServerId（缓存服务器名）
	 * 		    strKey（key）
	 * 		    strValue(value)
	 *          nTaskType(数据操作类型)
	 *          nMaxRepeatTimes(同步操作最大次数，默认为3)
	 *          nExpairTimes(缓存保留时间，可选)
	 * @param cdo
	 * @return
	 * @throws FactoryException
	 * @throws HasNoParameterException
	 */
	public TaskFuture dataSyncDelete(String strKey, int nMaxRepeatTimes) throws Exception
	{
		return DatasyncFacade.submit(strKey, null, nMaxRepeatTimes, Task.TYPE_MEMCACHE_REMOVE, this, null);
	}

	public TaskFuture dataSyncDelete(String strKey) throws Exception
	{
		return dataSyncDelete(strKey, GlobalConfiguration.getInstance().getMemcacheFailedRetryDefaultTime());
	}
	
	
	public TaskFuture dataSyncDelete(CDO cdo) throws Exception
	{
		cdo.setIntegerValue("nTaskType", Task.TYPE_MEMCACHE_REMOVE);
		return doDataSync(cdo);
	}
	
	public Object get(String strKey) throws Exception
	{
		return CacheHandler.getInstance().get(strCacheServerId,strKey);
	}
	
	public MemcachedNode  getMemcachedNode(String strKey)
	{
		return CacheHandler.getInstance().getMemcachedNode(strCacheServerId,strKey);
	}
	public boolean put(String strKey,Object objValue,int expireTime) throws Exception
	{
		try{
			return CacheHandler.getInstance().put(strCacheServerId,strKey,objValue,expireTime);
		}catch(Exception e){
			if(GlobalConfiguration.getInstance().isMemcacheFailedRetry())
				return this.dataSyncPut(strKey, objValue, GlobalConfiguration.getInstance().getMemcacheFailedRetryDefaultTime(), expireTime).isDone();
			return false;
		}
	}
	
	public TaskFuture dataSyncPut(String strKey, Object objValue) throws Exception
	{
		return dataSyncPut(strKey, objValue, GlobalConfiguration.getInstance().getMemcacheFailedRetryDefaultTime(), null);
	}
	
	/**
	 * 
	 * @param strKey
	 * @param objValue
	 * @param expireTime 缓存存活时间
	 * @return
	 * @throws Exception
	 */
	public TaskFuture dataSyncPut(String strKey, Object objValue, int expireTime) throws Exception
	{
		return dataSyncPut(strKey, objValue, GlobalConfiguration.getInstance().getMemcacheFailedRetryDefaultTime(), expireTime);
	}

	public TaskFuture dataSyncPut(String strKey, Object objValue, int nMaxRepeatTimes, Integer nExpireTime) throws Exception
	{
		return DatasyncFacade.submit(strKey, objValue, nMaxRepeatTimes, Task.TYPE_MEMCACHE_PUT, this, nExpireTime);
	}

	public TaskFuture dataSyncPut(CDO cdo) throws Exception
	{
		cdo.setIntegerValue("nTaskType", Task.TYPE_MEMCACHE_PUT);
		return doDataSync(cdo);
	}

	/**
	 * @param cdo
	 * @return
	 * @throws FactoryException
	 * @throws HasNoParameterException
	 */
	private TaskFuture doDataSync(CDO cdo) throws FactoryException,
			HasNoParameterException {
		
		if(!cdo.exists("strCacheServerId")){
			cdo.setStringValue("strCacheServerId", this.strCacheServerId);
		}
		return DatasyncFacade.submit(cdo);
	}

	public boolean put(String strKey,Object objValue) throws Exception
	{
		try{
			return CacheHandler.getInstance().put(strCacheServerId,strKey,objValue);
		}catch(Exception e){
			if(GlobalConfiguration.getInstance().isMemcacheFailedRetry())
				return this.dataSyncPut(strKey, objValue).isDone();
			return false;
		}
	}

	public boolean removeURLCache(String strURL,String strUrlHost)
	{
		return CacheHandler.getInstance().removeURLCache(strURLCacheServerId,strURL,strUrlHost);
	}
	public List<String> getErrorServer()
	{
		return CacheHandler.getInstance().getErrorServer(strCacheServerId);
	}
	public boolean testConnection()
	{
		return CacheHandler.getInstance().testConnection(strCacheServerId);
	}
	public void close()
	{
		
	}

	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public CacheHandlerTemplate(String strCacheServerId)
	{
		this.strCacheServerId = strCacheServerId;
	}
	
	public String getStrCacheServerId(){
		return this.strCacheServerId;
	}
}
