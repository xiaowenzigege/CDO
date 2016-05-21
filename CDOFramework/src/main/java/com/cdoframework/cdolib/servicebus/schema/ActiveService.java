/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.servicebus.schema;

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
 * Class ActiveService.
 * 
 * @version $Revision$ $Date$
 */
public class ActiveService implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _id
     */
    private java.lang.String _id;

    /**
     * Field _classPath
     */
    private java.lang.String _classPath;

    /**
     * Field _clustered
     */
    private boolean _clustered;

    /**
     * keeps track of state for field: _clustered
     */
    private boolean _has_clustered;


      //----------------/
     //- Constructors -/
    //----------------/

    public ActiveService() 
     {
        super();
    } //-- com.cdoframework.cdolib.servicebus.schema.ActiveService()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method deleteClustered
     * 
     */
    public void deleteClustered()
    {
        this._has_clustered= false;
    } //-- void deleteClustered() 

    /**
     * Returns the value of field 'classPath'.
     * 
     * @return String
     * @return the value of field 'classPath'.
     */
    public java.lang.String getClassPath()
    {
        return this._classPath;
    } //-- java.lang.String getClassPath() 

    /**
     * Returns the value of field 'clustered'.
     * 
     * @return boolean
     * @return the value of field 'clustered'.
     */
    public boolean getClustered()
    {
        return this._clustered;
    } //-- boolean getClustered() 

    /**
     * Returns the value of field 'id'.
     * 
     * @return String
     * @return the value of field 'id'.
     */
    public java.lang.String getId()
    {
        return this._id;
    } //-- java.lang.String getId() 

    /**
     * Method hasClustered
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasClustered()
    {
        return this._has_clustered;
    } //-- boolean hasClustered() 

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
     * Sets the value of field 'classPath'.
     * 
     * @param classPath the value of field 'classPath'.
     */
    public void setClassPath(java.lang.String classPath)
    {
        this._classPath = classPath;
    } //-- void setClassPath(java.lang.String) 

    /**
     * Sets the value of field 'clustered'.
     * 
     * @param clustered the value of field 'clustered'.
     */
    public void setClustered(boolean clustered)
    {
        this._clustered = clustered;
        this._has_clustered = true;
    } //-- void setClustered(boolean) 

    /**
     * Sets the value of field 'id'.
     * 
     * @param id the value of field 'id'.
     */
    public void setId(java.lang.String id)
    {
        this._id = id;
    } //-- void setId(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return ActiveService
     */
    public static com.cdoframework.cdolib.servicebus.schema.ActiveService unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.servicebus.schema.ActiveService) Unmarshaller.unmarshal(com.cdoframework.cdolib.servicebus.schema.ActiveService.class, reader);
    } //-- com.cdoframework.cdolib.servicebus.schema.ActiveService unmarshal(java.io.Reader) 

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
