/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.3</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.service.framework.transfilter.xsd;

/**
 * 
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class LogTransType implements java.io.Serializable {

    /**
     * operation name, can chinese.
     */
    private java.lang.String operationName;

    /**
     * operation type
     */
    private java.lang.String operationType;

    /**
     * cdoRequest key
     */
    private java.lang.String relationId;

    /**
     * cdoRequest key
     */
    private java.lang.String relationTitle;

    /**
     * cdoRequest key
     */
    private java.lang.String userId;

    /**
     * cdoRequest key
     */
    private java.lang.String userName;

    /**
     * default0,0~9
     */
    private int priority = 0;

    /**
     * Keeps track of whether primitive field priority has been set
     * already.
     */
    private boolean _haspriority;

    public LogTransType() {
        super();
    }

    /**
     */
    public void deletePriority() {
        this._haspriority= false;
    }

    /**
     * Returns the value of field 'operationName'. The field
     * 'operationName' has the following description: operation
     * name, can chinese.
     * 
     * @return the value of field 'OperationName'.
     */
    public java.lang.String getOperationName() {
        return this.operationName;
    }

    /**
     * Returns the value of field 'operationType'. The field
     * 'operationType' has the following description: operation
     * type
     * 
     * @return the value of field 'OperationType'.
     */
    public java.lang.String getOperationType() {
        return this.operationType;
    }

    /**
     * Returns the value of field 'priority'. The field 'priority'
     * has the following description: default0,0~9
     * 
     * @return the value of field 'Priority'.
     */
    public int getPriority() {
        return this.priority;
    }

    /**
     * Returns the value of field 'relationId'. The field
     * 'relationId' has the following description: cdoRequest key
     * 
     * @return the value of field 'RelationId'.
     */
    public java.lang.String getRelationId() {
        return this.relationId;
    }

    /**
     * Returns the value of field 'relationTitle'. The field
     * 'relationTitle' has the following description: cdoRequest
     * key
     * 
     * @return the value of field 'RelationTitle'.
     */
    public java.lang.String getRelationTitle() {
        return this.relationTitle;
    }

    /**
     * Returns the value of field 'userId'. The field 'userId' has
     * the following description: cdoRequest key
     * 
     * @return the value of field 'UserId'.
     */
    public java.lang.String getUserId() {
        return this.userId;
    }

    /**
     * Returns the value of field 'userName'. The field 'userName'
     * has the following description: cdoRequest key
     * 
     * @return the value of field 'UserName'.
     */
    public java.lang.String getUserName() {
        return this.userName;
    }

    /**
     * Method hasPriority.
     * 
     * @return true if at least one Priority has been added
     */
    public boolean hasPriority() {
        return this._haspriority;
    }

    /**
     * Method isValid.
     * 
     * @return true if this object is valid according to the schema
     */
    public boolean isValid() {
        try {
            validate();
        } catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    }

    /**
     * 
     * 
     * @param out
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void marshal(final java.io.Writer out) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Marshaller.marshal(this, out);
    }

    /**
     * 
     * 
     * @param handler
     * @throws java.io.IOException if an IOException occurs during
     * marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     */
    public void marshal(final org.xml.sax.ContentHandler handler) throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Marshaller.marshal(this, handler);
    }

    /**
     * Sets the value of field 'operationName'. The field
     * 'operationName' has the following description: operation
     * name, can chinese.
     * 
     * @param operationName the value of field 'operationName'.
     */
    public void setOperationName(final java.lang.String operationName) {
        this.operationName = operationName;
    }

    /**
     * Sets the value of field 'operationType'. The field
     * 'operationType' has the following description: operation
     * type
     * 
     * @param operationType the value of field 'operationType'.
     */
    public void setOperationType(final java.lang.String operationType) {
        this.operationType = operationType;
    }

    /**
     * Sets the value of field 'priority'. The field 'priority' has
     * the following description: default0,0~9
     * 
     * @param priority the value of field 'priority'.
     */
    public void setPriority(final int priority) {
        this.priority = priority;
        this._haspriority = true;
    }

    /**
     * Sets the value of field 'relationId'. The field 'relationId'
     * has the following description: cdoRequest key
     * 
     * @param relationId the value of field 'relationId'.
     */
    public void setRelationId(final java.lang.String relationId) {
        this.relationId = relationId;
    }

    /**
     * Sets the value of field 'relationTitle'. The field
     * 'relationTitle' has the following description: cdoRequest
     * key
     * 
     * @param relationTitle the value of field 'relationTitle'.
     */
    public void setRelationTitle(final java.lang.String relationTitle) {
        this.relationTitle = relationTitle;
    }

    /**
     * Sets the value of field 'userId'. The field 'userId' has the
     * following description: cdoRequest key
     * 
     * @param userId the value of field 'userId'.
     */
    public void setUserId(final java.lang.String userId) {
        this.userId = userId;
    }

    /**
     * Sets the value of field 'userName'. The field 'userName' has
     * the following description: cdoRequest key
     * 
     * @param userName the value of field 'userName'.
     */
    public void setUserName(final java.lang.String userName) {
        this.userName = userName;
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled
     * com.cdoframework.cdolib.service.framework.transfilter.xsd.LogTransType
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.xsd.LogTransType unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.service.framework.transfilter.xsd.LogTransType) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.xsd.LogTransType.class, reader);
    }

    /**
     * 
     * 
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void validate() throws org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    }

}
