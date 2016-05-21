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
 * Class Modify.
 * 
 * @version $Revision$ $Date$
 */
public class Modify extends com.cdoframework.cdolib.database.dataservice.CollectionNameType 
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
     * Field _multiLine
     */
    private boolean _multiLine = true;

    /**
     * keeps track of state for field: _multiLine
     */
    private boolean _has_multiLine;

    /**
     * Field _insert
     */
    private boolean _insert = false;

    /**
     * keeps track of state for field: _insert
     */
    private boolean _has_insert;

    /**
     * Field _modifyFieldList
     */
    private java.util.ArrayList _modifyFieldList;

    /**
     * Field _creterias
     */
    private com.cdoframework.cdolib.database.dataservice.Creterias _creterias;


      //----------------/
     //- Constructors -/
    //----------------/

    public Modify() 
     {
        super();
        _modifyFieldList = new ArrayList();
    } //-- com.cdoframework.cdolib.database.dataservice.Modify()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addModifyField
     * 
     * 
     * 
     * @param vModifyField
     */
    public void addModifyField(com.cdoframework.cdolib.database.dataservice.ModifyField vModifyField)
        throws java.lang.IndexOutOfBoundsException
    {
        _modifyFieldList.add(vModifyField);
    } //-- void addModifyField(com.cdoframework.cdolib.database.dataservice.ModifyField) 

    /**
     * Method addModifyField
     * 
     * 
     * 
     * @param index
     * @param vModifyField
     */
    public void addModifyField(int index, com.cdoframework.cdolib.database.dataservice.ModifyField vModifyField)
        throws java.lang.IndexOutOfBoundsException
    {
        _modifyFieldList.add(index, vModifyField);
    } //-- void addModifyField(int, com.cdoframework.cdolib.database.dataservice.ModifyField) 

    /**
     * Method clearModifyField
     * 
     */
    public void clearModifyField()
    {
        _modifyFieldList.clear();
    } //-- void clearModifyField() 

    /**
     * Method deleteInsert
     * 
     */
    public void deleteInsert()
    {
        this._has_insert= false;
    } //-- void deleteInsert() 

    /**
     * Method deleteMultiLine
     * 
     */
    public void deleteMultiLine()
    {
        this._has_multiLine= false;
    } //-- void deleteMultiLine() 

    /**
     * Method enumerateModifyField
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateModifyField()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_modifyFieldList.iterator());
    } //-- java.util.Enumeration enumerateModifyField() 

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
     * Returns the value of field 'insert'.
     * 
     * @return boolean
     * @return the value of field 'insert'.
     */
    public boolean getInsert()
    {
        return this._insert;
    } //-- boolean getInsert() 

    /**
     * Method getModifyField
     * 
     * 
     * 
     * @param index
     * @return ModifyField
     */
    public com.cdoframework.cdolib.database.dataservice.ModifyField getModifyField(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _modifyFieldList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.database.dataservice.ModifyField) _modifyFieldList.get(index);
    } //-- com.cdoframework.cdolib.database.dataservice.ModifyField getModifyField(int) 

    /**
     * Method getModifyField
     * 
     * 
     * 
     * @return ModifyField
     */
    public com.cdoframework.cdolib.database.dataservice.ModifyField[] getModifyField()
    {
        int size = _modifyFieldList.size();
        com.cdoframework.cdolib.database.dataservice.ModifyField[] mArray = new com.cdoframework.cdolib.database.dataservice.ModifyField[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.database.dataservice.ModifyField) _modifyFieldList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.database.dataservice.ModifyField[] getModifyField() 

    /**
     * Method getModifyFieldCount
     * 
     * 
     * 
     * @return int
     */
    public int getModifyFieldCount()
    {
        return _modifyFieldList.size();
    } //-- int getModifyFieldCount() 

    /**
     * Returns the value of field 'multiLine'.
     * 
     * @return boolean
     * @return the value of field 'multiLine'.
     */
    public boolean getMultiLine()
    {
        return this._multiLine;
    } //-- boolean getMultiLine() 

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
     * Method hasInsert
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasInsert()
    {
        return this._has_insert;
    } //-- boolean hasInsert() 

    /**
     * Method hasMultiLine
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasMultiLine()
    {
        return this._has_multiLine;
    } //-- boolean hasMultiLine() 

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
     * Method removeModifyField
     * 
     * 
     * 
     * @param vModifyField
     * @return boolean
     */
    public boolean removeModifyField(com.cdoframework.cdolib.database.dataservice.ModifyField vModifyField)
    {
        boolean removed = _modifyFieldList.remove(vModifyField);
        return removed;
    } //-- boolean removeModifyField(com.cdoframework.cdolib.database.dataservice.ModifyField) 

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
     * Sets the value of field 'insert'.
     * 
     * @param insert the value of field 'insert'.
     */
    public void setInsert(boolean insert)
    {
        this._insert = insert;
        this._has_insert = true;
    } //-- void setInsert(boolean) 

    /**
     * Method setModifyField
     * 
     * 
     * 
     * @param index
     * @param vModifyField
     */
    public void setModifyField(int index, com.cdoframework.cdolib.database.dataservice.ModifyField vModifyField)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _modifyFieldList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _modifyFieldList.set(index, vModifyField);
    } //-- void setModifyField(int, com.cdoframework.cdolib.database.dataservice.ModifyField) 

    /**
     * Method setModifyField
     * 
     * 
     * 
     * @param modifyFieldArray
     */
    public void setModifyField(com.cdoframework.cdolib.database.dataservice.ModifyField[] modifyFieldArray)
    {
        //-- copy array
        _modifyFieldList.clear();
        for (int i = 0; i < modifyFieldArray.length; i++) {
            _modifyFieldList.add(modifyFieldArray[i]);
        }
    } //-- void setModifyField(com.cdoframework.cdolib.database.dataservice.ModifyField) 

    /**
     * Sets the value of field 'multiLine'.
     * 
     * @param multiLine the value of field 'multiLine'.
     */
    public void setMultiLine(boolean multiLine)
    {
        this._multiLine = multiLine;
        this._has_multiLine = true;
    } //-- void setMultiLine(boolean) 

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
        return (com.cdoframework.cdolib.database.dataservice.CollectionNameType) Unmarshaller.unmarshal(com.cdoframework.cdolib.database.dataservice.Modify.class, reader);
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
