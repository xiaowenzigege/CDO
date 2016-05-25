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
public class BigTable implements java.io.Serializable {

    /**
     * Field name.
     */
    private java.lang.String name;

    /**
     * Field groupId.
     */
    private java.lang.String groupId;

    /**
     * Field groupCount.
     */
    private int groupCount;

    /**
     * Keeps track of whether primitive field groupCount has been
     * set already.
     */
    private boolean _hasgroupCount;

    /**
     * Field groupTableCount.
     */
    private int groupTableCount;

    /**
     * Keeps track of whether primitive field groupTableCount has
     * been set already.
     */
    private boolean _hasgroupTableCount;

    /**
     * Field groupTableCapacity.
     */
    private long groupTableCapacity;

    /**
     * Keeps track of whether primitive field groupTableCapacity
     * has been set already.
     */
    private boolean _hasgroupTableCapacity;

    public BigTable() {
        super();
    }

    /**
     */
    public void deleteGroupCount() {
        this._hasgroupCount= false;
    }

    /**
     */
    public void deleteGroupTableCapacity() {
        this._hasgroupTableCapacity= false;
    }

    /**
     */
    public void deleteGroupTableCount() {
        this._hasgroupTableCount= false;
    }

    /**
     * Returns the value of field 'groupCount'.
     * 
     * @return the value of field 'GroupCount'.
     */
    public int getGroupCount() {
        return this.groupCount;
    }

    /**
     * Returns the value of field 'groupId'.
     * 
     * @return the value of field 'GroupId'.
     */
    public java.lang.String getGroupId() {
        return this.groupId;
    }

    /**
     * Returns the value of field 'groupTableCapacity'.
     * 
     * @return the value of field 'GroupTableCapacity'.
     */
    public long getGroupTableCapacity() {
        return this.groupTableCapacity;
    }

    /**
     * Returns the value of field 'groupTableCount'.
     * 
     * @return the value of field 'GroupTableCount'.
     */
    public int getGroupTableCount() {
        return this.groupTableCount;
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
     * Method hasGroupCount.
     * 
     * @return true if at least one GroupCount has been added
     */
    public boolean hasGroupCount() {
        return this._hasgroupCount;
    }

    /**
     * Method hasGroupTableCapacity.
     * 
     * @return true if at least one GroupTableCapacity has been adde
     */
    public boolean hasGroupTableCapacity() {
        return this._hasgroupTableCapacity;
    }

    /**
     * Method hasGroupTableCount.
     * 
     * @return true if at least one GroupTableCount has been added
     */
    public boolean hasGroupTableCount() {
        return this._hasgroupTableCount;
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
     * Sets the value of field 'groupCount'.
     * 
     * @param groupCount the value of field 'groupCount'.
     */
    public void setGroupCount(final int groupCount) {
        this.groupCount = groupCount;
        this._hasgroupCount = true;
    }

    /**
     * Sets the value of field 'groupId'.
     * 
     * @param groupId the value of field 'groupId'.
     */
    public void setGroupId(final java.lang.String groupId) {
        this.groupId = groupId;
    }

    /**
     * Sets the value of field 'groupTableCapacity'.
     * 
     * @param groupTableCapacity the value of field
     * 'groupTableCapacity'.
     */
    public void setGroupTableCapacity(final long groupTableCapacity) {
        this.groupTableCapacity = groupTableCapacity;
        this._hasgroupTableCapacity = true;
    }

    /**
     * Sets the value of field 'groupTableCount'.
     * 
     * @param groupTableCount the value of field 'groupTableCount'.
     */
    public void setGroupTableCount(final int groupTableCount) {
        this.groupTableCount = groupTableCount;
        this._hasgroupTableCount = true;
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
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled
     * com.cdoframework.cdolib.database.xsd.BigTable
     */
    public static com.cdoframework.cdolib.database.xsd.BigTable unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.database.xsd.BigTable) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.database.xsd.BigTable.class, reader);
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
