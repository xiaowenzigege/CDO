package com.cdoframework.cdolib.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cdoframework.cdolib.base.Utility;
import com.cdoframework.cdolib.data.cdo.CDO;

public class JsonUtil {
	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static DateFormat df = new SimpleDateFormat(DATE_TIME_FORMAT);

	/**
	 * 根据参数前缀 装配CDORequest
	 */
	private static final String CDOTYPE_LONG_PREFIX = "l";
	private static final String CDOTYPE_STRING_PREFIX = "str";
	private static final String CDOTYPE_INTEGER_PREFIX = "n";
	private static final String CDOTYPE_BOOLEAN_PREFIX = "b";
	private static final String CDOTYPE_DATETIME_PREFIX = "dt";
	private static final String CDOTYPE_SHORT_PREFIX = "sh";
	private static final String CDOTYPE_CDO_PREFIX = "cdo";

	/**
	 * json转换为cdo对象
	 * 
	 * @param strJSON
	 * @return CDO
	 * @throws JSONException
	 * @throws Exception
	 */
	public static CDO jsonToCDO(String strJSON) throws JSONException, Exception {
		return jsonToCDO(strJSON, true);
	}

	/**
	 * 将json字符串转换成CDO对象
	 * 
	 * @param strJSON
	 *            转换前的字符串
	 * @param isCDOType
	 *            true 表示转换成CDO对应的类型 必须满足 CDOTYPE_前缀条件 false 表示 按照一般性处理 转换成CDO String类型
	 * @return 转换后的CDO對象
	 * @throws JSONException
	 * @throws Exception
	 */
	public static CDO jsonToCDO(String strJSON, Boolean isCDOType) throws JSONException, Exception {
		CDO cdoRequest = new CDO();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		JSONObject jsonObj = transferToJsonObj(strJSON); // 转换成jsonObjects
		setMapFromJson(jsonObj, resultMap);
		setCDORequest(cdoRequest, resultMap, isCDOType);

		return cdoRequest;
	}

	public static void jsonToCDO(String strJSON, CDO cdoResponse) throws JSONException, Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		JSONObject jsonObj = transferToJsonObj(strJSON); // 转换成jsonObjects
		setMapFromJson(jsonObj, resultMap);
		setCDORequest(cdoResponse, resultMap, true);
	}

	/*
	 * 对json字符串转换成jsonObject对象, 其中包括了转义字符斜杠的处理.
	 */
	private static JSONObject transferToJsonObj(String strJSON) throws JSONException {
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
	 * 转换json数组为CDO数组
	 * 
	 * @param jsonArray格式[{...},..]
	 * @return CDO[]
	 * @throws Exception
	 */
	public static CDO[] jsonArrayToCDOArray(String jsonArray) throws Exception {
		if (jsonArray == null || jsonArray.trim().length() <= 0 || jsonArray.equalsIgnoreCase("[]")) {
			return new CDO[0];
		}
		String tempList = "{\"cdosTempList\":" + jsonArray + "}";
		CDO tempCDO = JsonUtil.jsonToCDO(tempList);
		CDO[] cdosTempList = tempCDO.getCDOArrayValue("cdosTempList");
		return cdosTempList;
	}

	/**
	 * cdos中不支持还嵌套cdos,cdos有且只有cdo
	 * 
	 * @param cdos,key
	 * @return 返回json对象{key:[{...},..]}
	 */
	public static String cdosToJson(String key, CDO[] cdos) {
		if (cdos == null || cdos.length == 0) {
			return null;
		}
		String strJsonArray = cdosToJsonArray(cdos);
		strJsonArray = "{" + key + ":" + strJsonArray + "}";
		return strJsonArray;
	}

	/**
	 * cdos转换成JSONARRAY
	 * 
	 * @param cdos
	 * @return 返回json数组[{...},..]
	 */
	@SuppressWarnings("unchecked")
	public static String cdosToJsonArray(CDO[] cdos) {
		if (cdos == null || cdos.length == 0) {
			return null;
		}
		StringBuffer buf = new StringBuffer(80);
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
	 * 根据 Json数据格式，得到一个具有构造一个等级结构
	 * 
	 * @throws JSONException
	 */
	private static void setMapFromJson(JSONObject jsonObj, Map<String, Object> map) throws JSONException {
		try {
			String key = "";
			Object obj = null;
			Map<String, Object> jsonMap = null;
			List jsonList = null;
			for (Iterator it = jsonObj.keys(); it.hasNext();) {
				key = (String) it.next();
				obj = jsonObj.get(key);
				if (obj instanceof JSONObject) {
					jsonMap = new HashMap();
					map.put(key, jsonMap);
					setMapFromJson((JSONObject) obj, jsonMap);
				} else if (obj instanceof JSONArray) {
					jsonList = new ArrayList();
					map.put(key, jsonList);
					setListFromJson((JSONArray) obj, jsonList);
				} else {
					map.put(key, obj);
				}
			}
		} catch (Exception e) {
			throw new JSONException(e);
		}
	}

	/**
	 * 根据 Json数据格式，得到一个具有构造一个等级结构
	 * 
	 * @param jsonObj
	 * @param list
	 * @throws Exception
	 */
	private static void setListFromJson(JSONArray jsonObj, List list) throws Exception {
		Object obj = null;
		String value = "";
		Map jsonMap = null;
		List jsonList = null;
		for (int i = 0; i < jsonObj.length(); i++) {
			obj = jsonObj.get(i);
			if (obj instanceof JSONObject) {
				jsonMap = new HashMap();
				list.add(jsonMap);
				setMapFromJson((JSONObject) obj, jsonMap);
			} else if (obj instanceof JSONArray) {
				jsonList = new ArrayList();
				list.add(jsonList);
				setListFromJson((JSONArray) obj, jsonList);
			} else {
				list.add(obj);
			}
		}
	}

	/**
	 * 装配CDO对象 (cdoRequest)请求参数
	 * 
	 * @param cdoRequest
	 * @param map
	 * @throws Exception
	 */
	private static void setCDORequest(CDO cdoRequest, Map map, boolean isCDOType) throws Exception {
		Object obj = null;
		CDO tmpCDO = null;
		for (Iterator it = map.keySet().iterator(); it.hasNext();) {
			String key = (String) it.next();
			obj = map.get(key);
			if (obj instanceof Map) {
				tmpCDO = new CDO();
				cdoRequest.setCDOValue(key, tmpCDO);
				setCDORequest(tmpCDO, (Map) obj, isCDOType);
			} else if (obj instanceof List) {
				setCDORequest(cdoRequest, key, (List) obj, isCDOType);
			} else {
				// TODO 根据参数前缀字符 判断要插入的 数据类型
				setCDORequest(cdoRequest, key, obj, null, isCDOType);
			}
		}
	}

	private static void setCDORequest(CDO cdoRequest, String key, List list, boolean isCDOType) throws Exception {
		Object obj = null;
		CDO[] tmpCDOs = new CDO[list.size()];
		if (isCDOType && list.size() == 0) {
			if (key.startsWith(CDOTYPE_STRING_PREFIX)) {
				cdoRequest.setStringArrayValue(key, new String[0]);
			} else if (key.startsWith(CDOTYPE_DATETIME_PREFIX)) {
				cdoRequest.setDateTimeArrayValue(key, new String[0]);
			} else if (key.startsWith(CDOTYPE_CDO_PREFIX)) {
				cdoRequest.setCDOArrayValue(key, new CDO[0]);
			} else if (key.startsWith(CDOTYPE_SHORT_PREFIX)) {
				cdoRequest.setShortArrayValue(key, new short[0]);
			} else if (key.startsWith(CDOTYPE_LONG_PREFIX)) {
				cdoRequest.setLongArrayValue(key, new long[0]);
			} else if (key.startsWith(CDOTYPE_INTEGER_PREFIX)) {
				cdoRequest.setIntegerArrayValue(key, new int[0]);
			} else if (key.startsWith(CDOTYPE_BOOLEAN_PREFIX)) {
				cdoRequest.setBooleanArrayValue(key, new boolean[0]);
			}
		}
		for (int i = 0; i < list.size(); i++) {
			obj = list.get(i);
			if (obj instanceof Map) {
				tmpCDOs[i] = new CDO();
				cdoRequest.setCDOArrayValue(key, tmpCDOs);
				setCDORequest(tmpCDOs[i], (Map) obj, isCDOType);
			} else {// 如果list的内容不为Map形式，则必为主数据类型
				if (i == 0) {
					// 根据参数前缀字符 判断要插入的 数据类型，
					setCDORequest(cdoRequest, key, "", list, isCDOType);
					break;
				}
			}
		}
	}

	/**
	 * 根据 参数key前缀 装配CDO 数据类型
	 * 
	 * @param isCDOType
	 *            true 表示转换成CDO对应的类型 必须满足 CDOTYPE_前缀条件 false 表示 按照一般性处理 转换成CDO String类型
	 * @param cdoRequest
	 * @param key
	 * @param list
	 * @throws Exception
	 */
	public static void setCDORequest(CDO cdoRequest, String key, Object obj, List list, boolean isCDOType)
			throws Exception {
		boolean isArrflag = false;
		if (list != null)
			isArrflag = true;
		try {
			if (isCDOType) {
				if (key.startsWith(CDOTYPE_STRING_PREFIX)) {// 处理 String
					String value = "";
					if (isArrflag) {
						String str[] = new String[list.size()];
						for (int i = 0; i < list.size(); i++) {
							value = getStr(list.get(i));
							str[i] = value;
						}
						cdoRequest.setStringArrayValue(key, str);
					} else {
						value = getStr(obj);
						if (value == null)
							return;
						cdoRequest.setStringValue(key, value);
					}
					return;
				} else if (key.startsWith(CDOTYPE_DATETIME_PREFIX)) {// 处理 DateTime
					String value = "";
					if (isArrflag) {
						String str[] = new String[list.size()];
						for (int i = 0; i < list.size(); i++) {
							value = getStr(list.get(i));
							if (value != null && value.trim().length() > 0) {
								str[i] = Utility.parseDateTimeValue(value);
							} else {
								str[i] = value;
							}
						}
						cdoRequest.setDateTimeArrayValue(key, str);
					} else {
						// 处理mongodb null数据
						value = getStr(obj);
						if (value == null)
							return;
						value = Utility.parseDateTimeValue(value);
						cdoRequest.setDateTimeValue(key, value);
					}
					return;
				} else if (key.startsWith(CDOTYPE_BOOLEAN_PREFIX)) {// 处理 BOOEAN
					String value = "";
					if (isArrflag) {
						boolean b[] = new boolean[list.size()];
						for (int i = 0; i < list.size(); i++) {
							value = getStr(list.get(i));
							b[i] = false;
							if (value != null && value.trim().length() > 0)
								b[i] = Utility.parseBooleanValue(value);
						}
						cdoRequest.setBooleanArrayValue(key, b);
					} else {
						value = getStr(obj);
						if (value == null)
							return;
						boolean b = false;
						if (value != null && value.trim().length() > 0)
							b = Utility.parseBooleanValue(value);
						cdoRequest.setBooleanValue(key, b);
					}
					return;
				} else if (key.startsWith(CDOTYPE_LONG_PREFIX)) {// 处理 Long
					String value = "";
					if (isArrflag) {
						long ls[] = new long[list.size()];
						for (int i = 0; i < list.size(); i++) {
							value = getStr(list.get(i));
							if (value != null && value.trim().length() > 0)
								// ls[i]=new Long(value);
								ls[i] = new Double(value).longValue();
						}
						cdoRequest.setLongArrayValue(key, ls);
					} else {
						value = getStr(obj);
						if (value == null || value.trim().equals(""))
							return;
						long l = 0;
						// l=new Long(value);
						l = new Double(value).longValue();
						cdoRequest.setLongValue(key, l);
					}
					return;
				} else if (key.startsWith(CDOTYPE_INTEGER_PREFIX) || key.startsWith(CDOTYPE_SHORT_PREFIX)) {
					// TODO: 处理Integer,后期修改,因为传过来的有可能
					String value = "";
					if (isArrflag) {
						int ns[] = new int[list.size()];
						for (int i = 0; i < list.size(); i++) {
							value = getStr(list.get(i));
							if (value != null && value.trim().length() > 0)
								// ns[i]=new Integer(value);
								ns[i] = new Double(value).intValue();
						}
						cdoRequest.setIntegerArrayValue(key, ns);
					} else {
						value = getStr(obj);
						if (value == null || value.trim().equals(""))
							return;
						int n = 0;
						// n=new Integer(value);
						n = new Double(value).intValue();
						cdoRequest.setIntegerValue(key, n);
					}
				} else {// 按照一般性处理，都作为String 处理
					String value = "";
					if (isArrflag) {
						String str[] = new String[list.size()];
						for (int i = 0; i < list.size(); i++) {
							value = getStr(list.get(i));
							str[i] = value;
						}
						cdoRequest.setStringArrayValue(key, str);
					} else {
						value = getStr(obj);
						if (value == null)
							return;
						cdoRequest.setStringValue(key, value);
					}
				}
			} else {// 按照一般性处理，都作为String 处理
				String value = "";
				if (isArrflag) {
					String str[] = new String[list.size()];
					for (int i = 0; i < list.size(); i++) {
						value = getStr(list.get(i));
						str[i] = value;
					}
					cdoRequest.setStringArrayValue(key, str);
				} else {
					value = getStr(obj);
					if (value == null)
						return;
					cdoRequest.setStringValue(key, value);
				}
			}
		} catch (Exception ex) {
			throw new Exception(ex);
		}
	}

	private static String getStr(Object obj) {
		if (obj == null)
			return null;
		String str = obj.toString();
		if ("null".equals(str)) {
			return null;
		}
		return str;
	}

	public static void main(String[] args) {
		String aa = "[{\"lCategoryId\":1,\"nPropertyIndex\":0,\"strPropertyName\":\"颜\\\\\\色\",\"bPropertyDisabled\":false,\"bPropertyRequired\":false,\"nPropertyCanQuery\":1,\"bPropertyEditable\":false,\"strPropertyType\":\"SELECT\",\"strPropertyFormat\":\"红色|黄色|蓝色|军绿色|海蓝色\",\"nLevel\":1,\"bPropertyCanQuery\":true,\"strsPropertyFormat\":[\"红色\",\"黄色\",\"蓝色\",\"军绿色\",\"海蓝色\"],\"strPropertyValue\":\"\",\"nPropValueIndex\":-1}, {\"lCategoryId\":1,\"nPropertyIndex\":2,\"strPropertyName\":\"重量\",\"bPropertyDisabled\":false,\"bPropertyRequired\":false,\"nPropertyCanQuery\":1,\"bPropertyEditable\":true,\"strPropertyType\":\"SELECT\",\"strPropertyFormat\":\"超重|重|轻\",\"nLevel\":1,\"bPropertyCanQuery\":true,\"strsPropertyFormat\":[\"超重\",\"重\",\"轻\"],\"strPropertyValue\":\"\",\"nPropValueIndex\":-1}, {\"lCategoryId\":1,\"nPropertyIndex\":4,\"strPropertyName\":\"新旧程度\",\"bPropertyDisabled\":false,\"bPropertyRequired\":true,\"nPropertyCanQuery\":1,\"bPropertyEditable\":true,\"strPropertyType\":\"SELECT\",\"strPropertyFormat\":\"七成新|九成新|五成新|三成新\",\"nLevel\":1,\"bPropertyCanQuery\":true,\"strsPropertyFormat\":[\"七成新\",\"九成新\",\"五成新\",\"三成新\"],\"strPropertyValue\":\"七成新\",\"nPropValueIndex\":0}, {\"lCategoryId\":1,\"nPropertyIndex\":5,\"strPropertyName\":\"价格区间\",\"bPropertyDisabled\":false,\"bPropertyRequired\":false,\"nPropertyCanQuery\":0,\"bPropertyEditable\":true,\"strPropertyType\":\"SELECT\",\"strPropertyFormat\":\"不限|500-1000元|1000-2000元|2000-4000元\",\"nLevel\":1,\"bPropertyCanQuery\":false,\"strsPropertyFormat\":[\"不限\",\"500-1000元\",\"1000-2000元\",\"2000-4000元\"],\"strPropertyValue\":\"\",\"nPropValueIndex\":-1}, {\"lCategoryId\":1,\"nPropertyIndex\":6,\"strPropertyName\":\"适用人群\",\"bPropertyDisabled\":false,\"bPropertyRequired\":true,\"nPropertyCanQuery\":0,\"bPropertyEditable\":false,\"strPropertyType\":\"SELECT\",\"strPropertyFormat\":\"老人|小孩|青年人|中年人\",\"nLevel\":1,\"bPropertyCanQuery\":false,\"strsPropertyFormat\":[\"老人\",\"小孩\",\"青年人\",\"中年人\"],\"strPropertyValue\":\"老人\",\"nPropValueIndex\":0}, {\"lCategoryId\":1,\"nPropertyIndex\":8,\"strPropertyName\":\"长度\",\"bPropertyDisabled\":false,\"bPropertyRequired\":false,\"nPropertyCanQuery\":0,\"bPropertyEditable\":false,\"strPropertyType\":\"SELECT\",\"strPropertyFormat\":\"长|中长|短\",\"nLevel\":1,\"bPropertyCanQuery\":false,\"strsPropertyFormat\":[\"长\",\"中长\",\"短\"],\"strPropertyValue\":\"\",\"nPropValueIndex\":-1}, {\"lCategoryId\":1001,\"nPropertyIndex\":0,\"strPropertyName\":\"按特殊功能选择\",\"bPropertyDisabled\":false,\"bPropertyRequired\":false,\"nPropertyCanQuery\":1,\"bPropertyEditable\":false,\"strPropertyType\":\"SELECT\",\"strPropertyFormat\":\"防抖|面部优先|支持笑脸快门|广角|长焦|微距|手动|支持高清视频拍摄|触摸屏|旋转液晶屏|防水|防摔|防尘|防冻|支持外接闪光灯|支持GPS功能|支持RAW格式文件|无特殊功能\",\"nLevel\":2,\"bPropertyCanQuery\":true,\"strsPropertyFormat\":[\"防抖\",\"面部优先\",\"支持笑脸快门\",\"广角\",\"长焦\",\"微距\",\"手动\",\"支持高清视频拍摄\",\"触摸屏\",\"旋转液晶屏\",\"防水\",\"防摔\",\"防尘\",\"防冻\",\"支持外接闪光灯\",\"支持GPS功能\",\"支持RAW格式文件\",\"无特殊功能\"],\"strPropertyValue\":\"\",\"nPropValueIndex\":-1}, {\"lCategoryId\":1001,\"nPropertyIndex\":1,\"strPropertyName\":\"相机价格\",\"bPropertyDisabled\":false,\"bPropertyRequired\":false,\"nPropertyCanQuery\":1,\"bPropertyEditable\":false,\"strPropertyType\":\"OTHER\",\"strPropertyFormat\":\"0-800元|801-1500元|1501-2500元|2501-4000元|4000元以上\",\"nLevel\":2,\"bPropertyCanQuery\":true,\"strsPropertyFormat\":[\"0-800元\",\"801-1500元\",\"1501-2500元\",\"2501-4000元\",\"4000元以上\"],\"strPropertyValue\":\"\",\"nPropValueIndex\":-1}, {\"lCategoryId\":1001,\"nPropertyIndex\":2,\"strPropertyName\":\"光学变焦\",\"bPropertyDisabled\":false,\"bPropertyRequired\":false,\"nPropertyCanQuery\":1,\"bPropertyEditable\":false,\"strPropertyType\":\"OTHER\",\"strPropertyFormat\":\"定焦|2倍以下|2倍|3倍|3.4倍|3.6倍|4倍|5倍|6倍|7倍|8倍|10倍|12倍|14倍|15倍|18倍|2.5倍|20倍|24倍|26倍|3.8倍|4.6倍|可更换镜头|其它倍数\",\"nLevel\":2,\"bPropertyCanQuery\":true,\"strsPropertyFormat\":[\"定焦\",\"2倍以下\",\"2倍\",\"3倍\",\"3.4倍\",\"3.6倍\",\"4倍\",\"5倍\",\"6倍\",\"7倍\",\"8倍\",\"10倍\",\"12倍\",\"14倍\",\"15倍\",\"18倍\",\"2.5倍\",\"20倍\",\"24倍\",\"26倍\",\"3.8倍\",\"4.6倍\",\"可更换镜头\",\"其它倍数\"],\"strPropertyValue\":\"\",\"nPropValueIndex\":-1}, {\"lCategoryId\":1001,\"nPropertyIndex\":3,\"strPropertyName\":\"上市时间\",\"bPropertyDisabled\":false,\"bPropertyRequired\":false,\"nPropertyCanQuery\":0,\"bPropertyEditable\":false,\"strPropertyType\":\"OTHER\",\"strPropertyFormat\":\"2009年|2008年|2007年|2006年|2005年|2004年|2004年以前\",\"nLevel\":2,\"bPropertyCanQuery\":false,\"strsPropertyFormat\":[\"2009年\",\"2008年\",\"2007年\",\"2006年\",\"2005年\",\"2004年\",\"2004年以前\"],\"strPropertyValue\":\"\",\"nPropValueIndex\":-1}, {\"lCategoryId\":1001,\"nPropertyIndex\":4,\"strPropertyName\":\"颜色\",\"bPropertyDisabled\":false,\"bPropertyRequired\":false,\"nPropertyCanQuery\":0,\"bPropertyEditable\":false,\"strPropertyType\":\"OTHER\",\"strPropertyFormat\":\"军绿色|天蓝色|巧克力色|桔色|浅灰色|浅绿色|浅黄色|深卡其布色|深灰色|深紫色|深蓝色|白色|粉红色|紫罗兰|紫色|红色|绿色|花色|蓝色|褐色|透明|酒红色|黄色|黑色\",\"nLevel\":2,\"bPropertyCanQuery\":false,\"strsPropertyFormat\":[\"军绿色\",\"天蓝色\",\"巧克力色\",\"桔色\",\"浅灰色\",\"浅绿色\",\"浅黄色\",\"深卡其布色\",\"深灰色\",\"深紫色\",\"深蓝色\",\"白色\",\"粉红色\",\"紫罗兰\",\"紫色\",\"红色\",\"绿色\",\"花色\",\"蓝色\",\"褐色\",\"透明\",\"酒红色\",\"黄色\",\"黑色\"],\"strPropertyValue\":\"\",\"nPropValueIndex\":-1}, {\"lCategoryId\":1001,\"nPropertyIndex\":5,\"strPropertyName\":\"显示屏尺寸\",\"bPropertyDisabled\":false,\"bPropertyRequired\":false,\"nPropertyCanQuery\":0,\"bPropertyEditable\":false,\"strPropertyType\":\"OTHER\",\"strPropertyFormat\":\"1.8及以下|2|2.4|2.5|2.6|2.7|2.8|3|3.1|3.2|3.5及以上\",\"nLevel\":2,\"bPropertyCanQuery\":false,\"strsPropertyFormat\":[\"1.8及以下\",\"2\",\"2.4\",\"2.5\",\"2.6\",\"2.7\",\"2.8\",\"3\",\"3.1\",\"3.2\",\"3.5及以上\"],\"strPropertyValue\":\"\",\"nPropValueIndex\":-1}, {\"lCategoryId\":1001,\"nPropertyIndex\":6,\"strPropertyName\":\"像素\",\"bPropertyDisabled\":false,\"bPropertyRequired\":false,\"nPropertyCanQuery\":0,\"bPropertyEditable\":false,\"strPropertyType\":\"OTHER\",\"strPropertyFormat\":\"200万及以下|300万|600万|400万|500万|800万|1000万|700万|1100万|1200万|900万|1300万|1400万|1500万|1600万及以上\",\"nLevel\":2,\"bPropertyCanQuery\":false,\"strsPropertyFormat\":[\"200万及以下\",\"300万\",\"600万\",\"400万\",\"500万\",\"800万\",\"1000万\",\"700万\",\"1100万\",\"1200万\",\"900万\",\"1300万\",\"1400万\",\"1500万\",\"1600万及以上\"],\"strPropertyValue\":\"\",\"nPropValueIndex\":-1}, {\"lCategoryId\":1001,\"nPropertyIndex\":7,\"strPropertyName\":\"储存介质\",\"bPropertyDisabled\":false,\"bPropertyRequired\":false,\"nPropertyCanQuery\":0,\"bPropertyEditable\":false,\"strPropertyType\":\"OTHER\",\"strPropertyFormat\":\"SD卡|高速SD卡|CF卡|高速CF卡|MINISD卡|高速miniSD|RSMMC卡|TF/MICRO SD卡|MS卡(记忆棒)|xD Picture卡|SmartMedia卡|MMC(多媒体卡)|MMC Plus|MMC Micro|微硬盘|其它闪存卡|Memory Stick Micro (M2)|Memory Stick Duo|Memory Stick PRO|Memory Stick PRO Duo|高速Memory Stick PRO|高速Memory Stick PRO Duo|SDHC|其它记忆棒|工业用CF卡\",\"nLevel\":2,\"bPropertyCanQuery\":false,\"strsPropertyFormat\":[\"SD卡\",\"高速SD卡\",\"CF卡\",\"高速CF卡\",\"MINISD卡\",\"高速miniSD\",\"RSMMC卡\",\"TF/MICRO SD卡\",\"MS卡(记忆棒)\",\"xD Picture卡\",\"SmartMedia卡\",\"MMC(多媒体卡)\",\"MMC Plus\",\"MMC Micro\",\"微硬盘\",\"其它闪存卡\",\"Memory Stick Micro (M2)\",\"Memory Stick Duo\",\"Memory Stick PRO\",\"Memory Stick PRO Duo\",\"高速Memory Stick PRO\",\"高速Memory Stick PRO Duo\",\"SDHC\",\"其它记忆棒\",\"工业用CF卡\"],\"strPropertyValue\":\"\",\"nPropValueIndex\":-1}, {\"lCategoryId\":1001,\"nPropertyIndex\":8,\"strPropertyName\":\"电池类型\",\"bPropertyDisabled\":false,\"bPropertyRequired\":false,\"nPropertyCanQuery\":0,\"bPropertyEditable\":false,\"strPropertyType\":\"OTHER\",\"strPropertyFormat\":\"AA电池|专用锂电|镍氢电池|其它|其它电池\",\"nLevel\":2,\"bPropertyCanQuery\":false,\"strsPropertyFormat\":[\"AA电池\",\"专用锂电\",\"镍氢电池\",\"其它\",\"其它电池\"],\"strPropertyValue\":\"\",\"nPropValueIndex\":-1}, {\"lCategoryId\":1001,\"nPropertyIndex\":9,\"strPropertyName\":\"售后服务\",\"bPropertyDisabled\":false,\"bPropertyRequired\":false,\"nPropertyCanQuery\":0,\"bPropertyEditable\":false,\"strPropertyType\":\"OTHER\",\"strPropertyFormat\":\"全国联保|店铺三包|自用(已过保)|其它售后服务\",\"nLevel\":2,\"bPropertyCanQuery\":false,\"strsPropertyFormat\":[\"全国联保\",\"店铺三包\",\"自用(已过保)\",\"其它售后服务\"],\"strPropertyValue\":\"\",\"nPropValueIndex\":-1}, {\"lCategoryId\":1001,\"nPropertyIndex\":10,\"strPropertyName\":\"套餐\",\"bPropertyDisabled\":false,\"bPropertyRequired\":false,\"nPropertyCanQuery\":0,\"bPropertyEditable\":false,\"strPropertyType\":\"OTHER\",\"strPropertyFormat\":\"无|套餐一|套餐二|套餐三|套餐四|套餐五|套餐六|套餐七|套餐八\",\"nLevel\":2,\"bPropertyCanQuery\":false,\"strsPropertyFormat\":[\"无\",\"套餐一\",\"套餐二\",\"套餐三\",\"套餐四\",\"套餐五\",\"套餐六\",\"套餐七\",\"套餐八\"],\"strPropertyValue\":\"\",\"nPropValueIndex\":-1}, {\"lCategoryId\":1001,\"nPropertyIndex\":11,\"strPropertyName\":\"适合送给谁\",\"bPropertyDisabled\":false,\"bPropertyRequired\":false,\"nPropertyCanQuery\":0,\"bPropertyEditable\":false,\"strPropertyType\":\"OTHER\",\"strPropertyFormat\":\"中年女性|中年男性|女青年|男青年|老年女性|老年男性|女童|少女|少男|男童\",\"nLevel\":2,\"bPropertyCanQuery\":false,\"strsPropertyFormat\":[\"中年女性\",\"中年男性\",\"女青年\",\"男青年\",\"老年女性\",\"老年男性\",\"女童\",\"少女\",\"少男\",\"男童\"],\"strPropertyValue\":\"\",\"nPropValueIndex\":-1}, {\"lCategoryId\":1001,\"nPropertyIndex\":12,\"strPropertyName\":\"摄像机颜色\",\"bPropertyDisabled\":false,\"bPropertyRequired\":false,\"nPropertyCanQuery\":0,\"bPropertyEditable\":false,\"strPropertyType\":\"OTHER\",\"strPropertyFormat\":\"黑色|银色|金色|红色|玫瑰红|粉色|浅粉|浅蓝|蓝色|深蓝|草绿|绿色|黄色|灰色|白色|咖啡色|橙色|紫色\",\"nLevel\":2,\"bPropertyCanQuery\":false,\"strsPropertyFormat\":[\"黑色\",\"银色\",\"金色\",\"红色\",\"玫瑰红\",\"粉色\",\"浅粉\",\"浅蓝\",\"蓝色\",\"深蓝\",\"草绿\",\"绿色\",\"黄色\",\"灰色\",\"白色\",\"咖啡色\",\"橙色\",\"紫色\"],\"strPropertyValue\":\"\",\"nPropValueIndex\":-1}]";
		String no_nomal = "[{'\\x\\\\yz':'28_113_48000\\\\\\00000\\\\\\\\963_1258_2_155','nSubGoodsValueIndex':'0'},{'wyz':'28_113_4800000000963_1258_2_155','nSubGoodsValueIndex':'0'}]";
		try {
			CDO[] cdo = JsonUtil.jsonArrayToCDOArray(no_nomal);
			// String dd = JsonUtil.cdosToJsonArray(cdo);
			// CDO[] cdo1 = JsonUtil.jsonArrayToCDOArray(dd);
			// CDO cdo2 = new CDO();
			// cdo2.setCDOArrayValue("test", cdo1);
			// System.out.print(cdo2.toXMLWithIndent());
			// CDO cdo=JsonUtil.jsonToCDO(no_nomal);
			System.out.println("no_nomal2cdos:"+cdo);
			String revert = cdosToJsonArray(cdo);
			System.out.println("revert:"+revert);
		} catch (JSONException e) {
			// logger.error(e.getMessage(),e);
		} catch (Exception e) {
			// logger.error(e.getMessage(),e);
		}
	}
}
