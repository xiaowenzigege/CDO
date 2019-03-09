package com.cdoframework.cdolib.util;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.cdoframework.cdolib.base.Utility;
import com.cdoframework.cdolib.data.cdo.CDO;

/**
 * json 与cdo 互相转换工具类
 * @author KenelLiu
 *
 */
public class JsonUtil {
	
	final static String Class_Byte="BYTE";
	final static String Class_Short="SHORT";
	final static String Class_Int="INT";
	final static String Class_Integer="INTEGER";
	final static String Class_Long="LONG";
	final static String Class_Float="FLOAT";
	final static String Class_Double="DOUBLE";
	
	final static String Class_Boolean="BOOLEAN";
	final static String Class_String="STRING";
	final static String Class_Time="TIME";
	final static String Class_Date="DATE";
	final static String Class_DateTime="DATETIME";
	final static String Class_File="FILE";
	final static String Class_CDO="CDO";
	
	static class Empty{}
	/**
	 * 
	 * @param strJSON json字符串格式
	 * @return 将字符串json格式转换成CDO对象,会将strJSON中key对应value都转换成String
	 * @throws JSONException
	 */
	public static CDO json2CDO(String strJSON) throws JSONException{		
		return json2CDO(strJSON,Empty.class,Class_String);
	}
	/**
	 * 
	 * @param strJSON json字符串格式
	 * @param cls  定义 了strJSON中key的类型,数据转换时,会将key对应value转换成key的类型数据.
	 *   如 : strJSON={"key1":"value1","key2":20} 在cls中   定义了 String key1, int key2 变量,
	 *   则    转换时 将strJSON中key1对应value1转成CDO中string对象,key2对应value转成CDO中int对象
	 * @return CDO 对象
	 * @throws JSONException
	 */
	public static CDO json2CDO(String strJSON,Class<?> cls) throws JSONException{
		return json2CDO(strJSON,cls,null);
	}
	/**
	 * 
	 * @param strJSON json字符串格式
	 * @param cls  定义 了strJSON中key的类型,数据转换时,会将key对应value转换成key的类型数据.
	 *   如 : strJSON={"key1":"value1","key2":20} 在cls中   定义了 String key1, int key2 变量,
	 *   则    转换时 将strJSON中key1对应value1转成CDO中string对象,key2对应value转成CDO中int对象
	 * @param defaultType  当cls中未定义 strJSON中key的类型时,默认使用此类型．
	 * @return
	 * @throws JSONException
	 */
	public static CDO json2CDO(String strJSON,Class<?> cls,String defaultType) throws JSONException{
		if(strJSON==null)
			return null;
		if(!strJSON.startsWith("{") && !strJSON.startsWith("\r{") && !strJSON.startsWith("\r\n{")){
			throw new JSONException(strJSON+" is not jsonObject");
		} 
		if(cls==null)
			 throw new NullPointerException("json2CDO depend on Class is null");
		
		JSONObject jsonObj = String2Json(strJSON); // 转换成JSONObject
		CDO cdoRequest = new CDO();	
		Set<String> clsFieldsName=new HashSet<String>();
		try{			
			Field[] fields=cls.getDeclaredFields();
			for(int i=0;i<fields.length;i++){
				clsFieldsName.add(fields[i].getName());
			}	
			setCDOFromJson(jsonObj, cdoRequest,cls,clsFieldsName,defaultType);
		}finally{
			clsFieldsName.clear();
			clsFieldsName=null;
		}
		return cdoRequest;
	}

	/**
	 * 
	 * @param strJSONArray JSONArray字符串格式
	 * @return 将字符串JSONArray格式转换成CDO[]对象,会将strJSONArray中key对应value都转换成String
	 * @throws JSONException
	 */
	public static CDO[] jsonArray2CDOArray(String strJSONArray) throws JSONException   {
		return jsonArray2CDOArray(strJSONArray, Empty.class, Class_String);
	}
	
	/**
	 * 
	 * @param strJSONArray JSONArray字符串格式
	 * @param cls  定义 了JSONArray中key的类型,数据转换时,会将key对应value转换成key的类型数据.
	 *   如 : strJSON={"key1":"value1","key2":20} 在cls中   定义了 String key1, int key2 变量,
	 *   则    转换时 将strJSON中key1对应value1转成CDO中string对象,key2对应value转成CDO中int对象
	 * @return
	 * @throws JSONException 
	 */
	public static CDO[] jsonArray2CDOArray(String strJSONArray,Class<?>  cls) throws JSONException {
		return jsonArray2CDOArray(strJSONArray,cls,null);
	}
	/**
	 * 
	 * @param strJSONArray JSONArray字符串格式
	 * @param cls 定义 了JSONArray中key的类型,数据转换时,会将key对应value转换成key的类型数据.
	 *   如 : strJSON={"key1":"value1","key2":20} 在cls中   定义了 String key1, int key2 变量,
	 *   则    转换时 将strJSON中key1对应value1转成CDO中string对象,key2对应value转成CDO中int对象	   
	 * @param defaultType 当cls中未定义 strJSONArray中key的类型时,默认使用此类型．
	 * @return
	 * @throws JSONException 
	 * @throws Exception
	 */
	public static CDO[] jsonArray2CDOArray(String strJSONArray,Class<?>  cls,String defaultType) throws JSONException {
		if (strJSONArray == null || strJSONArray.trim().length() <= 0 || strJSONArray.equalsIgnoreCase("[]")) {
			return new CDO[0];
		}
		if(!strJSONArray.startsWith("[") && !strJSONArray.startsWith("\r[") && !strJSONArray.startsWith("\r\n[")){
			throw new JSONException(strJSONArray+" is not jsonArray");
		} 
		String strJSON= "{\"cdosTmp\":" + strJSONArray + "}";
		CDO parentCDO=json2CDO(strJSON,cls,defaultType);
		return parentCDO.getCDOArrayValue("cdosTmp");
	}
	/**
	 * CDO数组转换成json 数组
	 * @param cdos
	 * @return
	 */
	public static String cdoArray2JsonArray(CDO[] cdos) {
		if (cdos == null) {
			return null;
		}
		StringBuffer buf = new StringBuffer(100);
		buf.append("[");
		for (int i = 0; i < cdos.length; i++) {
			if (i > 0) {
				buf.append(",");
			}
			buf.append(cdos[i].toJSON());
		}
		buf.append("]");
		return buf.toString();
	}
	/**
	 * 
	 * @param strJSON
	 * @return
	 * @throws JSONException
	 */
	private static JSONObject String2Json(String strJSON) throws JSONException {
		JSONObject jsonObj = null;
		try {
			jsonObj = new JSONObject(strJSON); // 偶数个的这里会转换成功
		} catch (org.json.JSONException jex) {
			// 特殊处理,替换1个反斜杠为2个,2个以上的不匹配
			String newStr = strJSON.replaceAll("\\\\{1}", "\\\\\\\\"); 
			jsonObj = new JSONObject(newStr);
		}
		return jsonObj;
	}
	/**
	 * 根据 Json数据格式，得到一个具有构造一个等级结构
	 * 
	 * @throws JSONException
	 */
	private static void setCDOFromJson(JSONObject jsonObj,CDO cdoRequest,Class<?>  cls,Set<String> clsFieldsName,String defaultType) throws JSONException {
		try {
			String key = "";
			Object obj = null;
			CDO subCDO=null;
			for (Iterator<?> it = jsonObj.keys(); it.hasNext();) {
				key = (String) it.next();
				obj = jsonObj.get(key);
				if (obj instanceof JSONObject) {
					subCDO = new CDO();
					cdoRequest.setCDOValue(key, subCDO);
					setCDOFromJson((JSONObject) obj, subCDO,cls,clsFieldsName,defaultType);
				} else if (obj instanceof JSONArray) {
					//数组 里的数据 1为普通数据类型,2 json对象类型,3 不支持数组嵌套数组 ,混合数据
					JSONArray jsonArr=(JSONArray) obj;
					if(jsonArr.length()==0){	
						setEmptyArray(cdoRequest,key,cls,clsFieldsName,defaultType);
						continue;
					}					
				   setCDOListFromJson((JSONArray)obj,cdoRequest,key,cls,clsFieldsName,defaultType);
				} else {
					//设置普通数据类型
					setCommonField(cdoRequest, key, obj,cls,clsFieldsName,defaultType);
				}
			}
		} catch (Exception e) {
			throw new JSONException(e);
		}
	}
	
	
	private static void setCDOListFromJson(JSONArray jsonObj,CDO cdoParent,String key,Class<?>  cls,Set<String> clsFieldsName,String defaultType) throws Exception {
		Object obj = null;
		List<String> commonList=null;
		List<CDO> cdoList=null;
		for (int i = 0; i < jsonObj.length(); i++) {
			obj = jsonObj.get(i);
			if (obj instanceof JSONObject) {
				if(cdoList==null){
					cdoList=new ArrayList<CDO>();
					cdoParent.setCDOListValue(key, cdoList);	
				}				
				CDO subCDO = new CDO();
				cdoList.add(subCDO);
				setCDOFromJson((JSONObject) obj, subCDO,cls,clsFieldsName,defaultType);
			} else if (obj instanceof JSONArray) {
				throw new JSONException("unsupport json Array nesting, [[]] is unsupported");				
			} else {
				if(commonList==null)
					commonList=new ArrayList<String>();
				commonList.add(obj==null?"":obj.toString());
			}
		}
		//设置普通类型数组
		if(commonList!=null){
			setCommonArray(cdoParent, key,commonList,cls,clsFieldsName,defaultType);
		}

	}
	
	private static void setCommonField(CDO cdoRequest,String key,Object values,Class<?>  cls,Set<String> clsFieldsName,String defaultType) throws JSONException{
		try{
			String type=getClassType(cls, key,clsFieldsName, defaultType);									
			switch(type){
				case Class_Byte:
					 cdoRequest.setByteValue(key,Utility.parseByteValue(values));
					 break;
				case Class_Short:
					 cdoRequest.setShortValue(key, Utility.parseShortValue(values));
					 break;
				case Class_Int:
					  cdoRequest.setIntegerValue(key, Utility.parseIntegerValue(values));
					  break;
				case Class_Integer:
					  cdoRequest.setIntegerValue(key, Utility.parseIntegerValue(values));
					  break;
				case Class_Long:
					 cdoRequest.setLongValue(key, Utility.parseLongValue(values));
					 break;
				case Class_Float:
					cdoRequest.setFloatValue(key,  Utility.parseFloatValue(values));
					break;
				case Class_Double:	
					cdoRequest.setDoubleValue(key, Utility.parseDoubleValue(values));
					break;
				case Class_Boolean:	
					cdoRequest.setBooleanValue(key,Utility.parseBooleanValue(values));
					break;
				case Class_String:
					cdoRequest.setStringValue(key,Utility.parseStingValue(values));
					break;
				case Class_Time:
					cdoRequest.setTimeValue(key, Utility.parseTimeValue(values));
					break;
				case Class_Date:
					cdoRequest.setDateValue(key, Utility.parseDateValue(values));
					break;
				case Class_DateTime:	
					cdoRequest.setDateTimeValue(key,Utility.parseDateTimeValue(values));
					break;
				case Class_File:
					cdoRequest.setFileValue(key, new File(Utility.parseStingValue(values)));
					break;
				default:
					throw new JSONException("unsupported json to cdo type,Json Key=["+key+"] Json value["+values+"] cast to "+type);							
			}
		}catch(Exception ex){
			throw new JSONException(ex);
		}

	}
	private static void setEmptyArray(CDO cdoRequest,String key,Class<?>  cls,Set<String> clsFieldsName,String defaultType) throws JSONException{
		try{
			String type=getClassType(cls, key,clsFieldsName,defaultType);					
			switch(type){
				case Class_Byte:
					 cdoRequest.setByteArrayValue(key, new byte[0]);
					 break;
				case Class_Short:
					 cdoRequest.setShortArrayValue(key, new short[0]);
					 break;
				case Class_Int:
					  cdoRequest.setIntegerArrayValue(key, new int[0]);
					  break;
				case Class_Integer:
					  cdoRequest.setIntegerArrayValue(key, new int[0]);
					  break;
				case Class_Long:
					 cdoRequest.setLongArrayValue(key, new long[0]);
					 break;
				case Class_Float:
					cdoRequest.setFloatArrayValue(key, new float[0]);
					break;
				case Class_Double:	
					cdoRequest.setDoubleArrayValue(key, new double[0]);
					break;
				case Class_Boolean:	
					cdoRequest.setBooleanArrayValue(key, new boolean[0]);
					break;
				case Class_String:
					cdoRequest.setStringArrayValue(key, new String[0]);
					break;
				case Class_Time:
					cdoRequest.setTimeArrayValue(key, new String[0]);
					break;
				case Class_Date:
					cdoRequest.setDateArrayValue(key, new String[0]);
					break;
				case Class_DateTime:	
					cdoRequest.setDateTimeArrayValue(key, new String[0]);
					break;
				case Class_CDO:
					cdoRequest.setCDOArrayValue(key, new CDO[0]);
					break;
				default:
					throw new JSONException("unsupported json to cdo type="+cls.getDeclaredField(key).getType().getName());							
			}
		}catch(Exception ex){
			throw new JSONException(ex);
		}

	}
	/**
	 * 设置常规类型数组
	 * @param cdoRequest
	 * @param key
	 * @param values
	 * @param json2CDOType
	 * @param cls
	 * @throws JSONException
	 */
	private static void setCommonArray(CDO cdoRequest,String key,List<String> commonList,Class<?>  cls,Set<String> clsFieldsName,String defaultType) throws JSONException{
		String[] values=commonList.toArray(new String[commonList.size()]);
		try{
			String type=getClassType(cls, key,clsFieldsName,defaultType);
			switch(type){
				case Class_Byte:
					 cdoRequest.setByteArrayValue(key,Utility.parseByteArrayValue(values));
					 break;
				case Class_Short:
					 cdoRequest.setShortArrayValue(key, Utility.parseShortArrayValue(values));
					 break;
				case Class_Int:
					  cdoRequest.setIntegerArrayValue(key, Utility.parseIntegerArrayValue(values));
					  break;
				case Class_Integer:
					  cdoRequest.setIntegerArrayValue(key, Utility.parseIntegerArrayValue(values));
					  break;
				case Class_Long:
					 cdoRequest.setLongArrayValue(key, Utility.parseLongArrayValue(values));
					 break;
				case Class_Float:
					cdoRequest.setFloatArrayValue(key,  Utility.parseFloatArrayValue(values));
					break;
				case Class_Double:	
					cdoRequest.setDoubleArrayValue(key, Utility.parseDoubleArrayValue(values));
					break;
				case Class_Boolean:	
					cdoRequest.setBooleanArrayValue(key,Utility.parseBooleanArrayValue(values));
					break;
				case Class_String:
					cdoRequest.setStringArrayValue(key, Utility.parseStringArrayValue(values));
					break;
				case Class_Time:
					cdoRequest.setTimeArrayValue(key, values);
					break;
				case Class_Date:
					cdoRequest.setDateArrayValue(key, values);
					break;
				case Class_DateTime:	
					cdoRequest.setDateTimeArrayValue(key,values);
					break;
				default:
					throw new JSONException("unsupported json to cdo type,Json Key=["+key+"] Json value["+commonList+"] cast to "+cls.getDeclaredField(key).getType().getName());
			}			
		}catch(Exception ex){
			throw new JSONException(ex);
		}
	}
	
	private static String getClassType(Class<?> cls,String key,Set<String> clsFieldsName,String defaultType) throws NoSuchFieldException, SecurityException {
		if(defaultType!=null){
			if(!clsFieldsName.contains(key)){
				return defaultType; 
			}
		}
		String type=cls.getDeclaredField(key).getType().getSimpleName().toUpperCase();			
		type=type.indexOf("[")>0?type.substring(0,type.indexOf("[")):type;
		return type;
	}
}
