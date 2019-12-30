package com.cdo.util.http;

import java.io.File;
import java.util.Map;
import java.util.Set;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import org.apache.log4j.Logger;
import com.cdo.util.bean.Response;
import com.cdo.util.constants.Constants;
import com.cdo.util.exception.ResponseException;
import com.cdoframework.cdolib.data.cdo.CDO;
/**
 * 
 * @author KenelLiu
 *
 */
public class HttpUtil {
	private static final Logger logger=Logger.getLogger(HttpUtil.class); 
	

	private static int version=0;
	static{
		//默认使用4.5,否则使用4.2
		String httpVersion=System.getProperty(Constants.HTTP.Version,"4.5");	
		if(httpVersion.startsWith("4.5")){
			version=45;
		}else{
			version=42;
		}
	}
    
    private HttpUtil() {
		
	}
  
	
	public static HttpClient getHttpClient() {
		HttpPool httpPool=null;
		if(version==45){
			httpPool=new HttpPool45();			
		}else{
			httpPool=new HttpPool42();			
		}
		return httpPool.getHttpClient();
	}	

	/**
	 * 
	 * @param url 
	 * @return  Response responseText  返回文本
	 * @throws ResponseException
	 */
	public static Response post(String url) throws ResponseException{
		return post(url,null);
	}
	/**
	 * 
	 * @param url
	 * @param params  普通参数格式<name,value>
	 * @return Response responseText  返回文本
	 * @throws ResponseException
	 */
	public static Response post(String url,Map<String, String> params) throws ResponseException{
		return post(url,params,null);
	}
	/**
	 * 
	 * @param url
	 * @param params  普通参数格式<name,value>
	 * @param header  头部参数格式<name,value>
	 * @returnResponse responseText  返回文本
	 * @throws ResponseException
	 */
	public static Response post(String url,Map<String, String> params,Map<String, String> header)throws ResponseException{
		return post(url, params, header,null);
	} 
	/**
	 * 
	 * @param url
	 * @param paramsFile  文件参数格式<name,file>
	 * @return Response responseText  返回文本
	 * @throws ResponseException
	 */
	public static Response postFile(String url,Map<String, File> paramsFile)throws ResponseException{
		return postFile(url,paramsFile,null);
	}
	/**
	 * 
	 * @param url
	 * @param paramsFile  文件参数格式<name,file>
	 * @param params  普通参数格式<name,value>
	 * @return Response responseText  返回文本
	 * @throws ResponseException
	 */
	public static Response postFile(String url,Map<String, File> paramsFile,Map<String, String> params)throws ResponseException {
		return postFile(url,paramsFile,params,null);
	}
	/**
	 * 
	 * @param url
	 * @param paramsFile 文件参数格式<name,file>
	 * @param params   普通参数格式<name,value>
	 * @param header 头部参数格式<name,value>
	 * @return Response responseText  返回文本
	 * @throws ResponseException
	 */
	public static Response postFile(String url,Map<String, File> paramsFile,Map<String, String> params,Map<String, String> header)throws ResponseException {
		return post(url,params,header,paramsFile);
	}
	/**
	 * 
	 * @param url
	 * @param params 普通参数
	 * @param header head参数
	 * @param paramsFile 文件参数
	 * @return Response responseText  返回文本
	 * @throws ResponseException
	 */
	private static Response post(String url,Map<String, String> params,Map<String, String> header,Map<String, File> paramsFile) throws ResponseException {
		com.cdo.util.http.HttpClient client=new com.cdo.util.http.HttpClient(url); 
		client.setHeaders(header);
		client.setNameValuePair(params);			
		client.setUploadFiles(paramsFile);
		return client.execute();	
	}
	
	/**
	 * 
	 * @param url
	 * @param httpMethod  
	 * @param params
	 * @param header
	 * @return
	 * @throws ResponseException
	 */
	public static Response doMethod(String url,String httpMethod,Map<String, String> params,Map<String, String> header) throws ResponseException{
		com.cdo.util.http.HttpClient client=new com.cdo.util.http.HttpClient(url); 
		client.setHeaders(header);
		client.setNameValuePair(params);			
		client.setMethod(httpMethod);
		return client.execute();	
	}
	
	//-------------------普通获取get --------------------//
	/**
	 * 
	 * @param url
	 * @return Response responseText  返回文本
	 * @throws ResponseException
	 */
	public static Response get(String url) throws ResponseException{
		 return get(url,null); 
	}	
	
	/**
	 * 
	 * @param url
	 * @param header
	 * @return Response responseText  返回文本
	 * @throws ResponseException
	 */
	public static Response get(String url,Map<String, String> header) throws ResponseException{
		com.cdo.util.http.HttpClient client=new com.cdo.util.http.HttpClient(url); 
		client.setMethod(com.cdo.util.http.HttpClient.METHOD_GET);
		client.setHeaders(header);
		return client.execute();
		
	}		
	
	
	
	//--------------------post body-----------------------------//
	/**
	 * 
	 * @param url
	 * @param strContent
	 * @return  Response responseText  返回文本
	 * @throws ResponseException
	 */
	public static Response postBody(String url,String strContent) throws ResponseException{
		return postBody(url,strContent,null);
	}
	/**
	 * 
	 * @param url
	 * @param strContent
	 * @param header
	 * @return  Response responseText  返回文本
	 * @throws ResponseException
	 */
	public static Response postBody(String url,String strContent,Map<String, String> header) throws ResponseException{

		return postBody(url, strContent, null, header);
	}	
	/**
	 * 
	 * @param url
	 * @param cdoRequest
	 * @return
	 * @throws ResponseException
	 */
	public static Response postBody(String url,CDO cdoRequest) throws ResponseException{
		return postBody(url,cdoRequest,null);
	}
	/**
	 * 
	 * @param url
	 * @param cdoRequest
	 * @param header
	 * @return
	 * @throws ResponseException
	 */
	public static Response postBody(String url,CDO cdoRequest,Map<String, String> header) throws ResponseException{
		return postBody(url, null, cdoRequest, header);
	}	
	
	private static Response postBody(String url,String strContent,CDO cdoRequest,Map<String, String> header) throws ResponseException{

		HttpClient client=null;
		HttpPost request=null;
		HttpEntity entity=null;
		try {
			// 创建POST请求
			request = new HttpPost(url);
			if(header!=null){
				Set<String> headerKeys = header.keySet();
				for (String headKey : headerKeys) {
					request.addHeader(headKey, header.get(headKey));
				}
			}
			if(cdoRequest!=null){
				// 编码参数
				entity = new ByteArrayEntity(cdoRequest.toProtoBuilder().build().toByteArray());
				request.setEntity(entity);
			}else{
				if(strContent!=null && strContent.trim().length()>0){
					entity = new StringEntity(strContent,Constants.Encoding.CHARSET_UTF8);
					request.setEntity(entity);
				}
			}		
			// 发送请求
			client = getHttpClient();
			HttpResponse res = client.execute(request);				
			Response response=new Response();
			response.setStatusCode(res.getStatusLine().getStatusCode());
			response.setAllHeaders(res.getAllHeaders());
			response.setResponseBytes(res.getEntity()==null?null:EntityUtils.toByteArray(res.getEntity()));
			return response;				
		} catch (Exception e) {			
			logger.error("请求发生异常:"+e.getMessage(),e);
		}finally{
			if(request!=null){try{request.releaseConnection();}catch(Exception ex){}}			
		}
		return null;
	}	
	//-------------------- body-----------------------------//
	/**
	 * 
	 * @param url
	 * @param strContent
	 * @return  Response responseText  返回文本
	 * @throws ResponseException
	 */
	public static Response doMethodBody(String url,String strContent,String httpMehtod) throws ResponseException{
		return doMethodBody(url,strContent,httpMehtod,null);
	}
	/**
	 * 
	 * @param url
	 * @param strContent
	 * @param header
	 * @return  Response responseText  返回文本
	 * @throws ResponseException
	 */
	public static Response doMethodBody(String url,String strContent,String httpMehtod,Map<String, String> header) throws ResponseException{
	
		com.cdo.util.http.HttpClient client=new com.cdo.util.http.HttpClient(url); 
		client.setHeaders(header);
		client.setTransMode(com.cdo.util.http.HttpClient.TRANSMODE_BODY);
		client.setBody(strContent);
		client.setMethod(httpMehtod);
		return client.execute();  	
	}
	
}