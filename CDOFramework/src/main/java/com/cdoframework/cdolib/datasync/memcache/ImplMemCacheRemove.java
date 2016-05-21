package com.cdoframework.cdolib.datasync.memcache;


import org.apache.log4j.Logger;

import com.cdoframework.cdolib.datasync.Task;
import com.cdoframework.cdolib.datasync.TaskInfo;
import com.cdoframework.cdolib.datasync.exception.ExecuteException;
import com.cdoframework.cdolib.framework.CacheHandler;

/**
 * MemCache 删除缓存操作Task任务实现类
 * @author dylan_xu
 */
public class ImplMemCacheRemove extends Task<Boolean>
{
	private static Logger logger = Logger.getLogger(ImplMemCacheRemove.class);
	
	public ImplMemCacheRemove(TaskInfo taskInfo)
	{
		this.taskInfo = taskInfo;
	}

	@Override
	public synchronized Boolean execute() throws ExecuteException
	{
		boolean result = false;
		if(taskInfo != null)
		{
			com.cdoframework.cdolib.framework.CacheHandlerTemplate cacheHandler = taskInfo.getCacheHandler();
			if(cacheHandler != null)
			{
				try
				{
					result = CacheHandler.getInstance().delete(cacheHandler.getStrCacheServerId(), taskInfo.getStrKey());
				}
				catch(Exception e)
				{
					logger.error(e.getMessage());
					throw new ExecuteException(e);
				}
			}
			else
			{
				logger.error("[MemCacheTaskObject cacheHandler IS NULL]");
			}
		}
		else
		{
			logger.error("[MemCacheTaskObject TASK IS NULL]");
		}
		return result;
	}
	
	@Override
	public synchronized Boolean repair() throws ExecuteException {
		this.nRepairTimes++;
		logger.debug("删除数据缓存失败，进行数据删除操作！删除Key=" + taskInfo.getStrKey());
		return this.execute();
	}
}
