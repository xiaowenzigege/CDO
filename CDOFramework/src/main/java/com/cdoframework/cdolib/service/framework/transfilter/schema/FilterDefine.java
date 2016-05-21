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
import java.io.StringReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Enumeration;

import org.apache.log4j.Logger;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

/**
 * Class FilterDefine.
 * 
 * @version $Revision$ $Date$
 */
public class FilterDefine implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _defaultCacheId
     */
    private java.lang.String _defaultCacheId;

    /**
     * Field _filterList
     */
    private java.util.ArrayList _filterList;


      //----------------/
     //- Constructors -/
    //----------------/

    public FilterDefine() 
     {
        super();
        _filterList = new ArrayList();
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.FilterDefine()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addFilter
     * 
     * 
     * 
     * @param vFilter
     */
    public void addFilter(com.cdoframework.cdolib.service.framework.transfilter.schema.Filter vFilter)
        throws java.lang.IndexOutOfBoundsException
    {
        _filterList.add(vFilter);
    } //-- void addFilter(com.cdoframework.cdolib.service.framework.transfilter.schema.Filter) 

    /**
     * Method addFilter
     * 
     * 
     * 
     * @param index
     * @param vFilter
     */
    public void addFilter(int index, com.cdoframework.cdolib.service.framework.transfilter.schema.Filter vFilter)
        throws java.lang.IndexOutOfBoundsException
    {
        _filterList.add(index, vFilter);
    } //-- void addFilter(int, com.cdoframework.cdolib.service.framework.transfilter.schema.Filter) 

    /**
     * Method clearFilter
     * 
     */
    public void clearFilter()
    {
        _filterList.clear();
    } //-- void clearFilter() 

    /**
     * Method enumerateFilter
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateFilter()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_filterList.iterator());
    } //-- java.util.Enumeration enumerateFilter() 

    /**
     * Returns the value of field 'defaultCacheId'.
     * 
     * @return String
     * @return the value of field 'defaultCacheId'.
     */
    public java.lang.String getDefaultCacheId()
    {
        return this._defaultCacheId;
    } //-- java.lang.String getDefaultCacheId() 

    /**
     * Method getFilter
     * 
     * 
     * 
     * @param index
     * @return Filter
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.Filter getFilter(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _filterList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.Filter) _filterList.get(index);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.Filter getFilter(int) 

    /**
     * Method getFilter
     * 
     * 
     * 
     * @return Filter
     */
    public com.cdoframework.cdolib.service.framework.transfilter.schema.Filter[] getFilter()
    {
        int size = _filterList.size();
        com.cdoframework.cdolib.service.framework.transfilter.schema.Filter[] mArray = new com.cdoframework.cdolib.service.framework.transfilter.schema.Filter[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.service.framework.transfilter.schema.Filter) _filterList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.Filter[] getFilter() 

    /**
     * Method getFilterCount
     * 
     * 
     * 
     * @return int
     */
    public int getFilterCount()
    {
        return _filterList.size();
    } //-- int getFilterCount() 

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
     * Method removeFilter
     * 
     * 
     * 
     * @param vFilter
     * @return boolean
     */
    public boolean removeFilter(com.cdoframework.cdolib.service.framework.transfilter.schema.Filter vFilter)
    {
        boolean removed = _filterList.remove(vFilter);
        return removed;
    } //-- boolean removeFilter(com.cdoframework.cdolib.service.framework.transfilter.schema.Filter) 

    /**
     * Sets the value of field 'defaultCacheId'.
     * 
     * @param defaultCacheId the value of field 'defaultCacheId'.
     */
    public void setDefaultCacheId(java.lang.String defaultCacheId)
    {
        this._defaultCacheId = defaultCacheId;
    } //-- void setDefaultCacheId(java.lang.String) 

    /**
     * Method setFilter
     * 
     * 
     * 
     * @param index
     * @param vFilter
     */
    public void setFilter(int index, com.cdoframework.cdolib.service.framework.transfilter.schema.Filter vFilter)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _filterList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _filterList.set(index, vFilter);
    } //-- void setFilter(int, com.cdoframework.cdolib.service.framework.transfilter.schema.Filter) 

    /**
     * Method setFilter
     * 
     * 
     * 
     * @param filterArray
     */
    public void setFilter(com.cdoframework.cdolib.service.framework.transfilter.schema.Filter[] filterArray)
    {
        //-- copy array
        _filterList.clear();
        for (int i = 0; i < filterArray.length; i++) {
            _filterList.add(filterArray[i]);
        }
    } //-- void setFilter(com.cdoframework.cdolib.service.framework.transfilter.schema.Filter) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return FilterDefine
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.schema.FilterDefine unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.service.framework.transfilter.schema.FilterDefine) Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.schema.FilterDefine.class, reader);
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.FilterDefine unmarshal(java.io.Reader) 

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
    //------------------------------manual code----------------------
    static Logger logger = Logger.getLogger(FilterDefine.class);
    /**
	 * 从一个XML字件串解析成DataService对象
	 * 
	 * @param strXML
	 * @return
	 */
	public static FilterDefine fromXML(java.lang.String strXML) throws Exception
	{
		StringReader reader=null;
		try
		{
			reader=new StringReader(strXML);
			FilterDefine filterDefine=(FilterDefine)FilterDefine.unmarshal(reader);
			return filterDefine;
		}
		finally
		{
			if(reader!=null)
			{
				try
				{
					reader.close();
				}
				catch(Exception e)
				{
					logger.error("fromXML:"+e.getMessage(),e);
				}
			}
		}
	}
}
