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
 * Class PreLoadCache.
 * 
 * @version $Revision$ $Date$
 */
public class PreLoadCache implements java.io.Serializable {


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
     * Field _loadCacheList
     */
    private java.util.ArrayList _loadCacheList;


      //----------------/
     //- Constructors -/
    //----------------/

    public PreLoadCache() 
     {
        super();
        _loadCacheList = new ArrayList();
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PreLoadCache()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addLoadCache
     * 
     * 
     * 
     * @param vLoadCache
     */
    public void addLoadCache(com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCache vLoadCache)
        throws java.lang.IndexOutOfBoundsException
    {
        _loadCacheList.add(vLoadCache);
    } //-- void addLoadCache(com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCache) 

    /**
     * Method addLoadCache
     * 
     * 
     * 
     * @param index
     * @param vLoadCache
     */
    public void addLoadCache(int index, com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCache vLoadCache)
        throws java.lang.IndexOutOfBoundsException
    {
        _loadCacheList.add(index, vLoadCache);
    } //-- void addLoadCache(int, com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCache) 

    /**
     * Method clearLoadCache
     * 
     */
    public void clearLoadCache()
    {
        _loadCacheList.clear();
    } //-- void clearLoadCache() 

    /**
     * Method deleteSyn
     * 
     */
    public void deleteSyn()
    {
        this._has_syn= false;
    } //-- void deleteSyn() 

    /**
     * Method enumerateLoadCache
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateLoadCache()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_loadCacheList.iterator());
    } //-- java.util.Enumeration enumerateLoadCache() 

    /**
     * Method getLoadCache
     * 
     * 
     * 
     * @param index
     * @return LoadCache
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCache getLoadCache(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _loadCacheList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCache) _loadCacheList.get(index);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCache getLoadCache(int) 

    /**
     * Method getLoadCache
     * 
     * 
     * 
     * @return LoadCache
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCache[] getLoadCache()
    {
        int size = _loadCacheList.size();
        com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCache[] mArray = new com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCache[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCache) _loadCacheList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCache[] getLoadCache() 

    /**
     * Method getLoadCacheCount
     * 
     * 
     * 
     * @return int
     */
    public int getLoadCacheCount()
    {
        return _loadCacheList.size();
    } //-- int getLoadCacheCount() 

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
     * Method removeLoadCache
     * 
     * 
     * 
     * @param vLoadCache
     * @return boolean
     */
    public boolean removeLoadCache(com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCache vLoadCache)
    {
        boolean removed = _loadCacheList.remove(vLoadCache);
        return removed;
    } //-- boolean removeLoadCache(com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCache) 

    /**
     * Method setLoadCache
     * 
     * 
     * 
     * @param index
     * @param vLoadCache
     */
    public void setLoadCache(int index, com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCache vLoadCache)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _loadCacheList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _loadCacheList.set(index, vLoadCache);
    } //-- void setLoadCache(int, com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCache) 

    /**
     * Method setLoadCache
     * 
     * 
     * 
     * @param loadCacheArray
     */
    public void setLoadCache(com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCache[] loadCacheArray)
    {
        //-- copy array
        _loadCacheList.clear();
        for (int i = 0; i < loadCacheArray.length; i++) {
            _loadCacheList.add(loadCacheArray[i]);
        }
    } //-- void setLoadCache(com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCache) 

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
     * @return PreLoadCache
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.schema.PreLoadCache unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.PreLoadCache) Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.schema.PreLoadCache.class, reader);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PreLoadCache unmarshal(java.io.Reader) 

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
