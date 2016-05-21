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
 * Class LoadCache.
 * 
 * @version $Revision$ $Date$
 */
public class LoadCache implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _transaction
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.Transaction _transaction;

    /**
     * Field _responseCondition
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.ResponseCondition _responseCondition;

    /**
     * Field _loadCacheChoiceList
     */
    private java.util.ArrayList _loadCacheChoiceList;


      //----------------/
     //- Constructors -/
    //----------------/

    public LoadCache() 
     {
        super();
        _loadCacheChoiceList = new ArrayList();
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCache()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addLoadCacheChoice
     * 
     * 
     * 
     * @param vLoadCacheChoice
     */
    public void addLoadCacheChoice(com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoice vLoadCacheChoice)
        throws java.lang.IndexOutOfBoundsException
    {
        _loadCacheChoiceList.add(vLoadCacheChoice);
    } //-- void addLoadCacheChoice(com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoice) 

    /**
     * Method addLoadCacheChoice
     * 
     * 
     * 
     * @param index
     * @param vLoadCacheChoice
     */
    public void addLoadCacheChoice(int index, com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoice vLoadCacheChoice)
        throws java.lang.IndexOutOfBoundsException
    {
        _loadCacheChoiceList.add(index, vLoadCacheChoice);
    } //-- void addLoadCacheChoice(int, com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoice) 

    /**
     * Method clearLoadCacheChoice
     * 
     */
    public void clearLoadCacheChoice()
    {
        _loadCacheChoiceList.clear();
    } //-- void clearLoadCacheChoice() 

    /**
     * Method enumerateLoadCacheChoice
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateLoadCacheChoice()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_loadCacheChoiceList.iterator());
    } //-- java.util.Enumeration enumerateLoadCacheChoice() 

    /**
     * Method getLoadCacheChoice
     * 
     * 
     * 
     * @param index
     * @return LoadCacheChoice
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoice getLoadCacheChoice(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _loadCacheChoiceList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoice) _loadCacheChoiceList.get(index);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoice getLoadCacheChoice(int) 

    /**
     * Method getLoadCacheChoice
     * 
     * 
     * 
     * @return LoadCacheChoice
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoice[] getLoadCacheChoice()
    {
        int size = _loadCacheChoiceList.size();
        com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoice[] mArray = new com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoice[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoice) _loadCacheChoiceList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoice[] getLoadCacheChoice() 

    /**
     * Method getLoadCacheChoiceCount
     * 
     * 
     * 
     * @return int
     */
    public int getLoadCacheChoiceCount()
    {
        return _loadCacheChoiceList.size();
    } //-- int getLoadCacheChoiceCount() 

    /**
     * Returns the value of field 'responseCondition'.
     * 
     * @return ResponseCondition
     * @return the value of field 'responseCondition'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.ResponseCondition getResponseCondition()
    {
        return this._responseCondition;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.ResponseCondition getResponseCondition() 

    /**
     * Returns the value of field 'transaction'.
     * 
     * @return Transaction
     * @return the value of field 'transaction'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.Transaction getTransaction()
    {
        return this._transaction;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.Transaction getTransaction() 

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
     * Method removeLoadCacheChoice
     * 
     * 
     * 
     * @param vLoadCacheChoice
     * @return boolean
     */
    public boolean removeLoadCacheChoice(com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoice vLoadCacheChoice)
    {
        boolean removed = _loadCacheChoiceList.remove(vLoadCacheChoice);
        return removed;
    } //-- boolean removeLoadCacheChoice(com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoice) 

    /**
     * Method setLoadCacheChoice
     * 
     * 
     * 
     * @param index
     * @param vLoadCacheChoice
     */
    public void setLoadCacheChoice(int index, com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoice vLoadCacheChoice)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _loadCacheChoiceList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _loadCacheChoiceList.set(index, vLoadCacheChoice);
    } //-- void setLoadCacheChoice(int, com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoice) 

    /**
     * Method setLoadCacheChoice
     * 
     * 
     * 
     * @param loadCacheChoiceArray
     */
    public void setLoadCacheChoice(com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoice[] loadCacheChoiceArray)
    {
        //-- copy array
        _loadCacheChoiceList.clear();
        for (int i = 0; i < loadCacheChoiceArray.length; i++) {
            _loadCacheChoiceList.add(loadCacheChoiceArray[i]);
        }
    } //-- void setLoadCacheChoice(com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCacheChoice) 

    /**
     * Sets the value of field 'responseCondition'.
     * 
     * @param responseCondition the value of field
     * 'responseCondition'.
     */
    public void setResponseCondition(com.cdoframework.cdolib.service.framework.transfilter.schema.ResponseCondition responseCondition)
    {
        this._responseCondition = responseCondition;
    } //-- void setResponseCondition(com.cdoframework.cdolib.service.framework.transfilter.schema.ResponseCondition) 

    /**
     * Sets the value of field 'transaction'.
     * 
     * @param transaction the value of field 'transaction'.
     */
    public void setTransaction(com.cdoframework.cdolib.service.framework.transfilter.schema.Transaction transaction)
    {
        this._transaction = transaction;
    } //-- void setTransaction(com.cdoframework.cdolib.service.framework.transfilter.schema.Transaction) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return LoadCache
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCache unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCache) Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCache.class, reader);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCache unmarshal(java.io.Reader) 

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
