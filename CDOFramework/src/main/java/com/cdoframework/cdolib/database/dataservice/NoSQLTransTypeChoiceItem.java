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
 * Class NoSQLTransTypeChoiceItem.
 * 
 * @version $Revision$ $Date$
 */
public class NoSQLTransTypeChoiceItem implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _add
     */
    private com.cdoframework.cdolib.database.dataservice.Add _add;

    /**
     * Field _remove
     */
    private com.cdoframework.cdolib.database.dataservice.Remove _remove;

    /**
     * Field _modify
     */
    private com.cdoframework.cdolib.database.dataservice.Modify _modify;

    /**
     * Field _replace
     */
    private com.cdoframework.cdolib.database.dataservice.Replace _replace;

    /**
     * Field _queryRecordSet
     */
    private com.cdoframework.cdolib.database.dataservice.QueryRecordSet _queryRecordSet;

    /**
     * Field _queryRecord
     */
    private com.cdoframework.cdolib.database.dataservice.QueryRecord _queryRecord;

    /**
     * Field _queryField
     */
    private com.cdoframework.cdolib.database.dataservice.QueryField _queryField;

    /**
     * Field _queryCount
     */
    private com.cdoframework.cdolib.database.dataservice.QueryCount _queryCount;


      //----------------/
     //- Constructors -/
    //----------------/

    public NoSQLTransTypeChoiceItem() 
     {
        super();
    } //-- com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoiceItem()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'add'.
     * 
     * @return Add
     * @return the value of field 'add'.
     */
    public com.cdoframework.cdolib.database.dataservice.Add getAdd()
    {
        return this._add;
    } //-- com.cdoframework.cdolib.database.dataservice.Add getAdd() 

    /**
     * Returns the value of field 'modify'.
     * 
     * @return Modify
     * @return the value of field 'modify'.
     */
    public com.cdoframework.cdolib.database.dataservice.Modify getModify()
    {
        return this._modify;
    } //-- com.cdoframework.cdolib.database.dataservice.Modify getModify() 

    /**
     * Returns the value of field 'queryCount'.
     * 
     * @return QueryCount
     * @return the value of field 'queryCount'.
     */
    public com.cdoframework.cdolib.database.dataservice.QueryCount getQueryCount()
    {
        return this._queryCount;
    } //-- com.cdoframework.cdolib.database.dataservice.QueryCount getQueryCount() 

    /**
     * Returns the value of field 'queryField'.
     * 
     * @return QueryField
     * @return the value of field 'queryField'.
     */
    public com.cdoframework.cdolib.database.dataservice.QueryField getQueryField()
    {
        return this._queryField;
    } //-- com.cdoframework.cdolib.database.dataservice.QueryField getQueryField() 

    /**
     * Returns the value of field 'queryRecord'.
     * 
     * @return QueryRecord
     * @return the value of field 'queryRecord'.
     */
    public com.cdoframework.cdolib.database.dataservice.QueryRecord getQueryRecord()
    {
        return this._queryRecord;
    } //-- com.cdoframework.cdolib.database.dataservice.QueryRecord getQueryRecord() 

    /**
     * Returns the value of field 'queryRecordSet'.
     * 
     * @return QueryRecordSet
     * @return the value of field 'queryRecordSet'.
     */
    public com.cdoframework.cdolib.database.dataservice.QueryRecordSet getQueryRecordSet()
    {
        return this._queryRecordSet;
    } //-- com.cdoframework.cdolib.database.dataservice.QueryRecordSet getQueryRecordSet() 

    /**
     * Returns the value of field 'remove'.
     * 
     * @return Remove
     * @return the value of field 'remove'.
     */
    public com.cdoframework.cdolib.database.dataservice.Remove getRemove()
    {
        return this._remove;
    } //-- com.cdoframework.cdolib.database.dataservice.Remove getRemove() 

    /**
     * Returns the value of field 'replace'.
     * 
     * @return Replace
     * @return the value of field 'replace'.
     */
    public com.cdoframework.cdolib.database.dataservice.Replace getReplace()
    {
        return this._replace;
    } //-- com.cdoframework.cdolib.database.dataservice.Replace getReplace() 

    /**
     * Sets the value of field 'add'.
     * 
     * @param add the value of field 'add'.
     */
    public void setAdd(com.cdoframework.cdolib.database.dataservice.Add add)
    {
        this._add = add;
    } //-- void setAdd(com.cdoframework.cdolib.database.dataservice.Add) 

    /**
     * Sets the value of field 'modify'.
     * 
     * @param modify the value of field 'modify'.
     */
    public void setModify(com.cdoframework.cdolib.database.dataservice.Modify modify)
    {
        this._modify = modify;
    } //-- void setModify(com.cdoframework.cdolib.database.dataservice.Modify) 

    /**
     * Sets the value of field 'queryCount'.
     * 
     * @param queryCount the value of field 'queryCount'.
     */
    public void setQueryCount(com.cdoframework.cdolib.database.dataservice.QueryCount queryCount)
    {
        this._queryCount = queryCount;
    } //-- void setQueryCount(com.cdoframework.cdolib.database.dataservice.QueryCount) 

    /**
     * Sets the value of field 'queryField'.
     * 
     * @param queryField the value of field 'queryField'.
     */
    public void setQueryField(com.cdoframework.cdolib.database.dataservice.QueryField queryField)
    {
        this._queryField = queryField;
    } //-- void setQueryField(com.cdoframework.cdolib.database.dataservice.QueryField) 

    /**
     * Sets the value of field 'queryRecord'.
     * 
     * @param queryRecord the value of field 'queryRecord'.
     */
    public void setQueryRecord(com.cdoframework.cdolib.database.dataservice.QueryRecord queryRecord)
    {
        this._queryRecord = queryRecord;
    } //-- void setQueryRecord(com.cdoframework.cdolib.database.dataservice.QueryRecord) 

    /**
     * Sets the value of field 'queryRecordSet'.
     * 
     * @param queryRecordSet the value of field 'queryRecordSet'.
     */
    public void setQueryRecordSet(com.cdoframework.cdolib.database.dataservice.QueryRecordSet queryRecordSet)
    {
        this._queryRecordSet = queryRecordSet;
    } //-- void setQueryRecordSet(com.cdoframework.cdolib.database.dataservice.QueryRecordSet) 

    /**
     * Sets the value of field 'remove'.
     * 
     * @param remove the value of field 'remove'.
     */
    public void setRemove(com.cdoframework.cdolib.database.dataservice.Remove remove)
    {
        this._remove = remove;
    } //-- void setRemove(com.cdoframework.cdolib.database.dataservice.Remove) 

    /**
     * Sets the value of field 'replace'.
     * 
     * @param replace the value of field 'replace'.
     */
    public void setReplace(com.cdoframework.cdolib.database.dataservice.Replace replace)
    {
        this._replace = replace;
    } //-- void setReplace(com.cdoframework.cdolib.database.dataservice.Replace) 

}
