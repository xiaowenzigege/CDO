
package com.cdoframework.cdolib.data.cdo;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cdo.google.protocol.GoogleCDO;
import com.cdoframework.cdolib.base.Utility;



public class CDOListField extends ArrayFieldImpl
{

	//内部类,所有内部类在此声明----------------------------------------------------------------------------------

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------

	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = -1583510869570826742L;
	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------
	private List<CDO> cdosValue;
	public void setValue(List<CDO> cdosValue)
	{
		if(cdosValue==null)
			cdosValue=new ArrayList<CDO>();
		this.cdosValue=cdosValue;
	}

	public  List<CDO> getValue()
	{
		return this.cdosValue;
	}

	public CDO getValueAt(int nIndex)
	{
		return cdosValue.get(nIndex);
	}

	public void setValueAt(int nIndex,CDO cdoValue)
	{
		if(cdoValue==null)
		{
			cdoValue=new CDO();
		}
		cdosValue.set(nIndex, cdoValue);
	}
	
	public int getLength()
	{
		return cdosValue.size();
	}

	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------

	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------
	public void toAvro(String prefixField,Map<CharSequence,ByteBuffer> fieldMap){
		for(int i=0;i<this.cdosValue.size();i=i+1){
			String prefix=prefixField+this.getName()+"["+i+"].";
			this.cdosValue.get(i).toAvro(prefix,fieldMap);
		}
		
	}	
	
	public int toAvro(String prefixField,Map<CharSequence,ByteBuffer> fieldMap,int maxLevel){
		int curLevel=1;
		if(prefixField.length()>0){
			curLevel=(prefixField.split("\\.").length+1);
		}							
		maxLevel=maxLevel>curLevel?maxLevel:curLevel;				
		for(int i=0;i<this.cdosValue.size();i=i+1){
			String prefix=prefixField+this.getName()+"["+i+"].";
			curLevel=this.cdosValue.get(i).toAvro(prefix,fieldMap,maxLevel);
			if(curLevel>maxLevel)
				maxLevel=curLevel;
		}
		return maxLevel;
	}
	
	@Override
	public void toProto(String prefixField,GoogleCDO.CDOProto.Builder cdoProto){
		for(int i=0;i<this.cdosValue.size();i=i+1){
			String prefix=prefixField+this.getName()+"["+i+"].";
			this.cdosValue.get(i).toProto(prefix,cdoProto);
		}
	}
	
	@Override
	public int toProto(String prefixField,GoogleCDO.CDOProto.Builder cdoProto,int maxLevel){
		int curLevel=1;
		if(prefixField.length()>0){
			curLevel=(prefixField.split("\\.").length+1);
		}							
		maxLevel=maxLevel>curLevel?maxLevel:curLevel;				
		for(int i=0;i<this.cdosValue.size();i=i+1){
			String prefix=prefixField+this.getName()+"["+i+"].";
			curLevel=this.cdosValue.get(i).toProto(prefix,cdoProto,maxLevel);
			if(curLevel>maxLevel)
				maxLevel=curLevel;
		}
		return maxLevel;
	}		
	
	public void toXML(StringBuilder strbXML)
	{
		strbXML.append("<CDOAF N=\"").append(this.getName()).append("\">");
		for(int i=0;i<this.cdosValue.size();i=i+1)
		{
			this.cdosValue.get(i).toXML(strbXML);
		}
		strbXML.append("</CDOAF>");
	}
	
	public void toXMLWithIndent(int nIndentSize,StringBuilder strbXML)
	{
		String strIndent=Utility.makeSameCharString('\t',nIndentSize);

		strbXML.append(strIndent).append("<CDOAF N=\"").append(this.getName()).append("\">\r\n");
		for(int i=0;i<this.cdosValue.size();i=i+1)
		{
			this.cdosValue.get(i).toXMLWithIndent(strIndent,strbXML);
		}
		strbXML.append(strIndent).append("</CDOAF>\r\n");
	}	
	
	public Object getObjectValue()
	{
		return cdosValue;
	}

	public Object getObjectValueAt(int nIndex)
	{
		return cdosValue.get(nIndex);
	}


	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public CDOListField()
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super();
		
		setType(Data.CDO_LIST);
		
		this.cdosValue	=new ArrayList<CDO>();
	}

	public CDOListField(String strFieldName)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strFieldName);
		
		setType(Data.CDO_LIST);
		
		this.cdosValue	=new ArrayList<CDO>();
	}

	public CDOListField(String strFieldName,List<CDO> cdosValue)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		super(strFieldName);
		
		setType(Data.CDO_LIST);
		
		if(cdosValue==null)
		{
			cdosValue=new ArrayList<CDO>();
		}

		setValue(cdosValue);
	}
	
	public CDOListField(List<CDO>  cdosValue)
	{	
		setType(Data.CDO_LIST);
		
		if(cdosValue==null)
		{
			cdosValue=new ArrayList<CDO>();
		}

		setValue(cdosValue);
	}
	public String toJSONString()
	{
		StringBuffer str_JSON=new StringBuffer();
		str_JSON.append("\\\"").append(this.getName()).append("\\\"").append(":").append("[");
		int _length=this.cdosValue.size();
		for(int i=0;i<_length;i=i+1)
		{
			String _sign=(i==_length-1)?"":",";
			str_JSON.append("").append(this.cdosValue.get(i).toJSON()).append(_sign);
		}
		str_JSON.append("],");
		return str_JSON.toString();
	}

	public String toJSON()
	{
		StringBuffer str_JSON=new StringBuffer();
		str_JSON.append("\"").append(this.getName()).append("\"").append(":").append("[");
		int _length=this.cdosValue.size();
		for(int i=0;i<_length;i=i+1)
		{
			String _sign=(i==_length-1)?"":",";
			str_JSON.append("").append(this.cdosValue.get(i).toJSON()).append(_sign);
		}
		str_JSON.append("],");
		return str_JSON.toString();
	}
}
