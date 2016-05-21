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
 * Class ConnectionPool.
 * 
 * @version $Revision$ $Date$
 */
public class ConnectionPool implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _maxActiveConnectionCount
     */
    private int _maxActiveConnectionCount = 0;

    /**
     * keeps track of state for field: _maxActiveConnectionCount
     */
    private boolean _has_maxActiveConnectionCount;

    /**
     * Field _minIdleConnectionCount
     */
    private int _minIdleConnectionCount = 1;

    /**
     * keeps track of state for field: _minIdleConnectionCount
     */
    private boolean _has_minIdleConnectionCount;

    /**
     * Field _maxIdleConnectionCount
     */
    private int _maxIdleConnectionCount = 3;

    /**
     * keeps track of state for field: _maxIdleConnectionCount
     */
    private boolean _has_maxIdleConnectionCount;

    /**
     * Field _maxWaitTime
     */
    private long _maxWaitTime = 60000;

    /**
     * keeps track of state for field: _maxWaitTime
     */
    private boolean _has_maxWaitTime;


      //----------------/
     //- Constructors -/
    //----------------/

    public ConnectionPool() 
     {
        super();
    } //-- com.cdoframework.cdolib.servicebus.schema.ConnectionPool()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method deleteMaxActiveConnectionCount
     * 
     */
    public void deleteMaxActiveConnectionCount()
    {
        this._has_maxActiveConnectionCount= false;
    } //-- void deleteMaxActiveConnectionCount() 

    /**
     * Method deleteMaxIdleConnectionCount
     * 
     */
    public void deleteMaxIdleConnectionCount()
    {
        this._has_maxIdleConnectionCount= false;
    } //-- void deleteMaxIdleConnectionCount() 

    /**
     * Method deleteMaxWaitTime
     * 
     */
    public void deleteMaxWaitTime()
    {
        this._has_maxWaitTime= false;
    } //-- void deleteMaxWaitTime() 

    /**
     * Method deleteMinIdleConnectionCount
     * 
     */
    public void deleteMinIdleConnectionCount()
    {
        this._has_minIdleConnectionCount= false;
    } //-- void deleteMinIdleConnectionCount() 

    /**
     * Returns the value of field 'maxActiveConnectionCount'.
     * 
     * @return int
     * @return the value of field 'maxActiveConnectionCount'.
     */
    public int getMaxActiveConnectionCount()
    {
        return this._maxActiveConnectionCount;
    } //-- int getMaxActiveConnectionCount() 

    /**
     * Returns the value of field 'maxIdleConnectionCount'.
     * 
     * @return int
     * @return the value of field 'maxIdleConnectionCount'.
     */
    public int getMaxIdleConnectionCount()
    {
        return this._maxIdleConnectionCount;
    } //-- int getMaxIdleConnectionCount() 

    /**
     * Returns the value of field 'maxWaitTime'.
     * 
     * @return long
     * @return the value of field 'maxWaitTime'.
     */
    public long getMaxWaitTime()
    {
        return this._maxWaitTime;
    } //-- long getMaxWaitTime() 

    /**
     * Returns the value of field 'minIdleConnectionCount'.
     * 
     * @return int
     * @return the value of field 'minIdleConnectionCount'.
     */
    public int getMinIdleConnectionCount()
    {
        return this._minIdleConnectionCount;
    } //-- int getMinIdleConnectionCount() 

    /**
     * Method hasMaxActiveConnectionCount
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasMaxActiveConnectionCount()
    {
        return this._has_maxActiveConnectionCount;
    } //-- boolean hasMaxActiveConnectionCount() 

    /**
     * Method hasMaxIdleConnectionCount
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasMaxIdleConnectionCount()
    {
        return this._has_maxIdleConnectionCount;
    } //-- boolean hasMaxIdleConnectionCount() 

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
     * Method hasMinIdleConnectionCount
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasMinIdleConnectionCount()
    {
        return this._has_minIdleConnectionCount;
    } //-- boolean hasMinIdleConnectionCount() 

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
     * Sets the value of field 'maxActiveConnectionCount'.
     * 
     * @param maxActiveConnectionCount the value of field
     * 'maxActiveConnectionCount'.
     */
    public void setMaxActiveConnectionCount(int maxActiveConnectionCount)
    {
        this._maxActiveConnectionCount = maxActiveConnectionCount;
        this._has_maxActiveConnectionCount = true;
    } //-- void setMaxActiveConnectionCount(int) 

    /**
     * Sets the value of field 'maxIdleConnectionCount'.
     * 
     * @param maxIdleConnectionCount the value of field
     * 'maxIdleConnectionCount'.
     */
    public void setMaxIdleConnectionCount(int maxIdleConnectionCount)
    {
        this._maxIdleConnectionCount = maxIdleConnectionCount;
        this._has_maxIdleConnectionCount = true;
    } //-- void setMaxIdleConnectionCount(int) 

    /**
     * Sets the value of field 'maxWaitTime'.
     * 
     * @param maxWaitTime the value of field 'maxWaitTime'.
     */
    public void setMaxWaitTime(long maxWaitTime)
    {
        this._maxWaitTime = maxWaitTime;
        this._has_maxWaitTime = true;
    } //-- void setMaxWaitTime(long) 

    /**
     * Sets the value of field 'minIdleConnectionCount'.
     * 
     * @param minIdleConnectionCount the value of field
     * 'minIdleConnectionCount'.
     */
    public void setMinIdleConnectionCount(int minIdleConnectionCount)
    {
        this._minIdleConnectionCount = minIdleConnectionCount;
        this._has_minIdleConnectionCount = true;
    } //-- void setMinIdleConnectionCount(int) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return ConnectionPool
     */
    public static com.cdoframework.cdolib.servicebus.schema.ConnectionPool unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.servicebus.schema.ConnectionPool) Unmarshaller.unmarshal(com.cdoframework.cdolib.servicebus.schema.ConnectionPool.class, reader);
    } //-- com.cdoframework.cdolib.servicebus.schema.ConnectionPool unmarshal(java.io.Reader) 

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
