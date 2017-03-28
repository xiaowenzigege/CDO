package com.cdoframework.cdolib.data.cdo;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import com.cdoframework.cdolib.base.DataType;

public class ParseCDOBuffer extends CDOBuffer {
	
	
	/**
	 *  根据fieldName 中的[.]符号 依次递归调用
	 * @param cdo
	 * @param buffer
	 * @param fieldName
	 */
	protected void parseHierarchicCDO(CDO cdo,ByteBuffer buffer,String fieldName){
		 int index=fieldName.indexOf(".");//.表示层级概念			 		 
		 if(index==-1){
			  //到达最底层,设置数据
			  setCDOValue(cdo, fieldName, buffer);				
		 }else{
			 String preKey=fieldName.substring(0,index);
			 String sufKey=fieldName.substring(index+1);				 
			 int arrIndex=preKey.lastIndexOf("[");
			 if(arrIndex==-1){//普通CDO
				    CDO tmpCDO=null;
				    String cdoKey=preKey;
				    if(!cdo.exists(cdoKey)){
				    	tmpCDO=new CDO();
				    	cdo.setCDOValue(cdoKey, tmpCDO);
				    }else{
				    	tmpCDO=cdo.getCDOValue(cdoKey);
				    }
				    parseHierarchicCDO(tmpCDO,buffer,sufKey);
			 }else{//CDO 数组
				String cdoKey=preKey.substring(0,arrIndex);
				//数组下标
				int cdoIndex=Integer.parseInt(preKey.substring(arrIndex+1, preKey.length()-1));
				
				CDO tmpCDO=null;
			    if(!cdo.exists(cdoKey)){
			    	List<CDO> list=new ArrayList<CDO>();
			    	if(cdoIndex>-1){
			    		//arrIndex=-1  表示 是一个空CDO数组,不需要设置值
			    		tmpCDO=new CDO();			    	
			    		list.add(tmpCDO);
			    	}
			    	cdo.setCDOListValue(cdoKey, list);
			    }else{
			    	List<CDO> list=cdo.getCDOListValue(cdoKey);
			    	if(cdoIndex>(list.size()-1)){
			    		tmpCDO=new CDO();
			    		list.add(tmpCDO);
			    	}else{
			    		tmpCDO=list.get(cdoIndex);
			    	}
			    }
			    if(cdoIndex>-1)//arrIndex=-1  表示 是一个空CDO数组,不需要设置值
			    	parseHierarchicCDO(tmpCDO, buffer,sufKey);				
		 }   
	   }
	}
	
	/**
	 * 
	 * @param cdo
	 * @param key
	 * @param buffer
	 */
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
				buffer.position(1);
				byte b=buffer.get();	
				buffer.clear();
				cdo.setByteValue(key, b);
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
			case DataType.FILE_TYPE:
				setFileValue(cdo, key, buffer);
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
