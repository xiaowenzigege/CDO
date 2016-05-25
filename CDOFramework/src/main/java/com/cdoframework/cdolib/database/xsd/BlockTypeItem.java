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
public class BlockTypeItem implements java.io.Serializable {

    /**
     * Internal choice value storage
     */
    private java.lang.Object _choiceValue;

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
     * Field selectRecordSet.
     */
    private com.cdoframework.cdolib.database.xsd.SelectRecordSet selectRecordSet;

    /**
     * Field selectRecord.
     */
    private com.cdoframework.cdolib.database.xsd.SelectRecord selectRecord;

    /**
     * Field selectField.
     */
    private com.cdoframework.cdolib.database.xsd.SelectField selectField;

    /**
     * Field setVar.
     */
    private com.cdoframework.cdolib.database.xsd.SetVar setVar;

    /**
     * Field _if.
     */
    private com.cdoframework.cdolib.database.xsd.If _if;

    /**
     * Field _for.
     */
    private com.cdoframework.cdolib.database.xsd.For _for;

    /**
     * Field _return.
     */
    private com.cdoframework.cdolib.database.xsd.Return _return;

    /**
     * Field _break.
     */
    private java.lang.Object _break;

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

    public BlockTypeItem() {
        super();
    }

    /**
     * Returns the value of field 'break'.
     * 
     * @return the value of field 'Break'.
     */
    public java.lang.Object getBreak() {
        return this._break;
    }

    /**
     * Returns the value of field 'choiceValue'. The field
     * 'choiceValue' has the following description: Internal choice
     * value storage
     * 
     * @return the value of field 'ChoiceValue'.
     */
    public java.lang.Object getChoiceValue() {
        return this._choiceValue;
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
     * Returns the value of field 'return'.
     * 
     * @return the value of field 'Return'.
     */
    public com.cdoframework.cdolib.database.xsd.Return getReturn() {
        return this._return;
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
     * Sets the value of field 'break'.
     * 
     * @param _break
     * @param break the value of field 'break'.
     */
    public void setBreak(final java.lang.Object _break) {
        this._break = _break;
        this._choiceValue = _break;
    }

    /**
     * Sets the value of field 'commit'.
     * 
     * @param commit the value of field 'commit'.
     */
    public void setCommit(final java.lang.Object commit) {
        this.commit = commit;
        this._choiceValue = commit;
    }

    /**
     * Sets the value of field 'delete'.
     * 
     * @param delete the value of field 'delete'.
     */
    public void setDelete(final com.cdoframework.cdolib.database.xsd.Delete delete) {
        this.delete = delete;
        this._choiceValue = delete;
    }

    /**
     * Sets the value of field 'for'.
     * 
     * @param _for
     * @param for the value of field 'for'.
     */
    public void setFor(final com.cdoframework.cdolib.database.xsd.For _for) {
        this._for = _for;
        this._choiceValue = _for;
    }

    /**
     * Sets the value of field 'if'.
     * 
     * @param _if
     * @param if the value of field 'if'.
     */
    public void setIf(final com.cdoframework.cdolib.database.xsd.If _if) {
        this._if = _if;
        this._choiceValue = _if;
    }

    /**
     * Sets the value of field 'insert'.
     * 
     * @param insert the value of field 'insert'.
     */
    public void setInsert(final com.cdoframework.cdolib.database.xsd.Insert insert) {
        this.insert = insert;
        this._choiceValue = insert;
    }

    /**
     * Sets the value of field 'return'.
     * 
     * @param _return
     * @param return the value of field 'return'.
     */
    public void setReturn(final com.cdoframework.cdolib.database.xsd.Return _return) {
        this._return = _return;
        this._choiceValue = _return;
    }

    /**
     * Sets the value of field 'rollback'.
     * 
     * @param rollback the value of field 'rollback'.
     */
    public void setRollback(final java.lang.Object rollback) {
        this.rollback = rollback;
        this._choiceValue = rollback;
    }

    /**
     * Sets the value of field 'selectConnection'.
     * 
     * @param selectConnection the value of field 'selectConnection'
     */
    public void setSelectConnection(final com.cdoframework.cdolib.database.xsd.SelectConnection selectConnection) {
        this.selectConnection = selectConnection;
        this._choiceValue = selectConnection;
    }

    /**
     * Sets the value of field 'selectField'.
     * 
     * @param selectField the value of field 'selectField'.
     */
    public void setSelectField(final com.cdoframework.cdolib.database.xsd.SelectField selectField) {
        this.selectField = selectField;
        this._choiceValue = selectField;
    }

    /**
     * Sets the value of field 'selectRecord'.
     * 
     * @param selectRecord the value of field 'selectRecord'.
     */
    public void setSelectRecord(final com.cdoframework.cdolib.database.xsd.SelectRecord selectRecord) {
        this.selectRecord = selectRecord;
        this._choiceValue = selectRecord;
    }

    /**
     * Sets the value of field 'selectRecordSet'.
     * 
     * @param selectRecordSet the value of field 'selectRecordSet'.
     */
    public void setSelectRecordSet(final com.cdoframework.cdolib.database.xsd.SelectRecordSet selectRecordSet) {
        this.selectRecordSet = selectRecordSet;
        this._choiceValue = selectRecordSet;
    }

    /**
     * Sets the value of field 'setVar'.
     * 
     * @param setVar the value of field 'setVar'.
     */
    public void setSetVar(final com.cdoframework.cdolib.database.xsd.SetVar setVar) {
        this.setVar = setVar;
        this._choiceValue = setVar;
    }

    /**
     * Sets the value of field 'update'.
     * 
     * @param update the value of field 'update'.
     */
    public void setUpdate(final com.cdoframework.cdolib.database.xsd.Update update) {
        this.update = update;
        this._choiceValue = update;
    }

}
