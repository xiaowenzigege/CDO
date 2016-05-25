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
public class LoadCacheChoiceItem implements java.io.Serializable {

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
     * Field _for.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.For _for;

    public LoadCacheChoiceItem() {
        super();
    }

    /**
     * Returns the value of field 'for'.
     * 
     * @return the value of field 'For'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.For getFor() {
        return this._for;
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
     * Sets the value of field 'for'.
     * 
     * @param _for
     * @param for the value of field 'for'.
     */
    public void setFor(final com.cdoframework.cdolib.service.framework.transfilter.xsd.For _for) {
        this._for = _for;
    }

    /**
     * Sets the value of field 'pushCache'.
     * 
     * @param pushCache the value of field 'pushCache'.
     */
    public void setPushCache(final com.cdoframework.cdolib.service.framework.transfilter.xsd.PushCache pushCache) {
        this.pushCache = pushCache;
    }

    /**
     * Sets the value of field 'removeCache'.
     * 
     * @param removeCache the value of field 'removeCache'.
     */
    public void setRemoveCache(final com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveCache removeCache) {
        this.removeCache = removeCache;
    }

    /**
     * Sets the value of field 'removeURLCache'.
     * 
     * @param removeURLCache the value of field 'removeURLCache'.
     */
    public void setRemoveURLCache(final com.cdoframework.cdolib.service.framework.transfilter.xsd.RemoveURLCache removeURLCache) {
        this.removeURLCache = removeURLCache;
    }

}
