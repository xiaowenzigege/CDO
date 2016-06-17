package com.cdo.avro.serialize;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.cdo.avro.protocol.AvroCDO;
import com.cdoframework.cdolib.base.DataType;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.data.cdo.CDOBuffer;

public class AvroCDODeserialize extends CDOBuffer{
	

	
	public  CDO parseFrom(AvroCDO avro){		
		CDO cdo=new CDO();
		avro2CDO(cdo,avro.getFields(),avro.getLevel());					
		return cdo;
	} 
	
	private  void avro2CDO(CDO cdo,Map<CharSequence,ByteBuffer> fieldsMap,int level){
		 Map<String,CDO> mapCDO=new LinkedHashMap<String, CDO>();
		 for(Iterator<Map.Entry<CharSequence,ByteBuffer>> iterator=fieldsMap.entrySet().iterator();iterator.hasNext();){
			 Entry<CharSequence, ByteBuffer> entry=iterator.next();
			 String key=entry.getKey().toString();
			 ByteBuffer buffer=entry.getValue();
			 
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
		 Map<String, CDO> retMapCDO=mergeRightCDO(mapCDO, level); 
		 Map<String, List<CDO>> arrMap=new HashMap<String, List<CDO>>();
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

	private  Map<String,CDO> mergeRightCDO(Map<String,CDO>  mapCDO,int level){
		 Map<String,CDO> childMap=new LinkedHashMap<String, CDO>();
		 Map<String, List<CDO>> arrMap=new HashMap<String, List<CDO>>();
		 Map<String, String> arrFieldMap=new HashMap<String, String>();
		 String preKey="";
		 String curKey="";
		 int start=0;
		 int mapIndex=0;
		 for(Iterator<Map.Entry<String,CDO>> iterator=mapCDO.entrySet().iterator();iterator.hasNext();){
			 Entry<String, CDO> entry=iterator.next();
			 CDO valueCDO=entry.getValue();
			 String key=entry.getKey();
			 int length=key.split("\\.").length-1;
			 //从右向左进行合并	
			 if(level==length){				
				 int index=key.lastIndexOf(".");
				 String fieldKey="";
				 String realkey="";
				 if(index>0){
					  fieldKey=key.substring(index+1);				 
					  realkey=key.substring(0,index);	
				 }else{
					  fieldKey=key;				 
					  realkey=key;	 
				 }
			 
				 CDO cdoMerge=mapCDO.get(realkey);
				 if(cdoMerge==null){
					 cdoMerge=new CDO();
				 }	 
				 //是否是cdo数组
				 int arrIndex=fieldKey.lastIndexOf("[");
				 if(arrIndex>0){
					 if(start==0){
						 preKey=realkey; 
					 }
					 curKey=realkey;	
					 
					 if(!curKey.equals(preKey)&& !preKey.equals("")){
						 //当前key与前一个key 不相等,说明该cdo数组结束。
						List<CDO> list=arrMap.get(preKey);
						cdoMerge=childMap.get(preKey);
						cdoMerge.setCDOArrayValue(arrFieldMap.get(preKey), list.toArray(new CDO[list.size()]));
						childMap.put(preKey, cdoMerge);
					 }
					 List<CDO> list=arrMap.get(curKey);
					 if(list==null)
						 list=new ArrayList<CDO>();
					 list.add(valueCDO);
					 arrMap.put(realkey, list);
					 //保存cdo数组名称 
					 arrFieldMap.put(realkey, fieldKey.substring(0, arrIndex));
					 preKey=curKey;
					 start++; 
				 }
				 childMap.put(realkey, cdoMerge);				 
			 }else{
				 childMap.put(key, valueCDO);
			 }			 
			 //遍历到结束
			 if(mapIndex==mapCDO.size()-1){
					List<CDO> list=arrMap.get(curKey);
					if(list!=null && list.size()>0){
						CDO cdoMerge=childMap.get(curKey);
						cdoMerge.setCDOArrayValue(arrFieldMap.get(curKey), list.toArray(new CDO[list.size()]));
						childMap.put(curKey, cdoMerge);
					}
			 }
			 mapIndex++;
		 }		
		 if(level>0){
			  return mergeRightCDO(childMap, (level-1));
		 }
		 return childMap;
	}

	
	@SuppressWarnings("unchecked")
	private  void setCDOValue(CDO cdo,String key,ByteBuffer buffer){	
		 int dataType=buffer.get();	
		 buffer.clear();
		 if(dataType<DataType.BOOLEAN_ARRAY_TYPE){
			 //普通类型
			 setCDOValue(cdo, key, dataType, buffer);
		 }else{
			 //数组类型 
			 setCDOValueArr(cdo, key, dataType, buffer);
		 }
		
	}
	private  void setCDOValue(CDO cdo,String key,int dataType,ByteBuffer buffer){
		switch (dataType) {		
			case DataType.BOOLEAN_TYPE:
				  setBooleanValue(cdo, key, buffer);
				break;
			case DataType.BYTE_TYPE:
				cdo.setByteValue(key, buffer.get());
				break;
			case DataType.SHORT_TYPE:			
				 setShortValue(cdo, key, buffer);
				break;
			case DataType.INTEGER_TYPE:
				setIntegerValue(cdo, key, buffer);
				break;
			case DataType.LONG_TYPE:
				setLongValue(cdo, key, buffer);
				break;
			case DataType.FLOAT_TYPE:
				setFloatValue(cdo, key, buffer);
				break;
			case DataType.DOUBLE_TYPE:
				setDoubleValue(cdo, key, buffer);
				break;
			case DataType.STRING_TYPE:
				setStringValue(cdo, key, buffer);
				break;
			case DataType.DATE_TYPE:
				setDateValue(cdo, key, buffer);
				break;
			case DataType.TIME_TYPE:
				setTimeValue(cdo, key, buffer);
				break;	
			case DataType.DATETIME_TYPE:
				setDateTimeValue(cdo, key, buffer);
				break;	
			default:
				throw new java.lang.RuntimeException("unsport object type! key="+key+",type="+dataType);
		}
		
	}
	
	
	private  void setCDOValueArr(CDO cdo,String key,int dataType,ByteBuffer buffer){
		switch (dataType) {		
			case DataType.BOOLEAN_ARRAY_TYPE:
				setBooleanArrayValue(cdo, key, buffer);
				break;
			case DataType.BYTE_ARRAY_TYPE:
				setByteArrayValue(cdo, key, buffer);
				break;
			case DataType.SHORT_ARRAY_TYPE:
				setShortArrayValue(cdo, key, buffer);
				break;
			case DataType.INTEGER_ARRAY_TYPE:
				setIntegerArrayValue(cdo, key, buffer);
				break;
			case DataType.LONG_ARRAY_TYPE:
				setLongArrayValue(cdo, key, buffer);
				break;
			case DataType.FLOAT_ARRAY_TYPE:
				setFloatArrayValue(cdo, key, buffer);
				break;
			case DataType.DOUBLE_ARRAY_TYPE:
				setDoubleArrayValue(cdo, key, buffer);
				break;
			case DataType.STRING_ARRAY_TYPE:
				setStringArrayValue(cdo, key, buffer);
				break;
			case DataType.DATE_ARRAY_TYPE:
				setDateArrayValue(cdo, key, buffer);
				break;
			case DataType.TIME_ARRAY_TYPE:
				setTimeArrayValue(cdo, key, buffer);
				break;	
			case DataType.DATETIME_ARRAY_TYPE:
				setDateTimeArrayValue(cdo, key, buffer);
				break;	
			default:
				throw new java.lang.RuntimeException("unsport object type! key="+key+",type="+dataType);
		}		
	}
}
