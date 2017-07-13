package com.cdo.business.rpc.server;


import java.net.InetSocketAddress;
import org.apache.log4j.Logger;
import com.cdo.google.handle.CDOMessage;
import com.cdo.google.handle.Header;
import com.cdo.google.handle.ProtoProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;

public class HeartbeatServerHandler extends SimpleChannelInboundHandler<CDOMessage> {

	private static Logger logger=Logger.getLogger(HeartbeatServerHandler.class);
    /**
     * 服务端接受到   客户端心跳信息,进行回复
     */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, CDOMessage msg)
			throws Exception {	
		Header header=msg.getHeader();
		switch (header.getType()) {
			case ProtoProtocol.TYPE_HEARTBEAT_REQ:
				Header resHeader=new Header();
				resHeader.setType(ProtoProtocol.TYPE_HEARTBEAT_RES);
				CDOMessage resMessage=new CDOMessage();
				resMessage.setHeader(resHeader);
				ctx.writeAndFlush(resMessage);			
			    if(logger.isDebugEnabled())
	    			logger.debug("server receive client heart ["+ctx.channel()+"] heart msg:"+msg);
				break;
			case ProtoProtocol.TYPE_HEARTBEAT_RES: 
				//服务端主动发起心跳检查,客户端回复心跳
			    if(logger.isDebugEnabled())
			    			logger.debug("server receive client address["+(InetSocketAddress)ctx.channel().remoteAddress()+"] heart msg:"+msg);
			    //关闭服务
			     break;
			case ProtoProtocol.TYPE_STOPLOCALServer:
		  		  RPCServer.stop();
		  		  break;
			default:
				ctx.fireChannelRead(msg);
				break;
		}  

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
