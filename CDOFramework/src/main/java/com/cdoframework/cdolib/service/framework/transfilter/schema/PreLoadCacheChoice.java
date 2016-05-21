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
 * Class PreLoadCacheChoice.
 * 
 * @version $Revision$ $Date$
 */
public class PreLoadCacheChoice implements java.io.Serializable {


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

    public PreLoadCacheChoice() 
     {
        super();
        _items = new ArrayList();
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PreLoadCacheChoice()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addPreLoadCacheChoiceItem
     * 
     * 
     * 
     * @param vPreLoadCacheChoiceItem
     */
    public void addPreLoadCacheChoiceItem(com.cdoframework.cdolib.service.framework.transfilter.schema.PreLoadCacheChoiceItem vPreLoadCacheChoiceItem)
        throws java.lang.IndexOutOfBoundsException
    {
        _items.add(vPreLoadCacheChoiceItem);
    } //-- void addPreLoadCacheChoiceItem(com.cdoframework.cdolib.service.framework.transfilter.schema.PreLoadCacheChoiceItem) 

    /**
     * Method addPreLoadCacheChoiceItem
     * 
     * 
     * 
     * @param index
     * @param vPreLoadCacheChoiceItem
     */
    public void addPreLoadCacheChoiceItem(int index, com.cdoframework.cdolib.service.framework.transfilter.schema.PreLoadCacheChoiceItem vPreLoadCacheChoiceItem)
        throws java.lang.IndexOutOfBoundsException
    {
        _items.add(index, vPreLoadCacheChoiceItem);
    } //-- void addPreLoadCacheChoiceItem(int, com.cdoframework.cdolib.service.framework.transfilter.schema.PreLoadCacheChoiceItem) 

    /**
     * Method clearPreLoadCacheChoiceItem
     * 
     */
    public void clearPreLoadCacheChoiceItem()
    {
        _items.clear();
    } //-- void clearPreLoadCacheChoiceItem() 

    /**
     * Method enumeratePreLoadCacheChoiceItem
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumeratePreLoadCacheChoiceItem()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_items.iterator());
    } //-- java.util.Enumeration enumeratePreLoadCacheChoiceItem() 

    /**
     * Method getPreLoadCacheChoiceItem
     * 
     * 
     * 
     * @param index
     * @return PreLoadCacheChoiceItem
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.PreLoadCacheChoiceItem getPreLoadCacheChoiceItem(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _items.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.PreLoadCacheChoiceItem) _items.get(index);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PreLoadCacheChoiceItem getPreLoadCacheChoiceItem(int) 

    /**
     * Method getPreLoadCacheChoiceItem
     * 
     * 
     * 
     * @return PreLoadCacheChoiceItem
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.PreLoadCacheChoiceItem[] getPreLoadCacheChoiceItem()
    {
        int size = _items.size();
        com.cdoframework.cdolib.service.framework.transfilter.schema.PreLoadCacheChoiceItem[] mArray = new com.cdoframework.cdolib.service.framework.transfilter.schema.PreLoadCacheChoiceItem[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.service.framework.transfilter.schema.PreLoadCacheChoiceItem) _items.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PreLoadCacheChoiceItem[] getPreLoadCacheChoiceItem() 

    /**
     * Method getPreLoadCacheChoiceItemCount
     * 
     * 
     * 
     * @return int
     */
    public int getPreLoadCacheChoiceItemCount()
    {
        return _items.size();
    } //-- int getPreLoadCacheChoiceItemCount() 

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
     * Method removePreLoadCacheChoiceItem
     * 
     * 
     * 
     * @param vPreLoadCacheChoiceItem
     * @return boolean
     */
    public boolean removePreLoadCacheChoiceItem(com.cdoframework.cdolib.service.framework.transfilter.schema.PreLoadCacheChoiceItem vPreLoadCacheChoiceItem)
    {
        boolean removed = _items.remove(vPreLoadCacheChoiceItem);
        return removed;
    } //-- boolean removePreLoadCacheChoiceItem(com.cdoframework.cdolib.service.framework.transfilter.schema.PreLoadCacheChoiceItem) 

    /**
     * Method setPreLoadCacheChoiceItem
     * 
     * 
     * 
     * @param index
     * @param vPreLoadCacheChoiceItem
     */
    public void setPreLoadCacheChoiceItem(int index, com.cdoframework.cdolib.service.framework.transfilter.schema.PreLoadCacheChoiceItem vPreLoadCacheChoiceItem)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _items.size())) {
            throw new IndexOutOfBoundsException();
        }
        _items.set(index, vPreLoadCacheChoiceItem);
    } //-- void setPreLoadCacheChoiceItem(int, com.cdoframework.cdolib.service.framework.transfilter.schema.PreLoadCacheChoiceItem) 

    /**
     * Method setPreLoadCacheChoiceItem
     * 
     * 
     * 
     * @param preLoadCacheChoiceItemArray
     */
    public void setPreLoadCacheChoiceItem(com.cdoframework.cdolib.service.framework.transfilter.schema.PreLoadCacheChoiceItem[] preLoadCacheChoiceItemArray)
    {
        //-- copy array
        _items.clear();
        for (int i = 0; i < preLoadCacheChoiceItemArray.length; i++) {
            _items.add(preLoadCacheChoiceItemArray[i]);
        }
    } //-- void setPreLoadCacheChoiceItem(com.cdoframework.cdolib.service.framework.transfilter.schema.PreLoadCacheChoiceItem) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return PreLoadCacheChoice
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.schema.PreLoadCacheChoice unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.PreLoadCacheChoice) Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.schema.PreLoadCacheChoice.class, reader);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PreLoadCacheChoice unmarshal(java.io.Reader) 

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
