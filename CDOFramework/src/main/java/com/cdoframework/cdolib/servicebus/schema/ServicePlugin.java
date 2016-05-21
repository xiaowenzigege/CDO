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
 * Class ServicePlugin.
 * 
 * @version $Revision$ $Date$
 */
public class ServicePlugin implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

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
     * Field _serviceConfigList
     */
    private java.util.ArrayList _serviceConfigList;

    /**
     * Field _dataServiceList
     */
    private java.util.ArrayList _dataServiceList;

    /**
     * Field _transServiceList
     */
    private java.util.ArrayList _transServiceList;

    /**
     * Field _activeServiceList
     */
    private java.util.ArrayList _activeServiceList;


      //----------------/
     //- Constructors -/
    //----------------/

    public ServicePlugin() 
     {
        super();
        _parameterList = new ArrayList();
        _dataGroupList = new ArrayList();
        _noSQLDBList = new ArrayList();
        _serviceConfigList = new ArrayList();
        _dataServiceList = new ArrayList();
        _transServiceList = new ArrayList();
        _activeServiceList = new ArrayList();
    } //-- com.cdoframework.cdolib.servicebus.schema.ServicePlugin()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addActiveService
     * 
     * 
     * 
     * @param vActiveService
     */
    public void addActiveService(com.cdoframework.cdolib.servicebus.schema.ActiveService vActiveService)
        throws java.lang.IndexOutOfBoundsException
    {
        _activeServiceList.add(vActiveService);
    } //-- void addActiveService(com.cdoframework.cdolib.servicebus.schema.ActiveService) 

    /**
     * Method addActiveService
     * 
     * 
     * 
     * @param index
     * @param vActiveService
     */
    public void addActiveService(int index, com.cdoframework.cdolib.servicebus.schema.ActiveService vActiveService)
        throws java.lang.IndexOutOfBoundsException
    {
        _activeServiceList.add(index, vActiveService);
    } //-- void addActiveService(int, com.cdoframework.cdolib.servicebus.schema.ActiveService) 

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
     * Method addDataService
     * 
     * 
     * 
     * @param vDataService
     */
    public void addDataService(com.cdoframework.cdolib.servicebus.schema.DataService vDataService)
        throws java.lang.IndexOutOfBoundsException
    {
        _dataServiceList.add(vDataService);
    } //-- void addDataService(com.cdoframework.cdolib.servicebus.schema.DataService) 

    /**
     * Method addDataService
     * 
     * 
     * 
     * @param index
     * @param vDataService
     */
    public void addDataService(int index, com.cdoframework.cdolib.servicebus.schema.DataService vDataService)
        throws java.lang.IndexOutOfBoundsException
    {
        _dataServiceList.add(index, vDataService);
    } //-- void addDataService(int, com.cdoframework.cdolib.servicebus.schema.DataService) 

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
     * Method addServiceConfig
     * 
     * 
     * 
     * @param vServiceConfig
     */
    public void addServiceConfig(com.cdoframework.cdolib.servicebus.schema.ServiceConfig vServiceConfig)
        throws java.lang.IndexOutOfBoundsException
    {
        _serviceConfigList.add(vServiceConfig);
    } //-- void addServiceConfig(com.cdoframework.cdolib.servicebus.schema.ServiceConfig) 

    /**
     * Method addServiceConfig
     * 
     * 
     * 
     * @param index
     * @param vServiceConfig
     */
    public void addServiceConfig(int index, com.cdoframework.cdolib.servicebus.schema.ServiceConfig vServiceConfig)
        throws java.lang.IndexOutOfBoundsException
    {
        _serviceConfigList.add(index, vServiceConfig);
    } //-- void addServiceConfig(int, com.cdoframework.cdolib.servicebus.schema.ServiceConfig) 

    /**
     * Method addTransService
     * 
     * 
     * 
     * @param vTransService
     */
    public void addTransService(com.cdoframework.cdolib.servicebus.schema.TransService vTransService)
        throws java.lang.IndexOutOfBoundsException
    {
        _transServiceList.add(vTransService);
    } //-- void addTransService(com.cdoframework.cdolib.servicebus.schema.TransService) 

    /**
     * Method addTransService
     * 
     * 
     * 
     * @param index
     * @param vTransService
     */
    public void addTransService(int index, com.cdoframework.cdolib.servicebus.schema.TransService vTransService)
        throws java.lang.IndexOutOfBoundsException
    {
        _transServiceList.add(index, vTransService);
    } //-- void addTransService(int, com.cdoframework.cdolib.servicebus.schema.TransService) 

    /**
     * Method clearActiveService
     * 
     */
    public void clearActiveService()
    {
        _activeServiceList.clear();
    } //-- void clearActiveService() 

    /**
     * Method clearDataGroup
     * 
     */
    public void clearDataGroup()
    {
        _dataGroupList.clear();
    } //-- void clearDataGroup() 

    /**
     * Method clearDataService
     * 
     */
    public void clearDataService()
    {
        _dataServiceList.clear();
    } //-- void clearDataService() 

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
     * Method clearServiceConfig
     * 
     */
    public void clearServiceConfig()
    {
        _serviceConfigList.clear();
    } //-- void clearServiceConfig() 

    /**
     * Method clearTransService
     * 
     */
    public void clearTransService()
    {
        _transServiceList.clear();
    } //-- void clearTransService() 

    /**
     * Method enumerateActiveService
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateActiveService()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_activeServiceList.iterator());
    } //-- java.util.Enumeration enumerateActiveService() 

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
     * Method enumerateDataService
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateDataService()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_dataServiceList.iterator());
    } //-- java.util.Enumeration enumerateDataService() 

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
     * Method enumerateServiceConfig
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateServiceConfig()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_serviceConfigList.iterator());
    } //-- java.util.Enumeration enumerateServiceConfig() 

    /**
     * Method enumerateTransService
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateTransService()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_transServiceList.iterator());
    } //-- java.util.Enumeration enumerateTransService() 

    /**
     * Method getActiveService
     * 
     * 
     * 
     * @param index
     * @return ActiveService
     */
    public com.cdoframework.cdolib.servicebus.schema.ActiveService getActiveService(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _activeServiceList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.servicebus.schema.ActiveService) _activeServiceList.get(index);
    } //-- com.cdoframework.cdolib.servicebus.schema.ActiveService getActiveService(int) 

    /**
     * Method getActiveService
     * 
     * 
     * 
     * @return ActiveService
     */
    public com.cdoframework.cdolib.servicebus.schema.ActiveService[] getActiveService()
    {
        int size = _activeServiceList.size();
        com.cdoframework.cdolib.servicebus.schema.ActiveService[] mArray = new com.cdoframework.cdolib.servicebus.schema.ActiveService[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.servicebus.schema.ActiveService) _activeServiceList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.servicebus.schema.ActiveService[] getActiveService() 

    /**
     * Method getActiveServiceCount
     * 
     * 
     * 
     * @return int
     */
    public int getActiveServiceCount()
    {
        return _activeServiceList.size();
    } //-- int getActiveServiceCount() 

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
     * Method getDataService
     * 
     * 
     * 
     * @param index
     * @return DataService
     */
    public com.cdoframework.cdolib.servicebus.schema.DataService getDataService(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _dataServiceList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.servicebus.schema.DataService) _dataServiceList.get(index);
    } //-- com.cdoframework.cdolib.servicebus.schema.DataService getDataService(int) 

    /**
     * Method getDataService
     * 
     * 
     * 
     * @return DataService
     */
    public com.cdoframework.cdolib.servicebus.schema.DataService[] getDataService()
    {
        int size = _dataServiceList.size();
        com.cdoframework.cdolib.servicebus.schema.DataService[] mArray = new com.cdoframework.cdolib.servicebus.schema.DataService[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.servicebus.schema.DataService) _dataServiceList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.servicebus.schema.DataService[] getDataService() 

    /**
     * Method getDataServiceCount
     * 
     * 
     * 
     * @return int
     */
    public int getDataServiceCount()
    {
        return _dataServiceList.size();
    } //-- int getDataServiceCount() 

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
     * Method getServiceConfig
     * 
     * 
     * 
     * @param index
     * @return ServiceConfig
     */
    public com.cdoframework.cdolib.servicebus.schema.ServiceConfig getServiceConfig(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _serviceConfigList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.servicebus.schema.ServiceConfig) _serviceConfigList.get(index);
    } //-- com.cdoframework.cdolib.servicebus.schema.ServiceConfig getServiceConfig(int) 

    /**
     * Method getServiceConfig
     * 
     * 
     * 
     * @return ServiceConfig
     */
    public com.cdoframework.cdolib.servicebus.schema.ServiceConfig[] getServiceConfig()
    {
        int size = _serviceConfigList.size();
        com.cdoframework.cdolib.servicebus.schema.ServiceConfig[] mArray = new com.cdoframework.cdolib.servicebus.schema.ServiceConfig[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.servicebus.schema.ServiceConfig) _serviceConfigList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.servicebus.schema.ServiceConfig[] getServiceConfig() 

    /**
     * Method getServiceConfigCount
     * 
     * 
     * 
     * @return int
     */
    public int getServiceConfigCount()
    {
        return _serviceConfigList.size();
    } //-- int getServiceConfigCount() 

    /**
     * Method getTransService
     * 
     * 
     * 
     * @param index
     * @return TransService
     */
    public com.cdoframework.cdolib.servicebus.schema.TransService getTransService(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _transServiceList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.servicebus.schema.TransService) _transServiceList.get(index);
    } //-- com.cdoframework.cdolib.servicebus.schema.TransService getTransService(int) 

    /**
     * Method getTransService
     * 
     * 
     * 
     * @return TransService
     */
    public com.cdoframework.cdolib.servicebus.schema.TransService[] getTransService()
    {
        int size = _transServiceList.size();
        com.cdoframework.cdolib.servicebus.schema.TransService[] mArray = new com.cdoframework.cdolib.servicebus.schema.TransService[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.servicebus.schema.TransService) _transServiceList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.servicebus.schema.TransService[] getTransService() 

    /**
     * Method getTransServiceCount
     * 
     * 
     * 
     * @return int
     */
    public int getTransServiceCount()
    {
        return _transServiceList.size();
    } //-- int getTransServiceCount() 

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
     * Method removeActiveService
     * 
     * 
     * 
     * @param vActiveService
     * @return boolean
     */
    public boolean removeActiveService(com.cdoframework.cdolib.servicebus.schema.ActiveService vActiveService)
    {
        boolean removed = _activeServiceList.remove(vActiveService);
        return removed;
    } //-- boolean removeActiveService(com.cdoframework.cdolib.servicebus.schema.ActiveService) 

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
     * Method removeDataService
     * 
     * 
     * 
     * @param vDataService
     * @return boolean
     */
    public boolean removeDataService(com.cdoframework.cdolib.servicebus.schema.DataService vDataService)
    {
        boolean removed = _dataServiceList.remove(vDataService);
        return removed;
    } //-- boolean removeDataService(com.cdoframework.cdolib.servicebus.schema.DataService) 

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
     * Method removeServiceConfig
     * 
     * 
     * 
     * @param vServiceConfig
     * @return boolean
     */
    public boolean removeServiceConfig(com.cdoframework.cdolib.servicebus.schema.ServiceConfig vServiceConfig)
    {
        boolean removed = _serviceConfigList.remove(vServiceConfig);
        return removed;
    } //-- boolean removeServiceConfig(com.cdoframework.cdolib.servicebus.schema.ServiceConfig) 

    /**
     * Method removeTransService
     * 
     * 
     * 
     * @param vTransService
     * @return boolean
     */
    public boolean removeTransService(com.cdoframework.cdolib.servicebus.schema.TransService vTransService)
    {
        boolean removed = _transServiceList.remove(vTransService);
        return removed;
    } //-- boolean removeTransService(com.cdoframework.cdolib.servicebus.schema.TransService) 

    /**
     * Method setActiveService
     * 
     * 
     * 
     * @param index
     * @param vActiveService
     */
    public void setActiveService(int index, com.cdoframework.cdolib.servicebus.schema.ActiveService vActiveService)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _activeServiceList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _activeServiceList.set(index, vActiveService);
    } //-- void setActiveService(int, com.cdoframework.cdolib.servicebus.schema.ActiveService) 

    /**
     * Method setActiveService
     * 
     * 
     * 
     * @param activeServiceArray
     */
    public void setActiveService(com.cdoframework.cdolib.servicebus.schema.ActiveService[] activeServiceArray)
    {
        //-- copy array
        _activeServiceList.clear();
        for (int i = 0; i < activeServiceArray.length; i++) {
            _activeServiceList.add(activeServiceArray[i]);
        }
    } //-- void setActiveService(com.cdoframework.cdolib.servicebus.schema.ActiveService) 

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
     * Method setDataService
     * 
     * 
     * 
     * @param index
     * @param vDataService
     */
    public void setDataService(int index, com.cdoframework.cdolib.servicebus.schema.DataService vDataService)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _dataServiceList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _dataServiceList.set(index, vDataService);
    } //-- void setDataService(int, com.cdoframework.cdolib.servicebus.schema.DataService) 

    /**
     * Method setDataService
     * 
     * 
     * 
     * @param dataServiceArray
     */
    public void setDataService(com.cdoframework.cdolib.servicebus.schema.DataService[] dataServiceArray)
    {
        //-- copy array
        _dataServiceList.clear();
        for (int i = 0; i < dataServiceArray.length; i++) {
            _dataServiceList.add(dataServiceArray[i]);
        }
    } //-- void setDataService(com.cdoframework.cdolib.servicebus.schema.DataService) 

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
     * Method setServiceConfig
     * 
     * 
     * 
     * @param index
     * @param vServiceConfig
     */
    public void setServiceConfig(int index, com.cdoframework.cdolib.servicebus.schema.ServiceConfig vServiceConfig)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _serviceConfigList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _serviceConfigList.set(index, vServiceConfig);
    } //-- void setServiceConfig(int, com.cdoframework.cdolib.servicebus.schema.ServiceConfig) 

    /**
     * Method setServiceConfig
     * 
     * 
     * 
     * @param serviceConfigArray
     */
    public void setServiceConfig(com.cdoframework.cdolib.servicebus.schema.ServiceConfig[] serviceConfigArray)
    {
        //-- copy array
        _serviceConfigList.clear();
        for (int i = 0; i < serviceConfigArray.length; i++) {
            _serviceConfigList.add(serviceConfigArray[i]);
        }
    } //-- void setServiceConfig(com.cdoframework.cdolib.servicebus.schema.ServiceConfig) 

    /**
     * Method setTransService
     * 
     * 
     * 
     * @param index
     * @param vTransService
     */
    public void setTransService(int index, com.cdoframework.cdolib.servicebus.schema.TransService vTransService)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _transServiceList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _transServiceList.set(index, vTransService);
    } //-- void setTransService(int, com.cdoframework.cdolib.servicebus.schema.TransService) 

    /**
     * Method setTransService
     * 
     * 
     * 
     * @param transServiceArray
     */
    public void setTransService(com.cdoframework.cdolib.servicebus.schema.TransService[] transServiceArray)
    {
        //-- copy array
        _transServiceList.clear();
        for (int i = 0; i < transServiceArray.length; i++) {
            _transServiceList.add(transServiceArray[i]);
        }
    } //-- void setTransService(com.cdoframework.cdolib.servicebus.schema.TransService) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return ServicePlugin
     */
    public static com.cdoframework.cdolib.servicebus.schema.ServicePlugin unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.servicebus.schema.ServicePlugin) Unmarshaller.unmarshal(com.cdoframework.cdolib.servicebus.schema.ServicePlugin.class, reader);
    } //-- com.cdoframework.cdolib.servicebus.schema.ServicePlugin unmarshal(java.io.Reader) 

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
    static Logger logger = Logger.getLogger(ServicePlugin.class);
	
    /**
     * 从一个XML字件串解析成BusinessPlugin对象
     * @param strXML
     * @return
     */
    public static ServicePlugin   fromXML(String strXML) throws Exception
    {
    	StringReader reader=null;
    	try
    	{
    		reader=new StringReader(strXML);
    		ServicePlugin servicePlugin = (ServicePlugin)ServicePlugin.unmarshal(reader);


    		return servicePlugin;
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
