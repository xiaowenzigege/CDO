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
public class CriteriaType implements java.io.Serializable {

    /**
     * Field name.
     */
    private java.lang.String name;

    /**
     * Field operator.
     */
    private com.cdoframework.cdolib.servicebus.xsd.types.CriteriaTypeOperatorType operator;

    /**
     * Field value.
     */
    private java.lang.String value;

    /**
     * Field type.
     */
    private com.cdoframework.cdolib.servicebus.xsd.types.CriteriaTypeTypeType type;

    /**
     * Field valueRequired.
     */
    private boolean valueRequired = false;

    /**
     * Keeps track of whether primitive field valueRequired has
     * been set already.
     */
    private boolean _hasvalueRequired;

    public CriteriaType() {
        super();
    }

    /**
     */
    public void deleteValueRequired() {
        this._hasvalueRequired= false;
    }

    /**
     * Returns the value of field 'name'.
     * 
     * @return the value of field 'Name'.
     */
    public java.lang.String getName() {
        return this.name;
    }

    /**
     * Returns the value of field 'operator'.
     * 
     * @return the value of field 'Operator'.
     */
    public com.cdoframework.cdolib.servicebus.xsd.types.CriteriaTypeOperatorType getOperator() {
        return this.operator;
    }

    /**
     * Returns the value of field 'type'.
     * 
     * @return the value of field 'Type'.
     */
    public com.cdoframework.cdolib.servicebus.xsd.types.CriteriaTypeTypeType getType() {
        return this.type;
    }

    /**
     * Returns the value of field 'value'.
     * 
     * @return the value of field 'Value'.
     */
    public java.lang.String getValue() {
        return this.value;
    }

    /**
     * Returns the value of field 'valueRequired'.
     * 
     * @return the value of field 'ValueRequired'.
     */
    public boolean getValueRequired() {
        return this.valueRequired;
    }

    /**
     * Method hasValueRequired.
     * 
     * @return true if at least one ValueRequired has been added
     */
    public boolean hasValueRequired() {
        return this._hasvalueRequired;
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
     * Returns the value of field 'valueRequired'.
     * 
     * @return the value of field 'ValueRequired'.
     */
    public boolean isValueRequired() {
        return this.valueRequired;
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
     * Sets the value of field 'name'.
     * 
     * @param name the value of field 'name'.
     */
    public void setName(final java.lang.String name) {
        this.name = name;
    }

    /**
     * Sets the value of field 'operator'.
     * 
     * @param operator the value of field 'operator'.
     */
    public void setOperator(final com.cdoframework.cdolib.servicebus.xsd.types.CriteriaTypeOperatorType operator) {
        this.operator = operator;
    }

    /**
     * Sets the value of field 'type'.
     * 
     * @param type the value of field 'type'.
     */
    public void setType(final com.cdoframework.cdolib.servicebus.xsd.types.CriteriaTypeTypeType type) {
        this.type = type;
    }

    /**
     * Sets the value of field 'value'.
     * 
     * @param value the value of field 'value'.
     */
    public void setValue(final java.lang.String value) {
        this.value = value;
    }

    /**
     * Sets the value of field 'valueRequired'.
     * 
     * @param valueRequired the value of field 'valueRequired'.
     */
    public void setValueRequired(final boolean valueRequired) {
        this.valueRequired = valueRequired;
        this._hasvalueRequired = true;
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
     * com.cdoframework.cdolib.servicebus.xsd.CriteriaType
     */
    public static com.cdoframework.cdolib.servicebus.xsd.CriteriaType unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.servicebus.xsd.CriteriaType) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.servicebus.xsd.CriteriaType.class, reader);
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
