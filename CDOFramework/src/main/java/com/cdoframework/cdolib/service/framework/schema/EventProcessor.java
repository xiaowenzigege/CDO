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
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

/**
 * Class EventProcessor.
 * 
 * @version $Revision$ $Date$
 */
public class EventProcessor implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _logDataGroupId
     */
    private java.lang.String _logDataGroupId;

    /**
     * Field _maxThreadCount
     */
    private int _maxThreadCount = 10;

    /**
     * keeps track of state for field: _maxThreadCount
     */
    private boolean _has_maxThreadCount;

    /**
     * Field _minThreadCount
     */
    private int _minThreadCount = 1;

    /**
     * keeps track of state for field: _minThreadCount
     */
    private boolean _has_minThreadCount;

    /**
     * Field _maxIdelTreadCount
     */
    private int _maxIdelTreadCount = 10;

    /**
     * keeps track of state for field: _maxIdelTreadCount
     */
    private boolean _has_maxIdelTreadCount;

    /**
     * Field _maxWaitEventCount
     */
    private int _maxWaitEventCount = 1000;

    /**
     * keeps track of state for field: _maxWaitEventCount
     */
    private boolean _has_maxWaitEventCount;


      //----------------/
     //- Constructors -/
    //----------------/

    public EventProcessor() 
     {
        super();
    } //-- com.cdoframework.cdolib.service.framework.schema.EventProcessor()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method deleteMaxIdelTreadCount
     * 
     */
    public void deleteMaxIdelTreadCount()
    {
        this._has_maxIdelTreadCount= false;
    } //-- void deleteMaxIdelTreadCount() 

    /**
     * Method deleteMaxThreadCount
     * 
     */
    public void deleteMaxThreadCount()
    {
        this._has_maxThreadCount= false;
    } //-- void deleteMaxThreadCount() 

    /**
     * Method deleteMaxWaitEventCount
     * 
     */
    public void deleteMaxWaitEventCount()
    {
        this._has_maxWaitEventCount= false;
    } //-- void deleteMaxWaitEventCount() 

    /**
     * Method deleteMinThreadCount
     * 
     */
    public void deleteMinThreadCount()
    {
        this._has_minThreadCount= false;
    } //-- void deleteMinThreadCount() 

    /**
     * Returns the value of field 'logDataGroupId'.
     * 
     * @return String
     * @return the value of field 'logDataGroupId'.
     */
    public java.lang.String getLogDataGroupId()
    {
        return this._logDataGroupId;
    } //-- java.lang.String getLogDataGroupId() 

    /**
     * Returns the value of field 'maxIdelTreadCount'.
     * 
     * @return int
     * @return the value of field 'maxIdelTreadCount'.
     */
    public int getMaxIdelTreadCount()
    {
        return this._maxIdelTreadCount;
    } //-- int getMaxIdelTreadCount() 

    /**
     * Returns the value of field 'maxThreadCount'.
     * 
     * @return int
     * @return the value of field 'maxThreadCount'.
     */
    public int getMaxThreadCount()
    {
        return this._maxThreadCount;
    } //-- int getMaxThreadCount() 

    /**
     * Returns the value of field 'maxWaitEventCount'.
     * 
     * @return int
     * @return the value of field 'maxWaitEventCount'.
     */
    public int getMaxWaitEventCount()
    {
        return this._maxWaitEventCount;
    } //-- int getMaxWaitEventCount() 

    /**
     * Returns the value of field 'minThreadCount'.
     * 
     * @return int
     * @return the value of field 'minThreadCount'.
     */
    public int getMinThreadCount()
    {
        return this._minThreadCount;
    } //-- int getMinThreadCount() 

    /**
     * Method hasMaxIdelTreadCount
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasMaxIdelTreadCount()
    {
        return this._has_maxIdelTreadCount;
    } //-- boolean hasMaxIdelTreadCount() 

    /**
     * Method hasMaxThreadCount
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasMaxThreadCount()
    {
        return this._has_maxThreadCount;
    } //-- boolean hasMaxThreadCount() 

    /**
     * Method hasMaxWaitEventCount
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasMaxWaitEventCount()
    {
        return this._has_maxWaitEventCount;
    } //-- boolean hasMaxWaitEventCount() 

    /**
     * Method hasMinThreadCount
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasMinThreadCount()
    {
        return this._has_minThreadCount;
    } //-- boolean hasMinThreadCount() 

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
     * Sets the value of field 'logDataGroupId'.
     * 
     * @param logDataGroupId the value of field 'logDataGroupId'.
     */
    public void setLogDataGroupId(java.lang.String logDataGroupId)
    {
        this._logDataGroupId = logDataGroupId;
    } //-- void setLogDataGroupId(java.lang.String) 

    /**
     * Sets the value of field 'maxIdelTreadCount'.
     * 
     * @param maxIdelTreadCount the value of field
     * 'maxIdelTreadCount'.
     */
    public void setMaxIdelTreadCount(int maxIdelTreadCount)
    {
        this._maxIdelTreadCount = maxIdelTreadCount;
        this._has_maxIdelTreadCount = true;
    } //-- void setMaxIdelTreadCount(int) 

    /**
     * Sets the value of field 'maxThreadCount'.
     * 
     * @param maxThreadCount the value of field 'maxThreadCount'.
     */
    public void setMaxThreadCount(int maxThreadCount)
    {
        this._maxThreadCount = maxThreadCount;
        this._has_maxThreadCount = true;
    } //-- void setMaxThreadCount(int) 

    /**
     * Sets the value of field 'maxWaitEventCount'.
     * 
     * @param maxWaitEventCount the value of field
     * 'maxWaitEventCount'.
     */
    public void setMaxWaitEventCount(int maxWaitEventCount)
    {
        this._maxWaitEventCount = maxWaitEventCount;
        this._has_maxWaitEventCount = true;
    } //-- void setMaxWaitEventCount(int) 

    /**
     * Sets the value of field 'minThreadCount'.
     * 
     * @param minThreadCount the value of field 'minThreadCount'.
     */
    public void setMinThreadCount(int minThreadCount)
    {
        this._minThreadCount = minThreadCount;
        this._has_minThreadCount = true;
    } //-- void setMinThreadCount(int) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return EventProcessor
     */
    public static com.cdoframework.cdolib.service.framework.schema.EventProcessor unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.service.framework.schema.EventProcessor) Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.schema.EventProcessor.class, reader);
    } //-- com.cdoframework.cdolib.service.framework.schema.EventProcessor unmarshal(java.io.Reader) 

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
