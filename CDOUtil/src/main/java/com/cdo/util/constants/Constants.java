package com.cdo.util.constants;

import com.cdo.util.exception.EncodeException;

/**
 * 
 * @author KenelLiu
 *
 */
public class Constants {

	public static class Netty{
		public static final String THREAD_BOSS="io.netty.eventLoopThreads";//netty主线程，默认 Runtime.getRuntime().availableProcessors()
		public static final String THREAD_WORK="io.netty.thread.channel";//处理netty主线程工作线程,默认Runtime.getRuntime().availableProcessors()*2
		public static final String THREAD_BUSINESS="io.netty.thread.bussiness";//处理业务线程数量  服务端默认30
		public static final String BUSINESS_TIME_OUT="io.netty.bussiness.timeOut";//客户端发送请求，等待服务端返回结果的超时时间,默认30分钟
		
	}
	//分页常量
	public static class Page{		
		public static final String PAGE_INDEX="nPageIndex";
		public static final String PAGE_SIZE="nPageSize";
		
		//-------- 每页显示最小 最大数量-----------------//
		public static final int PAGE_SIZE_MIN=10;
		public static final int PAGE_SIZE_MAX=100;
	}
	
	//--字符编码变量-------//
	public static class Encoding {
		public static final String CHARSET_UTF8="UTF-8";
		public static final String CHARSET_GBK="GBK";
		public static final String CHARSET_GBK2312="GBK2312";
		public static final String CHARSET_ISO8859="ISO-8859-1";
		
		public static final byte[]  encode(String value){
			return encode(value, CHARSET_UTF8);
		}
		
		public static final byte[] encode(String value,String charset){
			if(value==null)
				throw new EncodeException("value is null");
			try{
				return value.getBytes(charset);
			}catch(Exception ex){
				throw new EncodeException(ex);
			}
		}
	}
	//byteBuffer读取字节长度
	public static class Byte{
		public final static int maxSize=2048;
		public final static int defaultSize=1024;
		public final static int minSize=512;
	}
	
	//CDO传输数据的变量
	public static class CDO{
		public final static String HTTP_CDO_REQUEST="$$CDORequest$$";
		public final static String HTTP_CDO_UPLOADFILE_KEY="$CDOSerialFile$";
		
		public final static String HTTP_CDO_RESPONSE="$$CDOResponse$$";
		public final static String HTTP_UPLOAD_FILE_PATH="uploadPath";
		public final static String TEMP_FILE_PATH="tmpPath";
		public final static String UPLOAD_FILE_MAX_SIZE="uploadFileMaxSize";
		
	}
}
