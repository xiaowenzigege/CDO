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
public class SQLTransChoiceItem implements java.io.Serializable {

    /**
     * Field insert.
     */
    private com.cdoframework.cdolib.database.xsd.Insert insert;

    /**
     * Field update.
     */
    private com.cdoframework.cdolib.database.xsd.Update update;

    /**
     * Field delete.
     */
    private com.cdoframework.cdolib.database.xsd.Delete delete;

    /**
     * Field selectField.
     */
    private com.cdoframework.cdolib.database.xsd.SelectField selectField;

    /**
     * Field selectRecord.
     */
    private com.cdoframework.cdolib.database.xsd.SelectRecord selectRecord;

    /**
     * Field selectRecordSet.
     */
    private com.cdoframework.cdolib.database.xsd.SelectRecordSet selectRecordSet;

    /**
     * Field _if.
     */
    private com.cdoframework.cdolib.database.xsd.If _if;

    /**
     * Field _for.
     */
    private com.cdoframework.cdolib.database.xsd.For _for;

    /**
     * Field setVar.
     */
    private com.cdoframework.cdolib.database.xsd.SetVar setVar;

    /**
     * Field selectConnection.
     */
    private com.cdoframework.cdolib.database.xsd.SelectConnection selectConnection;

    /**
     * Field commit.
     */
    private java.lang.Object commit;

    /**
     * Field rollback.
     */
    private java.lang.Object rollback;

    public SQLTransChoiceItem() {
        super();
    }

    /**
     * Returns the value of field 'commit'.
     * 
     * @return the value of field 'Commit'.
     */
    public java.lang.Object getCommit() {
        return this.commit;
    }

    /**
     * Returns the value of field 'delete'.
     * 
     * @return the value of field 'Delete'.
     */
    public com.cdoframework.cdolib.database.xsd.Delete getDelete() {
        return this.delete;
    }

    /**
     * Returns the value of field 'for'.
     * 
     * @return the value of field 'For'.
     */
    public com.cdoframework.cdolib.database.xsd.For getFor() {
        return this._for;
    }

    /**
     * Returns the value of field 'if'.
     * 
     * @return the value of field 'If'.
     */
    public com.cdoframework.cdolib.database.xsd.If getIf() {
        return this._if;
    }

    /**
     * Returns the value of field 'insert'.
     * 
     * @return the value of field 'Insert'.
     */
    public com.cdoframework.cdolib.database.xsd.Insert getInsert() {
        return this.insert;
    }

    /**
     * Returns the value of field 'rollback'.
     * 
     * @return the value of field 'Rollback'.
     */
    public java.lang.Object getRollback() {
        return this.rollback;
    }

    /**
     * Returns the value of field 'selectConnection'.
     * 
     * @return the value of field 'SelectConnection'.
     */
    public com.cdoframework.cdolib.database.xsd.SelectConnection getSelectConnection() {
        return this.selectConnection;
    }

    /**
     * Returns the value of field 'selectField'.
     * 
     * @return the value of field 'SelectField'.
     */
    public com.cdoframework.cdolib.database.xsd.SelectField getSelectField() {
        return this.selectField;
    }

    /**
     * Returns the value of field 'selectRecord'.
     * 
     * @return the value of field 'SelectRecord'.
     */
    public com.cdoframework.cdolib.database.xsd.SelectRecord getSelectRecord() {
        return this.selectRecord;
    }

    /**
     * Returns the value of field 'selectRecordSet'.
     * 
     * @return the value of field 'SelectRecordSet'.
     */
    public com.cdoframework.cdolib.database.xsd.SelectRecordSet getSelectRecordSet() {
        return this.selectRecordSet;
    }

    /**
     * Returns the value of field 'setVar'.
     * 
     * @return the value of field 'SetVar'.
     */
    public com.cdoframework.cdolib.database.xsd.SetVar getSetVar() {
        return this.setVar;
    }

    /**
     * Returns the value of field 'update'.
     * 
     * @return the value of field 'Update'.
     */
    public com.cdoframework.cdolib.database.xsd.Update getUpdate() {
        return this.update;
    }

    /**
     * Sets the value of field 'commit'.
     * 
     * @param commit the value of field 'commit'.
     */
    public void setCommit(final java.lang.Object commit) {
        this.commit = commit;
    }

    /**
     * Sets the value of field 'delete'.
     * 
     * @param delete the value of field 'delete'.
     */
    public void setDelete(final com.cdoframework.cdolib.database.xsd.Delete delete) {
        this.delete = delete;
    }

    /**
     * Sets the value of field 'for'.
     * 
     * @param _for
     * @param for the value of field 'for'.
     */
    public void setFor(final com.cdoframework.cdolib.database.xsd.For _for) {
        this._for = _for;
    }

    /**
     * Sets the value of field 'if'.
     * 
     * @param _if
     * @param if the value of field 'if'.
     */
    public void setIf(final com.cdoframework.cdolib.database.xsd.If _if) {
        this._if = _if;
    }

    /**
     * Sets the value of field 'insert'.
     * 
     * @param insert the value of field 'insert'.
     */
    public void setInsert(final com.cdoframework.cdolib.database.xsd.Insert insert) {
        this.insert = insert;
    }

    /**
     * Sets the value of field 'rollback'.
     * 
     * @param rollback the value of field 'rollback'.
     */
    public void setRollback(final java.lang.Object rollback) {
        this.rollback = rollback;
    }

    /**
     * Sets the value of field 'selectConnection'.
     * 
     * @param selectConnection the value of field 'selectConnection'
     */
    public void setSelectConnection(final com.cdoframework.cdolib.database.xsd.SelectConnection selectConnection) {
        this.selectConnection = selectConnection;
    }

    /**
     * Sets the value of field 'selectField'.
     * 
     * @param selectField the value of field 'selectField'.
     */
    public void setSelectField(final com.cdoframework.cdolib.database.xsd.SelectField selectField) {
        this.selectField = selectField;
    }

    /**
     * Sets the value of field 'selectRecord'.
     * 
     * @param selectRecord the value of field 'selectRecord'.
     */
    public void setSelectRecord(final com.cdoframework.cdolib.database.xsd.SelectRecord selectRecord) {
        this.selectRecord = selectRecord;
    }

    /**
     * Sets the value of field 'selectRecordSet'.
     * 
     * @param selectRecordSet the value of field 'selectRecordSet'.
     */
    public void setSelectRecordSet(final com.cdoframework.cdolib.database.xsd.SelectRecordSet selectRecordSet) {
        this.selectRecordSet = selectRecordSet;
    }

    /**
     * Sets the value of field 'setVar'.
     * 
     * @param setVar the value of field 'setVar'.
     */
    public void setSetVar(final com.cdoframework.cdolib.database.xsd.SetVar setVar) {
        this.setVar = setVar;
    }

    /**
     * Sets the value of field 'update'.
     * 
     * @param update the value of field 'update'.
     */
    public void setUpdate(final com.cdoframework.cdolib.database.xsd.Update update) {
        this.update = update;
    }

}
