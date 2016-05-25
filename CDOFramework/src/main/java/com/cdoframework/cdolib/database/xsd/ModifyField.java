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
public class ModifyField extends com.cdoframework.cdolib.database.xsd.FieldType 
implements java.io.Serializable
{

    /**
     * Field value.
     */
    private java.lang.String value;

    /**
     * Field method.
     */
    private com.cdoframework.cdolib.database.xsd.types.ModifyFieldMethodType method;

    /**
     * Field valueRequired.
     */
    private boolean valueRequired = false;

    /**
     * Keeps track of whether primitive field valueRequired has
     * been set already.
     */
    private boolean _hasvalueRequired;

    public ModifyField() {
        super();
    }

    /**
     */
    public void deleteValueRequired() {
        this._hasvalueRequired= false;
    }

    /**
     * Returns the value of field 'method'.
     * 
     * @return the value of field 'Method'.
     */
    public com.cdoframework.cdolib.database.xsd.types.ModifyFieldMethodType getMethod() {
        return this.method;
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
     * Sets the value of field 'method'.
     * 
     * @param method the value of field 'method'.
     */
    public void setMethod(final com.cdoframework.cdolib.database.xsd.types.ModifyFieldMethodType method) {
        this.method = method;
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
     * com.cdoframework.cdolib.database.xsd.ModifyField
     */
    public static com.cdoframework.cdolib.database.xsd.ModifyField unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.database.xsd.ModifyField) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.database.xsd.ModifyField.class, reader);
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
