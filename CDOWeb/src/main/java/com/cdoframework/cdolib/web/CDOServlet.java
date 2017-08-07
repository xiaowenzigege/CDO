

package com.cdoframework.cdolib.web;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.FileCleanerCleanup;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileCleaningTracker;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import com.cdo.business.BusinessService;
import com.cdo.util.constants.Constants;
import com.cdo.util.serial.Serializable;
import com.cdoframework.cdolib.base.DataType;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.data.cdo.Field;
import com.cdoframework.cdolib.data.cdo.FileField;
import com.cdoframework.cdolib.servicebus.IServiceBus;
//import com.cdoframework.cdolib.servicebus.ITransService;
/**
 * 
 * @author KenelLiu
 *  创建多列时，可以使用 这样方法调用
 *  if(cdoRequest.exists(ITransService.PACKAGE_KEY)){		
		String classPath=cdoRequest.getStringValue(ITransService.PACKAGE_KEY)+"."+cdoRequest.getStringValue(ITransService.SERVICENAME_KEY);					
		TransService transService=(TransService)Class.forName(classPath).newInstance();
		transService.inject(transService);
		ret=transService.processTrans(cdoRequest, cdoResponse);	
	}
 */
public  class CDOServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6566513350461152047L;

	private static Logger log=Logger.getLogger(CDOServlet.class);
	private final static long maxFileSize=1024*1024*50;	

	private IServiceBus serviceBus;

	public CDOServlet(){
		serviceBus=BusinessService.getInstance().getServiceBus();
	}

	// 公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------

	// 接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
			// 构造请求对象
		CDO cdoRequest=null;
		Return ret=null;
		Map<String, File> mapFileMap=null;		
		// 输出响应数据
		CDO cdoOutput=new CDO();
		CDO cdoReturn=new CDO();
		CDO cdoResponse=new CDO();		
		try
		{
			String serialFile=request.getHeader(Constants.CDO.HTTP_CDO_UPLOAD_FILE_FLAG);
			if(serialFile!=null && serialFile.trim().equals("1")){
				//表示有文件对象需要上传 
				mapFileMap=new HashMap<String,File>();
				cdoRequest=processUploadFile(request, mapFileMap);
				if(mapFileMap!=null){
					for(Iterator<Map.Entry<String, File>> iterator=mapFileMap.entrySet().iterator();iterator.hasNext();){
						Entry<String, File> entry=iterator.next();
						cdoRequest.setFileValue(entry.getKey(), entry.getValue());
					}
				}
			}else{
				cdoRequest=processProtobufEntity(request);
			}
		
		  if(cdoRequest==null)
				throw new IOException("cdoRequest is null");		
		  ret=serviceBus.handleTrans(cdoRequest, cdoResponse);		
		  if(ret==null)
			  throw new IOException("ret is null,may be Request method not found,cdoRequest="+cdoRequest);
		
		  
		  cdoReturn.setIntegerValue("nCode",ret.getCode());
		  cdoReturn.setStringValue("strText",ret.getText());
		  cdoReturn.setStringValue("strInfo",ret.getInfo());

		  cdoOutput.setCDOValue("cdoReturn",cdoReturn);
		  cdoOutput.setCDOValue("cdoResponse", cdoResponse);			
		  //输出	
		  outputReponse(response, cdoOutput,cdoResponse);
		}catch(Throwable e){						
			log.error("error:"+e.getMessage(),e);
			cdoReturn.setIntegerValue("nCode",-1);
			cdoReturn.setStringValue("strText"," handle Service Error :"+e.getMessage());
			cdoReturn.setStringValue("strInfo"," handle Service Error :"+e.getMessage());
			
			cdoOutput.setCDOValue("cdoReturn",cdoReturn);
			cdoOutput.setCDOValue("cdoResponse",cdoResponse);
			//输出	
			outputReponse(response, cdoOutput,cdoResponse);
		}finally{
			if(cdoRequest!=null)
				cdoRequest.deepRelease();	
		}		
	}
	
	// 事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

		/**
		 * 安全输出http响应
		 * @param response
		 * @param cdoOutput
		 * @throws IOException 
		 */
		private void outputReponse(HttpServletResponse response, CDO cdoOutput,CDO cdoResponse) throws IOException {
			
			ServletOutputStream out=null;
			InputStream inStream=null;
			try {		
				//转换成字节
				byte[] byteOutput=cdoOutput.toProtoBuilder().build().toByteArray();
				//----设置header----		
				File[] files=setResponseHeader(response, byteOutput, cdoResponse);
				//输出内容
				out=response.getOutputStream();			
				out.write(byteOutput);	
				//输出文件
				for(int i=0;i<files.length;i++){
			         /*创建输入流*/
	                inStream = new FileInputStream(files[i]);
	                byte[] buf = new byte[Constants.Byte.maxSize*2];             
	                int readLength;
	                while (((readLength = inStream.read(buf)) != -1)) {
	                	out.write(buf, 0, readLength);
	                }
	                inStream.close(); 
				}						
				out.flush();
			} catch (Throwable e) {
				log.error("wirte response errr"+e.getMessage(), e);
			}finally{
				if(inStream!=null){try{inStream.close();}catch(Exception ex){};}
				if(out != null)try{out.close();}catch(Exception ex){};}	
				cdoOutput.deepRelease();
		}
		
		private File[] setResponseHeader(HttpServletResponse response, byte[] byteOutput,CDO cdoResponse) throws UnsupportedEncodingException{
			response.setContentType("application/octet-stream;charset=UTF-8");
			response.setHeader("Cache-control","no-cache,no-store");
			response.setHeader("Accept-Ranges", "bytes");
			int cdoXmlLen=byteOutput.length;
			long totalLen=cdoXmlLen;
			
			StringBuilder sbFieldName=new StringBuilder(15);
			StringBuilder sbLength=new StringBuilder();
			StringBuilder sbFileName=new StringBuilder(15);
			sbFieldName.append(Constants.CDO.HTTP_CDO_RESPONSE);
			sbLength.append(cdoXmlLen);	
			sbFileName.append("xml");
			
			List<File> listFiles=new ArrayList<File>();
			if(cdoResponse!=null && cdoResponse.getSerialFileCount()>0){
	    		 Iterator<Map.Entry<String,Field>> it=cdoResponse.entrySet().iterator();    		 
	    		 while(it.hasNext()){
	    			 Map.Entry<String,Field> entry=it.next();
	    			 Field objExt=entry.getValue();
	    			 if(objExt.getType().getDataType()==DataType.FILE_TYPE){  
	    				 FileField f=(FileField)objExt;
	    				 sbFieldName.append(","+entry.getKey());
	    				 sbLength.append(","+f.getValue().length());
	    				 sbFileName.append(","+f.getValue().getName());
	    				 totalLen=totalLen+f.getValue().length();
	    				 listFiles.add(f.getValue());
	    			 }
	    		 }	    		
			}
			response.setHeader(sbFieldName.toString(), sbLength.toString()+";"+sbFileName);
//			response.setHeader("Content-Length", ""+totalLen);  在resin http/1.1 会处理字节存在问题,不设置长度
			return listFiles.toArray(new File[listFiles.size()]);
		}
	 /**
	  * 使用http+proto格式	
	  * @param request
	  * @return
	  * @throws IOException
	  */
	 private CDO processProtobufEntity(HttpServletRequest request) throws  IOException {  		  
			 ServletInputStream sis=null;  
			 ByteArrayOutputStream baos=null;
			try {    
		        	final int BUFFER_SIZE = 4*Constants.Byte.defaultSize;
		            byte[] buffer = new byte[ BUFFER_SIZE];
		            sis = request .getInputStream();
		            int length = 0;
		            baos = new ByteArrayOutputStream();	        	        
		            while(( length =  sis.read( buffer))>0){   
		                  baos.write( buffer, 0, length);
		            } 	           
		            return Serializable.byte2ProtoCDO(baos.toByteArray());     
		        } catch(Throwable e) {  
		           throw new IOException(e.getMessage(),e);        
		        }finally{
		        	if(sis!=null){try{sis.close();}catch(Exception ex){}}
		        	if(baos!=null){try{baos.close();}catch(Exception ex){}}
		        }  	      
		   }
	 
	 /**
	  * 文件上传  使用 常规的muti part格式
	  * @param request
	  * @param mapFile
	  * @return
	  * @throws ServletException
	  * @throws IOException
	  */
	private  CDO processUploadFile(HttpServletRequest request,Map<String,File> mapFile) throws ServletException, IOException {  
	        try {    
	            String saveDirPath =System.getProperty(Constants.CDO.HTTP_CDO_UPLOAD_FILE_PATH,
	            		this.getServletContext().getRealPath("/cdoUploadPath"));
	            
	            String tmpDirPath =System.getProperty("java.io.tmpdir");
	            

	            long maxSize=Long.parseLong(System.getProperty(Constants.CDO.HTTP_CDO_UPLOAD_FILE_MaxSize, maxFileSize+""));

	            if(log.isDebugEnabled()){
		            log.debug(" tmpDir= [" + tmpDirPath + "]"+" saveDir= [" + saveDirPath + "]");  		          		         
	            }
	            
	            File saveDir = new File(saveDirPath);  
	            File tmpDir= new File(tmpDirPath);  

	            DiskFileItemFactory factory = new DiskFileItemFactory();  
	            factory.setSizeThreshold(5*DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD);//设置成50K
	            //DiskFileItemFactory中DEFAULT_SIZE_THRESHOLD=10240表示如果上传文件大于10K则会产生上传临时文件  
	            //上传临时文件的默认目录为java.io.tmpdir中保存的路径，根据操作系统的不同会有区别  	              
	            if(!tmpDir.exists() || !tmpDir.isDirectory()) {  
	            	tmpDir.mkdirs();  
	            }  
	            //重新设置临时文件保存目录  
	            factory.setRepository(tmpDir);  
	  
	            //设置文件清除追踪器，文件上传过程中产生的临时文件会在  
	            FileCleaningTracker fileCleaningTracker = FileCleanerCleanup.getFileCleaningTracker(this.getServletContext());  
	            factory.setFileCleaningTracker(fileCleaningTracker);  
	  
	            ServletFileUpload upload = new ServletFileUpload(factory);  
	            
	            //设置文件上传进度监听器  
//	            FileProcessListener processListener = new FileProcessListener(request.getSession());  
//	            upload.setProgressListener(processListener);  	  
	            // 设置文件上传的大小限制
	            upload.setFileSizeMax(maxSize);  
	  
	            // 设置文件上传的头编码，如果需要正确接收中文文件路径或者文件名  
	            // 这里需要设置对应的字符编码，为了通用这里设置为UTF-8  
	            upload.setHeaderEncoding(Constants.Encoding.CHARSET_UTF8);  	  
	            //解析请求数据包  
	            List<FileItem> fileItems = upload.parseRequest(request);  
	            //遍历解析完成后的Form数据和上传文件数据  	          
	            for (Iterator<FileItem> iterator = fileItems.iterator(); iterator.hasNext();) {  
	                FileItem fileItem = iterator.next();  
	                String fieldName = fileItem.getFieldName();  
	                String name = fileItem.getName();	                	 
	                //如果为上传文件数据  
	                if(!fileItem.isFormField()) { 
	                   	if(log.isDebugEnabled())
	                		log.debug("fieldName[" + fieldName + "] fileName[" + name + "]"); 
	                    if(fileItem.getSize() > 0) {  
	                        String fileName = FilenameUtils.getName(name);  
	                        File file=new File(saveDir, fileName);
	                        FileUtils.copyInputStreamToFile(fileItem.getInputStream(),file);
	                        //保存文件
	                        mapFile.put(fieldName, file);	                        
	                    }else{
	                    	log.warn("upload File fieldName[" + fieldName + "] fileName[" + name + "] size=0");
	                    }  
	                }else{
	                   	if(log.isDebugEnabled())
	                		log.debug("fieldName[" + fieldName + "] value[" + fileItem.getString() + "]"); 
	                	if(fieldName.equals(Constants.CDO.HTTP_CDO_REQUEST)){	                		
	                		 return CDO.fromXML(fileItem.getString());
	                	}
	                } 
	            }
	           
	           throw new IOException("param["+Constants.CDO.HTTP_CDO_REQUEST+"] is not exists.... ");  
	        } catch(Exception e) {  
	        	log.error("解释上传文件数据失败....."+e.getMessage(), e);
	        	throw new IOException(e.getMessage(),e);
	        }  
	        
	    }  
}
