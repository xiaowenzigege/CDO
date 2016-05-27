/**
 * www.cdoforum.com 2007版权所有
 *
 * $Header: /CVSData/Frank/CVSROOT/CDOForum/CDOLib/Source/com/cdoframework/cdolib/base/ObjectExt.java,v 1.2 2008/03/22 13:32:28 Frank Exp $
 *
 * $Log: ObjectExt.java,v $
 * Revision 1.2  2008/03/22 13:32:28  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2008/03/05 01:34:43  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/12/27 12:28:05  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/12/15 09:35:37  Frank
 * *** empty log message ***
 *
 * Revision 1.3  2007/09/30 00:11:39  Frank
 * *** empty log message ***
 *
 * Revision 1.2  2007/08/23 10:22:12  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/07/24 13:40:40  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/07/24 13:36:57  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2006/09/02 14:43:40  Frank
 * �޸�DataTrans,�޸�Complex
 *
 *
 */

package com.cdoframework.cdolib.base;

import java.io.File;

import com.cdoframework.cdolib.data.cdo.CDO;

/**
 * @author Frank
 * 本类为带有Type的Object类
 * Type为Object的实际类型的定义字符串，比如Byte,byte[],Date,Date[]
 * 其中Date,Time,DateTime实际为特定格式的String，但是Type不是String
 */
public class ObjectExt implements DataType
{

	//静态对象,所有static在此声明并初始化------------------------------------------------------------------------

	//内部对象,所有在本类中创建并使用的对象在此声明--------------------------------------------------------------

	//属性对象,所有在本类中创建，并允许外部访问的对象在此声明并提供get/set方法-----------------------------------
	private Object objValue;
	public Object getValue()
	{
		return objValue;
	}
	public Object getObjectValue()
	{
		switch(nType)
		{			
			case DataType.BOOLEAN_TYPE:					
			case DataType.SHORT_TYPE:
			case DataType.INTEGER_TYPE:
			case DataType.LONG_TYPE:	
			case DataType.DOUBLE_TYPE:
			case DataType.FLOAT_TYPE:
			case DataType.TIME_TYPE:
			case DataType.DATE_TYPE:
			case DataType.DATETIME_TYPE:
			case DataType.STRING_TYPE:	
				return objValue;
			case DataType.BOOLEAN_ARRAY_TYPE:
				boolean[] bs=(boolean[])objValue;
				Boolean[] bsObj=new Boolean[bs.length];
				for(int i=0;i<bsObj.length;i++)
				{
					bsObj[i]=new Boolean(bs[i]);	
				}
				return bsObj;
			case DataType.SHORT_ARRAY_TYPE:
				short[] ss=(short[])objValue;
				Short[] ssObj=new Short[ss.length];
				for(int i=0;i<ssObj.length;i++)
				{
					ssObj[i]=new Short(ss[i]);	
				}
				return ssObj;
			case DataType.INTEGER_ARRAY_TYPE:
				int[] ns=(int[])objValue;
				Integer[] nsObj=new Integer[ns.length];
				for(int i=0;i<nsObj.length;i++)
				{
					nsObj[i]=new Integer(ns[i]);	
				}
				return nsObj;
			case DataType.LONG_ARRAY_TYPE:
				long[] ls=(long[])objValue;
				Long[] lsObj=new Long[ls.length];
				for(int i=0;i<lsObj.length;i++)
				{
					lsObj[i]=new Long(ls[i]);	
				}
				return lsObj;	
			case DataType.FLOAT_ARRAY_TYPE:
				float[] fs=(float[])objValue;
				Float[] fsObj=new Float[fs.length];
				for(int i=0;i<fsObj.length;i++)
				{
					fsObj[i]=new Float(fs[i]);	
				}
				return fsObj;	
			case DataType.DOUBLE_ARRAY_TYPE:
				double[] ds=(double[])objValue;
				Double[] dsObj=new Double[ds.length];
				for(int i=0;i<dsObj.length;i++)
				{
					dsObj[i]=new Double(ds[i]);	
				}
				return dsObj;
			case DataType.STRING_ARRAY_TYPE:
			case DataType.DATE_ARRAY_TYPE:
			case DataType.DATETIME_ARRAY_TYPE:
			case DataType.TIME_ARRAY_TYPE:				
				return (String[])objValue;	
			default:
				throw new RuntimeException(" type is not unSupported! ");				
		}
	}
	public void setValue(Object object)
	{
		this.objValue=object;
	}

	private int nType;
	public int getType()
	{
		return this.nType;
	}
	public void setType(int nType)
	{
		this.nType=nType;
	}
	
	public int getLength()
	{
		// fix objValue NullPointerException.
		if(objValue == null) {
			return 0;
		}
		switch(this.nType)
		{
			case ObjectExt.BOOLEAN_ARRAY_TYPE:
			{
				return ((boolean[])objValue).length;
			}
			case ObjectExt.BYTE_ARRAY_TYPE:
			{
				return ((byte[])objValue).length;
			}
			case ObjectExt.SHORT_ARRAY_TYPE:
			{
				return ((short[])objValue).length;
			}
			case ObjectExt.INTEGER_ARRAY_TYPE:
			{
				return ((int[])objValue).length;
			}
			case ObjectExt.LONG_ARRAY_TYPE:
			{
				return ((long[])objValue).length;
			}
			case ObjectExt.FLOAT_ARRAY_TYPE:
			{
				return ((float[])objValue).length;
			}
			case ObjectExt.DOUBLE_ARRAY_TYPE:
			{
				return ((double[])objValue).length;
			}
			case ObjectExt.STRING_ARRAY_TYPE:
			case ObjectExt.DATE_ARRAY_TYPE:	
			case ObjectExt.TIME_ARRAY_TYPE:	
			case ObjectExt.DATETIME_ARRAY_TYPE:	
			{
				return ((String[])objValue).length;
			}					
			case ObjectExt.CDO_ARRAY_TYPE:
			{
				return ((CDO[])objValue).length;
			}
		}
		throw new RuntimeException("Type cast failed");	
	}

	//引用对象,所有在外部创建并传入使用的对象在此声明并提供set方法-----------------------------------------------

	//内部方法,所有仅在本类或派生类中使用的函数在此定义为protected方法-------------------------------------------

	//公共方法,所有可提供外部使用的函数在此定义为public方法------------------------------------------------------
	public boolean isArrayType()
	{
		if(this.nType>=ObjectExt.BOOLEAN_ARRAY_TYPE)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean getBoolean()
	{
		if(this.nType==ObjectExt.BOOLEAN_TYPE)
		{
			return (Boolean)objValue;
		}
		
		throw new RuntimeException("Type cast failed");
	}

	public byte getByte()
	{
		switch(this.nType)
		{
			case ObjectExt.BYTE_TYPE:
			{
				return ((Byte)objValue).byteValue();
			}
			case ObjectExt.SHORT_TYPE:
			{
				return ((Short)objValue).byteValue();
			}
			case ObjectExt.INTEGER_TYPE:
			{
				return ((Integer)objValue).byteValue();
			}
			case ObjectExt.LONG_TYPE:
			{
				return ((Long)objValue).byteValue();
			}
			case ObjectExt.FLOAT_TYPE:
			{
				return ((Float)objValue).byteValue();
			}
			case ObjectExt.DOUBLE_TYPE:
			{
				return ((Double)objValue).byteValue();
			}
			case DataType.STRING_TYPE:		
			{
				return Byte.parseByte((String)objValue);
			}
		}
		
		throw new RuntimeException("Type cast failed");
	}

	public short getShort()
	{
		switch(this.nType)
		{
			case ObjectExt.BYTE_TYPE:
			{
				return ((Byte)objValue).byteValue();
			}
			case ObjectExt.SHORT_TYPE:
			{
				return ((Short)objValue).shortValue();
			}
			case ObjectExt.INTEGER_TYPE:
			{
				return ((Integer)objValue).shortValue();
			}
			case ObjectExt.LONG_TYPE:
			{
				return ((Long)objValue).shortValue();
			}
			case ObjectExt.FLOAT_TYPE:
			{
				return ((Float)objValue).shortValue();
			}
			case ObjectExt.DOUBLE_TYPE:
			{
				return ((Double)objValue).shortValue();
			}
			case ObjectExt.STRING_TYPE:
			{
				return Short.parseShort((String)objValue);
			}
		}
		
		throw new RuntimeException("Type cast failed");
	}

	public int getInteger()
	{
		switch(this.nType)
		{
			case ObjectExt.BYTE_TYPE:
			{
				return ((Byte)objValue).byteValue();
			}
			case ObjectExt.SHORT_TYPE:
			{
				return ((Short)objValue).shortValue();
			}
			case ObjectExt.INTEGER_TYPE:
			{
				return ((Integer)objValue).intValue();
			}
			case ObjectExt.LONG_TYPE:
			{
				return ((Long)objValue).intValue();
			}
			case ObjectExt.FLOAT_TYPE:
			{
				return ((Float)objValue).intValue();
			}
			case ObjectExt.DOUBLE_TYPE:
			{
				return ((Double)objValue).intValue();
			}
			case ObjectExt.STRING_TYPE:
			{
				return Integer.parseInt((String)objValue);
			}
		}
		
		throw new RuntimeException("Type cast failed");
	}

	public long getLong()
	{
		switch(this.nType)
		{
			case ObjectExt.BYTE_TYPE:
			{
				return ((Byte)objValue).byteValue();
			}
			case ObjectExt.SHORT_TYPE:
			{
				return ((Short)objValue).shortValue();
			}
			case ObjectExt.INTEGER_TYPE:
			{
				return ((Integer)objValue).intValue();
			}
			case ObjectExt.LONG_TYPE:
			{
				return ((Long)objValue).longValue();
			}
			case ObjectExt.FLOAT_TYPE:
			{
				return ((Float)objValue).longValue();
			}
			case ObjectExt.DOUBLE_TYPE:
			{
				return ((Double)objValue).longValue();
			}
			case ObjectExt.STRING_TYPE:
			{
				return Long.parseLong((String)objValue);
			}
		}
		
		throw new RuntimeException("Type cast failed");
	}

	public float getFloat()
	{
		switch(this.nType)
		{
			case ObjectExt.BYTE_TYPE:
			{
				return ((Byte)objValue).byteValue();
			}
			case ObjectExt.SHORT_TYPE:
			{
				return ((Short)objValue).shortValue();
			}
			case ObjectExt.INTEGER_TYPE:
			{
				return ((Integer)objValue).floatValue();
			}
			case ObjectExt.LONG_TYPE:
			{
				return ((Long)objValue).floatValue();
			}
			case ObjectExt.FLOAT_TYPE:
			{
				return ((Float)objValue).floatValue();
			}
			case ObjectExt.DOUBLE_TYPE:
			{
				return ((Double)objValue).floatValue();
			}
			case ObjectExt.STRING_TYPE:
			{
				return Float.parseFloat((String)objValue);
			}
		}
		
		throw new RuntimeException("Type cast failed");
	}

	public double getDouble()
	{
		switch(this.nType)
		{
			case ObjectExt.BYTE_TYPE:
			{
				return ((Byte)objValue).byteValue();
			}
			case ObjectExt.SHORT_TYPE:
			{
				return ((Short)objValue).shortValue();
			}
			case ObjectExt.INTEGER_TYPE:
			{
				return ((Integer)objValue).intValue();
			}
			case ObjectExt.LONG_TYPE:
			{
				return ((Long)objValue).longValue();
			}
			case ObjectExt.FLOAT_TYPE:
			{
				return ((Float)objValue).floatValue();
			}
			case ObjectExt.DOUBLE_TYPE:
			{
				return ((Double)objValue).doubleValue();
			}
			case ObjectExt.STRING_TYPE:
			{
				return Double.parseDouble((String)objValue);
			}
		}
		
		throw new RuntimeException("Type cast failed");
	}

	public String getString()
	{
		// fix objValue NullPointerException.
		if(objValue == null) {
			return null;
		}
		switch(this.nType)
		{
			case ObjectExt.BYTE_TYPE:
			case ObjectExt.SHORT_TYPE:
			case ObjectExt.INTEGER_TYPE:
			case ObjectExt.LONG_TYPE:
			case ObjectExt.FLOAT_TYPE:
			case ObjectExt.DOUBLE_TYPE:
			case ObjectExt.STRING_TYPE:
			case ObjectExt.DATE_TYPE:
			case ObjectExt.TIME_TYPE:
			case ObjectExt.DATETIME_TYPE:
			{
				return objValue.toString();
			}
		}
		
		throw new RuntimeException("Type cast failed");
	}

	public String getDate()
	{
		// fix objValue NullPointerException.
		if(objValue == null) {
			return null;
		}
		switch(this.nType)
		{
			case ObjectExt.DATE_TYPE:
			{
				return objValue.toString();
			}
			case ObjectExt.DATETIME_TYPE:
			{
				return objValue.toString().substring(0,10);
			}
		}
		
		throw new RuntimeException("Type cast failed");
	}

	public String getTime()
	{
		// fix objValue NullPointerException.
		if(objValue == null) {
			return null;
		}
		switch(this.nType)
		{
			case ObjectExt.DATE_TYPE:
			{
				return objValue.toString();
			}
			case ObjectExt.DATETIME_TYPE:
			{
				return objValue.toString().substring(11);
			}
		}
		
		throw new RuntimeException("Type cast failed");
	}

	public String getDateTime()
	{
		// fix objValue NullPointerException.
		if(objValue == null) {
			return null;
		}
		switch(this.nType)
		{
			case ObjectExt.DATETIME_TYPE:
			{
				return objValue.toString();
			}
		}
		
		throw new RuntimeException("Type cast failed");
	}

	public CDO getCDO()
	{
		return (CDO)objValue;
	}
	
	public File getFile(){
		return (File)objValue;
	}
	
	public boolean[] getBooleanArray()
	{
		switch(this.nType)
		{
			case ObjectExt.BOOLEAN_ARRAY_TYPE:
			{
				return (boolean[])objValue;
			}
		}
		
		throw new RuntimeException("Type cast failed");
	}

	public byte[] getByteArray()
	{
		switch(this.nType)
		{
			case ObjectExt.BYTE_ARRAY_TYPE:
			{
				return (byte[])objValue;
			}
		}
		
		throw new RuntimeException("Type cast failed");
	}

	public short[] getShortArray()
	{
		switch(this.nType)
		{
			case ObjectExt.SHORT_ARRAY_TYPE:
			{
				return (short[])objValue;
			}
		}
		
		throw new RuntimeException("Type cast failed");
	}

	public int[] getIntegerArray()
	{
		switch(this.nType)
		{
			case ObjectExt.INTEGER_ARRAY_TYPE:
			{
				return (int[])objValue;
			}
		}
		
		throw new RuntimeException("Type cast failed");
	}

	public long[] getLongArray()
	{
		switch(this.nType)
		{
			case ObjectExt.LONG_ARRAY_TYPE:
			{
				return (long[])objValue;
			}
		}
		
		throw new RuntimeException("Type cast failed");
	}

	public float[] getFloatArray()
	{
		switch(this.nType)
		{
			case ObjectExt.FLOAT_ARRAY_TYPE:
			{
				return (float[])objValue;
			}
		}
		
		throw new RuntimeException("Type cast failed");
	}

	public double[] getDoubleArray()
	{
		switch(this.nType)
		{
			case ObjectExt.DOUBLE_ARRAY_TYPE:
			{
				return (double[])objValue;
			}
		}
		
		throw new RuntimeException("Type cast failed");
	}

	public String[] getStringArray()
	{
		switch(this.nType)
		{
			case ObjectExt.STRING_ARRAY_TYPE:
			{
				return (String[])objValue;
			}
		}
		
		throw new RuntimeException("Type cast failed");
	}

	public String[] getDateArray()
	{
		switch(this.nType)
		{
			case ObjectExt.DATE_ARRAY_TYPE:
			{
				return (String[])objValue;
			}
		}
		
		throw new RuntimeException("Type cast failed");
	}

	public String[] getTimeArray()
	{
		switch(this.nType)
		{
			case ObjectExt.TIME_ARRAY_TYPE:
			{
				return (String[])objValue;
			}
		}
		
		throw new RuntimeException("Type cast failed");
	}

	public String[] getDateTimeArray()
	{
		switch(this.nType)
		{
			case ObjectExt.DATETIME_ARRAY_TYPE:
			{
				return (String[])objValue;
			}
		}
		
		throw new RuntimeException("Type cast failed");
	}

	public CDO[] getCDOArray()
	{
		return (CDO[])objValue;
	}

	public Boolean[] getBlooeanObjArray()
	{
		switch(this.nType)
		{
			case ObjectExt.BOOLEAN_ARRAY_TYPE:
			{
				boolean[] bs=getBooleanArray();
				Boolean[] bsObj=new Boolean[bs.length];
				for(int i=0;i<bsObj.length;i++)
				{
					bsObj[i]=new Boolean(bs[i]);	
				}
				return bsObj;
			}
		}
		throw new RuntimeException("Type cast failed");		
	}
	public Short[] getShortObjArray()
	{
		switch(this.nType)
		{
			case ObjectExt.SHORT_ARRAY_TYPE:
			{
				short[] bs=getShortArray();
				Short[] bsObj=new Short[bs.length];
				for(int i=0;i<bsObj.length;i++)
				{
					bsObj[i]=new Short(bs[i]);	
				}
				return bsObj;
			}
		}
		throw new RuntimeException("Type cast failed");		
	}	
	public Integer[] getIntegerObjArray()
	{
		switch(this.nType)
		{
			case ObjectExt.INTEGER_ARRAY_TYPE:
			{
				int[] bs=getIntegerArray();
				Integer[] bsObj=new Integer[bs.length];
				for(int i=0;i<bsObj.length;i++)
				{
					bsObj[i]=new Integer(bs[i]);	
				}
				return bsObj;
			}
		}
		throw new RuntimeException("Type cast failed");		
	}
	public Long[] getLongObjArray()
	{
		switch(this.nType)
		{
			case ObjectExt.LONG_ARRAY_TYPE:
			{
				long[] ls=getLongArray();
				Long[] lsObj=new Long[ls.length];
				for(int i=0;i<lsObj.length;i++)
				{
					lsObj[i]=new Long(ls[i]);	
				}
				return lsObj;
			}
		}
		throw new RuntimeException("Type cast failed");		
	}		
	public Float[] getFloatObjArray()
	{
		switch(this.nType)
		{
			case ObjectExt.FLOAT_ARRAY_TYPE:
			{
				float[] fs=getFloatArray();
				Float[] fsObj=new Float[fs.length];
				for(int i=0;i<fsObj.length;i++)
				{
					fsObj[i]=new Float(fs[i]);	
				}
				return fsObj;
			}
		}
		throw new RuntimeException("Type cast failed");	
	}
	
	public Double[] getDoubleObjArray()
	{
		switch(this.nType)
		{
			case ObjectExt.DOUBLE_ARRAY_TYPE:
			{
				double[] ds=getDoubleArray();
				Double[] dsObj=new Double[ds.length];
				for(int i=0;i<dsObj.length;i++)
				{
					dsObj[i]=new Double(ds[i]);	
				}
				return dsObj;
			}
		}
		throw new RuntimeException("Type cast failed");	
	}

	public Object getValueAt(int nIndex)
	{
		switch(this.nType)
		{
			case ObjectExt.BOOLEAN_ARRAY_TYPE:
			{
				return ((boolean[])objValue)[nIndex];
			}
			case ObjectExt.BYTE_ARRAY_TYPE:
			{
				return ((byte[])objValue)[nIndex];
			}
			case ObjectExt.SHORT_ARRAY_TYPE:
			{
				return ((short[])objValue)[nIndex];
			}
			case ObjectExt.INTEGER_ARRAY_TYPE:
			{
				return ((int[])objValue)[nIndex];
			}
			case ObjectExt.LONG_ARRAY_TYPE:
			{
				return ((long[])objValue)[nIndex];
			}
			case ObjectExt.FLOAT_ARRAY_TYPE:
			{
				return ((float[])objValue)[nIndex];
			}
			case ObjectExt.DOUBLE_ARRAY_TYPE:
			{
				return ((double[])objValue)[nIndex];
			}
			case ObjectExt.STRING_ARRAY_TYPE:
			{
				return ((String[])objValue)[nIndex];
			}
			case ObjectExt.DATE_ARRAY_TYPE:
			{
				return ((String[])objValue)[nIndex];
			}
			case ObjectExt.TIME_ARRAY_TYPE:
			{
				return ((String[])objValue)[nIndex];
			}
			case ObjectExt.DATETIME_ARRAY_TYPE:
			{
				return ((String[])objValue)[nIndex];
			}
			case ObjectExt.CDO_ARRAY_TYPE:
			{
				return ((CDO[])objValue)[nIndex];
			}
		}
		throw new RuntimeException("Type cast failed");	
	}

	public ObjectExt getValueAtExt(int nIndex)
	{
		switch(this.nType)
		{
			case ObjectExt.BOOLEAN_ARRAY_TYPE:
			{
				return new ObjectExt(ObjectExt.BOOLEAN_TYPE,((boolean[])objValue)[nIndex]);
			}
			case ObjectExt.BYTE_ARRAY_TYPE:
			{
				return new ObjectExt(ObjectExt.BYTE_TYPE,((Byte[])objValue)[nIndex]);
			}
			case ObjectExt.SHORT_ARRAY_TYPE:
			{
				return new ObjectExt(ObjectExt.SHORT_TYPE,((short[])objValue)[nIndex]);
			}
			case ObjectExt.INTEGER_ARRAY_TYPE:
			{
				return new ObjectExt(ObjectExt.INTEGER_TYPE,((int[])objValue)[nIndex]);
			}
			case ObjectExt.LONG_ARRAY_TYPE:
			{
				return new ObjectExt(ObjectExt.LONG_TYPE,((long[])objValue)[nIndex]);
			}
			case ObjectExt.FLOAT_ARRAY_TYPE:
			{
				return new ObjectExt(ObjectExt.FLOAT_TYPE,((float[])objValue)[nIndex]);
			}
			case ObjectExt.DOUBLE_ARRAY_TYPE:
			{
				return new ObjectExt(ObjectExt.DOUBLE_TYPE,((double[])objValue)[nIndex]);
			}
			case ObjectExt.STRING_ARRAY_TYPE:
			{
				return new ObjectExt(ObjectExt.STRING_TYPE,((String[])objValue)[nIndex]);
			}
			case ObjectExt.DATE_ARRAY_TYPE:
			{
				return new ObjectExt(ObjectExt.DATE_TYPE,((String[])objValue)[nIndex]);
			}
			case ObjectExt.TIME_ARRAY_TYPE:
			{
				return new ObjectExt(ObjectExt.TIME_TYPE,((String[])objValue)[nIndex]);
			}
			case ObjectExt.DATETIME_ARRAY_TYPE:
			{
				return new ObjectExt(ObjectExt.DATETIME_TYPE,((String[])objValue)[nIndex]);
			}
			case ObjectExt.CDO_ARRAY_TYPE:
			{
				return new ObjectExt(ObjectExt.CDO_TYPE,((CDO[])objValue)[nIndex]);
			}
		}
		throw new RuntimeException("Type cast failed");	
	}
	
	public void setValueAt(int nIndex,Object obj)
	{
		switch(this.nType)
		{
			case ObjectExt.BOOLEAN_ARRAY_TYPE:
			{
				((boolean[])objValue)[nIndex]=(Boolean)obj;
				return;
			}
			case ObjectExt.BYTE_ARRAY_TYPE:
			{
				((byte[])objValue)[nIndex]=(Byte)obj;
				return;
			}
			case ObjectExt.SHORT_ARRAY_TYPE:
			{
				((short[])objValue)[nIndex]=(Short)obj;
				return;
			}
			case ObjectExt.INTEGER_ARRAY_TYPE:
			{
				((int[])objValue)[nIndex]=(Integer)obj;
				return;
			}
			case ObjectExt.LONG_ARRAY_TYPE:
			{
				((long[])objValue)[nIndex]=(Long)obj;
				return;
			}
			case ObjectExt.FLOAT_ARRAY_TYPE:
			{
				((float[])objValue)[nIndex]=(Float)obj;
				return;
			}
			case ObjectExt.DOUBLE_ARRAY_TYPE:
			{
				((double[])objValue)[nIndex]=(Double)obj;
				return;
			}
			case ObjectExt.STRING_ARRAY_TYPE:
			{
				((String[])objValue)[nIndex]=(String)obj;
				return;
			}
			case ObjectExt.DATE_ARRAY_TYPE:
			{
				((String[])objValue)[nIndex]=(String)obj;
				return;
			}
			case ObjectExt.TIME_ARRAY_TYPE:
			{
				((String[])objValue)[nIndex]=(String)obj;
				return;
			}
			case ObjectExt.DATETIME_ARRAY_TYPE:
			{
				((String[])objValue)[nIndex]=(String)obj;
				return;
			}
			case ObjectExt.CDO_ARRAY_TYPE:
			{
				((CDO[])objValue)[nIndex]=(CDO)obj;
				return;
			}
		}
		throw new RuntimeException("Type cast failed");	
	}

	//接口实现,所有实现接口函数的实现在此定义--------------------------------------------------------------------

	//事件处理,所有重载派生类的事件类方法(一般为on...ed)在此定义-------------------------------------------------

	//事件定义,所有在本类中定义并调用，由派生类实现或重载的事件类方法(一般为on...ed)在此定义---------------------

	//构造函数,所有构造函数在此定义------------------------------------------------------------------------------

	public ObjectExt()
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		this.objValue	=null;
		this.nType	=NONE_TYPE;
	}

	public ObjectExt(int nType,Object object)
	{

		//请在此加入初始化代码,内部对象和属性对象负责创建或赋初值,引用对象初始化为null，初始化完成后在设置各对象之间的关系
		this.objValue	=object;
		this.nType	=nType;
	}
}
