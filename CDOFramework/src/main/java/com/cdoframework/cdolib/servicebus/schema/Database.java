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
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

/**
 * Class Database.
 * 
 * @version $Revision$ $Date$
 */
public class Database implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _URI
     */
    private java.lang.String _URI;

    /**
     * Field _loadLevel
     */
    private int _loadLevel;

    /**
     * keeps track of state for field: _loadLevel
     */
    private boolean _has_loadLevel;

    /**
     * Field _user
     */
    private com.cdoframework.cdolib.servicebus.schema.User _user;

    /**
     * Field _propertyList
     */
    private java.util.ArrayList _propertyList;

    /**
     * Field _connectionPool
     */
    private com.cdoframework.cdolib.servicebus.schema.ConnectionPool _connectionPool;


      //----------------/
     //- Constructors -/
    //----------------/

    public Database() 
     {
        super();
        _propertyList = new ArrayList();
    } //-- com.cdoframework.cdolib.servicebus.schema.Database()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addProperty
     * 
     * 
     * 
     * @param vProperty
     */
    public void addProperty(com.cdoframework.cdolib.servicebus.schema.Property vProperty)
        throws java.lang.IndexOutOfBoundsException
    {
        _propertyList.add(vProperty);
    } //-- void addProperty(com.cdoframework.cdolib.servicebus.schema.Property) 

    /**
     * Method addProperty
     * 
     * 
     * 
     * @param index
     * @param vProperty
     */
    public void addProperty(int index, com.cdoframework.cdolib.servicebus.schema.Property vProperty)
        throws java.lang.IndexOutOfBoundsException
    {
        _propertyList.add(index, vProperty);
    } //-- void addProperty(int, com.cdoframework.cdolib.servicebus.schema.Property) 

    /**
     * Method clearProperty
     * 
     */
    public void clearProperty()
    {
        _propertyList.clear();
    } //-- void clearProperty() 

    /**
     * Method deleteLoadLevel
     * 
     */
    public void deleteLoadLevel()
    {
        this._has_loadLevel= false;
    } //-- void deleteLoadLevel() 

    /**
     * Method enumerateProperty
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateProperty()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_propertyList.iterator());
    } //-- java.util.Enumeration enumerateProperty() 

    /**
     * Returns the value of field 'connectionPool'.
     * 
     * @return ConnectionPool
     * @return the value of field 'connectionPool'.
     */
    public com.cdoframework.cdolib.servicebus.schema.ConnectionPool getConnectionPool()
    {
        return this._connectionPool;
    } //-- com.cdoframework.cdolib.servicebus.schema.ConnectionPool getConnectionPool() 

    /**
     * Returns the value of field 'loadLevel'.
     * 
     * @return int
     * @return the value of field 'loadLevel'.
     */
    public int getLoadLevel()
    {
        return this._loadLevel;
    } //-- int getLoadLevel() 

    /**
     * Method getProperty
     * 
     * 
     * 
     * @param index
     * @return Property
     */
    public com.cdoframework.cdolib.servicebus.schema.Property getProperty(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _propertyList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.servicebus.schema.Property) _propertyList.get(index);
    } //-- com.cdoframework.cdolib.servicebus.schema.Property getProperty(int) 

    /**
     * Method getProperty
     * 
     * 
     * 
     * @return Property
     */
    public com.cdoframework.cdolib.servicebus.schema.Property[] getProperty()
    {
        int size = _propertyList.size();
        com.cdoframework.cdolib.servicebus.schema.Property[] mArray = new com.cdoframework.cdolib.servicebus.schema.Property[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.servicebus.schema.Property) _propertyList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.servicebus.schema.Property[] getProperty() 

    /**
     * Method getPropertyCount
     * 
     * 
     * 
     * @return int
     */
    public int getPropertyCount()
    {
        return _propertyList.size();
    } //-- int getPropertyCount() 

    /**
     * Returns the value of field 'URI'.
     * 
     * @return String
     * @return the value of field 'URI'.
     */
    public java.lang.String getURI()
    {
        return this._URI;
    } //-- java.lang.String getURI() 

    /**
     * Returns the value of field 'user'.
     * 
     * @return User
     * @return the value of field 'user'.
     */
    public com.cdoframework.cdolib.servicebus.schema.User getUser()
    {
        return this._user;
    } //-- com.cdoframework.cdolib.servicebus.schema.User getUser() 

    /**
     * Method hasLoadLevel
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasLoadLevel()
    {
        return this._has_loadLevel;
    } //-- boolean hasLoadLevel() 

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
     * Method removeProperty
     * 
     * 
     * 
     * @param vProperty
     * @return boolean
     */
    public boolean removeProperty(com.cdoframework.cdolib.servicebus.schema.Property vProperty)
    {
        boolean removed = _propertyList.remove(vProperty);
        return removed;
    } //-- boolean removeProperty(com.cdoframework.cdolib.servicebus.schema.Property) 

    /**
     * Sets the value of field 'connectionPool'.
     * 
     * @param connectionPool the value of field 'connectionPool'.
     */
    public void setConnectionPool(com.cdoframework.cdolib.servicebus.schema.ConnectionPool connectionPool)
    {
        this._connectionPool = connectionPool;
    } //-- void setConnectionPool(com.cdoframework.cdolib.servicebus.schema.ConnectionPool) 

    /**
     * Sets the value of field 'loadLevel'.
     * 
     * @param loadLevel the value of field 'loadLevel'.
     */
    public void setLoadLevel(int loadLevel)
    {
        this._loadLevel = loadLevel;
        this._has_loadLevel = true;
    } //-- void setLoadLevel(int) 

    /**
     * Method setProperty
     * 
     * 
     * 
     * @param index
     * @param vProperty
     */
    public void setProperty(int index, com.cdoframework.cdolib.servicebus.schema.Property vProperty)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _propertyList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _propertyList.set(index, vProperty);
    } //-- void setProperty(int, com.cdoframework.cdolib.servicebus.schema.Property) 

    /**
     * Method setProperty
     * 
     * 
     * 
     * @param propertyArray
     */
    public void setProperty(com.cdoframework.cdolib.servicebus.schema.Property[] propertyArray)
    {
        //-- copy array
        _propertyList.clear();
        for (int i = 0; i < propertyArray.length; i++) {
            _propertyList.add(propertyArray[i]);
        }
    } //-- void setProperty(com.cdoframework.cdolib.servicebus.schema.Property) 

    /**
     * Sets the value of field 'URI'.
     * 
     * @param URI the value of field 'URI'.
     */
    public void setURI(java.lang.String URI)
    {
        this._URI = URI;
    } //-- void setURI(java.lang.String) 

    /**
     * Sets the value of field 'user'.
     * 
     * @param user the value of field 'user'.
     */
    public void setUser(com.cdoframework.cdolib.servicebus.schema.User user)
    {
        this._user = user;
    } //-- void setUser(com.cdoframework.cdolib.servicebus.schema.User) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Database
     */
    public static com.cdoframework.cdolib.servicebus.schema.Database unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.servicebus.schema.Database) Unmarshaller.unmarshal(com.cdoframework.cdolib.servicebus.schema.Database.class, reader);
    } //-- com.cdoframework.cdolib.servicebus.schema.Database unmarshal(java.io.Reader) 

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

}
