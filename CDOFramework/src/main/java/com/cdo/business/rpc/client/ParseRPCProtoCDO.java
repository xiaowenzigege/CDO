package com.cdo.business.rpc.client;


import com.cdo.google.handle.ParseProtoCDO;
import com.cdo.google.protocol.GoogleCDO;
import com.cdoframework.cdolib.data.cdo.CDO;
/**
 * @see {@link com.cdo.business.rpc.server.RPCServerHandler}
 * @author KenelLiu
 *   解释  响应的ProtoCDO
 *   响应数据为CDO序列化，该CDO 仅包含[cdoReturn,cdoResponse]2个cdo对象,
 *   且顺序是固定的，cdoReturn 排序为第一位,
 *   cdoReturn 只有 三个数据  
 *   其余 cdoResponse里的数据
 */
public class ParseRPCProtoCDO extends ParseProtoCDO {
    public static ParseRPCProtoCDO ProtoRPCParse;
    static{
    	ProtoRPCParse=new ParseRPCProtoCDO();
   }
	private ParseRPCProtoCDO(){}
	
	 void parse(GoogleCDO.CDOProto proto,CDO cdoResponse,CDO cdoReturn){		
		proto2CDO(proto.getFieldsList(),cdoResponse,cdoReturn);					
	} 	
	
}
