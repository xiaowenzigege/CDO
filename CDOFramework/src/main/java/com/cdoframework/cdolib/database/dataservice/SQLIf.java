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

import com.cdoframework.cdolib.database.dataservice.types.SQLIfOperatorType;
import com.cdoframework.cdolib.database.dataservice.types.SQLIfTypeType;
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
 * Class SQLIf.
 * 
 * @version $Revision$ $Date$
 */
public class SQLIf implements java.io.Serializable {


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
    private com.cdoframework.cdolib.database.dataservice.types.SQLIfOperatorType _operator;

    /**
     * Field _value2
     */
    private java.lang.String _value2;

    /**
     * Field _type
     */
    private com.cdoframework.cdolib.database.dataservice.types.SQLIfTypeType _type;

    /**
     * Field _SQLThen
     */
    private com.cdoframework.cdolib.database.dataservice.SQLThen _SQLThen;

    /**
     * Field _SQLElse
     */
    private com.cdoframework.cdolib.database.dataservice.SQLElse _SQLElse;


      //----------------/
     //- Constructors -/
    //----------------/

    public SQLIf() 
     {
        super();
    } //-- com.cdoframework.cdolib.database.dataservice.SQLIf()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'operator'.
     * 
     * @return SQLIfOperatorType
     * @return the value of field 'operator'.
     */
    public com.cdoframework.cdolib.database.dataservice.types.SQLIfOperatorType getOperator()
    {
        return this._operator;
    } //-- com.cdoframework.cdolib.database.dataservice.types.SQLIfOperatorType getOperator() 

    /**
     * Returns the value of field 'SQLElse'.
     * 
     * @return SQLElse
     * @return the value of field 'SQLElse'.
     */
    public com.cdoframework.cdolib.database.dataservice.SQLElse getSQLElse()
    {
        return this._SQLElse;
    } //-- com.cdoframework.cdolib.database.dataservice.SQLElse getSQLElse() 

    /**
     * Returns the value of field 'SQLThen'.
     * 
     * @return SQLThen
     * @return the value of field 'SQLThen'.
     */
    public com.cdoframework.cdolib.database.dataservice.SQLThen getSQLThen()
    {
        return this._SQLThen;
    } //-- com.cdoframework.cdolib.database.dataservice.SQLThen getSQLThen() 

    /**
     * Returns the value of field 'type'.
     * 
     * @return SQLIfTypeType
     * @return the value of field 'type'.
     */
    public com.cdoframework.cdolib.database.dataservice.types.SQLIfTypeType getType()
    {
        return this._type;
    } //-- com.cdoframework.cdolib.database.dataservice.types.SQLIfTypeType getType() 

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
     * Sets the value of field 'operator'.
     * 
     * @param operator the value of field 'operator'.
     */
    public void setOperator(com.cdoframework.cdolib.database.dataservice.types.SQLIfOperatorType operator)
    {
        this._operator = operator;
    } //-- void setOperator(com.cdoframework.cdolib.database.dataservice.types.SQLIfOperatorType) 

    /**
     * Sets the value of field 'SQLElse'.
     * 
     * @param SQLElse the value of field 'SQLElse'.
     */
    public void setSQLElse(com.cdoframework.cdolib.database.dataservice.SQLElse SQLElse)
    {
        this._SQLElse = SQLElse;
    } //-- void setSQLElse(com.cdoframework.cdolib.database.dataservice.SQLElse) 

    /**
     * Sets the value of field 'SQLThen'.
     * 
     * @param SQLThen the value of field 'SQLThen'.
     */
    public void setSQLThen(com.cdoframework.cdolib.database.dataservice.SQLThen SQLThen)
    {
        this._SQLThen = SQLThen;
    } //-- void setSQLThen(com.cdoframework.cdolib.database.dataservice.SQLThen) 

    /**
     * Sets the value of field 'type'.
     * 
     * @param type the value of field 'type'.
     */
    public void setType(com.cdoframework.cdolib.database.dataservice.types.SQLIfTypeType type)
    {
        this._type = type;
    } //-- void setType(com.cdoframework.cdolib.database.dataservice.types.SQLIfTypeType) 

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
     * @return SQLIf
     */
    public static com.cdoframework.cdolib.database.dataservice.SQLIf unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.database.dataservice.SQLIf) Unmarshaller.unmarshal(com.cdoframework.cdolib.database.dataservice.SQLIf.class, reader);
    } //-- com.cdoframework.cdolib.database.dataservice.SQLIf unmarshal(java.io.Reader) 

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
