package com.cdo.google.handle;

import java.nio.ByteBuffer;

import com.cdo.google.protocol.GoogleCDO;
import com.google.protobuf.ByteString;
import com.google.protobuf.BytesValue;
import com.google.protobuf.InvalidProtocolBufferException;

public class ExampleGoogleCDOMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GoogleCDO.CDOProto.Entry.Builder entry=GoogleCDO.CDOProto.Entry.newBuilder();
		ByteBuffer buffer=ByteBuffer.allocate(1);
		buffer.put((byte)4);
		buffer.flip();
		ByteString byteStr=ByteString.copyFrom(buffer);
		entry.setName("key");
		entry.setValue(byteStr);
	
		GoogleCDO.CDOProto.Builder builder=GoogleCDO.CDOProto.newBuilder();
		builder.addFields(entry);
		builder.setLevel(3);

		
		byte[] bytes=builder.build().toByteArray(); 
		
		try {
			GoogleCDO.CDOProto prot=GoogleCDO.CDOProto.parseFrom(bytes);
			System.out.println(prot.getLevel());
			prot.getFieldsList();
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
