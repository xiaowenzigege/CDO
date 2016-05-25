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
public class Modify extends com.cdoframework.cdolib.database.xsd.CollectionNameType 
implements java.io.Serializable
{

    /**
     * Field recordCountId.
     */
    private java.lang.String recordCountId;

    /**
     * Field multiLine.
     */
    private boolean multiLine = true;

    /**
     * Keeps track of whether primitive field multiLine has been
     * set already.
     */
    private boolean _hasmultiLine;

    /**
     * Field insert.
     */
    private boolean insert = false;

    /**
     * Keeps track of whether primitive field insert has been set
     * already.
     */
    private boolean _hasinsert;

    private java.util.List<com.cdoframework.cdolib.database.xsd.ModifyField> modifyFieldList;

    /**
     * Field creterias.
     */
    private com.cdoframework.cdolib.database.xsd.Creterias creterias;

    public Modify() {
        super();
        this.modifyFieldList = new java.util.ArrayList<com.cdoframework.cdolib.database.xsd.ModifyField>();
    }

    /**
     * 
     * 
     * @param vModifyField
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addModifyField(final com.cdoframework.cdolib.database.xsd.ModifyField vModifyField) throws java.lang.IndexOutOfBoundsException {
        this.modifyFieldList.add(vModifyField);
    }

    /**
     * 
     * 
     * @param index
     * @param vModifyField
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addModifyField(final int index,final com.cdoframework.cdolib.database.xsd.ModifyField vModifyField) throws java.lang.IndexOutOfBoundsException {
        this.modifyFieldList.add(index, vModifyField);
    }

    /**
     */
    public void deleteInsert() {
        this._hasinsert= false;
    }

    /**
     */
    public void deleteMultiLine() {
        this._hasmultiLine= false;
    }

    /**
     * Method enumerateModifyField.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.database.xsd.ModifyField> enumerateModifyField() {
        return java.util.Collections.enumeration(this.modifyFieldList);
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
     * Returns the value of field 'insert'.
     * 
     * @return the value of field 'Insert'.
     */
    public boolean getInsert() {
        return this.insert;
    }

    /**
     * Method getModifyField.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.database.xsd.ModifyField at the
     * given index
     */
    public com.cdoframework.cdolib.database.xsd.ModifyField getModifyField(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.modifyFieldList.size()) {
            throw new IndexOutOfBoundsException("getModifyField: Index value '" + index + "' not in range [0.." + (this.modifyFieldList.size() - 1) + "]");
        }

        return modifyFieldList.get(index);
    }

    /**
     * Method getModifyField.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.database.xsd.ModifyField[] getModifyField() {
        com.cdoframework.cdolib.database.xsd.ModifyField[] array = new com.cdoframework.cdolib.database.xsd.ModifyField[0];
        return this.modifyFieldList.toArray(array);
    }

    /**
     * Method getModifyFieldCount.
     * 
     * @return the size of this collection
     */
    public int getModifyFieldCount() {
        return this.modifyFieldList.size();
    }

    /**
     * Returns the value of field 'multiLine'.
     * 
     * @return the value of field 'MultiLine'.
     */
    public boolean getMultiLine() {
        return this.multiLine;
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
     * Method hasInsert.
     * 
     * @return true if at least one Insert has been added
     */
    public boolean hasInsert() {
        return this._hasinsert;
    }

    /**
     * Method hasMultiLine.
     * 
     * @return true if at least one MultiLine has been added
     */
    public boolean hasMultiLine() {
        return this._hasmultiLine;
    }

    /**
     * Returns the value of field 'insert'.
     * 
     * @return the value of field 'Insert'.
     */
    public boolean isInsert() {
        return this.insert;
    }

    /**
     * Returns the value of field 'multiLine'.
     * 
     * @return the value of field 'MultiLine'.
     */
    public boolean isMultiLine() {
        return this.multiLine;
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
     * Method iterateModifyField.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.database.xsd.ModifyField> iterateModifyField() {
        return this.modifyFieldList.iterator();
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
    public void removeAllModifyField() {
        this.modifyFieldList.clear();
    }

    /**
     * Method removeModifyField.
     * 
     * @param vModifyField
     * @return true if the object was removed from the collection.
     */
    public boolean removeModifyField(final com.cdoframework.cdolib.database.xsd.ModifyField vModifyField) {
        boolean removed = modifyFieldList.remove(vModifyField);
        return removed;
    }

    /**
     * Method removeModifyFieldAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.database.xsd.ModifyField removeModifyFieldAt(final int index) {
        java.lang.Object obj = this.modifyFieldList.remove(index);
        return (com.cdoframework.cdolib.database.xsd.ModifyField) obj;
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
     * Sets the value of field 'insert'.
     * 
     * @param insert the value of field 'insert'.
     */
    public void setInsert(final boolean insert) {
        this.insert = insert;
        this._hasinsert = true;
    }

    /**
     * 
     * 
     * @param index
     * @param vModifyField
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setModifyField(final int index,final com.cdoframework.cdolib.database.xsd.ModifyField vModifyField) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.modifyFieldList.size()) {
            throw new IndexOutOfBoundsException("setModifyField: Index value '" + index + "' not in range [0.." + (this.modifyFieldList.size() - 1) + "]");
        }

        this.modifyFieldList.set(index, vModifyField);
    }

    /**
     * 
     * 
     * @param vModifyFieldArray
     */
    public void setModifyField(final com.cdoframework.cdolib.database.xsd.ModifyField[] vModifyFieldArray) {
        //-- copy array
        modifyFieldList.clear();

        for (int i = 0; i < vModifyFieldArray.length; i++) {
                this.modifyFieldList.add(vModifyFieldArray[i]);
        }
    }

    /**
     * Sets the value of field 'multiLine'.
     * 
     * @param multiLine the value of field 'multiLine'.
     */
    public void setMultiLine(final boolean multiLine) {
        this.multiLine = multiLine;
        this._hasmultiLine = true;
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
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled
     * com.cdoframework.cdolib.database.xsd.Modify
     */
    public static com.cdoframework.cdolib.database.xsd.Modify unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.database.xsd.Modify) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.database.xsd.Modify.class, reader);
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
