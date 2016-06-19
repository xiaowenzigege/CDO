package com.cdo.example;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.cdo.google.handle.CDOProtoParse;
import com.cdo.google.protocol.GoogleCDO;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.google.protobuf.ByteString;
import com.google.protobuf.BytesValue;
import com.google.protobuf.InvalidProtocolBufferException;

public class ExampleGoogleCDOMain {

	public static void main(String[] args) {
		GoogleCDO.CDOProto proto=ExampleCDO.getCDO().toProto();

		CDO cdo=CDOProtoParse.ProtoParse.parse(proto);
		System.out.println("proto xml="+cdo.toXMLWithIndent());

	}

}
