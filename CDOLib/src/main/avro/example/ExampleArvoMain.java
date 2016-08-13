package com.cdo.example;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileStream;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.ipc.HandshakeRequest;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import com.cdo.avro.handle.AvroUtils;
import com.cdo.avro.protocol.AvroCDO;
import com.cdo.google.protocol.GoogleCDO;
import com.cdoframework.cdolib.data.cdo.BooleanArrayField;
import com.cdoframework.cdolib.data.cdo.ByteArrayField;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.data.cdo.DateArrayField;
import com.cdoframework.cdolib.data.cdo.DateTimeArrayField;
import com.cdoframework.cdolib.data.cdo.IntegerArrayField;
import com.cdoframework.cdolib.data.cdo.LongArrayField;
import com.cdoframework.cdolib.data.cdo.ShortArrayField;
import com.cdoframework.cdolib.data.cdo.TimeArrayField;



public class ExampleArvoMain {
	 private static  String schemaDescription =
	         "{ \n" +
	            " \"namespace\": \"example.avro\", \n" +
	            " \"name\": \"AvroCDO\", \n" +
	            " \"type\": \"record\",\n" +
	            " \"fields\": [\n" +	                     
	                     " {\"name\":\"booleanValue\",\"type\":[\"null\",\"boolean\"]},\n"+
	                     " {\"name\":\"stringValue\",\"type\":[\"null\",\"string\"]},\n"+
	                     " {\"name\":\"timeValue\",\"type\":[\"null\",\"string\"]},\n"+
	                     " {\"name\":\"dateValue\",\"type\":[\"null\",\"string\"]},\n"+
	                     " {\"name\":\"dateTimeValue\",\"type\":[\"null\",\"string\"]},\n"+
	                     " {\"name\":\"byteValue\",\"type\":[\"null\",\"bytes\"]},\n"+
	                     " {\"name\":\"shortValue\",\"type\":[\"null\",\"int\"]},\n"+
	                     " {\"name\":\"integerValue\",\"type\":[\"null\",\"int\"]},\n"+
	                     " {\"name\":\"longValue\",\"type\":[\"null\",\"long\"]},\n"+
	                     " {\"name\":\"floatValue\",\"type\":[\"null\",\"float\"]},\n"+
	                     " {\"name\":\"doubleValue\",\"type\":[\"null\",\"double\"]},\n"+
	                     
	                     "{\"name\":\"booleanArrayValue\",\"type\":[\"null\",{\"type\":\"array\",\"items\":\"boolean\"}]},\n"+
	                     "{\"name\":\"stringArrayValue\",\"type\":[\"null\",{\"type\":\"array\",\"items\":\"string\"}]},\n"+
	                     "{\"name\":\"timeArrayValue\",\"type\":[\"null\",{\"type\":\"array\",\"items\":\"string\"}]},\n"+
	                     "{\"name\":\"dateArrayValue\",\"type\":[\"null\",{\"type\":\"array\",\"items\":\"string\"}]},\n"+
	                     "{\"name\":\"dateTimeArrayValue\",\"type\":[\"null\",{\"type\":\"array\",\"items\":\"string\"}]},\n"+
	                     "{\"name\":\"byteArrayValue\",\"type\":[\"null\",{\"type\":\"array\",\"items\":\"bytes\"}]},\n"+
	                     "{\"name\":\"shortArrayValue\",\"type\":[\"null\",{\"type\":\"array\",\"items\":\"int\"}]},\n"+
	                     "{\"name\":\"integerArrayValue\",\"type\":[\"null\",{\"type\":\"array\",\"items\":\"int\"}]},\n"+
	                     "{\"name\":\"longArrayValue\",\"type\":[\"null\",{\"type\":\"array\",\"items\":\"long\"}]},\n"+
	                     "{\"name\":\"floatArrayValue\",\"type\":[\"null\",{\"type\":\"array\",\"items\":\"float\"}]},\n"+
	                     "{\"name\":\"doubleArrayValue\",\"type\":[\"null\",{\"type\":\"array\",\"items\":\"double\"}]}\n"+
	                     
	            "]\n" +
	         "}";

	       private static  String schemaDescriptionExt =
	         " { \n" +
	             " \"namespace\": \"example.avro\", \n" +
	             " \"name\": \"CDO\", \n" +
	             " \"type\": \"record\",\n" +
	             " \"fields\": [\n" +
	                      " {\"name\": \"CDO\", \"type\": example.avro.AvroCDO },\n" +
	                      " {\"name\": \"specialData\", \"type\": \"int\"} ]\n" +
	          "}";

	   	private static String readFile(InputStream inputStream) throws IOException{
	 	  	BufferedReader in=new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
	  	  	StringBuilder message = new StringBuilder();   
	  	    String line = null;  
	  	    while ((line = in.readLine()) != null) {   
	  	    	message.append(line+"\n");   
	  	          } 
	  	    return message.toString();
		}
	   	
	   	private static void mergeSchema(){
//			schemaDescription=readFile(new FileInputStream("E:/CDO/CDO/CDOLib/src/main/avro/CDONameValuePair.avsc"));
			AvroUtils.parseSchema(schemaDescription);
//			schemaDescriptionExt=readFile(new FileInputStream("E:/CDO/CDO/CDOLib/src/main/avro/ArrayCDONameValuePair.avsc"));
			Schema extended = AvroUtils.parseSchema(schemaDescriptionExt);
			System.out.println(extended.toString(true));
	   	}
	public static void main(String[] args) {
		try{
			//mergeSchema();
			testHandshake();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}


	private static void  testAvro(CDO cdo) throws IOException{
		
		long startTime=System.nanoTime();						
		AvroCDO arvoCDO=ExampleCDO.getCDO().toAvro();		
		System.out.println("sss="+("0;a".substring(2)));
		System.out.println("new AvroCDO nan s ="+(System.nanoTime()-startTime));
		
		startTime=System.nanoTime();		
		
		ByteArrayOutputStream out=new ByteArrayOutputStream();
	    DatumWriter<AvroCDO> writer=new SpecificDatumWriter<AvroCDO>(AvroCDO.class);    
	    DataFileWriter<AvroCDO> dataFileWriter = new DataFileWriter<AvroCDO>(writer); 		          
	    dataFileWriter.create(arvoCDO.getSchema(),out);	    	    
	    System.out.println("avro serialize shcema ns==="+(System.nanoTime()-startTime));

	    startTime=System.nanoTime();
	    
	    dataFileWriter.append(arvoCDO);
		dataFileWriter.close();
		out.close();
		System.out.println("avro write serialize  data ns==="+(System.nanoTime()-startTime));
		
		startTime=System.nanoTime();
		
		ByteArrayInputStream in=new ByteArrayInputStream(out.toByteArray());
	    DatumReader<AvroCDO> reader=new SpecificDatumReader<AvroCDO>(AvroCDO.class);	    	   
		DataFileStream<AvroCDO> dataFileReader = new DataFileStream<AvroCDO>(in,reader);
		System.out.println("create avro reader deserialize shcema ns==="+(System.nanoTime()-startTime));	  
	    startTime=System.nanoTime();	    
		arvoCDO= null;
	    while (dataFileReader.hasNext()) {
	    	   arvoCDO = dataFileReader.next();	            
	    }		    
		System.out.println("avro  read deserialize  data ns==="+(System.nanoTime()-startTime));
	}
	
	private static void  testHandshake() throws IOException{
		
		long startTime=System.nanoTime();					
		HandshakeRequest request=new HandshakeRequest();
		System.out.println("new Handshake nan s ="+(System.nanoTime()-startTime));
		startTime=System.nanoTime();
		
		ByteArrayOutputStream out=new ByteArrayOutputStream();
	    DatumWriter<HandshakeRequest> writer=new SpecificDatumWriter<HandshakeRequest>(HandshakeRequest.class);    
	    DataFileWriter<HandshakeRequest> dataFileWriter = new DataFileWriter<HandshakeRequest>(writer); 		          
	    dataFileWriter.create(request.getSchema(),out);
	    
	    System.out.println("create Handshake write serialize shcema ns==="+(System.nanoTime()-startTime));
	    startTime=System.nanoTime();
	    
	    dataFileWriter.append(request);
		dataFileWriter.close();
		out.close();
		
		System.out.println("avro Handshake serialize  data ns==="+(System.nanoTime()-startTime));
		startTime=System.nanoTime();
		
		ByteArrayInputStream in=new ByteArrayInputStream(out.toByteArray());
	    DatumReader<HandshakeRequest> reader=new SpecificDatumReader<HandshakeRequest>(HandshakeRequest.class);	    	   
		DataFileStream<HandshakeRequest> dataFileReader = new DataFileStream<HandshakeRequest>(in,reader);
	
		System.out.println("create Handshake reader deserialize shcema ns==="+(System.nanoTime()-startTime));	  
	    startTime=System.nanoTime();
	    
	    request= null;
	    while (dataFileReader.hasNext()) {
	    	request = dataFileReader.next();	            
	    }	
	    
		System.out.println("Handshake  read deserialize  data ns==="+(System.nanoTime()-startTime));
	}

}
