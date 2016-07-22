package com.cdo.example;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.cdo.avro.handle.ParseAvroCDO;
import com.cdo.avro.protocol.AvroCDO;
import com.cdo.google.handle.ParseProtoCDO;
import com.cdo.google.protocol.GoogleCDO;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.google.protobuf.ByteString;
import com.google.protobuf.BytesValue;
import com.google.protobuf.InvalidProtocolBufferException;

public class ExampleGoogleCDOMain {

	public static void main(String[] args) {
		CDO cdo=ExampleCDO.getCDO();
		long startTime=System.currentTimeMillis();
		for(int i=0;i<1000000; i++){
			
			cdo.setIntegerValue("i", i);
			
//			GoogleCDO.CDOProto.Builder proto=cdo.toProtoBuilder();
//			ParseProtoCDO.ProtoParse.parse(proto.build());
//			long protoMis=System.currentTimeMillis()-startTime;
//			startTime=System.currentTimeMillis();
			AvroCDO avro=cdo.toAvro();			
//			ParseAvroCDO.AvroParse.parse(avro);
//			long avroMis=System.currentTimeMillis()-startTime;
//			startTime=System.currentTimeMillis();
			String xmlString=cdo.toXML();
			CDO.fromXML(xmlString);
//			long xmlMis=System.currentTimeMillis()-startTime;
//			System.out.println("time="+protoMis+","+avroMis+","+xmlMis);
		}
		long protoMis=System.currentTimeMillis()-startTime;
		System.out.println("time="+protoMis);
	}
//5681  //5521  //18629
}
