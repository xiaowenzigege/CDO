/**
 * 
 */
package com.cdoframework.cdolib.framework;

import java.util.ArrayList;
import java.util.List;

import com.cdoframework.cdolib.base.Return;

/**
 * @author Administrator
 * @deprecated
 * 此类仅为特殊目的设计,并未考虑到所有情况,弃用
 *
 */
public abstract class ThreadPoolExt extends ThreadPool implements Runnable
{
	
	/**
	 * 
	 */
	public ThreadPoolExt()
	{
		super();
	}
	
	private int nPageIndexFrom = 1;
	public void setPageIndexFrom(int nPageIndexFrom)
	{
		this.nPageIndexFrom = nPageIndexFrom;
	}
	private int nPageSize = 40;
	public void setPageSize(int nPageSize)
	{
		if(nPageSize>0)
		{
			this.nPageSize = nPageSize;
		}		
	}
	private boolean bAllwaysFirstPage = true;//总是取第一页
	public void setAllwaysFirstPage( boolean bAllwaysFirstPage)
	{
		this.bAllwaysFirstPage = bAllwaysFirstPage;
	}
	
	
	@Override
	public Return start()
	{
		super.start();
		return Return.OK;
	}

	public void run()
	{	
		start();
		int nPageIndex = nPageIndexFrom;
		while(!super.bCanExit)
		{
			if(logger.isDebugEnabled()){logger.debug("======== getTaskList  pageIndex = "+nPageIndex);}
			List<Task> lsTask = new ArrayList<Task>(nPageSize);
			int nCount = this.getTaskList(nPageIndex,nPageSize,lsTask);
			if(nCount == 0)
			{
				//执行完毕
				if(logger.isDebugEnabled()){logger.debug("取任务完成=======================:商品总数:"+nCount);}
				break;
			}
			int nSzie = lsTask.size();
			if(logger.isDebugEnabled()){logger.debug("======== getTaskList  pageIndex = "+nPageIndex + "nSzie = "+nSzie);}
			if(nSzie>0)
			{
				for(Task task:lsTask)
				{					
					int ret = super.addTask(task);
					if(ret!=0)
					{//没有空闲线程,等待重试
						while(true)
						{
							sleep(100);
							ret = super.addTask(task);
							if(ret==0)
							{//添加任务成功
								break;
							}
						}
					}					
				}
			}
			if(nPageIndex*nPageSize >nCount)
			{
				//执行完毕
				if(logger.isDebugEnabled()){logger.debug("取任务完成=======================:商品总数:"+nCount);}
				break;
			}	
			if(bAllwaysFirstPage == false)
			{
				nPageIndex++;
			}
			sleep(10000);
		}
	}
	
	/**
	 * 
	 * @param nPageIndex
	 * @param nPageSize
	 * @param lsTask 用于反回任务列表
	 * @return 总记录数
	 */
	public abstract int getTaskList(int nPageIndex,int nPageSize,List<Task> lsTask);
}
