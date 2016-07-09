package com.cdo.avro.handle;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.cdo.avro.protocol.AvroCDO;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.data.cdo.ParseCDOBuffer;

public class ParseAvroCDO extends ParseCDOBuffer{
	
    public static ParseAvroCDO AvroParse;
    static{
    	AvroParse=new ParseAvroCDO();
   }
	protected ParseAvroCDO(){		
	}
	
	public  CDO parse(AvroCDO avro){		
		CDO cdo=new CDO();
		avro2CDO(cdo,avro.getFields());					
		return cdo;
	} 
	
	private  void avro2CDO(CDO cdo,Map<CharSequence,ByteBuffer> fieldsMap){
		 for(Iterator<Map.Entry<CharSequence,ByteBuffer>> iterator=fieldsMap.entrySet().iterator();iterator.hasNext();){
			 Entry<CharSequence, ByteBuffer> entry=iterator.next();
			 String key=entry.getKey().toString();
			 ByteBuffer buffer=entry.getValue();
			 parseHierarchicCDO(cdo,buffer,key);
		  }	    			 
	}
			
}
