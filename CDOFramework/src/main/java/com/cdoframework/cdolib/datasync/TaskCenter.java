package com.cdoframework.cdolib.datasync;

import org.apache.log4j.Logger;
/**
 * Task任务调度中心
 * @author dylan_xu
 */
public class TaskCenter<T>
{	
	private static Logger logger = Logger.getLogger(TaskCenter.class);
	//内部类,所有内部类在此声明---------------------------------------------------
	//静态对象,所有static在此声明并初始化------------------------------------------
	private static volatile TaskCenter instance;
	private TaskCenter(){};
	private static ThreadPoolManager retryThreadPool = null;
	public static TaskCenter getInstance()
	{
		if(instance == null){
			synchronized(TaskCenter.class)
			{
				if(instance == null){
					instance = new TaskCenter();
					retryThreadPool = ThreadPoolManager.getRetryInstance();
				}
			}
		}
		return instance;
	}
	
	/**
	 * 提交Task到任务中心
	 * @param task
	 */
	public TaskFuture submit(Task<T> task)
	{
		TaskFuture future = new TaskFuture();
		future.setTask(task);
		try
		{
			future.setWorkerFuture(retryThreadPool.submit(new TaskFailThread(task)));
			
			task.setTaskFuture(future);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return future;
	}

}
