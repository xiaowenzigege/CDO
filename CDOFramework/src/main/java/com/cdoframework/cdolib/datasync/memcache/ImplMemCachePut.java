package com.cdoframework.cdolib.datasync.memcache;

import org.apache.log4j.Logger;

import com.cdoframework.cdolib.datasync.Task;
import com.cdoframework.cdolib.datasync.TaskInfo;
import com.cdoframework.cdolib.datasync.exception.ExecuteException;
import com.cdoframework.cdolib.framework.CacheHandler;
import com.cdoframework.cdolib.framework.CacheHandlerTemplate;


/**
 * MemCache 更新或新增缓存操作Task任务实现类
 * @author dylan_xu
 */
public class ImplMemCachePut extends Task<Boolean>
{
	private static final Logger logger = Logger.getLogger(ImplMemCachePut.class);

	public ImplMemCachePut(){};
	
	public ImplMemCachePut(TaskInfo taskInfo)
	{
		this.taskInfo = taskInfo;
	}

	@Override
	public synchronized Boolean execute() throws ExecuteException
	{
		boolean result = false;
		if(taskInfo != null)
		{
			CacheHandlerTemplate cacheHandler = taskInfo.getCacheHandler();
			if(cacheHandler != null)
			{
				try
				{
					if(taskInfo.getExpairTimes() != 0){
						result = CacheHandler.getInstance().put(cacheHandler.getStrCacheServerId() ,taskInfo.getStrKey(), taskInfo.getObjValue(),  taskInfo.getExpairTimes());
					}
					else{
						//执行memcache更新或新增缓存操作
						result = CacheHandler.getInstance().put(cacheHandler.getStrCacheServerId() ,taskInfo.getStrKey(), taskInfo.getObjValue());
					}
					
				}
				catch(Exception e)
				{
					logger.error(e.getMessage());
					throw new ExecuteException(e);
				}
			}
			else
			{
				logger.error("[MemCachePutTask cacheHandler IS NULL]");
			}
		}
		else
		{
			logger.error("[MemCachePutTask TASK IS NULL]");
		}
		return result;
	}
	
	/**
	 * 数据同步入memcache失败时，删除memcache中当前key对于的值
	 */
	public synchronized Boolean repair() throws ExecuteException {
		this.nRepairTimes++;
		CacheHandlerTemplate cacheHandler = taskInfo.getCacheHandler();
		try {
			logger.debug("数据放入缓存失败，进行数据删除操作！删除Key=" + taskInfo.getStrKey());
			return CacheHandler.getInstance().delete(cacheHandler.getStrCacheServerId(), taskInfo.getStrKey());
		} catch (Exception e) {
			logger.error("数据删除失败：key="+taskInfo.getStrKey()+"原因："+e.getMessage());
			throw new ExecuteException(e);
		}
	}
}
