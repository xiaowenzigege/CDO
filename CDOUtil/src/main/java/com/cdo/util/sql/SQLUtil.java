package com.cdo.util.sql;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
public class SQLUtil {

		/**
		 * 关闭结果集
		 * 
		 * @param rs
		 */
		public static void closeResultSet(ResultSet rs)
		{
			if(rs==null)
			{
				return;
			}

			try
			{
				rs.close();
			}
			catch(Exception e)
			{
			}
		}

		/**
		 * 关闭Statement
		 * 
		 * @param stat
		 */
		public  static void closePreparedStatement(PreparedStatement stat)
		{
			if(stat==null)
			{
				return;
			}

			try
			{
				stat.close();
			}
			catch(Exception e)
			{
			}
		}
		
		/**
		 * 关闭Statement
		 * 
		 * @param stat
		 */
		public static void closeStatement(Statement stat)
		{
			if(stat==null)
			{
				return;
			}

			try
			{
				stat.close();
			}
			catch(Exception e)
			{
			}
		}
		/**
		 * 关闭Connection
		 * 
		 * @param conn
		 */
		public static void closeConnection(Connection conn)
		{
			if(conn==null)
			{
				return;
			}
			try
			{
				conn.close();
			}
			catch(Exception e)
			{
			}
		}	
		

		
		public static  PreparedStatement getPreparedStatement(PreparedStatement pst, List _params) throws SQLException {	

			if (_params != null) {
				for (int i = 1; i <= _params.size(); i++) {
					Object param = _params.get(i - 1);

					if (param == null)
						pst.setNull(i, java.sql.Types.VARCHAR);
					if (param instanceof String)
						pst.setString(i, (String) param);
					if (param instanceof Integer)
						pst.setInt(i, ((Integer) param).intValue());
					if (param instanceof Long)
						pst.setLong(i, ((Long) param).longValue());
					if (param instanceof Float)
						pst.setFloat(i, ((Float) param).floatValue());
					if (param instanceof Double)
						pst.setDouble(i, ((Double) param).doubleValue());
					if (param instanceof java.util.Date)
						pst.setTimestamp(i, new java.sql.Timestamp(((java.util.Date)param).getTime()));
	                if(param instanceof BigDecimal) {
						pst.setBigDecimal(i,(BigDecimal)param);
					}
				}
			}

			return pst;
		}
	}