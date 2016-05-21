/**
 * www.cdoforum.com 2007版权所有
 *
 * $Header: /CVSData/Frank/CVSROOT/CDOForum/CDOFrameBase/Source/com/cdoframework/cdolib/servicebus/IServicePlugin.java,v 1.1 2008/04/12 13:44:37 Frank Exp $
 *
 * $Log: IServicePlugin.java,v $
 * Revision 1.1  2008/04/12 13:44:37  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2008/03/05 01:34:59  Frank
 * *** empty log message ***
 *
 * Revision 1.2  2008/01/28 02:02:20  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2008/01/22 12:24:12  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2008/01/22 10:11:28  Frank
 * *** empty log message ***
 *
 * Revision 1.3  2008/01/13 13:19:38  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/12/25 14:10:51  Frank
 * *** empty log message ***
 *
 * Revision 1.4  2007/11/03 04:29:35  Frank
 * *** empty log message ***
 *
 * Revision 1.3  2007/09/01 02:09:38  Frank
 * *** empty log message ***
 *
 * Revision 1.2  2007/09/01 01:55:14  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/08/31 07:08:32  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/08/28 12:44:53  Frank
 * *** empty log message ***
 *
 *
 */

package com.cdoframework.cdolib.servicebus;

import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.database.INoSQLDataEngine;

/**
 * @author Frank
 * 
 * 本接口定义插件对象的行为
 */
public interface IServicePlugin
{
	/**
	 * 获取插件xml中定义的参数数值
	 * @param strName 参数名
	 * @param strDefaultValue 缺省值，如果参数名不存在，返回该值
	 * @return 参数值
	 */
	public String getParameter(String strName,String strDefaultValue);
	
	public String getParameter(String strName);
	
	
	/**
	 * 取插件名
	 * @return
	 */
	String getPluginName();

	INoSQLDataEngine getNoSQLDataEngine(String strNoSQLDBId);
	Return handleTrans(CDO cdoRequest,CDO cdoResponse);
	Return handleDataTrans(CDO cdoRequest,CDO cdoResponse);
	Return handleDataTrans(String strServiceName,CDO cdoRequest,CDO cdoResponse);
}
