package com.cdo.util.business;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import com.cdo.util.bean.CDOHTTPResponse;
import com.cdo.util.constants.Constants;
import com.cdo.util.http.HttpClient;
import com.cdoframework.cdolib.base.ObjectExt;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;

public class BusinessClient implements IBusinessClient{
	private static Logger logger = Logger.getLogger(BusinessClient.class);

	private static String strDefaultUrl;	
	private String strUrl;

	public BusinessClient() {
	}

	public BusinessClient(String configValue) {
		strDefaultUrl = configValue;
	}

	public String getStrUrl() {
		return this.strUrl;
	}

	public void setStrUrl(String strUrl) {
		this.strUrl = strUrl;
	}

	static {
		try {
			ResourceBundle resourceBundle = PropertyResourceBundle.getBundle("resource");
			if (resourceBundle != null) {
				strDefaultUrl = resourceBundle.getString("strBusinessUrl");
			}
		} catch (Exception e) {
			logger.warn("BusinessClient 资源文件[resource.properties]未找到,"+e.getMessage());
		}
	}
	
	public Return handleTrans(CDO cdoRequest, CDO cdoResponse) {

		if ((this.strUrl == null) || (this.strUrl.equals("")))
			this.strUrl = strDefaultUrl;
		String strCDOXml = "";
		CDOHTTPResponse httpRes=null;
		try {
			HttpClient httpClient = null;
			String strRequest = cdoRequest.toXML();
			String strTransName = cdoRequest.getStringValue("strTransName");
			boolean isPost=false;
			String strURL="";
			//有文件传输 或长度已超过300
			if(strRequest.length()>300 || cdoRequest.getSerialFileCount()>0)
				 isPost=true;
			if(!isPost)
				strURL = this.strUrl + "?strTransName=" + strTransName + "&"+Constants.CDO.HTTP_CDO_REQUEST+"="+ URLEncoder.encode(strRequest, "UTF-8");
			//编码后长度超过300
			if (isPost||strURL.length() >= 300) {
				//超过Get 长度，采用post处理
				httpClient = new HttpClient(this.strUrl);
				//普通参数
				Map<String,String> paramMap = new HashMap<String,String>();
				paramMap.put("strTransName", cdoRequest.getStringValue("strTransName"));
				paramMap.put(Constants.CDO.HTTP_CDO_REQUEST, strRequest);
				//设置普通参数
				httpClient.setNameValuePair(paramMap);
				//设置文件参数 和设置 头信息				
				if(cdoRequest.getSerialFileCount()>0){
					 Map<String,File> uploadFiles=new HashMap<String,File>();
		    		 Map<String,String> headers=new HashMap<String,String>();
					 Iterator<Map.Entry<String,ObjectExt>> it=cdoRequest.entrySet().iterator();		    		 
		    		 while(it.hasNext()){
		    			 Map.Entry<String,ObjectExt> entry=it.next();
		    			 ObjectExt objExt=entry.getValue();
		    			 if(objExt.getType()==ObjectExt.FILE_TYPE){
		    				 uploadFiles.put(entry.getKey(),entry.getValue().getFile());
		    				 //设置普通参表示有文件上传
		    				 headers.put(Constants.CDO.HTTP_CDO_UPLOADFILE_KEY, "true");
		    			 }
		    		 }
		    		 httpClient.setHeaders(headers);
		    		 httpClient.setUploadFiles(uploadFiles);
				}

				if (logger.isInfoEnabled()) 
					logger.info("\r\n  send Post strUrl="+this.strUrl+",param="+cdoRequest.toXMLLog());
								
			} else {
				httpClient = new HttpClient(strURL);
				httpClient.setMethod(HttpClient.METHOD_GET);
				
				if (logger.isInfoEnabled())
					logger.info("\r\n Get strUrl= " + strURL);
				
			}
			//响应			
			httpRes=httpClient.execute(cdoResponse);
					
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			return Return.valueOf(-1, " http response :" + httpRes+ ";Send Http Request ERROR:" + ex.getMessage());
		}
		
		if(httpRes.getStatusCode() != HttpStatus.SC_OK){
			return Return.valueOf(-1, " http Status:" + httpRes.getStatusCode()
			        + ";Send Http Request ERROR:" + httpRes);
		}
		try {		
			strCDOXml =httpRes.getResponseText();			
			if (strCDOXml != null && !strCDOXml.trim().equals("")) {				            
			    
				CDO tmpCDO = CDO.fromXML(strCDOXml);
				cdoResponse.copyFrom(tmpCDO.getCDOValue("cdoResponse"));
				httpRes.copyFile2CDO(cdoResponse);
				CDO cdoReturn = tmpCDO.getCDOValue("cdoReturn");					
			    return new Return(cdoReturn.getIntegerValue("nCode"),cdoReturn.getStringValue("strText"), cdoReturn.getStringValue("strInfo"));			
		  }
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			return Return.valueOf(-1, "ERROR:" + ex.getMessage());
		}
		logger.error("返回数据: strCDOXml IS NULL,please check network connection");
		return Return.valueOf(-1, "ERROR:return strCDOXml IS NULL,please check network connection");
	}
}