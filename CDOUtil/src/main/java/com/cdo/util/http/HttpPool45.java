package com.cdo.util.http;
import java.nio.charset.CodingErrorAction;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.Consts;
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
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.log4j.Logger;

import com.cdo.util.constants.Constants;
import com.cdo.util.exception.HttpException;

import io.netty.util.internal.SystemPropertyUtil;
public class HttpPool45 extends HttpPool{

	private static final Logger logger=Logger.getLogger(HttpUtil.class); 
	
    private static final String DEFAULT_ACCEPT = "text/html,text/xml,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"; 
    private static final String DEFAULT_ACCEPT_CHARSET = "utf-8,ISO-8859-1;q=0.7,*;q=0.7"; 
    private static final String DEFAULT_ACCEPT_ENCODING = "x-gzip, gzip"; 
	
    private static  PoolingHttpClientConnectionManager poolHttp;
	private static  RequestConfig defaultRequestConfig;
    private static int retryTime=1;

	static {
		try{
			int socketTimeout=(SystemPropertyUtil.getInt(Constants.HTTP.SocketTimeout_MS,30))*1000;//默认 30秒 （单位毫秒）		
			int connectTimeout=(SystemPropertyUtil.getInt(Constants.HTTP.ConnectionTimeout_MS,30))*1000;//默认 30秒 （单位毫秒）
			int requestTimeout=(SystemPropertyUtil.getInt(Constants.HTTP.RequestTimeout_MS,30))*1000;//默认 30秒 （单位毫秒）
			
			int defaultMaxPerRoute= SystemPropertyUtil.getInt(Constants.HTTP.DefaultMaxPerRoute,100);//默认100
			int maxTotal=SystemPropertyUtil.getInt(Constants.HTTP.MaxTotal, 600);//默认600
			int retryTime=SystemPropertyUtil.getInt(Constants.HTTP.RetryTime, 1);//默认1
			
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
			poolHttp.setMaxTotal(maxTotal);
			poolHttp.setDefaultMaxPerRoute(defaultMaxPerRoute);
			
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
	            .setSocketTimeout(socketTimeout)
	            .setConnectTimeout(connectTimeout)
	            .setConnectionRequestTimeout(requestTimeout)
	            .setExpectContinueEnabled(Boolean.FALSE)
	            .build();
	//            .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
	//            .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))	            
//			   getHttpClient();
			  if(logger.isInfoEnabled())
				  logger.info("Initialize http4.5 pool,socketTimeout="+(socketTimeout/1000)+"s,connectTimeout="+(connectTimeout/1000)
						  +"s,requestTimeout="+(requestTimeout/1000)+" s,maxTotal="+maxTotal
						  +",defaultMaxPerRoute="+defaultMaxPerRoute+",retryTime="+retryTime);
       
		}catch(Exception ex){
			logger.error("创建http4.5 pool 出现异常:"+ex.getMessage(), ex);
			throw new HttpException("创建http pool 出现异常:"+ex.getMessage(), ex);
		}
	}
	
	public HttpPool45() {		
	
	}
	
	public  CloseableHttpClient getHttpClient() {	
        return HttpClients.custom()
        .setConnectionManager(poolHttp)
        .setDefaultRequestConfig(defaultRequestConfig).setRetryHandler(new DefaultHttpRequestRetryHandler(retryTime, false)).build();
	}
	
    
	
}
