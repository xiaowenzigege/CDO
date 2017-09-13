package com.cdo.util.sql;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.cdoframework.cdolib.base.DateTime;
import com.cdoframework.cdolib.base.Utility;
import com.cdoframework.cdolib.data.cdo.CDO;
public class SQLUtil {
		private static String strSystemCharset=System.getProperty("sun.jnu.encoding");
		/**
		 * 关闭结果集
		 * 
		 * @param rs
		 */
		public static void closeResultSet(ResultSet rs){
			if(rs==null)
				return;
			try{rs.close();}catch(Exception e){}
		}

		/**
		 * 关闭Statement
		 * 
		 * @param stat
		 */
		public  static void closePreparedStatement(PreparedStatement stat){
			if(stat==null)
				return;

			try{stat.close();}catch(Exception e){}
		}
		
		/**
		 * 关闭Statement
		 * 
		 * @param stat
		 */
		public static void closeStatement(Statement stat){
			if(stat==null)
				return;

			try{stat.close();}catch(Exception e){}
		}
		/**
		 * 关闭Connection
		 * 
		 * @param conn
		 */
		public static void closeConnection(Connection conn){
			if(conn==null)
				return;
			try{conn.close();}catch(Exception e){}
		}	
		

		/**
		 * 读取数据放入  cdoField中
		 * @param rs
		 * @param cdoResponse
		 * @param strCharset
		 * @throws SQLException
		 * @throws IOException
		 */
		public static void readRecord(ResultSet rs,CDO cdoField,String strDBCharset) throws SQLException, IOException{			
			ResultSetMetaData meta=rs.getMetaData();
			String[] strsFieldName=new String[meta.getColumnCount()];
			int[] nsFieldType=new int[strsFieldName.length];
			int[] nsPrecision=new int[strsFieldName.length];
			int[] nsScale=new int[strsFieldName.length];
			for(int i=0;i<strsFieldName.length;i++)
			{
				/**支持JDBC4**/
				strsFieldName[i]=meta.getColumnLabel(i+1);
				nsFieldType[i]=meta.getColumnType(i+1);
				nsPrecision[i]=meta.getPrecision(i+1);
				nsScale[i]=meta.getScale(i+1);
			}
			// 读取记录信息
			readRecord(rs,strsFieldName,nsFieldType,nsPrecision,nsScale,cdoField,strDBCharset);
		
		}
		/**
		 * 
		 * @param rs
		 * @param cdoResponse 放入cdo数组
		 * @param cdosKey
		 * @param strDBCharset
		 * @throws SQLException
		 * @throws IOException
		 */
		public static void readRecordSet(ResultSet rs,CDO cdoResponse,String cdosKey,String strDBCharset) throws SQLException,IOException{

			ResultSetMetaData meta=rs.getMetaData();
			String[] strsFieldName=new String[meta.getColumnCount()];
			int[] nsFieldType=new int[strsFieldName.length];
			int[] nsPrecision=new int[strsFieldName.length];
			int[] nsScale=new int[strsFieldName.length];
	
			for(int i=0;i<strsFieldName.length;i++)
			{
				
				strsFieldName[i]=meta.getColumnLabel(i+1);
				nsFieldType[i]=meta.getColumnType(i+1);
				nsPrecision[i]=meta.getPrecision(i+1);
				nsScale[i]=meta.getScale(i+1);
			}

			// 读取记录
			ArrayList<CDO> alRecord=new ArrayList<CDO>();
			while(true)
			{
				// 读取记录信息
				CDO cdoRecord=readRecord(rs,strsFieldName,nsFieldType,nsPrecision,nsScale,strDBCharset);
				if(cdoRecord==null)
				{
					break;
				}
				alRecord.add(cdoRecord);
			}
			cdoResponse.setCDOListValue(cdosKey, alRecord);
	}
		/**
		 * 读取记录
		 * @param rs
		 * @param strsFieldName
		 * @param naFieldType
		 * @param nsPrecision
		 * @param nsScale
		 * @param strCharset
		 * @return
		 * @throws SQLException
		 * @throws IOException
		 */
		private  static CDO readRecord(ResultSet rs,String[] strsFieldName,int[] naFieldType,int[] nsPrecision,int[] nsScale,String strCharset) throws SQLException,IOException
		{
			CDO cdoRecord=new CDO();

			if(readRecord(rs,strsFieldName,naFieldType,nsPrecision,nsScale,cdoRecord,strCharset)==0)
			{
				return null;
			}
			
			return cdoRecord;
		}	
		
		private static int readRecord(ResultSet rs,String[] strsFieldName,int[] naFieldType,int[] nsPrecision,int[] nsScale,CDO cdoRecord,String strCharset) throws SQLException,IOException
		{
			if(rs.next()==false)
			{
				return 0;
			}
			
			for(int i=0;i<strsFieldName.length;i++)
			{
				String strFieldName=strsFieldName[i];
				
				try
				{
					if(rs.getObject(i+1)==null)
					{
						continue;
					}
				}catch(Exception e)
				{//已反序列化对象,getObject应该是绝对安全的,不应该有异常,但有的driver处理空时会抛出异常,此处做兼容性处理,不需要抛出异常
					continue;				
				}

				int nFieldType=naFieldType[i];
				switch(nFieldType)
				{
					case Types.BIT:
					{
						byte byValue=rs.getByte(i+1);
						if(byValue==0)
						{
							cdoRecord.setBooleanValue(strFieldName,false);
						}
						else
						{
							cdoRecord.setBooleanValue(strFieldName,true);
						}
						break;
					}
					case Types.TINYINT:
					{
						cdoRecord.setByteValue(strFieldName,rs.getByte(i+1));
						break;
					}
					case Types.SMALLINT:
					{
						cdoRecord.setShortValue(strFieldName,rs.getShort(i+1));
						break;
					}
					case Types.INTEGER:
					{
						cdoRecord.setIntegerValue(strFieldName,rs.getInt(i+1));
						break;
					}
					case Types.BIGINT:
					{
						cdoRecord.setLongValue(strFieldName,rs.getLong(i+1));
						break;
					}
					case Types.REAL:
					{
						cdoRecord.setFloatValue(strFieldName,rs.getFloat(i+1));
						break;
					}
					case Types.DOUBLE:
					case Types.FLOAT:
					{
						cdoRecord.setDoubleValue(strFieldName,rs.getDouble(i+1));
						break;
					}
					case Types.DECIMAL:
					case Types.NUMERIC:
					{
						if(nsScale[i]==0)
						{// 整数
							if(nsPrecision[i]<3)
							{
								cdoRecord.setByteValue(strFieldName,rs.getByte(i+1));
							}
							else if(nsPrecision[i]<5)
							{
								cdoRecord.setShortValue(strFieldName,rs.getShort(i+1));
							}
							else if(nsPrecision[i]<10)
							{
								cdoRecord.setIntegerValue(strFieldName,rs.getInt(i+1));
							}
							else if(nsPrecision[i]<20)
							{
								cdoRecord.setLongValue(strFieldName,rs.getLong(i+1));
							}
							else
							{
								cdoRecord.setDoubleValue(strFieldName,rs.getDouble(i+1));
							}
						}
						else
						{// 小数
							cdoRecord.setDoubleValue(strFieldName,rs.getDouble(i+1));
						}
						break;
					}
					case Types.VARCHAR:
					case Types.LONGVARCHAR:
					case Types.CHAR:
					{
						String strValue=rs.getString(i+1);
						strValue=Utility.encodingText(strValue,strCharset,strSystemCharset);

						cdoRecord.setStringValue(strFieldName,strValue);
						break;
					}
					case Types.CLOB:
					{
						String strValue="";
						Clob clobValue=rs.getClob(i+1);
						char[] chsValue=new char[(int)clobValue.length()];
						clobValue.getCharacterStream().read(chsValue);
						strValue=new String(chsValue);
						strValue=Utility.encodingText(strValue,strCharset,strSystemCharset);
						cdoRecord.setStringValue(strFieldName,strValue.trim());
						break;
					}
					case Types.DATE:
					case Types.TIME:
					case Types.TIMESTAMP:
					{
						try
						{
							String strValue="";
							DateTime dtValue=new DateTime(rs.getTimestamp(i+1));
							strValue=dtValue.toString("yyyy-MM-dd HH:mm:ss");
							cdoRecord.setDateTimeValue(strFieldName,strValue);						
						}
						catch(Exception e)
						{						
						}
						
						break;
					}
					case Types.BINARY:
					case Types.VARBINARY:	
					case Types.LONGVARBINARY:
					{
						byte[] bysValue=null;
						InputStream stream=null;
						try
						{
							stream=rs.getBinaryStream(i+1);
							bysValue=new byte[stream.available()];
							stream.read(bysValue);
						}
						finally
						{
							Utility.closeStream(stream);
						}

						cdoRecord.setByteArrayValue(strFieldName,bysValue);
						break;
					}
				
					case Types.BLOB:
					{
						byte[] bysValue=null;
						Blob blobValue=rs.getBlob(i+1);
						bysValue=blobValue.getBytes(1,(int)blobValue.length());
						cdoRecord.setByteArrayValue(strFieldName,bysValue);
						break;
					}
					default:
						throw new SQLException("Unsupported sql data type "+nFieldType+" on field "+strFieldName);
				}
			}
			return 1;
		}
		
	
	public static  PreparedStatement getPreparedStatement(PreparedStatement pst, List<Object> _params) throws SQLException {			
			if (_params != null) {
				for (int i = 1; i <= _params.size(); i++) {
					Object param = _params.get(i - 1);

					if (param == null){
						pst.setNull(i, java.sql.Types.VARCHAR);
						continue;
					}
					if (param instanceof String){
						pst.setString(i, (String) param);
						continue;
					}
					if (param instanceof Short){
						pst.setShort(i, ((Short) param).shortValue());
						continue;
					}
					if (param instanceof Integer){
						pst.setInt(i, ((Integer) param).intValue());
						continue;
					}
					if (param instanceof Long){
						pst.setLong(i, ((Long) param).longValue());
						continue;
					}
					if (param instanceof java.util.Date){
						pst.setTimestamp(i, new java.sql.Timestamp(((java.util.Date)param).getTime()));
						continue;
					}
	                if(param instanceof BigDecimal) {
						pst.setBigDecimal(i,(BigDecimal)param);
						continue;
					}					
					if (param instanceof Boolean){
						pst.setBoolean(i, (Boolean)param);
						continue;
					}
					if (param instanceof Float){
						pst.setFloat(i, ((Float) param).floatValue());
						continue;
					}
					if (param instanceof Double){
						pst.setDouble(i, ((Double) param).doubleValue());
						continue;
					}
				}
			}
			return pst;
		}		
		
	}