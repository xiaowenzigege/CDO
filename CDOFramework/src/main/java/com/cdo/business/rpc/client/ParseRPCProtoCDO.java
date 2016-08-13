package com.cdo.business.rpc.client;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.cdo.google.handle.ParseProtoCDO;
import com.cdo.google.protocol.GoogleCDO;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.data.cdo.Field;

public class ParseRPCProtoCDO extends ParseProtoCDO {
    public static ParseRPCProtoCDO ProtoRPCParse;
    static{
    	ProtoRPCParse=new ParseRPCProtoCDO();
   }
	private ParseRPCProtoCDO(){}
	
	 void parse(GoogleCDO.CDOProto proto,CDO cdoResponse,CDO cdoReturn){		
		proto2CDO(proto.getFieldsList(),cdoResponse,cdoReturn);					
	} 	
	/**
	 * 响应数据返回是cdoResponse,cdoReturn 所以可以可以直接分拆处理 
	 * @param fieldList
	 * @param cdoResponse
	 * @param cdoReturn
	 */
	private  void proto2CDO(List<GoogleCDO.CDOProto.Entry> fieldList,CDO cdoResponse,CDO cdoReturn){

		 for(int i=0;i<fieldList.size();i++){
			 GoogleCDO.CDOProto.Entry entry=fieldList.get(i);
			 String key=entry.getName();
			 ByteBuffer buffer = ByteBuffer.wrap(entry.getValue().toByteArray());
			 buffer.clear();			 
			 int index=key.indexOf(".");
			 String cdoKey=key.substring(0,index);
			 String sufKey=key.substring(index+1);	
			 if(cdoKey.equals("cdoReturn")){
				 parseHierarchicCDO(cdoReturn,buffer,sufKey);				 
			 }else{					 
				 parseHierarchicCDO(cdoResponse,buffer,sufKey);
			 }							 
		 }
	}	
}
