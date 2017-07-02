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
public class ZkProducer implements java.io.Serializable {

    /**
     * Field id.
     */
    private java.lang.String id;

    /**
     * Field connect.
     */
    private java.lang.String connect;

    /**
     * Field weight.
     */
    private int weight = 1;

    /**
     * Keeps track of whether primitive field weight has been set
     * already.
     */
    private boolean _hasweight;

    public ZkProducer() {
        super();
    }

    /**
     */
    public void deleteWeight() {
        this._hasweight= false;
    }

    /**
     * Returns the value of field 'connect'.
     * 
     * @return the value of field 'Connect'.
     */
    public java.lang.String getConnect() {
        return this.connect;
    }

    /**
     * Returns the value of field 'id'.
     * 
     * @return the value of field 'Id'.
     */
    public java.lang.String getId() {
        return this.id;
    }

    /**
     * Returns the value of field 'weight'.
     * 
     * @return the value of field 'Weight'.
     */
    public int getWeight() {
        return this.weight;
    }

    /**
     * Method hasWeight.
     * 
     * @return true if at least one Weight has been added
     */
    public boolean hasWeight() {
        return this._hasweight;
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
     * Sets the value of field 'connect'.
     * 
     * @param connect the value of field 'connect'.
     */
    public void setConnect(final java.lang.String connect) {
        this.connect = connect;
    }

    /**
     * Sets the value of field 'id'.
     * 
     * @param id the value of field 'id'.
     */
    public void setId(final java.lang.String id) {
        this.id = id;
    }

    /**
     * Sets the value of field 'weight'.
     * 
     * @param weight the value of field 'weight'.
     */
    public void setWeight(final int weight) {
        this.weight = weight;
        this._hasweight = true;
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
     * com.cdoframework.cdolib.servicebus.xsd.ZkProducer
     */
    public static com.cdoframework.cdolib.servicebus.xsd.ZkProducer unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.servicebus.xsd.ZkProducer) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.servicebus.xsd.ZkProducer.class, reader);
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
