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
public class PreLoadCache implements java.io.Serializable {

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

    private java.util.List<com.cdoframework.cdolib.service.framework.transfilter.xsd.LoadCache> loadCacheList;

    public PreLoadCache() {
        super();
        this.loadCacheList = new java.util.ArrayList<com.cdoframework.cdolib.service.framework.transfilter.xsd.LoadCache>();
    }

    /**
     * 
     * 
     * @param vLoadCache
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addLoadCache(final com.cdoframework.cdolib.service.framework.transfilter.xsd.LoadCache vLoadCache) throws java.lang.IndexOutOfBoundsException {
        this.loadCacheList.add(vLoadCache);
    }

    /**
     * 
     * 
     * @param index
     * @param vLoadCache
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addLoadCache(final int index,final com.cdoframework.cdolib.service.framework.transfilter.xsd.LoadCache vLoadCache) throws java.lang.IndexOutOfBoundsException {
        this.loadCacheList.add(index, vLoadCache);
    }

    /**
     */
    public void deleteSyn() {
        this._hassyn= false;
    }

    /**
     * Method enumerateLoadCache.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.service.framework.transfilter.xsd.LoadCache> enumerateLoadCache() {
        return java.util.Collections.enumeration(this.loadCacheList);
    }

    /**
     * Method getLoadCache.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.service.framework.transfilter.xsd.LoadCache
     * at the given index
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.LoadCache getLoadCache(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.loadCacheList.size()) {
            throw new IndexOutOfBoundsException("getLoadCache: Index value '" + index + "' not in range [0.." + (this.loadCacheList.size() - 1) + "]");
        }

        return loadCacheList.get(index);
    }

    /**
     * Method getLoadCache.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.LoadCache[] getLoadCache() {
        com.cdoframework.cdolib.service.framework.transfilter.xsd.LoadCache[] array = new com.cdoframework.cdolib.service.framework.transfilter.xsd.LoadCache[0];
        return this.loadCacheList.toArray(array);
    }

    /**
     * Method getLoadCacheCount.
     * 
     * @return the size of this collection
     */
    public int getLoadCacheCount() {
        return this.loadCacheList.size();
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
     * Method iterateLoadCache.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.service.framework.transfilter.xsd.LoadCache> iterateLoadCache() {
        return this.loadCacheList.iterator();
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
    public void removeAllLoadCache() {
        this.loadCacheList.clear();
    }

    /**
     * Method removeLoadCache.
     * 
     * @param vLoadCache
     * @return true if the object was removed from the collection.
     */
    public boolean removeLoadCache(final com.cdoframework.cdolib.service.framework.transfilter.xsd.LoadCache vLoadCache) {
        boolean removed = loadCacheList.remove(vLoadCache);
        return removed;
    }

    /**
     * Method removeLoadCacheAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.LoadCache removeLoadCacheAt(final int index) {
        java.lang.Object obj = this.loadCacheList.remove(index);
        return (com.cdoframework.cdolib.service.framework.transfilter.xsd.LoadCache) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vLoadCache
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setLoadCache(final int index,final com.cdoframework.cdolib.service.framework.transfilter.xsd.LoadCache vLoadCache) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.loadCacheList.size()) {
            throw new IndexOutOfBoundsException("setLoadCache: Index value '" + index + "' not in range [0.." + (this.loadCacheList.size() - 1) + "]");
        }

        this.loadCacheList.set(index, vLoadCache);
    }

    /**
     * 
     * 
     * @param vLoadCacheArray
     */
    public void setLoadCache(final com.cdoframework.cdolib.service.framework.transfilter.xsd.LoadCache[] vLoadCacheArray) {
        //-- copy array
        loadCacheList.clear();

        for (int i = 0; i < vLoadCacheArray.length; i++) {
                this.loadCacheList.add(vLoadCacheArray[i]);
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
     * com.cdoframework.cdolib.service.framework.transfilter.xsd.PreLoadCache
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.xsd.PreLoadCache unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.service.framework.transfilter.xsd.PreLoadCache) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.xsd.PreLoadCache.class, reader);
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
