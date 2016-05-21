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

import com.cdoframework.cdolib.database.dataservice.types.SQLTransTransFlagType;
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
 * Class SQLTrans.
 * 
 * @version $Revision$ $Date$
 */
public class SQLTrans implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _transName
     */
    private java.lang.String _transName;

    /**
     * Field _transFlag
     */
    private com.cdoframework.cdolib.database.dataservice.types.SQLTransTransFlagType _transFlag;

    /**
     * Field _dataGroupId
     */
    private java.lang.String _dataGroupId;

    /**
     * Field _bigTableGroupId
     */
    private java.lang.String _bigTableGroupId;

    /**
     * Field _SQLTransChoiceList
     */
    private java.util.ArrayList _SQLTransChoiceList;

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

    public SQLTrans() 
     {
        super();
        _SQLTransChoiceList = new ArrayList();
    } //-- com.cdoframework.cdolib.database.dataservice.SQLTrans()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addSQLTransChoice
     * 
     * 
     * 
     * @param vSQLTransChoice
     */
    public void addSQLTransChoice(com.cdoframework.cdolib.database.dataservice.SQLTransChoice vSQLTransChoice)
        throws java.lang.IndexOutOfBoundsException
    {
        _SQLTransChoiceList.add(vSQLTransChoice);
    } //-- void addSQLTransChoice(com.cdoframework.cdolib.database.dataservice.SQLTransChoice) 

    /**
     * Method addSQLTransChoice
     * 
     * 
     * 
     * @param index
     * @param vSQLTransChoice
     */
    public void addSQLTransChoice(int index, com.cdoframework.cdolib.database.dataservice.SQLTransChoice vSQLTransChoice)
        throws java.lang.IndexOutOfBoundsException
    {
        _SQLTransChoiceList.add(index, vSQLTransChoice);
    } //-- void addSQLTransChoice(int, com.cdoframework.cdolib.database.dataservice.SQLTransChoice) 

    /**
     * Method clearSQLTransChoice
     * 
     */
    public void clearSQLTransChoice()
    {
        _SQLTransChoiceList.clear();
    } //-- void clearSQLTransChoice() 

    /**
     * Method enumerateSQLTransChoice
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateSQLTransChoice()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_SQLTransChoiceList.iterator());
    } //-- java.util.Enumeration enumerateSQLTransChoice() 

    /**
     * Returns the value of field 'bigTableGroupId'.
     * 
     * @return String
     * @return the value of field 'bigTableGroupId'.
     */
    public java.lang.String getBigTableGroupId()
    {
        return this._bigTableGroupId;
    } //-- java.lang.String getBigTableGroupId() 

    /**
     * Returns the value of field 'dataGroupId'.
     * 
     * @return String
     * @return the value of field 'dataGroupId'.
     */
    public java.lang.String getDataGroupId()
    {
        return this._dataGroupId;
    } //-- java.lang.String getDataGroupId() 

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
     * Method getSQLTransChoice
     * 
     * 
     * 
     * @param index
     * @return SQLTransChoice
     */
    public com.cdoframework.cdolib.database.dataservice.SQLTransChoice getSQLTransChoice(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _SQLTransChoiceList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.database.dataservice.SQLTransChoice) _SQLTransChoiceList.get(index);
    } //-- com.cdoframework.cdolib.database.dataservice.SQLTransChoice getSQLTransChoice(int) 

    /**
     * Method getSQLTransChoice
     * 
     * 
     * 
     * @return SQLTransChoice
     */
    public com.cdoframework.cdolib.database.dataservice.SQLTransChoice[] getSQLTransChoice()
    {
        int size = _SQLTransChoiceList.size();
        com.cdoframework.cdolib.database.dataservice.SQLTransChoice[] mArray = new com.cdoframework.cdolib.database.dataservice.SQLTransChoice[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.database.dataservice.SQLTransChoice) _SQLTransChoiceList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.database.dataservice.SQLTransChoice[] getSQLTransChoice() 

    /**
     * Method getSQLTransChoiceCount
     * 
     * 
     * 
     * @return int
     */
    public int getSQLTransChoiceCount()
    {
        return _SQLTransChoiceList.size();
    } //-- int getSQLTransChoiceCount() 

    /**
     * Returns the value of field 'transFlag'.
     * 
     * @return SQLTransTransFlagType
     * @return the value of field 'transFlag'.
     */
    public com.cdoframework.cdolib.database.dataservice.types.SQLTransTransFlagType getTransFlag()
    {
        return this._transFlag;
    } //-- com.cdoframework.cdolib.database.dataservice.types.SQLTransTransFlagType getTransFlag() 

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
     * Method removeSQLTransChoice
     * 
     * 
     * 
     * @param vSQLTransChoice
     * @return boolean
     */
    public boolean removeSQLTransChoice(com.cdoframework.cdolib.database.dataservice.SQLTransChoice vSQLTransChoice)
    {
        boolean removed = _SQLTransChoiceList.remove(vSQLTransChoice);
        return removed;
    } //-- boolean removeSQLTransChoice(com.cdoframework.cdolib.database.dataservice.SQLTransChoice) 

    /**
     * Sets the value of field 'bigTableGroupId'.
     * 
     * @param bigTableGroupId the value of field 'bigTableGroupId'.
     */
    public void setBigTableGroupId(java.lang.String bigTableGroupId)
    {
        this._bigTableGroupId = bigTableGroupId;
    } //-- void setBigTableGroupId(java.lang.String) 

    /**
     * Sets the value of field 'dataGroupId'.
     * 
     * @param dataGroupId the value of field 'dataGroupId'.
     */
    public void setDataGroupId(java.lang.String dataGroupId)
    {
        this._dataGroupId = dataGroupId;
    } //-- void setDataGroupId(java.lang.String) 

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
     * Method setSQLTransChoice
     * 
     * 
     * 
     * @param index
     * @param vSQLTransChoice
     */
    public void setSQLTransChoice(int index, com.cdoframework.cdolib.database.dataservice.SQLTransChoice vSQLTransChoice)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _SQLTransChoiceList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _SQLTransChoiceList.set(index, vSQLTransChoice);
    } //-- void setSQLTransChoice(int, com.cdoframework.cdolib.database.dataservice.SQLTransChoice) 

    /**
     * Method setSQLTransChoice
     * 
     * 
     * 
     * @param SQLTransChoiceArray
     */
    public void setSQLTransChoice(com.cdoframework.cdolib.database.dataservice.SQLTransChoice[] SQLTransChoiceArray)
    {
        //-- copy array
        _SQLTransChoiceList.clear();
        for (int i = 0; i < SQLTransChoiceArray.length; i++) {
            _SQLTransChoiceList.add(SQLTransChoiceArray[i]);
        }
    } //-- void setSQLTransChoice(com.cdoframework.cdolib.database.dataservice.SQLTransChoice) 

    /**
     * Sets the value of field 'transFlag'.
     * 
     * @param transFlag the value of field 'transFlag'.
     */
    public void setTransFlag(com.cdoframework.cdolib.database.dataservice.types.SQLTransTransFlagType transFlag)
    {
        this._transFlag = transFlag;
    } //-- void setTransFlag(com.cdoframework.cdolib.database.dataservice.types.SQLTransTransFlagType) 

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
     * @return SQLTrans
     */
    public static com.cdoframework.cdolib.database.dataservice.SQLTrans unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.database.dataservice.SQLTrans) Unmarshaller.unmarshal(com.cdoframework.cdolib.database.dataservice.SQLTrans.class, reader);
    } //-- com.cdoframework.cdolib.database.dataservice.SQLTrans unmarshal(java.io.Reader) 

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

    /**
     * ************************************************************************************************************************************
     * ************************************************************************************************************************************
     * *********************************************************Manual code  **************************************************************
     * ************************************************************************************************************************************
     * ************************************************************************************************************************************
     */    
    private DataService dataService;


	public DataService getDataService()
	{
		return dataService;
	}


	public void setDataService(DataService dataService)
	{
		this.dataService=dataService;
	}
}
