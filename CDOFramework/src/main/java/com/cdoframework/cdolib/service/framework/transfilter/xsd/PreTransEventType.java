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
public class PreTransEventType implements java.io.Serializable {

    private java.util.List<com.cdoframework.cdolib.service.framework.transfilter.xsd.PreTransEventTypeItem> _items;

    public PreTransEventType() {
        super();
        this._items = new java.util.ArrayList<com.cdoframework.cdolib.service.framework.transfilter.xsd.PreTransEventTypeItem>();
    }

    /**
     * 
     * 
     * @param vPreTransEventTypeItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addPreTransEventTypeItem(final com.cdoframework.cdolib.service.framework.transfilter.xsd.PreTransEventTypeItem vPreTransEventTypeItem) throws java.lang.IndexOutOfBoundsException {
        this._items.add(vPreTransEventTypeItem);
    }

    /**
     * 
     * 
     * @param index
     * @param vPreTransEventTypeItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addPreTransEventTypeItem(final int index,final com.cdoframework.cdolib.service.framework.transfilter.xsd.PreTransEventTypeItem vPreTransEventTypeItem) throws java.lang.IndexOutOfBoundsException {
        this._items.add(index, vPreTransEventTypeItem);
    }

    /**
     * Method enumeratePreTransEventTypeItem.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.service.framework.transfilter.xsd.PreTransEventTypeItem> enumeratePreTransEventTypeItem() {
        return java.util.Collections.enumeration(this._items);
    }

    /**
     * Method getPreTransEventTypeItem.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.service.framework.transfilter.xsd.PreTransEventTypeItem
     * at the given index
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.PreTransEventTypeItem getPreTransEventTypeItem(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("getPreTransEventTypeItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        return _items.get(index);
    }

    /**
     * Method getPreTransEventTypeItem.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.PreTransEventTypeItem[] getPreTransEventTypeItem() {
        com.cdoframework.cdolib.service.framework.transfilter.xsd.PreTransEventTypeItem[] array = new com.cdoframework.cdolib.service.framework.transfilter.xsd.PreTransEventTypeItem[0];
        return this._items.toArray(array);
    }

    /**
     * Method getPreTransEventTypeItemCount.
     * 
     * @return the size of this collection
     */
    public int getPreTransEventTypeItemCount() {
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
     * Method iteratePreTransEventTypeItem.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.service.framework.transfilter.xsd.PreTransEventTypeItem> iteratePreTransEventTypeItem() {
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
    public void removeAllPreTransEventTypeItem() {
        this._items.clear();
    }

    /**
     * Method removePreTransEventTypeItem.
     * 
     * @param vPreTransEventTypeItem
     * @return true if the object was removed from the collection.
     */
    public boolean removePreTransEventTypeItem(final com.cdoframework.cdolib.service.framework.transfilter.xsd.PreTransEventTypeItem vPreTransEventTypeItem) {
        boolean removed = _items.remove(vPreTransEventTypeItem);
        return removed;
    }

    /**
     * Method removePreTransEventTypeItemAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.PreTransEventTypeItem removePreTransEventTypeItemAt(final int index) {
        java.lang.Object obj = this._items.remove(index);
        return (com.cdoframework.cdolib.service.framework.transfilter.xsd.PreTransEventTypeItem) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vPreTransEventTypeItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setPreTransEventTypeItem(final int index,final com.cdoframework.cdolib.service.framework.transfilter.xsd.PreTransEventTypeItem vPreTransEventTypeItem) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("setPreTransEventTypeItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        this._items.set(index, vPreTransEventTypeItem);
    }

    /**
     * 
     * 
     * @param vPreTransEventTypeItemArray
     */
    public void setPreTransEventTypeItem(final com.cdoframework.cdolib.service.framework.transfilter.xsd.PreTransEventTypeItem[] vPreTransEventTypeItemArray) {
        //-- copy array
        _items.clear();

        for (int i = 0; i < vPreTransEventTypeItemArray.length; i++) {
                this._items.add(vPreTransEventTypeItemArray[i]);
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
     * com.cdoframework.cdolib.service.framework.transfilter.xsd.PreTransEventType
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.xsd.PreTransEventType unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.service.framework.transfilter.xsd.PreTransEventType) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.xsd.PreTransEventType.class, reader);
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
