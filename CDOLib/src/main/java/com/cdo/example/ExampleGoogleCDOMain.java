package com.cdo.example;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.cdo.google.handle.ParseProtoCDO;
import com.cdo.google.protocol.GoogleCDO;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.google.protobuf.ByteString;
import com.google.protobuf.BytesValue;
import com.google.protobuf.InvalidProtocolBufferException;

public class ExampleGoogleCDOMain {

	public static void main(String[] args) {
		GoogleCDO.CDOProto.Builder proto=ExampleCDO.getCDO().toProtoBuilder();

		CDO cdo=ParseProtoCDO.ProtoParse.parse(proto.build());
		System.out.println("proto xml="+cdo.toXMLWithIndent());
//		GoogleCDO.CDOProto.getDefaultInstance().getParserForType().p;
	}

}
