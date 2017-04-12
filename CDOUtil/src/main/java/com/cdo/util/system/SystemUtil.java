package com.cdo.util.system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
}
