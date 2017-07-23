package com.cdo.business.rpc.server.proto;

import java.io.File;
//import java.net.InetSocketAddress;
import java.util.List;
//import java.util.concurrent.ArrayBlockingQueue;
//import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
//import java.util.concurrent.SynchronousQueue;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;

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
//import io.netty.handler.timeout.IdleStateEvent;
//import io.netty.util.ReferenceCountUtil;
import io.netty.util.internal.SystemPropertyUtil;
/**
 * 不考虑  处理CDO 文件流
 * @author KenelLiu
 *
 */
public class RPCServerHandler extends SimpleChannelInboundHandler<CDOMessage> {

	private static Logger logger=Logger.getLogger(RPCServerHandler.class);
	private final  BusinessService serviceBus=BusinessService.getInstance();	
	
//	private ThreadPoolExecutor executor;
	private int corePoolSize=Math.max(1,SystemPropertyUtil.getInt(Constants.Business.CoreSize,Runtime.getRuntime().availableProcessors()));
	private ExecutorService exService=null;
	//空闲线程存活的时间
//	private int keepAliveTime=Math.max(60,SystemPropertyUtil.getInt(Constants.Business.IDLE_KeepAliveTime,60));
	//使用 io channel处理业务，则是   一个服务器对应了大量的客户端
	private boolean directNioChannel=SystemPropertyUtil.getBoolean(Constants.Business.DIRECT_NIO_CHANNEL,false);
	
	private Channel channel;
	private LinkedTransferQueue<GoogleCDO.CDOProto> lnkTransQueue;
	private  Consumer[] consumer;
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
		  switch(header.getType()){
		  	  case ProtoProtocol.TYPE_CDO:
					GoogleCDO.CDOProto proto=(GoogleCDO.CDOProto)(msg.getBody());
//					List<File> listFile=msg.getFiles();//优化速度,不考虑处理文件流	
					if(directNioChannel)
						process(proto, null);
					else{
//						HandleData handleData=new HandleData();
//						handleData.setProto(proto);
//						handleData.setListFile(listFile);
						try{
							lnkTransQueue.transfer(proto);
						}catch(InterruptedException ex){
							logger.warn("add element to TransferQueue occured InterruptedException,retry add... ...");
							tryAddElement(proto);
						}
					}
		  		 
		  	 default:
		  		ctx.fireChannelRead(msg); 
		  }		  	
	}
	
	private void tryAddElement(GoogleCDO.CDOProto proto){
		int retry=1;
		while(retry<=3){
			try{
				lnkTransQueue.transfer(proto);
				break;
			}catch(InterruptedException ex){
				retry++;
				try{Thread.sleep(500);}catch(Exception e){};
			}
		}
	}
	
	
	 void process(GoogleCDO.CDOProto proto,List<File> listFile){
		CDO cdoRequest=null;				
		CDO cdoOutput=null;
		GoogleCDO.CDOProto.Builder resProtoBuiler=null;
		try{		
	    	//响应里存在文件,即下载到客服端.是否有文件传输到客户端
//			List<File> files=null;
			
			String strServiceName=null;
			String strTransName=null;
			boolean isAync=false; //是否是异步
			//解释cdo
	    	try{
				cdoRequest=ParseProtoCDO.ProtoParse.parse(proto);
				strServiceName=cdoRequest.exists(ITransService.SERVICENAME_KEY)?cdoRequest.getStringValue(ITransService.SERVICENAME_KEY):"null";
				strTransName=cdoRequest.exists(ITransService.TRANSNAME_KEY)?cdoRequest.getStringValue(ITransService.TRANSNAME_KEY):"null";				
				//执行业务逻辑后  输出......
				cdoOutput=handleTrans(cdoRequest,listFile,strServiceName,strTransName);	   
//	    		files=RPCFile.readFileFromCDO(cdoOutput.getCDOValue("cdoResponse"));		    		
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
//			resMessage.setFiles(files);
			if(channel!=null)
				channel.writeAndFlush(resMessage);	
			else
			   logger.warn("channel is null,can't send response data back.cdoRequest="+cdoRequest.toXML()+","+cdoOutput);
		}finally{
			cdoRequest.deepRelease();
			cdoOutput.deepRelease();
			resProtoBuiler=null;
			proto=null;
		}		
	}	
	/**
	 * 为了在客户端比较快速解释，使用固定顺序，采用 cdoReturn放在第一位,cdoResponse放在第二位
	 * 返回的 cdoOutput 只有2个cdo对象,cdoReturn,cdoResponse
	 * cdoReturn 只有 三个数据 
	 * 其余 cdoResponse里的数据
	 * 使用下标  判断 替换 原来的 字符串比较
	 * @param cdoRequest
	 * @param listFile
	 * @param strServiceName
	 * @param strTransName
	 * @return
	 */
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
        
        if(corePoolSize==1){
        	if(logger.isDebugEnabled())
        		logger.debug("channel="+channel+" is channelRegistered, corePoolSize="+corePoolSize+",reset directNioChannel=true");
        	directNioChannel=true;        	
        }
        if(directNioChannel){
        	if(logger.isDebugEnabled())
        		logger.debug("channel="+channel+" is channelRegistered, directNioChannel=true");
        	return;
        }
        
		lnkTransQueue = new LinkedTransferQueue<GoogleCDO.CDOProto>();
		exService=Executors.newFixedThreadPool(corePoolSize);
		consumer=new Consumer[corePoolSize]; 
		for(int i=0;i<corePoolSize;i++){
			consumer[i]=new Consumer(channel+",Consumer"+i,lnkTransQueue,this);
			exService.submit(consumer[i]);
		}		
		if(logger.isInfoEnabled())
			logger.info("channel="+channel+" is channelRegistered, lnkTransQueue executor create newFixedThreadPool coresize="+corePoolSize);
    }
    
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelUnregistered();        
        logger.warn("channel="+channel+" is channelUnregistered,lnkTransQueue executor will be shutdown");
        if(directNioChannel){
        	return;
        }
        lnkTransQueue.clear();
		for(int i=0;i<consumer.length;i++){
			consumer[i].setStop(true);	
		    consumer[i]=null;   
		}	               
        lnkTransQueue=null;            
        exService.shutdownNow();
    }
    
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {        
        super.channelInactive(ctx);     
    }
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	super.channelActive(ctx);	
    }

}
