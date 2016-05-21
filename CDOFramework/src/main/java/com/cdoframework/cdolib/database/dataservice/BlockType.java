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
 * Class BlockType.
 * 
 * @version $Revision$ $Date$
 */
public class BlockType implements java.io.Serializable {


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

    public BlockType() 
     {
        super();
        _items = new ArrayList();
    } //-- com.cdoframework.cdolib.database.dataservice.BlockType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addBlockTypeItem
     * 
     * 
     * 
     * @param vBlockTypeItem
     */
    public void addBlockTypeItem(com.cdoframework.cdolib.database.dataservice.BlockTypeItem vBlockTypeItem)
        throws java.lang.IndexOutOfBoundsException
    {
        _items.add(vBlockTypeItem);
    } //-- void addBlockTypeItem(com.cdoframework.cdolib.database.dataservice.BlockTypeItem) 

    /**
     * Method addBlockTypeItem
     * 
     * 
     * 
     * @param index
     * @param vBlockTypeItem
     */
    public void addBlockTypeItem(int index, com.cdoframework.cdolib.database.dataservice.BlockTypeItem vBlockTypeItem)
        throws java.lang.IndexOutOfBoundsException
    {
        _items.add(index, vBlockTypeItem);
    } //-- void addBlockTypeItem(int, com.cdoframework.cdolib.database.dataservice.BlockTypeItem) 

    /**
     * Method clearBlockTypeItem
     * 
     */
    public void clearBlockTypeItem()
    {
        _items.clear();
    } //-- void clearBlockTypeItem() 

    /**
     * Method enumerateBlockTypeItem
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateBlockTypeItem()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_items.iterator());
    } //-- java.util.Enumeration enumerateBlockTypeItem() 

    /**
     * Method getBlockTypeItem
     * 
     * 
     * 
     * @param index
     * @return BlockTypeItem
     */
    public com.cdoframework.cdolib.database.dataservice.BlockTypeItem getBlockTypeItem(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _items.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.database.dataservice.BlockTypeItem) _items.get(index);
    } //-- com.cdoframework.cdolib.database.dataservice.BlockTypeItem getBlockTypeItem(int) 

    /**
     * Method getBlockTypeItem
     * 
     * 
     * 
     * @return BlockTypeItem
     */
    public com.cdoframework.cdolib.database.dataservice.BlockTypeItem[] getBlockTypeItem()
    {
        int size = _items.size();
        com.cdoframework.cdolib.database.dataservice.BlockTypeItem[] mArray = new com.cdoframework.cdolib.database.dataservice.BlockTypeItem[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.database.dataservice.BlockTypeItem) _items.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.database.dataservice.BlockTypeItem[] getBlockTypeItem() 

    /**
     * Method getBlockTypeItemCount
     * 
     * 
     * 
     * @return int
     */
    public int getBlockTypeItemCount()
    {
        return _items.size();
    } //-- int getBlockTypeItemCount() 

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
     * Method removeBlockTypeItem
     * 
     * 
     * 
     * @param vBlockTypeItem
     * @return boolean
     */
    public boolean removeBlockTypeItem(com.cdoframework.cdolib.database.dataservice.BlockTypeItem vBlockTypeItem)
    {
        boolean removed = _items.remove(vBlockTypeItem);
        return removed;
    } //-- boolean removeBlockTypeItem(com.cdoframework.cdolib.database.dataservice.BlockTypeItem) 

    /**
     * Method setBlockTypeItem
     * 
     * 
     * 
     * @param index
     * @param vBlockTypeItem
     */
    public void setBlockTypeItem(int index, com.cdoframework.cdolib.database.dataservice.BlockTypeItem vBlockTypeItem)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _items.size())) {
            throw new IndexOutOfBoundsException();
        }
        _items.set(index, vBlockTypeItem);
    } //-- void setBlockTypeItem(int, com.cdoframework.cdolib.database.dataservice.BlockTypeItem) 

    /**
     * Method setBlockTypeItem
     * 
     * 
     * 
     * @param blockTypeItemArray
     */
    public void setBlockTypeItem(com.cdoframework.cdolib.database.dataservice.BlockTypeItem[] blockTypeItemArray)
    {
        //-- copy array
        _items.clear();
        for (int i = 0; i < blockTypeItemArray.length; i++) {
            _items.add(blockTypeItemArray[i]);
        }
    } //-- void setBlockTypeItem(com.cdoframework.cdolib.database.dataservice.BlockTypeItem) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return BlockType
     */
    public static com.cdoframework.cdolib.database.dataservice.BlockType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.database.dataservice.BlockType) Unmarshaller.unmarshal(com.cdoframework.cdolib.database.dataservice.BlockType.class, reader);
    } //-- com.cdoframework.cdolib.database.dataservice.BlockType unmarshal(java.io.Reader) 

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
