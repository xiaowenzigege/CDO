package com.cdo.xml.handle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.cdo.google.protocol.GoogleCDO;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.google.protobuf.MessageLite;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.ReferenceCountUtil;
/**
 * 
 * @author KenelLiu
 * XML CDO 协议=魔数(2个字节)+消息类型(1个字节)+对象内容长度(3个字节)
 */
public class CDOXmlDecoder extends ByteToMessageDecoder {
	 private static Logger log=Logger.getLogger(CDOXmlDecoder.class);
	 
	 XMLMessage message;
	 
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {		
	
			//读取cdo协议头
			int headLen=XMLProtocol.PROTOCOL_LEN;		
			while(in.readableBytes()>=headLen){
				in.markReaderIndex();
				//读取魔数
				short lowMagic=(short)(in.readByte()&0xff); 
				short magic=(short)(((short)((in.readByte()&0xff)<<8))|lowMagic);
				if(magic!=XMLProtocol.MAGIC_NUMBER){		
					in.resetReaderIndex();				
					return; 
				}
				//读取支持的消息类型
				byte type=in.readByte();			
				if(!checkMsgType(type)){
					in.resetReaderIndex();
					return;
				}			
				//读取cdo内容长度
				int len=readBodyLen(in);
				//读取callId
				int callId=readCallId(in);
				//创建对象
				message=new XMLMessage();
				XMLHeader header=new XMLHeader();
				header.setType(type);
				message.setHeader(header);	
				message.setCallId(callId);
				//CDO 消息类型需要特别处理，其余为心跳消息检测
				if(type==XMLProtocol.TYPE_CDO){
					if (in.readableBytes() <len) {
				           in.resetReaderIndex();
				           return;
				     }										
					try{		
						ByteBuf bodyByteBuf= in.readBytes(len);
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
				        
				       String body=new String(array, offset, readableLen);
				       message.setBody(body);

					}catch(Throwable ex){
						log.error("decoder error:"+ex.getMessage(), ex);
						CDO cdoRequest=new CDO();
						cdoRequest.setStringValue("decoder  exception", ex.getMessage());
						message.setBody(cdoRequest.toXML()); 
					}
				 out.add(message);
				} 		   
			}
	}
	
	//进行检查消息类型
	private boolean checkMsgType(byte type){
		//读取魔数
		boolean flag=false;
		switch (type) {
			case XMLProtocol.TYPE_CDO:
			case XMLProtocol.TYPE_HEARTBEAT_REQ:
			case XMLProtocol.TYPE_HEARTBEAT_RES:
			case XMLProtocol.TYPE_STOPLOCALServer:	
				flag=true;
				break;
			default:
				flag=false;
				break;
		}
		return flag;
	}

	/**读取3个字节，表示cdo body长度
	 * @see {@link com.cdo.google.handle.CDOXmlEncoder#encodeCDOHeader(MessageLite,int)}
	 * @param in
	 * @return
	 */
   private int readBodyLen(ByteBuf in){
	   int len=0;
	   for(int i=0;i<3;i++){
		   len+=(in.readByte()&0xff)<<(8*i);
	   }
	   return len;	   
   }

   private int readCallId(ByteBuf in){
	   int len=0;
	   for(int i=0;i<4;i++){
		   len+=(in.readByte()&0xff)<<(8*i);
	   }
	   return len;	   
   }
}
