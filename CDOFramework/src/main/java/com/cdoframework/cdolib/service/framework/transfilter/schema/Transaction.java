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
 * Class Transaction.
 * 
 * @version $Revision$ $Date$
 */
public class Transaction implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _serviceName
     */
    private java.lang.String _serviceName;

    /**
     * Field _transName
     */
    private java.lang.String _transName;

    /**
     * Field _requestKeyList
     */
    private java.util.ArrayList _requestKeyList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Transaction() 
     {
        super();
        _requestKeyList = new ArrayList();
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.Transaction()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addRequestKey
     * 
     * 
     * 
     * @param vRequestKey
     */
    public void addRequestKey(com.cdoframework.cdolib.service.framework.transfilter.schema.RequestKey vRequestKey)
        throws java.lang.IndexOutOfBoundsException
    {
        _requestKeyList.add(vRequestKey);
    } //-- void addRequestKey(com.cdoframework.cdolib.service.framework.transfilter.schema.RequestKey) 

    /**
     * Method addRequestKey
     * 
     * 
     * 
     * @param index
     * @param vRequestKey
     */
    public void addRequestKey(int index, com.cdoframework.cdolib.service.framework.transfilter.schema.RequestKey vRequestKey)
        throws java.lang.IndexOutOfBoundsException
    {
        _requestKeyList.add(index, vRequestKey);
    } //-- void addRequestKey(int, com.cdoframework.cdolib.service.framework.transfilter.schema.RequestKey) 

    /**
     * Method clearRequestKey
     * 
     */
    public void clearRequestKey()
    {
        _requestKeyList.clear();
    } //-- void clearRequestKey() 

    /**
     * Method enumerateRequestKey
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateRequestKey()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_requestKeyList.iterator());
    } //-- java.util.Enumeration enumerateRequestKey() 

    /**
     * Method getRequestKey
     * 
     * 
     * 
     * @param index
     * @return RequestKey
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.RequestKey getRequestKey(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _requestKeyList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.RequestKey) _requestKeyList.get(index);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.RequestKey getRequestKey(int) 

    /**
     * Method getRequestKey
     * 
     * 
     * 
     * @return RequestKey
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.RequestKey[] getRequestKey()
    {
        int size = _requestKeyList.size();
        com.cdoframework.cdolib.service.framework.transfilter.schema.RequestKey[] mArray = new com.cdoframework.cdolib.service.framework.transfilter.schema.RequestKey[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.service.framework.transfilter.schema.RequestKey) _requestKeyList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.RequestKey[] getRequestKey() 

    /**
     * Method getRequestKeyCount
     * 
     * 
     * 
     * @return int
     */
    public int getRequestKeyCount()
    {
        return _requestKeyList.size();
    } //-- int getRequestKeyCount() 

    /**
     * Returns the value of field 'serviceName'.
     * 
     * @return String
     * @return the value of field 'serviceName'.
     */
    public java.lang.String getServiceName()
    {
        return this._serviceName;
    } //-- java.lang.String getServiceName() 

    /**
     * Returns the value of field 'transName'.
     * 
     * @return String
     * @return the value of field 'transName'.
     */
    public java.lang.String getTransName()
    {
        return this._transName;
    } //-- java.lang.String getTransName() 

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
     * Method removeRequestKey
     * 
     * 
     * 
     * @param vRequestKey
     * @return boolean
     */
    public boolean removeRequestKey(com.cdoframework.cdolib.service.framework.transfilter.schema.RequestKey vRequestKey)
    {
        boolean removed = _requestKeyList.remove(vRequestKey);
        return removed;
    } //-- boolean removeRequestKey(com.cdoframework.cdolib.service.framework.transfilter.schema.RequestKey) 

    /**
     * Method setRequestKey
     * 
     * 
     * 
     * @param index
     * @param vRequestKey
     */
    public void setRequestKey(int index, com.cdoframework.cdolib.service.framework.transfilter.schema.RequestKey vRequestKey)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _requestKeyList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _requestKeyList.set(index, vRequestKey);
    } //-- void setRequestKey(int, com.cdoframework.cdolib.service.framework.transfilter.schema.RequestKey) 

    /**
     * Method setRequestKey
     * 
     * 
     * 
     * @param requestKeyArray
     */
    public void setRequestKey(com.cdoframework.cdolib.service.framework.transfilter.schema.RequestKey[] requestKeyArray)
    {
        //-- copy array
        _requestKeyList.clear();
        for (int i = 0; i < requestKeyArray.length; i++) {
            _requestKeyList.add(requestKeyArray[i]);
        }
    } //-- void setRequestKey(com.cdoframework.cdolib.service.framework.transfilter.schema.RequestKey) 

    /**
     * Sets the value of field 'serviceName'.
     * 
     * @param serviceName the value of field 'serviceName'.
     */
    public void setServiceName(java.lang.String serviceName)
    {
        this._serviceName = serviceName;
    } //-- void setServiceName(java.lang.String) 

    /**
     * Sets the value of field 'transName'.
     * 
     * @param transName the value of field 'transName'.
     */
    public void setTransName(java.lang.String transName)
    {
        this._transName = transName;
    } //-- void setTransName(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Transaction
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.schema.Transaction unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.Transaction) Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.schema.Transaction.class, reader);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.Transaction unmarshal(java.io.Reader) 

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
    private Filter filter;
	public void setFilter(Filter filter)
	{
		this.filter = filter;
	}
	public Filter getFilter()
	{
		return filter;
	}
}
