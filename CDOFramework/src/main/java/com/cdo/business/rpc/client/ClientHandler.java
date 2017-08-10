package com.cdo.business.rpc.client;

import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;

import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {
	 
	public IRPCResponse handleTrans(CDO cdoRequest){
		 
		return null;
	 }
	
	public Return asyncHandleTrans(CDO cdoRequest){
		return null;
	}
}
