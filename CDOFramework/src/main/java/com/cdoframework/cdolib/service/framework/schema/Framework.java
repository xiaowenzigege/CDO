/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.service.framework.schema;

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
 * Class Framework.
 * 
 * @version $Revision$ $Date$
 */
public class Framework implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _parameterList
     */
    private java.util.ArrayList _parameterList;

    /**
     * Field _memCacheServerGroupList
     */
    private java.util.ArrayList _memCacheServerGroupList;

    /**
     * Field _idNodeProcessor
     */
    private boolean _idNodeProcessor = false;

    /**
     * keeps track of state for field: _idNodeProcessor
     */
    private boolean _has_idNodeProcessor;

    /**
     * Field _URLCacheServerList
     */
    private java.util.ArrayList _URLCacheServerList;

    /**
     * Field _filterConfigList
     */
    private java.util.ArrayList _filterConfigList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Framework() 
     {
        super();
        _parameterList = new ArrayList();
        _memCacheServerGroupList = new ArrayList();
        _URLCacheServerList = new ArrayList();
        _filterConfigList = new ArrayList();
    } //-- com.cdoframework.cdolib.service.framework.schema.Framework()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addFilterConfig
     * 
     * 
     * 
     * @param vFilterConfig
     */
    public void addFilterConfig(com.cdoframework.cdolib.service.framework.schema.FilterConfig vFilterConfig)
        throws java.lang.IndexOutOfBoundsException
    {
        _filterConfigList.add(vFilterConfig);
    } //-- void addFilterConfig(com.cdoframework.cdolib.service.framework.schema.FilterConfig) 

    /**
     * Method addFilterConfig
     * 
     * 
     * 
     * @param index
     * @param vFilterConfig
     */
    public void addFilterConfig(int index, com.cdoframework.cdolib.service.framework.schema.FilterConfig vFilterConfig)
        throws java.lang.IndexOutOfBoundsException
    {
        _filterConfigList.add(index, vFilterConfig);
    } //-- void addFilterConfig(int, com.cdoframework.cdolib.service.framework.schema.FilterConfig) 

    /**
     * Method addMemCacheServerGroup
     * 
     * 
     * 
     * @param vMemCacheServerGroup
     */
    public void addMemCacheServerGroup(com.cdoframework.cdolib.service.framework.schema.MemCacheServerGroup vMemCacheServerGroup)
        throws java.lang.IndexOutOfBoundsException
    {
        _memCacheServerGroupList.add(vMemCacheServerGroup);
    } //-- void addMemCacheServerGroup(com.cdoframework.cdolib.service.framework.schema.MemCacheServerGroup) 

    /**
     * Method addMemCacheServerGroup
     * 
     * 
     * 
     * @param index
     * @param vMemCacheServerGroup
     */
    public void addMemCacheServerGroup(int index, com.cdoframework.cdolib.service.framework.schema.MemCacheServerGroup vMemCacheServerGroup)
        throws java.lang.IndexOutOfBoundsException
    {
        _memCacheServerGroupList.add(index, vMemCacheServerGroup);
    } //-- void addMemCacheServerGroup(int, com.cdoframework.cdolib.service.framework.schema.MemCacheServerGroup) 

    /**
     * Method addParameter
     * 
     * 
     * 
     * @param vParameter
     */
    public void addParameter(com.cdoframework.cdolib.service.framework.schema.Parameter vParameter)
        throws java.lang.IndexOutOfBoundsException
    {
        _parameterList.add(vParameter);
    } //-- void addParameter(com.cdoframework.cdolib.service.framework.schema.Parameter) 

    /**
     * Method addParameter
     * 
     * 
     * 
     * @param index
     * @param vParameter
     */
    public void addParameter(int index, com.cdoframework.cdolib.service.framework.schema.Parameter vParameter)
        throws java.lang.IndexOutOfBoundsException
    {
        _parameterList.add(index, vParameter);
    } //-- void addParameter(int, com.cdoframework.cdolib.service.framework.schema.Parameter) 

    /**
     * Method addURLCacheServer
     * 
     * 
     * 
     * @param vURLCacheServer
     */
    public void addURLCacheServer(com.cdoframework.cdolib.service.framework.schema.URLCacheServer vURLCacheServer)
        throws java.lang.IndexOutOfBoundsException
    {
        _URLCacheServerList.add(vURLCacheServer);
    } //-- void addURLCacheServer(com.cdoframework.cdolib.service.framework.schema.URLCacheServer) 

    /**
     * Method addURLCacheServer
     * 
     * 
     * 
     * @param index
     * @param vURLCacheServer
     */
    public void addURLCacheServer(int index, com.cdoframework.cdolib.service.framework.schema.URLCacheServer vURLCacheServer)
        throws java.lang.IndexOutOfBoundsException
    {
        _URLCacheServerList.add(index, vURLCacheServer);
    } //-- void addURLCacheServer(int, com.cdoframework.cdolib.service.framework.schema.URLCacheServer) 

    /**
     * Method clearFilterConfig
     * 
     */
    public void clearFilterConfig()
    {
        _filterConfigList.clear();
    } //-- void clearFilterConfig() 

    /**
     * Method clearMemCacheServerGroup
     * 
     */
    public void clearMemCacheServerGroup()
    {
        _memCacheServerGroupList.clear();
    } //-- void clearMemCacheServerGroup() 

    /**
     * Method clearParameter
     * 
     */
    public void clearParameter()
    {
        _parameterList.clear();
    } //-- void clearParameter() 

    /**
     * Method clearURLCacheServer
     * 
     */
    public void clearURLCacheServer()
    {
        _URLCacheServerList.clear();
    } //-- void clearURLCacheServer() 

    /**
     * Method deleteIdNodeProcessor
     * 
     */
    public void deleteIdNodeProcessor()
    {
        this._has_idNodeProcessor= false;
    } //-- void deleteIdNodeProcessor() 

    /**
     * Method enumerateFilterConfig
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateFilterConfig()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_filterConfigList.iterator());
    } //-- java.util.Enumeration enumerateFilterConfig() 

    /**
     * Method enumerateMemCacheServerGroup
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateMemCacheServerGroup()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_memCacheServerGroupList.iterator());
    } //-- java.util.Enumeration enumerateMemCacheServerGroup() 

    /**
     * Method enumerateParameter
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateParameter()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_parameterList.iterator());
    } //-- java.util.Enumeration enumerateParameter() 

    /**
     * Method enumerateURLCacheServer
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateURLCacheServer()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_URLCacheServerList.iterator());
    } //-- java.util.Enumeration enumerateURLCacheServer() 

    /**
     * Method getFilterConfig
     * 
     * 
     * 
     * @param index
     * @return FilterConfig
     */
    public com.cdoframework.cdolib.service.framework.schema.FilterConfig getFilterConfig(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _filterConfigList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.service.framework.schema.FilterConfig) _filterConfigList.get(index);
    } //-- com.cdoframework.cdolib.service.framework.schema.FilterConfig getFilterConfig(int) 

    /**
     * Method getFilterConfig
     * 
     * 
     * 
     * @return FilterConfig
     */
    public com.cdoframework.cdolib.service.framework.schema.FilterConfig[] getFilterConfig()
    {
        int size = _filterConfigList.size();
        com.cdoframework.cdolib.service.framework.schema.FilterConfig[] mArray = new com.cdoframework.cdolib.service.framework.schema.FilterConfig[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.service.framework.schema.FilterConfig) _filterConfigList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.service.framework.schema.FilterConfig[] getFilterConfig() 

    /**
     * Method getFilterConfigCount
     * 
     * 
     * 
     * @return int
     */
    public int getFilterConfigCount()
    {
        return _filterConfigList.size();
    } //-- int getFilterConfigCount() 

    /**
     * Returns the value of field 'idNodeProcessor'.
     * 
     * @return boolean
     * @return the value of field 'idNodeProcessor'.
     */
    public boolean getIdNodeProcessor()
    {
        return this._idNodeProcessor;
    } //-- boolean getIdNodeProcessor() 

    /**
     * Method getMemCacheServerGroup
     * 
     * 
     * 
     * @param index
     * @return MemCacheServerGroup
     */
    public com.cdoframework.cdolib.service.framework.schema.MemCacheServerGroup getMemCacheServerGroup(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _memCacheServerGroupList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.service.framework.schema.MemCacheServerGroup) _memCacheServerGroupList.get(index);
    } //-- com.cdoframework.cdolib.service.framework.schema.MemCacheServerGroup getMemCacheServerGroup(int) 

    /**
     * Method getMemCacheServerGroup
     * 
     * 
     * 
     * @return MemCacheServerGroup
     */
    public com.cdoframework.cdolib.service.framework.schema.MemCacheServerGroup[] getMemCacheServerGroup()
    {
        int size = _memCacheServerGroupList.size();
        com.cdoframework.cdolib.service.framework.schema.MemCacheServerGroup[] mArray = new com.cdoframework.cdolib.service.framework.schema.MemCacheServerGroup[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.service.framework.schema.MemCacheServerGroup) _memCacheServerGroupList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.service.framework.schema.MemCacheServerGroup[] getMemCacheServerGroup() 

    /**
     * Method getMemCacheServerGroupCount
     * 
     * 
     * 
     * @return int
     */
    public int getMemCacheServerGroupCount()
    {
        return _memCacheServerGroupList.size();
    } //-- int getMemCacheServerGroupCount() 

    /**
     * Method getParameter
     * 
     * 
     * 
     * @param index
     * @return Parameter
     */
    public com.cdoframework.cdolib.service.framework.schema.Parameter getParameter(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _parameterList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.service.framework.schema.Parameter) _parameterList.get(index);
    } //-- com.cdoframework.cdolib.service.framework.schema.Parameter getParameter(int) 

    /**
     * Method getParameter
     * 
     * 
     * 
     * @return Parameter
     */
    public com.cdoframework.cdolib.service.framework.schema.Parameter[] getParameter()
    {
        int size = _parameterList.size();
        com.cdoframework.cdolib.service.framework.schema.Parameter[] mArray = new com.cdoframework.cdolib.service.framework.schema.Parameter[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.service.framework.schema.Parameter) _parameterList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.service.framework.schema.Parameter[] getParameter() 

    /**
     * Method getParameterCount
     * 
     * 
     * 
     * @return int
     */
    public int getParameterCount()
    {
        return _parameterList.size();
    } //-- int getParameterCount() 

    /**
     * Method getURLCacheServer
     * 
     * 
     * 
     * @param index
     * @return URLCacheServer
     */
    public com.cdoframework.cdolib.service.framework.schema.URLCacheServer getURLCacheServer(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _URLCacheServerList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.service.framework.schema.URLCacheServer) _URLCacheServerList.get(index);
    } //-- com.cdoframework.cdolib.service.framework.schema.URLCacheServer getURLCacheServer(int) 

    /**
     * Method getURLCacheServer
     * 
     * 
     * 
     * @return URLCacheServer
     */
    public com.cdoframework.cdolib.service.framework.schema.URLCacheServer[] getURLCacheServer()
    {
        int size = _URLCacheServerList.size();
        com.cdoframework.cdolib.service.framework.schema.URLCacheServer[] mArray = new com.cdoframework.cdolib.service.framework.schema.URLCacheServer[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.service.framework.schema.URLCacheServer) _URLCacheServerList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.service.framework.schema.URLCacheServer[] getURLCacheServer() 

    /**
     * Method getURLCacheServerCount
     * 
     * 
     * 
     * @return int
     */
    public int getURLCacheServerCount()
    {
        return _URLCacheServerList.size();
    } //-- int getURLCacheServerCount() 

    /**
     * Method hasIdNodeProcessor
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasIdNodeProcessor()
    {
        return this._has_idNodeProcessor;
    } //-- boolean hasIdNodeProcessor() 

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
     * Method removeFilterConfig
     * 
     * 
     * 
     * @param vFilterConfig
     * @return boolean
     */
    public boolean removeFilterConfig(com.cdoframework.cdolib.service.framework.schema.FilterConfig vFilterConfig)
    {
        boolean removed = _filterConfigList.remove(vFilterConfig);
        return removed;
    } //-- boolean removeFilterConfig(com.cdoframework.cdolib.service.framework.schema.FilterConfig) 

    /**
     * Method removeMemCacheServerGroup
     * 
     * 
     * 
     * @param vMemCacheServerGroup
     * @return boolean
     */
    public boolean removeMemCacheServerGroup(com.cdoframework.cdolib.service.framework.schema.MemCacheServerGroup vMemCacheServerGroup)
    {
        boolean removed = _memCacheServerGroupList.remove(vMemCacheServerGroup);
        return removed;
    } //-- boolean removeMemCacheServerGroup(com.cdoframework.cdolib.service.framework.schema.MemCacheServerGroup) 

    /**
     * Method removeParameter
     * 
     * 
     * 
     * @param vParameter
     * @return boolean
     */
    public boolean removeParameter(com.cdoframework.cdolib.service.framework.schema.Parameter vParameter)
    {
        boolean removed = _parameterList.remove(vParameter);
        return removed;
    } //-- boolean removeParameter(com.cdoframework.cdolib.service.framework.schema.Parameter) 

    /**
     * Method removeURLCacheServer
     * 
     * 
     * 
     * @param vURLCacheServer
     * @return boolean
     */
    public boolean removeURLCacheServer(com.cdoframework.cdolib.service.framework.schema.URLCacheServer vURLCacheServer)
    {
        boolean removed = _URLCacheServerList.remove(vURLCacheServer);
        return removed;
    } //-- boolean removeURLCacheServer(com.cdoframework.cdolib.service.framework.schema.URLCacheServer) 

    /**
     * Method setFilterConfig
     * 
     * 
     * 
     * @param index
     * @param vFilterConfig
     */
    public void setFilterConfig(int index, com.cdoframework.cdolib.service.framework.schema.FilterConfig vFilterConfig)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _filterConfigList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _filterConfigList.set(index, vFilterConfig);
    } //-- void setFilterConfig(int, com.cdoframework.cdolib.service.framework.schema.FilterConfig) 

    /**
     * Method setFilterConfig
     * 
     * 
     * 
     * @param filterConfigArray
     */
    public void setFilterConfig(com.cdoframework.cdolib.service.framework.schema.FilterConfig[] filterConfigArray)
    {
        //-- copy array
        _filterConfigList.clear();
        for (int i = 0; i < filterConfigArray.length; i++) {
            _filterConfigList.add(filterConfigArray[i]);
        }
    } //-- void setFilterConfig(com.cdoframework.cdolib.service.framework.schema.FilterConfig) 

    /**
     * Sets the value of field 'idNodeProcessor'.
     * 
     * @param idNodeProcessor the value of field 'idNodeProcessor'.
     */
    public void setIdNodeProcessor(boolean idNodeProcessor)
    {
        this._idNodeProcessor = idNodeProcessor;
        this._has_idNodeProcessor = true;
    } //-- void setIdNodeProcessor(boolean) 

    /**
     * Method setMemCacheServerGroup
     * 
     * 
     * 
     * @param index
     * @param vMemCacheServerGroup
     */
    public void setMemCacheServerGroup(int index, com.cdoframework.cdolib.service.framework.schema.MemCacheServerGroup vMemCacheServerGroup)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _memCacheServerGroupList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _memCacheServerGroupList.set(index, vMemCacheServerGroup);
    } //-- void setMemCacheServerGroup(int, com.cdoframework.cdolib.service.framework.schema.MemCacheServerGroup) 

    /**
     * Method setMemCacheServerGroup
     * 
     * 
     * 
     * @param memCacheServerGroupArray
     */
    public void setMemCacheServerGroup(com.cdoframework.cdolib.service.framework.schema.MemCacheServerGroup[] memCacheServerGroupArray)
    {
        //-- copy array
        _memCacheServerGroupList.clear();
        for (int i = 0; i < memCacheServerGroupArray.length; i++) {
            _memCacheServerGroupList.add(memCacheServerGroupArray[i]);
        }
    } //-- void setMemCacheServerGroup(com.cdoframework.cdolib.service.framework.schema.MemCacheServerGroup) 

    /**
     * Method setParameter
     * 
     * 
     * 
     * @param index
     * @param vParameter
     */
    public void setParameter(int index, com.cdoframework.cdolib.service.framework.schema.Parameter vParameter)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _parameterList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _parameterList.set(index, vParameter);
    } //-- void setParameter(int, com.cdoframework.cdolib.service.framework.schema.Parameter) 

    /**
     * Method setParameter
     * 
     * 
     * 
     * @param parameterArray
     */
    public void setParameter(com.cdoframework.cdolib.service.framework.schema.Parameter[] parameterArray)
    {
        //-- copy array
        _parameterList.clear();
        for (int i = 0; i < parameterArray.length; i++) {
            _parameterList.add(parameterArray[i]);
        }
    } //-- void setParameter(com.cdoframework.cdolib.service.framework.schema.Parameter) 

    /**
     * Method setURLCacheServer
     * 
     * 
     * 
     * @param index
     * @param vURLCacheServer
     */
    public void setURLCacheServer(int index, com.cdoframework.cdolib.service.framework.schema.URLCacheServer vURLCacheServer)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _URLCacheServerList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _URLCacheServerList.set(index, vURLCacheServer);
    } //-- void setURLCacheServer(int, com.cdoframework.cdolib.service.framework.schema.URLCacheServer) 

    /**
     * Method setURLCacheServer
     * 
     * 
     * 
     * @param URLCacheServerArray
     */
    public void setURLCacheServer(com.cdoframework.cdolib.service.framework.schema.URLCacheServer[] URLCacheServerArray)
    {
        //-- copy array
        _URLCacheServerList.clear();
        for (int i = 0; i < URLCacheServerArray.length; i++) {
            _URLCacheServerList.add(URLCacheServerArray[i]);
        }
    } //-- void setURLCacheServer(com.cdoframework.cdolib.service.framework.schema.URLCacheServer) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Framework
     */
    public static com.cdoframework.cdolib.service.framework.schema.Framework unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.service.framework.schema.Framework) Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.schema.Framework.class, reader);
    } //-- com.cdoframework.cdolib.service.framework.schema.Framework unmarshal(java.io.Reader) 

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
