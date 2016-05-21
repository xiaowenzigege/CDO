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
 * Class PostRemoveCache.
 * 
 * @version $Revision$ $Date$
 */
public class PostRemoveCache implements java.io.Serializable {


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
     * Field _removeCacheList
     */
    private java.util.ArrayList _removeCacheList;


      //----------------/
     //- Constructors -/
    //----------------/

    public PostRemoveCache() 
     {
        super();
        _removeCacheList = new ArrayList();
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PostRemoveCache()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addRemoveCache
     * 
     * 
     * 
     * @param vRemoveCache
     */
    public void addRemoveCache(com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveCache vRemoveCache)
        throws java.lang.IndexOutOfBoundsException
    {
        _removeCacheList.add(vRemoveCache);
    } //-- void addRemoveCache(com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveCache) 

    /**
     * Method addRemoveCache
     * 
     * 
     * 
     * @param index
     * @param vRemoveCache
     */
    public void addRemoveCache(int index, com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveCache vRemoveCache)
        throws java.lang.IndexOutOfBoundsException
    {
        _removeCacheList.add(index, vRemoveCache);
    } //-- void addRemoveCache(int, com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveCache) 

    /**
     * Method clearRemoveCache
     * 
     */
    public void clearRemoveCache()
    {
        _removeCacheList.clear();
    } //-- void clearRemoveCache() 

    /**
     * Method deleteSyn
     * 
     */
    public void deleteSyn()
    {
        this._has_syn= false;
    } //-- void deleteSyn() 

    /**
     * Method enumerateRemoveCache
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateRemoveCache()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_removeCacheList.iterator());
    } //-- java.util.Enumeration enumerateRemoveCache() 

    /**
     * Method getRemoveCache
     * 
     * 
     * 
     * @param index
     * @return RemoveCache
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveCache getRemoveCache(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _removeCacheList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveCache) _removeCacheList.get(index);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveCache getRemoveCache(int) 

    /**
     * Method getRemoveCache
     * 
     * 
     * 
     * @return RemoveCache
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveCache[] getRemoveCache()
    {
        int size = _removeCacheList.size();
        com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveCache[] mArray = new com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveCache[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveCache) _removeCacheList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveCache[] getRemoveCache() 

    /**
     * Method getRemoveCacheCount
     * 
     * 
     * 
     * @return int
     */
    public int getRemoveCacheCount()
    {
        return _removeCacheList.size();
    } //-- int getRemoveCacheCount() 

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
     * Method removeRemoveCache
     * 
     * 
     * 
     * @param vRemoveCache
     * @return boolean
     */
    public boolean removeRemoveCache(com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveCache vRemoveCache)
    {
        boolean removed = _removeCacheList.remove(vRemoveCache);
        return removed;
    } //-- boolean removeRemoveCache(com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveCache) 

    /**
     * Method setRemoveCache
     * 
     * 
     * 
     * @param index
     * @param vRemoveCache
     */
    public void setRemoveCache(int index, com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveCache vRemoveCache)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _removeCacheList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _removeCacheList.set(index, vRemoveCache);
    } //-- void setRemoveCache(int, com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveCache) 

    /**
     * Method setRemoveCache
     * 
     * 
     * 
     * @param removeCacheArray
     */
    public void setRemoveCache(com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveCache[] removeCacheArray)
    {
        //-- copy array
        _removeCacheList.clear();
        for (int i = 0; i < removeCacheArray.length; i++) {
            _removeCacheList.add(removeCacheArray[i]);
        }
    } //-- void setRemoveCache(com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveCache) 

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
     * @return PostRemoveCache
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.schema.PostRemoveCache unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.PostRemoveCache) Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.schema.PostRemoveCache.class, reader);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PostRemoveCache unmarshal(java.io.Reader) 

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
