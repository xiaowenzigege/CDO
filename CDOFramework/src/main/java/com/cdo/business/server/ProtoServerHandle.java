package com.cdo.business.server;

import org.apache.log4j.Logger;
import com.cdo.business.BusinessService;
import com.cdo.google.handle.ProtoCDOParse;
import com.cdo.google.protocol.GoogleCDO;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.servicebus.ITransService;
import com.google.protobuf.MessageLite;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ProtoServerHandle extends SimpleChannelInboundHandler<MessageLite> {

	private static Logger logger=Logger.getLogger(ProtoServerHandle.class);
	private final  BusinessService serviceBus=BusinessService.getInstance();
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, MessageLite msg)
			throws Exception {
		// TODO Auto-generated method stub
		if(msg instanceof GoogleCDO.CDOProto){
			GoogleCDO.CDOProto proto=(GoogleCDO.CDOProto)msg;
			GoogleCDO.CDOProto returnProto=handleTrans(proto);
			ctx.write(returnProto);
		}
	}

	
	private GoogleCDO.CDOProto handleTrans(GoogleCDO.CDOProto proto){
		
		CDO cdoOutput=new CDO();
		try{
			CDO cdoRequest=ProtoCDOParse.ProtoParse.parse(proto);
			CDO cdoResponse=new CDO();
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
		return cdoOutput.toProto();
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
