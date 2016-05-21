/**
 * www.woyo.com 2010版权所有
 *
 * $Header: $
 *
 * $Log: $
 *
 */

package com.cdoframework.cdolib.framework;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Administrator
 * 
 * 该类是一个并行任务的处理类，能同时处理多个任务。
 * 
 * 每个任务通过addTask方法提交，添加进来的任务异步执行
 */
public abstract class ParallelTaskProcessor extends ThreadGroup
{

	//内部类,所有内部类在此声明----------------------------------------------------------------------------------
	class Task
	{
		HashMap hmInput;
		ArrayList<HashMap> alOutputSet;
	}

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------

	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------
	private ArrayList<Task> alTask;

	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------

	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------

	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------
	
	/**
	 * @param hmRequest: 任务请求数据
	 * @param alResponse: 任务执行的结果以HashMap形式放到alResponse参数中，并且会notify alResponse的等待者
	 */
	public void addTask(HashMap hmInput,ArrayList<HashMap> alOutputSet)
	{
		Task task=new Task();
		task.hmInput=hmInput;
		task.alOutputSet=alOutputSet;
		
		synchronized(this.alTask)
		{
			this.alTask.add(task);
			this.alTask.notify();
		}
	}

	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see com.cdoframework.cdolib.framework.ThreadGroup#threadProc()
	 */
	@Override
	protected void threadProc()
	{
		while(this.bCanExit==false)
		{
			Task task=null;
			
			synchronized(this.alTask)
			{
				int nSize=this.alTask.size();
				if(nSize==0)
				{
					try
					{
						this.alTask.wait(1000);
					}
					catch(Exception e)
					{
					}
					continue;
				}
				task=this.alTask.remove(0);
			}

			HashMap hmResponse=null;
			try
			{
				hmResponse=this.handleTask(task.hmInput);
			}
			catch(Exception e)
			{
			}
			synchronized(task.alOutputSet)
			{
				task.alOutputSet.add(hmResponse);
				task.alOutputSet.notify();
			}
		}
	}

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------
	abstract protected HashMap handleTask(HashMap hmInput);


	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public ParallelTaskProcessor()
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		this.alTask=new ArrayList<Task>();
	}
}
