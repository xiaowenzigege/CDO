package com.cdoframework.cdolib.service.framework;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.framework.IFilter;
import com.cdoframework.cdolib.framework.ITaskExecutor;
import com.cdoframework.cdolib.framework.Task;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.BusinessLog;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.CreteriasType;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.Event;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.Filter;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.FilterDefine;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.For;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.ForItem;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.LoadCache;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.LoadCacheChoice;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.LoadCacheChoiceItem;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.PostEvent;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.PostEventGroup;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.PostLoadCache;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.PostPushCache;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.PostRemoveCache;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.PostRemoveURLCache;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.PostTransEventTypeItem;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.PostTransaction;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.PreEvent;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.PreEventGroup;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.PreLoadCache;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.PrePushCache;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.PreRemoveCache;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.PreRemoveURLCache;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.PreTransEventTypeItem;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.PreTransaction;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.PushCache;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveCache;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveURLCache;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.RequestKey;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.ReturnCode;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.TransCache;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.Transaction;
import com.cdoframework.cdolib.service.framework.transfilter.xsd.types.RequestKeyScopeType;
import com.cdoframework.cdolib.servicebus.EventTask;
import com.cdoframework.cdolib.servicebus.IServiceBus;
import com.cdoframework.cdolib.servicebus.ITransService;

/**
 * @author Aaron
 */
public class FilterManager implements IFilter,ITaskExecutor
{

	//内部类,所有内部类在此声明----------------------------------------------------------------------------------

	private static final int THIRTY_DAYS = 2592000;//60*60*24*30

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------
	static Logger logger = Logger.getLogger(FilterManager.class);
	
	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------
	private HashMap<String,HashMap<String,Filter>> hmFilter;
	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------
	private CacheManager cacheManager;
	public void setCacheManager(CacheManager cacheManager)
	{
		this.cacheManager = cacheManager;
	}
	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------
	private IServiceBus serviceBus;
	public void setServiceBus(IServiceBus serviceBus)
	{
		this.serviceBus = serviceBus;
	}
	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	//私有方法 所有仅在本类或派生类中使用的函数在此定义为private方法-------------------------------------------
	
	private Filter getFilter(String strServiceName,String strTransName)
	{
		HashMap<String,Filter>  hmTransFilter = this.hmFilter.get(strServiceName);
		if(hmTransFilter==null)
		{
			return null;
		}
		return hmTransFilter.get(strTransName);
	}
	private void parseFilter(Filter filter,FilterDefine filterDefine)
	{		
		TransCache transCache = filter.getTransCache();
		if(transCache!=null)
		{
			transCache.setFilter(filter);
		}
		PreEvent preEvent = filter.getPreEvent();
		if(preEvent!=null)
		{
			parsePreEvent(filter,preEvent);
		}
		PostEvent postEvent = filter.getPostEvent();
		if(postEvent!=null)
		{
			parsePostEvent(filter,postEvent);
		}
	}
	
	private void parseFor(Filter filter,For for1)
	{
		if(for1!=null)
		{
			ForItem[] forItems = for1.getForItem();
			if(forItems!=null && forItems.length>0)
			{
				for(ForItem forItem:forItems)
				{
					PushCache pushCache = forItem.getPushCache();
					if(pushCache!=null)
					{
						pushCache.setFilter(filter);
					}
					RemoveCache removeCache = forItem.getRemoveCache();
					if(removeCache!=null)
					{
						removeCache.setFilter(filter);
					}
					RemoveURLCache removeURLCache = forItem.getRemoveURLCache();
					if(removeURLCache != null)
					{
						removeURLCache.setFilter(filter);
					}
				}
			}
		}
	}
	
	private void parsePreEvent(Filter filter,PreEvent preEvent)
	{
		PreTransEventTypeItem[] items = preEvent.getPreTransEventTypeItem();
		if(items!=null && items.length>0)
		{
			for(PreTransEventTypeItem item:items)
			{
				PrePushCache prePushCache = item.getPrePushCache();
				if(prePushCache!=null)
				{
					PushCache[] pcs = prePushCache.getPushCache();
					if(pcs!=null && pcs.length>0)
					{
						for(PushCache pc:pcs)
						{
							pc.setFilter(filter);
						}
					}
				}
				BusinessLog businessLog = item.getBusinessLog();
				if(businessLog!=null)
				{
					businessLog.setFilter(filter);
				}
				Event event = item.getEvent();
				if(event!=null)
				{
					event.setFilter(filter);
				}
				PreLoadCache preLoadCache = item.getPreLoadCache();
				if(preLoadCache!=null)
				{
					LoadCache[] loadCaches = preLoadCache.getLoadCache();
					if(loadCaches!=null && loadCaches.length>0)
					{
						for(LoadCache loadCache:loadCaches)
						{
							loadCache.setFilter(filter);
							loadCache.getTransaction().setFilter(filter);
//							LoadCacheChoice[] loadCacheChoices = loadCache.getLoadCacheChoice();
							LoadCacheChoice loadCacheChoices = loadCache.getLoadCacheChoice();
							if(loadCacheChoices !=null )
							{
//								for(LoadCacheChoice loadCacheChoice:loadCacheChoices)
//								{
									
//									LoadCacheChoiceItem[]  loadCacheChoiceItems = loadCacheChoice.getLoadCacheChoiceItem();
								    LoadCacheChoiceItem[]  loadCacheChoiceItems = loadCacheChoices.getLoadCacheChoiceItem();
									if(loadCacheChoiceItems!=null && loadCacheChoiceItems.length>0)
									{
										for(LoadCacheChoiceItem loadCacheChoiceItem:loadCacheChoiceItems)
										{
											PushCache pushCache = loadCacheChoiceItem.getPushCache();
											if(pushCache!=null)
											{
												pushCache.setFilter(filter);
											}
											RemoveCache removeCache = loadCacheChoiceItem.getRemoveCache();
											if(removeCache!=null)
											{
												removeCache.setFilter(filter);
											}
											RemoveURLCache removeURLCache = loadCacheChoiceItem.getRemoveURLCache();
											if(removeURLCache!=null)
											{
												removeURLCache.setFilter(filter);
											}
											For for1 = loadCacheChoiceItem.getFor();
											parseFor(filter,for1);
										}
									}
//								}
							}
						}
					}
				}
				PreEventGroup preEventGroup = item.getPreEventGroup();
				if(preEventGroup!=null)
				{
					For[] fors= preEventGroup.getFor();
					if(fors!=null && fors.length>0)
					{
						for(For for1:fors)
						{
							parseFor(filter,for1);
						}
					}
				}
				PreRemoveURLCache preRemoveURLCache = item.getPreRemoveURLCache();
				if(preRemoveURLCache!=null)
				{
					RemoveURLCache[] removeURLCaches =preRemoveURLCache.getRemoveURLCache();
					if(removeURLCaches!=null && removeURLCaches.length>0)
					{
						for(RemoveURLCache removeURLCache:removeURLCaches)
						{
							removeURLCache.setFilter(filter);
						}
					}
				}
				PreTransaction preTransaction = item.getPreTransaction();
				if(preTransaction!=null)
				{
					Transaction[] transactions = preTransaction.getTransaction();
					if(transactions!=null && transactions.length>0)
					{
						for(Transaction transacton:transactions)
						{
							transacton.setFilter(filter);
						}
					}
				}
				PreRemoveCache preRemoveCache = item.getPreRemoveCache();
				if(preRemoveCache!=null)
				{
					RemoveCache[] removeCaches = preRemoveCache.getRemoveCache();
					if(removeCaches!=null && removeCaches.length>0)
					{
						for(RemoveCache removeCache:removeCaches)
						{
							removeCache.setFilter(filter);
						}
					}
				}
			}
		}
	}
	private void parsePostEvent(Filter filter, PostEvent postEvent)
	{
		PostTransEventTypeItem[] items = postEvent.getPostTransEventTypeItem();
		if(items!=null && items.length>0)
		{
			for(PostTransEventTypeItem item:items)
			{
				PostPushCache postPushCache = item.getPostPushCache();
				if(postPushCache!=null)
				{
					PushCache[] pcs = postPushCache.getPushCache();
					if(pcs!=null && pcs.length>0)
					{
						for(PushCache pc:pcs)
						{
							pc.setFilter(filter);
						}
					}
				}
				BusinessLog businessLog = item.getBusinessLog();
				if(businessLog!=null)
				{
					businessLog.setFilter(filter);
				}
				Event event = item.getEvent();
				if(event!=null)
				{
					event.setFilter(filter);
				}
				PostLoadCache postLoadCache = item.getPostLoadCache();
				if(postLoadCache!=null)
				{
					LoadCache[] loadCaches = postLoadCache.getLoadCache();
					if(loadCaches!=null && loadCaches.length>0)
					{
						for(LoadCache loadCache:loadCaches)
						{
							loadCache.setFilter(filter);
							loadCache.getTransaction().setFilter(filter);
//							LoadCacheChoice[] loadCacheChoices = loadCache.getLoadCacheChoice();
							LoadCacheChoice loadCacheChoices = loadCache.getLoadCacheChoice();
							if(loadCacheChoices !=null)// && loadCacheChoices.length>0)
							{
//								for(LoadCacheChoice loadCacheChoice:loadCacheChoices)
//								{
									
									LoadCacheChoiceItem[]  loadCacheChoiceItems = loadCacheChoices.getLoadCacheChoiceItem();
									if(loadCacheChoiceItems!=null && loadCacheChoiceItems.length>0)
									{
										for(LoadCacheChoiceItem loadCacheChoiceItem:loadCacheChoiceItems)
										{
											PushCache pushCache = loadCacheChoiceItem.getPushCache();
											if(pushCache!=null)
											{
												pushCache.setFilter(filter);
											}
											RemoveCache removeCache = loadCacheChoiceItem.getRemoveCache();
											if(removeCache!=null)
											{
												removeCache.setFilter(filter);
											}
											RemoveURLCache removeURLCache = loadCacheChoiceItem.getRemoveURLCache();
											if(removeURLCache!=null)
											{
												removeURLCache.setFilter(filter);
											}
											For for1 = loadCacheChoiceItem.getFor();
											parseFor(filter,for1);
										}
									}
//								}
							}
						}
					}
				}
				PostEventGroup postEventGroup = item.getPostEventGroup();
				if(postEventGroup!=null)
				{
					For[] fors= postEventGroup.getFor();
					if(fors!=null && fors.length>0)
					{
						for(For for1:fors)
						{
							parseFor(filter,for1);
						}
					}
				}
				PostRemoveURLCache postRemoveURLCache = item.getPostRemoveURLCache();
				if(postRemoveURLCache!=null)
				{
					RemoveURLCache[] removeURLCaches = postRemoveURLCache.getRemoveURLCache();
					if(removeURLCaches!=null && removeURLCaches.length>0)
					{
						for(RemoveURLCache removeURLCache:removeURLCaches)
						{
							removeURLCache.setFilter(filter);
						}
					}
				}
				PostTransaction postTransaction = item.getPostTransaction();
				if(postTransaction!=null)
				{
					Transaction[] transactions = postTransaction.getTransaction();
					if(transactions!=null && transactions.length>0)
					{
						for(Transaction transacton:transactions)
						{
							transacton.setFilter(filter);
						}
					}
				}
				PostRemoveCache postRemoveCache = item.getPostRemoveCache();
				if(postRemoveCache!=null)
				{
					RemoveCache[] removeCaches = postRemoveCache.getRemoveCache();
					if(removeCaches!=null && removeCaches.length>0)
					{
						for(RemoveCache removeCache:removeCaches)
						{
							removeCache.setFilter(filter);
						}
					}
				}
			}
		}
	}
	private  static String getOpratorValue(CDO cdoOperator,String strKeyDefine,String strOperatorkey,CDO cdoRequest,CDO cdoResponse)
	{
		String strValue = null;
		if(strKeyDefine!=null && strKeyDefine.length()>0)
		{
			if(cdoRequest.exists(strKeyDefine))
			{
				strValue = cdoRequest.getObjectValue(strKeyDefine).toString();
			}else if(cdoResponse!=null && cdoResponse.exists(strKeyDefine))
			{
				strValue = cdoResponse.getObjectValue(strKeyDefine).toString();
			}
		}
		if(strValue==null && cdoOperator!=null)
		{
			strValue = cdoOperator.getStringValue(strOperatorkey);
		}
		if(strValue==null)
		{
			strValue = "";
		}
		return strValue;
	}
	
	private void addBusinessLog(String strServiceName,String strTransName,BusinessLog businessLog,CDO cdoRequest,CDO cdoResponse,Return retResult,int nScene)
	{
		if(businessLog==null)
		{
			return;
		}
		CDO cdoLogRequest = FilterManager.generateBusinessLogRequest(strServiceName,strTransName,cdoRequest,cdoResponse,nScene,retResult,businessLog);
		this.serviceBus.handleTrans(cdoLogRequest,new CDO());
	}
	private void addBusinessLog(CDO cdoLogRequest)
	{
		this.serviceBus.handleTrans(cdoLogRequest,new CDO());
	}

	private Return removeCache(RemoveCache removeCache,CDO cdoRequest,CDO cdoResponse)
	{
		return this.removeCache(-1,null,removeCache,cdoRequest,cdoResponse);
	}
	
	private Return removeCache(int fromIndex,String strIndexId,RemoveCache removeCache,CDO cdoRequest,CDO cdoResponse)
	{
		if(removeCache==null)
		{//不需要处理
			return Return.OK;
		}
		String strCacheId = removeCache.getCacheId();
		if(strCacheId==null)
		{
			strCacheId = removeCache.getFilter().getFilterDefine().getDefaultCacheId();
		}
		Filter filter = removeCache.getFilter();
		if(strCacheId == null)
		{
			logger.warn("can not find removeCache cache server "+filter.getServiceName()+"."+filter.getTransName());
		}
		String strKey = null;
		try
		{
			strKey=FrameworkUtil.getKey(fromIndex,strIndexId,removeCache.getCacheKey(),cdoRequest,cdoResponse);
		}
		catch(Exception e1)
		{
			logger.error("removeCache:"+e1.getMessage(),e1);
		}
		if(strKey == null)
		{
			if(logger.isDebugEnabled())
			{
				logger.debug("can not generate key "+filter.getServiceName()+"."+filter.getTransName());
			}
		}
		try
		{
			boolean bOK =  cacheManager.delete(strCacheId,strKey);
			if(bOK==false)
			{
				return Return.valueOf(-1,"faild to remove cache");
			}
		}
		catch(Exception e)
		{
			logger.error("removeCache:"+e.getMessage(),e);
			return Return.valueOf(-1,e.getLocalizedMessage());
		}
		return Return.OK;
	}

	
	private Return pushCache(PushCache pushCache,CDO cdoRequest,CDO cdoResponse)
	{
		return this.pushCache(-1,null,pushCache,cdoRequest,cdoResponse);
	}
	private Return pushCache(int fromIndex,String strIndexId,PushCache pushCache,CDO cdoRequest,CDO cdoResponse)
	{
		if(pushCache==null)
		{//不需要处理
			return Return.OK;
		}
		String cacheServerId = pushCache.getCacheId();
		if(cacheServerId==null)
		{
			cacheServerId = pushCache.getFilter().getFilterDefine().getDefaultCacheId();
		}
		Filter filter = pushCache.getFilter();
		if(cacheServerId == null)
		{
			logger.warn("can not find removeCache cache server "+filter.getServiceName()+"."+filter.getTransName());
		}
		String strKey = null;
		try
		{
			strKey=FrameworkUtil.getKey(fromIndex,strIndexId,pushCache.getCacheKey(),cdoRequest,cdoRequest);
		}
		catch(Exception e1)
		{
			logger.error("pushCache:"+e1.getMessage(),e1);
		}
		if(strKey == null)
		{
			if(logger.isDebugEnabled())
			{
				logger.debug("can not generate key "+filter.getServiceName()+"."+filter.getTransName());
			}
		}
		Object objValue = FrameworkUtil.getValue(fromIndex,strIndexId,pushCache.getCacheValue(),cdoRequest,cdoResponse);
		
		try
		{
			boolean bOK =  cacheManager.put(cacheServerId,strKey,(String)objValue,pushCache.getExpireTime());
			if(bOK == false)
			{
				return Return.valueOf(-1,"faild to push cache");
			}
		}
		catch(Exception e)
		{
			logger.error("pushCache:"+e.getMessage(),e);
			return Return.valueOf(-1,e.getLocalizedMessage());
		}
		return Return.OK;
	}

	private Return  removeURLCache(RemoveURLCache removeURLCache,CDO cdoRequest,CDO cdoResponse)
	{
		if(removeURLCache==null)
		{//不需要处理 
			return Return.OK;
		}
		boolean bOK = this.cacheManager.removeURLCache(removeURLCache,cdoRequest,cdoResponse);
		if(bOK == false)
		{
			return Return.valueOf(-1,"faild to removeURLCache ");
		}
		return Return.OK;
	}
	private Return removeURLCache(int fromIndex,String strIndexId,RemoveURLCache removeURLCache,CDO cdoRequest,CDO cdoResponse)
	{
		if(removeURLCache==null)
		{//不需要处理 
			return Return.OK;
		}
		boolean bOK =  this.cacheManager.removeURLCache(fromIndex,strIndexId,removeURLCache,cdoRequest,cdoResponse);
		if(bOK == false)
		{
			return Return.valueOf(-1,"faild to removeURLCache");
		}
		return Return.OK;
	}

	private Return handleTransaction(Transaction transaction,CDO cdoOldRequest,CDO cdoOldResponse,boolean bReload,Return oldReturn)
	{
		CDO cdoNewRequest = new CDO();
		CDO cdoNewResponse = new CDO();
		return this.handleTransaction(transaction,cdoOldRequest,cdoOldResponse,cdoNewRequest,cdoNewResponse,bReload,oldReturn);
	}

	private Return handleTransaction(int fromIndex,String strIndexId,Transaction transaction,CDO cdoOldRequest,CDO cdoOldResponse,boolean bReload,Return oldReturn)
	{
		CDO cdoNewRequest = new CDO();
		CDO cdoNewResponse = new CDO();
		return this.handleTransaction(fromIndex,strIndexId,transaction,cdoOldRequest,cdoOldResponse,cdoNewRequest,cdoNewResponse,bReload,oldReturn);
	}
	
	private Return handleTransaction(Transaction transaction,CDO cdoOldRequest,CDO cdoOldResponse,CDO cdoRequest,CDO cdoResponse,boolean bReload,Return oldReturn)
	{
		return this.handleTransaction(-1,null,transaction,cdoOldRequest,cdoOldResponse,cdoRequest,cdoResponse,bReload,oldReturn);
	}
	
	private Return handleTransaction(int fromIndex,String strIndexId,Transaction transaction,CDO cdoOldRequest,CDO cdoOldResponse,CDO cdoRequest,CDO cdoResponse,boolean bReload,Return oldReturn)
	{
		if(transaction==null)
		{//不需要处理
			return Return.OK;
		}

		RequestKey[] requestKeys = transaction.getRequestKey();
		if(requestKeys==null)
		{
			cdoRequest.copyFrom(cdoOldRequest);
		}
		
//		String strTransName = transaction.getTransName();
		cdoRequest.setStringValue("strServiceName",transaction.getServiceName());
		cdoRequest.setStringValue("strTransName",transaction.getTransName());

		if(requestKeys!=null)
		{
			try
			{
				for(RequestKey requestKey:requestKeys)
				{
					String strValueId = requestKey.getValueId();
					String strFieldId = requestKey.getFieldId();
					if(strValueId!=null)
					{
						strValueId = strValueId.substring(1,strValueId.length()-1);
						if(fromIndex>-1)
						{
							String reg = "["+strIndexId+"]";
							String rep = "["+fromIndex+"]";
							strValueId = strValueId.replace(reg,rep);
						}
					}
					if(strFieldId==null)
					{
						strFieldId = strValueId;
					}
					else
					{
						strFieldId = strFieldId.substring(1,strFieldId.length()-1);
					}
					if(FrameworkUtil.TRANSACTION_OLD_REQUEST.equalsIgnoreCase(strFieldId))
					{
						if(cdoOldRequest!=null)
						{
							cdoRequest.setCDOValue(strFieldId,cdoOldRequest);
						}						
					}
					else if(FrameworkUtil.TRANSACTION_OLD_RESPONSE.equalsIgnoreCase(strFieldId))
					{
						if(cdoOldResponse!=null)
						{
							cdoRequest.setCDOValue(strFieldId,cdoOldResponse);
						}
					}
					else  if(FrameworkUtil.TRANSACTION_OLD_RESULT.equalsIgnoreCase(strFieldId))
					{
						if(oldReturn!=null)
						{
							CDO cdoOldReturn = new CDO();
							cdoOldReturn.setIntegerValue("nReturnCode",oldReturn.getCode());
							cdoOldReturn.setStringValue("strReturnText",oldReturn.getText());
							cdoOldReturn.setStringValue("strReturnInfo",oldReturn.getInfo());
							cdoRequest.setCDOValue(strFieldId,cdoOldReturn);
						}
					}
					else
					{					
						Object value = requestKey.getValue();
						if(value==null)
						{
							if(strValueId==null)
							{
								String strText = "in handleTransaction, FiledId:{"+strValueId+"} does not exist";
								logger.error(strText);
								return Return.valueOf(1,strText);
							}
							
							CDO cdoSource = null;
							if(requestKey.getScope().value().equals(RequestKeyScopeType.REQUEST.value()))
							{
								cdoSource = cdoOldRequest;
							}else						
							{
								cdoSource = cdoOldResponse;
							}
							if(cdoSource==null)
							{
								logger.error("in handleTransaction, FiledId:{"+strValueId+"} does not exist");
								return Return.valueOf(1,"in handleTransaction, FiledId:{"+strValueId+"} does not exist");
							}
							if(cdoSource.exists(strValueId))
							{
								cdoRequest.setObjectExt(strFieldId,cdoSource.getObject(strValueId));
							}
							else
							{
								logger.info("in handleTransaction, FiledId:{"+strValueId+"} does not exist");
								//TODO 此处需要增强,应该可以设定是否必须有请求参数
								//return Return.valueOf(1,"in handleTransaction, FiledId:{"+strValueId+"} does not exist");
							}
						}						
						else
						{
							if(requestKey.getType()==null)
							{
								logger.error("in handleTransaction, Prog faild to find value type :"+strValueId);
								return Return.valueOf(2,"in handleTransaction, Prog faild to find value type :"+strValueId);
							}
							FrameworkUtil.setConstantsObject(cdoRequest,strFieldId,FrameworkUtil.mappingToDataType(requestKey.getType()),value);
						}
					}								
				}
			}catch(Exception e)
			{
				logger.error("handleTransaction:"+e.getMessage(),e);
				//TODO
				return Return.valueOf(3,e.getLocalizedMessage());
			}
		}
		
		//执行trans
		if(bReload)
		{
			cdoRequest.setIntegerValue(RELOAD_FLAG,1);
		}
		return this.serviceBus.handleTrans(cdoRequest,cdoResponse);	
	}
	private Return handleFor(For for1,CDO cdoRequest,CDO cdoResponse,Return oldReturn)
	{
		Return ret = Return.OK;
		if(for1==null)
		{//不需要处理
			return ret;
		}		
		ForItem[] items = for1.getForItem();
		String strCount = for1.getCount();
		int nFromIndex =Integer.parseInt(for1.getFromIndex());
		String strIndexIdTemp = for1.getIndexId();
		String strIndexId = strIndexIdTemp.substring(1,strIndexIdTemp.length()-1);
		int nCount = cdoRequest.getIntegerValue(strCount.substring(1,strCount.length()-1));
		
		for(;nFromIndex<nCount;nFromIndex++)
		{
			for(ForItem item:items)
			{
				PushCache pushCache = item.getPushCache();
				Return retTemp = this.pushCache(nFromIndex,strIndexId,pushCache,cdoRequest,cdoResponse);
				if(retTemp.getCode()!=0) {ret = retTemp;};
				RemoveCache removeCache = item.getRemoveCache();
				retTemp = this.removeCache(nFromIndex,strIndexId,removeCache,cdoRequest,cdoResponse);
				if(retTemp.getCode()!=0) {ret = retTemp;};
				RemoveURLCache removeURLCache = item.getRemoveURLCache();
				retTemp = this.removeURLCache(nFromIndex,strIndexId,removeURLCache,cdoRequest,cdoResponse);
				if(retTemp.getCode()!=0) {ret = retTemp;};
				Transaction transaction = item.getTransaction();
				retTemp = this.handleTransaction(nFromIndex,strIndexId,transaction,cdoRequest,cdoResponse,false,oldReturn);
				if(retTemp.getCode()!=0) {ret = retTemp;};
				LoadCache loadCache = item.getLoadCache();
				retTemp = this.loadCache(nFromIndex,strIndexId,loadCache,cdoRequest,cdoResponse);
				if(retTemp.getCode()!=0) {ret = retTemp;};				
			}
		}
		return ret;
	}

	/**
	 * 先按规则取数据,再把结果放到cache中
	 * @param loadCache
	 * @param eventTask
	 * @return true:不需要重试,false需要重试
	 */
	private Return loadCache(LoadCache loadCache,CDO cdoOldRequest,CDO cdoOldResponse)
	{
		return this.loadCache(-1,null,loadCache,cdoOldRequest,cdoOldResponse);
	}
	
	private Return loadCache(int fromIndex,String strIndexId,LoadCache loadCache,CDO cdoOldRequest,CDO cdoOldResponse)
	{
		if(loadCache==null)
		{
			return Return.OK;
		}
		CDO cdoRequest = new CDO();
		CDO cdoResponse = new CDO();		
		Transaction transaction = loadCache.getTransaction();
		String strTransName = transaction.getTransName();
		Return ret = this.handleTransaction(fromIndex,strIndexId,transaction,cdoOldRequest,cdoOldResponse,cdoRequest,cdoResponse,true,null);
		if(ret==null)
		{
			logger.error("can not find transName: "+strTransName);
			//TODO
			return Return.valueOf(-1,"can not find transName: "+strTransName);
		}
		if(ret.getCode()!=0)
		{
			logger.error("Faild to loadCache: "+strTransName + " " +ret.getText());
			//TODO 
			return ret;
		}
		CreteriasType condition = loadCache.getResponseCondition();
		boolean bEnter = false;
		try
		{
			bEnter=FrameworkUtil.isFitCreterias(condition,cdoResponse);
		}
		catch(Exception e)
		{
			//TODO 需要再仔细考虑
			logger.error("loadCache:"+e.getMessage(),e);
		}
		if(bEnter==false)
		{//不需要进一步处理
			return Return.OK;
		}
		ret = Return.OK;
//		LoadCacheChoice[] lcs = loadCache.getLoadCacheChoice();
		LoadCacheChoice lcs = loadCache.getLoadCacheChoice();
//		for(LoadCacheChoice lc:lcs)
//		{
			LoadCacheChoiceItem[] lcItems = lcs.getLoadCacheChoiceItem();
			if(lcItems!=null && lcItems.length>0)
			{
				for(LoadCacheChoiceItem item:lcItems)
				{
					PushCache pushCache = item.getPushCache();
					Return retTemp = this.pushCache(pushCache,cdoRequest,cdoResponse);
					if(retTemp.getCode()!=0) {ret = retTemp;}
					RemoveCache removeCache = item.getRemoveCache();
					retTemp = this.removeCache(removeCache,cdoRequest,cdoResponse);
					if(retTemp.getCode()!=0) {ret = retTemp;}
					RemoveURLCache removeURLCache = item.getRemoveURLCache();
					retTemp = this.removeURLCache(removeURLCache,cdoRequest,cdoResponse);
					if(retTemp.getCode()!=0) {ret = retTemp;};
					For for1 = item.getFor();
					if(for1 !=null)
					{
						retTemp = this.handleFor(for1,cdoRequest,cdoResponse,null);
						if(retTemp.getCode()!=0) {ret = retTemp;};
					}
				}
			}
//		}
		
		return ret;
	}

	private void handlePostEvent(PostEvent postEvent,EventTask eventTask)
	{
		CDO cdoRequest = eventTask.getTransRequest();
		CDO cdoResponse = eventTask.getTransResponse();
		String strServiceName = eventTask.getServiceName();
		String strTransName = eventTask.getTransName();
		Return oldReturn = eventTask.getTransHandleResult();
		PostTransEventTypeItem[] postTransEventItems = postEvent.getPostTransEventTypeItem();
		for(PostTransEventTypeItem item:postTransEventItems)
		{
			PostPushCache postPushCache = item.getPostPushCache();
			if(postPushCache!=null && postPushCache.getSyn()==false)
			{
				CreteriasType cts = postPushCache.getRequestCondition();
				CreteriasType cts2 = postPushCache.getResponseCondition();
				boolean isEnter = false;
				try
				{
					isEnter=FrameworkUtil.isFitCreterias(cts,cdoRequest);
					if(isEnter)
					{
						isEnter = FrameworkUtil.isFitCreterias(cts2,cdoResponse);
					}
				}
				catch(Exception e)
				{
					logger.error("handlePostEvent:"+e.getMessage(),e);
				}
				if(isEnter==true)
				{					
					PushCache[] pushCaches = postPushCache.getPushCache();
					{
						if(pushCaches!=null && pushCaches.length>0)
						{
							for(PushCache pushCache:pushCaches)
							{
								this.pushCache(pushCache,cdoRequest,cdoResponse);
							}
						}
					}
				}
				
			}
			PostRemoveCache postRemoveCache = item.getPostRemoveCache();
			if(postRemoveCache!=null && postRemoveCache.getSyn()==false)
			{
				CreteriasType cts = postRemoveCache.getRequestCondition();
				CreteriasType cts2 = postRemoveCache.getResponseCondition();
				boolean isEnter = false;
				try
				{
					isEnter=FrameworkUtil.isFitCreterias(cts,cdoRequest);
					if(isEnter)
					{
						isEnter = FrameworkUtil.isFitCreterias(cts2,cdoResponse);
					}
				}
				catch(Exception e)
				{
					logger.error("handlePostEvent:"+e.getMessage(),e);
				}
				if(isEnter==true)
				{				
					RemoveCache[] removeCaches = postRemoveCache.getRemoveCache();
					if(removeCaches!=null && removeCaches.length>0)
					{
						for(RemoveCache removeCache:removeCaches)
						{
							this.removeCache(removeCache,cdoRequest,cdoResponse);
						}
					}					
				}
			}
			PostRemoveURLCache postRemoveURLCache = item.getPostRemoveURLCache();
			if(postRemoveURLCache!=null && postRemoveURLCache.getSyn()==false)
			{
					CreteriasType cts = postRemoveURLCache.getRequestCondition();
					CreteriasType cts2 = postRemoveURLCache.getResponseCondition();
					boolean isEnter = false;
					try
					{
						isEnter=FrameworkUtil.isFitCreterias(cts,cdoRequest);
						if(isEnter)
						{
							isEnter = FrameworkUtil.isFitCreterias(cts2,cdoResponse);
						}
					}
					catch(Exception e)
					{
						logger.error("handlePostEvent:"+e.getMessage(),e);
					}
					if(isEnter==true)
					{				
						RemoveURLCache[] removeURLCaches = postRemoveURLCache.getRemoveURLCache();
						if(removeURLCaches!=null && removeURLCaches.length>0)
						{
							for(RemoveURLCache removeURLCache:removeURLCaches)
							{
								this.removeURLCache(removeURLCache,cdoRequest,cdoResponse);
							}
						}						
					}
			}
			PostLoadCache postLoadCache = item.getPostLoadCache();
			if(postLoadCache!=null && postLoadCache.getSyn()==false)
			{
					CreteriasType cts = postLoadCache.getRequestCondition();
					CreteriasType cts2 = postLoadCache.getResponseCondition();
					boolean isEnter = false;
					try
					{
						isEnter=FrameworkUtil.isFitCreterias(cts,cdoRequest);
						if(isEnter)
						{
							isEnter = FrameworkUtil.isFitCreterias(cts2,cdoResponse);
						}
					}
					catch(Exception e)
					{
						logger.error("handlePostEvent:"+e.getMessage(),e);
					}
					if(isEnter==true)
					{
						LoadCache[] loadCaches = postLoadCache.getLoadCache();
						if(loadCaches!=null && loadCaches.length>0)
						{
							for(LoadCache loadCache:loadCaches)
							{
								this.loadCache(loadCache,cdoRequest,cdoResponse);
							}
						}
					}
			}
			PostTransaction postTransaction = item.getPostTransaction();
			if(postTransaction != null && postTransaction.getSyn()==false)
			{
				CreteriasType cts = postTransaction.getRequestCondition();
				CreteriasType cts2 = postTransaction.getResponseCondition();
				ReturnCode returnCode = postTransaction.getReturnCode();
				boolean isEnter = false;

				try
				{
					isEnter = FrameworkUtil.isFitReturn(returnCode,oldReturn);
				}
				catch(Exception e)
				{
					logger.error("handlePostEvent:"+e.getMessage(),e);
				}
				
				if(isEnter==true)
				{
					try
					{
						isEnter=FrameworkUtil.isFitCreterias(cts,cdoRequest);
						if(isEnter)
						{
							isEnter = FrameworkUtil.isFitCreterias(cts2,cdoResponse);
						}
					}
					catch(Exception e)
					{
						logger.error("handlePostEvent:"+e.getMessage(),e);
					}
					if(isEnter==true)
					{
						Transaction[] transactions = postTransaction.getTransaction();
						if(transactions!=null && transactions.length>0)
						{
							for(Transaction transaction:transactions)
							{
								this.handleTransaction(transaction,cdoRequest,cdoResponse,false,oldReturn);
							}
						}
					}
				}
			}
			PostEventGroup postEventGroup = item.getPostEventGroup();
			if(postEventGroup!=null && postEventGroup.getSyn()==false)
			{
					CreteriasType cts = postEventGroup.getRequestCondition();
					CreteriasType cts2 = postEventGroup.getResponseCondition();
					boolean isEnter = false;
					try
					{
						isEnter=FrameworkUtil.isFitCreterias(cts,cdoRequest);
						if(isEnter)
						{
							isEnter = FrameworkUtil.isFitCreterias(cts2,cdoResponse);
						}
					}
					catch(Exception e)
					{
						logger.error("handlePostEvent:"+e.getMessage(),e);
					}
					if(isEnter==true)
					{
						For[] fors = postEventGroup.getFor();
						if(fors!=null && fors.length>0)
						{
							for(For for1:fors)
							{
								this.handleFor(for1,cdoRequest,cdoResponse,oldReturn);
							}
						}
					}
			}
			BusinessLog businessLog = item.getBusinessLog();
			if(businessLog!=null)
			{
				this.addBusinessLog(strServiceName,strTransName,businessLog,cdoRequest,cdoResponse,oldReturn,EventTask.EVENT_SCENE_AFTER);
			}
			Event event = item.getEvent();
			handleEvent(event,eventTask);			
		}		
	}

	private void handlePreEvent(PreEvent preEvent,EventTask eventTask)
	{
		CDO cdoRequest = eventTask.getTransRequest();
		String strServiceName = eventTask.getServiceName();
		String strTransName = eventTask.getTransName();
		PreTransEventTypeItem[] preTransEventItems = preEvent.getPreTransEventTypeItem();
		for(PreTransEventTypeItem item:preTransEventItems)
		{
			PrePushCache prePushCache = item.getPrePushCache();
			if(prePushCache!=null)
			{
				if(prePushCache.getSyn()==false)
				{//异步执行
					CreteriasType cts = prePushCache.getRequestCondition();
					boolean isEnter = false;
					try
					{
						isEnter=FrameworkUtil.isFitCreterias(cts,cdoRequest);
					}
					catch(Exception e)
					{
						logger.error("handlePreEvent:"+e.getMessage(),e);
					}
					if(isEnter==true)
					{
						PushCache[] pushCaches = prePushCache.getPushCache();
						{
							if(pushCaches!=null && pushCaches.length>0)
							{
								for(PushCache pushCache:pushCaches)
								{
									this.pushCache(pushCache,cdoRequest,cdoRequest);
								}
							}
						}
					}
				}
			}
			PreRemoveCache preRemoveCache = item.getPreRemoveCache();
			if(preRemoveCache!=null)
			{
				if(preRemoveCache.getSyn()==false)
				{
					CreteriasType cts = preRemoveCache.getRequestCondition();
					boolean isEnter = false;
					try
					{
						isEnter=FrameworkUtil.isFitCreterias(cts,cdoRequest);
					}
					catch(Exception e)
					{
						logger.error("handlePreEvent:"+e.getMessage(),e);
					}
					if(isEnter==true)
					{
						RemoveCache[] removeCaches = preRemoveCache.getRemoveCache();
						if(removeCaches!=null && removeCaches.length>0)
						{
							for(RemoveCache removeCache:removeCaches)
							{
								this.removeCache(removeCache,cdoRequest,cdoRequest);
							}
						}
					}
				}
			}
			PreRemoveURLCache preRemoveURLCache = item.getPreRemoveURLCache();
			if(preRemoveURLCache!=null)
			{
				if(preRemoveURLCache.getSyn()==false)
				{
					CreteriasType cts = preRemoveURLCache.getRequestCondition();
					boolean isEnter = false;
					try
					{
						isEnter=FrameworkUtil.isFitCreterias(cts,cdoRequest);
					}
					catch(Exception e)
					{
						logger.error("handlePreEvent:"+e.getMessage(),e);
					}
					if(isEnter==true)
					{
						RemoveURLCache[] removeURLCaches = preRemoveURLCache.getRemoveURLCache();
						if(removeURLCaches!=null && removeURLCaches.length>0)
						{
							for(RemoveURLCache removeURLCache:removeURLCaches)
							{
								this.removeURLCache(removeURLCache,cdoRequest,cdoRequest);
							}
						}
					}
				}
			}
			PreLoadCache preLoadCache = item.getPreLoadCache();
			if(preLoadCache!=null)
			{
				if(preLoadCache.getSyn()==false)
				{//同步执行
					CreteriasType cts = preLoadCache.getRequestCondition();
					boolean isEnter = false;
					try
					{
						isEnter=FrameworkUtil.isFitCreterias(cts,cdoRequest);
					}
					catch(Exception e)
					{
						logger.error("handlePreEvent:"+e.getMessage(),e);
					}
					if(isEnter==true)
					{
						LoadCache[] loadCaches = preLoadCache.getLoadCache();
						if(loadCaches!=null && loadCaches.length>0)
						{
							for(LoadCache loadCache:loadCaches)
							{
								this.loadCache(loadCache,cdoRequest,cdoRequest);
							}
						}
					}
				}
			}
			PreTransaction preTransaction = item.getPreTransaction();
			if(preTransaction != null)
			{
				if(preTransaction.getSyn()==false)
				{
					CreteriasType cts = preTransaction.getRequestCondition();
					boolean isEnter = false;
					try
					{
						isEnter=FrameworkUtil.isFitCreterias(cts,cdoRequest);
					}
					catch(Exception e)
					{
						logger.error("handlePreEvent:"+e.getMessage(),e);
					}
					if(isEnter==false)
					{
						Transaction[] transactions = preTransaction.getTransaction();
						if(transactions!=null && transactions.length>0)
						{
							for(Transaction transaction:transactions)
							{
								this.handleTransaction(transaction,cdoRequest,cdoRequest,false,null);
							}
						}
					}
				}

			}
			PreEventGroup preEventGroup = item.getPreEventGroup();
			if(preEventGroup!=null)
			{
				if(preEventGroup.getSyn()==false)
				{
					CreteriasType cts = preEventGroup.getRequestCondition();
					boolean isEnter = false;
					try
					{
						isEnter=FrameworkUtil.isFitCreterias(cts,cdoRequest);
					}
					catch(Exception e)
					{
						logger.error("handlePreEvent:"+e.getMessage(),e);
					}
					if(isEnter==true)
					{
						For[] fors = preEventGroup.getFor();
						if(fors!=null && fors.length>0)
						{
							for(For for1:fors)
							{
								this.handleFor(for1,cdoRequest,cdoRequest,null);
							}
						}
					}
				}
			}
			BusinessLog businessLog = item.getBusinessLog();
			if(businessLog!=null)
			{
				this.addBusinessLog(strServiceName,strTransName,businessLog,cdoRequest,cdoRequest,Return.OK,eventTask.getEventScene());
			}
			Event event = item.getEvent();
			handleEvent(event,eventTask);
		}
	}
	
	private void handleEvent(Event event,EventTask eventTask)
	{
		if(event==null)
		{
			return;
		}
		
		CDO cdoEvent = new CDO();
		cdoEvent.setStringValue("strEventName",event.getEventName());		
		cdoEvent.setCDOValue("cdoEventReqeust",eventTask.getTransRequest());
		if(eventTask.getTransResponse()!=null)
		{
			cdoEvent.setCDOValue("cdoEventResponse",eventTask.getTransResponse());
		}
		Return ret = eventTask.getTransHandleResult();
		if(ret!=null)
		{
			CDO cdoResult = new CDO();
			cdoResult.setIntegerValue("nReturnCode",ret.getCode());
			cdoResult.setStringValue("strText",ret.getText());
			cdoResult.setStringValue("strInfo",ret.getInfo());
			cdoEvent.setCDOValue("cdoResult",cdoResult);
		}
		serviceBus.handleEvent(cdoEvent);
		
	}
	
	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------
	public Return append(String strFilterConfigXML)
	{
		try
		{
			FilterDefine filterDefine = FilterDefine.fromXML(strFilterConfigXML);
			int nFilterCount = filterDefine.getFilterCount();
			if(nFilterCount == 0)
			{
				logger.warn("There is none filter be found in "+strFilterConfigXML);
				return Return.OK;
			}
			Filter[] filters = filterDefine.getFilter();
			for(Filter filter:filters)
			{
				filter.setFilterDefine(filterDefine);
				String strServiceName = filter.getServiceName();
				HashMap<String,Filter> serviceFilter = this.hmFilter.get(strServiceName);
				if(serviceFilter==null)
				{
					serviceFilter = new HashMap<String,Filter>(10);
					this.hmFilter.put(strServiceName,serviceFilter);
				}
				String strTransName = filter.getTransName();
				Filter oldFilter = serviceFilter.put(strTransName,filter);
				
				if(oldFilter!=null)
				{
					String errorText = new StringBuilder().append("duplicated filter: serviceName=[").append(strServiceName).append("] transName=[").append("]").toString();
					logger.fatal(errorText);
					return Return.valueOf(1,errorText);
				}
				parseFilter(filter,filterDefine);					
			}
		}
		catch(Exception e)
		{
			logger.error("append:"+e.getMessage(),e);
			return Return.valueOf(-1,e.getLocalizedMessage());
		}
		return Return.OK;
	}
	
	public static CDO generateBusinessLogRequest(String strServiceName,String strTransName,CDO cdoRequest,CDO cdoResponse,int nScene,Return handleResult,BusinessLog businessLogDefine)
	{	
		CDO cdoBusinessLog = new CDO();
		//nPhrase
		cdoBusinessLog.setIntegerValue("nPhrase",nScene);
		//strTransName
		cdoBusinessLog.setStringValue("strTransName2",strServiceName+"."+strTransName);
		
		//strOperationName
		String strOperationName = businessLogDefine.getOperationName();
		if(strOperationName==null)
		{			
			strOperationName = "";
		}
		cdoBusinessLog.setStringValue("strOperationName",strOperationName);
		
		//strOperationType
		String strOperationType = businessLogDefine.getOperationType();
		if(strOperationType==null)
		{			
			strOperationType = "";
		}
		cdoBusinessLog.setStringValue("strOperationType",strOperationType);
		
		//strRelationId
		String strRelationId = businessLogDefine.getRelationId();
		if(strRelationId!=null)
		{
			if(cdoRequest.exists(strRelationId))
			{
				strRelationId = cdoRequest.getObjectValue(strRelationId).toString();
			}else
			{
				if(cdoResponse!=null && cdoResponse.exists(strRelationId))
				{
					strRelationId = cdoResponse.getObjectValue(strRelationId).toString();
				}
			}
		}
		else
		{
			strRelationId = "";
		}
		cdoBusinessLog.setStringValue("strRelationId",strRelationId);
		
		//strRelationTitle
		String strRelationTitle = businessLogDefine.getRelationTitle();
		if(strRelationTitle!=null)
		{
			if(cdoRequest.exists(strRelationTitle))
			{
				strRelationTitle = cdoRequest.getObjectValue(strRelationTitle).toString();
			}else
			{
				if(cdoResponse!=null && cdoResponse.exists(strRelationTitle))
				{
					strRelationTitle = cdoResponse.getObjectValue(strRelationTitle).toString();
				}
			}
		}
		else
		{
			strRelationTitle = "";
		}
		cdoBusinessLog.setStringValue("strRelationTitle",strRelationTitle);
		
		//strCDORequestXml
		cdoBusinessLog.setStringValue("strCDORequestXml",cdoRequest.toXML());	
		//strCDOResponseXml
		String strCDOResponseXml = "";
		if(cdoResponse!=null)
		{
			strCDOResponseXml = cdoResponse.toXML();
		}
		cdoBusinessLog.setStringValue("strCDOResponseXml",strCDOResponseXml);	
		
		// userid,username,ip
		CDO cdoOperator = null;
		if(cdoRequest.exists("cdoOperator"))
		{
			cdoOperator = cdoRequest.getCDOValue("cdoOperator");
		}		
		
		//userid
		String strUserId = getOpratorValue(cdoOperator,businessLogDefine.getUserId(),"lUserId",cdoRequest,cdoResponse);
		cdoBusinessLog.setStringValue("strUserId",strUserId);	
		
		//strUserName		
		String strUserName = getOpratorValue(cdoOperator,businessLogDefine.getUserName(),"strUserName",cdoRequest,cdoResponse);
		cdoBusinessLog.setStringValue("strUserName",strUserName);	
		
		//strIP
		String strIP = getOpratorValue(cdoOperator,null,"strIp",cdoRequest,cdoResponse);
		cdoBusinessLog.setStringValue("strIP",strIP);
		
		//nReturnResult
		int nReturnResult = Integer.MAX_VALUE;
		if(handleResult!=null)
		{
			nReturnResult = handleResult.getCode();
		}
		cdoBusinessLog.setIntegerValue("nReturnResult",nReturnResult);
		
		//strException
		String strException = "";
		if(handleResult!=null && handleResult.getCode()!=0)
		{
			strException = handleResult.getText();
		}
		cdoBusinessLog.setStringValue("strException",strException);
		
		//nPriority
		int nPriority = businessLogDefine.getPriority();
		cdoBusinessLog.setIntegerValue("nPriority",nPriority);
		
		cdoBusinessLog.setStringValue(ITransService.SERVICENAME_KEY,"SystemService");
		cdoBusinessLog.setStringValue(ITransService.TRANSNAME_KEY,"addBusinessLog");
		return cdoBusinessLog;		
	}

	
	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------
	
	/**
	 * 处理事后事件
	 * TODO 同步事件以后要独立出去,但要考虑好同步事件出错时的处理逻辑;另外此逻辑有冗余代码,需要改进
	 * 目前,同步事件即使出错,异步事件仍执行
	 */
	public Return handlePostEvent(String strServiceName,String strTransName,CDO cdoRequest,CDO cdoResponse,Return transReturn)
	{
		try
		{
			Filter filter = this.getFilter(strServiceName,strTransName);
			if(filter==null)
			{
				return Return.OK;
			}
			PostEvent postEvent = filter.getPostEvent();
			if(postEvent==null)
			{
				return Return.OK;
			}
						
			//处理异步action
			EventTask eventTask = new EventTask(EventTask.EVENT_SCENE_BEFORE);
			eventTask.setServiceName(strServiceName);
			eventTask.setTransName(strTransName);
			eventTask.setTransRequest(cdoRequest);
			eventTask.setTransResponse(cdoResponse);
			eventTask.setTransHanleResult(transReturn);
			eventTask.setOtherTask(postEvent);
			serviceBus.getEventHandler().addEventTask(eventTask);
			
			//处理同步action
			return handlePostFilter(postEvent,cdoRequest,cdoResponse,transReturn);
		}
		catch(Exception e)
		{
			logger.error("handlePostEvent:"+e.getMessage(),e);
			return Return.valueOf(-1,e.getLocalizedMessage(),"",e);
		}

	}


	public Return handlePostFilter(PostEvent postEvent,CDO cdoRequest,CDO cdoResponse,Return transReturn)
	{
		try
		{
			PostTransEventTypeItem[] postTransEventItems = postEvent.getPostTransEventTypeItem();
			for(PostTransEventTypeItem item:postTransEventItems)
			{
				PostPushCache postPushCache = item.getPostPushCache();
				if(postPushCache!=null && postPushCache.getSyn()==true)
				{
					CreteriasType cts = postPushCache.getRequestCondition();
					CreteriasType cts2 = postPushCache.getResponseCondition();
					boolean isEnter = false;
					try
					{
						isEnter=FrameworkUtil.isFitCreterias(cts,cdoRequest);
						if(isEnter)
						{
							isEnter = FrameworkUtil.isFitCreterias(cts2,cdoResponse);
						}
					}
					catch(Exception e)
					{
						logger.error("handlePostEvent:"+e.getMessage(),e);
						return Return.valueOf(-1, e.getLocalizedMessage(),"",e);
					}
					if(isEnter==true)
					{					
						PushCache[] pushCaches = postPushCache.getPushCache();
						{
							if(pushCaches!=null && pushCaches.length>0)
							{
								for(PushCache pushCache:pushCaches)
								{
									Return ret = this.pushCache(pushCache,cdoRequest,cdoResponse);
									if(ret.getCode()!=0){return ret;};
								}
							}
						}
					}
					
				}
				PostRemoveCache postRemoveCache = item.getPostRemoveCache();
				if(postRemoveCache!=null && postRemoveCache.getSyn()==true)
				{
					CreteriasType cts = postRemoveCache.getRequestCondition();
					CreteriasType cts2 = postRemoveCache.getResponseCondition();
					boolean isEnter = false;
					try
					{
						isEnter=FrameworkUtil.isFitCreterias(cts,cdoRequest);
						if(isEnter)
						{
							isEnter = FrameworkUtil.isFitCreterias(cts2,cdoResponse);
						}
					}
					catch(Exception e)
					{
						logger.error("handlePostEvent:"+e.getMessage(),e);
						return Return.valueOf(-1, e.getLocalizedMessage(),"",e);
					}
					if(isEnter==true)
					{				
						RemoveCache[] removeCaches = postRemoveCache.getRemoveCache();
						if(removeCaches!=null && removeCaches.length>0)
						{
							for(RemoveCache removeCache:removeCaches)
							{
								Return ret = this.removeCache(removeCache,cdoRequest,cdoResponse);
								if(ret.getCode()!=0){return ret;}
							}
						}					
					}
				}
				PostRemoveURLCache postRemoveURLCache = item.getPostRemoveURLCache();
				if(postRemoveURLCache!=null && postRemoveURLCache.getSyn()==true)
				{
						CreteriasType cts = postRemoveURLCache.getRequestCondition();
						CreteriasType cts2 = postRemoveURLCache.getResponseCondition();
						boolean isEnter = false;
						try
						{
							isEnter=FrameworkUtil.isFitCreterias(cts,cdoRequest);
							if(isEnter)
							{
								isEnter = FrameworkUtil.isFitCreterias(cts2,cdoResponse);
							}
						}
						catch(Exception e)
						{
							logger.error("handlePostEvent:"+e.getMessage(),e);
							return Return.valueOf(-1,e.getLocalizedMessage(),"",e);
						}
						if(isEnter==true)
						{				
							RemoveURLCache[] removeURLCaches = postRemoveURLCache.getRemoveURLCache();
							if(removeURLCaches!=null && removeURLCaches.length>0)
							{
								for(RemoveURLCache removeURLCache:removeURLCaches)
								{
									Return ret = this.removeURLCache(removeURLCache,cdoRequest,cdoResponse);
									if(ret.getCode()!=0){return ret;}
								}
							}						
						}
				}
				PostLoadCache postLoadCache = item.getPostLoadCache();
				if(postLoadCache!=null && postLoadCache.getSyn()==true)
				{
						CreteriasType cts = postLoadCache.getRequestCondition();
						CreteriasType cts2 = postLoadCache.getResponseCondition();
						boolean isEnter = false;
						try
						{
							isEnter=FrameworkUtil.isFitCreterias(cts,cdoRequest);
							if(isEnter)
							{
								isEnter = FrameworkUtil.isFitCreterias(cts2,cdoResponse);
							}
						}
						catch(Exception e)
						{
							logger.error("handlePostEvent:"+e.getMessage(),e);
							return Return.valueOf(-1,e.getLocalizedMessage(),"",e);
						}
						if(isEnter==true)
						{
							LoadCache[] loadCaches = postLoadCache.getLoadCache();
							if(loadCaches!=null && loadCaches.length>0)
							{
								for(LoadCache loadCache:loadCaches)
								{
									Return ret = this.loadCache(loadCache,cdoRequest,cdoResponse);
									if(ret.getCode()!=0){return ret;}
								}
							}
						}
				}
				PostTransaction postTransaction = item.getPostTransaction();
				if(postTransaction != null && postTransaction.getSyn()==true)
				{
					CreteriasType cts = postTransaction.getRequestCondition();
					CreteriasType cts2 = postTransaction.getResponseCondition();
					ReturnCode returnCode = postTransaction.getReturnCode();
					boolean isEnter = false;

					try
					{
						isEnter = FrameworkUtil.isFitReturn(returnCode,transReturn);
					}
					catch(Exception e)
					{
						logger.error("handlePostEvent:"+e.getMessage(),e);
						return Return.valueOf(-1,e.getLocalizedMessage(),"",e);
					}
					
					if(isEnter==true)
					{
						try
						{
							isEnter=FrameworkUtil.isFitCreterias(cts,cdoRequest);
							if(isEnter)
							{
								isEnter = FrameworkUtil.isFitCreterias(cts2,cdoResponse);
							}
						}
						catch(Exception e)
						{
							logger.error("handlePostEvent:"+e.getMessage(),e);
							return Return.valueOf(-1,e.getLocalizedMessage(),"",e);
						}
						if(isEnter==true)
						{
							Transaction[] transactions = postTransaction.getTransaction();
							if(transactions!=null && transactions.length>0)
							{
								for(Transaction transaction:transactions)
								{
									Return ret = this.handleTransaction(transaction,cdoRequest,cdoResponse,false,transReturn);
									if(ret.getCode()!=0){return ret;}
								}
							}
						}
					}
				}
				PostEventGroup postEventGroup = item.getPostEventGroup();
				if(postEventGroup!=null && postEventGroup.getSyn()==true)
				{
						CreteriasType cts = postEventGroup.getRequestCondition();
						CreteriasType cts2 = postEventGroup.getResponseCondition();
						boolean isEnter = false;
						try
						{
							isEnter=FrameworkUtil.isFitCreterias(cts,cdoRequest);
							if(isEnter)
							{
								isEnter = FrameworkUtil.isFitCreterias(cts2,cdoResponse);
							}
						}
						catch(Exception e)
						{
							logger.error("handlePostEvent:"+e.getMessage(),e);
							return Return.valueOf(-1,e.getLocalizedMessage(),"",e);
						}
						if(isEnter==true)
						{
							For[] fors = postEventGroup.getFor();
							if(fors!=null && fors.length>0)
							{
								for(For for1:fors)
								{
									Return ret = this.handleFor(for1,cdoRequest,cdoResponse,transReturn);
									if(ret.getCode()!=0){return ret;}
								}
							}
						}
				}
			}
		}
		catch(Exception e)
		{
			logger.error("handlePostEvent:"+e.getMessage(),e);
			return Return.valueOf(-1,e.getLocalizedMessage(),"",e);
		}
		return Return.OK;
	}


	/**
	 * 0:成功或者没有事务定义
	 * 1:有同步执行的事件未执行成功
	 * -1:发生系统错误
	 */
	public Return handlePreEvent(String strServiceName,String strTransName,CDO cdoRequest)
	{
		try
		{
			Filter filter = this.getFilter(strServiceName,strTransName);
			if(filter==null)
			{
				return Return.OK;
			}
			PreEvent preEvent = filter.getPreEvent();
			if(preEvent==null)
			{
				return Return.OK;
			}
			PreTransEventTypeItem[] preTransEventItems = preEvent.getPreTransEventTypeItem();
			for(PreTransEventTypeItem item:preTransEventItems)
			{
				PrePushCache prePushCache = item.getPrePushCache();
				if(prePushCache!=null)
				{
					if(prePushCache.getSyn()==true)
					{//同步执行
						CreteriasType cts = prePushCache.getRequestCondition();
						boolean isEnter = FrameworkUtil.isFitCreterias(cts,cdoRequest);
						if(isEnter==true)
						{				
							PushCache[] pushCaches = prePushCache.getPushCache();
							if(pushCaches!=null && pushCaches.length>0)
							{
								for(PushCache pushCache:pushCaches)
								{
									Return ret  = this.pushCache(pushCache,cdoRequest,cdoRequest);
									if(ret.getCode()!=0)
									{
										return Return.valueOf(1,ret.getText());
									}
								}
							}
						}
					}
				}
				PreRemoveCache preRemoveCache = item.getPreRemoveCache();
				if(preRemoveCache!=null)
				{
					if(preRemoveCache.getSyn()==true)
					{
						CreteriasType cts = preRemoveCache.getRequestCondition();
						boolean isEnter = FrameworkUtil.isFitCreterias(cts,cdoRequest);
						if(isEnter==true)
						{
							RemoveCache[] removeCaches = preRemoveCache.getRemoveCache();
							if(removeCaches!=null && removeCaches.length >0)
							{
								for(RemoveCache removeCache:removeCaches)
								{
									Return ret = this.removeCache(removeCache,cdoRequest,cdoRequest);
									if(ret.getCode()!=0)
									{
										return Return.valueOf(1,ret.getText());
									}	
								}
							}
						}
					}
				}
				PreRemoveURLCache preRemoveURLCache = item.getPreRemoveURLCache();
				if(preRemoveURLCache!=null)
				{
					if(preRemoveURLCache.getSyn()==true)
					{
						CreteriasType cts = preRemoveURLCache.getRequestCondition();
						boolean isEnter = FrameworkUtil.isFitCreterias(cts,cdoRequest);
						if(isEnter==true)
						{
							RemoveURLCache[] removeURLCaches = preRemoveURLCache.getRemoveURLCache();
							if(removeURLCaches!=null && removeURLCaches.length>0)
							{
								for(RemoveURLCache removeURLCache:removeURLCaches)
								{
									Return ret = this.removeURLCache(removeURLCache,cdoRequest,cdoRequest);
									if(ret.getCode()!=0)
									{
										return Return.valueOf(1,ret.getText());
									}
								}
							}
						}
					}
				}
				PreLoadCache preLoadCache = item.getPreLoadCache();
				if(preLoadCache!=null)
				{
					if(preLoadCache.getSyn()==true)
					{//同步执行
						CreteriasType cts = preLoadCache.getRequestCondition();
						boolean isEnter = FrameworkUtil.isFitCreterias(cts,cdoRequest);
						if(isEnter==true)
						{
							LoadCache[] loadCaches = preLoadCache.getLoadCache();
							if(loadCaches!=null && loadCaches.length>0)
							{
								for(LoadCache loadCache:loadCaches)
								{
									Return ret = this.loadCache(loadCache,cdoRequest,cdoRequest);
									if(ret.getCode()!=0)
									{
										return Return.valueOf(1,ret.getText());
									}
								}
							}	
						}
					}
				}
				PreTransaction preTransaction = item.getPreTransaction();
				if(preTransaction != null)
				{
					if(preTransaction.getSyn()==true)
					{//同步
						CreteriasType cts = preTransaction.getRequestCondition();
						boolean isEnter = FrameworkUtil.isFitCreterias(cts,cdoRequest);
						if(isEnter==true)
						{
							Transaction[] transactions = preTransaction.getTransaction();
							if(transactions!=null && transactions.length>0)
							{
								for(Transaction transaction:transactions)
								{
									Return ret = this.handleTransaction(transaction,cdoRequest,cdoRequest,false,null);
									if(ret.getCode()!=0)
									{
										return Return.valueOf(1,ret.getText());
									}
								}
							}
						}
					}
				}
				PreEventGroup preEventGroup = item.getPreEventGroup();
				if(preEventGroup!=null)
				{
					if(preEventGroup.getSyn()==true)
					{
						CreteriasType cts = preEventGroup.getRequestCondition();
						boolean isEnter = FrameworkUtil.isFitCreterias(cts,cdoRequest);
						if(isEnter==true)
						{
							For[] fors = preEventGroup.getFor();
							if(fors!=null && fors.length>0)
							{
								for(For for1:fors)
								{
									Return ret = this.handleFor(for1,cdoRequest,cdoRequest,null);
									if(ret.getCode()!=0)
									{
										return Return.valueOf(1,ret.getText());
									}
								}
							}
						}
					}
				}
			}
			EventTask eventTask = new EventTask(EventTask.EVENT_SCENE_BEFORE);
			eventTask.setServiceName(strServiceName);
			eventTask.setTransName(strTransName);
			eventTask.setTransRequest(cdoRequest);
			eventTask.setTransResponse(cdoRequest);
			eventTask.setOtherTask(preEvent);
			serviceBus.getEventHandler().addEventTask(eventTask);
		}
		catch(Exception e)
		{
			logger.error("handlePreEvent:"+e.getMessage(),e);
			return Return.valueOf(-1,e.getLocalizedMessage());
		}
		return Return.OK;
	}
	
	/**
	 * 	 * 0:cache并且从cache server中取得值,strExt的值为cache的key
	 * 1:不cache
	 * 2:cache,但cache server中无对应的值,
	 * 3 通过Reload做刷新cache
	 * -1:取cache时发生系统错误
	 */
	public Return handlePreTransCache(String strServiceName,String strTransName,CDO cdoRequest,CDO cdoResponse)
	{
		Filter filter = this.getFilter(strServiceName,strTransName);
		if(filter==null)
		{
			return Return.valueOf(1,"no Filter");			
		}
		TransCache transCache = filter.getTransCache();
		if(transCache==null)
		{
			return Return.valueOf(1,"no Filter");
		}
		if(cdoRequest.exists(RELOAD_FLAG))
		{
			cdoRequest.remove(RELOAD_FLAG);
			return Return.valueOf(3,"must reload");
		}
		boolean isValid = false;
		try
		{
			isValid = FrameworkUtil.isFitCreterias(transCache.getRequestCondition(),cdoRequest);
		}
		catch(Exception e)
		{
			logger.warn(e);			
		}
		if(isValid == false)
		{
			return Return.valueOf(1,"不需要cache");
		}
//		objExtTransDefine.setValue(transCache);

		String strKey = null;
		try
		{
			strKey = transCache.getKey(cdoRequest);
		}
		catch(Exception e)
		{
			logger.warn(e);
		}
		if(strKey==null)
		{
			return Return.valueOf(-1,"Can not generate cache key "+strServiceName+"."+strTransName);
		}		
//		strExtCacheKey.setValue(strKey);
		
		String strCacheId = transCache.getCacheId();
		if(strCacheId==null)
		{
			strCacheId = transCache.getFilter().getFilterDefine().getDefaultCacheId();
		}
		
		if(strCacheId==null)
		{
			strCacheId = transCache.getFilter().getFilterDefine().getDefaultCacheId();
			return Return.valueOf(-1,"Can not get cache server"+strServiceName+"."+strTransName);
		}
		Object obj = null;
		try
		{
			obj=cacheManager.get(strCacheId,strKey);
		}
		catch(Exception e1)
		{
			logger.error("handlePreTransCache:"+e1.getMessage(),e1);
			return Return.valueOf(2,e1.getLocalizedMessage());
		}
		if(obj==null)
		{
			return Return.valueOf(2,"no cache value");
		}

		try
		{
			transCache.handleCacheValue(obj,cdoResponse);
		}
		catch(Exception e)
		{
			return Return.valueOf(2,"Can not set value to response: "+strServiceName+"."+strTransName);
		}

		return Return.OK;
	}
	public void handlePostTransCache(String strServiceName,String strTransName,CDO cdoRequest,CDO cdoResponse)
	{
		Filter filter = this.getFilter(strServiceName,strTransName);
		if(filter==null)
		{
			return;			
		}
		TransCache transCache = filter.getTransCache();
		if(transCache==null)
		{
			return;
		}
		boolean isValid = false;
		try
		{
			isValid = FrameworkUtil.isFitCreterias(transCache.getRequestCondition(),cdoRequest);
		}
		catch(Exception e)
		{
			logger.warn(e);			
		}
		if(isValid == false)
		{
			return;
		}
//		objExtTransDefine.setValue(transCache);
		String strKey = null;
		try
		{
			strKey = transCache.getKey(cdoRequest);
		}
		catch(Exception e)
		{
			logger.warn(e);
		}
		if(strKey==null)
		{
			logger.error("Can not generate cache key "+strServiceName+"."+strTransName);
			return;
		}		
//		strExtCacheKey.setValue(strKey);
		
		String strCacheId = transCache.getCacheId();
		if(strCacheId==null)
		{
			strCacheId = transCache.getFilter().getFilterDefine().getDefaultCacheId();
		}
		if(strCacheId==null)
		{
			logger.error("Can not get cache server"+strServiceName+"."+strTransName);
		}

		//cached value
		Object objValue = transCache.getCacheValue(cdoResponse);
		if(objValue==null)
		{
			String strText = "can not generate cache value :"+strServiceName+"."+strTransName;
			if(logger.isDebugEnabled())
			{
				logger.debug(strText);
			}
			return;
		}
        // fix nExpireTime丢失bug
		int nExpireTime = transCache.getExpireTime();
		//根据memcache协议，失效期有三种类型
		// 0 ： 永远不失效
		// 0<nExpireTime<= 60*60*24*30 及30天， 指当前时间的偏移量。
		// nExpireTime > 60*60*24*30 及30天 指失效的时间点。
		// 框架默认的nExpireTime是一个偏移量，为了避免配置的参数大于30天被错误处理，
		// 大于30天的转化成时间点。
		if(nExpireTime >= THIRTY_DAYS) {
			nExpireTime = nExpireTime +(int)System.currentTimeMillis()/1000 ;
		}
		//put value to memcached
		boolean bOK = false;
		try
		{
			bOK = cacheManager.put(strCacheId,strKey,objValue, nExpireTime);
		}
		catch(Exception e)
		{
			logger.error("handlePostTransCache:"+e.getMessage(),e);
		}
	}

	public void handleTask(Task task)
	{
		EventTask eventTask = (EventTask)task.getData();
		Object objTask = eventTask.getOtherTask();
		
		//业务日志
		CDO cdoBusinessLogRequest = eventTask.getBusinessLogReqeust();
		if(eventTask.getBusinessLogReqeust()!=null)
		{
			BusinessLog logDefine = null;
			if(objTask!=null)
			{
				logDefine = (BusinessLog)objTask;
				this.addBusinessLog(eventTask.getServiceName(),eventTask.getTransName(),logDefine,eventTask.getTransRequest(),eventTask.getTransResponse(),eventTask.getTransHandleResult(),eventTask.getEventScene());
			}else
			{
				this.addBusinessLog(cdoBusinessLogRequest);
			}
			return;
		}
		//普通事务
		if(objTask instanceof PreEvent)
		{
			handlePreEvent((PreEvent)objTask,eventTask);
		}else if(objTask instanceof PostEvent)
		{
			handlePostEvent((PostEvent)objTask,eventTask);
		}
		else
		{
			logger.error("unkown task type: "+objTask.getClass().getName());
		}
	}

	public Return start()
	{
		return Return.OK;
	}

	public void stop()
	{
	}
	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public FilterManager()
	{
		hmFilter	= new HashMap<String,HashMap<String,Filter>>(30);
	}


}
