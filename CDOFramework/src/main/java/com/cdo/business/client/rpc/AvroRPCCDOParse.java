package com.cdo.business.client.rpc;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.cdo.avro.handle.AvroCDOParse;
import com.cdo.avro.protocol.AvroCDO;
import com.cdoframework.cdolib.data.cdo.CDO;

public class AvroRPCCDOParse extends AvroCDOParse {
    public static AvroRPCCDOParse AvroRPCParse;
    static{
    	AvroRPCParse=new AvroRPCCDOParse();
   }
	private  AvroRPCCDOParse(){		
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
		 Map<String,CDO> mapCDO=new LinkedHashMap<String, CDO>();		 
		 int maxLevel=0;
		 for(Iterator<Map.Entry<CharSequence,ByteBuffer>> iterator=fieldsMap.entrySet().iterator();iterator.hasNext();){
			 Entry<CharSequence, ByteBuffer> entry=iterator.next();
			 String key=entry.getKey().toString();
			 ByteBuffer buffer=entry.getValue();
			 
			 int length=key.split("\\.").length-1;			 
			 int index=key.lastIndexOf(".");
			 String fieldKey=key.substring(index+1);
			 String mapKey=key.substring(0,index);
			 //处理 cdoReturn
			 if(length==1 && mapKey.equals("cdoReturn")){
				 setCDOValue(cdoReturn, fieldKey, buffer);	
				 continue;
			 }		
			 //处理cdoResponse
			 if(length==1 && mapKey.equals("cdoResponse")){
				 setCDOValue(cdoResponse, fieldKey, buffer);	
				 continue;
			 }	
			 maxLevel=maxLevel<length?length:maxLevel;
			 CDO cdoValue=mapCDO.get(mapKey);
			 if(cdoValue==null){
				 cdoValue=new CDO(); 
			 }
			 setCDOValue(cdoValue, fieldKey, buffer);	
			 mapCDO.put(mapKey, cdoValue);		 
		 }
		 if(maxLevel==0)
			 return;
		 //将属于同一数组的CDO依次从右向左 进行合并
		 Map<String, CDO> retMapCDO=mergeRightCDO(mapCDO, maxLevel,2); 
		 Map<String, List<CDO>> arrMap=new HashMap<String, List<CDO>>();
		 //处理cdoResponse
		 for(Iterator<Map.Entry<String,CDO>> iterator=retMapCDO.entrySet().iterator();iterator.hasNext();){
			    Entry<String, CDO> entry=iterator.next();
			    String key=entry.getKey();
			    CDO  valueCDO= entry.getValue();
			    int index=key.lastIndexOf(".");
			    String fieldKey=key.substring(index+1);			   
				index=fieldKey.lastIndexOf("[");
				
				if(index==-1){
					cdoResponse.setCDOValue(fieldKey, entry.getValue());
				}else{
					//数组
					 fieldKey=fieldKey.substring(0,index);
					 List<CDO> list=arrMap.get(fieldKey);
					 if(list==null)
						 list=new ArrayList<CDO>();
					 list.add(valueCDO);
					 arrMap.put(fieldKey, list); 
				}			
		 }	
		 for(Iterator<Map.Entry<String,List<CDO>>> iterator=arrMap.entrySet().iterator();iterator.hasNext();){
			   Entry<String, List<CDO>> entry=iterator.next();
			   String key=entry.getKey();
			   List<CDO> list=entry.getValue();			   
			   cdoResponse.setCDOArrayValue(key, list.toArray(new CDO[list.size()]));	
		 }		 
	}
}
