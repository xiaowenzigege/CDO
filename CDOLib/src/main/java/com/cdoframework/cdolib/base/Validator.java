package com.cdoframework.cdolib.base;
import java.util.*;
import com.cdoframework.cdolib.data.cdo.CDO;

public class Validator{
	
	
	public static String validate(Collection<Validator.FieldBean > col , CDO cdoRequest){
		String retStr="";
		if(col== null || col.isEmpty())
		{
			return retStr;
		}
		StringBuilder sb=new StringBuilder(50);
		Iterator<FieldBean> iter = col.iterator();
		while(iter.hasNext()){
			FieldBean f = iter.next();
			Class  c = f.getFieldType();
			String className = c.getName() ;  
			try{
				if( className .equals( Integer.class.getName())  ){
					
					if(f.getDefaultValue()!=null){
						if( !cdoRequest.exists(f.getFieldId()))
						cdoRequest.setIntegerValue(f.getFieldId(),(Integer)f.getDefaultValue()); 
					}
					Integer v = cdoRequest.getIntegerValue(f.getFieldId());  
					if(f.getMinValue() != null && v.intValue() < ((Integer)f.getMinValue()).intValue()){
						sb.append(f.getFieldId()+",");
					}
				}else if( className .equals( String.class.getName())  ){
					if(f.getDefaultValue()!=null){
						if( !cdoRequest.exists(f.getFieldId()))
						cdoRequest.setStringValue(f.getFieldId(),(String)f.getDefaultValue()); 
					}
					  cdoRequest.getStringValue(f.getFieldId());
					
				}else if( className .equals( Short.class.getName())){
					
					if(f.getDefaultValue()!=null){
						if( !cdoRequest.exists(f.getFieldId()))
						cdoRequest.setShortValue(f.getFieldId(),(Short)f.getDefaultValue()); 
					}
					
					Short v =cdoRequest.getShortValue(f.getFieldId());  
					if(f.getMinValue() != null && v.shortValue() < ((Short)f.getMinValue()).shortValue()){
						sb.append(f.getFieldId()+",");
					}
				}else if( className .equals( Long.class.getName())){
					
					if(f.getDefaultValue()!=null){
						if( !cdoRequest.exists(f.getFieldId()))
						cdoRequest.setLongValue(f.getFieldId(),(Long)f.getDefaultValue()); 
					} 
					
					Long v =  cdoRequest.getLongValue(f.getFieldId()); 
					if(f.getMinValue() != null && v.longValue() < ((Long)f.getMinValue()).longValue()){
						sb.append(f.getFieldId()+",");
					}
				}else if( className .equals( Boolean.class.getName())){
					
					if(f.getDefaultValue()!=null){
						if( !cdoRequest.exists(f.getFieldId()))
						cdoRequest.setBooleanValue(f.getFieldId(),(Boolean)f.getDefaultValue()); 
					} 						
					cdoRequest.getBooleanValue(f.getFieldId()); 		
				}else 
				{
					cdoRequest.getObjectValue(f.getFieldId());
					
				}
			}
			catch(Exception ex)
			{
				sb.append(f.getFieldId()+",");
			}

			
		}
		if(sb.toString().trim().length()>0){
			retStr="参数["+sb.toString().substring(0,sb.toString().length()-1)+"]不存在或 参数类型不合法 或 参数值为null";
		}
		return retStr;
	}
	
	public static class FieldBean{
		
		private String fieldId;
		
		private Class fieldType;
		
		private Object minValue;
		
		private Object defaultValue;

		public Object getDefaultValue()
		{
			return defaultValue;
		}

		public FieldBean setDefaultValue(Object defaultValue)
		{
			this.defaultValue=defaultValue;
			return this;
		}

		public FieldBean(String fieldId, Class fieldType){
			this.fieldId = fieldId;
			this.fieldType = fieldType;
		}
		
		public FieldBean(String fieldId,Class fieldType,Object defValue)
		{
			this.fieldId = fieldId;
			this.fieldType = fieldType;
			this.defaultValue=defValue;
		}
		
		public FieldBean(){
			
		}
		
		public String getFieldId()
		{
			return fieldId;
		}

		public FieldBean setFieldId(String fieldId)
		{
			this.fieldId=fieldId;
			return this;
		}

		public Class getFieldType()
		{
			return this.fieldType;
		}

		public FieldBean setFieldType(Class fieldType)
		{
			this.fieldType=fieldType;
			return this;
		}

		public Object getMinValue()
		{
			return minValue;
		}

		public FieldBean setMinValue(Object minValue)
		{
			this.minValue=minValue;
			return this;
		} 

	}

}
