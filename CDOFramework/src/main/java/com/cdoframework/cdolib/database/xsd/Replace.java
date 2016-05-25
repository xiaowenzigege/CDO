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
public class Replace extends com.cdoframework.cdolib.database.xsd.CollectionNameType 
implements java.io.Serializable
{

    /**
     * Field recordCountId.
     */
    private java.lang.String recordCountId;

    private java.util.List<com.cdoframework.cdolib.database.xsd.ReplaceField> replaceFieldList;

    /**
     * Field creterias.
     */
    private com.cdoframework.cdolib.database.xsd.Creterias creterias;

    public Replace() {
        super();
        this.replaceFieldList = new java.util.ArrayList<com.cdoframework.cdolib.database.xsd.ReplaceField>();
    }

    /**
     * 
     * 
     * @param vReplaceField
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addReplaceField(final com.cdoframework.cdolib.database.xsd.ReplaceField vReplaceField) throws java.lang.IndexOutOfBoundsException {
        this.replaceFieldList.add(vReplaceField);
    }

    /**
     * 
     * 
     * @param index
     * @param vReplaceField
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addReplaceField(final int index,final com.cdoframework.cdolib.database.xsd.ReplaceField vReplaceField) throws java.lang.IndexOutOfBoundsException {
        this.replaceFieldList.add(index, vReplaceField);
    }

    /**
     * Method enumerateReplaceField.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.database.xsd.ReplaceField> enumerateReplaceField() {
        return java.util.Collections.enumeration(this.replaceFieldList);
    }

    /**
     * Returns the value of field 'creterias'.
     * 
     * @return the value of field 'Creterias'.
     */
    public com.cdoframework.cdolib.database.xsd.Creterias getCreterias() {
        return this.creterias;
    }

    /**
     * Returns the value of field 'recordCountId'.
     * 
     * @return the value of field 'RecordCountId'.
     */
    public java.lang.String getRecordCountId() {
        return this.recordCountId;
    }

    /**
     * Method getReplaceField.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.database.xsd.ReplaceField at the
     * given index
     */
    public com.cdoframework.cdolib.database.xsd.ReplaceField getReplaceField(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.replaceFieldList.size()) {
            throw new IndexOutOfBoundsException("getReplaceField: Index value '" + index + "' not in range [0.." + (this.replaceFieldList.size() - 1) + "]");
        }

        return replaceFieldList.get(index);
    }

    /**
     * Method getReplaceField.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.database.xsd.ReplaceField[] getReplaceField() {
        com.cdoframework.cdolib.database.xsd.ReplaceField[] array = new com.cdoframework.cdolib.database.xsd.ReplaceField[0];
        return this.replaceFieldList.toArray(array);
    }

    /**
     * Method getReplaceFieldCount.
     * 
     * @return the size of this collection
     */
    public int getReplaceFieldCount() {
        return this.replaceFieldList.size();
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
     * Method iterateReplaceField.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.database.xsd.ReplaceField> iterateReplaceField() {
        return this.replaceFieldList.iterator();
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
    public void removeAllReplaceField() {
        this.replaceFieldList.clear();
    }

    /**
     * Method removeReplaceField.
     * 
     * @param vReplaceField
     * @return true if the object was removed from the collection.
     */
    public boolean removeReplaceField(final com.cdoframework.cdolib.database.xsd.ReplaceField vReplaceField) {
        boolean removed = replaceFieldList.remove(vReplaceField);
        return removed;
    }

    /**
     * Method removeReplaceFieldAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.database.xsd.ReplaceField removeReplaceFieldAt(final int index) {
        java.lang.Object obj = this.replaceFieldList.remove(index);
        return (com.cdoframework.cdolib.database.xsd.ReplaceField) obj;
    }

    /**
     * Sets the value of field 'creterias'.
     * 
     * @param creterias the value of field 'creterias'.
     */
    public void setCreterias(final com.cdoframework.cdolib.database.xsd.Creterias creterias) {
        this.creterias = creterias;
    }

    /**
     * Sets the value of field 'recordCountId'.
     * 
     * @param recordCountId the value of field 'recordCountId'.
     */
    public void setRecordCountId(final java.lang.String recordCountId) {
        this.recordCountId = recordCountId;
    }

    /**
     * 
     * 
     * @param index
     * @param vReplaceField
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setReplaceField(final int index,final com.cdoframework.cdolib.database.xsd.ReplaceField vReplaceField) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.replaceFieldList.size()) {
            throw new IndexOutOfBoundsException("setReplaceField: Index value '" + index + "' not in range [0.." + (this.replaceFieldList.size() - 1) + "]");
        }

        this.replaceFieldList.set(index, vReplaceField);
    }

    /**
     * 
     * 
     * @param vReplaceFieldArray
     */
    public void setReplaceField(final com.cdoframework.cdolib.database.xsd.ReplaceField[] vReplaceFieldArray) {
        //-- copy array
        replaceFieldList.clear();

        for (int i = 0; i < vReplaceFieldArray.length; i++) {
                this.replaceFieldList.add(vReplaceFieldArray[i]);
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
     * com.cdoframework.cdolib.database.xsd.Replace
     */
    public static com.cdoframework.cdolib.database.xsd.Replace unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.database.xsd.Replace) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.database.xsd.Replace.class, reader);
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
