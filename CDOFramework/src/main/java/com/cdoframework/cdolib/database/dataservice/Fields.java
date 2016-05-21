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
 * Class Fields.
 * 
 * @version $Revision$ $Date$
 */
public class Fields implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _field
     */
    private com.cdoframework.cdolib.database.dataservice.Field _field;


      //----------------/
     //- Constructors -/
    //----------------/

    public Fields() 
     {
        super();
    } //-- com.cdoframework.cdolib.database.dataservice.Fields()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'field'.
     * 
     * @return Field
     * @return the value of field 'field'.
     */
    public com.cdoframework.cdolib.database.dataservice.Field getField()
    {
        return this._field;
    } //-- com.cdoframework.cdolib.database.dataservice.Field getField() 

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
     * Sets the value of field 'field'.
     * 
     * @param field the value of field 'field'.
     */
    public void setField(com.cdoframework.cdolib.database.dataservice.Field field)
    {
        this._field = field;
    } //-- void setField(com.cdoframework.cdolib.database.dataservice.Field) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Fields
     */
    public static com.cdoframework.cdolib.database.dataservice.Fields unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.database.dataservice.Fields) Unmarshaller.unmarshal(com.cdoframework.cdolib.database.dataservice.Fields.class, reader);
    } //-- com.cdoframework.cdolib.database.dataservice.Fields unmarshal(java.io.Reader) 

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
