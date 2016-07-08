package com.cdo.google.handle;

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
		for(Iterator<Map.Entry<String,Field>> iterator=cdo.iterator();iterator.hasNext();){
	    		Entry<String,Field> entry=iterator.next();
	    		list2array(cdo, entry.getKey(), entry.getValue());    		
	    } 
	}
	
}
