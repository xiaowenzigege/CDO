/**
 * www.woyo.com 2010版权所有
 *
 * $Header: $
 *
 * $Log: $
 *
 */

package com.cdoframework.cdolib.base;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Administrator
 */
public class CycleList<T>
{

	//内部类,所有内部类在此声明----------------------------------------------------------------------------------

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------

	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------
	private ArrayList<T> alT;
	private ReentrantReadWriteLock lockList;
	private int nIndex;
	private ReentrantReadWriteLock lockIndex;

	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------

	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------

	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------
	public void add(T t)
	{
		lockList.writeLock().lock();
		try
		{
			this.alT.add(t);
		}
		finally
		{
			lockList.writeLock().unlock();
		}
	}
	
	public T get()
	{
		lockList.readLock().lock();
		try
		{
			if(this.alT.size()==0)
			{
				return null;
			}
			int nReadIndex=0;
			lockIndex.writeLock().lock();
			nReadIndex=this.nIndex;
			this.nIndex++;
			if(this.nIndex>=this.alT.size())
			{
				this.nIndex=0;
			}
			lockIndex.writeLock().unlock();
			return this.alT.get(nReadIndex);
		}
		finally
		{
			lockList.readLock().unlock();
		}
	}

	public void clear()
	{
		lockList.writeLock().lock();
		try
		{
			this.alT.clear();
		}
		finally
		{
			lockList.writeLock().unlock();
		}
	}
	
	public int size()
	{
		lockList.readLock().lock();
		try
		{
			return this.alT.size();
		}
		finally
		{
			lockList.readLock().unlock();
		}
	}

	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public CycleList()
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		this.alT		=new ArrayList<T>();
		this.nIndex		=0;
		this.lockList	=new ReentrantReadWriteLock();
		this.lockIndex	=new ReentrantReadWriteLock();
	}

}
