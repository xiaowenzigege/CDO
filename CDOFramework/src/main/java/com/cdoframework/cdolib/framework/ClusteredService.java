/**
 * www.cdoforum.com 2007版权所有

 * $Header: /CVSData/Frank/CVSROOT/CDOForum/CDOFrameBase/Source/com/cdoframework/cdolib/framework/ClusteredObject.java,v 1.1 2008/04/12 13:44:36 Frank Exp $
 *
 * $Log: ClusteredObject.java,v $
 * Revision 1.1  2008/04/12 13:44:36  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2008/03/05 01:34:06  Frank
 * *** empty log message ***
 *
 * Revision 1.2  2007/09/30 00:10:18  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/08/31 07:08:26  Frank
 * *** empty log message ***
 *
 * Revision 1.3  2007/08/22 06:57:18  Frank
 * *** empty log message ***
 *
 * Revision 1.2  2007/08/22 06:33:54  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/08/22 06:12:52  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/08/22 06:08:39  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/08/22 06:05:17  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/08/08 09:32:16  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2006/08/04 14:08:02  Frank
 * Init Project
 *
 * Revision 1.1  2006/07/10 22:38:32  Frank
 * Init
 *
 * Revision 1.1  2006/05/16 09:34:18  Frank
 * *** empty log message ***
 *
 *
 */

package com.cdoframework.cdolib.framework;

import com.cdoframework.cdolib.base.IActiveObject;

/**
 * @author Frank
 */
public class ClusteredService
{

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------

	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------

	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------
	protected String strObjectId;
	public String getObjectId(){return strObjectId;}
	public void setObjectId(String strObjectId){this.strObjectId=strObjectId;}

	public IActiveObject objObject;
	public IActiveObject getModuleObject(){return objObject;}
	public void setObject(IActiveObject objObject){this.objObject=objObject;}
	
	public int nState;
	public int getState(){return nState;}
	public void setState(int nState){this.nState=nState;}
	
	public long lCheckTime;
	public long getCheckTime(){return lCheckTime;}
	public void setCheckTime(long lCheckTime){this.lCheckTime=lCheckTime;}

	public boolean bLocked;
	public boolean isLocked(){return bLocked;}
	public void setLocked(boolean bLocked){this.bLocked=bLocked;}

	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------

	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------

	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public ClusteredService()
	{
		super();

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		strObjectId		="";
		objObject		=null;
		nState			=0;
		lCheckTime		=0;
		bLocked			=false;
	}

}
