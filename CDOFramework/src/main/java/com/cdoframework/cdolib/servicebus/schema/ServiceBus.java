/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.servicebus.schema;

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
 * Class ServiceBus.
 * 
 * @version $Revision$ $Date$
 */
public class ServiceBus implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _dataGroupId
     */
    private java.lang.String _dataGroupId;

    /**
     * Field _noSQLDBId
     */
    private java.lang.String _noSQLDBId;

    /**
     * Field _parameterList
     */
    private java.util.ArrayList _parameterList;

    /**
     * Field _dataGroupList
     */
    private java.util.ArrayList _dataGroupList;

    /**
     * Field _noSQLDBList
     */
    private java.util.ArrayList _noSQLDBList;

    /**
     * Field _clusterController
     */
    private com.cdoframework.cdolib.servicebus.schema.ClusterController _clusterController;

    /**
     * Field _pluginXMLResourceList
     */
    private java.util.ArrayList _pluginXMLResourceList;

    /**
     * Field _eventProcessor
     */
    private com.cdoframework.cdolib.servicebus.schema.EventProcessor _eventProcessor;


      //----------------/
     //- Constructors -/
    //----------------/

    public ServiceBus() 
     {
        super();
        _parameterList = new ArrayList();
        _dataGroupList = new ArrayList();
        _noSQLDBList = new ArrayList();
        _pluginXMLResourceList = new ArrayList();
    } //-- com.cdoframework.cdolib.servicebus.schema.ServiceBus()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addDataGroup
     * 
     * 
     * 
     * @param vDataGroup
     */
    public void addDataGroup(com.cdoframework.cdolib.servicebus.schema.DataGroup vDataGroup)
        throws java.lang.IndexOutOfBoundsException
    {
        _dataGroupList.add(vDataGroup);
    } //-- void addDataGroup(com.cdoframework.cdolib.servicebus.schema.DataGroup) 

    /**
     * Method addDataGroup
     * 
     * 
     * 
     * @param index
     * @param vDataGroup
     */
    public void addDataGroup(int index, com.cdoframework.cdolib.servicebus.schema.DataGroup vDataGroup)
        throws java.lang.IndexOutOfBoundsException
    {
        _dataGroupList.add(index, vDataGroup);
    } //-- void addDataGroup(int, com.cdoframework.cdolib.servicebus.schema.DataGroup) 

    /**
     * Method addNoSQLDB
     * 
     * 
     * 
     * @param vNoSQLDB
     */
    public void addNoSQLDB(com.cdoframework.cdolib.servicebus.schema.NoSQLDB vNoSQLDB)
        throws java.lang.IndexOutOfBoundsException
    {
        _noSQLDBList.add(vNoSQLDB);
    } //-- void addNoSQLDB(com.cdoframework.cdolib.servicebus.schema.NoSQLDB) 

    /**
     * Method addNoSQLDB
     * 
     * 
     * 
     * @param index
     * @param vNoSQLDB
     */
    public void addNoSQLDB(int index, com.cdoframework.cdolib.servicebus.schema.NoSQLDB vNoSQLDB)
        throws java.lang.IndexOutOfBoundsException
    {
        _noSQLDBList.add(index, vNoSQLDB);
    } //-- void addNoSQLDB(int, com.cdoframework.cdolib.servicebus.schema.NoSQLDB) 

    /**
     * Method addParameter
     * 
     * 
     * 
     * @param vParameter
     */
    public void addParameter(com.cdoframework.cdolib.servicebus.schema.Parameter vParameter)
        throws java.lang.IndexOutOfBoundsException
    {
        _parameterList.add(vParameter);
    } //-- void addParameter(com.cdoframework.cdolib.servicebus.schema.Parameter) 

    /**
     * Method addParameter
     * 
     * 
     * 
     * @param index
     * @param vParameter
     */
    public void addParameter(int index, com.cdoframework.cdolib.servicebus.schema.Parameter vParameter)
        throws java.lang.IndexOutOfBoundsException
    {
        _parameterList.add(index, vParameter);
    } //-- void addParameter(int, com.cdoframework.cdolib.servicebus.schema.Parameter) 

    /**
     * Method addPluginXMLResource
     * 
     * 
     * 
     * @param vPluginXMLResource
     */
    public void addPluginXMLResource(java.lang.String vPluginXMLResource)
        throws java.lang.IndexOutOfBoundsException
    {
        _pluginXMLResourceList.add(vPluginXMLResource);
    } //-- void addPluginXMLResource(java.lang.String) 

    /**
     * Method addPluginXMLResource
     * 
     * 
     * 
     * @param index
     * @param vPluginXMLResource
     */
    public void addPluginXMLResource(int index, java.lang.String vPluginXMLResource)
        throws java.lang.IndexOutOfBoundsException
    {
        _pluginXMLResourceList.add(index, vPluginXMLResource);
    } //-- void addPluginXMLResource(int, java.lang.String) 

    /**
     * Method clearDataGroup
     * 
     */
    public void clearDataGroup()
    {
        _dataGroupList.clear();
    } //-- void clearDataGroup() 

    /**
     * Method clearNoSQLDB
     * 
     */
    public void clearNoSQLDB()
    {
        _noSQLDBList.clear();
    } //-- void clearNoSQLDB() 

    /**
     * Method clearParameter
     * 
     */
    public void clearParameter()
    {
        _parameterList.clear();
    } //-- void clearParameter() 

    /**
     * Method clearPluginXMLResource
     * 
     */
    public void clearPluginXMLResource()
    {
        _pluginXMLResourceList.clear();
    } //-- void clearPluginXMLResource() 

    /**
     * Method enumerateDataGroup
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateDataGroup()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_dataGroupList.iterator());
    } //-- java.util.Enumeration enumerateDataGroup() 

    /**
     * Method enumerateNoSQLDB
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateNoSQLDB()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_noSQLDBList.iterator());
    } //-- java.util.Enumeration enumerateNoSQLDB() 

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
     * Method enumeratePluginXMLResource
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumeratePluginXMLResource()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_pluginXMLResourceList.iterator());
    } //-- java.util.Enumeration enumeratePluginXMLResource() 

    /**
     * Returns the value of field 'clusterController'.
     * 
     * @return ClusterController
     * @return the value of field 'clusterController'.
     */
    public com.cdoframework.cdolib.servicebus.schema.ClusterController getClusterController()
    {
        return this._clusterController;
    } //-- com.cdoframework.cdolib.servicebus.schema.ClusterController getClusterController() 

    /**
     * Method getDataGroup
     * 
     * 
     * 
     * @param index
     * @return DataGroup
     */
    public com.cdoframework.cdolib.servicebus.schema.DataGroup getDataGroup(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _dataGroupList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.servicebus.schema.DataGroup) _dataGroupList.get(index);
    } //-- com.cdoframework.cdolib.servicebus.schema.DataGroup getDataGroup(int) 

    /**
     * Method getDataGroup
     * 
     * 
     * 
     * @return DataGroup
     */
    public com.cdoframework.cdolib.servicebus.schema.DataGroup[] getDataGroup()
    {
        int size = _dataGroupList.size();
        com.cdoframework.cdolib.servicebus.schema.DataGroup[] mArray = new com.cdoframework.cdolib.servicebus.schema.DataGroup[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.servicebus.schema.DataGroup) _dataGroupList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.servicebus.schema.DataGroup[] getDataGroup() 

    /**
     * Method getDataGroupCount
     * 
     * 
     * 
     * @return int
     */
    public int getDataGroupCount()
    {
        return _dataGroupList.size();
    } //-- int getDataGroupCount() 

    /**
     * Returns the value of field 'dataGroupId'.
     * 
     * @return String
     * @return the value of field 'dataGroupId'.
     */
    public java.lang.String getDataGroupId()
    {
        return this._dataGroupId;
    } //-- java.lang.String getDataGroupId() 

    /**
     * Returns the value of field 'eventProcessor'.
     * 
     * @return EventProcessor
     * @return the value of field 'eventProcessor'.
     */
    public com.cdoframework.cdolib.servicebus.schema.EventProcessor getEventProcessor()
    {
        return this._eventProcessor;
    } //-- com.cdoframework.cdolib.servicebus.schema.EventProcessor getEventProcessor() 

    /**
     * Method getNoSQLDB
     * 
     * 
     * 
     * @param index
     * @return NoSQLDB
     */
    public com.cdoframework.cdolib.servicebus.schema.NoSQLDB getNoSQLDB(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _noSQLDBList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.servicebus.schema.NoSQLDB) _noSQLDBList.get(index);
    } //-- com.cdoframework.cdolib.servicebus.schema.NoSQLDB getNoSQLDB(int) 

    /**
     * Method getNoSQLDB
     * 
     * 
     * 
     * @return NoSQLDB
     */
    public com.cdoframework.cdolib.servicebus.schema.NoSQLDB[] getNoSQLDB()
    {
        int size = _noSQLDBList.size();
        com.cdoframework.cdolib.servicebus.schema.NoSQLDB[] mArray = new com.cdoframework.cdolib.servicebus.schema.NoSQLDB[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.servicebus.schema.NoSQLDB) _noSQLDBList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.servicebus.schema.NoSQLDB[] getNoSQLDB() 

    /**
     * Method getNoSQLDBCount
     * 
     * 
     * 
     * @return int
     */
    public int getNoSQLDBCount()
    {
        return _noSQLDBList.size();
    } //-- int getNoSQLDBCount() 

    /**
     * Returns the value of field 'noSQLDBId'.
     * 
     * @return String
     * @return the value of field 'noSQLDBId'.
     */
    public java.lang.String getNoSQLDBId()
    {
        return this._noSQLDBId;
    } //-- java.lang.String getNoSQLDBId() 

    /**
     * Method getParameter
     * 
     * 
     * 
     * @param index
     * @return Parameter
     */
    public com.cdoframework.cdolib.servicebus.schema.Parameter getParameter(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _parameterList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.servicebus.schema.Parameter) _parameterList.get(index);
    } //-- com.cdoframework.cdolib.servicebus.schema.Parameter getParameter(int) 

    /**
     * Method getParameter
     * 
     * 
     * 
     * @return Parameter
     */
    public com.cdoframework.cdolib.servicebus.schema.Parameter[] getParameter()
    {
        int size = _parameterList.size();
        com.cdoframework.cdolib.servicebus.schema.Parameter[] mArray = new com.cdoframework.cdolib.servicebus.schema.Parameter[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.servicebus.schema.Parameter) _parameterList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.servicebus.schema.Parameter[] getParameter() 

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
     * Method getPluginXMLResource
     * 
     * 
     * 
     * @param index
     * @return String
     */
    public java.lang.String getPluginXMLResource(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _pluginXMLResourceList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (String)_pluginXMLResourceList.get(index);
    } //-- java.lang.String getPluginXMLResource(int) 

    /**
     * Method getPluginXMLResource
     * 
     * 
     * 
     * @return String
     */
    public java.lang.String[] getPluginXMLResource()
    {
        int size = _pluginXMLResourceList.size();
        java.lang.String[] mArray = new java.lang.String[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (String)_pluginXMLResourceList.get(index);
        }
        return mArray;
    } //-- java.lang.String[] getPluginXMLResource() 

    /**
     * Method getPluginXMLResourceCount
     * 
     * 
     * 
     * @return int
     */
    public int getPluginXMLResourceCount()
    {
        return _pluginXMLResourceList.size();
    } //-- int getPluginXMLResourceCount() 

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
     * Method removeDataGroup
     * 
     * 
     * 
     * @param vDataGroup
     * @return boolean
     */
    public boolean removeDataGroup(com.cdoframework.cdolib.servicebus.schema.DataGroup vDataGroup)
    {
        boolean removed = _dataGroupList.remove(vDataGroup);
        return removed;
    } //-- boolean removeDataGroup(com.cdoframework.cdolib.servicebus.schema.DataGroup) 

    /**
     * Method removeNoSQLDB
     * 
     * 
     * 
     * @param vNoSQLDB
     * @return boolean
     */
    public boolean removeNoSQLDB(com.cdoframework.cdolib.servicebus.schema.NoSQLDB vNoSQLDB)
    {
        boolean removed = _noSQLDBList.remove(vNoSQLDB);
        return removed;
    } //-- boolean removeNoSQLDB(com.cdoframework.cdolib.servicebus.schema.NoSQLDB) 

    /**
     * Method removeParameter
     * 
     * 
     * 
     * @param vParameter
     * @return boolean
     */
    public boolean removeParameter(com.cdoframework.cdolib.servicebus.schema.Parameter vParameter)
    {
        boolean removed = _parameterList.remove(vParameter);
        return removed;
    } //-- boolean removeParameter(com.cdoframework.cdolib.servicebus.schema.Parameter) 

    /**
     * Method removePluginXMLResource
     * 
     * 
     * 
     * @param vPluginXMLResource
     * @return boolean
     */
    public boolean removePluginXMLResource(java.lang.String vPluginXMLResource)
    {
        boolean removed = _pluginXMLResourceList.remove(vPluginXMLResource);
        return removed;
    } //-- boolean removePluginXMLResource(java.lang.String) 

    /**
     * Sets the value of field 'clusterController'.
     * 
     * @param clusterController the value of field
     * 'clusterController'.
     */
    public void setClusterController(com.cdoframework.cdolib.servicebus.schema.ClusterController clusterController)
    {
        this._clusterController = clusterController;
    } //-- void setClusterController(com.cdoframework.cdolib.servicebus.schema.ClusterController) 

    /**
     * Method setDataGroup
     * 
     * 
     * 
     * @param index
     * @param vDataGroup
     */
    public void setDataGroup(int index, com.cdoframework.cdolib.servicebus.schema.DataGroup vDataGroup)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _dataGroupList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _dataGroupList.set(index, vDataGroup);
    } //-- void setDataGroup(int, com.cdoframework.cdolib.servicebus.schema.DataGroup) 

    /**
     * Method setDataGroup
     * 
     * 
     * 
     * @param dataGroupArray
     */
    public void setDataGroup(com.cdoframework.cdolib.servicebus.schema.DataGroup[] dataGroupArray)
    {
        //-- copy array
        _dataGroupList.clear();
        for (int i = 0; i < dataGroupArray.length; i++) {
            _dataGroupList.add(dataGroupArray[i]);
        }
    } //-- void setDataGroup(com.cdoframework.cdolib.servicebus.schema.DataGroup) 

    /**
     * Sets the value of field 'dataGroupId'.
     * 
     * @param dataGroupId the value of field 'dataGroupId'.
     */
    public void setDataGroupId(java.lang.String dataGroupId)
    {
        this._dataGroupId = dataGroupId;
    } //-- void setDataGroupId(java.lang.String) 

    /**
     * Sets the value of field 'eventProcessor'.
     * 
     * @param eventProcessor the value of field 'eventProcessor'.
     */
    public void setEventProcessor(com.cdoframework.cdolib.servicebus.schema.EventProcessor eventProcessor)
    {
        this._eventProcessor = eventProcessor;
    } //-- void setEventProcessor(com.cdoframework.cdolib.servicebus.schema.EventProcessor) 

    /**
     * Method setNoSQLDB
     * 
     * 
     * 
     * @param index
     * @param vNoSQLDB
     */
    public void setNoSQLDB(int index, com.cdoframework.cdolib.servicebus.schema.NoSQLDB vNoSQLDB)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _noSQLDBList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _noSQLDBList.set(index, vNoSQLDB);
    } //-- void setNoSQLDB(int, com.cdoframework.cdolib.servicebus.schema.NoSQLDB) 

    /**
     * Method setNoSQLDB
     * 
     * 
     * 
     * @param noSQLDBArray
     */
    public void setNoSQLDB(com.cdoframework.cdolib.servicebus.schema.NoSQLDB[] noSQLDBArray)
    {
        //-- copy array
        _noSQLDBList.clear();
        for (int i = 0; i < noSQLDBArray.length; i++) {
            _noSQLDBList.add(noSQLDBArray[i]);
        }
    } //-- void setNoSQLDB(com.cdoframework.cdolib.servicebus.schema.NoSQLDB) 

    /**
     * Sets the value of field 'noSQLDBId'.
     * 
     * @param noSQLDBId the value of field 'noSQLDBId'.
     */
    public void setNoSQLDBId(java.lang.String noSQLDBId)
    {
        this._noSQLDBId = noSQLDBId;
    } //-- void setNoSQLDBId(java.lang.String) 

    /**
     * Method setParameter
     * 
     * 
     * 
     * @param index
     * @param vParameter
     */
    public void setParameter(int index, com.cdoframework.cdolib.servicebus.schema.Parameter vParameter)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _parameterList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _parameterList.set(index, vParameter);
    } //-- void setParameter(int, com.cdoframework.cdolib.servicebus.schema.Parameter) 

    /**
     * Method setParameter
     * 
     * 
     * 
     * @param parameterArray
     */
    public void setParameter(com.cdoframework.cdolib.servicebus.schema.Parameter[] parameterArray)
    {
        //-- copy array
        _parameterList.clear();
        for (int i = 0; i < parameterArray.length; i++) {
            _parameterList.add(parameterArray[i]);
        }
    } //-- void setParameter(com.cdoframework.cdolib.servicebus.schema.Parameter) 

    /**
     * Method setPluginXMLResource
     * 
     * 
     * 
     * @param index
     * @param vPluginXMLResource
     */
    public void setPluginXMLResource(int index, java.lang.String vPluginXMLResource)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _pluginXMLResourceList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _pluginXMLResourceList.set(index, vPluginXMLResource);
    } //-- void setPluginXMLResource(int, java.lang.String) 

    /**
     * Method setPluginXMLResource
     * 
     * 
     * 
     * @param pluginXMLResourceArray
     */
    public void setPluginXMLResource(java.lang.String[] pluginXMLResourceArray)
    {
        //-- copy array
        _pluginXMLResourceList.clear();
        for (int i = 0; i < pluginXMLResourceArray.length; i++) {
            _pluginXMLResourceList.add(pluginXMLResourceArray[i]);
        }
    } //-- void setPluginXMLResource(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return ServiceBus
     */
    public static com.cdoframework.cdolib.servicebus.schema.ServiceBus unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.servicebus.schema.ServiceBus) Unmarshaller.unmarshal(com.cdoframework.cdolib.servicebus.schema.ServiceBus.class, reader);
    } //-- com.cdoframework.cdolib.servicebus.schema.ServiceBus unmarshal(java.io.Reader) 

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
    
    static Logger logger = Logger.getLogger(ServiceBus.class);
    /**
     * ****************************************************************************************
     * 从一个XML字件串解析成BusinessManager对象
     * @param strXML
     * @return
     */
    public static ServiceBus   fromXML(java.lang.String strXML) throws Exception
    {
    	StringReader reader=null;
    	try
    	{
    		reader=new StringReader(strXML);
    		ServiceBus serviceBus = (ServiceBus)ServiceBus.unmarshal(reader);
    		return serviceBus;
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
    			{//TODO 日志
    				logger.error("fromXML:"+e.getMessage(),e);
    			}
    		}
    	}
    }
}
