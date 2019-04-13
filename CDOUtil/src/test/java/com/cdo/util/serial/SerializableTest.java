package com.cdo.util.serial;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONException;
import org.junit.Test;

import com.cdo.example.ExampleCDO;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.util.JsonUtil;
import com.google.protobuf.InvalidProtocolBufferException;

public class SerializableTest {
//2503677911  proto 10000	
//4594253151	
//9681573489  proto 100000
//94178946699 proto 1000000	
	
//7394665215    avro 10000		
//31593708833   avro 100000
//273424576786  avro 1000000	
//	
//4353563702    xml 10000	
//23358583880	xml 100000	
//183192556571  xml 1000000		
	@Test
	public void testProtoCDO2Byte() throws IOException {
	  List<CDO> list=new ArrayList<CDO>(); 
	  CDO cdo=new CDO();
	  cdo.setStringValue("strKey", "key1");
	  cdo.setStringValue("strValue", "val1");
	  list.add(cdo);
	  CDO cdo1=cdo.clone();
	  cdo1.setStringValue("strKey", "key2");
	  cdo1.setStringValue("strValue", "val2");
	  list.add(cdo1);

	  List<String> t=list.stream().map((x)->{ return x.getStringValue("strKey");})
			  .collect(Collectors.toList());
//	  List t=list.stream().map((x)->{String b=x+"sss";return Integer.valueOf(b);})
//			  .filter((k)->{return k>0;}).collect(Collectors.toList());
//	  System.out.println(t);
//	  
//	  List<String> list1=new ArrayList<>(); 
//	  list1.add("abc");
//	  list1.add("sgsg");
//	  List k=Stream.of(list,list1).flatMap(x->{x.remove(0);return x.stream();}).collect(Collectors.toList());
	  System.out.println(t);

	} 	 

}
