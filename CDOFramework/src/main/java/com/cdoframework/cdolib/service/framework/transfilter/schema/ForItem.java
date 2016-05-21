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
 * Class ForItem.
 * 
 * @version $Revision$ $Date$
 */
public class ForItem implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Internal choice value storage
     */
    private java.lang.Object _choiceValue;

    /**
     * Field _pushCache
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.PushCache _pushCache;

    /**
     * Field _removeCache
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveCache _removeCache;

    /**
     * Field _removeURLCache
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveURLCache _removeURLCache;

    /**
     * Field _loadCache
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCache _loadCache;

    /**
     * Field _transaction
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.Transaction _transaction;


      //----------------/
     //- Constructors -/
    //----------------/

    public ForItem() 
     {
        super();
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.ForItem()


      //-----------/
     //- Methods -/
    //-----------/

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
     * Returns the value of field 'loadCache'.
     * 
     * @return LoadCache
     * @return the value of field 'loadCache'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCache getLoadCache()
    {
        return this._loadCache;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCache getLoadCache() 

    /**
     * Returns the value of field 'pushCache'.
     * 
     * @return PushCache
     * @return the value of field 'pushCache'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.PushCache getPushCache()
    {
        return this._pushCache;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PushCache getPushCache() 

    /**
     * Returns the value of field 'removeCache'.
     * 
     * @return RemoveCache
     * @return the value of field 'removeCache'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveCache getRemoveCache()
    {
        return this._removeCache;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveCache getRemoveCache() 

    /**
     * Returns the value of field 'removeURLCache'.
     * 
     * @return RemoveURLCache
     * @return the value of field 'removeURLCache'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveURLCache getRemoveURLCache()
    {
        return this._removeURLCache;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveURLCache getRemoveURLCache() 

    /**
     * Returns the value of field 'transaction'.
     * 
     * @return Transaction
     * @return the value of field 'transaction'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.Transaction getTransaction()
    {
        return this._transaction;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.Transaction getTransaction() 

    /**
     * Sets the value of field 'loadCache'.
     * 
     * @param loadCache the value of field 'loadCache'.
     */
    public void setLoadCache(com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCache loadCache)
    {
        this._loadCache = loadCache;
        this._choiceValue = loadCache;
    } //-- void setLoadCache(com.cdoframework.cdolib.service.framework.transfilter.schema.LoadCache) 

    /**
     * Sets the value of field 'pushCache'.
     * 
     * @param pushCache the value of field 'pushCache'.
     */
    public void setPushCache(com.cdoframework.cdolib.service.framework.transfilter.schema.PushCache pushCache)
    {
        this._pushCache = pushCache;
        this._choiceValue = pushCache;
    } //-- void setPushCache(com.cdoframework.cdolib.service.framework.transfilter.schema.PushCache) 

    /**
     * Sets the value of field 'removeCache'.
     * 
     * @param removeCache the value of field 'removeCache'.
     */
    public void setRemoveCache(com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveCache removeCache)
    {
        this._removeCache = removeCache;
        this._choiceValue = removeCache;
    } //-- void setRemoveCache(com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveCache) 

    /**
     * Sets the value of field 'removeURLCache'.
     * 
     * @param removeURLCache the value of field 'removeURLCache'.
     */
    public void setRemoveURLCache(com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveURLCache removeURLCache)
    {
        this._removeURLCache = removeURLCache;
        this._choiceValue = removeURLCache;
    } //-- void setRemoveURLCache(com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveURLCache) 

    /**
     * Sets the value of field 'transaction'.
     * 
     * @param transaction the value of field 'transaction'.
     */
    public void setTransaction(com.cdoframework.cdolib.service.framework.transfilter.schema.Transaction transaction)
    {
        this._transaction = transaction;
        this._choiceValue = transaction;
    } //-- void setTransaction(com.cdoframework.cdolib.service.framework.transfilter.schema.Transaction) 

}
