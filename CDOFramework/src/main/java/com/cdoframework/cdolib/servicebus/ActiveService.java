package com.cdoframework.cdolib.servicebus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.cdoframework.cdolib.annotation.TransName;
import com.cdoframework.cdolib.base.ObjectExt;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.base.ThreadExt;
import com.cdoframework.cdolib.base.Validator;
import com.cdoframework.cdolib.data.cdo.CDO;

public abstract class ActiveService extends ThreadExt implements IActiveService
{
	private Logger logger = Logger.getLogger(ActiveService.class);
	
	protected Map<String, Method> activeMap = new HashMap<String, Method>();
	
	public Return validate(CDO cdoRequest)
	{
		String strTransName = "";
	 
		try
		{
			strTransName = cdoRequest.getStringValue("strTransName");
		}catch(Exception e)
		{
			logger.error("validate:"+e.getMessage(),e);
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
	
	protected Map<String ,Collection<Validator.FieldBean>> validateMap = new HashMap<String ,Collection<Validator.FieldBean>>();

	private Return  validateArray(CDO cdoRequest,String strTransName){
//		 ObjectExt[] fs  = cdoRequest.getFieldValues();
//		 String[] strsFieldIds=cdoRequest.getFieldIds();
//		  
//		 	for(int i=0;i<fs.length;i++){
//		 		ObjectExt f=fs[i];
//			 	String name =	strsFieldIds[i].toLowerCase();
//		 	   String key = (str!=null?(str.toLowerCase()+"."):"")+name;
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

	protected IServiceBus serviceBus=null;
	protected IServicePlugin servicePlugin=null;
	private String strServiceName;
	protected IService service;
	/**
	 * 设置服务名
	 * @param strServiceName
	 */
	final  public void setServiceName(String strServiceName)
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
					if(activeMap.put(name, method) != null) {
						logger.error("存在同名的transName："+ name);
						System.exit(-1);
					}
				}
			}
		}
	}

	public void destroy()
	{
	}

	public void handleEvent(CDO cdoEvent)
	{
	}
	
	public Return handleTrans(CDO cdoRequest,CDO cdoResponse)
	{
		return null;
	}

	private boolean bIsCustered=false;
	
	public boolean isClusterd()
	{
		return this.bIsCustered;
	}
	
	public void setClustered(boolean bIsClustered)
	{
		this.bIsCustered=bIsClustered;
	}
	protected void addvalidate()
	{
		
	}
	
	@Override
	public final Return processTrans(CDO cdoRequest, CDO cdoResponse) {
		String strTransName = cdoRequest.getStringValue("strTransName");
		Method method = null;
		if((method = activeMap.get(strTransName)) != null) {
			try {
				return (Return) method.invoke(this, cdoRequest, cdoResponse);
			} catch (IllegalArgumentException e) {
				logger.warn(strTransName+ ": 参数错误"+cdoRequest+cdoResponse);
			} catch (IllegalAccessException e) {
				logger.warn(strTransName+": 函数访问错误IllegalAccessException");
			} catch (InvocationTargetException e) {
				logger.warn(strTransName+ ": 函数调用错误InvocationTargetException");
			}
		}
		return null;
	}
	
	@Override
	public boolean containsTrans(String strTransName) {
		return activeMap.containsKey(strTransName);
	}

}
