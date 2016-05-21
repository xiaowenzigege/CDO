package com.cdo.util.resource;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import com.cdo.util.resource.BundleConfig;

/**
 * 
 * @author KenelLiu
 *
 */
public class GlobalResource{
  public static BundleConfig resource = null;
  public static BundleConfig cdoConfig=null;
  private static Logger logger = Logger.getLogger(GlobalResource.class);

  static {
    try { 
    	resource = new BundleConfig("resource.*?.properties");     	
    } catch (Exception e) {
      if (logger.isInfoEnabled())
        logger.info(e.getMessage());
    }
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