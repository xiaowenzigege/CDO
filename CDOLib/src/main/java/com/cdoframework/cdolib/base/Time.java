/**
 * www.cdoforum.com 2007版权所有
 *
 * $Header: /CVSData/Frank/CVSROOT/CDOForum/CDOLib/Source/com/cdoframework/cdolib/base/Time.java,v 1.1 2008/03/05 01:34:44 Frank Exp $
 *
 * $Log: Time.java,v $
 * Revision 1.1  2008/03/05 01:34:44  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/12/15 09:35:38  Frank
 * *** empty log message ***
 *
 * Revision 1.2  2007/09/30 00:11:39  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/07/24 13:40:40  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/07/24 13:36:57  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2006/09/02 14:43:40  Frank
 * �޸�DataTrans,�޸�Complex
 *
 *
 */

package com.cdoframework.cdolib.base;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * @author Frank
 */
public class Time
{

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------

	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------
	protected Timestamp	m_dateTime;

	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------
	public Timestamp getTimestamp()
	{
		return m_dateTime;
	}

	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------

	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------
	public String toString()
	{
		SimpleDateFormat format	=new SimpleDateFormat("HH:mm:ss");
		return format.format(m_dateTime);
	}

	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public Time()
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		m_dateTime	=new Timestamp(System.currentTimeMillis());
	}

	public Time(int nHour,int nMinute,int nSecond)
	{
		GregorianCalendar g=new GregorianCalendar();
		g.set(0, 0, 0,nHour,nMinute,nSecond);
		m_dateTime	=new Timestamp(g.getTimeInMillis());
	}

	public Time(String strTime)
	{
		try
		{
			SimpleDateFormat format	=new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");	
			java.util.Date date	=format.parse("1900-01-01 "+strTime);
			m_dateTime	=new Timestamp(date.getTime());
		}
		catch(Exception e)
		{
		}
	}

}
