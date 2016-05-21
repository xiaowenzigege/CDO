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

import com.cdoframework.cdolib.database.dataservice.types.IfOperatorType;
import com.cdoframework.cdolib.database.dataservice.types.IfTypeType;
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
 * Class If.
 * 
 * @version $Revision$ $Date$
 */
public class If implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _value1
     */
    private java.lang.String _value1;

    /**
     * Field _operator
     */
    private com.cdoframework.cdolib.database.dataservice.types.IfOperatorType _operator;

    /**
     * Field _value2
     */
    private java.lang.String _value2 = "";

    /**
     * Field _type
     */
    private com.cdoframework.cdolib.database.dataservice.types.IfTypeType _type;

    /**
     * Field _then
     */
    private com.cdoframework.cdolib.database.dataservice.Then _then;

    /**
     * Field _else
     */
    private com.cdoframework.cdolib.database.dataservice.Else _else;


      //----------------/
     //- Constructors -/
    //----------------/

    public If() 
     {
        super();
        setValue2("");
    } //-- com.cdoframework.cdolib.database.dataservice.If()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'else'.
     * 
     * @return Else
     * @return the value of field 'else'.
     */
    public com.cdoframework.cdolib.database.dataservice.Else getElse()
    {
        return this._else;
    } //-- com.cdoframework.cdolib.database.dataservice.Else getElse() 

    /**
     * Returns the value of field 'operator'.
     * 
     * @return IfOperatorType
     * @return the value of field 'operator'.
     */
    public com.cdoframework.cdolib.database.dataservice.types.IfOperatorType getOperator()
    {
        return this._operator;
    } //-- com.cdoframework.cdolib.database.dataservice.types.IfOperatorType getOperator() 

    /**
     * Returns the value of field 'then'.
     * 
     * @return Then
     * @return the value of field 'then'.
     */
    public com.cdoframework.cdolib.database.dataservice.Then getThen()
    {
        return this._then;
    } //-- com.cdoframework.cdolib.database.dataservice.Then getThen() 

    /**
     * Returns the value of field 'type'.
     * 
     * @return IfTypeType
     * @return the value of field 'type'.
     */
    public com.cdoframework.cdolib.database.dataservice.types.IfTypeType getType()
    {
        return this._type;
    } //-- com.cdoframework.cdolib.database.dataservice.types.IfTypeType getType() 

    /**
     * Returns the value of field 'value1'.
     * 
     * @return String
     * @return the value of field 'value1'.
     */
    public java.lang.String getValue1()
    {
        return this._value1;
    } //-- java.lang.String getValue1() 

    /**
     * Returns the value of field 'value2'.
     * 
     * @return String
     * @return the value of field 'value2'.
     */
    public java.lang.String getValue2()
    {
        return this._value2;
    } //-- java.lang.String getValue2() 

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
     * Sets the value of field 'else'.
     * 
     * @param _else
     * @param else the value of field 'else'.
     */
    public void setElse(com.cdoframework.cdolib.database.dataservice.Else _else)
    {
        this._else = _else;
    } //-- void setElse(com.cdoframework.cdolib.database.dataservice.Else) 

    /**
     * Sets the value of field 'operator'.
     * 
     * @param operator the value of field 'operator'.
     */
    public void setOperator(com.cdoframework.cdolib.database.dataservice.types.IfOperatorType operator)
    {
        this._operator = operator;
    } //-- void setOperator(com.cdoframework.cdolib.database.dataservice.types.IfOperatorType) 

    /**
     * Sets the value of field 'then'.
     * 
     * @param then the value of field 'then'.
     */
    public void setThen(com.cdoframework.cdolib.database.dataservice.Then then)
    {
        this._then = then;
    } //-- void setThen(com.cdoframework.cdolib.database.dataservice.Then) 

    /**
     * Sets the value of field 'type'.
     * 
     * @param type the value of field 'type'.
     */
    public void setType(com.cdoframework.cdolib.database.dataservice.types.IfTypeType type)
    {
        this._type = type;
    } //-- void setType(com.cdoframework.cdolib.database.dataservice.types.IfTypeType) 

    /**
     * Sets the value of field 'value1'.
     * 
     * @param value1 the value of field 'value1'.
     */
    public void setValue1(java.lang.String value1)
    {
        this._value1 = value1;
    } //-- void setValue1(java.lang.String) 

    /**
     * Sets the value of field 'value2'.
     * 
     * @param value2 the value of field 'value2'.
     */
    public void setValue2(java.lang.String value2)
    {
        this._value2 = value2;
    } //-- void setValue2(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return If
     */
    public static com.cdoframework.cdolib.database.dataservice.If unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.database.dataservice.If) Unmarshaller.unmarshal(com.cdoframework.cdolib.database.dataservice.If.class, reader);
    } //-- com.cdoframework.cdolib.database.dataservice.If unmarshal(java.io.Reader) 

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
