/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.3</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.servicebus.xsd;

/**
 * 
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class EventProcessor implements java.io.Serializable {

    /**
     * Field maxThreadCount.
     */
    private int maxThreadCount = 10;

    /**
     * Keeps track of whether primitive field maxThreadCount has
     * been set already.
     */
    private boolean _hasmaxThreadCount;

    /**
     * Field minThreadCount.
     */
    private int minThreadCount = 1;

    /**
     * Keeps track of whether primitive field minThreadCount has
     * been set already.
     */
    private boolean _hasminThreadCount;

    /**
     * Field maxIdelTreadCount.
     */
    private int maxIdelTreadCount = 10;

    /**
     * Keeps track of whether primitive field maxIdelTreadCount has
     * been set already.
     */
    private boolean _hasmaxIdelTreadCount;

    /**
     * Field maxWaitEventCount.
     */
    private int maxWaitEventCount = 1000;

    /**
     * Keeps track of whether primitive field maxWaitEventCount has
     * been set already.
     */
    private boolean _hasmaxWaitEventCount;

    public EventProcessor() {
        super();
    }

    /**
     */
    public void deleteMaxIdelTreadCount() {
        this._hasmaxIdelTreadCount= false;
    }

    /**
     */
    public void deleteMaxThreadCount() {
        this._hasmaxThreadCount= false;
    }

    /**
     */
    public void deleteMaxWaitEventCount() {
        this._hasmaxWaitEventCount= false;
    }

    /**
     */
    public void deleteMinThreadCount() {
        this._hasminThreadCount= false;
    }

    /**
     * Returns the value of field 'maxIdelTreadCount'.
     * 
     * @return the value of field 'MaxIdelTreadCount'.
     */
    public int getMaxIdelTreadCount() {
        return this.maxIdelTreadCount;
    }

    /**
     * Returns the value of field 'maxThreadCount'.
     * 
     * @return the value of field 'MaxThreadCount'.
     */
    public int getMaxThreadCount() {
        return this.maxThreadCount;
    }

    /**
     * Returns the value of field 'maxWaitEventCount'.
     * 
     * @return the value of field 'MaxWaitEventCount'.
     */
    public int getMaxWaitEventCount() {
        return this.maxWaitEventCount;
    }

    /**
     * Returns the value of field 'minThreadCount'.
     * 
     * @return the value of field 'MinThreadCount'.
     */
    public int getMinThreadCount() {
        return this.minThreadCount;
    }

    /**
     * Method hasMaxIdelTreadCount.
     * 
     * @return true if at least one MaxIdelTreadCount has been added
     */
    public boolean hasMaxIdelTreadCount() {
        return this._hasmaxIdelTreadCount;
    }

    /**
     * Method hasMaxThreadCount.
     * 
     * @return true if at least one MaxThreadCount has been added
     */
    public boolean hasMaxThreadCount() {
        return this._hasmaxThreadCount;
    }

    /**
     * Method hasMaxWaitEventCount.
     * 
     * @return true if at least one MaxWaitEventCount has been added
     */
    public boolean hasMaxWaitEventCount() {
        return this._hasmaxWaitEventCount;
    }

    /**
     * Method hasMinThreadCount.
     * 
     * @return true if at least one MinThreadCount has been added
     */
    public boolean hasMinThreadCount() {
        return this._hasminThreadCount;
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
     * Sets the value of field 'maxIdelTreadCount'.
     * 
     * @param maxIdelTreadCount the value of field
     * 'maxIdelTreadCount'.
     */
    public void setMaxIdelTreadCount(final int maxIdelTreadCount) {
        this.maxIdelTreadCount = maxIdelTreadCount;
        this._hasmaxIdelTreadCount = true;
    }

    /**
     * Sets the value of field 'maxThreadCount'.
     * 
     * @param maxThreadCount the value of field 'maxThreadCount'.
     */
    public void setMaxThreadCount(final int maxThreadCount) {
        this.maxThreadCount = maxThreadCount;
        this._hasmaxThreadCount = true;
    }

    /**
     * Sets the value of field 'maxWaitEventCount'.
     * 
     * @param maxWaitEventCount the value of field
     * 'maxWaitEventCount'.
     */
    public void setMaxWaitEventCount(final int maxWaitEventCount) {
        this.maxWaitEventCount = maxWaitEventCount;
        this._hasmaxWaitEventCount = true;
    }

    /**
     * Sets the value of field 'minThreadCount'.
     * 
     * @param minThreadCount the value of field 'minThreadCount'.
     */
    public void setMinThreadCount(final int minThreadCount) {
        this.minThreadCount = minThreadCount;
        this._hasminThreadCount = true;
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
     * com.cdoframework.cdolib.servicebus.xsd.EventProcessor
     */
    public static com.cdoframework.cdolib.servicebus.xsd.EventProcessor unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.servicebus.xsd.EventProcessor) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.servicebus.xsd.EventProcessor.class, reader);
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

}
