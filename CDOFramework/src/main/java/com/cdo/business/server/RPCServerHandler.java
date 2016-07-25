package com.cdo.business.server;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.cdo.business.BusinessService;
import com.cdo.google.handle.CDOMessage;
import com.cdo.google.handle.Header;
import com.cdo.google.handle.ParseProtoCDO;
import com.cdo.google.handle.ProtoProtocol;
import com.cdo.google.protocol.GoogleCDO;
import com.cdo.util.common.UUidGenerator;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.servicebus.ITransService;
import com.google.protobuf.MessageLite;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;

public class RPCServerHandler extends SimpleChannelInboundHandler<CDOMessage> {

	private static Logger logger=Logger.getLogger(RPCServerHandler.class);
	private final  BusinessService serviceBus=BusinessService.getInstance();
	static Map<String,SocketChannel> nettyChannelMap=new ConcurrentHashMap<String, SocketChannel>();
	private ExecutorService executor =Executors.newFixedThreadPool(20);
    private  Channel channel;
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        channel = ctx.channel();
    }	
    /**
     * 防止   单一机器的长连接   发生阻塞,事务采用线程池处理,长连接channel仅用于数据传输，
     * 不能在上面处理事务,根据是内部长连接还是外部长连接，需要调节连接池的数量。
     */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, CDOMessage msg)
			throws Exception {	
		  Header header=msg.getHeader();
		if(header.getType()==ProtoProtocol.TYPE_CDO){			
			GoogleCDO.CDOProto proto=(GoogleCDO.CDOProto)(msg.getBody());
			String clientId=UUidGenerator.ClientId.toString(proto.getClientId().toByteArray());
			nettyChannelMap.put(clientId,(SocketChannel)ctx.channel());
			Task task=new Task(proto);
			executor.submit(task);
		}else if(header.getType()==ProtoProtocol.TYPE_HEARTBEAT_REQ){
			//心跳检查
			Header resHeader=new Header();
			resHeader.setType(ProtoProtocol.TYPE_HEARTBEAT_RES);
			CDOMessage resMessage=new CDOMessage();
			resMessage.setHeader(resHeader);
			ctx.writeAndFlush(resMessage);
		}else{
			ctx.fireChannelRead(msg);
		}
	}

	private class Task implements Runnable{
		private GoogleCDO.CDOProto proto;
		public Task(GoogleCDO.CDOProto proto){
			this.proto=proto;
		}
		@Override
		public void run() {				
			GoogleCDO.CDOProto.Builder retProtoBuiler=handleTrans(this.proto);
			String clientId=UUidGenerator.ClientId.toString(this.proto.getClientId().toByteArray());
			SocketChannel channel=nettyChannelMap.get(clientId);
			
			retProtoBuiler.setCallId(proto.getCallId());			
			Header resHeader=new Header();
			resHeader.setType(ProtoProtocol.TYPE_CDO);
			CDOMessage resMessage=new CDOMessage();
			resMessage.setHeader(resHeader);
			resMessage.setBody(retProtoBuiler.build());
			
			channel.writeAndFlush(resMessage);			
			nettyChannelMap.remove(clientId);			
		}
		
		private GoogleCDO.CDOProto.Builder handleTrans(GoogleCDO.CDOProto proto){
			
			CDO cdoOutput=new CDO();			
			try{				
				CDO cdoResponse=new CDO();
				CDO cdoRequest=ParseProtoCDO.ProtoParse.parse(proto);				
				Return ret=serviceBus.handleTrans(cdoRequest, cdoResponse);				
				if(ret==null){
					String strServiceName=cdoRequest.exists(ITransService.SERVICENAME_KEY)?cdoRequest.getStringValue(ITransService.SERVICENAME_KEY):"null";
					String strTransName=cdoRequest.exists(ITransService.TRANSNAME_KEY)?cdoRequest.getStringValue(ITransService.TRANSNAME_KEY):"null";					
					setOutCDO(cdoOutput," ret is null,Request method :strServiceName="+strServiceName+",strTransName="+strTransName);	
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
				setOutCDO(cdoOutput,"服务端处理异常:"+ex.getMessage());
			} 	
			return cdoOutput.toProtoBuilder();
		}
		
		private void setOutCDO(CDO cdoOutput,String message){
			
			CDO cdoReturn=new CDO();
			cdoReturn.setIntegerValue("nCode",-1);
			cdoReturn.setStringValue("strText",message);
			cdoReturn.setStringValue("strInfo",message);
			
			cdoOutput.setCDOValue("cdoReturn",cdoReturn);
			cdoOutput.setCDOValue("cdoResponse",new CDO());		
		}
		
	}
	
}
