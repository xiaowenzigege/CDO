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
import java.io.Writer;
import java.util.ArrayList;
import java.util.Enumeration;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

/**
 * Class BigTableGroup.
 * 
 * @version $Revision$ $Date$
 */
public class BigTableGroup implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _id
     */
    private java.lang.String _id = "";

    /**
     * Field _bigTableList
     */
    private java.util.ArrayList _bigTableList;


      //----------------/
     //- Constructors -/
    //----------------/

    public BigTableGroup() 
     {
        super();
        setId("");
        _bigTableList = new ArrayList();
    } //-- com.cdoframework.cdolib.database.dataservice.BigTableGroup()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addBigTable
     * 
     * 
     * 
     * @param vBigTable
     */
    public void addBigTable(com.cdoframework.cdolib.database.dataservice.BigTable vBigTable)
        throws java.lang.IndexOutOfBoundsException
    {
        _bigTableList.add(vBigTable);
    } //-- void addBigTable(com.cdoframework.cdolib.database.dataservice.BigTable) 

    /**
     * Method addBigTable
     * 
     * 
     * 
     * @param index
     * @param vBigTable
     */
    public void addBigTable(int index, com.cdoframework.cdolib.database.dataservice.BigTable vBigTable)
        throws java.lang.IndexOutOfBoundsException
    {
        _bigTableList.add(index, vBigTable);
    } //-- void addBigTable(int, com.cdoframework.cdolib.database.dataservice.BigTable) 

    /**
     * Method clearBigTable
     * 
     */
    public void clearBigTable()
    {
        _bigTableList.clear();
    } //-- void clearBigTable() 

    /**
     * Method enumerateBigTable
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateBigTable()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_bigTableList.iterator());
    } //-- java.util.Enumeration enumerateBigTable() 

    /**
     * Method getBigTable
     * 
     * 
     * 
     * @param index
     * @return BigTable
     */
    public com.cdoframework.cdolib.database.dataservice.BigTable getBigTable(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _bigTableList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.database.dataservice.BigTable) _bigTableList.get(index);
    } //-- com.cdoframework.cdolib.database.dataservice.BigTable getBigTable(int) 

    /**
     * Method getBigTable
     * 
     * 
     * 
     * @return BigTable
     */
    public com.cdoframework.cdolib.database.dataservice.BigTable[] getBigTable()
    {
        int size = _bigTableList.size();
        com.cdoframework.cdolib.database.dataservice.BigTable[] mArray = new com.cdoframework.cdolib.database.dataservice.BigTable[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.database.dataservice.BigTable) _bigTableList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.database.dataservice.BigTable[] getBigTable() 

    /**
     * Method getBigTableCount
     * 
     * 
     * 
     * @return int
     */
    public int getBigTableCount()
    {
        return _bigTableList.size();
    } //-- int getBigTableCount() 

    /**
     * Returns the value of field 'id'.
     * 
     * @return String
     * @return the value of field 'id'.
     */
    public java.lang.String getId()
    {
        return this._id;
    } //-- java.lang.String getId() 

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
     * Method removeBigTable
     * 
     * 
     * 
     * @param vBigTable
     * @return boolean
     */
    public boolean removeBigTable(com.cdoframework.cdolib.database.dataservice.BigTable vBigTable)
    {
        boolean removed = _bigTableList.remove(vBigTable);
        return removed;
    } //-- boolean removeBigTable(com.cdoframework.cdolib.database.dataservice.BigTable) 

    /**
     * Method setBigTable
     * 
     * 
     * 
     * @param index
     * @param vBigTable
     */
    public void setBigTable(int index, com.cdoframework.cdolib.database.dataservice.BigTable vBigTable)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _bigTableList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _bigTableList.set(index, vBigTable);
    } //-- void setBigTable(int, com.cdoframework.cdolib.database.dataservice.BigTable) 

    /**
     * Method setBigTable
     * 
     * 
     * 
     * @param bigTableArray
     */
    public void setBigTable(com.cdoframework.cdolib.database.dataservice.BigTable[] bigTableArray)
    {
        //-- copy array
        _bigTableList.clear();
        for (int i = 0; i < bigTableArray.length; i++) {
            _bigTableList.add(bigTableArray[i]);
        }
    } //-- void setBigTable(com.cdoframework.cdolib.database.dataservice.BigTable) 

    /**
     * Sets the value of field 'id'.
     * 
     * @param id the value of field 'id'.
     */
    public void setId(java.lang.String id)
    {
        this._id = id;
    } //-- void setId(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return BigTableGroup
     */
    public static com.cdoframework.cdolib.database.dataservice.BigTableGroup unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.database.dataservice.BigTableGroup) Unmarshaller.unmarshal(com.cdoframework.cdolib.database.dataservice.BigTableGroup.class, reader);
    } //-- com.cdoframework.cdolib.database.dataservice.BigTableGroup unmarshal(java.io.Reader) 

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

}
