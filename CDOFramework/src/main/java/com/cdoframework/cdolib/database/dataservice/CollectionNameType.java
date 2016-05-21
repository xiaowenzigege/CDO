/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.database.dataservice;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

import com.cdoframework.cdolib.database.INoSQLDataEngine;

/**
 * Class CollectionNameType.
 * 
 * @version $Revision$ $Date$
 */
public class CollectionNameType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _DBServerId
     */
    private java.lang.String _DBServerId;

    /**
     * Field _DBName
     */
    private java.lang.String _DBName;

    /**
     * Field _collectionName
     */
    private java.lang.String _collectionName;


      //----------------/
     //- Constructors -/
    //----------------/

    public CollectionNameType() 
     {
        super();
    } //-- com.cdoframework.cdolib.database.dataservice.CollectionNameType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'collectionName'.
     * 
     * @return String
     * @return the value of field 'collectionName'.
     */
    public java.lang.String getCollectionName()
    {
        return this._collectionName;
    } //-- java.lang.String getCollectionName() 

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
     * Returns the value of field 'DBServerId'.
     * 
     * @return String
     * @return the value of field 'DBServerId'.
     */
    public java.lang.String getDBServerId()
    {
        return this._DBServerId;
    } //-- java.lang.String getDBServerId() 

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
     * Sets the value of field 'collectionName'.
     * 
     * @param collectionName the value of field 'collectionName'.
     */
    public void setCollectionName(java.lang.String collectionName)
    {
        this._collectionName = collectionName;
    } //-- void setCollectionName(java.lang.String) 

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
     * Sets the value of field 'DBServerId'.
     * 
     * @param DBServerId the value of field 'DBServerId'.
     */
    public void setDBServerId(java.lang.String DBServerId)
    {
        this._DBServerId = DBServerId;
    } //-- void setDBServerId(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return CollectionNameType
     */
    public static com.cdoframework.cdolib.database.dataservice.CollectionNameType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.database.dataservice.CollectionNameType) Unmarshaller.unmarshal(com.cdoframework.cdolib.database.dataservice.CollectionNameType.class, reader);
    } //-- com.cdoframework.cdolib.database.dataservice.CollectionNameType unmarshal(java.io.Reader) 

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
     * ************************************************************************************************************************************
     * ************************************************************************************************************************************
     * *********************************************************Manual code  **************************************************************
     * ************************************************************************************************************************************
     * ************************************************************************************************************************************
     */
    private INoSQLDataEngine nosqlDataEngine;
    public INoSQLDataEngine getNosqlDataEngine()
    {
    	return this.nosqlDataEngine;
    }
    public void setNoSQLDataEngine(INoSQLDataEngine nosqlDataEngine)
    {
    	this.nosqlDataEngine = nosqlDataEngine;
    }
}
