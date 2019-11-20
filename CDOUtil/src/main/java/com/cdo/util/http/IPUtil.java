package com.cdo.util.http;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.cdo.util.common.StringUtil;
/**
 * 
 * @author KenelLiu
 *
 */
public class IPUtil {
	private static Logger logger=Logger.getLogger(IPUtil.class);
		/**
		 * 
		 * @param request
		 * @return
		 */
	  public static String getIpAddr(HttpServletRequest request){
		   //只有通过了负载均衡或者HTTP代理才会添加该项
	        String ip = request.getHeader("x-forwarded-for");  
	        //apache服务器才有这个请求头
	        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("Proxy-Client-IP");  
	        } 
	        //weblogic有这个请求头
	        if(ip == null || ip.length() == 0  || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("WL-Proxy-Client-IP");  
	        } 
	        //nginx代理有这个请求头
	        if(ip == null || ip.length() == 0  || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("X-Real-IP"); 
	        }
	        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getRemoteAddr();  
	        }  
	        return ip;  
	   }

	  /**
	   * 
	   * @param request
	   * @param ip
	   * @return
	   */
	  public static String getIpAddr(HttpServletRequest request, String ip){
	    if (StringUtil.isNotEmpty(ip)) {
	     return ip;
	    }
	    return getIpAddr(request);
	   }
	  
	  
	  public static String getLocalIp(){
		  String serverIP = "";
		  InetAddress ip = null;
		  try {
		   //如果是Windows操作系统
		   if(isWindowsOS()){
			   ip = InetAddress.getLocalHost();
		   }else{ //如果是Linux操作系统
		    boolean bFindIP = false;
		    Enumeration<NetworkInterface> netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface.getNetworkInterfaces();
		    while(netInterfaces.hasMoreElements()) {
			     if(bFindIP)
			    	 break;		     
			     NetworkInterface ni =netInterfaces.nextElement();
			     //----------特定情况，可以考虑用ni.getName判断
			     //遍历所有ip
			     Enumeration<InetAddress> ips = ni.getInetAddresses();
			     while (ips.hasMoreElements()) {
				      ip = (InetAddress) ips.nextElement();
				      if(ip.isSiteLocalAddress() 
				    		  && !ip.isLoopbackAddress()   //127.开头的都是lookback地址
				    		  && ip.getHostAddress().indexOf(":")==-1){
				          bFindIP = true;
				          break; 
				         }
			     }
		     }
		    }
		  }catch (Exception e) {		   
		   logger.error(e.getMessage(), e);
		  }
		  if(null != ip){
			  serverIP = ip.getHostAddress();
		  }
		  if(logger.isInfoEnabled())
			  logger.info("local server IP="+serverIP);
		  return serverIP;
	  }
	  
	  private static boolean isWindowsOS(){

		    boolean isWindowsOS = false;

		    String osName = System.getProperty("os.name");

		    if(osName.toLowerCase().indexOf("windows")>-1){

		     isWindowsOS = true;

		    }

		    return isWindowsOS;
		  }
	  	 
	  
}
