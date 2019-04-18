/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.4.1</a>, using an XML
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

    /**
     * Field database.
     */
    private com.cdoframework.cdolib.servicebus.xsd.Database database;

    public DataGroup() {
        super();
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
     * Returns the value of field 'database'.
     * 
     * @return the value of field 'Database'.
     */
    public com.cdoframework.cdolib.servicebus.xsd.Database getDatabase() {
        return this.database;
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
     * Sets the value of field 'database'.
     * 
     * @param database the value of field 'database'.
     */
    public void setDatabase(final com.cdoframework.cdolib.servicebus.xsd.Database database) {
        this.database = database;
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
    /**
  	 * ***************************************manual  code *************************************************
  	 * 序列化DataGroup对象
  	 */
  	public IDataEngine init() throws Exception
  	{
  		
  		
//  		CycleList<IDataEngine> clDataEngine=new CycleList<IDataEngine>();
  		
  		Database dbs=this.getDatabase();
  		IDataEngine dataEngine=(IDataEngine)Class.forName(this.getClassPath()).newInstance();			  		
  		dataEngine.setDriver(this.getDriver());
		dataEngine.setURI(dbs.getURI());
		dataEngine.setCharset(this.getCharset());
			
		dataEngine.setUserName(dbs.getUser().getUserName());
		dataEngine.setPassword(dbs.getUser().getPassword());

		Properties properties=null;
		int nPropertyCount=dbs.getPropertyCount();
		if(nPropertyCount>0){
				properties=new Properties();
				for(int j=0;j<nPropertyCount;j++){
					Property proper=dbs.getProperty(j);
					properties.setProperty(proper.getName(),proper.getValue());
				}
				dataEngine.setProperties(properties);
			}			
		ConnectionPool connPool=dbs.getConnectionPool();
		if(connPool!=null){
				dataEngine.setInitialSize(connPool.getInitialSize());
				dataEngine.setnMaxTotal(connPool.getMaxTotal());
				dataEngine.setMinIdle(connPool.getMinIdle());
				dataEngine.setMaxIdle(connPool.getMaxTotal());
				dataEngine.setnMaxConnLifetimeMillis(connPool.getMaxConnLifetimeMillis());
				
				dataEngine.setRemoveAbandonedTimeout(connPool.getRemoveAbandonedTimeout());
				dataEngine.setTestWhileIdle(connPool.getTestWhileIdle());
				dataEngine.setTestOnBorrow(connPool.getTestOnBorrow());
				dataEngine.setValidationQuery(connPool.getValidationQuery());
				dataEngine.setPoolPreparedStatements(connPool.getPoolPreparedStatements());
				
				dataEngine.setRemoveAbandonedOnMaintenance(connPool.getRemoveAbandonedOnMaintenance());
				dataEngine.setLogAbandoned(connPool.getLogAbandoned());		
		}
		Return ret = dataEngine.open();
		if(ret.getCode()!=Return.OK.getCode()){
				throw new Exception("Could not create JDBC connection "+dataEngine.getURI());
			}			
//		clDataEngine.add(dataEngine);  		
  		return dataEngine;
  	}
}
