package com.cdo.business.web.client;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import com.cdo.util.bean.CDOHTTPResponse;
import com.cdo.util.constants.Constants;
import com.cdo.util.exception.HttpException;
import com.cdo.util.serial.Serializable;
import com.cdoframework.cdolib.base.DataType;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.data.cdo.Field;
import com.cdoframework.cdolib.data.cdo.FileField;
import com.cdoframework.cdolib.servicebus.ITransService;

public class WebHttpClient implements IWebClient{
	private static Logger logger = Logger.getLogger(WebHttpClient.class);
	
	private String strUrl;

	public WebHttpClient() {
	}

	public WebHttpClient(String strUrl) {		
		this.strUrl = strUrl;
	}

	public String getUrl() {
		return this.strUrl;
	}

	public void setUrl(String strUrl) {
		this.strUrl = strUrl;
	}

	
	public Return handleTrans(CDO cdoRequest, CDO cdoResponse) {

		if ((this.strUrl == null) || (this.strUrl.equals(""))){
			throw new HttpException(" target strUrl  is null ");
		}			
//		String strCDOXml = "";
		CDOHTTPResponse httpRes=null;
		try {
			CDOHTTPClient httpClient = new CDOHTTPClient(this.strUrl);	
			httpClient.setTransCDO(cdoRequest);
//			//普通参数
//			Map<String,String> paramMap = new HashMap<String,String>();
//			paramMap.put("strTransName", cdoRequest.getStringValue("strTransName"));
//			paramMap.put(Constants.CDO.HTTP_CDO_REQUEST, cdoRequest.toXML());
//			//设置普通参数
//			httpClient.setNameValuePair(paramMap);
//			httpClient.setTransCDO(true);
			//cdo有文件上传,设置文件参数 和设置 头信息				
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
	    				 headers.put(Constants.CDO.HTTP_CDO_UPLOADFILE_KEY, "1");//表示CDO里包含有文件
	    			 }
	    		 }
	    		 httpClient.setHeaders(headers);
	    		 httpClient.setUploadFiles(uploadFiles);
			}
			//主要处理由外部，设置控制文件流，默认为自动关闭。当由外部通过OutStreamCDO控制  设置autoCloseStream 外部控制关闭
			httpClient.setCdoResponse(cdoResponse);
			//响应数据	
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
			/**
			strCDOXml =httpRes.getResponseText();			
			if (strCDOXml != null && !strCDOXml.trim().equals("")) {				            			    
				CDO cdoReturn=new CDO();
				ParseHTTPXmlCDO.xmlToCDO(strCDOXml, cdoReturn, cdoResponse);								
				httpRes.copyFile2CDO(cdoResponse);
			    return new Return(cdoReturn.getIntegerValue("nCode"),cdoReturn.getStringValue("strText"), cdoReturn.getStringValue("strInfo"));			
		  }**/
//			CDO cdoOutput=Serializable.byte2ProtoCDO();  
//			CDO cdoReturn=cdoOutput.getCDOValue("cdoReturn");
			CDO cdoReturn=new CDO();
			ParseHTTPProtoCDO.HTTPProtoParse.parse(httpRes.getResponseBytes(), cdoReturn, cdoResponse);
			//有文件传输,仅设置文件句柄，文件在磁盘上，或者通过外部开发者自己控制，写入到指定设备[内存，磁盘，输出流上]
			httpRes.copyFile2CDO(cdoResponse);			
			return new Return(cdoReturn.getIntegerValue("nCode"),cdoReturn.getStringValue("strText"), cdoReturn.getStringValue("strInfo"));	
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			return Return.valueOf(-1, "ERROR:" + ex.getMessage());
		}finally{
			httpRes.setResponseBytes(null);
			httpRes=null;
		}
//		logger.error("返回数据: strCDOXml IS NULL,please check network connection");
//		return Return.valueOf(-1, "ERROR:return strCDOXml IS NULL,please check network connection");
	}

	@Override
	public Return asyncHandleTrans(CDO cdoRequest,CDO cdoResponse){		
		cdoRequest.setBooleanValue(ITransService.ASYNCH_KEY, true);
		return this.handleTrans(cdoRequest, cdoResponse);		
	}
}