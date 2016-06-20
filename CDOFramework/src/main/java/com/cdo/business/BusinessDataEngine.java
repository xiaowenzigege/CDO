package com.cdo.business;


import org.apache.log4j.Logger;

public class BusinessDataEngine extends com.cdoframework.cdolib.database.DataEngine
{
	private static final Logger log = Logger.getLogger(BusinessDataEngine.class);
	
	public void onSQLStatement(String strSQL)
	{
		if(log.isDebugEnabled()){log.debug("SQL:"+strSQL);}
	}

	public void onException(String strText,Exception e)
	{				
		log.error("Database Exception: "+strText+"\r\n"+e+":"+e);
	}

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public BusinessDataEngine()
	{

	}
}
