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
public class LoadCache implements java.io.Serializable {

    /**
     * Field transaction.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.Transaction transaction;

    /**
     * Field responseCondition.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.ResponseCondition responseCondition;

    /**
     * Field loadCacheChoice.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.LoadCacheChoice loadCacheChoice;

    public LoadCache() {
        super();
    }

    /**
     * Returns the value of field 'loadCacheChoice'.
     * 
     * @return the value of field 'LoadCacheChoice'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.LoadCacheChoice getLoadCacheChoice() {
        return this.loadCacheChoice;
    }

    /**
     * Returns the value of field 'responseCondition'.
     * 
     * @return the value of field 'ResponseCondition'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.ResponseCondition getResponseCondition() {
        return this.responseCondition;
    }

    /**
     * Returns the value of field 'transaction'.
     * 
     * @return the value of field 'Transaction'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.Transaction getTransaction() {
        return this.transaction;
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
     * Sets the value of field 'loadCacheChoice'.
     * 
     * @param loadCacheChoice the value of field 'loadCacheChoice'.
     */
    public void setLoadCacheChoice(final com.cdoframework.cdolib.service.framework.transfilter.xsd.LoadCacheChoice loadCacheChoice) {
        this.loadCacheChoice = loadCacheChoice;
    }

    /**
     * Sets the value of field 'responseCondition'.
     * 
     * @param responseCondition the value of field
     * 'responseCondition'.
     */
    public void setResponseCondition(final com.cdoframework.cdolib.service.framework.transfilter.xsd.ResponseCondition responseCondition) {
        this.responseCondition = responseCondition;
    }

    /**
     * Sets the value of field 'transaction'.
     * 
     * @param transaction the value of field 'transaction'.
     */
    public void setTransaction(final com.cdoframework.cdolib.service.framework.transfilter.xsd.Transaction transaction) {
        this.transaction = transaction;
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
     * com.cdoframework.cdolib.service.framework.transfilter.xsd.LoadCache
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.xsd.LoadCache unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.service.framework.transfilter.xsd.LoadCache) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.xsd.LoadCache.class, reader);
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
    
    //------------------------------manual code----------------------//
    private Filter filter;
	public void setFilter(Filter filter)
	{
		this.filter = filter;
	}
	public Filter getFilter()
	{
		return filter;
	}
}
