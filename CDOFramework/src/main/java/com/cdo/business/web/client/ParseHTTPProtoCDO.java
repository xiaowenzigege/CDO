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
 * 解释http  响应的ProtoCDO
 *CDOServlet中的 cdoOutput输出，顺序
 *，cdoReturn只有 三个数值，排在前面，后面为cdoResponse
 * cdoOutput 有2个对象cdoReturn，cdoResponse
 */
public class ParseHTTPProtoCDO extends ParseProtoCDO {
	
    public static ParseHTTPProtoCDO HTTPProtoParse;
    static{
    	HTTPProtoParse=new ParseHTTPProtoCDO();
   }
	protected ParseHTTPProtoCDO(){}
	
	 void protoToCDO(byte[] byteOutput,CDO cdoReturn,CDO cdoResponse) throws InvalidProtocolBufferException
	{
			MessageLite result= GoogleCDO.CDOProto.getDefaultInstance().getParserForType().parseFrom(byteOutput, 0, byteOutput.length);
			GoogleCDO.CDOProto proto=(GoogleCDO.CDOProto)result;
			List<GoogleCDO.CDOProto.Entry> fieldList=proto.getFieldsList();
			
			 for(int i=0;i<fieldList.size();i++){
				 GoogleCDO.CDOProto.Entry entry=fieldList.get(i);
				 String key=entry.getName();
				 ByteBuffer buffer = ByteBuffer.wrap(entry.getValue().toByteArray());
				 buffer.clear();	
				 if(i<=2){
					 //前三个为cdoReturn对象里的数据，后面为cdoReponse对象里的数据
					 parseHierarchicCDO(cdoReturn,buffer,key);
				 }else{
					 parseHierarchicCDO(cdoResponse,buffer,key);
				 }
				
			  }	
			
	}
	 

}
