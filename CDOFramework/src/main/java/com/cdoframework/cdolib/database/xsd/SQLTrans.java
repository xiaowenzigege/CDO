/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.3</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.database.xsd;

/**
 * 
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class SQLTrans implements java.io.Serializable {

    /**
     * Field transName.
     */
    private java.lang.String transName;

    /**
     * Field transFlag.
     */
    private com.cdoframework.cdolib.database.xsd.types.SQLTransTransFlagType transFlag;

    /**
     * Field dataGroupId.
     */
    private java.lang.String dataGroupId;

    /**
     * Field bigTableGroupId.
     */
    private java.lang.String bigTableGroupId;

    /**
     * Field zkId.
     */
    private java.lang.String zkId;

    /**
     * Field SQLTransChoice.
     */
    private com.cdoframework.cdolib.database.xsd.SQLTransChoice SQLTransChoice;

    /**
     * Field onException.
     */
    private com.cdoframework.cdolib.database.xsd.OnException onException;

    /**
     * Field _return.
     */
    private com.cdoframework.cdolib.database.xsd.Return _return;

    public SQLTrans() {
        super();
    }

    /**
     * Returns the value of field 'bigTableGroupId'.
     * 
     * @return the value of field 'BigTableGroupId'.
     */
    public java.lang.String getBigTableGroupId() {
        return this.bigTableGroupId;
    }

    /**
     * Returns the value of field 'dataGroupId'.
     * 
     * @return the value of field 'DataGroupId'.
     */
    public java.lang.String getDataGroupId() {
        return this.dataGroupId;
    }

    /**
     * Returns the value of field 'onException'.
     * 
     * @return the value of field 'OnException'.
     */
    public com.cdoframework.cdolib.database.xsd.OnException getOnException() {
        return this.onException;
    }

    /**
     * Returns the value of field 'return'.
     * 
     * @return the value of field 'Return'.
     */
    public com.cdoframework.cdolib.database.xsd.Return getReturn() {
        return this._return;
    }

    /**
     * Returns the value of field 'SQLTransChoice'.
     * 
     * @return the value of field 'SQLTransChoice'.
     */
    public com.cdoframework.cdolib.database.xsd.SQLTransChoice getSQLTransChoice() {
        return this.SQLTransChoice;
    }

    /**
     * Returns the value of field 'transFlag'.
     * 
     * @return the value of field 'TransFlag'.
     */
    public com.cdoframework.cdolib.database.xsd.types.SQLTransTransFlagType getTransFlag() {
        return this.transFlag;
    }

    /**
     * Returns the value of field 'transName'.
     * 
     * @return the value of field 'TransName'.
     */
    public java.lang.String getTransName() {
        return this.transName;
    }

    /**
     * Returns the value of field 'zkId'.
     * 
     * @return the value of field 'ZkId'.
     */
    public java.lang.String getZkId() {
        return this.zkId;
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
     * Sets the value of field 'bigTableGroupId'.
     * 
     * @param bigTableGroupId the value of field 'bigTableGroupId'.
     */
    public void setBigTableGroupId(final java.lang.String bigTableGroupId) {
        this.bigTableGroupId = bigTableGroupId;
    }

    /**
     * Sets the value of field 'dataGroupId'.
     * 
     * @param dataGroupId the value of field 'dataGroupId'.
     */
    public void setDataGroupId(final java.lang.String dataGroupId) {
        this.dataGroupId = dataGroupId;
    }

    /**
     * Sets the value of field 'onException'.
     * 
     * @param onException the value of field 'onException'.
     */
    public void setOnException(final com.cdoframework.cdolib.database.xsd.OnException onException) {
        this.onException = onException;
    }

    /**
     * Sets the value of field 'return'.
     * 
     * @param _return
     * @param return the value of field 'return'.
     */
    public void setReturn(final com.cdoframework.cdolib.database.xsd.Return _return) {
        this._return = _return;
    }

    /**
     * Sets the value of field 'SQLTransChoice'.
     * 
     * @param SQLTransChoice the value of field 'SQLTransChoice'.
     */
    public void setSQLTransChoice(final com.cdoframework.cdolib.database.xsd.SQLTransChoice SQLTransChoice) {
        this.SQLTransChoice = SQLTransChoice;
    }

    /**
     * Sets the value of field 'transFlag'.
     * 
     * @param transFlag the value of field 'transFlag'.
     */
    public void setTransFlag(final com.cdoframework.cdolib.database.xsd.types.SQLTransTransFlagType transFlag) {
        this.transFlag = transFlag;
    }

    /**
     * Sets the value of field 'transName'.
     * 
     * @param transName the value of field 'transName'.
     */
    public void setTransName(final java.lang.String transName) {
        this.transName = transName;
    }

    /**
     * Sets the value of field 'zkId'.
     * 
     * @param zkId the value of field 'zkId'.
     */
    public void setZkId(final java.lang.String zkId) {
        this.zkId = zkId;
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
     * com.cdoframework.cdolib.database.xsd.SQLTrans
     */
    public static com.cdoframework.cdolib.database.xsd.SQLTrans unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.database.xsd.SQLTrans) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.database.xsd.SQLTrans.class, reader);
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
    
    /**
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
