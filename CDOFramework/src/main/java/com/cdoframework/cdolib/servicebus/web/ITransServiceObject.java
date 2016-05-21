/**
 * www.cdoforum.com 2007版权所有
 *
 * $Header: /CVSData/Frank/CVSROOT/CDOForum/CDOFrameBase/Source/com/cdoframework/cdolib/servicebus/web/ITransServiceObject.java,v 1.1 2008/04/12 13:44:37 Frank Exp $
 *
 * $Log: ITransServiceObject.java,v $
 * Revision 1.1  2008/04/12 13:44:37  Frank
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
 * Revision 1.1  2008/01/22 10:12:10  Frank
 * *** empty log message ***
 *
 * Revision 1.3  2008/01/13 13:20:10  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/12/25 14:10:51  Frank
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

package com.cdoframework.cdolib.servicebus.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;

/**
 * @author Frank
 */
public interface ITransServiceObject extends IServiceObject
{
	/**
	 * 初始化服务对象操作
	 * @return 初始化结果
	 */
	Return init();

	/**
	 * 销毁事务对象操作
	 */
	void destroy();

	/**
	 * 处理一个事务
	 * @param request HTTP请求对象
	 * @param response HTTP应答对象
	 * @param cdoRequest 事务请求对象，事务名用strTransName区分开
	 * @param cdoResponse 事务应答对象
	 * @return 事务处理结果，如果事务名不被支持，则返回null
	 */
	Return handleTrans(HttpServletRequest request,HttpServletResponse response,CDO cdoRequest,CDO cdoResponse);
}
