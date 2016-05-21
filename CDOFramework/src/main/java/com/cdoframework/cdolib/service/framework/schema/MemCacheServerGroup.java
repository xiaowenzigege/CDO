/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.service.framework.schema;

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
 * Class MemCacheServerGroup.
 * 
 * @version $Revision$ $Date$
 */
public class MemCacheServerGroup implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _id
     */
    private java.lang.String _id;

    /**
     * Field _cacheServerList
     */
    private java.util.ArrayList _cacheServerList;


      //----------------/
     //- Constructors -/
    //----------------/

    public MemCacheServerGroup() 
     {
        super();
        _cacheServerList = new ArrayList();
    } //-- com.cdoframework.cdolib.service.framework.schema.MemCacheServerGroup()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addCacheServer
     * 
     * 
     * 
     * @param vCacheServer
     */
    public void addCacheServer(com.cdoframework.cdolib.service.framework.schema.CacheServer vCacheServer)
        throws java.lang.IndexOutOfBoundsException
    {
        _cacheServerList.add(vCacheServer);
    } //-- void addCacheServer(com.cdoframework.cdolib.service.framework.schema.CacheServer) 

    /**
     * Method addCacheServer
     * 
     * 
     * 
     * @param index
     * @param vCacheServer
     */
    public void addCacheServer(int index, com.cdoframework.cdolib.service.framework.schema.CacheServer vCacheServer)
        throws java.lang.IndexOutOfBoundsException
    {
        _cacheServerList.add(index, vCacheServer);
    } //-- void addCacheServer(int, com.cdoframework.cdolib.service.framework.schema.CacheServer) 

    /**
     * Method clearCacheServer
     * 
     */
    public void clearCacheServer()
    {
        _cacheServerList.clear();
    } //-- void clearCacheServer() 

    /**
     * Method enumerateCacheServer
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateCacheServer()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_cacheServerList.iterator());
    } //-- java.util.Enumeration enumerateCacheServer() 

    /**
     * Method getCacheServer
     * 
     * 
     * 
     * @param index
     * @return CacheServer
     */
    public com.cdoframework.cdolib.service.framework.schema.CacheServer getCacheServer(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _cacheServerList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.service.framework.schema.CacheServer) _cacheServerList.get(index);
    } //-- com.cdoframework.cdolib.service.framework.schema.CacheServer getCacheServer(int) 

    /**
     * Method getCacheServer
     * 
     * 
     * 
     * @return CacheServer
     */
    public com.cdoframework.cdolib.service.framework.schema.CacheServer[] getCacheServer()
    {
        int size = _cacheServerList.size();
        com.cdoframework.cdolib.service.framework.schema.CacheServer[] mArray = new com.cdoframework.cdolib.service.framework.schema.CacheServer[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.service.framework.schema.CacheServer) _cacheServerList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.service.framework.schema.CacheServer[] getCacheServer() 

    /**
     * Method getCacheServerCount
     * 
     * 
     * 
     * @return int
     */
    public int getCacheServerCount()
    {
        return _cacheServerList.size();
    } //-- int getCacheServerCount() 

    /**
     * Returns the value of field 'id'.
     * 
     * @return String
     * @return the value of field 'id'.
     */
    public java.lang.String getId()
    {
        return this._id;
    } //-- java.lang.String getId() 

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
     * Method removeCacheServer
     * 
     * 
     * 
     * @param vCacheServer
     * @return boolean
     */
    public boolean removeCacheServer(com.cdoframework.cdolib.service.framework.schema.CacheServer vCacheServer)
    {
        boolean removed = _cacheServerList.remove(vCacheServer);
        return removed;
    } //-- boolean removeCacheServer(com.cdoframework.cdolib.service.framework.schema.CacheServer) 

    /**
     * Method setCacheServer
     * 
     * 
     * 
     * @param index
     * @param vCacheServer
     */
    public void setCacheServer(int index, com.cdoframework.cdolib.service.framework.schema.CacheServer vCacheServer)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _cacheServerList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _cacheServerList.set(index, vCacheServer);
    } //-- void setCacheServer(int, com.cdoframework.cdolib.service.framework.schema.CacheServer) 

    /**
     * Method setCacheServer
     * 
     * 
     * 
     * @param cacheServerArray
     */
    public void setCacheServer(com.cdoframework.cdolib.service.framework.schema.CacheServer[] cacheServerArray)
    {
        //-- copy array
        _cacheServerList.clear();
        for (int i = 0; i < cacheServerArray.length; i++) {
            _cacheServerList.add(cacheServerArray[i]);
        }
    } //-- void setCacheServer(com.cdoframework.cdolib.service.framework.schema.CacheServer) 

    /**
     * Sets the value of field 'id'.
     * 
     * @param id the value of field 'id'.
     */
    public void setId(java.lang.String id)
    {
        this._id = id;
    } //-- void setId(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return MemCacheServerGroup
     */
    public static com.cdoframework.cdolib.service.framework.schema.MemCacheServerGroup unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.service.framework.schema.MemCacheServerGroup) Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.schema.MemCacheServerGroup.class, reader);
    } //-- com.cdoframework.cdolib.service.framework.schema.MemCacheServerGroup unmarshal(java.io.Reader) 

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
