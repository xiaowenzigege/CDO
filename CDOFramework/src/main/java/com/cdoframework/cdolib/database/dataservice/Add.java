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
 * Class Add.
 * 
 * @version $Revision$ $Date$
 */
public class Add extends com.cdoframework.cdolib.database.dataservice.CollectionNameType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _addFieldList
     */
    private java.util.ArrayList _addFieldList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Add() 
     {
        super();
        _addFieldList = new ArrayList();
    } //-- com.cdoframework.cdolib.database.dataservice.Add()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addAddField
     * 
     * 
     * 
     * @param vAddField
     */
    public void addAddField(com.cdoframework.cdolib.database.dataservice.AddField vAddField)
        throws java.lang.IndexOutOfBoundsException
    {
        _addFieldList.add(vAddField);
    } //-- void addAddField(com.cdoframework.cdolib.database.dataservice.AddField) 

    /**
     * Method addAddField
     * 
     * 
     * 
     * @param index
     * @param vAddField
     */
    public void addAddField(int index, com.cdoframework.cdolib.database.dataservice.AddField vAddField)
        throws java.lang.IndexOutOfBoundsException
    {
        _addFieldList.add(index, vAddField);
    } //-- void addAddField(int, com.cdoframework.cdolib.database.dataservice.AddField) 

    /**
     * Method clearAddField
     * 
     */
    public void clearAddField()
    {
        _addFieldList.clear();
    } //-- void clearAddField() 

    /**
     * Method enumerateAddField
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateAddField()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_addFieldList.iterator());
    } //-- java.util.Enumeration enumerateAddField() 

    /**
     * Method getAddField
     * 
     * 
     * 
     * @param index
     * @return AddField
     */
    public com.cdoframework.cdolib.database.dataservice.AddField getAddField(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _addFieldList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.database.dataservice.AddField) _addFieldList.get(index);
    } //-- com.cdoframework.cdolib.database.dataservice.AddField getAddField(int) 

    /**
     * Method getAddField
     * 
     * 
     * 
     * @return AddField
     */
    public com.cdoframework.cdolib.database.dataservice.AddField[] getAddField()
    {
        int size = _addFieldList.size();
        com.cdoframework.cdolib.database.dataservice.AddField[] mArray = new com.cdoframework.cdolib.database.dataservice.AddField[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.database.dataservice.AddField) _addFieldList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.database.dataservice.AddField[] getAddField() 

    /**
     * Method getAddFieldCount
     * 
     * 
     * 
     * @return int
     */
    public int getAddFieldCount()
    {
        return _addFieldList.size();
    } //-- int getAddFieldCount() 

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
     * Method removeAddField
     * 
     * 
     * 
     * @param vAddField
     * @return boolean
     */
    public boolean removeAddField(com.cdoframework.cdolib.database.dataservice.AddField vAddField)
    {
        boolean removed = _addFieldList.remove(vAddField);
        return removed;
    } //-- boolean removeAddField(com.cdoframework.cdolib.database.dataservice.AddField) 

    /**
     * Method setAddField
     * 
     * 
     * 
     * @param index
     * @param vAddField
     */
    public void setAddField(int index, com.cdoframework.cdolib.database.dataservice.AddField vAddField)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _addFieldList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _addFieldList.set(index, vAddField);
    } //-- void setAddField(int, com.cdoframework.cdolib.database.dataservice.AddField) 

    /**
     * Method setAddField
     * 
     * 
     * 
     * @param addFieldArray
     */
    public void setAddField(com.cdoframework.cdolib.database.dataservice.AddField[] addFieldArray)
    {
        //-- copy array
        _addFieldList.clear();
        for (int i = 0; i < addFieldArray.length; i++) {
            _addFieldList.add(addFieldArray[i]);
        }
    } //-- void setAddField(com.cdoframework.cdolib.database.dataservice.AddField) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return CollectionNameType
     */
    public static com.cdoframework.cdolib.database.dataservice.CollectionNameType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.database.dataservice.CollectionNameType) Unmarshaller.unmarshal(com.cdoframework.cdolib.database.dataservice.Add.class, reader);
    } //-- com.cdoframework.cdolib.database.dataservice.CollectionNameType unmarshal(java.io.Reader) 

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
