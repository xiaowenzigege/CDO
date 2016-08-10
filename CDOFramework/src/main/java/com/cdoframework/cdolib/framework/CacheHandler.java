package com.cdoframework.cdolib.framework;

import java.util.ArrayList;
import java.util.List;

import net.spy.memcached.MemcachedNode;

/**
 * @author Aaron
 */
public class CacheHandler implements ICacher
{

	//内部类,所有内部类在此声明----------------------------------------------------------------------------------

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------
	private static CacheHandler instance = new CacheHandler();
	public static CacheHandler getInstance()
	{
		return instance;
	}
	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------

	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------

	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------
	private ICacher cacheImpl;
	public void setCacher(ICacher cacher)
	{
		this.cacheImpl = cacher;
	}
	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	//私有方法 所有仅在本类或派生类中使用的函数在此定义为private方法-------------------------------------------

	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------

	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see com.cdoframework.cdolib.framework.ICacher#delete(java.lang.String, java.lang.String)
	 */
	public boolean delete(String cacheServerId,String strKey) throws Exception
	{
		if(cacheImpl!=null)
		{
			return cacheImpl.delete(cacheServerId,strKey);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.cdoframework.cdolib.framework.ICacher#get(java.lang.String, java.lang.String)
	 */
	public Object get(String cacheServerId,String strKey) throws Exception
	{
		if(cacheImpl!=null)
		{
			return cacheImpl.get(cacheServerId,strKey);
		}
		return null;
	}

	
	/* (non-Javadoc)
	 * @see com.cdoframework.cdolib.framework.ICacher#put(java.lang.String, java.lang.String, java.lang.Object, int)
	 */
	public boolean put(String cacheServerId,String strKey,Object objValue,int expireTime) throws Exception
	{
		if(cacheImpl!=null)
		{
			return cacheImpl.put(cacheServerId,strKey,objValue,expireTime);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.cdoframework.cdolib.framework.ICacher#put(java.lang.String, java.lang.String, java.lang.Object)
	 */
	public boolean put(String cacheServerId,String strKey,Object objValue) throws Exception
	{
		if(cacheImpl!=null)
		{
			return cacheImpl.put(cacheServerId,strKey,objValue);
		}
		return false;
	}
	
	public boolean removeURLCache(String strURLCacheServerId,String strURL,String strUrlHost)
	{
		if(cacheImpl!=null)
		{
			return cacheImpl.removeURLCache(strURLCacheServerId,strURL,strUrlHost);
		}
		return false;
	}
	public List<String> getErrorServer()
	{
		if(cacheImpl!=null)
		{
			return cacheImpl.getErrorServer();
		}
		return new ArrayList<String>(0);
	}
	

	public List<String> getErrorServer(String cacheServerId)
	{
		if(cacheImpl!=null)
		{
			return cacheImpl.getErrorServer(cacheServerId);
		}
		return new ArrayList<String>(0);
	}
	
	public boolean testConnection(String cacheServerId)
	{
		if(cacheImpl!=null)
		{
			return cacheImpl.testConnection(cacheServerId);
		}
		return false;
	}

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------


	public void close()
	{
		if(cacheImpl!=null)
		{
			cacheImpl.close();
		}
		
	}

	@Override
	public MemcachedNode getMemcachedNode(String cacheServerId, String strKey) {
		if(cacheImpl!=null)
		{
			return cacheImpl.getMemcachedNode(cacheServerId,strKey);
		}
		return null;
	}



}
