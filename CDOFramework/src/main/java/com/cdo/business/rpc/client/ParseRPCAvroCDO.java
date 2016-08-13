package com.cdo.business.rpc.client;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.cdo.avro.handle.ParseAvroCDO;
import com.cdo.avro.protocol.AvroCDO;
import com.cdoframework.cdolib.data.cdo.CDO;

public class ParseRPCAvroCDO extends ParseAvroCDO {
    public static ParseRPCAvroCDO AvroRPCParse;
    static{
    	AvroRPCParse=new ParseRPCAvroCDO();
   }
	private  ParseRPCAvroCDO(){		
	}
	
	 void parse(AvroCDO avro,CDO cdoResponse,CDO cdoReturn){		
		avro2CDO(avro.getFields(),cdoResponse,cdoReturn);							
	} 
	
	/**
	 * 数据返回是cdoReturn,cdoResponse 直接分拆处理 
	 * @param fieldsMap
	 * @param level
	 * @param cdoResponse
	 * @param cdoReturn
	 */
	private  void avro2CDO(Map<CharSequence,ByteBuffer> fieldsMap,CDO cdoResponse,CDO cdoReturn){
		
		 for(Iterator<Map.Entry<CharSequence,ByteBuffer>> iterator=fieldsMap.entrySet().iterator();iterator.hasNext();){
			 Entry<CharSequence, ByteBuffer> entry=iterator.next();
			 String key=entry.getKey().toString();
			 ByteBuffer buffer=entry.getValue();
			 
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
