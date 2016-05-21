/**
 * www.cdoforum.com 2007版权所有
 *
 * $Header: /CVSData/Frank/CVSROOT/CDOForum/CDOFrameBase/Source/com/cdoframework/cdolib/servicebus/web/IServiceObject.java,v 1.1 2008/04/12 13:44:37 Frank Exp $
 *
 * $Log: IServiceObject.java,v $
 * Revision 1.1  2008/04/12 13:44:37  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2008/03/05 01:34:58  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2008/01/28 02:02:19  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2008/01/22 12:24:05  Frank
 * *** empty log message ***
 *
 * Revision 1.3  2008/01/22 10:12:10  Frank
 * *** empty log message ***
 *
 * Revision 1.2  2007/12/26 12:29:34  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/12/25 14:10:51  Frank
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

import com.cdoframework.cdolib.servicebus.IServicePlugin;

/**
 * @author Frank
 */
public interface IServiceObject
{
	/**
	 * 传入服务总线对象的引用
	 * @param serviceBus 服务总线对象
	 */
	void setServiceBus(IServiceBus serviceBus);

	/**
	 * 传入服务插件对象的引用
	 * @param servicePlugin 服务插件对象
	 */
	void setServicePlugin(IServicePlugin servicePlugin);
}
