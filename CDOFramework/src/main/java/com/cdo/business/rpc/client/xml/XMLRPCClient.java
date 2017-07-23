package com.cdo.business.rpc.client.xml;

import java.util.Map;
import org.apache.log4j.Logger;

import com.cdo.business.rpc.RPCFile;
import com.cdo.business.rpc.client.ClientHandler;
import com.cdo.business.rpc.client.NettyClientFactory;
import com.cdo.business.rpc.route.CircleRPCQueue;
import com.cdo.business.rpc.zk.ZKRPCClient;
import com.cdo.business.rpc.zk.ZkNodeData;
import com.cdo.business.web.client.ParseHTTPXmlCDO;
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
public class XMLRPCClient extends ZKRPCClient{
	private final static Logger logger=Logger.getLogger(XMLRPCClient.class);		
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
				int retryCount=1;//重试3次				
				while(retryCount<=3){
					rpcHandle=getRPCClient(strServiceName);
					if(rpcHandle!=null) //创建链接成功，退出重试
						break;
					try{Thread.sleep(1000+500*retryCount);}catch(Exception em){}
					retryCount++;
				}
			}	
		  cdoReturn=new CDO();
		  XMLResponse response=(XMLResponse)rpcHandle.handleTrans(cdoRequest);		  
		  ParseHTTPXmlCDO.xmlToCDO(response.getStrXML(), cdoReturn, cdoResponse);			   		   
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
