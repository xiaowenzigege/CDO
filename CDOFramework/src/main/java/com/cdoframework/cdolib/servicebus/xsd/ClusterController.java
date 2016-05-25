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
public class ClusterController implements java.io.Serializable {

    /**
     * Field dataGroupId.
     */
    private java.lang.String dataGroupId;

    /**
     * Field maxDeadSecond.
     */
    private int maxDeadSecond = 5;

    /**
     * Keeps track of whether primitive field maxDeadSecond has
     * been set already.
     */
    private boolean _hasmaxDeadSecond;

    /**
     * Field pulseSecond.
     */
    private int pulseSecond = 2;

    /**
     * Keeps track of whether primitive field pulseSecond has been
     * set already.
     */
    private boolean _haspulseSecond;

    public ClusterController() {
        super();
    }

    /**
     */
    public void deleteMaxDeadSecond() {
        this._hasmaxDeadSecond= false;
    }

    /**
     */
    public void deletePulseSecond() {
        this._haspulseSecond= false;
    }

    /**
     * Returns the value of field 'dataGroupId'.
     * 
     * @return the value of field 'DataGroupId'.
     */
    public java.lang.String getDataGroupId() {
        return this.dataGroupId;
    }

    /**
     * Returns the value of field 'maxDeadSecond'.
     * 
     * @return the value of field 'MaxDeadSecond'.
     */
    public int getMaxDeadSecond() {
        return this.maxDeadSecond;
    }

    /**
     * Returns the value of field 'pulseSecond'.
     * 
     * @return the value of field 'PulseSecond'.
     */
    public int getPulseSecond() {
        return this.pulseSecond;
    }

    /**
     * Method hasMaxDeadSecond.
     * 
     * @return true if at least one MaxDeadSecond has been added
     */
    public boolean hasMaxDeadSecond() {
        return this._hasmaxDeadSecond;
    }

    /**
     * Method hasPulseSecond.
     * 
     * @return true if at least one PulseSecond has been added
     */
    public boolean hasPulseSecond() {
        return this._haspulseSecond;
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
     * Sets the value of field 'dataGroupId'.
     * 
     * @param dataGroupId the value of field 'dataGroupId'.
     */
    public void setDataGroupId(final java.lang.String dataGroupId) {
        this.dataGroupId = dataGroupId;
    }

    /**
     * Sets the value of field 'maxDeadSecond'.
     * 
     * @param maxDeadSecond the value of field 'maxDeadSecond'.
     */
    public void setMaxDeadSecond(final int maxDeadSecond) {
        this.maxDeadSecond = maxDeadSecond;
        this._hasmaxDeadSecond = true;
    }

    /**
     * Sets the value of field 'pulseSecond'.
     * 
     * @param pulseSecond the value of field 'pulseSecond'.
     */
    public void setPulseSecond(final int pulseSecond) {
        this.pulseSecond = pulseSecond;
        this._haspulseSecond = true;
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
     * com.cdoframework.cdolib.servicebus.xsd.ClusterController
     */
    public static com.cdoframework.cdolib.servicebus.xsd.ClusterController unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.servicebus.xsd.ClusterController) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.servicebus.xsd.ClusterController.class, reader);
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
