package com.cdo.google.handle;

import java.util.List;

import com.cdo.google.protocol.GoogleCDO;
import com.google.protobuf.MessageLite;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class CDOProtobufDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {
		//包头长度    协议头类型(1个字节表示)+协议头标识+4个字节表示对象内容长度
		int headLen=Proto.PROTOCOL_CDO_HEADER.length()+5;
		//CDO 协议头header长度
		while(in.readableBytes()>headLen){
			in.markReaderIndex();
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
	//读取协议类型和协议头标识 ,并进行检查
	private boolean checkHeader(ByteBuf in){
		byte protoType=in.readByte(); //读取协议类型
		if(protoType!=Proto.Type.CDO.getType()){			
			return false;
		}
		StringBuilder sbHeader=new StringBuilder();
		for(int i=0;i<Proto.PROTOCOL_CDO_HEADER.length();i++){
			sbHeader.append((char)in.readByte());//读取协议头标识  进行比对
		}
		if(!sbHeader.toString().equals(Proto.PROTOCOL_CDO_HEADER)){		
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
