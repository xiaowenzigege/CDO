package com.cdoframework.cdolib.service.framework;

import org.apache.log4j.Logger;

import com.cdoframework.cdolib.base.IdGeneratorFactory;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.servicebus.IService;

/**
 * 改造成用DataTrans
 * @author Aaron
 */
public class IdNodeProcessor 
{
	Logger logger = Logger.getLogger(IdNodeProcessor.class);
	//cdosObjectIdList
	public Return init(IService service)
	{

		try
		{
			CDO cdoRequest  = new CDO();
			CDO cdoResponse = new CDO();
			cdoRequest.setStringValue("strServiceName","SystemService");
			cdoRequest.setStringValue("strTransName","RefreshIdNode");
			Return ret = service.handleDataTrans(cdoRequest,cdoResponse);
			if(ret.getCode()!=0)
			{
				return ret;
			}
			if(cdoResponse.isEmpty("cdosIdNode"))
			{
				logger.warn("There is no id node record");
				return Return.valueOf(-1,"There is no id node record");
			}
			CDO[]  cdosObjectIdList =cdoResponse.getCDOArrayValue("cdosIdNode");
			for(int i=0;i<cdosObjectIdList.length;i++)
			{
				String strObjectId = cdosObjectIdList[i].getStringValue("strObjectId");
				int nNodeId = cdosObjectIdList[i].getIntegerValue("nNodeId");
				IdGeneratorFactory.getInstance().put(strObjectId,nNodeId);
			}
		}
		catch(Exception e)
		{
			return Return.valueOf(-1,"cdosObjectIdList");
		}
		return Return.OK;
	}
}
