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
 * Class BigTable.
 * 
 * @version $Revision$ $Date$
 */
public class BigTable implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _name
     */
    private java.lang.String _name;

    /**
     * Field _groupId
     */
    private java.lang.String _groupId;

    /**
     * Field _groupCount
     */
    private int _groupCount;

    /**
     * keeps track of state for field: _groupCount
     */
    private boolean _has_groupCount;

    /**
     * Field _groupTableCount
     */
    private int _groupTableCount;

    /**
     * keeps track of state for field: _groupTableCount
     */
    private boolean _has_groupTableCount;

    /**
     * Field _groupTableCapacity
     */
    private long _groupTableCapacity;

    /**
     * keeps track of state for field: _groupTableCapacity
     */
    private boolean _has_groupTableCapacity;


      //----------------/
     //- Constructors -/
    //----------------/

    public BigTable() 
     {
        super();
    } //-- com.cdoframework.cdolib.database.dataservice.BigTable()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method deleteGroupCount
     * 
     */
    public void deleteGroupCount()
    {
        this._has_groupCount= false;
    } //-- void deleteGroupCount() 

    /**
     * Method deleteGroupTableCapacity
     * 
     */
    public void deleteGroupTableCapacity()
    {
        this._has_groupTableCapacity= false;
    } //-- void deleteGroupTableCapacity() 

    /**
     * Method deleteGroupTableCount
     * 
     */
    public void deleteGroupTableCount()
    {
        this._has_groupTableCount= false;
    } //-- void deleteGroupTableCount() 

    /**
     * Returns the value of field 'groupCount'.
     * 
     * @return int
     * @return the value of field 'groupCount'.
     */
    public int getGroupCount()
    {
        return this._groupCount;
    } //-- int getGroupCount() 

    /**
     * Returns the value of field 'groupId'.
     * 
     * @return String
     * @return the value of field 'groupId'.
     */
    public java.lang.String getGroupId()
    {
        return this._groupId;
    } //-- java.lang.String getGroupId() 

    /**
     * Returns the value of field 'groupTableCapacity'.
     * 
     * @return long
     * @return the value of field 'groupTableCapacity'.
     */
    public long getGroupTableCapacity()
    {
        return this._groupTableCapacity;
    } //-- long getGroupTableCapacity() 

    /**
     * Returns the value of field 'groupTableCount'.
     * 
     * @return int
     * @return the value of field 'groupTableCount'.
     */
    public int getGroupTableCount()
    {
        return this._groupTableCount;
    } //-- int getGroupTableCount() 

    /**
     * Returns the value of field 'name'.
     * 
     * @return String
     * @return the value of field 'name'.
     */
    public java.lang.String getName()
    {
        return this._name;
    } //-- java.lang.String getName() 

    /**
     * Method hasGroupCount
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasGroupCount()
    {
        return this._has_groupCount;
    } //-- boolean hasGroupCount() 

    /**
     * Method hasGroupTableCapacity
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasGroupTableCapacity()
    {
        return this._has_groupTableCapacity;
    } //-- boolean hasGroupTableCapacity() 

    /**
     * Method hasGroupTableCount
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasGroupTableCount()
    {
        return this._has_groupTableCount;
    } //-- boolean hasGroupTableCount() 

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
     * Sets the value of field 'groupCount'.
     * 
     * @param groupCount the value of field 'groupCount'.
     */
    public void setGroupCount(int groupCount)
    {
        this._groupCount = groupCount;
        this._has_groupCount = true;
    } //-- void setGroupCount(int) 

    /**
     * Sets the value of field 'groupId'.
     * 
     * @param groupId the value of field 'groupId'.
     */
    public void setGroupId(java.lang.String groupId)
    {
        this._groupId = groupId;
    } //-- void setGroupId(java.lang.String) 

    /**
     * Sets the value of field 'groupTableCapacity'.
     * 
     * @param groupTableCapacity the value of field
     * 'groupTableCapacity'.
     */
    public void setGroupTableCapacity(long groupTableCapacity)
    {
        this._groupTableCapacity = groupTableCapacity;
        this._has_groupTableCapacity = true;
    } //-- void setGroupTableCapacity(long) 

    /**
     * Sets the value of field 'groupTableCount'.
     * 
     * @param groupTableCount the value of field 'groupTableCount'.
     */
    public void setGroupTableCount(int groupTableCount)
    {
        this._groupTableCount = groupTableCount;
        this._has_groupTableCount = true;
    } //-- void setGroupTableCount(int) 

    /**
     * Sets the value of field 'name'.
     * 
     * @param name the value of field 'name'.
     */
    public void setName(java.lang.String name)
    {
        this._name = name;
    } //-- void setName(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return BigTable
     */
    public static com.cdoframework.cdolib.database.dataservice.BigTable unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.database.dataservice.BigTable) Unmarshaller.unmarshal(com.cdoframework.cdolib.database.dataservice.BigTable.class, reader);
    } //-- com.cdoframework.cdolib.database.dataservice.BigTable unmarshal(java.io.Reader) 

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
