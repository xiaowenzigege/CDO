package com.cdoframework.cdolib.service.framework;

import org.apache.log4j.Logger;

import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.BusinessLog;
import com.cdoframework.cdolib.servicebus.ITransService;

/**
 * 日志数据库操作
 * 
 * @author skyf_chen
 * 
 */

public class BusinessLogger {
	Logger logger = Logger.getLogger(BusinessLogger.class);

	private  static String getOpratorValue(CDO cdoOperator,String strKeyDefine,String strOperatorkey,CDO cdoRequest,CDO cdoResponse)
	{
		String strValue = null;
		if(strKeyDefine!=null && strKeyDefine.length()>0)
		{
			if(cdoRequest.exists(strKeyDefine))
			{
				strValue = cdoRequest.getObjectValue(strKeyDefine).toString();
			}else if(cdoResponse!=null && cdoResponse.exists(strKeyDefine))
			{
				strValue = cdoResponse.getObjectValue(strKeyDefine).toString();
			}
		}
		if(strValue==null && cdoOperator!=null)
		{
			strValue = cdoOperator.getStringValue(strOperatorkey);
		}
		if(strValue==null)
		{
			strValue = "";
		}
		return strValue;
	}
	
	public static CDO generateBusinessLogRequest(String strServiceName,String strTransName,CDO cdoRequest,CDO cdoResponse,int nScene,Return handleResult,BusinessLog businessLogDefine)
	{	
		CDO cdoBusinessLog = new CDO();
		//nPhrase
		cdoBusinessLog.setIntegerValue("nPhrase",nScene);
		//strTransName
		cdoBusinessLog.setStringValue("strTransName2",strServiceName+"."+strTransName);
		
		//strOperationName
		String strOperationName = businessLogDefine.getOperationName();
		if(strOperationName==null)
		{			
			strOperationName = "";
		}
		cdoBusinessLog.setStringValue("strOperationName",strOperationName);
		
		//strOperationType
		String strOperationType = businessLogDefine.getOperationType();
		if(strOperationType==null)
		{			
			strOperationType = "";
		}
		cdoBusinessLog.setStringValue("strOperationType",strOperationType);
		
		//strRelationId
		String strRelationId = businessLogDefine.getRelationId();
		if(strRelationId!=null)
		{
			if(cdoRequest.exists(strRelationId))
			{
				strRelationId = cdoRequest.getObjectValue(strRelationId).toString();
			}else
			{
				if(cdoResponse!=null && cdoResponse.exists(strRelationId))
				{
					strRelationId = cdoResponse.getObjectValue(strRelationId).toString();
				}
			}
		}
		else
		{
			strRelationId = "";
		}
		cdoBusinessLog.setStringValue("strRelationId",strRelationId);
		
		//strRelationTitle
		String strRelationTitle = businessLogDefine.getRelationTitle();
		if(strRelationTitle!=null)
		{
			if(cdoRequest.exists(strRelationTitle))
			{
				strRelationTitle = cdoRequest.getObjectValue(strRelationTitle).toString();
			}else
			{
				if(cdoResponse!=null && cdoResponse.exists(strRelationTitle))
				{
					strRelationTitle = cdoResponse.getObjectValue(strRelationTitle).toString();
				}
			}
		}
		else
		{
			strRelationTitle = "";
		}
		cdoBusinessLog.setStringValue("strRelationTitle",strRelationTitle);
		
		//strCDORequestXml
		cdoBusinessLog.setStringValue("strCDORequestXml",cdoRequest.toXML());	
		//strCDOResponseXml
		String strCDOResponseXml = "";
		if(cdoResponse!=null)
		{
			strCDOResponseXml = cdoResponse.toXML();
		}
		cdoBusinessLog.setStringValue("strCDOResponseXml",strCDOResponseXml);	
		
		// userid,username,ip
		CDO cdoOperator = null;
		if(cdoRequest.exists("cdoOperator"))
		{
			cdoOperator = cdoRequest.getCDOValue("cdoOperator");
		}		
		
		//userid
		String strUserId = getOpratorValue(cdoOperator,businessLogDefine.getUserId(),"lUserId",cdoRequest,cdoResponse);
		cdoBusinessLog.setStringValue("strUserId",strUserId);	
		
		//strUserName		
		String strUserName = getOpratorValue(cdoOperator,businessLogDefine.getUserName(),"strUserName",cdoRequest,cdoResponse);
		cdoBusinessLog.setStringValue("strUserName",strUserName);	
		
		//strIP
		String strIP = getOpratorValue(cdoOperator,null,"strIp",cdoRequest,cdoResponse);
		cdoBusinessLog.setStringValue("strIP",strIP);
		
		//nReturnResult
		int nReturnResult = Integer.MAX_VALUE;
		if(handleResult!=null)
		{
			nReturnResult = handleResult.getCode();
		}
		cdoBusinessLog.setIntegerValue("nReturnResult",nReturnResult);
		
		//strException
		String strException = "";
		if(handleResult!=null && handleResult.getCode()!=0)
		{
			strException = handleResult.getText();
		}
		cdoBusinessLog.setStringValue("strException",strException);
		
		//nPriority
		int nPriority = businessLogDefine.getPriority();
		cdoBusinessLog.setIntegerValue("nPriority",nPriority);
		
		cdoBusinessLog.setStringValue(ITransService.SERVICENAME_KEY,"SystemService");
		cdoBusinessLog.setStringValue(ITransService.TRANSNAME_KEY,"addBusinessLog");
		return cdoBusinessLog;		
	}
}
