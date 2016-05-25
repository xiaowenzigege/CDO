package com.cdoframework.cdolib.service.framework;

import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.service.framework.xsd.URLCacheServer;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveURLCache;

public interface IURLCacheServerClient
{
	Return init(URLCacheServer urlCacheServer);
	void removeURLCache(String url,String webServerHost) throws Exception;
	boolean removeCacheURL(RemoveURLCache removeURLCache,CDO cdoRequest,CDO cdoResponse);	
	boolean removeCacheURL(int fromIndex,String strIndexId,RemoveURLCache removeURLCache,CDO cdoRequest,CDO cdoResponse);
	String getId();
	boolean testConnection();
}
