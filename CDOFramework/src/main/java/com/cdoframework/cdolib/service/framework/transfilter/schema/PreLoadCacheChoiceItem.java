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
 * Class PreLoadCacheChoiceItem.
 * 
 * @version $Revision$ $Date$
 */
public class PreLoadCacheChoiceItem implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

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
     * Field _for
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.For _for;


      //----------------/
     //- Constructors -/
    //----------------/

    public PreLoadCacheChoiceItem() 
     {
        super();
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.PreLoadCacheChoiceItem()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'for'.
     * 
     * @return For
     * @return the value of field 'for'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.For getFor()
    {
        return this._for;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.For getFor() 

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
     * Sets the value of field 'for'.
     * 
     * @param _for
     * @param for the value of field 'for'.
     */
    public void setFor(com.cdoframework.cdolib.service.framework.transfilter.schema.For _for)
    {
        this._for = _for;
    } //-- void setFor(com.cdoframework.cdolib.service.framework.transfilter.schema.For) 

    /**
     * Sets the value of field 'pushCache'.
     * 
     * @param pushCache the value of field 'pushCache'.
     */
    public void setPushCache(com.cdoframework.cdolib.service.framework.transfilter.schema.PushCache pushCache)
    {
        this._pushCache = pushCache;
    } //-- void setPushCache(com.cdoframework.cdolib.service.framework.transfilter.schema.PushCache) 

    /**
     * Sets the value of field 'removeCache'.
     * 
     * @param removeCache the value of field 'removeCache'.
     */
    public void setRemoveCache(com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveCache removeCache)
    {
        this._removeCache = removeCache;
    } //-- void setRemoveCache(com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveCache) 

    /**
     * Sets the value of field 'removeURLCache'.
     * 
     * @param removeURLCache the value of field 'removeURLCache'.
     */
    public void setRemoveURLCache(com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveURLCache removeURLCache)
    {
        this._removeURLCache = removeURLCache;
    } //-- void setRemoveURLCache(com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveURLCache) 

}
