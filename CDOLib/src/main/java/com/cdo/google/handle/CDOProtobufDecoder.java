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
		//CDO 协议=魔数(2个字节)+消息类型(1个字节)+对象内容长度(4个字节)	
		int headLen=ProtoProtocol.PROTOCOL_LEN;
		//CDO 协议头header长度
		while(in.readableBytes()>headLen){
			in.markReaderIndex();
			//读取魔数
			short lowMagic=(short)(in.readByte()&0xff); 
			short magic=(short)(((short)((in.readByte()&0xff)<<8))|lowMagic);
			if(magic!=ProtoProtocol.MAGIC_NUMBER){		
				in.resetReaderIndex();
				return; 
			}
			byte type=in.readByte();
			//判断是否是CDO协议
			if(!checkMsgType(type)){
				in.resetReaderIndex();
				return;
			}
			//读取包头中 body的长度
			int len=readBodyLen(in);
			
			CDOMessage message=new CDOMessage();
			Header header=new Header();
			header.setType(type);
			message.setHeader(header);									
			if(type==ProtoProtocol.TYPE_CDO){
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
	           message.setBody(result); 	        
			} 
			out.add(message);
		}
	}
	
	//进行检查消息类型
	private boolean checkMsgType(byte type){
		//读取魔数
		boolean flag=false;
		switch (type) {
			case ProtoProtocol.TYPE_CDO:
			case ProtoProtocol.TYPE_HEARTBEAT_REQ:
			case ProtoProtocol.TYPE_HEARTBEAT_RES:
				flag=true;
				break;
			default:
				flag=false;
				break;
		}
		return flag;
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
