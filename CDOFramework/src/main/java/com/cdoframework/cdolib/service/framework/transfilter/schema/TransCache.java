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

import com.cdoframework.cdolib.base.ObjectExt;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.service.framework.FrameworkUtil;

/**
 * Class TransCache.
 * 
 * @version $Revision$ $Date$
 */
public class TransCache extends TransCacheType 
implements java.io.Serializable
{


      //----------------/
     //- Constructors -/
    //----------------/

    public TransCache() 
     {
        super();
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.TransCache()


      //-----------/
     //- Methods -/
    //-----------/

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
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return TransCacheType
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.schema.TransCacheType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.TransCacheType) Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.schema.TransCache.class, reader);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.TransCacheType unmarshal(java.io.Reader) 

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


	public Object getCacheValue(CDO cdoResponse)
	{
		CacheValue cacheValue = this.getCacheValue();
		if(cacheValue==null)
		{
			return cdoResponse.toXML();
		}
		String strFieldId = cacheValue.getFieldId();
		if(strFieldId==null)
		{
			return cdoResponse.toXML();
		}
		if(cdoResponse.exists(strFieldId))
		{
			return cdoResponse.getObject(strFieldId);
		}
		return null;
	}


	public String getKey(CDO cdoRequest) throws Exception
	{
		CDO cdoTemp = null;
		String[] strsRemoveKey = this.getRemoveKey();
		if(strsRemoveKey!=null && strsRemoveKey.length>0)
		{
			cdoTemp = new CDO();			
			cdoTemp.copyFrom(cdoRequest);
			for(String strRemoveKey:strsRemoveKey){
				cdoTemp.remove(strRemoveKey);
			}
		}
		else
		{
			cdoTemp = cdoRequest;
		}
		CacheKey cacheKey = this.getCacheKey();
		if(cacheKey==null)
		{
			return cdoTemp.toJSON();
		}
		String strContent = cacheKey.getContent();
		int nType = cacheKey.getType().getType();
		return FrameworkUtil.getString(nType,strContent,cdoTemp,cdoRequest);
	}
	public String getCacheId()
	{
		String strId = super.getCacheId();
		if(strId!=null)
		{
			return strId;
		}else
		{
			return this.getFilter().getFilterDefine().getDefaultCacheId();
		}
	}


	public void handleCacheValue(Object obj,CDO cdoResponse) throws Exception
	{
		CacheValue cacheValue = this.getCacheValue();
		if(cacheValue==null)
		{
			CDO.xmlToCDO(obj.toString(),cdoResponse);
			return;
		}
		String strFieldId = cacheValue.getFieldId();
		if(strFieldId==null)
		{
			CDO.xmlToCDO(obj.toString(),cdoResponse);
			return;
		}
		cdoResponse.setObjectExt(strFieldId,(ObjectExt)obj);
		
	}
}
