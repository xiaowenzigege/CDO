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
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

/**
 * Class PoolConfig.
 * 
 * @version $Revision$ $Date$
 */
public class PoolConfig implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _size
     */
    private int _size = 10;

    /**
     * keeps track of state for field: _size
     */
    private boolean _has_size;

    /**
     * Field _maxBlockPerConn
     */
    private int _maxBlockPerConn = 5;

    /**
     * keeps track of state for field: _maxBlockPerConn
     */
    private boolean _has_maxBlockPerConn;

    /**
     * Field _maxWaitTime
     */
    private int _maxWaitTime = 120000;

    /**
     * keeps track of state for field: _maxWaitTime
     */
    private boolean _has_maxWaitTime;

    /**
     * Field _connectTimeout
     */
    private int _connectTimeout = 0;

    /**
     * keeps track of state for field: _connectTimeout
     */
    private boolean _has_connectTimeout;

    /**
     * Field _socketTimeout
     */
    private int _socketTimeout = 0;

    /**
     * keeps track of state for field: _socketTimeout
     */
    private boolean _has_socketTimeout;

    /**
     * Field _autoConnectRetry
     */
    private boolean _autoConnectRetry = false;

    /**
     * keeps track of state for field: _autoConnectRetry
     */
    private boolean _has_autoConnectRetry;


      //----------------/
     //- Constructors -/
    //----------------/

    public PoolConfig() 
     {
        super();
    } //-- com.cdoframework.cdolib.servicebus.schema.PoolConfig()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method deleteAutoConnectRetry
     * 
     */
    public void deleteAutoConnectRetry()
    {
        this._has_autoConnectRetry= false;
    } //-- void deleteAutoConnectRetry() 

    /**
     * Method deleteConnectTimeout
     * 
     */
    public void deleteConnectTimeout()
    {
        this._has_connectTimeout= false;
    } //-- void deleteConnectTimeout() 

    /**
     * Method deleteMaxBlockPerConn
     * 
     */
    public void deleteMaxBlockPerConn()
    {
        this._has_maxBlockPerConn= false;
    } //-- void deleteMaxBlockPerConn() 

    /**
     * Method deleteMaxWaitTime
     * 
     */
    public void deleteMaxWaitTime()
    {
        this._has_maxWaitTime= false;
    } //-- void deleteMaxWaitTime() 

    /**
     * Method deleteSize
     * 
     */
    public void deleteSize()
    {
        this._has_size= false;
    } //-- void deleteSize() 

    /**
     * Method deleteSocketTimeout
     * 
     */
    public void deleteSocketTimeout()
    {
        this._has_socketTimeout= false;
    } //-- void deleteSocketTimeout() 

    /**
     * Returns the value of field 'autoConnectRetry'.
     * 
     * @return boolean
     * @return the value of field 'autoConnectRetry'.
     */
    public boolean getAutoConnectRetry()
    {
        return this._autoConnectRetry;
    } //-- boolean getAutoConnectRetry() 

    /**
     * Returns the value of field 'connectTimeout'.
     * 
     * @return int
     * @return the value of field 'connectTimeout'.
     */
    public int getConnectTimeout()
    {
        return this._connectTimeout;
    } //-- int getConnectTimeout() 

    /**
     * Returns the value of field 'maxBlockPerConn'.
     * 
     * @return int
     * @return the value of field 'maxBlockPerConn'.
     */
    public int getMaxBlockPerConn()
    {
        return this._maxBlockPerConn;
    } //-- int getMaxBlockPerConn() 

    /**
     * Returns the value of field 'maxWaitTime'.
     * 
     * @return int
     * @return the value of field 'maxWaitTime'.
     */
    public int getMaxWaitTime()
    {
        return this._maxWaitTime;
    } //-- int getMaxWaitTime() 

    /**
     * Returns the value of field 'size'.
     * 
     * @return int
     * @return the value of field 'size'.
     */
    public int getSize()
    {
        return this._size;
    } //-- int getSize() 

    /**
     * Returns the value of field 'socketTimeout'.
     * 
     * @return int
     * @return the value of field 'socketTimeout'.
     */
    public int getSocketTimeout()
    {
        return this._socketTimeout;
    } //-- int getSocketTimeout() 

    /**
     * Method hasAutoConnectRetry
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasAutoConnectRetry()
    {
        return this._has_autoConnectRetry;
    } //-- boolean hasAutoConnectRetry() 

    /**
     * Method hasConnectTimeout
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasConnectTimeout()
    {
        return this._has_connectTimeout;
    } //-- boolean hasConnectTimeout() 

    /**
     * Method hasMaxBlockPerConn
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasMaxBlockPerConn()
    {
        return this._has_maxBlockPerConn;
    } //-- boolean hasMaxBlockPerConn() 

    /**
     * Method hasMaxWaitTime
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasMaxWaitTime()
    {
        return this._has_maxWaitTime;
    } //-- boolean hasMaxWaitTime() 

    /**
     * Method hasSize
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasSize()
    {
        return this._has_size;
    } //-- boolean hasSize() 

    /**
     * Method hasSocketTimeout
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasSocketTimeout()
    {
        return this._has_socketTimeout;
    } //-- boolean hasSocketTimeout() 

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
     * Sets the value of field 'autoConnectRetry'.
     * 
     * @param autoConnectRetry the value of field 'autoConnectRetry'
     */
    public void setAutoConnectRetry(boolean autoConnectRetry)
    {
        this._autoConnectRetry = autoConnectRetry;
        this._has_autoConnectRetry = true;
    } //-- void setAutoConnectRetry(boolean) 

    /**
     * Sets the value of field 'connectTimeout'.
     * 
     * @param connectTimeout the value of field 'connectTimeout'.
     */
    public void setConnectTimeout(int connectTimeout)
    {
        this._connectTimeout = connectTimeout;
        this._has_connectTimeout = true;
    } //-- void setConnectTimeout(int) 

    /**
     * Sets the value of field 'maxBlockPerConn'.
     * 
     * @param maxBlockPerConn the value of field 'maxBlockPerConn'.
     */
    public void setMaxBlockPerConn(int maxBlockPerConn)
    {
        this._maxBlockPerConn = maxBlockPerConn;
        this._has_maxBlockPerConn = true;
    } //-- void setMaxBlockPerConn(int) 

    /**
     * Sets the value of field 'maxWaitTime'.
     * 
     * @param maxWaitTime the value of field 'maxWaitTime'.
     */
    public void setMaxWaitTime(int maxWaitTime)
    {
        this._maxWaitTime = maxWaitTime;
        this._has_maxWaitTime = true;
    } //-- void setMaxWaitTime(int) 

    /**
     * Sets the value of field 'size'.
     * 
     * @param size the value of field 'size'.
     */
    public void setSize(int size)
    {
        this._size = size;
        this._has_size = true;
    } //-- void setSize(int) 

    /**
     * Sets the value of field 'socketTimeout'.
     * 
     * @param socketTimeout the value of field 'socketTimeout'.
     */
    public void setSocketTimeout(int socketTimeout)
    {
        this._socketTimeout = socketTimeout;
        this._has_socketTimeout = true;
    } //-- void setSocketTimeout(int) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return PoolConfig
     */
    public static com.cdoframework.cdolib.servicebus.schema.PoolConfig unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.servicebus.schema.PoolConfig) Unmarshaller.unmarshal(com.cdoframework.cdolib.servicebus.schema.PoolConfig.class, reader);
    } //-- com.cdoframework.cdolib.servicebus.schema.PoolConfig unmarshal(java.io.Reader) 

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
