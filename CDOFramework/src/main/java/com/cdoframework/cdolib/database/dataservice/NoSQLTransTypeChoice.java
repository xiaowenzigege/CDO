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
 * Class NoSQLTransTypeChoice.
 * 
 * @version $Revision$ $Date$
 */
public class NoSQLTransTypeChoice implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _items
     */
    private java.util.ArrayList _items;


      //----------------/
     //- Constructors -/
    //----------------/

    public NoSQLTransTypeChoice() 
     {
        super();
        _items = new ArrayList();
    } //-- com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoice()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addNoSQLTransTypeChoiceItem
     * 
     * 
     * 
     * @param vNoSQLTransTypeChoiceItem
     */
    public void addNoSQLTransTypeChoiceItem(com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoiceItem vNoSQLTransTypeChoiceItem)
        throws java.lang.IndexOutOfBoundsException
    {
        _items.add(vNoSQLTransTypeChoiceItem);
    } //-- void addNoSQLTransTypeChoiceItem(com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoiceItem) 

    /**
     * Method addNoSQLTransTypeChoiceItem
     * 
     * 
     * 
     * @param index
     * @param vNoSQLTransTypeChoiceItem
     */
    public void addNoSQLTransTypeChoiceItem(int index, com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoiceItem vNoSQLTransTypeChoiceItem)
        throws java.lang.IndexOutOfBoundsException
    {
        _items.add(index, vNoSQLTransTypeChoiceItem);
    } //-- void addNoSQLTransTypeChoiceItem(int, com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoiceItem) 

    /**
     * Method clearNoSQLTransTypeChoiceItem
     * 
     */
    public void clearNoSQLTransTypeChoiceItem()
    {
        _items.clear();
    } //-- void clearNoSQLTransTypeChoiceItem() 

    /**
     * Method enumerateNoSQLTransTypeChoiceItem
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateNoSQLTransTypeChoiceItem()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_items.iterator());
    } //-- java.util.Enumeration enumerateNoSQLTransTypeChoiceItem() 

    /**
     * Method getNoSQLTransTypeChoiceItem
     * 
     * 
     * 
     * @param index
     * @return NoSQLTransTypeChoiceItem
     */
    public com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoiceItem getNoSQLTransTypeChoiceItem(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _items.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoiceItem) _items.get(index);
    } //-- com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoiceItem getNoSQLTransTypeChoiceItem(int) 

    /**
     * Method getNoSQLTransTypeChoiceItem
     * 
     * 
     * 
     * @return NoSQLTransTypeChoiceItem
     */
    public com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoiceItem[] getNoSQLTransTypeChoiceItem()
    {
        int size = _items.size();
        com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoiceItem[] mArray = new com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoiceItem[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoiceItem) _items.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoiceItem[] getNoSQLTransTypeChoiceItem() 

    /**
     * Method getNoSQLTransTypeChoiceItemCount
     * 
     * 
     * 
     * @return int
     */
    public int getNoSQLTransTypeChoiceItemCount()
    {
        return _items.size();
    } //-- int getNoSQLTransTypeChoiceItemCount() 

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
     * Method removeNoSQLTransTypeChoiceItem
     * 
     * 
     * 
     * @param vNoSQLTransTypeChoiceItem
     * @return boolean
     */
    public boolean removeNoSQLTransTypeChoiceItem(com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoiceItem vNoSQLTransTypeChoiceItem)
    {
        boolean removed = _items.remove(vNoSQLTransTypeChoiceItem);
        return removed;
    } //-- boolean removeNoSQLTransTypeChoiceItem(com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoiceItem) 

    /**
     * Method setNoSQLTransTypeChoiceItem
     * 
     * 
     * 
     * @param index
     * @param vNoSQLTransTypeChoiceItem
     */
    public void setNoSQLTransTypeChoiceItem(int index, com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoiceItem vNoSQLTransTypeChoiceItem)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _items.size())) {
            throw new IndexOutOfBoundsException();
        }
        _items.set(index, vNoSQLTransTypeChoiceItem);
    } //-- void setNoSQLTransTypeChoiceItem(int, com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoiceItem) 

    /**
     * Method setNoSQLTransTypeChoiceItem
     * 
     * 
     * 
     * @param noSQLTransTypeChoiceItemArray
     */
    public void setNoSQLTransTypeChoiceItem(com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoiceItem[] noSQLTransTypeChoiceItemArray)
    {
        //-- copy array
        _items.clear();
        for (int i = 0; i < noSQLTransTypeChoiceItemArray.length; i++) {
            _items.add(noSQLTransTypeChoiceItemArray[i]);
        }
    } //-- void setNoSQLTransTypeChoiceItem(com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoiceItem) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return NoSQLTransTypeChoice
     */
    public static com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoice unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoice) Unmarshaller.unmarshal(com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoice.class, reader);
    } //-- com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoice unmarshal(java.io.Reader) 

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
