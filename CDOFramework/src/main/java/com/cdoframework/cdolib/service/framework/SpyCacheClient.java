package com.cdoframework.cdolib.service.framework;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.Future;

import net.spy.memcached.MemcachedClient;

import org.apache.log4j.Logger;

import com.cdoframework.cdolib.base.Return;


/**
 * @author Administrator
 * 
 *TODO 考虑cluster
 */
public class SpyCacheClient
{

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------
	static Logger logger = Logger.getLogger(SpyCacheClient.class);
	public static int FOREVER=2000000;
	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------
	
	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------
	private MemcachedClient client;
	public MemcachedClient getCacheClient()
	{
		return this.client;
	}
	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------

	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	//私有方法 所有仅在本类或派生类中使用的函数在此定义为private方法-------------------------------------------

	//TODO 此方法要改成异步方式

    
	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------
 
	public Return init(String host, int port)
	{
		InetSocketAddress isa = new InetSocketAddress(host,port);
		try
		{
			this.client = new MemcachedClient(isa);
			return Return.OK;
		}
		catch(IOException e)
		{
			logger.error("can not connect to "+host+":"+port);
			return Return.valueOf(-1,"can not connect to "+host+":"+port,e);
		}
	}
	public Return init(List<InetSocketAddress> lsISA)
	{
		try
		{
			this.client = new MemcachedClient(lsISA);
			return Return.OK;
		}
		catch(IOException e)
		{
			logger.error(e.getLocalizedMessage());
			return Return.valueOf(-1,e.getLocalizedMessage(),e);
		}
	}
    public boolean putCacheValue(String strCacheKey,Object obj,int nExpireTime)
    {
		Future<Boolean> fb = this.client.set(strCacheKey,nExpireTime,obj);
		try
		{
			if(fb.get().booleanValue()==false)
			{			
				if(logger.isDebugEnabled()){
					logger.debug("faild to store memcache key:"+strCacheKey);
				}
				return false;
			}
		}
		catch(Exception e)
		{
			if(logger.isDebugEnabled()){
				logger.debug("faild to get result of [storing memcache], key:"+strCacheKey);
			}
			return false;
		}
		return true;
    }
    public boolean putCacheValue(String strCacheKey,Object obj)
    {
     	return     	putCacheValue(strCacheKey,obj,FOREVER);
    }
	
	
	public Object getCacheValue(String strCacheKey)
	{
    	if(strCacheKey==null)
    	{
    		return null;
    	}
    	Object objResult =  null;
		try
		{
			objResult = this.client.get(strCacheKey);
		}
		catch(Exception e)
		{
			if(logger.isDebugEnabled()){logger.debug(e);}
		} 
    	return objResult;
	}
	public boolean deleteCacheValue(String strCacheKey)
	{
		Future<Boolean> fb = this.client.delete(strCacheKey);
		try
		{
			if(fb.get().booleanValue()==false)
			{			
				if(logger.isDebugEnabled()){
					logger.debug("faild to delete memcache key:"+strCacheKey);
				}
				return false;
			}
		}
		catch(Exception e)
		{
			if(logger.isDebugEnabled()){
				logger.debug("faild to get result of [deleting memcache], key:"+strCacheKey);
			}
			return false;
		}
		return true;
	}
	public void close()
	{
		this.client.shutdown();
	}
	
	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	/**
	 * 测试连接是否正常
	 * 如果没有启用memcache,则直接返回正常 
	 * @return
	 */
	public boolean testConnection()
	{
		if(this.client==null)
		{
			return false;
		}
		boolean bresult = this.putCacheValue("_MALL_TEST_CONNECTION_","_MALL_TEST_CONNECTION_");
		if(bresult == false)
		{
			return false;
		}
		String value = (String)this.getCacheValue("_MALL_TEST_CONNECTION_");
		if(value==null)
		{
			return false;
		}	
		return true;
	}
	
	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------
	public SpyCacheClient()
	{
		
	}
	public SpyCacheClient(String host, int port) throws Exception
	{
		Return ret = this.init(host,port);
		if(ret.getCode()!=0)
		{
			throw new Exception(ret.getText(),ret.getThrowable());
		}
	}
	public SpyCacheClient(List<InetSocketAddress> lsISA) throws Exception
	{
		Return ret = this.init(lsISA);
		if(ret.getCode()!=0)
		{
			throw new Exception(ret.getText(),ret.getThrowable());
		}
	}
}
