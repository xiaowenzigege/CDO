/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.3</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.servicebus.xsd;

import java.io.StringReader;

import org.apache.log4j.Logger;

/**
 * 
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class ServicePlugin implements java.io.Serializable {

    private java.util.List<com.cdoframework.cdolib.servicebus.xsd.Parameter> parameterList;

    private java.util.List<com.cdoframework.cdolib.servicebus.xsd.DataGroup> dataGroupList;

    private java.util.List<com.cdoframework.cdolib.servicebus.xsd.NoSQLDB> noSQLDBList;

    private java.util.List<com.cdoframework.cdolib.servicebus.xsd.ServiceConfig> serviceConfigList;

    private java.util.List<com.cdoframework.cdolib.servicebus.xsd.DataService> dataServiceList;

    private java.util.List<com.cdoframework.cdolib.servicebus.xsd.TransService> transServiceList;

    private java.util.List<com.cdoframework.cdolib.servicebus.xsd.ActiveService> activeServiceList;

    public ServicePlugin() {
        super();
        this.parameterList = new java.util.ArrayList<com.cdoframework.cdolib.servicebus.xsd.Parameter>();
        this.dataGroupList = new java.util.ArrayList<com.cdoframework.cdolib.servicebus.xsd.DataGroup>();
        this.noSQLDBList = new java.util.ArrayList<com.cdoframework.cdolib.servicebus.xsd.NoSQLDB>();
        this.serviceConfigList = new java.util.ArrayList<com.cdoframework.cdolib.servicebus.xsd.ServiceConfig>();
        this.dataServiceList = new java.util.ArrayList<com.cdoframework.cdolib.servicebus.xsd.DataService>();
        this.transServiceList = new java.util.ArrayList<com.cdoframework.cdolib.servicebus.xsd.TransService>();
        this.activeServiceList = new java.util.ArrayList<com.cdoframework.cdolib.servicebus.xsd.ActiveService>();
    }

    /**
     * 
     * 
     * @param vActiveService
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addActiveService(final com.cdoframework.cdolib.servicebus.xsd.ActiveService vActiveService) throws java.lang.IndexOutOfBoundsException {
        this.activeServiceList.add(vActiveService);
    }

    /**
     * 
     * 
     * @param index
     * @param vActiveService
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addActiveService(final int index,final com.cdoframework.cdolib.servicebus.xsd.ActiveService vActiveService) throws java.lang.IndexOutOfBoundsException {
        this.activeServiceList.add(index, vActiveService);
    }

    /**
     * 
     * 
     * @param vDataGroup
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addDataGroup(final com.cdoframework.cdolib.servicebus.xsd.DataGroup vDataGroup) throws java.lang.IndexOutOfBoundsException {
        this.dataGroupList.add(vDataGroup);
    }

    /**
     * 
     * 
     * @param index
     * @param vDataGroup
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addDataGroup(final int index,final com.cdoframework.cdolib.servicebus.xsd.DataGroup vDataGroup) throws java.lang.IndexOutOfBoundsException {
        this.dataGroupList.add(index, vDataGroup);
    }

    /**
     * 
     * 
     * @param vDataService
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addDataService(final com.cdoframework.cdolib.servicebus.xsd.DataService vDataService) throws java.lang.IndexOutOfBoundsException {
        this.dataServiceList.add(vDataService);
    }

    /**
     * 
     * 
     * @param index
     * @param vDataService
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addDataService(final int index,final com.cdoframework.cdolib.servicebus.xsd.DataService vDataService) throws java.lang.IndexOutOfBoundsException {
        this.dataServiceList.add(index, vDataService);
    }

    /**
     * 
     * 
     * @param vNoSQLDB
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addNoSQLDB(final com.cdoframework.cdolib.servicebus.xsd.NoSQLDB vNoSQLDB) throws java.lang.IndexOutOfBoundsException {
        this.noSQLDBList.add(vNoSQLDB);
    }

    /**
     * 
     * 
     * @param index
     * @param vNoSQLDB
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addNoSQLDB(final int index,final com.cdoframework.cdolib.servicebus.xsd.NoSQLDB vNoSQLDB) throws java.lang.IndexOutOfBoundsException {
        this.noSQLDBList.add(index, vNoSQLDB);
    }

    /**
     * 
     * 
     * @param vParameter
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addParameter(final com.cdoframework.cdolib.servicebus.xsd.Parameter vParameter) throws java.lang.IndexOutOfBoundsException {
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
    public void addParameter(final int index,final com.cdoframework.cdolib.servicebus.xsd.Parameter vParameter) throws java.lang.IndexOutOfBoundsException {
        this.parameterList.add(index, vParameter);
    }

    /**
     * 
     * 
     * @param vServiceConfig
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addServiceConfig(final com.cdoframework.cdolib.servicebus.xsd.ServiceConfig vServiceConfig) throws java.lang.IndexOutOfBoundsException {
        this.serviceConfigList.add(vServiceConfig);
    }

    /**
     * 
     * 
     * @param index
     * @param vServiceConfig
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addServiceConfig(final int index,final com.cdoframework.cdolib.servicebus.xsd.ServiceConfig vServiceConfig) throws java.lang.IndexOutOfBoundsException {
        this.serviceConfigList.add(index, vServiceConfig);
    }

    /**
     * 
     * 
     * @param vTransService
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addTransService(final com.cdoframework.cdolib.servicebus.xsd.TransService vTransService) throws java.lang.IndexOutOfBoundsException {
        this.transServiceList.add(vTransService);
    }

    /**
     * 
     * 
     * @param index
     * @param vTransService
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addTransService(final int index,final com.cdoframework.cdolib.servicebus.xsd.TransService vTransService) throws java.lang.IndexOutOfBoundsException {
        this.transServiceList.add(index, vTransService);
    }

    /**
     * Method enumerateActiveService.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.servicebus.xsd.ActiveService> enumerateActiveService() {
        return java.util.Collections.enumeration(this.activeServiceList);
    }

    /**
     * Method enumerateDataGroup.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.servicebus.xsd.DataGroup> enumerateDataGroup() {
        return java.util.Collections.enumeration(this.dataGroupList);
    }

    /**
     * Method enumerateDataService.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.servicebus.xsd.DataService> enumerateDataService() {
        return java.util.Collections.enumeration(this.dataServiceList);
    }

    /**
     * Method enumerateNoSQLDB.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.servicebus.xsd.NoSQLDB> enumerateNoSQLDB() {
        return java.util.Collections.enumeration(this.noSQLDBList);
    }

    /**
     * Method enumerateParameter.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.servicebus.xsd.Parameter> enumerateParameter() {
        return java.util.Collections.enumeration(this.parameterList);
    }

    /**
     * Method enumerateServiceConfig.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.servicebus.xsd.ServiceConfig> enumerateServiceConfig() {
        return java.util.Collections.enumeration(this.serviceConfigList);
    }

    /**
     * Method enumerateTransService.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.servicebus.xsd.TransService> enumerateTransService() {
        return java.util.Collections.enumeration(this.transServiceList);
    }

    /**
     * Method getActiveService.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.servicebus.xsd.ActiveService at the
     * given index
     */
    public com.cdoframework.cdolib.servicebus.xsd.ActiveService getActiveService(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.activeServiceList.size()) {
            throw new IndexOutOfBoundsException("getActiveService: Index value '" + index + "' not in range [0.." + (this.activeServiceList.size() - 1) + "]");
        }

        return activeServiceList.get(index);
    }

    /**
     * Method getActiveService.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.servicebus.xsd.ActiveService[] getActiveService() {
        com.cdoframework.cdolib.servicebus.xsd.ActiveService[] array = new com.cdoframework.cdolib.servicebus.xsd.ActiveService[0];
        return this.activeServiceList.toArray(array);
    }

    /**
     * Method getActiveServiceCount.
     * 
     * @return the size of this collection
     */
    public int getActiveServiceCount() {
        return this.activeServiceList.size();
    }

    /**
     * Method getDataGroup.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.servicebus.xsd.DataGroup at the
     * given index
     */
    public com.cdoframework.cdolib.servicebus.xsd.DataGroup getDataGroup(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.dataGroupList.size()) {
            throw new IndexOutOfBoundsException("getDataGroup: Index value '" + index + "' not in range [0.." + (this.dataGroupList.size() - 1) + "]");
        }

        return dataGroupList.get(index);
    }

    /**
     * Method getDataGroup.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.servicebus.xsd.DataGroup[] getDataGroup() {
        com.cdoframework.cdolib.servicebus.xsd.DataGroup[] array = new com.cdoframework.cdolib.servicebus.xsd.DataGroup[0];
        return this.dataGroupList.toArray(array);
    }

    /**
     * Method getDataGroupCount.
     * 
     * @return the size of this collection
     */
    public int getDataGroupCount() {
        return this.dataGroupList.size();
    }

    /**
     * Method getDataService.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.servicebus.xsd.DataService at the
     * given index
     */
    public com.cdoframework.cdolib.servicebus.xsd.DataService getDataService(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.dataServiceList.size()) {
            throw new IndexOutOfBoundsException("getDataService: Index value '" + index + "' not in range [0.." + (this.dataServiceList.size() - 1) + "]");
        }

        return dataServiceList.get(index);
    }

    /**
     * Method getDataService.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.servicebus.xsd.DataService[] getDataService() {
        com.cdoframework.cdolib.servicebus.xsd.DataService[] array = new com.cdoframework.cdolib.servicebus.xsd.DataService[0];
        return this.dataServiceList.toArray(array);
    }

    /**
     * Method getDataServiceCount.
     * 
     * @return the size of this collection
     */
    public int getDataServiceCount() {
        return this.dataServiceList.size();
    }

    /**
     * Method getNoSQLDB.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.servicebus.xsd.NoSQLDB at the given
     * index
     */
    public com.cdoframework.cdolib.servicebus.xsd.NoSQLDB getNoSQLDB(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.noSQLDBList.size()) {
            throw new IndexOutOfBoundsException("getNoSQLDB: Index value '" + index + "' not in range [0.." + (this.noSQLDBList.size() - 1) + "]");
        }

        return noSQLDBList.get(index);
    }

    /**
     * Method getNoSQLDB.Returns the contents of the collection in
     * an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.servicebus.xsd.NoSQLDB[] getNoSQLDB() {
        com.cdoframework.cdolib.servicebus.xsd.NoSQLDB[] array = new com.cdoframework.cdolib.servicebus.xsd.NoSQLDB[0];
        return this.noSQLDBList.toArray(array);
    }

    /**
     * Method getNoSQLDBCount.
     * 
     * @return the size of this collection
     */
    public int getNoSQLDBCount() {
        return this.noSQLDBList.size();
    }

    /**
     * Method getParameter.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.servicebus.xsd.Parameter at the
     * given index
     */
    public com.cdoframework.cdolib.servicebus.xsd.Parameter getParameter(final int index) throws java.lang.IndexOutOfBoundsException {
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
    public com.cdoframework.cdolib.servicebus.xsd.Parameter[] getParameter() {
        com.cdoframework.cdolib.servicebus.xsd.Parameter[] array = new com.cdoframework.cdolib.servicebus.xsd.Parameter[0];
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
     * Method getServiceConfig.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.servicebus.xsd.ServiceConfig at the
     * given index
     */
    public com.cdoframework.cdolib.servicebus.xsd.ServiceConfig getServiceConfig(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.serviceConfigList.size()) {
            throw new IndexOutOfBoundsException("getServiceConfig: Index value '" + index + "' not in range [0.." + (this.serviceConfigList.size() - 1) + "]");
        }

        return serviceConfigList.get(index);
    }

    /**
     * Method getServiceConfig.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.servicebus.xsd.ServiceConfig[] getServiceConfig() {
        com.cdoframework.cdolib.servicebus.xsd.ServiceConfig[] array = new com.cdoframework.cdolib.servicebus.xsd.ServiceConfig[0];
        return this.serviceConfigList.toArray(array);
    }

    /**
     * Method getServiceConfigCount.
     * 
     * @return the size of this collection
     */
    public int getServiceConfigCount() {
        return this.serviceConfigList.size();
    }

    /**
     * Method getTransService.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.servicebus.xsd.TransService at the
     * given index
     */
    public com.cdoframework.cdolib.servicebus.xsd.TransService getTransService(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.transServiceList.size()) {
            throw new IndexOutOfBoundsException("getTransService: Index value '" + index + "' not in range [0.." + (this.transServiceList.size() - 1) + "]");
        }

        return transServiceList.get(index);
    }

    /**
     * Method getTransService.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.servicebus.xsd.TransService[] getTransService() {
        com.cdoframework.cdolib.servicebus.xsd.TransService[] array = new com.cdoframework.cdolib.servicebus.xsd.TransService[0];
        return this.transServiceList.toArray(array);
    }

    /**
     * Method getTransServiceCount.
     * 
     * @return the size of this collection
     */
    public int getTransServiceCount() {
        return this.transServiceList.size();
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
     * Method iterateActiveService.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.servicebus.xsd.ActiveService> iterateActiveService() {
        return this.activeServiceList.iterator();
    }

    /**
     * Method iterateDataGroup.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.servicebus.xsd.DataGroup> iterateDataGroup() {
        return this.dataGroupList.iterator();
    }

    /**
     * Method iterateDataService.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.servicebus.xsd.DataService> iterateDataService() {
        return this.dataServiceList.iterator();
    }

    /**
     * Method iterateNoSQLDB.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.servicebus.xsd.NoSQLDB> iterateNoSQLDB() {
        return this.noSQLDBList.iterator();
    }

    /**
     * Method iterateParameter.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.servicebus.xsd.Parameter> iterateParameter() {
        return this.parameterList.iterator();
    }

    /**
     * Method iterateServiceConfig.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.servicebus.xsd.ServiceConfig> iterateServiceConfig() {
        return this.serviceConfigList.iterator();
    }

    /**
     * Method iterateTransService.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.servicebus.xsd.TransService> iterateTransService() {
        return this.transServiceList.iterator();
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
     * Method removeActiveService.
     * 
     * @param vActiveService
     * @return true if the object was removed from the collection.
     */
    public boolean removeActiveService(final com.cdoframework.cdolib.servicebus.xsd.ActiveService vActiveService) {
        boolean removed = activeServiceList.remove(vActiveService);
        return removed;
    }

    /**
     * Method removeActiveServiceAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.servicebus.xsd.ActiveService removeActiveServiceAt(final int index) {
        java.lang.Object obj = this.activeServiceList.remove(index);
        return (com.cdoframework.cdolib.servicebus.xsd.ActiveService) obj;
    }

    /**
     */
    public void removeAllActiveService() {
        this.activeServiceList.clear();
    }

    /**
     */
    public void removeAllDataGroup() {
        this.dataGroupList.clear();
    }

    /**
     */
    public void removeAllDataService() {
        this.dataServiceList.clear();
    }

    /**
     */
    public void removeAllNoSQLDB() {
        this.noSQLDBList.clear();
    }

    /**
     */
    public void removeAllParameter() {
        this.parameterList.clear();
    }

    /**
     */
    public void removeAllServiceConfig() {
        this.serviceConfigList.clear();
    }

    /**
     */
    public void removeAllTransService() {
        this.transServiceList.clear();
    }

    /**
     * Method removeDataGroup.
     * 
     * @param vDataGroup
     * @return true if the object was removed from the collection.
     */
    public boolean removeDataGroup(final com.cdoframework.cdolib.servicebus.xsd.DataGroup vDataGroup) {
        boolean removed = dataGroupList.remove(vDataGroup);
        return removed;
    }

    /**
     * Method removeDataGroupAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.servicebus.xsd.DataGroup removeDataGroupAt(final int index) {
        java.lang.Object obj = this.dataGroupList.remove(index);
        return (com.cdoframework.cdolib.servicebus.xsd.DataGroup) obj;
    }

    /**
     * Method removeDataService.
     * 
     * @param vDataService
     * @return true if the object was removed from the collection.
     */
    public boolean removeDataService(final com.cdoframework.cdolib.servicebus.xsd.DataService vDataService) {
        boolean removed = dataServiceList.remove(vDataService);
        return removed;
    }

    /**
     * Method removeDataServiceAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.servicebus.xsd.DataService removeDataServiceAt(final int index) {
        java.lang.Object obj = this.dataServiceList.remove(index);
        return (com.cdoframework.cdolib.servicebus.xsd.DataService) obj;
    }

    /**
     * Method removeNoSQLDB.
     * 
     * @param vNoSQLDB
     * @return true if the object was removed from the collection.
     */
    public boolean removeNoSQLDB(final com.cdoframework.cdolib.servicebus.xsd.NoSQLDB vNoSQLDB) {
        boolean removed = noSQLDBList.remove(vNoSQLDB);
        return removed;
    }

    /**
     * Method removeNoSQLDBAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.servicebus.xsd.NoSQLDB removeNoSQLDBAt(final int index) {
        java.lang.Object obj = this.noSQLDBList.remove(index);
        return (com.cdoframework.cdolib.servicebus.xsd.NoSQLDB) obj;
    }

    /**
     * Method removeParameter.
     * 
     * @param vParameter
     * @return true if the object was removed from the collection.
     */
    public boolean removeParameter(final com.cdoframework.cdolib.servicebus.xsd.Parameter vParameter) {
        boolean removed = parameterList.remove(vParameter);
        return removed;
    }

    /**
     * Method removeParameterAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.servicebus.xsd.Parameter removeParameterAt(final int index) {
        java.lang.Object obj = this.parameterList.remove(index);
        return (com.cdoframework.cdolib.servicebus.xsd.Parameter) obj;
    }

    /**
     * Method removeServiceConfig.
     * 
     * @param vServiceConfig
     * @return true if the object was removed from the collection.
     */
    public boolean removeServiceConfig(final com.cdoframework.cdolib.servicebus.xsd.ServiceConfig vServiceConfig) {
        boolean removed = serviceConfigList.remove(vServiceConfig);
        return removed;
    }

    /**
     * Method removeServiceConfigAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.servicebus.xsd.ServiceConfig removeServiceConfigAt(final int index) {
        java.lang.Object obj = this.serviceConfigList.remove(index);
        return (com.cdoframework.cdolib.servicebus.xsd.ServiceConfig) obj;
    }

    /**
     * Method removeTransService.
     * 
     * @param vTransService
     * @return true if the object was removed from the collection.
     */
    public boolean removeTransService(final com.cdoframework.cdolib.servicebus.xsd.TransService vTransService) {
        boolean removed = transServiceList.remove(vTransService);
        return removed;
    }

    /**
     * Method removeTransServiceAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.servicebus.xsd.TransService removeTransServiceAt(final int index) {
        java.lang.Object obj = this.transServiceList.remove(index);
        return (com.cdoframework.cdolib.servicebus.xsd.TransService) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vActiveService
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setActiveService(final int index,final com.cdoframework.cdolib.servicebus.xsd.ActiveService vActiveService) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.activeServiceList.size()) {
            throw new IndexOutOfBoundsException("setActiveService: Index value '" + index + "' not in range [0.." + (this.activeServiceList.size() - 1) + "]");
        }

        this.activeServiceList.set(index, vActiveService);
    }

    /**
     * 
     * 
     * @param vActiveServiceArray
     */
    public void setActiveService(final com.cdoframework.cdolib.servicebus.xsd.ActiveService[] vActiveServiceArray) {
        //-- copy array
        activeServiceList.clear();

        for (int i = 0; i < vActiveServiceArray.length; i++) {
                this.activeServiceList.add(vActiveServiceArray[i]);
        }
    }

    /**
     * 
     * 
     * @param index
     * @param vDataGroup
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setDataGroup(final int index,final com.cdoframework.cdolib.servicebus.xsd.DataGroup vDataGroup) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.dataGroupList.size()) {
            throw new IndexOutOfBoundsException("setDataGroup: Index value '" + index + "' not in range [0.." + (this.dataGroupList.size() - 1) + "]");
        }

        this.dataGroupList.set(index, vDataGroup);
    }

    /**
     * 
     * 
     * @param vDataGroupArray
     */
    public void setDataGroup(final com.cdoframework.cdolib.servicebus.xsd.DataGroup[] vDataGroupArray) {
        //-- copy array
        dataGroupList.clear();

        for (int i = 0; i < vDataGroupArray.length; i++) {
                this.dataGroupList.add(vDataGroupArray[i]);
        }
    }

    /**
     * 
     * 
     * @param index
     * @param vDataService
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setDataService(final int index,final com.cdoframework.cdolib.servicebus.xsd.DataService vDataService) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.dataServiceList.size()) {
            throw new IndexOutOfBoundsException("setDataService: Index value '" + index + "' not in range [0.." + (this.dataServiceList.size() - 1) + "]");
        }

        this.dataServiceList.set(index, vDataService);
    }

    /**
     * 
     * 
     * @param vDataServiceArray
     */
    public void setDataService(final com.cdoframework.cdolib.servicebus.xsd.DataService[] vDataServiceArray) {
        //-- copy array
        dataServiceList.clear();

        for (int i = 0; i < vDataServiceArray.length; i++) {
                this.dataServiceList.add(vDataServiceArray[i]);
        }
    }

    /**
     * 
     * 
     * @param index
     * @param vNoSQLDB
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setNoSQLDB(final int index,final com.cdoframework.cdolib.servicebus.xsd.NoSQLDB vNoSQLDB) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.noSQLDBList.size()) {
            throw new IndexOutOfBoundsException("setNoSQLDB: Index value '" + index + "' not in range [0.." + (this.noSQLDBList.size() - 1) + "]");
        }

        this.noSQLDBList.set(index, vNoSQLDB);
    }

    /**
     * 
     * 
     * @param vNoSQLDBArray
     */
    public void setNoSQLDB(final com.cdoframework.cdolib.servicebus.xsd.NoSQLDB[] vNoSQLDBArray) {
        //-- copy array
        noSQLDBList.clear();

        for (int i = 0; i < vNoSQLDBArray.length; i++) {
                this.noSQLDBList.add(vNoSQLDBArray[i]);
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
    public void setParameter(final int index,final com.cdoframework.cdolib.servicebus.xsd.Parameter vParameter) throws java.lang.IndexOutOfBoundsException {
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
    public void setParameter(final com.cdoframework.cdolib.servicebus.xsd.Parameter[] vParameterArray) {
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
     * @param vServiceConfig
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setServiceConfig(final int index,final com.cdoframework.cdolib.servicebus.xsd.ServiceConfig vServiceConfig) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.serviceConfigList.size()) {
            throw new IndexOutOfBoundsException("setServiceConfig: Index value '" + index + "' not in range [0.." + (this.serviceConfigList.size() - 1) + "]");
        }

        this.serviceConfigList.set(index, vServiceConfig);
    }

    /**
     * 
     * 
     * @param vServiceConfigArray
     */
    public void setServiceConfig(final com.cdoframework.cdolib.servicebus.xsd.ServiceConfig[] vServiceConfigArray) {
        //-- copy array
        serviceConfigList.clear();

        for (int i = 0; i < vServiceConfigArray.length; i++) {
                this.serviceConfigList.add(vServiceConfigArray[i]);
        }
    }

    /**
     * 
     * 
     * @param index
     * @param vTransService
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setTransService(final int index,final com.cdoframework.cdolib.servicebus.xsd.TransService vTransService) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.transServiceList.size()) {
            throw new IndexOutOfBoundsException("setTransService: Index value '" + index + "' not in range [0.." + (this.transServiceList.size() - 1) + "]");
        }

        this.transServiceList.set(index, vTransService);
    }

    /**
     * 
     * 
     * @param vTransServiceArray
     */
    public void setTransService(final com.cdoframework.cdolib.servicebus.xsd.TransService[] vTransServiceArray) {
        //-- copy array
        transServiceList.clear();

        for (int i = 0; i < vTransServiceArray.length; i++) {
                this.transServiceList.add(vTransServiceArray[i]);
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
     * com.cdoframework.cdolib.servicebus.xsd.ServicePlugin
     */
    public static com.cdoframework.cdolib.servicebus.xsd.ServicePlugin unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.servicebus.xsd.ServicePlugin) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.servicebus.xsd.ServicePlugin.class, reader);
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
  /**
     * *************************************** manual  code *************************************************
     * 
     * @param strXML
     * @return
     */
    static Logger logger = Logger.getLogger(ServicePlugin.class);
    
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
