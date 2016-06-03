package com.cdo.avro.schema;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileStream;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import com.cdoframework.cdolib.data.cdo.CDO;



public class ArvoMain {
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
	       
	public static void main(String[] args) {
		try{
//			schemaDescription=readFile(new FileInputStream("E:/CDO/CDO/CDOLib/src/main/avro/AvroCDO.avsc"));
			AvroUtils.parseSchema(schemaDescription);
//			schemaDescriptionExt=readFile(new FileInputStream("E:/CDO/CDO/CDOLib/src/main/avro/CDO.avsc"));
			Schema extended = AvroUtils.parseSchema(schemaDescriptionExt);
			System.out.println(extended.toString(true));
			Schema schema = new Schema.Parser().parse(new File("d:/test/test.txt"));
			GenericRecord user1 = new GenericData.Record(schema);
			user1.put("name", "Alyssa");
			user1.put("favorite_number", 256);
			// Leave favorite color null

			GenericRecord user2 = new GenericData.Record(schema);
			user2.put("name", "Ben");
			user2.put("favorite_number", 7);
			user2.put("favorite_color", "red");
//			user2.put(key, v);
			CDO cdo = new CDO();
			cdo.setByteArrayValue("bytes", new byte[]{1,2,3});
			cdo.setStringValue("strName1","张三");
			cdo.setIntegerValue("nAge1",0);
			cdo.setDateValue("dBirthday1","2000-01-01");
			int[] nsValue=new int[3];
			for(int i=0;i<nsValue.length;i++)
			{
				nsValue[i]=i;
			}
			cdo.setIntegerArrayValue("nsValue1",nsValue);
			String[] strsValue=new String[3];
			for(int i=0;i<strsValue.length;i++)
			{
				strsValue[i]="strValue"+i;
			}
			cdo.setStringArrayValue("strsValue",strsValue);


			CDO cdoResponse=new CDO();
			cdoResponse.setIntegerValue("ncount", 5);
			CDO[] cdosList=new CDO[5];
			for(int i=0;i<cdosList.length;i++)
			{			
				cdosList[i]=new CDO();

				CDO[] cdo1=new CDO[2];
				for(int j=0;j<cdo1.length;j++){
					cdo1[j]=new CDO();
					cdo1[j].setStringValue("strName2","张三"+i);
					cdo1[j].setDateValue("dBirthday2","2000-01-01");
				}
				cdosList[i].setCDOArrayValue("cdo1", cdo1);
				cdosList[i].setBooleanValue("booleanValue", true);
				cdosList[i].setStringValue("strValue", "cdosList张"+i);
				cdosList[i].setIntegerArrayValue("nsCDOList"+i, new int[]{1,2,3});
				CDO cdo2=new CDO();
				cdo2.setStringArrayValue("xx", new String[]{"ss","x"});
				cdo2.setIntegerArrayValue("nsCDOList"+i, new int[]{1,2,3});
				cdosList[i].setCDOValue("cdo", cdo2);
				
			}
			cdoResponse.setCDOArrayValue("cdosList", cdosList);
			
			CDO cdoReturn=new CDO();
			cdoReturn.setIntegerValue("nCode",0);
			cdoReturn.setStringValue("strText","测试");
			cdoReturn.setStringValue("strInfo","测试");
			cdo.setCDOValue("cdoReturn",cdoReturn);
			cdo.setCDOValue("cdoResponse", cdoResponse);

			CDO cdoChild=new CDO();
			cdoChild.setStringValue("strName2","张三");
			cdoChild.setIntegerValue("nAge2",0);
			cdoChild.setDateValue("dBirthday2","2000-01-01");
			cdoChild.setIntegerArrayValue("nsValue2",nsValue);
			CDO cdo2=new CDO();
			cdo2.setStringValue("x", "v");
			cdoChild.setCDOValue("cdo2", cdo2);
			cdo.setCDOValue("cdoChild",cdoChild);			
//			 for(int i=0;i<1;i++){
//					AvroCDOSerialize serialize=new AvroCDOSerialize(cdo);
//					AvroCDOMixed arvo= serialize.toAvro();	
//					
//					ByteArrayOutputStream out=new ByteArrayOutputStream();
//					
//					DatumWriter<AvroCDOMixed> userDatumWriter = new SpecificDatumWriter<AvroCDOMixed>(AvroCDOMixed.class);
//					DataFileWriter<AvroCDOMixed> dataFileWriter = new DataFileWriter<AvroCDOMixed>(userDatumWriter);
//					dataFileWriter.create(arvo.getSchema(),out);
//					dataFileWriter.append(arvo);
//					dataFileWriter.close();
////					System.out.println("time2 ="+(System.currentTimeMillis()-startTime));
//					
//					InputStream bis = new ByteArrayInputStream(out.toByteArray());
//					
//					DatumReader<AvroCDOMixed> userDatumReader = new SpecificDatumReader<AvroCDOMixed>(AvroCDOMixed.class);
//					DataFileStream<AvroCDOMixed> dataFileReader = new DataFileStream<AvroCDOMixed>(bis,userDatumReader);
//					
//					while (dataFileReader.hasNext()) {
//						arvo=dataFileReader.next(arvo);
//						cdo=AvroCDOSerialize.fromAvro(arvo);			
//					}
////					System.out.println("time3 ="+(System.currentTimeMillis()-startTime));
//			 }			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	private static String readFile(InputStream inputStream) throws IOException{
 	  	BufferedReader in=new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
  	  	StringBuilder message = new StringBuilder();   
  	    String line = null;  
  	    while ((line = in.readLine()) != null) {   
  	    	message.append(line+"\n");   
  	          } 
  	    return message.toString();
	}
}
