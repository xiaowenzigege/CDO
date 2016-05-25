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
public class PoolConfig implements java.io.Serializable {

    /**
     * Field size.
     */
    private int size = 10;

    /**
     * Keeps track of whether primitive field size has been set
     * already.
     */
    private boolean _hassize;

    /**
     * Field maxBlockPerConn.
     */
    private int maxBlockPerConn = 5;

    /**
     * Keeps track of whether primitive field maxBlockPerConn has
     * been set already.
     */
    private boolean _hasmaxBlockPerConn;

    /**
     * Field maxWaitTime.
     */
    private int maxWaitTime = 120000;

    /**
     * Keeps track of whether primitive field maxWaitTime has been
     * set already.
     */
    private boolean _hasmaxWaitTime;

    /**
     * Field connectTimeout.
     */
    private int connectTimeout = 0;

    /**
     * Keeps track of whether primitive field connectTimeout has
     * been set already.
     */
    private boolean _hasconnectTimeout;

    /**
     * Field socketTimeout.
     */
    private int socketTimeout = 0;

    /**
     * Keeps track of whether primitive field socketTimeout has
     * been set already.
     */
    private boolean _hassocketTimeout;

    /**
     * Field autoConnectRetry.
     */
    private boolean autoConnectRetry = false;

    /**
     * Keeps track of whether primitive field autoConnectRetry has
     * been set already.
     */
    private boolean _hasautoConnectRetry;

    public PoolConfig() {
        super();
    }

    /**
     */
    public void deleteAutoConnectRetry() {
        this._hasautoConnectRetry= false;
    }

    /**
     */
    public void deleteConnectTimeout() {
        this._hasconnectTimeout= false;
    }

    /**
     */
    public void deleteMaxBlockPerConn() {
        this._hasmaxBlockPerConn= false;
    }

    /**
     */
    public void deleteMaxWaitTime() {
        this._hasmaxWaitTime= false;
    }

    /**
     */
    public void deleteSize() {
        this._hassize= false;
    }

    /**
     */
    public void deleteSocketTimeout() {
        this._hassocketTimeout= false;
    }

    /**
     * Returns the value of field 'autoConnectRetry'.
     * 
     * @return the value of field 'AutoConnectRetry'.
     */
    public boolean getAutoConnectRetry() {
        return this.autoConnectRetry;
    }

    /**
     * Returns the value of field 'connectTimeout'.
     * 
     * @return the value of field 'ConnectTimeout'.
     */
    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    /**
     * Returns the value of field 'maxBlockPerConn'.
     * 
     * @return the value of field 'MaxBlockPerConn'.
     */
    public int getMaxBlockPerConn() {
        return this.maxBlockPerConn;
    }

    /**
     * Returns the value of field 'maxWaitTime'.
     * 
     * @return the value of field 'MaxWaitTime'.
     */
    public int getMaxWaitTime() {
        return this.maxWaitTime;
    }

    /**
     * Returns the value of field 'size'.
     * 
     * @return the value of field 'Size'.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Returns the value of field 'socketTimeout'.
     * 
     * @return the value of field 'SocketTimeout'.
     */
    public int getSocketTimeout() {
        return this.socketTimeout;
    }

    /**
     * Method hasAutoConnectRetry.
     * 
     * @return true if at least one AutoConnectRetry has been added
     */
    public boolean hasAutoConnectRetry() {
        return this._hasautoConnectRetry;
    }

    /**
     * Method hasConnectTimeout.
     * 
     * @return true if at least one ConnectTimeout has been added
     */
    public boolean hasConnectTimeout() {
        return this._hasconnectTimeout;
    }

    /**
     * Method hasMaxBlockPerConn.
     * 
     * @return true if at least one MaxBlockPerConn has been added
     */
    public boolean hasMaxBlockPerConn() {
        return this._hasmaxBlockPerConn;
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
     * Method hasSize.
     * 
     * @return true if at least one Size has been added
     */
    public boolean hasSize() {
        return this._hassize;
    }

    /**
     * Method hasSocketTimeout.
     * 
     * @return true if at least one SocketTimeout has been added
     */
    public boolean hasSocketTimeout() {
        return this._hassocketTimeout;
    }

    /**
     * Returns the value of field 'autoConnectRetry'.
     * 
     * @return the value of field 'AutoConnectRetry'.
     */
    public boolean isAutoConnectRetry() {
        return this.autoConnectRetry;
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
     * Sets the value of field 'autoConnectRetry'.
     * 
     * @param autoConnectRetry the value of field 'autoConnectRetry'
     */
    public void setAutoConnectRetry(final boolean autoConnectRetry) {
        this.autoConnectRetry = autoConnectRetry;
        this._hasautoConnectRetry = true;
    }

    /**
     * Sets the value of field 'connectTimeout'.
     * 
     * @param connectTimeout the value of field 'connectTimeout'.
     */
    public void setConnectTimeout(final int connectTimeout) {
        this.connectTimeout = connectTimeout;
        this._hasconnectTimeout = true;
    }

    /**
     * Sets the value of field 'maxBlockPerConn'.
     * 
     * @param maxBlockPerConn the value of field 'maxBlockPerConn'.
     */
    public void setMaxBlockPerConn(final int maxBlockPerConn) {
        this.maxBlockPerConn = maxBlockPerConn;
        this._hasmaxBlockPerConn = true;
    }

    /**
     * Sets the value of field 'maxWaitTime'.
     * 
     * @param maxWaitTime the value of field 'maxWaitTime'.
     */
    public void setMaxWaitTime(final int maxWaitTime) {
        this.maxWaitTime = maxWaitTime;
        this._hasmaxWaitTime = true;
    }

    /**
     * Sets the value of field 'size'.
     * 
     * @param size the value of field 'size'.
     */
    public void setSize(final int size) {
        this.size = size;
        this._hassize = true;
    }

    /**
     * Sets the value of field 'socketTimeout'.
     * 
     * @param socketTimeout the value of field 'socketTimeout'.
     */
    public void setSocketTimeout(final int socketTimeout) {
        this.socketTimeout = socketTimeout;
        this._hassocketTimeout = true;
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
     * com.cdoframework.cdolib.servicebus.xsd.PoolConfig
     */
    public static com.cdoframework.cdolib.servicebus.xsd.PoolConfig unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.servicebus.xsd.PoolConfig) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.servicebus.xsd.PoolConfig.class, reader);
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
