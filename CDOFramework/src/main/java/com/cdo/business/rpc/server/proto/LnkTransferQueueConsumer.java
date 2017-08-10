package com.cdo.business.rpc.server.proto;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TransferQueue;

import org.apache.log4j.Logger;

import com.cdo.business.BusinessService;
import com.cdo.google.Header;
import com.cdo.google.ParseProtoCDO;
import com.cdo.google.handle.CDOMessage;
import com.cdo.google.handle.ProtoProtocol;
import com.cdo.google.protocol.GoogleCDO;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.servicebus.IServiceBus;

public class LnkTransferQueueConsumer implements Runnable {
	private static Logger logger=Logger.getLogger(LnkTransferQueueConsumer.class);
	private boolean stop=false;
	private String name;
	private TransferQueue<GoogleCDO.CDOProto> lnkTransQueue;
	private RPCServerHandler handle;
	private IServiceBus serviceBus;
	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public LnkTransferQueueConsumer(String name,TransferQueue<GoogleCDO.CDOProto> lnkTransQueue,RPCServerHandler handle){
		this.name=name;
		this.lnkTransQueue=lnkTransQueue;
		this.handle=handle;
		this.serviceBus=BusinessService.getInstance().getServiceBus();
	}
	@Override
	public void run() {	
		while(true){
			GoogleCDO.CDOProto proto=null;
			try {
				if(logger.isDebugEnabled())
					logger.debug("name="+name+" is waiting to take element....,Thread="+Thread.currentThread().getId());
				proto=lnkTransQueue.take();
				if(logger.isDebugEnabled())							
					logger.debug("name="+name+" received Element....,Thread="+Thread.currentThread().getId());
			} catch (InterruptedException  ex) {
				if(logger.isDebugEnabled())
					logger.debug("name="+name+"  Thread break,sleep 0.5 seconds,continue run()");
				try{Thread.sleep(500);}catch(Exception e){}									
			}catch(Exception ex){
				if(lnkTransQueue==null || stop){
					logger.warn("name="+name+",stop="+stop+",lnkTransQueue="+lnkTransQueue+",break Consumer queue");					
					break;
				}
			}
			if(proto!=null){
				process(proto);					
			}
		}
	}
	
	/**
	 * 为了在客户端比较快速解释，使用固定顺序，采用 cdoReturn放在第一位,cdoResponse放在第二位
	 * 返回的 cdoOutput 只有2个cdo对象,cdoReturn,cdoResponse
	 * cdoReturn 只有 三个数据 
	 * 其余 cdoResponse里的数据
	 * @param proto
	 */
	 void process(GoogleCDO.CDOProto proto){
		 
			//输入数据
			CDO cdoRequest=null;			
			//输出响应数据 
			CDO cdoOutput=new CDO();
			CDO cdoReturn=new CDO();
			CDO cdoResponse=new CDO();	
			Return ret=null;
			//解释cdo
	    	try{
				cdoRequest=ParseProtoCDO.ProtoParse.parse(proto);					
				//处理业务
				ret=serviceBus.handleTrans(cdoRequest, cdoResponse);					 		
				if(ret==null)
					  throw new IOException("ret is null,may be Request method not found,cdoRequest="+cdoRequest);
				
				cdoReturn.setIntegerValue("nCode",ret.getCode());
				cdoReturn.setStringValue("strText",ret.getText());
				cdoReturn.setStringValue("strInfo",ret.getInfo());

				cdoOutput.setCDOValue("cdoReturn",cdoReturn);
				cdoOutput.setCDOValue("cdoResponse", cdoResponse);							    		
	    	}catch(Throwable ex){
			    //处理出现异常 ,返回给错误给客户端
	    		logger.error(ex.getMessage(), ex);		    		
				cdoReturn.setIntegerValue("nCode",-1);
				cdoReturn.setStringValue("strText"," handle Service Error :"+ex.getMessage());
				cdoReturn.setStringValue("strInfo"," handle Service Error :"+ex.getMessage());
				
				cdoOutput.setCDOValue("cdoReturn",cdoReturn);
				cdoOutput.setCDOValue("cdoResponse",cdoResponse);					
	    	}finally{
		    	//同步调用,回写结果
	    		GoogleCDO.CDOProto.Builder resProto=cdoOutput.toProtoBuilder();
	    		resProto.setCallId(proto.getCallId());				
				Header resHeader=new Header();
				resHeader.setType(ProtoProtocol.TYPE_CDO);
				CDOMessage resMessage=new CDOMessage();
				resMessage.setHeader(resHeader);
				resMessage.setBody(resProto.build());

				handle.writeAndFlush(resMessage);
				cdoRequest.deepRelease();
				cdoOutput.deepRelease();
	    	} 	    		
		}	
	
}
