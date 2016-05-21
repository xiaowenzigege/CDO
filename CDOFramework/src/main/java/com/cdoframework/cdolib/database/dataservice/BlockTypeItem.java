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

import java.io.Serializable;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class BlockTypeItem.
 * 
 * @version $Revision$ $Date$
 */
public class BlockTypeItem implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Internal choice value storage
     */
    private java.lang.Object _choiceValue;

    /**
     * Field _insert
     */
    private com.cdoframework.cdolib.database.dataservice.Insert _insert;

    /**
     * Field _update
     */
    private com.cdoframework.cdolib.database.dataservice.Update _update;

    /**
     * Field _delete
     */
    private com.cdoframework.cdolib.database.dataservice.Delete _delete;

    /**
     * Field _selectRecordSet
     */
    private com.cdoframework.cdolib.database.dataservice.SelectRecordSet _selectRecordSet;

    /**
     * Field _selectRecord
     */
    private com.cdoframework.cdolib.database.dataservice.SelectRecord _selectRecord;

    /**
     * Field _selectField
     */
    private com.cdoframework.cdolib.database.dataservice.SelectField _selectField;

    /**
     * Field _setVar
     */
    private com.cdoframework.cdolib.database.dataservice.SetVar _setVar;

    /**
     * Field _if
     */
    private com.cdoframework.cdolib.database.dataservice.If _if;

    /**
     * Field _for
     */
    private com.cdoframework.cdolib.database.dataservice.For _for;

    /**
     * Field _return
     */
    private com.cdoframework.cdolib.database.dataservice.Return _return;

    /**
     * Field _break
     */
    private java.lang.Object _break;

    /**
     * Field _selectConnection
     */
    private com.cdoframework.cdolib.database.dataservice.SelectConnection _selectConnection;

    /**
     * Field _commit
     */
    private java.lang.Object _commit;

    /**
     * Field _rollback
     */
    private java.lang.Object _rollback;


      //----------------/
     //- Constructors -/
    //----------------/

    public BlockTypeItem() 
     {
        super();
    } //-- com.cdoframework.cdolib.database.dataservice.BlockTypeItem()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'break'.
     * 
     * @return Object
     * @return the value of field 'break'.
     */
    public java.lang.Object getBreak()
    {
        return this._break;
    } //-- java.lang.Object getBreak() 

    /**
     * Returns the value of field 'choiceValue'. The field
     * 'choiceValue' has the following description: Internal choice
     * value storage
     * 
     * @return Object
     * @return the value of field 'choiceValue'.
     */
    public java.lang.Object getChoiceValue()
    {
        return this._choiceValue;
    } //-- java.lang.Object getChoiceValue() 

    /**
     * Returns the value of field 'commit'.
     * 
     * @return Object
     * @return the value of field 'commit'.
     */
    public java.lang.Object getCommit()
    {
        return this._commit;
    } //-- java.lang.Object getCommit() 

    /**
     * Returns the value of field 'delete'.
     * 
     * @return Delete
     * @return the value of field 'delete'.
     */
    public com.cdoframework.cdolib.database.dataservice.Delete getDelete()
    {
        return this._delete;
    } //-- com.cdoframework.cdolib.database.dataservice.Delete getDelete() 

    /**
     * Returns the value of field 'for'.
     * 
     * @return For
     * @return the value of field 'for'.
     */
    public com.cdoframework.cdolib.database.dataservice.For getFor()
    {
        return this._for;
    } //-- com.cdoframework.cdolib.database.dataservice.For getFor() 

    /**
     * Returns the value of field 'if'.
     * 
     * @return If
     * @return the value of field 'if'.
     */
    public com.cdoframework.cdolib.database.dataservice.If getIf()
    {
        return this._if;
    } //-- com.cdoframework.cdolib.database.dataservice.If getIf() 

    /**
     * Returns the value of field 'insert'.
     * 
     * @return Insert
     * @return the value of field 'insert'.
     */
    public com.cdoframework.cdolib.database.dataservice.Insert getInsert()
    {
        return this._insert;
    } //-- com.cdoframework.cdolib.database.dataservice.Insert getInsert() 

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
     * Returns the value of field 'rollback'.
     * 
     * @return Object
     * @return the value of field 'rollback'.
     */
    public java.lang.Object getRollback()
    {
        return this._rollback;
    } //-- java.lang.Object getRollback() 

    /**
     * Returns the value of field 'selectConnection'.
     * 
     * @return SelectConnection
     * @return the value of field 'selectConnection'.
     */
    public com.cdoframework.cdolib.database.dataservice.SelectConnection getSelectConnection()
    {
        return this._selectConnection;
    } //-- com.cdoframework.cdolib.database.dataservice.SelectConnection getSelectConnection() 

    /**
     * Returns the value of field 'selectField'.
     * 
     * @return SelectField
     * @return the value of field 'selectField'.
     */
    public com.cdoframework.cdolib.database.dataservice.SelectField getSelectField()
    {
        return this._selectField;
    } //-- com.cdoframework.cdolib.database.dataservice.SelectField getSelectField() 

    /**
     * Returns the value of field 'selectRecord'.
     * 
     * @return SelectRecord
     * @return the value of field 'selectRecord'.
     */
    public com.cdoframework.cdolib.database.dataservice.SelectRecord getSelectRecord()
    {
        return this._selectRecord;
    } //-- com.cdoframework.cdolib.database.dataservice.SelectRecord getSelectRecord() 

    /**
     * Returns the value of field 'selectRecordSet'.
     * 
     * @return SelectRecordSet
     * @return the value of field 'selectRecordSet'.
     */
    public com.cdoframework.cdolib.database.dataservice.SelectRecordSet getSelectRecordSet()
    {
        return this._selectRecordSet;
    } //-- com.cdoframework.cdolib.database.dataservice.SelectRecordSet getSelectRecordSet() 

    /**
     * Returns the value of field 'setVar'.
     * 
     * @return SetVar
     * @return the value of field 'setVar'.
     */
    public com.cdoframework.cdolib.database.dataservice.SetVar getSetVar()
    {
        return this._setVar;
    } //-- com.cdoframework.cdolib.database.dataservice.SetVar getSetVar() 

    /**
     * Returns the value of field 'update'.
     * 
     * @return Update
     * @return the value of field 'update'.
     */
    public com.cdoframework.cdolib.database.dataservice.Update getUpdate()
    {
        return this._update;
    } //-- com.cdoframework.cdolib.database.dataservice.Update getUpdate() 

    /**
     * Sets the value of field 'break'.
     * 
     * @param _break
     * @param break the value of field 'break'.
     */
    public void setBreak(java.lang.Object _break)
    {
        this._break = _break;
        this._choiceValue = _break;
    } //-- void setBreak(java.lang.Object) 

    /**
     * Sets the value of field 'commit'.
     * 
     * @param commit the value of field 'commit'.
     */
    public void setCommit(java.lang.Object commit)
    {
        this._commit = commit;
        this._choiceValue = commit;
    } //-- void setCommit(java.lang.Object) 

    /**
     * Sets the value of field 'delete'.
     * 
     * @param delete the value of field 'delete'.
     */
    public void setDelete(com.cdoframework.cdolib.database.dataservice.Delete delete)
    {
        this._delete = delete;
        this._choiceValue = delete;
    } //-- void setDelete(com.cdoframework.cdolib.database.dataservice.Delete) 

    /**
     * Sets the value of field 'for'.
     * 
     * @param _for
     * @param for the value of field 'for'.
     */
    public void setFor(com.cdoframework.cdolib.database.dataservice.For _for)
    {
        this._for = _for;
        this._choiceValue = _for;
    } //-- void setFor(com.cdoframework.cdolib.database.dataservice.For) 

    /**
     * Sets the value of field 'if'.
     * 
     * @param _if
     * @param if the value of field 'if'.
     */
    public void setIf(com.cdoframework.cdolib.database.dataservice.If _if)
    {
        this._if = _if;
        this._choiceValue = _if;
    } //-- void setIf(com.cdoframework.cdolib.database.dataservice.If) 

    /**
     * Sets the value of field 'insert'.
     * 
     * @param insert the value of field 'insert'.
     */
    public void setInsert(com.cdoframework.cdolib.database.dataservice.Insert insert)
    {
        this._insert = insert;
        this._choiceValue = insert;
    } //-- void setInsert(com.cdoframework.cdolib.database.dataservice.Insert) 

    /**
     * Sets the value of field 'return'.
     * 
     * @param _return
     * @param return the value of field 'return'.
     */
    public void setReturn(com.cdoframework.cdolib.database.dataservice.Return _return)
    {
        this._return = _return;
        this._choiceValue = _return;
    } //-- void setReturn(com.cdoframework.cdolib.database.dataservice.Return) 

    /**
     * Sets the value of field 'rollback'.
     * 
     * @param rollback the value of field 'rollback'.
     */
    public void setRollback(java.lang.Object rollback)
    {
        this._rollback = rollback;
        this._choiceValue = rollback;
    } //-- void setRollback(java.lang.Object) 

    /**
     * Sets the value of field 'selectConnection'.
     * 
     * @param selectConnection the value of field 'selectConnection'
     */
    public void setSelectConnection(com.cdoframework.cdolib.database.dataservice.SelectConnection selectConnection)
    {
        this._selectConnection = selectConnection;
        this._choiceValue = selectConnection;
    } //-- void setSelectConnection(com.cdoframework.cdolib.database.dataservice.SelectConnection) 

    /**
     * Sets the value of field 'selectField'.
     * 
     * @param selectField the value of field 'selectField'.
     */
    public void setSelectField(com.cdoframework.cdolib.database.dataservice.SelectField selectField)
    {
        this._selectField = selectField;
        this._choiceValue = selectField;
    } //-- void setSelectField(com.cdoframework.cdolib.database.dataservice.SelectField) 

    /**
     * Sets the value of field 'selectRecord'.
     * 
     * @param selectRecord the value of field 'selectRecord'.
     */
    public void setSelectRecord(com.cdoframework.cdolib.database.dataservice.SelectRecord selectRecord)
    {
        this._selectRecord = selectRecord;
        this._choiceValue = selectRecord;
    } //-- void setSelectRecord(com.cdoframework.cdolib.database.dataservice.SelectRecord) 

    /**
     * Sets the value of field 'selectRecordSet'.
     * 
     * @param selectRecordSet the value of field 'selectRecordSet'.
     */
    public void setSelectRecordSet(com.cdoframework.cdolib.database.dataservice.SelectRecordSet selectRecordSet)
    {
        this._selectRecordSet = selectRecordSet;
        this._choiceValue = selectRecordSet;
    } //-- void setSelectRecordSet(com.cdoframework.cdolib.database.dataservice.SelectRecordSet) 

    /**
     * Sets the value of field 'setVar'.
     * 
     * @param setVar the value of field 'setVar'.
     */
    public void setSetVar(com.cdoframework.cdolib.database.dataservice.SetVar setVar)
    {
        this._setVar = setVar;
        this._choiceValue = setVar;
    } //-- void setSetVar(com.cdoframework.cdolib.database.dataservice.SetVar) 

    /**
     * Sets the value of field 'update'.
     * 
     * @param update the value of field 'update'.
     */
    public void setUpdate(com.cdoframework.cdolib.database.dataservice.Update update)
    {
        this._update = update;
        this._choiceValue = update;
    } //-- void setUpdate(com.cdoframework.cdolib.database.dataservice.Update) 

}
