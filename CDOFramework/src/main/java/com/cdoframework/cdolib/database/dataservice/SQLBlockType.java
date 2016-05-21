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
 * Class SQLBlockType.
 * 
 * @version $Revision$ $Date$
 */
public class SQLBlockType implements java.io.Serializable {


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

    public SQLBlockType() 
     {
        super();
        _items = new ArrayList();
    } //-- com.cdoframework.cdolib.database.dataservice.SQLBlockType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addSQLBlockTypeItem
     * 
     * 
     * 
     * @param vSQLBlockTypeItem
     */
    public void addSQLBlockTypeItem(com.cdoframework.cdolib.database.dataservice.SQLBlockTypeItem vSQLBlockTypeItem)
        throws java.lang.IndexOutOfBoundsException
    {
        _items.add(vSQLBlockTypeItem);
    } //-- void addSQLBlockTypeItem(com.cdoframework.cdolib.database.dataservice.SQLBlockTypeItem) 

    /**
     * Method addSQLBlockTypeItem
     * 
     * 
     * 
     * @param index
     * @param vSQLBlockTypeItem
     */
    public void addSQLBlockTypeItem(int index, com.cdoframework.cdolib.database.dataservice.SQLBlockTypeItem vSQLBlockTypeItem)
        throws java.lang.IndexOutOfBoundsException
    {
        _items.add(index, vSQLBlockTypeItem);
    } //-- void addSQLBlockTypeItem(int, com.cdoframework.cdolib.database.dataservice.SQLBlockTypeItem) 

    /**
     * Method clearSQLBlockTypeItem
     * 
     */
    public void clearSQLBlockTypeItem()
    {
        _items.clear();
    } //-- void clearSQLBlockTypeItem() 

    /**
     * Method enumerateSQLBlockTypeItem
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateSQLBlockTypeItem()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_items.iterator());
    } //-- java.util.Enumeration enumerateSQLBlockTypeItem() 

    /**
     * Method getSQLBlockTypeItem
     * 
     * 
     * 
     * @param index
     * @return SQLBlockTypeItem
     */
    public com.cdoframework.cdolib.database.dataservice.SQLBlockTypeItem getSQLBlockTypeItem(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _items.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.database.dataservice.SQLBlockTypeItem) _items.get(index);
    } //-- com.cdoframework.cdolib.database.dataservice.SQLBlockTypeItem getSQLBlockTypeItem(int) 

    /**
     * Method getSQLBlockTypeItem
     * 
     * 
     * 
     * @return SQLBlockTypeItem
     */
    public com.cdoframework.cdolib.database.dataservice.SQLBlockTypeItem[] getSQLBlockTypeItem()
    {
        int size = _items.size();
        com.cdoframework.cdolib.database.dataservice.SQLBlockTypeItem[] mArray = new com.cdoframework.cdolib.database.dataservice.SQLBlockTypeItem[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.database.dataservice.SQLBlockTypeItem) _items.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.database.dataservice.SQLBlockTypeItem[] getSQLBlockTypeItem() 

    /**
     * Method getSQLBlockTypeItemCount
     * 
     * 
     * 
     * @return int
     */
    public int getSQLBlockTypeItemCount()
    {
        return _items.size();
    } //-- int getSQLBlockTypeItemCount() 

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
     * Method removeSQLBlockTypeItem
     * 
     * 
     * 
     * @param vSQLBlockTypeItem
     * @return boolean
     */
    public boolean removeSQLBlockTypeItem(com.cdoframework.cdolib.database.dataservice.SQLBlockTypeItem vSQLBlockTypeItem)
    {
        boolean removed = _items.remove(vSQLBlockTypeItem);
        return removed;
    } //-- boolean removeSQLBlockTypeItem(com.cdoframework.cdolib.database.dataservice.SQLBlockTypeItem) 

    /**
     * Method setSQLBlockTypeItem
     * 
     * 
     * 
     * @param index
     * @param vSQLBlockTypeItem
     */
    public void setSQLBlockTypeItem(int index, com.cdoframework.cdolib.database.dataservice.SQLBlockTypeItem vSQLBlockTypeItem)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _items.size())) {
            throw new IndexOutOfBoundsException();
        }
        _items.set(index, vSQLBlockTypeItem);
    } //-- void setSQLBlockTypeItem(int, com.cdoframework.cdolib.database.dataservice.SQLBlockTypeItem) 

    /**
     * Method setSQLBlockTypeItem
     * 
     * 
     * 
     * @param SQLBlockTypeItemArray
     */
    public void setSQLBlockTypeItem(com.cdoframework.cdolib.database.dataservice.SQLBlockTypeItem[] SQLBlockTypeItemArray)
    {
        //-- copy array
        _items.clear();
        for (int i = 0; i < SQLBlockTypeItemArray.length; i++) {
            _items.add(SQLBlockTypeItemArray[i]);
        }
    } //-- void setSQLBlockTypeItem(com.cdoframework.cdolib.database.dataservice.SQLBlockTypeItem) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return SQLBlockType
     */
    public static com.cdoframework.cdolib.database.dataservice.SQLBlockType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.database.dataservice.SQLBlockType) Unmarshaller.unmarshal(com.cdoframework.cdolib.database.dataservice.SQLBlockType.class, reader);
    } //-- com.cdoframework.cdolib.database.dataservice.SQLBlockType unmarshal(java.io.Reader) 

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
