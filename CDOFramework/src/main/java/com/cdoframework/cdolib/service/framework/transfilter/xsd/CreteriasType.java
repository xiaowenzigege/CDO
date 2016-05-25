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
public class CreteriasType implements java.io.Serializable {

    /**
     * Field type.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.types.CreteriasTypeItemTypeType type = com.cdoframework.cdolib.service.framework.transfilter.xsd.types.CreteriasTypeItemTypeType.fromValue("AND");

    private java.util.List<com.cdoframework.cdolib.service.framework.transfilter.xsd.CreteriasTypeItem> _items;

    public CreteriasType() {
        super();
        setType(com.cdoframework.cdolib.service.framework.transfilter.xsd.types.CreteriasTypeItemTypeType.fromValue("AND"));
        this._items = new java.util.ArrayList<com.cdoframework.cdolib.service.framework.transfilter.xsd.CreteriasTypeItem>();
    }

    /**
     * 
     * 
     * @param vCreteriasTypeItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addCreteriasTypeItem(final com.cdoframework.cdolib.service.framework.transfilter.xsd.CreteriasTypeItem vCreteriasTypeItem) throws java.lang.IndexOutOfBoundsException {
        this._items.add(vCreteriasTypeItem);
    }

    /**
     * 
     * 
     * @param index
     * @param vCreteriasTypeItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addCreteriasTypeItem(final int index,final com.cdoframework.cdolib.service.framework.transfilter.xsd.CreteriasTypeItem vCreteriasTypeItem) throws java.lang.IndexOutOfBoundsException {
        this._items.add(index, vCreteriasTypeItem);
    }

    /**
     * Method enumerateCreteriasTypeItem.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.service.framework.transfilter.xsd.CreteriasTypeItem> enumerateCreteriasTypeItem() {
        return java.util.Collections.enumeration(this._items);
    }

    /**
     * Method getCreteriasTypeItem.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.service.framework.transfilter.xsd.CreteriasTypeItem
     * at the given index
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.CreteriasTypeItem getCreteriasTypeItem(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("getCreteriasTypeItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        return _items.get(index);
    }

    /**
     * Method getCreteriasTypeItem.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.CreteriasTypeItem[] getCreteriasTypeItem() {
        com.cdoframework.cdolib.service.framework.transfilter.xsd.CreteriasTypeItem[] array = new com.cdoframework.cdolib.service.framework.transfilter.xsd.CreteriasTypeItem[0];
        return this._items.toArray(array);
    }

    /**
     * Method getCreteriasTypeItemCount.
     * 
     * @return the size of this collection
     */
    public int getCreteriasTypeItemCount() {
        return this._items.size();
    }

    /**
     * Returns the value of field 'type'.
     * 
     * @return the value of field 'Type'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.types.CreteriasTypeItemTypeType getType() {
        return this.type;
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
     * Method iterateCreteriasTypeItem.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.service.framework.transfilter.xsd.CreteriasTypeItem> iterateCreteriasTypeItem() {
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
    public void removeAllCreteriasTypeItem() {
        this._items.clear();
    }

    /**
     * Method removeCreteriasTypeItem.
     * 
     * @param vCreteriasTypeItem
     * @return true if the object was removed from the collection.
     */
    public boolean removeCreteriasTypeItem(final com.cdoframework.cdolib.service.framework.transfilter.xsd.CreteriasTypeItem vCreteriasTypeItem) {
        boolean removed = _items.remove(vCreteriasTypeItem);
        return removed;
    }

    /**
     * Method removeCreteriasTypeItemAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.CreteriasTypeItem removeCreteriasTypeItemAt(final int index) {
        java.lang.Object obj = this._items.remove(index);
        return (com.cdoframework.cdolib.service.framework.transfilter.xsd.CreteriasTypeItem) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vCreteriasTypeItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setCreteriasTypeItem(final int index,final com.cdoframework.cdolib.service.framework.transfilter.xsd.CreteriasTypeItem vCreteriasTypeItem) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("setCreteriasTypeItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        this._items.set(index, vCreteriasTypeItem);
    }

    /**
     * 
     * 
     * @param vCreteriasTypeItemArray
     */
    public void setCreteriasTypeItem(final com.cdoframework.cdolib.service.framework.transfilter.xsd.CreteriasTypeItem[] vCreteriasTypeItemArray) {
        //-- copy array
        _items.clear();

        for (int i = 0; i < vCreteriasTypeItemArray.length; i++) {
                this._items.add(vCreteriasTypeItemArray[i]);
        }
    }

    /**
     * Sets the value of field 'type'.
     * 
     * @param type the value of field 'type'.
     */
    public void setType(final com.cdoframework.cdolib.service.framework.transfilter.xsd.types.CreteriasTypeItemTypeType type) {
        this.type = type;
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
     * com.cdoframework.cdolib.service.framework.transfilter.xsd.CreteriasType
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.xsd.CreteriasType unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.service.framework.transfilter.xsd.CreteriasType) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.xsd.CreteriasType.class, reader);
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
