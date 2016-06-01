package com.cdo.avro.schema;

import java.nio.ByteBuffer;
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

public class AvroCDOSerialize {
	
	private final static String prefix_Short="$S_";
	private final static String prefix_STR_Time="$T_";
	private final static String prefix_STR_Date="$D_";	
	private final static String prefix_STR_DateTime="$DT_";
	private final static String prefix_STR_Byte="$BY_";
	
	private final static String prefix_Arr_Boolean="$BA_";
	private final static String prefix_Arr_Short="$SA_";
	private final static String prefix_Arr_Int="$NA_";
	private final static String prefix_Arr_Long="$LA_";
	private final static String prefix_Arr_Float="$FA_";
	private final static String prefix_Arr_Double="$DBA_";
	private CDO cdo;
	
	public AvroCDOSerialize(CDO cdo){
		this.cdo=cdo;
	}
	public  AvroCDO toAvro(){
		 Map<CharSequence,Object> map=new LinkedHashMap<CharSequence, Object>();
		 CDO2Avro("", this.cdo, map);		
		 return  AvroCDO.newBuilder().setKey(map).build();
	} 
	
	private  void CDO2Avro(String cdoKey,CDO cdo,Map<CharSequence, Object> map){				 
		if(cdoKey!=null && !cdoKey.trim().equals("")){
				cdoKey=cdoKey+".";
		 }	
		 String fieldKey="";	
		 for(Iterator<Map.Entry<String,ObjectExt>> iterator=cdo.entrySet().iterator();iterator.hasNext();){
			 Entry<String, ObjectExt> entry=iterator.next();
			 fieldKey=cdoKey+entry.getKey();
			 switch (entry.getValue().getType()) {
				case DataType.BOOLEAN_TYPE:	
					map.put(fieldKey, entry.getValue().getBoolean());
					break;
				case DataType.SHORT_TYPE:
					map.put(prefix_Short+fieldKey, entry.getValue().getInteger());
					break;
				case DataType.INTEGER_TYPE:
					map.put(fieldKey, entry.getValue().getInteger());
					break;
				case DataType.LONG_TYPE:
					map.put(fieldKey, entry.getValue().getLength());
					break;
				case DataType.FLOAT_TYPE:
					map.put(fieldKey, entry.getValue().getFloat());
					break;					
				case DataType.DOUBLE_TYPE:
					map.put(fieldKey, entry.getValue().getDouble());
					break;
				//------------字符串处理---------------------//
				case DataType.STRING_TYPE:
					map.put(fieldKey, entry.getValue().getString());
					break;					
				case DataType.TIME_TYPE:
					map.put(prefix_STR_Time+fieldKey, entry.getValue().getTime());
					break;
				case DataType.DATE_TYPE:
					map.put(prefix_STR_Date+fieldKey, entry.getValue().getDate());
					break;
				case DataType.DATETIME_TYPE:
					map.put(prefix_STR_DateTime+fieldKey, entry.getValue().getDateTime());
					break;
				case DataType.BYTE_TYPE:
					map.put(prefix_STR_Byte+fieldKey,entry.getValue().getByte()+"");
					break;	
				//---------string 数组----------------//	
				case DataType.TIME_ARRAY_TYPE:
					map.put(prefix_STR_Time+fieldKey, Arrays.asList(entry.getValue().getTimeArray()));
					break;
				case DataType.DATE_ARRAY_TYPE:
					map.put(prefix_STR_Date+fieldKey, Arrays.asList(entry.getValue().getDateArray()));
					break;
				case DataType.DATETIME_ARRAY_TYPE:
					map.put(prefix_STR_DateTime+fieldKey, Arrays.asList(entry.getValue().getDateTimeArray()));
					break;					
				case DataType.STRING_ARRAY_TYPE:
					map.put(fieldKey,Arrays.asList(entry.getValue().getStringArray()));
					break;		
				//---------字节数组------------------//	
				case DataType.BYTE_ARRAY_TYPE:
					map.put(fieldKey,ByteBuffer.wrap(entry.getValue().getByteArray()));
					break;					
				case DataType.BOOLEAN_ARRAY_TYPE:
					map.put(prefix_Arr_Boolean+fieldKey, ByteBuffer.wrap(bs2Bytes(entry.getValue().getBooleanArray())));
					break;
				case DataType.SHORT_ARRAY_TYPE:
					map.put(prefix_Arr_Short+fieldKey, ByteBuffer.wrap(short2Bytes(entry.getValue().getShortArray())));
					break;
				case DataType.INTEGER_ARRAY_TYPE:
					map.put(prefix_Arr_Int+fieldKey, ByteBuffer.wrap(int2Bytes(entry.getValue().getIntegerArray())));
					break;
				case DataType.LONG_ARRAY_TYPE:
					map.put(prefix_Arr_Long+fieldKey, ByteBuffer.wrap(long2Bytes(entry.getValue().getLongArray())));
					break;
				case DataType.FLOAT_ARRAY_TYPE:
					map.put(prefix_Arr_Float+fieldKey, ByteBuffer.wrap(float2Bytes(entry.getValue().getFloatArray())));
					break;
				case DataType.DOUBLE_ARRAY_TYPE:	
					map.put(prefix_Arr_Double+fieldKey, ByteBuffer.wrap(double2Bytes(entry.getValue().getDoubleArray())));
					break;
				//---------复杂结构 CDO--------------//
				case DataType.CDO_TYPE:											
					CDO2Avro(fieldKey, entry.getValue().getCDO(), map);
					break;
				case DataType.CDO_ARRAY_TYPE:
					CDO[] cdoList=entry.getValue().getCDOArray();
					for(int i=0;i<cdoList.length;i++){
						CDO2Avro(fieldKey+"["+i+"]", cdoList[i], map);
					}
					break;
				default:
					new java.lang.RuntimeException("type is not unsported!");
					break;
				}
			 
		 }
	}
	
	public static CDO fromAvro(AvroCDO avro){		
		CDO cdo=new CDO();
		avro2CDO(avro.getKey(), cdo);					
		return cdo;
	} 
	
	private static void avro2CDO(Map<CharSequence,Object> map,CDO cdo){
		 Map<String,CDO> mapCDO=new LinkedHashMap<String, CDO>();
		 int maxDot=0;
		 for(Iterator<Map.Entry<CharSequence,Object>> iterator=map.entrySet().iterator();iterator.hasNext();){
			 Entry<CharSequence, Object> entry=iterator.next();
			 String key=entry.getKey().toString();
			 String realKey=getKey(key);//去掉前缀
			 String prefix=key.substring(0,(key.length()-realKey.length()));
			 Object object=entry.getValue();
			 int index=realKey.lastIndexOf(".");
			 if(index==-1){//.表示层级概念
				setCDOValue(cdo, key, object);
			 }else{
				 int length=(realKey.split("\\.")).length-2;
				 maxDot=length>maxDot?length:maxDot;
				 String fieldKey=prefix+realKey.substring(index+1);
				 String mapKey=realKey.substring(0,index);
				 CDO cdoValue=mapCDO.get(mapKey);
				 if(cdoValue==null){
					 cdoValue=new CDO(); 
				 }
				 setCDOValue(cdoValue, fieldKey, object);	
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
	private static void setCDOValue(CDO cdo,String key,Object object){	
		
		 if(object instanceof Boolean){
			 //boolean
			 cdo.setBooleanValue(key, ((Boolean)object).booleanValue());
		 }else if(object instanceof Integer){
			 //short int
			 if(key.startsWith(prefix_Short)){
				 cdo.setShortValue(getKey(key), ((Integer)object).shortValue());
			 }else{
				 cdo.setIntegerValue(key,  ((Integer)object).intValue());
			 }
		 }else if(object instanceof Long){
			 //long
			 cdo.setLongValue(key,  ((Long)object).longValue());
		 }else if(object instanceof Float){
			 //float
			 cdo.setFloatValue(key,  ((Float)object).floatValue());
		 }else if(object instanceof Double){
			 //double
			 cdo.setDoubleValue(key,  ((Double)object).doubleValue());
		 }else if(object instanceof  org.apache.avro.util.Utf8){
			 //time date dateTime,String,1个byte
			 String value=new String(((org.apache.avro.util.Utf8)object).getBytes());
			 if(key.startsWith(prefix_STR_Time)){
				 cdo.setTimeValue(getKey(key), value);
			 }else if(key.startsWith(prefix_STR_Date)){
				 cdo.setDateValue(getKey(key), value);
			 }else if(key.startsWith(prefix_STR_DateTime)){
				 cdo.setDateTimeValue(getKey(key), value); 
			 }else if(key.startsWith(prefix_STR_Byte)){
				 cdo.setByteValue(getKey(key), Byte.parseByte(value));
			 }else{
				 cdo.setStringValue(key, value); 
			 }
				 
		 }else if(object instanceof java.util.List<?>){
			 //time date dateTime String
			 List<Utf8> listValue=(List<Utf8>)object;
			 Utf8[] utf8=listValue.toArray(new Utf8[listValue.size()]);
			 String value[]=new String[utf8.length];
			 for(int i=0;i<utf8.length;i++){
				 value[i]=new String(utf8[i].getBytes());
			 }
			 if(key.startsWith(prefix_STR_Time)){
				 cdo.setTimeArrayValue(getKey(key), value);
			 }else if(key.startsWith(prefix_STR_Date)){
				 cdo.setDateArrayValue(getKey(key), value);
			 }else if(key.startsWith(prefix_STR_DateTime)){
				 cdo.setDateTimeArrayValue(getKey(key), value); 
			 }else{
				 cdo.setStringArrayValue(key, value); 
			 }
			 
		 }else if(object instanceof ByteBuffer){
			 //bytes,boolean,short,integer,long,float,double数组
			 ByteBuffer value=(ByteBuffer)object;
			 if(key.startsWith(prefix_Arr_Boolean)){
				 cdo.setBooleanArrayValue(getKey(key), byte2Boolean(value));
			 }else if(key.startsWith(prefix_Arr_Short)){
				 cdo.setShortArrayValue(getKey(key),byte2Short(value));
			 }else if(key.startsWith(prefix_Arr_Int)){
				 cdo.setIntegerArrayValue(getKey(key),byte2Int(value));
			 }else if(key.startsWith(prefix_Arr_Long)){
				 cdo.setLongArrayValue(getKey(key),byte2Long(value));
			 }else if(key.startsWith(prefix_Arr_Float)){
				 cdo.setFloatArrayValue(getKey(key),byte2Float(value));
			 }else if(key.startsWith(prefix_Arr_Double)){
				 cdo.setDoubleArrayValue(getKey(key),byte2Double(value));
			 }else{
				 cdo.setByteArrayValue(key,value.array());
			 }				 
		 }else{
			 throw new java.lang.RuntimeException("unsport object type! key="+key+",type="+object.getClass().getName());
		 }
	}
	

	
	private static String getKey(String key){
		if(key.startsWith(prefix_Short)){
			return key.substring(prefix_Short.length());
		}else if(key.startsWith(prefix_STR_Time)){
			return key.substring(prefix_STR_Time.length());
		}else if(key.startsWith(prefix_STR_Date)){
			return key.substring(prefix_STR_Date.length());
		}else if(key.startsWith(prefix_STR_DateTime)){
			return key.substring(prefix_STR_DateTime.length());
		}else if(key.startsWith(prefix_STR_Byte)){
			return key.substring(prefix_STR_Byte.length());
		}else if(key.startsWith(prefix_Arr_Boolean)){
			return key.substring(prefix_Arr_Boolean.length());
		}else if(key.startsWith(prefix_Arr_Short)){
			return key.substring(prefix_Arr_Short.length());
		}else if(key.startsWith(prefix_Arr_Int)){
			return key.substring(prefix_Arr_Int.length());
		}else if(key.startsWith(prefix_Arr_Long)){
			return key.substring(prefix_Arr_Long.length());
		}else if(key.startsWith(prefix_Arr_Float)){
			return key.substring(prefix_Arr_Float.length());
		}else if(key.startsWith(prefix_Arr_Double)){
			return key.substring(prefix_Arr_Double.length());
		}
		return key;
	}
	/**
	 * 把[boolean,short,int,long,float,double]数组处理成字节
	 * @param objectExt
	 * @param length
	 * @param bytes
	 */
	private  byte[] bs2Bytes(boolean[] bs){		
		ByteBuffer buffer=ByteBuffer.allocate(1*bs.length);
		for (int i = 0; i < bs.length; i++) {
			if(bs[i])
				buffer.put((byte)1);
			else
				buffer.put((byte)0);
		}
		return  buffer.array();
	}
	
	private  byte[] short2Bytes(short[] sh){	
		ByteBuffer buffer=ByteBuffer.allocate(2*sh.length);
		for (int i = 0; i < sh.length; i++) {
			buffer.putShort(sh[i]);
		}
		return  buffer.array();
	}
	
	private  byte[] int2Bytes(int[] ns){	
		ByteBuffer buffer=ByteBuffer.allocate(4*ns.length);
		for (int i = 0; i < ns.length; i++) {
			buffer.putInt(ns[i]);
		}
		return  buffer.array();
	}
	private  byte[] long2Bytes(long[] ls){	
		ByteBuffer buffer=ByteBuffer.allocate(8*ls.length);
		for (int i = 0; i < ls.length; i++) {
			buffer.putLong(ls[i]);
		}
		return  buffer.array();
	}	
	private  byte[] float2Bytes(float[] fs){	
		ByteBuffer buffer=ByteBuffer.allocate(4*fs.length);
		for (int i = 0; i < fs.length; i++) {
			buffer.putFloat(fs[i]);
		}
		return  buffer.array();
	}
	
	private  byte[] double2Bytes(double[] ds){	
		ByteBuffer buffer=ByteBuffer.allocate(8*ds.length);
		for (int i = 0; i < ds.length; i++) {
			buffer.putDouble(ds[i]);
		}
		return  buffer.array();
	}
	//-------------------------把[boolean,short,int,long,float,double]数组处理成字节end------------------------//
	/**
	 * 把 字节数组 转换成对应
	 *  boolean,short,int,long,float,double的数组
	 * 
	 * @param object
	 * @return
	 */
	private static boolean[] byte2Boolean(ByteBuffer object){
		 byte[] value=object.array();
		 int bytesLen=1;
		 boolean[] arr=new boolean[value.length/bytesLen];
		 ByteBuffer buffer=ByteBuffer.allocate(bytesLen);
		 int index=1;
		 int k=0;
		 for(int i=0;i<value.length;i++){
		 	buffer.put(value[i]);
		 	index++;
		 	if(index>bytesLen){
		 		buffer.flip();
		 		arr[k]=buffer.get()==1?true:false;
		 		buffer.clear();
		 		index=1;
		 		k++;
		 	}
		 }
		 return arr;
	} 
	
	private static short[] byte2Short(ByteBuffer object){
		 byte[] value=object.array();
		 int bytesLen=2;
		 short[] arr=new short[value.length/bytesLen];
		 ByteBuffer buffer=ByteBuffer.allocate(bytesLen);
		 int index=1;
		 int k=0;
		 for(int i=0;i<value.length;i++){
		 	buffer.put(value[i]);
		 	index++;
		 	if(index>bytesLen){
		 		buffer.flip();
		 		arr[k]=buffer.getShort();
		 		buffer.clear();
		 		index=1;
		 		k++;
		 	}
		 }
		 return arr;
	} 	
	
	private static int[] byte2Int(ByteBuffer object){
		 byte[] value=object.array();
		 int bytesLen=4;
		 int[] arr=new int[value.length/bytesLen];
		 ByteBuffer buffer=ByteBuffer.allocate(bytesLen);
		 int index=1;
		 int k=0;
		 for(int i=0;i<value.length;i++){
		 	buffer.put(value[i]);
		 	index++;
		 	if(index>bytesLen){
		 		buffer.flip();
		 		arr[k]=buffer.getInt();
		 		buffer.clear();
		 		index=1;
		 		k++;
		 	}
		 }
		 return arr;
	}
	private static long[] byte2Long(ByteBuffer object){
		 byte[] value=object.array();
		 int bytesLen=8;
		 long[] arr=new long[value.length/bytesLen];
		 ByteBuffer buffer=ByteBuffer.allocate(bytesLen);
		 int index=1;
		 int k=0;
		 for(int i=0;i<value.length;i++){
		 	buffer.put(value[i]);
		 	index++;
		 	if(index>bytesLen){
		 		buffer.flip();
		 		arr[k]=buffer.getLong();
		 		buffer.clear();
		 		index=1;
		 		k++;
		 	}
		 }
		 return arr;
	} 	
	
	private static float[] byte2Float(ByteBuffer object){
		 byte[] value=object.array();
		 int bytesLen=4;
		 float[] arr=new float[value.length/bytesLen];
		 ByteBuffer buffer=ByteBuffer.allocate(bytesLen);
		 int index=1;
		 int k=0;
		 for(int i=0;i<value.length;i++){
		 	buffer.put(value[i]);
		 	index++;
		 	if(index>bytesLen){
		 		buffer.flip();
		 		arr[k]=buffer.getFloat();
		 		buffer.clear();
		 		index=1;
		 		k++;
		 	}
		 }
		 return arr;
	} 
	
	private static double[] byte2Double(ByteBuffer object){
		 byte[] value=object.array();
		 int bytesLen=8;
		 double[] arr=new double[value.length/bytesLen];
		 ByteBuffer buffer=ByteBuffer.allocate(bytesLen);
		 int index=1;
		 int k=0;
		 for(int i=0;i<value.length;i++){
		 	buffer.put(value[i]);
		 	index++;
		 	if(index>bytesLen){
		 		buffer.flip();
		 		arr[k]=buffer.getDouble();
		 		buffer.clear();
		 		index=1;
		 		k++;
		 	}
		 }
		 return arr;
	} 
	//-------------------------把字节数组处理成[boolean,short,int,long,float,double]数组------------------------//
}
