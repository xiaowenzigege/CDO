package com.cdo.util.serial;

import java.io.File;

import com.cdo.google.handle.ParseProtoCDO;
import com.cdo.google.protocol.GoogleCDO;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;

public class Serializable {

	
	
	public static byte[] toByteArray(CDO cdo){
		return cdo.toProtoBuilder().build().toByteArray();
	}
	
	public static CDO fromByteArray(byte[] array) throws InvalidProtocolBufferException{		
		MessageLite result= GoogleCDO.CDOProto.getDefaultInstance().getParserForType().parseFrom(array, 0, array.length);
		GoogleCDO.CDOProto proto=(GoogleCDO.CDOProto)result;
		return ParseProtoCDO.ProtoParse.parse(proto);
	}	
}
