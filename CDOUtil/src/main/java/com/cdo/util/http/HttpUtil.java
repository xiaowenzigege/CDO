package com.cdo.util.http;


import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContexts;

import com.cdo.util.bean.Response;
import com.cdo.util.exception.ResponseException;


/**
 * 
 * @author KenelLiu
 *
 */
public class HttpUtil {

	
    private static final String DEFAULT_ACCEPT = "text/html,text/xml,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"; 
    private static final String DEFAULT_ACCEPT_CHARSET = "utf-8,ISO-8859-1;q=0.7,*;q=0.7"; 
    private static final String DEFAULT_ACCEPT_ENCODING = "x-gzip, gzip"; 
	
    
	private static HttpClientBuilder builder;
	private static HttpClientBuilder KeepLivebuilder;
	private static PoolingHttpClientConnectionManager poolHttp;
	private static RegistryBuilder<ConnectionSocketFactory> registry;
	private static RequestConfig.Builder requestBuilder;
  	private static ConnectionKeepAliveStrategy keepAliveStrategy;
	private HttpUtil() {
	}

	static{
		//注册端口
	    registry = RegistryBuilder.<ConnectionSocketFactory>create();
	    registry=registry.register("http", PlainConnectionSocketFactory.INSTANCE);
	    SSLContext sslcontext = SSLContexts.createSystemDefault();
	    registry=registry.register("https",  new SSLConnectionSocketFactory(sslcontext));
	    
	    
	    //--------创建连接池------------//	    
		poolHttp= new PoolingHttpClientConnectionManager(registry.build());
		poolHttp.setMaxTotal(400);
		poolHttp.setDefaultMaxPerRoute(200);
		poolHttp.closeExpiredConnections();
		poolHttp.closeIdleConnections(10, TimeUnit.SECONDS);
		//--------设置客服端连接配置-------------//
		requestBuilder= RequestConfig.custom();
		requestBuilder = requestBuilder.setConnectTimeout(6000*5);
		requestBuilder = requestBuilder.setSocketTimeout(6000*5);
		requestBuilder = requestBuilder.setConnectionRequestTimeout(6000*5);
		requestBuilder = requestBuilder.setContentCompressionEnabled(true);
		requestBuilder = requestBuilder.setExpectContinueEnabled(Boolean.FALSE);		
       //--------设置默认header配置---------// 				    
  
      
       
       //-----若使用一段时间内的连接  复用tcp  需要服务端配置相关的参数值,否则服务端关闭连接，客服端tcp rst一样失败
       //--------应用服务器 默认一般都为短连接      ----------------//
       keepAliveStrategy = new ConnectionKeepAliveStrategy() {
    	   public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
			        // Honor 'keep-alive' header
			        HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
			        while (it.hasNext()) {
			            HeaderElement he = it.nextElement();
			            String param = he.getName();
			            String value = he.getValue();
			            if (value != null && param.equalsIgnoreCase("timeout")) {
			                try {
			                    return Long.parseLong(value) * 1000;
			                } catch(NumberFormatException ignore) {
			                }
			            }
			        }
			        return 30 * 1000;
			    }
			};

       	   	   
	}

	
	public static HttpClient getHttpClient() {	
		if(builder==null){
			HashSet<Header>  defaultHeaders = new HashSet<Header>();   
		       defaultHeaders.add(new BasicHeader(HttpHeaders.ACCEPT_CHARSET, DEFAULT_ACCEPT_CHARSET));
		       defaultHeaders.add(new BasicHeader(HttpHeaders.ACCEPT_ENCODING, DEFAULT_ACCEPT_ENCODING));
		       defaultHeaders.add(new BasicHeader(HttpHeaders.ACCEPT, DEFAULT_ACCEPT)); 
			   defaultHeaders.add(new BasicHeader(HttpHeaders.CONNECTION, "close"));   
		       //----- 创建连接---------------------//
		       builder = HttpClientBuilder.create()
			   .setDefaultRequestConfig(requestBuilder.build())
			   .setConnectionManager(poolHttp)
			   .setDefaultHeaders(defaultHeaders)			  
			   .evictExpiredConnections();			 
		}
		return builder.build();
	}

	public static HttpClient getKeepLiveHttpClient() {	
		if(KeepLivebuilder==null){			   		      
			   HashSet<Header>  defaultHeaders = new HashSet<Header>();   
		       defaultHeaders.add(new BasicHeader(HttpHeaders.ACCEPT_CHARSET, DEFAULT_ACCEPT_CHARSET));
		       defaultHeaders.add(new BasicHeader(HttpHeaders.ACCEPT_ENCODING, DEFAULT_ACCEPT_ENCODING));
		       defaultHeaders.add(new BasicHeader(HttpHeaders.ACCEPT, DEFAULT_ACCEPT)); 
		       //----- 创建连接---------------------//
		       KeepLivebuilder = HttpClientBuilder.create()
			   .setDefaultRequestConfig(requestBuilder.build())
			   .setConnectionManager(poolHttp)
			   .setDefaultHeaders(defaultHeaders)
			   .setKeepAliveStrategy(keepAliveStrategy)
			   .evictExpiredConnections();			 
		}
		return KeepLivebuilder.build();
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
		com.cdo.util.http.HttpClient client=new com.cdo.util.http.HttpClient(url); 
		client.setHeaders(header);
		client.setTransMode(com.cdo.util.http.HttpClient.TRANSMODE_BODY);
		client.setBody(strContent);
		return client.execute();	
	}	
}