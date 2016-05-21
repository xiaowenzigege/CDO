/**
*$Header: /CVSData/Frank/CVSROOT/CDOForum/CDOLib/Source/com/cdoframework/cdolib/base/PropertiesExt.java,v 1.1 2008/03/05 01:34:42 Frank Exp $
*掌信彩通信息科技(中国)有限公司版权所有
*$Log: PropertiesExt.java,v $
*Revision 1.1  2008/03/05 01:34:42  Frank
**** empty log message ***
*
*Revision 1.1  2007/12/27 12:28:05  Frank
**** empty log message ***
*
*Revision 1.1  2007/12/15 09:35:38  Frank
**** empty log message ***
*
*Revision 1.3  2007/12/15 09:07:13  Frank
**** empty log message ***
*
*Revision 1.2  2007/08/30 14:14:23  Frank
**** empty log message ***
*
*Revision 1.1  2007/07/24 13:40:40  Frank
**** empty log message ***
*
*Revision 1.1  2007/07/24 13:36:57  Frank
**** empty log message ***
*
*Revision 1.1  2006/11/15 14:07:21  linshihong
**** empty log message ***
*
*/
package com.cdoframework.cdolib.base;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author linshihong
 * 功能概述:
 *
 */
public class PropertiesExt
{

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------

	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------
	protected Properties properties;
	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------

	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------

	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------
	/**
	 * 从资源加载所有参数
	 */
	public Return loadConfigFromResource(String strResource)
	{
		return loadConfigFromResource(strResource, true);
	}
	
	public Return loadConfigFromResource(String strResource,boolean isClassPath)
	{
		InputStream stream=null;		
		try
		{
			if(isClassPath)
				stream=Resources.getResourceAsStream(strResource);
			else
				stream=new FileInputStream(strResource);
			properties.load(stream);			
			return Return.OK;
		}
		catch(Exception e)
		{
			return Return.valueOf(-1,e.getMessage());
		}
		finally
		{
			try
			{
				stream.close();
			}
			catch(Exception e)
			{
			}
		}
	}	
	/**
	 * 从文件加载所有参数
	 */
	public Return loadConfigFromFile(String strFile)
	{
		InputStream stream=null;
		
		try
		{
			stream=new FileInputStream(strFile);
			properties.load(stream);
			
			return Return.OK;
		}
		catch(Exception e)
		{
			return Return.valueOf(-1,e.getMessage());
		}
		finally
		{
			try
			{
				stream.close();
			}
			catch(Exception e)
			{
			}
		}
	}
	
	/**
	 * 获取指定参数，如果参数不存在，则返回""
	 * @param strName 参数名称
	 * @return 参数值
	 */
	public String getParameter(String strName)
	{
		try
		{
			String strValue=properties.getProperty(strName);
			
			if(strValue!=null)
			{
				return strValue;
			}
			else
			{
				return "";
			}
		}
		catch(Exception e)
		{
			return "";
		}
	}
	
	/**
	 * 获取指定参数，如果参数存在，则返回指定的缺省值
	 * @param strName 参数名称
	 * @return 参数值
	 */
	public String getParameter(String strName,String strDefaultValue)
	{
		try
		{
			String strValue=properties.getProperty(strName);
			
			if(strValue!=null)
			{
				return strValue;
			}
			else
			{
				return strDefaultValue;
			}
		}
		catch(Exception e)
		{
			return strDefaultValue;
		}
	}
	
	/**
	 * 获取指定参数，如果参数存在，则返回指定的缺省值
	 * @param strName 参数名称
	 * @return 参数值
	 */
	public int getParameter(String strName,int nDefaultValue)
	{
		try
		{
			String strValue=properties.getProperty(strName);
			
			if(strValue!=null)
			{
				return Integer.parseInt(strValue);
			}
			else
			{
				return nDefaultValue;
			}
		}
		catch(Exception e)
		{
			return nDefaultValue;
		}
	}
	
	public long getParameter(String strName,long lDefaultValue)
	{
		try
		{
			String strValue=properties.getProperty(strName);
			
			if(strValue!=null)
			{
				return Long.parseLong(strValue);
			}
			else
			{
				return lDefaultValue;
			}
		}
		catch(Exception e)
		{
			return lDefaultValue;
		}
	}
	
	public double getParameter(String strName,double dDefaultValue)
	{
		try
		{
			String strValue=properties.getProperty(strName);
			
			if(strValue!=null)
			{
				return Double.parseDouble(strValue);
			}
			else
			{
				return dDefaultValue;
			}
		}
		catch(Exception e)
		{
			return dDefaultValue;
		}
	}
	public boolean getParameter(String strName,boolean _b)
	{
		try
		{
			String strValue=properties.getProperty(strName);
			
			if(strValue!=null)
			{
				int i = Integer.parseInt(strValue);
				return i>0?true:false;	
			}
			else
			{
				return _b;
			}
		}
		catch(Exception e)
		{
			return _b;
		}
	}
	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public PropertiesExt()
	{
		properties = new Properties();
	}
	public PropertiesExt(Properties properties)
	{
		this.properties = properties;
	}
}
