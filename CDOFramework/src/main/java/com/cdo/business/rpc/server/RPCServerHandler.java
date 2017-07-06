package com.cdo.business.rpc.server;

import java.io.File;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.cdo.business.BusinessService;
import com.cdo.business.rpc.RPCFile;
import com.cdo.google.handle.CDOMessage;
import com.cdo.google.handle.Header;
import com.cdo.google.handle.ParseProtoCDO;
import com.cdo.google.handle.ProtoProtocol;
import com.cdo.google.protocol.GoogleCDO;
import com.cdo.util.constants.Constants;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.servicebus.ITransService;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.internal.SystemPropertyUtil;

public class RPCServerHandler extends SimpleChannelInboundHandler<CDOMessage> {

	private static Logger logger=Logger.getLogger(RPCServerHandler.class);
	private final  BusinessService serviceBus=BusinessService.getInstance();	
	
	private ThreadPoolExecutor executor;
	private int corePoolSize=Math.max(4,SystemPropertyUtil.getInt(Constants.Netty.THREAD_BUSINESS_CoreSize,Runtime.getRuntime().availableProcessors()));
	private int maxPoolSize=Math.max(4,SystemPropertyUtil.getInt(Constants.Netty.THREAD_BUSINESS_MaxSize,(int)(Runtime.getRuntime().availableProcessors())));
	private int queueSize=Math.max(10,SystemPropertyUtil.getInt(Constants.Netty.THREAD_BUSINESS_TASK_QueueSize,10));
	private int threshold=(int)(queueSize*0.8);
	//空闲线程存活的时间
	private int keepAliveTime=Math.max(60,SystemPropertyUtil.getInt(Constants.Netty.THREAD_BUSINESS_IDLE_KeepAliveTime,60));
	//使用 io channel处理业务，则是   一个服务器对应了大量的客户端
	private boolean useChannel=SystemPropertyUtil.getBoolean(Constants.Netty.THREAD_BUSINESS_USE_CHANNEL,false);
	
	private Channel channel;
	
	public RPCServerHandler(){
	
	}
    /**
     * 防止   客户机与服务器之间的长连接   发生阻塞,业务数据采用线程池处理,长连接channel仅用于数据读取io数据
     * 多个action通过长连接 发送请求到service端 
     */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, CDOMessage msg)
			throws Exception {	
		  Header header=msg.getHeader();
		  
		if(header.getType()==ProtoProtocol.TYPE_CDO){			
			try{
				GoogleCDO.CDOProto proto=(GoogleCDO.CDOProto)(msg.getBody());
				List<File> listFile=msg.getFiles();				
				if(useChannel){
					process(proto, listFile);
				}else{
					//几个客户端通过长连接,发送了大量的数据
					long  taskCount=executor.getTaskCount();
					long  taskComplete=executor.getCompletedTaskCount();
					long  remainCount=taskCount-taskComplete;
					if(remainCount>threshold){
						process(proto, listFile);
					}else{
						Task task=new Task(proto,listFile);
						executor.submit(task);					
						if(logger.isInfoEnabled()){
							logger.info("submit task ["+channel+"]"+ " taskCount="+executor.getTaskCount()+
									" CompletedTaskCount="+executor.getCompletedTaskCount()+",remainCount="+
									remainCount+",activeCount="+executor.getActiveCount()+","+" taskQueueSize="+executor.getQueue().size());						
						}
					}					
					
				}

			}catch(Throwable ex){
				logger.fatal("parse header type CDO "+ex.getMessage(),ex);
			}finally{
				ReferenceCountUtil.release(msg);
			}
		}else if(header.getType()==ProtoProtocol.TYPE_HEARTBEAT_REQ){
			//客户端主动发起 心跳检查，服务端回复心跳
		 try{	
				Header resHeader=new Header();
				resHeader.setType(ProtoProtocol.TYPE_HEARTBEAT_RES);
				CDOMessage resMessage=new CDOMessage();
				resMessage.setHeader(resHeader);
				ctx.writeAndFlush(resMessage);
				
			    if(logger.isDebugEnabled())
	    			logger.debug("server receive client address["+(InetSocketAddress)ctx.channel().remoteAddress()+"] heart msg:"+msg);
			}finally{
				ReferenceCountUtil.release(msg);
			}
		}else if(header.getType()==ProtoProtocol.TYPE_HEARTBEAT_RES){
			try{
				//服务端主动发起心跳检查,客户端回复心跳
			    if(logger.isDebugEnabled())
			    			logger.debug("server receive client address["+(InetSocketAddress)ctx.channel().remoteAddress()+"] heart msg:"+msg);
			}finally{
				ReferenceCountUtil.release(msg);
			}
		}else if(header.getType()==ProtoProtocol.TYPE_STOPLOCALServer){
			RPCServer.stop();
		}else{
			ctx.fireChannelRead(msg);
		}
	}

	private class Task implements Runnable{
		private GoogleCDO.CDOProto proto;
		private List<File> listFile;
		public Task(GoogleCDO.CDOProto proto,List<File> listFile){
			this.proto=proto;
			this.listFile=listFile;
		}
		@Override
		public void run() {	
			process(proto, listFile);	
			if(logger.isInfoEnabled()){
				long  taskCount=executor.getTaskCount();
				long  taskComplete=executor.getCompletedTaskCount();
				long  remainCount=taskCount-taskComplete;				
				logger.info("do task ["+channel+"]"+ " taskCount="+taskCount+
						" CompletedTaskCount="+taskComplete+",remainCount="+
						remainCount+",activeCount="+executor.getActiveCount()+","+" taskQueueSize="+executor.getQueue().size());						
			}

		}	
		
	}
	
	private void process(GoogleCDO.CDOProto proto,List<File> listFile){
		CDO cdoRequest=null;				
		CDO cdoOutput=null;
		try{		
	    	//响应里存在文件,即下载到客服端.是否有文件传输到客户端
			List<File> files=null;
			GoogleCDO.CDOProto.Builder resProtoBuiler=null;
			String strServiceName=null;
			String strTransName=null;
			boolean isAync=false; //是否是异步
			//解释cdo
	    	try{
				cdoRequest=ParseProtoCDO.ProtoParse.parse(proto);
				strServiceName=cdoRequest.exists(ITransService.SERVICENAME_KEY)?cdoRequest.getStringValue(ITransService.SERVICENAME_KEY):"null";
				strTransName=cdoRequest.exists(ITransService.TRANSNAME_KEY)?cdoRequest.getStringValue(ITransService.TRANSNAME_KEY):"null";
				isAync=cdoRequest.exists(ITransService.ASYNCH_KEY)?cdoRequest.getBooleanValue(ITransService.ASYNCH_KEY):false;
				//执行业务逻辑后  输出......
				cdoOutput=handleTrans(cdoRequest,listFile,strServiceName,strTransName);	   
	    		files=RPCFile.readFileFromCDO(cdoOutput.getCDOValue("cdoResponse"));		    		
	    		resProtoBuiler=cdoOutput.toProtoBuilder();		    		
	    	}catch(Throwable ex){
			    //解释异常 ,..文件不存在,返回给错误给客户端
	    		logger.error(ex.getMessage(), ex);
	    		if(cdoOutput==null)
	    			cdoOutput=new CDO();
	    		setFailOutCDO(cdoOutput, "strServiceName="+strServiceName+",strTransName="+strTransName+" RPCServer 发生异常:"+ex.getMessage());
	    		resProtoBuiler=cdoOutput.toProtoBuilder();
	    	}
	    	if(isAync){
	    		//异步调用,不需要回写数据
	    		return;
	    	}	    	
	    	//同步调用,回写结果
			resProtoBuiler.setCallId(proto.getCallId());				
			Header resHeader=new Header();
			resHeader.setType(ProtoProtocol.TYPE_CDO);
			CDOMessage resMessage=new CDOMessage();
			resMessage.setHeader(resHeader);
			resMessage.setBody(resProtoBuiler.build());
			resMessage.setFiles(files);
			if(channel!=null)
				channel.writeAndFlush(resMessage);	
			else
			   logger.warn("channel is null,can't send response data back.cdoRequest="+cdoRequest.toXML()+","+cdoOutput);
		}finally{
			cdoRequest.deepRelease();
			cdoOutput.deepRelease();		
		}		
	}	
	
	private CDO handleTrans(CDO cdoRequest,List<File> listFile,String strServiceName,String strTransName){		
		CDO cdoOutput=new CDO();
		CDO cdoResponse=new CDO();;		
		try{							
			//将client传过来的文件 设置到cdoRequest里
			RPCFile.setFile2CDO(cdoRequest,listFile);
			//处理业务
			Return ret=serviceBus.handleTrans(cdoRequest, cdoResponse);
			if(ret==null){			
				setFailOutCDO(cdoOutput," ret is null,Request method :strServiceName="+strServiceName+",strTransName="+strTransName);	
				logger.error("ret is null,Request method:strServiceName="+strServiceName+",strTransName="+strTransName);
			}else{
				CDO cdoReturn=new CDO();
				cdoReturn.setIntegerValue("nCode",ret.getCode());
				cdoReturn.setStringValue("strText",ret.getText());
				cdoReturn.setStringValue("strInfo",ret.getInfo());

				cdoOutput.setCDOValue("cdoReturn",cdoReturn);
				cdoOutput.setCDOValue("cdoResponse", cdoResponse);					
			}
		}catch(Throwable ex){
			logger.error(ex.getMessage(), ex);	
			setFailOutCDO(cdoOutput,"strServiceName="+strServiceName+",strTransName="+strTransName+"服务端业务处理异常:"+ex.getMessage());
		}	
		return cdoOutput;
	}

	private void setFailOutCDO(CDO cdoOutput,String message){		
		CDO cdoReturn=new CDO();
		cdoReturn.setIntegerValue("nCode",-1);
		cdoReturn.setStringValue("strText",message);
		cdoReturn.setStringValue("strInfo",message);
		
		cdoOutput.setCDOValue("cdoReturn",cdoReturn);
		cdoOutput.setCDOValue("cdoResponse",new CDO());		
	}
	
	
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        channel = ctx.channel();
    }
    
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {        
        super.channelInactive(ctx);      	
        logger.warn("["+ctx.channel()+"]ctx channelInactive,business ThreadPoolExecutor will shutdown");
        executor.shutdown();
    }
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	super.channelActive(ctx);
		if(maxPoolSize<corePoolSize){
			logger.warn("maxPoolSize<corePoolSize,maxPoolSize["+maxPoolSize+"],corePoolSize["+corePoolSize+"],reset maxPoolSize=coreSize="+corePoolSize);
			maxPoolSize=corePoolSize;
		}		
		if(maxPoolSize==corePoolSize){
			keepAliveTime=0;
			logger.warn("maxPoolSize=corePoolSize,maxPoolSize["+maxPoolSize+"],corePoolSize["+corePoolSize+"],reset keepAliveTimee="+keepAliveTime);
		}
		
		BlockingQueue<Runnable> workQueue=new ArrayBlockingQueue<Runnable>(queueSize);
		this.executor=new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, 
				TimeUnit.SECONDS, workQueue, new ThreadPoolExecutor.CallerRunsPolicy());
		if(logger.isInfoEnabled())
			logger.info("["+ctx.channel()+"]ctx  channelActive Initialize thread pool succeed. ThreadPool: corePoolSize = " 
					+ executor.getCorePoolSize()+ ",maxPoolSize = " + executor.getMaximumPoolSize()
					+" idle keepAliveTime="+executor.getKeepAliveTime(TimeUnit.SECONDS)+"s,chnannel max queue size="+queueSize);	
    }
    
    /**
     * 服务端设定  在60秒内未接受到客户端的请求数据,则关闭连接
     * 空闲时 使用客户端来检查,大约每5秒发起心跳检查   @see RPCClient#IdleStateHandler
     * 
     */
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            switch (e.state()) {//The connection is closed when there is no inbound traffic  for 30 seconds.see RPCServerInitializer,RPCClient
                case READER_IDLE:
                	ctx.close();
                    break;
                case WRITER_IDLE:                	
        			break;
                default:
                    break;
            }
        }
    }
}
