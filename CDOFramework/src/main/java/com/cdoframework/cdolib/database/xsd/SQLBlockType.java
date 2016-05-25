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
public class SQLBlockType implements java.io.Serializable {

    private java.util.List<com.cdoframework.cdolib.database.xsd.SQLBlockTypeItem> _items;

    public SQLBlockType() {
        super();
        this._items = new java.util.ArrayList<com.cdoframework.cdolib.database.xsd.SQLBlockTypeItem>();
    }

    /**
     * 
     * 
     * @param vSQLBlockTypeItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addSQLBlockTypeItem(final com.cdoframework.cdolib.database.xsd.SQLBlockTypeItem vSQLBlockTypeItem) throws java.lang.IndexOutOfBoundsException {
        this._items.add(vSQLBlockTypeItem);
    }

    /**
     * 
     * 
     * @param index
     * @param vSQLBlockTypeItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addSQLBlockTypeItem(final int index,final com.cdoframework.cdolib.database.xsd.SQLBlockTypeItem vSQLBlockTypeItem) throws java.lang.IndexOutOfBoundsException {
        this._items.add(index, vSQLBlockTypeItem);
    }

    /**
     * Method enumerateSQLBlockTypeItem.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.database.xsd.SQLBlockTypeItem> enumerateSQLBlockTypeItem() {
        return java.util.Collections.enumeration(this._items);
    }

    /**
     * Method getSQLBlockTypeItem.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.database.xsd.SQLBlockTypeItem at the
     * given index
     */
    public com.cdoframework.cdolib.database.xsd.SQLBlockTypeItem getSQLBlockTypeItem(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("getSQLBlockTypeItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        return _items.get(index);
    }

    /**
     * Method getSQLBlockTypeItem.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.database.xsd.SQLBlockTypeItem[] getSQLBlockTypeItem() {
        com.cdoframework.cdolib.database.xsd.SQLBlockTypeItem[] array = new com.cdoframework.cdolib.database.xsd.SQLBlockTypeItem[0];
        return this._items.toArray(array);
    }

    /**
     * Method getSQLBlockTypeItemCount.
     * 
     * @return the size of this collection
     */
    public int getSQLBlockTypeItemCount() {
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
     * Method iterateSQLBlockTypeItem.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.database.xsd.SQLBlockTypeItem> iterateSQLBlockTypeItem() {
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
    public void removeAllSQLBlockTypeItem() {
        this._items.clear();
    }

    /**
     * Method removeSQLBlockTypeItem.
     * 
     * @param vSQLBlockTypeItem
     * @return true if the object was removed from the collection.
     */
    public boolean removeSQLBlockTypeItem(final com.cdoframework.cdolib.database.xsd.SQLBlockTypeItem vSQLBlockTypeItem) {
        boolean removed = _items.remove(vSQLBlockTypeItem);
        return removed;
    }

    /**
     * Method removeSQLBlockTypeItemAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.database.xsd.SQLBlockTypeItem removeSQLBlockTypeItemAt(final int index) {
        java.lang.Object obj = this._items.remove(index);
        return (com.cdoframework.cdolib.database.xsd.SQLBlockTypeItem) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vSQLBlockTypeItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setSQLBlockTypeItem(final int index,final com.cdoframework.cdolib.database.xsd.SQLBlockTypeItem vSQLBlockTypeItem) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("setSQLBlockTypeItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        this._items.set(index, vSQLBlockTypeItem);
    }

    /**
     * 
     * 
     * @param vSQLBlockTypeItemArray
     */
    public void setSQLBlockTypeItem(final com.cdoframework.cdolib.database.xsd.SQLBlockTypeItem[] vSQLBlockTypeItemArray) {
        //-- copy array
        _items.clear();

        for (int i = 0; i < vSQLBlockTypeItemArray.length; i++) {
                this._items.add(vSQLBlockTypeItemArray[i]);
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
     * com.cdoframework.cdolib.database.xsd.SQLBlockType
     */
    public static com.cdoframework.cdolib.database.xsd.SQLBlockType unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.database.xsd.SQLBlockType) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.database.xsd.SQLBlockType.class, reader);
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
