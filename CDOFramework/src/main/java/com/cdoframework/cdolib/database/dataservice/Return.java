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
 * Class Return.
 * 
 * @version $Revision$ $Date$
 */
public class Return implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _code
     */
    private int _code;

    /**
     * keeps track of state for field: _code
     */
    private boolean _has_code;

    /**
     * Field _info
     */
    private java.lang.String _info;

    /**
     * Field _text
     */
    private java.lang.String _text = "OK";

    /**
     * Field _returnItemList
     */
    private java.util.ArrayList _returnItemList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Return() 
     {
        super();
        setText("OK");
        _returnItemList = new ArrayList();
    } //-- com.cdoframework.cdolib.database.dataservice.Return()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addReturnItem
     * 
     * 
     * 
     * @param vReturnItem
     */
    public void addReturnItem(com.cdoframework.cdolib.database.dataservice.ReturnItem vReturnItem)
        throws java.lang.IndexOutOfBoundsException
    {
        _returnItemList.add(vReturnItem);
    } //-- void addReturnItem(com.cdoframework.cdolib.database.dataservice.ReturnItem) 

    /**
     * Method addReturnItem
     * 
     * 
     * 
     * @param index
     * @param vReturnItem
     */
    public void addReturnItem(int index, com.cdoframework.cdolib.database.dataservice.ReturnItem vReturnItem)
        throws java.lang.IndexOutOfBoundsException
    {
        _returnItemList.add(index, vReturnItem);
    } //-- void addReturnItem(int, com.cdoframework.cdolib.database.dataservice.ReturnItem) 

    /**
     * Method clearReturnItem
     * 
     */
    public void clearReturnItem()
    {
        _returnItemList.clear();
    } //-- void clearReturnItem() 

    /**
     * Method deleteCode
     * 
     */
    public void deleteCode()
    {
        this._has_code= false;
    } //-- void deleteCode() 

    /**
     * Method enumerateReturnItem
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateReturnItem()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_returnItemList.iterator());
    } //-- java.util.Enumeration enumerateReturnItem() 

    /**
     * Returns the value of field 'code'.
     * 
     * @return int
     * @return the value of field 'code'.
     */
    public int getCode()
    {
        return this._code;
    } //-- int getCode() 

    /**
     * Returns the value of field 'info'.
     * 
     * @return String
     * @return the value of field 'info'.
     */
    public java.lang.String getInfo()
    {
        return this._info;
    } //-- java.lang.String getInfo() 

    /**
     * Method getReturnItem
     * 
     * 
     * 
     * @param index
     * @return ReturnItem
     */
    public com.cdoframework.cdolib.database.dataservice.ReturnItem getReturnItem(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _returnItemList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.database.dataservice.ReturnItem) _returnItemList.get(index);
    } //-- com.cdoframework.cdolib.database.dataservice.ReturnItem getReturnItem(int) 

    /**
     * Method getReturnItem
     * 
     * 
     * 
     * @return ReturnItem
     */
    public com.cdoframework.cdolib.database.dataservice.ReturnItem[] getReturnItem()
    {
        int size = _returnItemList.size();
        com.cdoframework.cdolib.database.dataservice.ReturnItem[] mArray = new com.cdoframework.cdolib.database.dataservice.ReturnItem[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.database.dataservice.ReturnItem) _returnItemList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.database.dataservice.ReturnItem[] getReturnItem() 

    /**
     * Method getReturnItemCount
     * 
     * 
     * 
     * @return int
     */
    public int getReturnItemCount()
    {
        return _returnItemList.size();
    } //-- int getReturnItemCount() 

    /**
     * Returns the value of field 'text'.
     * 
     * @return String
     * @return the value of field 'text'.
     */
    public java.lang.String getText()
    {
        return this._text;
    } //-- java.lang.String getText() 

    /**
     * Method hasCode
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasCode()
    {
        return this._has_code;
    } //-- boolean hasCode() 

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
     * Method removeReturnItem
     * 
     * 
     * 
     * @param vReturnItem
     * @return boolean
     */
    public boolean removeReturnItem(com.cdoframework.cdolib.database.dataservice.ReturnItem vReturnItem)
    {
        boolean removed = _returnItemList.remove(vReturnItem);
        return removed;
    } //-- boolean removeReturnItem(com.cdoframework.cdolib.database.dataservice.ReturnItem) 

    /**
     * Sets the value of field 'code'.
     * 
     * @param code the value of field 'code'.
     */
    public void setCode(int code)
    {
        this._code = code;
        this._has_code = true;
    } //-- void setCode(int) 

    /**
     * Sets the value of field 'info'.
     * 
     * @param info the value of field 'info'.
     */
    public void setInfo(java.lang.String info)
    {
        this._info = info;
    } //-- void setInfo(java.lang.String) 

    /**
     * Method setReturnItem
     * 
     * 
     * 
     * @param index
     * @param vReturnItem
     */
    public void setReturnItem(int index, com.cdoframework.cdolib.database.dataservice.ReturnItem vReturnItem)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _returnItemList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _returnItemList.set(index, vReturnItem);
    } //-- void setReturnItem(int, com.cdoframework.cdolib.database.dataservice.ReturnItem) 

    /**
     * Method setReturnItem
     * 
     * 
     * 
     * @param returnItemArray
     */
    public void setReturnItem(com.cdoframework.cdolib.database.dataservice.ReturnItem[] returnItemArray)
    {
        //-- copy array
        _returnItemList.clear();
        for (int i = 0; i < returnItemArray.length; i++) {
            _returnItemList.add(returnItemArray[i]);
        }
    } //-- void setReturnItem(com.cdoframework.cdolib.database.dataservice.ReturnItem) 

    /**
     * Sets the value of field 'text'.
     * 
     * @param text the value of field 'text'.
     */
    public void setText(java.lang.String text)
    {
        this._text = text;
    } //-- void setText(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Return
     */
    public static com.cdoframework.cdolib.database.dataservice.Return unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.database.dataservice.Return) Unmarshaller.unmarshal(com.cdoframework.cdolib.database.dataservice.Return.class, reader);
    } //-- com.cdoframework.cdolib.database.dataservice.Return unmarshal(java.io.Reader) 

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
