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

import com.cdoframework.cdolib.database.dataservice.types.NoSQLTransTypeOperatorType;
import com.cdoframework.cdolib.database.dataservice.types.NoSQLTransTypeTypeType;
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
 * Class NoSQLTransType.
 * 
 * @version $Revision$ $Date$
 */
public class NoSQLTransType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _transName
     */
    private java.lang.String _transName;

    /**
     * Field _value1
     */
    private java.lang.String _value1;

    /**
     * Field _operator
     */
    private com.cdoframework.cdolib.database.dataservice.types.NoSQLTransTypeOperatorType _operator;

    /**
     * Field _value2
     */
    private java.lang.String _value2;

    /**
     * Field _type
     */
    private com.cdoframework.cdolib.database.dataservice.types.NoSQLTransTypeTypeType _type;

    /**
     * Field _noSQLTransTypeChoiceList
     */
    private java.util.ArrayList _noSQLTransTypeChoiceList;

    /**
     * Field _onException
     */
    private com.cdoframework.cdolib.database.dataservice.OnException _onException;

    /**
     * Field _return
     */
    private com.cdoframework.cdolib.database.dataservice.Return _return;


      //----------------/
     //- Constructors -/
    //----------------/

    public NoSQLTransType() 
     {
        super();
        _noSQLTransTypeChoiceList = new ArrayList();
    } //-- com.cdoframework.cdolib.database.dataservice.NoSQLTransType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addNoSQLTransTypeChoice
     * 
     * 
     * 
     * @param vNoSQLTransTypeChoice
     */
    public void addNoSQLTransTypeChoice(com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoice vNoSQLTransTypeChoice)
        throws java.lang.IndexOutOfBoundsException
    {
        _noSQLTransTypeChoiceList.add(vNoSQLTransTypeChoice);
    } //-- void addNoSQLTransTypeChoice(com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoice) 

    /**
     * Method addNoSQLTransTypeChoice
     * 
     * 
     * 
     * @param index
     * @param vNoSQLTransTypeChoice
     */
    public void addNoSQLTransTypeChoice(int index, com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoice vNoSQLTransTypeChoice)
        throws java.lang.IndexOutOfBoundsException
    {
        _noSQLTransTypeChoiceList.add(index, vNoSQLTransTypeChoice);
    } //-- void addNoSQLTransTypeChoice(int, com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoice) 

    /**
     * Method clearNoSQLTransTypeChoice
     * 
     */
    public void clearNoSQLTransTypeChoice()
    {
        _noSQLTransTypeChoiceList.clear();
    } //-- void clearNoSQLTransTypeChoice() 

    /**
     * Method enumerateNoSQLTransTypeChoice
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateNoSQLTransTypeChoice()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_noSQLTransTypeChoiceList.iterator());
    } //-- java.util.Enumeration enumerateNoSQLTransTypeChoice() 

    /**
     * Method getNoSQLTransTypeChoice
     * 
     * 
     * 
     * @param index
     * @return NoSQLTransTypeChoice
     */
    public com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoice getNoSQLTransTypeChoice(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _noSQLTransTypeChoiceList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoice) _noSQLTransTypeChoiceList.get(index);
    } //-- com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoice getNoSQLTransTypeChoice(int) 

    /**
     * Method getNoSQLTransTypeChoice
     * 
     * 
     * 
     * @return NoSQLTransTypeChoice
     */
    public com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoice[] getNoSQLTransTypeChoice()
    {
        int size = _noSQLTransTypeChoiceList.size();
        com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoice[] mArray = new com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoice[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoice) _noSQLTransTypeChoiceList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoice[] getNoSQLTransTypeChoice() 

    /**
     * Method getNoSQLTransTypeChoiceCount
     * 
     * 
     * 
     * @return int
     */
    public int getNoSQLTransTypeChoiceCount()
    {
        return _noSQLTransTypeChoiceList.size();
    } //-- int getNoSQLTransTypeChoiceCount() 

    /**
     * Returns the value of field 'onException'.
     * 
     * @return OnException
     * @return the value of field 'onException'.
     */
    public com.cdoframework.cdolib.database.dataservice.OnException getOnException()
    {
        return this._onException;
    } //-- com.cdoframework.cdolib.database.dataservice.OnException getOnException() 

    /**
     * Returns the value of field 'operator'.
     * 
     * @return NoSQLTransTypeOperatorType
     * @return the value of field 'operator'.
     */
    public com.cdoframework.cdolib.database.dataservice.types.NoSQLTransTypeOperatorType getOperator()
    {
        return this._operator;
    } //-- com.cdoframework.cdolib.database.dataservice.types.NoSQLTransTypeOperatorType getOperator() 

    /**
     * Returns the value of field 'return'.
     * 
     * @return Return
     * @return the value of field 'return'.
     */
    public com.cdoframework.cdolib.database.dataservice.Return getReturn()
    {
        return this._return;
    } //-- com.cdoframework.cdolib.database.dataservice.Return getReturn() 

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
     * Returns the value of field 'type'.
     * 
     * @return NoSQLTransTypeTypeType
     * @return the value of field 'type'.
     */
    public com.cdoframework.cdolib.database.dataservice.types.NoSQLTransTypeTypeType getType()
    {
        return this._type;
    } //-- com.cdoframework.cdolib.database.dataservice.types.NoSQLTransTypeTypeType getType() 

    /**
     * Returns the value of field 'value1'.
     * 
     * @return String
     * @return the value of field 'value1'.
     */
    public java.lang.String getValue1()
    {
        return this._value1;
    } //-- java.lang.String getValue1() 

    /**
     * Returns the value of field 'value2'.
     * 
     * @return String
     * @return the value of field 'value2'.
     */
    public java.lang.String getValue2()
    {
        return this._value2;
    } //-- java.lang.String getValue2() 

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
     * Method removeNoSQLTransTypeChoice
     * 
     * 
     * 
     * @param vNoSQLTransTypeChoice
     * @return boolean
     */
    public boolean removeNoSQLTransTypeChoice(com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoice vNoSQLTransTypeChoice)
    {
        boolean removed = _noSQLTransTypeChoiceList.remove(vNoSQLTransTypeChoice);
        return removed;
    } //-- boolean removeNoSQLTransTypeChoice(com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoice) 

    /**
     * Method setNoSQLTransTypeChoice
     * 
     * 
     * 
     * @param index
     * @param vNoSQLTransTypeChoice
     */
    public void setNoSQLTransTypeChoice(int index, com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoice vNoSQLTransTypeChoice)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _noSQLTransTypeChoiceList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _noSQLTransTypeChoiceList.set(index, vNoSQLTransTypeChoice);
    } //-- void setNoSQLTransTypeChoice(int, com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoice) 

    /**
     * Method setNoSQLTransTypeChoice
     * 
     * 
     * 
     * @param noSQLTransTypeChoiceArray
     */
    public void setNoSQLTransTypeChoice(com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoice[] noSQLTransTypeChoiceArray)
    {
        //-- copy array
        _noSQLTransTypeChoiceList.clear();
        for (int i = 0; i < noSQLTransTypeChoiceArray.length; i++) {
            _noSQLTransTypeChoiceList.add(noSQLTransTypeChoiceArray[i]);
        }
    } //-- void setNoSQLTransTypeChoice(com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoice) 

    /**
     * Sets the value of field 'onException'.
     * 
     * @param onException the value of field 'onException'.
     */
    public void setOnException(com.cdoframework.cdolib.database.dataservice.OnException onException)
    {
        this._onException = onException;
    } //-- void setOnException(com.cdoframework.cdolib.database.dataservice.OnException) 

    /**
     * Sets the value of field 'operator'.
     * 
     * @param operator the value of field 'operator'.
     */
    public void setOperator(com.cdoframework.cdolib.database.dataservice.types.NoSQLTransTypeOperatorType operator)
    {
        this._operator = operator;
    } //-- void setOperator(com.cdoframework.cdolib.database.dataservice.types.NoSQLTransTypeOperatorType) 

    /**
     * Sets the value of field 'return'.
     * 
     * @param _return
     * @param return the value of field 'return'.
     */
    public void setReturn(com.cdoframework.cdolib.database.dataservice.Return _return)
    {
        this._return = _return;
    } //-- void setReturn(com.cdoframework.cdolib.database.dataservice.Return) 

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
     * Sets the value of field 'type'.
     * 
     * @param type the value of field 'type'.
     */
    public void setType(com.cdoframework.cdolib.database.dataservice.types.NoSQLTransTypeTypeType type)
    {
        this._type = type;
    } //-- void setType(com.cdoframework.cdolib.database.dataservice.types.NoSQLTransTypeTypeType) 

    /**
     * Sets the value of field 'value1'.
     * 
     * @param value1 the value of field 'value1'.
     */
    public void setValue1(java.lang.String value1)
    {
        this._value1 = value1;
    } //-- void setValue1(java.lang.String) 

    /**
     * Sets the value of field 'value2'.
     * 
     * @param value2 the value of field 'value2'.
     */
    public void setValue2(java.lang.String value2)
    {
        this._value2 = value2;
    } //-- void setValue2(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return NoSQLTransType
     */
    public static com.cdoframework.cdolib.database.dataservice.NoSQLTransType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.database.dataservice.NoSQLTransType) Unmarshaller.unmarshal(com.cdoframework.cdolib.database.dataservice.NoSQLTransType.class, reader);
    } //-- com.cdoframework.cdolib.database.dataservice.NoSQLTransType unmarshal(java.io.Reader) 

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
