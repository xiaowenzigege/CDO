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
 * Class TransCacheType.
 * 
 * @version $Revision$ $Date$
 */
public class TransCacheType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _cacheId
     */
    private java.lang.String _cacheId;

    /**
     * Field _expireTime
     */
    private int _expireTime;

    /**
     * keeps track of state for field: _expireTime
     */
    private boolean _has_expireTime;

    /**
     * Field _requestCondition
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.RequestCondition _requestCondition;

    /**
     * Field _removeKeyList
     */
    private java.util.ArrayList _removeKeyList;

    /**
     * Field _cacheKey
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.CacheKey _cacheKey;

    /**
     * Field _cacheValue
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.CacheValue _cacheValue;


      //----------------/
     //- Constructors -/
    //----------------/

    public TransCacheType() 
     {
        super();
        _removeKeyList = new ArrayList();
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.TransCacheType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addRemoveKey
     * 
     * 
     * 
     * @param vRemoveKey
     */
    public void addRemoveKey(java.lang.String vRemoveKey)
        throws java.lang.IndexOutOfBoundsException
    {
        _removeKeyList.add(vRemoveKey);
    } //-- void addRemoveKey(java.lang.String) 

    /**
     * Method addRemoveKey
     * 
     * 
     * 
     * @param index
     * @param vRemoveKey
     */
    public void addRemoveKey(int index, java.lang.String vRemoveKey)
        throws java.lang.IndexOutOfBoundsException
    {
        _removeKeyList.add(index, vRemoveKey);
    } //-- void addRemoveKey(int, java.lang.String) 

    /**
     * Method clearRemoveKey
     * 
     */
    public void clearRemoveKey()
    {
        _removeKeyList.clear();
    } //-- void clearRemoveKey() 

    /**
     * Method deleteExpireTime
     * 
     */
    public void deleteExpireTime()
    {
        this._has_expireTime= false;
    } //-- void deleteExpireTime() 

    /**
     * Method enumerateRemoveKey
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateRemoveKey()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_removeKeyList.iterator());
    } //-- java.util.Enumeration enumerateRemoveKey() 

    /**
     * Returns the value of field 'cacheId'.
     * 
     * @return String
     * @return the value of field 'cacheId'.
     */
    public java.lang.String getCacheId()
    {
        return this._cacheId;
    } //-- java.lang.String getCacheId() 

    /**
     * Returns the value of field 'cacheKey'.
     * 
     * @return CacheKey
     * @return the value of field 'cacheKey'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.CacheKey getCacheKey()
    {
        return this._cacheKey;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.CacheKey getCacheKey() 

    /**
     * Returns the value of field 'cacheValue'.
     * 
     * @return CacheValue
     * @return the value of field 'cacheValue'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.CacheValue getCacheValue()
    {
        return this._cacheValue;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.CacheValue getCacheValue() 

    /**
     * Returns the value of field 'expireTime'.
     * 
     * @return int
     * @return the value of field 'expireTime'.
     */
    public int getExpireTime()
    {
        return this._expireTime;
    } //-- int getExpireTime() 

    /**
     * Method getRemoveKey
     * 
     * 
     * 
     * @param index
     * @return String
     */
    public java.lang.String getRemoveKey(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _removeKeyList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (String)_removeKeyList.get(index);
    } //-- java.lang.String getRemoveKey(int) 

    /**
     * Method getRemoveKey
     * 
     * 
     * 
     * @return String
     */
    public java.lang.String[] getRemoveKey()
    {
        int size = _removeKeyList.size();
        java.lang.String[] mArray = new java.lang.String[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (String)_removeKeyList.get(index);
        }
        return mArray;
    } //-- java.lang.String[] getRemoveKey() 

    /**
     * Method getRemoveKeyCount
     * 
     * 
     * 
     * @return int
     */
    public int getRemoveKeyCount()
    {
        return _removeKeyList.size();
    } //-- int getRemoveKeyCount() 

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
     * Method hasExpireTime
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasExpireTime()
    {
        return this._has_expireTime;
    } //-- boolean hasExpireTime() 

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
     * Method removeRemoveKey
     * 
     * 
     * 
     * @param vRemoveKey
     * @return boolean
     */
    public boolean removeRemoveKey(java.lang.String vRemoveKey)
    {
        boolean removed = _removeKeyList.remove(vRemoveKey);
        return removed;
    } //-- boolean removeRemoveKey(java.lang.String) 

    /**
     * Sets the value of field 'cacheId'.
     * 
     * @param cacheId the value of field 'cacheId'.
     */
    public void setCacheId(java.lang.String cacheId)
    {
        this._cacheId = cacheId;
    } //-- void setCacheId(java.lang.String) 

    /**
     * Sets the value of field 'cacheKey'.
     * 
     * @param cacheKey the value of field 'cacheKey'.
     */
    public void setCacheKey(com.cdoframework.cdolib.service.framework.transfilter.schema.CacheKey cacheKey)
    {
        this._cacheKey = cacheKey;
    } //-- void setCacheKey(com.cdoframework.cdolib.service.framework.transfilter.schema.CacheKey) 

    /**
     * Sets the value of field 'cacheValue'.
     * 
     * @param cacheValue the value of field 'cacheValue'.
     */
    public void setCacheValue(com.cdoframework.cdolib.service.framework.transfilter.schema.CacheValue cacheValue)
    {
        this._cacheValue = cacheValue;
    } //-- void setCacheValue(com.cdoframework.cdolib.service.framework.transfilter.schema.CacheValue) 

    /**
     * Sets the value of field 'expireTime'.
     * 
     * @param expireTime the value of field 'expireTime'.
     */
    public void setExpireTime(int expireTime)
    {
        this._expireTime = expireTime;
        this._has_expireTime = true;
    } //-- void setExpireTime(int) 

    /**
     * Method setRemoveKey
     * 
     * 
     * 
     * @param index
     * @param vRemoveKey
     */
    public void setRemoveKey(int index, java.lang.String vRemoveKey)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _removeKeyList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _removeKeyList.set(index, vRemoveKey);
    } //-- void setRemoveKey(int, java.lang.String) 

    /**
     * Method setRemoveKey
     * 
     * 
     * 
     * @param removeKeyArray
     */
    public void setRemoveKey(java.lang.String[] removeKeyArray)
    {
        //-- copy array
        _removeKeyList.clear();
        for (int i = 0; i < removeKeyArray.length; i++) {
            _removeKeyList.add(removeKeyArray[i]);
        }
    } //-- void setRemoveKey(java.lang.String) 

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
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return TransCacheType
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.schema.TransCacheType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.TransCacheType) Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.schema.TransCacheType.class, reader);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.TransCacheType unmarshal(java.io.Reader) 

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
