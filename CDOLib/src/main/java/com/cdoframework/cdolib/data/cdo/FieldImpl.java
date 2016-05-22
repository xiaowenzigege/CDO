/**
 * www.cdoforum.com 2007版权所有
 *
 * $Header: /CVSData/Frank/CVSROOT/CDOForum/CDOLib/Source/com/cdoframework/cdolib/data/cdo/Field.java,v 1.3 2008/03/22 13:32:54 Frank Exp $
 *
 * $Log: Field.java,v $
 * Revision 1.3  2008/03/22 13:32:54  Frank
 * *** empty log message ***
 *
 * Revision 1.2  2008/03/12 10:30:57  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2008/03/07 11:20:20  Frank
 * *** empty log message ***
 *
 * Revision 1.3  2007/11/03 02:25:40  Frank
 * *** empty log message ***
 *
 * Revision 1.2  2007/10/11 13:55:05  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/10/11 01:10:56  Frank
 * *** empty log message ***
 *
 *
 */

package com.cdoframework.cdolib.data.cdo;


/**
 * @author Frank
 */
public class FieldImpl implements Field
{


	//内部类,所有内部类在此声明----------------------------------------------------------------------------------

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------
	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1324693182949266208L;
	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------
	private int nType;
	public void setType(int nType)
	{
		this.nType=nType;
	}
	public int getType()
	{
		return nType;
	}

	private String strName;
	public void setName(String strName)
	{
		this.strName=strName;
	}
	public String getName()
	{
		return strName;
	}

	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------

	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------

	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public FieldImpl()
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		nType=NONE_TYPE;
		strName="";
	}

	public FieldImpl(String strFieldName)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		nType=NONE_TYPE;
		strName=strFieldName;
	}

	// 添加转化成JSON的格式
	public  String toJSON()
	{
		return null;
	}

	public  String toJSONString()
	{
		return null;
	}	
	public  Object getObjectValue()
	{
		return null;
	}
	@Override
	public void toXML(StringBuilder strbXML) {

		
	}
	@Override
	public void toXMLWithIndent(int nIndentSize, StringBuilder strbXML) {		
		
	}
	@Override
	public void toXMLLog(StringBuilder strbXML) {
		toXML(strbXML);		
	}
	@Override
	public String toString() {
		return toJSONString();		
	}
}
