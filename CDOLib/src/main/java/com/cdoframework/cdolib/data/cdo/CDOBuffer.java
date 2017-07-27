package com.cdoframework.cdolib.data.cdo;

import java.nio.ByteBuffer;

import com.cdoframework.cdolib.base.DataType;
import com.cdoframework.cdolib.data.cdo.CDO.FieldId;
/**
 * 
 * @author KenelLiu
 *
 */
public class CDOBuffer {


    protected void setBooleanValue(CDO cdo,String strFieldId,ByteBuffer buffer)
    {
    	
    	FieldId fieldId=cdo.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	BooleanField field=new BooleanField(fieldId.strFieldId,buffer);
   		cdo.setObjectValue(fieldId,DataType.BOOLEAN_TYPE,null,field,cdo);
    }
    
    protected void setShortValue(CDO cdo,String strFieldId,ByteBuffer buffer)
    {
    	
    	FieldId fieldId=cdo.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	Field field=new ShortField(fieldId.strFieldId,buffer);
		cdo.setObjectValue(fieldId,DataType.SHORT_TYPE,null,field,cdo);
    }

    protected void setIntegerValue(CDO cdo,String strFieldId,ByteBuffer buffer)
    {
    	
    	FieldId fieldId=cdo.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	Field field=new IntegerField(fieldId.strFieldId,buffer);
		cdo.setObjectValue(fieldId,DataType.INTEGER_TYPE,null,field,cdo);
    }

    protected void setLongValue(CDO cdo,String strFieldId,ByteBuffer buffer)
    {
    	
    	
    	FieldId fieldId=cdo.parseFieldId(strFieldId);

    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	Field field=new LongField(fieldId.strFieldId,buffer);
		cdo.setObjectValue(fieldId,DataType.LONG_TYPE,null,field,cdo);
    }

    protected void setFloatValue(CDO cdo,String strFieldId,ByteBuffer buffer)
    {
    	

    	FieldId fieldId=cdo.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	Field field=new FloatField(fieldId.strFieldId,buffer);
		cdo.setObjectValue(fieldId,DataType.FLOAT_TYPE,null,field,cdo);
    }

    protected void setDoubleValue(CDO cdo,String strFieldId,ByteBuffer buffer)
    {
    	

    	FieldId fieldId=cdo.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	Field field=new DoubleField(fieldId.strFieldId,buffer);
		cdo.setObjectValue(fieldId,DataType.DOUBLE_TYPE,null,field,cdo);
    }

    protected void setStringValue(CDO cdo,String strFieldId,ByteBuffer buffer)
    {
    	

    	FieldId fieldId=cdo.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	Field field=new StringField(fieldId.strFieldId,buffer);
		cdo.setObjectValue(fieldId,DataType.STRING_TYPE,null,field,cdo);
    }

    
    protected void setDateValue(CDO cdo,String strFieldId,ByteBuffer buffer)
    {
    	

    	FieldId fieldId=cdo.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	Field field=new DateField(fieldId.strFieldId,buffer);
		cdo.setObjectValue(fieldId,DataType.DATE_TYPE,null,field,cdo);
    }

    protected void setTimeValue(CDO cdo,String strFieldId,ByteBuffer buffer)
    {
    	

    	FieldId fieldId=cdo.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	Field field=new TimeField(fieldId.strFieldId,buffer);
		cdo.setObjectValue(fieldId,DataType.TIME_TYPE,null,field,cdo);
    }

    protected void setDateTimeValue(CDO cdo,String strFieldId,ByteBuffer buffer)
    {
    	

    	FieldId fieldId=cdo.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	Field field=new DateTimeField(fieldId.strFieldId,buffer);
		cdo.setObjectValue(fieldId,DataType.DATETIME_TYPE,null,field,cdo);
    }
    
    protected void setFileValue(CDO cdo,String strFieldId,ByteBuffer buffer)
    {
    	

    	FieldId fieldId=cdo.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	Field field=new FileField(fieldId.strFieldId,buffer);
		cdo.setObjectValue(fieldId,DataType.FILE_TYPE,null,field,cdo);
		if(fieldId.nType==FieldId.SIMPLE){
			cdo.setFileCount(cdo.getSerialFileCount()+1);
		}
    }


    protected void setBooleanArrayValue(CDO cdo,String strFieldId,ByteBuffer buffer)
    {
    	

    	FieldId fieldId=cdo.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
    	if(fieldId.nType==FieldId.ARRAY_ELEMENT)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	else
    	{
    		Field field=new BooleanArrayField(fieldId.strFieldId,buffer);
    		cdo.setObjectValue(fieldId,DataType.BOOLEAN_ARRAY_TYPE,null,field,cdo);
    	}
    }

    protected void setByteArrayValue(CDO cdo,String strFieldId,ByteBuffer buffer)
    {
    	

    	FieldId fieldId=cdo.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
    	if(fieldId.nType==FieldId.ARRAY_ELEMENT)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	else
    	{
    		Field field=new ByteArrayField(fieldId.strFieldId,buffer);
    		cdo.setObjectValue(fieldId,DataType.BYTE_ARRAY_TYPE,null,field,cdo);
    	}
    }

    protected void setShortArrayValue(CDO cdo,String strFieldId,ByteBuffer buffer)
    {
    	

    	FieldId fieldId=cdo.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
    	if(fieldId.nType==FieldId.ARRAY_ELEMENT)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	else
    	{
    		Field field=new ShortArrayField(fieldId.strFieldId,buffer);
    		cdo.setObjectValue(fieldId,DataType.SHORT_ARRAY_TYPE,null,field,cdo);
    	}
    }

    protected void setIntegerArrayValue(CDO cdo,String strFieldId,ByteBuffer buffer)
    {
    	

    	FieldId fieldId=cdo.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
    	if(fieldId.nType==FieldId.ARRAY_ELEMENT)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	else
    	{
    		Field field=new IntegerArrayField(fieldId.strFieldId,buffer); 
    		cdo.setObjectValue(fieldId,DataType.INTEGER_ARRAY_TYPE,null,field,cdo);
    	}
    }
    protected void setFloatArrayValue(CDO cdo,String strFieldId,ByteBuffer buffer)
    {
    	

    	FieldId fieldId=cdo.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
    	if(fieldId.nType==FieldId.ARRAY_ELEMENT)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	else
    	{
    		Field field=new FloatArrayField(fieldId.strFieldId,buffer); 
    		cdo.setObjectValue(fieldId,DataType.FLOAT_ARRAY_TYPE,null,field,cdo);
    	}
    }
    protected void setDoubleArrayValue(CDO cdo,String strFieldId,ByteBuffer buffer)
    {
    	
    	FieldId fieldId=cdo.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
    	if(fieldId.nType==FieldId.ARRAY_ELEMENT)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	else
    	{
    		Field field=new DoubleArrayField(fieldId.strFieldId,buffer); 
    		cdo.setObjectValue(fieldId,DataType.DOUBLE_ARRAY_TYPE,null,field,cdo);
    	}
    }
    protected void setLongArrayValue(CDO cdo,String strFieldId,ByteBuffer buffer)
    {
    	

    	FieldId fieldId=cdo.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
    	if(fieldId.nType==FieldId.ARRAY_ELEMENT)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	else
    	{
    		Field field=new LongArrayField(fieldId.strFieldId,buffer); 
    		cdo.setObjectValue(fieldId,DataType.LONG_ARRAY_TYPE,null,field,cdo);
    	}
    }
    protected void setStringArrayValue(CDO cdo,String strFieldId,ByteBuffer buffer)
    {
    	

    	FieldId fieldId=cdo.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
    	if(fieldId.nType==FieldId.ARRAY_ELEMENT)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	else
    	{
    		Field field=new StringArrayField(fieldId.strFieldId,buffer);  
    		cdo.setObjectValue(fieldId,DataType.STRING_ARRAY_TYPE,null,field,cdo);
    	}
    }

    protected void setDateArrayValue(CDO cdo,String strFieldId,ByteBuffer buffer)
    {
    	

    	FieldId fieldId=cdo.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
    	if(fieldId.nType==FieldId.ARRAY_ELEMENT)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	else
    	{
    		Field field=new DateArrayField(fieldId.strFieldId,buffer);  
    		cdo.setObjectValue(fieldId,DataType.DATE_ARRAY_TYPE,null,field,cdo);
    	}
    }

    protected void setTimeArrayValue(CDO cdo,String strFieldId,ByteBuffer buffer)
    {
    	

    	FieldId fieldId=cdo.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
    	if(fieldId.nType==FieldId.ARRAY_ELEMENT)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	else
    	{
    		Field field=new TimeArrayField(fieldId.strFieldId,buffer);  
    		cdo.setObjectValue(fieldId,DataType.TIME_ARRAY_TYPE,null,field,cdo);
    	}
    }

    protected void setDateTimeArrayValue(CDO cdo,String strFieldId,ByteBuffer buffer)
    {
    	

    	FieldId fieldId=cdo.parseFieldId(strFieldId);
    	if(fieldId==null)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	
    	if(fieldId.nType==FieldId.ARRAY_ELEMENT)
    	{
			throw new RuntimeException("Invalid FieldId "+strFieldId);
    	}
    	else
    	{
    		Field field=new DateTimeArrayField(fieldId.strFieldId,buffer);     		
    		cdo.setObjectValue(fieldId,DataType.DATETIME_ARRAY_TYPE,null,field,cdo);
    	}
    }  
}
