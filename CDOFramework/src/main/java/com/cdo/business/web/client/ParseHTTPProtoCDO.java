package com.cdo.business.web.client;


import java.nio.ByteBuffer;
import java.util.List;

import com.cdo.google.handle.ParseProtoCDO;
import com.cdo.google.protocol.GoogleCDO;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;

/**
 * @see {@link com.cdoframework.cdolib.web.CDOServlet}
 * @author KenelLiu
 * 解释http  响应的bytes
 *   响应数据为CDO序列化，该CDO 仅包含[cdoReturn,cdoResponse]2个cdo对象,
 *   且顺序是固定的，cdoReturn 排序为第一位,
 *   cdoReturn 只有 三个数据  
 *   其余 cdoResponse里的数据
 */
public class ParseHTTPProtoCDO extends ParseProtoCDO {
	
    public static ParseHTTPProtoCDO HTTPProtoParse;
    static{
    	HTTPProtoParse=new ParseHTTPProtoCDO();
   }
	
	
	private ParseHTTPProtoCDO(){}
	
	 void parse(byte[] byteOutput,CDO cdoResponse,CDO cdoReturn) throws InvalidProtocolBufferException{
		MessageLite result= GoogleCDO.CDOProto.getDefaultInstance().getParserForType().parseFrom(byteOutput, 0, byteOutput.length);
		GoogleCDO.CDOProto proto=(GoogleCDO.CDOProto)result;
		proto2CDO(proto.getFieldsList(),cdoResponse,cdoReturn);					
	} 	
	 

}
