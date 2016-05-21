package com.cdoframework.cdolib.framework;
import java.util.ArrayList;
import java.util.LinkedList;

import org.apache.log4j.Logger;

import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.base.ThreadExt;


public abstract class ThreadPool extends ThreadExt implements ITaskExecutor
{
	static Logger logger = Logger.getLogger(ThreadPool.class);

	class WorkThread extends ThreadExt 
	{
		protected ITaskExecutor 	m_threadPool;
		public void setThreadPoolMethod(ITaskExecutor threadPoolMethod)
		{
			m_threadPool=threadPoolMethod;
		}

		private LinkedList<Task> llTaskQueue = new LinkedList<Task>();

	    public WorkThread()
	    {
			m_threadPool = null;
	    }
	    
	    public int addTask(Task task)
	    {
			synchronized(llTaskQueue)
			{
				int nSize=llTaskQueue.size();
				if(nSize>0)
				{
					return -1;
				}
				llTaskQueue.add(task);
				llTaskQueue.notify();
			}
			
			return 0;
	    }

	    @Override
		protected void threadProc()
		{
			bCanExit	=false;

			while(true)
			{
				Task task=null;
				
				synchronized(llTaskQueue)
				{
					if(llTaskQueue.size()>0)
					{//有任务，获取任务
						task=llTaskQueue.removeFirst();
					}
					else
					{//没有任务
						if(this.bCanExit==true)
						{//可以退出
							break;
						}
						//不可以退出
						//等待任务
						try
						{
							llTaskQueue.wait(1000);
						}
						catch(Exception e)
						{
							logger.error("threadProc:"+e.getMessage(),e);
						}
						if(llTaskQueue.size()>0)
						{//等到任务
							task=llTaskQueue.removeFirst();
						}
						else
						{//没有等到任务
							continue;
						}
					}
				}

				//处理任务
				try
				{
					m_threadPool.handleTask(task);
				}
				catch(Exception e)
				{
				}
			}
		}
	}
	
	protected ArrayList<WorkThread> lsThreadPool;

	protected String strPoolName;
	public String getPoolName()
	{
		return strPoolName;
	}
	public void	setPoolName(String strPoolName)
	{
		this.strPoolName=strPoolName;
	}

	public int getThreadCount()
	{
		synchronized(this.lsThreadPool)
		{
			return lsThreadPool.size();
		}
	}

	protected int m_nMinThreadCount;
	public void setMinThreadCount(int nMinThreadCount)
	{
		this.m_nMinThreadCount=nMinThreadCount;
	}

	protected int m_nMaxThreadCount;
	public void setMaxThreadCount(int nMaxThreadCount)
	{
		this.m_nMaxThreadCount=nMaxThreadCount;
	}

	protected int m_nMaxFreeThreadCount;
	public void setMaxFreeThreadCount(int nMaxFreeThreadCount)
	{
		this.m_nMaxFreeThreadCount=nMaxFreeThreadCount;
	}

	//构造函数
    public ThreadPool()
    {
		strPoolName				="";
		lsThreadPool			=new ArrayList<WorkThread>();
		alTask = new ArrayList<Task>(50);
		m_nMinThreadCount		=1;
		m_nMaxThreadCount		=10;
		m_nMaxFreeThreadCount	=3;
		MaxWaitEventCount = 1000;
    }

    
	@Override
	public Return start()
	{
		//启动时候，默认增加空闲线程
		for(int i=0;i<this.m_nMinThreadCount;i++){
			WorkThread workThread = new WorkThread();
			this.lsThreadPool.add(workThread);
			workThread.setThreadPoolMethod(this);
			workThread.start();
		}
		
		return super.start();
	}

	@Override
	public void stop()
	{
		super.stop();
		synchronized(lsThreadPool)
		{
			int nSize=lsThreadPool.size();
			for(int i=0;i<nSize;i++)
			{
				WorkThread workThread=lsThreadPool.get(i);
				workThread.stop();
			}
		}
		synchronized(alTask)
		{
			alTask.clear();
		}
		alTask = null;
	}
	
	private ArrayList<Task> alTask;//任务列表
	private int MaxWaitEventCount;//最大任务数
	public void setMaxWaitEventCount(int maxWaitEventCount)
	{
		MaxWaitEventCount = maxWaitEventCount;
	}
	
	/**
	 * 加入任务
	 * @author skyf_chen
	 * @param task
	 * @return 0成功
	 */
	public int addTask(Task task)
	{
		synchronized(alTask)
		{
			if(alTask.size()<=MaxWaitEventCount)
			{
				alTask.add(task);
				alTask.notify();
				return 0;
			}			
		}		
		return -1;
	}
	
	/**
	 * 处理任务
	 * @author skyf_chen
	 */
	@Override
	protected void threadProc()
	{
		Task task = null;
		while(!this.bCanExit)
		{
			task = null;
			synchronized(alTask)
			{
				if(alTask.size()==0)
				{
					try
					{
						alTask.wait(1000);
					}
					catch(InterruptedException e)
					{
						logger.error(e.getMessage(),e);
					}
					
				}
				if(alTask.size()>0)
				{
					task = alTask.remove(0);
				}
				else
				{
					continue;
				}
			}

			synchronized(lsThreadPool)
			{
				//如果这个线程池中对应的线程对象
				if(lsThreadPool.size()>0)
				{
					for(int i=0;i<lsThreadPool.size();i++)
					{
						WorkThread workThread=lsThreadPool.get(i);
						int ret = workThread.addTask(task);
						if(ret==0) {
							task = null;
							break;
						}
					}

					if(task == null)
					{
						continue;
					}
					//如果没找到可用线程
					if(lsThreadPool.size()<this.m_nMaxThreadCount) {
						WorkThread tempWorkThread = new WorkThread();
						tempWorkThread.setThreadPoolMethod(this);
						tempWorkThread.start();
						lsThreadPool.add(tempWorkThread);
						tempWorkThread.addTask(task);
					} else {
						synchronized(alTask)
						{
							alTask.add(0,task);
							alTask.notify();
						}
					}
					
				}
			}
		}
	}
	
	
}