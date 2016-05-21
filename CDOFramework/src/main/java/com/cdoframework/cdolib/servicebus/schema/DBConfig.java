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
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

/**
 * Class DBConfig.
 * 
 * @version $Revision$ $Date$
 */
public class DBConfig implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _DBName
     */
    private java.lang.String _DBName;

    /**
     * Field _userName
     */
    private java.lang.String _userName;

    /**
     * Field _password
     */
    private java.lang.String _password;


      //----------------/
     //- Constructors -/
    //----------------/

    public DBConfig() 
     {
        super();
    } //-- com.cdoframework.cdolib.servicebus.schema.DBConfig()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'DBName'.
     * 
     * @return String
     * @return the value of field 'DBName'.
     */
    public java.lang.String getDBName()
    {
        return this._DBName;
    } //-- java.lang.String getDBName() 

    /**
     * Returns the value of field 'password'.
     * 
     * @return String
     * @return the value of field 'password'.
     */
    public java.lang.String getPassword()
    {
        return this._password;
    } //-- java.lang.String getPassword() 

    /**
     * Returns the value of field 'userName'.
     * 
     * @return String
     * @return the value of field 'userName'.
     */
    public java.lang.String getUserName()
    {
        return this._userName;
    } //-- java.lang.String getUserName() 

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
     * Sets the value of field 'DBName'.
     * 
     * @param DBName the value of field 'DBName'.
     */
    public void setDBName(java.lang.String DBName)
    {
        this._DBName = DBName;
    } //-- void setDBName(java.lang.String) 

    /**
     * Sets the value of field 'password'.
     * 
     * @param password the value of field 'password'.
     */
    public void setPassword(java.lang.String password)
    {
        this._password = password;
    } //-- void setPassword(java.lang.String) 

    /**
     * Sets the value of field 'userName'.
     * 
     * @param userName the value of field 'userName'.
     */
    public void setUserName(java.lang.String userName)
    {
        this._userName = userName;
    } //-- void setUserName(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return DBConfig
     */
    public static com.cdoframework.cdolib.servicebus.schema.DBConfig unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.servicebus.schema.DBConfig) Unmarshaller.unmarshal(com.cdoframework.cdolib.servicebus.schema.DBConfig.class, reader);
    } //-- com.cdoframework.cdolib.servicebus.schema.DBConfig unmarshal(java.io.Reader) 

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
