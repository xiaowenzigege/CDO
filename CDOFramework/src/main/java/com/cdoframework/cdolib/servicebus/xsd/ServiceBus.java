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
public class ServiceBus implements java.io.Serializable {

    /**
     * Field dataGroupId.
     */
    private java.lang.String dataGroupId;

    /**
     * Field noSQLDBId.
     */
    private java.lang.String noSQLDBId;

    private java.util.List<com.cdoframework.cdolib.servicebus.xsd.Parameter> parameterList;

    private java.util.List<com.cdoframework.cdolib.servicebus.xsd.DataGroup> dataGroupList;

    private java.util.List<com.cdoframework.cdolib.servicebus.xsd.ZkProducer> zkProducerList;

    private java.util.List<com.cdoframework.cdolib.servicebus.xsd.ZkConsumer> zkConsumerList;

    private java.util.List<com.cdoframework.cdolib.servicebus.xsd.NoSQLDB> noSQLDBList;

    /**
     * Field clusterController.
     */
    private com.cdoframework.cdolib.servicebus.xsd.ClusterController clusterController;

    /**
     * Field eventProcessor.
     */
    private com.cdoframework.cdolib.servicebus.xsd.EventProcessor eventProcessor;

    private java.util.List<java.lang.String> pluginXMLResourceList;

    public ServiceBus() {
        super();
        this.parameterList = new java.util.ArrayList<com.cdoframework.cdolib.servicebus.xsd.Parameter>();
        this.dataGroupList = new java.util.ArrayList<com.cdoframework.cdolib.servicebus.xsd.DataGroup>();
        this.zkProducerList = new java.util.ArrayList<com.cdoframework.cdolib.servicebus.xsd.ZkProducer>();
        this.zkConsumerList = new java.util.ArrayList<com.cdoframework.cdolib.servicebus.xsd.ZkConsumer>();
        this.noSQLDBList = new java.util.ArrayList<com.cdoframework.cdolib.servicebus.xsd.NoSQLDB>();
        this.pluginXMLResourceList = new java.util.ArrayList<java.lang.String>();
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
     * @param vPluginXMLResource
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addPluginXMLResource(final java.lang.String vPluginXMLResource) throws java.lang.IndexOutOfBoundsException {
        this.pluginXMLResourceList.add(vPluginXMLResource);
    }

    /**
     * 
     * 
     * @param index
     * @param vPluginXMLResource
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addPluginXMLResource(final int index,final java.lang.String vPluginXMLResource) throws java.lang.IndexOutOfBoundsException {
        this.pluginXMLResourceList.add(index, vPluginXMLResource);
    }

    /**
     * 
     * 
     * @param vZkConsumer
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addZkConsumer(final com.cdoframework.cdolib.servicebus.xsd.ZkConsumer vZkConsumer) throws java.lang.IndexOutOfBoundsException {
        this.zkConsumerList.add(vZkConsumer);
    }

    /**
     * 
     * 
     * @param index
     * @param vZkConsumer
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addZkConsumer(final int index,final com.cdoframework.cdolib.servicebus.xsd.ZkConsumer vZkConsumer) throws java.lang.IndexOutOfBoundsException {
        this.zkConsumerList.add(index, vZkConsumer);
    }

    /**
     * 
     * 
     * @param vZkProducer
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addZkProducer(final com.cdoframework.cdolib.servicebus.xsd.ZkProducer vZkProducer) throws java.lang.IndexOutOfBoundsException {
        this.zkProducerList.add(vZkProducer);
    }

    /**
     * 
     * 
     * @param index
     * @param vZkProducer
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addZkProducer(final int index,final com.cdoframework.cdolib.servicebus.xsd.ZkProducer vZkProducer) throws java.lang.IndexOutOfBoundsException {
        this.zkProducerList.add(index, vZkProducer);
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
     * Method enumeratePluginXMLResource.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends java.lang.String> enumeratePluginXMLResource() {
        return java.util.Collections.enumeration(this.pluginXMLResourceList);
    }

    /**
     * Method enumerateZkConsumer.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.servicebus.xsd.ZkConsumer> enumerateZkConsumer() {
        return java.util.Collections.enumeration(this.zkConsumerList);
    }

    /**
     * Method enumerateZkProducer.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.servicebus.xsd.ZkProducer> enumerateZkProducer() {
        return java.util.Collections.enumeration(this.zkProducerList);
    }

    /**
     * Returns the value of field 'clusterController'.
     * 
     * @return the value of field 'ClusterController'.
     */
    public com.cdoframework.cdolib.servicebus.xsd.ClusterController getClusterController() {
        return this.clusterController;
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
     * Returns the value of field 'dataGroupId'.
     * 
     * @return the value of field 'DataGroupId'.
     */
    public java.lang.String getDataGroupId() {
        return this.dataGroupId;
    }

    /**
     * Returns the value of field 'eventProcessor'.
     * 
     * @return the value of field 'EventProcessor'.
     */
    public com.cdoframework.cdolib.servicebus.xsd.EventProcessor getEventProcessor() {
        return this.eventProcessor;
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
     * Returns the value of field 'noSQLDBId'.
     * 
     * @return the value of field 'NoSQLDBId'.
     */
    public java.lang.String getNoSQLDBId() {
        return this.noSQLDBId;
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
     * Method getPluginXMLResource.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the java.lang.String at the given index
     */
    public java.lang.String getPluginXMLResource(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.pluginXMLResourceList.size()) {
            throw new IndexOutOfBoundsException("getPluginXMLResource: Index value '" + index + "' not in range [0.." + (this.pluginXMLResourceList.size() - 1) + "]");
        }

        return (java.lang.String) pluginXMLResourceList.get(index);
    }

    /**
     * Method getPluginXMLResource.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public java.lang.String[] getPluginXMLResource() {
        java.lang.String[] array = new java.lang.String[0];
        return this.pluginXMLResourceList.toArray(array);
    }

    /**
     * Method getPluginXMLResourceCount.
     * 
     * @return the size of this collection
     */
    public int getPluginXMLResourceCount() {
        return this.pluginXMLResourceList.size();
    }

    /**
     * Method getZkConsumer.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.servicebus.xsd.ZkConsumer at the
     * given index
     */
    public com.cdoframework.cdolib.servicebus.xsd.ZkConsumer getZkConsumer(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.zkConsumerList.size()) {
            throw new IndexOutOfBoundsException("getZkConsumer: Index value '" + index + "' not in range [0.." + (this.zkConsumerList.size() - 1) + "]");
        }

        return zkConsumerList.get(index);
    }

    /**
     * Method getZkConsumer.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.servicebus.xsd.ZkConsumer[] getZkConsumer() {
        com.cdoframework.cdolib.servicebus.xsd.ZkConsumer[] array = new com.cdoframework.cdolib.servicebus.xsd.ZkConsumer[0];
        return this.zkConsumerList.toArray(array);
    }

    /**
     * Method getZkConsumerCount.
     * 
     * @return the size of this collection
     */
    public int getZkConsumerCount() {
        return this.zkConsumerList.size();
    }

    /**
     * Method getZkProducer.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.servicebus.xsd.ZkProducer at the
     * given index
     */
    public com.cdoframework.cdolib.servicebus.xsd.ZkProducer getZkProducer(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.zkProducerList.size()) {
            throw new IndexOutOfBoundsException("getZkProducer: Index value '" + index + "' not in range [0.." + (this.zkProducerList.size() - 1) + "]");
        }

        return zkProducerList.get(index);
    }

    /**
     * Method getZkProducer.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.servicebus.xsd.ZkProducer[] getZkProducer() {
        com.cdoframework.cdolib.servicebus.xsd.ZkProducer[] array = new com.cdoframework.cdolib.servicebus.xsd.ZkProducer[0];
        return this.zkProducerList.toArray(array);
    }

    /**
     * Method getZkProducerCount.
     * 
     * @return the size of this collection
     */
    public int getZkProducerCount() {
        return this.zkProducerList.size();
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
     * Method iterateDataGroup.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.servicebus.xsd.DataGroup> iterateDataGroup() {
        return this.dataGroupList.iterator();
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
     * Method iteratePluginXMLResource.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends java.lang.String> iteratePluginXMLResource() {
        return this.pluginXMLResourceList.iterator();
    }

    /**
     * Method iterateZkConsumer.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.servicebus.xsd.ZkConsumer> iterateZkConsumer() {
        return this.zkConsumerList.iterator();
    }

    /**
     * Method iterateZkProducer.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.servicebus.xsd.ZkProducer> iterateZkProducer() {
        return this.zkProducerList.iterator();
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
    public void removeAllDataGroup() {
        this.dataGroupList.clear();
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
    public void removeAllPluginXMLResource() {
        this.pluginXMLResourceList.clear();
    }

    /**
     */
    public void removeAllZkConsumer() {
        this.zkConsumerList.clear();
    }

    /**
     */
    public void removeAllZkProducer() {
        this.zkProducerList.clear();
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
     * Method removePluginXMLResource.
     * 
     * @param vPluginXMLResource
     * @return true if the object was removed from the collection.
     */
    public boolean removePluginXMLResource(final java.lang.String vPluginXMLResource) {
        boolean removed = pluginXMLResourceList.remove(vPluginXMLResource);
        return removed;
    }

    /**
     * Method removePluginXMLResourceAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public java.lang.String removePluginXMLResourceAt(final int index) {
        java.lang.Object obj = this.pluginXMLResourceList.remove(index);
        return (java.lang.String) obj;
    }

    /**
     * Method removeZkConsumer.
     * 
     * @param vZkConsumer
     * @return true if the object was removed from the collection.
     */
    public boolean removeZkConsumer(final com.cdoframework.cdolib.servicebus.xsd.ZkConsumer vZkConsumer) {
        boolean removed = zkConsumerList.remove(vZkConsumer);
        return removed;
    }

    /**
     * Method removeZkConsumerAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.servicebus.xsd.ZkConsumer removeZkConsumerAt(final int index) {
        java.lang.Object obj = this.zkConsumerList.remove(index);
        return (com.cdoframework.cdolib.servicebus.xsd.ZkConsumer) obj;
    }

    /**
     * Method removeZkProducer.
     * 
     * @param vZkProducer
     * @return true if the object was removed from the collection.
     */
    public boolean removeZkProducer(final com.cdoframework.cdolib.servicebus.xsd.ZkProducer vZkProducer) {
        boolean removed = zkProducerList.remove(vZkProducer);
        return removed;
    }

    /**
     * Method removeZkProducerAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.servicebus.xsd.ZkProducer removeZkProducerAt(final int index) {
        java.lang.Object obj = this.zkProducerList.remove(index);
        return (com.cdoframework.cdolib.servicebus.xsd.ZkProducer) obj;
    }

    /**
     * Sets the value of field 'clusterController'.
     * 
     * @param clusterController the value of field
     * 'clusterController'.
     */
    public void setClusterController(final com.cdoframework.cdolib.servicebus.xsd.ClusterController clusterController) {
        this.clusterController = clusterController;
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
     * Sets the value of field 'dataGroupId'.
     * 
     * @param dataGroupId the value of field 'dataGroupId'.
     */
    public void setDataGroupId(final java.lang.String dataGroupId) {
        this.dataGroupId = dataGroupId;
    }

    /**
     * Sets the value of field 'eventProcessor'.
     * 
     * @param eventProcessor the value of field 'eventProcessor'.
     */
    public void setEventProcessor(final com.cdoframework.cdolib.servicebus.xsd.EventProcessor eventProcessor) {
        this.eventProcessor = eventProcessor;
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
     * Sets the value of field 'noSQLDBId'.
     * 
     * @param noSQLDBId the value of field 'noSQLDBId'.
     */
    public void setNoSQLDBId(final java.lang.String noSQLDBId) {
        this.noSQLDBId = noSQLDBId;
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
     * @param vPluginXMLResource
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setPluginXMLResource(final int index,final java.lang.String vPluginXMLResource) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.pluginXMLResourceList.size()) {
            throw new IndexOutOfBoundsException("setPluginXMLResource: Index value '" + index + "' not in range [0.." + (this.pluginXMLResourceList.size() - 1) + "]");
        }

        this.pluginXMLResourceList.set(index, vPluginXMLResource);
    }

    /**
     * 
     * 
     * @param vPluginXMLResourceArray
     */
    public void setPluginXMLResource(final java.lang.String[] vPluginXMLResourceArray) {
        //-- copy array
        pluginXMLResourceList.clear();

        for (int i = 0; i < vPluginXMLResourceArray.length; i++) {
                this.pluginXMLResourceList.add(vPluginXMLResourceArray[i]);
        }
    }

    /**
     * 
     * 
     * @param index
     * @param vZkConsumer
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setZkConsumer(final int index,final com.cdoframework.cdolib.servicebus.xsd.ZkConsumer vZkConsumer) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.zkConsumerList.size()) {
            throw new IndexOutOfBoundsException("setZkConsumer: Index value '" + index + "' not in range [0.." + (this.zkConsumerList.size() - 1) + "]");
        }

        this.zkConsumerList.set(index, vZkConsumer);
    }

    /**
     * 
     * 
     * @param vZkConsumerArray
     */
    public void setZkConsumer(final com.cdoframework.cdolib.servicebus.xsd.ZkConsumer[] vZkConsumerArray) {
        //-- copy array
        zkConsumerList.clear();

        for (int i = 0; i < vZkConsumerArray.length; i++) {
                this.zkConsumerList.add(vZkConsumerArray[i]);
        }
    }

    /**
     * 
     * 
     * @param index
     * @param vZkProducer
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setZkProducer(final int index,final com.cdoframework.cdolib.servicebus.xsd.ZkProducer vZkProducer) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.zkProducerList.size()) {
            throw new IndexOutOfBoundsException("setZkProducer: Index value '" + index + "' not in range [0.." + (this.zkProducerList.size() - 1) + "]");
        }

        this.zkProducerList.set(index, vZkProducer);
    }

    /**
     * 
     * 
     * @param vZkProducerArray
     */
    public void setZkProducer(final com.cdoframework.cdolib.servicebus.xsd.ZkProducer[] vZkProducerArray) {
        //-- copy array
        zkProducerList.clear();

        for (int i = 0; i < vZkProducerArray.length; i++) {
                this.zkProducerList.add(vZkProducerArray[i]);
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
     * com.cdoframework.cdolib.servicebus.xsd.ServiceBus
     */    public static com.cdoframework.cdolib.servicebus.xsd.ServiceBus unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.servicebus.xsd.ServiceBus) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.servicebus.xsd.ServiceBus.class, reader);
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
    static Logger logger = Logger.getLogger(ServiceBus.class);
    
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
    			{
    				logger.error("fromXML:"+e.getMessage(),e);
    			}
    		}
    	}
    }
}
