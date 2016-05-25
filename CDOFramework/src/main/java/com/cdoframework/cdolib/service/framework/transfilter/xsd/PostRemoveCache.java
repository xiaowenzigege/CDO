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
public class PostRemoveCache implements java.io.Serializable {

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

    private java.util.List<com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveCache> removeCacheList;

    public PostRemoveCache() {
        super();
        this.removeCacheList = new java.util.ArrayList<com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveCache>();
    }

    /**
     * 
     * 
     * @param vRemoveCache
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addRemoveCache(final com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveCache vRemoveCache) throws java.lang.IndexOutOfBoundsException {
        this.removeCacheList.add(vRemoveCache);
    }

    /**
     * 
     * 
     * @param index
     * @param vRemoveCache
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addRemoveCache(final int index,final com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveCache vRemoveCache) throws java.lang.IndexOutOfBoundsException {
        this.removeCacheList.add(index, vRemoveCache);
    }

    /**
     */
    public void deleteSyn() {
        this._hassyn= false;
    }

    /**
     * Method enumerateRemoveCache.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveCache> enumerateRemoveCache() {
        return java.util.Collections.enumeration(this.removeCacheList);
    }

    /**
     * Method getRemoveCache.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveCache
     * at the given index
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveCache getRemoveCache(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.removeCacheList.size()) {
            throw new IndexOutOfBoundsException("getRemoveCache: Index value '" + index + "' not in range [0.." + (this.removeCacheList.size() - 1) + "]");
        }

        return removeCacheList.get(index);
    }

    /**
     * Method getRemoveCache.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveCache[] getRemoveCache() {
        com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveCache[] array = new com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveCache[0];
        return this.removeCacheList.toArray(array);
    }

    /**
     * Method getRemoveCacheCount.
     * 
     * @return the size of this collection
     */
    public int getRemoveCacheCount() {
        return this.removeCacheList.size();
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
     * Method iterateRemoveCache.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveCache> iterateRemoveCache() {
        return this.removeCacheList.iterator();
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
    public void removeAllRemoveCache() {
        this.removeCacheList.clear();
    }

    /**
     * Method removeRemoveCache.
     * 
     * @param vRemoveCache
     * @return true if the object was removed from the collection.
     */
    public boolean removeRemoveCache(final com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveCache vRemoveCache) {
        boolean removed = removeCacheList.remove(vRemoveCache);
        return removed;
    }

    /**
     * Method removeRemoveCacheAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveCache removeRemoveCacheAt(final int index) {
        java.lang.Object obj = this.removeCacheList.remove(index);
        return (com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveCache) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vRemoveCache
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setRemoveCache(final int index,final com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveCache vRemoveCache) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.removeCacheList.size()) {
            throw new IndexOutOfBoundsException("setRemoveCache: Index value '" + index + "' not in range [0.." + (this.removeCacheList.size() - 1) + "]");
        }

        this.removeCacheList.set(index, vRemoveCache);
    }

    /**
     * 
     * 
     * @param vRemoveCacheArray
     */
    public void setRemoveCache(final com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveCache[] vRemoveCacheArray) {
        //-- copy array
        removeCacheList.clear();

        for (int i = 0; i < vRemoveCacheArray.length; i++) {
                this.removeCacheList.add(vRemoveCacheArray[i]);
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
     * com.cdoframework.cdolib.service.framework.transfilter.xsd.PostRemoveCache
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.xsd.PostRemoveCache unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.service.framework.transfilter.xsd.PostRemoveCache) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.xsd.PostRemoveCache.class, reader);
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
