/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.3</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.database.xsd;

/**
 * 
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class NoSQLTransType implements java.io.Serializable {

    /**
     * Field transName.
     */
    private java.lang.String transName;

    /**
     * Field value1.
     */
    private java.lang.String value1;

    /**
     * Field operator.
     */
    private com.cdoframework.cdolib.database.xsd.types.NoSQLTransTypeOperatorType operator;

    /**
     * Field value2.
     */
    private java.lang.String value2;

    /**
     * Field type.
     */
    private com.cdoframework.cdolib.database.xsd.types.NoSQLTransTypeTypeType type;

    /**
     * Field noSQLTransTypeChoice.
     */
    private com.cdoframework.cdolib.database.xsd.NoSQLTransTypeChoice noSQLTransTypeChoice;

    /**
     * Field onException.
     */
    private com.cdoframework.cdolib.database.xsd.OnException onException;

    /**
     * Field _return.
     */
    private com.cdoframework.cdolib.database.xsd.Return _return;

    public NoSQLTransType() {
        super();
    }

    /**
     * Returns the value of field 'noSQLTransTypeChoice'.
     * 
     * @return the value of field 'NoSQLTransTypeChoice'.
     */
    public com.cdoframework.cdolib.database.xsd.NoSQLTransTypeChoice getNoSQLTransTypeChoice() {
        return this.noSQLTransTypeChoice;
    }

    /**
     * Returns the value of field 'onException'.
     * 
     * @return the value of field 'OnException'.
     */
    public com.cdoframework.cdolib.database.xsd.OnException getOnException() {
        return this.onException;
    }

    /**
     * Returns the value of field 'operator'.
     * 
     * @return the value of field 'Operator'.
     */
    public com.cdoframework.cdolib.database.xsd.types.NoSQLTransTypeOperatorType getOperator() {
        return this.operator;
    }

    /**
     * Returns the value of field 'return'.
     * 
     * @return the value of field 'Return'.
     */
    public com.cdoframework.cdolib.database.xsd.Return getReturn() {
        return this._return;
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
     * Returns the value of field 'type'.
     * 
     * @return the value of field 'Type'.
     */
    public com.cdoframework.cdolib.database.xsd.types.NoSQLTransTypeTypeType getType() {
        return this.type;
    }

    /**
     * Returns the value of field 'value1'.
     * 
     * @return the value of field 'Value1'.
     */
    public java.lang.String getValue1() {
        return this.value1;
    }

    /**
     * Returns the value of field 'value2'.
     * 
     * @return the value of field 'Value2'.
     */
    public java.lang.String getValue2() {
        return this.value2;
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
     * Sets the value of field 'noSQLTransTypeChoice'.
     * 
     * @param noSQLTransTypeChoice the value of field
     * 'noSQLTransTypeChoice'.
     */
    public void setNoSQLTransTypeChoice(final com.cdoframework.cdolib.database.xsd.NoSQLTransTypeChoice noSQLTransTypeChoice) {
        this.noSQLTransTypeChoice = noSQLTransTypeChoice;
    }

    /**
     * Sets the value of field 'onException'.
     * 
     * @param onException the value of field 'onException'.
     */
    public void setOnException(final com.cdoframework.cdolib.database.xsd.OnException onException) {
        this.onException = onException;
    }

    /**
     * Sets the value of field 'operator'.
     * 
     * @param operator the value of field 'operator'.
     */
    public void setOperator(final com.cdoframework.cdolib.database.xsd.types.NoSQLTransTypeOperatorType operator) {
        this.operator = operator;
    }

    /**
     * Sets the value of field 'return'.
     * 
     * @param _return
     * @param return the value of field 'return'.
     */
    public void setReturn(final com.cdoframework.cdolib.database.xsd.Return _return) {
        this._return = _return;
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
     * Sets the value of field 'type'.
     * 
     * @param type the value of field 'type'.
     */
    public void setType(final com.cdoframework.cdolib.database.xsd.types.NoSQLTransTypeTypeType type) {
        this.type = type;
    }

    /**
     * Sets the value of field 'value1'.
     * 
     * @param value1 the value of field 'value1'.
     */
    public void setValue1(final java.lang.String value1) {
        this.value1 = value1;
    }

    /**
     * Sets the value of field 'value2'.
     * 
     * @param value2 the value of field 'value2'.
     */
    public void setValue2(final java.lang.String value2) {
        this.value2 = value2;
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
     * com.cdoframework.cdolib.database.xsd.NoSQLTransType
     */
    public static com.cdoframework.cdolib.database.xsd.NoSQLTransType unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.database.xsd.NoSQLTransType) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.database.xsd.NoSQLTransType.class, reader);
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
