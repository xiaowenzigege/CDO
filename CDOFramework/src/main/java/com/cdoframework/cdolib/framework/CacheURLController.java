/**
 * 
 */
package com.cdoframework.cdolib.framework;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;

/**
 * @author Aaron
 *
 */
public class CacheURLController
{
	private static Logger logger = Logger.getLogger(CacheURLController.class);
	public static long CACHE_AGE_DEFAULT=31536000000L;
	private HashMap<String,HashMap<String,CDO>> hmCacheURL;
	private static CacheURLController instance= new CacheURLController();
	
	public static CacheURLController getInstance()
	{
		return instance;
	}

	public Return init(String strXML)
	{
		CDO define = null;
		try
		{
			define = CDO.fromXML(strXML);
		}
		catch(Exception e)
		{
			logger.error("init:"+e.getMessage(),e);
			return Return.valueOf(-1,e.getLocalizedMessage());
		}
		if(define.isEmpty("cdosTransCache"))
		{
			if(logger.isDebugEnabled()){logger.debug("There is no trans cache define ,that's ok....................");}
			return Return.OK;
		}
		
		CDO[] cdosTransCache = define.getCDOArrayValue("cdosTransCache");
		try
		{
			for(CDO cacheURL : cdosTransCache)
			{
				String strServiceName = cacheURL.getStringValue("strServiceName");
				String strTransName = cacheURL.getStringValue("strTransName");
				HashMap<String,CDO> serviceMap = hmCacheURL.get(strServiceName);
				if(serviceMap==null)
				{
					serviceMap = new HashMap<String,CDO>(10);
					hmCacheURL.put(strServiceName,serviceMap);
				}
				CDO odlcacheURL = serviceMap.put(strTransName,cacheURL);
				if(odlcacheURL!=null)
				{
					return Return.valueOf(-1,"duplicate cacheURL define serviceName="+strServiceName+" transName="+strTransName);
				}
			}
		}catch(Exception e)
		{
			logger.error("init:"+e.getMessage(),e);
			return Return.valueOf(-1,e.getLocalizedMessage());
		}
		return Return.OK;
	}
	public long getExpireTime(CDO cdoRequest,CDO cdoResponse)
	{
		String strServiceName = null;
		String strTransName = null;
		try
		{
			strServiceName = cdoRequest.getStringValue("strServiceName");
			strTransName = cdoRequest.getStringValue("strTransName");
		}
		catch(Exception e)
		{
			return -1;
		}
		
		HashMap<String,CDO> serviceMap = hmCacheURL.get(strServiceName);
		if(serviceMap==null)
		{
			return -1;
		}
		CDO cacheURL = serviceMap.get(strTransName);
		if(cacheURL==null)
		{
			return -1;
		}
		if(cacheURL.exists("strsExcludeKey"))
		{
			String[] strsExcludeKey = cacheURL.getStringArrayValue("strsExcludeKey");
			for(String strExcludeKey:strsExcludeKey)
			{
				if(cdoRequest.exists(strExcludeKey))
				{//被排除掉了
					return -1;
				}
			}
		}
		if(cacheURL.exists("lExpireTime"))
		{
			return cacheURL.getLongValue("lExpireTime");
		}
		else
		{
			return CACHE_AGE_DEFAULT;
		}
	}


	private CacheURLController()
	{
		hmCacheURL = new HashMap<String,HashMap<String,CDO>>(20);
	}
}
