package com.cdo.util.http;

import java.io.File;
import java.nio.charset.CodingErrorAction;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.config.CookieSpecs;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.config.ConnectionConfig;
//import org.apache.http.config.MessageConstraints;
//import org.apache.http.config.Registry;
//import org.apache.http.config.RegistryBuilder;
//import org.apache.http.config.SocketConfig;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
//import org.apache.http.conn.socket.ConnectionSocketFactory;
//import org.apache.http.conn.socket.PlainConnectionSocketFactory;
//import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
//import org.apache.http.ssl.SSLContexts;
import org.apache.log4j.Logger;

import com.cdo.util.bean.Response;
import com.cdo.util.constants.Constants;
import com.cdo.util.exception.HttpException;
import com.cdo.util.exception.ResponseException;

import io.netty.util.internal.SystemPropertyUtil;
/**
 * 
 * @author KenelLiu
 *
 */
@SuppressWarnings("deprecation")
public class HttpUtil {
	private static final Logger logger=Logger.getLogger(HttpUtil.class); 
	
//    private static final String DEFAULT_ACCEPT = "text/html,text/xml,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"; 
//    private static final String DEFAULT_ACCEPT_CHARSET = "utf-8,ISO-8859-1;q=0.7,*;q=0.7"; 
//    private static final String DEFAULT_ACCEPT_ENCODING = "x-gzip, gzip"; 
//	
//    private  static HttpClientBuilder builder;		
	
    
    private HttpUtil() {
		
	}
    /**
     * 4.5 example http连接池 新版写法  在jdk1.6下，在ubuntu会创建大量的子进程，消耗大量资源，回收资源慢,
     * 故采用旧式写法，减少子进程创建
     * 
	static {
		try{
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
		    MessageConstraints messageConstraints = MessageConstraints.custom()
		            .setMaxHeaderCount(200)
		            .setMaxLineLength(2000)
		            .build();
	       // Create connection configuration
	       ConnectionConfig connectionConfig = ConnectionConfig.custom()
	           .setMalformedInputAction(CodingErrorAction.IGNORE)
	           .setUnmappableInputAction(CodingErrorAction.IGNORE)
	           .setCharset(Consts.UTF_8)           
	           .setMessageConstraints(messageConstraints)
	           .build();
		    //--------创建连接池------------//	    
	        PoolingHttpClientConnectionManager poolHttp= new PoolingHttpClientConnectionManager(socketFactoryRegistry);
			poolHttp.setMaxTotal(500);
			poolHttp.setDefaultMaxPerRoute(100);
			poolHttp.closeExpiredConnections();
			poolHttp.closeIdleConnections(30, TimeUnit.SECONDS);      
			poolHttp.setValidateAfterInactivity(30*1000);
			poolHttp.setDefaultSocketConfig(socketConfig);
			poolHttp.setDefaultConnectionConfig(connectionConfig);
			
	        // Use custom cookie store if necessary.
//	        CookieStore cookieStore = new BasicCookieStore();
	        // Use custom credentials provider if necessary.
	//        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
	        // Create global request configuration
	        RequestConfig defaultRequestConfig = RequestConfig.custom()
	            .setCookieSpec(CookieSpecs.DEFAULT)	               
	            .setSocketTimeout(5000*6)
	            .setConnectTimeout(5000*6)
	            .setConnectionRequestTimeout(5000*6)
	            .setExpectContinueEnabled(Boolean.FALSE)	            
	//            .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
	//            .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))
	            .build();
	        
	        builder=HttpClients.custom()
	        .setConnectionManager(poolHttp)
	        .setDefaultRequestConfig(defaultRequestConfig).evictIdleConnections(120,TimeUnit.SECONDS);
       
		}catch(Exception ex){
			logger.error("创建http pool 出现异常:"+ex.getMessage(), ex);
			throw new HttpException("创建http pool 出现异常:"+ex.getMessage(), ex);
		}
	}
	
	public static CloseableHttpClient getHttpClient() {	
		return builder.build();
	}
     **/
	private static HttpParams params;
	private static ThreadSafeClientConnManager conMgr;
	
	static{
		int soTime=SystemPropertyUtil.getInt(Constants.HTTP.SoTimeout_MS, 1000*30);//默认 30秒 （单位毫秒）
		int connTimeout=SystemPropertyUtil.getInt(Constants.HTTP.ConnectionTimeout_MS, 1000*30);//默认 30秒 （单位毫秒）
		long  maxConnectionLifetime=SystemPropertyUtil.getLong(Constants.HTTP.Max_Connection_Lifetime_MS, 1000*60);//默认 60秒 （单位毫秒）
		long idleTimeout=SystemPropertyUtil.getLong(Constants.HTTP.IdleConnection_TimeOut_MS, 1000*10);//默认 10秒 （单位毫秒）
		
		int defaultMaxPerRoute= SystemPropertyUtil.getInt(Constants.HTTP.DefaultMaxPerRoute,350);//默认 30秒 （单位毫秒）
		int maxTotal=SystemPropertyUtil.getInt(Constants.HTTP.MaxTotal, 500);
		
		if(logger.isInfoEnabled())
			logger.info("Initialize http pool connTimeout="+connTimeout+"ms,SoTimeout="+soTime+"ms,"
					+ " maxConnectionLifetime="+maxConnectionLifetime+"ms,idleTimeout="+idleTimeout+"ms"
					+ " defaultMaxPerRoute="+defaultMaxPerRoute+",maxTotal="+maxTotal);
		
		params = new BasicHttpParams();
		// 设置一些基本参数
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params, Constants.Encoding.CHARSET_UTF8);
		HttpProtocolParams.setUseExpectContinue(params, Boolean.TRUE);
		HttpProtocolParams.setHttpElementCharset(params, Constants.Encoding.CHARSET_UTF8);
		HttpProtocolParams.setUserAgent(params, "HttpComponents/1.1");
		// 超时设置		
		HttpConnectionParams.setConnectionTimeout(params, connTimeout);		
		HttpConnectionParams.setSoTimeout(params, soTime);//默认 30秒 （单位毫秒）
		
		// 设置我们的HttpClient支持HTTP和HTTPS两种模式
		SchemeRegistry schReg = new SchemeRegistry();
		schReg.register(new Scheme("http", 80, PlainSocketFactory
				.getSocketFactory()));
		schReg.register(new Scheme("https", 443, SSLSocketFactory
				.getSocketFactory()));
		
		// 使用线程安全的连接管理来创建HttpClient
		// 从连接池中取连接的超时时间 1分钟 1000*60 
		conMgr = new ThreadSafeClientConnManager(
				schReg, maxConnectionLifetime, TimeUnit.MILLISECONDS);
		conMgr.setDefaultMaxPerRoute(defaultMaxPerRoute);
		conMgr.setMaxTotal(maxTotal);
		conMgr.closeExpiredConnections();
		conMgr.closeIdleConnections(idleTimeout, TimeUnit.MILLISECONDS);	
		
	}
	
	public static HttpClient getHttpClient() {
		return new DefaultHttpClient(conMgr, params);
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
/**		
 *      com.cdo.util.http.HttpClient client=new com.cdo.util.http.HttpClient(url); 
		client.setHeaders(header);
		client.setTransMode(com.cdo.util.http.HttpClient.TRANSMODE_BODY);
		client.setBody(strContent);
 * 	
 */
		HttpClient client=null;
		HttpPost request=null;
		try {
			// 创建POST请求
			request = new HttpPost(url);
			if(header!=null){
				Set<String> headerKeys = header.keySet();
				for (String headKey : headerKeys) {
					request.addHeader(headKey, header.get(headKey));
				}
			}
			if(strContent!=null && strContent.trim().length()>0){
				// 编码参数
				StringEntity entity = new StringEntity(strContent,Constants.Encoding.CHARSET_UTF8);
				request.setEntity(entity);
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
			if(client!=null){try{client.getConnectionManager().closeExpiredConnections();}catch(Exception ex){}}
		}
		return null;
	}	
	
	
}