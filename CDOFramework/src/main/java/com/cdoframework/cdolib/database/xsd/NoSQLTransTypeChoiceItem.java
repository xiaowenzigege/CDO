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
public class NoSQLTransTypeChoiceItem implements java.io.Serializable {

    /**
     * Field add.
     */
    private com.cdoframework.cdolib.database.xsd.Add add;

    /**
     * Field remove.
     */
    private com.cdoframework.cdolib.database.xsd.Remove remove;

    /**
     * Field modify.
     */
    private com.cdoframework.cdolib.database.xsd.Modify modify;

    /**
     * Field replace.
     */
    private com.cdoframework.cdolib.database.xsd.Replace replace;

    /**
     * Field queryRecordSet.
     */
    private com.cdoframework.cdolib.database.xsd.QueryRecordSet queryRecordSet;

    /**
     * Field queryRecord.
     */
    private com.cdoframework.cdolib.database.xsd.QueryRecord queryRecord;

    /**
     * Field queryField.
     */
    private com.cdoframework.cdolib.database.xsd.QueryField queryField;

    /**
     * Field queryCount.
     */
    private com.cdoframework.cdolib.database.xsd.QueryCount queryCount;

    public NoSQLTransTypeChoiceItem() {
        super();
    }

    /**
     * Returns the value of field 'add'.
     * 
     * @return the value of field 'Add'.
     */
    public com.cdoframework.cdolib.database.xsd.Add getAdd() {
        return this.add;
    }

    /**
     * Returns the value of field 'modify'.
     * 
     * @return the value of field 'Modify'.
     */
    public com.cdoframework.cdolib.database.xsd.Modify getModify() {
        return this.modify;
    }

    /**
     * Returns the value of field 'queryCount'.
     * 
     * @return the value of field 'QueryCount'.
     */
    public com.cdoframework.cdolib.database.xsd.QueryCount getQueryCount() {
        return this.queryCount;
    }

    /**
     * Returns the value of field 'queryField'.
     * 
     * @return the value of field 'QueryField'.
     */
    public com.cdoframework.cdolib.database.xsd.QueryField getQueryField() {
        return this.queryField;
    }

    /**
     * Returns the value of field 'queryRecord'.
     * 
     * @return the value of field 'QueryRecord'.
     */
    public com.cdoframework.cdolib.database.xsd.QueryRecord getQueryRecord() {
        return this.queryRecord;
    }

    /**
     * Returns the value of field 'queryRecordSet'.
     * 
     * @return the value of field 'QueryRecordSet'.
     */
    public com.cdoframework.cdolib.database.xsd.QueryRecordSet getQueryRecordSet() {
        return this.queryRecordSet;
    }

    /**
     * Returns the value of field 'remove'.
     * 
     * @return the value of field 'Remove'.
     */
    public com.cdoframework.cdolib.database.xsd.Remove getRemove() {
        return this.remove;
    }

    /**
     * Returns the value of field 'replace'.
     * 
     * @return the value of field 'Replace'.
     */
    public com.cdoframework.cdolib.database.xsd.Replace getReplace() {
        return this.replace;
    }

    /**
     * Sets the value of field 'add'.
     * 
     * @param add the value of field 'add'.
     */
    public void setAdd(final com.cdoframework.cdolib.database.xsd.Add add) {
        this.add = add;
    }

    /**
     * Sets the value of field 'modify'.
     * 
     * @param modify the value of field 'modify'.
     */
    public void setModify(final com.cdoframework.cdolib.database.xsd.Modify modify) {
        this.modify = modify;
    }

    /**
     * Sets the value of field 'queryCount'.
     * 
     * @param queryCount the value of field 'queryCount'.
     */
    public void setQueryCount(final com.cdoframework.cdolib.database.xsd.QueryCount queryCount) {
        this.queryCount = queryCount;
    }

    /**
     * Sets the value of field 'queryField'.
     * 
     * @param queryField the value of field 'queryField'.
     */
    public void setQueryField(final com.cdoframework.cdolib.database.xsd.QueryField queryField) {
        this.queryField = queryField;
    }

    /**
     * Sets the value of field 'queryRecord'.
     * 
     * @param queryRecord the value of field 'queryRecord'.
     */
    public void setQueryRecord(final com.cdoframework.cdolib.database.xsd.QueryRecord queryRecord) {
        this.queryRecord = queryRecord;
    }

    /**
     * Sets the value of field 'queryRecordSet'.
     * 
     * @param queryRecordSet the value of field 'queryRecordSet'.
     */
    public void setQueryRecordSet(final com.cdoframework.cdolib.database.xsd.QueryRecordSet queryRecordSet) {
        this.queryRecordSet = queryRecordSet;
    }

    /**
     * Sets the value of field 'remove'.
     * 
     * @param remove the value of field 'remove'.
     */
    public void setRemove(final com.cdoframework.cdolib.database.xsd.Remove remove) {
        this.remove = remove;
    }

    /**
     * Sets the value of field 'replace'.
     * 
     * @param replace the value of field 'replace'.
     */
    public void setReplace(final com.cdoframework.cdolib.database.xsd.Replace replace) {
        this.replace = replace;
    }

}
