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
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import org.apache.log4j.Logger;

import com.cdo.util.bean.Response;
import com.cdo.util.constants.Constants;
import com.cdo.util.exception.HttpException;
import com.cdo.util.exception.ResponseException;
import com.cdo.util.serial.Serializable;
import com.cdoframework.cdolib.data.cdo.CDO;
import io.netty.util.internal.SystemPropertyUtil;
public class HttpPool42 extends HttpPool{
	private static final Logger logger=Logger.getLogger(HttpPool42.class); 
	private static HttpParams params;
	private static ThreadSafeClientConnManager conMgr;
	
	static{
	  try{	
			int socketTimeout=(SystemPropertyUtil.getInt(Constants.HTTP.SocketTimeout_MS,30))*1000;//默认 30秒 （单位毫秒）		
			int connTimeout=(SystemPropertyUtil.getInt(Constants.HTTP.ConnectionTimeout_MS,30))*1000;//默认 30秒 （单位毫秒）
			int requestTimeout=(SystemPropertyUtil.getInt(Constants.HTTP.RequestTimeout_MS,30))*1000;//默认 30秒 （单位毫秒）
			
			int defaultMaxPerRoute= SystemPropertyUtil.getInt(Constants.HTTP.DefaultMaxPerRoute,100);//默认100
			int maxTotal=SystemPropertyUtil.getInt(Constants.HTTP.MaxTotal, 600);//默认600
			int retryTime=SystemPropertyUtil.getInt(Constants.HTTP.RetryTime, 1);//默认1
			
			long  maxConnectionLifeTime=(SystemPropertyUtil.getLong(Constants.HTTP.Max_Connection_Lifetime_MS,60))*1000;//默认 60秒 （单位毫秒）
			long IdleConnectionTimeout=(SystemPropertyUtil.getLong(Constants.HTTP.IdleConnection_TimeOut_MS,10))*1000;//默认 10秒 （单位毫秒）
			
			params = new BasicHttpParams();
			// 设置一些基本参数
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, Constants.Encoding.CHARSET_UTF8);
			HttpProtocolParams.setUseExpectContinue(params, Boolean.TRUE);
			HttpProtocolParams.setHttpElementCharset(params, Constants.Encoding.CHARSET_UTF8);
			HttpProtocolParams.setUserAgent(params, "HttpComponents/1.1");
			// 超时设置		
			HttpConnectionParams.setConnectionTimeout(params, connTimeout);		
			HttpConnectionParams.setSoTimeout(params, socketTimeout);//默认 30秒 （单位毫秒）
			
			// 设置我们的HttpClient支持HTTP和HTTPS两种模式
			SchemeRegistry schReg = new SchemeRegistry();
			schReg.register(new Scheme("http", 80, PlainSocketFactory
					.getSocketFactory()));
			schReg.register(new Scheme("https", 443, SSLSocketFactory
					.getSocketFactory()));
			
			// 使用线程安全的连接管理来创建HttpClient
			// 从连接池中取连接的超时时间 1分钟 1000*60 
			conMgr = new ThreadSafeClientConnManager(
					schReg, maxConnectionLifeTime, TimeUnit.MILLISECONDS);
			conMgr.setDefaultMaxPerRoute(defaultMaxPerRoute);
			conMgr.setMaxTotal(maxTotal);
			conMgr.closeExpiredConnections();
			conMgr.closeIdleConnections(IdleConnectionTimeout, TimeUnit.MILLISECONDS);
			
			if(logger.isInfoEnabled())
				logger.info("Initialize http4.2 pool,socketTimeout="+(socketTimeout/1000)+"s,connectTimeout="+(connTimeout/1000)+"s,"
						+ " maxConnectionLifeTime="+(maxConnectionLifeTime/1000)+"s,IdleConnectionTimeout="+(IdleConnectionTimeout/1000)+"s"
						+ " maxTotal="+maxTotal+",defaultMaxPerRoute="+defaultMaxPerRoute);
		}catch(Exception ex){
			logger.error("创建http4.2 pool 出现异常:"+ex.getMessage(), ex);
			throw new HttpException("创建http pool 出现异常:"+ex.getMessage(), ex);
		}
	}
	
	public HttpPool42() {
		
	}
	
	public HttpClient getHttpClient() {
		return new DefaultHttpClient(conMgr, params);
	}	
}
