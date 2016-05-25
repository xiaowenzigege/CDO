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
public class QueryRecord extends com.cdoframework.cdolib.database.xsd.CollectionNameType 
implements java.io.Serializable
{

    /**
     * Field outputId.
     */
    private java.lang.String outputId;

    /**
     * Field recordCountId.
     */
    private java.lang.String recordCountId;

    private java.util.List<com.cdoframework.cdolib.database.xsd.QField> QFieldList;

    /**
     * Field creterias.
     */
    private com.cdoframework.cdolib.database.xsd.Creterias creterias;

    private java.util.List<com.cdoframework.cdolib.database.xsd.Order> orderList;

    private java.util.List<com.cdoframework.cdolib.database.xsd.Group> groupList;

    public QueryRecord() {
        super();
        this.QFieldList = new java.util.ArrayList<com.cdoframework.cdolib.database.xsd.QField>();
        this.orderList = new java.util.ArrayList<com.cdoframework.cdolib.database.xsd.Order>();
        this.groupList = new java.util.ArrayList<com.cdoframework.cdolib.database.xsd.Group>();
    }

    /**
     * 
     * 
     * @param vGroup
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addGroup(final com.cdoframework.cdolib.database.xsd.Group vGroup) throws java.lang.IndexOutOfBoundsException {
        this.groupList.add(vGroup);
    }

    /**
     * 
     * 
     * @param index
     * @param vGroup
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addGroup(final int index,final com.cdoframework.cdolib.database.xsd.Group vGroup) throws java.lang.IndexOutOfBoundsException {
        this.groupList.add(index, vGroup);
    }

    /**
     * 
     * 
     * @param vOrder
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addOrder(final com.cdoframework.cdolib.database.xsd.Order vOrder) throws java.lang.IndexOutOfBoundsException {
        this.orderList.add(vOrder);
    }

    /**
     * 
     * 
     * @param index
     * @param vOrder
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addOrder(final int index,final com.cdoframework.cdolib.database.xsd.Order vOrder) throws java.lang.IndexOutOfBoundsException {
        this.orderList.add(index, vOrder);
    }

    /**
     * 
     * 
     * @param vQField
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addQField(final com.cdoframework.cdolib.database.xsd.QField vQField) throws java.lang.IndexOutOfBoundsException {
        this.QFieldList.add(vQField);
    }

    /**
     * 
     * 
     * @param index
     * @param vQField
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addQField(final int index,final com.cdoframework.cdolib.database.xsd.QField vQField) throws java.lang.IndexOutOfBoundsException {
        this.QFieldList.add(index, vQField);
    }

    /**
     * Method enumerateGroup.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.database.xsd.Group> enumerateGroup() {
        return java.util.Collections.enumeration(this.groupList);
    }

    /**
     * Method enumerateOrder.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.database.xsd.Order> enumerateOrder() {
        return java.util.Collections.enumeration(this.orderList);
    }

    /**
     * Method enumerateQField.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.database.xsd.QField> enumerateQField() {
        return java.util.Collections.enumeration(this.QFieldList);
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
     * Method getGroup.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.database.xsd.Group at the given index
     */
    public com.cdoframework.cdolib.database.xsd.Group getGroup(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.groupList.size()) {
            throw new IndexOutOfBoundsException("getGroup: Index value '" + index + "' not in range [0.." + (this.groupList.size() - 1) + "]");
        }

        return groupList.get(index);
    }

    /**
     * Method getGroup.Returns the contents of the collection in an
     * Array.  <p>Note:  Just in case the collection contents are
     * changing in another thread, we pass a 0-length Array of the
     * correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.database.xsd.Group[] getGroup() {
        com.cdoframework.cdolib.database.xsd.Group[] array = new com.cdoframework.cdolib.database.xsd.Group[0];
        return this.groupList.toArray(array);
    }

    /**
     * Method getGroupCount.
     * 
     * @return the size of this collection
     */
    public int getGroupCount() {
        return this.groupList.size();
    }

    /**
     * Method getOrder.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.database.xsd.Order at the given index
     */
    public com.cdoframework.cdolib.database.xsd.Order getOrder(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.orderList.size()) {
            throw new IndexOutOfBoundsException("getOrder: Index value '" + index + "' not in range [0.." + (this.orderList.size() - 1) + "]");
        }

        return orderList.get(index);
    }

    /**
     * Method getOrder.Returns the contents of the collection in an
     * Array.  <p>Note:  Just in case the collection contents are
     * changing in another thread, we pass a 0-length Array of the
     * correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.database.xsd.Order[] getOrder() {
        com.cdoframework.cdolib.database.xsd.Order[] array = new com.cdoframework.cdolib.database.xsd.Order[0];
        return this.orderList.toArray(array);
    }

    /**
     * Method getOrderCount.
     * 
     * @return the size of this collection
     */
    public int getOrderCount() {
        return this.orderList.size();
    }

    /**
     * Returns the value of field 'outputId'.
     * 
     * @return the value of field 'OutputId'.
     */
    public java.lang.String getOutputId() {
        return this.outputId;
    }

    /**
     * Method getQField.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.database.xsd.QField at the given inde
     */
    public com.cdoframework.cdolib.database.xsd.QField getQField(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.QFieldList.size()) {
            throw new IndexOutOfBoundsException("getQField: Index value '" + index + "' not in range [0.." + (this.QFieldList.size() - 1) + "]");
        }

        return QFieldList.get(index);
    }

    /**
     * Method getQField.Returns the contents of the collection in
     * an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.database.xsd.QField[] getQField() {
        com.cdoframework.cdolib.database.xsd.QField[] array = new com.cdoframework.cdolib.database.xsd.QField[0];
        return this.QFieldList.toArray(array);
    }

    /**
     * Method getQFieldCount.
     * 
     * @return the size of this collection
     */
    public int getQFieldCount() {
        return this.QFieldList.size();
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
     * Method iterateGroup.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.database.xsd.Group> iterateGroup() {
        return this.groupList.iterator();
    }

    /**
     * Method iterateOrder.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.database.xsd.Order> iterateOrder() {
        return this.orderList.iterator();
    }

    /**
     * Method iterateQField.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.database.xsd.QField> iterateQField() {
        return this.QFieldList.iterator();
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
    public void removeAllGroup() {
        this.groupList.clear();
    }

    /**
     */
    public void removeAllOrder() {
        this.orderList.clear();
    }

    /**
     */
    public void removeAllQField() {
        this.QFieldList.clear();
    }

    /**
     * Method removeGroup.
     * 
     * @param vGroup
     * @return true if the object was removed from the collection.
     */
    public boolean removeGroup(final com.cdoframework.cdolib.database.xsd.Group vGroup) {
        boolean removed = groupList.remove(vGroup);
        return removed;
    }

    /**
     * Method removeGroupAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.database.xsd.Group removeGroupAt(final int index) {
        java.lang.Object obj = this.groupList.remove(index);
        return (com.cdoframework.cdolib.database.xsd.Group) obj;
    }

    /**
     * Method removeOrder.
     * 
     * @param vOrder
     * @return true if the object was removed from the collection.
     */
    public boolean removeOrder(final com.cdoframework.cdolib.database.xsd.Order vOrder) {
        boolean removed = orderList.remove(vOrder);
        return removed;
    }

    /**
     * Method removeOrderAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.database.xsd.Order removeOrderAt(final int index) {
        java.lang.Object obj = this.orderList.remove(index);
        return (com.cdoframework.cdolib.database.xsd.Order) obj;
    }

    /**
     * Method removeQField.
     * 
     * @param vQField
     * @return true if the object was removed from the collection.
     */
    public boolean removeQField(final com.cdoframework.cdolib.database.xsd.QField vQField) {
        boolean removed = QFieldList.remove(vQField);
        return removed;
    }

    /**
     * Method removeQFieldAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.database.xsd.QField removeQFieldAt(final int index) {
        java.lang.Object obj = this.QFieldList.remove(index);
        return (com.cdoframework.cdolib.database.xsd.QField) obj;
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
     * 
     * 
     * @param index
     * @param vGroup
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setGroup(final int index,final com.cdoframework.cdolib.database.xsd.Group vGroup) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.groupList.size()) {
            throw new IndexOutOfBoundsException("setGroup: Index value '" + index + "' not in range [0.." + (this.groupList.size() - 1) + "]");
        }

        this.groupList.set(index, vGroup);
    }

    /**
     * 
     * 
     * @param vGroupArray
     */
    public void setGroup(final com.cdoframework.cdolib.database.xsd.Group[] vGroupArray) {
        //-- copy array
        groupList.clear();

        for (int i = 0; i < vGroupArray.length; i++) {
                this.groupList.add(vGroupArray[i]);
        }
    }

    /**
     * 
     * 
     * @param index
     * @param vOrder
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setOrder(final int index,final com.cdoframework.cdolib.database.xsd.Order vOrder) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.orderList.size()) {
            throw new IndexOutOfBoundsException("setOrder: Index value '" + index + "' not in range [0.." + (this.orderList.size() - 1) + "]");
        }

        this.orderList.set(index, vOrder);
    }

    /**
     * 
     * 
     * @param vOrderArray
     */
    public void setOrder(final com.cdoframework.cdolib.database.xsd.Order[] vOrderArray) {
        //-- copy array
        orderList.clear();

        for (int i = 0; i < vOrderArray.length; i++) {
                this.orderList.add(vOrderArray[i]);
        }
    }

    /**
     * Sets the value of field 'outputId'.
     * 
     * @param outputId the value of field 'outputId'.
     */
    public void setOutputId(final java.lang.String outputId) {
        this.outputId = outputId;
    }

    /**
     * 
     * 
     * @param index
     * @param vQField
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setQField(final int index,final com.cdoframework.cdolib.database.xsd.QField vQField) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.QFieldList.size()) {
            throw new IndexOutOfBoundsException("setQField: Index value '" + index + "' not in range [0.." + (this.QFieldList.size() - 1) + "]");
        }

        this.QFieldList.set(index, vQField);
    }

    /**
     * 
     * 
     * @param vQFieldArray
     */
    public void setQField(final com.cdoframework.cdolib.database.xsd.QField[] vQFieldArray) {
        //-- copy array
        QFieldList.clear();

        for (int i = 0; i < vQFieldArray.length; i++) {
                this.QFieldList.add(vQFieldArray[i]);
        }
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
     * com.cdoframework.cdolib.database.xsd.QueryRecord
     */
    public static com.cdoframework.cdolib.database.xsd.QueryRecord unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.database.xsd.QueryRecord) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.database.xsd.QueryRecord.class, reader);
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
