/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.3</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.service.framework.xsd;

/**
 * 
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class MemcacheType implements java.io.Serializable {

    /**
     * Field id.
     */
    private java.lang.String id;

    /**
     * Field initConnCount.
     */
    private int initConnCount;

    /**
     * Keeps track of whether primitive field initConnCount has
     * been set already.
     */
    private boolean _hasinitConnCount;

    /**
     * Field maxConnCount.
     */
    private int maxConnCount;

    /**
     * Keeps track of whether primitive field maxConnCount has been
     * set already.
     */
    private boolean _hasmaxConnCount;

    /**
     * Field minConnCount.
     */
    private int minConnCount;

    /**
     * Keeps track of whether primitive field minConnCount has been
     * set already.
     */
    private boolean _hasminConnCount;

    /**
     * Field maxIdleTime.
     */
    private int maxIdleTime;

    /**
     * Keeps track of whether primitive field maxIdleTime has been
     * set already.
     */
    private boolean _hasmaxIdleTime;

    /**
     * Field maxBusyTime.
     */
    private int maxBusyTime;

    /**
     * Keeps track of whether primitive field maxBusyTime has been
     * set already.
     */
    private boolean _hasmaxBusyTime;

    /**
     * Field maintSleep.
     */
    private int maintSleep;

    /**
     * Keeps track of whether primitive field maintSleep has been
     * set already.
     */
    private boolean _hasmaintSleep;

    /**
     * Field readTimeout.
     */
    private int readTimeout;

    /**
     * Keeps track of whether primitive field readTimeout has been
     * set already.
     */
    private boolean _hasreadTimeout;

    /**
     * Field connectionTimeout.
     */
    private int connectionTimeout;

    /**
     * Keeps track of whether primitive field connectionTimeout has
     * been set already.
     */
    private boolean _hasconnectionTimeout;

    /**
     * Field nagle.
     */
    private boolean nagle;

    /**
     * Keeps track of whether primitive field nagle has been set
     * already.
     */
    private boolean _hasnagle;

    /**
     * Field hashingAlg.
     */
    private int hashingAlg;

    /**
     * Keeps track of whether primitive field hashingAlg has been
     * set already.
     */
    private boolean _hashashingAlg;

    /**
     * Field aliveCheck.
     */
    private boolean aliveCheck;

    /**
     * Keeps track of whether primitive field aliveCheck has been
     * set already.
     */
    private boolean _hasaliveCheck;

    /**
     * Field failback.
     */
    private boolean failback;

    /**
     * Keeps track of whether primitive field failback has been set
     * already.
     */
    private boolean _hasfailback;

    /**
     * Field failover.
     */
    private boolean failover;

    /**
     * Keeps track of whether primitive field failover has been set
     * already.
     */
    private boolean _hasfailover;

    private java.util.List<com.cdoframework.cdolib.service.framework.xsd.Parameter> parameterList;

    private java.util.List<com.cdoframework.cdolib.service.framework.xsd.Server> serverList;

    public MemcacheType() {
        super();
        this.parameterList = new java.util.ArrayList<com.cdoframework.cdolib.service.framework.xsd.Parameter>();
        this.serverList = new java.util.ArrayList<com.cdoframework.cdolib.service.framework.xsd.Server>();
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
     * @param vServer
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addServer(final com.cdoframework.cdolib.service.framework.xsd.Server vServer) throws java.lang.IndexOutOfBoundsException {
        this.serverList.add(vServer);
    }

    /**
     * 
     * 
     * @param index
     * @param vServer
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addServer(final int index,final com.cdoframework.cdolib.service.framework.xsd.Server vServer) throws java.lang.IndexOutOfBoundsException {
        this.serverList.add(index, vServer);
    }

    /**
     */
    public void deleteAliveCheck() {
        this._hasaliveCheck= false;
    }

    /**
     */
    public void deleteConnectionTimeout() {
        this._hasconnectionTimeout= false;
    }

    /**
     */
    public void deleteFailback() {
        this._hasfailback= false;
    }

    /**
     */
    public void deleteFailover() {
        this._hasfailover= false;
    }

    /**
     */
    public void deleteHashingAlg() {
        this._hashashingAlg= false;
    }

    /**
     */
    public void deleteInitConnCount() {
        this._hasinitConnCount= false;
    }

    /**
     */
    public void deleteMaintSleep() {
        this._hasmaintSleep= false;
    }

    /**
     */
    public void deleteMaxBusyTime() {
        this._hasmaxBusyTime= false;
    }

    /**
     */
    public void deleteMaxConnCount() {
        this._hasmaxConnCount= false;
    }

    /**
     */
    public void deleteMaxIdleTime() {
        this._hasmaxIdleTime= false;
    }

    /**
     */
    public void deleteMinConnCount() {
        this._hasminConnCount= false;
    }

    /**
     */
    public void deleteNagle() {
        this._hasnagle= false;
    }

    /**
     */
    public void deleteReadTimeout() {
        this._hasreadTimeout= false;
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
     * Method enumerateServer.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.service.framework.xsd.Server> enumerateServer() {
        return java.util.Collections.enumeration(this.serverList);
    }

    /**
     * Returns the value of field 'aliveCheck'.
     * 
     * @return the value of field 'AliveCheck'.
     */
    public boolean getAliveCheck() {
        return this.aliveCheck;
    }

    /**
     * Returns the value of field 'connectionTimeout'.
     * 
     * @return the value of field 'ConnectionTimeout'.
     */
    public int getConnectionTimeout() {
        return this.connectionTimeout;
    }

    /**
     * Returns the value of field 'failback'.
     * 
     * @return the value of field 'Failback'.
     */
    public boolean getFailback() {
        return this.failback;
    }

    /**
     * Returns the value of field 'failover'.
     * 
     * @return the value of field 'Failover'.
     */
    public boolean getFailover() {
        return this.failover;
    }

    /**
     * Returns the value of field 'hashingAlg'.
     * 
     * @return the value of field 'HashingAlg'.
     */
    public int getHashingAlg() {
        return this.hashingAlg;
    }

    /**
     * Returns the value of field 'id'.
     * 
     * @return the value of field 'Id'.
     */
    public java.lang.String getId() {
        return this.id;
    }

    /**
     * Returns the value of field 'initConnCount'.
     * 
     * @return the value of field 'InitConnCount'.
     */
    public int getInitConnCount() {
        return this.initConnCount;
    }

    /**
     * Returns the value of field 'maintSleep'.
     * 
     * @return the value of field 'MaintSleep'.
     */
    public int getMaintSleep() {
        return this.maintSleep;
    }

    /**
     * Returns the value of field 'maxBusyTime'.
     * 
     * @return the value of field 'MaxBusyTime'.
     */
    public int getMaxBusyTime() {
        return this.maxBusyTime;
    }

    /**
     * Returns the value of field 'maxConnCount'.
     * 
     * @return the value of field 'MaxConnCount'.
     */
    public int getMaxConnCount() {
        return this.maxConnCount;
    }

    /**
     * Returns the value of field 'maxIdleTime'.
     * 
     * @return the value of field 'MaxIdleTime'.
     */
    public int getMaxIdleTime() {
        return this.maxIdleTime;
    }

    /**
     * Returns the value of field 'minConnCount'.
     * 
     * @return the value of field 'MinConnCount'.
     */
    public int getMinConnCount() {
        return this.minConnCount;
    }

    /**
     * Returns the value of field 'nagle'.
     * 
     * @return the value of field 'Nagle'.
     */
    public boolean getNagle() {
        return this.nagle;
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
     * Returns the value of field 'readTimeout'.
     * 
     * @return the value of field 'ReadTimeout'.
     */
    public int getReadTimeout() {
        return this.readTimeout;
    }

    /**
     * Method getServer.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.service.framework.xsd.Server at the
     * given index
     */
    public com.cdoframework.cdolib.service.framework.xsd.Server getServer(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.serverList.size()) {
            throw new IndexOutOfBoundsException("getServer: Index value '" + index + "' not in range [0.." + (this.serverList.size() - 1) + "]");
        }

        return serverList.get(index);
    }

    /**
     * Method getServer.Returns the contents of the collection in
     * an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.service.framework.xsd.Server[] getServer() {
        com.cdoframework.cdolib.service.framework.xsd.Server[] array = new com.cdoframework.cdolib.service.framework.xsd.Server[0];
        return this.serverList.toArray(array);
    }

    /**
     * Method getServerCount.
     * 
     * @return the size of this collection
     */
    public int getServerCount() {
        return this.serverList.size();
    }

    /**
     * Method hasAliveCheck.
     * 
     * @return true if at least one AliveCheck has been added
     */
    public boolean hasAliveCheck() {
        return this._hasaliveCheck;
    }

    /**
     * Method hasConnectionTimeout.
     * 
     * @return true if at least one ConnectionTimeout has been added
     */
    public boolean hasConnectionTimeout() {
        return this._hasconnectionTimeout;
    }

    /**
     * Method hasFailback.
     * 
     * @return true if at least one Failback has been added
     */
    public boolean hasFailback() {
        return this._hasfailback;
    }

    /**
     * Method hasFailover.
     * 
     * @return true if at least one Failover has been added
     */
    public boolean hasFailover() {
        return this._hasfailover;
    }

    /**
     * Method hasHashingAlg.
     * 
     * @return true if at least one HashingAlg has been added
     */
    public boolean hasHashingAlg() {
        return this._hashashingAlg;
    }

    /**
     * Method hasInitConnCount.
     * 
     * @return true if at least one InitConnCount has been added
     */
    public boolean hasInitConnCount() {
        return this._hasinitConnCount;
    }

    /**
     * Method hasMaintSleep.
     * 
     * @return true if at least one MaintSleep has been added
     */
    public boolean hasMaintSleep() {
        return this._hasmaintSleep;
    }

    /**
     * Method hasMaxBusyTime.
     * 
     * @return true if at least one MaxBusyTime has been added
     */
    public boolean hasMaxBusyTime() {
        return this._hasmaxBusyTime;
    }

    /**
     * Method hasMaxConnCount.
     * 
     * @return true if at least one MaxConnCount has been added
     */
    public boolean hasMaxConnCount() {
        return this._hasmaxConnCount;
    }

    /**
     * Method hasMaxIdleTime.
     * 
     * @return true if at least one MaxIdleTime has been added
     */
    public boolean hasMaxIdleTime() {
        return this._hasmaxIdleTime;
    }

    /**
     * Method hasMinConnCount.
     * 
     * @return true if at least one MinConnCount has been added
     */
    public boolean hasMinConnCount() {
        return this._hasminConnCount;
    }

    /**
     * Method hasNagle.
     * 
     * @return true if at least one Nagle has been added
     */
    public boolean hasNagle() {
        return this._hasnagle;
    }

    /**
     * Method hasReadTimeout.
     * 
     * @return true if at least one ReadTimeout has been added
     */
    public boolean hasReadTimeout() {
        return this._hasreadTimeout;
    }

    /**
     * Returns the value of field 'aliveCheck'.
     * 
     * @return the value of field 'AliveCheck'.
     */
    public boolean isAliveCheck() {
        return this.aliveCheck;
    }

    /**
     * Returns the value of field 'failback'.
     * 
     * @return the value of field 'Failback'.
     */
    public boolean isFailback() {
        return this.failback;
    }

    /**
     * Returns the value of field 'failover'.
     * 
     * @return the value of field 'Failover'.
     */
    public boolean isFailover() {
        return this.failover;
    }

    /**
     * Returns the value of field 'nagle'.
     * 
     * @return the value of field 'Nagle'.
     */
    public boolean isNagle() {
        return this.nagle;
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
     * Method iterateParameter.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.service.framework.xsd.Parameter> iterateParameter() {
        return this.parameterList.iterator();
    }

    /**
     * Method iterateServer.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.service.framework.xsd.Server> iterateServer() {
        return this.serverList.iterator();
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
    public void removeAllParameter() {
        this.parameterList.clear();
    }

    /**
     */
    public void removeAllServer() {
        this.serverList.clear();
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
     * Method removeServer.
     * 
     * @param vServer
     * @return true if the object was removed from the collection.
     */
    public boolean removeServer(final com.cdoframework.cdolib.service.framework.xsd.Server vServer) {
        boolean removed = serverList.remove(vServer);
        return removed;
    }

    /**
     * Method removeServerAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.service.framework.xsd.Server removeServerAt(final int index) {
        java.lang.Object obj = this.serverList.remove(index);
        return (com.cdoframework.cdolib.service.framework.xsd.Server) obj;
    }

    /**
     * Sets the value of field 'aliveCheck'.
     * 
     * @param aliveCheck the value of field 'aliveCheck'.
     */
    public void setAliveCheck(final boolean aliveCheck) {
        this.aliveCheck = aliveCheck;
        this._hasaliveCheck = true;
    }

    /**
     * Sets the value of field 'connectionTimeout'.
     * 
     * @param connectionTimeout the value of field
     * 'connectionTimeout'.
     */
    public void setConnectionTimeout(final int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
        this._hasconnectionTimeout = true;
    }

    /**
     * Sets the value of field 'failback'.
     * 
     * @param failback the value of field 'failback'.
     */
    public void setFailback(final boolean failback) {
        this.failback = failback;
        this._hasfailback = true;
    }

    /**
     * Sets the value of field 'failover'.
     * 
     * @param failover the value of field 'failover'.
     */
    public void setFailover(final boolean failover) {
        this.failover = failover;
        this._hasfailover = true;
    }

    /**
     * Sets the value of field 'hashingAlg'.
     * 
     * @param hashingAlg the value of field 'hashingAlg'.
     */
    public void setHashingAlg(final int hashingAlg) {
        this.hashingAlg = hashingAlg;
        this._hashashingAlg = true;
    }

    /**
     * Sets the value of field 'id'.
     * 
     * @param id the value of field 'id'.
     */
    public void setId(final java.lang.String id) {
        this.id = id;
    }

    /**
     * Sets the value of field 'initConnCount'.
     * 
     * @param initConnCount the value of field 'initConnCount'.
     */
    public void setInitConnCount(final int initConnCount) {
        this.initConnCount = initConnCount;
        this._hasinitConnCount = true;
    }

    /**
     * Sets the value of field 'maintSleep'.
     * 
     * @param maintSleep the value of field 'maintSleep'.
     */
    public void setMaintSleep(final int maintSleep) {
        this.maintSleep = maintSleep;
        this._hasmaintSleep = true;
    }

    /**
     * Sets the value of field 'maxBusyTime'.
     * 
     * @param maxBusyTime the value of field 'maxBusyTime'.
     */
    public void setMaxBusyTime(final int maxBusyTime) {
        this.maxBusyTime = maxBusyTime;
        this._hasmaxBusyTime = true;
    }

    /**
     * Sets the value of field 'maxConnCount'.
     * 
     * @param maxConnCount the value of field 'maxConnCount'.
     */
    public void setMaxConnCount(final int maxConnCount) {
        this.maxConnCount = maxConnCount;
        this._hasmaxConnCount = true;
    }

    /**
     * Sets the value of field 'maxIdleTime'.
     * 
     * @param maxIdleTime the value of field 'maxIdleTime'.
     */
    public void setMaxIdleTime(final int maxIdleTime) {
        this.maxIdleTime = maxIdleTime;
        this._hasmaxIdleTime = true;
    }

    /**
     * Sets the value of field 'minConnCount'.
     * 
     * @param minConnCount the value of field 'minConnCount'.
     */
    public void setMinConnCount(final int minConnCount) {
        this.minConnCount = minConnCount;
        this._hasminConnCount = true;
    }

    /**
     * Sets the value of field 'nagle'.
     * 
     * @param nagle the value of field 'nagle'.
     */
    public void setNagle(final boolean nagle) {
        this.nagle = nagle;
        this._hasnagle = true;
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
     * Sets the value of field 'readTimeout'.
     * 
     * @param readTimeout the value of field 'readTimeout'.
     */
    public void setReadTimeout(final int readTimeout) {
        this.readTimeout = readTimeout;
        this._hasreadTimeout = true;
    }

    /**
     * 
     * 
     * @param index
     * @param vServer
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setServer(final int index,final com.cdoframework.cdolib.service.framework.xsd.Server vServer) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.serverList.size()) {
            throw new IndexOutOfBoundsException("setServer: Index value '" + index + "' not in range [0.." + (this.serverList.size() - 1) + "]");
        }

        this.serverList.set(index, vServer);
    }

    /**
     * 
     * 
     * @param vServerArray
     */
    public void setServer(final com.cdoframework.cdolib.service.framework.xsd.Server[] vServerArray) {
        //-- copy array
        serverList.clear();

        for (int i = 0; i < vServerArray.length; i++) {
                this.serverList.add(vServerArray[i]);
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
     * com.cdoframework.cdolib.service.framework.xsd.MemcacheType
     */
    public static com.cdoframework.cdolib.service.framework.xsd.MemcacheType unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.service.framework.xsd.MemcacheType) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.xsd.MemcacheType.class, reader);
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

}
