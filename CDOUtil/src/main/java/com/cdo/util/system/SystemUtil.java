package com.cdo.util.system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.log4j.Logger;

public class SystemUtil {
	private static Logger logger=Logger.getLogger(SystemUtil.class);
	
	
	public static String getSystemBit(){
		String system=System.getProperty("os.name").toLowerCase();
		if(logger.isInfoEnabled())
			logger.info("os.name="+system);
		if(system.startsWith("windows")){
			String arch = System.getenv("PROCESSOR_ARCHITECTURE");
			String wow64Arch = System.getenv("PROCESSOR_ARCHITEW6432");
			if(arch.endsWith("64")|| 
					(wow64Arch != null && wow64Arch.endsWith("64"))){
				return "64";
			}else{
				return "32";
			}
		}else if(system.startsWith("linux")){
		try {
				Process process = Runtime.getRuntime().exec("getconf LONG_BIT");
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
				String bit=bufferedReader.readLine();
				if(bit.contains("64")){
					return "64";
				}else{
					return "32";
				}
			} catch (IOException e) {
				return "Unknown";
			}
		}
		return "Unknown";
	}
	
	public static boolean isLinux(){
		String system=System.getProperty("os.name").toLowerCase();
		if(system.startsWith("linux")){
			return true;
		}else{
			return false;
		}
	}
	public static String getLocalServerIP(){
		String SERVER_IP="";
		List<String> list=new ArrayList<String>();
		try{
			Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
		
			InetAddress ip = null;
			while (allNetInterfaces.hasMoreElements()){
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();			
				Enumeration addresses = netInterface.getInetAddresses();
				while (addresses.hasMoreElements()){
					ip = (InetAddress) addresses.nextElement();
					if (ip != null && ip instanceof Inet4Address ){						
						list.add(ip.getHostAddress());
					} 
				}
			}
		}catch(Exception ex){
			logger.error(ex.getMessage(), ex);
		}
		if(list.size()==1)
			return list.get(0);
		else{
			for(int i=0;i<list.size();i++){
				SERVER_IP=list.get(i);
				if(!SERVER_IP.startsWith("127.0.0.1"))					
					break;
			}
		}		
		return SERVER_IP;
	   }  	
}
