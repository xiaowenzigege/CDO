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
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

/**
 * Class LogTransType.
 * 
 * @version $Revision$ $Date$
 */
public class LogTransType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * operation name, can chinese.
     */
    private java.lang.String _operationName;

    /**
     * operation type
     */
    private java.lang.String _operationType;

    /**
     * cdoRequest key
     */
    private java.lang.String _relationId;

    /**
     * cdoRequest key
     */
    private java.lang.String _relationTitle;

    /**
     * cdoRequest key
     */
    private java.lang.String _userId;

    /**
     * cdoRequest key
     */
    private java.lang.String _userName;

    /**
     * default0,0~9
     */
    private int _priority = 0;

    /**
     * keeps track of state for field: _priority
     */
    private boolean _has_priority;


      //----------------/
     //- Constructors -/
    //----------------/

    public LogTransType() 
     {
        super();
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.LogTransType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method deletePriority
     * 
     */
    public void deletePriority()
    {
        this._has_priority= false;
    } //-- void deletePriority() 

    /**
     * Returns the value of field 'operationName'. The field
     * 'operationName' has the following description: operation
     * name, can chinese.
     * 
     * @return String
     * @return the value of field 'operationName'.
     */
    public java.lang.String getOperationName()
    {
        return this._operationName;
    } //-- java.lang.String getOperationName() 

    /**
     * Returns the value of field 'operationType'. The field
     * 'operationType' has the following description: operation
     * type
     * 
     * @return String
     * @return the value of field 'operationType'.
     */
    public java.lang.String getOperationType()
    {
        return this._operationType;
    } //-- java.lang.String getOperationType() 

    /**
     * Returns the value of field 'priority'. The field 'priority'
     * has the following description: default0,0~9
     * 
     * @return int
     * @return the value of field 'priority'.
     */
    public int getPriority()
    {
        return this._priority;
    } //-- int getPriority() 

    /**
     * Returns the value of field 'relationId'. The field
     * 'relationId' has the following description: cdoRequest key
     * 
     * @return String
     * @return the value of field 'relationId'.
     */
    public java.lang.String getRelationId()
    {
        return this._relationId;
    } //-- java.lang.String getRelationId() 

    /**
     * Returns the value of field 'relationTitle'. The field
     * 'relationTitle' has the following description: cdoRequest
     * key
     * 
     * @return String
     * @return the value of field 'relationTitle'.
     */
    public java.lang.String getRelationTitle()
    {
        return this._relationTitle;
    } //-- java.lang.String getRelationTitle() 

    /**
     * Returns the value of field 'userId'. The field 'userId' has
     * the following description: cdoRequest key
     * 
     * @return String
     * @return the value of field 'userId'.
     */
    public java.lang.String getUserId()
    {
        return this._userId;
    } //-- java.lang.String getUserId() 

    /**
     * Returns the value of field 'userName'. The field 'userName'
     * has the following description: cdoRequest key
     * 
     * @return String
     * @return the value of field 'userName'.
     */
    public java.lang.String getUserName()
    {
        return this._userName;
    } //-- java.lang.String getUserName() 

    /**
     * Method hasPriority
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasPriority()
    {
        return this._has_priority;
    } //-- boolean hasPriority() 

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
     * Sets the value of field 'operationName'. The field
     * 'operationName' has the following description: operation
     * name, can chinese.
     * 
     * @param operationName the value of field 'operationName'.
     */
    public void setOperationName(java.lang.String operationName)
    {
        this._operationName = operationName;
    } //-- void setOperationName(java.lang.String) 

    /**
     * Sets the value of field 'operationType'. The field
     * 'operationType' has the following description: operation
     * type
     * 
     * @param operationType the value of field 'operationType'.
     */
    public void setOperationType(java.lang.String operationType)
    {
        this._operationType = operationType;
    } //-- void setOperationType(java.lang.String) 

    /**
     * Sets the value of field 'priority'. The field 'priority' has
     * the following description: default0,0~9
     * 
     * @param priority the value of field 'priority'.
     */
    public void setPriority(int priority)
    {
        this._priority = priority;
        this._has_priority = true;
    } //-- void setPriority(int) 

    /**
     * Sets the value of field 'relationId'. The field 'relationId'
     * has the following description: cdoRequest key
     * 
     * @param relationId the value of field 'relationId'.
     */
    public void setRelationId(java.lang.String relationId)
    {
        this._relationId = relationId;
    } //-- void setRelationId(java.lang.String) 

    /**
     * Sets the value of field 'relationTitle'. The field
     * 'relationTitle' has the following description: cdoRequest
     * key
     * 
     * @param relationTitle the value of field 'relationTitle'.
     */
    public void setRelationTitle(java.lang.String relationTitle)
    {
        this._relationTitle = relationTitle;
    } //-- void setRelationTitle(java.lang.String) 

    /**
     * Sets the value of field 'userId'. The field 'userId' has the
     * following description: cdoRequest key
     * 
     * @param userId the value of field 'userId'.
     */
    public void setUserId(java.lang.String userId)
    {
        this._userId = userId;
    } //-- void setUserId(java.lang.String) 

    /**
     * Sets the value of field 'userName'. The field 'userName' has
     * the following description: cdoRequest key
     * 
     * @param userName the value of field 'userName'.
     */
    public void setUserName(java.lang.String userName)
    {
        this._userName = userName;
    } //-- void setUserName(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return LogTransType
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.schema.LogTransType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.LogTransType) Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.schema.LogTransType.class, reader);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.LogTransType unmarshal(java.io.Reader) 

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
