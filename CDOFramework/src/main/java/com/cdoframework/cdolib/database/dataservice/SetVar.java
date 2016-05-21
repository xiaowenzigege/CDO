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

import com.cdoframework.cdolib.database.dataservice.types.SetVarTypeType;
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
 * Class SetVar.
 * 
 * @version $Revision$ $Date$
 */
public class SetVar implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _type
     */
    private com.cdoframework.cdolib.database.dataservice.types.SetVarTypeType _type;

    /**
     * Field _varId
     */
    private java.lang.String _varId;

    /**
     * Field _value
     */
    private java.lang.String _value;


      //----------------/
     //- Constructors -/
    //----------------/

    public SetVar() 
     {
        super();
    } //-- com.cdoframework.cdolib.database.dataservice.SetVar()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'type'.
     * 
     * @return SetVarTypeType
     * @return the value of field 'type'.
     */
    public com.cdoframework.cdolib.database.dataservice.types.SetVarTypeType getType()
    {
        return this._type;
    } //-- com.cdoframework.cdolib.database.dataservice.types.SetVarTypeType getType() 

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
     * Returns the value of field 'varId'.
     * 
     * @return String
     * @return the value of field 'varId'.
     */
    public java.lang.String getVarId()
    {
        return this._varId;
    } //-- java.lang.String getVarId() 

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
     * Sets the value of field 'type'.
     * 
     * @param type the value of field 'type'.
     */
    public void setType(com.cdoframework.cdolib.database.dataservice.types.SetVarTypeType type)
    {
        this._type = type;
    } //-- void setType(com.cdoframework.cdolib.database.dataservice.types.SetVarTypeType) 

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
     * Sets the value of field 'varId'.
     * 
     * @param varId the value of field 'varId'.
     */
    public void setVarId(java.lang.String varId)
    {
        this._varId = varId;
    } //-- void setVarId(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return SetVar
     */
    public static com.cdoframework.cdolib.database.dataservice.SetVar unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.database.dataservice.SetVar) Unmarshaller.unmarshal(com.cdoframework.cdolib.database.dataservice.SetVar.class, reader);
    } //-- com.cdoframework.cdolib.database.dataservice.SetVar unmarshal(java.io.Reader) 

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
