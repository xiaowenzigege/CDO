package com.cdo.example;

import java.io.File;
import java.net.InetAddress;

import com.cdoframework.cdolib.base.Date;
import com.cdoframework.cdolib.base.DateTime;
import com.cdoframework.cdolib.base.Time;
import com.cdoframework.cdolib.data.cdo.BooleanArrayField;
import com.cdoframework.cdolib.data.cdo.ByteArrayField;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.data.cdo.DateArrayField;
import com.cdoframework.cdolib.data.cdo.DateTimeArrayField;
import com.cdoframework.cdolib.data.cdo.DoubleArrayField;
import com.cdoframework.cdolib.data.cdo.FloatArrayField;
import com.cdoframework.cdolib.data.cdo.IntegerArrayField;
import com.cdoframework.cdolib.data.cdo.LongArrayField;
import com.cdoframework.cdolib.data.cdo.ShortArrayField;
import com.cdoframework.cdolib.data.cdo.TimeArrayField;

public class ExampleCDO {

	
	public static class  ExampleCDOType{
		byte byte1,bytes;
		boolean boolean1,booleanArr;
		short short1,shortArr;
		int int1,intArr;
		long long1,longArr;
		float float1,floatArr;
		double double1,doubleArr;
		String str1,strArr;
		Date date1,dateArr;
		Time time1,timeArr;
		DateTime dateTime1,dateTimeArr;
		File file;
	}
	
	public static ExampleCDOType getCDOClassType(){
		return new ExampleCDOType();
	}
	
	public static CDO getCDO(){
			CDO cdo = new CDO();		
			cdo.setByteValue("byte1", (byte)2);
			cdo.setByteArrayValue("bytes", new byte[]{1,2,3});
			cdo.setBooleanValue("boolean1", true);
			cdo.setBooleanArrayValue("booleanArr", new boolean[]{false,true,true,false});
			cdo.setShortValue("short1", (short)100);
			cdo.setShortArrayValue("shortArr", new short[]{100,200,300});
			cdo.setIntegerValue("int1", 300);
			cdo.setIntegerArrayValue("intArr", new int[]{400,500,600});
			cdo.setLongValue("long1", 7000);
			cdo.setLongArrayValue("longArr", new long[]{9000,10000});
			cdo.setFloatValue("float1", 3.0f);
			cdo.setFloatArrayValue("floatArr", new float[]{1.0f,2.0f,3.0f});
			cdo.setDoubleValue("double1", 5.0);
			cdo.setDoubleArrayValue("doubleArr", new double[]{6.0,7.0,8.0});
			cdo.setStringValue("str1", "张三");
			cdo.setStringArrayValue("strArr", new String[]{ "张3", "张4", "张5"});
			cdo.setDateValue("date1", "2016-05-01");
			cdo.setDateArrayValue("dateArr", new String[]{"2012-05-01","2013-05-01","2014-05-01"});
			cdo.setTimeValue("time1", "20:00:00");
			cdo.setTimeArrayValue("timeArr", new String[]{"17:00:00","18:00:00","20:00:00"});
			cdo.setDateTimeValue("dateTime1", "2012-05-01 20:00:00");
			cdo.setDateTimeArrayValue("dateTimeArr", new String[]{"2012-05-01 20:00:00","2013-05-01 21:00:00","2014-05-01 22:00:00"});
			cdo.setFileValue("file", new File("D:/test/excel.xlsx"));
			
//			for(int i=0;i<5;i++){
//
//				System.out.println(((ByteArrayField)cdo.getField("bytes")).getValue());
//				System.out.println(((ByteArrayField)cdo.getField("bytes")).getValue());
//			    ((ByteArrayField)cdo.getField("bytes")).setValueAt(0, (byte)2);			   
//				System.out.println(((ByteArrayField)cdo.getField("bytes")).getValue());
//				System.out.println(((ByteArrayField)cdo.getField("bytes")).getValueAt(0));
//				System.out.println(((ByteArrayField)cdo.getField("bytes")).getValueAt(1));
//				System.out.println(((ByteArrayField)cdo.getField("bytes")).getValueAt(2));
//				
//				System.out.println(cdo.getBooleanValue("boolean1"));
//				System.out.println(((BooleanArrayField)cdo.getField("booleanArr")).getValue());
//				System.out.println(((BooleanArrayField)cdo.getField("booleanArr")).getValue());
//			    ((BooleanArrayField)cdo.getField("booleanArr")).setValueAt(3, true);
//				System.out.println(((BooleanArrayField)cdo.getField("booleanArr")).getValue());
//				System.out.println(((BooleanArrayField)cdo.getField("booleanArr")).getValueAt(0));				
//				System.out.println(((BooleanArrayField)cdo.getField("booleanArr")).getValueAt(3));
//				
//				System.out.println(((DateArrayField)cdo.getField("dateArr")).getValueAt(1));
//				System.out.println(((DateArrayField)cdo.getField("dateArr")).getValueAt(1));
//				System.out.println(((DateArrayField)cdo.getField("dateArr")).getValue()[2]);
//				System.out.println(((DateArrayField)cdo.getField("dateArr")).getValue()[2]);
//				System.out.println(((DateArrayField)cdo.getField("dateArr")).getLength());
//				System.out.println(((DateArrayField)cdo.getField("dateArr")).getLength());	
//				
//				System.out.println(((TimeArrayField)cdo.getField("timeArr")).getValueAt(1));
//				System.out.println(((TimeArrayField)cdo.getField("timeArr")).getValueAt(1));
//				System.out.println(((TimeArrayField)cdo.getField("timeArr")).getValue()[0]);
//				System.out.println(((TimeArrayField)cdo.getField("timeArr")).getValue()[0]);
//				System.out.println(((TimeArrayField)cdo.getField("timeArr")).getLength());
//				System.out.println(((TimeArrayField)cdo.getField("timeArr")).getLength());
//				
//				System.out.println(((DateTimeArrayField)cdo.getField("dateTimeArr")).getValueAt(1));
//				System.out.println(((DateTimeArrayField)cdo.getField("dateTimeArr")).getValueAt(0));
//				System.out.println(((DateTimeArrayField)cdo.getField("dateTimeArr")).getValue()[0]);
//				System.out.println(((DateTimeArrayField)cdo.getField("dateTimeArr")).getValue()[0]);
//				System.out.println(((DateTimeArrayField)cdo.getField("dateTimeArr")).getLength());
//				System.out.println(((DateTimeArrayField)cdo.getField("dateTimeArr")).getLength());
//				
//				System.out.println(((ShortArrayField)cdo.getField("shortArr")).getValueAt(1));
//				System.out.println(((ShortArrayField)cdo.getField("shortArr")).getValueAt(1));
//				System.out.println(((ShortArrayField)cdo.getField("shortArr")).getLength());
//				System.out.println(((ShortArrayField)cdo.getField("shortArr")).getLength());			
//				System.out.println(((ShortArrayField)cdo.getField("shortArr")).getValue()[0]);
//				System.out.println(((ShortArrayField)cdo.getField("shortArr")).getValue()[0]);
//
//				System.out.println(((IntegerArrayField)cdo.getField("intArr")).getValueAt(1));
//				System.out.println(((IntegerArrayField)cdo.getField("intArr")).getValueAt(1));
//				System.out.println(((IntegerArrayField)cdo.getField("intArr")).getLength());
//				System.out.println(((IntegerArrayField)cdo.getField("intArr")).getLength());			
//				System.out.println(((IntegerArrayField)cdo.getField("intArr")).getValue()[0]);
//				System.out.println(((IntegerArrayField)cdo.getField("intArr")).getValue()[0]);
//				
//				System.out.println(((LongArrayField)cdo.getField("longArr")).getValueAt(1));
//				System.out.println(((LongArrayField)cdo.getField("longArr")).getValueAt(1));
//				System.out.println(((LongArrayField)cdo.getField("longArr")).getLength());
//				System.out.println(((LongArrayField)cdo.getField("longArr")).getLength());			
//				System.out.println(((LongArrayField)cdo.getField("longArr")).getValue()[0]);
//				System.out.println(((LongArrayField)cdo.getField("longArr")).getValue()[0]);
//				
//				System.out.println(((FloatArrayField)cdo.getField("floatArr")).getValueAt(1));
//				System.out.println(((FloatArrayField)cdo.getField("floatArr")).getValueAt(1));
//				System.out.println(((FloatArrayField)cdo.getField("floatArr")).getLength());
//				System.out.println(((FloatArrayField)cdo.getField("floatArr")).getLength());			
//				System.out.println(((FloatArrayField)cdo.getField("floatArr")).getValue()[0]);
//				System.out.println(((FloatArrayField)cdo.getField("floatArr")).getValue()[0]);
//				
//				System.out.println(((DoubleArrayField)cdo.getField("doubleArr")).getValueAt(1));
//				System.out.println(((DoubleArrayField)cdo.getField("doubleArr")).getValueAt(1));
//				System.out.println(((DoubleArrayField)cdo.getField("doubleArr")).getLength());
//				System.out.println(((DoubleArrayField)cdo.getField("doubleArr")).getLength());			
//				System.out.println(((DoubleArrayField)cdo.getField("doubleArr")).getValue()[0]);
//				System.out.println(((DoubleArrayField)cdo.getField("doubleArr")).getValue()[0]);				
//			}	
			
			CDO cdoOut=new CDO();
			cdoOut.copyFrom(cdo);
			cdoOut.setCDOValue("cdo", cdo.clone());
			cdoOut.setCDOArrayValue("cdoArr", new CDO[]{cdo.clone(),cdo.clone()});
			return cdoOut;
	 }
	
}
