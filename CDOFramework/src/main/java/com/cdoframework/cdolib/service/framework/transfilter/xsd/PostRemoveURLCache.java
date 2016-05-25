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
public class PostRemoveURLCache implements java.io.Serializable {

    /**
     * Field syn.
     */
    private boolean syn = false;

    /**
     * Keeps track of whether primitive field syn has been set
     * already.
     */
    private boolean _hassyn;

    /**
     * Field requestCondition.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.RequestCondition requestCondition;

    /**
     * Field responseCondition.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.ResponseCondition responseCondition;

    private java.util.List<com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveURLCache> removeURLCacheList;

    public PostRemoveURLCache() {
        super();
        this.removeURLCacheList = new java.util.ArrayList<com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveURLCache>();
    }

    /**
     * 
     * 
     * @param vRemoveURLCache
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addRemoveURLCache(final com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveURLCache vRemoveURLCache) throws java.lang.IndexOutOfBoundsException {
        this.removeURLCacheList.add(vRemoveURLCache);
    }

    /**
     * 
     * 
     * @param index
     * @param vRemoveURLCache
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addRemoveURLCache(final int index,final com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveURLCache vRemoveURLCache) throws java.lang.IndexOutOfBoundsException {
        this.removeURLCacheList.add(index, vRemoveURLCache);
    }

    /**
     */
    public void deleteSyn() {
        this._hassyn= false;
    }

    /**
     * Method enumerateRemoveURLCache.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveURLCache> enumerateRemoveURLCache() {
        return java.util.Collections.enumeration(this.removeURLCacheList);
    }

    /**
     * Method getRemoveURLCache.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveURLCache
     * at the given index
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveURLCache getRemoveURLCache(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.removeURLCacheList.size()) {
            throw new IndexOutOfBoundsException("getRemoveURLCache: Index value '" + index + "' not in range [0.." + (this.removeURLCacheList.size() - 1) + "]");
        }

        return removeURLCacheList.get(index);
    }

    /**
     * Method getRemoveURLCache.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveURLCache[] getRemoveURLCache() {
        com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveURLCache[] array = new com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveURLCache[0];
        return this.removeURLCacheList.toArray(array);
    }

    /**
     * Method getRemoveURLCacheCount.
     * 
     * @return the size of this collection
     */
    public int getRemoveURLCacheCount() {
        return this.removeURLCacheList.size();
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
     * Returns the value of field 'responseCondition'.
     * 
     * @return the value of field 'ResponseCondition'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.ResponseCondition getResponseCondition() {
        return this.responseCondition;
    }

    /**
     * Returns the value of field 'syn'.
     * 
     * @return the value of field 'Syn'.
     */
    public boolean getSyn() {
        return this.syn;
    }

    /**
     * Method hasSyn.
     * 
     * @return true if at least one Syn has been added
     */
    public boolean hasSyn() {
        return this._hassyn;
    }

    /**
     * Returns the value of field 'syn'.
     * 
     * @return the value of field 'Syn'.
     */
    public boolean isSyn() {
        return this.syn;
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
     * Method iterateRemoveURLCache.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveURLCache> iterateRemoveURLCache() {
        return this.removeURLCacheList.iterator();
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
    public void removeAllRemoveURLCache() {
        this.removeURLCacheList.clear();
    }

    /**
     * Method removeRemoveURLCache.
     * 
     * @param vRemoveURLCache
     * @return true if the object was removed from the collection.
     */
    public boolean removeRemoveURLCache(final com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveURLCache vRemoveURLCache) {
        boolean removed = removeURLCacheList.remove(vRemoveURLCache);
        return removed;
    }

    /**
     * Method removeRemoveURLCacheAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveURLCache removeRemoveURLCacheAt(final int index) {
        java.lang.Object obj = this.removeURLCacheList.remove(index);
        return (com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveURLCache) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vRemoveURLCache
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setRemoveURLCache(final int index,final com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveURLCache vRemoveURLCache) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.removeURLCacheList.size()) {
            throw new IndexOutOfBoundsException("setRemoveURLCache: Index value '" + index + "' not in range [0.." + (this.removeURLCacheList.size() - 1) + "]");
        }

        this.removeURLCacheList.set(index, vRemoveURLCache);
    }

    /**
     * 
     * 
     * @param vRemoveURLCacheArray
     */
    public void setRemoveURLCache(final com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveURLCache[] vRemoveURLCacheArray) {
        //-- copy array
        removeURLCacheList.clear();

        for (int i = 0; i < vRemoveURLCacheArray.length; i++) {
                this.removeURLCacheList.add(vRemoveURLCacheArray[i]);
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
     * Sets the value of field 'responseCondition'.
     * 
     * @param responseCondition the value of field
     * 'responseCondition'.
     */
    public void setResponseCondition(final com.cdoframework.cdolib.service.framework.transfilter.xsd.ResponseCondition responseCondition) {
        this.responseCondition = responseCondition;
    }

    /**
     * Sets the value of field 'syn'.
     * 
     * @param syn the value of field 'syn'.
     */
    public void setSyn(final boolean syn) {
        this.syn = syn;
        this._hassyn = true;
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
     * com.cdoframework.cdolib.service.framework.transfilter.xsd.PostRemoveURLCache
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.xsd.PostRemoveURLCache unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.service.framework.transfilter.xsd.PostRemoveURLCache) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.xsd.PostRemoveURLCache.class, reader);
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
