package com.cdo.business.client.rpc;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.apache.log4j.Logger;

import com.cdo.avro.protocol.AvroCDO;
import com.cdo.google.protocol.GoogleCDO;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.servicebus.ITransService;

/**
 * 
 * @author KenelLiu
 *
 */
public class ProtoPRCClient implements IRPCClient{
	private final static Logger logger=Logger.getLogger(ProtoPRCClient.class);
	static final boolean SSL = System.getProperty("ssl") != null;
    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8463"));
    static  ProtoClientHandle handle; 
    
    public static void openConnection() throws Exception {
        final SslContext sslCtx;
        if (SSL) {
            sslCtx = SslContextBuilder.forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE).build();
        } else {
            sslCtx = null;
        }
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
             .channel(NioSocketChannel.class)
             .handler(new ProtoClientInitializer(sslCtx));
            // Make a new connection.
            Channel  channel= b.connect(HOST, PORT).sync().channel();
            // Get the handler instance to initiate the request.
            handle = channel.pipeline().get(ProtoClientHandle.class);
        } finally {
            group.shutdownGracefully();
        }
    }
	/**
	 * @see {@link com.cdo.business.server.ProtoRPCServer#handleTrans(CDO,CDO)}
	 * @param cdoRequest
	 * @param cdoResponse
	 * @return
	 */
	public Return handleTrans(CDO cdoRequest, CDO cdoResponse){		
	  try {		  
		  	if(cdoRequest.getSerialFileCount()>0){
		  		return Return.valueOf(-1, "AvroRPC is unsupported file type");
		  	}
		  	
		  	CDO cdoReturn=new CDO();
			GoogleCDO.CDOProto proto=handle.handleTrans(cdoRequest);
			ProtoRPCCDOParse.ProtoRPCParse.parse(proto, cdoResponse, cdoReturn);			
		    return new Return(cdoReturn.getIntegerValue("nCode"),cdoReturn.getStringValue("strText"), cdoReturn.getStringValue("strInfo"));		
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			String strServiceName=cdoRequest.exists(ITransService.SERVICENAME_KEY)?cdoRequest.getStringValue(ITransService.SERVICENAME_KEY):"null";
			String strTransName=cdoRequest.exists(ITransService.TRANSNAME_KEY)?cdoRequest.getStringValue(ITransService.TRANSNAME_KEY):"null";					
			logger.error("Request method :strServiceName="+strServiceName+",strTransName="+strTransName+",error="+e.getMessage(),e);
			return new Return(-1,e.getMessage(),e.getMessage());
		}		  
	}
}
