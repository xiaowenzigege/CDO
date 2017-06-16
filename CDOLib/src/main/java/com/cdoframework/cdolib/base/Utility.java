/**
 * www.cdoforum.com 2007版权所有

 * $Header: /CVSData/Frank/CVSROOT/CDOForum/CDOLib/Source/com/cdoframework/cdolib/base/Utility.java,v 1.4 2008/03/12 10:30:58 Frank Exp $
 * 
 * $Log: Utility.java,v $
 * Revision 1.4  2008/03/12 10:30:58  Frank
 * *** empty log message ***
 *
 *
 */

package com.cdoframework.cdolib.base;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/*编码规则--------------------------------------------------------------
 1. 所有成员变量的命名必须要反映变量类型和用途
 2. 类名首字母一律大写
 3. 所有命名要有含义并且名符其实，并且命名主体部分尽量用一个或数个完整的
 英文单词或常用的缩写，尽量少用缩写，所有命名长度不超过30个字符，尽量
 不超过20个字符长度
 4. 每个函数或对象的声明必须放在所属区域部分，如果找不到合适的区域，可以
 创建新的区域
 5. 尽量用通过JavaDoc注释说明每个变量、属性和函数的功能，用途、入口和出口
 参数、返回值 等，以帮助阅读者理解
 6. 函数体中要加上恰当的注释，尤其是比较关键或逻辑较复杂的地方
 7. 书写格式要美观，尽量用TAB，少用或不用空格，并且代码尽量对齐
 8. 较复杂的代码段要分段书写，每段前要加上该段的简单功能描述
 9. 所有的{，}单独占一行，必要时可以在其后加上注释
 10.每个if,then,while,...,等等的内容必须在{}内，即使只有一行
 ---------------------------------------------------------------------*/

/**********************************************************************
 在此添加该类的功能描述
 **********************************************************************/
public class Utility
{

	
	static protected DecimalFormat		decFormat       =new DecimalFormat();
	private static Logger logger=Logger.getLogger(Utility.class);
	/**
	 * 检查是否有相同的字符串
	 * @param strsString
	 * @return
	 */
	public static boolean hasSameString(String[] strsString)
	{
		HashSet<String> hsString=new HashSet<String>();
		for(int i=0;i<strsString.length;i++)
		{
			if(hsString.add(strsString[i])==false)
			{
				return true;
			}
		}
		
		return false;
	}

	/**
	 * 查找字符串数组中指定字符串的位置
	 * @param strsString
	 * @param strValue
	 * @return 查不到返回-1
	 */
	public static int findString(String[] strsString,String strValue)
	{
		for(int i=0;i<strsString.length;i++)
		{
			if(strsString[i].equals(strValue)==true)
			{
				return i;
			}
		}		
		return -1;
	}

	/**
	 * 用指定连接字符连接多个字符串
	 */
	public static String connectString(char chSeperator,String[] strsString)
	{
		StringBuilder strbOutput=new StringBuilder();
		
		for(int i=0;i<strsString.length;i++)
		{
			if(i>0)
			{
				strbOutput.append(chSeperator);
			}
			
			strbOutput.append(strsString[i]);
		}
		
		return strbOutput.toString();
	}
	
	/**
	 * 使用分隔符将字符串分隔
	 * @param strSource
	 * @param chSeperator
	 * @return
	 */
	public static String[] splitString(String strSource,char chSeperator)
	{
		ArrayList<String> alOutput=new ArrayList<String>();
		
		StringBuilder strbString=new StringBuilder();
		for(int i=0;i<strSource.length();i++)
		{
			char chChar=strSource.charAt(i);
			if(chChar==chSeperator)
			{
				alOutput.add(strbString.toString());

				strbString=new StringBuilder();
			}
			else
			{
				strbString.append(chChar);
			}
		}
		alOutput.add(strbString.toString());
		
		return alOutput.toArray(new String[alOutput.size()]);
	}
	
	/**
	 * 使用分隔符将字符串分隔
	 * @param strSource
	 * @param chSeperator
	 * @param bRepeated 是否重复分隔符当作一个
	 * @return
	 */
	public static String[] splitString(String strSource,char chSeperator,boolean bRepeated)
	{
		ArrayList<String> alOutput=new ArrayList<String>();
		
		StringBuilder strbString=new StringBuilder();
		char chLastChar='\0';
		for(int i=0;i<strSource.length();i++)
		{
			char chChar=strSource.charAt(i);
			if(chChar==chSeperator)
			{
				if(bRepeated==true)
				{
					if(chChar!=chLastChar)
					{
						alOutput.add(strbString.toString());
	
						strbString=new StringBuilder();
					}
				}
				else
				{
					alOutput.add(strbString.toString());

					strbString=new StringBuilder();
				}
			}
			else
			{
				strbString.append(chChar);
			}
			chLastChar=chChar;
		}
		alOutput.add(strbString.toString());

		return  alOutput.toArray(new String[alOutput.size()]);
	}

	/**
	 * 将指定字符串的每一行格式化成一个字符串
	 * 结果串中不包含回车符和换行符
	 * @param str
	 * @return
	 */
	public static String[] readLine(String str)
	{		
		List<String> list = new ArrayList<String>(10);
		BufferedReader reader = new BufferedReader(new StringReader(str));
		String strContent = null;
		try
		{
			while ((strContent=reader.readLine()) !=null)
			{
				list.add(strContent);
			}
		}
		catch(Exception e)
		{
			return null;
		}	
		finally
		{
			try
			{
				reader.close();
			}
			catch(IOException e)
			{
			}
		}

		return list.toArray(new String[list.size()]);		
	}
	/**
	 * 字符串编码转换
	 * @param strText
	 * @param strFromCoding
	 * @param strToCoding
	 * @return
	 */
	public static String encodingText(String strText,String strFromCoding,String strToCoding)
	{
		if(strFromCoding.equalsIgnoreCase(strToCoding))
		{
			return strText;
		}
		try
		{
			return new String(strText.getBytes(strFromCoding),strToCoding);
		}
		catch(Exception e)
		{
			return null;
		}
	}

	/**
	 * NULL转换成空字符串
	 * @param strValue
	 * @return
	 */
	public static String noNull(String strValue)
	{
		if(strValue==null)
		{
			return "";
		}
		
		return strValue;
	}

	/**
	 * 读取文本文件的内容
	 * @param strFile
	 * @param strCoding
	 * @return
	 */
	public static String readTextFile(String strFile)
	{
		FileInputStream stream=null;
		InputStreamReader reader=null;
		try
		{
			stream=new java.io.FileInputStream(strFile);
			reader=new InputStreamReader(stream);

			char[] chsData=new char[(int)stream.getChannel().size()];
			int nReadSize=reader.read(chsData,0,chsData.length);
			
			return new String(chsData,0,nReadSize);
		}
		catch(Exception e)
		{
			return null;
		}
		finally
		{
			try
			{
				if(reader!=null)
				{
					reader.close();
				}
				if(stream!=null)
				{
				stream.close();
				}
			}
			catch(Exception e)
			{
				
			}
		}
	}

	/**
	 * 读取文本文件的内容
	 * @param strFile
	 * @param strCoding
	 * @return
	 */
	public static String readTextFile(String strFile,String strCoding)
	{
		FileInputStream stream=null;
		InputStreamReader reader=null;
		try
		{
			stream=new java.io.FileInputStream(strFile);
			reader=new InputStreamReader(stream,strCoding);
		
			char[] chsData=new char[(int)stream.getChannel().size()];
			int nReadSize=reader.read(chsData,0,chsData.length);	
			
			return new String(chsData,0,nReadSize);
		}
		catch(Exception e)
		{
			return null;
		}
		finally
		{
			try
			{
				if(reader!=null)
				{
					reader.close();
				}
				if(stream!=null)
				{
				stream.close();
				}
			}
			catch(Exception e)
			{
				
			}
		}
	}

	/**
	 * 读取文本Source的内容
	 * @param strFile
	 * @param strCoding
	 * @return
	 */
	public static String readTextResource(String strFile,String strCoding)
	{
		return readTextResource(strFile, strCoding, true);
	}

	/**
	 * 读取文本Source的内容
	 * @param strFile
	 * @param strCoding
	 * @return
	 */
	public static String readTextResource(String strFile,String strCoding,boolean isClassPath)
	{
		InputStream stream=null;
		InputStreamReader reader=null;
		try
		{
			if(isClassPath){
				stream=Resources.getResourceAsStream(strFile);
			}else{
				stream=new FileInputStream(strFile);
			}
			reader=new InputStreamReader(stream,strCoding);

			StringBuilder strbContent=new StringBuilder();
			char[] chsData=new char[1024];// Aaron change 10240 to 1024 on 2010-08-17. 10240 might cause OOM
			while(true)
			{
				int nReadSize=reader.read(chsData,0,chsData.length);
				if(nReadSize<=0)
				{
					break;
				}
				strbContent.append(new String(chsData,0,nReadSize));
			}
			
			return strbContent.toString();
		}
		catch(Exception e)
		{
			logger.error("strFile:"+strFile+",strCoding:"+strCoding+";"+e.getMessage());			
			return null;
		}
		finally
		{
			try
			{
				if(reader!=null)
				{
					reader.close();
				}
			}
			catch(Exception e)
			{
			}
			if(stream!=null)
			{
				try
				{
					stream.close();
				}
				catch(Exception e)
				{
					
				}
			}
		}
	}
	
	public static String readTextResource(String strFile)
	{
		InputStream stream=null;
		InputStreamReader reader=null;
		try
		{
			stream=Resources.getResourceAsStream(strFile);
			reader=new InputStreamReader(stream);

			StringBuilder strbContent=new StringBuilder();
			char[] chsData=new char[10240];// Aaron change 10240 to 1024 on 2010-08-17. 10240 might cause OOM
			while(true)
			{
				int nReadSize=reader.read(chsData,0,chsData.length);
				if(nReadSize<=0)
				{
					break;
				}
				strbContent.append(new String(chsData,0,nReadSize));
			}
			
			return strbContent.toString();
		}
		catch(Exception e)
		{
			return null;
		}
		finally
		{
			try
			{
				if(reader!=null)
				{
					reader.close();
				}
			}
			catch(Exception e)
			{
			}
			if(stream!=null)
			{
				try
				{
					stream.close();
				}
				catch(Exception e)
				{
					
				}
			}
		}
	}	
	/**
	 * 检查一个对象是否是一个类或接口的实例
	 * @param obj
	 * @param strClassName
	 * @return
	 */
	public static boolean IsInstanceOf(Object obj,String strClassName)
	{
		try
		{
			return Class.forName(strClassName).isInstance(obj);
		}
		catch(Exception e)
		{
			return false;
		}
	}

	/**
	 * 读取文件所有数据
	 * @param strFile
	 * @return
	 */
	public static byte[] readFile(String strFile)
	{
		FileInputStream stream=null;
		try
		{
			stream=new java.io.FileInputStream(strFile);
			
			byte[] bysData=new byte[(int)stream.getChannel().size()];
			stream.read(bysData);
			
			return bysData;
		}
		catch(Exception e)
		{
			return null;
		}
		finally
		{
			try
			{
				if(stream!=null)
				{
					stream.close();
				}
			}
			catch(Exception e)
			{
			}
		}
	}

	/**
	 * 将所有数据写入文件
	 * @param strFile
	 * @return
	 */
	public static void writeFile(String strFile,byte[] bysData)
	{
		FileOutputStream stream=null;
		try
		{
			stream=new FileOutputStream(strFile);
			
			stream.write(bysData,0,bysData.length);
		}
		catch(Exception e)
		{
			return;
		}
		finally
		{
			try
			{
				if(stream!=null)
				{
					stream.close();
				}
			}
			catch(Exception e)
			{
			}
		}
	}

	/**
	 * 将所有数据写入文件
	 * @param strFile
	 * @return
	 */
	public static void writeFile(File file,byte[] bysData)
	{
		FileOutputStream stream=null;
		try
		{
			stream=new FileOutputStream(file);
			
			stream.write(bysData,0,bysData.length);
		}
		catch(Exception e)
		{
			return;
		}
		finally
		{
			try
			{
				if(stream!=null)
				{
					stream.close();
				}
			}
			catch(Exception e)
			{
			}
		}
	}
	
	public static void closeStream(InputStream stream)
	{
		if(stream!=null)
		{
			try
			{
				stream.close();
			}
			catch(Exception e)
			{
			}
		}
	}
	
	public static void closeStream(OutputStream stream)
	{
		if(stream!=null)
		{
			try
			{
				stream.close();
			}
			catch(Exception e)
			{
			}
		}
	}
	public static void close(Socket socket)
	{
		if(socket!=null)
		{
			try
			{
				socket.close();
			}
			catch(Exception e)
			{
			}
		}
	}

	/**
	 * 将一个文件从一个位置移动到另一个位置
	 * @param strFromFile 原始文件
	 * @param strToFile 目标位置
	 * @return 移动结果
	 */
	static public Return moveFile(String strFromFile,String strToFile)
	{
		File fileFrom	=new File(strFromFile);
		File fileTo		=new File(strToFile);

		if(fileFrom.renameTo(fileTo)==false)
		{
			return Return.valueOf(-1,"Move file failed");
		}

		return Return.OK;
	}

	static public String getHostName()
	{
        String strHostName=null;

        try
        {
	        InetAddress netAddress = InetAddress.getLocalHost();
	        //String PCName= IPAdd.getHostAddress();
	        strHostName=netAddress.getHostName();
        }
        catch(Exception e)
        {
        }
        return strHostName;
	}

	/**
	 * 取本机IP
	 * <br>注意:本方法可能返回127.0.0.1
	 * @return
	 */
	static public String getIPAddress()
	{
        String strIPAddress=null;

        try
        {
	        InetAddress netAddress = InetAddress.getLocalHost();
	        strIPAddress	=netAddress.getHostAddress();
        }
        catch(Exception e)
        {
        	
        }
        return strIPAddress;
	}
	/**
	 * 取本机IP
	 * <br>用InetAddress类有可能会取到127的地址,所以当取到127的地址后使用NetworkInterface类
	 * <br>在非windows操作系统中,推荐使用本方法,而不使用本类中的<getIPAddress()>方法
	 * 
	 * @return 失败返回NULL,成功返回IPV4地址
	 * 
	 */
	static public String getLocalIp()
	{
		String strIp = null;
		
		//首先偿试用InetAddress
		try
		{
			InetAddress netAddress=InetAddress.getLocalHost();
			strIp	=netAddress.getHostAddress();
			if(!strIp.startsWith("127"))
			{
				return strIp;	
			}			
		}
		catch(UnknownHostException e1)
		{			
		}
		
		//未找到非127的地址
	    Enumeration<NetworkInterface> netInterfaces = null;
		try
		{
			netInterfaces=NetworkInterface.getNetworkInterfaces();
		}
		catch(SocketException e)
		{	//使用NetworkInterface失败,只能返回127.0.0.1的地址了	       
			return strIp;
		}
	   InetAddress ip = null;
	   while(netInterfaces.hasMoreElements())
	   {
			NetworkInterface ni=(NetworkInterface)netInterfaces.nextElement();	
			Enumeration<InetAddress> enumInetAddress = ni.getInetAddresses();
			while(enumInetAddress.hasMoreElements())
			{
			    ip=(InetAddress) enumInetAddress.nextElement();
			    strIp = ip.getHostAddress();
			    
			    //过虑掉127和localhost
			    if( strIp.startsWith("127")||strIp.startsWith("l")||strIp.startsWith("L"))
			    {
			    	continue;
			    }	    
			    return strIp;
			}
	   }
	   
	   //还没有取到,返回空或127.0.0.1
	   return strIp;	   
	}

	public static byte[] hexStringToBytes(String strHexString)
	{
		String strDigital="0123456789ABCDEF";
	    
		byte[] bytes= new byte[strHexString.length()/2];
	    int temp;
	    for(int i=0;i<bytes.length;i++){
	      temp=strDigital.indexOf(strHexString.substring(2*i,2*i+1))*16;
	      temp+=strDigital.indexOf(strHexString.substring(2*i+1,2*i+2));
	      bytes[i]=(byte)(temp&0xff);
	    }

	    return bytes;
	}

	public static String bytesToHexString(byte[] bysBytes)
	{
		String strDigital="0123456789ABCDEF";
		
	    StringBuilder sb=new StringBuilder("");
	    byte[] bs = bysBytes;
	    int bit;
	    
	    for(int i=0;i<bs.length;i++)
	    {
	      bit=(bs[i]&0x0f0)>>4;
	      sb.append(strDigital.substring(bit,bit+1));
	      bit=bs[i]&0x0f;
	      sb.append(strDigital.substring(bit,bit+1));
	    }

	    return sb.toString();
	}

	public static void closeReader(Reader reader)
	{
		if(reader==null)
		{
			return;
		}
		try
		{
			reader.close();
		}
		catch(Exception e)
		{
		}
	}

	public static String getExceptionMessage(Exception e)
	{
		StringBuilder strbMessage=new StringBuilder(1024);

		StackTraceElement[] ste=e.getStackTrace();
		strbMessage.append(e.getMessage());
		for(int i=0;i<ste.length;i++)
		{
			strbMessage.append("\r\n");
			strbMessage.append(ste[i].toString());
		}

		return strbMessage.toString();
	}

	public static String makeSameCharString(char ch,int nLength)
	{
		char[] chsOutput=new char[nLength];
		for(int i=0;i<nLength;i++)
		{
			chsOutput[i]=ch;
		}
		
		return new String(chsOutput);
	}

	public static String format(Object obj,String strFormat) throws Exception
	{
		if(Utility.IsInstanceOf(obj,"java.lang.String")==true)
		{//String Format,Format is like "Length|FillChar|0 or 1",最后一个值0表示在前面补,1表示在后面补
			String strObj=(String)obj;
			if(strFormat==null || strFormat.length()==0)
			{
				return strObj;
			}
			
			//分析得到格式
			String[] strsFormatItem=Utility.splitString(strFormat,',');
			int nLength=0;
			char chFill=' ';
			int nFillAt=0;
			if(strsFormatItem.length>=1)
			{
				nLength=Integer.parseInt(strsFormatItem[0]);
			}
			if(strsFormatItem.length>=2)
			{
				if(strsFormatItem[1].length()!=1)
				{
					throw new Exception("Unsupported format: "+strFormat);
				}
				chFill=strsFormatItem[1].charAt(0);
			}
			if(strsFormatItem.length>=3)
			{
				nFillAt=Integer.parseInt(strsFormatItem[2]);
				if(nFillAt!=0 && nFillAt!=1)
				{
					throw new Exception("Unsupported format: "+strFormat);
				}
			}
			else
			{
				throw new Exception("Unsupported format: "+strFormat);
			}
			
			//生成输出
			if(strObj.length()>=nLength)
			{
				return strObj;
			}
			
			String strOutput="";
			if(nFillAt==0)
			{
				strOutput=makeSameCharString(chFill,nLength-strObj.length())+strObj;
			}
			else
			{
				strOutput=strObj+makeSameCharString(chFill,nLength-strObj.length());
			}
			
			return strOutput;
		}
		else if(Utility.IsInstanceOf(obj,"java.lang.Byte")==true || Utility.IsInstanceOf(obj,"java.lang.Integer")==true
			|| Utility.IsInstanceOf(obj,"java.lang.Long")==true)
		{//Integer Format,Format is like "Length|FillChar"
			String strObj=obj.toString();
			if(strFormat==null || strFormat.length()==0)
			{
				return strObj;
			}

			//分析得到格式
			String[] strsFormatItem=Utility.splitString(strFormat,',');
			int nLength=0;
			char chFill=' ';
			int nFillAt=0;
			if(strsFormatItem.length>=1)
			{
				nLength=Integer.parseInt(strsFormatItem[0]);
			}
			if(strsFormatItem.length>=2)
			{
				if(strsFormatItem[1].length()!=1)
				{
					throw new Exception("Unsupported format: "+strFormat);
				}
				chFill=strsFormatItem[1].charAt(0);
			}
			if(strsFormatItem.length>=3)
			{
				nFillAt=Integer.parseInt(strsFormatItem[2]);
				if(nFillAt!=0 && nFillAt!=1)
				{
					throw new Exception("Unsupported format: "+strFormat);
				}
			}
			else
			{
				throw new Exception("Unsupported format: "+strFormat);
			}
			
			//生成输出
			if(strObj.length()>=nLength)
			{
				return strObj;
			}
			
			String strOutput="";
			if(nFillAt==0)
			{
				strOutput=makeSameCharString(chFill,nLength-strObj.length())+strObj;
			}
			else
			{
				strOutput=strObj+makeSameCharString(chFill,nLength-strObj.length());
			}
			
			return strOutput;
		}
		else if(Utility.IsInstanceOf(obj,"EZLib.Base.EZDateTime")==true)
		{//DateTime Format,Format is same as java format
			return ((DateTime)obj).toString(strFormat);
		}
		else
		{//Unsupported
			throw new Exception("Unsupported format: "+strFormat);
		}
	}

	public static String formatArray(Object objArray,int nIndex,String strFormat) throws Exception
	{
		if(objArray.getClass().isArray()==false)
		{
			throw new Exception("Object not an array");
		}
		
		Object obj=null;
		if(Utility.IsInstanceOf(objArray,"[B")==true)
		{
			byte[] bysObj=(byte[])objArray;
			obj=new Byte(bysObj[nIndex]);
		}
		else if(Utility.IsInstanceOf(objArray,"[I")==true)
		{
			int[] nsObj=(int[])objArray;
			obj=new Integer(nsObj[nIndex]);
		}
		else if(Utility.IsInstanceOf(objArray,"[J")==true)
		{
			long[] lsObj=(long[])objArray;
			obj=new Long(lsObj[nIndex]);
		}
		else if(Utility.IsInstanceOf(objArray,"[Ljava.lang.String")==true)
		{
			String[] strsObj=(String[])objArray;
			obj=strsObj[nIndex];
		}
		else if(Utility.IsInstanceOf(objArray,"[LEZLib.Base.EZDateTime")==true)
		{
			DateTime[] dtsObj=(DateTime[])objArray;
			obj=dtsObj[nIndex];
		}
		else
		{
			throw new Exception("Unsupported array: "+objArray.getClass().getName());
		}
		
		if(Utility.IsInstanceOf(obj,"java.lang.String")==true)
		{//String Format,Format is like "Length|FillChar|0 or 1"
			String strObj=(String)obj;
			if(strFormat==null || strFormat.length()==0)
			{
				return strObj;
			}
			
			//分析得到格式
			String[] strsFormatItem=Utility.splitString(strFormat,',');
			int nLength=0;
			char chFill=' ';
			int nFillAt=0;
			if(strsFormatItem.length>=1)
			{
				nLength=Integer.parseInt(strsFormatItem[0]);
			}
			if(strsFormatItem.length>=2)
			{
				if(strsFormatItem[1].length()!=1)
				{
					throw new Exception("Unsupported format: "+strFormat);
				}
				chFill=strsFormatItem[1].charAt(0);
			}
			if(strsFormatItem.length>=3)
			{
				nFillAt=Integer.parseInt(strsFormatItem[2]);
				if(nFillAt!=0 && nFillAt!=1)
				{
					throw new Exception("Unsupported format: "+strFormat);
				}
			}
			else
			{
				throw new Exception("Unsupported format: "+strFormat);
			}
			
			//生成输出
			if(strObj.length()>=nLength)
			{
				return strObj;
			}
			
			String strOutput="";
			if(nFillAt==0)
			{
				strOutput=makeSameCharString(chFill,nLength-strObj.length())+strObj;
			}
			else
			{
				strOutput=strObj+makeSameCharString(chFill,nLength-strObj.length());
			}
			
			return strOutput;
		}
		else if(Utility.IsInstanceOf(obj,"java.lang.Byte")==true || Utility.IsInstanceOf(obj,"java.lang.Integer")==true
			|| Utility.IsInstanceOf(obj,"java.lang.Long")==true)
		{//Integer Format,Format is like "Length|FillChar"
			String strObj=obj.toString();
			if(strFormat==null || strFormat.length()==0)
			{
				return strObj;
			}

			//分析得到格式
			String[] strsFormatItem=Utility.splitString(strFormat,',');
			int nLength=0;
			char chFill=' ';
			int nFillAt=0;
			if(strsFormatItem.length>=1)
			{
				nLength=Integer.parseInt(strsFormatItem[0]);
			}
			if(strsFormatItem.length>=2)
			{
				if(strsFormatItem[1].length()!=1)
				{
					throw new Exception("Unsupported format: "+strFormat);
				}
				chFill=strsFormatItem[1].charAt(0);
			}
			if(strsFormatItem.length>=3)
			{
				nFillAt=Integer.parseInt(strsFormatItem[2]);
				if(nFillAt!=0 && nFillAt!=1)
				{
					throw new Exception("Unsupported format: "+strFormat);
				}
			}
			else
			{
				throw new Exception("Unsupported format: "+strFormat);
			}
			
			//生成输出
			if(strObj.length()>=nLength)
			{
				return strObj;
			}
			
			String strOutput="";
			if(nFillAt==0)
			{
				strOutput=makeSameCharString(chFill,nLength-strObj.length())+strObj;
			}
			else
			{
				strOutput=strObj+makeSameCharString(chFill,nLength-strObj.length());
			}
			
			return strOutput;
		}
		else if(Utility.IsInstanceOf(obj,"EZLib.Base.EZDateTime")==true)
		{//DateTime Format,Format is same as java format
			return ((DateTime)obj).toString(strFormat);
		}
		else
		{//Unsupported
			throw new Exception("Unsupported format: "+strFormat);
		}
	}

	/**
	 * 产生临时文件,并记录数据,返回文件名
	 * @param strPrefix
	 * @param strPostfix
	 * @param bysContent
	 * @return
	 */
	public static String writeTempFile(String strPrefix,String strPostfix,byte[] bysContent)
	{
		File fileTemp=null;

		try
		{
			fileTemp=File.createTempFile(strPrefix,strPostfix);
			Utility.writeFile(fileTemp,bysContent);
		}
		catch(Exception e)
		{
			return null;
		}

		return fileTemp.getAbsolutePath();
	}

	public static boolean isLeapYear(int nYear)
	{
		if(nYear%100==0)
		{
			if(nYear%400==0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			if(nYear%4==0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}

	public static boolean checkDate(String strValue)
	{
		int nLength=strValue.length();
		if(nLength==0)
		{
			return true;
		}
		if(nLength!=10)
		{
			return false;
		}

		if(strValue.charAt(4)!='-' && strValue.charAt(7)!='-')
		{
			return false;
		}
		String strYear=strValue.substring(0,4);
		String strMonth=strValue.substring(5,7);
		String strDay=strValue.substring(8);
		if(Utility.isIntText(strYear)==false || Utility.isIntText(strMonth)==false || Utility.isIntText(strDay)==false)
		{
			return false;
		}
		int nMonth=Integer.parseInt(strMonth);
		if(nMonth<1 || nMonth>12)
		{
			return false;
		}
		int nDay=Integer.parseInt(strDay);
		if(nDay<1 || nDay>31)
		{
			return false;
		}
		if(nMonth==1 || nMonth==3 || nMonth==5 || nMonth==7 || nMonth==8 || nMonth==10 || nMonth==12)			
		{//大月
			return true;
		}
		if((nMonth==4 || nMonth==6 || nMonth==9 || nMonth==11))
		{//小月
			if(nDay>30)
			{
				return false;
			}
			else
			{
				return true;
			}
		}

		int nYear=Integer.parseInt(strYear);
		if(Utility.isLeapYear(nYear)==false && nDay>28)
		{
			return false;
		}
		if(Utility.isLeapYear(nYear) && nDay>29)
		{
			return false;
		}
		return true;
	}

	public static boolean checkTime(String strValue)
	{
		int nLength=strValue.length();
		if(nLength==0)
		{
			return true;
		}
		if(nLength!=8)
		{
			return false;
		}

		if(strValue.charAt(2)!=':' && strValue.charAt(5)!=':')
		{
			return false;
		}
		String strHour=strValue.substring(0,2);
		String strMinute=strValue.substring(3,5);
		String strSecond=strValue.substring(6);
		if(Utility.isIntText(strHour)==false || Utility.isIntText(strMinute)==false || Utility.isIntText(strSecond)==false)
		{
			return false;
		}
		int nHour=Integer.parseInt(strHour);
		if(nHour<0 || nHour>23)
		{
			return false;
		}
		int nMinute=Integer.parseInt(strMinute);
		if(nMinute<0 || nMinute>59)
		{
			return false;
		}
		int nSecond=Integer.parseInt(strSecond);
		if(nSecond<0 || nSecond>59)
		{
			return false;
		}

		return true;
	}

	public static boolean checkDateTime(String strValue)
	{
		if(strValue.length()!=19)
		{
			return false;
		}
		if(strValue.charAt(10)!=' ')
		{
			return false;
		}
		
		String strDate=strValue.substring(0,10);
		if(checkDate(strDate)==false)
		{
			return false;
		}
		String strTime=strValue.substring(11);
		if(checkTime(strTime)==false)
		{
			return false;
		}
		
		return true;
	}

	public static boolean isDateArray(String[] strsDate)
	{
		if(strsDate==null)
		{
			return false;
		}
		for(int i=0;i<strsDate.length;i++)
		{
			if(strsDate[i].matches("([0-9]{4}-[0-9]{2}-[0-9]{2})?")==false)
			{
				return false;
			}
		}
		
		return true;
	}

	public static boolean isTimeArray(String[] strsTime)
	{
		if(strsTime==null)
		{
			return false;
		}
		for(int i=0;i<strsTime.length;i++)
		{
			if(strsTime[i].matches("([0-9]{2}:[0-9]{2}:[0-9]{2})?")==false)
			{
				return false;
			}
		}
		
		return true;
	}

	public static boolean isDateTimeArray(String[] strsDateTime)
	{
		if(strsDateTime==null)
		{
			return false;
		}
		for(int i=0;i<strsDateTime.length;i++)
		{
			if(strsDateTime[i].matches("([0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2})?")==false)
			{
				return false;
			}
		}
		
		return true;
	}

	/**
	 * 查找到 nIndex 位置的字符的匹配字符
	 * @param nIndex
	 * @param strText
	 * @return
	 */
	public static int findMatchedChar(int nIndex,String strText)
	{
		if(nIndex<0)
		{
			return -1;
		}
		
		char[] chsText=strText.toCharArray();
		
		char chChar=chsText[nIndex];
		int nCount=0;
		int nStartIndex=-1;
		int nEndIndex=-1;

		char chFind=' ';
		switch(chChar)
		{
			case '(':
				chFind=')';
				break;
			case '{':
				chFind='}';
				break;
			case '[':
				chFind=']';
				break;
			case ')':
				chFind='(';
				break;
			case '}':
				chFind='{';
				break;
			case ']':
				chFind='[';
				break;
			default:
				return -1;
		}

		int nLength=chsText.length;
		switch(chChar)
		{
			case '(':
			case '{':
			case '[':
				for(int i=nIndex+1;i<nLength;i++)
				{
					char ch=chsText[i];
					
					if(ch==chChar)
					{
						nCount++;
					}
					else if(ch==chFind)
					{
						if(nCount==0)
						{
							nEndIndex=i;
							break;
						}
						else
						{
							nCount--;
						}
					}
				}
				return nEndIndex;
			case ')':
			case '}':
			case ']':
				for(int i=nIndex-1;i>=0;i--)
				{
					char ch=chsText[i];
					
					if(ch==chChar)
					{
						nCount++;
					}
					else if(ch==chFind)
					{
						if(nCount==0)
						{
							nStartIndex=i;
							break;
						}
						else
						{
							nCount--;
						}
					}
				}
				return nStartIndex;
			default:
				return -1;
		}
	}

	/**
	 * 查找到 nIndex 位置的字符的匹配字符
	 * @param nIndex
	 * @param strText
	 * @return
	 */
	public static int findMatchedChar(int nIndex,char[] chsText)
	{
		if(nIndex<0)
		{
			return -1;
		}
		
		char chChar=chsText[nIndex];
		int nCount=0;
		int nStartIndex=-1;
		int nEndIndex=-1;

		char chFind=' ';
		switch(chChar)
		{
			case '(':
				chFind=')';
				break;
			case '{':
				chFind='}';
				break;
			case '[':
				chFind=']';
				break;
			case ')':
				chFind='(';
				break;
			case '}':
				chFind='{';
				break;
			case ']':
				chFind='[';
				break;
			default:
				return -1;
		}

		int nLength=chsText.length;
		switch(chChar)
		{
			case '(':
			case '{':
			case '[':
				for(int i=nIndex+1;i<nLength;i++)
				{
					char ch=chsText[i];
					
					if(ch==chChar)
					{
						nCount++;
					}
					else if(ch==chFind)
					{
						if(nCount==0)
						{
							nEndIndex=i;
							break;
						}
						else
						{
							nCount--;
						}
					}
				}
				return nEndIndex;
			case ')':
			case '}':
			case ']':
				for(int i=nIndex-1;i>=0;i--)
				{
					char ch=chsText[i];
					
					if(ch==chChar)
					{
						nCount++;
					}
					else if(ch==chFind)
					{
						if(nCount==0)
						{
							nStartIndex=i;
							break;
						}
						else
						{
							nCount--;
						}
					}
				}
				return nStartIndex;
			default:
				return -1;
		}
	}

	public static String[] getDirFileList(String strDirOrFile)
	{
		java.io.File file=null;
		
		try
		{
			file=new File(strDirOrFile);
			String[] strsFile=null;
			if(file.isFile()==true)
			{
				strsFile=new String[1];
				strsFile[0]=strDirOrFile;
				return strsFile;
			}
			File[] filesList=file.listFiles();
			strsFile=new String[filesList.length];
			for(int i=0;i<filesList.length;i++)
			{
				String strFile=filesList[i].getAbsolutePath();
				strsFile[i]=strFile;
			}
			
			return strsFile;
		}
		catch(Exception e)
		{
			return null;
		}
	}

	public static String mapToString(Map<String,Object> map)
	{
		Iterator<String> iterator = map.keySet().iterator();
		StringBuilder sb = new StringBuilder();
		String key = null;
		String value = null;
		while(iterator.hasNext()) {
			key = (String)iterator.next();
			value = map.get(key).toString();
			sb.append(key).append("=").append(value).append(";");
		}
		return sb.toString();
	}
	
	public static int getSecondOfDay()
	{
		Calendar cal = Calendar.getInstance();
		int nReturn = cal.get(Calendar.SECOND);
		nReturn += cal.get(Calendar.MINUTE)*60;
		nReturn += cal.get(Calendar.HOUR_OF_DAY)*3600;
		return nReturn;
	}
	
	public static int getWeekDay()
	{
		Calendar cal = Calendar.getInstance();
		int nReturn = cal.get(Calendar.DAY_OF_WEEK);
		return nReturn-1;
	}
	
	public static String formatIPV4(String strIp)
	{
		if(strIp==null)
		{
			return null;
		}
		String[] strs = strIp.split("\\.");
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<strs.length;i++)
		{
			if(i>0)
			{
				sb.append(".");
			}
			int len = strs[i].length();
			if(len==1)
			{
				sb.append("00");
			}
			else if(len==2)
			{
				sb.append("0");
			}
			sb.append(strs[i]);				
		}
		return sb.toString();
	}
	/**
	 * 从一行正文中取得第一个单词
	 * @param strText 正文
	 * @param strWord 取得的单词
	 * @return 该单词的起始位置，如果没有取到，则返回0
	 */
	static public int getFirstWord(String strText,StringBuilder strWord)
	{
		strWord.setLength(0);

		int nIndex	=0;
		for(int i=0;i<strText.length();i++)
		{
			char chChar	=strText.charAt(i);

			if(chChar==' ' || chChar=='\t')
			{
				if(strWord.length()>0)
				{
					break;
				}
				nIndex++;
			}
			else
			{
				strWord.append(chChar);
			}
		}

		if(strWord.length()==0)
		{
			nIndex	=0;
		}

		return nIndex;
	}
	/**
	 * 取指定字符串的子串
	 * @param strSource 源字符串
	 * @param nIndex 字串的起始位置
	 * @param nLength 字串的长度
	 * @return 输出的子串，可能为""
	 */
	static public String subStr(String strSource,int nIndex,int nLength)
	{
		int nSize   =strSource.length();
		if(nIndex>=nSize)
		{
			return "";
		}
		if(nIndex+nLength>=nSize)
		{
			return strSource.substring(nIndex);
		}
		return strSource.substring(nIndex,nIndex+nLength);
	}
	/**
	 * 检查一个字符串是否为数字字符串，即全部由0－9组成
	 * @param strText 待检查的字符串
	 * @return 是:true,否:false
	 */
	static public boolean isIntText(String strText)
	{
		strText	=strText.trim();
		if(strText.length()==0)
		{
			return false;
		}

		for(int i=0;i<strText.length();i++)
		{
			if(strText.charAt(i)<'0' || strText.charAt(i)>'9')
			{
				return false;
			}
		}

		return true;
	}
	/**
	 * 检查一个字符串是否为数字字符串，即全部由0－9组成
	 * @param strText 待检查的字符串
	 * @return 是:true,否:false
	 */
	static public boolean isIntTextSet(String strText)
	{
		strText	=strText.trim();
		if(strText.length()==0)
		{
			return false;
		}

		for(int i=0;i<strText.length();i++)
		{
			if((strText.charAt(i)<'0' || strText.charAt(i)>'9')&&strText.charAt(i)!=' ')
			{
				return false;
			}
		}

		return true;
	}
	static public String intToString(long nValue)
	{
		Long intTemp	=new Long(nValue);
		return intTemp.toString();
	}
	static public String intToString(long nValue,int nLength)
	{
		StringBuilder		strbFormat		=new StringBuilder(32);
		for(int i=0;i<nLength;i++)
		{
			strbFormat.append('0');
		}

		synchronized(decFormat)
		{
			decFormat.applyPattern(strbFormat.toString());
			return decFormat.format(nValue);
		}

	}

	public static boolean isNumberText(String strText,int nDigitCount)
	{
		if(strText.length()==0)
		{
			return false;
		}

		int nDotCount=0;
//		int nDotPos=strText.length();
		for(int i=0;i<strText.length();i++)
		{
			if(strText.charAt(i)=='.')
			{
				if(nDotCount>0)
				{
					return false;
				}
				if(i+3<strText.length())
				{
					return false;
				}
				nDotCount++;
//				nDotPos	=i;
			}
			else if(strText.charAt(i)<'0' || strText.charAt(i)>'9')
			{
				return false;
			}
		}

		return true;
	}

	public static boolean isNumberText(String strText)
	{
		if(strText.length()==0)
		{
			return false;
		}

		for(int i=0;i<strText.length();i++)
		{
			if(strText.charAt(i)<'0' || strText.charAt(i)>'9')
			{
				return false;
			}
		}

		return true;
	}

	public static long numberTextToInteger(String strText,int nDigitCount) throws Exception
	{
		if(strText.length()==0)
		{
			throw new Exception("");
		}

		long lValue	=0;
		int nDotCount=0;
		int nDotPos=strText.length();
		for(int i=0;i<strText.length();i++)
		{
			if(strText.charAt(i)=='.')
			{
				if(nDotCount>0)
				{
					throw new Exception("");
				}
				if(i+nDigitCount+1<strText.length())
				{
					throw new Exception("");
				}
				nDotCount++;
				nDotPos	=i;
			}
			else if(strText.charAt(i)<'0' || strText.charAt(i)>'9')
			{
				throw new Exception("");
			}
			else
			{
				if(nDotCount>0 && i-nDotPos>nDigitCount)
				{
					throw new Exception("");
				}
				lValue=lValue*10+(strText.charAt(i)-'0');
			}
		}
		if(nDotCount==0)
		{
			for(int i=0;i<nDigitCount;i++)
			{
				lValue*=10;
			}
			return lValue;
		}

		for(int i=0;i<nDigitCount-(strText.length()-nDotPos-1);i++)
		{
			lValue*=10;
		}

		return lValue;
	}

	public static String integerToNumberText(long lValue,int nDigitCount)
	{
		String strDigit	="";
		long lTemp	=lValue;
		for(int i=0;i<nDigitCount;i++)
		{
			if(lTemp>=0)
			{
				strDigit=""+lTemp%10+strDigit;
			}
			else
			{
				strDigit=""+(-lTemp%10)+strDigit;
			}
			lTemp/=10;
		}

		if(strDigit.length()>0)
		{
			if(lTemp==0&&lValue<0)
			{
				return '-'+lTemp+'.'+strDigit;

			}
			else
			{
				return ""+lTemp+'.'+strDigit;
			}
		}
		else
		{
			return ""+lTemp+strDigit;
		}
	}

	public static Serializable deepClone(Serializable obj)
	{
		Serializable objOutput=null;
		byte[] bysObject=null;

		//将对象输出到byte[]
		ObjectOutputStream out =null; 
		java.io.ByteArrayOutputStream streamOutput=new ByteArrayOutputStream();
		try
		{ 
			out = new ObjectOutputStream(streamOutput); 
			out.writeObject(obj); 
			bysObject=streamOutput.toByteArray();
		} 
		catch (Exception e)
		{
			return null;
		}
		finally
		{
			try
			{
				out.close();
			}
			catch(Exception e)
			{
			}
			try
			{
				streamOutput.close();
			}
			catch(Exception ex)
			{
			}
		}

		//根据byte[]生成InputStream
		java.io.ByteArrayInputStream streamInput=new ByteArrayInputStream(bysObject);
		
		//生成新对象
		ObjectInputStream in =null;; 
		try
		{ 
			in = new ObjectInputStream(streamInput); 
			objOutput = (Serializable) in.readObject(); 
		} 
		catch (Exception e)
		{
			return null;
		}
		finally
		{
			try
			{
				in.close();
			}
			catch(Exception ex)
			{
			}
			try
			{
				streamInput.close();
			}
			catch(Exception ex)
			{
			}
		}

		return objOutput;
	}
	
	public static int compareLong(Object objValue1,Object objValue2)
	{
		Long value1 = parseLongValue(objValue1);
		Long value2 = parseLongValue(objValue2);
		return value1.compareTo(value2);
	}
	public static int compareDouble(Object objValue1,Object objValue2)
	{
		Double value1 = Double.valueOf(objValue1.toString());
		Double value2 = Double.valueOf(objValue2.toString());
		return value1.compareTo(value2);
	}	
	public static int compareString(Object objValue1,Object objValue2)
	{
		String strValue1 = objValue1.toString();
		String strValue2 = objValue2.toString();
		return strValue1.compareTo(strValue2);
	}
	public static Object parseObjectValue(int nType,Object source)
	{
		if(source==null)
		{
			return null;
		}
		switch (nType)
		{
			case DataType.STRING_TYPE:
			{
				return source.toString();
			}
			case DataType.INTEGER_TYPE:
			{
				return parseIntegerValue(source);
			}
			case DataType.LONG_TYPE:
			{
				return parseLongValue(source);
			}
			case DataType.DATETIME_TYPE:
			{
				return parseDateTimeValue(source);
			}
			case DataType.DATE_TYPE:
			{
				return parseDateValue(source);
			}
			case DataType.TIME_TYPE:
			{
				return parseTimeValue(source);
			}
			case DataType.FLOAT_TYPE:
			{
				return parseFloatValue(source);
			}
			case DataType.DOUBLE_TYPE:
			{
				return parseDoubleValue(source);
			}
			case DataType.BOOLEAN_TYPE:
			{
				return parseBooleanValue(source);
			}
			case DataType.BYTE_TYPE:
			{
				return parseByteValue(source);
			}
			case DataType.SHORT_TYPE:
			{
				return parseShortValue(source);
			}
			case DataType.BYTE_ARRAY_TYPE:
			{
				return parseByteArrayValue(source);
			}
			case DataType.INTEGER_ARRAY_TYPE:
				{
					return parseIntegerArrayValue(source);
				}
			case DataType.LONG_ARRAY_TYPE:
				{
					return parseLongArrayValue(source);
				}
			case DataType.BOOLEAN_ARRAY_TYPE:
				{
					return parseBooleanArrayValue(source);
				}
			case DataType.SHORT_ARRAY_TYPE:
				{
					return parseShortArrayValue(source);
				}
			case DataType.STRING_ARRAY_TYPE:
				{
					return parseStringArrayValue(source);
				}				
			default:
			{
				throw new RuntimeException("invalid type "+nType);
			}
		}
	}

	public static String parseStingValue(Object source)
	{
		if(source==null)
		{
			return null;
		}
		return source.toString();
	}
	public static String[] parseStringArrayValue(Object source)
	{
		if(source==null)
		{
			return null;
		}
		if(source instanceof String[])
		{
			return (String[])source;
		}
		if(source instanceof String)
		{
			String[] ss = ((String)source).split(",");
			return ss;
		}
		return new String[]{source.toString()};
	}
	public static Integer parseIntegerValue(Object source)
	{
		if(source==null)
		{
			return null;
		}
		if(source instanceof Integer)
		{
			return (Integer)source;
		}
		return Double.valueOf(source.toString()).intValue();
			
	}
	public static int[] parseIntegerArrayValue(Object source)
	{
		if(source==null)
		{
			return null;
		}
		if(source instanceof int[])
		{
			return (int[])source;
		}
		if(source instanceof Object[])
		{
			Object[] objs=(Object[])source;
			int[] values=new int[objs.length];
			for(int i=0;i<objs.length;i++)
			{
				values[i]=Double.valueOf(objs[i].toString()).intValue();
			}
			return values;
		}
		if(source instanceof String)
		{
			String[] ss = ((String)source).split(",");
			int[] bsArr=new int[ss.length];
			for(int i=0;i<ss.length;i++)
			{
				bsArr[i] = Double.valueOf(ss[i]).intValue();
			}
			return bsArr;
		}		
		return new int[]{Double.valueOf(source.toString()).intValue()};		
	}	
	public static Long parseLongValue(Object source)
	{
		if(source==null)
		{
			return null;
		}

		if(source instanceof Long)
		{
			return (Long)source;
		}
		return Double.valueOf(source.toString()).longValue();
			
	}
	public static long[] parseLongArrayValue(Object source)
	{
		if(source==null)
		{
			return null;
		}
		if(source instanceof long[])
		{
			return (long[])source;
		}
		if(source instanceof int[])
		{
			int[] nsSource = (int[]) source;
			long[] lsResult = new long[nsSource.length];
			for(int i=0;i<nsSource.length;i++)
			{
				lsResult[i] = nsSource[i];
			}
			return lsResult;
		}
		if(source instanceof Object[])
		{
			Object[] objs=(Object[])source;
			long[] values=new long[objs.length];
			for(int i=0;i<objs.length;i++)
			{
				values[i]=new Double(objs[i].toString()).longValue();
			}
			return values;
		}
		if(source instanceof String)
		{
			String[] ss = ((String)source).split(",");
			long[] bsArr=new long[ss.length];
			for(int i=0;i<ss.length;i++)
			{
				bsArr[i] = new Double(ss[i]).longValue();
			}
			return bsArr;
		}		
		return new long[]{new Double(source.toString()).longValue()};		
	}		
	public static String parseDateTimeValue(Object source)
	{
		if(source==null)
		{
			return null;
		}
		if(source instanceof String)
		{		
			String strValue = source.toString();
			if(Utility.checkDateTime(strValue)==true)
			{
				return strValue;
			}
			else if(Utility.checkDate(strValue)==true)
			{
				return strValue+" 00:00:00";
			}
			else
			{
				throw new RuntimeException("Invalid date format "+source);
			}
		}
		else if(source instanceof java.util.Date)			
		{
			java.util.Date temp = (java.util.Date)source;			
			return new DateTime(temp.getTime()).toString();
		}
		else if(source instanceof DateTime)
		{
			return source.toString();
		}
		else if(source instanceof Date)
		{
			return ((Date)source).toString()+" 00:00:00";
		}
		else
		{
			throw new RuntimeException("Invalid date format "+source);
		}
		
			
	}
	public static String parseDateValue(Object source)
	{
		if(source==null)
		{
			return null;
		}
		if(source instanceof String)
		{
			String strValue = source.toString();
			if(Utility.checkDate(strValue)==true)
			{
				return strValue;
			}
			if(Utility.checkDateTime(strValue))
			{
				return strValue.substring(0,10);
			}
			else
			{
				throw new RuntimeException("Invalid date format");
			}
		}
		else if(source instanceof java.util.Date)			
		{
			java.util.Date temp = (java.util.Date)source;
			return new Date(temp.getTime()).toString();
		}		
		else if(source instanceof Date)
		{
			return source.toString();
		}
		else if(source instanceof DateTime)
		{
			return source.toString().substring(0,10);
		}
		else
		{
			throw new RuntimeException("Invalid date format "+source);
		}
		
	}
	public static String parseTimeValue(Object source)
	{
		if(source==null)
		{
			return null;
		}
		if(source instanceof String)
		{
			String strValue = source.toString();
			if(Utility.checkTime(strValue)==true)
			{
				return strValue;
			}
			if(Utility.checkDateTime(strValue))
			{
				return strValue.substring(11);
			}
			throw new RuntimeException("Invalid date format");
		}
		else if(source instanceof java.util.Date)			
		{
			java.util.Date temp = (java.util.Date)source;
			DateTime dateTime=new DateTime(temp.getTime());
			return new Time(dateTime.getHour(),dateTime.getMinute(),dateTime.getSecond()).toString();
		}		
		else if(source instanceof Time)
		{
			return source.toString();
		}
		else if(source instanceof DateTime)
		{
			return source.toString().substring(11);
		}
		else
		{
			throw new RuntimeException("Invalid date format "+source);
		}
	}
	public static Float parseFloatValue(Object source)
	{
		if(source==null)
		{
			return null;
		}
		if(source instanceof Float)
		{
			return (Float)source;
		}
		return Double.valueOf(source.toString()).floatValue();
	}
	
	public static float[] parseFloatArrayValue(Object source)
	{
		if(source==null)
		{
			return null;
		}
		if(source instanceof float[])
		{
			return (float[])source;
		}
		if(source instanceof Object[])
		{
			Object[] objs=(Object[])source;
			float[] values=new float[objs.length];
			for(int i=0;i<objs.length;i++)
			{
				values[i]=Double.valueOf(objs[i].toString()).floatValue();
			}
			return values;
		}
		if(source instanceof String)
		{
			String[] ss = ((String)source).split(",");
			float[] bsArr=new float[ss.length];
			for(int i=0;i<ss.length;i++)
			{
				bsArr[i] = Float.parseFloat(ss[i]);
			}
			return bsArr;
		}
		return new float[]{Double.valueOf(source.toString()).floatValue()};		
	}
	
	public static Double parseDoubleValue(Object source)
	{
		if(source==null)
		{
			return null;
		}
		if(source instanceof Double)
		{
			return (Double)source;
		}
		return Double.valueOf(source.toString());
			
	}
	public static double[] parseDoubleArrayValue(Object source)
	{
		if(source==null)
		{
			return null;
		}
		if(source instanceof double[])
		{
			return (double[])source;
		}
		if(source instanceof Object[])
		{
			Object[] objs=(Object[])source;
			double[] values=new double[objs.length];
			for(int i=0;i<objs.length;i++)
			{
				values[i]=Double.valueOf(objs[i].toString()).doubleValue();
			}
			return values;
		}
		if(source instanceof String)
		{
			String[] ss = ((String)source).split(",");
			double[] bsArr=new double[ss.length];
			for(int i=0;i<ss.length;i++)
			{
				bsArr[i] = Double.parseDouble(ss[i]);
			}
			return bsArr;
		}		
		return new double[]{Double.valueOf(source.toString()).doubleValue()};		
	}
	
	public static Boolean parseBooleanValue(Object source)
	{
		if(source==null)
		{
			return null;
		}
		if(source instanceof Boolean)
		{
			return (Boolean)source;
		}
		String strValue = source.toString();
		if("true".equalsIgnoreCase(strValue))
		{
			return Boolean.TRUE;
		}
		if("false".equalsIgnoreCase(strValue))
		{
			return Boolean.FALSE;
		}
		if(Long.parseLong(strValue)==0)
		{
			return Boolean.FALSE;
		}
		else
		{
			return Boolean.TRUE;
		}			
	}
	public static boolean[] parseBooleanArrayValue(Object source)
	{
		if(source==null)
		{
			return null;
		}
		if(source instanceof boolean[])
		{
			return (boolean[])source;
		}
		if(source instanceof Boolean[])
		{
			Boolean[] bs=(Boolean[])source;
			boolean[] bsArr=new boolean[bs.length];
			for(int i=0;i<bs.length;i++)
			{
				bsArr[i]=bs[i].booleanValue();
			}
			return bsArr;
		}
		if(source instanceof Object[])
		{
			Object[] objs=(Object[])source;
			boolean[] values=new boolean[objs.length];
			for(int i=0;i<objs.length;i++)
			{
				values[i]=Boolean.valueOf(objs[i].toString());
			}
			return values;
		}		
		if(source instanceof String)
		{
			String[] ss = ((String)source).split(",");
			boolean[] bsArr=new boolean[ss.length];
			for(int i=0;i<ss.length;i++)
			{
				if("true".equalsIgnoreCase(ss[i]))
				{
					bsArr[i]=true;
				}
				else
				{
					bsArr[i]=false;
				}
			}
			return bsArr;
		}
		return null;			
	}	
	public static Boolean[] parseBooleanObjectArrayValue(Object source)
	{
		if(source==null)
		{
			return null;
		}
		if(source instanceof Boolean[])
		{
			return (Boolean[])source;
		}
		if(source instanceof boolean[])
		{
			boolean[] bs=(boolean[])source;
			Boolean[] bsArr=new Boolean[bs.length];
			for(int i=0;i<bs.length;i++)
			{
				bsArr[i]=new Boolean(bs[i]);
			}
			return bsArr;
		}
		if(source instanceof String)
		{
			String[] ss = ((String)source).split(",");
			Boolean[] bsArr=new Boolean[ss.length];
			for(int i=0;i<ss.length;i++)
			{
				if("true".equalsIgnoreCase(ss[i]))
				{
					bsArr[i]=Boolean.TRUE;
				}
				else
				{
					bsArr[i]=Boolean.FALSE;
				}
			}
			return bsArr;
		}
		return null;			
	}
	
	public static Short parseShortValue(Object source)
	{
		if(source==null)
		{
			return null;
		}
		if(source instanceof Byte)
		{
			return (Short)source;
		}
		return Double.valueOf(source.toString()).shortValue();
		
	}
	
	public static short[] parseShortArrayValue(Object source)
	{
		if(source==null)
		{
			return null;
		}
		if(source instanceof short[])
		{
			return (short[])source;
		}
		if(source instanceof Object[])
		{
			Object[] objs=(Object[])source;
			short[] values=new short[objs.length];
			for(int i=0;i<objs.length;i++)
			{
				values[i]=Double.valueOf(objs[i].toString()).shortValue();
			}
			return values;
		}
		if(source instanceof String)
		{
			String[] ss = ((String)source).split(",");
			short[] bsArr=new short[ss.length];
			for(int i=0;i<ss.length;i++)
			{
				bsArr[i] = Short.parseShort(ss[i]);
			}
			return bsArr;
		}		
		return new short[]{Long.valueOf(source.toString()).shortValue()};		
	}
		
	public static Byte parseByteValue(Object source)
	{
		if(source==null)
		{
			return null;
		}
		if(source instanceof Byte)
		{
			return (Byte)source;
		}
		return Long.valueOf(source.toString()).byteValue();
			
	}
	
	public static byte[] parseByteArrayValue(Object source) {
		if(source==null)
		{
			return null;
		}
		if(source instanceof byte[])
		{
			return (byte[])source;
		}
		if(source instanceof Object[])
		{
			Object[] objs=(Object[])source;
			byte[] values=new byte[objs.length];
			for(int i=0;i<objs.length;i++)
			{
				values[i]=Byte.parseByte(objs[i].toString());
			}
			return values;
		}		
		if(source instanceof String)
		{
			String[] ss = ((String)source).split(",");
			byte[] bsArr=new byte[ss.length];
			for(int i=0;i<ss.length;i++)
			{
				bsArr[i] = Byte.parseByte(ss[i]);
			}
			return bsArr;
		}		
		return source.toString().getBytes();
	}	
	
    public static String numberToStringWithFixedLength(int value, int length){
        String temp = "";
        for(int i = 0; i < length; i++)
            temp = temp + "0";

        DecimalFormat df = new DecimalFormat(temp);
        return df.format(value);      
    }
    
		
	

}