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
 * Class Creteria.
 * 
 * @version $Revision$ $Date$
 */
public class Creteria extends com.cdoframework.cdolib.database.dataservice.CriteriaType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _name
     */
    private java.lang.String _name;

    /**
     * Field _valueRequired
     */
    private boolean _valueRequired = false;

    /**
     * keeps track of state for field: _valueRequired
     */
    private boolean _has_valueRequired;

    /**
     * Field _andList
     */
    private java.util.ArrayList _andList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Creteria() 
     {
        super();
        _andList = new ArrayList();
    } //-- com.cdoframework.cdolib.database.dataservice.Creteria()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addAnd
     * 
     * 
     * 
     * @param vAnd
     */
    public void addAnd(com.cdoframework.cdolib.database.dataservice.And vAnd)
        throws java.lang.IndexOutOfBoundsException
    {
        _andList.add(vAnd);
    } //-- void addAnd(com.cdoframework.cdolib.database.dataservice.And) 

    /**
     * Method addAnd
     * 
     * 
     * 
     * @param index
     * @param vAnd
     */
    public void addAnd(int index, com.cdoframework.cdolib.database.dataservice.And vAnd)
        throws java.lang.IndexOutOfBoundsException
    {
        _andList.add(index, vAnd);
    } //-- void addAnd(int, com.cdoframework.cdolib.database.dataservice.And) 

    /**
     * Method clearAnd
     * 
     */
    public void clearAnd()
    {
        _andList.clear();
    } //-- void clearAnd() 

    /**
     * Method deleteValueRequired
     * 
     */
    public void deleteValueRequired()
    {
        this._has_valueRequired= false;
    } //-- void deleteValueRequired() 

    /**
     * Method enumerateAnd
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateAnd()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_andList.iterator());
    } //-- java.util.Enumeration enumerateAnd() 

    /**
     * Method getAnd
     * 
     * 
     * 
     * @param index
     * @return And
     */
    public com.cdoframework.cdolib.database.dataservice.And getAnd(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _andList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.database.dataservice.And) _andList.get(index);
    } //-- com.cdoframework.cdolib.database.dataservice.And getAnd(int) 

    /**
     * Method getAnd
     * 
     * 
     * 
     * @return And
     */
    public com.cdoframework.cdolib.database.dataservice.And[] getAnd()
    {
        int size = _andList.size();
        com.cdoframework.cdolib.database.dataservice.And[] mArray = new com.cdoframework.cdolib.database.dataservice.And[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.database.dataservice.And) _andList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.database.dataservice.And[] getAnd() 

    /**
     * Method getAndCount
     * 
     * 
     * 
     * @return int
     */
    public int getAndCount()
    {
        return _andList.size();
    } //-- int getAndCount() 

    /**
     * Returns the value of field 'name'.
     * 
     * @return String
     * @return the value of field 'name'.
     */
    public java.lang.String getName()
    {
        return this._name;
    } //-- java.lang.String getName() 

    /**
     * Returns the value of field 'valueRequired'.
     * 
     * @return boolean
     * @return the value of field 'valueRequired'.
     */
    public boolean getValueRequired()
    {
        return this._valueRequired;
    } //-- boolean getValueRequired() 

    /**
     * Method hasValueRequired
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasValueRequired()
    {
        return this._has_valueRequired;
    } //-- boolean hasValueRequired() 

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
     * Method removeAnd
     * 
     * 
     * 
     * @param vAnd
     * @return boolean
     */
    public boolean removeAnd(com.cdoframework.cdolib.database.dataservice.And vAnd)
    {
        boolean removed = _andList.remove(vAnd);
        return removed;
    } //-- boolean removeAnd(com.cdoframework.cdolib.database.dataservice.And) 

    /**
     * Method setAnd
     * 
     * 
     * 
     * @param index
     * @param vAnd
     */
    public void setAnd(int index, com.cdoframework.cdolib.database.dataservice.And vAnd)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _andList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _andList.set(index, vAnd);
    } //-- void setAnd(int, com.cdoframework.cdolib.database.dataservice.And) 

    /**
     * Method setAnd
     * 
     * 
     * 
     * @param andArray
     */
    public void setAnd(com.cdoframework.cdolib.database.dataservice.And[] andArray)
    {
        //-- copy array
        _andList.clear();
        for (int i = 0; i < andArray.length; i++) {
            _andList.add(andArray[i]);
        }
    } //-- void setAnd(com.cdoframework.cdolib.database.dataservice.And) 

    /**
     * Sets the value of field 'name'.
     * 
     * @param name the value of field 'name'.
     */
    public void setName(java.lang.String name)
    {
        this._name = name;
    } //-- void setName(java.lang.String) 

    /**
     * Sets the value of field 'valueRequired'.
     * 
     * @param valueRequired the value of field 'valueRequired'.
     */
    public void setValueRequired(boolean valueRequired)
    {
        this._valueRequired = valueRequired;
        this._has_valueRequired = true;
    } //-- void setValueRequired(boolean) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return CriteriaType
     */
    public static com.cdoframework.cdolib.database.dataservice.CriteriaType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.database.dataservice.CriteriaType) Unmarshaller.unmarshal(com.cdoframework.cdolib.database.dataservice.Creteria.class, reader);
    } //-- com.cdoframework.cdolib.database.dataservice.CriteriaType unmarshal(java.io.Reader) 

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
