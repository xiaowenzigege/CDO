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
 * Class PostTransEventTypeItem.
 * 
 * @version $Revision$ $Date$
 */
public class PostTransEventTypeItem implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Internal choice value storage
     */
    private java.lang.Object _choiceValue;

    /**
     * Field _postTransaction
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.PostTransaction _postTransaction;

    /**
     * Field _postPushCache
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.PostPushCache _postPushCache;

    /**
     * Field _postRemoveCache
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.PostRemoveCache _postRemoveCache;

    /**
     * Field _postRemoveURLCache
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.PostRemoveURLCache _postRemoveURLCache;

    /**
     * Field _postEventGroup
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.PostEventGroup _postEventGroup;

    /**
     * Field _postLoadCache
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.PostLoadCache _postLoadCache;

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

    public PostTransEventTypeItem() 
     {
        super();
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PostTransEventTypeItem()


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
     * Returns the value of field 'postEventGroup'.
     * 
     * @return PostEventGroup
     * @return the value of field 'postEventGroup'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.PostEventGroup getPostEventGroup()
    {
        return this._postEventGroup;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PostEventGroup getPostEventGroup() 

    /**
     * Returns the value of field 'postLoadCache'.
     * 
     * @return PostLoadCache
     * @return the value of field 'postLoadCache'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.PostLoadCache getPostLoadCache()
    {
        return this._postLoadCache;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PostLoadCache getPostLoadCache() 

    /**
     * Returns the value of field 'postPushCache'.
     * 
     * @return PostPushCache
     * @return the value of field 'postPushCache'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.PostPushCache getPostPushCache()
    {
        return this._postPushCache;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PostPushCache getPostPushCache() 

    /**
     * Returns the value of field 'postRemoveCache'.
     * 
     * @return PostRemoveCache
     * @return the value of field 'postRemoveCache'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.PostRemoveCache getPostRemoveCache()
    {
        return this._postRemoveCache;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PostRemoveCache getPostRemoveCache() 

    /**
     * Returns the value of field 'postRemoveURLCache'.
     * 
     * @return PostRemoveURLCache
     * @return the value of field 'postRemoveURLCache'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.PostRemoveURLCache getPostRemoveURLCache()
    {
        return this._postRemoveURLCache;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PostRemoveURLCache getPostRemoveURLCache() 

    /**
     * Returns the value of field 'postTransaction'.
     * 
     * @return PostTransaction
     * @return the value of field 'postTransaction'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.PostTransaction getPostTransaction()
    {
        return this._postTransaction;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PostTransaction getPostTransaction() 

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
     * Sets the value of field 'postEventGroup'.
     * 
     * @param postEventGroup the value of field 'postEventGroup'.
     */
    public void setPostEventGroup(com.cdoframework.cdolib.service.framework.transfilter.schema.PostEventGroup postEventGroup)
    {
        this._postEventGroup = postEventGroup;
        this._choiceValue = postEventGroup;
    } //-- void setPostEventGroup(com.cdoframework.cdolib.service.framework.transfilter.schema.PostEventGroup) 

    /**
     * Sets the value of field 'postLoadCache'.
     * 
     * @param postLoadCache the value of field 'postLoadCache'.
     */
    public void setPostLoadCache(com.cdoframework.cdolib.service.framework.transfilter.schema.PostLoadCache postLoadCache)
    {
        this._postLoadCache = postLoadCache;
        this._choiceValue = postLoadCache;
    } //-- void setPostLoadCache(com.cdoframework.cdolib.service.framework.transfilter.schema.PostLoadCache) 

    /**
     * Sets the value of field 'postPushCache'.
     * 
     * @param postPushCache the value of field 'postPushCache'.
     */
    public void setPostPushCache(com.cdoframework.cdolib.service.framework.transfilter.schema.PostPushCache postPushCache)
    {
        this._postPushCache = postPushCache;
        this._choiceValue = postPushCache;
    } //-- void setPostPushCache(com.cdoframework.cdolib.service.framework.transfilter.schema.PostPushCache) 

    /**
     * Sets the value of field 'postRemoveCache'.
     * 
     * @param postRemoveCache the value of field 'postRemoveCache'.
     */
    public void setPostRemoveCache(com.cdoframework.cdolib.service.framework.transfilter.schema.PostRemoveCache postRemoveCache)
    {
        this._postRemoveCache = postRemoveCache;
        this._choiceValue = postRemoveCache;
    } //-- void setPostRemoveCache(com.cdoframework.cdolib.service.framework.transfilter.schema.PostRemoveCache) 

    /**
     * Sets the value of field 'postRemoveURLCache'.
     * 
     * @param postRemoveURLCache the value of field
     * 'postRemoveURLCache'.
     */
    public void setPostRemoveURLCache(com.cdoframework.cdolib.service.framework.transfilter.schema.PostRemoveURLCache postRemoveURLCache)
    {
        this._postRemoveURLCache = postRemoveURLCache;
        this._choiceValue = postRemoveURLCache;
    } //-- void setPostRemoveURLCache(com.cdoframework.cdolib.service.framework.transfilter.schema.PostRemoveURLCache) 

    /**
     * Sets the value of field 'postTransaction'.
     * 
     * @param postTransaction the value of field 'postTransaction'.
     */
    public void setPostTransaction(com.cdoframework.cdolib.service.framework.transfilter.schema.PostTransaction postTransaction)
    {
        this._postTransaction = postTransaction;
        this._choiceValue = postTransaction;
    } //-- void setPostTransaction(com.cdoframework.cdolib.service.framework.transfilter.schema.PostTransaction) 

}
