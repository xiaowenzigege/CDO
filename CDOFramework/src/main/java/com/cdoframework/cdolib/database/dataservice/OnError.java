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
 * Class OnError.
 * 
 * @version $Revision$ $Date$
 */
public class OnError implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _code
     */
    private int _code;

    /**
     * keeps track of state for field: _code
     */
    private boolean _has_code;

    /**
     * Field _return
     */
    private com.cdoframework.cdolib.database.dataservice.Return _return;


      //----------------/
     //- Constructors -/
    //----------------/

    public OnError() 
     {
        super();
    } //-- com.cdoframework.cdolib.database.dataservice.OnError()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method deleteCode
     * 
     */
    public void deleteCode()
    {
        this._has_code= false;
    } //-- void deleteCode() 

    /**
     * Returns the value of field 'code'.
     * 
     * @return int
     * @return the value of field 'code'.
     */
    public int getCode()
    {
        return this._code;
    } //-- int getCode() 

    /**
     * Returns the value of field 'return'.
     * 
     * @return Return
     * @return the value of field 'return'.
     */
    public com.cdoframework.cdolib.database.dataservice.Return getReturn()
    {
        return this._return;
    } //-- com.cdoframework.cdolib.database.dataservice.Return getReturn() 

    /**
     * Method hasCode
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasCode()
    {
        return this._has_code;
    } //-- boolean hasCode() 

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
     * Sets the value of field 'code'.
     * 
     * @param code the value of field 'code'.
     */
    public void setCode(int code)
    {
        this._code = code;
        this._has_code = true;
    } //-- void setCode(int) 

    /**
     * Sets the value of field 'return'.
     * 
     * @param _return
     * @param return the value of field 'return'.
     */
    public void setReturn(com.cdoframework.cdolib.database.dataservice.Return _return)
    {
        this._return = _return;
    } //-- void setReturn(com.cdoframework.cdolib.database.dataservice.Return) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return OnError
     */
    public static com.cdoframework.cdolib.database.dataservice.OnError unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.database.dataservice.OnError) Unmarshaller.unmarshal(com.cdoframework.cdolib.database.dataservice.OnError.class, reader);
    } //-- com.cdoframework.cdolib.database.dataservice.OnError unmarshal(java.io.Reader) 

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
