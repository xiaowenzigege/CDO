package com.cdo.business.threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.cdo.business.BusinessService;
import com.cdo.util.constants.Constants;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;

import io.netty.util.internal.SystemPropertyUtil;

public class ThreadPoolFactory {
	private int corePoolSize=Math.max(4,SystemPropertyUtil.getInt(Constants.Business.CoreSize,Runtime.getRuntime().availableProcessors()));
	private int maxPoolSize=Math.max(4,SystemPropertyUtil.getInt(Constants.Business.MaxSize,Runtime.getRuntime().availableProcessors()));
	private int queueSize=Math.max(10,SystemPropertyUtil.getInt(Constants.Business.QueueSize,10));
	private int threshold=Math.min((int)(queueSize*0.8),SystemPropertyUtil.getInt(Constants.Business.Threshold,queueSize));	
	//空闲线程存活的时间
	private int keepAliveTime=Math.max(60,SystemPropertyUtil.getInt(Constants.Business.IDLE_KeepAliveTime,60));
	
	private BusinessService businessService=BusinessService.getInstance();	
	
	private static Logger logger=Logger.getLogger(ThreadPoolFactory.class);
	
	private static final ThreadPoolFactory factory= new ThreadPoolFactory();
	
	private ThreadPoolExecutor executor=null;
	
	private ThreadPoolFactory(){	
		if(maxPoolSize<corePoolSize){
			logger.warn("maxPoolSize<corePoolSize,maxPoolSize["+maxPoolSize+"],corePoolSize["+corePoolSize+"],reset maxPoolSize=coreSize="+corePoolSize);
			maxPoolSize=corePoolSize;
		}		
		if(maxPoolSize==corePoolSize){
			keepAliveTime=0;
			logger.warn("maxPoolSize=corePoolSize,maxPoolSize["+maxPoolSize+"],corePoolSize["+corePoolSize+"],reset keepAliveTimee="+keepAliveTime);
		}
		BlockingQueue<Runnable> workQueue=new LinkedBlockingQueue<Runnable>(queueSize);
		this.executor=new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, 
				TimeUnit.SECONDS, workQueue, new ThreadPoolExecutor.CallerRunsPolicy());
		
		if(logger.isInfoEnabled())
			logger.info("Initialize thread pool succeed. ThreadPool: corePoolSize = " 
					+ executor.getCorePoolSize()+ ",maxPoolSize = " + executor.getMaximumPoolSize()
					+" idle keepAliveTime="+executor.getKeepAliveTime(TimeUnit.SECONDS)+"s,thresholdSize="+threshold);		
	}
	
	public static ThreadPoolFactory getInstatnce(){
		return factory;
	}	
	
	public void submit(CDO cdoRequest){
		Task task=new Task(cdoRequest);
		addTask(task);
	}
	
	private void addTask(Task task){
		try{			
			executor.submit(task);
		}catch(Exception ex){
			logger.warn(" task fail,after 2 seconds retry join task into work queue");
			try{Thread.sleep(2000);}catch(Exception e){}
			addTask(task);
		}
	}
	/**
	 * 允许增加
	 * @return
	 */
	public boolean isAdd(){
		return threshold>getRemainCount();
	}
	/**
	 * RemainCount=TaskCount-CompletedTaskCount=ActiveCount+getQueue().size()
	 * @return
	 */
	public long getRemainCount(){
		return executor.getTaskCount()-executor.getCompletedTaskCount();
	}
	private class Task implements Runnable{
		private CDO cdoRequest;
		public  Task(CDO cdoRequest){
			this.cdoRequest=cdoRequest;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			CDO cdoRes=new CDO();
			try{	
				long  taskCount=executor.getTaskCount();
				long  taskComplete=executor.getCompletedTaskCount();
				long  remainCount=taskCount-taskComplete;
				
				if(logger.isInfoEnabled()){
					logger.info("exeute task,current taskCount="+executor.getTaskCount()+
							" CompletedTaskCount="+executor.getCompletedTaskCount()+",remainCount="+
							remainCount+",activeCount="+executor.getActiveCount()+","+" taskQueueSize="+executor.getQueue().size());						
				}				
				Return ret=businessService.handleTrans(cdoRequest, cdoRes);				
				if(ret.getCode()!=Return.OK.getCode())
					logger.error("异步处理客户端数据 发生异常:"+ret.getText()+",cdoRequest="+cdoRequest.toXML(), ret.getThrowable());
			}catch(Throwable ex){
				logger.error("异步调用错误:"+ex.getMessage(), ex);
			}finally{
				cdoRequest.deepRelease();
				cdoRes.deepRelease();									
			 }	
		}}
	
}
