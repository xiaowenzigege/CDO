/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.3</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.servicebus.xsd;

/**
 * 
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class Database implements java.io.Serializable {

    /**
     * Field URI.
     */
    private java.lang.String URI;

    /**
     * Field loadLevel.
     */
    private int loadLevel;

    /**
     * Keeps track of whether primitive field loadLevel has been
     * set already.
     */
    private boolean _hasloadLevel;

    /**
     * Field user.
     */
    private com.cdoframework.cdolib.servicebus.xsd.User user;

    private java.util.List<com.cdoframework.cdolib.servicebus.xsd.Property> propertyList;

    /**
     * Field connectionPool.
     */
    private com.cdoframework.cdolib.servicebus.xsd.ConnectionPool connectionPool;

    public Database() {
        super();
        this.propertyList = new java.util.ArrayList<com.cdoframework.cdolib.servicebus.xsd.Property>();
    }

    /**
     * 
     * 
     * @param vProperty
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addProperty(final com.cdoframework.cdolib.servicebus.xsd.Property vProperty) throws java.lang.IndexOutOfBoundsException {
        this.propertyList.add(vProperty);
    }

    /**
     * 
     * 
     * @param index
     * @param vProperty
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addProperty(final int index,final com.cdoframework.cdolib.servicebus.xsd.Property vProperty) throws java.lang.IndexOutOfBoundsException {
        this.propertyList.add(index, vProperty);
    }

    /**
     */
    public void deleteLoadLevel() {
        this._hasloadLevel= false;
    }

    /**
     * Method enumerateProperty.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.servicebus.xsd.Property> enumerateProperty() {
        return java.util.Collections.enumeration(this.propertyList);
    }

    /**
     * Returns the value of field 'connectionPool'.
     * 
     * @return the value of field 'ConnectionPool'.
     */
    public com.cdoframework.cdolib.servicebus.xsd.ConnectionPool getConnectionPool() {
        return this.connectionPool;
    }

    /**
     * Returns the value of field 'loadLevel'.
     * 
     * @return the value of field 'LoadLevel'.
     */
    public int getLoadLevel() {
        return this.loadLevel;
    }

    /**
     * Method getProperty.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.servicebus.xsd.Property at the given
     * index
     */
    public com.cdoframework.cdolib.servicebus.xsd.Property getProperty(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.propertyList.size()) {
            throw new IndexOutOfBoundsException("getProperty: Index value '" + index + "' not in range [0.." + (this.propertyList.size() - 1) + "]");
        }

        return propertyList.get(index);
    }

    /**
     * Method getProperty.Returns the contents of the collection in
     * an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.servicebus.xsd.Property[] getProperty() {
        com.cdoframework.cdolib.servicebus.xsd.Property[] array = new com.cdoframework.cdolib.servicebus.xsd.Property[0];
        return this.propertyList.toArray(array);
    }

    /**
     * Method getPropertyCount.
     * 
     * @return the size of this collection
     */
    public int getPropertyCount() {
        return this.propertyList.size();
    }

    /**
     * Returns the value of field 'URI'.
     * 
     * @return the value of field 'URI'.
     */
    public java.lang.String getURI() {
        return this.URI;
    }

    /**
     * Returns the value of field 'user'.
     * 
     * @return the value of field 'User'.
     */
    public com.cdoframework.cdolib.servicebus.xsd.User getUser() {
        return this.user;
    }

    /**
     * Method hasLoadLevel.
     * 
     * @return true if at least one LoadLevel has been added
     */
    public boolean hasLoadLevel() {
        return this._hasloadLevel;
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
     * Method iterateProperty.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.servicebus.xsd.Property> iterateProperty() {
        return this.propertyList.iterator();
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
    public void removeAllProperty() {
        this.propertyList.clear();
    }

    /**
     * Method removeProperty.
     * 
     * @param vProperty
     * @return true if the object was removed from the collection.
     */
    public boolean removeProperty(final com.cdoframework.cdolib.servicebus.xsd.Property vProperty) {
        boolean removed = propertyList.remove(vProperty);
        return removed;
    }

    /**
     * Method removePropertyAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.servicebus.xsd.Property removePropertyAt(final int index) {
        java.lang.Object obj = this.propertyList.remove(index);
        return (com.cdoframework.cdolib.servicebus.xsd.Property) obj;
    }

    /**
     * Sets the value of field 'connectionPool'.
     * 
     * @param connectionPool the value of field 'connectionPool'.
     */
    public void setConnectionPool(final com.cdoframework.cdolib.servicebus.xsd.ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    /**
     * Sets the value of field 'loadLevel'.
     * 
     * @param loadLevel the value of field 'loadLevel'.
     */
    public void setLoadLevel(final int loadLevel) {
        this.loadLevel = loadLevel;
        this._hasloadLevel = true;
    }

    /**
     * 
     * 
     * @param index
     * @param vProperty
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setProperty(final int index,final com.cdoframework.cdolib.servicebus.xsd.Property vProperty) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.propertyList.size()) {
            throw new IndexOutOfBoundsException("setProperty: Index value '" + index + "' not in range [0.." + (this.propertyList.size() - 1) + "]");
        }

        this.propertyList.set(index, vProperty);
    }

    /**
     * 
     * 
     * @param vPropertyArray
     */
    public void setProperty(final com.cdoframework.cdolib.servicebus.xsd.Property[] vPropertyArray) {
        //-- copy array
        propertyList.clear();

        for (int i = 0; i < vPropertyArray.length; i++) {
                this.propertyList.add(vPropertyArray[i]);
        }
    }

    /**
     * Sets the value of field 'URI'.
     * 
     * @param URI the value of field 'URI'.
     */
    public void setURI(final java.lang.String URI) {
        this.URI = URI;
    }

    /**
     * Sets the value of field 'user'.
     * 
     * @param user the value of field 'user'.
     */
    public void setUser(final com.cdoframework.cdolib.servicebus.xsd.User user) {
        this.user = user;
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
     * com.cdoframework.cdolib.servicebus.xsd.Database
     */
    public static com.cdoframework.cdolib.servicebus.xsd.Database unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.servicebus.xsd.Database) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.servicebus.xsd.Database.class, reader);
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
