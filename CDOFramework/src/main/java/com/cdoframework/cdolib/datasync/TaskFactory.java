package com.cdoframework.cdolib.datasync;

import com.cdoframework.cdolib.datasync.exception.FactoryException;
import com.cdoframework.cdolib.datasync.handTrans.ImplDBSQLTrans;
import com.cdoframework.cdolib.datasync.memcache.ImplMemCachePut;
import com.cdoframework.cdolib.datasync.memcache.ImplMemCacheRemove;

public class TaskFactory
{
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID=1L;

	public static Task createTaskFactory(TaskInfo task)throws FactoryException
	{
		if(task.getNType() == Task.TYPE_MEMCACHE_PUT)
		{
			return new ImplMemCachePut(task);
		}
		else if(task.getNType() == Task.TYPE_MEMCACHE_REMOVE)
		{
			return new ImplMemCacheRemove(task);
		}
		else if(task.getNType() == Task.TYPE_HANDTRANS)
		{
			return new ImplDBSQLTrans(task);
		}
		else throw new FactoryException();
	}
}
