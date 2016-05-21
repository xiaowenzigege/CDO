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
public class SortedSet<K,V>
{

	//内部类,所有内部类在此声明----------------------------------------------------------------------------------
	class SortedItem implements Comparable<SortedItem>
	{
		public K k;
		public V v;
		
		public int compareTo(SortedItem si2)
		{
			SortedItem si1=this;

			if(k instanceof String)
			{//String类型
				String strK1=(String)si1.k;
				String strK2=(String)si2.k;
				return strK1.compareTo(strK2);
			}
			if(k instanceof Integer)
			{
				Integer k1=(Integer)si1.k;
				Integer k2=(Integer)si2.k;
				if(k1>k2)
				{
					return 1;
				}
				if(k1<k2)
				{
					return -1;
				}
				return 0;
			}
			if(k instanceof Long)
			{
				Long k1=(Long)si1.k;
				Long k2=(Long)si2.k;
				if(k1>k2)
				{
					return 1;
				}
				if(k1<k2)
				{
					return -1;
				}
				return 0;
			}
			throw new RuntimeException("Unsurported K type: "+k.getClass().getName());
		}
	}

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------

	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------
	private ArrayList<SortedItem> alSortedItem;
	private ReentrantReadWriteLock lockList;

	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------

	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------

	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------
	public void add(K k,V v)
	{
		lockList.writeLock().lock();
		try
		{
			SortedItem si=new SortedItem();
			si.k=k;
			si.v=v;
			
			if(this.alSortedItem.size()==0)
			{
				this.alSortedItem.add(si);
				return;
			}
			
			int nHeadIndex=0;
			int nTailIndex=alSortedItem.size()-1;
			while(true)
			{
				if(nTailIndex<=nHeadIndex)
				{
					break;
				}
				
				int nIndex=(nTailIndex+nHeadIndex)/2;
				SortedItem siIndex=this.alSortedItem.get(nIndex);
				if(si.compareTo(siIndex)<0)
				{
					nTailIndex=nIndex-1;
				}
				else if(si.compareTo(siIndex)>=0)
				{
					nHeadIndex=nIndex+1;
				}
			}
			SortedItem siIndex=this.alSortedItem.get(nHeadIndex);
			if(siIndex.compareTo(si)<=0)
			{
				nHeadIndex++;
			}
			alSortedItem.add(nHeadIndex,si);
		}
		finally
		{
			lockList.writeLock().unlock();
		}
	}
	
	public V[] toArray(boolean bAscending,V[] vsOutput)
	{
		lockList.readLock().lock();
		try
		{
			int nSize=this.alSortedItem.size();
			if(nSize==0)
			{
				return null;
			}
			
			Object[] objs=new Object[nSize];
			objs=this.alSortedItem.toArray(objs);
			if(bAscending==false)
			{//降序，做降序排列
				int nHeadIndex=0;
				int nTailIndex=nSize-1;
				while(true)
				{
					if(nHeadIndex>=nTailIndex)
					{
						break;
					}
					Object v=objs[nHeadIndex];
					objs[nHeadIndex]=objs[nTailIndex];
					objs[nTailIndex]=v;
					nHeadIndex++;
					nTailIndex--;
				}
			}
			
			for(int i=0;i<nSize;i++)
			{
				vsOutput[i]=((SortedItem)objs[i]).v;
			}
			return vsOutput;
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
			this.alSortedItem.clear();
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
			return this.alSortedItem.size();
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

	public SortedSet()
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		this.alSortedItem	=new ArrayList<SortedItem>();
		this.lockList	=new ReentrantReadWriteLock();
	}

}
