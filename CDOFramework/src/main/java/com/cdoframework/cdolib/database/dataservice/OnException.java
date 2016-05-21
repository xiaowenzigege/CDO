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
 * Class OnException.
 * 
 * @version $Revision$ $Date$
 */
public class OnException implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _onErrorList
     */
    private java.util.ArrayList _onErrorList;

    /**
     * Field _return
     */
    private com.cdoframework.cdolib.database.dataservice.Return _return;


      //----------------/
     //- Constructors -/
    //----------------/

    public OnException() 
     {
        super();
        _onErrorList = new ArrayList();
    } //-- com.cdoframework.cdolib.database.dataservice.OnException()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addOnError
     * 
     * 
     * 
     * @param vOnError
     */
    public void addOnError(com.cdoframework.cdolib.database.dataservice.OnError vOnError)
        throws java.lang.IndexOutOfBoundsException
    {
        _onErrorList.add(vOnError);
    } //-- void addOnError(com.cdoframework.cdolib.database.dataservice.OnError) 

    /**
     * Method addOnError
     * 
     * 
     * 
     * @param index
     * @param vOnError
     */
    public void addOnError(int index, com.cdoframework.cdolib.database.dataservice.OnError vOnError)
        throws java.lang.IndexOutOfBoundsException
    {
        _onErrorList.add(index, vOnError);
    } //-- void addOnError(int, com.cdoframework.cdolib.database.dataservice.OnError) 

    /**
     * Method clearOnError
     * 
     */
    public void clearOnError()
    {
        _onErrorList.clear();
    } //-- void clearOnError() 

    /**
     * Method enumerateOnError
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateOnError()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_onErrorList.iterator());
    } //-- java.util.Enumeration enumerateOnError() 

    /**
     * Method getOnError
     * 
     * 
     * 
     * @param index
     * @return OnError
     */
    public com.cdoframework.cdolib.database.dataservice.OnError getOnError(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _onErrorList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.database.dataservice.OnError) _onErrorList.get(index);
    } //-- com.cdoframework.cdolib.database.dataservice.OnError getOnError(int) 

    /**
     * Method getOnError
     * 
     * 
     * 
     * @return OnError
     */
    public com.cdoframework.cdolib.database.dataservice.OnError[] getOnError()
    {
        int size = _onErrorList.size();
        com.cdoframework.cdolib.database.dataservice.OnError[] mArray = new com.cdoframework.cdolib.database.dataservice.OnError[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.database.dataservice.OnError) _onErrorList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.database.dataservice.OnError[] getOnError() 

    /**
     * Method getOnErrorCount
     * 
     * 
     * 
     * @return int
     */
    public int getOnErrorCount()
    {
        return _onErrorList.size();
    } //-- int getOnErrorCount() 

    /**
     * Returns the value of field 'return'.
     * 
     * @return Return
     * @return the value of field 'return'.
     */
    public com.cdoframework.cdolib.database.dataservice.Return getReturn()
    {
        return this._return;
    } //-- com.cdoframework.cdolib.database.dataservice.Return getReturn() 

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
     * Method removeOnError
     * 
     * 
     * 
     * @param vOnError
     * @return boolean
     */
    public boolean removeOnError(com.cdoframework.cdolib.database.dataservice.OnError vOnError)
    {
        boolean removed = _onErrorList.remove(vOnError);
        return removed;
    } //-- boolean removeOnError(com.cdoframework.cdolib.database.dataservice.OnError) 

    /**
     * Method setOnError
     * 
     * 
     * 
     * @param index
     * @param vOnError
     */
    public void setOnError(int index, com.cdoframework.cdolib.database.dataservice.OnError vOnError)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _onErrorList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _onErrorList.set(index, vOnError);
    } //-- void setOnError(int, com.cdoframework.cdolib.database.dataservice.OnError) 

    /**
     * Method setOnError
     * 
     * 
     * 
     * @param onErrorArray
     */
    public void setOnError(com.cdoframework.cdolib.database.dataservice.OnError[] onErrorArray)
    {
        //-- copy array
        _onErrorList.clear();
        for (int i = 0; i < onErrorArray.length; i++) {
            _onErrorList.add(onErrorArray[i]);
        }
    } //-- void setOnError(com.cdoframework.cdolib.database.dataservice.OnError) 

    /**
     * Sets the value of field 'return'.
     * 
     * @param _return
     * @param return the value of field 'return'.
     */
    public void setReturn(com.cdoframework.cdolib.database.dataservice.Return _return)
    {
        this._return = _return;
    } //-- void setReturn(com.cdoframework.cdolib.database.dataservice.Return) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return OnException
     */
    public static com.cdoframework.cdolib.database.dataservice.OnException unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.database.dataservice.OnException) Unmarshaller.unmarshal(com.cdoframework.cdolib.database.dataservice.OnException.class, reader);
    } //-- com.cdoframework.cdolib.database.dataservice.OnException unmarshal(java.io.Reader) 

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
