/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.service.framework.schema;

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
 * Class IdNodeProcessor.
 * 
 * @version $Revision$ $Date$
 */
public class IdNodeProcessor implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _dataGroupId
     */
    private java.lang.String _dataGroupId;


      //----------------/
     //- Constructors -/
    //----------------/

    public IdNodeProcessor() 
     {
        super();
    } //-- com.cdoframework.cdolib.framework.schema.IdNodeProcessor()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'dataGroupId'.
     * 
     * @return String
     * @return the value of field 'dataGroupId'.
     */
    public java.lang.String getDataGroupId()
    {
        return this._dataGroupId;
    } //-- java.lang.String getDataGroupId() 

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
     * Sets the value of field 'dataGroupId'.
     * 
     * @param dataGroupId the value of field 'dataGroupId'.
     */
    public void setDataGroupId(java.lang.String dataGroupId)
    {
        this._dataGroupId = dataGroupId;
    } //-- void setDataGroupId(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return IdNodeProcessor
     */
    public static com.cdoframework.cdolib.service.framework.schema.IdNodeProcessor unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.service.framework.schema.IdNodeProcessor) Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.schema.IdNodeProcessor.class, reader);
    } //-- com.cdoframework.cdolib.framework.schema.IdNodeProcessor unmarshal(java.io.Reader) 

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
