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
 * Class Replace.
 * 
 * @version $Revision$ $Date$
 */
public class Replace extends com.cdoframework.cdolib.database.dataservice.CollectionNameType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _recordCountId
     */
    private java.lang.String _recordCountId;

    /**
     * Field _replaceFieldList
     */
    private java.util.ArrayList _replaceFieldList;

    /**
     * Field _creterias
     */
    private com.cdoframework.cdolib.database.dataservice.Creterias _creterias;


      //----------------/
     //- Constructors -/
    //----------------/

    public Replace() 
     {
        super();
        _replaceFieldList = new ArrayList();
    } //-- com.cdoframework.cdolib.database.dataservice.Replace()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addReplaceField
     * 
     * 
     * 
     * @param vReplaceField
     */
    public void addReplaceField(com.cdoframework.cdolib.database.dataservice.ReplaceField vReplaceField)
        throws java.lang.IndexOutOfBoundsException
    {
        _replaceFieldList.add(vReplaceField);
    } //-- void addReplaceField(com.cdoframework.cdolib.database.dataservice.ReplaceField) 

    /**
     * Method addReplaceField
     * 
     * 
     * 
     * @param index
     * @param vReplaceField
     */
    public void addReplaceField(int index, com.cdoframework.cdolib.database.dataservice.ReplaceField vReplaceField)
        throws java.lang.IndexOutOfBoundsException
    {
        _replaceFieldList.add(index, vReplaceField);
    } //-- void addReplaceField(int, com.cdoframework.cdolib.database.dataservice.ReplaceField) 

    /**
     * Method clearReplaceField
     * 
     */
    public void clearReplaceField()
    {
        _replaceFieldList.clear();
    } //-- void clearReplaceField() 

    /**
     * Method enumerateReplaceField
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateReplaceField()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_replaceFieldList.iterator());
    } //-- java.util.Enumeration enumerateReplaceField() 

    /**
     * Returns the value of field 'creterias'.
     * 
     * @return Creterias
     * @return the value of field 'creterias'.
     */
    public com.cdoframework.cdolib.database.dataservice.Creterias getCreterias()
    {
        return this._creterias;
    } //-- com.cdoframework.cdolib.database.dataservice.Creterias getCreterias() 

    /**
     * Returns the value of field 'recordCountId'.
     * 
     * @return String
     * @return the value of field 'recordCountId'.
     */
    public java.lang.String getRecordCountId()
    {
        return this._recordCountId;
    } //-- java.lang.String getRecordCountId() 

    /**
     * Method getReplaceField
     * 
     * 
     * 
     * @param index
     * @return ReplaceField
     */
    public com.cdoframework.cdolib.database.dataservice.ReplaceField getReplaceField(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _replaceFieldList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.database.dataservice.ReplaceField) _replaceFieldList.get(index);
    } //-- com.cdoframework.cdolib.database.dataservice.ReplaceField getReplaceField(int) 

    /**
     * Method getReplaceField
     * 
     * 
     * 
     * @return ReplaceField
     */
    public com.cdoframework.cdolib.database.dataservice.ReplaceField[] getReplaceField()
    {
        int size = _replaceFieldList.size();
        com.cdoframework.cdolib.database.dataservice.ReplaceField[] mArray = new com.cdoframework.cdolib.database.dataservice.ReplaceField[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.database.dataservice.ReplaceField) _replaceFieldList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.database.dataservice.ReplaceField[] getReplaceField() 

    /**
     * Method getReplaceFieldCount
     * 
     * 
     * 
     * @return int
     */
    public int getReplaceFieldCount()
    {
        return _replaceFieldList.size();
    } //-- int getReplaceFieldCount() 

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
     * Method removeReplaceField
     * 
     * 
     * 
     * @param vReplaceField
     * @return boolean
     */
    public boolean removeReplaceField(com.cdoframework.cdolib.database.dataservice.ReplaceField vReplaceField)
    {
        boolean removed = _replaceFieldList.remove(vReplaceField);
        return removed;
    } //-- boolean removeReplaceField(com.cdoframework.cdolib.database.dataservice.ReplaceField) 

    /**
     * Sets the value of field 'creterias'.
     * 
     * @param creterias the value of field 'creterias'.
     */
    public void setCreterias(com.cdoframework.cdolib.database.dataservice.Creterias creterias)
    {
        this._creterias = creterias;
    } //-- void setCreterias(com.cdoframework.cdolib.database.dataservice.Creterias) 

    /**
     * Sets the value of field 'recordCountId'.
     * 
     * @param recordCountId the value of field 'recordCountId'.
     */
    public void setRecordCountId(java.lang.String recordCountId)
    {
        this._recordCountId = recordCountId;
    } //-- void setRecordCountId(java.lang.String) 

    /**
     * Method setReplaceField
     * 
     * 
     * 
     * @param index
     * @param vReplaceField
     */
    public void setReplaceField(int index, com.cdoframework.cdolib.database.dataservice.ReplaceField vReplaceField)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _replaceFieldList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _replaceFieldList.set(index, vReplaceField);
    } //-- void setReplaceField(int, com.cdoframework.cdolib.database.dataservice.ReplaceField) 

    /**
     * Method setReplaceField
     * 
     * 
     * 
     * @param replaceFieldArray
     */
    public void setReplaceField(com.cdoframework.cdolib.database.dataservice.ReplaceField[] replaceFieldArray)
    {
        //-- copy array
        _replaceFieldList.clear();
        for (int i = 0; i < replaceFieldArray.length; i++) {
            _replaceFieldList.add(replaceFieldArray[i]);
        }
    } //-- void setReplaceField(com.cdoframework.cdolib.database.dataservice.ReplaceField) 

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
        return (com.cdoframework.cdolib.database.dataservice.CollectionNameType) Unmarshaller.unmarshal(com.cdoframework.cdolib.database.dataservice.Replace.class, reader);
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
