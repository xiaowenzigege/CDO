package com.cdo.business.rpc.client.proto;

import java.util.Map;
import org.apache.log4j.Logger;

import com.cdo.business.rpc.RPCFile;
import com.cdo.business.rpc.client.ClientHandler;
import com.cdo.business.rpc.route.CircleRPCQueue;
import com.cdo.business.rpc.zk.ZKRPCClient;
import com.cdo.business.rpc.zk.ZkNodeData;
import com.cdo.google.protocol.GoogleCDO;
import com.cdo.util.algorithm.RoundRobinScheduling;
import com.cdo.util.exception.NotEstablishConnectException;
import com.cdo.util.server.Server;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.servicebus.ITransService;

/**
 * 
 * @author KenelLiu
 *
 */
public class RPCClient extends ZKRPCClient{
	private final static Logger logger=Logger.getLogger(RPCClient.class);		
	/**
	 * @see {@link com.cdo.business.rpc.server.RPCServer#handleTrans(CDO,CDO)}
	 * @param cdoRequest
	 * @param cdoResponse
	 * @return
	 */
	public Return handleTrans(CDO cdoRequest, CDO cdoResponse){
		if(!cdoRequest.exists(ITransService.SERVICENAME_KEY)){
			return new Return(-1,"Service Name is null,plealse check strServiceName value");	
		}
	   String strServiceName=cdoRequest.getStringValue(ITransService.SERVICENAME_KEY);
	  
	   CDO cdoReturn=null;
	   try {	
		  ClientHandler rpcHandle=getRPCClient(strServiceName);
		  if(rpcHandle==null){
				int retryCount=1;//重试5次				
				while(retryCount<=5){
					rpcHandle=getRPCClient(strServiceName);
					if(rpcHandle!=null) //创建链接成功，退出重试
						break;
					try{Thread.sleep(1500+500*retryCount);}catch(Exception em){}
					retryCount++;
				}
			}	
		  
		   RPCResponse response=(RPCResponse)rpcHandle.handleTrans(cdoRequest);
			//cdo 内容
		   GoogleCDO.CDOProto proto=response.getCdoProto();
		   cdoReturn=new CDO();
			//解释google buffer
		   ParseRPCProtoCDO.ProtoRPCParse.parse(proto, cdoResponse, cdoReturn);
			//设置响应文件
		   RPCFile.setFile2CDO(cdoResponse, response.getListFile());
		   
		   return new Return(cdoReturn.getIntegerValue("nCode"),cdoReturn.getStringValue("strText"), cdoReturn.getStringValue("strInfo"));
	  	}catch(NotEstablishConnectException ex){
			logger.warn("Service["+strServiceName+"] not registered on zk server");
			return new Return(-99,"Service["+strServiceName+"] not registered on zk server");		  
		} catch (Throwable e) {			
			String strTransName=cdoRequest.exists(ITransService.TRANSNAME_KEY)?cdoRequest.getStringValue(ITransService.TRANSNAME_KEY):"null";					
			logger.error("Request method :strServiceName="+strServiceName+",strTransName="+strTransName+",error="+e.getMessage(),e);
			return new Return(-1,e.getMessage(),e.getMessage());
		}finally{
			if(cdoReturn!=null)
				cdoReturn.deepRelease();
		}		  
	}	
	



	
}
