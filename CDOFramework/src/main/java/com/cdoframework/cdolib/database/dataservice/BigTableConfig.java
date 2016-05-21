/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.database.dataservice;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Enumeration;

import org.apache.log4j.Logger;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

import com.cdoframework.cdolib.servicebus.schema.ServiceBus;

/**
 * Class BigTableConfig.
 * 
 * @version $Revision$ $Date$
 */
public class BigTableConfig implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _bigTableGroupList
     */
    private java.util.ArrayList _bigTableGroupList;


      //----------------/
     //- Constructors -/
    //----------------/

    public BigTableConfig() 
     {
        super();
        _bigTableGroupList = new ArrayList();
    } //-- com.cdoframework.cdolib.database.dataservice.BigTableConfig()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addBigTableGroup
     * 
     * 
     * 
     * @param vBigTableGroup
     */
    public void addBigTableGroup(com.cdoframework.cdolib.database.dataservice.BigTableGroup vBigTableGroup)
        throws java.lang.IndexOutOfBoundsException
    {
        _bigTableGroupList.add(vBigTableGroup);
    } //-- void addBigTableGroup(com.cdoframework.cdolib.database.dataservice.BigTableGroup) 

    /**
     * Method addBigTableGroup
     * 
     * 
     * 
     * @param index
     * @param vBigTableGroup
     */
    public void addBigTableGroup(int index, com.cdoframework.cdolib.database.dataservice.BigTableGroup vBigTableGroup)
        throws java.lang.IndexOutOfBoundsException
    {
        _bigTableGroupList.add(index, vBigTableGroup);
    } //-- void addBigTableGroup(int, com.cdoframework.cdolib.database.dataservice.BigTableGroup) 

    /**
     * Method clearBigTableGroup
     * 
     */
    public void clearBigTableGroup()
    {
        _bigTableGroupList.clear();
    } //-- void clearBigTableGroup() 

    /**
     * Method enumerateBigTableGroup
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateBigTableGroup()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_bigTableGroupList.iterator());
    } //-- java.util.Enumeration enumerateBigTableGroup() 

    /**
     * Method getBigTableGroup
     * 
     * 
     * 
     * @param index
     * @return BigTableGroup
     */
    public com.cdoframework.cdolib.database.dataservice.BigTableGroup getBigTableGroup(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _bigTableGroupList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.database.dataservice.BigTableGroup) _bigTableGroupList.get(index);
    } //-- com.cdoframework.cdolib.database.dataservice.BigTableGroup getBigTableGroup(int) 

    /**
     * Method getBigTableGroup
     * 
     * 
     * 
     * @return BigTableGroup
     */
    public com.cdoframework.cdolib.database.dataservice.BigTableGroup[] getBigTableGroup()
    {
        int size = _bigTableGroupList.size();
        com.cdoframework.cdolib.database.dataservice.BigTableGroup[] mArray = new com.cdoframework.cdolib.database.dataservice.BigTableGroup[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.database.dataservice.BigTableGroup) _bigTableGroupList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.database.dataservice.BigTableGroup[] getBigTableGroup() 

    /**
     * Method getBigTableGroupCount
     * 
     * 
     * 
     * @return int
     */
    public int getBigTableGroupCount()
    {
        return _bigTableGroupList.size();
    } //-- int getBigTableGroupCount() 

    /**
     * Method isValid
     * 
     * 
     * 
     * @return boolean
     */
    public boolean isValid()
    {
        try {
            validate();
        }
        catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    } //-- boolean isValid() 

    /**
     * Method marshal
     * 
     * 
     * 
     * @param out
     */
    public void marshal(java.io.Writer out)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, out);
    } //-- void marshal(java.io.Writer) 

    /**
     * Method marshal
     * 
     * 
     * 
     * @param handler
     */
    public void marshal(org.xml.sax.ContentHandler handler)
        throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, handler);
    } //-- void marshal(org.xml.sax.ContentHandler) 

    /**
     * Method removeBigTableGroup
     * 
     * 
     * 
     * @param vBigTableGroup
     * @return boolean
     */
    public boolean removeBigTableGroup(com.cdoframework.cdolib.database.dataservice.BigTableGroup vBigTableGroup)
    {
        boolean removed = _bigTableGroupList.remove(vBigTableGroup);
        return removed;
    } //-- boolean removeBigTableGroup(com.cdoframework.cdolib.database.dataservice.BigTableGroup) 

    /**
     * Method setBigTableGroup
     * 
     * 
     * 
     * @param index
     * @param vBigTableGroup
     */
    public void setBigTableGroup(int index, com.cdoframework.cdolib.database.dataservice.BigTableGroup vBigTableGroup)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _bigTableGroupList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _bigTableGroupList.set(index, vBigTableGroup);
    } //-- void setBigTableGroup(int, com.cdoframework.cdolib.database.dataservice.BigTableGroup) 

    /**
     * Method setBigTableGroup
     * 
     * 
     * 
     * @param bigTableGroupArray
     */
    public void setBigTableGroup(com.cdoframework.cdolib.database.dataservice.BigTableGroup[] bigTableGroupArray)
    {
        //-- copy array
        _bigTableGroupList.clear();
        for (int i = 0; i < bigTableGroupArray.length; i++) {
            _bigTableGroupList.add(bigTableGroupArray[i]);
        }
    } //-- void setBigTableGroup(com.cdoframework.cdolib.database.dataservice.BigTableGroup) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return BigTableConfig
     */
    public static com.cdoframework.cdolib.database.dataservice.BigTableConfig unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.database.dataservice.BigTableConfig) Unmarshaller.unmarshal(com.cdoframework.cdolib.database.dataservice.BigTableConfig.class, reader);
    } //-- com.cdoframework.cdolib.database.dataservice.BigTableConfig unmarshal(java.io.Reader) 

    /**
     * Method validate
     * 
     */
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

    static Logger logger = Logger.getLogger(BigTableConfig.class);
    /**
     * ****************************************************************************************
     * 从一个XML字件串解析成BusinessManager对象
     * @param strXML
     * @return
     */
    public static BigTableConfig   fromXML(java.lang.String strXML) throws Exception
    {
    	StringReader reader=null;
    	try
    	{
    		reader=new StringReader(strXML);
    		BigTableConfig bigTableConfig = (BigTableConfig)BigTableConfig.unmarshal(reader);
    		return bigTableConfig;
    	}
    	finally
    	{
    		if(reader!=null)
    		{
    			try
    			{
    				reader.close();
    			}
    			catch(Exception e)
    			{//TODO 日志
    				logger.error("bigTableConfig fromXML:"+e.getMessage(),e);
    			}
    		}
    	}
    }    
}
