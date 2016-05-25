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
public class PostTransEventTypeItem implements java.io.Serializable {

    /**
     * Internal choice value storage
     */
    private java.lang.Object _choiceValue;

    /**
     * Field postTransaction.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.PostTransaction postTransaction;

    /**
     * Field postPushCache.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.PostPushCache postPushCache;

    /**
     * Field postRemoveCache.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.PostRemoveCache postRemoveCache;

    /**
     * Field postRemoveURLCache.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.PostRemoveURLCache postRemoveURLCache;

    /**
     * Field postEventGroup.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.PostEventGroup postEventGroup;

    /**
     * Field postLoadCache.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.PostLoadCache postLoadCache;

    /**
     * Field businessLog.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.BusinessLog businessLog;

    /**
     * Field event.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.Event event;

    public PostTransEventTypeItem() {
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
     * Returns the value of field 'postEventGroup'.
     * 
     * @return the value of field 'PostEventGroup'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.PostEventGroup getPostEventGroup() {
        return this.postEventGroup;
    }

    /**
     * Returns the value of field 'postLoadCache'.
     * 
     * @return the value of field 'PostLoadCache'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.PostLoadCache getPostLoadCache() {
        return this.postLoadCache;
    }

    /**
     * Returns the value of field 'postPushCache'.
     * 
     * @return the value of field 'PostPushCache'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.PostPushCache getPostPushCache() {
        return this.postPushCache;
    }

    /**
     * Returns the value of field 'postRemoveCache'.
     * 
     * @return the value of field 'PostRemoveCache'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.PostRemoveCache getPostRemoveCache() {
        return this.postRemoveCache;
    }

    /**
     * Returns the value of field 'postRemoveURLCache'.
     * 
     * @return the value of field 'PostRemoveURLCache'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.PostRemoveURLCache getPostRemoveURLCache() {
        return this.postRemoveURLCache;
    }

    /**
     * Returns the value of field 'postTransaction'.
     * 
     * @return the value of field 'PostTransaction'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.PostTransaction getPostTransaction() {
        return this.postTransaction;
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
     * Sets the value of field 'postEventGroup'.
     * 
     * @param postEventGroup the value of field 'postEventGroup'.
     */
    public void setPostEventGroup(final com.cdoframework.cdolib.service.framework.transfilter.xsd.PostEventGroup postEventGroup) {
        this.postEventGroup = postEventGroup;
        this._choiceValue = postEventGroup;
    }

    /**
     * Sets the value of field 'postLoadCache'.
     * 
     * @param postLoadCache the value of field 'postLoadCache'.
     */
    public void setPostLoadCache(final com.cdoframework.cdolib.service.framework.transfilter.xsd.PostLoadCache postLoadCache) {
        this.postLoadCache = postLoadCache;
        this._choiceValue = postLoadCache;
    }

    /**
     * Sets the value of field 'postPushCache'.
     * 
     * @param postPushCache the value of field 'postPushCache'.
     */
    public void setPostPushCache(final com.cdoframework.cdolib.service.framework.transfilter.xsd.PostPushCache postPushCache) {
        this.postPushCache = postPushCache;
        this._choiceValue = postPushCache;
    }

    /**
     * Sets the value of field 'postRemoveCache'.
     * 
     * @param postRemoveCache the value of field 'postRemoveCache'.
     */
    public void setPostRemoveCache(final com.cdoframework.cdolib.service.framework.transfilter.xsd.PostRemoveCache postRemoveCache) {
        this.postRemoveCache = postRemoveCache;
        this._choiceValue = postRemoveCache;
    }

    /**
     * Sets the value of field 'postRemoveURLCache'.
     * 
     * @param postRemoveURLCache the value of field
     * 'postRemoveURLCache'.
     */
    public void setPostRemoveURLCache(final com.cdoframework.cdolib.service.framework.transfilter.xsd.PostRemoveURLCache postRemoveURLCache) {
        this.postRemoveURLCache = postRemoveURLCache;
        this._choiceValue = postRemoveURLCache;
    }

    /**
     * Sets the value of field 'postTransaction'.
     * 
     * @param postTransaction the value of field 'postTransaction'.
     */
    public void setPostTransaction(final com.cdoframework.cdolib.service.framework.transfilter.xsd.PostTransaction postTransaction) {
        this.postTransaction = postTransaction;
        this._choiceValue = postTransaction;
    }

}
