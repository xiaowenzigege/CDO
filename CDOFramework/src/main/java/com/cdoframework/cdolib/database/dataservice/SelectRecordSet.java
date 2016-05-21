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
 * Class SelectRecordSet.
 * 
 * @version $Revision$ $Date$
 */
public class SelectRecordSet extends com.cdoframework.cdolib.database.dataservice.SQLBlockType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _outputId
     */
    private java.lang.String _outputId;

    /**
     * Field _keyFieldName
     */
    private java.lang.String _keyFieldName = "";

    /**
     * Field _recordCountId
     */
    private java.lang.String _recordCountId = "";


      //----------------/
     //- Constructors -/
    //----------------/

    public SelectRecordSet() 
     {
        super();
        setKeyFieldName("");
        setRecordCountId("");
    } //-- com.cdoframework.cdolib.database.dataservice.SelectRecordSet()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'keyFieldName'.
     * 
     * @return String
     * @return the value of field 'keyFieldName'.
     */
    public java.lang.String getKeyFieldName()
    {
        return this._keyFieldName;
    } //-- java.lang.String getKeyFieldName() 

    /**
     * Returns the value of field 'outputId'.
     * 
     * @return String
     * @return the value of field 'outputId'.
     */
    public java.lang.String getOutputId()
    {
        return this._outputId;
    } //-- java.lang.String getOutputId() 

    /**
     * Returns the value of field 'recordCountId'.
     * 
     * @return String
     * @return the value of field 'recordCountId'.
     */
    public java.lang.String getRecordCountId()
    {
        return this._recordCountId;
    } //-- java.lang.String getRecordCountId() 

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
     * Sets the value of field 'keyFieldName'.
     * 
     * @param keyFieldName the value of field 'keyFieldName'.
     */
    public void setKeyFieldName(java.lang.String keyFieldName)
    {
        this._keyFieldName = keyFieldName;
    } //-- void setKeyFieldName(java.lang.String) 

    /**
     * Sets the value of field 'outputId'.
     * 
     * @param outputId the value of field 'outputId'.
     */
    public void setOutputId(java.lang.String outputId)
    {
        this._outputId = outputId;
    } //-- void setOutputId(java.lang.String) 

    /**
     * Sets the value of field 'recordCountId'.
     * 
     * @param recordCountId the value of field 'recordCountId'.
     */
    public void setRecordCountId(java.lang.String recordCountId)
    {
        this._recordCountId = recordCountId;
    } //-- void setRecordCountId(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return SQLBlockType
     */
    public static com.cdoframework.cdolib.database.dataservice.SQLBlockType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.database.dataservice.SQLBlockType) Unmarshaller.unmarshal(com.cdoframework.cdolib.database.dataservice.SelectRecordSet.class, reader);
    } //-- com.cdoframework.cdolib.database.dataservice.SQLBlockType unmarshal(java.io.Reader) 

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
