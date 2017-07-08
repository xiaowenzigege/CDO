package com.cdo.business.rpc.client;


import io.netty.util.internal.SystemPropertyUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.cdo.google.protocol.GoogleCDO;
import com.cdo.util.constants.Constants;
import com.cdoframework.cdolib.data.cdo.CDO;

public class CallsLinkedHashMap {
	private LinkedHashMap<Integer, Call> calls=new LinkedHashMap<Integer, Call>();
	private final long minute=60*1000;
	//默认设置5分钟
	private final int  maxTimeOut=Math.max(5,SystemPropertyUtil.getInt(Constants.Business.TIME_OUT,5));
	public void put(Integer callId,Call call){
		calls.put(callId, call);	
		setTimeOut();
	}
	
	public Call get(Integer callId){
		return calls.get(callId);
	}
	
	public Call remove(Integer callId){
		return calls.remove(callId);
	}
	
	private void setTimeOut(){
		Iterator<Map.Entry<Integer, Call>> it= calls.entrySet().iterator();
		List<Integer> callIdList=new ArrayList<Integer>();
		while(it.hasNext()){
			Map.Entry<Integer,Call> entry =it.next(); 
			if(((System.currentTimeMillis()-entry.getValue().getSendTime())/minute)>maxTimeOut){
				callIdList.add(entry.getKey());
				continue;
			}
			break;
		}
		//删除超时的	
    	for(int i=0;i<callIdList.size();i++){
    		int callId=callIdList.get(i);
			Call call = calls.get(callId);
			calls.remove(callId);
			
			CDO cdoOutput=new CDO();
			setOutCDO(cdoOutput, "等待超过"+maxTimeOut+"分钟  未获取到响应,客户端设置超时.");
			GoogleCDO.CDOProto.Builder proto=cdoOutput.toProtoBuilder();
			proto.setCallId(callId);
	        call.setRPCResponse(new RPCResponse(proto.build()));	    	
    	} 		
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
