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
public class Creteria extends com.cdoframework.cdolib.database.xsd.CriteriaType 
implements java.io.Serializable
{

    /**
     * Field name.
     */
    private java.lang.String name;

    /**
     * Field valueRequired.
     */
    private boolean valueRequired = false;

    /**
     * Keeps track of whether primitive field valueRequired has
     * been set already.
     */
    private boolean _hasvalueRequired;

    private java.util.List<com.cdoframework.cdolib.database.xsd.And> andList;

    public Creteria() {
        super();
        this.andList = new java.util.ArrayList<com.cdoframework.cdolib.database.xsd.And>();
    }

    /**
     * 
     * 
     * @param vAnd
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAnd(final com.cdoframework.cdolib.database.xsd.And vAnd) throws java.lang.IndexOutOfBoundsException {
        this.andList.add(vAnd);
    }

    /**
     * 
     * 
     * @param index
     * @param vAnd
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAnd(final int index,final com.cdoframework.cdolib.database.xsd.And vAnd) throws java.lang.IndexOutOfBoundsException {
        this.andList.add(index, vAnd);
    }

    /**
     */
    public void deleteValueRequired() {
        this._hasvalueRequired= false;
    }

    /**
     * Method enumerateAnd.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.database.xsd.And> enumerateAnd() {
        return java.util.Collections.enumeration(this.andList);
    }

    /**
     * Method getAnd.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.database.xsd.And at the given index
     */
    public com.cdoframework.cdolib.database.xsd.And getAnd(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.andList.size()) {
            throw new IndexOutOfBoundsException("getAnd: Index value '" + index + "' not in range [0.." + (this.andList.size() - 1) + "]");
        }

        return andList.get(index);
    }

    /**
     * Method getAnd.Returns the contents of the collection in an
     * Array.  <p>Note:  Just in case the collection contents are
     * changing in another thread, we pass a 0-length Array of the
     * correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.database.xsd.And[] getAnd() {
        com.cdoframework.cdolib.database.xsd.And[] array = new com.cdoframework.cdolib.database.xsd.And[0];
        return this.andList.toArray(array);
    }

    /**
     * Method getAndCount.
     * 
     * @return the size of this collection
     */
    public int getAndCount() {
        return this.andList.size();
    }

    /**
     * Returns the value of field 'name'.
     * 
     * @return the value of field 'Name'.
     */
    public java.lang.String getName() {
        return this.name;
    }

    /**
     * Returns the value of field 'valueRequired'.
     * 
     * @return the value of field 'ValueRequired'.
     */
    public boolean getValueRequired() {
        return this.valueRequired;
    }

    /**
     * Method hasValueRequired.
     * 
     * @return true if at least one ValueRequired has been added
     */
    public boolean hasValueRequired() {
        return this._hasvalueRequired;
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
     * Returns the value of field 'valueRequired'.
     * 
     * @return the value of field 'ValueRequired'.
     */
    public boolean isValueRequired() {
        return this.valueRequired;
    }

    /**
     * Method iterateAnd.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.database.xsd.And> iterateAnd() {
        return this.andList.iterator();
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
    public void removeAllAnd() {
        this.andList.clear();
    }

    /**
     * Method removeAnd.
     * 
     * @param vAnd
     * @return true if the object was removed from the collection.
     */
    public boolean removeAnd(final com.cdoframework.cdolib.database.xsd.And vAnd) {
        boolean removed = andList.remove(vAnd);
        return removed;
    }

    /**
     * Method removeAndAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.database.xsd.And removeAndAt(final int index) {
        java.lang.Object obj = this.andList.remove(index);
        return (com.cdoframework.cdolib.database.xsd.And) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vAnd
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setAnd(final int index,final com.cdoframework.cdolib.database.xsd.And vAnd) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.andList.size()) {
            throw new IndexOutOfBoundsException("setAnd: Index value '" + index + "' not in range [0.." + (this.andList.size() - 1) + "]");
        }

        this.andList.set(index, vAnd);
    }

    /**
     * 
     * 
     * @param vAndArray
     */
    public void setAnd(final com.cdoframework.cdolib.database.xsd.And[] vAndArray) {
        //-- copy array
        andList.clear();

        for (int i = 0; i < vAndArray.length; i++) {
                this.andList.add(vAndArray[i]);
        }
    }

    /**
     * Sets the value of field 'name'.
     * 
     * @param name the value of field 'name'.
     */
    public void setName(final java.lang.String name) {
        this.name = name;
    }

    /**
     * Sets the value of field 'valueRequired'.
     * 
     * @param valueRequired the value of field 'valueRequired'.
     */
    public void setValueRequired(final boolean valueRequired) {
        this.valueRequired = valueRequired;
        this._hasvalueRequired = true;
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
     * com.cdoframework.cdolib.database.xsd.Creteria
     */
    public static com.cdoframework.cdolib.database.xsd.Creteria unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.database.xsd.Creteria) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.database.xsd.Creteria.class, reader);
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
