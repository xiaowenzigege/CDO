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
 * Class PreRemoveURLCache.
 * 
 * @version $Revision$ $Date$
 */
public class PreRemoveURLCache implements java.io.Serializable {


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
     * Field _removeURLCacheList
     */
    private java.util.ArrayList _removeURLCacheList;


      //----------------/
     //- Constructors -/
    //----------------/

    public PreRemoveURLCache() 
     {
        super();
        _removeURLCacheList = new ArrayList();
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PreRemoveURLCache()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addRemoveURLCache
     * 
     * 
     * 
     * @param vRemoveURLCache
     */
    public void addRemoveURLCache(com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveURLCache vRemoveURLCache)
        throws java.lang.IndexOutOfBoundsException
    {
        _removeURLCacheList.add(vRemoveURLCache);
    } //-- void addRemoveURLCache(com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveURLCache) 

    /**
     * Method addRemoveURLCache
     * 
     * 
     * 
     * @param index
     * @param vRemoveURLCache
     */
    public void addRemoveURLCache(int index, com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveURLCache vRemoveURLCache)
        throws java.lang.IndexOutOfBoundsException
    {
        _removeURLCacheList.add(index, vRemoveURLCache);
    } //-- void addRemoveURLCache(int, com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveURLCache) 

    /**
     * Method clearRemoveURLCache
     * 
     */
    public void clearRemoveURLCache()
    {
        _removeURLCacheList.clear();
    } //-- void clearRemoveURLCache() 

    /**
     * Method deleteSyn
     * 
     */
    public void deleteSyn()
    {
        this._has_syn= false;
    } //-- void deleteSyn() 

    /**
     * Method enumerateRemoveURLCache
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateRemoveURLCache()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_removeURLCacheList.iterator());
    } //-- java.util.Enumeration enumerateRemoveURLCache() 

    /**
     * Method getRemoveURLCache
     * 
     * 
     * 
     * @param index
     * @return RemoveURLCache
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveURLCache getRemoveURLCache(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _removeURLCacheList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveURLCache) _removeURLCacheList.get(index);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveURLCache getRemoveURLCache(int) 

    /**
     * Method getRemoveURLCache
     * 
     * 
     * 
     * @return RemoveURLCache
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveURLCache[] getRemoveURLCache()
    {
        int size = _removeURLCacheList.size();
        com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveURLCache[] mArray = new com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveURLCache[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveURLCache) _removeURLCacheList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveURLCache[] getRemoveURLCache() 

    /**
     * Method getRemoveURLCacheCount
     * 
     * 
     * 
     * @return int
     */
    public int getRemoveURLCacheCount()
    {
        return _removeURLCacheList.size();
    } //-- int getRemoveURLCacheCount() 

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
     * Method removeRemoveURLCache
     * 
     * 
     * 
     * @param vRemoveURLCache
     * @return boolean
     */
    public boolean removeRemoveURLCache(com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveURLCache vRemoveURLCache)
    {
        boolean removed = _removeURLCacheList.remove(vRemoveURLCache);
        return removed;
    } //-- boolean removeRemoveURLCache(com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveURLCache) 

    /**
     * Method setRemoveURLCache
     * 
     * 
     * 
     * @param index
     * @param vRemoveURLCache
     */
    public void setRemoveURLCache(int index, com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveURLCache vRemoveURLCache)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _removeURLCacheList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _removeURLCacheList.set(index, vRemoveURLCache);
    } //-- void setRemoveURLCache(int, com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveURLCache) 

    /**
     * Method setRemoveURLCache
     * 
     * 
     * 
     * @param removeURLCacheArray
     */
    public void setRemoveURLCache(com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveURLCache[] removeURLCacheArray)
    {
        //-- copy array
        _removeURLCacheList.clear();
        for (int i = 0; i < removeURLCacheArray.length; i++) {
            _removeURLCacheList.add(removeURLCacheArray[i]);
        }
    } //-- void setRemoveURLCache(com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveURLCache) 

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
     * @return PreRemoveURLCache
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.schema.PreRemoveURLCache unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.PreRemoveURLCache) Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.schema.PreRemoveURLCache.class, reader);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PreRemoveURLCache unmarshal(java.io.Reader) 

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
