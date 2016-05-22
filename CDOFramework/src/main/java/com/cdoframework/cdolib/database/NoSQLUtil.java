package com.cdoframework.cdolib.database;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.cdoframework.cdolib.base.DataType;
import com.cdoframework.cdolib.base.DateTime;
import com.cdoframework.cdolib.base.ObjectExt;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.base.Utility;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.database.dataservice.Add;
import com.cdoframework.cdolib.database.dataservice.AddField;
import com.cdoframework.cdolib.database.dataservice.CollectionNameType;
import com.cdoframework.cdolib.database.dataservice.Creteria;
import com.cdoframework.cdolib.database.dataservice.Creterias;
import com.cdoframework.cdolib.database.dataservice.CriteriaType;
import com.cdoframework.cdolib.database.dataservice.Modify;
import com.cdoframework.cdolib.database.dataservice.ModifyField;
import com.cdoframework.cdolib.database.dataservice.NoSQLTrans;
import com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoice;
import com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoiceItem;
import com.cdoframework.cdolib.database.dataservice.OnError;
import com.cdoframework.cdolib.database.dataservice.OnException;
import com.cdoframework.cdolib.database.dataservice.Order;
import com.cdoframework.cdolib.database.dataservice.QField;
import com.cdoframework.cdolib.database.dataservice.QueryCount;
import com.cdoframework.cdolib.database.dataservice.QueryField;
import com.cdoframework.cdolib.database.dataservice.QueryRecord;
import com.cdoframework.cdolib.database.dataservice.QueryRecordSet;
import com.cdoframework.cdolib.database.dataservice.Remove;
import com.cdoframework.cdolib.database.dataservice.Replace;
import com.cdoframework.cdolib.database.dataservice.ReplaceField;
import com.cdoframework.cdolib.database.dataservice.Scope;
import com.cdoframework.cdolib.database.dataservice.types.CriteriaTypeOperatorType;
import com.cdoframework.cdolib.database.dataservice.types.CriteriaTypeTypeType;
import com.cdoframework.cdolib.database.dataservice.types.FieldTypeTypeType;
import com.cdoframework.cdolib.database.dataservice.types.ModifyFieldMethodType;
import com.cdoframework.cdolib.util.Function;
import com.cdoframework.cdolib.util.JsonUtil4NoSQL;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.QueryOperators;
import com.mongodb.WriteResult;

/**
 * @author Administrator
 */
public class NoSQLUtil
{
	// 内部类,所有内部类在此声明----------------------------------------------------------------------------------

	// 静态对象,所有static在此声明并初始化------------------------------------------------------------------------
	static Logger logger = Logger.getLogger(NoSQLUtil.class);
	public static String FUNCTION_DATETIME = "now()";

	// 内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------

	// 属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------

	// 引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------

	// 内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	protected static void handleReturn(com.cdoframework.cdolib.base.Return ret, com.cdoframework.cdolib.database.dataservice.Return returnObject, CDO cdoRequest, CDO cdoResponse) throws Exception
	{
		int nReturnItemCount = returnObject.getReturnItemCount();
		for (int j = 0; j < nReturnItemCount; j++)
		{
			String strFieldId = returnObject.getReturnItem(j).getFieldId();
			String strValueId = returnObject.getReturnItem(j).getValueId();
			strFieldId = getStringKey(strFieldId);
			strValueId = getStringKey(strValueId);
			ObjectExt object = null;
			try
			{
				object = cdoRequest.getObject(strValueId);
				// 输出
				int nType = object.getType();
				Object objValue = object.getValue();
				cdoResponse.setObjectExt(strFieldId, object);
			}
			catch (Exception e)
			{
				continue;
			}
		}

		ret.setCode(returnObject.getCode());
		ret.setInfo(returnObject.getInfo());
		ret.setText(returnObject.getText());
	}

	// 公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------

	// 接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.cdoframework.cdolib.database.INoSQLDataEngine#executeTrans(com.
	 * cdoframework.cdolib.database.dataservice.NoSQLTrans,
	 * com.cdoframework.cdolib.data.cdo.CDO,
	 * com.cdoframework.cdolib.data.cdo.CDO)
	 */
	public static Return executeTrans(NoSQLTrans noSqlTrans, CDO cdoRequest, CDO cdoResponse)
	{
		Return ret = null;

		// 根据transName 只能得到一个 NoSQLTransTypeChoice
		String strTransName = noSqlTrans.getTransName();
		try
		{
			NoSQLTransTypeChoice noSQLTransTypeChoices = noSqlTrans.getNoSQLTransTypeChoice()[0];
			NoSQLTransTypeChoiceItem noSqlTransTypeChoiceItem = null;
			for (int i = 0; i < noSQLTransTypeChoices.getNoSQLTransTypeChoiceItemCount(); i++)
			{
				noSqlTransTypeChoiceItem = noSQLTransTypeChoices.getNoSQLTransTypeChoiceItem(i);
				ret = queryField(strTransName, noSqlTransTypeChoiceItem.getQueryField(), cdoRequest);
				if (ret != null && ret.getCode() != 0)
				{
					logger.error(ret.getText());
					break;
				}
				ret = queryRecord(strTransName, noSqlTransTypeChoiceItem.getQueryRecord(), cdoRequest);
				if (ret != null && ret.getCode() != 0)
				{
					logger.error(ret.getText());
					break;
				}
				ret = queryRecordSet(strTransName, noSqlTransTypeChoiceItem.getQueryRecordSet(), cdoRequest);
				if (ret != null && ret.getCode() != 0)
				{
					logger.error(ret.getText());
					break;
				}
				ret = add(strTransName, noSqlTransTypeChoiceItem.getAdd(),cdoRequest);
				if (ret != null && ret.getCode() != 0)
				{
					logger.error(ret.getText());
					break;
				}
				ret = modify(strTransName, noSqlTransTypeChoiceItem.getModify(), cdoRequest);
				if (ret != null && ret.getCode() != 0)
				{
					logger.error(ret.getText());
					break;
				}
				ret = replace(strTransName, noSqlTransTypeChoiceItem.getReplace(), cdoRequest);
				if (ret != null && ret.getCode() != 0)
				{
					logger.error(ret.getText());
					break;
				}
				ret = remove(strTransName, noSqlTransTypeChoiceItem.getRemove(), cdoRequest);
				if (ret != null && ret.getCode() != 0)
				{
					logger.error(ret.getText());
					break;
				}
				ret = queryCount(strTransName, noSqlTransTypeChoiceItem.getQueryCount(), cdoRequest);
			}
			if (ret != null && ret.getCode() != 0)
			{
				logger.error(ret.getText());
				return ret;
			}
			ret = Return.OK;
			handleReturn(ret, noSqlTrans.getReturn(), cdoRequest, cdoResponse);
			return ret;
		}
		catch (MongoException mongoException)
		{
			logger.error("caught exception when handle strTransName " + strTransName, mongoException);
			onException("caught exception when handle strTransName " + strTransName, mongoException);

			OnException onException = noSqlTrans.getOnException();
			int nErrorCount = onException.getOnErrorCount();
			for (int i = 0; i < nErrorCount; i++)
			{
				OnError onError = onException.getOnError(i);
				if (onError.getCode() == mongoException.getCode())
				{
					ret = Return.valueOf(onError.getReturn().getCode(), onError.getReturn().getText(), onError.getReturn().getInfo());
					break;
				}
			}
			if (ret == null)
			{// 没有定义OnError
				ret = Return.valueOf(onException.getReturn().getCode(), onException.getReturn().getText(), onException.getReturn().getInfo(), mongoException);
			}
			return ret;
		}
		catch (Exception ex)
		{
			logger.error("caught exception when handle strTransName " + strTransName, ex);
			onException("caught exception when handle strTransName " + strTransName, ex);

			OnException onException = noSqlTrans.getOnException();
			ret = Return.valueOf(onException.getReturn().getCode(), onException.getReturn().getText(), onException.getReturn().getInfo());
			return ret;
		}
	}

	/**
	 * 
	 * @param queryCount
	 * @param cdoRequest
	 * @param cdoBlock
	 * @return
	 */
	private static Return queryCount(String strTransName, QueryCount queryCount, CDO cdoRequest)
	{
		if (queryCount == null) return null;

		BasicDBObject query = new BasicDBObject();
		creterias(query, queryCount.getCreterias(), cdoRequest);
		DBCollection coll = getCollection(queryCount);

		long result = -1;
		onBeforeQueryCount(strTransName, query);
		try
		{
			coll.getDB().requestStart();
			result = coll.count(query);
		}
		catch (Exception e)
		{
			logger.error("queryCount:" + e.getMessage(), e);
			return Return.valueOf(-1, e.getLocalizedMessage());
		}
		finally
		{
			closeCollection(coll);
		}
		String strOuputId = queryCount.getOutputId();
		recordEffectCount(strOuputId, cdoRequest, result);
		return Return.OK;
	}

	private static void onBeforeQueryCount(String strTransName, BasicDBObject query)
	{
		if (logger.isDebugEnabled())
		{
			logger.debug(strTransName + "-query :" + query);
		}
	}

	// 查询相关
	private static Return queryField(String strTransName, QueryField queryField, CDO cdoRequest)
	{
		if (queryField == null) return null;
		DBCollection coll = null;
		try
		{
			// 查询条件
			Creterias creterias = queryField.getCreterias();
			BasicDBObject creterObject = new BasicDBObject();
			creterias(creterObject, creterias, cdoRequest);

			// 输出 outputId,strRecordCountId
			String outPutId = getStringKey(queryField.getOutputId());
			String strRecordCountId = queryField.getRecordCountId();

			// 查询字段
			BasicDBObject queryFields = new BasicDBObject();
			QField[] xmlQFields = new QField[1];
			QField qField = queryField.getQField();
			xmlQFields[0] = qField;
			Map<String, QField> map = new HashMap<String, QField>();
			setQueryQField(xmlQFields, queryFields, map);

			// 查询排序
			BasicDBObject orderObjects = new BasicDBObject();
			Order[] orders = queryField.getOrder();
			setQueryOrder(cdoRequest, orderObjects, orders);

			// 数据库连接
			coll = getCollection(queryField);
			coll.getDB().requestStart();

			// 查询字段结果
			onBeforeRequery(strTransName, creterObject, queryFields);
			DBCursor dbCur = coll.find(creterObject, queryFields).sort(orderObjects).limit(1);
			return getQueryResult(cdoRequest, outPutId, strRecordCountId, map, dbCur, false, true, "queryField:caught a exception that needn't break off main process ");
		}
		catch (Exception ex)
		{
			logger.error("queryField:" + ex.getMessage(), ex);
			throw new RuntimeException(" query field failed:" + ex.getMessage());
		}
		finally
		{
			closeCollection(coll);
		}
	}
	
	private static Return getQueryResult(CDO cdoRequest, String outPutId, String strRecordCountId, Map<String, QField> map, 
			DBCursor dbCur, boolean multi, boolean isQueryField, String errinfo) throws Exception
	{
		List<DBObject> list = dbCur.toArray();
		recordEffectCount(cdoRequest, dbCur.count(), strRecordCountId, multi);

		try
		{
			dbCur.close();
		}
		catch (Exception e)
		{
			logger.error(errinfo + e.getMessage(), e);
		}
		
		CDO[] cdos;
		if (list.size()>0 || multi == true)
		{
			cdos = new CDO[list.size()];
		}
		else
		{
			cdos = new CDO[1];
			cdos[0] = new CDO();
		}
		
		int i = 0;
		Map temp = new HashMap();
		String key = null;
		for (DBObject dbObject : list) 
		{
			for (Iterator iterator = dbObject.keySet().iterator(); iterator.hasNext();) {
				key = (String)iterator.next();
				temp.put(key, dbObject.get(key));
				JsonUtil4NoSQL.setCDORequest(cdos[i]==null?cdos[i]=new CDO():cdos[i], temp, true, map, null);
				temp.clear();
			}
			i++;
		}
		
		if (isQueryField) 
		{
			if (list.size()==0)
			{
				return Return.OK;
			}
//			ObjectExt objectExt = cdos[0].getObjectAt(0);
			ObjectExt objectExt = cdos[0].iterator().next().getValue();
			JsonUtil4NoSQL.setCDOValue(cdoRequest, objectExt.getType(), key, objectExt.getValue());
		} 
		else
		{
			if (multi)
			{
				cdoRequest.setCDOArrayValue(outPutId, cdos);
			}
			else 
			{
				cdoRequest.setCDOValue(outPutId, cdos[0]);
			}
		}

		return Return.OK;
	}

	private static void recordEffectCount(String strOuputId, CDO cdoRequest, long result)
	{
		cdoRequest.setLongValue(getStringKey(strOuputId), result);
	}
	

	/**
	 * 
	 * @param cdoRequest 请求对象
	 * @param count 集合计数
	 * @param strRecordCountId	集合计数key	
	 * @param multi	是否是获取recordeSet 集合的总数量
	 */
	private static void recordEffectCount(CDO cdoRequest, long count, String strRecordCountId, boolean multi)
	{
		if (strRecordCountId != null)
		{
			strRecordCountId = getStringKey(strRecordCountId);
			if (!multi) count = count > 1 ? 1 : count;
			cdoRequest.setIntegerValue(strRecordCountId, (int)count);
		}
	}

	private static void recordEffectCount(CDO cdoRequest, WriteResult wr, String strRecordCountId)
	{
		if (strRecordCountId != null)
		{
			strRecordCountId = getStringKey(strRecordCountId);
			cdoRequest.setLongValue(strRecordCountId, Utility.parseLongValue(wr.getField("n")));
		}
	}

	private static void setQueryOrder(CDO cdoRequest, BasicDBObject orderObjects)
	{// 否则判断 cdoRequest 是否有 cdosSortConditon[strFieldName,strOrder]
		if (cdoRequest.exists(INoSQLDataEngine.SORT_CDO_KEY))
		{
			CDO[] cdosSortConditon = cdoRequest.getCDOArrayValue(INoSQLDataEngine.SORT_CDO_KEY);
			String strFieldName = null;
			String strOrder = null;
			for (int i = 0; i < cdosSortConditon.length; i++)
			{
				strFieldName = null;
				strOrder = "asc";
				if (cdosSortConditon[i].exists(INoSQLDataEngine.SORT_CDO_FIELD_NAME))
				{
					strFieldName = cdosSortConditon[i].getStringValue(INoSQLDataEngine.SORT_CDO_FIELD_NAME);
					if (cdosSortConditon[i].exists(INoSQLDataEngine.SORT_CDO_ORDER))
					{
						strOrder = cdosSortConditon[i].getStringValue(INoSQLDataEngine.SORT_CDO_ORDER);
					}
					if (strOrder.equalsIgnoreCase("desc"))
					{
						orderObjects.put(strFieldName, -1);
					}
					else
					{
						orderObjects.put(strFieldName, 1);
					}
				}
			}
		}
	}

	/**
	 * 去掉{及}
	 * 
	 * @param strKey
	 * @return
	 */
	private static String getStringKey(String strKey)
	{
		if (strKey == null) return strKey;
		if (strKey.startsWith("{") && strKey.endsWith("}"))
			return strKey.substring(1, strKey.length() - 1);
		return strKey;
	}

	private static Return queryRecord(String strTransName, QueryRecord queryRecord, CDO cdoRequest)
	{
		if (queryRecord == null) return null;
		DBCollection coll = null;
		try
		{
			// 查询条件
			Creterias creterias = queryRecord.getCreterias();
			BasicDBObject creterObject = new BasicDBObject();
			creterias(creterObject, creterias, cdoRequest);

			// 输出 outputId,recordCount
			String outPutId = getStringKey(queryRecord.getOutputId());
			String strRecordCountId = queryRecord.getRecordCountId();
			
			// 查询字段
			BasicDBObject queryFields = new BasicDBObject();
			QField[] xmlQFields = queryRecord.getQField();
			Map<String, QField> map = new HashMap<String, QField>();
			setQueryQField(xmlQFields, queryFields, map);
			
			// 查询排序
			BasicDBObject orderObjects = new BasicDBObject();
			Order[] orders = queryRecord.getOrder();
			setQueryOrder(cdoRequest, orderObjects, orders);
			
			// 数据库连接
			coll = getCollection(queryRecord);
			coll.getDB().requestStart();

			// 查询字段结果
			onBeforeRequery(strTransName, creterObject, queryFields);
			DBCursor dbCur = coll.find(creterObject, queryFields).sort(orderObjects).limit(1);
			
			return getQueryResult(cdoRequest, outPutId, strRecordCountId, map, dbCur, false, false, "queryRecord:caught a exception that needn't break off main process ");
			
		}
		catch (Exception ex)
		{
			logger.error("queryRecord：" + ex.getMessage(), ex);
			throw new RuntimeException(" query field failed:" + ex.getMessage());
		}
		finally
		{
			closeCollection(coll);
		}
	}

	protected static void onBeforeRequery(String strTransName, BasicDBObject creterObject, BasicDBObject queryFields)
	{
		if (logger.isDebugEnabled())
		{
			logger.debug(strTransName + "-creteria:" + creterObject);
			logger.debug(strTransName + "-queryFields:" + queryFields);
		}
	}


	private static Return queryRecordSet(String strTransName, QueryRecordSet queryRecordSet, CDO cdoRequest)
	{
		if (queryRecordSet == null) return null;
		DBCollection coll = null;
		try
		{
			// 查询条件
			Creterias creterias = queryRecordSet.getCreterias();
			BasicDBObject creterObject = new BasicDBObject();
			creterias(creterObject, creterias, cdoRequest);
			// 输出 outputId,strRecordCountId
			String outPutId = getStringKey(queryRecordSet.getOutputId());
			String strRecordCountId = queryRecordSet.getRecordCountId();
			// 查询字段
			BasicDBObject queryFields = new BasicDBObject();
			Map<String, QField> map = new HashMap<String, QField>();
			setQueryQField(queryRecordSet.getQField(), queryFields, map);
			// 查询排序
			BasicDBObject orderObjects = new BasicDBObject();
			Order[] orders = queryRecordSet.getOrder();
			setQueryOrder(cdoRequest, orderObjects, orders);

			coll = getCollection(queryRecordSet);
			coll.getDB().requestStart();

			// 查询字段结果
			Scope scope = queryRecordSet.getScope();
			DBCursor dbCur = null;
			if (scope != null)
			{// 处理
				Object strSkip = scope.getSkip();
				Object strLimit = scope.getLimit();
				int nLimit = 0;
				if (strLimit != null)
				{
					if(!cdoRequest.exists(getStringKey(strLimit.toString())))
					{
						nLimit = Utility.parseIntegerValue(strLimit.toString());
					}
					else
					{
						nLimit = Utility.parseIntegerValue(cdoRequest.getObjectValue(getStringKey(strLimit.toString())));
					}
					
					if (nLimit <= 0)
					{
						throw new RuntimeException(" limit value is not right!" + nLimit);
					}
				}
				onBeforeRequery(strTransName, creterObject, queryFields);
				int nSkip = 0;
				if (strSkip != null)
				{
					if(!cdoRequest.exists(getStringKey(strSkip.toString())))
					{
						nSkip = Utility.parseIntegerValue(strSkip.toString());
					}
					else
					{
						nSkip = Utility.parseIntegerValue(cdoRequest.getObjectValue(getStringKey(strSkip.toString())));
					}			
					
					if (nSkip < 0)
					{
						throw new RuntimeException(" skip value is not right,Skip=" + nSkip);
					}
					dbCur = coll.find(creterObject, queryFields).sort(orderObjects).skip(nSkip).limit(nLimit);
				}
				else
				{
					dbCur = coll.find(creterObject, queryFields).sort(orderObjects).limit(nLimit);
				}
			}
			else
			{
				dbCur = coll.find(creterObject, queryFields).sort(orderObjects);
			}

			return getQueryResult(cdoRequest, outPutId, strRecordCountId, map, dbCur, true, false, "queryRecordSet:caught a exception that needn't break off main process ");
			
		}
		catch (Exception ex)
		{
			logger.error("queryRecordSet:" + ex.getMessage(), ex);
			throw new RuntimeException(" query field failed:" + ex.getMessage());
		}
		finally
		{
			closeCollection(coll);
		}
	}

	private static void setQueryOrder(CDO cdoRequest, BasicDBObject orderObjects, Order[] orders)
	{
		if (orders != null && orders.length > 0)
		{
			String strFieldName=null;
			String strDirection=null;
			for (int i = 0; i < orders.length; i++)
			{
				strFieldName=orders[i].getFieldName();
				strDirection=orders[i].getDirection();
				if( strDirection== null || strDirection.equals(""))
					strDirection="1";
				
				if(strFieldName.startsWith("{") && strFieldName.endsWith("}"))
				{
					strFieldName=cdoRequest.getObjectValue(getStringKey(strFieldName))+"";
				}
				if(strDirection.startsWith("{") && strDirection.endsWith("}"))
				{
					strDirection=cdoRequest.getObjectValue(getStringKey(strDirection))+"";
					if(strDirection.equalsIgnoreCase("asc")|| strDirection.equalsIgnoreCase("1"))
					{
						strDirection="1";
					}else if(strDirection.equalsIgnoreCase("desc")||strDirection.equalsIgnoreCase("-1"))
					{
						strDirection="-1";
					}else{
						throw new RuntimeException("Direction attribute value is not valid!"+orders[i].getDirection()+"="+strDirection);
					}	
				}
				orderObjects.put(strFieldName,new Integer(strDirection));
			}
		}
		else
		{// 否则判断 cdoRequest 是否有 cdosSortConditon[strFieldName,strOrder]
			setQueryOrder(cdoRequest, orderObjects);
		}
	}

	
	private static QField[] setQueryQField(QField[] xmlQFields, BasicDBObject queryFields, Map<String, QField> map)
	{
		queryFields.put("_id", 0);
		if (xmlQFields != null && xmlQFields.length > 0)
		{
			String strName = null;
			String strValue = null;
			for (int i = 0; i < xmlQFields.length; i++)
			{
				strName = xmlQFields[i].getName();
				strValue = xmlQFields[i].getValue();
				if (strValue == null)
				{// 构造需要查询的字段
					queryFields.put(strName, 1);
				}
				else 
				{
					queryFields.put(strName, strValue);
				}
				map.put(strName, xmlQFields[i]);
			}
		}
		return xmlQFields;
	}

	private static void closeCollection(DBCollection coll)
	{
		if (coll != null)
		{
			coll.getDB().requestDone();
		}
	}

	/**
	 * 生成查询条件
	 * 
	 * @param creterias
	 * @param cdoRequest
	 * @param bValueRequired
	 * @return
	 */
	private static void creterias(BasicDBObject basicDBObject, Creterias creterias, CDO cdoRequest)
	{
		if (creterias == null)
		{
			return;
		}
		String strType = creterias.getType();
		Creteria creteria = null;
		Creterias subCreterias = null;
		if (strType.equalsIgnoreCase(INoSQLDataEngine.CERTERIAS_TYPE_AND))
		{
			for (int i = 0; i < creterias.getCreteriasTypeItemCount(); i++)
			{
				creteria = creterias.getCreteriasTypeItem(i).getCreteria();
				if (creteria != null)
				{
					setCreteria(basicDBObject, cdoRequest, creteria);
				}
				subCreterias = creterias.getCreteriasTypeItem(i).getCreterias();
				if (subCreterias != null)
					creterias(basicDBObject, subCreterias, cdoRequest);
			}
		}
		else if (strType.equalsIgnoreCase(INoSQLDataEngine.CERTERIAS_TYPE_OR))
		{// 如果为or,初始化
			BasicDBObject[] subBasicDBObjects = new BasicDBObject[creterias.getCreteriasTypeItemCount()];
			for (int i = 0; i < creterias.getCreteriasTypeItemCount(); i++)
			{
				subBasicDBObjects[i] = new BasicDBObject();
			}
			basicDBObject.put(INoSQLDataEngine.CERTERIAS_OPERATION_OR, subBasicDBObjects);
			for (int i = 0; i < creterias.getCreteriasTypeItemCount(); i++)
			{
				creteria = creterias.getCreteriasTypeItem(i).getCreteria();
				if (creteria != null)
				{
					setCreteria(subBasicDBObjects[i], cdoRequest, creteria);
				}
				subCreterias = creterias.getCreteriasTypeItem(i).getCreterias();
				if (subCreterias != null)
					creterias(subBasicDBObjects[i], subCreterias, cdoRequest);
			}
		}
		else
		{
			throw new RuntimeException(" certerias type[" + strType + "]operation type is not unsupported!");
		}
	}

	// private static void setCreteria(BasicDBObject basicDBObject,CDO
	// cdoRequest,Creteria creteria)
	// {
	// setCreteria(basicDBObject,cdoRequest,creteria,false);
	// }
	/**
	 * 填充查询条件对象
	 */
	private static void setCreteria(BasicDBObject basicDBObject, CDO cdoRequest, Creteria creteria)
	{
		String fieldName = creteria.getName();
		boolean isValueRequired = false;
		boolean hasValueRequired = creteria.hasValueRequired();
		if (hasValueRequired)
		{
			isValueRequired = creteria.getValueRequired();
		}
		if (creteria.getAndCount() == 0)
		{// 对同一属性只有单一条件
			setSingleCreteria(basicDBObject, cdoRequest, creteria, fieldName, isValueRequired);
		}
		else
		{// 对同一属性有多个条件
			BasicDBObject subBO = new BasicDBObject();
			setSubCreteria(subBO, cdoRequest, creteria, fieldName, isValueRequired);
			for (CriteriaType ct : creteria.getAnd())
			{
				setSubCreteria(subBO, cdoRequest, ct, fieldName, isValueRequired);
			}
			if (subBO.size() > 0)
			{
				basicDBObject.put(fieldName, subBO);
			}
		}

	}

	/**
	 * 填充查询条件对象 处理同一属性只有单一条件
	 * 
	 * @param basicDBObject
	 * @param cdoRequest
	 * @param creteriaType
	 * @param fieldName
	 * @param isValueRequired
	 */
	private static void setSingleCreteria(BasicDBObject basicDBObject, CDO cdoRequest, CriteriaType creteriaType, String fieldName, boolean isValueRequired)
	{
		// 操作符
		CriteriaTypeOperatorType operatType = creteriaType.getOperator();
		// 值对象
		ObjectExt valueObject = null;
		// 根据操作符来判断生成查询条件,有的条件不需要关心value和type
		switch (operatType.getType())
		{
		case CriteriaTypeOperatorType.VALUE_0_TYPE:// =
			valueObject = parseCreteriaTypeValue(cdoRequest, creteriaType, fieldName, isValueRequired);
			if (valueObject == null)
			{
				break;
			}
			basicDBObject.put(fieldName, Utility.parseObjectValue(valueObject.getType(), valueObject.getValue()));
			break;
		case CriteriaTypeOperatorType.VALUE_1_TYPE:// !=
			valueObject = parseCreteriaTypeValue(cdoRequest, creteriaType, fieldName, isValueRequired);
			if (valueObject == null)
			{
				break;
			}
			basicDBObject.put(fieldName, new BasicDBObject(QueryOperators.NE, Utility.parseObjectValue(valueObject.getType(), valueObject.getValue())));
			break;
		case CriteriaTypeOperatorType.VALUE_2_TYPE:// >
			valueObject = parseCreteriaTypeValue(cdoRequest, creteriaType,fieldName, isValueRequired);
			if (valueObject == null)
			{
				break;
			}
			basicDBObject.put(fieldName, new BasicDBObject(QueryOperators.GT, Utility.parseObjectValue(valueObject.getType(), valueObject.getValue())));
			break;
		case CriteriaTypeOperatorType.VALUE_3_TYPE:// >=
			valueObject = parseCreteriaTypeValue(cdoRequest, creteriaType,fieldName, isValueRequired);
			if (valueObject == null)
			{
				break;
			}
			basicDBObject.put(fieldName, new BasicDBObject(QueryOperators.GTE, Utility.parseObjectValue(valueObject.getType(), valueObject.getValue())));
			break;
		case CriteriaTypeOperatorType.VALUE_4_TYPE:// <
			valueObject = parseCreteriaTypeValue(cdoRequest, creteriaType, fieldName, isValueRequired);
			if (valueObject == null)
			{
				break;
			}
			basicDBObject.put(fieldName, new BasicDBObject(QueryOperators.LT, Utility.parseObjectValue(valueObject.getType(), valueObject.getValue())));
			break;
		case CriteriaTypeOperatorType.VALUE_5_TYPE:// <=
			valueObject = parseCreteriaTypeValue(cdoRequest, creteriaType, fieldName, isValueRequired);
			if (valueObject == null)
			{
				break;
			}
			basicDBObject.put(fieldName, new BasicDBObject(QueryOperators.LTE,Utility.parseObjectValue(valueObject.getType(), valueObject.getValue())));
			break;
		case CriteriaTypeOperatorType.VALUE_6_TYPE:// ISNULL
			basicDBObject.put(fieldName, new BasicDBObject(INoSQLDataEngine.FIND_TYPE, INoSQLDataEngine.TYPE_NULL));
			break;
		case CriteriaTypeOperatorType.VALUE_7_TYPE:// ISNOTNULL
			int nDataType = parseCreteriaTypeValueType(cdoRequest, creteriaType, fieldName, isValueRequired);
			int nBasicObjectType = TypeMapping.mappingToMonGoType(nDataType);
			if (nBasicObjectType == -1)
			{
				throw new RuntimeException(" Creteria data Type is not unSupported! " + nDataType);
			}
			basicDBObject.put(fieldName, new BasicDBObject(INoSQLDataEngine.FIND_TYPE, nBasicObjectType));
			break;
		case CriteriaTypeOperatorType.VALUE_8_TYPE:// MATCH
			valueObject = parseCreteriaTypeValue(cdoRequest, creteriaType, fieldName, isValueRequired);
			if (valueObject == null)
			{
				break;
			}
			if (valueObject != null)
			{
				Pattern pattern = Pattern.compile("^" + Function.FormatTextForMongodb(valueObject.getValue().toString()) + ".*", Pattern.CASE_INSENSITIVE);
				basicDBObject.put(fieldName, pattern);
			}
			break;
		case CriteriaTypeOperatorType.VALUE_9_TYPE:// NOTMATCH
			// TODO 暂不支持
			throw new RuntimeException(" Creteria Operator Type NOTMATCH is not unSupported!");
		case CriteriaTypeOperatorType.VALUE_10_TYPE:// IN
			valueObject = parseCreteriaTypeValue(cdoRequest, creteriaType, fieldName, isValueRequired);
			if (valueObject == null)
			{
				break;
			}
			basicDBObject.put(fieldName, new BasicDBObject(QueryOperators.IN, (Object[]) valueObject.getObjectValue()));
			break;
		case CriteriaTypeOperatorType.VALUE_11_TYPE:// NOTIN
			valueObject = parseCreteriaTypeValue(cdoRequest, creteriaType, fieldName, isValueRequired);
			if (valueObject == null)
			{
				break;
			}
			basicDBObject.put(fieldName, new BasicDBObject(QueryOperators.NIN,(Object[]) valueObject.getObjectValue()));
			break;
		case CriteriaTypeOperatorType.VALUE_12_TYPE:// Exists
			basicDBObject.put(fieldName, new BasicDBObject(INoSQLDataEngine.FIND_EXISTS, true));
			break;
		case CriteriaTypeOperatorType.VALUE_13_TYPE:// NotExists
			basicDBObject.put(fieldName, new BasicDBObject(INoSQLDataEngine.FIND_EXISTS, false));
			break;
		case CriteriaTypeOperatorType.VALUE_14_TYPE:// MATCHALL
			valueObject = parseCreteriaTypeValue(cdoRequest, creteriaType, fieldName, isValueRequired);
			if (valueObject == null)
			{
				break;
			}
			if (valueObject != null)
			{
				Pattern pattern = Pattern.compile(".*" + Function.FormatTextForMongodb(valueObject.getValue().toString()) + ".*", Pattern.CASE_INSENSITIVE);
				basicDBObject.put(fieldName, pattern);
			}
			break;
		case CriteriaTypeOperatorType.VALUE_15_TYPE:// MATCHRIGHT
			valueObject = parseCreteriaTypeValue(cdoRequest, creteriaType, fieldName, isValueRequired);
			if (valueObject == null)
			{
				break;
			}
			if (valueObject != null)
			{
				Pattern pattern = Pattern.compile(".*" + Function.FormatTextForMongodb(valueObject.getValue().toString()) + "$", Pattern.CASE_INSENSITIVE);
				basicDBObject.put(fieldName, pattern);
			}
			break;
		default:
			throw new RuntimeException(" Creteria Operator Type is not unSupported!");
		}
	}

	/**
	 * 填充查询条件对象 处理一个属性有多个条件的情况
	 * 
	 * @param basicDBObject
	 * @param cdoRequest
	 * @param creteriaType
	 * @param fieldName
	 * @param isValueRequired
	 */
	private static void setSubCreteria(BasicDBObject basicDBObject, CDO cdoRequest, CriteriaType creteriaType, String fieldName, boolean isValueRequired)
	{
		// 操作符
		CriteriaTypeOperatorType operatType = creteriaType.getOperator();
		// 值对象
		ObjectExt valueObject = null;
		// 根据操作符来判断生成查询条件,有的条件不需要关心value和type
		switch (operatType.getType())
		{
		case CriteriaTypeOperatorType.VALUE_0_TYPE:// =
			throw new RuntimeException(" Creteria Operator Type = is not unSupported for multi-criteria");
		case CriteriaTypeOperatorType.VALUE_1_TYPE:// !=
			valueObject = parseCreteriaTypeValue(cdoRequest, creteriaType, fieldName, isValueRequired);
			if (valueObject == null)
			{
				break;
			}
			basicDBObject.put(QueryOperators.NE, Utility.parseObjectValue(valueObject.getType(), valueObject.getValue()));
			break;
		case CriteriaTypeOperatorType.VALUE_2_TYPE:// >
			valueObject = parseCreteriaTypeValue(cdoRequest, creteriaType, fieldName, isValueRequired);
			if (valueObject == null)
			{
				break;
			}
			basicDBObject.put(QueryOperators.GT, Utility.parseObjectValue(valueObject.getType(), valueObject.getValue()));
			break;
		case CriteriaTypeOperatorType.VALUE_3_TYPE:// >=
			valueObject = parseCreteriaTypeValue(cdoRequest, creteriaType, fieldName, isValueRequired);
			if (valueObject == null)
			{
				break;
			}
			basicDBObject.put(QueryOperators.GTE, Utility.parseObjectValue(valueObject.getType(), valueObject.getValue()));
			break;
		case CriteriaTypeOperatorType.VALUE_4_TYPE:// <
			valueObject = parseCreteriaTypeValue(cdoRequest, creteriaType, fieldName, isValueRequired);
			if (valueObject == null)
			{
				break;
			}
			basicDBObject.put(QueryOperators.LT, Utility.parseObjectValue(valueObject.getType(), valueObject.getValue()));
			break;
		case CriteriaTypeOperatorType.VALUE_5_TYPE:// <=
			valueObject = parseCreteriaTypeValue(cdoRequest, creteriaType, fieldName, isValueRequired);
			if (valueObject == null)
			{
				break;
			}
			basicDBObject.put(QueryOperators.LTE, Utility.parseObjectValue(valueObject.getType(), valueObject.getValue()));
			break;
		case CriteriaTypeOperatorType.VALUE_6_TYPE:// ISNULL
			throw new RuntimeException(" Creteria Operator Type ISNULL is not unSupported for multi-criteria");
		case CriteriaTypeOperatorType.VALUE_7_TYPE:// ISNOTNULL
			throw new RuntimeException(" Creteria Operator Type ISNOTNULL is not unSupported for multi-criteria");
		case CriteriaTypeOperatorType.VALUE_8_TYPE:// MATCH
			valueObject = parseCreteriaTypeValue(cdoRequest, creteriaType, fieldName, isValueRequired);
			if (valueObject != null)
			{
				Pattern pattern = Pattern.compile("^" + Function.FormatTextForMongodb(valueObject.getValue().toString()) + ".*", Pattern.CASE_INSENSITIVE);
				basicDBObject.put(INoSQLDataEngine.FIND_REGEX, pattern);
			}
			break;
		case CriteriaTypeOperatorType.VALUE_9_TYPE:// NOTMATCH
			// TODO 暂不支持
			throw new RuntimeException(" Creteria Operator Type is not unSupported!");
		case CriteriaTypeOperatorType.VALUE_10_TYPE:// IN
			valueObject = parseCreteriaTypeValue(cdoRequest, creteriaType, fieldName, isValueRequired);
			if (valueObject == null)
			{
				break;
			}
			basicDBObject.put(QueryOperators.IN, (Object[]) valueObject.getObjectValue());
			break;
		case CriteriaTypeOperatorType.VALUE_11_TYPE:// NOTIN
			valueObject = parseCreteriaTypeValue(cdoRequest, creteriaType, fieldName, isValueRequired);
			if (valueObject == null)
			{
				break;
			}
			basicDBObject.put(QueryOperators.NIN, (Object[]) valueObject.getObjectValue());
			break;
		case CriteriaTypeOperatorType.VALUE_12_TYPE:// Exists
			throw new RuntimeException(" Creteria Operator Type EXISTS is not unSupported for multi-criteria");
		case CriteriaTypeOperatorType.VALUE_13_TYPE:// NotExists
			throw new RuntimeException(" Creteria Operator Type NOTEXISTS is not unSupported for multi-criteria");
		default:
			throw new RuntimeException(" Creteria Operator Type is not unSupported!");
		}

	}

	private static int parseCreteriaTypeValueType(CDO cdoRequest, CriteriaType creteriaType, String fieldName, boolean isValueRequired)
	{
		// 值类型
		CriteriaTypeTypeType criteriaType = creteriaType.getType();// 配置的类型
		if (criteriaType != null)
		{
			return TypeMapping.mappingToDataType(criteriaType);
		}
		// 值
		String strValue = creteriaType.getValue();
		if (strValue == null)
		{
			strValue = "";
		}
		else
		{
			strValue = strValue.trim();
		}
		if (strValue.startsWith("{") && strValue.endsWith("}"))
		{// 变量,需要从传入参数cdoRequest中解析
			String strKey = getStringKey(strValue);
			if (cdoRequest.exists(strKey))
			{
				ObjectExt obj = cdoRequest.getObject(strKey);
				return obj.getType();
			}
			else
			{
				if (isValueRequired)
				{// 如果key 在cdoRequst 不存在，且这个key 要求存在于cdoRequest
					throw new RuntimeException("Field [" + strKey + "] is not found!");
				}
				else
				{
					return DataType.NONE_TYPE;
				}
			}
		}
		else
		{// 值
			throw new RuntimeException("Field [" + fieldName
					+ "] type is not set!");
		}
	}

	/**
	 * 解析条件配置对象的值和值类型
	 * 
	 * @param cdoRequest
	 * @param creteriaType
	 * @param fieldName
	 * @param isValueRequired
	 * @return
	 */
	private static ObjectExt parseCreteriaTypeValue(CDO cdoRequest, CriteriaType creteriaType, String fieldName, boolean isValueRequired)
	{
		ObjectExt result = null;
		// 值类型
		CriteriaTypeTypeType criteriaType = creteriaType.getType();// 配置的类型
		int nDataType = DataType.NONE_TYPE;
		if (criteriaType != null)
		{
			nDataType = TypeMapping.mappingToDataType(criteriaType);
		}
		// 值
		String strValue = creteriaType.getValue();
		if (strValue == null)
		{
			strValue = "";
		}
		else
		{
			strValue = strValue.trim();
		}
		if (strValue.startsWith("{") && strValue.endsWith("}"))
		{// 变量,需要从传入参数cdoRequest中解析
			String strKey = getStringKey(strValue);
			if (cdoRequest.exists(strKey))
			{
				ObjectExt obj = cdoRequest.getObject(strKey);
				if (nDataType != DataType.NONE_TYPE)
				{
					result = getObjectExt(nDataType, obj);
				}
				else
				{
					result = obj;
				}
			}
			else
			{
				if (isValueRequired)
				{// 如果key 在cdoRequst 不存在，且这个key 要求存在于cdoRequest
					throw new RuntimeException("Field [" + strKey + "] is not found!");
				}
				else
				{
					return null;
				}
			}
		}
		else
		{// 值
			if (criteriaType == null)
			{
				throw new RuntimeException("Field [" + fieldName + "] type is not set!");
			}
			result = getObject(TypeMapping.mappingToDataType(criteriaType), strValue);
		}
		return result;
	}

	private static ObjectExt getObjectExt(int cdoDataType, ObjectExt obj)
	{
		switch (cdoDataType)
		{
		case DataType.STRING_TYPE:
		case DataType.BOOLEAN_TYPE:
		case DataType.DATE_TYPE:
		case DataType.DATE_ARRAY_TYPE:
		case DataType.DATETIME_ARRAY_TYPE:
		case DataType.DATETIME_TYPE:
		case DataType.DOUBLE_TYPE:
		case DataType.FLOAT_TYPE:
		case DataType.INTEGER_TYPE:
		case DataType.LONG_TYPE:
		case DataType.SHORT_TYPE:
		case DataType.TIME_ARRAY_TYPE:
		case DataType.TIME_TYPE:
			return obj;
		case DataType.BOOLEAN_ARRAY_TYPE:
			obj.setValue(Utility.parseBooleanArrayValue(obj.getValue()));
			return obj;
		case DataType.SHORT_ARRAY_TYPE:
			obj.setValue(Utility.parseShortArrayValue(obj.getValue()));
			return obj;
		case DataType.INTEGER_ARRAY_TYPE:
			obj.setValue(Utility.parseIntegerArrayValue(obj.getValue()));
			return obj;
		case DataType.LONG_ARRAY_TYPE:
			obj.setValue(Utility.parseLongArrayValue(obj.getValue()));
			return obj;
		case DataType.FLOAT_ARRAY_TYPE:
			obj.setValue(Utility.parseFloatArrayValue(obj.getValue()));
			return obj;
		case DataType.DOUBLE_ARRAY_TYPE:
			obj.setValue(Utility.parseDoubleArrayValue(obj.getValue()));
			return obj;
		case DataType.STRING_ARRAY_TYPE:
			obj.setValue(Utility.parseStringArrayValue(obj.getValue()));
			return obj;
		default:
			throw new RuntimeException(" Creteria type is not unSupported! ");
		}
	}

	private static ObjectExt getObject(int cdoDataType, Object obj)
	{
		ObjectExt result = new ObjectExt();
		result.setType(cdoDataType);
		result.setValue(Utility.parseObjectValue(cdoDataType, obj));
		return result;
	}

	// 操作相关
	private static Return add(String strTransName, Add add, CDO cdoRequest) throws Exception
	{
		if (add == null) return null;

		AddField[] fields = add.getAddField();
		String fieldName = null;
		String key = null;
		FieldTypeTypeType fieldType = null;
		Object value = null;
		BasicDBObject basicDBObject = new BasicDBObject();
		DBCollection coll = getCollection(add);
		try
		{
			coll.getDB().requestStart();
			for (AddField field : fields)
			{
				fieldName = field.getName();
				fieldType = field.getType();
				key = field.getValue();
				String strDefaultValue = field.getDefaultValue();
				if (key == null)
				{// 未设定值
					if (strDefaultValue == null)
					{// 未设定默认值
						if (field.getValueRequired())
						{// 不能为NULL
							throw new RuntimeException("Field [" + fieldName + "] does not exist ");
						}
						// 可以为NULL
						basicDBObject.put(fieldName, null);
					}
					else
					{// 有默认值,使用默认值
						setConstantsDBObject(basicDBObject, fieldName, TypeMapping.mappingToDataType(fieldType), strDefaultValue);
					}
					continue;
				}
				key = key.trim();
				if (key.startsWith("{") && key.endsWith("}"))
				{// 判断key是否为{....}，若是则为变量，不是则为常量
					key = getStringKey(key);
					if (!cdoRequest.exists(key))
					{// 请求参数无设定值
						if (strDefaultValue == null)
						{// 未设定默认值
							if (field.getValueRequired())
							{// 不能为NULL
								throw new RuntimeException("Field [" + fieldName + "] does not exist ");
							}
							// 可以为NULL
							basicDBObject.put(fieldName, null);
						}
						else
						{// 有默认值,使用默认值
							setConstantsDBObject(basicDBObject, fieldName, TypeMapping.mappingToDataType(fieldType), strDefaultValue);
						}
						continue;
					}

					if (fieldType != null)
					{
						setVarDBObject(basicDBObject, fieldName, TypeMapping.mappingToDataType(fieldType), cdoRequest, key);
					}
					else
					{
						setVarDBObject(basicDBObject, fieldName, cdoRequest.getObject(key).getType(), cdoRequest, key);
					}
				}
				else
				{
					value = key;
					if (fieldType == null)
					{
						throw new Exception("Field [" + fieldName + "] type is not set!");
					}
					setConstantsDBObject(basicDBObject, fieldName, TypeMapping.mappingToDataType(fieldType), value);
				}
			}
			onBeforeAdd(strTransName, basicDBObject);
			coll.insert(basicDBObject);
		}
		catch (Exception ex)
		{
			logger.error("add:" + ex.getMessage(), ex);
			throw ex;
		}
		finally
		{
			closeCollection(coll);
		}
		return Return.OK;
	}

	private static void onBeforeAdd(String strTransName, BasicDBObject basicDBObject)
	{
		if (logger.isDebugEnabled())
		{
			logger.debug(strTransName + "-addField:" + basicDBObject);
		}
	}

	/**
	 * 更新符合条件的对象
	 * 如果指定插入标记(insert)为true,则在查询不到对象时,新增一个对象到集合,这个对象是更新字段和条件字段的组合.默认不插入
	 * 如果指定了多行标记(multiLine)为false,则只更新第一条;默认更新所有符合条件的对象
	 * 
	 * @param modify
	 * @param cdoRequest
	 * @param cdoBlock
	 * @return
	 * @throws Exception
	 */
	private static Return modify(String strTransName, Modify modify, CDO cdoRequest) throws Exception
	{
		if (modify == null)
		{
			return null;
		}

		// init
		// field----------------------------------------------------------------------------------------
		BasicDBObject basicDBObject = new BasicDBObject();
		ModifyField[] fields = modify.getModifyField();
		if (fields == null)
		{
			throw new Exception("Field define is expected under modify tag");
		}

		BasicDBObject bdoINC = null;
		BasicDBObject bdoSET = null;
		BasicDBObject bdoUNSET = null;
		BasicDBObject bdoPUSH = null;
		BasicDBObject bdoPUSHALL = null;
		BasicDBObject bdoAddToSET = null;
		BasicDBObject bdoPOP = null;
		BasicDBObject bdoPULL = null;
		BasicDBObject bdoPULLALL = null;

		for (ModifyField field : fields)
		{
			// 字段类型
			int nType = DataType.NONE_TYPE;
			if (field.getType() != null)
			{
				nType = TypeMapping.mappingToDataType(field.getType());
			}
			// 字段名
			String strFieldName = field.getName();

			// 确定更新操作类型和容器
			BasicDBObject subObject = null;
			ModifyFieldMethodType mmt = field.getMethod();
			boolean bPut = false;
			if (mmt != null)
			{
				switch (mmt.getType())
				{
				case ModifyFieldMethodType.INC_TYPE:
				{
					if (bdoINC == null)
					{
						bdoINC = new BasicDBObject();
					}
					subObject = bdoINC;
					break;
				}
				case ModifyFieldMethodType.SET_TYPE:
				{
					if (bdoSET == null)
					{
						bdoSET = new BasicDBObject();
					}
					subObject = bdoSET;
					break;
				}
				case ModifyFieldMethodType.POP_TYPE:
				{
					if (bdoPOP == null)
					{
						bdoPOP = new BasicDBObject();
					}
					subObject = bdoPOP;
					break;
				}
				case ModifyFieldMethodType.PULL_TYPE:
				{
					if (bdoPULL == null)
					{
						bdoPULL = new BasicDBObject();
					}
					subObject = bdoPULL;
					break;
				}
				case ModifyFieldMethodType.PULLALL_TYPE:
				{
					if (bdoPULLALL == null)
					{
						bdoPULLALL = new BasicDBObject();
					}
					subObject = bdoPULLALL;
					break;
				}
				case ModifyFieldMethodType.PUSH_TYPE:
				{
					if (bdoPUSH == null)
					{
						bdoPUSH = new BasicDBObject();
					}
					subObject = bdoPUSH;
					break;
				}
				case ModifyFieldMethodType.PUSHALL_TYPE:
				{
					if (bdoPUSHALL == null)
					{
						bdoPUSHALL = new BasicDBObject();
					}
					subObject = bdoPUSHALL;
					break;
				}
				case ModifyFieldMethodType.UNSET_TYPE:
				{
					if (bdoUNSET == null)
					{
						bdoUNSET = new BasicDBObject();
					}
					subObject = bdoUNSET;
					;
					break;
				}
				case ModifyFieldMethodType.ADDTOSET_TYPE:
				{
					if (bdoAddToSET == null)
					{
						bdoAddToSET = new BasicDBObject();
					}
					subObject = bdoAddToSET;
					break;
				}
				case ModifyFieldMethodType.SETNULL_TYPE:
				{
					if (bdoSET == null)
					{
						bdoSET = new BasicDBObject();
					}
					subObject = bdoSET;
					bdoSET.put(strFieldName, null);
					bPut = true;
					break;
				}
				default:
				{// TODO 其它方法也需要支持
					throw new Exception(" This version can not support:"
							+ mmt.toString());
				}
				}
			}
			else
			{
				if (bdoSET == null)
				{
					bdoSET = new BasicDBObject();
				}
				subObject = bdoSET;
			}
			if (bPut)
			{
				continue;
			}

			// 字段值
			String strValue = field.getValue();

			boolean bValueRequired = field.getValueRequired();

			if (strValue == null)
			{
				if (bValueRequired)
				{
					throw new Exception("Field value define is expected under modify tag. FieldName is " + strFieldName);
				}
				else
				{
					continue;
				}
			}
			if (strValue.startsWith("{") && strValue.endsWith("}"))
			{
				strValue = getStringKey(strValue);
				if (!cdoRequest.exists(strValue))
				{
					if (bValueRequired)
					{
						throw new Exception("Field value does not exist under modify tag. FieldName is " + strFieldName);
					}
					else
					{
						continue;
					}
				}
				ObjectExt objExt = cdoRequest.getObject(strValue);
				if (nType == DataType.NONE_TYPE)
				{
					nType = objExt.getType();
				}
				setVarDBObject(subObject, strFieldName, nType, objExt);
			}
			else
			{
				if (nType == DataType.NONE_TYPE)
				{
					throw new Exception("Field type define is expected under modify tag. FieldName is " + strFieldName);
				}
				setConstantsDBObject(subObject, strFieldName, nType, strValue);
			}
		}

		// 生成更新对象
		if (bdoSET != null)
		{
			basicDBObject.put(INoSQLDataEngine.UPDATE_METHOD_SET, bdoSET);
		}
		if (bdoINC != null)
		{
			basicDBObject.put(INoSQLDataEngine.UPDATE_METHOD_INC, bdoINC);
		}
		if (bdoUNSET != null)
		{
			basicDBObject.put(INoSQLDataEngine.UPDATE_METHOD_UNSET, bdoUNSET);
		}
		if (bdoPUSH != null)
		{
			basicDBObject.put(INoSQLDataEngine.UPDATE_METHOD_PUSH, bdoPUSH);
		}
		if (bdoPUSHALL != null)
		{
			basicDBObject.put(INoSQLDataEngine.UPDATE_METHOD_PUTALL, bdoPUSHALL);
		}
		if (bdoAddToSET != null)
		{
			basicDBObject.put(INoSQLDataEngine.UPDATE_METHOD_ADDTOSET, bdoAddToSET);
		}
		if (bdoPOP != null)
		{
			basicDBObject.put(INoSQLDataEngine.UPDATE_METHOD_POP, bdoPOP);
		}
		if (bdoPULL != null)
		{
			basicDBObject.put(INoSQLDataEngine.UPDATE_METHOD_PULL, bdoPULL);
		}
		if (bdoPULLALL != null)
		{
			basicDBObject.put(INoSQLDataEngine.UPDATE_METHOD_PULLALL, bdoPULLALL);
		}

		// 生成查询条件--------------------------------------------------------------------------------------
		BasicDBObject query = new BasicDBObject();
		creterias(query, modify.getCreterias(), cdoRequest);

		// 数据库操作----------------------------------------------------------------------------------------
		DBCollection collection = getCollection(modify);
		WriteResult wr = null;
		// start request

		try
		{
			collection.getDB().requestStart();

			// 执行操作
			onBeforeModify(strTransName, query, basicDBObject);
			wr = collection.update(query, basicDBObject, modify.getInsert(), modify.getMultiLine());

			String strRecordCountId = modify.getRecordCountId();
			recordEffectCount(cdoRequest, wr, strRecordCountId);

		}
		catch (Exception e)
		{
			logger.error("modify:" + e.getMessage(), e);
			return Return.valueOf(-1, e.getLocalizedMessage());
		}
		finally
		{
			closeCollection(collection);
		}
		return Return.OK;
	}

	protected static void onBeforeModify(String strTransName, BasicDBObject query, BasicDBObject basicDBObject)
	{
		if (logger.isDebugEnabled())
		{
			logger.debug(strTransName + "-query:" + query);
		}
		if (logger.isDebugEnabled())
		{
			logger.debug(strTransName + "-update field::" + basicDBObject);
		}
	}

	/**
	 * 替换第一条符合条件的记录
	 * 
	 * @param replace
	 * @param cdoRequest
	 * @param cdoBlock
	 * @return
	 * @throws Exception
	 */
	private static Return replace(String strTransName, Replace replace, CDO cdoRequest) throws Exception
	{
		if (replace == null)
		{
			return null;
		}

		// init
		// field----------------------------------------------------------------------------------------
		BasicDBObject basicDBObject = new BasicDBObject();
		ReplaceField[] fields = replace.getReplaceField();
		if (fields == null)
		{
			throw new Exception("Field define is expected under replace tag");
		}

		for (ReplaceField field : fields)
		{

			// 字段类型
			int nType = DataType.NONE_TYPE;
			if (field.getType() != null)
			{
				nType = TypeMapping.mappingToDataType(field.getType());
			}
			// 字段名
			String strFieldName = field.getName();

			// 字段值
			String strValue = field.getValue();

			if (strValue == null)
			{
				if (field.getValueRequired())
				{
					throw new Exception("Field value define is expected under modify tag. FieldName is " + strFieldName);
				}
				else
				{
					continue;
				}
			}
			if (strValue.startsWith("{") && strValue.endsWith("}"))
			{
				strValue = getStringKey(strValue);
				if (!cdoRequest.exists(strValue))
				{
					if (field.getValueRequired())
					{
						throw new Exception("Field value does not exist under modify tag. FieldName is " + strFieldName);
					}
					basicDBObject.put(strFieldName, null);
					continue;
				}
				ObjectExt objExt = cdoRequest.getObject(strValue);
				if (nType == DataType.NONE_TYPE)
				{
					nType = objExt.getType();
				}
				setVarDBObject(basicDBObject, strFieldName, nType, objExt);
			}
			else
			{
				if (nType == DataType.NONE_TYPE)
				{
					throw new Exception("Field type define is expected under modify tag. FieldName is " + strFieldName);
				}
				setConstantsDBObject(basicDBObject, strFieldName, nType, strValue);
			}
		}

		// 生成查询条件--------------------------------------------------------------------------------------
		BasicDBObject query = new BasicDBObject();
		creterias(query, replace.getCreterias(), cdoRequest);
		// 数据库操作----------------------------------------------------------------------------------------
		DBCollection collection = getCollection(replace);
		WriteResult wr = null;
		// start request

		try
		{
			collection.getDB().requestStart();

			// 执行操作
			onBeforeModify(strTransName, query, basicDBObject);
			wr = collection.update(query, basicDBObject, true, false);

			String strRecordCountId = replace.getRecordCountId();
			recordEffectCount(cdoRequest, wr, strRecordCountId);
		}
		catch (Exception e)
		{
			logger.error("replace:" + e.getMessage(), e);
			return Return.valueOf(-1, e.getLocalizedMessage());
		}
		finally
		{
			closeCollection(collection);
		}
		return Return.OK;
	}

	/**
	 * 移除符合条件的对象
	 * 
	 * @param remove
	 * @param cdoRequest
	 * @param cdoBlock
	 * @return
	 */
	private static Return remove(String strTransName, Remove remove, CDO cdoRequest)
	{
		if (remove == null)
		{
			return null;
		}

		// 生成查询条件--------------------------------------------------------------------------------------
		BasicDBObject query = new BasicDBObject();
		creterias(query, remove.getCreterias(), cdoRequest);

		// 数据库操作----------------------------------------------------------------------------------------
		DBCollection collection = getCollection(remove);
		WriteResult wr = null;
		try
		{
			// start request
			collection.getDB().requestStart();

			onRemoveBefore(strTransName, query);
			wr = collection.remove(query);

			String strRecordCountId = remove.getRecordCountId();
			recordEffectCount(cdoRequest, wr, strRecordCountId);
		}
		catch (Exception e)
		{
			logger.error("remove:" + e.getMessage(), e);
			return Return.valueOf(-1, e.getLocalizedMessage());
		}
		finally
		{
			closeCollection(collection);
		}
		return Return.OK;
	}

	private static void onRemoveBefore(String strTransName, BasicDBObject query)
	{
		if (logger.isDebugEnabled())
		{
			logger.debug(strTransName + "-query:" + query);
		}
	}

	private static void setVarDBObject(BasicDBObject basicDBObject, String fieldName, int nDataType, CDO cdoRequest, String key) throws Exception
	{
		ObjectExt objExt = cdoRequest.getObject(key);
		setVarDBObject(basicDBObject, fieldName, nDataType, objExt);
	}

	private static void setVarDBObject(BasicDBObject basicDBObject, String fieldName, int nDataType, ObjectExt objExt) throws Exception
	{
		switch (nDataType)
		{
		case DataType.STRING_TYPE:
			basicDBObject.put(fieldName, objExt.getValue());
			break;
		case DataType.LONG_TYPE:
			basicDBObject.put(fieldName, objExt.getValue());
			break;
		case DataType.INTEGER_TYPE:
			basicDBObject.put(fieldName, objExt.getValue());
			break;
		case DataType.DATETIME_TYPE:
			basicDBObject.put(fieldName, objExt.getValue());
			break;
		case DataType.CDO_TYPE:
			CDO cdo = (CDO) objExt.getValue();
			BasicDBObject subDBObject = new BasicDBObject();
			setCDODBObject(subDBObject, cdo);
			basicDBObject.put(fieldName, subDBObject);
			break;
		case DataType.CDO_ARRAY_TYPE:
			CDO[] cdoArr = (CDO[]) objExt.getValue();
			BasicDBObject[] subDBObjectArr = new BasicDBObject[cdoArr.length];
			for (int i = 0; i < cdoArr.length; i++)
			{
				subDBObjectArr[i] = new BasicDBObject();
				setCDODBObject(subDBObjectArr[i], cdoArr[i]);
			}
			basicDBObject.put(fieldName, subDBObjectArr);
			break;
		case DataType.BOOLEAN_TYPE:
			basicDBObject.put(fieldName, objExt.getValue());
			break;
		case DataType.BOOLEAN_ARRAY_TYPE:
			basicDBObject.put(fieldName, objExt.getBlooeanObjArray());
			break;
		case DataType.DATE_TYPE:
			basicDBObject.put(fieldName, objExt.getValue());
			break;
		case DataType.DATE_ARRAY_TYPE:
			basicDBObject.put(fieldName, objExt.getValue());
			break;
		case DataType.DATETIME_ARRAY_TYPE:
			basicDBObject.put(fieldName, objExt.getValue());
			break;
		case DataType.DOUBLE_TYPE:
			basicDBObject.put(fieldName, objExt.getValue());
			break;
		case DataType.DOUBLE_ARRAY_TYPE:
			basicDBObject.put(fieldName, objExt.getDoubleObjArray());
			break;
		case DataType.FLOAT_TYPE:
			basicDBObject.put(fieldName, objExt.getValue());
			break;
		case DataType.FLOAT_ARRAY_TYPE:
			basicDBObject.put(fieldName, objExt.getFloatObjArray());
			break;
		case DataType.INTEGER_ARRAY_TYPE:
			basicDBObject.put(fieldName, objExt.getIntegerObjArray());
			break;
		case DataType.LONG_ARRAY_TYPE:
			basicDBObject.put(fieldName, objExt.getLongObjArray());
			break;
		case DataType.SHORT_TYPE:
			basicDBObject.put(fieldName, objExt.getValue());
			break;
		case DataType.SHORT_ARRAY_TYPE:
			basicDBObject.put(fieldName, objExt.getShortObjArray());
			break;
		case DataType.STRING_ARRAY_TYPE:
			basicDBObject.put(fieldName, objExt.getValue());
			break;
		case DataType.TIME_TYPE:
			basicDBObject.put(fieldName, objExt.getValue());
			break;
		case DataType.TIME_ARRAY_TYPE:
			basicDBObject.put(fieldName, objExt.getValue());
			break;
		case DataType.BYTE_TYPE:// TODO
			break;
		case DataType.BYTE_ARRAY_TYPE:// TODO
			break;
		default:
			throw new Exception(" fieldName [" + fieldName + "]  unsupport type!");
		}
	}

	/**
	 * 处理子对象CDO
	 * 
	 * @param basicDBObject
	 * @param cdo
	 * @throws Exception
	 */
	private static void setCDODBObject(BasicDBObject basicDBObject, CDO cdo) throws Exception
	{
		String fieldName = null;
		int cdoFieldType = DataType.NONE_TYPE;

//		String[] strFieldIds = cdo.getFieldIds();
//		ObjectExt[] objs = cdo.getFieldValues();
//		for (int i = 0; i < strFieldIds.length; i++)
//		{
//			fieldName = strFieldIds[i];
//			cdoFieldType = objs[i].getType();
//			setVarDBObject(basicDBObject, fieldName, cdoFieldType, cdo, fieldName);
//		}
		
		for (Iterator<Map.Entry<String, ObjectExt>> it=cdo.iterator();it.hasNext();)
		{
			Entry<String, ObjectExt> entry=it.next();
			fieldName = entry.getKey();
			cdoFieldType = entry.getValue().getType();
			setVarDBObject(basicDBObject, fieldName, cdoFieldType, cdo, fieldName);
		}		
	}

	/**
	 * 直接用XML中填的值,而不是从CDO中取的处理逻辑
	 * 
	 * @param basicDBObject
	 * @param fieldName
	 * @param nDataType
	 * @param value
	 * @throws Exception
	 */
	private static void setConstantsDBObject(BasicDBObject basicDBObject, String fieldName, int nDataType, Object value) throws Exception
	{
		switch (nDataType)
		{
		case DataType.STRING_TYPE:
			basicDBObject.put(fieldName, value.toString());
			break;
		case DataType.LONG_TYPE:
			basicDBObject.put(fieldName, Utility.parseLongValue(value));
			break;
		case DataType.SHORT_TYPE:
			basicDBObject.put(fieldName, Utility.parseShortValue(value));
			break;
		case DataType.INTEGER_TYPE:
			basicDBObject.put(fieldName, Utility.parseIntegerValue(value));
			break;
		case DataType.DATETIME_TYPE:
		{
			String strDT = null;
			if (FUNCTION_DATETIME.equalsIgnoreCase(value.toString()))
			{
				try
				{
					strDT = DateTime.Now().toString(DataType.DATETIME_FORMAT_STRING);
				}
				catch (Exception e)
				{// 保证不会出错
				}
			}
			else
			{
				strDT = Utility.parseDateTimeValue(value);
			}
			basicDBObject.put(fieldName, strDT);
			break;
		}
		case DataType.BOOLEAN_TYPE:
			basicDBObject.put(fieldName, Utility.parseBooleanValue(value));
			break;
		case DataType.DATE_TYPE:
		{
			String strDate = null;
			if (FUNCTION_DATETIME.equalsIgnoreCase(value.toString()))
			{
				try
				{
					strDate = DateTime.Now().toString(DataType.DATE_FORMAT_STRING);
				}
				catch (Exception e)
				{// 保证不会出错
				}
			}
			else
			{
				strDate = Utility.parseDateValue(value);
			}
			basicDBObject.put(fieldName, strDate);
			break;
		}
		case DataType.TIME_TYPE:
		{
			String strTime = null;
			if (FUNCTION_DATETIME.equalsIgnoreCase(value.toString()))
			{
				try
				{
					strTime = DateTime.Now().toString(DataType.TIME_FORMAT_STRING);
				}
				catch (Exception e)
				{// 保证不会出错
				}
			}
			else
			{
				strTime = Utility.parseTimeValue(value);
			}
			basicDBObject.put(fieldName, strTime);
			break;
		}
		case DataType.DOUBLE_TYPE:
			basicDBObject.put(fieldName, Utility.parseDoubleValue(value));
			break;
		case DataType.FLOAT_TYPE:
			basicDBObject.put(fieldName, Utility.parseFloatValue(value));
			break;
		default:
			throw new Exception(" Constants fieldName [" + fieldName + "]  unsupport type!");
		}
	}

	public static DBCollection getCollection(CollectionNameType collectionNameType)
	{
		return collectionNameType.getNosqlDataEngine().getCollection(collectionNameType.getDBName(),collectionNameType.getCollectionName());
	}

	public static DB getDB(CollectionNameType collectionNameType)
	{
		return collectionNameType.getNosqlDataEngine().getDB(collectionNameType.getDBName());
	}

	// 事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	// 事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------
	public static void onException(String strText, Exception e)
	{
		logger.error(strText, e);
	}

	public static void onTransEnd()
	{// TODO Auto-generated method stub

	}

	public static void onTransStart()
	{// TODO Auto-generated method stub

	}
	// 构造函数,所有构造函数在此定义------------------------------------------------------------------------------

}
