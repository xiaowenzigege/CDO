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

import com.cdoframework.cdolib.service.framework.transfilter.schema.types.ReturnCodeOperatorType;
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
 * Class ReturnCode.
 * 
 * @version $Revision$ $Date$
 */
public class ReturnCode implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _operator
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.types.ReturnCodeOperatorType _operator;

    /**
     * Field _value
     */
    private int _value;

    /**
     * keeps track of state for field: _value
     */
    private boolean _has_value;


      //----------------/
     //- Constructors -/
    //----------------/

    public ReturnCode() 
     {
        super();
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.ReturnCode()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method deleteValue
     * 
     */
    public void deleteValue()
    {
        this._has_value= false;
    } //-- void deleteValue() 

    /**
     * Returns the value of field 'operator'.
     * 
     * @return ReturnCodeOperatorType
     * @return the value of field 'operator'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.types.ReturnCodeOperatorType getOperator()
    {
        return this._operator;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.types.ReturnCodeOperatorType getOperator() 

    /**
     * Returns the value of field 'value'.
     * 
     * @return int
     * @return the value of field 'value'.
     */
    public int getValue()
    {
        return this._value;
    } //-- int getValue() 

    /**
     * Method hasValue
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasValue()
    {
        return this._has_value;
    } //-- boolean hasValue() 

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
     * Sets the value of field 'operator'.
     * 
     * @param operator the value of field 'operator'.
     */
    public void setOperator(com.cdoframework.cdolib.service.framework.transfilter.schema.types.ReturnCodeOperatorType operator)
    {
        this._operator = operator;
    } //-- void setOperator(com.cdoframework.cdolib.service.framework.transfilter.schema.types.ReturnCodeOperatorType) 

    /**
     * Sets the value of field 'value'.
     * 
     * @param value the value of field 'value'.
     */
    public void setValue(int value)
    {
        this._value = value;
        this._has_value = true;
    } //-- void setValue(int) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return ReturnCode
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.schema.ReturnCode unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.ReturnCode) Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.schema.ReturnCode.class, reader);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.ReturnCode unmarshal(java.io.Reader) 

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
