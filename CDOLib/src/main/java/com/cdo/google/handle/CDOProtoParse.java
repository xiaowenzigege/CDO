package com.cdo.google.handle;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;





import com.cdo.avro.protocol.AvroCDO;
import com.cdo.google.protocol.GoogleCDO;
import com.cdo.parse.CDOParse;
import com.cdoframework.cdolib.base.DataType;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.data.cdo.CDOBuffer;

public class CDOProtoParse extends CDOParse{
	
    public static CDOProtoParse ProtoParse;
    static{
    	ProtoParse=new CDOProtoParse();
   }
	private CDOProtoParse(){}
	

	
	public  CDO parse(GoogleCDO.CDOProto proto){		
		CDO cdo=new CDO();
		proto2CDO(cdo,proto.getFieldsList(),proto.getLevel());					
		return cdo;
	} 
	
	private  void proto2CDO(CDO cdo,List<GoogleCDO.CDOProto.Entry> fieldList,int level){
		 Map<String,CDO> mapCDO=new LinkedHashMap<String, CDO>();
		 //将最右边的基础字段合并到   cdo上
		 for(int i=0;i<fieldList.size();i++){
			 GoogleCDO.CDOProto.Entry entry=fieldList.get(i);
			 String key=entry.getName();
			 ByteBuffer buffer = ByteBuffer.wrap(entry.getValue().toByteArray());
			 
			 int index=key.lastIndexOf(".");
			 if(index==-1){//.表示层级概念
				setCDOValue(cdo, key, buffer);
			 }else{
				 String fieldKey=key.substring(index+1);
				 String mapKey=key.substring(0,index);
				 CDO cdoValue=mapCDO.get(mapKey);
				 if(cdoValue==null){
					 cdoValue=new CDO(); 
				 }
				 setCDOValue(cdoValue, fieldKey, buffer);	
				 mapCDO.put(mapKey, cdoValue);
			 }
			 
		 }
		 //将属于同一数组的CDO依次从右向左 进行合并
		 Map<String, CDO> retMapCDO=mergeRightCDO(mapCDO, level); 
		 Map<String, List<CDO>> arrMap=new HashMap<String, List<CDO>>();
		 //处理最外层CDO及 CDO数组
		 for(Iterator<Map.Entry<String,CDO>> iterator=retMapCDO.entrySet().iterator();iterator.hasNext();){
			   Entry<String, CDO> entry=iterator.next();
			   String key=entry.getKey();
			   CDO  valueCDO= entry.getValue();
				int index=key.lastIndexOf("[");
				if(index==-1){
					cdo.setCDOValue(entry.getKey(), entry.getValue());
				}else{
					//数组
					 String fieldKey=key.substring(0,index);
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
			   cdo.setCDOArrayValue(key, list.toArray(new CDO[list.size()]));	
		 }
	}
   
}
