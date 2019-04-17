/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.4.1</a>, using an XML
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
public class SQLFor extends com.cdoframework.cdolib.database.xsd.SQLBlockType 
implements java.io.Serializable
{

    /**
     * Field arrKey.
     */
    private java.lang.String arrKey;

    /**
     * Field indexId.
     */
    private java.lang.String indexId;

    /**
     * Field fromIndex.
     */
    private java.lang.String fromIndex = "0";

    /**
     * Field step.
     */
    private java.lang.String step;

    /**
     * Field count.
     */
    private java.lang.String count;

    public SQLFor() {
        super();
        setFromIndex("0");
    }

    /**
     * Returns the value of field 'arrKey'.
     * 
     * @return the value of field 'ArrKey'.
     */
    public java.lang.String getArrKey() {
        return this.arrKey;
    }

    /**
     * Returns the value of field 'count'.
     * 
     * @return the value of field 'Count'.
     */
    public java.lang.String getCount() {
        return this.count;
    }

    /**
     * Returns the value of field 'fromIndex'.
     * 
     * @return the value of field 'FromIndex'.
     */
    public java.lang.String getFromIndex() {
        return this.fromIndex;
    }

    /**
     * Returns the value of field 'indexId'.
     * 
     * @return the value of field 'IndexId'.
     */
    public java.lang.String getIndexId() {
        return this.indexId;
    }

    /**
     * Returns the value of field 'step'.
     * 
     * @return the value of field 'Step'.
     */
    public java.lang.String getStep() {
        return this.step;
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
     * Sets the value of field 'arrKey'.
     * 
     * @param arrKey the value of field 'arrKey'.
     */
    public void setArrKey(final java.lang.String arrKey) {
        this.arrKey = arrKey;
    }

    /**
     * Sets the value of field 'count'.
     * 
     * @param count the value of field 'count'.
     */
    public void setCount(final java.lang.String count) {
        this.count = count;
    }

    /**
     * Sets the value of field 'fromIndex'.
     * 
     * @param fromIndex the value of field 'fromIndex'.
     */
    public void setFromIndex(final java.lang.String fromIndex) {
        this.fromIndex = fromIndex;
    }

    /**
     * Sets the value of field 'indexId'.
     * 
     * @param indexId the value of field 'indexId'.
     */
    public void setIndexId(final java.lang.String indexId) {
        this.indexId = indexId;
    }

    /**
     * Sets the value of field 'step'.
     * 
     * @param step the value of field 'step'.
     */
    public void setStep(final java.lang.String step) {
        this.step = step;
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
     * com.cdoframework.cdolib.database.xsd.SQLFor
     */
    public static com.cdoframework.cdolib.database.xsd.SQLFor unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.database.xsd.SQLFor) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.database.xsd.SQLFor.class, reader);
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
