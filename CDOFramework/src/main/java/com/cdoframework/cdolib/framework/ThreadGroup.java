package com.cdoframework.cdolib.framework;

import java.util.ArrayList;

import com.cdoframework.cdolib.base.IActiveObject;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.base.ThreadExt;


/**
 * @author Frank
 */
public abstract class ThreadGroup implements IActiveObject
{

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------

	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------
	private ArrayList	alThread;
	protected boolean	bCanExit;

	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------
	public boolean	isRunning()
	{
		synchronized(alThread)
		{
			if(alThread.size()>0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}

	private int nThreadCount;
	public void setThreadCount(int nThreadCount){this.nThreadCount=nThreadCount;}
	public int getThreadCount(){return alThread.size();}

	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------

	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------
	protected void	onThreadStopped(GroupedThread thread)
	{
		synchronized(alThread)
		{
			alThread.remove(thread);
		}
	}

	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------
	static public void sleep(int nMilliSecond)
	{
		ThreadExt.sleep(nMilliSecond);
	}

	public Return	start(int nThreadCount)
	{
		this.nThreadCount=nThreadCount;
		if(isRunning()==true)
		{
			return Return.OK;
		}

		synchronized(alThread)
		{
			for(int i=0;i<nThreadCount;i++)
			{
				GroupedThread thread=new GroupedThread();
				thread.setThreadGroup(this);
				Return ret=thread.start();
				if(ret.getCode()==0)
				{
					alThread.add(thread);
				}
			}
		}

		return Return.OK;
	}

	public Return	start()
	{
		if(isRunning()==true)
		{
			return Return.OK;
		}

		this.bCanExit=false;

		synchronized(alThread)
		{
			for(int i=0;i<nThreadCount;i++)
			{
				GroupedThread thread=new GroupedThread();
				thread.setThreadGroup(this);
				Return ret=thread.start();
				if(ret.getCode()==0)
				{
					alThread.add(thread);
				}
			}
		}

		return Return.OK;
	}

	public void		stop()
	{
		if(isRunning()==false)
		{
			return;
		}

		stopProc();

		while(true)
		{
			synchronized(alThread)
			{
				if(alThread.size()==0)
				{
					break;
				}
			}
			sleep(10);
		}
		this.bCanExit=false;
	}

	abstract protected void threadProc();

	protected void stopProc()
	{
		bCanExit	=true;
	}

	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public ThreadGroup()
	{
		super();

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		nThreadCount	=3;
		alThread		=new ArrayList();
		bCanExit		=false;
	}


	public ThreadGroup(int nThreadCount)
	{
		super();

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		this.nThreadCount	=nThreadCount;
		alThread		=new ArrayList();
		bCanExit		=false;
	}
}
