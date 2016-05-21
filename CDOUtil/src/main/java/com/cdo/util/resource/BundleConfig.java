package com.cdo.util.resource;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.cdoframework.cdolib.base.PropertiesExt;
import com.cdoframework.cdolib.base.Resources;

/**
 * 
 * @author KenelLiu
 *
 */
public class BundleConfig{

  private PropertiesExt prop = null;

  private Logger logger = Logger.getLogger(getClass());

  /**
   * 
   * @param strConfig
   * @param isClassPath  true 表示从class路径下读取配置文件,false 表示从指定路径strConfig 读取
   * @throws IOException
   */
  public BundleConfig(String strConfig,boolean isClassPath) throws IOException{
	  if(logger.isInfoEnabled())
		  logger.info(strConfig+",scan classpath path?"+isClassPath);		  
	  this.prop = new PropertiesExt();
	  if(isClassPath){
	      for (String file : listResource(strConfig)) {
	        this.prop.loadConfigFromResource(file);
	      } 
	  }else{
		  this.prop.loadConfigFromResource(strConfig, false);
	  }
  }
  
  public BundleConfig(String strConfig) throws Exception {
	  this(strConfig,true);    
  }

  public String getString(String key)
  {
    return getString(key, null);
  }

  public String getString(String key, String defaultValue)
  {
    String stringValue = null;
    if (this.prop != null)
    {
      stringValue = this.prop.getParameter(key, defaultValue);
    }
    return stringValue;
  }

  public int getInt(String key)
  {
    return getInt(key, -1);
  }

  public int getInt(String key, int defaultValue)
  {
    int intValue = -1;
    String stringValue = getString(key);

    if (stringValue != null) {
      try {
        intValue = Integer.parseInt(stringValue);
      }
      catch (NumberFormatException ex) {
        intValue = defaultValue;
      }
    }
    else {
      intValue = defaultValue;
    }
    return intValue;
  }

  public long getLong(String key)
  {
    return getLong(key, -1L);
  }

  public long getLong(String key, long defaultValue)
  {
    long longValue = -1L;
    String stringValue = getString(key);

    if (stringValue != null) {
      try {
        longValue = Long.parseLong(stringValue);
      }
      catch (NumberFormatException ex) {
        longValue = defaultValue;
      }
    }
    else {
      longValue = defaultValue;
    }
    return longValue;
  }

  public boolean getBoolean(String key) {
    return getBoolean(key, false);
  }

  public boolean getBoolean(String key, boolean defaultValue)
  {
    String stringValue = getString(key);
    if (stringValue != null) {
      return "TRUE".equalsIgnoreCase(stringValue);
    }
    return defaultValue;
  }

  String[] listResource(String fileReg)throws IOException{
    String currentPath = Resources.getResourceURL("").getFile().replaceAll("%20", " ");
    String[] files = new File(currentPath).list(new ResourcesFilter(fileReg));
    return files;
  }

	  
  private static class ResourcesFilter
    implements FilenameFilter
  {
    private String nameReg = null;

    public ResourcesFilter(String nameReg) {
      this.nameReg = nameReg;
    }

    public boolean accept(File dir, String name)
    {
      return Pattern.matches(this.nameReg, name);
    }
  }
  
}