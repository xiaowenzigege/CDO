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
import java.util.ArrayList;
import java.util.Enumeration;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

/**
 * Class CreteriasType.
 * 
 * @version $Revision$ $Date$
 */
public class CreteriasType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _type
     */
    private java.lang.String _type = "AND";

    /**
     * Field _items
     */
    private java.util.ArrayList _items;


      //----------------/
     //- Constructors -/
    //----------------/

    public CreteriasType() 
     {
        super();
        setType("AND");
        _items = new ArrayList();
    } //-- com.cdoframework.cdolib.database.dataservice.CreteriasType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addCreteriasTypeItem
     * 
     * 
     * 
     * @param vCreteriasTypeItem
     */
    public void addCreteriasTypeItem(com.cdoframework.cdolib.database.dataservice.CreteriasTypeItem vCreteriasTypeItem)
        throws java.lang.IndexOutOfBoundsException
    {
        _items.add(vCreteriasTypeItem);
    } //-- void addCreteriasTypeItem(com.cdoframework.cdolib.database.dataservice.CreteriasTypeItem) 

    /**
     * Method addCreteriasTypeItem
     * 
     * 
     * 
     * @param index
     * @param vCreteriasTypeItem
     */
    public void addCreteriasTypeItem(int index, com.cdoframework.cdolib.database.dataservice.CreteriasTypeItem vCreteriasTypeItem)
        throws java.lang.IndexOutOfBoundsException
    {
        _items.add(index, vCreteriasTypeItem);
    } //-- void addCreteriasTypeItem(int, com.cdoframework.cdolib.database.dataservice.CreteriasTypeItem) 

    /**
     * Method clearCreteriasTypeItem
     * 
     */
    public void clearCreteriasTypeItem()
    {
        _items.clear();
    } //-- void clearCreteriasTypeItem() 

    /**
     * Method enumerateCreteriasTypeItem
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateCreteriasTypeItem()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_items.iterator());
    } //-- java.util.Enumeration enumerateCreteriasTypeItem() 

    /**
     * Method getCreteriasTypeItem
     * 
     * 
     * 
     * @param index
     * @return CreteriasTypeItem
     */
    public com.cdoframework.cdolib.database.dataservice.CreteriasTypeItem getCreteriasTypeItem(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _items.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.database.dataservice.CreteriasTypeItem) _items.get(index);
    } //-- com.cdoframework.cdolib.database.dataservice.CreteriasTypeItem getCreteriasTypeItem(int) 

    /**
     * Method getCreteriasTypeItem
     * 
     * 
     * 
     * @return CreteriasTypeItem
     */
    public com.cdoframework.cdolib.database.dataservice.CreteriasTypeItem[] getCreteriasTypeItem()
    {
        int size = _items.size();
        com.cdoframework.cdolib.database.dataservice.CreteriasTypeItem[] mArray = new com.cdoframework.cdolib.database.dataservice.CreteriasTypeItem[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.database.dataservice.CreteriasTypeItem) _items.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.database.dataservice.CreteriasTypeItem[] getCreteriasTypeItem() 

    /**
     * Method getCreteriasTypeItemCount
     * 
     * 
     * 
     * @return int
     */
    public int getCreteriasTypeItemCount()
    {
        return _items.size();
    } //-- int getCreteriasTypeItemCount() 

    /**
     * Returns the value of field 'type'.
     * 
     * @return String
     * @return the value of field 'type'.
     */
    public java.lang.String getType()
    {
        return this._type;
    } //-- java.lang.String getType() 

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
     * Method removeCreteriasTypeItem
     * 
     * 
     * 
     * @param vCreteriasTypeItem
     * @return boolean
     */
    public boolean removeCreteriasTypeItem(com.cdoframework.cdolib.database.dataservice.CreteriasTypeItem vCreteriasTypeItem)
    {
        boolean removed = _items.remove(vCreteriasTypeItem);
        return removed;
    } //-- boolean removeCreteriasTypeItem(com.cdoframework.cdolib.database.dataservice.CreteriasTypeItem) 

    /**
     * Method setCreteriasTypeItem
     * 
     * 
     * 
     * @param index
     * @param vCreteriasTypeItem
     */
    public void setCreteriasTypeItem(int index, com.cdoframework.cdolib.database.dataservice.CreteriasTypeItem vCreteriasTypeItem)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _items.size())) {
            throw new IndexOutOfBoundsException();
        }
        _items.set(index, vCreteriasTypeItem);
    } //-- void setCreteriasTypeItem(int, com.cdoframework.cdolib.database.dataservice.CreteriasTypeItem) 

    /**
     * Method setCreteriasTypeItem
     * 
     * 
     * 
     * @param creteriasTypeItemArray
     */
    public void setCreteriasTypeItem(com.cdoframework.cdolib.database.dataservice.CreteriasTypeItem[] creteriasTypeItemArray)
    {
        //-- copy array
        _items.clear();
        for (int i = 0; i < creteriasTypeItemArray.length; i++) {
            _items.add(creteriasTypeItemArray[i]);
        }
    } //-- void setCreteriasTypeItem(com.cdoframework.cdolib.database.dataservice.CreteriasTypeItem) 

    /**
     * Sets the value of field 'type'.
     * 
     * @param type the value of field 'type'.
     */
    public void setType(java.lang.String type)
    {
        this._type = type;
    } //-- void setType(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return CreteriasType
     */
    public static com.cdoframework.cdolib.database.dataservice.CreteriasType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.database.dataservice.CreteriasType) Unmarshaller.unmarshal(com.cdoframework.cdolib.database.dataservice.CreteriasType.class, reader);
    } //-- com.cdoframework.cdolib.database.dataservice.CreteriasType unmarshal(java.io.Reader) 

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
