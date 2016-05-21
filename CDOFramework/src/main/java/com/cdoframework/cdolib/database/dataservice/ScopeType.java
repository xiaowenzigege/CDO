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
 * Class ScopeType.
 * 
 * @version $Revision$ $Date$
 */
public class ScopeType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _skip
     */
    private java.lang.String _skip;

    /**
     * Field _limit
     */
    private java.lang.String _limit;


      //----------------/
     //- Constructors -/
    //----------------/

    public ScopeType() 
     {
        super();
    } //-- com.cdoframework.cdolib.database.dataservice.ScopeType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'limit'.
     * 
     * @return String
     * @return the value of field 'limit'.
     */
    public java.lang.String getLimit()
    {
        return this._limit;
    } //-- java.lang.String getLimit() 

    /**
     * Returns the value of field 'skip'.
     * 
     * @return String
     * @return the value of field 'skip'.
     */
    public java.lang.String getSkip()
    {
        return this._skip;
    } //-- java.lang.String getSkip() 

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
     * Sets the value of field 'limit'.
     * 
     * @param limit the value of field 'limit'.
     */
    public void setLimit(java.lang.String limit)
    {
        this._limit = limit;
    } //-- void setLimit(java.lang.String) 

    /**
     * Sets the value of field 'skip'.
     * 
     * @param skip the value of field 'skip'.
     */
    public void setSkip(java.lang.String skip)
    {
        this._skip = skip;
    } //-- void setSkip(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return ScopeType
     */
    public static com.cdoframework.cdolib.database.dataservice.ScopeType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.database.dataservice.ScopeType) Unmarshaller.unmarshal(com.cdoframework.cdolib.database.dataservice.ScopeType.class, reader);
    } //-- com.cdoframework.cdolib.database.dataservice.ScopeType unmarshal(java.io.Reader) 

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
