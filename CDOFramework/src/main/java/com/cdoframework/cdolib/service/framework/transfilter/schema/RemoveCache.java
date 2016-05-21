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

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

/**
 * Class RemoveCache.
 * 
 * @version $Revision$ $Date$
 */
public class RemoveCache implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _cacheId
     */
    private java.lang.String _cacheId;

    /**
     * internal content storage
     */
    private java.lang.String _content = "";

    /**
     * Field _cacheKey
     */
    private com.cdoframework.cdolib.service.framework.transfilter.schema.CacheKey _cacheKey;


      //----------------/
     //- Constructors -/
    //----------------/

    public RemoveCache() 
     {
        super();
        setContent("");
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveCache()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'cacheId'.
     * 
     * @return String
     * @return the value of field 'cacheId'.
     */
    public java.lang.String getCacheId()
    {
        return this._cacheId;
    } //-- java.lang.String getCacheId() 

    /**
     * Returns the value of field 'cacheKey'.
     * 
     * @return CacheKey
     * @return the value of field 'cacheKey'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.CacheKey getCacheKey()
    {
        return this._cacheKey;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.CacheKey getCacheKey() 

    /**
     * Returns the value of field 'content'. The field 'content'
     * has the following description: internal content storage
     * 
     * @return String
     * @return the value of field 'content'.
     */
    public java.lang.String getContent()
    {
        return this._content;
    } //-- java.lang.String getContent() 

    /**
     * Method isValid
     * 
     * 
     * 
     * @return boolean
     */
    public boolean isValid()
    {
        try {
            validate();
        }
        catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    } //-- boolean isValid() 

    /**
     * Method marshal
     * 
     * 
     * 
     * @param out
     */
    public void marshal(java.io.Writer out)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, out);
    } //-- void marshal(java.io.Writer) 

    /**
     * Method marshal
     * 
     * 
     * 
     * @param handler
     */
    public void marshal(org.xml.sax.ContentHandler handler)
        throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, handler);
    } //-- void marshal(org.xml.sax.ContentHandler) 

    /**
     * Sets the value of field 'cacheId'.
     * 
     * @param cacheId the value of field 'cacheId'.
     */
    public void setCacheId(java.lang.String cacheId)
    {
        this._cacheId = cacheId;
    } //-- void setCacheId(java.lang.String) 

    /**
     * Sets the value of field 'cacheKey'.
     * 
     * @param cacheKey the value of field 'cacheKey'.
     */
    public void setCacheKey(com.cdoframework.cdolib.service.framework.transfilter.schema.CacheKey cacheKey)
    {
        this._cacheKey = cacheKey;
    } //-- void setCacheKey(com.cdoframework.cdolib.service.framework.transfilter.schema.CacheKey) 

    /**
     * Sets the value of field 'content'. The field 'content' has
     * the following description: internal content storage
     * 
     * @param content the value of field 'content'.
     */
    public void setContent(java.lang.String content)
    {
        this._content = content;
    } //-- void setContent(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return RemoveCache
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveCache unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveCache) Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveCache.class, reader);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.RemoveCache unmarshal(java.io.Reader) 

    /**
     * Method validate
     * 
     */
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 
    private Filter filter;
	public void setFilter(Filter filter)
	{
		this.filter = filter;
	}
	public Filter getFilter()
	{
		return filter;
	}
}
