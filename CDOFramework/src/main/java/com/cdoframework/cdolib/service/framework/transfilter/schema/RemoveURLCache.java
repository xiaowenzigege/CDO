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
 * Class RemoveURLCache.
 * 
 * @version $Revision$ $Date$
 */
public class RemoveURLCache implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _serverId
     */
    private java.lang.String _serverId;

    /**
     * Field _cacheURLList
     */
    private java.util.ArrayList _cacheURLList;


      //----------------/
     //- Constructors -/
    //----------------/

    public RemoveURLCache() 
     {
        super();
        _cacheURLList = new ArrayList();
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveURLCache()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addCacheURL
     * 
     * 
     * 
     * @param vCacheURL
     */
    public void addCacheURL(com.cdoframework.cdolib.service.framework.transfilter.schema.CacheURL vCacheURL)
        throws java.lang.IndexOutOfBoundsException
    {
        _cacheURLList.add(vCacheURL);
    } //-- void addCacheURL(com.cdoframework.cdolib.service.framework.transfilter.schema.CacheURL) 

    /**
     * Method addCacheURL
     * 
     * 
     * 
     * @param index
     * @param vCacheURL
     */
    public void addCacheURL(int index, com.cdoframework.cdolib.service.framework.transfilter.schema.CacheURL vCacheURL)
        throws java.lang.IndexOutOfBoundsException
    {
        _cacheURLList.add(index, vCacheURL);
    } //-- void addCacheURL(int, com.cdoframework.cdolib.service.framework.transfilter.schema.CacheURL) 

    /**
     * Method clearCacheURL
     * 
     */
    public void clearCacheURL()
    {
        _cacheURLList.clear();
    } //-- void clearCacheURL() 

    /**
     * Method enumerateCacheURL
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateCacheURL()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_cacheURLList.iterator());
    } //-- java.util.Enumeration enumerateCacheURL() 

    /**
     * Method getCacheURL
     * 
     * 
     * 
     * @param index
     * @return CacheURL
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.CacheURL getCacheURL(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _cacheURLList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.CacheURL) _cacheURLList.get(index);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.CacheURL getCacheURL(int) 

    /**
     * Method getCacheURL
     * 
     * 
     * 
     * @return CacheURL
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.CacheURL[] getCacheURL()
    {
        int size = _cacheURLList.size();
        com.cdoframework.cdolib.service.framework.transfilter.schema.CacheURL[] mArray = new com.cdoframework.cdolib.service.framework.transfilter.schema.CacheURL[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.service.framework.transfilter.schema.CacheURL) _cacheURLList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.CacheURL[] getCacheURL() 

    /**
     * Method getCacheURLCount
     * 
     * 
     * 
     * @return int
     */
    public int getCacheURLCount()
    {
        return _cacheURLList.size();
    } //-- int getCacheURLCount() 

    /**
     * Returns the value of field 'serverId'.
     * 
     * @return String
     * @return the value of field 'serverId'.
     */
    public java.lang.String getServerId()
    {
        return this._serverId;
    } //-- java.lang.String getServerId() 

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
     * Method removeCacheURL
     * 
     * 
     * 
     * @param vCacheURL
     * @return boolean
     */
    public boolean removeCacheURL(com.cdoframework.cdolib.service.framework.transfilter.schema.CacheURL vCacheURL)
    {
        boolean removed = _cacheURLList.remove(vCacheURL);
        return removed;
    } //-- boolean removeCacheURL(com.cdoframework.cdolib.service.framework.transfilter.schema.CacheURL) 

    /**
     * Method setCacheURL
     * 
     * 
     * 
     * @param index
     * @param vCacheURL
     */
    public void setCacheURL(int index, com.cdoframework.cdolib.service.framework.transfilter.schema.CacheURL vCacheURL)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _cacheURLList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _cacheURLList.set(index, vCacheURL);
    } //-- void setCacheURL(int, com.cdoframework.cdolib.service.framework.transfilter.schema.CacheURL) 

    /**
     * Method setCacheURL
     * 
     * 
     * 
     * @param cacheURLArray
     */
    public void setCacheURL(com.cdoframework.cdolib.service.framework.transfilter.schema.CacheURL[] cacheURLArray)
    {
        //-- copy array
        _cacheURLList.clear();
        for (int i = 0; i < cacheURLArray.length; i++) {
            _cacheURLList.add(cacheURLArray[i]);
        }
    } //-- void setCacheURL(com.cdoframework.cdolib.service.framework.transfilter.schema.CacheURL) 

    /**
     * Sets the value of field 'serverId'.
     * 
     * @param serverId the value of field 'serverId'.
     */
    public void setServerId(java.lang.String serverId)
    {
        this._serverId = serverId;
    } //-- void setServerId(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return RemoveURLCache
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveURLCache unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveURLCache) Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveURLCache.class, reader);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveURLCache unmarshal(java.io.Reader) 

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
    private Filter filter;
	public void setFilter(Filter filter)
	{
		this.filter = filter;
	}
	public Filter getFilter()
	{
		return filter;
	}
}
