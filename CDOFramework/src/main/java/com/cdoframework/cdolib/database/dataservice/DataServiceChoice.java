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
 * Class DataServiceChoice.
 * 
 * @version $Revision$ $Date$
 */
public class DataServiceChoice implements java.io.Serializable {


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

    public DataServiceChoice() 
     {
        super();
        _items = new ArrayList();
    } //-- com.cdoframework.cdolib.database.dataservice.DataServiceChoice()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addDataServiceChoiceItem
     * 
     * 
     * 
     * @param vDataServiceChoiceItem
     */
    public void addDataServiceChoiceItem(com.cdoframework.cdolib.database.dataservice.DataServiceChoiceItem vDataServiceChoiceItem)
        throws java.lang.IndexOutOfBoundsException
    {
        _items.add(vDataServiceChoiceItem);
    } //-- void addDataServiceChoiceItem(com.cdoframework.cdolib.database.dataservice.DataServiceChoiceItem) 

    /**
     * Method addDataServiceChoiceItem
     * 
     * 
     * 
     * @param index
     * @param vDataServiceChoiceItem
     */
    public void addDataServiceChoiceItem(int index, com.cdoframework.cdolib.database.dataservice.DataServiceChoiceItem vDataServiceChoiceItem)
        throws java.lang.IndexOutOfBoundsException
    {
        _items.add(index, vDataServiceChoiceItem);
    } //-- void addDataServiceChoiceItem(int, com.cdoframework.cdolib.database.dataservice.DataServiceChoiceItem) 

    /**
     * Method clearDataServiceChoiceItem
     * 
     */
    public void clearDataServiceChoiceItem()
    {
        _items.clear();
    } //-- void clearDataServiceChoiceItem() 

    /**
     * Method enumerateDataServiceChoiceItem
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateDataServiceChoiceItem()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_items.iterator());
    } //-- java.util.Enumeration enumerateDataServiceChoiceItem() 

    /**
     * Method getDataServiceChoiceItem
     * 
     * 
     * 
     * @param index
     * @return DataServiceChoiceItem
     */
    public com.cdoframework.cdolib.database.dataservice.DataServiceChoiceItem getDataServiceChoiceItem(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _items.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.database.dataservice.DataServiceChoiceItem) _items.get(index);
    } //-- com.cdoframework.cdolib.database.dataservice.DataServiceChoiceItem getDataServiceChoiceItem(int) 

    /**
     * Method getDataServiceChoiceItem
     * 
     * 
     * 
     * @return DataServiceChoiceItem
     */
    public com.cdoframework.cdolib.database.dataservice.DataServiceChoiceItem[] getDataServiceChoiceItem()
    {
        int size = _items.size();
        com.cdoframework.cdolib.database.dataservice.DataServiceChoiceItem[] mArray = new com.cdoframework.cdolib.database.dataservice.DataServiceChoiceItem[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.database.dataservice.DataServiceChoiceItem) _items.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.database.dataservice.DataServiceChoiceItem[] getDataServiceChoiceItem() 

    /**
     * Method getDataServiceChoiceItemCount
     * 
     * 
     * 
     * @return int
     */
    public int getDataServiceChoiceItemCount()
    {
        return _items.size();
    } //-- int getDataServiceChoiceItemCount() 

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
     * Method removeDataServiceChoiceItem
     * 
     * 
     * 
     * @param vDataServiceChoiceItem
     * @return boolean
     */
    public boolean removeDataServiceChoiceItem(com.cdoframework.cdolib.database.dataservice.DataServiceChoiceItem vDataServiceChoiceItem)
    {
        boolean removed = _items.remove(vDataServiceChoiceItem);
        return removed;
    } //-- boolean removeDataServiceChoiceItem(com.cdoframework.cdolib.database.dataservice.DataServiceChoiceItem) 

    /**
     * Method setDataServiceChoiceItem
     * 
     * 
     * 
     * @param index
     * @param vDataServiceChoiceItem
     */
    public void setDataServiceChoiceItem(int index, com.cdoframework.cdolib.database.dataservice.DataServiceChoiceItem vDataServiceChoiceItem)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _items.size())) {
            throw new IndexOutOfBoundsException();
        }
        _items.set(index, vDataServiceChoiceItem);
    } //-- void setDataServiceChoiceItem(int, com.cdoframework.cdolib.database.dataservice.DataServiceChoiceItem) 

    /**
     * Method setDataServiceChoiceItem
     * 
     * 
     * 
     * @param dataServiceChoiceItemArray
     */
    public void setDataServiceChoiceItem(com.cdoframework.cdolib.database.dataservice.DataServiceChoiceItem[] dataServiceChoiceItemArray)
    {
        //-- copy array
        _items.clear();
        for (int i = 0; i < dataServiceChoiceItemArray.length; i++) {
            _items.add(dataServiceChoiceItemArray[i]);
        }
    } //-- void setDataServiceChoiceItem(com.cdoframework.cdolib.database.dataservice.DataServiceChoiceItem) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return DataServiceChoice
     */
    public static com.cdoframework.cdolib.database.dataservice.DataServiceChoice unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.database.dataservice.DataServiceChoice) Unmarshaller.unmarshal(com.cdoframework.cdolib.database.dataservice.DataServiceChoice.class, reader);
    } //-- com.cdoframework.cdolib.database.dataservice.DataServiceChoice unmarshal(java.io.Reader) 

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
