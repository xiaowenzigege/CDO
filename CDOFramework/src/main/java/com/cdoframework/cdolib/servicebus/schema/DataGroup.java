/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.servicebus.schema;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;

import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

import com.cdoframework.cdolib.base.CycleList;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.database.IDataEngine;

/**
 * Class DataGroup.
 * 
 * @version $Revision$ $Date$
 */
public class DataGroup implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _id
     */
    private java.lang.String _id;

    /**
     * Field _driver
     */
    private java.lang.String _driver;

    /**
     * Field _charset
     */
    private java.lang.String _charset;

    /**
     * Field _classPath
     */
    private java.lang.String _classPath;

    /**
     * Field _databaseList
     */
    private java.util.ArrayList _databaseList;


      //----------------/
     //- Constructors -/
    //----------------/

    public DataGroup() 
     {
        super();
        _databaseList = new ArrayList();
    } //-- com.cdoframework.cdolib.servicebus.schema.DataGroup()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addDatabase
     * 
     * 
     * 
     * @param vDatabase
     */
    public void addDatabase(com.cdoframework.cdolib.servicebus.schema.Database vDatabase)
        throws java.lang.IndexOutOfBoundsException
    {
        _databaseList.add(vDatabase);
    } //-- void addDatabase(com.cdoframework.cdolib.servicebus.schema.Database) 

    /**
     * Method addDatabase
     * 
     * 
     * 
     * @param index
     * @param vDatabase
     */
    public void addDatabase(int index, com.cdoframework.cdolib.servicebus.schema.Database vDatabase)
        throws java.lang.IndexOutOfBoundsException
    {
        _databaseList.add(index, vDatabase);
    } //-- void addDatabase(int, com.cdoframework.cdolib.servicebus.schema.Database) 

    /**
     * Method clearDatabase
     * 
     */
    public void clearDatabase()
    {
        _databaseList.clear();
    } //-- void clearDatabase() 

    /**
     * Method enumerateDatabase
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateDatabase()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_databaseList.iterator());
    } //-- java.util.Enumeration enumerateDatabase() 

    /**
     * Returns the value of field 'charset'.
     * 
     * @return String
     * @return the value of field 'charset'.
     */
    public java.lang.String getCharset()
    {
        return this._charset;
    } //-- java.lang.String getCharset() 

    /**
     * Returns the value of field 'classPath'.
     * 
     * @return String
     * @return the value of field 'classPath'.
     */
    public java.lang.String getClassPath()
    {
        return this._classPath;
    } //-- java.lang.String getClassPath() 

    /**
     * Method getDatabase
     * 
     * 
     * 
     * @param index
     * @return Database
     */
    public com.cdoframework.cdolib.servicebus.schema.Database getDatabase(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _databaseList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.servicebus.schema.Database) _databaseList.get(index);
    } //-- com.cdoframework.cdolib.servicebus.schema.Database getDatabase(int) 

    /**
     * Method getDatabase
     * 
     * 
     * 
     * @return Database
     */
    public com.cdoframework.cdolib.servicebus.schema.Database[] getDatabase()
    {
        int size = _databaseList.size();
        com.cdoframework.cdolib.servicebus.schema.Database[] mArray = new com.cdoframework.cdolib.servicebus.schema.Database[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.servicebus.schema.Database) _databaseList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.servicebus.schema.Database[] getDatabase() 

    /**
     * Method getDatabaseCount
     * 
     * 
     * 
     * @return int
     */
    public int getDatabaseCount()
    {
        return _databaseList.size();
    } //-- int getDatabaseCount() 

    /**
     * Returns the value of field 'driver'.
     * 
     * @return String
     * @return the value of field 'driver'.
     */
    public java.lang.String getDriver()
    {
        return this._driver;
    } //-- java.lang.String getDriver() 

    /**
     * Returns the value of field 'id'.
     * 
     * @return String
     * @return the value of field 'id'.
     */
    public java.lang.String getId()
    {
        return this._id;
    } //-- java.lang.String getId() 

    /**
     * Method isValid
     * 
     * 
     * 
     * @return boolean
     */
    public boolean isValid()
    {
        try {
            validate();
        }
        catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    } //-- boolean isValid() 

    /**
     * Method marshal
     * 
     * 
     * 
     * @param out
     */
    public void marshal(java.io.Writer out)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, out);
    } //-- void marshal(java.io.Writer) 

    /**
     * Method marshal
     * 
     * 
     * 
     * @param handler
     */
    public void marshal(org.xml.sax.ContentHandler handler)
        throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, handler);
    } //-- void marshal(org.xml.sax.ContentHandler) 

    /**
     * Method removeDatabase
     * 
     * 
     * 
     * @param vDatabase
     * @return boolean
     */
    public boolean removeDatabase(com.cdoframework.cdolib.servicebus.schema.Database vDatabase)
    {
        boolean removed = _databaseList.remove(vDatabase);
        return removed;
    } //-- boolean removeDatabase(com.cdoframework.cdolib.servicebus.schema.Database) 

    /**
     * Sets the value of field 'charset'.
     * 
     * @param charset the value of field 'charset'.
     */
    public void setCharset(java.lang.String charset)
    {
        this._charset = charset;
    } //-- void setCharset(java.lang.String) 

    /**
     * Sets the value of field 'classPath'.
     * 
     * @param classPath the value of field 'classPath'.
     */
    public void setClassPath(java.lang.String classPath)
    {
        this._classPath = classPath;
    } //-- void setClassPath(java.lang.String) 

    /**
     * Method setDatabase
     * 
     * 
     * 
     * @param index
     * @param vDatabase
     */
    public void setDatabase(int index, com.cdoframework.cdolib.servicebus.schema.Database vDatabase)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _databaseList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _databaseList.set(index, vDatabase);
    } //-- void setDatabase(int, com.cdoframework.cdolib.servicebus.schema.Database) 

    /**
     * Method setDatabase
     * 
     * 
     * 
     * @param databaseArray
     */
    public void setDatabase(com.cdoframework.cdolib.servicebus.schema.Database[] databaseArray)
    {
        //-- copy array
        _databaseList.clear();
        for (int i = 0; i < databaseArray.length; i++) {
            _databaseList.add(databaseArray[i]);
        }
    } //-- void setDatabase(com.cdoframework.cdolib.servicebus.schema.Database) 

    /**
     * Sets the value of field 'driver'.
     * 
     * @param driver the value of field 'driver'.
     */
    public void setDriver(java.lang.String driver)
    {
        this._driver = driver;
    } //-- void setDriver(java.lang.String) 

    /**
     * Sets the value of field 'id'.
     * 
     * @param id the value of field 'id'.
     */
    public void setId(java.lang.String id)
    {
        this._id = id;
    } //-- void setId(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return DataGroup
     */
    public static com.cdoframework.cdolib.servicebus.schema.DataGroup unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.servicebus.schema.DataGroup) Unmarshaller.unmarshal(com.cdoframework.cdolib.servicebus.schema.DataGroup.class, reader);
    } //-- com.cdoframework.cdolib.servicebus.schema.DataGroup unmarshal(java.io.Reader) 

    /**
     * Method validate
     * 
     */
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

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