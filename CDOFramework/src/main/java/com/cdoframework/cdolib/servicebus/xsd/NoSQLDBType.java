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
public class NoSQLDBType implements java.io.Serializable {

    /**
     * Field id.
     */
    private java.lang.String id;

    /**
     * Field poolConfig.
     */
    private com.cdoframework.cdolib.servicebus.xsd.PoolConfig poolConfig;

    private java.util.List<com.cdoframework.cdolib.servicebus.xsd.ServerAddr> serverAddrList;

    private java.util.List<com.cdoframework.cdolib.servicebus.xsd.DBConfig> DBConfigList;

    public NoSQLDBType() {
        super();
        this.serverAddrList = new java.util.ArrayList<com.cdoframework.cdolib.servicebus.xsd.ServerAddr>();
        this.DBConfigList = new java.util.ArrayList<com.cdoframework.cdolib.servicebus.xsd.DBConfig>();
    }

    /**
     * 
     * 
     * @param vDBConfig
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addDBConfig(final com.cdoframework.cdolib.servicebus.xsd.DBConfig vDBConfig) throws java.lang.IndexOutOfBoundsException {
        this.DBConfigList.add(vDBConfig);
    }

    /**
     * 
     * 
     * @param index
     * @param vDBConfig
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addDBConfig(final int index,final com.cdoframework.cdolib.servicebus.xsd.DBConfig vDBConfig) throws java.lang.IndexOutOfBoundsException {
        this.DBConfigList.add(index, vDBConfig);
    }

    /**
     * 
     * 
     * @param vServerAddr
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addServerAddr(final com.cdoframework.cdolib.servicebus.xsd.ServerAddr vServerAddr) throws java.lang.IndexOutOfBoundsException {
        this.serverAddrList.add(vServerAddr);
    }

    /**
     * 
     * 
     * @param index
     * @param vServerAddr
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addServerAddr(final int index,final com.cdoframework.cdolib.servicebus.xsd.ServerAddr vServerAddr) throws java.lang.IndexOutOfBoundsException {
        this.serverAddrList.add(index, vServerAddr);
    }

    /**
     * Method enumerateDBConfig.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.servicebus.xsd.DBConfig> enumerateDBConfig() {
        return java.util.Collections.enumeration(this.DBConfigList);
    }

    /**
     * Method enumerateServerAddr.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.servicebus.xsd.ServerAddr> enumerateServerAddr() {
        return java.util.Collections.enumeration(this.serverAddrList);
    }

    /**
     * Method getDBConfig.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.servicebus.xsd.DBConfig at the given
     * index
     */
    public com.cdoframework.cdolib.servicebus.xsd.DBConfig getDBConfig(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.DBConfigList.size()) {
            throw new IndexOutOfBoundsException("getDBConfig: Index value '" + index + "' not in range [0.." + (this.DBConfigList.size() - 1) + "]");
        }

        return DBConfigList.get(index);
    }

    /**
     * Method getDBConfig.Returns the contents of the collection in
     * an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.servicebus.xsd.DBConfig[] getDBConfig() {
        com.cdoframework.cdolib.servicebus.xsd.DBConfig[] array = new com.cdoframework.cdolib.servicebus.xsd.DBConfig[0];
        return this.DBConfigList.toArray(array);
    }

    /**
     * Method getDBConfigCount.
     * 
     * @return the size of this collection
     */
    public int getDBConfigCount() {
        return this.DBConfigList.size();
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
     * Returns the value of field 'poolConfig'.
     * 
     * @return the value of field 'PoolConfig'.
     */
    public com.cdoframework.cdolib.servicebus.xsd.PoolConfig getPoolConfig() {
        return this.poolConfig;
    }

    /**
     * Method getServerAddr.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.servicebus.xsd.ServerAddr at the
     * given index
     */
    public com.cdoframework.cdolib.servicebus.xsd.ServerAddr getServerAddr(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.serverAddrList.size()) {
            throw new IndexOutOfBoundsException("getServerAddr: Index value '" + index + "' not in range [0.." + (this.serverAddrList.size() - 1) + "]");
        }

        return serverAddrList.get(index);
    }

    /**
     * Method getServerAddr.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.servicebus.xsd.ServerAddr[] getServerAddr() {
        com.cdoframework.cdolib.servicebus.xsd.ServerAddr[] array = new com.cdoframework.cdolib.servicebus.xsd.ServerAddr[0];
        return this.serverAddrList.toArray(array);
    }

    /**
     * Method getServerAddrCount.
     * 
     * @return the size of this collection
     */
    public int getServerAddrCount() {
        return this.serverAddrList.size();
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
     * Method iterateDBConfig.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.servicebus.xsd.DBConfig> iterateDBConfig() {
        return this.DBConfigList.iterator();
    }

    /**
     * Method iterateServerAddr.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.servicebus.xsd.ServerAddr> iterateServerAddr() {
        return this.serverAddrList.iterator();
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
    public void removeAllDBConfig() {
        this.DBConfigList.clear();
    }

    /**
     */
    public void removeAllServerAddr() {
        this.serverAddrList.clear();
    }

    /**
     * Method removeDBConfig.
     * 
     * @param vDBConfig
     * @return true if the object was removed from the collection.
     */
    public boolean removeDBConfig(final com.cdoframework.cdolib.servicebus.xsd.DBConfig vDBConfig) {
        boolean removed = DBConfigList.remove(vDBConfig);
        return removed;
    }

    /**
     * Method removeDBConfigAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.servicebus.xsd.DBConfig removeDBConfigAt(final int index) {
        java.lang.Object obj = this.DBConfigList.remove(index);
        return (com.cdoframework.cdolib.servicebus.xsd.DBConfig) obj;
    }

    /**
     * Method removeServerAddr.
     * 
     * @param vServerAddr
     * @return true if the object was removed from the collection.
     */
    public boolean removeServerAddr(final com.cdoframework.cdolib.servicebus.xsd.ServerAddr vServerAddr) {
        boolean removed = serverAddrList.remove(vServerAddr);
        return removed;
    }

    /**
     * Method removeServerAddrAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.servicebus.xsd.ServerAddr removeServerAddrAt(final int index) {
        java.lang.Object obj = this.serverAddrList.remove(index);
        return (com.cdoframework.cdolib.servicebus.xsd.ServerAddr) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vDBConfig
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setDBConfig(final int index,final com.cdoframework.cdolib.servicebus.xsd.DBConfig vDBConfig) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.DBConfigList.size()) {
            throw new IndexOutOfBoundsException("setDBConfig: Index value '" + index + "' not in range [0.." + (this.DBConfigList.size() - 1) + "]");
        }

        this.DBConfigList.set(index, vDBConfig);
    }

    /**
     * 
     * 
     * @param vDBConfigArray
     */
    public void setDBConfig(final com.cdoframework.cdolib.servicebus.xsd.DBConfig[] vDBConfigArray) {
        //-- copy array
        DBConfigList.clear();

        for (int i = 0; i < vDBConfigArray.length; i++) {
                this.DBConfigList.add(vDBConfigArray[i]);
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
     * Sets the value of field 'poolConfig'.
     * 
     * @param poolConfig the value of field 'poolConfig'.
     */
    public void setPoolConfig(final com.cdoframework.cdolib.servicebus.xsd.PoolConfig poolConfig) {
        this.poolConfig = poolConfig;
    }

    /**
     * 
     * 
     * @param index
     * @param vServerAddr
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setServerAddr(final int index,final com.cdoframework.cdolib.servicebus.xsd.ServerAddr vServerAddr) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.serverAddrList.size()) {
            throw new IndexOutOfBoundsException("setServerAddr: Index value '" + index + "' not in range [0.." + (this.serverAddrList.size() - 1) + "]");
        }

        this.serverAddrList.set(index, vServerAddr);
    }

    /**
     * 
     * 
     * @param vServerAddrArray
     */
    public void setServerAddr(final com.cdoframework.cdolib.servicebus.xsd.ServerAddr[] vServerAddrArray) {
        //-- copy array
        serverAddrList.clear();

        for (int i = 0; i < vServerAddrArray.length; i++) {
                this.serverAddrList.add(vServerAddrArray[i]);
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
     * com.cdoframework.cdolib.servicebus.xsd.NoSQLDBType
     */
    public static com.cdoframework.cdolib.servicebus.xsd.NoSQLDBType unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.servicebus.xsd.NoSQLDBType) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.servicebus.xsd.NoSQLDBType.class, reader);
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
