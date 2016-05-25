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
public class TransCacheType implements java.io.Serializable {

    /**
     * Field cacheId.
     */
    private java.lang.String cacheId;

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
     * Field requestCondition.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.RequestCondition requestCondition;

    private java.util.List<java.lang.String> removeKeyList;

    /**
     * Field cacheKey.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.CacheKey cacheKey;

    /**
     * Field cacheValue.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.CacheValue cacheValue;

    public TransCacheType() {
        super();
        this.removeKeyList = new java.util.ArrayList<java.lang.String>();
    }

    /**
     * 
     * 
     * @param vRemoveKey
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addRemoveKey(final java.lang.String vRemoveKey) throws java.lang.IndexOutOfBoundsException {
        this.removeKeyList.add(vRemoveKey);
    }

    /**
     * 
     * 
     * @param index
     * @param vRemoveKey
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addRemoveKey(final int index,final java.lang.String vRemoveKey) throws java.lang.IndexOutOfBoundsException {
        this.removeKeyList.add(index, vRemoveKey);
    }

    /**
     */
    public void deleteExpireTime() {
        this._hasexpireTime= false;
    }

    /**
     * Method enumerateRemoveKey.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends java.lang.String> enumerateRemoveKey() {
        return java.util.Collections.enumeration(this.removeKeyList);
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
     * Method getRemoveKey.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the java.lang.String at the given index
     */
    public java.lang.String getRemoveKey(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.removeKeyList.size()) {
            throw new IndexOutOfBoundsException("getRemoveKey: Index value '" + index + "' not in range [0.." + (this.removeKeyList.size() - 1) + "]");
        }

        return (java.lang.String) removeKeyList.get(index);
    }

    /**
     * Method getRemoveKey.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public java.lang.String[] getRemoveKey() {
        java.lang.String[] array = new java.lang.String[0];
        return this.removeKeyList.toArray(array);
    }

    /**
     * Method getRemoveKeyCount.
     * 
     * @return the size of this collection
     */
    public int getRemoveKeyCount() {
        return this.removeKeyList.size();
    }

    /**
     * Returns the value of field 'requestCondition'.
     * 
     * @return the value of field 'RequestCondition'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.RequestCondition getRequestCondition() {
        return this.requestCondition;
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
     * Method iterateRemoveKey.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends java.lang.String> iterateRemoveKey() {
        return this.removeKeyList.iterator();
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
     */
    public void removeAllRemoveKey() {
        this.removeKeyList.clear();
    }

    /**
     * Method removeRemoveKey.
     * 
     * @param vRemoveKey
     * @return true if the object was removed from the collection.
     */
    public boolean removeRemoveKey(final java.lang.String vRemoveKey) {
        boolean removed = removeKeyList.remove(vRemoveKey);
        return removed;
    }

    /**
     * Method removeRemoveKeyAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public java.lang.String removeRemoveKeyAt(final int index) {
        java.lang.Object obj = this.removeKeyList.remove(index);
        return (java.lang.String) obj;
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
     * 
     * 
     * @param index
     * @param vRemoveKey
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setRemoveKey(final int index,final java.lang.String vRemoveKey) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.removeKeyList.size()) {
            throw new IndexOutOfBoundsException("setRemoveKey: Index value '" + index + "' not in range [0.." + (this.removeKeyList.size() - 1) + "]");
        }

        this.removeKeyList.set(index, vRemoveKey);
    }

    /**
     * 
     * 
     * @param vRemoveKeyArray
     */
    public void setRemoveKey(final java.lang.String[] vRemoveKeyArray) {
        //-- copy array
        removeKeyList.clear();

        for (int i = 0; i < vRemoveKeyArray.length; i++) {
                this.removeKeyList.add(vRemoveKeyArray[i]);
        }
    }

    /**
     * Sets the value of field 'requestCondition'.
     * 
     * @param requestCondition the value of field 'requestCondition'
     */
    public void setRequestCondition(final com.cdoframework.cdolib.service.framework.transfilter.xsd.RequestCondition requestCondition) {
        this.requestCondition = requestCondition;
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
     * com.cdoframework.cdolib.service.framework.transfilter.xsd.TransCacheType
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.xsd.TransCacheType unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.service.framework.transfilter.xsd.TransCacheType) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.xsd.TransCacheType.class, reader);
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
