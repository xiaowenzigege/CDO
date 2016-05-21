/**
 * www.cdoforum.com 2007版权所有
 *
 * $Header: /CVSData/Frank/CVSROOT/CDOForum/CDOFrameBase/Source/com/cdoframework/cdolib/servicebus/business/IServiceBus.java,v 1.1 2008/04/12 13:44:37 Frank Exp $
 *
 * $Log: IServiceBus.java,v $
 * Revision 1.1  2008/04/12 13:44:37  Frank
 * *** empty log message ***
 *
 * Revision 1.3  2008/04/12 11:53:24  Frank
 * *** empty log message ***
 *
 * Revision 1.2  2008/03/23 14:09:25  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2008/03/05 01:34:59  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2008/01/28 02:02:19  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2008/01/22 12:23:43  Frank
 * *** empty log message ***
 *
 * Revision 1.3  2008/01/22 11:30:47  Frank
 * *** empty log message ***
 *
 * Revision 1.2  2008/01/22 10:11:58  Frank
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

package com.cdoframework.cdolib.servicebus;

import java.util.HashMap;

import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.database.BigTableEngine;
import com.cdoframework.cdolib.database.IDataEngine;
import com.cdoframework.cdolib.database.INoSQLDataEngine;

/**
 * @author Frank
 */
public interface IServiceBus extends com.cdoframework.cdolib.base.IActiveObject
{
	/**
	/**
	 * 调用事务处理的方法
	 * ServiceBus会根据Request内的数据和ServiceBus.xml的配置内容，自动查找到对应的插件，调用其对象提供的处理方法
	 * @param cdoRequest 事务请求对象
	 * @param cdoResponse 事务应答对象
	 * @return 事务处理结果
	 */
	Return handleTrans(CDO cdoRequest,CDO cdoResponse);

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
	
	/**
	 * 
	 * @return 
	 */
	BigTableEngine getBigTableEngine();
	
	
	/**
	 * 
	 * @return
	 */
	INoSQLDataEngine getDefaultNoSQLDataEngine();
	
	
	/**
	 * 
	 */
	void addClusterActiveService(String strActiveServiceId,IActiveService activeService);
	
	/**
	 * 测试服务器状态
	 * @return 
	 * @throws RuntimeException
	 */
	boolean testServerState() throws RuntimeException;
	/**
	 * 探测服务器状态信息  返回错误信息 正确返回空字符串
	 * @return 
	 * @throws RuntimeException
	 */
	String detectServerState();

	void handleEvent(CDO cdoEvent);
	
	IEventHandler getEventHandler();
	
	/**
	 * 获取servicebus xml中定义的参数数值
	 * @param strName 参数名
	 * @param strDefaultValue 缺省值，如果参数名不存在，返回该值
	 * @return 参数值
	 */
	public String getParameter(String strName,String strDefaultValue);
	
	public String getParameter(String strName);
	/**提供连接给外部使用**/
	public HashMap<String,com.cdoframework.cdolib.base.CycleList<IDataEngine>> getHMDataGroup();

}
