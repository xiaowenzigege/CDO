package com.cdoframework.cdolib.datasync;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import com.cdoframework.cdolib.parameter.GlobalConfiguration;

public class TaskFailThread<T> implements Callable<T>
{
	private static final Logger logger = Logger.getLogger(TaskFailThread.class);
	private Task<T> task;
	
	public TaskFailThread(){};
	public TaskFailThread(Task<T> task)
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
				result = task.repair();
			} catch (Exception e) {
				logger.warn("数据同步操作异常:"+e.getMessage());
				if(result == null || !(result instanceof Boolean) || !(Boolean)result){
					if(task.needExecute()){
						ThreadPoolManager retryInstance = ThreadPoolManager.getRetryInstance();
						try {
							Thread.sleep(GlobalConfiguration.getInstance().getMemcacheFailedRetryDefaultSleepTime());
						} catch (InterruptedException e1) {
						}
						task.getTaskFuture().setWorkerFuture(retryInstance.submit(this));
					}
				}
			}
			if(logger.isDebugEnabled()){
				logger.debug("线程池中当前的任务 TaskQueue数:"+ThreadPoolManager.getRetryInstance().getTaskQueue().size());
				logger.debug("任务：" + this.getClass().getName() + " 当前运行次数：" + task.nRepairTimes + " 线程最大重复次数：" + task.getMaxExecuteTimes());
			}
		}
		
		return result;
	}

}
