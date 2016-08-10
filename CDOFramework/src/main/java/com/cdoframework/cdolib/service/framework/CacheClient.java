package com.cdoframework.cdolib.service.framework;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import net.spy.memcached.MemcachedClient;
import net.spy.memcached.MemcachedNode;
import net.spy.memcached.internal.OperationFuture;

import org.apache.log4j.Logger;

import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.service.framework.xsd.CacheServer;
import com.cdoframework.cdolib.service.framework.xsd.MemCacheServerGroup;
import com.cdoframework.cdolib.service.framework.xsd.Server;

/**
 * @author Administrator
 * 
 *TODO 考虑cluster
 */
public class CacheClient
{

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------
	static Logger logger = Logger.getLogger(CacheClient.class);
	static int FOREVER=2000000;
	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------
	private ArrayList<MemcachedClient> alCacheClient;
	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------

	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------
	private String strId;

	public String getId()
	{
		return this.strId;
	}
	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	//私有方法 所有仅在本类或派生类中使用的函数在此定义为private方法-------------------------------------------
	
	//TODO 此方法要改成异步方式

    
	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------
 
    public boolean putCacheValue(String strCacheKey,Object obj,int nExpireTime)
    {
    	if(strCacheKey==null)
    	{
    		return false;
    	}
    	boolean bResult = true;
    	if(logger.isDebugEnabled() && obj!= null && obj.toString().contains("�")) {
    		logger.debug("乱码存入memcache："+strCacheKey + " : "+ nExpireTime+ " : "+obj);
    	}
		try
		{
			for(MemcachedClient cacheClient:alCacheClient)
			{
				Future<Boolean> fb = cacheClient.set(strCacheKey,nExpireTime,obj);
				if(fb.get().booleanValue()==false)
				{
					bResult = false;
					if(logger.isDebugEnabled()){
						logger.debug("faild to store memcache key:"+strCacheKey);
					}    			
				}
			}
		}
		catch(Exception e)
		{
			bResult = false;
			logger.warn(e);
			throw new RuntimeException(e);
		}
   	
    	return bResult;
    }
    public boolean putCacheValue(String strCacheKey,Object obj)
    {
     	return putCacheValue(strCacheKey,obj,FOREVER);     	
    }
	public boolean putResponseToCache(String strCacheKey,CDO cdoResponse)
	{
		String strXML=cdoResponse.toXML();
		return this.putCacheValue(strCacheKey,strXML);
	}
	public boolean putResponseToCache(String strCacheKey,CDO cdoResponse,int nExpireTime)
	{
		String strXML=cdoResponse.toXML();
		return this.putCacheValue(strCacheKey,strXML,nExpireTime);
	}
	
	public Object getCacheValue(String strCacheKey)
	{
    	if(strCacheKey==null)
    	{
    		return null;
    	}
		
    	Object objResult =  null;  
		for(MemcachedClient cacheClient:alCacheClient)
		{
			try
			{
				objResult = cacheClient.get(strCacheKey);
				if(objResult!=null)
				{
					break;
				}
			}
			catch(Exception e)
			{
				if(logger.isDebugEnabled()){logger.debug(e);}
			}  
		}
    	
    	return objResult;
	}
	public boolean deleteCacheValue(String strCacheKey)
	{
	   	if(strCacheKey==null)
    	{
    		return false;
    	}
    	boolean bResult = true;
    	for(MemcachedClient cacheClient:alCacheClient)
    	{
			try
			{
				Future<Boolean> fb = cacheClient.delete(strCacheKey);
				if(fb.get().booleanValue()==false)
				{
					bResult = false;
					if(logger.isDebugEnabled()){
						logger.debug("faild to delete memcache key:"+strCacheKey);
					}    			
				}    			
			}
			catch(Exception e)
			{
				bResult =  false;
				logger.warn(e);
				throw new RuntimeException(e);
			}
    	}
    	return bResult;
	}
	public 	Return initMemCach(MemCacheServerGroup group)
    {
		this.strId = group.getId();
		
		CacheServer[] cacheServers = group.getCacheServer();
		if(cacheServers==null || cacheServers.length==0)
		{
			String strText = "Got wrong cacheserver define with id: "+strId;
			logger.error(strText);
			return Return.valueOf(-1,strText);
		}

		for(CacheServer cacheServer:cacheServers)
		{
			Server[] servers=cacheServer.getServer();
			if(servers==null || servers.length==0)
			{
				String strText ="Wrong server conifg with id: "+this.strId;
				logger.error(strText);
				return Return.valueOf(-1,strText);
			}
			List<InetSocketAddress> lsinetSocketAddress = new ArrayList<InetSocketAddress>(servers.length);
			for(int i=0;i<servers.length;i++)
			{
				lsinetSocketAddress.add(new InetSocketAddress(servers[i].getIP(),servers[i].getPort()));
			}
			try
			{
				MemcachedClient cacheClient=new MemcachedClient(lsinetSocketAddress);
				this.alCacheClient.add(cacheClient);
			}
			catch(IOException e)
			{
				//允许目前memcache server不存在，报警出来即可
				logger.warn("Got wrong server conifg with id: "+this.strId,e);
			}			
		}

		return Return.OK;
    }
	public Return init()
	{
		return Return.OK;
	}
	public void close()
	{
		try
		{
			for(MemcachedClient mc:alCacheClient)
			{
				mc.shutdown();					
			}
		}
		catch(Exception e)
		{
			logger.warn(e);
		}
	}
	
	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	/**
	 * 测试连接是否正常
	 * 如果没有启用memcache,则直接返回正常 
	 * @return
	 */
	public boolean testConnection()
	{

		String strKey = "_MALL_TEST_CONNECTION_";

		for(MemcachedClient MC:alCacheClient)
		{
			Future<Boolean> fb = MC.set(strKey,FOREVER,"_MALL_TEST_CONNECTION_");
			try
			{
				if(fb!=null && fb.get().equals(Boolean.TRUE))
				{
					return true;
				}
			}
			catch(Exception e)
			{					
			}
			
		}		
		return false;
	}

	
	public List<String> getErrorServer()
	{
		List<String> lsErrors = new ArrayList<String>();
		String strKey = "_MALL_TEST_CONNECTION_";

		for(MemcachedClient mc : alCacheClient)
		{			
			OperationFuture<Boolean> fb = mc.set(strKey,Integer.MAX_VALUE,"_MALL_TEST_CONNECTION_");			
			try
			{
				if(fb!=null && fb.get().equals(Boolean.TRUE))
				{
					lsErrors.add("error Server: "+mc.toString());
				}
			}
			catch(Exception e)
			{					
			}
			
		}	

		return lsErrors;
	}
	
	
	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------
	public CacheClient()
	{
		alCacheClient = new ArrayList<MemcachedClient>(2);
	}
	
	public MemcachedNode getMemcachedNode(String strCacheKey)
	{
    	if(strCacheKey==null)
    	{
    		return null;
    	}
		
    	MemcachedNode objResult =  null;  
		for(MemcachedClient cacheClient:alCacheClient)
		{
			try
			{
				objResult = cacheClient.getNodeLocator().getPrimary(strCacheKey);
				if(objResult!=null)
				{
					break;
				}
			}
			catch(Exception e)
			{
				if(logger.isDebugEnabled()){logger.debug(e);}
			}  
		}
    	
    	return objResult;
	}
}
