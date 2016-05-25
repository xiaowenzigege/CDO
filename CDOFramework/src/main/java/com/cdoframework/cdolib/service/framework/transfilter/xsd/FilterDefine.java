/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.3</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.service.framework.transfilter.xsd;

import java.io.StringReader;

import org.apache.log4j.Logger;

/**
 * 
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class FilterDefine implements java.io.Serializable {

    /**
     * Field defaultCacheId.
     */
    private java.lang.String defaultCacheId;

    private java.util.List<com.cdoframework.cdolib.service.framework.transfilter.xsd.Filter> filterList;

    public FilterDefine() {
        super();
        this.filterList = new java.util.ArrayList<com.cdoframework.cdolib.service.framework.transfilter.xsd.Filter>();
    }

    /**
     * 
     * 
     * @param vFilter
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addFilter(final com.cdoframework.cdolib.service.framework.transfilter.xsd.Filter vFilter) throws java.lang.IndexOutOfBoundsException {
        this.filterList.add(vFilter);
    }

    /**
     * 
     * 
     * @param index
     * @param vFilter
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addFilter(final int index,final com.cdoframework.cdolib.service.framework.transfilter.xsd.Filter vFilter) throws java.lang.IndexOutOfBoundsException {
        this.filterList.add(index, vFilter);
    }

    /**
     * Method enumerateFilter.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.service.framework.transfilter.xsd.Filter> enumerateFilter() {
        return java.util.Collections.enumeration(this.filterList);
    }

    /**
     * Returns the value of field 'defaultCacheId'.
     * 
     * @return the value of field 'DefaultCacheId'.
     */
    public java.lang.String getDefaultCacheId() {
        return this.defaultCacheId;
    }

    /**
     * Method getFilter.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.service.framework.transfilter.xsd.Filter
     * at the given index
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.Filter getFilter(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.filterList.size()) {
            throw new IndexOutOfBoundsException("getFilter: Index value '" + index + "' not in range [0.." + (this.filterList.size() - 1) + "]");
        }

        return filterList.get(index);
    }

    /**
     * Method getFilter.Returns the contents of the collection in
     * an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.Filter[] getFilter() {
        com.cdoframework.cdolib.service.framework.transfilter.xsd.Filter[] array = new com.cdoframework.cdolib.service.framework.transfilter.xsd.Filter[0];
        return this.filterList.toArray(array);
    }

    /**
     * Method getFilterCount.
     * 
     * @return the size of this collection
     */
    public int getFilterCount() {
        return this.filterList.size();
    }

    /**
     * Method isValid.
     * 
     * @return true if this object is valid according to the schema
     */
    public boolean isValid() {
        try {
            validate();
        } catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    }

    /**
     * Method iterateFilter.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.service.framework.transfilter.xsd.Filter> iterateFilter() {
        return this.filterList.iterator();
    }

    /**
     * 
     * 
     * @param out
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void marshal(final java.io.Writer out) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Marshaller.marshal(this, out);
    }

    /**
     * 
     * 
     * @param handler
     * @throws java.io.IOException if an IOException occurs during
     * marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     */
    public void marshal(final org.xml.sax.ContentHandler handler) throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Marshaller.marshal(this, handler);
    }

    /**
     */
    public void removeAllFilter() {
        this.filterList.clear();
    }

    /**
     * Method removeFilter.
     * 
     * @param vFilter
     * @return true if the object was removed from the collection.
     */
    public boolean removeFilter(final com.cdoframework.cdolib.service.framework.transfilter.xsd.Filter vFilter) {
        boolean removed = filterList.remove(vFilter);
        return removed;
    }

    /**
     * Method removeFilterAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.Filter removeFilterAt(final int index) {
        java.lang.Object obj = this.filterList.remove(index);
        return (com.cdoframework.cdolib.service.framework.transfilter.xsd.Filter) obj;
    }

    /**
     * Sets the value of field 'defaultCacheId'.
     * 
     * @param defaultCacheId the value of field 'defaultCacheId'.
     */
    public void setDefaultCacheId(final java.lang.String defaultCacheId) {
        this.defaultCacheId = defaultCacheId;
    }

    /**
     * 
     * 
     * @param index
     * @param vFilter
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setFilter(final int index,final com.cdoframework.cdolib.service.framework.transfilter.xsd.Filter vFilter) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.filterList.size()) {
            throw new IndexOutOfBoundsException("setFilter: Index value '" + index + "' not in range [0.." + (this.filterList.size() - 1) + "]");
        }

        this.filterList.set(index, vFilter);
    }

    /**
     * 
     * 
     * @param vFilterArray
     */
    public void setFilter(final com.cdoframework.cdolib.service.framework.transfilter.xsd.Filter[] vFilterArray) {
        //-- copy array
        filterList.clear();

        for (int i = 0; i < vFilterArray.length; i++) {
                this.filterList.add(vFilterArray[i]);
        }
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled
     * com.cdoframework.cdolib.service.framework.transfilter.xsd.FilterDefine
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.xsd.FilterDefine unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.service.framework.transfilter.xsd.FilterDefine) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.xsd.FilterDefine.class, reader);
    }

    /**
     * 
     * 
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void validate() throws org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    }
    
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
