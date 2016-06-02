package com.cdo.avro.schema;

import java.io.UnsupportedEncodingException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.avro.util.Utf8;

import com.cdoframework.cdolib.base.DataType;
import com.cdoframework.cdolib.base.ObjectExt;
import com.cdoframework.cdolib.data.cdo.CDO;

public class AvroCDODeserialize {
	
	
	
	public static CDO fromAvro(AvroCDO avro){		
		CDO cdo=new CDO();
		avro2CDO(cdo,avro.getFields(),avro.getLevel());					
		return cdo;
	} 
	
	private static void avro2CDO(CDO cdo,Map<CharSequence,ByteBuffer> fieldsMap,int maxDot){
		 Map<String,CDO> mapCDO=new LinkedHashMap<String, CDO>();
//		 int maxDot=0;
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
		 Map<String, CDO> retMapCDO=mergeRightCDO(mapCDO, maxDot); 
		 for(Iterator<Map.Entry<String,CDO>> iterator=retMapCDO.entrySet().iterator();iterator.hasNext();){
			   Entry<String, CDO> entry=iterator.next();
			   cdo.setCDOValue(entry.getKey(), entry.getValue());
		 }		
	}

	private static Map<String,CDO> mergeRightCDO(Map<String,CDO>  mapCDO,int maxDot){
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
			 if(maxDot==length){				
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
		 if(maxDot>0){
			  return mergeRightCDO(childMap, (maxDot-1));
		 }
		 return childMap;
	}

	
	@SuppressWarnings("unchecked")
	private static void setCDOValue(CDO cdo,String key,ByteBuffer buffer){	
		 int dataType=buffer.get();	
		 if(dataType<DataType.BOOLEAN_ARRAY_TYPE){
			 //普通类型
			 setCDOValue(cdo, key, dataType, buffer);
		 }else{
			 //数组类型 
			 setCDOValueArr(cdo, key, dataType, buffer);
		 }
		
	}

	private static void setCDOValue(CDO cdo,String key,int dataType,ByteBuffer buffer){
		switch (dataType) {		
			case DataType.BOOLEAN_TYPE:
				int b=buffer.get();
				if(b==1)
					cdo.setBooleanValue(key, true);
				else
					cdo.setBooleanValue(key, false);
				break;
			case DataType.BYTE_TYPE:
				cdo.setByteValue(key, buffer.get());
				break;
			case DataType.SHORT_TYPE:
				cdo.setShortValue(key, buffer.getShort());
				break;
			case DataType.INTEGER_TYPE:
				cdo.setIntegerValue(key, buffer.getInt());
				break;
			case DataType.LONG_TYPE:
				cdo.setLongValue(key, buffer.getLong());
				break;
			case DataType.FLOAT_TYPE:
				cdo.setFloatValue(key, buffer.getFloat());
				break;
			case DataType.DOUBLE_TYPE:
				cdo.setDoubleValue(key, buffer.getDouble());
				break;
			case DataType.STRING_TYPE:
				cdo.setStringValue(key, byte2Str(buffer));
				break;
			case DataType.DATE_TYPE:
				cdo.setDateValue(key, byte2Str(buffer));
				break;
			case DataType.TIME_TYPE:
				cdo.setTimeValue(key, byte2Str(buffer));
				break;	
			case DataType.DATETIME_TYPE:
				cdo.setDateTimeValue(key, byte2Str(buffer));
				break;	
			default:
				throw new java.lang.RuntimeException("unsport object type! key="+key+",type="+dataType);
		}
		
	}
	
	
	private static void setCDOValueArr(CDO cdo,String key,int dataType,ByteBuffer buffer){
		switch (dataType) {		
			case DataType.BOOLEAN_ARRAY_TYPE:
				cdo.setBooleanArrayValue(key, byte2Boolean(buffer));
				break;
			case DataType.BYTE_ARRAY_TYPE:
				cdo.setByteArrayValue(key, byte2ArrBytes(key, buffer));
				break;
			case DataType.SHORT_ARRAY_TYPE:
				cdo.setShortArrayValue(key, byte2Short(buffer));
				break;
			case DataType.INTEGER_ARRAY_TYPE:
				cdo.setIntegerArrayValue(key, byte2Int(buffer));
				break;
			case DataType.LONG_ARRAY_TYPE:
				cdo.setLongArrayValue(key, byte2Long(buffer));
				break;
			case DataType.FLOAT_ARRAY_TYPE:
				cdo.setFloatArrayValue(key, byte2Float(buffer));
				break;
			case DataType.DOUBLE_ARRAY_TYPE:
				cdo.setDoubleArrayValue(key, byte2Double(buffer));
				break;
			case DataType.STRING_ARRAY_TYPE:
				cdo.setStringArrayValue(key, byte2StrArr(buffer));
				break;
			case DataType.DATE_ARRAY_TYPE:
				cdo.setDateArrayValue(key, byte2StrArr(buffer));
				break;
			case DataType.TIME_ARRAY_TYPE:
				cdo.setTimeArrayValue(key, byte2StrArr(buffer));
				break;	
			case DataType.DATETIME_ARRAY_TYPE:
				cdo.setDateTimeArrayValue(key, byte2StrArr(buffer));
				break;	
			default:
				throw new java.lang.RuntimeException("unsport object type! key="+key+",type="+dataType);
		}		
	}	
	
	/**
	 *  第一个字节为 字符串 字段类型 ,之后为字符内容
	 * @param buffer
	 * @return
	 */
	private static String  byte2Str(ByteBuffer buffer){
		buffer.position(1);
		ByteBuffer slice = buffer.slice();
		byte[] dst=new byte[slice.capacity()];
		slice.get(dst);
		return new String(dst,Charset.forName("UTF-8"));
	}
	
	private static String[] byte2StrArr(ByteBuffer buffer){
		 int len=buffer.getShort();
		 String[] arr=new String[len];
		 int totalContentLen=0;
		 int index=3;
		 ByteBuffer slice=null;
		 byte[] dst=null;
		 for(int i=0;i<len;i++){
			 //计算字符串内容所在buffer 下标
			 int contentLen=buffer.getInt(index);			 
			 int pos=(3+4*(i+1))+totalContentLen;
			 totalContentLen=totalContentLen+contentLen;	
			 buffer.position(pos);
			 buffer.limit(pos+contentLen);
			 //截取内容 ,buffer copy出来，直接使用buffer.array[] 有不存在的字符会有乱码
			 slice=buffer.slice();
			 dst=new byte[contentLen];
			 slice.get(dst);			
			
			 arr[i]=new String(dst,Charset.forName("UTF-8"));
			 //移位
			 index=pos+contentLen;			
		 }
		 return arr;
	}
	//-------------------------把[boolean,short,int,long,float,double]数组处理成字节end------------------------//
	/**
	 * 把 字节数组 转换成对应
	 *  boolean,short,int,long,float,double的数组
	 * 
	 * @param object
	 * @return
	 */
	private static boolean[] byte2Boolean(ByteBuffer buffer){
		 int len=buffer.getShort();
		 boolean[] arr=new boolean[len];
		 byte b=0;
		 for(int i=0;i<len;i++){
			 b=buffer.get();
			 if(b==1)
				 arr[i]=true;
			 else
				 arr[i]=false;
		 }
		 return arr;
	} 
	/**
	 * 	//读取buffer  第一个字节为字段类型，
	 *    第二字段4个字节 表示 长度   内容 从第5个字节开始  字节数组
	 *    字节数组一般比较大，所以赋值时，分成4等份,同时处理  
	 * @param key
	 * @param buffer
	 * @return
	 */
	private static byte[] byte2ArrBytes(String key,ByteBuffer buffer){
		int len=buffer.getInt();
		byte[] arr=new byte[len];
		int length=arr.length;	
	
		int mid=length/2+5;
		int quarter=mid/2+5;
		int j=quarter+1+5;
		int m=mid+1+5;
		int quarter3=mid+quarter+5;
		int n=mid+quarter+1+5;					
		for(int i=0;i<=quarter ;i++){
			try{
				arr[i]=buffer.get(i);				
				if(j<=mid){
					arr[j]=buffer.get(j);
					j++;
				}
				if(m<=quarter3){
					arr[m]=buffer.get(m);
					m++;
				}
				if(n<length){
					arr[n]=buffer.get(n);
					n++;
				}				
			}catch(Exception ex){
				throw new RuntimeException("read buffer byte is error,desialze key="+key+","+ex.getMessage(),ex);
			}					
		}		
	  return arr;
	} 
	
	private static short[] byte2Short(ByteBuffer buffer){
		 int len=buffer.getShort();
		 short[] arr=new short[len];		 
		 for(int i=0;i<len;i++){
			 arr[i]=buffer.getShort();
		 }
		 return arr;
	} 	
	
	private static int[] byte2Int(ByteBuffer buffer){
		 int len=buffer.getShort();
		 int[] arr=new int[len];		 
		 for(int i=0;i<len;i++){
			 arr[i]=buffer.getInt();
		 }
		 return arr;
	}
	private static long[] byte2Long(ByteBuffer buffer){
		 int len=buffer.getShort();
		 long[] arr=new long[len];		 
		 for(int i=0;i<len;i++){
			 arr[i]=buffer.getLong();
		 }
		 return arr;
	} 	
	
	private static float[] byte2Float(ByteBuffer buffer){
		 int len=buffer.getShort();
		 float[] arr=new float[len];		 
		 for(int i=0;i<len;i++){
			 arr[i]=buffer.getFloat();
		 }
		 return arr;
	} 
	
	private static double[] byte2Double(ByteBuffer buffer){
		 int len=buffer.getShort();
		 double[] arr=new double[len];		 
		 for(int i=0;i<len;i++){
			 arr[i]=buffer.getDouble();
		 }
		 return arr;
	} 
	//-------------------------把字节数组处理成[boolean,short,int,long,float,double]数组------------------------//
}
