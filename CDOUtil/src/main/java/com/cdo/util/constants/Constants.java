package com.cdo.util.constants;

/**
 * 
 * @author KenelLiu
 *
 */
public class Constants {

	//分页常量
	public static class Page{		
		public static final String PAGE_INDEX="nPageIndex";
		public static final String PAGE_SIZE="nPageSize";
		
		//-------- 每页显示最小 最大数量-----------------//
		public static final int PAGE_SIZE_MIN=10;
		public static final int PAGE_SIZE_MAX=100;
	}
	
	//--字符编码变量-------//
	public static class Encoding {
		public static final String CHARSET_UTF8="UTF-8";
		public static final String CHARSET_GBK="GBK";
		public static final String CHARSET_GBK2312="GBK2312";
		public static final String CHARSET_ISO8859="ISO-8859-1";
	}
	//byteBuffer读取字节长度
	public static class Byte{
		public final static int maxSize=2048;
		public final static int defaultSize=1024;
		public final static int minSize=512;
	}
	
	public static class CDO{
		public final static String HTTP_CDO_REQUEST="$$CDORequest$$";
		public final static String HTTP_CDO_UPLOADFILE_KEY="$CDOSerialFile$";
		
		public final static String HTTP_CDO_RESPONSE="$$CDOResponse$$";
		public final static String HTTP_UPLOAD_FILE_PATH="uploadPath";
		public final static String TEMP_FILE_PATH="tmpPath";
		public final static String UPLOAD_FILE_MAX_SIZE="uploadFileMaxSize";
		
	}
}
