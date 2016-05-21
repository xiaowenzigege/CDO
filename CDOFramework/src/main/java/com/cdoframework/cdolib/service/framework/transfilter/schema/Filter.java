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
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

/**
 * Class Filter.
 * 
 * @version $Revision$ $Date$
 */
public class Filter implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _serviceName
     */
    private java.lang.String _serviceName;

    /**
     * Field _transName
     */
    private java.lang.String _transName;

    /**
     * Field _transCache
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.TransCache _transCache;

    /**
     * Field _preEvent
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.PreEvent _preEvent;

    /**
     * Field _postEvent
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.PostEvent _postEvent;


      //----------------/
     //- Constructors -/
    //----------------/

    public Filter() 
     {
        super();
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.Filter()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'postEvent'.
     * 
     * @return PostEvent
     * @return the value of field 'postEvent'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.PostEvent getPostEvent()
    {
        return this._postEvent;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PostEvent getPostEvent() 

    /**
     * Returns the value of field 'preEvent'.
     * 
     * @return PreEvent
     * @return the value of field 'preEvent'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.PreEvent getPreEvent()
    {
        return this._preEvent;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PreEvent getPreEvent() 

    /**
     * Returns the value of field 'serviceName'.
     * 
     * @return String
     * @return the value of field 'serviceName'.
     */
    public java.lang.String getServiceName()
    {
        return this._serviceName;
    } //-- java.lang.String getServiceName() 

    /**
     * Returns the value of field 'transCache'.
     * 
     * @return TransCache
     * @return the value of field 'transCache'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.TransCache getTransCache()
    {
        return this._transCache;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.TransCache getTransCache() 

    /**
     * Returns the value of field 'transName'.
     * 
     * @return String
     * @return the value of field 'transName'.
     */
    public java.lang.String getTransName()
    {
        return this._transName;
    } //-- java.lang.String getTransName() 

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
     * Sets the value of field 'postEvent'.
     * 
     * @param postEvent the value of field 'postEvent'.
     */
    public void setPostEvent(com.cdoframework.cdolib.service.framework.transfilter.schema.PostEvent postEvent)
    {
        this._postEvent = postEvent;
    } //-- void setPostEvent(com.cdoframework.cdolib.service.framework.transfilter.schema.PostEvent) 

    /**
     * Sets the value of field 'preEvent'.
     * 
     * @param preEvent the value of field 'preEvent'.
     */
    public void setPreEvent(com.cdoframework.cdolib.service.framework.transfilter.schema.PreEvent preEvent)
    {
        this._preEvent = preEvent;
    } //-- void setPreEvent(com.cdoframework.cdolib.service.framework.transfilter.schema.PreEvent) 

    /**
     * Sets the value of field 'serviceName'.
     * 
     * @param serviceName the value of field 'serviceName'.
     */
    public void setServiceName(java.lang.String serviceName)
    {
        this._serviceName = serviceName;
    } //-- void setServiceName(java.lang.String) 

    /**
     * Sets the value of field 'transCache'.
     * 
     * @param transCache the value of field 'transCache'.
     */
    public void setTransCache(com.cdoframework.cdolib.service.framework.transfilter.schema.TransCache transCache)
    {
        this._transCache = transCache;
    } //-- void setTransCache(com.cdoframework.cdolib.service.framework.transfilter.schema.TransCache) 

    /**
     * Sets the value of field 'transName'.
     * 
     * @param transName the value of field 'transName'.
     */
    public void setTransName(java.lang.String transName)
    {
        this._transName = transName;
    } //-- void setTransName(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Filter
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.schema.Filter unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.Filter) Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.schema.Filter.class, reader);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.Filter unmarshal(java.io.Reader) 

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
    private FilterDefine filterDefine;


	public FilterDefine getFilterDefine()
	{
		return filterDefine;
	}


	public void setFilterDefine(FilterDefine filterDefine)
	{
		this.filterDefine=filterDefine;
	}
}
