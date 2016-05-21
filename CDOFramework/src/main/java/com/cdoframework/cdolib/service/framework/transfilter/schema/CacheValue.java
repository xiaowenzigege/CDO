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

import com.cdoframework.cdolib.data.cdo.CDO;

/**
 * Class CacheValue.
 * 
 * @version $Revision$ $Date$
 */
public class CacheValue implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _fieldId
     */
    private java.lang.String _fieldId;


      //----------------/
     //- Constructors -/
    //----------------/

    public CacheValue() 
     {
        super();
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.CacheValue()


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
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return CacheValue
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.schema.CacheValue unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.CacheValue) Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.schema.CacheValue.class, reader);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.CacheValue unmarshal(java.io.Reader) 

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
    public Object getValue(CDO cdoRequest,CDO cdoResponse)
    {
		String strFieldId = this.getFieldId();
		if(strFieldId==null)
		{
			return cdoResponse.toXML();
		}
		if(cdoResponse.exists(strFieldId))
		{
			return cdoResponse.getField(strFieldId);
		}
		else
		{
			if(cdoRequest.exists(strFieldId))
			{
				return cdoRequest.getField(strFieldId);
			}
		}
		return null;
    }
}
