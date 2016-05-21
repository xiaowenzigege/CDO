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
 * Class PostLoadCacheChoice.
 * 
 * @version $Revision$ $Date$
 */
public class PostLoadCacheChoice implements java.io.Serializable {


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

    public PostLoadCacheChoice() 
     {
        super();
        _items = new ArrayList();
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PostLoadCacheChoice()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addPostLoadCacheChoiceItem
     * 
     * 
     * 
     * @param vPostLoadCacheChoiceItem
     */
    public void addPostLoadCacheChoiceItem(com.cdoframework.cdolib.service.framework.transfilter.schema.PostLoadCacheChoiceItem vPostLoadCacheChoiceItem)
        throws java.lang.IndexOutOfBoundsException
    {
        _items.add(vPostLoadCacheChoiceItem);
    } //-- void addPostLoadCacheChoiceItem(com.cdoframework.cdolib.service.framework.transfilter.schema.PostLoadCacheChoiceItem) 

    /**
     * Method addPostLoadCacheChoiceItem
     * 
     * 
     * 
     * @param index
     * @param vPostLoadCacheChoiceItem
     */
    public void addPostLoadCacheChoiceItem(int index, com.cdoframework.cdolib.service.framework.transfilter.schema.PostLoadCacheChoiceItem vPostLoadCacheChoiceItem)
        throws java.lang.IndexOutOfBoundsException
    {
        _items.add(index, vPostLoadCacheChoiceItem);
    } //-- void addPostLoadCacheChoiceItem(int, com.cdoframework.cdolib.service.framework.transfilter.schema.PostLoadCacheChoiceItem) 

    /**
     * Method clearPostLoadCacheChoiceItem
     * 
     */
    public void clearPostLoadCacheChoiceItem()
    {
        _items.clear();
    } //-- void clearPostLoadCacheChoiceItem() 

    /**
     * Method enumeratePostLoadCacheChoiceItem
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumeratePostLoadCacheChoiceItem()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_items.iterator());
    } //-- java.util.Enumeration enumeratePostLoadCacheChoiceItem() 

    /**
     * Method getPostLoadCacheChoiceItem
     * 
     * 
     * 
     * @param index
     * @return PostLoadCacheChoiceItem
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.PostLoadCacheChoiceItem getPostLoadCacheChoiceItem(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _items.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.PostLoadCacheChoiceItem) _items.get(index);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PostLoadCacheChoiceItem getPostLoadCacheChoiceItem(int) 

    /**
     * Method getPostLoadCacheChoiceItem
     * 
     * 
     * 
     * @return PostLoadCacheChoiceItem
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.PostLoadCacheChoiceItem[] getPostLoadCacheChoiceItem()
    {
        int size = _items.size();
        com.cdoframework.cdolib.service.framework.transfilter.schema.PostLoadCacheChoiceItem[] mArray = new com.cdoframework.cdolib.service.framework.transfilter.schema.PostLoadCacheChoiceItem[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.service.framework.transfilter.schema.PostLoadCacheChoiceItem) _items.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PostLoadCacheChoiceItem[] getPostLoadCacheChoiceItem() 

    /**
     * Method getPostLoadCacheChoiceItemCount
     * 
     * 
     * 
     * @return int
     */
    public int getPostLoadCacheChoiceItemCount()
    {
        return _items.size();
    } //-- int getPostLoadCacheChoiceItemCount() 

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
     * Method removePostLoadCacheChoiceItem
     * 
     * 
     * 
     * @param vPostLoadCacheChoiceItem
     * @return boolean
     */
    public boolean removePostLoadCacheChoiceItem(com.cdoframework.cdolib.service.framework.transfilter.schema.PostLoadCacheChoiceItem vPostLoadCacheChoiceItem)
    {
        boolean removed = _items.remove(vPostLoadCacheChoiceItem);
        return removed;
    } //-- boolean removePostLoadCacheChoiceItem(com.cdoframework.cdolib.service.framework.transfilter.schema.PostLoadCacheChoiceItem) 

    /**
     * Method setPostLoadCacheChoiceItem
     * 
     * 
     * 
     * @param index
     * @param vPostLoadCacheChoiceItem
     */
    public void setPostLoadCacheChoiceItem(int index, com.cdoframework.cdolib.service.framework.transfilter.schema.PostLoadCacheChoiceItem vPostLoadCacheChoiceItem)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _items.size())) {
            throw new IndexOutOfBoundsException();
        }
        _items.set(index, vPostLoadCacheChoiceItem);
    } //-- void setPostLoadCacheChoiceItem(int, com.cdoframework.cdolib.service.framework.transfilter.schema.PostLoadCacheChoiceItem) 

    /**
     * Method setPostLoadCacheChoiceItem
     * 
     * 
     * 
     * @param postLoadCacheChoiceItemArray
     */
    public void setPostLoadCacheChoiceItem(com.cdoframework.cdolib.service.framework.transfilter.schema.PostLoadCacheChoiceItem[] postLoadCacheChoiceItemArray)
    {
        //-- copy array
        _items.clear();
        for (int i = 0; i < postLoadCacheChoiceItemArray.length; i++) {
            _items.add(postLoadCacheChoiceItemArray[i]);
        }
    } //-- void setPostLoadCacheChoiceItem(com.cdoframework.cdolib.service.framework.transfilter.schema.PostLoadCacheChoiceItem) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return PostLoadCacheChoice
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.schema.PostLoadCacheChoice unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.PostLoadCacheChoice) Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.schema.PostLoadCacheChoice.class, reader);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PostLoadCacheChoice unmarshal(java.io.Reader) 

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
