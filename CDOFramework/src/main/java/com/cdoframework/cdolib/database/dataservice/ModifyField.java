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

import com.cdoframework.cdolib.database.dataservice.types.ModifyFieldMethodType;
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
 * Class ModifyField.
 * 
 * @version $Revision$ $Date$
 */
public class ModifyField extends com.cdoframework.cdolib.database.dataservice.FieldType 
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
     * Field _method
     */
    private com.cdoframework.cdolib.database.dataservice.types.ModifyFieldMethodType _method;

    /**
     * Field _valueRequired
     */
    private boolean _valueRequired = false;

    /**
     * keeps track of state for field: _valueRequired
     */
    private boolean _has_valueRequired;


      //----------------/
     //- Constructors -/
    //----------------/

    public ModifyField() 
     {
        super();
    } //-- com.cdoframework.cdolib.database.dataservice.ModifyField()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method deleteValueRequired
     * 
     */
    public void deleteValueRequired()
    {
        this._has_valueRequired= false;
    } //-- void deleteValueRequired() 

    /**
     * Returns the value of field 'method'.
     * 
     * @return ModifyFieldMethodType
     * @return the value of field 'method'.
     */
    public com.cdoframework.cdolib.database.dataservice.types.ModifyFieldMethodType getMethod()
    {
        return this._method;
    } //-- com.cdoframework.cdolib.database.dataservice.types.ModifyFieldMethodType getMethod() 

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
     * Returns the value of field 'valueRequired'.
     * 
     * @return boolean
     * @return the value of field 'valueRequired'.
     */
    public boolean getValueRequired()
    {
        return this._valueRequired;
    } //-- boolean getValueRequired() 

    /**
     * Method hasValueRequired
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasValueRequired()
    {
        return this._has_valueRequired;
    } //-- boolean hasValueRequired() 

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
     * Sets the value of field 'method'.
     * 
     * @param method the value of field 'method'.
     */
    public void setMethod(com.cdoframework.cdolib.database.dataservice.types.ModifyFieldMethodType method)
    {
        this._method = method;
    } //-- void setMethod(com.cdoframework.cdolib.database.dataservice.types.ModifyFieldMethodType) 

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
     * Sets the value of field 'valueRequired'.
     * 
     * @param valueRequired the value of field 'valueRequired'.
     */
    public void setValueRequired(boolean valueRequired)
    {
        this._valueRequired = valueRequired;
        this._has_valueRequired = true;
    } //-- void setValueRequired(boolean) 

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
        return (com.cdoframework.cdolib.database.dataservice.FieldType) Unmarshaller.unmarshal(com.cdoframework.cdolib.database.dataservice.ModifyField.class, reader);
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
