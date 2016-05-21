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
 * Class OrderType.
 * 
 * @version $Revision$ $Date$
 */
public class OrderType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _fieldName
     */
    private java.lang.String _fieldName;

    /**
     * Field _direction
     */
    private java.lang.String _direction = "1";


      //----------------/
     //- Constructors -/
    //----------------/

    public OrderType() 
     {
        super();
        setDirection("1");
    } //-- com.cdoframework.cdolib.database.dataservice.OrderType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'direction'.
     * 
     * @return String
     * @return the value of field 'direction'.
     */
    public java.lang.String getDirection()
    {
        return this._direction;
    } //-- java.lang.String getDirection() 

    /**
     * Returns the value of field 'fieldName'.
     * 
     * @return String
     * @return the value of field 'fieldName'.
     */
    public java.lang.String getFieldName()
    {
        return this._fieldName;
    } //-- java.lang.String getFieldName() 

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
     * Sets the value of field 'direction'.
     * 
     * @param direction the value of field 'direction'.
     */
    public void setDirection(java.lang.String direction)
    {
        this._direction = direction;
    } //-- void setDirection(java.lang.String) 

    /**
     * Sets the value of field 'fieldName'.
     * 
     * @param fieldName the value of field 'fieldName'.
     */
    public void setFieldName(java.lang.String fieldName)
    {
        this._fieldName = fieldName;
    } //-- void setFieldName(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return OrderType
     */
    public static com.cdoframework.cdolib.database.dataservice.OrderType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.database.dataservice.OrderType) Unmarshaller.unmarshal(com.cdoframework.cdolib.database.dataservice.OrderType.class, reader);
    } //-- com.cdoframework.cdolib.database.dataservice.OrderType unmarshal(java.io.Reader) 

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
