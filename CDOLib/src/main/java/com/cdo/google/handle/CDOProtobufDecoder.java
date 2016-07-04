package com.cdo.google.handle;

import java.util.List;

import org.apache.log4j.Logger;

import com.cdo.google.protocol.GoogleCDO;
import com.google.protobuf.MessageLite;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class CDOProtobufDecoder extends ByteToMessageDecoder {
	 private static Logger log=Logger.getLogger(CDOProtobufEncoder.class);
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {
		//魔数(2个字节)+协议标识+协议类型(1个字节)+对象内容长度(4个字节)
		int headLen=Proto.PROTOCOL_CDO_HEADER_TOTAL_LEN;
		//CDO 协议头header长度
		while(in.readableBytes()>headLen){
			in.markReaderIndex();
			//判断是否是CDO协议
			if(!checkHeader(in)){
				in.resetReaderIndex();
				return;
			}
			//读取包头中 body的长度
			int len=readBodyLen(in);
			if (in.readableBytes() <len) {
	             in.resetReaderIndex();
	            return;
	        }			
			ByteBuf bodyByteBuf = in.readBytes(len);
		    byte[] array;
	        int offset;	        
	        int readableLen= bodyByteBuf.readableBytes();
	        if (bodyByteBuf.hasArray()) {
	                array = bodyByteBuf.array();
	                offset = bodyByteBuf.arrayOffset() + bodyByteBuf.readerIndex();
	        } else {
	                array = new byte[readableLen];
	                bodyByteBuf.getBytes(bodyByteBuf.readerIndex(), array, 0, readableLen);
	                offset = 0;
	       }	        
	        //反序列化
            MessageLite result= GoogleCDO.CDOProto.getDefaultInstance().getParserForType().parseFrom(array, offset, readableLen);
            out.add(result);	        
		}
	}
	//读取协议头信息，进行检查
	private boolean checkHeader(ByteBuf in){
		//读取魔数
		short lowMagic=(short)(in.readByte()&0xff); 
		short magic=(short)(((short)((in.readByte()&0xff)<<8))+lowMagic);
		if(magic!=Proto.Type.CDO.getMagic()){			
			return false;
		}
		//读取协议标识  比对 
		StringBuilder sbHeader=new StringBuilder();
		for(int i=0;i<Proto.PROTOCOL_CDO.length();i++){
			sbHeader.append((char)in.readByte()); 
		}
		if(!sbHeader.toString().equals(Proto.PROTOCOL_CDO)){		
			return false;
		}
		//读取协议类型
		byte protoType=in.readByte();
		if(protoType!=Proto.Type.CDO.getType()){
			return false;
		}
		return true;
	}

	/**读取4个字节，表示body长度
	 * @see {@link com.cdo.google.handle.CDOProtobufEncoder#encodeCDOHeader(MessageLite,int)}
	 * @param in
	 * @return
	 */
   private int readBodyLen(ByteBuf in){
	   int len=0;
	   for(int i=0;i<4;i++){
		   len+=(in.readByte()&0xff)<<(8*i);
	   }
	   return len;	   
   }
   
}
