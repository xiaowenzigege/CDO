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
public class ConnectionPool implements java.io.Serializable {

    /**
     * Field initialConnectionCount.
     */
    private int initialConnectionCount = 1;

    /**
     * Keeps track of whether primitive field
     * initialConnectionCount has been set already.
     */
    private boolean _hasinitialConnectionCount;

    /**
     * Field maxActiveConnectionCount.
     */
    private int maxActiveConnectionCount = 5;

    /**
     * Keeps track of whether primitive field
     * maxActiveConnectionCount has been set already.
     */
    private boolean _hasmaxActiveConnectionCount;

    /**
     * Field minIdleConnectionCount.
     */
    private int minIdleConnectionCount = 1;

    /**
     * Keeps track of whether primitive field
     * minIdleConnectionCount has been set already.
     */
    private boolean _hasminIdleConnectionCount;

    /**
     * Field maxIdleConnectionCount.
     */
    private int maxIdleConnectionCount = 5;

    /**
     * Keeps track of whether primitive field
     * maxIdleConnectionCount has been set already.
     */
    private boolean _hasmaxIdleConnectionCount;

    /**
     * Field maxWaitTime.
     */
    private long maxWaitTime = 10000;

    /**
     * Keeps track of whether primitive field maxWaitTime has been
     * set already.
     */
    private boolean _hasmaxWaitTime;

    public ConnectionPool() {
        super();
    }

    /**
     */
    public void deleteInitialConnectionCount() {
        this._hasinitialConnectionCount= false;
    }

    /**
     */
    public void deleteMaxActiveConnectionCount() {
        this._hasmaxActiveConnectionCount= false;
    }

    /**
     */
    public void deleteMaxIdleConnectionCount() {
        this._hasmaxIdleConnectionCount= false;
    }

    /**
     */
    public void deleteMaxWaitTime() {
        this._hasmaxWaitTime= false;
    }

    /**
     */
    public void deleteMinIdleConnectionCount() {
        this._hasminIdleConnectionCount= false;
    }

    /**
     * Returns the value of field 'initialConnectionCount'.
     * 
     * @return the value of field 'InitialConnectionCount'.
     */
    public int getInitialConnectionCount() {
        return this.initialConnectionCount;
    }

    /**
     * Returns the value of field 'maxActiveConnectionCount'.
     * 
     * @return the value of field 'MaxActiveConnectionCount'.
     */
    public int getMaxActiveConnectionCount() {
        return this.maxActiveConnectionCount;
    }

    /**
     * Returns the value of field 'maxIdleConnectionCount'.
     * 
     * @return the value of field 'MaxIdleConnectionCount'.
     */
    public int getMaxIdleConnectionCount() {
        return this.maxIdleConnectionCount;
    }

    /**
     * Returns the value of field 'maxWaitTime'.
     * 
     * @return the value of field 'MaxWaitTime'.
     */
    public long getMaxWaitTime() {
        return this.maxWaitTime;
    }

    /**
     * Returns the value of field 'minIdleConnectionCount'.
     * 
     * @return the value of field 'MinIdleConnectionCount'.
     */
    public int getMinIdleConnectionCount() {
        return this.minIdleConnectionCount;
    }

    /**
     * Method hasInitialConnectionCount.
     * 
     * @return true if at least one InitialConnectionCount has been
     * added
     */
    public boolean hasInitialConnectionCount() {
        return this._hasinitialConnectionCount;
    }

    /**
     * Method hasMaxActiveConnectionCount.
     * 
     * @return true if at least one MaxActiveConnectionCount has
     * been added
     */
    public boolean hasMaxActiveConnectionCount() {
        return this._hasmaxActiveConnectionCount;
    }

    /**
     * Method hasMaxIdleConnectionCount.
     * 
     * @return true if at least one MaxIdleConnectionCount has been
     * added
     */
    public boolean hasMaxIdleConnectionCount() {
        return this._hasmaxIdleConnectionCount;
    }

    /**
     * Method hasMaxWaitTime.
     * 
     * @return true if at least one MaxWaitTime has been added
     */
    public boolean hasMaxWaitTime() {
        return this._hasmaxWaitTime;
    }

    /**
     * Method hasMinIdleConnectionCount.
     * 
     * @return true if at least one MinIdleConnectionCount has been
     * added
     */
    public boolean hasMinIdleConnectionCount() {
        return this._hasminIdleConnectionCount;
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
     * Sets the value of field 'initialConnectionCount'.
     * 
     * @param initialConnectionCount the value of field
     * 'initialConnectionCount'.
     */
    public void setInitialConnectionCount(final int initialConnectionCount) {
        this.initialConnectionCount = initialConnectionCount;
        this._hasinitialConnectionCount = true;
    }

    /**
     * Sets the value of field 'maxActiveConnectionCount'.
     * 
     * @param maxActiveConnectionCount the value of field
     * 'maxActiveConnectionCount'.
     */
    public void setMaxActiveConnectionCount(final int maxActiveConnectionCount) {
        this.maxActiveConnectionCount = maxActiveConnectionCount;
        this._hasmaxActiveConnectionCount = true;
    }

    /**
     * Sets the value of field 'maxIdleConnectionCount'.
     * 
     * @param maxIdleConnectionCount the value of field
     * 'maxIdleConnectionCount'.
     */
    public void setMaxIdleConnectionCount(final int maxIdleConnectionCount) {
        this.maxIdleConnectionCount = maxIdleConnectionCount;
        this._hasmaxIdleConnectionCount = true;
    }

    /**
     * Sets the value of field 'maxWaitTime'.
     * 
     * @param maxWaitTime the value of field 'maxWaitTime'.
     */
    public void setMaxWaitTime(final long maxWaitTime) {
        this.maxWaitTime = maxWaitTime;
        this._hasmaxWaitTime = true;
    }

    /**
     * Sets the value of field 'minIdleConnectionCount'.
     * 
     * @param minIdleConnectionCount the value of field
     * 'minIdleConnectionCount'.
     */
    public void setMinIdleConnectionCount(final int minIdleConnectionCount) {
        this.minIdleConnectionCount = minIdleConnectionCount;
        this._hasminIdleConnectionCount = true;
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
     * com.cdoframework.cdolib.servicebus.xsd.ConnectionPool
     */
    public static com.cdoframework.cdolib.servicebus.xsd.ConnectionPool unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.servicebus.xsd.ConnectionPool) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.servicebus.xsd.ConnectionPool.class, reader);
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
