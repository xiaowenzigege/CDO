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
public class ForItem implements java.io.Serializable {

    /**
     * Internal choice value storage
     */
    private java.lang.Object _choiceValue;

    /**
     * Field pushCache.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.PushCache pushCache;

    /**
     * Field removeCache.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveCache removeCache;

    /**
     * Field removeURLCache.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveURLCache removeURLCache;

    /**
     * Field loadCache.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.LoadCache loadCache;

    /**
     * Field transaction.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.Transaction transaction;

    public ForItem() {
        super();
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
     * Returns the value of field 'loadCache'.
     * 
     * @return the value of field 'LoadCache'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.LoadCache getLoadCache() {
        return this.loadCache;
    }

    /**
     * Returns the value of field 'pushCache'.
     * 
     * @return the value of field 'PushCache'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.PushCache getPushCache() {
        return this.pushCache;
    }

    /**
     * Returns the value of field 'removeCache'.
     * 
     * @return the value of field 'RemoveCache'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveCache getRemoveCache() {
        return this.removeCache;
    }

    /**
     * Returns the value of field 'removeURLCache'.
     * 
     * @return the value of field 'RemoveURLCache'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveURLCache getRemoveURLCache() {
        return this.removeURLCache;
    }

    /**
     * Returns the value of field 'transaction'.
     * 
     * @return the value of field 'Transaction'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.Transaction getTransaction() {
        return this.transaction;
    }

    /**
     * Sets the value of field 'loadCache'.
     * 
     * @param loadCache the value of field 'loadCache'.
     */
    public void setLoadCache(final com.cdoframework.cdolib.service.framework.transfilter.xsd.LoadCache loadCache) {
        this.loadCache = loadCache;
        this._choiceValue = loadCache;
    }

    /**
     * Sets the value of field 'pushCache'.
     * 
     * @param pushCache the value of field 'pushCache'.
     */
    public void setPushCache(final com.cdoframework.cdolib.service.framework.transfilter.xsd.PushCache pushCache) {
        this.pushCache = pushCache;
        this._choiceValue = pushCache;
    }

    /**
     * Sets the value of field 'removeCache'.
     * 
     * @param removeCache the value of field 'removeCache'.
     */
    public void setRemoveCache(final com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveCache removeCache) {
        this.removeCache = removeCache;
        this._choiceValue = removeCache;
    }

    /**
     * Sets the value of field 'removeURLCache'.
     * 
     * @param removeURLCache the value of field 'removeURLCache'.
     */
    public void setRemoveURLCache(final com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveURLCache removeURLCache) {
        this.removeURLCache = removeURLCache;
        this._choiceValue = removeURLCache;
    }

    /**
     * Sets the value of field 'transaction'.
     * 
     * @param transaction the value of field 'transaction'.
     */
    public void setTransaction(final com.cdoframework.cdolib.service.framework.transfilter.xsd.Transaction transaction) {
        this.transaction = transaction;
        this._choiceValue = transaction;
    }

}
