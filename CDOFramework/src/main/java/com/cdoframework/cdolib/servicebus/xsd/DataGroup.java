/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.3</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.servicebus.xsd;

import java.util.Properties;

import com.cdoframework.cdolib.base.CycleList;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.database.IDataEngine;

/**
 * 
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class DataGroup implements java.io.Serializable {

    /**
     * Field id.
     */
    private java.lang.String id;

    /**
     * Field driver.
     */
    private java.lang.String driver;

    /**
     * Field charset.
     */
    private java.lang.String charset;

    /**
     * Field classPath.
     */
    private java.lang.String classPath;

    private java.util.List<com.cdoframework.cdolib.servicebus.xsd.Database> databaseList;

    public DataGroup() {
        super();
        this.databaseList = new java.util.ArrayList<com.cdoframework.cdolib.servicebus.xsd.Database>();
    }

    /**
     * 
     * 
     * @param vDatabase
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addDatabase(final com.cdoframework.cdolib.servicebus.xsd.Database vDatabase) throws java.lang.IndexOutOfBoundsException {
        this.databaseList.add(vDatabase);
    }

    /**
     * 
     * 
     * @param index
     * @param vDatabase
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addDatabase(final int index,final com.cdoframework.cdolib.servicebus.xsd.Database vDatabase) throws java.lang.IndexOutOfBoundsException {
        this.databaseList.add(index, vDatabase);
    }

    /**
     * Method enumerateDatabase.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.servicebus.xsd.Database> enumerateDatabase() {
        return java.util.Collections.enumeration(this.databaseList);
    }

    /**
     * Returns the value of field 'charset'.
     * 
     * @return the value of field 'Charset'.
     */
    public java.lang.String getCharset() {
        return this.charset;
    }

    /**
     * Returns the value of field 'classPath'.
     * 
     * @return the value of field 'ClassPath'.
     */
    public java.lang.String getClassPath() {
        return this.classPath;
    }

    /**
     * Method getDatabase.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.servicebus.xsd.Database at the given
     * index
     */
    public com.cdoframework.cdolib.servicebus.xsd.Database getDatabase(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.databaseList.size()) {
            throw new IndexOutOfBoundsException("getDatabase: Index value '" + index + "' not in range [0.." + (this.databaseList.size() - 1) + "]");
        }

        return databaseList.get(index);
    }

    /**
     * Method getDatabase.Returns the contents of the collection in
     * an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.servicebus.xsd.Database[] getDatabase() {
        com.cdoframework.cdolib.servicebus.xsd.Database[] array = new com.cdoframework.cdolib.servicebus.xsd.Database[0];
        return this.databaseList.toArray(array);
    }

    /**
     * Method getDatabaseCount.
     * 
     * @return the size of this collection
     */
    public int getDatabaseCount() {
        return this.databaseList.size();
    }

    /**
     * Returns the value of field 'driver'.
     * 
     * @return the value of field 'Driver'.
     */
    public java.lang.String getDriver() {
        return this.driver;
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
     * Method iterateDatabase.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.servicebus.xsd.Database> iterateDatabase() {
        return this.databaseList.iterator();
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
    public void removeAllDatabase() {
        this.databaseList.clear();
    }

    /**
     * Method removeDatabase.
     * 
     * @param vDatabase
     * @return true if the object was removed from the collection.
     */
    public boolean removeDatabase(final com.cdoframework.cdolib.servicebus.xsd.Database vDatabase) {
        boolean removed = databaseList.remove(vDatabase);
        return removed;
    }

    /**
     * Method removeDatabaseAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.servicebus.xsd.Database removeDatabaseAt(final int index) {
        java.lang.Object obj = this.databaseList.remove(index);
        return (com.cdoframework.cdolib.servicebus.xsd.Database) obj;
    }

    /**
     * Sets the value of field 'charset'.
     * 
     * @param charset the value of field 'charset'.
     */
    public void setCharset(final java.lang.String charset) {
        this.charset = charset;
    }

    /**
     * Sets the value of field 'classPath'.
     * 
     * @param classPath the value of field 'classPath'.
     */
    public void setClassPath(final java.lang.String classPath) {
        this.classPath = classPath;
    }

    /**
     * 
     * 
     * @param index
     * @param vDatabase
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setDatabase(final int index,final com.cdoframework.cdolib.servicebus.xsd.Database vDatabase) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.databaseList.size()) {
            throw new IndexOutOfBoundsException("setDatabase: Index value '" + index + "' not in range [0.." + (this.databaseList.size() - 1) + "]");
        }

        this.databaseList.set(index, vDatabase);
    }

    /**
     * 
     * 
     * @param vDatabaseArray
     */
    public void setDatabase(final com.cdoframework.cdolib.servicebus.xsd.Database[] vDatabaseArray) {
        //-- copy array
        databaseList.clear();

        for (int i = 0; i < vDatabaseArray.length; i++) {
                this.databaseList.add(vDatabaseArray[i]);
        }
    }

    /**
     * Sets the value of field 'driver'.
     * 
     * @param driver the value of field 'driver'.
     */
    public void setDriver(final java.lang.String driver) {
        this.driver = driver;
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
     * com.cdoframework.cdolib.servicebus.xsd.DataGroup
     */
    public static com.cdoframework.cdolib.servicebus.xsd.DataGroup unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.servicebus.xsd.DataGroup) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.servicebus.xsd.DataGroup.class, reader);
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
    
  //------------------------------manual code----------------------
    /**
	 * 初始化DataGroup对象
	 */
	public CycleList<IDataEngine> init() throws Exception
	{
		Database[] dbs=this.getDatabase();
		
		CycleList<IDataEngine> clDataEngine=new CycleList<IDataEngine>();
		for(int i=0;i<dbs.length;i++)
		{
			IDataEngine dataEngine=(IDataEngine)Class.forName(this.getClassPath()).newInstance();
			
			dataEngine.setDriver(this.getDriver());
			dataEngine.setURI(dbs[i].getURI());
			dataEngine.setCharset(this.getCharset());
			dataEngine.setLoadLevel(dbs[i].getLoadLevel());
			dataEngine.setUserName(dbs[i].getUser().getUserName());
			dataEngine.setPassword(dbs[i].getUser().getPassword());

			Properties properties=null;
			int nPropertyCount=dbs[i].getPropertyCount();
			if(nPropertyCount>0)
			{
				properties=new Properties();
				for(int j=0;j<nPropertyCount;j++)
				{
					Property proper=dbs[i].getProperty(j);
					properties.setProperty(proper.getName(),proper.getValue());
				}
			}
			if(properties!=null)
			{
				dataEngine.setProperties(properties);
			}
			
			ConnectionPool connPool=dbs[i].getConnectionPool();
			if(connPool!=null)
			{
				dataEngine.setMinConnectionCount(connPool.getMinIdleConnectionCount());
				
				dataEngine.setMaxConnectionCount(connPool.getMaxIdleConnectionCount());
				dataEngine.setTimeout(connPool.getMaxWaitTime());
			}
			Return ret = dataEngine.open();
			if(ret.getCode()!=0)
			{
				throw new Exception("Could not create JDBC connection "+dataEngine.getURI());
			}
			
			clDataEngine.add(dataEngine);
		}
		
		return clDataEngine;
	}
}
