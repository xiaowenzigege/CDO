/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.service.framework.schema;

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
 * Class Server.
 * 
 * @version $Revision$ $Date$
 */
public class Server implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _IP
     */
    private java.lang.String _IP;

    /**
     * Field _port
     */
    private int _port;

    /**
     * keeps track of state for field: _port
     */
    private boolean _has_port;


      //----------------/
     //- Constructors -/
    //----------------/

    public Server() 
     {
        super();
    } //-- com.cdoframework.cdolib.service.framework.schema.Server()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method deletePort
     * 
     */
    public void deletePort()
    {
        this._has_port= false;
    } //-- void deletePort() 

    /**
     * Returns the value of field 'IP'.
     * 
     * @return String
     * @return the value of field 'IP'.
     */
    public java.lang.String getIP()
    {
        return this._IP;
    } //-- java.lang.String getIP() 

    /**
     * Returns the value of field 'port'.
     * 
     * @return int
     * @return the value of field 'port'.
     */
    public int getPort()
    {
        return this._port;
    } //-- int getPort() 

    /**
     * Method hasPort
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasPort()
    {
        return this._has_port;
    } //-- boolean hasPort() 

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
     * Sets the value of field 'IP'.
     * 
     * @param IP the value of field 'IP'.
     */
    public void setIP(java.lang.String IP)
    {
        this._IP = IP;
    } //-- void setIP(java.lang.String) 

    /**
     * Sets the value of field 'port'.
     * 
     * @param port the value of field 'port'.
     */
    public void setPort(int port)
    {
        this._port = port;
        this._has_port = true;
    } //-- void setPort(int) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Server
     */
    public static com.cdoframework.cdolib.service.framework.schema.Server unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.service.framework.schema.Server) Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.schema.Server.class, reader);
    } //-- com.cdoframework.cdolib.service.framework.schema.Server unmarshal(java.io.Reader) 

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
