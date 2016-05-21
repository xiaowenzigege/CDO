package com.cdo.util.business;

import org.apache.log4j.Logger;

import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.base.Utility;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.framework.CacheHandler;
import com.cdoframework.cdolib.framework.FilterHandler;
import com.cdoframework.cdolib.service.framework.CacheManager;
import com.cdoframework.cdolib.service.framework.FilterManager;
import com.cdoframework.cdolib.service.framework.schema.FilterConfig;
import com.cdoframework.cdolib.service.framework.schema.Framework;
import com.cdoframework.cdolib.service.framework.schema.MemCacheServerGroup;




public class BusinessClientCache
{
  private static Logger logger = Logger.getLogger(BusinessClientCache.class);

  private CacheManager cacheManager = new CacheManager();

  private FilterManager filterManager = new FilterManager();

  private static BusinessClientCache clientCache = null;

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

  public static BusinessClientCache getInstance() {
    if (clientCache == null) {
      synchronized (BusinessClientCache.class) {
        clientCache = new BusinessClientCache();
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