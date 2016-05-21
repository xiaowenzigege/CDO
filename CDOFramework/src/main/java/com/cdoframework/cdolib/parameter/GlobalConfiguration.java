package com.cdoframework.cdolib.parameter;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class GlobalConfiguration {

	private final Logger logger = Logger.getLogger(GlobalConfiguration.class);
	private static GlobalConfiguration instance;
	private int memcacheFailedRetryDefaultTime = 3;
	private int memcacheFailedRetryDefaultSleepTime = 1000;
	private int mainThreadPoolSize = 5000;
	private int repairThreadPoolSize = 500;
	
	public static GlobalConfiguration getInstance() {
		if(instance == null){
			synchronized (GlobalConfiguration.class) {
				if(instance == null)
					instance = new GlobalConfiguration();
			}
		}
		return instance;
	}

	private GlobalConfiguration(){
		hmParameterMap = new HashMap<String, String>();
	}
	
	private Map<String, String> hmParameterMap;

	public void setHmParameterMap(final Map<String, String> hmParameterMap) {
		this.hmParameterMap =  hmParameterMap;
	}
	
	public Map<String, String> getHmParameterMap(){
		return this.hmParameterMap;
	}
	
	public boolean isMemcacheFailedRetry(){
		try{
			if(hmParameterMap == null || hmParameterMap.get("memcacheFailedRetryDefaultTime") == null){
				logger.warn("未能从配置文件读取到数据同步默认重试次数参数：memcacheFailedRetryDefaultTime,不采取数据同步策略！");
				return false;
			}
			return !"0".equalsIgnoreCase(this.hmParameterMap.get("memcacheFailedRetryDefaultTime"));
		}catch(Exception e){
			logger.warn("查询数据同步是否继续发生异常："+e.getMessage()+",使用默认值false");
			return false;
		}
	}

	public int getMemcacheFailedRetryDefaultTime() {
		try{
			if(hmParameterMap == null || hmParameterMap.get("memcacheFailedRetryDefaultTime") == null){
				logger.warn("未能从配置文件读取到数据同步默认重试次数参数：memcacheFailedRetryDefaultTime,使用默认值0");
				return memcacheFailedRetryDefaultTime = 0;
			}
			memcacheFailedRetryDefaultTime = Integer.parseInt(hmParameterMap.get("memcacheFailedRetryDefaultTime"));
		}catch(Exception e){
			logger.warn("解析参数:读取到数据同步默认重试次数memcacheFailedRetryDefaultTime发生异常："+e.getMessage()+",请检查配置情况！使用默认值0.");
			memcacheFailedRetryDefaultTime = 0;
		}
		return memcacheFailedRetryDefaultTime;
	}

	public int getMemcacheFailedRetryDefaultSleepTime() {
		try{
			if(hmParameterMap == null 
					|| hmParameterMap.get("memcacheFailedRetryDefaultSleepTime") == null){
				logger.warn("未能从配置文件读取到数据同步重试sleep时间参数:memcacheFailedRetryDefaultSleepTime,使用默认值10");
				return memcacheFailedRetryDefaultSleepTime = 10;
			}
			memcacheFailedRetryDefaultSleepTime = Integer.parseInt(hmParameterMap.get("memcacheFailedRetryDefaultSleepTime"));
		}catch(Exception e){
			logger.warn("解析参数:数据同步重试sleep时间memcacheFailedRetryDefaultSleepTime发生异常："+e.getMessage()+",请检查配置情况！使用默认值10.");
			memcacheFailedRetryDefaultSleepTime = 10;
		}
		return memcacheFailedRetryDefaultSleepTime;
	}
	
	public int getMainThreadPoolSize(){
		try{
			if(hmParameterMap == null || hmParameterMap.get("mainThreadPoolSize") == null){
				logger.warn("未能从配置文件读取到数据同步主线程池大小参数:mainThreadPoolSize,使用默认值5000");
				return mainThreadPoolSize = 5000;
			}
			mainThreadPoolSize = Integer.parseInt(hmParameterMap.get("mainThreadPoolSize"));
		}catch(Exception e){
			logger.warn("解析参数:数据同步主线程池大小 mainThreadPoolSize 发生异常："+e.getMessage()+",请检查配置情况！使用默认值5000.");
			mainThreadPoolSize = 5000;
		}
		return mainThreadPoolSize;
	}
	
	public int getRepairThreadPoolSize(){
		try{
			if(hmParameterMap == null || hmParameterMap.get("repairThreadPoolSize") == null){
				logger.warn("未能从配置文件读取到数据同步重试线程池大小：repairThreadPoolSize 使用默认值500");
				return repairThreadPoolSize = 500;
			}
			repairThreadPoolSize = Integer.parseInt(hmParameterMap.get("repairThreadPoolSize"));
		}catch(Exception e){
			logger.warn("解析参数:数据同步重试线程池大小 repairThreadPoolSize 发生异常："+e.getMessage()+",请检查配置情况！使用默认值500.");
			repairThreadPoolSize = 500;
		}
		return repairThreadPoolSize;
	}
}
