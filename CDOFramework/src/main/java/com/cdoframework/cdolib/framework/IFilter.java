package com.cdoframework.cdolib.framework;

import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;

public interface IFilter
{
	String RELOAD_FLAG = "nReloadFlag";
	/**
	 * 
	 * @param strServiceName
	 * @param strTransName
	 * @param cdoRequest
	 * @return
	 * 0:成功或者没有同步事务定义
	 * 1:有同步执行的事件未执行成功
	 */
	Return handlePreEvent(String strServiceName,String strTransName,CDO cdoRequest);
	
	/**
	 * 
	 * @param strServiceName
	 * @param strTransName
	 * @param cdoRequest
	 * @param cdoResponse
	 * @param transReturn
	 * @return
	 */
	Return handlePostEvent(String strServiceName,String strTransName,CDO cdoRequest,CDO cdoResponse,Return transReturn);
	
	/**
	 * 
	 * @param strServiceName
	 * @param strTransName
	 * @param cdoRequest
	 * @param strExt
	 * @return 
	 * 0:cache并且从cache server中取得值,strExt的值为cache的key
	 * 1:不cache
	 * 2:cache,但cache server中无对应的值
	 * 3:其它程序逻辑处理cache
	 * -1:取cache时发生系统错误
	 * 
	 */
	Return handlePreTransCache(String strServiceName,String strTransName,CDO cdoRequest,CDO cdoResponse);
	
	
	/**
	 * 
	 * @param strExtCacheKey
	 * @param objExtTransDefine
	 * @param cdoResponse
	 * @return
	 */
	void handlePostTransCache(String strServiceName,String strTransName,CDO cdoRequest,CDO cdoResponse);
	
}
