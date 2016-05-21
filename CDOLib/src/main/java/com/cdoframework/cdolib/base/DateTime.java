/**
 * www.cdoforum.com 2007版权所有

 * $Header: /CVSData/Frank/CVSROOT/CDOForum/CDOLib/Source/com/cdoframework/cdolib/base/DateTime.java,v 1.1 2008/03/05 01:34:43 Frank Exp $
 *
 * modify by Kenel Liu
 * $Log: DateTime.java,v $
 * Revision 1.1  2008/03/05 01:34:43  Frank
 */

package com.cdoframework.cdolib.base;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/*编码规则--------------------------------------------------------------
 1. 所有成员变量的命名必须要反映变量类型和用途
 2. 类名首字母一律大写
 3. 所有命名要有含义并且名符其实，并且命名主体部分尽量用一个或数个完整的
 英文单词或常用的缩写，尽量少用缩写，所有命名长度不超过30个字符，尽量
 不超过20个字符长度
 4. 每个函数或对象的声明必须放在所属区域部分，如果找不到合适的区域，可以
 创建新的区域
 5. 尽量用通过JavaDoc注释说明每个变量、属性和函数的功能，用途、入口和出口
 参数、返回值 等，以帮助阅读者理解
 6. 函数体中要加上恰当的注释，尤其是比较关键或逻辑较复杂的地方
 7. 书写格式要美观，尽量用TAB，少用或不用空格，并且代码尽量对齐
 8. 较复杂的代码段要分段书写，每段前要加上该段的简单功能描述
 9. 所有的{，}单独占一行，必要时可以在其后加上注释
 10.每个if,then,while,...,等等的内容必须在{}内，即使只有一行
 ---------------------------------------------------------------------*/

/**********************************************************************
 在此添加该类的功能描述
 **********************************************************************/
public class DateTime implements Serializable
{

	/*静态对象--------------------------------------------------------------
	 此处声明该类的所有静态(static)对象，并根据要求创建和初始化对象,举例如下：	
	 ---------------------------------------------------------------------*/
	private static final long serialVersionUID = 3508363098152297901L;
	/*内部对象--------------------------------------------------------------
	 本类中的所有成员变量对象在此处声明为protected(推荐)或private类型，对象
	 的变量命名要以“m_”开始，下划线后的第一字母要小写，并且变量命名中要隐
	 含变量的类型，不要在此处初始化对象，格式举例如下：
	 protected string		m_strName;
	 protected DataEngine	m_dataEngine;
	 ---------------------------------------------------------------------*/
	protected Timestamp	m_dateTime;
	private Calendar cal;
	/*属性------------------------------------------------------------------
	 本类中需要对外界开放的在本类中创建的成员变量对象在此处定义为属性
	 属性一般可以通过Get和Set方法设定，必要时也可以加以简化实现，举例如下
	 //简化方式，只可用于可读写的传值对象
	 ---------------------------------------------------------------------*/
	public Timestamp getTimestamp(){
		return m_dateTime;
	}

	public long		getTime(){return m_dateTime.getTime();}
	
	
	public void		setTime(long lTime){
		m_dateTime.setTime(lTime);
		TimestampToCalendar();		
	}

	public	int		getYear(){return cal.get(Calendar.YEAR);}
	
	public void		setYear(int nYear){
		cal.set(Calendar.YEAR, nYear);
		m_dateTime.setTime(cal.getTimeInMillis());		
	}

	public	int		getMonth(){return cal.get(Calendar.MONTH)+1;}
	
	public void		setMonth(int nMonth){	
		cal.set(Calendar.MONTH, nMonth);
		m_dateTime.setTime(cal.getTimeInMillis());		
	}

	public	int		getDay(){return cal.get(Calendar.DATE);}
	
	public void		setDay(int nDay){
		cal.set(Calendar.DATE, nDay);
		m_dateTime.setTime(cal.getTimeInMillis());		
	}

	public	int		getHour(){return  cal.get(Calendar.HOUR_OF_DAY);}
	public void		setHour(int nHour){
		cal.set(Calendar.HOUR, nHour);
		m_dateTime.setTime(cal.getTimeInMillis());		
	}

	public	int		getMinute(){return cal.get(Calendar.MINUTE);}
	public void		setMinute(int nMinute){		
		cal.set(Calendar.MINUTE, nMinute);
		m_dateTime.setTime(cal.getTimeInMillis());	
		}

	public	int		getSecond(){return cal.get(Calendar.SECOND);}
	public void		setSecond(int nSecond){		
		cal.set(Calendar.SECOND, nSecond);
		m_dateTime.setTime(cal.getTimeInMillis());	
	}

	public int		getWeekDay(){return cal.get(Calendar.DAY_OF_WEEK);}
	
	public int		getYearDay()
	{
		int nDay=0;
		for(int i=1;i<getMonth();i++)
		{
			nDay+=GetMonthDayCount(i);
		}
		nDay+=getDay()-1;
		return nDay;
	}
	
	/**
	 * 取从0点算起的秒数
	 * @return
	 */
	public int getSecondFromZero()
	{
		return this.getHour()*3600+this.getMinute()*60+this.getSecond();
	}

	static public DateTime	Now()
	{
		DateTime dtNow=new DateTime();
		dtNow.setTime(System.currentTimeMillis());
		return dtNow;
	}

	
	/*引用对象--------------------------------------------------------------
	 在本类中引用的由外界创建的成员对象变量在此以函数方式传入到本类中，定义
	 格式如下：
	 protected DataEngine	m_strDataengine;
	 public void	setDataEngine(DataEngine dataEngine)
	 {
	 m_dataEngine	=dataEngine;
	 }
	 ---------------------------------------------------------------------*/
	/*内部函数--------------------------------------------------------------
	 所有只在本类内部和本类的派生类中才能调用的函数在此声明为protected类型
	 格式如下：
	 protected FDReturn	handleMO(MO mo)
	 {
	 //...
	 return FDReturn.ValueOfOK();
	 }
	 ---------------------------------------------------------------------*/

	/*方法-----------------------------------------------------------------
	 所有要公开给外界的成员函数在此声明为public类型，格式如下：
	 public FDReturn	run()
	 {
	 //...
	 return FDReturn.ValueOfOK();
	 }
	 ---------------------------------------------------------------------*/

	/**
	 * 分析指定格式的日期时间字符串
	 * @param strDateTime 日期时间字符串
	 * @param strFormat 格式定义字符串
	 * @return 输出的日期时间对象
	 * @throws Exception 格式定义字符串异常
	 */
	public static DateTime	parse(String strDateTime,String strFormat) throws Exception
	{
		SimpleDateFormat format	=new SimpleDateFormat (strFormat);

		java.util.Date date	=format.parse(strDateTime);

		return new DateTime(date.getTime());
	}

	/**
	 * 检查是否闰年
	 * @return
	 */
	public boolean IsLeapYear()
	{
		if(getYear()%100==0)
		{
			if(getYear()%400==0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		if(getYear()%4==0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * 获取月的天数
	 * @return
	 */
	public int GetMonthDayCount(){
		return GetMonthDayCount(getMonth());
	}
	
	public int GetMonthDayCount(int nMonth)
	{
		switch(nMonth)
		{
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				return 31;
			case 4:
			case 6:
			case 9:
			case 11:
				return 30;
			default:
			{
				if(IsLeapYear()==true)
				{//闰年
					return 29;
				}
				else
				{//平年
					return 28;
				}
			}
		}
	}
	
	/**
	 * 转换成Timestamp对象
	 * @return 输出的Timestamp对象
	 */
	public Timestamp toTimestamp()
	{
		return m_dateTime;
	}

	/**
	 * 转换成Date对象
	 * @return 输出的Date对象
	 */
	public Date toDate()
	{
		return new Date(m_dateTime.getTime());
	}

	/**
	 * 转换成指定格式的字符串
	 * @param strFormat 格式定义字符串
	 * @return 输出的字符串
	 * @throws Exception 格式定义字符串异常
	 */
	public String toString(String strFormat) throws Exception
	{
		if(strFormat==null || strFormat.length()==0)
		{
			strFormat="yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat format	=new SimpleDateFormat(strFormat);
		return format.format(m_dateTime);
	}

	/**
	 * 转换成指定格式的字符串
	 * @param strFormat 格式定义字符串
	 * @return 输出的字符串
	 */
	public String toString()
	{
		SimpleDateFormat format	=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		return format.format(m_dateTime);
	}

	
	/*接口实现函数-----------------------------------------------------------
	 所有从接口继承并实现的函数在此实现，格式如下：
	 public FDReturn	SendMail()
	 {
	 ...
	 }
	 ---------------------------------------------------------------------*/

	/*事件处理函数-----------------------------------------------------------
	 所有重载的基类的事件类成员函数在此实现，格式如下：
	 protected virtual void	onConnected()
	 {
	 ...
	 }
	 ---------------------------------------------------------------------*/

	/*事件定义函数-----------------------------------------------------------
	 所有在本类中声明的允许派生类重载的带有事件性质的成员函数在此实现，格式如下：
	 protected virtual void	onConnected()
	 {
	 }
	 protected abstract void onDisconnect();
	 ---------------------------------------------------------------------*/

	/*构造函数--------------------------------------------------------------
	 初始化子对象，并设置子对象之间的关系
	 引用对象初始化为null
	 值对象一般初始化为默认值，数值型为0，字符串为""
	 创建子类对象
	 ---------------------------------------------------------------------*/
	public DateTime(){
		m_dateTime	=new Timestamp(System.currentTimeMillis());
		TimestampToCalendar();
	}

	public DateTime(long lNow){
		m_dateTime	=new Timestamp(lNow);
		TimestampToCalendar();
	}

	public DateTime(Timestamp dateTime)
	{
		m_dateTime	=dateTime;
		TimestampToCalendar();		
	}
	
	public DateTime(int nYear,int nMonth,int nDate)
	{
		cal=new GregorianCalendar();
		cal.set(nYear, (nMonth-1), nDate);		
		m_dateTime	=new Timestamp(cal.getTimeInMillis());				
	}

	public DateTime(int nYear,int nMonth,int nDate,int nHour,int nMinute,int nSecond)
	{
		cal=new GregorianCalendar();
		cal.set(nYear,(nMonth-1),nDate,nHour,nMinute,nSecond);		
		m_dateTime	=new Timestamp(cal.getTimeInMillis());		
	}

	public DateTime(String strDateTime)
	{
		try
		{
			SimpleDateFormat format	=new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
			
			java.util.Date date	=format.parse(strDateTime);
			m_dateTime=new Timestamp(date.getTime());
			TimestampToCalendar();
		}
		catch(Exception e)
		{
		}
	}
	
	private void TimestampToCalendar(){
		if(m_dateTime==null)
			return;
		if(cal==null)
			cal=new GregorianCalendar();
		cal.setTimeInMillis(m_dateTime.getTime());
	}
}

