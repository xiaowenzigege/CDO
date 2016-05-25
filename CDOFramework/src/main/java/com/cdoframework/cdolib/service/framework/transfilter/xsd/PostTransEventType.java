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
public class PostTransEventType implements java.io.Serializable {

    private java.util.List<com.cdoframework.cdolib.service.framework.transfilter.xsd.PostTransEventTypeItem> _items;

    public PostTransEventType() {
        super();
        this._items = new java.util.ArrayList<com.cdoframework.cdolib.service.framework.transfilter.xsd.PostTransEventTypeItem>();
    }

    /**
     * 
     * 
     * @param vPostTransEventTypeItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addPostTransEventTypeItem(final com.cdoframework.cdolib.service.framework.transfilter.xsd.PostTransEventTypeItem vPostTransEventTypeItem) throws java.lang.IndexOutOfBoundsException {
        this._items.add(vPostTransEventTypeItem);
    }

    /**
     * 
     * 
     * @param index
     * @param vPostTransEventTypeItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addPostTransEventTypeItem(final int index,final com.cdoframework.cdolib.service.framework.transfilter.xsd.PostTransEventTypeItem vPostTransEventTypeItem) throws java.lang.IndexOutOfBoundsException {
        this._items.add(index, vPostTransEventTypeItem);
    }

    /**
     * Method enumeratePostTransEventTypeItem.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.service.framework.transfilter.xsd.PostTransEventTypeItem> enumeratePostTransEventTypeItem() {
        return java.util.Collections.enumeration(this._items);
    }

    /**
     * Method getPostTransEventTypeItem.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.service.framework.transfilter.xsd.PostTransEventTypeItem
     * at the given index
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.PostTransEventTypeItem getPostTransEventTypeItem(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("getPostTransEventTypeItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        return _items.get(index);
    }

    /**
     * Method getPostTransEventTypeItem.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.PostTransEventTypeItem[] getPostTransEventTypeItem() {
        com.cdoframework.cdolib.service.framework.transfilter.xsd.PostTransEventTypeItem[] array = new com.cdoframework.cdolib.service.framework.transfilter.xsd.PostTransEventTypeItem[0];
        return this._items.toArray(array);
    }

    /**
     * Method getPostTransEventTypeItemCount.
     * 
     * @return the size of this collection
     */
    public int getPostTransEventTypeItemCount() {
        return this._items.size();
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
     * Method iteratePostTransEventTypeItem.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.service.framework.transfilter.xsd.PostTransEventTypeItem> iteratePostTransEventTypeItem() {
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
    public void removeAllPostTransEventTypeItem() {
        this._items.clear();
    }

    /**
     * Method removePostTransEventTypeItem.
     * 
     * @param vPostTransEventTypeItem
     * @return true if the object was removed from the collection.
     */
    public boolean removePostTransEventTypeItem(final com.cdoframework.cdolib.service.framework.transfilter.xsd.PostTransEventTypeItem vPostTransEventTypeItem) {
        boolean removed = _items.remove(vPostTransEventTypeItem);
        return removed;
    }

    /**
     * Method removePostTransEventTypeItemAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.PostTransEventTypeItem removePostTransEventTypeItemAt(final int index) {
        java.lang.Object obj = this._items.remove(index);
        return (com.cdoframework.cdolib.service.framework.transfilter.xsd.PostTransEventTypeItem) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vPostTransEventTypeItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setPostTransEventTypeItem(final int index,final com.cdoframework.cdolib.service.framework.transfilter.xsd.PostTransEventTypeItem vPostTransEventTypeItem) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("setPostTransEventTypeItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        this._items.set(index, vPostTransEventTypeItem);
    }

    /**
     * 
     * 
     * @param vPostTransEventTypeItemArray
     */
    public void setPostTransEventTypeItem(final com.cdoframework.cdolib.service.framework.transfilter.xsd.PostTransEventTypeItem[] vPostTransEventTypeItemArray) {
        //-- copy array
        _items.clear();

        for (int i = 0; i < vPostTransEventTypeItemArray.length; i++) {
                this._items.add(vPostTransEventTypeItemArray[i]);
        }
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
     * com.cdoframework.cdolib.service.framework.transfilter.xsd.PostTransEventType
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.xsd.PostTransEventType unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.service.framework.transfilter.xsd.PostTransEventType) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.xsd.PostTransEventType.class, reader);
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
