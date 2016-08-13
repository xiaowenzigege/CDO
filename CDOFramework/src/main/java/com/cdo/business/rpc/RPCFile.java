package com.cdo.business.rpc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cdoframework.cdolib.base.DataType;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.data.cdo.Field;
import com.cdoframework.cdolib.data.cdo.FileField;

public class RPCFile {
  
	static class FileBean{
    	private String key;
    	private String value;
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
    	
    }
    
   public static List<File> readFileFromCDO(CDO cdo) throws FileNotFoundException{
	   		if(cdo.getSerialFileCount()<=0)
	   			return null;

 			List<File> files=new ArrayList<File>();
 			List<FileBean> fileBeans=new ArrayList<FileBean>(); 			 
 			for(Iterator<Map.Entry<String,Field>> it=cdo.iterator();it.hasNext();){	   		 
 	   			 Map.Entry<String,Field> entry=it.next();
 	   			 Field objExt=entry.getValue();
 	   			 if(objExt.getType().getDataType()==DataType.FILE_TYPE){
 	   				 File f=((FileField)entry.getValue()).getValue();
 	   				 if(!f.exists() || !f.isFile()){
 	   					 throw new FileNotFoundException("file field:"+entry.getKey()+",value:"+f.getPath()+" is not found");
 	   				 } 	   				  	   				 
 	   				 FileBean fileBean=new FileBean(); 	   				
 	   				 fileBean.setKey(entry.getKey()+"FileName");
 	   				 fileBean.setValue(f.getName());
 	   				 
 	   				 files.add(f);
 	   				 fileBeans.add(fileBean);
 	   			 }
 	   		 } 
 			for (FileBean fileBean : fileBeans) {
 				cdo.setStringValue(fileBean.getKey(), fileBean.getValue());
			} 			
 			return files; 		
     }
   
	
   public static void setFile2CDO(CDO cdo,List<File> listFile){
		if(listFile==null || listFile.size()==0)
			return;
		 Iterator<Map.Entry<String,Field>> it=cdo.entrySet().iterator();
		 int index=0;
  		 while(it.hasNext()){
  			 Map.Entry<String,Field> entry=it.next();
  			 Field objExt=entry.getValue();
  			 if(objExt.getType().getDataType()==DataType.FILE_TYPE){
  				 if(listFile.size()>index){
  					 cdo.setFileValue(entry.getKey(), listFile.get(index));
  					 index++;
  				 }
  			 }
  		 }   					
	}   
}
