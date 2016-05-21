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
 * Class PreTransEventType.
 * 
 * @version $Revision$ $Date$
 */
public class PreTransEventType implements java.io.Serializable {


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

    public PreTransEventType() 
     {
        super();
        _items = new ArrayList();
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PreTransEventType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addPreTransEventTypeItem
     * 
     * 
     * 
     * @param vPreTransEventTypeItem
     */
    public void addPreTransEventTypeItem(com.cdoframework.cdolib.service.framework.transfilter.schema.PreTransEventTypeItem vPreTransEventTypeItem)
        throws java.lang.IndexOutOfBoundsException
    {
        _items.add(vPreTransEventTypeItem);
    } //-- void addPreTransEventTypeItem(com.cdoframework.cdolib.service.framework.transfilter.schema.PreTransEventTypeItem) 

    /**
     * Method addPreTransEventTypeItem
     * 
     * 
     * 
     * @param index
     * @param vPreTransEventTypeItem
     */
    public void addPreTransEventTypeItem(int index, com.cdoframework.cdolib.service.framework.transfilter.schema.PreTransEventTypeItem vPreTransEventTypeItem)
        throws java.lang.IndexOutOfBoundsException
    {
        _items.add(index, vPreTransEventTypeItem);
    } //-- void addPreTransEventTypeItem(int, com.cdoframework.cdolib.service.framework.transfilter.schema.PreTransEventTypeItem) 

    /**
     * Method clearPreTransEventTypeItem
     * 
     */
    public void clearPreTransEventTypeItem()
    {
        _items.clear();
    } //-- void clearPreTransEventTypeItem() 

    /**
     * Method enumeratePreTransEventTypeItem
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumeratePreTransEventTypeItem()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_items.iterator());
    } //-- java.util.Enumeration enumeratePreTransEventTypeItem() 

    /**
     * Method getPreTransEventTypeItem
     * 
     * 
     * 
     * @param index
     * @return PreTransEventTypeItem
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.PreTransEventTypeItem getPreTransEventTypeItem(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _items.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.PreTransEventTypeItem) _items.get(index);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PreTransEventTypeItem getPreTransEventTypeItem(int) 

    /**
     * Method getPreTransEventTypeItem
     * 
     * 
     * 
     * @return PreTransEventTypeItem
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.PreTransEventTypeItem[] getPreTransEventTypeItem()
    {
        int size = _items.size();
        com.cdoframework.cdolib.service.framework.transfilter.schema.PreTransEventTypeItem[] mArray = new com.cdoframework.cdolib.service.framework.transfilter.schema.PreTransEventTypeItem[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.service.framework.transfilter.schema.PreTransEventTypeItem) _items.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PreTransEventTypeItem[] getPreTransEventTypeItem() 

    /**
     * Method getPreTransEventTypeItemCount
     * 
     * 
     * 
     * @return int
     */
    public int getPreTransEventTypeItemCount()
    {
        return _items.size();
    } //-- int getPreTransEventTypeItemCount() 

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
     * Method removePreTransEventTypeItem
     * 
     * 
     * 
     * @param vPreTransEventTypeItem
     * @return boolean
     */
    public boolean removePreTransEventTypeItem(com.cdoframework.cdolib.service.framework.transfilter.schema.PreTransEventTypeItem vPreTransEventTypeItem)
    {
        boolean removed = _items.remove(vPreTransEventTypeItem);
        return removed;
    } //-- boolean removePreTransEventTypeItem(com.cdoframework.cdolib.service.framework.transfilter.schema.PreTransEventTypeItem) 

    /**
     * Method setPreTransEventTypeItem
     * 
     * 
     * 
     * @param index
     * @param vPreTransEventTypeItem
     */
    public void setPreTransEventTypeItem(int index, com.cdoframework.cdolib.service.framework.transfilter.schema.PreTransEventTypeItem vPreTransEventTypeItem)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _items.size())) {
            throw new IndexOutOfBoundsException();
        }
        _items.set(index, vPreTransEventTypeItem);
    } //-- void setPreTransEventTypeItem(int, com.cdoframework.cdolib.service.framework.transfilter.schema.PreTransEventTypeItem) 

    /**
     * Method setPreTransEventTypeItem
     * 
     * 
     * 
     * @param preTransEventTypeItemArray
     */
    public void setPreTransEventTypeItem(com.cdoframework.cdolib.service.framework.transfilter.schema.PreTransEventTypeItem[] preTransEventTypeItemArray)
    {
        //-- copy array
        _items.clear();
        for (int i = 0; i < preTransEventTypeItemArray.length; i++) {
            _items.add(preTransEventTypeItemArray[i]);
        }
    } //-- void setPreTransEventTypeItem(com.cdoframework.cdolib.service.framework.transfilter.schema.PreTransEventTypeItem) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return PreTransEventType
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.schema.PreTransEventType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.PreTransEventType) Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.schema.PreTransEventType.class, reader);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PreTransEventType unmarshal(java.io.Reader) 

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
