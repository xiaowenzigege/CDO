package com.cdo.util.resource;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import com.cdo.util.resource.BundleConfig;
import com.cdoframework.cdolib.base.Return;

/**
 * 
 * @author KenelLiu
 *
 */
public class GlobalResource{
  public static BundleConfig resource = null;
  public static BundleConfig cdoConfig=null;
  private static Logger logger = Logger.getLogger(GlobalResource.class);
  private static String configPath;
  static {
    try { 
    	resource = new BundleConfig("resource.*?.properties");     	
    } catch (Exception e) {
      if (logger.isInfoEnabled())
        logger.info(e.getMessage());
    }
  }
  
  public static void bundleInitCDOEnv() throws IOException{
	  bundleInitCDOEnv("CDO_CONFIG_FILE");
  }
  
  public static void bundleInitCDOEnv(String varPath) throws IOException{
	  	configPath=System.getProperty(varPath);	//文件全路径
		if(configPath==null || configPath.equals(""))
		{
			logger.error("vm启动参数  未正确设置:"+varPath+"="+configPath);
			return;
		}
		StringBuilder sb=new StringBuilder();
		char ch;
		for(int i=0;i<configPath.length();i++){
			ch=configPath.charAt(i);
			if(ch=='{'){
				if(i>0 && configPath.charAt(i-1)=='$'){
					sb=new StringBuilder();
				}
			}else if(ch=='}'){
				if(sb!=null){
					String value=System.getProperty(sb.toString());
					if(value!=null && !value.equals(""))
						configPath=configPath.replace("${"+sb.toString()+"}",value);
				}					
				sb=null;
			}else{
				if(sb!=null)
					sb.append(ch);
			}
		}
		GlobalResource.bundleLocalCDOEnv(configPath);
  }
  
  public static String getCDOEnvConfigPath(){
		return configPath;
  }
  /**
   *  vm 启动时，获取 cdo框架所需文件所在的位置，由启动时设定其文件位置
   * @param strConfig
   * @throws IOException
   */
  public static void bundleLocalCDOEnv(String strConfig) throws IOException{
	  if(GlobalResource.cdoConfig!=null)
		  return; 
	  GlobalResource.cdoConfig=new BundleConfig(strConfig, false);		  
  }
  
  /**
   * 
   * @param key   调用反射指定的方法
   * @param strPath  资源库的路径
   * @param isClassPath 是否是class目录
   * @throws IllegalArgumentException
   * @throws IllegalAccessException
   * @throws InvocationTargetException
   * @throws InstantiationException
   * @throws Exception
   */
  public static void bundleConfig(String key,String strPath,boolean isClassPath) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException, Exception{
	  Method[] methods=GlobalResource.class.getMethods();
	  String method="set"+key.substring(0,1).toUpperCase()+key.substring(1);
	  for(int i=0;i<methods.length;i++){
		  if(methods[i].getName().equals(method)){			 
			  methods[i].invoke(GlobalResource.class.newInstance(), new Object[]{new BundleConfig(strPath,isClassPath)});
			  break;
		  }
	  }
 }   
}