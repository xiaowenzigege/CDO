package com.cdo.util.http;


import java.io.File;
import java.nio.charset.CodingErrorAction;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.Consts;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;

//import org.apache.http.conn.scheme.PlainSocketFactory;
//import org.apache.http.conn.scheme.Scheme;
//import org.apache.http.conn.scheme.SchemeRegistry;
//import org.apache.http.conn.ssl.SSLSocketFactory;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
//import org.apache.http.params.BasicHttpParams;
//import org.apache.http.params.HttpConnectionParams;
//import org.apache.http.params.HttpParams;
//import org.apache.http.params.HttpProtocolParams;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.MessageConstraints;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContexts;
import org.apache.log4j.Logger;

import com.cdo.util.bean.Response;
import com.cdo.util.exception.HttpException;
import com.cdo.util.exception.ResponseException;
/**
 * 
 * @author KenelLiu
 *
 */
public class HttpUtil {
	private static final Logger logger=Logger.getLogger(HttpUtil.class); 
	
    private static final String DEFAULT_ACCEPT = "text/html,text/xml,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"; 
    private static final String DEFAULT_ACCEPT_CHARSET = "utf-8,ISO-8859-1;q=0.7,*;q=0.7"; 
    private static final String DEFAULT_ACCEPT_ENCODING = "x-gzip, gzip"; 
	
    private static  PoolingHttpClientConnectionManager poolHttp;
	private static  RequestConfig defaultRequestConfig;
    private static int retryTime=1;
    private HttpUtil() {
		
	}
    /**
     * 4.5 写法
     */
	static {
		try{
			int SocketTimeout=System.getProperty("http.socketTimeout")==null?
					30*1000:Integer.parseInt(System.getProperty("http.socketTimeout").trim())*1000;
			
			int ConnectTimeout=System.getProperty("http.connectTimeout")==null?
					30*1000:Integer.parseInt(System.getProperty("http.connectTimeout").trim())*1000;
			
			int RequestTimeOut=System.getProperty("http.requestTimeout")==null?
					30*1000:Integer.parseInt(System.getProperty("http.requestTimeout").trim())*1000;
					
			int MaxTotal=System.getProperty("http.maxTotal")==null?
					600:Integer.parseInt(System.getProperty("http.maxTotal").trim());
			
			int DefaultMaxPerRoute=System.getProperty("http.defaultMaxPerRoute")==null?
					100:Integer.parseInt(System.getProperty("http.defaultMaxPerRoute").trim());;
		    
			retryTime=System.getProperty("http.retryTime")==null?
					1:Integer.parseInt(System.getProperty("http.retryTime").trim());;
			
	        SSLContext sslContext = SSLContexts.custom().build();
	        sslContext.init(null,
	                new TrustManager[] { new X509TrustManager() {
	                     
	                    public X509Certificate[] getAcceptedIssuers() {
	                        return null;
	                    }
	
	                    public void checkClientTrusted(
	                            X509Certificate[] certs, String authType) {
	                    }
	
	                    public void checkServerTrusted(
	                            X509Certificate[] certs, String authType) {
	                    }
	                }}, null);
	        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
	                .register("http", PlainConnectionSocketFactory.INSTANCE)
	                .register("https", new SSLConnectionSocketFactory(sslContext))
	                .build();	    
		    
	        // Create socket configuration
	        SocketConfig socketConfig = SocketConfig.custom()
	            .setTcpNoDelay(true)
	            .setSoReuseAddress(true)
	            .setSoKeepAlive(true)            
	            .build();
			
			 // Create message constraints
		    MessageConstraints messageConstraints = MessageConstraints.custom().build();
		            //.setMaxHeaderCount(200)
		            //.setMaxLineLength(2000)
		            
	       // Create connection configuration
	       ConnectionConfig connectionConfig = ConnectionConfig.custom()
	           .setMalformedInputAction(CodingErrorAction.IGNORE)
	           .setUnmappableInputAction(CodingErrorAction.IGNORE)
	           .setCharset(Consts.UTF_8)           
	           .setMessageConstraints(messageConstraints)
	           .build();
		    //--------创建连接池------------//	    
	        poolHttp= new PoolingHttpClientConnectionManager(socketFactoryRegistry);
			poolHttp.setMaxTotal(MaxTotal);
			poolHttp.setDefaultMaxPerRoute(DefaultMaxPerRoute);
			
			poolHttp.closeExpiredConnections();
			poolHttp.closeIdleConnections(5, TimeUnit.MINUTES);      
//			poolHttp.setValidateAfterInactivity(60*1000);
			poolHttp.setDefaultSocketConfig(socketConfig);
			poolHttp.setDefaultConnectionConfig(connectionConfig);
			
	        // Use custom cookie store if necessary.
//	        CookieStore cookieStore = new BasicCookieStore();
	        // Use custom credentials provider if necessary.
	//        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
	        // Create global request configuration
			defaultRequestConfig= RequestConfig.custom()
	            .setCookieSpec(CookieSpecs.DEFAULT)	               
	            .setSocketTimeout(SocketTimeout)
	            .setConnectTimeout(ConnectTimeout)
	            .setConnectionRequestTimeout(RequestTimeOut)
	            .setExpectContinueEnabled(Boolean.FALSE)
	            .build();
	//            .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
	//            .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))	            
			   getHttpClient();
			  if(logger.isInfoEnabled())
				  logger.info("http pool,socketTimeout="+(SocketTimeout/1000)+" seconds,connectTimeout="+(ConnectTimeout/1000)
						  +" seconds,requestTimeout="+(RequestTimeOut/1000)+" seconds,maxTotal="+MaxTotal
						  +",defaultMaxPerRoute="+DefaultMaxPerRoute+",retryTime="+retryTime);
       
		}catch(Exception ex){
			logger.error("创建http pool 出现异常:"+ex.getMessage(), ex);
			throw new HttpException("创建http pool 出现异常:"+ex.getMessage(), ex);
		}
	}
	
	public static CloseableHttpClient getHttpClient() {	
        return HttpClients.custom()
        .setConnectionManager(poolHttp)
        .setDefaultRequestConfig(defaultRequestConfig).setRetryHandler(new DefaultHttpRequestRetryHandler(retryTime, false)).build();
	}
     /*** httpClient 4.2的写法
	private static final String CHARSET = HTTP.UTF_8;
	private static HttpParams params;
	private static ThreadSafeClientConnManager conMgr;
	
	static{
		
		params = new BasicHttpParams();
		// 设置一些基本参数
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params, CHARSET);
		HttpProtocolParams.setUseExpectContinue(params, true);
		HttpProtocolParams.setHttpElementCharset(params, CHARSET);
		HttpProtocolParams.setUserAgent(params, "HttpComponents/1.1");
		// 超时设置		
		HttpConnectionParams.setConnectionTimeout(params, 6000*5);//30秒 （单位毫秒）		
		HttpConnectionParams.setSoTimeout(params, 5000*6);//30秒 （单位毫秒）
		// 设置我们的HttpClient支持HTTP和HTTPS两种模式
		SchemeRegistry schReg = new SchemeRegistry();
		schReg.register(new Scheme("http", 80, PlainSocketFactory
				.getSocketFactory()));
		schReg.register(new Scheme("https", 443, SSLSocketFactory
				.getSocketFactory()));
		
		// 使用线程安全的连接管理来创建HttpClient
		// 从连接池中取连接的超时时间 1000毫秒 
		conMgr = new ThreadSafeClientConnManager(
				schReg, 1000*60, TimeUnit.MILLISECONDS);
		conMgr.setDefaultMaxPerRoute(300);
		conMgr.setMaxTotal(800);
		conMgr.closeExpiredConnections();
		conMgr.closeIdleConnections(10, TimeUnit.SECONDS);
	}
	
	public static HttpClient getHttpClient() {
		return new DefaultHttpClient(conMgr, params);
	}
	*/
	
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