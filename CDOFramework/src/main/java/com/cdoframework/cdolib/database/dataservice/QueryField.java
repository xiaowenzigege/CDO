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
 * Class QueryField.
 * 
 * @version $Revision$ $Date$
 */
public class QueryField extends com.cdoframework.cdolib.database.dataservice.CollectionNameType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _outputId
     */
    private java.lang.String _outputId;

    /**
     * Field _recordCountId
     */
    private java.lang.String _recordCountId;

    /**
     * Field _QField
     */
    private com.cdoframework.cdolib.database.dataservice.QField _QField;

    /**
     * Field _creterias
     */
    private com.cdoframework.cdolib.database.dataservice.Creterias _creterias;

    /**
     * Field _orderList
     */
    private java.util.ArrayList _orderList;


      //----------------/
     //- Constructors -/
    //----------------/

    public QueryField() 
     {
        super();
        _orderList = new ArrayList();
    } //-- com.cdoframework.cdolib.database.dataservice.QueryField()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addOrder
     * 
     * 
     * 
     * @param vOrder
     */
    public void addOrder(com.cdoframework.cdolib.database.dataservice.Order vOrder)
        throws java.lang.IndexOutOfBoundsException
    {
        _orderList.add(vOrder);
    } //-- void addOrder(com.cdoframework.cdolib.database.dataservice.Order) 

    /**
     * Method addOrder
     * 
     * 
     * 
     * @param index
     * @param vOrder
     */
    public void addOrder(int index, com.cdoframework.cdolib.database.dataservice.Order vOrder)
        throws java.lang.IndexOutOfBoundsException
    {
        _orderList.add(index, vOrder);
    } //-- void addOrder(int, com.cdoframework.cdolib.database.dataservice.Order) 

    /**
     * Method clearOrder
     * 
     */
    public void clearOrder()
    {
        _orderList.clear();
    } //-- void clearOrder() 

    /**
     * Method enumerateOrder
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateOrder()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_orderList.iterator());
    } //-- java.util.Enumeration enumerateOrder() 

    /**
     * Returns the value of field 'creterias'.
     * 
     * @return Creterias
     * @return the value of field 'creterias'.
     */
    public com.cdoframework.cdolib.database.dataservice.Creterias getCreterias()
    {
        return this._creterias;
    } //-- com.cdoframework.cdolib.database.dataservice.Creterias getCreterias() 

    /**
     * Method getOrder
     * 
     * 
     * 
     * @param index
     * @return Order
     */
    public com.cdoframework.cdolib.database.dataservice.Order getOrder(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _orderList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.database.dataservice.Order) _orderList.get(index);
    } //-- com.cdoframework.cdolib.database.dataservice.Order getOrder(int) 

    /**
     * Method getOrder
     * 
     * 
     * 
     * @return Order
     */
    public com.cdoframework.cdolib.database.dataservice.Order[] getOrder()
    {
        int size = _orderList.size();
        com.cdoframework.cdolib.database.dataservice.Order[] mArray = new com.cdoframework.cdolib.database.dataservice.Order[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.database.dataservice.Order) _orderList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.database.dataservice.Order[] getOrder() 

    /**
     * Method getOrderCount
     * 
     * 
     * 
     * @return int
     */
    public int getOrderCount()
    {
        return _orderList.size();
    } //-- int getOrderCount() 

    /**
     * Returns the value of field 'outputId'.
     * 
     * @return String
     * @return the value of field 'outputId'.
     */
    public java.lang.String getOutputId()
    {
        return this._outputId;
    } //-- java.lang.String getOutputId() 

    /**
     * Returns the value of field 'QField'.
     * 
     * @return QField
     * @return the value of field 'QField'.
     */
    public com.cdoframework.cdolib.database.dataservice.QField getQField()
    {
        return this._QField;
    } //-- com.cdoframework.cdolib.database.dataservice.QField getQField() 

    /**
     * Returns the value of field 'recordCountId'.
     * 
     * @return String
     * @return the value of field 'recordCountId'.
     */
    public java.lang.String getRecordCountId()
    {
        return this._recordCountId;
    } //-- java.lang.String getRecordCountId() 

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
     * Method removeOrder
     * 
     * 
     * 
     * @param vOrder
     * @return boolean
     */
    public boolean removeOrder(com.cdoframework.cdolib.database.dataservice.Order vOrder)
    {
        boolean removed = _orderList.remove(vOrder);
        return removed;
    } //-- boolean removeOrder(com.cdoframework.cdolib.database.dataservice.Order) 

    /**
     * Sets the value of field 'creterias'.
     * 
     * @param creterias the value of field 'creterias'.
     */
    public void setCreterias(com.cdoframework.cdolib.database.dataservice.Creterias creterias)
    {
        this._creterias = creterias;
    } //-- void setCreterias(com.cdoframework.cdolib.database.dataservice.Creterias) 

    /**
     * Method setOrder
     * 
     * 
     * 
     * @param index
     * @param vOrder
     */
    public void setOrder(int index, com.cdoframework.cdolib.database.dataservice.Order vOrder)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _orderList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _orderList.set(index, vOrder);
    } //-- void setOrder(int, com.cdoframework.cdolib.database.dataservice.Order) 

    /**
     * Method setOrder
     * 
     * 
     * 
     * @param orderArray
     */
    public void setOrder(com.cdoframework.cdolib.database.dataservice.Order[] orderArray)
    {
        //-- copy array
        _orderList.clear();
        for (int i = 0; i < orderArray.length; i++) {
            _orderList.add(orderArray[i]);
        }
    } //-- void setOrder(com.cdoframework.cdolib.database.dataservice.Order) 

    /**
     * Sets the value of field 'outputId'.
     * 
     * @param outputId the value of field 'outputId'.
     */
    public void setOutputId(java.lang.String outputId)
    {
        this._outputId = outputId;
    } //-- void setOutputId(java.lang.String) 

    /**
     * Sets the value of field 'QField'.
     * 
     * @param QField the value of field 'QField'.
     */
    public void setQField(com.cdoframework.cdolib.database.dataservice.QField QField)
    {
        this._QField = QField;
    } //-- void setQField(com.cdoframework.cdolib.database.dataservice.QField) 

    /**
     * Sets the value of field 'recordCountId'.
     * 
     * @param recordCountId the value of field 'recordCountId'.
     */
    public void setRecordCountId(java.lang.String recordCountId)
    {
        this._recordCountId = recordCountId;
    } //-- void setRecordCountId(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return CollectionNameType
     */
    public static com.cdoframework.cdolib.database.dataservice.CollectionNameType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.database.dataservice.CollectionNameType) Unmarshaller.unmarshal(com.cdoframework.cdolib.database.dataservice.QueryField.class, reader);
    } //-- com.cdoframework.cdolib.database.dataservice.CollectionNameType unmarshal(java.io.Reader) 

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
