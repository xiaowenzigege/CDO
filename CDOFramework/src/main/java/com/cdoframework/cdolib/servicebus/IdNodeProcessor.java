package com.cdoframework.cdolib.servicebus;


import java.sql.Connection;
import java.util.List;

import com.cdoframework.cdolib.base.CycleList;
import com.cdoframework.cdolib.base.IdGeneratorFactory;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.data.cdo.CDOArrayField;
import com.cdoframework.cdolib.database.IDataEngine;


public class IdNodeProcessor 
{

	private CycleList<IDataEngine> clDataEngine;
	public void setDataGroup(CycleList<IDataEngine> clDataEngine){this.clDataEngine=clDataEngine;}
	//cdosObjectIdList
	public Return init()
	{
		String strUpdateSQL = "UPDATE tbNodeId SET nNodeId=nNodeId+1";
		String strSelect	= "SELECT strObjectId,nNodeId FROM tbNodeId";
		// 加载图片
		CDO cdoRequest=new CDO();
		cdoRequest.setStringValue("strTransName","getNodeIdList");
		//获取数据库连接
		if(clDataEngine==null || clDataEngine.size()==0)
		{
			return Return.valueOf(-1,"clDataEngine is null in IdService");
		}
		IDataEngine dataEngine=clDataEngine.get();
		
		Connection conn = null;
		try
		{
			conn=dataEngine.getConnection();
			if(conn == null)
			{
				return Return.valueOf(-1,"IdService can not get connection");
			}
			conn.setAutoCommit(false);
			dataEngine.executeUpdate(conn,strUpdateSQL,cdoRequest);
			CDOArrayField cafRecordSet = new CDOArrayField();
			dataEngine.executeQueryRecordSet(conn,strSelect,cdoRequest,cafRecordSet);
			
			List<CDO> cdosObjectIdList =cafRecordSet.getValue();
			for(int i=0;i<cdosObjectIdList.size();i++)
			{
				String strObjectId = cdosObjectIdList.get(i).getStringValue("strObjectId");
				int nNodeId =cdosObjectIdList.get(i).getIntegerValue("nNodeId");
				IdGeneratorFactory.getInstance().put(strObjectId,nNodeId);
			}
		}
		catch(Exception e)
		{
			return Return.valueOf(-1,"cdosObjectIdList");
		}
		finally
		{
			dataEngine.closeConnection(conn);
		}
		return Return.OK;
	}
}
