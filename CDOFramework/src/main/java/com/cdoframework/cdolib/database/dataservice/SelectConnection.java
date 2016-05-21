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
 * Class SelectConnection.
 * 
 * @version $Revision$ $Date$
 */
public class SelectConnection implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _bigTableName
     */
    private java.lang.String _bigTableName = "";

    /**
     * Field _idFieldId
     */
    private java.lang.String _idFieldId = "";


      //----------------/
     //- Constructors -/
    //----------------/

    public SelectConnection() 
     {
        super();
        setBigTableName("");
        setIdFieldId("");
    } //-- com.cdoframework.cdolib.database.dataservice.SelectConnection()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'bigTableName'.
     * 
     * @return String
     * @return the value of field 'bigTableName'.
     */
    public java.lang.String getBigTableName()
    {
        return this._bigTableName;
    } //-- java.lang.String getBigTableName() 

    /**
     * Returns the value of field 'idFieldId'.
     * 
     * @return String
     * @return the value of field 'idFieldId'.
     */
    public java.lang.String getIdFieldId()
    {
        return this._idFieldId;
    } //-- java.lang.String getIdFieldId() 

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
     * Sets the value of field 'bigTableName'.
     * 
     * @param bigTableName the value of field 'bigTableName'.
     */
    public void setBigTableName(java.lang.String bigTableName)
    {
        this._bigTableName = bigTableName;
    } //-- void setBigTableName(java.lang.String) 

    /**
     * Sets the value of field 'idFieldId'.
     * 
     * @param idFieldId the value of field 'idFieldId'.
     */
    public void setIdFieldId(java.lang.String idFieldId)
    {
        this._idFieldId = idFieldId;
    } //-- void setIdFieldId(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return SelectConnection
     */
    public static com.cdoframework.cdolib.database.dataservice.SelectConnection unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.database.dataservice.SelectConnection) Unmarshaller.unmarshal(com.cdoframework.cdolib.database.dataservice.SelectConnection.class, reader);
    } //-- com.cdoframework.cdolib.database.dataservice.SelectConnection unmarshal(java.io.Reader) 

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
