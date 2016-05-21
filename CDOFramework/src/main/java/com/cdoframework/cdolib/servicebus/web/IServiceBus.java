/**
 * www.cdoforum.com 2007版权所有
 *
 * $Header: /CVSData/Frank/CVSROOT/CDOForum/CDOFrameBase/Source/com/cdoframework/cdolib/servicebus/web/IServiceBus.java,v 1.1 2008/04/12 13:44:37 Frank Exp $
 *
 * $Log: IServiceBus.java,v $
 * Revision 1.1  2008/04/12 13:44:37  Frank
 * *** empty log message ***
 *
 * Revision 1.2  2008/03/23 14:09:24  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2008/03/05 01:34:58  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2008/01/28 02:02:19  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2008/01/22 12:24:06  Frank
 * *** empty log message ***
 *
 * Revision 1.3  2008/01/22 11:31:23  Frank
 * *** empty log message ***
 *
 * Revision 1.2  2008/01/22 10:12:10  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/12/25 14:10:51  Frank
 * *** empty log message ***
 *
 * Revision 1.3  2007/12/15 09:06:55  Frank
 * *** empty log message ***
 *
 * Revision 1.2  2007/11/03 04:29:35  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/08/31 07:08:32  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/08/28 12:44:54  Frank
 * *** empty log message ***
 *
 *
 */

package com.cdoframework.cdolib.servicebus.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;

/**
 * @author Frank
 */
public interface IServiceBus
{
	/**
	 * 触发事件
	 * @param cdoEvent 事件对象
	 */
	void raiseEvent(CDO cdoEvent);

	/**
	 * 调用事务处理的方法
	 * ServiceBus会根据Request内的数据和ServiceBus.xml的配置内容，自动查找到对应的插件，调用其对象提供的处理方法
	 * @param request HTTP请求对象
	 * @param response HTTP应答对象
	 * @param cdoRequest 事务请求对象，事务名用strTransName区分开
	 * @param cdoResponse 事务应答对象
	 * @return 事务处理结果，如果事务名不被支持，则返回null
	 */
	Return handleTrans(HttpServletRequest request,HttpServletResponse response,CDO cdoRequest,CDO cdoResponse);

	/**
	 * 调用SQL事务的方法
	 * ServiceBus会根据Request内的数据和ServiceBus.xml的配置内容，自动查找到对应的插件，调用其SQLTrans对象提供的处理方法
	 * @param cdoRequest 事务请求对象
	 * @param cdoResponse 事务应答对象
	 * @return 事务处理结果
	 */
	public Return handleSQLTrans(CDO cdoRequest,CDO cdoResponse);

	/**
	 * 设置共享数据，共享数据可以在所有服务对象中共享
	 * @param strId 共享数据的Id
	 * @param objValue 共享数据的Value
	 */
	void setSharedData(String strId,Object objValue);

	/**
	 * 读取共享数据
	 * @param strId 共享数据的Id
	 * @return
	 */
	Object getSharedData(String strId);
}
