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
 * Class PostLoadCacheSequence.
 * 
 * @version $Revision$ $Date$
 */
public class PostLoadCacheSequence implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _requestCondition
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.RequestCondition _requestCondition;

    /**
     * Field _responseCondition
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.ResponseCondition _responseCondition;


      //----------------/
     //- Constructors -/
    //----------------/

    public PostLoadCacheSequence() 
     {
        super();
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PostLoadCacheSequence()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'requestCondition'.
     * 
     * @return RequestCondition
     * @return the value of field 'requestCondition'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.RequestCondition getRequestCondition()
    {
        return this._requestCondition;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.RequestCondition getRequestCondition() 

    /**
     * Returns the value of field 'responseCondition'.
     * 
     * @return ResponseCondition
     * @return the value of field 'responseCondition'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.ResponseCondition getResponseCondition()
    {
        return this._responseCondition;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.ResponseCondition getResponseCondition() 

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
     * Sets the value of field 'requestCondition'.
     * 
     * @param requestCondition the value of field 'requestCondition'
     */
    public void setRequestCondition(com.cdoframework.cdolib.service.framework.transfilter.schema.RequestCondition requestCondition)
    {
        this._requestCondition = requestCondition;
    } //-- void setRequestCondition(com.cdoframework.cdolib.service.framework.transfilter.schema.RequestCondition) 

    /**
     * Sets the value of field 'responseCondition'.
     * 
     * @param responseCondition the value of field
     * 'responseCondition'.
     */
    public void setResponseCondition(com.cdoframework.cdolib.service.framework.transfilter.schema.ResponseCondition responseCondition)
    {
        this._responseCondition = responseCondition;
    } //-- void setResponseCondition(com.cdoframework.cdolib.service.framework.transfilter.schema.ResponseCondition) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return PostLoadCacheSequence
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.schema.PostLoadCacheSequence unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.PostLoadCacheSequence) Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.schema.PostLoadCacheSequence.class, reader);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PostLoadCacheSequence unmarshal(java.io.Reader) 

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
