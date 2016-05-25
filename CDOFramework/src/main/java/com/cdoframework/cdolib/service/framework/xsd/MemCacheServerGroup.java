/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.3</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.service.framework.xsd;

/**
 * 
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class MemCacheServerGroup implements java.io.Serializable {

    /**
     * Field id.
     */
    private java.lang.String id;

    private java.util.List<com.cdoframework.cdolib.service.framework.xsd.CacheServer> cacheServerList;

    public MemCacheServerGroup() {
        super();
        this.cacheServerList = new java.util.ArrayList<com.cdoframework.cdolib.service.framework.xsd.CacheServer>();
    }

    /**
     * 
     * 
     * @param vCacheServer
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addCacheServer(final com.cdoframework.cdolib.service.framework.xsd.CacheServer vCacheServer) throws java.lang.IndexOutOfBoundsException {
        this.cacheServerList.add(vCacheServer);
    }

    /**
     * 
     * 
     * @param index
     * @param vCacheServer
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addCacheServer(final int index,final com.cdoframework.cdolib.service.framework.xsd.CacheServer vCacheServer) throws java.lang.IndexOutOfBoundsException {
        this.cacheServerList.add(index, vCacheServer);
    }

    /**
     * Method enumerateCacheServer.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.service.framework.xsd.CacheServer> enumerateCacheServer() {
        return java.util.Collections.enumeration(this.cacheServerList);
    }

    /**
     * Method getCacheServer.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.service.framework.xsd.CacheServer at
     * the given index
     */
    public com.cdoframework.cdolib.service.framework.xsd.CacheServer getCacheServer(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.cacheServerList.size()) {
            throw new IndexOutOfBoundsException("getCacheServer: Index value '" + index + "' not in range [0.." + (this.cacheServerList.size() - 1) + "]");
        }

        return cacheServerList.get(index);
    }

    /**
     * Method getCacheServer.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.service.framework.xsd.CacheServer[] getCacheServer() {
        com.cdoframework.cdolib.service.framework.xsd.CacheServer[] array = new com.cdoframework.cdolib.service.framework.xsd.CacheServer[0];
        return this.cacheServerList.toArray(array);
    }

    /**
     * Method getCacheServerCount.
     * 
     * @return the size of this collection
     */
    public int getCacheServerCount() {
        return this.cacheServerList.size();
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
     * Method iterateCacheServer.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.service.framework.xsd.CacheServer> iterateCacheServer() {
        return this.cacheServerList.iterator();
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
    public void removeAllCacheServer() {
        this.cacheServerList.clear();
    }

    /**
     * Method removeCacheServer.
     * 
     * @param vCacheServer
     * @return true if the object was removed from the collection.
     */
    public boolean removeCacheServer(final com.cdoframework.cdolib.service.framework.xsd.CacheServer vCacheServer) {
        boolean removed = cacheServerList.remove(vCacheServer);
        return removed;
    }

    /**
     * Method removeCacheServerAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.service.framework.xsd.CacheServer removeCacheServerAt(final int index) {
        java.lang.Object obj = this.cacheServerList.remove(index);
        return (com.cdoframework.cdolib.service.framework.xsd.CacheServer) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vCacheServer
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setCacheServer(final int index,final com.cdoframework.cdolib.service.framework.xsd.CacheServer vCacheServer) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.cacheServerList.size()) {
            throw new IndexOutOfBoundsException("setCacheServer: Index value '" + index + "' not in range [0.." + (this.cacheServerList.size() - 1) + "]");
        }

        this.cacheServerList.set(index, vCacheServer);
    }

    /**
     * 
     * 
     * @param vCacheServerArray
     */
    public void setCacheServer(final com.cdoframework.cdolib.service.framework.xsd.CacheServer[] vCacheServerArray) {
        //-- copy array
        cacheServerList.clear();

        for (int i = 0; i < vCacheServerArray.length; i++) {
                this.cacheServerList.add(vCacheServerArray[i]);
        }
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
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled
     * com.cdoframework.cdolib.service.framework.xsd.MemCacheServerGroup
     */
    public static com.cdoframework.cdolib.service.framework.xsd.MemCacheServerGroup unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.service.framework.xsd.MemCacheServerGroup) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.xsd.MemCacheServerGroup.class, reader);
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
