/**
 * www.cdoforum.com 2007版权所有
 *
 * $Header: /CVSData/Frank/CVSROOT/CDOForum/CDOFrameBase/Source/com/cdoframework/cdolib/servicebus/business/ITransServiceObject.java,v 1.1 2008/04/12 13:44:37 Frank Exp $
 *
 * $Log: ITransServiceObject.java,v $
 * Revision 1.1  2008/04/12 13:44:37  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2008/03/05 01:34:59  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2008/01/28 02:02:19  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2008/01/22 12:23:44  Frank
 * *** empty log message ***
 *
 * Revision 1.2  2008/01/22 11:30:47  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2008/01/22 10:11:58  Frank
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

package com.cdoframework.cdolib.servicebus;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;

/**
 * @author Frank
 */
/**
 * @author Administrator
 *
 */
public interface ITransService extends IServiceObject
{
	String	SERVICENAME_KEY="strServiceName";
	String	TRANSNAME_KEY="strTransName";
	
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
	 * @param cdoRequest 事务请求对象，事务名用strTransName区分开
	 * @param cdoResponse 事务应答对象
	 * @return 事务处理结果，如果事务名不被支持，则返回null
	 */
	Return handleTrans(CDO cdoRequest,CDO cdoResponse);
	
	/**
	 * 处理一个事务, 这个方法给注解方式使用，已达到兼容老的把handleTrans暴露给应用使用方法
	 * 
	 * @param cdoRequest 事务请求对象，事务名用strTransName区分开
	 * @param cdoResponse 事务应答对象
	 * @return 事务处理结果，如果事务名不被支持，则返回null
	 */
	public Return processTrans(CDO cdoRequest, CDO cdoResponse);

	/**
	 * 处理一个事务
	 * @param cdoRequest 事务请求对象，事务名用strTransName区分开
	 * @param cdoResponse 事务应答对象
	 * @return 事务处理结果，如果事务名不被支持，则返回null
	 */
	void handleEvent(CDO cdoEvent);
	
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
	
	/**
	 * 把子类注入到父类中，完成解析注解的transname。
	 * @param child
	 */
	void inject(ITransService child); 
	
	Return validate(CDO cdoRequest);
	
	boolean containsTrans(String strTransName);
	/**
	 * 提供普通数据库connection, 非bigTable 分库分表连接
	 * @param strDataGroupId
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection(String strDataGroupId) throws SQLException;

	
}
