package com.cdoframework.cdolib.data.cdo;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Map;

import com.cdo.google.protocol.GoogleCDO;
import com.cdoframework.cdolib.base.DataType;
import com.cdoframework.cdolib.base.Utility;
import com.cdoframework.cdolib.util.Function;

/**
 * 重新构造
 * @author KenelLiu
 *
 */
public class StringArrayField extends ArrayFieldImpl
{

	//内部类,所有内部类在此声明----------------------------------------------------------------------------------

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------

	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------

	/**
	 * 字符串数组 可变性太大，维护2个变量数值,
	 * String[] 数组用于cdo操作
	 * buffer 用于序列化
	 * 
	 */
	private static final long serialVersionUID = 1892117383525261592L;
	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------
	private String[] strsValue;
	
	private final int dataIndex=3;//数据保存的起始位置
	private int databuffer=0;//数据占用字节   随字符串UTF8长度 而改变
	private int preDataBuffer=0;//数据未改变之前 所占用字节
	
	public void setValue(String[] strsValue)
	{
		if(strsValue==null)
		{
			strsValue=new String[0];
		}
		for(int i=0;i<strsValue.length;i++)
		{
			if(strsValue[i]==null)
			{
				strsValue[i]="";
			}
		}
		this.strsValue=strsValue;			
	}
	public String[] getValue()
	{
		return this.strsValue;
	}

	public String getValueAt(int nIndex)
	{
		return strsValue[nIndex];
	}

	public void setValueAt(int nIndex,String strValue)
	{
		if(strValue==null)
		{
			strValue="";
		}
		strsValue[nIndex]=strValue;
	}
	
	public int getLength()
	{
		return strsValue.length;
	}
	
	@Override
	public Buffer getBuffer() {
		allocate(this.strsValue);
		return buffer;
	}
	
	private  void allocateBuffer(){
		int len=dataIndex+databuffer;
		buffer=ByteBuffer.allocate(len);
		buffer.put((byte)DataType.STRING_ARRAY_TYPE);
		buffer.putShort((short)strsValue.length);	
	}
	
	private void allocate(String[] strsValue){
		int[] dataLen=new int[strsValue.length];//每个字符串长度
		byte[][] content=new byte[strsValue.length][];//内容字节
		databuffer=strsValue.length*4;//表示数据内容长度所占用字节
		for(int i=0;i<strsValue.length;i++)
		{						
			content[i]=strsValue[i].getBytes(Charset.forName("UTF-8")); //字符串内容数据   Charset.forName faster in Java 7 & 8,slow in java6
			dataLen[i]=content[i].length;  //每个数据字节的长度
			databuffer=databuffer+content[i].length;//数组里的数据内容字节长度之和
		}		
		if(buffer==null){
			allocateBuffer();
		}else{			
			if(preDataBuffer<databuffer){
				//最新字符串 长度大于之前的长度
				allocateBuffer();
			}else if(preDataBuffer>databuffer){
				//最新字符串 长度小于原来字符串长度  截取原长度的一部分 作为本次数据存放,不需要重新分配内存
				int len=dataIndex+databuffer;
				buffer.position(0);
				buffer.limit(len);
				buffer=buffer.slice();
			}
			//若相等，数据进行覆盖即可;
		}		
		buffer.position(dataIndex);
		for(int i=0;i<strsValue.length;i++){
			buffer.putInt(dataLen[i]);
			buffer.put(content[i]);
		}
		buffer.flip();
		//设置字符串长度，当字符串改变 可以进行比较
		preDataBuffer=databuffer;
	}	

	private  void byte2StrArr(){
		if(buffer==null)
				return;
		 buffer.clear();
		 buffer.position(1);
		 int len=buffer.getShort();
		 this.strsValue=new String[len];
		 int totalContentLen=0;
		 int index=dataIndex;
		 ByteBuffer slice=null;
		 byte[] dst=null;
		 for(int i=0;i<len;i++){
			 //计算字符串内容所在buffer 下标
			 int contentLen=buffer.getInt(index);			 
			 int pos=(3+4*(i+1))+totalContentLen;
			 totalContentLen=totalContentLen+contentLen;	
			 buffer.position(pos);
			 buffer.limit(pos+contentLen);
			 //截取内容 ,buffer copy出来，直接使用buffer.array[] 有不存在的字符会有乱码
			 slice=buffer.slice();
			 dst=new byte[contentLen];
			 slice.get(dst);			
			
			 this.strsValue[i]=new String(dst,Charset.forName("UTF-8"));
			 //移位
			 buffer.limit(buffer.capacity());
			 index=pos+contentLen;			
		 }	
		 buffer.clear();
	}	
	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------

	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------
	@Override
	public void toAvro(String prefixField,Map<CharSequence,ByteBuffer> fieldMap){	
		allocate(this.strsValue);		
		super.toAvro(prefixField, fieldMap);
	}	
	
	@Override
	public void toProto(String prefixField,GoogleCDO.CDOProto.Builder cdoProto){
		allocate(this.strsValue);
		super.toProto(prefixField, cdoProto);
	}
	
	@Override
	public void toXML(StringBuilder strbXML)
	{	
		strbXML.append("<STRAF N=\"").append(this.getName()).append("\">");
		for(int i=0;i<this.strsValue.length;i=i+1)
		{
			strbXML.append("<STR>").append(Function.FormatTextForXML(this.strsValue[i])).append("</STR>");
		}
		strbXML.append("</STRAF>");
	}
	
	@Override
	public void toXMLWithIndent(int nIndentSize,StringBuilder strbXML)
	{
		String strIndent=Utility.makeSameCharString('\t',nIndentSize);
		
		strbXML.append(strIndent).append("<STRAF N=\"").append(this.getName()).append("\">\r\n");
		for(int i=0;i<this.strsValue.length;i=i+1)
		{
			strbXML.append(strIndent).append("\t<STR>").append(Function.FormatTextForXML(this.strsValue[i])).append("</STR>\r\n");
		}
		strbXML.append(strIndent).append("</STRAF>\r\n");
	}	
	
	@Override
	public String toJSON()
	{
		StringBuffer str_JSON=new StringBuffer();
		str_JSON.append("\"").append(this.getName()).append("\"").append(":").append("[");
		int _length=strsValue.length;
		for(int i=0;i<this.strsValue.length;i=i+1)
		{
			String _sign=(i==_length-1)?"\"":"\",";
			str_JSON.append("\"").append(Function.FormatTextForJson(this.strsValue[i])).append(_sign);
		}
		str_JSON.append("],");
		return str_JSON.toString();
	}
	@Override
	public String toHtmlJSON()
	{
		StringBuffer str_JSON=new StringBuffer();
		str_JSON.append("\"").append(this.getName()).append("\"").append(":").append("[");
		int _length=strsValue.length;
		for(int i=0;i<this.strsValue.length;i=i+1)
		{
			String _sign=(i==_length-1)?"\"":"\",";
			String strValue=Function.FormatTextForHTML(Function.FormatTextForJson(this.strsValue[i]));
			str_JSON.append("\"").append(strValue).append(_sign);
		}
		str_JSON.append("],");
		return str_JSON.toString();
	}
	
	@Override
	public String toString(){

		StringBuffer str_JSON=new StringBuffer();
		str_JSON.append("\"").append(this.getName()).append("\"").append(":").append("[");
		int _length=strsValue.length;
		String value=null;
		for(int i=0;i<this.strsValue.length;i=i+1)
		{
			String _sign=(i==_length-1)?"\"":"\",";
			
			value=this.strsValue[i];
			if(value.length()>50){
				value=value.substring(0, 50)+"......";
			}				
			str_JSON.append("\"").append(Function.FormatTextForJson(value)).append(_sign);
		}
		str_JSON.append("],");
		return str_JSON.toString();
	}
	
	public Object getObjectValue()
	{
		return strsValue;
	}

	public Object getObjectValueAt(int nIndex)
	{
		return strsValue[nIndex];
	}




	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public StringArrayField(String strFieldName)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strFieldName);
		
		setType(Data.STRING_ARRAY);
		
		this.strsValue	=new String[0];
		setValue(this.strsValue);
	}

	public StringArrayField(String strFieldName,String[] strsValue)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strFieldName);
		
		setType(Data.STRING_ARRAY);
		
		if(strsValue==null)
		{
			strsValue=new String[0];
		}

		this.strsValue	=strsValue;
		setValue(this.strsValue);
		
	}
	
	StringArrayField(String strFieldName,ByteBuffer buffer)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strFieldName);
		
		setType(Data.STRING_ARRAY);
		
		this.buffer=buffer;
		
		byte2StrArr();
	}
	
}
