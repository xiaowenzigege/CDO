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

import com.cdoframework.cdolib.service.framework.transfilter.schema.types.CacheKeyTypeTypeType;
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
 * Class CacheKeyType.
 * 
 * @version $Revision$ $Date$
 */
public class CacheKeyType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _type
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.types.CacheKeyTypeTypeType _type = com.cdoframework.cdolib.service.framework.transfilter.schema.types.CacheKeyTypeTypeType.valueOf("Json");

    /**
     * internal content storage
     */
    private java.lang.String _content = "";


      //----------------/
     //- Constructors -/
    //----------------/

    public CacheKeyType() 
     {
        super();
        setType(com.cdoframework.cdolib.service.framework.transfilter.schema.types.CacheKeyTypeTypeType.valueOf("Json"));
        setContent("");
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.CacheKeyType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'content'. The field 'content'
     * has the following description: internal content storage
     * 
     * @return String
     * @return the value of field 'content'.
     */
    public java.lang.String getContent()
    {
        return this._content;
    } //-- java.lang.String getContent() 

    /**
     * Returns the value of field 'type'.
     * 
     * @return CacheKeyTypeTypeType
     * @return the value of field 'type'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.types.CacheKeyTypeTypeType getType()
    {
        return this._type;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.types.CacheKeyTypeTypeType getType() 

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
     * Sets the value of field 'content'. The field 'content' has
     * the following description: internal content storage
     * 
     * @param content the value of field 'content'.
     */
    public void setContent(java.lang.String content)
    {
        this._content = content;
    } //-- void setContent(java.lang.String) 

    /**
     * Sets the value of field 'type'.
     * 
     * @param type the value of field 'type'.
     */
    public void setType(com.cdoframework.cdolib.service.framework.transfilter.schema.types.CacheKeyTypeTypeType type)
    {
        this._type = type;
    } //-- void setType(com.cdoframework.cdolib.service.framework.transfilter.schema.types.CacheKeyTypeTypeType) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return CacheKeyType
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.schema.CacheKeyType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.CacheKeyType) Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.schema.CacheKeyType.class, reader);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.CacheKeyType unmarshal(java.io.Reader) 

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
