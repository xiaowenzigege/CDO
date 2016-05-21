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
 * Class For.
 * 
 * @version $Revision$ $Date$
 */
public class For implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _indexId
     */
    private java.lang.String _indexId;

    /**
     * Field _fromIndex
     */
    private java.lang.String _fromIndex;

    /**
     * Field _count
     */
    private java.lang.String _count;

    /**
     * Field _items
     */
    private java.util.ArrayList _items;


      //----------------/
     //- Constructors -/
    //----------------/

    public For() 
     {
        super();
        _items = new ArrayList();
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.For()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addForItem
     * 
     * 
     * 
     * @param vForItem
     */
    public void addForItem(com.cdoframework.cdolib.service.framework.transfilter.schema.ForItem vForItem)
        throws java.lang.IndexOutOfBoundsException
    {
        _items.add(vForItem);
    } //-- void addForItem(com.cdoframework.cdolib.service.framework.transfilter.schema.ForItem) 

    /**
     * Method addForItem
     * 
     * 
     * 
     * @param index
     * @param vForItem
     */
    public void addForItem(int index, com.cdoframework.cdolib.service.framework.transfilter.schema.ForItem vForItem)
        throws java.lang.IndexOutOfBoundsException
    {
        _items.add(index, vForItem);
    } //-- void addForItem(int, com.cdoframework.cdolib.service.framework.transfilter.schema.ForItem) 

    /**
     * Method clearForItem
     * 
     */
    public void clearForItem()
    {
        _items.clear();
    } //-- void clearForItem() 

    /**
     * Method enumerateForItem
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateForItem()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_items.iterator());
    } //-- java.util.Enumeration enumerateForItem() 

    /**
     * Returns the value of field 'count'.
     * 
     * @return String
     * @return the value of field 'count'.
     */
    public java.lang.String getCount()
    {
        return this._count;
    } //-- java.lang.String getCount() 

    /**
     * Method getForItem
     * 
     * 
     * 
     * @param index
     * @return ForItem
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.ForItem getForItem(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _items.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.ForItem) _items.get(index);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.ForItem getForItem(int) 

    /**
     * Method getForItem
     * 
     * 
     * 
     * @return ForItem
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.ForItem[] getForItem()
    {
        int size = _items.size();
        com.cdoframework.cdolib.service.framework.transfilter.schema.ForItem[] mArray = new com.cdoframework.cdolib.service.framework.transfilter.schema.ForItem[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.service.framework.transfilter.schema.ForItem) _items.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.ForItem[] getForItem() 

    /**
     * Method getForItemCount
     * 
     * 
     * 
     * @return int
     */
    public int getForItemCount()
    {
        return _items.size();
    } //-- int getForItemCount() 

    /**
     * Returns the value of field 'fromIndex'.
     * 
     * @return String
     * @return the value of field 'fromIndex'.
     */
    public java.lang.String getFromIndex()
    {
        return this._fromIndex;
    } //-- java.lang.String getFromIndex() 

    /**
     * Returns the value of field 'indexId'.
     * 
     * @return String
     * @return the value of field 'indexId'.
     */
    public java.lang.String getIndexId()
    {
        return this._indexId;
    } //-- java.lang.String getIndexId() 

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
     * Method removeForItem
     * 
     * 
     * 
     * @param vForItem
     * @return boolean
     */
    public boolean removeForItem(com.cdoframework.cdolib.service.framework.transfilter.schema.ForItem vForItem)
    {
        boolean removed = _items.remove(vForItem);
        return removed;
    } //-- boolean removeForItem(com.cdoframework.cdolib.service.framework.transfilter.schema.ForItem) 

    /**
     * Sets the value of field 'count'.
     * 
     * @param count the value of field 'count'.
     */
    public void setCount(java.lang.String count)
    {
        this._count = count;
    } //-- void setCount(java.lang.String) 

    /**
     * Method setForItem
     * 
     * 
     * 
     * @param index
     * @param vForItem
     */
    public void setForItem(int index, com.cdoframework.cdolib.service.framework.transfilter.schema.ForItem vForItem)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _items.size())) {
            throw new IndexOutOfBoundsException();
        }
        _items.set(index, vForItem);
    } //-- void setForItem(int, com.cdoframework.cdolib.service.framework.transfilter.schema.ForItem) 

    /**
     * Method setForItem
     * 
     * 
     * 
     * @param forItemArray
     */
    public void setForItem(com.cdoframework.cdolib.service.framework.transfilter.schema.ForItem[] forItemArray)
    {
        //-- copy array
        _items.clear();
        for (int i = 0; i < forItemArray.length; i++) {
            _items.add(forItemArray[i]);
        }
    } //-- void setForItem(com.cdoframework.cdolib.service.framework.transfilter.schema.ForItem) 

    /**
     * Sets the value of field 'fromIndex'.
     * 
     * @param fromIndex the value of field 'fromIndex'.
     */
    public void setFromIndex(java.lang.String fromIndex)
    {
        this._fromIndex = fromIndex;
    } //-- void setFromIndex(java.lang.String) 

    /**
     * Sets the value of field 'indexId'.
     * 
     * @param indexId the value of field 'indexId'.
     */
    public void setIndexId(java.lang.String indexId)
    {
        this._indexId = indexId;
    } //-- void setIndexId(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return For
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.schema.For unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.For) Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.schema.For.class, reader);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.For unmarshal(java.io.Reader) 

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
