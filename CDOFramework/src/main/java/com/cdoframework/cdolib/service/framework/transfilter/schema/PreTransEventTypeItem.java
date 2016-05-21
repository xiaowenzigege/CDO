/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.service.framework.transfilter.schema;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.io.Serializable;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class PreTransEventTypeItem.
 * 
 * @version $Revision$ $Date$
 */
public class PreTransEventTypeItem implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Internal choice value storage
     */
    private java.lang.Object _choiceValue;

    /**
     * Field _preTransaction
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.PreTransaction _preTransaction;

    /**
     * Field _prePushCache
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.PrePushCache _prePushCache;

    /**
     * Field _preRemoveCache
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.PreRemoveCache _preRemoveCache;

    /**
     * Field _preRemoveURLCache
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.PreRemoveURLCache _preRemoveURLCache;

    /**
     * Field _preEventGroup
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.PreEventGroup _preEventGroup;

    /**
     * Field _preLoadCache
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.PreLoadCache _preLoadCache;

    /**
     * Field _businessLog
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.BusinessLog _businessLog;

    /**
     * Field _event
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.Event _event;


      //----------------/
     //- Constructors -/
    //----------------/

    public PreTransEventTypeItem() 
     {
        super();
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PreTransEventTypeItem()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'businessLog'.
     * 
     * @return BusinessLog
     * @return the value of field 'businessLog'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.BusinessLog getBusinessLog()
    {
        return this._businessLog;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.BusinessLog getBusinessLog() 

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
     * Returns the value of field 'event'.
     * 
     * @return Event
     * @return the value of field 'event'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.Event getEvent()
    {
        return this._event;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.Event getEvent() 

    /**
     * Returns the value of field 'preEventGroup'.
     * 
     * @return PreEventGroup
     * @return the value of field 'preEventGroup'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.PreEventGroup getPreEventGroup()
    {
        return this._preEventGroup;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PreEventGroup getPreEventGroup() 

    /**
     * Returns the value of field 'preLoadCache'.
     * 
     * @return PreLoadCache
     * @return the value of field 'preLoadCache'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.PreLoadCache getPreLoadCache()
    {
        return this._preLoadCache;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PreLoadCache getPreLoadCache() 

    /**
     * Returns the value of field 'prePushCache'.
     * 
     * @return PrePushCache
     * @return the value of field 'prePushCache'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.PrePushCache getPrePushCache()
    {
        return this._prePushCache;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PrePushCache getPrePushCache() 

    /**
     * Returns the value of field 'preRemoveCache'.
     * 
     * @return PreRemoveCache
     * @return the value of field 'preRemoveCache'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.PreRemoveCache getPreRemoveCache()
    {
        return this._preRemoveCache;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PreRemoveCache getPreRemoveCache() 

    /**
     * Returns the value of field 'preRemoveURLCache'.
     * 
     * @return PreRemoveURLCache
     * @return the value of field 'preRemoveURLCache'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.PreRemoveURLCache getPreRemoveURLCache()
    {
        return this._preRemoveURLCache;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PreRemoveURLCache getPreRemoveURLCache() 

    /**
     * Returns the value of field 'preTransaction'.
     * 
     * @return PreTransaction
     * @return the value of field 'preTransaction'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.PreTransaction getPreTransaction()
    {
        return this._preTransaction;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PreTransaction getPreTransaction() 

    /**
     * Sets the value of field 'businessLog'.
     * 
     * @param businessLog the value of field 'businessLog'.
     */
    public void setBusinessLog(com.cdoframework.cdolib.service.framework.transfilter.schema.BusinessLog businessLog)
    {
        this._businessLog = businessLog;
        this._choiceValue = businessLog;
    } //-- void setBusinessLog(com.cdoframework.cdolib.service.framework.transfilter.schema.BusinessLog) 

    /**
     * Sets the value of field 'event'.
     * 
     * @param event the value of field 'event'.
     */
    public void setEvent(com.cdoframework.cdolib.service.framework.transfilter.schema.Event event)
    {
        this._event = event;
        this._choiceValue = event;
    } //-- void setEvent(com.cdoframework.cdolib.service.framework.transfilter.schema.Event) 

    /**
     * Sets the value of field 'preEventGroup'.
     * 
     * @param preEventGroup the value of field 'preEventGroup'.
     */
    public void setPreEventGroup(com.cdoframework.cdolib.service.framework.transfilter.schema.PreEventGroup preEventGroup)
    {
        this._preEventGroup = preEventGroup;
        this._choiceValue = preEventGroup;
    } //-- void setPreEventGroup(com.cdoframework.cdolib.service.framework.transfilter.schema.PreEventGroup) 

    /**
     * Sets the value of field 'preLoadCache'.
     * 
     * @param preLoadCache the value of field 'preLoadCache'.
     */
    public void setPreLoadCache(com.cdoframework.cdolib.service.framework.transfilter.schema.PreLoadCache preLoadCache)
    {
        this._preLoadCache = preLoadCache;
        this._choiceValue = preLoadCache;
    } //-- void setPreLoadCache(com.cdoframework.cdolib.service.framework.transfilter.schema.PreLoadCache) 

    /**
     * Sets the value of field 'prePushCache'.
     * 
     * @param prePushCache the value of field 'prePushCache'.
     */
    public void setPrePushCache(com.cdoframework.cdolib.service.framework.transfilter.schema.PrePushCache prePushCache)
    {
        this._prePushCache = prePushCache;
        this._choiceValue = prePushCache;
    } //-- void setPrePushCache(com.cdoframework.cdolib.service.framework.transfilter.schema.PrePushCache) 

    /**
     * Sets the value of field 'preRemoveCache'.
     * 
     * @param preRemoveCache the value of field 'preRemoveCache'.
     */
    public void setPreRemoveCache(com.cdoframework.cdolib.service.framework.transfilter.schema.PreRemoveCache preRemoveCache)
    {
        this._preRemoveCache = preRemoveCache;
        this._choiceValue = preRemoveCache;
    } //-- void setPreRemoveCache(com.cdoframework.cdolib.service.framework.transfilter.schema.PreRemoveCache) 

    /**
     * Sets the value of field 'preRemoveURLCache'.
     * 
     * @param preRemoveURLCache the value of field
     * 'preRemoveURLCache'.
     */
    public void setPreRemoveURLCache(com.cdoframework.cdolib.service.framework.transfilter.schema.PreRemoveURLCache preRemoveURLCache)
    {
        this._preRemoveURLCache = preRemoveURLCache;
        this._choiceValue = preRemoveURLCache;
    } //-- void setPreRemoveURLCache(com.cdoframework.cdolib.service.framework.transfilter.schema.PreRemoveURLCache) 

    /**
     * Sets the value of field 'preTransaction'.
     * 
     * @param preTransaction the value of field 'preTransaction'.
     */
    public void setPreTransaction(com.cdoframework.cdolib.service.framework.transfilter.schema.PreTransaction preTransaction)
    {
        this._preTransaction = preTransaction;
        this._choiceValue = preTransaction;
    } //-- void setPreTransaction(com.cdoframework.cdolib.service.framework.transfilter.schema.PreTransaction) 

}
