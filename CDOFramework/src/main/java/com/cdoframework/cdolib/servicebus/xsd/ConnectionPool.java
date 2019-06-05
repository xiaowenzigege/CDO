/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.4.1</a>, using an XML
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
     * Field initialSize.
     */
    private int initialSize = 1;

    /**
     * Keeps track of whether primitive field initialSize has been
     * set already.
     */
    private boolean _hasinitialSize;

    /**
     * Field maxTotal.
     */
    private int maxTotal = 5;

    /**
     * Keeps track of whether primitive field maxTotal has been set
     * already.
     */
    private boolean _hasmaxTotal;

    /**
     * Field minIdle.
     */
    private int minIdle = 1;

    /**
     * Keeps track of whether primitive field minIdle has been set
     * already.
     */
    private boolean _hasminIdle;

    /**
     * Field maxIdle.
     */
    private int maxIdle = 5;

    /**
     * Keeps track of whether primitive field maxIdle has been set
     * already.
     */
    private boolean _hasmaxIdle;

    /**
     * Field maxConnLifetimeMillis.
     */
    private long maxConnLifetimeMillis = 30000;

    /**
     * Keeps track of whether primitive field maxConnLifetimeMillis
     * has been set already.
     */
    private boolean _hasmaxConnLifetimeMillis;

    /**
     * Field removeAbandonedTimeout.
     */
    private int removeAbandonedTimeout = 90;

    /**
     * Keeps track of whether primitive field
     * removeAbandonedTimeout has been set already.
     */
    private boolean _hasremoveAbandonedTimeout;

    /**
     * Field testWhileIdle.
     */
    private boolean testWhileIdle = true;

    /**
     * Keeps track of whether primitive field testWhileIdle has
     * been set already.
     */
    private boolean _hastestWhileIdle;

    /**
     * Field testOnBorrow.
     */
    private boolean testOnBorrow = true;

    /**
     * Keeps track of whether primitive field testOnBorrow has been
     * set already.
     */
    private boolean _hastestOnBorrow;

    /**
     * Field validationQuery.
     */
    private java.lang.String validationQuery = "SELECT 1 FROM DUAL";

    /**
     * Field poolPreparedStatements.
     */
    private boolean poolPreparedStatements = true;

    /**
     * Keeps track of whether primitive field
     * poolPreparedStatements has been set already.
     */
    private boolean _haspoolPreparedStatements;

    /**
     * Field removeAbandonedOnMaintenance.
     */
    private boolean removeAbandonedOnMaintenance = true;

    /**
     * Keeps track of whether primitive field
     * removeAbandonedOnMaintenance has been set already.
     */
    private boolean _hasremoveAbandonedOnMaintenance;

    /**
     * Field logAbandoned.
     */
    private boolean logAbandoned = true;

    /**
     * Keeps track of whether primitive field logAbandoned has been
     * set already.
     */
    private boolean _haslogAbandoned;

    public ConnectionPool() {
        super();
        setValidationQuery("SELECT 1 FROM DUAL");
    }

    /**
     */
    public void deleteInitialSize() {
        this._hasinitialSize= false;
    }

    /**
     */
    public void deleteLogAbandoned() {
        this._haslogAbandoned= false;
    }

    /**
     */
    public void deleteMaxConnLifetimeMillis() {
        this._hasmaxConnLifetimeMillis= false;
    }

    /**
     */
    public void deleteMaxIdle() {
        this._hasmaxIdle= false;
    }

    /**
     */
    public void deleteMaxTotal() {
        this._hasmaxTotal= false;
    }

    /**
     */
    public void deleteMinIdle() {
        this._hasminIdle= false;
    }

    /**
     */
    public void deletePoolPreparedStatements() {
        this._haspoolPreparedStatements= false;
    }

    /**
     */
    public void deleteRemoveAbandonedOnMaintenance() {
        this._hasremoveAbandonedOnMaintenance= false;
    }

    /**
     */
    public void deleteRemoveAbandonedTimeout() {
        this._hasremoveAbandonedTimeout= false;
    }

    /**
     */
    public void deleteTestOnBorrow() {
        this._hastestOnBorrow= false;
    }

    /**
     */
    public void deleteTestWhileIdle() {
        this._hastestWhileIdle= false;
    }

    /**
     * Returns the value of field 'initialSize'.
     * 
     * @return the value of field 'InitialSize'.
     */
    public int getInitialSize() {
        return this.initialSize;
    }

    /**
     * Returns the value of field 'logAbandoned'.
     * 
     * @return the value of field 'LogAbandoned'.
     */
    public boolean getLogAbandoned() {
        return this.logAbandoned;
    }

    /**
     * Returns the value of field 'maxConnLifetimeMillis'.
     * 
     * @return the value of field 'MaxConnLifetimeMillis'.
     */
    public long getMaxConnLifetimeMillis() {
        return this.maxConnLifetimeMillis;
    }

    /**
     * Returns the value of field 'maxIdle'.
     * 
     * @return the value of field 'MaxIdle'.
     */
    public int getMaxIdle() {
        return this.maxIdle;
    }

    /**
     * Returns the value of field 'maxTotal'.
     * 
     * @return the value of field 'MaxTotal'.
     */
    public int getMaxTotal() {
        return this.maxTotal;
    }

    /**
     * Returns the value of field 'minIdle'.
     * 
     * @return the value of field 'MinIdle'.
     */
    public int getMinIdle() {
        return this.minIdle;
    }

    /**
     * Returns the value of field 'poolPreparedStatements'.
     * 
     * @return the value of field 'PoolPreparedStatements'.
     */
    public boolean getPoolPreparedStatements() {
        return this.poolPreparedStatements;
    }

    /**
     * Returns the value of field 'removeAbandonedOnMaintenance'.
     * 
     * @return the value of field 'RemoveAbandonedOnMaintenance'.
     */
    public boolean getRemoveAbandonedOnMaintenance() {
        return this.removeAbandonedOnMaintenance;
    }

    /**
     * Returns the value of field 'removeAbandonedTimeout'.
     * 
     * @return the value of field 'RemoveAbandonedTimeout'.
     */
    public int getRemoveAbandonedTimeout() {
        return this.removeAbandonedTimeout;
    }

    /**
     * Returns the value of field 'testOnBorrow'.
     * 
     * @return the value of field 'TestOnBorrow'.
     */
    public boolean getTestOnBorrow() {
        return this.testOnBorrow;
    }

    /**
     * Returns the value of field 'testWhileIdle'.
     * 
     * @return the value of field 'TestWhileIdle'.
     */
    public boolean getTestWhileIdle() {
        return this.testWhileIdle;
    }

    /**
     * Returns the value of field 'validationQuery'.
     * 
     * @return the value of field 'ValidationQuery'.
     */
    public java.lang.String getValidationQuery() {
        return this.validationQuery;
    }

    /**
     * Method hasInitialSize.
     * 
     * @return true if at least one InitialSize has been added
     */
    public boolean hasInitialSize() {
        return this._hasinitialSize;
    }

    /**
     * Method hasLogAbandoned.
     * 
     * @return true if at least one LogAbandoned has been added
     */
    public boolean hasLogAbandoned() {
        return this._haslogAbandoned;
    }

    /**
     * Method hasMaxConnLifetimeMillis.
     * 
     * @return true if at least one MaxConnLifetimeMillis has been
     * added
     */
    public boolean hasMaxConnLifetimeMillis() {
        return this._hasmaxConnLifetimeMillis;
    }

    /**
     * Method hasMaxIdle.
     * 
     * @return true if at least one MaxIdle has been added
     */
    public boolean hasMaxIdle() {
        return this._hasmaxIdle;
    }

    /**
     * Method hasMaxTotal.
     * 
     * @return true if at least one MaxTotal has been added
     */
    public boolean hasMaxTotal() {
        return this._hasmaxTotal;
    }

    /**
     * Method hasMinIdle.
     * 
     * @return true if at least one MinIdle has been added
     */
    public boolean hasMinIdle() {
        return this._hasminIdle;
    }

    /**
     * Method hasPoolPreparedStatements.
     * 
     * @return true if at least one PoolPreparedStatements has been
     * added
     */
    public boolean hasPoolPreparedStatements() {
        return this._haspoolPreparedStatements;
    }

    /**
     * Method hasRemoveAbandonedOnMaintenance.
     * 
     * @return true if at least one RemoveAbandonedOnMaintenance
     * has been added
     */
    public boolean hasRemoveAbandonedOnMaintenance() {
        return this._hasremoveAbandonedOnMaintenance;
    }

    /**
     * Method hasRemoveAbandonedTimeout.
     * 
     * @return true if at least one RemoveAbandonedTimeout has been
     * added
     */
    public boolean hasRemoveAbandonedTimeout() {
        return this._hasremoveAbandonedTimeout;
    }

    /**
     * Method hasTestOnBorrow.
     * 
     * @return true if at least one TestOnBorrow has been added
     */
    public boolean hasTestOnBorrow() {
        return this._hastestOnBorrow;
    }

    /**
     * Method hasTestWhileIdle.
     * 
     * @return true if at least one TestWhileIdle has been added
     */
    public boolean hasTestWhileIdle() {
        return this._hastestWhileIdle;
    }

    /**
     * Returns the value of field 'logAbandoned'.
     * 
     * @return the value of field 'LogAbandoned'.
     */
    public boolean isLogAbandoned() {
        return this.logAbandoned;
    }

    /**
     * Returns the value of field 'poolPreparedStatements'.
     * 
     * @return the value of field 'PoolPreparedStatements'.
     */
    public boolean isPoolPreparedStatements() {
        return this.poolPreparedStatements;
    }

    /**
     * Returns the value of field 'removeAbandonedOnMaintenance'.
     * 
     * @return the value of field 'RemoveAbandonedOnMaintenance'.
     */
    public boolean isRemoveAbandonedOnMaintenance() {
        return this.removeAbandonedOnMaintenance;
    }

    /**
     * Returns the value of field 'testOnBorrow'.
     * 
     * @return the value of field 'TestOnBorrow'.
     */
    public boolean isTestOnBorrow() {
        return this.testOnBorrow;
    }

    /**
     * Returns the value of field 'testWhileIdle'.
     * 
     * @return the value of field 'TestWhileIdle'.
     */
    public boolean isTestWhileIdle() {
        return this.testWhileIdle;
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
     * Sets the value of field 'initialSize'.
     * 
     * @param initialSize the value of field 'initialSize'.
     */
    public void setInitialSize(final int initialSize) {
        this.initialSize = initialSize;
        this._hasinitialSize = true;
    }

    /**
     * Sets the value of field 'logAbandoned'.
     * 
     * @param logAbandoned the value of field 'logAbandoned'.
     */
    public void setLogAbandoned(final boolean logAbandoned) {
        this.logAbandoned = logAbandoned;
        this._haslogAbandoned = true;
    }

    /**
     * Sets the value of field 'maxConnLifetimeMillis'.
     * 
     * @param maxConnLifetimeMillis the value of field
     * 'maxConnLifetimeMillis'.
     */
    public void setMaxConnLifetimeMillis(final long maxConnLifetimeMillis) {
        this.maxConnLifetimeMillis = maxConnLifetimeMillis;
        this._hasmaxConnLifetimeMillis = true;
    }

    /**
     * Sets the value of field 'maxIdle'.
     * 
     * @param maxIdle the value of field 'maxIdle'.
     */
    public void setMaxIdle(final int maxIdle) {
        this.maxIdle = maxIdle;
        this._hasmaxIdle = true;
    }

    /**
     * Sets the value of field 'maxTotal'.
     * 
     * @param maxTotal the value of field 'maxTotal'.
     */
    public void setMaxTotal(final int maxTotal) {
        this.maxTotal = maxTotal;
        this._hasmaxTotal = true;
    }

    /**
     * Sets the value of field 'minIdle'.
     * 
     * @param minIdle the value of field 'minIdle'.
     */
    public void setMinIdle(final int minIdle) {
        this.minIdle = minIdle;
        this._hasminIdle = true;
    }

    /**
     * Sets the value of field 'poolPreparedStatements'.
     * 
     * @param poolPreparedStatements the value of field
     * 'poolPreparedStatements'.
     */
    public void setPoolPreparedStatements(final boolean poolPreparedStatements) {
        this.poolPreparedStatements = poolPreparedStatements;
        this._haspoolPreparedStatements = true;
    }

    /**
     * Sets the value of field 'removeAbandonedOnMaintenance'.
     * 
     * @param removeAbandonedOnMaintenance the value of field
     * 'removeAbandonedOnMaintenance'.
     */
    public void setRemoveAbandonedOnMaintenance(final boolean removeAbandonedOnMaintenance) {
        this.removeAbandonedOnMaintenance = removeAbandonedOnMaintenance;
        this._hasremoveAbandonedOnMaintenance = true;
    }

    /**
     * Sets the value of field 'removeAbandonedTimeout'.
     * 
     * @param removeAbandonedTimeout the value of field
     * 'removeAbandonedTimeout'.
     */
    public void setRemoveAbandonedTimeout(final int removeAbandonedTimeout) {
        this.removeAbandonedTimeout = removeAbandonedTimeout;
        this._hasremoveAbandonedTimeout = true;
    }

    /**
     * Sets the value of field 'testOnBorrow'.
     * 
     * @param testOnBorrow the value of field 'testOnBorrow'.
     */
    public void setTestOnBorrow(final boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
        this._hastestOnBorrow = true;
    }

    /**
     * Sets the value of field 'testWhileIdle'.
     * 
     * @param testWhileIdle the value of field 'testWhileIdle'.
     */
    public void setTestWhileIdle(final boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
        this._hastestWhileIdle = true;
    }

    /**
     * Sets the value of field 'validationQuery'.
     * 
     * @param validationQuery the value of field 'validationQuery'.
     */
    public void setValidationQuery(final java.lang.String validationQuery) {
        this.validationQuery = validationQuery;
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
