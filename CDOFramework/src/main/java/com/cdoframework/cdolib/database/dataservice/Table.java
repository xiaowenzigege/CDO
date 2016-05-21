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
 * Class Table.
 * 
 * @version $Revision$ $Date$
 */
public class Table implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _dataGroupId
     */
    private java.lang.String _dataGroupId;

    /**
     * Field _fromId
     */
    private long _fromId;

    /**
     * keeps track of state for field: _fromId
     */
    private boolean _has_fromId;

    /**
     * Field _toId
     */
    private long _toId;

    /**
     * keeps track of state for field: _toId
     */
    private boolean _has_toId;


      //----------------/
     //- Constructors -/
    //----------------/

    public Table() 
     {
        super();
    } //-- com.cdoframework.cdolib.database.dataservice.Table()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method deleteFromId
     * 
     */
    public void deleteFromId()
    {
        this._has_fromId= false;
    } //-- void deleteFromId() 

    /**
     * Method deleteToId
     * 
     */
    public void deleteToId()
    {
        this._has_toId= false;
    } //-- void deleteToId() 

    /**
     * Returns the value of field 'dataGroupId'.
     * 
     * @return String
     * @return the value of field 'dataGroupId'.
     */
    public java.lang.String getDataGroupId()
    {
        return this._dataGroupId;
    } //-- java.lang.String getDataGroupId() 

    /**
     * Returns the value of field 'fromId'.
     * 
     * @return long
     * @return the value of field 'fromId'.
     */
    public long getFromId()
    {
        return this._fromId;
    } //-- long getFromId() 

    /**
     * Returns the value of field 'toId'.
     * 
     * @return long
     * @return the value of field 'toId'.
     */
    public long getToId()
    {
        return this._toId;
    } //-- long getToId() 

    /**
     * Method hasFromId
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasFromId()
    {
        return this._has_fromId;
    } //-- boolean hasFromId() 

    /**
     * Method hasToId
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasToId()
    {
        return this._has_toId;
    } //-- boolean hasToId() 

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
     * Sets the value of field 'dataGroupId'.
     * 
     * @param dataGroupId the value of field 'dataGroupId'.
     */
    public void setDataGroupId(java.lang.String dataGroupId)
    {
        this._dataGroupId = dataGroupId;
    } //-- void setDataGroupId(java.lang.String) 

    /**
     * Sets the value of field 'fromId'.
     * 
     * @param fromId the value of field 'fromId'.
     */
    public void setFromId(long fromId)
    {
        this._fromId = fromId;
        this._has_fromId = true;
    } //-- void setFromId(long) 

    /**
     * Sets the value of field 'toId'.
     * 
     * @param toId the value of field 'toId'.
     */
    public void setToId(long toId)
    {
        this._toId = toId;
        this._has_toId = true;
    } //-- void setToId(long) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Table
     */
    public static com.cdoframework.cdolib.database.dataservice.Table unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.database.dataservice.Table) Unmarshaller.unmarshal(com.cdoframework.cdolib.database.dataservice.Table.class, reader);
    } //-- com.cdoframework.cdolib.database.dataservice.Table unmarshal(java.io.Reader) 

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
