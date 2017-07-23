package com.cdo.business.rpc.server.xml;

import org.apache.log4j.Logger;

import com.cdo.business.BusinessService;
import com.cdo.google.handle.ProtoProtocol;
import com.cdo.xml.handle.XMLHeader;
import com.cdo.xml.handle.XMLMessage;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.servicebus.ITransService;

import io.netty.channel.Channel;

public class BusinessHandle {
	private static Logger logger=Logger.getLogger(BusinessHandle.class);
	private Channel channel;
	private String strCDORequest;
	private int callId;
	public BusinessHandle(Channel channel,String strCDORequest,int callId){
		this.channel=channel;
		this.strCDORequest=strCDORequest;
		this.callId=callId;
	}
	
	void process(){
		CDO cdoRequest=null;				
		CDO cdoOutput=null;

		try{	
			String strServiceName=null;
			String strTransName=null;
			//解释cdo
	    	try{
				cdoRequest=CDO.fromXML(strCDORequest);
				strServiceName=cdoRequest.exists(ITransService.SERVICENAME_KEY)?cdoRequest.getStringValue(ITransService.SERVICENAME_KEY):"null";
				strTransName=cdoRequest.exists(ITransService.TRANSNAME_KEY)?cdoRequest.getStringValue(ITransService.TRANSNAME_KEY):"null";
				//执行业务逻辑后  输出......
				cdoOutput=handleTrans(cdoRequest,strServiceName,strTransName);	   	    		
	    	}catch(Throwable ex){
			    //解释异常 ,..文件不存在,返回给错误给客户端
	    		logger.error(ex.getMessage(), ex);
	    		if(cdoOutput==null)
	    			cdoOutput=new CDO();
	    		setFailOutCDO(cdoOutput, "strServiceName="+strServiceName+",strTransName="+strTransName+" RPCServer 发生异常:"+ex.getMessage());	    		
	    	}
	    	//同步调用,回写结果	    		    		
			XMLHeader resHeader=new XMLHeader();
			resHeader.setType(ProtoProtocol.TYPE_CDO);
			XMLMessage resMessage=new XMLMessage();
			resMessage.setHeader(resHeader);
			resMessage.setCallId(callId);
			resMessage.setBody(cdoOutput.toXML());
			if(channel!=null)
				channel.writeAndFlush(resMessage);	
			else
			   logger.warn("channel is null,can't send response data back.cdoRequest="+cdoRequest.toXML()+","+cdoOutput);
		}finally{
			cdoRequest.deepRelease();
			cdoOutput.deepRelease();
		}		
	}	
	/**
	 * 为了在客户端比较快速解释，使用固定顺序，采用 cdoReturn放在第一位,cdoResponse放在第二位
	 * 返回的 cdoOutput 只有2个cdo对象,cdoReturn,cdoResponse
	 * cdoReturn 只有 三个数据 
	 * 其余 cdoResponse里的数据
	 * 使用下标  判断 替换 原来的 字符串比较
	 * @param cdoRequest
	 * @param listFile
	 * @param strServiceName
	 * @param strTransName
	 * @return
	 */
	private CDO handleTrans(CDO cdoRequest,String strServiceName,String strTransName){		
		CDO cdoOutput=new CDO();
		CDO cdoResponse=new CDO();
		Return ret=null;
		try{							
			//处理业务
			if(cdoRequest.exists(ITransService.PACKAGE_KEY)){
				String classPath=cdoRequest.getStringValue(ITransService.PACKAGE_KEY)+"."+cdoRequest.getStringValue(ITransService.SERVICENAME_KEY);					
				ITransService transService=(ITransService)Class.forName(classPath).newInstance();
				ret=transService.processTrans(cdoRequest, cdoResponse);	
			}else{
				ret=BusinessService.getInstance().handleTrans(cdoRequest, cdoResponse);
			}
			if(ret==null){			
				setFailOutCDO(cdoOutput," ret is null,Request method :strServiceName="+strServiceName+",strTransName="+strTransName);	
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
			setFailOutCDO(cdoOutput,"strServiceName="+strServiceName+",strTransName="+strTransName+"服务端业务处理异常:"+ex.getMessage());
		}	
		return cdoOutput;
	}

	private void setFailOutCDO(CDO cdoOutput,String message){		
		CDO cdoReturn=new CDO();
		cdoReturn.setIntegerValue("nCode",-1);
		cdoReturn.setStringValue("strText",message);
		cdoReturn.setStringValue("strInfo",message);
		
		cdoOutput.setCDOValue("cdoReturn",cdoReturn);
		cdoOutput.setCDOValue("cdoResponse",new CDO());		
	}
	
}
