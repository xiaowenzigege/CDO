/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.3</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.database.xsd;

/**
 * 
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class DataServiceChoice implements java.io.Serializable {

    private java.util.List<com.cdoframework.cdolib.database.xsd.DataServiceChoiceItem> _items;

    public DataServiceChoice() {
        super();
        this._items = new java.util.ArrayList<com.cdoframework.cdolib.database.xsd.DataServiceChoiceItem>();
    }

    /**
     * 
     * 
     * @param vDataServiceChoiceItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addDataServiceChoiceItem(final com.cdoframework.cdolib.database.xsd.DataServiceChoiceItem vDataServiceChoiceItem) throws java.lang.IndexOutOfBoundsException {
        this._items.add(vDataServiceChoiceItem);
    }

    /**
     * 
     * 
     * @param index
     * @param vDataServiceChoiceItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addDataServiceChoiceItem(final int index,final com.cdoframework.cdolib.database.xsd.DataServiceChoiceItem vDataServiceChoiceItem) throws java.lang.IndexOutOfBoundsException {
        this._items.add(index, vDataServiceChoiceItem);
    }

    /**
     * Method enumerateDataServiceChoiceItem.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.database.xsd.DataServiceChoiceItem> enumerateDataServiceChoiceItem() {
        return java.util.Collections.enumeration(this._items);
    }

    /**
     * Method getDataServiceChoiceItem.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.database.xsd.DataServiceChoiceItem
     * at the given index
     */
    public com.cdoframework.cdolib.database.xsd.DataServiceChoiceItem getDataServiceChoiceItem(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("getDataServiceChoiceItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        return _items.get(index);
    }

    /**
     * Method getDataServiceChoiceItem.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.database.xsd.DataServiceChoiceItem[] getDataServiceChoiceItem() {
        com.cdoframework.cdolib.database.xsd.DataServiceChoiceItem[] array = new com.cdoframework.cdolib.database.xsd.DataServiceChoiceItem[0];
        return this._items.toArray(array);
    }

    /**
     * Method getDataServiceChoiceItemCount.
     * 
     * @return the size of this collection
     */
    public int getDataServiceChoiceItemCount() {
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
     * Method iterateDataServiceChoiceItem.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.database.xsd.DataServiceChoiceItem> iterateDataServiceChoiceItem() {
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
    public void removeAllDataServiceChoiceItem() {
        this._items.clear();
    }

    /**
     * Method removeDataServiceChoiceItem.
     * 
     * @param vDataServiceChoiceItem
     * @return true if the object was removed from the collection.
     */
    public boolean removeDataServiceChoiceItem(final com.cdoframework.cdolib.database.xsd.DataServiceChoiceItem vDataServiceChoiceItem) {
        boolean removed = _items.remove(vDataServiceChoiceItem);
        return removed;
    }

    /**
     * Method removeDataServiceChoiceItemAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.database.xsd.DataServiceChoiceItem removeDataServiceChoiceItemAt(final int index) {
        java.lang.Object obj = this._items.remove(index);
        return (com.cdoframework.cdolib.database.xsd.DataServiceChoiceItem) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vDataServiceChoiceItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setDataServiceChoiceItem(final int index,final com.cdoframework.cdolib.database.xsd.DataServiceChoiceItem vDataServiceChoiceItem) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("setDataServiceChoiceItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        this._items.set(index, vDataServiceChoiceItem);
    }

    /**
     * 
     * 
     * @param vDataServiceChoiceItemArray
     */
    public void setDataServiceChoiceItem(final com.cdoframework.cdolib.database.xsd.DataServiceChoiceItem[] vDataServiceChoiceItemArray) {
        //-- copy array
        _items.clear();

        for (int i = 0; i < vDataServiceChoiceItemArray.length; i++) {
                this._items.add(vDataServiceChoiceItemArray[i]);
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
     * com.cdoframework.cdolib.database.xsd.DataServiceChoice
     */
    public static com.cdoframework.cdolib.database.xsd.DataServiceChoice unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.database.xsd.DataServiceChoice) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.database.xsd.DataServiceChoice.class, reader);
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
