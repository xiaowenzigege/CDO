package com.cdo.business;


import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.cdoframework.cdolib.base.DataType;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.data.cdo.Field;

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
	
	public void onExecuteSQL(String strSQL,ArrayList<String> alParaName,CDO cdoRequest){
		if (log.isDebugEnabled()) {
			StringBuilder sb = new StringBuilder("\n{");
			for (int i = 0; i < alParaName.size(); i++) {
				Field object = cdoRequest.getObject(alParaName.get(i));
				Object objValue = object.getObjectValue();
				int nType = object.getType().getDataType();
				sb.append(nType==DataType.BYTE_ARRAY_TYPE?new String((byte[]) objValue):objValue);
				sb.append(',');
			}
			sb.append('}');
			log.debug(strSQL + sb.toString());
		}
	}
	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public BusinessDataEngine()
	{

	}
}
