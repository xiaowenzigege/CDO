package com.cdo.google.handle;

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
/**
 * 
 * @author KenelLiu
 * CDO 协议=魔数(2个字节)+消息类型(1个字节)+对象内容长度(3个字节)+文件数量(1个字节)+文件数量*8个字节(每8个字节表示文件长度)
 */
public class CDOProtobufDecoder extends ByteToMessageDecoder {
	 private static Logger log=Logger.getLogger(CDOProtobufEncoder.class);
	 
	 private boolean finFiles=true;//标识，标志文件处理完毕。无文件传输,自动标识文件传输完毕
	 
	 private long[] filesLen; //存储文件内容长度
	 private int fileCount=0;//文件个数
	 private File outFile;//当前输出文件
	 FileOutputStream fileOutputStream;//当前输出文件流 
	 private int fileIndex=0;//文件下标
	 private long total=0;//计算文件长度
	 
	 CDOMessage message;
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {
		
		if(!finFiles){
			//文件还未输出完毕，继续读取socket二进制流  输出文件
			outFile(in, filesLen[fileIndex],fileOutputStream,out);
			return;
		}		
		//读取cdo协议头
		int headLen=ProtoProtocol.PROTOCOL_LEN;		
		while(in.readableBytes()>headLen){
			in.markReaderIndex();
			//读取魔数
			short lowMagic=(short)(in.readByte()&0xff); 
			short magic=(short)(((short)((in.readByte()&0xff)<<8))|lowMagic);
			if(magic!=ProtoProtocol.MAGIC_NUMBER){		
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
			//读取文件个数
			fileCount=in.readByte();
			//读取每个文件内容的长度
			filesLen=new long[fileCount];
			filesLen=readFileLen(filesLen, in);
			//创建对象
			message=new CDOMessage();
			Header header=new Header();
			header.setType(type);
			message.setHeader(header);	
			//CDO 消息类型需要特别处理，其余为心跳消息检测
			if(type==ProtoProtocol.TYPE_CDO){
				if (in.readableBytes() <len) {
			           in.resetReaderIndex();
			           return;
			     }
				try{
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
		           
		           //存在文件传输,则依次临时输出文件到本地
		           if(fileCount>0){
		        	   finFiles=false;//设置标识,表示文件还未处理完毕
		        	   outFile=getOutFile();
		        	   fileOutputStream=new FileOutputStream(outFile);
		        	   List<File> fileList=new ArrayList<File>();
		        	   fileList.add(outFile);
		        	   message.setFiles(fileList);
		        	   //输出文件
		        	   outFile(in, filesLen[fileIndex],fileOutputStream,out);
		           }
				}catch(Throwable ex){
					log.error("decoder error:"+ex.getMessage(), ex);
					CDO cdoRequest=new CDO();
					cdoRequest.setStringValue("decoder  exception", ex.getMessage());
					message.setBody(cdoRequest.toProtoBuilder().build()); 
				}
			} 		   
			//无文件传输,数据解释完毕
		   if(fileCount==0){
			   out.add(message);
			   finFiles=true;			  
		   }
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
			case ProtoProtocol.TYPE_STOPLOCALServer:	
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
  
   private long[] readFileLen(long[] filesLen,ByteBuf in){
	   if(filesLen.length<=0)
		   return filesLen;	   	   
	   for(int k=0;k<filesLen.length;k++){
		   long len=0;
		   for(int i=0;i<8;i++){
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
	private File getOutFile() throws IOException{
		  String tmpDirPath=getTmpPath();			
		  String suffix=".tmp";
		  String date=new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date(System.nanoTime()));
		  String tempPath =tmpDirPath+"/"+date+suffix;				    				 
		  File tmpFile=new File(tempPath);			  
		  if(!tmpFile.exists())
		    	tmpFile.createNewFile();
		  return tmpFile;				 
	}
	
	/**
	 * 依次输出流中的文件
	 * 
	 * @param in  读取socket中的字节流
	 * @param fileLen  文件长度
	 * @param outputStream  输出临时文件
	 * @param out     输出对象
	 * @throws IOException
	 */
	private void outFile(ByteBuf in,long fileLen,OutputStream outputStream,List<Object> out) throws IOException{            
        try{	
        	if(in.isReadable()){
        		int readableLen=in.readableBytes();
        		long remainLen=fileLen-total;
        		int length=remainLen>readableLen?readableLen:(int)remainLen;
	        	byte[] bytes = new byte[length];
	            in.readBytes(bytes);	            
	            outputStream.write(bytes);
	            total=total+length;	
        	}
          if(total>=fileLen)
        		outputStream.flush();		       
        }catch(Exception ex){            	
        	throw new IOException(ex.getMessage(),ex);
        }finally{
        	if(total>=fileLen){
        	   //文件输出完，关闭流	
	    	  try{if(outputStream!=null)outputStream.close();}catch(Exception ex){};	    	  
	    	  //继续输出下一个文件
	    	  if((fileCount-1)>fileIndex){
	    		  fileIndex++;
	    		  total=0;
	    		  outFile=getOutFile();
	        	  fileOutputStream=new FileOutputStream(outFile);
	        	  message.getFiles().add(outFile);	        	  
	        	   //输出文件
	        	  outFile(in, filesLen[fileIndex],fileOutputStream,out);
	    	  }else{
	    		  out.add(message);
	    		  //所有文件已经处理完，标识完成
	    		  finFiles=true;
	    	  }
        	}
		}	
	}
}
