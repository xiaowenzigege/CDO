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
public class RequestKey implements java.io.Serializable {

    /**
     * Field scope.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.types.RequestKeyScopeType scope = com.cdoframework.cdolib.service.framework.transfilter.xsd.types.RequestKeyScopeType.fromValue("Request");

    /**
     * Field fieldId.
     */
    private java.lang.String fieldId;

    /**
     * Field valueId.
     */
    private java.lang.String valueId;

    /**
     * Field value.
     */
    private java.lang.String value;

    /**
     * Field type.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.types.RequestKeyTypeType type;

    public RequestKey() {
        super();
        setScope(com.cdoframework.cdolib.service.framework.transfilter.xsd.types.RequestKeyScopeType.fromValue("Request"));
    }

    /**
     * Returns the value of field 'fieldId'.
     * 
     * @return the value of field 'FieldId'.
     */
    public java.lang.String getFieldId() {
        return this.fieldId;
    }

    /**
     * Returns the value of field 'scope'.
     * 
     * @return the value of field 'Scope'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.types.RequestKeyScopeType getScope() {
        return this.scope;
    }

    /**
     * Returns the value of field 'type'.
     * 
     * @return the value of field 'Type'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.types.RequestKeyTypeType getType() {
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
     * Returns the value of field 'valueId'.
     * 
     * @return the value of field 'ValueId'.
     */
    public java.lang.String getValueId() {
        return this.valueId;
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
     * Sets the value of field 'fieldId'.
     * 
     * @param fieldId the value of field 'fieldId'.
     */
    public void setFieldId(final java.lang.String fieldId) {
        this.fieldId = fieldId;
    }

    /**
     * Sets the value of field 'scope'.
     * 
     * @param scope the value of field 'scope'.
     */
    public void setScope(final com.cdoframework.cdolib.service.framework.transfilter.xsd.types.RequestKeyScopeType scope) {
        this.scope = scope;
    }

    /**
     * Sets the value of field 'type'.
     * 
     * @param type the value of field 'type'.
     */
    public void setType(final com.cdoframework.cdolib.service.framework.transfilter.xsd.types.RequestKeyTypeType type) {
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
     * Sets the value of field 'valueId'.
     * 
     * @param valueId the value of field 'valueId'.
     */
    public void setValueId(final java.lang.String valueId) {
        this.valueId = valueId;
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
     * com.cdoframework.cdolib.service.framework.transfilter.xsd.RequestKey
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.xsd.RequestKey unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.service.framework.transfilter.xsd.RequestKey) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.xsd.RequestKey.class, reader);
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
