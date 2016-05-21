/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.service.framework.transfilter.schema;

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
 * Class LoadCacheChoice.
 * 
 * @version $Revision$ $Date$
 */
public class LoadCacheChoice implements java.io.Serializable {


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

    public LoadCacheChoice() 
     {
        super();
        _items = new ArrayList();
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoice()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addLoadCacheChoiceItem
     * 
     * 
     * 
     * @param vLoadCacheChoiceItem
     */
    public void addLoadCacheChoiceItem(com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoiceItem vLoadCacheChoiceItem)
        throws java.lang.IndexOutOfBoundsException
    {
        _items.add(vLoadCacheChoiceItem);
    } //-- void addLoadCacheChoiceItem(com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoiceItem) 

    /**
     * Method addLoadCacheChoiceItem
     * 
     * 
     * 
     * @param index
     * @param vLoadCacheChoiceItem
     */
    public void addLoadCacheChoiceItem(int index, com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoiceItem vLoadCacheChoiceItem)
        throws java.lang.IndexOutOfBoundsException
    {
        _items.add(index, vLoadCacheChoiceItem);
    } //-- void addLoadCacheChoiceItem(int, com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoiceItem) 

    /**
     * Method clearLoadCacheChoiceItem
     * 
     */
    public void clearLoadCacheChoiceItem()
    {
        _items.clear();
    } //-- void clearLoadCacheChoiceItem() 

    /**
     * Method enumerateLoadCacheChoiceItem
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateLoadCacheChoiceItem()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_items.iterator());
    } //-- java.util.Enumeration enumerateLoadCacheChoiceItem() 

    /**
     * Method getLoadCacheChoiceItem
     * 
     * 
     * 
     * @param index
     * @return LoadCacheChoiceItem
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoiceItem getLoadCacheChoiceItem(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _items.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoiceItem) _items.get(index);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoiceItem getLoadCacheChoiceItem(int) 

    /**
     * Method getLoadCacheChoiceItem
     * 
     * 
     * 
     * @return LoadCacheChoiceItem
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoiceItem[] getLoadCacheChoiceItem()
    {
        int size = _items.size();
        com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoiceItem[] mArray = new com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoiceItem[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoiceItem) _items.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoiceItem[] getLoadCacheChoiceItem() 

    /**
     * Method getLoadCacheChoiceItemCount
     * 
     * 
     * 
     * @return int
     */
    public int getLoadCacheChoiceItemCount()
    {
        return _items.size();
    } //-- int getLoadCacheChoiceItemCount() 

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
     * Method removeLoadCacheChoiceItem
     * 
     * 
     * 
     * @param vLoadCacheChoiceItem
     * @return boolean
     */
    public boolean removeLoadCacheChoiceItem(com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoiceItem vLoadCacheChoiceItem)
    {
        boolean removed = _items.remove(vLoadCacheChoiceItem);
        return removed;
    } //-- boolean removeLoadCacheChoiceItem(com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoiceItem) 

    /**
     * Method setLoadCacheChoiceItem
     * 
     * 
     * 
     * @param index
     * @param vLoadCacheChoiceItem
     */
    public void setLoadCacheChoiceItem(int index, com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoiceItem vLoadCacheChoiceItem)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _items.size())) {
            throw new IndexOutOfBoundsException();
        }
        _items.set(index, vLoadCacheChoiceItem);
    } //-- void setLoadCacheChoiceItem(int, com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoiceItem) 

    /**
     * Method setLoadCacheChoiceItem
     * 
     * 
     * 
     * @param loadCacheChoiceItemArray
     */
    public void setLoadCacheChoiceItem(com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoiceItem[] loadCacheChoiceItemArray)
    {
        //-- copy array
        _items.clear();
        for (int i = 0; i < loadCacheChoiceItemArray.length; i++) {
            _items.add(loadCacheChoiceItemArray[i]);
        }
    } //-- void setLoadCacheChoiceItem(com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoiceItem) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return LoadCacheChoice
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoice unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoice) Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoice.class, reader);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoice unmarshal(java.io.Reader) 

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
