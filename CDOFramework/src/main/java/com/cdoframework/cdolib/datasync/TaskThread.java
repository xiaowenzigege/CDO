package com.cdoframework.cdolib.datasync;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import com.cdoframework.cdolib.parameter.GlobalConfiguration;

public class TaskThread<T> implements Callable<T>
{
	private static final Logger logger = Logger.getLogger(TaskThread.class);
	private Task<T> task;
	public TaskThread(){};
	public TaskThread(Task<T> task)
	{
		this.task = task;
	}
	
	/****************************************************************************
	 * 
	 * taskObject.execute() 返回值为false时，判断该任务已被执行的次数，还在规定的策略之内则把任务加入到
	 * slaver ThreadPoolManager线程池中
	 * 
	 ****************************************************************************/
	public T call()
	{
		T result = null;
		if(task.needExecute())
		{
			try {
				result = task.execute();
			} catch (Exception e) {
				logger.warn("数据同步操作异常:"+e.getMessage());
				if(result == null || !(result instanceof Boolean) || !(Boolean)result){
					if(task.needExecute()){
						logger.warn("数据同步操作失败，准备重试!"+e.getMessage());
						ThreadPoolManager retryInstance = ThreadPoolManager.getRetryInstance();
						task.getTaskFuture().setWorkerFuture(retryInstance.submit(new TaskFailThread(task)));	
					}
				}
			}
			
			if(logger.isDebugEnabled()){
				logger.debug("主线程池中当前的任务 TaskQueue数:"+ThreadPoolManager.getMainInstance().getTaskQueue().size());
				logger.debug("从线程池中当前的任务 TaskQueue数:"+ThreadPoolManager.getRetryInstance().getTaskQueue().size());
				logger.debug("任务：" + this.getClass().getName() + " 当前运行次数：" + task.nRepairTimes + " 线程最大重复次数：" + task.getMaxExecuteTimes());
			}
		}
		
		return result;
	}

}
