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
 * Class PostTransEventType.
 * 
 * @version $Revision$ $Date$
 */
public class PostTransEventType implements java.io.Serializable {


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

    public PostTransEventType() 
     {
        super();
        _items = new ArrayList();
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PostTransEventType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addPostTransEventTypeItem
     * 
     * 
     * 
     * @param vPostTransEventTypeItem
     */
    public void addPostTransEventTypeItem(com.cdoframework.cdolib.service.framework.transfilter.schema.PostTransEventTypeItem vPostTransEventTypeItem)
        throws java.lang.IndexOutOfBoundsException
    {
        _items.add(vPostTransEventTypeItem);
    } //-- void addPostTransEventTypeItem(com.cdoframework.cdolib.service.framework.transfilter.schema.PostTransEventTypeItem) 

    /**
     * Method addPostTransEventTypeItem
     * 
     * 
     * 
     * @param index
     * @param vPostTransEventTypeItem
     */
    public void addPostTransEventTypeItem(int index, com.cdoframework.cdolib.service.framework.transfilter.schema.PostTransEventTypeItem vPostTransEventTypeItem)
        throws java.lang.IndexOutOfBoundsException
    {
        _items.add(index, vPostTransEventTypeItem);
    } //-- void addPostTransEventTypeItem(int, com.cdoframework.cdolib.service.framework.transfilter.schema.PostTransEventTypeItem) 

    /**
     * Method clearPostTransEventTypeItem
     * 
     */
    public void clearPostTransEventTypeItem()
    {
        _items.clear();
    } //-- void clearPostTransEventTypeItem() 

    /**
     * Method enumeratePostTransEventTypeItem
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumeratePostTransEventTypeItem()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_items.iterator());
    } //-- java.util.Enumeration enumeratePostTransEventTypeItem() 

    /**
     * Method getPostTransEventTypeItem
     * 
     * 
     * 
     * @param index
     * @return PostTransEventTypeItem
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.PostTransEventTypeItem getPostTransEventTypeItem(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _items.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.PostTransEventTypeItem) _items.get(index);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PostTransEventTypeItem getPostTransEventTypeItem(int) 

    /**
     * Method getPostTransEventTypeItem
     * 
     * 
     * 
     * @return PostTransEventTypeItem
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.PostTransEventTypeItem[] getPostTransEventTypeItem()
    {
        int size = _items.size();
        com.cdoframework.cdolib.service.framework.transfilter.schema.PostTransEventTypeItem[] mArray = new com.cdoframework.cdolib.service.framework.transfilter.schema.PostTransEventTypeItem[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.service.framework.transfilter.schema.PostTransEventTypeItem) _items.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PostTransEventTypeItem[] getPostTransEventTypeItem() 

    /**
     * Method getPostTransEventTypeItemCount
     * 
     * 
     * 
     * @return int
     */
    public int getPostTransEventTypeItemCount()
    {
        return _items.size();
    } //-- int getPostTransEventTypeItemCount() 

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
     * Method removePostTransEventTypeItem
     * 
     * 
     * 
     * @param vPostTransEventTypeItem
     * @return boolean
     */
    public boolean removePostTransEventTypeItem(com.cdoframework.cdolib.service.framework.transfilter.schema.PostTransEventTypeItem vPostTransEventTypeItem)
    {
        boolean removed = _items.remove(vPostTransEventTypeItem);
        return removed;
    } //-- boolean removePostTransEventTypeItem(com.cdoframework.cdolib.service.framework.transfilter.schema.PostTransEventTypeItem) 

    /**
     * Method setPostTransEventTypeItem
     * 
     * 
     * 
     * @param index
     * @param vPostTransEventTypeItem
     */
    public void setPostTransEventTypeItem(int index, com.cdoframework.cdolib.service.framework.transfilter.schema.PostTransEventTypeItem vPostTransEventTypeItem)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _items.size())) {
            throw new IndexOutOfBoundsException();
        }
        _items.set(index, vPostTransEventTypeItem);
    } //-- void setPostTransEventTypeItem(int, com.cdoframework.cdolib.service.framework.transfilter.schema.PostTransEventTypeItem) 

    /**
     * Method setPostTransEventTypeItem
     * 
     * 
     * 
     * @param postTransEventTypeItemArray
     */
    public void setPostTransEventTypeItem(com.cdoframework.cdolib.service.framework.transfilter.schema.PostTransEventTypeItem[] postTransEventTypeItemArray)
    {
        //-- copy array
        _items.clear();
        for (int i = 0; i < postTransEventTypeItemArray.length; i++) {
            _items.add(postTransEventTypeItemArray[i]);
        }
    } //-- void setPostTransEventTypeItem(com.cdoframework.cdolib.service.framework.transfilter.schema.PostTransEventTypeItem) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return PostTransEventType
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.schema.PostTransEventType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.PostTransEventType) Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.schema.PostTransEventType.class, reader);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PostTransEventType unmarshal(java.io.Reader) 

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
