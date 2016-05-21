package com.cdoframework.cdolib.datasync;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.cdoframework.cdolib.parameter.GlobalConfiguration;

public class ThreadPoolManager<T>
{
	private static Logger logger = Logger.getLogger(ThreadPoolManager.class);
	//Thread pool used to hold running threads----------------------------------
	private ThreadPoolExecutor taskPool = null;
	//Blocking queue used to hold waiting threads-------------------------------
	private BlockingQueue taskQueue = null;
	//线程池维护线程的最少数量-----------------------------------------------------
	private int corePoolSize = 2;
	//线程池维护线程的最大数量-----------------------------------------------------
	private int maxPoolSize = 4;
	private int queueSize = 5000;
	private long waitTime = 60;
	
	private static ThreadPoolManager mainInstance =
		new ThreadPoolManager(GlobalConfiguration.getInstance().getMainThreadPoolSize());
	private static ThreadPoolManager retryInstance =
		new ThreadPoolManager(GlobalConfiguration.getInstance().getRepairThreadPoolSize());
	
	private ThreadPoolManager(int queueSize){
		this.queueSize = queueSize;
		init();
	}
	
	private ThreadPoolManager(){
		init();
	};
	
	public static ThreadPoolManager getMainInstance()
	{
		return mainInstance;
	}
	
	public static ThreadPoolManager getRetryInstance(){
		return retryInstance;
	}
	
	/***************************************************************************
	 * 创建线程池，最小线程数为2，最大线程数为4，线程池维护线程的空闲时间为60秒
	 * 使用队列深度为4的有界队列，如果执行程序尚未关闭，则位于工作队列头部的任务将被删除
	 * 然后重试执行程序（如果再次失败，则重复此过程），里面已经根据队列深度对任务加载进行了控制。
	 * ThreadPoolExecutor.CallerRunsPolicy
	 * 用于被拒绝任务的处理程序，它直接在 execute 方法的调用线程中运行被拒绝的任务；
	 * 如果执行程序已关闭，则会丢弃该任务。
	 ***************************************************************************/
	public void init()
	{
		taskQueue = new ArrayBlockingQueue(queueSize);
		taskPool = new ThreadPoolExecutor(corePoolSize, maxPoolSize, waitTime, 
		TimeUnit.SECONDS, taskQueue, new ThreadPoolExecutor.CallerRunsPolicy());

		logger.info("Initialize thread pool succeed. ThreadPool: corePoolSize = " 
		+ corePoolSize + ", queueSize = " + queueSize 
		+ ", maxPoolSize = " + maxPoolSize);
	}
	/**
	 * 在将来某个时间执行给定任务
	 * @param command
	 */
	public Future submit(Callable<T> command)
	{
		//this.taskPool.execute(command);
		return taskPool.submit(command);
	}
	/**
	 * 从执行程序的内部队列中移除此任务（如果存在），从而如果尚未开始，则其不再运行。
	 * @param command
	 */
	public void remove(Runnable command)
	{
		this.taskPool.remove(command);
	}
	public ThreadPoolExecutor getTaskPool() {
		return taskPool;
	}
	public int getCorePoolSize() {
		return this.taskPool.getCorePoolSize();
	}
	public void setCorePoolSize(int corePoolSize) {
		this.taskPool.setCorePoolSize(corePoolSize);
	}
	public int getMaxPoolSize() {
		return this.taskPool.getMaximumPoolSize();
	}
	public void setMaxPoolSize(int maxPoolSize) {
		this.taskPool.setMaximumPoolSize(maxPoolSize);
	}
	public int getQueueSize() {
		return this.taskPool.getQueue().size();
	}
	public long getWaitTime() {
		return this.taskPool.getKeepAliveTime(TimeUnit.SECONDS);
	}
	public void setWaitTime(long waitTime) {
		this.taskPool.setKeepAliveTime(waitTime, TimeUnit.SECONDS);
	}
	public BlockingQueue getTaskQueue() {
		return taskQueue;
	}
}
