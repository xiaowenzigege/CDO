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
 * Class QField.
 * 
 * @version $Revision$ $Date$
 */
public class QField extends com.cdoframework.cdolib.database.dataservice.FieldType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _value
     */
    private java.lang.String _value;

    /**
     * Field _alias
     */
    private java.lang.String _alias;

    /**
     * Field _function
     */
    private java.lang.String _function;


      //----------------/
     //- Constructors -/
    //----------------/

    public QField() 
     {
        super();
    } //-- com.cdoframework.cdolib.database.dataservice.QField()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'alias'.
     * 
     * @return String
     * @return the value of field 'alias'.
     */
    public java.lang.String getAlias()
    {
        return this._alias;
    } //-- java.lang.String getAlias() 

    /**
     * Returns the value of field 'function'.
     * 
     * @return String
     * @return the value of field 'function'.
     */
    public java.lang.String getFunction()
    {
        return this._function;
    } //-- java.lang.String getFunction() 

    /**
     * Returns the value of field 'value'.
     * 
     * @return String
     * @return the value of field 'value'.
     */
    public java.lang.String getValue()
    {
        return this._value;
    } //-- java.lang.String getValue() 

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
     * Sets the value of field 'alias'.
     * 
     * @param alias the value of field 'alias'.
     */
    public void setAlias(java.lang.String alias)
    {
        this._alias = alias;
    } //-- void setAlias(java.lang.String) 

    /**
     * Sets the value of field 'function'.
     * 
     * @param function the value of field 'function'.
     */
    public void setFunction(java.lang.String function)
    {
        this._function = function;
    } //-- void setFunction(java.lang.String) 

    /**
     * Sets the value of field 'value'.
     * 
     * @param value the value of field 'value'.
     */
    public void setValue(java.lang.String value)
    {
        this._value = value;
    } //-- void setValue(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return FieldType
     */
    public static com.cdoframework.cdolib.database.dataservice.FieldType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.database.dataservice.FieldType) Unmarshaller.unmarshal(com.cdoframework.cdolib.database.dataservice.QField.class, reader);
    } //-- com.cdoframework.cdolib.database.dataservice.FieldType unmarshal(java.io.Reader) 

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
