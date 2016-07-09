package com.cdo.business.client.rpc;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.avro.ipc.NettyTransceiver;
import org.apache.avro.ipc.specific.SpecificRequestor;
import org.apache.log4j.Logger;

import com.cdo.avro.protocol.AvroCDO;
import com.cdo.avro.protocol.AvroCDOProtocol;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.servicebus.ITransService;

/**
 * 
 * @author KenelLiu
 *
 */
public class AvroPRCClient implements IRPCClient{
	private final static Logger logger=Logger.getLogger(AvroPRCClient.class);
	static NettyTransceiver client=null; 
	static AvroCDOProtocol proxy=null; 
	static {
        NettyTransceiver client;
		try {
			client = new NettyTransceiver(new InetSocketAddress("127.0.0.1",8090));
			proxy =(AvroCDOProtocol)SpecificRequestor.getClient(AvroCDOProtocol.class, client);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
	}
	
	/**
	 * @see {@link com.cdo.business.server.AvroPRCServer.CDORpcProtocol#handleTrans(AvroCDO)}
	 * @param cdoRequest
	 * @param cdoResponse
	 * @return
	 */
	public Return handleTrans(CDO cdoRequest, CDO cdoResponse){		
	  try {		  
		  	if(cdoRequest.getSerialFileCount()>0){
		  		return Return.valueOf(-1, "AvroRPC is unsupported file type");
		  	}
		  	CDO cdoReturn=new CDO();
			AvroCDO avroCDORes=proxy.handleTrans(cdoRequest.toAvro());			
			ParseRPCAvroCDO.AvroRPCParse.parse(avroCDORes, cdoResponse, cdoReturn);	
		    return new Return(cdoReturn.getIntegerValue("nCode"),cdoReturn.getStringValue("strText"), cdoReturn.getStringValue("strInfo"));		
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			String strServiceName=cdoRequest.exists(ITransService.SERVICENAME_KEY)?cdoRequest.getStringValue(ITransService.SERVICENAME_KEY):"null";
			String strTransName=cdoRequest.exists(ITransService.TRANSNAME_KEY)?cdoRequest.getStringValue(ITransService.TRANSNAME_KEY):"null";					
			logger.error("Request method :strServiceName="+strServiceName+",strTransName="+strTransName+",error="+e.getMessage(),e);
			return new Return(-1,e.getMessage(),e.getMessage());
		}		  
	}
}
