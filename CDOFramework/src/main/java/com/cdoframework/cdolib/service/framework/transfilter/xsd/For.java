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
public class For implements java.io.Serializable {

    /**
     * Field indexId.
     */
    private java.lang.String indexId;

    /**
     * Field fromIndex.
     */
    private java.lang.String fromIndex;

    /**
     * Field count.
     */
    private java.lang.String count;

    private java.util.List<com.cdoframework.cdolib.service.framework.transfilter.xsd.ForItem> _items;

    public For() {
        super();
        this._items = new java.util.ArrayList<com.cdoframework.cdolib.service.framework.transfilter.xsd.ForItem>();
    }

    /**
     * 
     * 
     * @param vForItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addForItem(final com.cdoframework.cdolib.service.framework.transfilter.xsd.ForItem vForItem) throws java.lang.IndexOutOfBoundsException {
        this._items.add(vForItem);
    }

    /**
     * 
     * 
     * @param index
     * @param vForItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addForItem(final int index,final com.cdoframework.cdolib.service.framework.transfilter.xsd.ForItem vForItem) throws java.lang.IndexOutOfBoundsException {
        this._items.add(index, vForItem);
    }

    /**
     * Method enumerateForItem.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.service.framework.transfilter.xsd.ForItem> enumerateForItem() {
        return java.util.Collections.enumeration(this._items);
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
     * Method getForItem.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.service.framework.transfilter.xsd.ForItem
     * at the given index
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.ForItem getForItem(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("getForItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        return _items.get(index);
    }

    /**
     * Method getForItem.Returns the contents of the collection in
     * an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.ForItem[] getForItem() {
        com.cdoframework.cdolib.service.framework.transfilter.xsd.ForItem[] array = new com.cdoframework.cdolib.service.framework.transfilter.xsd.ForItem[0];
        return this._items.toArray(array);
    }

    /**
     * Method getForItemCount.
     * 
     * @return the size of this collection
     */
    public int getForItemCount() {
        return this._items.size();
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
     * Method iterateForItem.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.service.framework.transfilter.xsd.ForItem> iterateForItem() {
        return this._items.iterator();
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
    public void removeAllForItem() {
        this._items.clear();
    }

    /**
     * Method removeForItem.
     * 
     * @param vForItem
     * @return true if the object was removed from the collection.
     */
    public boolean removeForItem(final com.cdoframework.cdolib.service.framework.transfilter.xsd.ForItem vForItem) {
        boolean removed = _items.remove(vForItem);
        return removed;
    }

    /**
     * Method removeForItemAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.ForItem removeForItemAt(final int index) {
        java.lang.Object obj = this._items.remove(index);
        return (com.cdoframework.cdolib.service.framework.transfilter.xsd.ForItem) obj;
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
     * 
     * 
     * @param index
     * @param vForItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setForItem(final int index,final com.cdoframework.cdolib.service.framework.transfilter.xsd.ForItem vForItem) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("setForItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        this._items.set(index, vForItem);
    }

    /**
     * 
     * 
     * @param vForItemArray
     */
    public void setForItem(final com.cdoframework.cdolib.service.framework.transfilter.xsd.ForItem[] vForItemArray) {
        //-- copy array
        _items.clear();

        for (int i = 0; i < vForItemArray.length; i++) {
                this._items.add(vForItemArray[i]);
        }
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
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled
     * com.cdoframework.cdolib.service.framework.transfilter.xsd.For
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.xsd.For unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.service.framework.transfilter.xsd.For) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.xsd.For.class, reader);
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
