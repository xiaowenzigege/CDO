package com.cdoframework.cdolib.servicebus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.cdoframework.cdolib.annotation.TransName;
import com.cdoframework.cdolib.base.CycleList;
import com.cdoframework.cdolib.base.ObjectExt;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.base.Validator;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.database.IDataEngine;
public abstract class TransService implements ITransService
{
	private Logger logger = Logger.getLogger(TransService.class);
	private String strServiceName;
	protected IServiceBus serviceBus=null;
	protected IServicePlugin servicePlugin=null;
	protected IService service = null;
	protected Map<String ,Collection<Validator.FieldBean>> validateMap = new HashMap<String ,Collection<Validator.FieldBean>>();
	protected Map<String, Method> transMap = new HashMap<String, Method>();

	final public void setServiceBus(IServiceBus serviceBus)
	{
		this.serviceBus=serviceBus;
	}

	final public void setServicePlugin(IServicePlugin servicePlugin)
	{
		this.servicePlugin=servicePlugin;
	}

	final public void setService(IService service)
	{
		this.service = service;
	}
	final public IService getService()
	{
		return this.service;
	}
	public Return init()
	{	
		return Return.OK;
	}
	
	@Override
	public void inject(ITransService child) {
		if(child != null)
		{
			Class<?> cls = child.getClass();
			Method[] methods = cls.getMethods();
			TransName transName = null;
			String name = null;
			for(Method method : methods) {
				// 查找所有带@TransName方法
				if(method.isAnnotationPresent(TransName.class)) {
					transName = method.getAnnotation(TransName.class);
					name = transName.name();
					if(name == null || name.equals("")) {
						name = method.getName();
					}
					// 一个服务类禁止出现重名的transName
					if(transMap.put(name, method) != null) {
						logger.error("存在同名的transName："+ name);
						System.exit(-1);
					}
				}
			}
		}
	}
	/**
	 * 设置服务名
	 * @param strServiceName
	 */
	final public void setServiceName(String strServiceName)
	{
		this.strServiceName = strServiceName;
	}
	
	/**
	 * 取服务名
	 * @return
	 */
	final public String getServiceName()
	{
		return this.strServiceName;
	}

	public void destroy()
	{
	}

	public void handleEvent(CDO cdoEvent)
	{
	}
	
	public Return validate(CDO cdoRequest)
	{
		String strTransName = "";
	 
		try
		{
			strTransName = cdoRequest.getStringValue("strTransName");
		}catch(Exception e)
		{
			return Return.valueOf(-1,"参数strTransName不存在");
		}
		String vs = Validator.validate(validateMap.get(strTransName),cdoRequest); 
		if("".equalsIgnoreCase(vs)){  
			return this.validateArray(cdoRequest,strTransName);
		}else
		{
			return Return.valueOf(-1, vs);
		}
	}
	
//	private ThreadLocal<String> threadLocal = new ThreadLocal<String>();
	 
	
	private Return validateArray(CDO cdoRequest,String strTransName){
//		 ObjectExt[] fs  = cdoRequest.getFieldValues();
//		 String[] strsFieldIds=cdoRequest.getFieldIds();
//		  
//		 	for(int i=0;i<fs.length;i++){
//		 		ObjectExt f=fs[i];
//		 	   String name =	strsFieldIds[i].toLowerCase();
//		 	   String key = (strTransName!=null?(strTransName.toLowerCase()+"."):"")+name;
//		 	   Object value = f.getValue();
//		 	   if(value instanceof CDO){
//		 		  Return ret =	validate((CDO)value,validateMap.get(key));
//		 	    	if( ret.getCode() == -1){
//		 	    		return ret; 
//		 	    	} 
//		 		  
//		 	   }else if(value instanceof CDO[]  ){
//		 	     for(CDO d :(CDO[])value){ 
//		 	    	Return ret =	validate(d,validateMap.get(key));
//		 	    	if( ret.getCode() == -1){
//		 	    		return ret;  
//		 	    	}  
//		 	      }
//		 	   } 
//		 	}  
//		 return Return.OK;
		
//		 ObjectExt[] fs  = cdoRequest.getFieldValues();
//		 String[] strsFieldIds=cdoRequest.getFieldIds();
//		  
		 	for(Iterator<Map.Entry<String, ObjectExt>> it=cdoRequest.iterator();it.hasNext();){
		 		Entry<String,ObjectExt> entry=it.next();
		 		ObjectExt f=entry.getValue();
		 	    String name =entry.getKey().toLowerCase();
		 	    String key = (strTransName!=null?(strTransName.toLowerCase()+"."):"")+name;
		 	    Object value = f.getValue();
		 	    if(value instanceof CDO){
		 		  Return ret =	validate((CDO)value,validateMap.get(key));
		 	    	if( ret.getCode() == -1){
		 	    		return ret; 
		 	    	} 
		 		  
		 	    }else if(value instanceof CDO[]  ){
		 	     for(CDO d :(CDO[])value){ 
		 	    	Return ret =	validate(d,validateMap.get(key));
		 	    	if( ret.getCode() == -1){
		 	    		return ret;  
		 	    	}  
		 	      }
		 	   } 
		 	}  
		 return Return.OK;		
	}
	
	protected Return validate(CDO cdoRequest ,Collection<Validator.FieldBean> validatorCollection )
	{
		 if(validatorCollection == null)
			 return Return.OK; 
		String vs = Validator.validate(validatorCollection,cdoRequest); 
		if("".equalsIgnoreCase(vs)){
			return Return.OK;
		}else
		{
			return Return.valueOf(-1, vs);
		}
	}
	
	protected void addvalidate()
	{		
	}
	
	@Override
	public final Return processTrans(CDO cdoRequest, CDO cdoResponse) {
		String strTransName = cdoRequest.getStringValue("strTransName");
		Method method = null;
		if((method = transMap.get(strTransName)) != null) {
			try {
				return (Return) method.invoke(this, cdoRequest, cdoResponse);
			} catch (IllegalArgumentException e) {
				logger.warn(strTransName+": 参数错误"+ cdoRequest+cdoResponse);
			} catch (IllegalAccessException e) {
				logger.warn(strTransName+": 函数访问错误IllegalAccessException");
			} catch (InvocationTargetException e) {
				logger.warn(strTransName+": 函数调用错误InvocationTargetException");
			}
		} 
		return null;
	}
	
	@Override
	public boolean containsTrans(String strTransName) {
		return transMap.containsKey(strTransName);
	}
	@Override
	public Connection getConnection(String strDataGroupId) throws SQLException{
		CycleList<IDataEngine> clDataEngine=this.serviceBus.getHMDataGroup().get(strDataGroupId);
		IDataEngine dataEngine=clDataEngine.get();
		return dataEngine.getConnection();
	}
}
