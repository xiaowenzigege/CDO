package com.cdoframework.cdolib.framework;

import java.util.List;

import net.spy.memcached.MemcachedNode;

/**
 * @author Aaron
 */
public interface ICacher
{
	/**
	 * 更新或新增缓存
	 * @param cacheServerId
	 * @param strKey
	 * @param objValue
	 * @param nExpireTime 失效时间,单位为毫秒
	 * @return
	 */
	boolean put(String cacheServerId,String strKey,Object objValue,int nExpireTime) throws Exception;
	/**
	 * 更新或新增缓存
	 * @param cacheServerId
	 * @param strKey
	 * @param objValue
	 * @return
	 */
	boolean put(String cacheServerId,String strKey,Object objValue) throws Exception;
	
	/**
	 * 删除缓存
	 * @param cacheServerId
	 * @param strKey
	 * @return
	 */
	boolean delete(String cacheServerId,String strKey) throws Exception;
	
	/**
	 * 取缓存
	 * @param cacheServerId
	 * @param strKey
	 * @return
	 */
	Object get(String cacheServerId,String strKey) throws Exception;
	
	MemcachedNode getMemcachedNode(String cacheServerId, String strKey);
	
	boolean removeURLCache(String strURLCacheServerId,String strURL,String strUrlHost);
	
	List<String> getErrorServer();
	List<String> getErrorServer(String cacheServerId);

	void close();
	boolean testConnection(String cacheServerId);
}
