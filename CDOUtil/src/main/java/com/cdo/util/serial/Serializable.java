package com.cdo.util.serial;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.avro.file.DataFileStream;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.json.JSONException;

import com.cdo.avro.handle.ParseAvroCDO;
import com.cdo.avro.protocol.AvroCDO;
import com.cdo.google.ParseProtoCDO;
import com.cdo.google.protocol.GoogleCDO;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.util.JsonUtil;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;

public class Serializable {	
	/**
	 * 	将CDO对象 序列化成 Google protobuf 字节数组 
	 * @param cdo
	 * @return
	 */
	public static byte[] protoCDO2Byte(CDO cdo){
		return cdo.toProtoBuilder().build().toByteArray();
	}
	/**
	 * 将   Google protobuf字节数组  反序列化成 CDO 对象
	 * @param array
	 * @return
	 * @throws InvalidProtocolBufferException
	 */
	public static CDO byte2ProtoCDO(byte[] array) throws InvalidProtocolBufferException{		
		MessageLite result= GoogleCDO.CDOProto.getDefaultInstance().getParserForType().parseFrom(array, 0, array.length);
		GoogleCDO.CDOProto proto=(GoogleCDO.CDOProto)result;
		return ParseProtoCDO.ProtoParse.parse(proto);
	}	

	/**
	 * 将CDO 对象序列化成  apache avro字节数组 
	 * @param cdo
	 * @return
	 * @throws IOException
	 */
	public static byte[] avroCDO2Byte(CDO cdo) throws IOException{
		ByteArrayOutputStream out=null;
		DatumWriter<AvroCDO> writer=null;
		DataFileWriter<AvroCDO> dataFileWriter=null;
		try{
			 out=new ByteArrayOutputStream();
			 AvroCDO avroCDO=cdo.toAvro();
			 writer=new SpecificDatumWriter<AvroCDO>(AvroCDO.class);    
			 dataFileWriter = new DataFileWriter<AvroCDO>(writer); 			 
	         dataFileWriter.create(AvroCDO.SCHEMA$,out);
	         dataFileWriter.append(avroCDO);
		 	 dataFileWriter.close();
			 out.close();
		     return out.toByteArray();
		}catch(Exception ex){
			throw new IOException("avro Serializable occured error："+ex.getMessage(),ex);
		}finally{
			if(dataFileWriter!=null){try{dataFileWriter.close();}catch(Exception e){}}			
			if(out!=null){try{out.close();}catch(Exception e){}}
		}  
	}
	/**
	 *  将apache avro字节数组   反序列化成 CDO 对象
	 * @param array
	 * @return
	 * @throws IOException
	 */
	public static CDO byte2AvroCDO(byte[] array) throws IOException{
		
		ByteArrayInputStream in=null;
		DatumReader<AvroCDO> reader=null;
		DataFileStream<AvroCDO> dataFileReader=null;
		try{
			 in=new ByteArrayInputStream(array);
		     reader=new SpecificDatumReader<AvroCDO>(AvroCDO.class);	    	   
			 dataFileReader = new DataFileStream<AvroCDO>(in,reader);			  	    	    			
		    while (dataFileReader.hasNext()) {
		    	return ParseAvroCDO.AvroParse.parse(dataFileReader.next());          
		    }
		    return null;
		}catch(Exception ex){
			throw new IOException("avro deSerializable occured error："+ex.getMessage(),ex);
		}finally{
			if(dataFileReader!=null){try{dataFileReader.close();}catch(Exception e){}}						
			if(in!=null){try{in.close();}catch(Exception e){}}
		}
	}
	
	/**
	 * 将List<CDO> 数组 对象转化成  apache avro字节数组 
	 * @param cdos
	 * @return
	 * @throws IOException
	 */
	public static byte[] arvoCDOArray2Byte(List<CDO> cdos) throws IOException{
		ByteArrayOutputStream out=null;
		DatumWriter<AvroCDO> writer=null;
		DataFileWriter<AvroCDO> dataFileWriter=null;
		try{
			 out=new ByteArrayOutputStream();
			 writer=new SpecificDatumWriter<AvroCDO>(AvroCDO.class);    
			 dataFileWriter = new DataFileWriter<AvroCDO>(writer); 		          
	         dataFileWriter.create(AvroCDO.SCHEMA$,out);	         
		     for (CDO cdo : cdos) {		    	
		    	dataFileWriter.append(cdo.toAvro());
			}	
		 	dataFileWriter.close();
			out.close();
		    return out.toByteArray();
		}catch(Exception ex){
			throw new IOException("avro Serializable occured error："+ex.getMessage(),ex);
		}finally{
			if(dataFileWriter!=null){try{dataFileWriter.close();}catch(Exception e){}}			
			if(out!=null){try{out.close();}catch(Exception e){}}
		}   
	}
	
	
	/**
	 *  将apache avro字节数组   反序列化成 List<CDO> 对象数组
	 * @param array
	 * @return
	 * @throws IOException
	 */
	public static List<CDO> byte2AvroCDOArray(byte[] array) throws IOException{
		
		ByteArrayInputStream in=null;
		DatumReader<AvroCDO> reader=null;
		DataFileStream<AvroCDO> dataFileReader=null;
		List<CDO> cdos=new ArrayList<CDO>();
		try{
			 in=new ByteArrayInputStream(array);
		     reader=new SpecificDatumReader<AvroCDO>(AvroCDO.class);	    	   
			 dataFileReader = new DataFileStream<AvroCDO>(in,reader);			 
		    while (dataFileReader.hasNext()) {
		    	cdos.add(ParseAvroCDO.AvroParse.parse(dataFileReader.next()));          
		    }
		    return cdos;
		}catch(Exception ex){
			throw new IOException("avro deSerializable occured error："+ex.getMessage(),ex);
		}finally{
			if(dataFileReader!=null){try{dataFileReader.close();}catch(Exception e){}}						
			if(in!=null){try{in.close();}catch(Exception e){}}
		}
	}	
	/**
	 * 将 json字符串 转换成CDO对象	 * 
	 * @param strJSON  json字符串
	 * @param cls    定义 了strJSON中    key作为变量,key变量的类型为    key对应的value值实际数据类型。
	 *      如 : strJSON={"key1":"value1","key2":20} 则 定义的class中   存在  int key2,String key1 变量,
	 *        则自动 将strJSON 转换key1成CDO 中string 对象,转换key2成CDO 中int 对象数据
	 * @return
	 * @throws JSONException
	 */
	public static CDO strJson2CDO(String strJSON,Class<?> cls) throws JSONException{
		return JsonUtil.json2CDO(strJSON, cls);
	}
}
