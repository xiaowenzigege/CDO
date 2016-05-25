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
public class BigTableGroup implements java.io.Serializable {

    /**
     * Field id.
     */
    private java.lang.String id = "";

    private java.util.List<com.cdoframework.cdolib.database.xsd.BigTable> bigTableList;

    public BigTableGroup() {
        super();
        setId("");
        this.bigTableList = new java.util.ArrayList<com.cdoframework.cdolib.database.xsd.BigTable>();
    }

    /**
     * 
     * 
     * @param vBigTable
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addBigTable(final com.cdoframework.cdolib.database.xsd.BigTable vBigTable) throws java.lang.IndexOutOfBoundsException {
        this.bigTableList.add(vBigTable);
    }

    /**
     * 
     * 
     * @param index
     * @param vBigTable
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addBigTable(final int index,final com.cdoframework.cdolib.database.xsd.BigTable vBigTable) throws java.lang.IndexOutOfBoundsException {
        this.bigTableList.add(index, vBigTable);
    }

    /**
     * Method enumerateBigTable.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.database.xsd.BigTable> enumerateBigTable() {
        return java.util.Collections.enumeration(this.bigTableList);
    }

    /**
     * Method getBigTable.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.database.xsd.BigTable at the given
     * index
     */
    public com.cdoframework.cdolib.database.xsd.BigTable getBigTable(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.bigTableList.size()) {
            throw new IndexOutOfBoundsException("getBigTable: Index value '" + index + "' not in range [0.." + (this.bigTableList.size() - 1) + "]");
        }

        return bigTableList.get(index);
    }

    /**
     * Method getBigTable.Returns the contents of the collection in
     * an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.database.xsd.BigTable[] getBigTable() {
        com.cdoframework.cdolib.database.xsd.BigTable[] array = new com.cdoframework.cdolib.database.xsd.BigTable[0];
        return this.bigTableList.toArray(array);
    }

    /**
     * Method getBigTableCount.
     * 
     * @return the size of this collection
     */
    public int getBigTableCount() {
        return this.bigTableList.size();
    }

    /**
     * Returns the value of field 'id'.
     * 
     * @return the value of field 'Id'.
     */
    public java.lang.String getId() {
        return this.id;
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
     * Method iterateBigTable.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.database.xsd.BigTable> iterateBigTable() {
        return this.bigTableList.iterator();
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
    public void removeAllBigTable() {
        this.bigTableList.clear();
    }

    /**
     * Method removeBigTable.
     * 
     * @param vBigTable
     * @return true if the object was removed from the collection.
     */
    public boolean removeBigTable(final com.cdoframework.cdolib.database.xsd.BigTable vBigTable) {
        boolean removed = bigTableList.remove(vBigTable);
        return removed;
    }

    /**
     * Method removeBigTableAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.database.xsd.BigTable removeBigTableAt(final int index) {
        java.lang.Object obj = this.bigTableList.remove(index);
        return (com.cdoframework.cdolib.database.xsd.BigTable) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vBigTable
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setBigTable(final int index,final com.cdoframework.cdolib.database.xsd.BigTable vBigTable) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.bigTableList.size()) {
            throw new IndexOutOfBoundsException("setBigTable: Index value '" + index + "' not in range [0.." + (this.bigTableList.size() - 1) + "]");
        }

        this.bigTableList.set(index, vBigTable);
    }

    /**
     * 
     * 
     * @param vBigTableArray
     */
    public void setBigTable(final com.cdoframework.cdolib.database.xsd.BigTable[] vBigTableArray) {
        //-- copy array
        bigTableList.clear();

        for (int i = 0; i < vBigTableArray.length; i++) {
                this.bigTableList.add(vBigTableArray[i]);
        }
    }

    /**
     * Sets the value of field 'id'.
     * 
     * @param id the value of field 'id'.
     */
    public void setId(final java.lang.String id) {
        this.id = id;
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
     * com.cdoframework.cdolib.database.xsd.BigTableGroup
     */
    public static com.cdoframework.cdolib.database.xsd.BigTableGroup unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.database.xsd.BigTableGroup) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.database.xsd.BigTableGroup.class, reader);
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
