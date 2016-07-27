/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.3</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.database.xsd;

import com.cdoframework.cdolib.database.INoSQLDataEngine;

/**
 * 
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class CollectionNameType implements java.io.Serializable {

    /**
     * Field DBServerId.
     */
    private java.lang.String DBServerId;

    /**
     * Field DBName.
     */
    private java.lang.String DBName;

    /**
     * Field collectionName.
     */
    private java.lang.String collectionName;

    public CollectionNameType() {
        super();
    }

    /**
     * Returns the value of field 'collectionName'.
     * 
     * @return the value of field 'CollectionName'.
     */
    public java.lang.String getCollectionName() {
        return this.collectionName;
    }

    /**
     * Returns the value of field 'DBName'.
     * 
     * @return the value of field 'DBName'.
     */
    public java.lang.String getDBName() {
        return this.DBName;
    }

    /**
     * Returns the value of field 'DBServerId'.
     * 
     * @return the value of field 'DBServerId'.
     */
    public java.lang.String getDBServerId() {
        return this.DBServerId;
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
     * Sets the value of field 'collectionName'.
     * 
     * @param collectionName the value of field 'collectionName'.
     */
    public void setCollectionName(final java.lang.String collectionName) {
        this.collectionName = collectionName;
    }

    /**
     * Sets the value of field 'DBName'.
     * 
     * @param DBName the value of field 'DBName'.
     */
    public void setDBName(final java.lang.String DBName) {
        this.DBName = DBName;
    }

    /**
     * Sets the value of field 'DBServerId'.
     * 
     * @param DBServerId the value of field 'DBServerId'.
     */
    public void setDBServerId(final java.lang.String DBServerId) {
        this.DBServerId = DBServerId;
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
     * com.cdoframework.cdolib.database.xsd.CollectionNameType
     */
    public static com.cdoframework.cdolib.database.xsd.CollectionNameType unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.database.xsd.CollectionNameType) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.database.xsd.CollectionNameType.class, reader);
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
