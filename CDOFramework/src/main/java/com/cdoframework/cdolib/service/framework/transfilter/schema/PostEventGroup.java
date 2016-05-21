/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.service.framework.transfilter.schema;

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
 * Class PostEventGroup.
 * 
 * @version $Revision$ $Date$
 */
public class PostEventGroup implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _syn
     */
    private boolean _syn = false;

    /**
     * keeps track of state for field: _syn
     */
    private boolean _has_syn;

    /**
     * Field _requestCondition
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.RequestCondition _requestCondition;

    /**
     * Field _responseCondition
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.ResponseCondition _responseCondition;

    /**
     * Field _forList
     */
    private java.util.ArrayList _forList;


      //----------------/
     //- Constructors -/
    //----------------/

    public PostEventGroup() 
     {
        super();
        _forList = new ArrayList();
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PostEventGroup()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addFor
     * 
     * 
     * 
     * @param vFor
     */
    public void addFor(com.cdoframework.cdolib.service.framework.transfilter.schema.For vFor)
        throws java.lang.IndexOutOfBoundsException
    {
        _forList.add(vFor);
    } //-- void addFor(com.cdoframework.cdolib.service.framework.transfilter.schema.For) 

    /**
     * Method addFor
     * 
     * 
     * 
     * @param index
     * @param vFor
     */
    public void addFor(int index, com.cdoframework.cdolib.service.framework.transfilter.schema.For vFor)
        throws java.lang.IndexOutOfBoundsException
    {
        _forList.add(index, vFor);
    } //-- void addFor(int, com.cdoframework.cdolib.service.framework.transfilter.schema.For) 

    /**
     * Method clearFor
     * 
     */
    public void clearFor()
    {
        _forList.clear();
    } //-- void clearFor() 

    /**
     * Method deleteSyn
     * 
     */
    public void deleteSyn()
    {
        this._has_syn= false;
    } //-- void deleteSyn() 

    /**
     * Method enumerateFor
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateFor()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_forList.iterator());
    } //-- java.util.Enumeration enumerateFor() 

    /**
     * Method getFor
     * 
     * 
     * 
     * @param index
     * @return For
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.For getFor(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _forList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.For) _forList.get(index);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.For getFor(int) 

    /**
     * Method getFor
     * 
     * 
     * 
     * @return For
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.For[] getFor()
    {
        int size = _forList.size();
        com.cdoframework.cdolib.service.framework.transfilter.schema.For[] mArray = new com.cdoframework.cdolib.service.framework.transfilter.schema.For[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.service.framework.transfilter.schema.For) _forList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.For[] getFor() 

    /**
     * Method getForCount
     * 
     * 
     * 
     * @return int
     */
    public int getForCount()
    {
        return _forList.size();
    } //-- int getForCount() 

    /**
     * Returns the value of field 'requestCondition'.
     * 
     * @return RequestCondition
     * @return the value of field 'requestCondition'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.RequestCondition getRequestCondition()
    {
        return this._requestCondition;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.RequestCondition getRequestCondition() 

    /**
     * Returns the value of field 'responseCondition'.
     * 
     * @return ResponseCondition
     * @return the value of field 'responseCondition'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.ResponseCondition getResponseCondition()
    {
        return this._responseCondition;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.ResponseCondition getResponseCondition() 

    /**
     * Returns the value of field 'syn'.
     * 
     * @return boolean
     * @return the value of field 'syn'.
     */
    public boolean getSyn()
    {
        return this._syn;
    } //-- boolean getSyn() 

    /**
     * Method hasSyn
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasSyn()
    {
        return this._has_syn;
    } //-- boolean hasSyn() 

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
     * Method removeFor
     * 
     * 
     * 
     * @param vFor
     * @return boolean
     */
    public boolean removeFor(com.cdoframework.cdolib.service.framework.transfilter.schema.For vFor)
    {
        boolean removed = _forList.remove(vFor);
        return removed;
    } //-- boolean removeFor(com.cdoframework.cdolib.service.framework.transfilter.schema.For) 

    /**
     * Method setFor
     * 
     * 
     * 
     * @param index
     * @param vFor
     */
    public void setFor(int index, com.cdoframework.cdolib.service.framework.transfilter.schema.For vFor)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _forList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _forList.set(index, vFor);
    } //-- void setFor(int, com.cdoframework.cdolib.service.framework.transfilter.schema.For) 

    /**
     * Method setFor
     * 
     * 
     * 
     * @param _forArray
     */
    public void setFor(com.cdoframework.cdolib.service.framework.transfilter.schema.For[] _forArray)
    {
        //-- copy array
        _forList.clear();
        for (int i = 0; i < _forArray.length; i++) {
            _forList.add(_forArray[i]);
        }
    } //-- void setFor(com.cdoframework.cdolib.service.framework.transfilter.schema.For) 

    /**
     * Sets the value of field 'requestCondition'.
     * 
     * @param requestCondition the value of field 'requestCondition'
     */
    public void setRequestCondition(com.cdoframework.cdolib.service.framework.transfilter.schema.RequestCondition requestCondition)
    {
        this._requestCondition = requestCondition;
    } //-- void setRequestCondition(com.cdoframework.cdolib.service.framework.transfilter.schema.RequestCondition) 

    /**
     * Sets the value of field 'responseCondition'.
     * 
     * @param responseCondition the value of field
     * 'responseCondition'.
     */
    public void setResponseCondition(com.cdoframework.cdolib.service.framework.transfilter.schema.ResponseCondition responseCondition)
    {
        this._responseCondition = responseCondition;
    } //-- void setResponseCondition(com.cdoframework.cdolib.service.framework.transfilter.schema.ResponseCondition) 

    /**
     * Sets the value of field 'syn'.
     * 
     * @param syn the value of field 'syn'.
     */
    public void setSyn(boolean syn)
    {
        this._syn = syn;
        this._has_syn = true;
    } //-- void setSyn(boolean) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return PostEventGroup
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.schema.PostEventGroup unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.PostEventGroup) Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.schema.PostEventGroup.class, reader);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PostEventGroup unmarshal(java.io.Reader) 

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
