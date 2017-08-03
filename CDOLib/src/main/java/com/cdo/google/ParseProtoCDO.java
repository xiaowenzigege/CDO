package com.cdo.google;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.cdo.google.protocol.GoogleCDO;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.data.cdo.Field;
import com.cdoframework.cdolib.data.cdo.ParseCDOBuffer;

public class ParseProtoCDO extends ParseCDOBuffer{
	
    public static ParseProtoCDO ProtoParse;
    static{
    	ProtoParse=new ParseProtoCDO();
   }
	protected ParseProtoCDO(){}
	

	
	public  CDO parse(GoogleCDO.CDOProto proto){		
		CDO cdo=new CDO();
		proto2CDO(cdo,proto.getFieldsList());
		return cdo;
	} 
		
	private  void proto2CDO(CDO cdo,List<GoogleCDO.CDOProto.Entry> fieldList){
		 for(int i=0;i<fieldList.size();i++){
			 GoogleCDO.CDOProto.Entry entry=fieldList.get(i);
			 String key=entry.getName();
			 ByteBuffer buffer = ByteBuffer.wrap(entry.getValue().toByteArray());
			 buffer.clear();	
			 parseHierarchicCDO(cdo,buffer,key);
		  }	    	
	}
	/**
	 * 为了在客户端比较快速解释，服务端响应数据返回 使用固定顺序，采用 cdoReturn放在第一位,cdoResponse放在第二位
	 * 
	 * 且 cdoReturn 只有 三个数据 
	 * 其余 cdoResponse里的数据
	 * 使用下标  判断 替换 原来的 字符串比较
	 * 
	 * 响应数据返回是cdoReturn,cdoResponse 可以直接分拆处理 
	 * @param fieldList
	 * @param cdoResponse
	 * @param cdoReturn
	 */
	protected  void proto2CDO(List<GoogleCDO.CDOProto.Entry> fieldList,CDO cdoResponse,CDO cdoReturn){

		 for(int i=0;i<fieldList.size();i++){
			 GoogleCDO.CDOProto.Entry entry=fieldList.get(i);
			 String key=entry.getName();
			 ByteBuffer buffer = ByteBuffer.wrap(entry.getValue().toByteArray());
			 buffer.clear();			 
			 int index=key.indexOf(".");
//			 String cdoKey=key.substring(0,index);
			 String sufKey=key.substring(index+1);	
//			 if(cdoKey.equals("cdoReturn")){
			 if(i<=2){	 
				 parseHierarchicCDO(cdoReturn,buffer,sufKey);				 
			 }else{					 
				 parseHierarchicCDO(cdoResponse,buffer,sufKey);
			 }							 
		 }
	}	
}
