package com.cdo.google.handle;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.cdo.google.protocol.GoogleCDO;
import com.google.protobuf.ByteString;
import com.google.protobuf.BytesValue;
import com.google.protobuf.InvalidProtocolBufferException;

public class ExampleGoogleCDOMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		GoogleMapCDO.CDOProto.Builder b=GoogleMapCDO.CDOProto.newBuilder();
//		b.putAllFields(values)
//		GoogleMapCDO.CDOProto.
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
			buffer=prot.getFieldsList().get(0).getValue().asReadOnlyByteBuffer();
			System.out.println(buffer.get());
//			buffer.asReadOnlyBuffer()
//			buffer.clear();
//			buffer.put((byte)5);
//			buffer.a
//			buffer.pu
			
			Map<String,String> map1=new LinkedHashMap<String,String>();
			map1.put("key21", "value1");
			map1.put("key12", "value2");
			
			Map<String,String> map2=new HashMap<String,String>();
			map2.putAll(map1);
			for(Iterator<String> it=map2.keySet().iterator();it.hasNext();){
				System.out.println(it.next());
			}
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
