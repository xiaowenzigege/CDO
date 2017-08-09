package com.cdoframework.cdolib.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import com.cdoframework.cdolib.data.cdo.CDO;

public class ReleaseFactory {
	private static Logger logger = Logger.getLogger(ReleaseFactory.class);
	//Thread pool used to hold running threads----------------------------------
	private ThreadPoolExecutor taskPool = null;
	private BlockingQueue taskQueue = null;
	//线程池维护线程的最少数量-----------------------------------------------------
	private int corePoolSize = 1;
	//线程池维护线程的最大数量-----------------------------------------------------
	private int maxPoolSize = 4;
	private int queueSize = 5000;
	private long waitTime = 60;	
	private static ReleaseFactory instance=new ReleaseFactory();
	
	public static ReleaseFactory getInstance()
	{
		return instance;
	}
	
	private  ReleaseFactory()
	{
		taskQueue = new ArrayBlockingQueue(queueSize);
		taskPool = new ThreadPoolExecutor(corePoolSize, maxPoolSize, waitTime, 
		TimeUnit.SECONDS, taskQueue, new ThreadPoolExecutor.CallerRunsPolicy());

		logger.info("Initialize Release thread pool succeed. ThreadPool: corePoolSize = " 
		+ corePoolSize + ", queueSize = " + queueSize 
		+ ", maxPoolSize = " + maxPoolSize);
	
	}
	
	/**
	 * 提交到释放队列
	 * @param task
	 */
	public void deepRelease(CDO cdo)
	{	
		try
		{	if(cdo==null)
				return;
			taskPool.submit(new ReleaseCDO(cdo));
		}catch(Throwable e){
			logger.error(e.getMessage());
		}
		
	}
	
	private  class ReleaseCDO implements Runnable{
		private CDO cdo;
		
		public ReleaseCDO(CDO cdo){
			this.cdo=cdo;
		}
		
		public void run() {
			try{
				this.cdo.deepRelease();
			}catch(Throwable ex){				
			}
		}
	}
}
