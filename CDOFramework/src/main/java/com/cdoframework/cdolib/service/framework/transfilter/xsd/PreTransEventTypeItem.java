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
public class PreTransEventTypeItem implements java.io.Serializable {

    /**
     * Internal choice value storage
     */
    private java.lang.Object _choiceValue;

    /**
     * Field preTransaction.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.PreTransaction preTransaction;

    /**
     * Field prePushCache.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.PrePushCache prePushCache;

    /**
     * Field preRemoveCache.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.PreRemoveCache preRemoveCache;

    /**
     * Field preRemoveURLCache.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.PreRemoveURLCache preRemoveURLCache;

    /**
     * Field preEventGroup.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.PreEventGroup preEventGroup;

    /**
     * Field preLoadCache.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.PreLoadCache preLoadCache;

    /**
     * Field businessLog.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.BusinessLog businessLog;

    /**
     * Field event.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.Event event;

    public PreTransEventTypeItem() {
        super();
    }

    /**
     * Returns the value of field 'businessLog'.
     * 
     * @return the value of field 'BusinessLog'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.BusinessLog getBusinessLog() {
        return this.businessLog;
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
     * Returns the value of field 'event'.
     * 
     * @return the value of field 'Event'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.Event getEvent() {
        return this.event;
    }

    /**
     * Returns the value of field 'preEventGroup'.
     * 
     * @return the value of field 'PreEventGroup'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.PreEventGroup getPreEventGroup() {
        return this.preEventGroup;
    }

    /**
     * Returns the value of field 'preLoadCache'.
     * 
     * @return the value of field 'PreLoadCache'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.PreLoadCache getPreLoadCache() {
        return this.preLoadCache;
    }

    /**
     * Returns the value of field 'prePushCache'.
     * 
     * @return the value of field 'PrePushCache'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.PrePushCache getPrePushCache() {
        return this.prePushCache;
    }

    /**
     * Returns the value of field 'preRemoveCache'.
     * 
     * @return the value of field 'PreRemoveCache'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.PreRemoveCache getPreRemoveCache() {
        return this.preRemoveCache;
    }

    /**
     * Returns the value of field 'preRemoveURLCache'.
     * 
     * @return the value of field 'PreRemoveURLCache'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.PreRemoveURLCache getPreRemoveURLCache() {
        return this.preRemoveURLCache;
    }

    /**
     * Returns the value of field 'preTransaction'.
     * 
     * @return the value of field 'PreTransaction'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.PreTransaction getPreTransaction() {
        return this.preTransaction;
    }

    /**
     * Sets the value of field 'businessLog'.
     * 
     * @param businessLog the value of field 'businessLog'.
     */
    public void setBusinessLog(final com.cdoframework.cdolib.service.framework.transfilter.xsd.BusinessLog businessLog) {
        this.businessLog = businessLog;
        this._choiceValue = businessLog;
    }

    /**
     * Sets the value of field 'event'.
     * 
     * @param event the value of field 'event'.
     */
    public void setEvent(final com.cdoframework.cdolib.service.framework.transfilter.xsd.Event event) {
        this.event = event;
        this._choiceValue = event;
    }

    /**
     * Sets the value of field 'preEventGroup'.
     * 
     * @param preEventGroup the value of field 'preEventGroup'.
     */
    public void setPreEventGroup(final com.cdoframework.cdolib.service.framework.transfilter.xsd.PreEventGroup preEventGroup) {
        this.preEventGroup = preEventGroup;
        this._choiceValue = preEventGroup;
    }

    /**
     * Sets the value of field 'preLoadCache'.
     * 
     * @param preLoadCache the value of field 'preLoadCache'.
     */
    public void setPreLoadCache(final com.cdoframework.cdolib.service.framework.transfilter.xsd.PreLoadCache preLoadCache) {
        this.preLoadCache = preLoadCache;
        this._choiceValue = preLoadCache;
    }

    /**
     * Sets the value of field 'prePushCache'.
     * 
     * @param prePushCache the value of field 'prePushCache'.
     */
    public void setPrePushCache(final com.cdoframework.cdolib.service.framework.transfilter.xsd.PrePushCache prePushCache) {
        this.prePushCache = prePushCache;
        this._choiceValue = prePushCache;
    }

    /**
     * Sets the value of field 'preRemoveCache'.
     * 
     * @param preRemoveCache the value of field 'preRemoveCache'.
     */
    public void setPreRemoveCache(final com.cdoframework.cdolib.service.framework.transfilter.xsd.PreRemoveCache preRemoveCache) {
        this.preRemoveCache = preRemoveCache;
        this._choiceValue = preRemoveCache;
    }

    /**
     * Sets the value of field 'preRemoveURLCache'.
     * 
     * @param preRemoveURLCache the value of field
     * 'preRemoveURLCache'.
     */
    public void setPreRemoveURLCache(final com.cdoframework.cdolib.service.framework.transfilter.xsd.PreRemoveURLCache preRemoveURLCache) {
        this.preRemoveURLCache = preRemoveURLCache;
        this._choiceValue = preRemoveURLCache;
    }

    /**
     * Sets the value of field 'preTransaction'.
     * 
     * @param preTransaction the value of field 'preTransaction'.
     */
    public void setPreTransaction(final com.cdoframework.cdolib.service.framework.transfilter.xsd.PreTransaction preTransaction) {
        this.preTransaction = preTransaction;
        this._choiceValue = preTransaction;
    }

}
