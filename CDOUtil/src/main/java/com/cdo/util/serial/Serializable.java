package com.cdo.util.serial;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.avro.file.DataFileStream;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.json.JSONException;

import com.cdo.avro.handle.ParseAvroCDO;
import com.cdo.avro.protocol.AvroCDO;
import com.cdo.google.handle.ParseProtoCDO;
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
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		AvroCDO arvoCDO=cdo.toAvro();
	    DatumWriter<AvroCDO> writer=new SpecificDatumWriter<AvroCDO>(AvroCDO.class);    
	    DataFileWriter<AvroCDO> dataFileWriter = new DataFileWriter<AvroCDO>(writer); 		          
	    dataFileWriter.create(arvoCDO.getSchema(),out);	    	    	   	    
	    dataFileWriter.append(arvoCDO);
		dataFileWriter.close();
		out.close();
		return out.toByteArray();
	}
	/**
	 *  将apache avro字节数组   反序列化成 CDO 对象
	 * @param array
	 * @return
	 * @throws IOException
	 */
	public static CDO byte2AvroCDO(byte[] array) throws IOException{
		ByteArrayInputStream in=new ByteArrayInputStream(array);
	    DatumReader<AvroCDO> reader=new SpecificDatumReader<AvroCDO>(AvroCDO.class);	    	   
		DataFileStream<AvroCDO> dataFileReader = new DataFileStream<AvroCDO>(in,reader);			  	    	    
		AvroCDO arvoCDO= null;
	    while (dataFileReader.hasNext()) {
	    	   arvoCDO = dataFileReader.next();	            
	    }	
	    if(arvoCDO!=null)
	    	return ParseAvroCDO.AvroParse.parse(arvoCDO);
	    return null;
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
