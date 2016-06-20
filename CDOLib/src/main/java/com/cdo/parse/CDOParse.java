package com.cdo.parse;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.cdoframework.cdolib.base.DataType;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.data.cdo.CDOBuffer;

public class CDOParse extends CDOBuffer {
	 /**
     * 
     * 递归调用  依次从右向左合并  相同名称cdo数组 
     * 每次调用，处理同一层级上的cdo
     * 注：
     *   最外层cdo数组不进行合并处理,需要在调用方进行合并处理才正确。
     *  列如：
     *  @see {@linkplain com.cdo.google.handle.ProtoCDOParse#proto2CDO(CDO,List,int)}
     *  @see {@link com.cdo.avro.handle.AvroCDOParse#avro2CDO(CDO,Map,int)}}
     *  数组处理 cdoarr[0].cdo1[0]  cdoarr[0].cdo1[1]  处理成 cdoarr[0].setCDOArrayValue("cod1",cdo1数组)
     *  数组处理 cdoarr[1].cdo2[0]  cdoarr[0].cdo2[1]  处理成 cdoarr[1].setCDOArrayValue("cod2",cdo1数组)
     *  但不会合并最外层的数组
     *    即 cdoarr[0], cdoarr[1] 不会进行合并.
     * avro2CDO proto2CDO
     * @param mapCDO
     * @param level
     * @return
     */
	protected  Map<String,CDO> mergeRightCDO(Map<String,CDO>  mapCDO,int level){
		 Map<String,CDO> childMap=new LinkedHashMap<String, CDO>();
		 Map<String, List<CDO>> arrMap=new HashMap<String, List<CDO>>();//合并CDO数组
		 Map<String, String> arrFieldMap=new HashMap<String, String>();//cdo数组对应的名称
		 String preKey="";
		 String curKey="";
		 int start=0;
		 int mapIndex=0;
		 for(Iterator<Map.Entry<String,CDO>> iterator=mapCDO.entrySet().iterator();iterator.hasNext();){
			 Entry<String, CDO> entry=iterator.next();
			 CDO valueCDO=entry.getValue();
			 String key=entry.getKey();
			 int length=key.split("\\.").length-1;//处理第几级
			 //从右向左进行合并	
			 if(level==length){				
				 int index=key.lastIndexOf(".");
				 String fieldKey="";//字段名称
				 String realkey="";//字符名称 				
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
				 int arrIndex=fieldKey.lastIndexOf("[");//是否是cdo数组
				 if(arrIndex>0 && index>0){
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
	protected  void setCDOValue(CDO cdo,String key,ByteBuffer buffer){	
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
				throw new java.lang.RuntimeException("unsupport object type! key="+key+",type="+dataType);
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
				throw new java.lang.RuntimeException("unsupport object type! key="+key+",type="+dataType);
		}		
	}
}
