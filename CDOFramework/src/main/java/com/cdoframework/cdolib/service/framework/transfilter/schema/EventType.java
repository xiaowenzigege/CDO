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
 * Class EventType.
 * 
 * @version $Revision$ $Date$
 */
public class EventType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _eventName
     */
    private java.lang.String _eventName;


      //----------------/
     //- Constructors -/
    //----------------/

    public EventType() 
     {
        super();
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.EventType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'eventName'.
     * 
     * @return String
     * @return the value of field 'eventName'.
     */
    public java.lang.String getEventName()
    {
        return this._eventName;
    } //-- java.lang.String getEventName() 

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
     * Sets the value of field 'eventName'.
     * 
     * @param eventName the value of field 'eventName'.
     */
    public void setEventName(java.lang.String eventName)
    {
        this._eventName = eventName;
    } //-- void setEventName(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return EventType
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.schema.EventType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.EventType) Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.schema.EventType.class, reader);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.EventType unmarshal(java.io.Reader) 

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
