package com.cdoframework.cdolib.datasync.facade;

import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.datasync.Task;
import com.cdoframework.cdolib.datasync.TaskCenter;
import com.cdoframework.cdolib.datasync.TaskFactory;
import com.cdoframework.cdolib.datasync.TaskFailPolicy;
import com.cdoframework.cdolib.datasync.TaskFuture;
import com.cdoframework.cdolib.datasync.TaskInfo;
import com.cdoframework.cdolib.datasync.exception.FactoryException;
import com.cdoframework.cdolib.datasync.exception.HasNoParameterException;
import com.cdoframework.cdolib.framework.CacheHandlerTemplate;
import com.cdoframework.cdolib.parameter.GlobalConfiguration;

/**
 * 同步数据组件的包装类，可以调用该类的submit()方法，具体参考submit(cdo)
 * @author doing_jiang
 *
 */
public class DatasyncFacade {

	/**
	 * CDO中包含：strCacheServerId（缓存服务器名）
	 * 		    strKey（key）
	 * 		    strValue(value)
	 *          nTaskType(数据操作类型)
	 *          nMaxRepeatTimes(同步操作最大次数，默认为2)
	 *          nExpairTimes(缓存保留时间，可选)
	 * @param cdo
	 * @return
	 * @throws FactoryException
	 * @throws HasNoParameterException
	 */
	@SuppressWarnings("unchecked")
	public static TaskFuture submit(CDO cdo) throws FactoryException, HasNoParameterException {
		TaskInfo taskInfo = analyseCDOToTaskInfo(cdo);
		
		return submit(taskInfo);
	}

	/**
	 * @param cdo
	 * @return
	 */
	private static TaskInfo analyseCDOToTaskInfo(CDO cdo) throws HasNoParameterException{
		checkParameters(cdo);
		
		int nMaxRepeatTimes = 2;
		String strValue = null;
		if(cdo.exists("nMaxRepeatTimes")){
			nMaxRepeatTimes = cdo.getIntegerValue("nMaxRepeatTimes");
		}
		else{
			nMaxRepeatTimes = GlobalConfiguration.getInstance().getMemcacheFailedRetryDefaultTime();
		}
		
		if(cdo.exists("strValue")){
			strValue = cdo.getStringValue("strValue");
		}
		
		String strCacheServerId = cdo.getStringValue("strCacheServerId");
		String strKey = cdo.getStringValue("strKey");
		int nTaskType = cdo.getIntegerValue("nTaskType");
		CacheHandlerTemplate cacheHandler = new CacheHandlerTemplate(strCacheServerId);
		TaskInfo taskInfo = new TaskInfo(nTaskType, strKey, strValue, cacheHandler, new TaskFailPolicy(nMaxRepeatTimes));
		if(!cdo.exists("nExpairTimes")){
			taskInfo.setExpairTimes(cdo.getIntegerValue("nExpairTimes"));
		}
		
		if(nTaskType == Task.TYPE_HANDTRANS){
			/*** 判断是否有CDO类型的 cdoResponse 参数 ***/
			if(cdo.exists("cdoResponse") && cdo.getCDOValue("cdoRequest") != null){
				taskInfo.setCdoRequest(cdo.getCDOValue("cdoRequest"));
			}
		}
		return taskInfo;
	}

	/**
	 * @param cdo
	 * @throws HasNoParameterException
	 */
	private static void checkParameters(CDO cdo) throws HasNoParameterException {
		checkParameter(cdo, "strCacheServerId");
		checkParameter(cdo, "strKey");
		checkParameter(cdo, "nTaskType");
	}

	/**
	 * @param cdo
	 * @throws HasNoParameterException
	 */
	private static void checkParameter(CDO cdo, String strKey) throws HasNoParameterException {
		if(!cdo.exists(strKey)){
			throw new HasNoParameterException("参数："+ strKey +" 不存在！");
		}
	}

	/**
	 * 调用该方法前请确保ServiceBus已被注入到DataSynServiceBus中
	 * @param cdoRequest strFailTransName为数据同步失败时调用的strTransName为null时调用strTransName指向的trans
	 * @param cdoResponse
	 * @return
	 * @throws FactoryException
	 * @throws HasNoParameterException
	 */
	public static TaskFuture submit(CDO cdoRequest, CDO cdoResponse) throws FactoryException, HasNoParameterException {
		cdoRequest.setCDOValue("cdoResponse", cdoResponse);
		return submit(cdoRequest);
	}

	public static TaskFuture submit(String strKey, Object objValue,
			int nMaxRepeatTimes, int nTaskType, CacheHandlerTemplate cacheHandlerTemplate, Integer nExpireTime) throws FactoryException {
		
		TaskInfo taskInfo = new TaskInfo(nTaskType, strKey, objValue, cacheHandlerTemplate, new TaskFailPolicy(nMaxRepeatTimes));
		if(nExpireTime != null){
			taskInfo.setExpairTimes(nExpireTime);
		}
		return submit(taskInfo);
	}

	public static TaskFuture submit(TaskInfo taskInfo) throws FactoryException {
		Task task = TaskFactory.createTaskFactory(taskInfo);
		
		return TaskCenter.getInstance().submit(task);
	}
	
	
	
}
