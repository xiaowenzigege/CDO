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
 * Class PostPushCache.
 * 
 * @version $Revision$ $Date$
 */
public class PostPushCache implements java.io.Serializable {


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
     * Field _pushCacheList
     */
    private java.util.ArrayList _pushCacheList;


      //----------------/
     //- Constructors -/
    //----------------/

    public PostPushCache() 
     {
        super();
        _pushCacheList = new ArrayList();
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PostPushCache()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addPushCache
     * 
     * 
     * 
     * @param vPushCache
     */
    public void addPushCache(com.cdoframework.cdolib.service.framework.transfilter.schema.PushCache vPushCache)
        throws java.lang.IndexOutOfBoundsException
    {
        _pushCacheList.add(vPushCache);
    } //-- void addPushCache(com.cdoframework.cdolib.service.framework.transfilter.schema.PushCache) 

    /**
     * Method addPushCache
     * 
     * 
     * 
     * @param index
     * @param vPushCache
     */
    public void addPushCache(int index, com.cdoframework.cdolib.service.framework.transfilter.schema.PushCache vPushCache)
        throws java.lang.IndexOutOfBoundsException
    {
        _pushCacheList.add(index, vPushCache);
    } //-- void addPushCache(int, com.cdoframework.cdolib.service.framework.transfilter.schema.PushCache) 

    /**
     * Method clearPushCache
     * 
     */
    public void clearPushCache()
    {
        _pushCacheList.clear();
    } //-- void clearPushCache() 

    /**
     * Method deleteSyn
     * 
     */
    public void deleteSyn()
    {
        this._has_syn= false;
    } //-- void deleteSyn() 

    /**
     * Method enumeratePushCache
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumeratePushCache()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_pushCacheList.iterator());
    } //-- java.util.Enumeration enumeratePushCache() 

    /**
     * Method getPushCache
     * 
     * 
     * 
     * @param index
     * @return PushCache
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.PushCache getPushCache(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _pushCacheList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.PushCache) _pushCacheList.get(index);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PushCache getPushCache(int) 

    /**
     * Method getPushCache
     * 
     * 
     * 
     * @return PushCache
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.PushCache[] getPushCache()
    {
        int size = _pushCacheList.size();
        com.cdoframework.cdolib.service.framework.transfilter.schema.PushCache[] mArray = new com.cdoframework.cdolib.service.framework.transfilter.schema.PushCache[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.service.framework.transfilter.schema.PushCache) _pushCacheList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PushCache[] getPushCache() 

    /**
     * Method getPushCacheCount
     * 
     * 
     * 
     * @return int
     */
    public int getPushCacheCount()
    {
        return _pushCacheList.size();
    } //-- int getPushCacheCount() 

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
     * Method removePushCache
     * 
     * 
     * 
     * @param vPushCache
     * @return boolean
     */
    public boolean removePushCache(com.cdoframework.cdolib.service.framework.transfilter.schema.PushCache vPushCache)
    {
        boolean removed = _pushCacheList.remove(vPushCache);
        return removed;
    } //-- boolean removePushCache(com.cdoframework.cdolib.service.framework.transfilter.schema.PushCache) 

    /**
     * Method setPushCache
     * 
     * 
     * 
     * @param index
     * @param vPushCache
     */
    public void setPushCache(int index, com.cdoframework.cdolib.service.framework.transfilter.schema.PushCache vPushCache)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _pushCacheList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _pushCacheList.set(index, vPushCache);
    } //-- void setPushCache(int, com.cdoframework.cdolib.service.framework.transfilter.schema.PushCache) 

    /**
     * Method setPushCache
     * 
     * 
     * 
     * @param pushCacheArray
     */
    public void setPushCache(com.cdoframework.cdolib.service.framework.transfilter.schema.PushCache[] pushCacheArray)
    {
        //-- copy array
        _pushCacheList.clear();
        for (int i = 0; i < pushCacheArray.length; i++) {
            _pushCacheList.add(pushCacheArray[i]);
        }
    } //-- void setPushCache(com.cdoframework.cdolib.service.framework.transfilter.schema.PushCache) 

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
     * @return PostPushCache
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.schema.PostPushCache unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.PostPushCache) Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.schema.PostPushCache.class, reader);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PostPushCache unmarshal(java.io.Reader) 

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
