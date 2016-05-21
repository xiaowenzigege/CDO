package com.cdoframework.cdolib.datasync.handTrans;


import org.apache.log4j.Logger;

import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.datasync.Task;
import com.cdoframework.cdolib.datasync.TaskInfo;
import com.cdoframework.cdolib.datasync.exception.ExecuteException;
import com.cdoframework.cdolib.datasync.util.DataSynServiceBus;
import com.cdoframework.cdolib.servicebus.ServiceBus;

/**
 * HandTrans 操作Task任务实现类
 */
public class ImplDBSQLTrans extends Task<Boolean>
{
	private static Logger logger = Logger.getLogger(ImplDBSQLTrans.class);
	
	public ImplDBSQLTrans(TaskInfo taskInfo)
	{
		this.taskInfo = taskInfo;
	}

	@Override
	public synchronized Boolean execute() throws ExecuteException {
		return execute(null);
	}
	
	@Override
	public synchronized Boolean repair() throws ExecuteException {
		this.nRepairTimes++;
		
		return this.execute(taskInfo.getCdoRequest().getStringValue("strFailTransName"));
	}
	
	private Boolean execute(String strTransName) throws ExecuteException
	{
		boolean result = false;
		if(taskInfo != null)
		{
			CDO cdoRequest = taskInfo.getCdoRequest();
			if(strTransName != null && !"".equals(strTransName)){
				cdoRequest.setStringValue("strTransName", strTransName);
			}
			CDO cdoResponse = cdoRequest.getCDOValue("cdoResponse");
			
			DataSynServiceBus dataSynServiceBus = DataSynServiceBus.newInstance();
			ServiceBus serviceBus = dataSynServiceBus.getServiceBus();
			
			if(serviceBus != null)
			{
				try
				{
					Return ret = serviceBus.handleTrans(cdoRequest, cdoResponse);
					if(ret.getCode() == 0){
						result = true;
					}
				}
				catch(Exception e)
				{
					logger.error(e.getMessage());
					throw new ExecuteException(e);
				}
			}
			else
			{
				logger.error("[ImplDBSQLTrans serviceBus IS NULL]");
			}
		}
		else
		{
			logger.error("[ImplDBSQLTrans TASK IS NULL]");
		}
		return result;
	}
	
}
