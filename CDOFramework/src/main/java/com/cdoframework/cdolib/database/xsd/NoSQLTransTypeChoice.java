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
public class NoSQLTransTypeChoice implements java.io.Serializable {

    private java.util.List<com.cdoframework.cdolib.database.xsd.NoSQLTransTypeChoiceItem> _items;

    public NoSQLTransTypeChoice() {
        super();
        this._items = new java.util.ArrayList<com.cdoframework.cdolib.database.xsd.NoSQLTransTypeChoiceItem>();
    }

    /**
     * 
     * 
     * @param vNoSQLTransTypeChoiceItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addNoSQLTransTypeChoiceItem(final com.cdoframework.cdolib.database.xsd.NoSQLTransTypeChoiceItem vNoSQLTransTypeChoiceItem) throws java.lang.IndexOutOfBoundsException {
        this._items.add(vNoSQLTransTypeChoiceItem);
    }

    /**
     * 
     * 
     * @param index
     * @param vNoSQLTransTypeChoiceItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addNoSQLTransTypeChoiceItem(final int index,final com.cdoframework.cdolib.database.xsd.NoSQLTransTypeChoiceItem vNoSQLTransTypeChoiceItem) throws java.lang.IndexOutOfBoundsException {
        this._items.add(index, vNoSQLTransTypeChoiceItem);
    }

    /**
     * Method enumerateNoSQLTransTypeChoiceItem.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.database.xsd.NoSQLTransTypeChoiceItem> enumerateNoSQLTransTypeChoiceItem() {
        return java.util.Collections.enumeration(this._items);
    }

    /**
     * Method getNoSQLTransTypeChoiceItem.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.database.xsd.NoSQLTransTypeChoiceItem
     * at the given index
     */
    public com.cdoframework.cdolib.database.xsd.NoSQLTransTypeChoiceItem getNoSQLTransTypeChoiceItem(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("getNoSQLTransTypeChoiceItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        return _items.get(index);
    }

    /**
     * Method getNoSQLTransTypeChoiceItem.Returns the contents of
     * the collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.database.xsd.NoSQLTransTypeChoiceItem[] getNoSQLTransTypeChoiceItem() {
        com.cdoframework.cdolib.database.xsd.NoSQLTransTypeChoiceItem[] array = new com.cdoframework.cdolib.database.xsd.NoSQLTransTypeChoiceItem[0];
        return this._items.toArray(array);
    }

    /**
     * Method getNoSQLTransTypeChoiceItemCount.
     * 
     * @return the size of this collection
     */
    public int getNoSQLTransTypeChoiceItemCount() {
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
     * Method iterateNoSQLTransTypeChoiceItem.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.database.xsd.NoSQLTransTypeChoiceItem> iterateNoSQLTransTypeChoiceItem() {
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
    public void removeAllNoSQLTransTypeChoiceItem() {
        this._items.clear();
    }

    /**
     * Method removeNoSQLTransTypeChoiceItem.
     * 
     * @param vNoSQLTransTypeChoiceItem
     * @return true if the object was removed from the collection.
     */
    public boolean removeNoSQLTransTypeChoiceItem(final com.cdoframework.cdolib.database.xsd.NoSQLTransTypeChoiceItem vNoSQLTransTypeChoiceItem) {
        boolean removed = _items.remove(vNoSQLTransTypeChoiceItem);
        return removed;
    }

    /**
     * Method removeNoSQLTransTypeChoiceItemAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.database.xsd.NoSQLTransTypeChoiceItem removeNoSQLTransTypeChoiceItemAt(final int index) {
        java.lang.Object obj = this._items.remove(index);
        return (com.cdoframework.cdolib.database.xsd.NoSQLTransTypeChoiceItem) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vNoSQLTransTypeChoiceItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setNoSQLTransTypeChoiceItem(final int index,final com.cdoframework.cdolib.database.xsd.NoSQLTransTypeChoiceItem vNoSQLTransTypeChoiceItem) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("setNoSQLTransTypeChoiceItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        this._items.set(index, vNoSQLTransTypeChoiceItem);
    }

    /**
     * 
     * 
     * @param vNoSQLTransTypeChoiceItemArray
     */
    public void setNoSQLTransTypeChoiceItem(final com.cdoframework.cdolib.database.xsd.NoSQLTransTypeChoiceItem[] vNoSQLTransTypeChoiceItemArray) {
        //-- copy array
        _items.clear();

        for (int i = 0; i < vNoSQLTransTypeChoiceItemArray.length; i++) {
                this._items.add(vNoSQLTransTypeChoiceItemArray[i]);
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
     * com.cdoframework.cdolib.database.xsd.NoSQLTransTypeChoice
     */
    public static com.cdoframework.cdolib.database.xsd.NoSQLTransTypeChoice unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.database.xsd.NoSQLTransTypeChoice) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.database.xsd.NoSQLTransTypeChoice.class, reader);
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
