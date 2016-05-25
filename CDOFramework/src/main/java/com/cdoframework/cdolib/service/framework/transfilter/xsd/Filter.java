/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.3</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.service.framework.transfilter.xsd;

/**
 * 
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class Filter implements java.io.Serializable {

    /**
     * Field serviceName.
     */
    private java.lang.String serviceName;

    /**
     * Field transName.
     */
    private java.lang.String transName;

    /**
     * Field transCache.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.TransCache transCache;

    /**
     * Field preEvent.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.PreEvent preEvent;

    /**
     * Field postEvent.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.PostEvent postEvent;

    public Filter() {
        super();
    }

    /**
     * Returns the value of field 'postEvent'.
     * 
     * @return the value of field 'PostEvent'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.PostEvent getPostEvent() {
        return this.postEvent;
    }

    /**
     * Returns the value of field 'preEvent'.
     * 
     * @return the value of field 'PreEvent'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.PreEvent getPreEvent() {
        return this.preEvent;
    }

    /**
     * Returns the value of field 'serviceName'.
     * 
     * @return the value of field 'ServiceName'.
     */
    public java.lang.String getServiceName() {
        return this.serviceName;
    }

    /**
     * Returns the value of field 'transCache'.
     * 
     * @return the value of field 'TransCache'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.TransCache getTransCache() {
        return this.transCache;
    }

    /**
     * Returns the value of field 'transName'.
     * 
     * @return the value of field 'TransName'.
     */
    public java.lang.String getTransName() {
        return this.transName;
    }

    /**
     * Method isValid.
     * 
     * @return true if this object is valid according to the schema
     */
    public boolean isValid() {
        try {
            validate();
        } catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    }

    /**
     * 
     * 
     * @param out
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void marshal(final java.io.Writer out) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Marshaller.marshal(this, out);
    }

    /**
     * 
     * 
     * @param handler
     * @throws java.io.IOException if an IOException occurs during
     * marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     */
    public void marshal(final org.xml.sax.ContentHandler handler) throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Marshaller.marshal(this, handler);
    }

    /**
     * Sets the value of field 'postEvent'.
     * 
     * @param postEvent the value of field 'postEvent'.
     */
    public void setPostEvent(final com.cdoframework.cdolib.service.framework.transfilter.xsd.PostEvent postEvent) {
        this.postEvent = postEvent;
    }

    /**
     * Sets the value of field 'preEvent'.
     * 
     * @param preEvent the value of field 'preEvent'.
     */
    public void setPreEvent(final com.cdoframework.cdolib.service.framework.transfilter.xsd.PreEvent preEvent) {
        this.preEvent = preEvent;
    }

    /**
     * Sets the value of field 'serviceName'.
     * 
     * @param serviceName the value of field 'serviceName'.
     */
    public void setServiceName(final java.lang.String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * Sets the value of field 'transCache'.
     * 
     * @param transCache the value of field 'transCache'.
     */
    public void setTransCache(final com.cdoframework.cdolib.service.framework.transfilter.xsd.TransCache transCache) {
        this.transCache = transCache;
    }

    /**
     * Sets the value of field 'transName'.
     * 
     * @param transName the value of field 'transName'.
     */
    public void setTransName(final java.lang.String transName) {
        this.transName = transName;
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled
     * com.cdoframework.cdolib.service.framework.transfilter.xsd.Filter
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.xsd.Filter unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.service.framework.transfilter.xsd.Filter) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.xsd.Filter.class, reader);
    }

    /**
     * 
     * 
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void validate() throws org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    }
    
    //------------------------------manual code----------------------
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
