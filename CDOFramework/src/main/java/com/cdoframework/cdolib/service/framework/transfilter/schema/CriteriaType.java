/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.service.framework.transfilter.schema;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import com.cdoframework.cdolib.service.framework.transfilter.schema.types.CriteriaTypeOperatorType;
import com.cdoframework.cdolib.service.framework.transfilter.schema.types.CriteriaTypeTypeType;
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
 * Class CriteriaType.
 * 
 * @version $Revision$ $Date$
 */
public class CriteriaType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _name
     */
    private java.lang.String _name;

    /**
     * Field _operator
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.types.CriteriaTypeOperatorType _operator;

    /**
     * Field _value
     */
    private java.lang.String _value;

    /**
     * Field _type
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.types.CriteriaTypeTypeType _type;

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

    public CriteriaType() 
     {
        super();
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.CriteriaType()


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
     * Returns the value of field 'name'.
     * 
     * @return String
     * @return the value of field 'name'.
     */
    public java.lang.String getName()
    {
        return this._name;
    } //-- java.lang.String getName() 

    /**
     * Returns the value of field 'operator'.
     * 
     * @return CriteriaTypeOperatorType
     * @return the value of field 'operator'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.types.CriteriaTypeOperatorType getOperator()
    {
        return this._operator;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.types.CriteriaTypeOperatorType getOperator() 

    /**
     * Returns the value of field 'type'.
     * 
     * @return CriteriaTypeTypeType
     * @return the value of field 'type'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.types.CriteriaTypeTypeType getType()
    {
        return this._type;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.types.CriteriaTypeTypeType getType() 

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
     * Sets the value of field 'name'.
     * 
     * @param name the value of field 'name'.
     */
    public void setName(java.lang.String name)
    {
        this._name = name;
    } //-- void setName(java.lang.String) 

    /**
     * Sets the value of field 'operator'.
     * 
     * @param operator the value of field 'operator'.
     */
    public void setOperator(com.cdoframework.cdolib.service.framework.transfilter.schema.types.CriteriaTypeOperatorType operator)
    {
        this._operator = operator;
    } //-- void setOperator(com.cdoframework.cdolib.service.framework.transfilter.schema.types.CriteriaTypeOperatorType) 

    /**
     * Sets the value of field 'type'.
     * 
     * @param type the value of field 'type'.
     */
    public void setType(com.cdoframework.cdolib.service.framework.transfilter.schema.types.CriteriaTypeTypeType type)
    {
        this._type = type;
    } //-- void setType(com.cdoframework.cdolib.service.framework.transfilter.schema.types.CriteriaTypeTypeType) 

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
     * @return CriteriaType
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.schema.CriteriaType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.CriteriaType) Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.schema.CriteriaType.class, reader);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.CriteriaType unmarshal(java.io.Reader) 

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
