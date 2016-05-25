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
public class Transaction implements java.io.Serializable {

    /**
     * Field serviceName.
     */
    private java.lang.String serviceName;

    /**
     * Field transName.
     */
    private java.lang.String transName;

    private java.util.List<com.cdoframework.cdolib.service.framework.transfilter.xsd.RequestKey> requestKeyList;

    public Transaction() {
        super();
        this.requestKeyList = new java.util.ArrayList<com.cdoframework.cdolib.service.framework.transfilter.xsd.RequestKey>();
    }

    /**
     * 
     * 
     * @param vRequestKey
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addRequestKey(final com.cdoframework.cdolib.service.framework.transfilter.xsd.RequestKey vRequestKey) throws java.lang.IndexOutOfBoundsException {
        this.requestKeyList.add(vRequestKey);
    }

    /**
     * 
     * 
     * @param index
     * @param vRequestKey
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addRequestKey(final int index,final com.cdoframework.cdolib.service.framework.transfilter.xsd.RequestKey vRequestKey) throws java.lang.IndexOutOfBoundsException {
        this.requestKeyList.add(index, vRequestKey);
    }

    /**
     * Method enumerateRequestKey.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.service.framework.transfilter.xsd.RequestKey> enumerateRequestKey() {
        return java.util.Collections.enumeration(this.requestKeyList);
    }

    /**
     * Method getRequestKey.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.service.framework.transfilter.xsd.RequestKey
     * at the given index
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.RequestKey getRequestKey(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.requestKeyList.size()) {
            throw new IndexOutOfBoundsException("getRequestKey: Index value '" + index + "' not in range [0.." + (this.requestKeyList.size() - 1) + "]");
        }

        return requestKeyList.get(index);
    }

    /**
     * Method getRequestKey.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.RequestKey[] getRequestKey() {
        com.cdoframework.cdolib.service.framework.transfilter.xsd.RequestKey[] array = new com.cdoframework.cdolib.service.framework.transfilter.xsd.RequestKey[0];
        return this.requestKeyList.toArray(array);
    }

    /**
     * Method getRequestKeyCount.
     * 
     * @return the size of this collection
     */
    public int getRequestKeyCount() {
        return this.requestKeyList.size();
    }

    /**
     * Returns the value of field 'serviceName'.
     * 
     * @return the value of field 'ServiceName'.
     */
    public java.lang.String getServiceName() {
        return this.serviceName;
    }

    /**
     * Returns the value of field 'transName'.
     * 
     * @return the value of field 'TransName'.
     */
    public java.lang.String getTransName() {
        return this.transName;
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
     * Method iterateRequestKey.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.service.framework.transfilter.xsd.RequestKey> iterateRequestKey() {
        return this.requestKeyList.iterator();
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
    public void removeAllRequestKey() {
        this.requestKeyList.clear();
    }

    /**
     * Method removeRequestKey.
     * 
     * @param vRequestKey
     * @return true if the object was removed from the collection.
     */
    public boolean removeRequestKey(final com.cdoframework.cdolib.service.framework.transfilter.xsd.RequestKey vRequestKey) {
        boolean removed = requestKeyList.remove(vRequestKey);
        return removed;
    }

    /**
     * Method removeRequestKeyAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.RequestKey removeRequestKeyAt(final int index) {
        java.lang.Object obj = this.requestKeyList.remove(index);
        return (com.cdoframework.cdolib.service.framework.transfilter.xsd.RequestKey) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vRequestKey
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setRequestKey(final int index,final com.cdoframework.cdolib.service.framework.transfilter.xsd.RequestKey vRequestKey) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.requestKeyList.size()) {
            throw new IndexOutOfBoundsException("setRequestKey: Index value '" + index + "' not in range [0.." + (this.requestKeyList.size() - 1) + "]");
        }

        this.requestKeyList.set(index, vRequestKey);
    }

    /**
     * 
     * 
     * @param vRequestKeyArray
     */
    public void setRequestKey(final com.cdoframework.cdolib.service.framework.transfilter.xsd.RequestKey[] vRequestKeyArray) {
        //-- copy array
        requestKeyList.clear();

        for (int i = 0; i < vRequestKeyArray.length; i++) {
                this.requestKeyList.add(vRequestKeyArray[i]);
        }
    }

    /**
     * Sets the value of field 'serviceName'.
     * 
     * @param serviceName the value of field 'serviceName'.
     */
    public void setServiceName(final java.lang.String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * Sets the value of field 'transName'.
     * 
     * @param transName the value of field 'transName'.
     */
    public void setTransName(final java.lang.String transName) {
        this.transName = transName;
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
     * com.cdoframework.cdolib.service.framework.transfilter.xsd.Transaction
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.xsd.Transaction unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.service.framework.transfilter.xsd.Transaction) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.xsd.Transaction.class, reader);
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
