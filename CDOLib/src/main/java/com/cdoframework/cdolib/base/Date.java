/**
 * www.cdoforum.com 2007版权所有
 *
 * $Header: /CVSData/Frank/CVSROOT/CDOForum/CDOLib/Source/com/cdoframework/cdolib/base/Date.java,v 1.1 2008/03/05 01:34:42 Frank Exp $
 *
 * $Log: Date.java,v $
 * Revision 1.1  2008/03/05 01:34:42  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/12/15 09:35:35  Frank
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
 * Revision 1.2  2007/06/25 23:07:33  zhangjuan
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
 * modify by Kenel Liu
 */
public class Date{

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
		SimpleDateFormat format	=new SimpleDateFormat("yyyy-MM-dd");
		return format.format(m_dateTime);
	}
	
	/**
	 * 按要求的格式显示日期 
	 * @param DTformat 日期格式 example:MMdd（月日）
	 * @return
	 */
	public String toString(String DTformat)
	{
		SimpleDateFormat format	=new SimpleDateFormat(DTformat);
		return format.format(m_dateTime);
	}

	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public Date()
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		m_dateTime	=new Timestamp(System.currentTimeMillis());
	}

	/**
	 * 获取与当前日期相差若干天的日期 zhangj add
	 * @param betweenDay 相差天数，负数表示前几天，正数表示后几天。 example:betweenDay=-1(取前一天的日期)
	 * @return
	 */
	public Date(int betweenDay)
	{
		GregorianCalendar g=new GregorianCalendar();
		g.setTime(new java.util.Date());
		g.add(java.util.Calendar.DATE,betweenDay);
		m_dateTime	=new Timestamp(g.getTimeInMillis());
	}
	
	/**
	 * 与某一天相差若干天的日期
	 * @param strDate 日期
	 * @param betweenDay 相差天数，负数表示前几天，正数表示后几天。 example:betweenDay=-1(取前一天的日期)
	 */
	public Date(String strDate,int betweenDay)
	{
		try
		{
			GregorianCalendar g=new GregorianCalendar();
			SimpleDateFormat format	=new SimpleDateFormat ("yyyy-MM-dd");			
			java.util.Date date	=format.parse(strDate);
			g.setTime(date);
			g.add(java.util.Calendar.DATE,betweenDay);
			m_dateTime	=new Timestamp(g.getTimeInMillis());
		}
		catch(Exception e)
		{
		}	
	}
	
	/**
	 * 
	 * @param nYear
	 * @param nMonth  1-12
	 * @param nDate  1-31
	 */
	public Date(int nYear,int nMonth,int nDate)
	{
		GregorianCalendar g=new GregorianCalendar();
		g.set(nYear, (nMonth-1), nDate);		
		m_dateTime	=new Timestamp(g.getTimeInMillis());
	}

	public Date(long timeInMillis)
	{	
		m_dateTime	=new Timestamp(timeInMillis);
		
	}
	
	public Date(String strDate)
	{
		try
		{
			SimpleDateFormat format	=new SimpleDateFormat ("yyyy-MM-dd");	
			java.util.Date date	=format.parse(strDate);
			m_dateTime	=new Timestamp(date.getTime());
		}
		catch(Exception e)
		{
		}
	}
}
