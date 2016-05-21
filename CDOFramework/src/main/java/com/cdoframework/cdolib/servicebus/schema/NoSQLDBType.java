/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.servicebus.schema;

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
 * Class NoSQLDBType.
 * 
 * @version $Revision$ $Date$
 */
public class NoSQLDBType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _id
     */
    private java.lang.String _id;

    /**
     * Field _poolConfig
     */
    private com.cdoframework.cdolib.servicebus.schema.PoolConfig _poolConfig;

    /**
     * Field _serverAddrList
     */
    private java.util.ArrayList _serverAddrList;

    /**
     * Field _DBConfigList
     */
    private java.util.ArrayList _DBConfigList;


      //----------------/
     //- Constructors -/
    //----------------/

    public NoSQLDBType() 
     {
        super();
        _serverAddrList = new ArrayList();
        _DBConfigList = new ArrayList();
    } //-- com.cdoframework.cdolib.servicebus.schema.NoSQLDBType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addDBConfig
     * 
     * 
     * 
     * @param vDBConfig
     */
    public void addDBConfig(com.cdoframework.cdolib.servicebus.schema.DBConfig vDBConfig)
        throws java.lang.IndexOutOfBoundsException
    {
        _DBConfigList.add(vDBConfig);
    } //-- void addDBConfig(com.cdoframework.cdolib.servicebus.schema.DBConfig) 

    /**
     * Method addDBConfig
     * 
     * 
     * 
     * @param index
     * @param vDBConfig
     */
    public void addDBConfig(int index, com.cdoframework.cdolib.servicebus.schema.DBConfig vDBConfig)
        throws java.lang.IndexOutOfBoundsException
    {
        _DBConfigList.add(index, vDBConfig);
    } //-- void addDBConfig(int, com.cdoframework.cdolib.servicebus.schema.DBConfig) 

    /**
     * Method addServerAddr
     * 
     * 
     * 
     * @param vServerAddr
     */
    public void addServerAddr(com.cdoframework.cdolib.servicebus.schema.ServerAddr vServerAddr)
        throws java.lang.IndexOutOfBoundsException
    {
        _serverAddrList.add(vServerAddr);
    } //-- void addServerAddr(com.cdoframework.cdolib.servicebus.schema.ServerAddr) 

    /**
     * Method addServerAddr
     * 
     * 
     * 
     * @param index
     * @param vServerAddr
     */
    public void addServerAddr(int index, com.cdoframework.cdolib.servicebus.schema.ServerAddr vServerAddr)
        throws java.lang.IndexOutOfBoundsException
    {
        _serverAddrList.add(index, vServerAddr);
    } //-- void addServerAddr(int, com.cdoframework.cdolib.servicebus.schema.ServerAddr) 

    /**
     * Method clearDBConfig
     * 
     */
    public void clearDBConfig()
    {
        _DBConfigList.clear();
    } //-- void clearDBConfig() 

    /**
     * Method clearServerAddr
     * 
     */
    public void clearServerAddr()
    {
        _serverAddrList.clear();
    } //-- void clearServerAddr() 

    /**
     * Method enumerateDBConfig
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateDBConfig()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_DBConfigList.iterator());
    } //-- java.util.Enumeration enumerateDBConfig() 

    /**
     * Method enumerateServerAddr
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateServerAddr()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_serverAddrList.iterator());
    } //-- java.util.Enumeration enumerateServerAddr() 

    /**
     * Method getDBConfig
     * 
     * 
     * 
     * @param index
     * @return DBConfig
     */
    public com.cdoframework.cdolib.servicebus.schema.DBConfig getDBConfig(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _DBConfigList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.servicebus.schema.DBConfig) _DBConfigList.get(index);
    } //-- com.cdoframework.cdolib.servicebus.schema.DBConfig getDBConfig(int) 

    /**
     * Method getDBConfig
     * 
     * 
     * 
     * @return DBConfig
     */
    public com.cdoframework.cdolib.servicebus.schema.DBConfig[] getDBConfig()
    {
        int size = _DBConfigList.size();
        com.cdoframework.cdolib.servicebus.schema.DBConfig[] mArray = new com.cdoframework.cdolib.servicebus.schema.DBConfig[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.servicebus.schema.DBConfig) _DBConfigList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.servicebus.schema.DBConfig[] getDBConfig() 

    /**
     * Method getDBConfigCount
     * 
     * 
     * 
     * @return int
     */
    public int getDBConfigCount()
    {
        return _DBConfigList.size();
    } //-- int getDBConfigCount() 

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
     * Returns the value of field 'poolConfig'.
     * 
     * @return PoolConfig
     * @return the value of field 'poolConfig'.
     */
    public com.cdoframework.cdolib.servicebus.schema.PoolConfig getPoolConfig()
    {
        return this._poolConfig;
    } //-- com.cdoframework.cdolib.servicebus.schema.PoolConfig getPoolConfig() 

    /**
     * Method getServerAddr
     * 
     * 
     * 
     * @param index
     * @return ServerAddr
     */
    public com.cdoframework.cdolib.servicebus.schema.ServerAddr getServerAddr(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _serverAddrList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.servicebus.schema.ServerAddr) _serverAddrList.get(index);
    } //-- com.cdoframework.cdolib.servicebus.schema.ServerAddr getServerAddr(int) 

    /**
     * Method getServerAddr
     * 
     * 
     * 
     * @return ServerAddr
     */
    public com.cdoframework.cdolib.servicebus.schema.ServerAddr[] getServerAddr()
    {
        int size = _serverAddrList.size();
        com.cdoframework.cdolib.servicebus.schema.ServerAddr[] mArray = new com.cdoframework.cdolib.servicebus.schema.ServerAddr[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.servicebus.schema.ServerAddr) _serverAddrList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.servicebus.schema.ServerAddr[] getServerAddr() 

    /**
     * Method getServerAddrCount
     * 
     * 
     * 
     * @return int
     */
    public int getServerAddrCount()
    {
        return _serverAddrList.size();
    } //-- int getServerAddrCount() 

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
     * Method removeDBConfig
     * 
     * 
     * 
     * @param vDBConfig
     * @return boolean
     */
    public boolean removeDBConfig(com.cdoframework.cdolib.servicebus.schema.DBConfig vDBConfig)
    {
        boolean removed = _DBConfigList.remove(vDBConfig);
        return removed;
    } //-- boolean removeDBConfig(com.cdoframework.cdolib.servicebus.schema.DBConfig) 

    /**
     * Method removeServerAddr
     * 
     * 
     * 
     * @param vServerAddr
     * @return boolean
     */
    public boolean removeServerAddr(com.cdoframework.cdolib.servicebus.schema.ServerAddr vServerAddr)
    {
        boolean removed = _serverAddrList.remove(vServerAddr);
        return removed;
    } //-- boolean removeServerAddr(com.cdoframework.cdolib.servicebus.schema.ServerAddr) 

    /**
     * Method setDBConfig
     * 
     * 
     * 
     * @param index
     * @param vDBConfig
     */
    public void setDBConfig(int index, com.cdoframework.cdolib.servicebus.schema.DBConfig vDBConfig)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _DBConfigList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _DBConfigList.set(index, vDBConfig);
    } //-- void setDBConfig(int, com.cdoframework.cdolib.servicebus.schema.DBConfig) 

    /**
     * Method setDBConfig
     * 
     * 
     * 
     * @param DBConfigArray
     */
    public void setDBConfig(com.cdoframework.cdolib.servicebus.schema.DBConfig[] DBConfigArray)
    {
        //-- copy array
        _DBConfigList.clear();
        for (int i = 0; i < DBConfigArray.length; i++) {
            _DBConfigList.add(DBConfigArray[i]);
        }
    } //-- void setDBConfig(com.cdoframework.cdolib.servicebus.schema.DBConfig) 

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
     * Sets the value of field 'poolConfig'.
     * 
     * @param poolConfig the value of field 'poolConfig'.
     */
    public void setPoolConfig(com.cdoframework.cdolib.servicebus.schema.PoolConfig poolConfig)
    {
        this._poolConfig = poolConfig;
    } //-- void setPoolConfig(com.cdoframework.cdolib.servicebus.schema.PoolConfig) 

    /**
     * Method setServerAddr
     * 
     * 
     * 
     * @param index
     * @param vServerAddr
     */
    public void setServerAddr(int index, com.cdoframework.cdolib.servicebus.schema.ServerAddr vServerAddr)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _serverAddrList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _serverAddrList.set(index, vServerAddr);
    } //-- void setServerAddr(int, com.cdoframework.cdolib.servicebus.schema.ServerAddr) 

    /**
     * Method setServerAddr
     * 
     * 
     * 
     * @param serverAddrArray
     */
    public void setServerAddr(com.cdoframework.cdolib.servicebus.schema.ServerAddr[] serverAddrArray)
    {
        //-- copy array
        _serverAddrList.clear();
        for (int i = 0; i < serverAddrArray.length; i++) {
            _serverAddrList.add(serverAddrArray[i]);
        }
    } //-- void setServerAddr(com.cdoframework.cdolib.servicebus.schema.ServerAddr) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return NoSQLDBType
     */
    public static com.cdoframework.cdolib.servicebus.schema.NoSQLDBType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.servicebus.schema.NoSQLDBType) Unmarshaller.unmarshal(com.cdoframework.cdolib.servicebus.schema.NoSQLDBType.class, reader);
    } //-- com.cdoframework.cdolib.servicebus.schema.NoSQLDBType unmarshal(java.io.Reader) 

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
