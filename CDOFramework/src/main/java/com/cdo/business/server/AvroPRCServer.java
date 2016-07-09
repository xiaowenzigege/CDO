package com.cdo.business.server;

import java.net.InetSocketAddress;

import org.apache.avro.AvroRemoteException;
import org.apache.avro.ipc.NettyServer;
import org.apache.avro.ipc.Server;
import org.apache.avro.ipc.specific.SpecificResponder;
import org.apache.log4j.Logger;

import com.cdo.avro.handle.ParseAvroCDO;
import com.cdo.avro.protocol.AvroCDO;
import com.cdo.avro.protocol.AvroCDOProtocol;
import com.cdo.business.BusinessService;
import com.cdo.util.resource.GlobalResource;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.servicebus.ITransService;

public class AvroPRCServer {
	private static Logger logger=Logger.getLogger(AvroPRCServer.class);
	private static BusinessService serviceBus;
	
	
	public static class CDORpcProtocol implements AvroCDOProtocol {
		@Override
		public AvroCDO handleTrans(AvroCDO avroCDOReq) throws AvroRemoteException {
			CDO cdoOutput=new CDO();
			try{
				CDO cdoRequest=ParseAvroCDO.AvroParse.parse(avroCDOReq);	
				CDO cdoResponse=new CDO();
				serviceBus = BusinessService.getInstance();	
				Return ret=serviceBus.handleTrans(cdoRequest, cdoResponse);
				if(ret==null){
					String strServiceName=cdoRequest.exists(ITransService.SERVICENAME_KEY)?cdoRequest.getStringValue(ITransService.SERVICENAME_KEY):"null";
					String strTransName=cdoRequest.exists(ITransService.TRANSNAME_KEY)?cdoRequest.getStringValue(ITransService.TRANSNAME_KEY):"null";					
					setOutCDO(cdoOutput," ret is null,Request method :strServiceName="+strServiceName+",strTransName="+strTransName);	
					logger.error("ret is null,Request method:strServiceName="+strServiceName+",strTransName="+strTransName);
				}else{
					CDO cdoReturn=new CDO();
					cdoReturn.setIntegerValue("nCode",ret.getCode());
					cdoReturn.setStringValue("strText",ret.getText());
					cdoReturn.setStringValue("strInfo",ret.getInfo());

					cdoOutput.setCDOValue("cdoReturn",cdoReturn);
					cdoOutput.setCDOValue("cdoResponse", cdoResponse);
					
				}
			}catch(Throwable ex){
				logger.error(ex.getMessage(), ex);	
				setOutCDO(cdoOutput,"服务端处理异常:"+ex.getMessage());
			} 	
			return cdoOutput.toAvro();
		}
		
		
		private void setOutCDO(CDO cdoOutput,String message){
			
			CDO cdoReturn=new CDO();
			cdoReturn.setIntegerValue("nCode",-1);
			cdoReturn.setStringValue("strText",message);
			cdoReturn.setStringValue("strInfo",message);
			
			cdoOutput.setCDOValue("cdoReturn",cdoReturn);
			cdoOutput.setCDOValue("cdoResponse",new CDO());
			
		}

    }
	
	private static Server server;
    private static void startServer(){
    	int port=GlobalResource.cdoConfig.getInt("netty.server.port");
        server = new NettyServer(new SpecificResponder(AvroCDOProtocol.class, new CDORpcProtocol()), new InetSocketAddress(port));
        
    }
    public static void main(String[] args){
		try{
			GlobalResource.bundleInitCDOEnv();	
			startServer();
			startService();				        
	        logger.info("AvroPRC Server started......");
	        server.join();
		}catch(Exception ex){
			logger.error(ex.getMessage(),ex);
			System.exit(-1);
			return;
		}	 
    }
    
    private static void startService(){
		Return ret = Return.OK;
		BusinessService app = BusinessService.getInstance();		
		try
		{
			ret = app.start();
		}catch(Throwable e){
			ret	=Return.valueOf(-1,e.getLocalizedMessage());
			logger.error(e.getMessage(),e);
		}
		if(ret.getCode()!=0)
		{
			logger.fatal(ret.getText());
			logger.fatal("||*****************************************||\r\n||*****************************************||\r\n||  started faild and will exit            ||\r\n||*****************************************||\r\n||*****************************************||");
			System.exit(-1);
			return;
		}
		if(logger.isInfoEnabled()){
			logger.info("business started-----------------");
		}
    }
}
