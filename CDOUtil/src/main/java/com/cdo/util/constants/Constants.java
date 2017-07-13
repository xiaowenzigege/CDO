package com.cdo.util.constants;

import com.cdo.util.exception.EncodeException;

/**
 * 
 * @author KenelLiu
 *
 */
public class Constants {

	public static class Netty{
		//服务端 RPCServer
		public static final String THREAD_SERVER_BOSS="io.netty.eventLoopThreads";//netty主线程，默认 Runtime.getRuntime().availableProcessors()*1
		public static final String THREAD_SERVER_WORK="io.netty.thread.channel";//处理netty主线程工作线程,默认Runtime.getRuntime().availableProcessors()*2
		//客户端 RPCClient  工作线程
		public static final String THREAD_CLIENT_WORK="io.netty.client.eventLoopThreads";//默认Runtime.getRuntime().availableProcessors()
		// 重试间隔时间
		public static final String THREAD_CLIENT_RETRY_INTERVAL="io.netty.client.retryInterval";//重试间隔时间,单位:秒
		// 重试总次数
		public static final String THREAD_CLIENT_RETRY_COUNT="io.netty.client.retryCount";
		//在一个jvm里  开启多少个长连接,连接同一服务端,默认为1个
		public static final String THREAD_CLIENT_CONNECT_ConnCount="io.netty.client.connCount";
	}
	
	//业务处理线程
	public static class Business{	
		//客户端  [同步/异步]调用线程
		public static final String ASYNC="business.async";//默认是异步调用	
        //后端是否使用rpc提供服务 		
		public static final String RPC="business.rpc";//是否使用RPC调用
		//RPC同步调用 接口  超时时间  10 分钟
		public static final String TIME_OUT="business.timeOut";			
		//RPC调用服务端  使用io channel进行处理 业务 .是否直接调用nio 处理业务
		public static final String DIRECT_NIO_CHANNEL="business.direct.nioChannel";		
		//线程数量
		public static final String CoreSize="business.coreSize";
		//最大线程数
		public static final String MaxSize="business.maxSize";
		//空闲时间  60 秒
		public static final String IDLE_KeepAliveTime="business.idleKeepAliveTime";
		//队列长度
		public static final String QueueSize="business.queueSize";//队列最最大长度是多少
		//阈值 remain =taskCount-taskComplete=activeCount+queueSize(队列剩余的个数)
		public static final String Threshold="business.threshold";
	}
	
	public static class HTTP{
		public static final String SoTimeout_MS="http.soTimeout";
		public static final String ConnectionTimeout_MS="http.connection.timeOut";		
		public static final String Max_Connection_Lifetime_MS="http.max.connection.lifeTime";
		public static final String IdleConnection_TimeOut_MS="http.IdleConnection.timeOut";				
		
		public static final String DefaultMaxPerRoute="http.default.maxPerRoute";
		public static final String MaxTotal="http.max.total";						
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
