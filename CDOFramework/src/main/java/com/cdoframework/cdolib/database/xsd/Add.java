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
public class Add extends com.cdoframework.cdolib.database.xsd.CollectionNameType 
implements java.io.Serializable
{

    private java.util.List<com.cdoframework.cdolib.database.xsd.AddField> addFieldList;

    public Add() {
        super();
        this.addFieldList = new java.util.ArrayList<com.cdoframework.cdolib.database.xsd.AddField>();
    }

    /**
     * 
     * 
     * @param vAddField
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAddField(final com.cdoframework.cdolib.database.xsd.AddField vAddField) throws java.lang.IndexOutOfBoundsException {
        this.addFieldList.add(vAddField);
    }

    /**
     * 
     * 
     * @param index
     * @param vAddField
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAddField(final int index,final com.cdoframework.cdolib.database.xsd.AddField vAddField) throws java.lang.IndexOutOfBoundsException {
        this.addFieldList.add(index, vAddField);
    }

    /**
     * Method enumerateAddField.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.database.xsd.AddField> enumerateAddField() {
        return java.util.Collections.enumeration(this.addFieldList);
    }

    /**
     * Method getAddField.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.database.xsd.AddField at the given
     * index
     */
    public com.cdoframework.cdolib.database.xsd.AddField getAddField(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.addFieldList.size()) {
            throw new IndexOutOfBoundsException("getAddField: Index value '" + index + "' not in range [0.." + (this.addFieldList.size() - 1) + "]");
        }

        return addFieldList.get(index);
    }

    /**
     * Method getAddField.Returns the contents of the collection in
     * an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.database.xsd.AddField[] getAddField() {
        com.cdoframework.cdolib.database.xsd.AddField[] array = new com.cdoframework.cdolib.database.xsd.AddField[0];
        return this.addFieldList.toArray(array);
    }

    /**
     * Method getAddFieldCount.
     * 
     * @return the size of this collection
     */
    public int getAddFieldCount() {
        return this.addFieldList.size();
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
     * Method iterateAddField.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.database.xsd.AddField> iterateAddField() {
        return this.addFieldList.iterator();
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
     * Method removeAddField.
     * 
     * @param vAddField
     * @return true if the object was removed from the collection.
     */
    public boolean removeAddField(final com.cdoframework.cdolib.database.xsd.AddField vAddField) {
        boolean removed = addFieldList.remove(vAddField);
        return removed;
    }

    /**
     * Method removeAddFieldAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.database.xsd.AddField removeAddFieldAt(final int index) {
        java.lang.Object obj = this.addFieldList.remove(index);
        return (com.cdoframework.cdolib.database.xsd.AddField) obj;
    }

    /**
     */
    public void removeAllAddField() {
        this.addFieldList.clear();
    }

    /**
     * 
     * 
     * @param index
     * @param vAddField
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setAddField(final int index,final com.cdoframework.cdolib.database.xsd.AddField vAddField) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.addFieldList.size()) {
            throw new IndexOutOfBoundsException("setAddField: Index value '" + index + "' not in range [0.." + (this.addFieldList.size() - 1) + "]");
        }

        this.addFieldList.set(index, vAddField);
    }

    /**
     * 
     * 
     * @param vAddFieldArray
     */
    public void setAddField(final com.cdoframework.cdolib.database.xsd.AddField[] vAddFieldArray) {
        //-- copy array
        addFieldList.clear();

        for (int i = 0; i < vAddFieldArray.length; i++) {
                this.addFieldList.add(vAddFieldArray[i]);
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
     * com.cdoframework.cdolib.database.xsd.Add
     */
    public static com.cdoframework.cdolib.database.xsd.Add unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.database.xsd.Add) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.database.xsd.Add.class, reader);
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
