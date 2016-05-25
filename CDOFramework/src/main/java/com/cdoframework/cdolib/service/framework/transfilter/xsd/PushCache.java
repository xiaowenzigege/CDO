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
public class PushCache implements java.io.Serializable {

    /**
     * Field expireTime.
     */
    private int expireTime;

    /**
     * Keeps track of whether primitive field expireTime has been
     * set already.
     */
    private boolean _hasexpireTime;

    /**
     * Field cacheId.
     */
    private java.lang.String cacheId;

    /**
     * Field cacheKey.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.CacheKey cacheKey;

    /**
     * Field cacheValue.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.CacheValue cacheValue;

    public PushCache() {
        super();
    }

    /**
     */
    public void deleteExpireTime() {
        this._hasexpireTime= false;
    }

    /**
     * Returns the value of field 'cacheId'.
     * 
     * @return the value of field 'CacheId'.
     */
    public java.lang.String getCacheId() {
        return this.cacheId;
    }

    /**
     * Returns the value of field 'cacheKey'.
     * 
     * @return the value of field 'CacheKey'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.CacheKey getCacheKey() {
        return this.cacheKey;
    }

    /**
     * Returns the value of field 'cacheValue'.
     * 
     * @return the value of field 'CacheValue'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.CacheValue getCacheValue() {
        return this.cacheValue;
    }

    /**
     * Returns the value of field 'expireTime'.
     * 
     * @return the value of field 'ExpireTime'.
     */
    public int getExpireTime() {
        return this.expireTime;
    }

    /**
     * Method hasExpireTime.
     * 
     * @return true if at least one ExpireTime has been added
     */
    public boolean hasExpireTime() {
        return this._hasexpireTime;
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
     * Sets the value of field 'cacheId'.
     * 
     * @param cacheId the value of field 'cacheId'.
     */
    public void setCacheId(final java.lang.String cacheId) {
        this.cacheId = cacheId;
    }

    /**
     * Sets the value of field 'cacheKey'.
     * 
     * @param cacheKey the value of field 'cacheKey'.
     */
    public void setCacheKey(final com.cdoframework.cdolib.service.framework.transfilter.xsd.CacheKey cacheKey) {
        this.cacheKey = cacheKey;
    }

    /**
     * Sets the value of field 'cacheValue'.
     * 
     * @param cacheValue the value of field 'cacheValue'.
     */
    public void setCacheValue(final com.cdoframework.cdolib.service.framework.transfilter.xsd.CacheValue cacheValue) {
        this.cacheValue = cacheValue;
    }

    /**
     * Sets the value of field 'expireTime'.
     * 
     * @param expireTime the value of field 'expireTime'.
     */
    public void setExpireTime(final int expireTime) {
        this.expireTime = expireTime;
        this._hasexpireTime = true;
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
     * com.cdoframework.cdolib.service.framework.transfilter.xsd.PushCache
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.xsd.PushCache unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.service.framework.transfilter.xsd.PushCache) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.xsd.PushCache.class, reader);
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
    //------------------------------manual code----------------------
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
