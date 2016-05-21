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
 * Class ReturnItem.
 * 
 * @version $Revision$ $Date$
 */
public class ReturnItem implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _fieldId
     */
    private java.lang.String _fieldId;

    /**
     * Field _valueId
     */
    private java.lang.String _valueId;


      //----------------/
     //- Constructors -/
    //----------------/

    public ReturnItem() 
     {
        super();
    } //-- com.cdoframework.cdolib.database.dataservice.ReturnItem()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'fieldId'.
     * 
     * @return String
     * @return the value of field 'fieldId'.
     */
    public java.lang.String getFieldId()
    {
        return this._fieldId;
    } //-- java.lang.String getFieldId() 

    /**
     * Returns the value of field 'valueId'.
     * 
     * @return String
     * @return the value of field 'valueId'.
     */
    public java.lang.String getValueId()
    {
        return this._valueId;
    } //-- java.lang.String getValueId() 

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
     * Sets the value of field 'fieldId'.
     * 
     * @param fieldId the value of field 'fieldId'.
     */
    public void setFieldId(java.lang.String fieldId)
    {
        this._fieldId = fieldId;
    } //-- void setFieldId(java.lang.String) 

    /**
     * Sets the value of field 'valueId'.
     * 
     * @param valueId the value of field 'valueId'.
     */
    public void setValueId(java.lang.String valueId)
    {
        this._valueId = valueId;
    } //-- void setValueId(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return ReturnItem
     */
    public static com.cdoframework.cdolib.database.dataservice.ReturnItem unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.database.dataservice.ReturnItem) Unmarshaller.unmarshal(com.cdoframework.cdolib.database.dataservice.ReturnItem.class, reader);
    } //-- com.cdoframework.cdolib.database.dataservice.ReturnItem unmarshal(java.io.Reader) 

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
