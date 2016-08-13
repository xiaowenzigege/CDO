package com.cdo.business.web.client;

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
import com.cdoframework.cdolib.base.DataType;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.data.cdo.Field;
import com.cdoframework.cdolib.data.cdo.FileField;

public class WebHttpClient implements IWebClient{
	private static Logger logger = Logger.getLogger(WebHttpClient.class);

	private static String strDefaultUrl;	
	private String strUrl;

	public WebHttpClient() {
	}

	public WebHttpClient(String configValue) {
		strDefaultUrl = configValue;
	}

	public String getUrl() {
		return this.strUrl;
	}

	public void setUrl(String strUrl) {
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
			CDOHTTPClient httpClient = null;
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
				httpClient = new CDOHTTPClient(this.strUrl);
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
					 Iterator<Map.Entry<String,Field>> it=cdoRequest.entrySet().iterator();		    		 
		    		 while(it.hasNext()){
		    			 Map.Entry<String,Field> entry=it.next();
		    			 Field objExt=entry.getValue();
		    			 if(objExt.getType().getDataType()==DataType.FILE_TYPE){
		    				 uploadFiles.put(entry.getKey(),((FileField)entry.getValue()).getValue());
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
				httpClient = new CDOHTTPClient(strURL);
				httpClient.setMethod(HttpClient.METHOD_GET);
				
				if (logger.isInfoEnabled())
					logger.info("\r\n Get strUrl= " + strURL);
				
			}
			//设置返回值 
			httpClient.setCdoResponse(cdoResponse);
			//响应	
			httpRes=httpClient.execute();					
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
				CDO cdoReturn=new CDO();
				ParseHTTPXmlCDO.xmlToCDO(strCDOXml, cdoReturn, cdoResponse);								
				httpRes.copyFile2CDO(cdoResponse);
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