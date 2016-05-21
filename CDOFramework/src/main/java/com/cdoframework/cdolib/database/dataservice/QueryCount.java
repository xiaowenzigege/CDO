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
 * Class QueryCount.
 * 
 * @version $Revision$ $Date$
 */
public class QueryCount extends com.cdoframework.cdolib.database.dataservice.CollectionNameType 
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
     * Field _creterias
     */
    private com.cdoframework.cdolib.database.dataservice.Creterias _creterias;


      //----------------/
     //- Constructors -/
    //----------------/

    public QueryCount() 
     {
        super();
    } //-- com.cdoframework.cdolib.database.dataservice.QueryCount()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'creterias'.
     * 
     * @return Creterias
     * @return the value of field 'creterias'.
     */
    public com.cdoframework.cdolib.database.dataservice.Creterias getCreterias()
    {
        return this._creterias;
    } //-- com.cdoframework.cdolib.database.dataservice.Creterias getCreterias() 

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
     * Sets the value of field 'creterias'.
     * 
     * @param creterias the value of field 'creterias'.
     */
    public void setCreterias(com.cdoframework.cdolib.database.dataservice.Creterias creterias)
    {
        this._creterias = creterias;
    } //-- void setCreterias(com.cdoframework.cdolib.database.dataservice.Creterias) 

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
        return (com.cdoframework.cdolib.database.dataservice.CollectionNameType) Unmarshaller.unmarshal(com.cdoframework.cdolib.database.dataservice.QueryCount.class, reader);
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
