package com.cdo.util.page;

import org.apache.log4j.Logger;

import com.cdo.util.constants.Constants;
import com.cdoframework.cdolib.data.cdo.CDO;

/**
 * 
 * @author KenelLiu
 *
 */
public class PageUtil {
		private static Logger logger=Logger.getLogger(PageUtil.class);
		public static int getPageIndex(CDO cdoRequest) {
			return getPageIndex(cdoRequest, Constants.Page.PAGE_INDEX);
		  }
	
		public static int getPageIndex(CDO cdoRequest, String key){
		    int nPageIndex = 1;
		    try {
		      nPageIndex = cdoRequest.getIntegerValue(key);
		      if (nPageIndex <= 0)
		        nPageIndex = 1;
		    }catch (Exception ex){
	           logger.warn("Set default  "+key+" ="+nPageIndex);
		    }
		    return nPageIndex;
		  }

		  public static int getPageSize(CDO cdoRequest) {
		    return getPageSize(cdoRequest, Constants.Page.PAGE_SIZE);
		  }

		  public static int getPageSize(CDO cdoRequest, String key) {
		    int nPageSize = Constants.Page.PAGE_SIZE_MIN;
		    try {
		      nPageSize = cdoRequest.getIntegerValue(key);
		      if ((nPageSize <= 0) || (nPageSize > Constants.Page.PAGE_SIZE_MAX))
		    	  nPageSize = Constants.Page.PAGE_SIZE_MIN;
		    }catch (Exception ex){
		    	 logger.warn("Set default "+key+" ="+nPageSize);
		    }
		    return nPageSize;
		  }	
}
