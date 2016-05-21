/**
 * www.cdoforum.com 2007版权所有
 *
 * $Header: /CVSData/Frank/CVSROOT/CDOForum/CDOBusiness/Source/com/cdoframework/cdolib/servicebus/business/IActiveServiceObject.java,v 1.1 2008/03/05 01:35:53 Frank Exp $
 *
 * $Log: IActiveServiceObject.java,v $
 * Revision 1.1  2008/03/05 01:35:53  Frank
 * *** empty log message ***
 *
 * Revision 1.2  2008/01/28 02:02:02  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2008/01/22 12:23:43  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2008/01/22 10:11:58  Frank
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

package com.cdoframework.cdolib.servicebus;



/**
 * @author Frank
 */
public interface IActiveService extends com.cdoframework.cdolib.base.IActiveObject,ITransService
{
	public boolean isClusterd();
	public void setClustered(boolean bIsClustered);
	/**
	 * 设置服务名
	 * @param strServiceName
	 */
	void setServiceName(String strServiceName);
	
	/**
	 * 取服务名
	 * @return
	 */
	String getServiceName();
}
