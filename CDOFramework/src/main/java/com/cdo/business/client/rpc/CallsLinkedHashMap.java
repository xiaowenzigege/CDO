package com.cdo.business.client.rpc;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.cdo.google.protocol.GoogleCDO;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.servicebus.ITransService;

public class CallsLinkedHashMap {
	private LinkedHashMap<Integer, Call> calls=new LinkedHashMap<Integer, Call>();
	private long hourMillis=2*60*60*1000;
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
			if(((entry.getValue().getSendTime()-System.currentTimeMillis())/hourMillis)>1){
				callIdList.add(entry.getKey());
			}else{				
				break;
			}
		}
		//删除超时的	
    	for(int i=0;i<callIdList.size();i++){
    		int callId=callIdList.get(i);
			Call call = calls.get(callId);
			calls.remove(callId);
			
			CDO cdoOutput=new CDO();
			setOutCDO(cdoOutput, "等待超过2小时 未获取到响应,设置超时.", callId);
			GoogleCDO.CDOProto proto=cdoOutput.toProto();
	        call.setRpcResponse(proto);	
    	
    	} 		
	}
	
	private void setOutCDO(CDO cdoOutput,String message,int callId){
		
		CDO cdoReturn=new CDO();
		cdoReturn.setIntegerValue(ITransService.CALL_ID, callId);
		cdoReturn.setIntegerValue("nCode",-1);
		cdoReturn.setStringValue("strText",message);
		cdoReturn.setStringValue("strInfo",message);
		
		cdoOutput.setCDOValue("cdoReturn",cdoReturn);
		cdoOutput.setCDOValue("cdoResponse",new CDO());		
	}
}
