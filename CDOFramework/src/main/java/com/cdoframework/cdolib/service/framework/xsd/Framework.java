/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.3</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.service.framework.xsd;

import java.io.StringReader;

import org.apache.log4j.Logger;


/**
 * 
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class Framework implements java.io.Serializable {

    private java.util.List<com.cdoframework.cdolib.service.framework.xsd.Parameter> parameterList;

    private java.util.List<com.cdoframework.cdolib.service.framework.xsd.MemCacheServerGroup> memCacheServerGroupList;

    /**
     * Field idNodeProcessor.
     */
    private boolean idNodeProcessor = false;

    /**
     * Keeps track of whether primitive field idNodeProcessor has
     * been set already.
     */
    private boolean _hasidNodeProcessor;

    private java.util.List<com.cdoframework.cdolib.service.framework.xsd.URLCacheServer> URLCacheServerList;

    private java.util.List<com.cdoframework.cdolib.service.framework.xsd.FilterConfig> filterConfigList;

    public Framework() {
        super();
        this.parameterList = new java.util.ArrayList<com.cdoframework.cdolib.service.framework.xsd.Parameter>();
        this.memCacheServerGroupList = new java.util.ArrayList<com.cdoframework.cdolib.service.framework.xsd.MemCacheServerGroup>();
        this.URLCacheServerList = new java.util.ArrayList<com.cdoframework.cdolib.service.framework.xsd.URLCacheServer>();
        this.filterConfigList = new java.util.ArrayList<com.cdoframework.cdolib.service.framework.xsd.FilterConfig>();
    }

    /**
     * 
     * 
     * @param vFilterConfig
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addFilterConfig(final com.cdoframework.cdolib.service.framework.xsd.FilterConfig vFilterConfig) throws java.lang.IndexOutOfBoundsException {
        this.filterConfigList.add(vFilterConfig);
    }

    /**
     * 
     * 
     * @param index
     * @param vFilterConfig
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addFilterConfig(final int index,final com.cdoframework.cdolib.service.framework.xsd.FilterConfig vFilterConfig) throws java.lang.IndexOutOfBoundsException {
        this.filterConfigList.add(index, vFilterConfig);
    }

    /**
     * 
     * 
     * @param vMemCacheServerGroup
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addMemCacheServerGroup(final com.cdoframework.cdolib.service.framework.xsd.MemCacheServerGroup vMemCacheServerGroup) throws java.lang.IndexOutOfBoundsException {
        this.memCacheServerGroupList.add(vMemCacheServerGroup);
    }

    /**
     * 
     * 
     * @param index
     * @param vMemCacheServerGroup
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addMemCacheServerGroup(final int index,final com.cdoframework.cdolib.service.framework.xsd.MemCacheServerGroup vMemCacheServerGroup) throws java.lang.IndexOutOfBoundsException {
        this.memCacheServerGroupList.add(index, vMemCacheServerGroup);
    }

    /**
     * 
     * 
     * @param vParameter
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addParameter(final com.cdoframework.cdolib.service.framework.xsd.Parameter vParameter) throws java.lang.IndexOutOfBoundsException {
        this.parameterList.add(vParameter);
    }

    /**
     * 
     * 
     * @param index
     * @param vParameter
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addParameter(final int index,final com.cdoframework.cdolib.service.framework.xsd.Parameter vParameter) throws java.lang.IndexOutOfBoundsException {
        this.parameterList.add(index, vParameter);
    }

    /**
     * 
     * 
     * @param vURLCacheServer
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addURLCacheServer(final com.cdoframework.cdolib.service.framework.xsd.URLCacheServer vURLCacheServer) throws java.lang.IndexOutOfBoundsException {
        this.URLCacheServerList.add(vURLCacheServer);
    }

    /**
     * 
     * 
     * @param index
     * @param vURLCacheServer
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addURLCacheServer(final int index,final com.cdoframework.cdolib.service.framework.xsd.URLCacheServer vURLCacheServer) throws java.lang.IndexOutOfBoundsException {
        this.URLCacheServerList.add(index, vURLCacheServer);
    }

    /**
     */
    public void deleteIdNodeProcessor() {
        this._hasidNodeProcessor= false;
    }

    /**
     * Method enumerateFilterConfig.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.service.framework.xsd.FilterConfig> enumerateFilterConfig() {
        return java.util.Collections.enumeration(this.filterConfigList);
    }

    /**
     * Method enumerateMemCacheServerGroup.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.service.framework.xsd.MemCacheServerGroup> enumerateMemCacheServerGroup() {
        return java.util.Collections.enumeration(this.memCacheServerGroupList);
    }

    /**
     * Method enumerateParameter.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.service.framework.xsd.Parameter> enumerateParameter() {
        return java.util.Collections.enumeration(this.parameterList);
    }

    /**
     * Method enumerateURLCacheServer.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.service.framework.xsd.URLCacheServer> enumerateURLCacheServer() {
        return java.util.Collections.enumeration(this.URLCacheServerList);
    }

    /**
     * Method getFilterConfig.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.service.framework.xsd.FilterConfig
     * at the given index
     */
    public com.cdoframework.cdolib.service.framework.xsd.FilterConfig getFilterConfig(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.filterConfigList.size()) {
            throw new IndexOutOfBoundsException("getFilterConfig: Index value '" + index + "' not in range [0.." + (this.filterConfigList.size() - 1) + "]");
        }

        return filterConfigList.get(index);
    }

    /**
     * Method getFilterConfig.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.service.framework.xsd.FilterConfig[] getFilterConfig() {
        com.cdoframework.cdolib.service.framework.xsd.FilterConfig[] array = new com.cdoframework.cdolib.service.framework.xsd.FilterConfig[0];
        return this.filterConfigList.toArray(array);
    }

    /**
     * Method getFilterConfigCount.
     * 
     * @return the size of this collection
     */
    public int getFilterConfigCount() {
        return this.filterConfigList.size();
    }

    /**
     * Returns the value of field 'idNodeProcessor'.
     * 
     * @return the value of field 'IdNodeProcessor'.
     */
    public boolean getIdNodeProcessor() {
        return this.idNodeProcessor;
    }

    /**
     * Method getMemCacheServerGroup.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.service.framework.xsd.MemCacheServerGroup
     * at the given index
     */
    public com.cdoframework.cdolib.service.framework.xsd.MemCacheServerGroup getMemCacheServerGroup(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.memCacheServerGroupList.size()) {
            throw new IndexOutOfBoundsException("getMemCacheServerGroup: Index value '" + index + "' not in range [0.." + (this.memCacheServerGroupList.size() - 1) + "]");
        }

        return memCacheServerGroupList.get(index);
    }

    /**
     * Method getMemCacheServerGroup.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.service.framework.xsd.MemCacheServerGroup[] getMemCacheServerGroup() {
        com.cdoframework.cdolib.service.framework.xsd.MemCacheServerGroup[] array = new com.cdoframework.cdolib.service.framework.xsd.MemCacheServerGroup[0];
        return this.memCacheServerGroupList.toArray(array);
    }

    /**
     * Method getMemCacheServerGroupCount.
     * 
     * @return the size of this collection
     */
    public int getMemCacheServerGroupCount() {
        return this.memCacheServerGroupList.size();
    }

    /**
     * Method getParameter.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.service.framework.xsd.Parameter at
     * the given index
     */
    public com.cdoframework.cdolib.service.framework.xsd.Parameter getParameter(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.parameterList.size()) {
            throw new IndexOutOfBoundsException("getParameter: Index value '" + index + "' not in range [0.." + (this.parameterList.size() - 1) + "]");
        }

        return parameterList.get(index);
    }

    /**
     * Method getParameter.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.service.framework.xsd.Parameter[] getParameter() {
        com.cdoframework.cdolib.service.framework.xsd.Parameter[] array = new com.cdoframework.cdolib.service.framework.xsd.Parameter[0];
        return this.parameterList.toArray(array);
    }

    /**
     * Method getParameterCount.
     * 
     * @return the size of this collection
     */
    public int getParameterCount() {
        return this.parameterList.size();
    }

    /**
     * Method getURLCacheServer.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.service.framework.xsd.URLCacheServer
     * at the given index
     */
    public com.cdoframework.cdolib.service.framework.xsd.URLCacheServer getURLCacheServer(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.URLCacheServerList.size()) {
            throw new IndexOutOfBoundsException("getURLCacheServer: Index value '" + index + "' not in range [0.." + (this.URLCacheServerList.size() - 1) + "]");
        }

        return URLCacheServerList.get(index);
    }

    /**
     * Method getURLCacheServer.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.service.framework.xsd.URLCacheServer[] getURLCacheServer() {
        com.cdoframework.cdolib.service.framework.xsd.URLCacheServer[] array = new com.cdoframework.cdolib.service.framework.xsd.URLCacheServer[0];
        return this.URLCacheServerList.toArray(array);
    }

    /**
     * Method getURLCacheServerCount.
     * 
     * @return the size of this collection
     */
    public int getURLCacheServerCount() {
        return this.URLCacheServerList.size();
    }

    /**
     * Method hasIdNodeProcessor.
     * 
     * @return true if at least one IdNodeProcessor has been added
     */
    public boolean hasIdNodeProcessor() {
        return this._hasidNodeProcessor;
    }

    /**
     * Returns the value of field 'idNodeProcessor'.
     * 
     * @return the value of field 'IdNodeProcessor'.
     */
    public boolean isIdNodeProcessor() {
        return this.idNodeProcessor;
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
     * Method iterateFilterConfig.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.service.framework.xsd.FilterConfig> iterateFilterConfig() {
        return this.filterConfigList.iterator();
    }

    /**
     * Method iterateMemCacheServerGroup.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.service.framework.xsd.MemCacheServerGroup> iterateMemCacheServerGroup() {
        return this.memCacheServerGroupList.iterator();
    }

    /**
     * Method iterateParameter.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.service.framework.xsd.Parameter> iterateParameter() {
        return this.parameterList.iterator();
    }

    /**
     * Method iterateURLCacheServer.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.service.framework.xsd.URLCacheServer> iterateURLCacheServer() {
        return this.URLCacheServerList.iterator();
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
    public void removeAllFilterConfig() {
        this.filterConfigList.clear();
    }

    /**
     */
    public void removeAllMemCacheServerGroup() {
        this.memCacheServerGroupList.clear();
    }

    /**
     */
    public void removeAllParameter() {
        this.parameterList.clear();
    }

    /**
     */
    public void removeAllURLCacheServer() {
        this.URLCacheServerList.clear();
    }

    /**
     * Method removeFilterConfig.
     * 
     * @param vFilterConfig
     * @return true if the object was removed from the collection.
     */
    public boolean removeFilterConfig(final com.cdoframework.cdolib.service.framework.xsd.FilterConfig vFilterConfig) {
        boolean removed = filterConfigList.remove(vFilterConfig);
        return removed;
    }

    /**
     * Method removeFilterConfigAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.service.framework.xsd.FilterConfig removeFilterConfigAt(final int index) {
        java.lang.Object obj = this.filterConfigList.remove(index);
        return (com.cdoframework.cdolib.service.framework.xsd.FilterConfig) obj;
    }

    /**
     * Method removeMemCacheServerGroup.
     * 
     * @param vMemCacheServerGroup
     * @return true if the object was removed from the collection.
     */
    public boolean removeMemCacheServerGroup(final com.cdoframework.cdolib.service.framework.xsd.MemCacheServerGroup vMemCacheServerGroup) {
        boolean removed = memCacheServerGroupList.remove(vMemCacheServerGroup);
        return removed;
    }

    /**
     * Method removeMemCacheServerGroupAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.service.framework.xsd.MemCacheServerGroup removeMemCacheServerGroupAt(final int index) {
        java.lang.Object obj = this.memCacheServerGroupList.remove(index);
        return (com.cdoframework.cdolib.service.framework.xsd.MemCacheServerGroup) obj;
    }

    /**
     * Method removeParameter.
     * 
     * @param vParameter
     * @return true if the object was removed from the collection.
     */
    public boolean removeParameter(final com.cdoframework.cdolib.service.framework.xsd.Parameter vParameter) {
        boolean removed = parameterList.remove(vParameter);
        return removed;
    }

    /**
     * Method removeParameterAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.service.framework.xsd.Parameter removeParameterAt(final int index) {
        java.lang.Object obj = this.parameterList.remove(index);
        return (com.cdoframework.cdolib.service.framework.xsd.Parameter) obj;
    }

    /**
     * Method removeURLCacheServer.
     * 
     * @param vURLCacheServer
     * @return true if the object was removed from the collection.
     */
    public boolean removeURLCacheServer(final com.cdoframework.cdolib.service.framework.xsd.URLCacheServer vURLCacheServer) {
        boolean removed = URLCacheServerList.remove(vURLCacheServer);
        return removed;
    }

    /**
     * Method removeURLCacheServerAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.service.framework.xsd.URLCacheServer removeURLCacheServerAt(final int index) {
        java.lang.Object obj = this.URLCacheServerList.remove(index);
        return (com.cdoframework.cdolib.service.framework.xsd.URLCacheServer) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vFilterConfig
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setFilterConfig(final int index,final com.cdoframework.cdolib.service.framework.xsd.FilterConfig vFilterConfig) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.filterConfigList.size()) {
            throw new IndexOutOfBoundsException("setFilterConfig: Index value '" + index + "' not in range [0.." + (this.filterConfigList.size() - 1) + "]");
        }

        this.filterConfigList.set(index, vFilterConfig);
    }

    /**
     * 
     * 
     * @param vFilterConfigArray
     */
    public void setFilterConfig(final com.cdoframework.cdolib.service.framework.xsd.FilterConfig[] vFilterConfigArray) {
        //-- copy array
        filterConfigList.clear();

        for (int i = 0; i < vFilterConfigArray.length; i++) {
                this.filterConfigList.add(vFilterConfigArray[i]);
        }
    }

    /**
     * Sets the value of field 'idNodeProcessor'.
     * 
     * @param idNodeProcessor the value of field 'idNodeProcessor'.
     */
    public void setIdNodeProcessor(final boolean idNodeProcessor) {
        this.idNodeProcessor = idNodeProcessor;
        this._hasidNodeProcessor = true;
    }

    /**
     * 
     * 
     * @param index
     * @param vMemCacheServerGroup
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setMemCacheServerGroup(final int index,final com.cdoframework.cdolib.service.framework.xsd.MemCacheServerGroup vMemCacheServerGroup) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.memCacheServerGroupList.size()) {
            throw new IndexOutOfBoundsException("setMemCacheServerGroup: Index value '" + index + "' not in range [0.." + (this.memCacheServerGroupList.size() - 1) + "]");
        }

        this.memCacheServerGroupList.set(index, vMemCacheServerGroup);
    }

    /**
     * 
     * 
     * @param vMemCacheServerGroupArray
     */
    public void setMemCacheServerGroup(final com.cdoframework.cdolib.service.framework.xsd.MemCacheServerGroup[] vMemCacheServerGroupArray) {
        //-- copy array
        memCacheServerGroupList.clear();

        for (int i = 0; i < vMemCacheServerGroupArray.length; i++) {
                this.memCacheServerGroupList.add(vMemCacheServerGroupArray[i]);
        }
    }

    /**
     * 
     * 
     * @param index
     * @param vParameter
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setParameter(final int index,final com.cdoframework.cdolib.service.framework.xsd.Parameter vParameter) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.parameterList.size()) {
            throw new IndexOutOfBoundsException("setParameter: Index value '" + index + "' not in range [0.." + (this.parameterList.size() - 1) + "]");
        }

        this.parameterList.set(index, vParameter);
    }

    /**
     * 
     * 
     * @param vParameterArray
     */
    public void setParameter(final com.cdoframework.cdolib.service.framework.xsd.Parameter[] vParameterArray) {
        //-- copy array
        parameterList.clear();

        for (int i = 0; i < vParameterArray.length; i++) {
                this.parameterList.add(vParameterArray[i]);
        }
    }

    /**
     * 
     * 
     * @param index
     * @param vURLCacheServer
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setURLCacheServer(final int index,final com.cdoframework.cdolib.service.framework.xsd.URLCacheServer vURLCacheServer) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.URLCacheServerList.size()) {
            throw new IndexOutOfBoundsException("setURLCacheServer: Index value '" + index + "' not in range [0.." + (this.URLCacheServerList.size() - 1) + "]");
        }

        this.URLCacheServerList.set(index, vURLCacheServer);
    }

    /**
     * 
     * 
     * @param vURLCacheServerArray
     */
    public void setURLCacheServer(final com.cdoframework.cdolib.service.framework.xsd.URLCacheServer[] vURLCacheServerArray) {
        //-- copy array
        URLCacheServerList.clear();

        for (int i = 0; i < vURLCacheServerArray.length; i++) {
                this.URLCacheServerList.add(vURLCacheServerArray[i]);
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
     * com.cdoframework.cdolib.service.framework.xsd.Framework
     */
    public static com.cdoframework.cdolib.service.framework.xsd.Framework unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.service.framework.xsd.Framework) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.xsd.Framework.class, reader);
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
    //--------------------------------------------manual code ------------------------------------------------
    static Logger logger = Logger.getLogger(Framework.class);
	
    /**
     * 从一个XML字件串解析成BusinessPlugin对象
     * @param strXML
     * @return
     */
    public static Framework   fromXML(String strXML) throws Exception
    {
    	StringReader reader=null;
    	try
    	{
    		reader=new StringReader(strXML);
    		Framework framework = (Framework)Framework.unmarshal(reader);


    		return framework;
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
