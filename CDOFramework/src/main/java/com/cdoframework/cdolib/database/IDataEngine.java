/**
 * www.cdoforum.com 2007版权所有
 * 
 * $Header: /CVSData/Frank/CVSROOT/CDOForum/CDODatabase/Source/com/cdoframework/cdolib/database/IDataEngine.java,v 1.1
 * 2008/04/12 13:35:46 Frank Exp $
 * 
 * $Log: IDataEngine.java,v $ Revision 1.1 2008/04/12 13:35:46 Frank *** empty log message ***
 * 
 * Revision 1.1 2008/03/05 01:34:58 Frank *** empty log message ***
 * 
 * Revision 1.8 2008/02/23 05:18:43 Frank *** empty log message ***
 * 
 * Revision 1.7 2008/01/11 12:31:44 Frank *** empty log message ***
 * 
 * Revision 1.6 2008/01/07 10:55:25 Frank *** empty log message ***
 * 
 * Revision 1.5 2008/01/04 13:20:34 Frank *** empty log message ***
 * 
 * Revision 1.4 2007/12/27 12:28:06 Frank *** empty log message ***
 * 
 * Revision 1.3 2007/12/15 09:35:39 Frank *** empty log message ***
 * 
 * Revision 1.2 2007/12/15 09:07:13 Frank *** empty log message ***
 * 
 * Revision 1.1 2007/11/03 03:00:58 Frank *** empty log message ***
 * 
 * 
 */

package com.cdoframework.cdolib.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Properties;

import com.cdoframework.cdolib.base.ObjectExt;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.data.cdo.CDOArrayField;
import com.cdoframework.cdolib.database.dataservice.SQLTrans;

public interface IDataEngine
{
	// 属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------
	public void setDriver(String strDriver);

	public String getDriver();

	public void setURI(String strURI);

	public String getURI();

	public String getCharset();

	public void setCharset(String strCharset);

	public Properties getProperties();

	public void setProperties(Properties properties);

	public String getUserName();

	public void setUserName(String strUserName);

	public String getPassword();

	public void setPassword(String strPassword);

	public void setMinConnectionCount(int nMinIdleConnectionCount);

	public int getMinConnectionCount();

	public void setMaxConnectionCount(int nMaxIdleConnectionCount);

	public int getMaxConnectionCount();

	public void setTimeout(long lTimeout);

	public long getTimeout();

	public boolean isOpened();
	
	public int getLoadLevel();
	
	public void setLoadLevel(int nLoadlevel);

	// 公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------
	/**
	 * 打开数据库
	 */
	public Return open();

	/**
	 * 关闭数据库
	 * 
	 */
	public void close();

	/**
	 * 获取一个数据库连接
	 * 
	 * @return
	 */
	public Connection getConnection() throws SQLException;

	public void commit(Connection conn) throws SQLException;

	public void rollback(Connection conn);

	/**
	 * 关闭结果集
	 * 
	 * @param rs
	 */
	public void closeResultSet(ResultSet rs);

	/**
	 * 关闭Statement
	 * 
	 * @param stat
	 */
	public void closeStatement(Statement stat);

	/**
	 * 关闭Statement
	 * 
	 * @param stat
	 */
	public void closeStatement(String strSQL,PreparedStatement stat);

	/**
	 * 关闭Connection
	 * 
	 * @param conn
	 */
	public void closeConnection(Connection conn);

	/**
	 * 关闭Connection
	 * 
	 * @param conn
	 */
	public void closeConnection(Connection conn,boolean bAutoCommit);

	public PreparedStatement prepareStatement(Connection conn,String strSourceSQL,CDO cdoRequest) throws SQLException;
	
	/**
	 * 读取当前的记录数据
	 * 
	 * @param rs
	 */
	public CDO readRecord(ResultSet rs,String[] strsFieldName,int[] naFieldType,int[] nsPrecision,int[] nsScale) throws SQLException,IOException;
	public int readRecord(ResultSet rs,String[] strsFieldName,int[] naFieldType,int[] nsPrecision,int[] nsScale,CDO cdoRecord) throws SQLException,IOException;

	/**
	 * 通过一个传入的数据库连接查询并输出第一条记录的第一个字段
	 * 
	 * @param conn
	 * @param strSQL
	 * @param cdoRequest
	 * @param cdoResponse
	 * @return
	 * @throws Exception
	 */
	public Object executeQueryField(Connection conn,String strSQL,CDO cdoRequest) throws SQLException,IOException;

	/**
	 * 通过一个传入的数据库连接查询并输出第一条记录的第一个字段(含类型)
	 * 
	 * @param conn
	 * @param strSQL
	 * @param cdoRequest
	 * @param cdoResponse
	 * @return
	 * @throws Exception
	 */
	public ObjectExt executeQueryFieldExt(Connection conn,String strSQL,CDO cdoRequest) throws SQLException,IOException;

	/**
	 * 通过一个传入的数据库连接查询并输出第一条记录
	 * 
	 * @param conn
	 * @param strSQL
	 * @param cdoRequest
	 * @param cdoResponse
	 * @return
	 * @throws Exception
	 */
	public int executeQueryRecord(Connection conn,String strSQL,CDO cdoRequest,CDO cdoResponse) throws SQLException,
					IOException;

	/**
	 * 通过一个传入的数据库连接查询并输出所有记录
	 * 
	 * @param conn
	 * @param strSQL
	 * @param cdoRequest
	 * @param cafRecordSet
	 * @return
	 * @throws Exception
	 */
	public int executeQueryRecordSet(Connection conn,String strSQL,CDO cdoRequest,CDOArrayField cafRecordSet)
					throws SQLException,IOException;

	/**
	 * 执行数据库更新语句
	 * 
	 * @param conn
	 * @param strSQL
	 * @param cdoRequest
	 * @return
	 * @throws Exception
	 */
	public int executeUpdate(Connection conn,String strSQL,CDO cdoRequest) throws SQLException,IOException;

	/**
	 * 执行数据库更新语句
	 * 
	 * @param conn
	 * @param strSQL
	 * @param cdoRequest
	 * @return
	 * @throws Exception
	 */
	public void executeSQL(Connection conn,String strSQL,CDO cdoRequest) throws SQLException,IOException;

	/**
	 * 使用指定的连接执行数据库事务
	 * 
	 * @param conn
	 * @param cdoRequest
	 * @return
	 */
	public Return executeTrans(Connection conn,HashMap<String,SQLTrans> hmTrans,CDO cdoRequest,CDO cdoResponse,
					boolean bUseTransFlag);

	/**
	 * 使用指定的连接执行数据库事务，启用TransFlag
	 * 
	 * @param conn
	 * @param cdoRequest
	 * @return
	 */
	public Return executeTrans(Connection conn,HashMap<String,SQLTrans> hmTrans,CDO cdoRequest,CDO cdoResponse);

	/**
	 * 执行数据库事务
	 * 
	 * @param cdoRequest
	 * @param cdoResponse
	 * @return
	 */
	public Return executeTrans(HashMap<String,SQLTrans> hmTrans,CDO cdoRequest,CDO cdoResponse);

	// 接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	// 事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	// 事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------
	public void onException(String strText,Exception e);

	public void onSQLStatement(String strSQL);
}
