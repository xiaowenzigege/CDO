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
 * Class QueryRecordSet.
 * 
 * @version $Revision$ $Date$
 */
public class QueryRecordSet extends com.cdoframework.cdolib.database.dataservice.CollectionNameType 
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
     * Field _keyFieldName
     */
    private java.lang.String _keyFieldName = "";

    /**
     * Field _recordCountId
     */
    private java.lang.String _recordCountId;

    /**
     * Field _QFieldList
     */
    private java.util.ArrayList _QFieldList;

    /**
     * Field _creterias
     */
    private com.cdoframework.cdolib.database.dataservice.Creterias _creterias;

    /**
     * Field _orderList
     */
    private java.util.ArrayList _orderList;

    /**
     * Field _scope
     */
    private com.cdoframework.cdolib.database.dataservice.Scope _scope;

    /**
     * Field _groupList
     */
    private java.util.ArrayList _groupList;


      //----------------/
     //- Constructors -/
    //----------------/

    public QueryRecordSet() 
     {
        super();
        setKeyFieldName("");
        _QFieldList = new ArrayList();
        _orderList = new ArrayList();
        _groupList = new ArrayList();
    } //-- com.cdoframework.cdolib.database.dataservice.QueryRecordSet()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addGroup
     * 
     * 
     * 
     * @param vGroup
     */
    public void addGroup(com.cdoframework.cdolib.database.dataservice.Group vGroup)
        throws java.lang.IndexOutOfBoundsException
    {
        _groupList.add(vGroup);
    } //-- void addGroup(com.cdoframework.cdolib.database.dataservice.Group) 

    /**
     * Method addGroup
     * 
     * 
     * 
     * @param index
     * @param vGroup
     */
    public void addGroup(int index, com.cdoframework.cdolib.database.dataservice.Group vGroup)
        throws java.lang.IndexOutOfBoundsException
    {
        _groupList.add(index, vGroup);
    } //-- void addGroup(int, com.cdoframework.cdolib.database.dataservice.Group) 

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
     * Method addQField
     * 
     * 
     * 
     * @param vQField
     */
    public void addQField(com.cdoframework.cdolib.database.dataservice.QField vQField)
        throws java.lang.IndexOutOfBoundsException
    {
        _QFieldList.add(vQField);
    } //-- void addQField(com.cdoframework.cdolib.database.dataservice.QField) 

    /**
     * Method addQField
     * 
     * 
     * 
     * @param index
     * @param vQField
     */
    public void addQField(int index, com.cdoframework.cdolib.database.dataservice.QField vQField)
        throws java.lang.IndexOutOfBoundsException
    {
        _QFieldList.add(index, vQField);
    } //-- void addQField(int, com.cdoframework.cdolib.database.dataservice.QField) 

    /**
     * Method clearGroup
     * 
     */
    public void clearGroup()
    {
        _groupList.clear();
    } //-- void clearGroup() 

    /**
     * Method clearOrder
     * 
     */
    public void clearOrder()
    {
        _orderList.clear();
    } //-- void clearOrder() 

    /**
     * Method clearQField
     * 
     */
    public void clearQField()
    {
        _QFieldList.clear();
    } //-- void clearQField() 

    /**
     * Method enumerateGroup
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateGroup()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_groupList.iterator());
    } //-- java.util.Enumeration enumerateGroup() 

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
     * Method enumerateQField
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateQField()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_QFieldList.iterator());
    } //-- java.util.Enumeration enumerateQField() 

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
     * Method getGroup
     * 
     * 
     * 
     * @param index
     * @return Group
     */
    public com.cdoframework.cdolib.database.dataservice.Group getGroup(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _groupList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.database.dataservice.Group) _groupList.get(index);
    } //-- com.cdoframework.cdolib.database.dataservice.Group getGroup(int) 

    /**
     * Method getGroup
     * 
     * 
     * 
     * @return Group
     */
    public com.cdoframework.cdolib.database.dataservice.Group[] getGroup()
    {
        int size = _groupList.size();
        com.cdoframework.cdolib.database.dataservice.Group[] mArray = new com.cdoframework.cdolib.database.dataservice.Group[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.database.dataservice.Group) _groupList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.database.dataservice.Group[] getGroup() 

    /**
     * Method getGroupCount
     * 
     * 
     * 
     * @return int
     */
    public int getGroupCount()
    {
        return _groupList.size();
    } //-- int getGroupCount() 

    /**
     * Returns the value of field 'keyFieldName'.
     * 
     * @return String
     * @return the value of field 'keyFieldName'.
     */
    public java.lang.String getKeyFieldName()
    {
        return this._keyFieldName;
    } //-- java.lang.String getKeyFieldName() 

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
     * Method getQField
     * 
     * 
     * 
     * @param index
     * @return QField
     */
    public com.cdoframework.cdolib.database.dataservice.QField getQField(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _QFieldList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.database.dataservice.QField) _QFieldList.get(index);
    } //-- com.cdoframework.cdolib.database.dataservice.QField getQField(int) 

    /**
     * Method getQField
     * 
     * 
     * 
     * @return QField
     */
    public com.cdoframework.cdolib.database.dataservice.QField[] getQField()
    {
        int size = _QFieldList.size();
        com.cdoframework.cdolib.database.dataservice.QField[] mArray = new com.cdoframework.cdolib.database.dataservice.QField[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.database.dataservice.QField) _QFieldList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.database.dataservice.QField[] getQField() 

    /**
     * Method getQFieldCount
     * 
     * 
     * 
     * @return int
     */
    public int getQFieldCount()
    {
        return _QFieldList.size();
    } //-- int getQFieldCount() 

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
     * Returns the value of field 'scope'.
     * 
     * @return Scope
     * @return the value of field 'scope'.
     */
    public com.cdoframework.cdolib.database.dataservice.Scope getScope()
    {
        return this._scope;
    } //-- com.cdoframework.cdolib.database.dataservice.Scope getScope() 

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
     * Method removeGroup
     * 
     * 
     * 
     * @param vGroup
     * @return boolean
     */
    public boolean removeGroup(com.cdoframework.cdolib.database.dataservice.Group vGroup)
    {
        boolean removed = _groupList.remove(vGroup);
        return removed;
    } //-- boolean removeGroup(com.cdoframework.cdolib.database.dataservice.Group) 

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
     * Method removeQField
     * 
     * 
     * 
     * @param vQField
     * @return boolean
     */
    public boolean removeQField(com.cdoframework.cdolib.database.dataservice.QField vQField)
    {
        boolean removed = _QFieldList.remove(vQField);
        return removed;
    } //-- boolean removeQField(com.cdoframework.cdolib.database.dataservice.QField) 

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
     * Method setGroup
     * 
     * 
     * 
     * @param index
     * @param vGroup
     */
    public void setGroup(int index, com.cdoframework.cdolib.database.dataservice.Group vGroup)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _groupList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _groupList.set(index, vGroup);
    } //-- void setGroup(int, com.cdoframework.cdolib.database.dataservice.Group) 

    /**
     * Method setGroup
     * 
     * 
     * 
     * @param groupArray
     */
    public void setGroup(com.cdoframework.cdolib.database.dataservice.Group[] groupArray)
    {
        //-- copy array
        _groupList.clear();
        for (int i = 0; i < groupArray.length; i++) {
            _groupList.add(groupArray[i]);
        }
    } //-- void setGroup(com.cdoframework.cdolib.database.dataservice.Group) 

    /**
     * Sets the value of field 'keyFieldName'.
     * 
     * @param keyFieldName the value of field 'keyFieldName'.
     */
    public void setKeyFieldName(java.lang.String keyFieldName)
    {
        this._keyFieldName = keyFieldName;
    } //-- void setKeyFieldName(java.lang.String) 

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
     * Method setQField
     * 
     * 
     * 
     * @param index
     * @param vQField
     */
    public void setQField(int index, com.cdoframework.cdolib.database.dataservice.QField vQField)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _QFieldList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _QFieldList.set(index, vQField);
    } //-- void setQField(int, com.cdoframework.cdolib.database.dataservice.QField) 

    /**
     * Method setQField
     * 
     * 
     * 
     * @param QFieldArray
     */
    public void setQField(com.cdoframework.cdolib.database.dataservice.QField[] QFieldArray)
    {
        //-- copy array
        _QFieldList.clear();
        for (int i = 0; i < QFieldArray.length; i++) {
            _QFieldList.add(QFieldArray[i]);
        }
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
     * Sets the value of field 'scope'.
     * 
     * @param scope the value of field 'scope'.
     */
    public void setScope(com.cdoframework.cdolib.database.dataservice.Scope scope)
    {
        this._scope = scope;
    } //-- void setScope(com.cdoframework.cdolib.database.dataservice.Scope) 

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
        return (com.cdoframework.cdolib.database.dataservice.CollectionNameType) Unmarshaller.unmarshal(com.cdoframework.cdolib.database.dataservice.QueryRecordSet.class, reader);
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
