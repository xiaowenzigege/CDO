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
 * Class PostTransaction.
 * 
 * @version $Revision$ $Date$
 */
public class PostTransaction implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _syn
     */
    private boolean _syn = false;

    /**
     * keeps track of state for field: _syn
     */
    private boolean _has_syn;

    /**
     * Field _returnCode
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.ReturnCode _returnCode;

    /**
     * Field _requestCondition
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.RequestCondition _requestCondition;

    /**
     * Field _responseCondition
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.ResponseCondition _responseCondition;

    /**
     * Field _transactionList
     */
    private java.util.ArrayList _transactionList;


      //----------------/
     //- Constructors -/
    //----------------/

    public PostTransaction() 
     {
        super();
        _transactionList = new ArrayList();
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PostTransaction()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addTransaction
     * 
     * 
     * 
     * @param vTransaction
     */
    public void addTransaction(com.cdoframework.cdolib.service.framework.transfilter.schema.Transaction vTransaction)
        throws java.lang.IndexOutOfBoundsException
    {
        _transactionList.add(vTransaction);
    } //-- void addTransaction(com.cdoframework.cdolib.service.framework.transfilter.schema.Transaction) 

    /**
     * Method addTransaction
     * 
     * 
     * 
     * @param index
     * @param vTransaction
     */
    public void addTransaction(int index, com.cdoframework.cdolib.service.framework.transfilter.schema.Transaction vTransaction)
        throws java.lang.IndexOutOfBoundsException
    {
        _transactionList.add(index, vTransaction);
    } //-- void addTransaction(int, com.cdoframework.cdolib.service.framework.transfilter.schema.Transaction) 

    /**
     * Method clearTransaction
     * 
     */
    public void clearTransaction()
    {
        _transactionList.clear();
    } //-- void clearTransaction() 

    /**
     * Method deleteSyn
     * 
     */
    public void deleteSyn()
    {
        this._has_syn= false;
    } //-- void deleteSyn() 

    /**
     * Method enumerateTransaction
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateTransaction()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_transactionList.iterator());
    } //-- java.util.Enumeration enumerateTransaction() 

    /**
     * Returns the value of field 'requestCondition'.
     * 
     * @return RequestCondition
     * @return the value of field 'requestCondition'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.RequestCondition getRequestCondition()
    {
        return this._requestCondition;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.RequestCondition getRequestCondition() 

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
     * Returns the value of field 'returnCode'.
     * 
     * @return ReturnCode
     * @return the value of field 'returnCode'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.ReturnCode getReturnCode()
    {
        return this._returnCode;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.ReturnCode getReturnCode() 

    /**
     * Returns the value of field 'syn'.
     * 
     * @return boolean
     * @return the value of field 'syn'.
     */
    public boolean getSyn()
    {
        return this._syn;
    } //-- boolean getSyn() 

    /**
     * Method getTransaction
     * 
     * 
     * 
     * @param index
     * @return Transaction
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.Transaction getTransaction(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _transactionList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.Transaction) _transactionList.get(index);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.Transaction getTransaction(int) 

    /**
     * Method getTransaction
     * 
     * 
     * 
     * @return Transaction
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.Transaction[] getTransaction()
    {
        int size = _transactionList.size();
        com.cdoframework.cdolib.service.framework.transfilter.schema.Transaction[] mArray = new com.cdoframework.cdolib.service.framework.transfilter.schema.Transaction[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.service.framework.transfilter.schema.Transaction) _transactionList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.Transaction[] getTransaction() 

    /**
     * Method getTransactionCount
     * 
     * 
     * 
     * @return int
     */
    public int getTransactionCount()
    {
        return _transactionList.size();
    } //-- int getTransactionCount() 

    /**
     * Method hasSyn
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasSyn()
    {
        return this._has_syn;
    } //-- boolean hasSyn() 

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
     * Method removeTransaction
     * 
     * 
     * 
     * @param vTransaction
     * @return boolean
     */
    public boolean removeTransaction(com.cdoframework.cdolib.service.framework.transfilter.schema.Transaction vTransaction)
    {
        boolean removed = _transactionList.remove(vTransaction);
        return removed;
    } //-- boolean removeTransaction(com.cdoframework.cdolib.service.framework.transfilter.schema.Transaction) 

    /**
     * Sets the value of field 'requestCondition'.
     * 
     * @param requestCondition the value of field 'requestCondition'
     */
    public void setRequestCondition(com.cdoframework.cdolib.service.framework.transfilter.schema.RequestCondition requestCondition)
    {
        this._requestCondition = requestCondition;
    } //-- void setRequestCondition(com.cdoframework.cdolib.service.framework.transfilter.schema.RequestCondition) 

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
     * Sets the value of field 'returnCode'.
     * 
     * @param returnCode the value of field 'returnCode'.
     */
    public void setReturnCode(com.cdoframework.cdolib.service.framework.transfilter.schema.ReturnCode returnCode)
    {
        this._returnCode = returnCode;
    } //-- void setReturnCode(com.cdoframework.cdolib.service.framework.transfilter.schema.ReturnCode) 

    /**
     * Sets the value of field 'syn'.
     * 
     * @param syn the value of field 'syn'.
     */
    public void setSyn(boolean syn)
    {
        this._syn = syn;
        this._has_syn = true;
    } //-- void setSyn(boolean) 

    /**
     * Method setTransaction
     * 
     * 
     * 
     * @param index
     * @param vTransaction
     */
    public void setTransaction(int index, com.cdoframework.cdolib.service.framework.transfilter.schema.Transaction vTransaction)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _transactionList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _transactionList.set(index, vTransaction);
    } //-- void setTransaction(int, com.cdoframework.cdolib.service.framework.transfilter.schema.Transaction) 

    /**
     * Method setTransaction
     * 
     * 
     * 
     * @param transactionArray
     */
    public void setTransaction(com.cdoframework.cdolib.service.framework.transfilter.schema.Transaction[] transactionArray)
    {
        //-- copy array
        _transactionList.clear();
        for (int i = 0; i < transactionArray.length; i++) {
            _transactionList.add(transactionArray[i]);
        }
    } //-- void setTransaction(com.cdoframework.cdolib.service.framework.transfilter.schema.Transaction) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return PostTransaction
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.schema.PostTransaction unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.PostTransaction) Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.schema.PostTransaction.class, reader);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PostTransaction unmarshal(java.io.Reader) 

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
