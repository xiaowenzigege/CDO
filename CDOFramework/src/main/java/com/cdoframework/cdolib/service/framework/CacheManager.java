package com.cdoframework.cdolib.service.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.spy.memcached.MemcachedNode;

import org.apache.log4j.Logger;

import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.framework.ICacher;
import com.cdoframework.cdolib.service.framework.xsd.MemCacheServerGroup;
import com.cdoframework.cdolib.service.framework.xsd.URLCacheServer;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveURLCache;

/**
 * @author Aaron
 */
public class CacheManager implements ICacher
{

	//内部类,所有内部类在此声明----------------------------------------------------------------------------------
	static Logger logger = Logger.getLogger(CacheManager.class);
	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------

	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------
	private HashMap<String,IURLCacheServerClient> hmSquidClient;
	private HashMap<String,CacheClient> hmCacheClient;
//	private HashMap<String,String> hmCachedKey;//本地缓存一些内容,暂缓实现
	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------

	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------

	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	//私有方法 所有仅在本类或派生类中使用的函数在此定义为private方法-------------------------------------------

	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------
	public Return initMemCacheServer(MemCacheServerGroup[] memCacheServerGroups)
	{
		if(memCacheServerGroups !=null && memCacheServerGroups.length>0)
		{
			for(MemCacheServerGroup group:memCacheServerGroups)
			{
				CacheClient cacheClient = new CacheClient();
				Return ret = cacheClient.initMemCach(group);
				if(ret.getCode()!=0)
				{
					logger.error(ret.getText());
					return ret;
				}
				this.hmCacheClient.put(group.getId(),cacheClient);
			}
		}
		return Return.OK;
	}
	void intSquidCacheServer(URLCacheServer[] urlCacheServers)
	{
		if(urlCacheServers!=null && urlCacheServers.length>0)
		{
			for(URLCacheServer urlCacheServer:urlCacheServers)
			{
				IURLCacheServerClient icc = null;
				String strId = urlCacheServer.getId();
				String strClassPath = urlCacheServer.getClassPath();
				if(strClassPath==null)
				{
					icc = new SquidSocketClient();
				}
				else
				{
					try
					{
						icc = (IURLCacheServerClient)Class.forName(strClassPath).newInstance();
					}
					catch(Exception e)
					{
						logger.warn(e);
						continue;
					}
				}
				Return ret = icc.init(urlCacheServer);
				if(ret.getCode()!=0)
				{
					logger.warn(ret.getCode());
					continue;
				}
				this.hmSquidClient.put(strId,icc);
			}
		}
	}
	
	public boolean removeURLCache(RemoveURLCache removeURLCache,CDO cdoRequest,CDO cdoResponse)
	{
		String strServerId = removeURLCache.getServerId();
		if(strServerId == null)
		{
			return false;
		}
		IURLCacheServerClient squidClient = this.hmSquidClient.get(strServerId);
		if(squidClient==null)
		{
			return false;
		}
		return squidClient.removeCacheURL(removeURLCache,cdoRequest,cdoResponse);		
	}
	
	public boolean removeURLCache(int fromIndex,String strIndexId,RemoveURLCache removeURLCache,CDO cdoRequest,CDO cdoResponse)
	{
		String strServerId = removeURLCache.getServerId();
		if(strServerId == null)
		{
			return false;
		}
		IURLCacheServerClient squidClient = this.hmSquidClient.get(strServerId);
		if(squidClient==null)
		{
			return false;
		}
		return squidClient.removeCacheURL(fromIndex,strIndexId,removeURLCache,cdoRequest,cdoResponse);		
	}
 
	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------
	public boolean delete(String cacheServerId,String strKey) throws Exception
	{
		CacheClient cacheClient = this.hmCacheClient.get(cacheServerId);
		if(cacheClient==null){return false;}
		return cacheClient.deleteCacheValue(strKey);
	}

	public Object get(String cacheServerId,String strKey) throws Exception
	{
		CacheClient cacheClient = this.hmCacheClient.get(cacheServerId);
		if(cacheClient==null){return null;}
		return cacheClient.getCacheValue(strKey);
	}

	public MemcachedNode getMemcachedNode(String cacheServerId,String strKey) 
	{
		CacheClient cacheClient = this.hmCacheClient.get(cacheServerId);
		if(cacheClient==null){return null;}
		return cacheClient.getMemcachedNode(strKey);
	}
	
	public boolean put(String cacheServerId,String strKey,Object objValue,int expireTime) throws Exception
	{
		CacheClient cacheClient = this.hmCacheClient.get(cacheServerId);
		if(cacheClient==null){return false;}
		return cacheClient.putCacheValue(strKey,objValue,expireTime);
	}

	public boolean put(String cacheServerId,String strKey,Object objValue) throws Exception
	{
		CacheClient cacheClient = this.hmCacheClient.get(cacheServerId);
		if(cacheClient==null){return false;}
		return cacheClient.putCacheValue(strKey,objValue);
	}

	public boolean removeURLCache(String strURLCacheServerId,String strURL,String strUrlHost)
	{
		IURLCacheServerClient squidClient = this.hmSquidClient.get(strURLCacheServerId);
		if(squidClient==null){return false;}
		try
		{
			squidClient.removeURLCache(strURL,strUrlHost);
		}
		catch(Exception e)
		{
			logger.error("removeURLCache:"+e.getMessage(),e);
			return false;
		}
		return true;
	}

	public List<String> getErrorServer()
	{		
		if(this.hmCacheClient==null || this.hmSquidClient==null)
		{
			return new ArrayList<String>(0);
		}
		List<String> lsErrors = new ArrayList<String>();
		
		if(this.hmCacheClient.size()>0)
		{		
			CacheClient[] clients = new CacheClient[this.hmCacheClient.size()];
			this.hmCacheClient.values().toArray(clients);
		
			for(CacheClient cc:clients)
			{
				if(cc.testConnection()==false)
				{
					lsErrors.add("ERROR MEMCACHE SERVER:"+cc.getId());
				}
			}
		}
		return lsErrors;		
	}

	public boolean testConnection(String cacheServerId)
	{
		if(this.hmCacheClient==null )
		{
			return false;
		}
		CacheClient cacheClient = this.hmCacheClient.get(cacheServerId);
		if(cacheClient==null){
			return false;
		}
		return cacheClient.testConnection();
	}

	public List<String> getErrorServer(String cacheServerId)
	{		
		if(this.hmCacheClient==null)
		{
			return new ArrayList<String>(0);
		}
		
		List<String> lsErrors = new ArrayList<String>();
		CacheClient cacheClient = this.hmCacheClient.get(cacheServerId);
		if(cacheClient==null){
			return new ArrayList<String>(0);
		}
		if(cacheClient.testConnection()==false)
		{
			lsErrors.add("ERROR MEMCACHE SERVER:"+cacheClient.getId());
		}
		return lsErrors;		
	}

	public void close()
	{
		
		if(this.hmCacheClient==null || this.hmSquidClient==null)
		{
			return;
		}
		try
		{
			if(this.hmCacheClient.size()>0)
			{		
				CacheClient[] clients = new CacheClient[this.hmCacheClient.size()];
				this.hmCacheClient.values().toArray(clients);
			
				for(CacheClient cc:clients)
				{
					cc.close();
				}
			}
		}
		catch(Exception e)
		{

		}
	}


	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public CacheManager()
	{
		hmSquidClient	= new HashMap<String,IURLCacheServerClient>(2); 
		hmCacheClient = new HashMap<String,CacheClient>(3); 
//		hmCachedKey = new HashMap<String,String>(1000);
	}


}
