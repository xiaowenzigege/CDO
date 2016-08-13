package com.cdo.business.cache.client;

import org.apache.log4j.Logger;

import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.base.Utility;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.framework.CacheHandler;
import com.cdoframework.cdolib.framework.FilterHandler;
import com.cdoframework.cdolib.service.framework.CacheManager;
import com.cdoframework.cdolib.service.framework.FilterManager;
import com.cdoframework.cdolib.service.framework.xsd.FilterConfig;
import com.cdoframework.cdolib.service.framework.xsd.Framework;
import com.cdoframework.cdolib.service.framework.xsd.MemCacheServerGroup;



/**
 * 在 action 层 调用配置文件初始化后
 * 在 action 层 使用缓存 ，这儿仅是获取cache
 * 
 * service端  使用框架 调用缓存即可
 * @author KenelLiu
 *
 */
public class ClientCache
{
  private static Logger logger = Logger.getLogger(ClientCache.class);

  private CacheManager cacheManager = new CacheManager();

  private FilterManager filterManager = new FilterManager();

  private static ClientCache clientCache = null;

  private void init()
  {
    logger.info("++++++++++++++++++++初始化BusinessClientCache+++++++++++++++++++++++");
    String strFrameworkResourceXMLFile = "frameworkResource.xml";
    String strFlitersConfigXMLFile = "filtersConfig.xml";
    if (logger.isInfoEnabled()) {
      logger.info("parsing xml file: " + strFrameworkResourceXMLFile);
      logger.info("parsing xml file: " + strFlitersConfigXMLFile);
    }

    StringBuilder strFrameworkXML = new StringBuilder();
    String strFrameworkResourceXML = Utility.readTextResource(strFrameworkResourceXMLFile, "utf-8");
    String strFlitersConfigXML = Utility.readTextResource(strFlitersConfigXMLFile, "utf-8");

    if (strFrameworkResourceXML == null)
    {
      return;
    }

    if ((strFlitersConfigXML != null) && (strFlitersConfigXML.indexOf("<FilterConfig") != -1))
    {
      strFlitersConfigXML = strFlitersConfigXML.substring(strFlitersConfigXML.indexOf("<FilterConfig"), strFlitersConfigXML.lastIndexOf("</FiltersConfig>"));
    }
    else
    {
      strFlitersConfigXML = "";
    }

    if (strFlitersConfigXML != null)
    {
      strFrameworkResourceXML = strFrameworkResourceXML.replaceAll("FrameworkResource", "Framework");
      strFrameworkXML.append(strFrameworkResourceXML.replaceAll("</Framework>", "")).append(strFlitersConfigXML).append("</Framework>");
    }

    Framework framework = null;
    try
    {
      framework = Framework.fromXML(strFrameworkXML.toString());
    }
    catch (Exception e)
    {
      logger.error("can not parse " + strFrameworkResourceXMLFile);
      logger.error("can not parse " + strFlitersConfigXMLFile);
      return;
    }

    if (framework == null) {
      return;
    }
    MemCacheServerGroup[] memCacheServerGroups = framework.getMemCacheServerGroup();

    Return ret = this.cacheManager.initMemCacheServer(memCacheServerGroups);
    if (ret.getCode() != 0) {
      logger.error(ret.getText());
    }

    CacheHandler.getInstance().setCacher(this.cacheManager);

    this.filterManager.setCacheManager(this.cacheManager);

    int nFilterConfigCount = framework.getFilterConfigCount();
    try
    {
      for (int i = 0; i < nFilterConfigCount; i++)
      {
        FilterConfig config = framework.getFilterConfig(i);
        if (logger.isInfoEnabled()) {
          logger.info("parse event config xml file :" + config.getResource());
        }
        String strFilterConfigXML = Utility.readTextResource(config.getResource(), "utf-8");
        if (strFilterConfigXML == null)
        {
          throw new Exception("Invalid resource " + config.getResource());
        }
        ret = this.filterManager.append(strFilterConfigXML);
        if (ret.getCode() != 0) {
          logger.error(ret.getText());
        }
      }
    }
    catch (Exception e)
    {
      logger.error("init:" + e.getMessage(), e);
    }
    FilterHandler.getInstance().setFilter(this.filterManager);
  }

  public static ClientCache getInstance() {
    if (clientCache == null) {
      synchronized (ClientCache.class) {
        clientCache = new ClientCache();
        clientCache.init();
      }
    }
    return clientCache;
  }

  public Return handlePreTransCache(CDO cdoRequest, CDO cdoResponse) {
    String strServiceName = cdoRequest.getStringValue("strServiceName");
    String strTransName = cdoRequest.getStringValue("strTransName");
    return FilterHandler.getInstance().handlePreTransCache(strServiceName, strTransName, cdoRequest, cdoResponse);
  }
}