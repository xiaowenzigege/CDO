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

import com.cdoframework.cdolib.service.framework.transfilter.schema.types.RequestKeyScopeType;
import com.cdoframework.cdolib.service.framework.transfilter.schema.types.RequestKeyTypeType;
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
 * Class RequestKey.
 * 
 * @version $Revision$ $Date$
 */
public class RequestKey implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _scope
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.types.RequestKeyScopeType _scope = com.cdoframework.cdolib.service.framework.transfilter.schema.types.RequestKeyScopeType.valueOf("Request");

    /**
     * Field _fieldId
     */
    private java.lang.String _fieldId;

    /**
     * Field _valueId
     */
    private java.lang.String _valueId;

    /**
     * Field _value
     */
    private java.lang.String _value;

    /**
     * Field _type
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.types.RequestKeyTypeType _type;


      //----------------/
     //- Constructors -/
    //----------------/

    public RequestKey() 
     {
        super();
        setScope(com.cdoframework.cdolib.service.framework.transfilter.schema.types.RequestKeyScopeType.valueOf("Request"));
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.RequestKey()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'fieldId'.
     * 
     * @return String
     * @return the value of field 'fieldId'.
     */
    public java.lang.String getFieldId()
    {
        return this._fieldId;
    } //-- java.lang.String getFieldId() 

    /**
     * Returns the value of field 'scope'.
     * 
     * @return RequestKeyScopeType
     * @return the value of field 'scope'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.types.RequestKeyScopeType getScope()
    {
        return this._scope;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.types.RequestKeyScopeType getScope() 

    /**
     * Returns the value of field 'type'.
     * 
     * @return RequestKeyTypeType
     * @return the value of field 'type'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.types.RequestKeyTypeType getType()
    {
        return this._type;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.types.RequestKeyTypeType getType() 

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
     * Returns the value of field 'valueId'.
     * 
     * @return String
     * @return the value of field 'valueId'.
     */
    public java.lang.String getValueId()
    {
        return this._valueId;
    } //-- java.lang.String getValueId() 

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
     * Sets the value of field 'fieldId'.
     * 
     * @param fieldId the value of field 'fieldId'.
     */
    public void setFieldId(java.lang.String fieldId)
    {
        this._fieldId = fieldId;
    } //-- void setFieldId(java.lang.String) 

    /**
     * Sets the value of field 'scope'.
     * 
     * @param scope the value of field 'scope'.
     */
    public void setScope(com.cdoframework.cdolib.service.framework.transfilter.schema.types.RequestKeyScopeType scope)
    {
        this._scope = scope;
    } //-- void setScope(com.cdoframework.cdolib.service.framework.transfilter.schema.types.RequestKeyScopeType) 

    /**
     * Sets the value of field 'type'.
     * 
     * @param type the value of field 'type'.
     */
    public void setType(com.cdoframework.cdolib.service.framework.transfilter.schema.types.RequestKeyTypeType type)
    {
        this._type = type;
    } //-- void setType(com.cdoframework.cdolib.service.framework.transfilter.schema.types.RequestKeyTypeType) 

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
     * Sets the value of field 'valueId'.
     * 
     * @param valueId the value of field 'valueId'.
     */
    public void setValueId(java.lang.String valueId)
    {
        this._valueId = valueId;
    } //-- void setValueId(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return RequestKey
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.schema.RequestKey unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.RequestKey) Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.schema.RequestKey.class, reader);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.RequestKey unmarshal(java.io.Reader) 

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
