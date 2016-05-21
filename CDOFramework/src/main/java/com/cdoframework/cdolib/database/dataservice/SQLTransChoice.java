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
 * Class SQLTransChoice.
 * 
 * @version $Revision$ $Date$
 */
public class SQLTransChoice implements java.io.Serializable {


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

    public SQLTransChoice() 
     {
        super();
        _items = new ArrayList();
    } //-- com.cdoframework.cdolib.database.dataservice.SQLTransChoice()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addSQLTransChoiceItem
     * 
     * 
     * 
     * @param vSQLTransChoiceItem
     */
    public void addSQLTransChoiceItem(com.cdoframework.cdolib.database.dataservice.SQLTransChoiceItem vSQLTransChoiceItem)
        throws java.lang.IndexOutOfBoundsException
    {
        _items.add(vSQLTransChoiceItem);
    } //-- void addSQLTransChoiceItem(com.cdoframework.cdolib.database.dataservice.SQLTransChoiceItem) 

    /**
     * Method addSQLTransChoiceItem
     * 
     * 
     * 
     * @param index
     * @param vSQLTransChoiceItem
     */
    public void addSQLTransChoiceItem(int index, com.cdoframework.cdolib.database.dataservice.SQLTransChoiceItem vSQLTransChoiceItem)
        throws java.lang.IndexOutOfBoundsException
    {
        _items.add(index, vSQLTransChoiceItem);
    } //-- void addSQLTransChoiceItem(int, com.cdoframework.cdolib.database.dataservice.SQLTransChoiceItem) 

    /**
     * Method clearSQLTransChoiceItem
     * 
     */
    public void clearSQLTransChoiceItem()
    {
        _items.clear();
    } //-- void clearSQLTransChoiceItem() 

    /**
     * Method enumerateSQLTransChoiceItem
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateSQLTransChoiceItem()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_items.iterator());
    } //-- java.util.Enumeration enumerateSQLTransChoiceItem() 

    /**
     * Method getSQLTransChoiceItem
     * 
     * 
     * 
     * @param index
     * @return SQLTransChoiceItem
     */
    public com.cdoframework.cdolib.database.dataservice.SQLTransChoiceItem getSQLTransChoiceItem(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _items.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.database.dataservice.SQLTransChoiceItem) _items.get(index);
    } //-- com.cdoframework.cdolib.database.dataservice.SQLTransChoiceItem getSQLTransChoiceItem(int) 

    /**
     * Method getSQLTransChoiceItem
     * 
     * 
     * 
     * @return SQLTransChoiceItem
     */
    public com.cdoframework.cdolib.database.dataservice.SQLTransChoiceItem[] getSQLTransChoiceItem()
    {
        int size = _items.size();
        com.cdoframework.cdolib.database.dataservice.SQLTransChoiceItem[] mArray = new com.cdoframework.cdolib.database.dataservice.SQLTransChoiceItem[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.database.dataservice.SQLTransChoiceItem) _items.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.database.dataservice.SQLTransChoiceItem[] getSQLTransChoiceItem() 

    /**
     * Method getSQLTransChoiceItemCount
     * 
     * 
     * 
     * @return int
     */
    public int getSQLTransChoiceItemCount()
    {
        return _items.size();
    } //-- int getSQLTransChoiceItemCount() 

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
     * Method removeSQLTransChoiceItem
     * 
     * 
     * 
     * @param vSQLTransChoiceItem
     * @return boolean
     */
    public boolean removeSQLTransChoiceItem(com.cdoframework.cdolib.database.dataservice.SQLTransChoiceItem vSQLTransChoiceItem)
    {
        boolean removed = _items.remove(vSQLTransChoiceItem);
        return removed;
    } //-- boolean removeSQLTransChoiceItem(com.cdoframework.cdolib.database.dataservice.SQLTransChoiceItem) 

    /**
     * Method setSQLTransChoiceItem
     * 
     * 
     * 
     * @param index
     * @param vSQLTransChoiceItem
     */
    public void setSQLTransChoiceItem(int index, com.cdoframework.cdolib.database.dataservice.SQLTransChoiceItem vSQLTransChoiceItem)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _items.size())) {
            throw new IndexOutOfBoundsException();
        }
        _items.set(index, vSQLTransChoiceItem);
    } //-- void setSQLTransChoiceItem(int, com.cdoframework.cdolib.database.dataservice.SQLTransChoiceItem) 

    /**
     * Method setSQLTransChoiceItem
     * 
     * 
     * 
     * @param SQLTransChoiceItemArray
     */
    public void setSQLTransChoiceItem(com.cdoframework.cdolib.database.dataservice.SQLTransChoiceItem[] SQLTransChoiceItemArray)
    {
        //-- copy array
        _items.clear();
        for (int i = 0; i < SQLTransChoiceItemArray.length; i++) {
            _items.add(SQLTransChoiceItemArray[i]);
        }
    } //-- void setSQLTransChoiceItem(com.cdoframework.cdolib.database.dataservice.SQLTransChoiceItem) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return SQLTransChoice
     */
    public static com.cdoframework.cdolib.database.dataservice.SQLTransChoice unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.database.dataservice.SQLTransChoice) Unmarshaller.unmarshal(com.cdoframework.cdolib.database.dataservice.SQLTransChoice.class, reader);
    } //-- com.cdoframework.cdolib.database.dataservice.SQLTransChoice unmarshal(java.io.Reader) 

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
