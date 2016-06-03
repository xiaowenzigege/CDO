/**
 * www.cdoforum.com 2007版权所有
 * 
 * $Header: /CVSData/Frank/CVSROOT/CDOForum/CDOWeb/Source/com/cdoframework/cdolib/web/CDOServlet.java,v 1.1 2008/03/05
 * 01:35:45 Frank Exp $
 * 
 * $Log: CDOServlet.java,v $ Revision 1.1 2008/03/05 01:35:45 Frank *** empty log message ***
 * 
 * 
 * Revision 1.1 2007/08/22 06:34:06 Frank *** empty log message ***
 * 
 * 
 */

package com.cdoframework.cdolib.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
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

import com.cdo.util.constants.Constants;
import com.cdo.util.resource.GlobalResource;
import com.cdoframework.cdolib.base.ObjectExt;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.data.cdo.Field;
import com.cdoframework.cdolib.data.cdo.FileField;
/**
 * 
 * @author KenelLiu
 *
 */
public abstract class CDOServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6566513350461152047L;

	private static Logger log=Logger.getLogger(CDOServlet.class);
	private final static long maxFileSize=1024*1024*50;
	// 静态对象,所有static在此声明并初始化------------------------------------------------------------------------

	// 内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------
	protected ServletConfig servletConfig;

	// 属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------

	// 引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------

	// 内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------
	protected String getRequestParameter(HttpServletRequest request,String strName)
	{
		String strValue=request.getParameter(strName);
		if(strValue==null)
		{
			strValue=request.getHeader(strName);
		}

		return strValue;
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
		String strCDORequest=null;
		Map<String, File> mapFileMap=null;
		String strTransName=null;
		try
		{
			String  serialFile=request.getHeader(Constants.CDO.HTTP_CDO_UPLOADFILE_KEY); 			
			if(serialFile!=null && serialFile.trim().equals("true")){
				//表示有文件对象需要上传 	
				mapFileMap=new HashMap<String,File>();
				strCDORequest=processUploadFile(request, mapFileMap);
			}else{
				strCDORequest=request.getParameter(Constants.CDO.HTTP_CDO_REQUEST);							
			}
			if(strCDORequest==null){
				cdoRequest=new CDO();
				strTransName=getRequestParameter(request, "strTransName");
				cdoRequest.setStringValue("strTransName",strTransName);
			}else{
				cdoRequest=CDO.fromXML(strCDORequest);	
				if(mapFileMap!=null){
					for(Iterator<Map.Entry<String, File>> iterator=mapFileMap.entrySet().iterator();iterator.hasNext();){
						Entry<String, File> entry=iterator.next();
						cdoRequest.setFileValue(entry.getKey(), entry.getValue());
					}
				}
				strTransName=cdoRequest.getStringValue("strTransName");
			}					
		}catch(Exception e){						
			log.error("error:"+e.getMessage(),e);
			outPut(response," Request Parameter Error :"+e.getMessage());
			return;						
		}	
		// 执行事务
		CDO cdoResponse=new CDO();
		Return ret=null;
		try{
			ret=handleTrans(request,response,cdoRequest,cdoResponse);
		}catch (Throwable e){
			log.error("error:"+e.getMessage(),e);
			outPut(response," Service Internal Error :"+e.getMessage());
			return; 				
		}			
		if(ret==null)
		{			
			outPut(response," Request method not found:strTransName="+strTransName);
			return;			
		}		
		// 输出结果
		try{	
			String strOutput="";
			CDO cdoOutput=new CDO();
			CDO cdoReturn=new CDO();
			cdoReturn.setIntegerValue("nCode",ret.getCode());
			cdoReturn.setStringValue("strText",ret.getText());
			cdoReturn.setStringValue("strInfo",ret.getInfo());

			cdoOutput.setCDOValue("cdoReturn",cdoReturn);
			cdoOutput.setCDOValue("cdoResponse", cdoResponse);
			strOutput=cdoOutput.toXML();			
			//TODO 可增加网页类cache  CacheUtil.handleReqCache(request, response, ret);
			outputReponse(response, strOutput,cdoResponse);
		}finally{
			cdoResponse=null;
		}		
	}
	private void outPut(HttpServletResponse response,String message) throws IOException{
		// 输出结果
		String strOutput="";
		CDO cdoOutput=new CDO();
		CDO cdoReturn=new CDO();
		cdoReturn.setIntegerValue("nCode",-1);
		cdoReturn.setStringValue("strText",message);
		cdoReturn.setStringValue("strInfo",message);
		cdoOutput.setCDOValue("cdoReturn",cdoReturn);
		cdoOutput.setCDOValue("cdoResponse",new CDO());
		strOutput=cdoOutput.toXML();
		
		response.setHeader("Cache-control","no-cache,no-store");
		outputReponse(response, strOutput,cdoOutput);
	}
	// 事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	/**
	 * 安全输出http响应
	 * @param response
	 * @param strOutput
	
	private void outputReponse(HttpServletResponse response, String strOutput) {
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out=null;
		try {
			out=response.getWriter();			
			out.write(strOutput);
			out.flush();
		} catch (Exception e) {
			log.error("wirte response errr", e);
		}finally{
			if(out != null){
				out.close();		
			}
		}
	}
 */
	/**
	 * 安全输出http响应
	 * @param response
	 * @param strOutput
	 * @throws IOException 
	 */
	private void outputReponse(HttpServletResponse response, String strOutput,CDO cdoResponse) throws IOException {
		
		//----设置header----
		File[] files=setResponseHeader(response, strOutput, cdoResponse);		
		ServletOutputStream out=null;
		InputStream inStream=null;
		try {		
			out=response.getOutputStream();			
			byte[] cdoResXml=strOutput.getBytes();
			//输出cdo xml
			out.write(cdoResXml);	
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
		} catch (Exception e) {
			log.error("wirte response errr"+e.getMessage(), e);
		}finally{
			if(inStream!=null){try{inStream.close();}catch(Exception ex){};}
			if(out != null){
				out.close();		
			}
		}
	}	
	
	private File[] setResponseHeader(HttpServletResponse response, String strOutput,CDO cdoResponse) throws UnsupportedEncodingException{
		response.setContentType("application/octet-stream;charset=UTF-8");
		response.setHeader("Accept-Ranges", "bytes");
		
		byte[] cdoResXml=strOutput.getBytes("UTF-8");
		int cdoXmlLen=cdoResXml.length;
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
    			 if(objExt.getType()==ObjectExt.FILE_TYPE){  
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
//		response.setHeader("Content-Length", ""+totalLen);  在resin http/1.1 会处理字节存在问题,不设置长度
		return listFiles.toArray(new File[listFiles.size()]);
	}
	
	// 事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------
	protected abstract Return handleTrans(HttpServletRequest request,HttpServletResponse response,CDO cdoRequest,
					CDO cdoResponse);

	// 构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public CDOServlet()
	{
		// 请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
	}

	
	protected String processUploadFile(HttpServletRequest request,Map<String,File> mapFile) throws ServletException, IOException {  
		   String strCDORequest=null;
	        try {    
	            String saveDirPath =GlobalResource.cdoConfig==null?null:GlobalResource.cdoConfig.getString(Constants.CDO.HTTP_UPLOAD_FILE_PATH);
	            String tmpDirPath =GlobalResource.cdoConfig==null?null:GlobalResource.cdoConfig.getString(Constants.CDO.TEMP_FILE_PATH);
	            long maxSize=maxFileSize;
	            if(GlobalResource.cdoConfig!=null && GlobalResource.cdoConfig.getString(Constants.CDO.UPLOAD_FILE_MAX_SIZE)!=null){
	            	maxSize=Long.parseLong(GlobalResource.cdoConfig.getString(Constants.CDO.UPLOAD_FILE_MAX_SIZE));
	            }
	            
	            if(saveDirPath==null || saveDirPath.trim().equals(""))
	            	saveDirPath = this.getServletContext().getRealPath("/cdoUploadPath");
	            if(tmpDirPath==null || tmpDirPath.trim().equals(""))
	            	tmpDirPath=System.getProperty("java.io.tmpdir");

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
	                		 strCDORequest=fileItem.getString();	              
	                	}
	                } 
	            }
	            
	        } catch(Exception e) {  
	        	log.error("解释上传文件数据失败....."+e.getMessage(), e);	              
	        }  
	        return strCDORequest;
	    }  
}
