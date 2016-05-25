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
public class RemoveURLCache implements java.io.Serializable {

    /**
     * Field serverId.
     */
    private java.lang.String serverId;

    private java.util.List<com.cdoframework.cdolib.service.framework.transfilter.xsd.CacheURL> cacheURLList;

    public RemoveURLCache() {
        super();
        this.cacheURLList = new java.util.ArrayList<com.cdoframework.cdolib.service.framework.transfilter.xsd.CacheURL>();
    }

    /**
     * 
     * 
     * @param vCacheURL
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addCacheURL(final com.cdoframework.cdolib.service.framework.transfilter.xsd.CacheURL vCacheURL) throws java.lang.IndexOutOfBoundsException {
        this.cacheURLList.add(vCacheURL);
    }

    /**
     * 
     * 
     * @param index
     * @param vCacheURL
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addCacheURL(final int index,final com.cdoframework.cdolib.service.framework.transfilter.xsd.CacheURL vCacheURL) throws java.lang.IndexOutOfBoundsException {
        this.cacheURLList.add(index, vCacheURL);
    }

    /**
     * Method enumerateCacheURL.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.service.framework.transfilter.xsd.CacheURL> enumerateCacheURL() {
        return java.util.Collections.enumeration(this.cacheURLList);
    }

    /**
     * Method getCacheURL.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.service.framework.transfilter.xsd.CacheURL
     * at the given index
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.CacheURL getCacheURL(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.cacheURLList.size()) {
            throw new IndexOutOfBoundsException("getCacheURL: Index value '" + index + "' not in range [0.." + (this.cacheURLList.size() - 1) + "]");
        }

        return cacheURLList.get(index);
    }

    /**
     * Method getCacheURL.Returns the contents of the collection in
     * an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.CacheURL[] getCacheURL() {
        com.cdoframework.cdolib.service.framework.transfilter.xsd.CacheURL[] array = new com.cdoframework.cdolib.service.framework.transfilter.xsd.CacheURL[0];
        return this.cacheURLList.toArray(array);
    }

    /**
     * Method getCacheURLCount.
     * 
     * @return the size of this collection
     */
    public int getCacheURLCount() {
        return this.cacheURLList.size();
    }

    /**
     * Returns the value of field 'serverId'.
     * 
     * @return the value of field 'ServerId'.
     */
    public java.lang.String getServerId() {
        return this.serverId;
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
     * Method iterateCacheURL.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.service.framework.transfilter.xsd.CacheURL> iterateCacheURL() {
        return this.cacheURLList.iterator();
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
    public void removeAllCacheURL() {
        this.cacheURLList.clear();
    }

    /**
     * Method removeCacheURL.
     * 
     * @param vCacheURL
     * @return true if the object was removed from the collection.
     */
    public boolean removeCacheURL(final com.cdoframework.cdolib.service.framework.transfilter.xsd.CacheURL vCacheURL) {
        boolean removed = cacheURLList.remove(vCacheURL);
        return removed;
    }

    /**
     * Method removeCacheURLAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.CacheURL removeCacheURLAt(final int index) {
        java.lang.Object obj = this.cacheURLList.remove(index);
        return (com.cdoframework.cdolib.service.framework.transfilter.xsd.CacheURL) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vCacheURL
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setCacheURL(final int index,final com.cdoframework.cdolib.service.framework.transfilter.xsd.CacheURL vCacheURL) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.cacheURLList.size()) {
            throw new IndexOutOfBoundsException("setCacheURL: Index value '" + index + "' not in range [0.." + (this.cacheURLList.size() - 1) + "]");
        }

        this.cacheURLList.set(index, vCacheURL);
    }

    /**
     * 
     * 
     * @param vCacheURLArray
     */
    public void setCacheURL(final com.cdoframework.cdolib.service.framework.transfilter.xsd.CacheURL[] vCacheURLArray) {
        //-- copy array
        cacheURLList.clear();

        for (int i = 0; i < vCacheURLArray.length; i++) {
                this.cacheURLList.add(vCacheURLArray[i]);
        }
    }

    /**
     * Sets the value of field 'serverId'.
     * 
     * @param serverId the value of field 'serverId'.
     */
    public void setServerId(final java.lang.String serverId) {
        this.serverId = serverId;
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
     * com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveURLCache
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveURLCache unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveURLCache) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveURLCache.class, reader);
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
