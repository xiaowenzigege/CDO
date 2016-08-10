package com.cdo.google.handle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
			//读取包头中 cdo body的长度
			int len=readBodyLen(in);
			int fileCount=in.readByte();//文件个数
			int[] filesLen=new int[fileCount];//文件内容长度
			filesLen=readFileLen(filesLen, in);
			
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
	           
	           //读取文件
	           for(int i=0;i<filesLen.length;i++){
	        	   outFile(in, filesLen[i]);
	           }
	           
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

	/**读取3个字节，表示cdo body长度
	 * @see {@link com.cdo.google.handle.CDOProtobufEncoder#encodeCDOHeader(MessageLite,int)}
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
  
   private int[] readFileLen(int[] filesLen,ByteBuf in){
	   if(filesLen.length<=0)
		   return filesLen;	   	   
	   for(int k=0;k<filesLen.length;k++){
		   int len=0;
		   for(int i=0;i<3;i++){
			   len+=(in.readByte()&0xff)<<(8*i);
		   }   
		   filesLen[k]=len;
	   }
	   return filesLen;	   
   }
	private String getTmpPath(){
	     String tmpDirPath=System.getProperty("java.io.tmpdir");
	     File tmpDir= new File(tmpDirPath); 
	     if(!tmpDir.exists() || !tmpDir.isDirectory())  
	           tmpDir.mkdirs();  
	     return tmpDirPath;
	}
   
	private void outFile(ByteBuf in,long fileLen) throws IOException{
		  String tmpDirPath=getTmpPath();			
		  String suffix=".tmp";
		  String date=new SimpleDateFormat("yyyyMMddHHmmsss").format(new Date());
		  String tempPath =tmpDirPath+"/"+date+suffix;				    				 
		  File tmpFile=new File(tempPath);	   				
		  if(!tmpFile.exists())
		    	tmpFile.createNewFile();
		  FileOutputStream fileOutputStream=null;
		  try{
		    	fileOutputStream=new FileOutputStream(tmpFile);
			    outFile(in,fileLen,fileOutputStream);
		      }finally{
		    	  try{if(fileOutputStream!=null)fileOutputStream.close();}catch(Exception ex){};
			}	   				 	
	}
	
	private void outFile(ByteBuf in,long fileLen,OutputStream outputStream) throws IOException{            
        try{		
			int maxSize=2048;		
			int l;
			long total=0;
			byte[] tmp =null;
			while(fileLen>total){	
				long remainLen=fileLen-total;
			    int length=remainLen>maxSize?maxSize:(int)remainLen;
//			    tmp= new byte[length];
//		        if((l = in.readableBytes())) != -1) {			        	
//		        	outputStream.write(tmp,0, l);			            
//		        }
//		        
				ByteBuf bodyByteBuf = in.readBytes(length);
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
		        outputStream.write(array);
		        total=total+length;		
			}  
			outputStream.flush();		       
        }catch(Exception ex){            	
        	throw new IOException(ex.getMessage(),ex);
        }
	}
}
