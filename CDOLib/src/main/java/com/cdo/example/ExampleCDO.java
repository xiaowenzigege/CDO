package com.cdo.example;

import com.cdoframework.cdolib.data.cdo.BooleanArrayField;
import com.cdoframework.cdolib.data.cdo.ByteArrayField;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.data.cdo.DateArrayField;
import com.cdoframework.cdolib.data.cdo.DateTimeArrayField;
import com.cdoframework.cdolib.data.cdo.IntegerArrayField;
import com.cdoframework.cdolib.data.cdo.LongArrayField;
import com.cdoframework.cdolib.data.cdo.ShortArrayField;
import com.cdoframework.cdolib.data.cdo.TimeArrayField;

public class ExampleCDO {

	public static CDO getCDO(){
			CDO cdo = new CDO();		
			cdo.setByteValue("byte", (byte)2);
			cdo.setByteArrayValue("bytes", new byte[]{1,2,3});
			cdo.setBooleanValue("bvalue", true);
			cdo.setBooleanArrayValue("bsValue", new boolean[]{false,true,true,false});
			cdo.setShortValue("short", (short)100);
			cdo.setShortArrayValue("shorts", new short[]{100,200,300});
			cdo.setIntegerValue("int", 300);
			cdo.setIntegerArrayValue("ints", new int[]{400,500,600});
			cdo.setLongValue("long", 7000);
			cdo.setLongArrayValue("longs", new long[]{9000,10000});
			cdo.setFloatValue("float", 3.0f);
			cdo.setFloatArrayValue("floats", new float[]{1.0f,2.0f,3.0f});
			cdo.setDoubleValue("double", 5.0);
			cdo.setDoubleArrayValue("doubles", new double[]{6.0,7.0,8.0});
			cdo.setStringValue("str", "张三");
			cdo.setStringArrayValue("strvalues", new String[]{ "张3", "张4", "张5"});
			cdo.setDateValue("date", "2016-05-01");
			cdo.setDateArrayValue("date1", new String[]{"2012-05-01","2013-05-01","2014-05-01"});
			cdo.setTimeValue("time", "20:00:00");
			cdo.setTimeArrayValue("times", new String[]{"17:00:00","18:00:00","20:00:00"});
			cdo.setDateTimeValue("dateTime", "2012-05-01 20:00:00");
			cdo.setDateTimeArrayValue("dateTimeValues", new String[]{"2012-05-01 20:00:00","2013-05-01 21:00:00","2014-05-01 22:00:00"});
			
			for(int i=0;i<5;i++){
				System.out.println(cdo.getDoubleValue("double"));
				System.out.println(cdo.getDoubleValue("double"));
				System.out.println(((ByteArrayField)cdo.getField("bytes")).getValue());
				System.out.println(((ByteArrayField)cdo.getField("bytes")).getValue());
			    ((ByteArrayField)cdo.getField("bytes")).setValueAt(2, (byte)2);
			    ((ByteArrayField)cdo.getField("bytes")).setValueAt(0, (byte)2);
			    ((ByteArrayField)cdo.getField("bytes")).setValueAt(1, (byte)2);
				System.out.println(((ByteArrayField)cdo.getField("bytes")).getValue());
				System.out.println(((ByteArrayField)cdo.getField("bytes")).getValueAt(0));
				System.out.println(((ByteArrayField)cdo.getField("bytes")).getValueAt(1));
				System.out.println(((ByteArrayField)cdo.getField("bytes")).getValueAt(2));
				
				System.out.println(cdo.getBooleanValue("bvalue"));
				System.out.println(((BooleanArrayField)cdo.getField("bsValue")).getValue());
				System.out.println(((BooleanArrayField)cdo.getField("bsValue")).getValue());
			    ((BooleanArrayField)cdo.getField("bsValue")).setValueAt(3, true);
				System.out.println(((BooleanArrayField)cdo.getField("bsValue")).getValue());
				System.out.println(((BooleanArrayField)cdo.getField("bsValue")).getValueAt(0));
				System.out.println(((BooleanArrayField)cdo.getField("bsValue")).getValueAt(1));
				System.out.println(((BooleanArrayField)cdo.getField("bsValue")).getValueAt(2));
				
				System.out.println(((DateArrayField)cdo.getField("date1")).getValueAt(1));
				System.out.println(((DateArrayField)cdo.getField("date1")).getValueAt(1));
				System.out.println(((DateArrayField)cdo.getField("date1")).getValue()[2]);
				System.out.println(((DateArrayField)cdo.getField("date1")).getValue()[2]);
				System.out.println(((DateArrayField)cdo.getField("date1")).getLength());
				System.out.println(((DateArrayField)cdo.getField("date1")).getLength());	
				
				System.out.println(((TimeArrayField)cdo.getField("times")).getValueAt(1));
				System.out.println(((TimeArrayField)cdo.getField("times")).getValueAt(1));
				System.out.println(((TimeArrayField)cdo.getField("times")).getValue()[0]);
				System.out.println(((TimeArrayField)cdo.getField("times")).getValue()[0]);
				System.out.println(((TimeArrayField)cdo.getField("times")).getLength());
				System.out.println(((TimeArrayField)cdo.getField("times")).getLength());
				
				System.out.println(((DateTimeArrayField)cdo.getField("dateTimeValues")).getValueAt(1));
				System.out.println(((DateTimeArrayField)cdo.getField("dateTimeValues")).getValueAt(0));
				System.out.println(((DateTimeArrayField)cdo.getField("dateTimeValues")).getValue()[0]);
				System.out.println(((DateTimeArrayField)cdo.getField("dateTimeValues")).getValue()[0]);
				System.out.println(((DateTimeArrayField)cdo.getField("dateTimeValues")).getLength());
				System.out.println(((DateTimeArrayField)cdo.getField("dateTimeValues")).getLength());
				
				System.out.println(((ShortArrayField)cdo.getField("shorts")).getValueAt(1));
				System.out.println(((ShortArrayField)cdo.getField("shorts")).getValueAt(1));
				System.out.println(((ShortArrayField)cdo.getField("shorts")).getLength());
				System.out.println(((ShortArrayField)cdo.getField("shorts")).getLength());			
				System.out.println(((ShortArrayField)cdo.getField("shorts")).getValue()[0]);
				System.out.println(((ShortArrayField)cdo.getField("shorts")).getValue()[0]);

				System.out.println(((IntegerArrayField)cdo.getField("ints")).getValueAt(1));
				System.out.println(((IntegerArrayField)cdo.getField("ints")).getValueAt(1));
				System.out.println(((IntegerArrayField)cdo.getField("ints")).getLength());
				System.out.println(((IntegerArrayField)cdo.getField("ints")).getLength());			
				System.out.println(((IntegerArrayField)cdo.getField("ints")).getValue()[0]);
				System.out.println(((IntegerArrayField)cdo.getField("ints")).getValue()[0]);
				
				System.out.println(((LongArrayField)cdo.getField("longs")).getValueAt(1));
				System.out.println(((LongArrayField)cdo.getField("longs")).getValueAt(1));
				System.out.println(((LongArrayField)cdo.getField("longs")).getLength());
				System.out.println(((LongArrayField)cdo.getField("longs")).getLength());			
				System.out.println(((LongArrayField)cdo.getField("longs")).getValue()[0]);
				System.out.println(((LongArrayField)cdo.getField("longs")).getValue()[0]);
				
				
			}
			
			
			CDO cdoOut=new CDO();
			
			CDO cdoReturn=new CDO();
			cdoReturn.setIntegerValue("nCode",0);
			cdoReturn.setStringValue("strText","测试成功");
			cdoReturn.setStringValue("strInfo","测试成功");
			
			CDO cdoResponse=new CDO();			
			cdoResponse.setIntegerValue("nCount", 30);
			cdoResponse.setCDOArrayValue("cdosList", new CDO[]{cdo.clone(),cdo.clone()});			
			cdoResponse.setStringValue("aa", "ab");
			
			cdoOut.setCDOValue("cdoResponse",cdoResponse );
			cdoOut.setCDOValue("cdoReturn", cdoReturn);
			System.out.println("xml="+cdoOut.toXMLWithIndent());			
			return cdoOut;
	}
}
