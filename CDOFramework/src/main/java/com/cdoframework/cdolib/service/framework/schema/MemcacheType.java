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
import java.io.Writer;
import java.util.ArrayList;
import java.util.Enumeration;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

/**
 * Class MemcacheType.
 * 
 * @version $Revision$ $Date$
 */
public class MemcacheType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _id
     */
    private java.lang.String _id;

    /**
     * Field _initConnCount
     */
    private int _initConnCount;

    /**
     * keeps track of state for field: _initConnCount
     */
    private boolean _has_initConnCount;

    /**
     * Field _maxConnCount
     */
    private int _maxConnCount;

    /**
     * keeps track of state for field: _maxConnCount
     */
    private boolean _has_maxConnCount;

    /**
     * Field _minConnCount
     */
    private int _minConnCount;

    /**
     * keeps track of state for field: _minConnCount
     */
    private boolean _has_minConnCount;

    /**
     * Field _maxIdleTime
     */
    private int _maxIdleTime;

    /**
     * keeps track of state for field: _maxIdleTime
     */
    private boolean _has_maxIdleTime;

    /**
     * Field _maxBusyTime
     */
    private int _maxBusyTime;

    /**
     * keeps track of state for field: _maxBusyTime
     */
    private boolean _has_maxBusyTime;

    /**
     * Field _maintSleep
     */
    private int _maintSleep;

    /**
     * keeps track of state for field: _maintSleep
     */
    private boolean _has_maintSleep;

    /**
     * Field _readTimeout
     */
    private int _readTimeout;

    /**
     * keeps track of state for field: _readTimeout
     */
    private boolean _has_readTimeout;

    /**
     * Field _connectionTimeout
     */
    private int _connectionTimeout;

    /**
     * keeps track of state for field: _connectionTimeout
     */
    private boolean _has_connectionTimeout;

    /**
     * Field _nagle
     */
    private boolean _nagle;

    /**
     * keeps track of state for field: _nagle
     */
    private boolean _has_nagle;

    /**
     * Field _hashingAlg
     */
    private int _hashingAlg;

    /**
     * keeps track of state for field: _hashingAlg
     */
    private boolean _has_hashingAlg;

    /**
     * Field _aliveCheck
     */
    private boolean _aliveCheck;

    /**
     * keeps track of state for field: _aliveCheck
     */
    private boolean _has_aliveCheck;

    /**
     * Field _failback
     */
    private boolean _failback;

    /**
     * keeps track of state for field: _failback
     */
    private boolean _has_failback;

    /**
     * Field _failover
     */
    private boolean _failover;

    /**
     * keeps track of state for field: _failover
     */
    private boolean _has_failover;

    /**
     * Field _parameterList
     */
    private java.util.ArrayList _parameterList;

    /**
     * Field _serverList
     */
    private java.util.ArrayList _serverList;


      //----------------/
     //- Constructors -/
    //----------------/

    public MemcacheType() 
     {
        super();
        _parameterList = new ArrayList();
        _serverList = new ArrayList();
    } //-- com.cdoframework.cdolib.service.framework.schema.MemcacheType()


      //-----------/
     //- Methods -/
    //-----------/

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
     * Method addServer
     * 
     * 
     * 
     * @param vServer
     */
    public void addServer(com.cdoframework.cdolib.service.framework.schema.Server vServer)
        throws java.lang.IndexOutOfBoundsException
    {
        _serverList.add(vServer);
    } //-- void addServer(com.cdoframework.cdolib.service.framework.schema.Server) 

    /**
     * Method addServer
     * 
     * 
     * 
     * @param index
     * @param vServer
     */
    public void addServer(int index, com.cdoframework.cdolib.service.framework.schema.Server vServer)
        throws java.lang.IndexOutOfBoundsException
    {
        _serverList.add(index, vServer);
    } //-- void addServer(int, com.cdoframework.cdolib.service.framework.schema.Server) 

    /**
     * Method clearParameter
     * 
     */
    public void clearParameter()
    {
        _parameterList.clear();
    } //-- void clearParameter() 

    /**
     * Method clearServer
     * 
     */
    public void clearServer()
    {
        _serverList.clear();
    } //-- void clearServer() 

    /**
     * Method deleteAliveCheck
     * 
     */
    public void deleteAliveCheck()
    {
        this._has_aliveCheck= false;
    } //-- void deleteAliveCheck() 

    /**
     * Method deleteConnectionTimeout
     * 
     */
    public void deleteConnectionTimeout()
    {
        this._has_connectionTimeout= false;
    } //-- void deleteConnectionTimeout() 

    /**
     * Method deleteFailback
     * 
     */
    public void deleteFailback()
    {
        this._has_failback= false;
    } //-- void deleteFailback() 

    /**
     * Method deleteFailover
     * 
     */
    public void deleteFailover()
    {
        this._has_failover= false;
    } //-- void deleteFailover() 

    /**
     * Method deleteHashingAlg
     * 
     */
    public void deleteHashingAlg()
    {
        this._has_hashingAlg= false;
    } //-- void deleteHashingAlg() 

    /**
     * Method deleteInitConnCount
     * 
     */
    public void deleteInitConnCount()
    {
        this._has_initConnCount= false;
    } //-- void deleteInitConnCount() 

    /**
     * Method deleteMaintSleep
     * 
     */
    public void deleteMaintSleep()
    {
        this._has_maintSleep= false;
    } //-- void deleteMaintSleep() 

    /**
     * Method deleteMaxBusyTime
     * 
     */
    public void deleteMaxBusyTime()
    {
        this._has_maxBusyTime= false;
    } //-- void deleteMaxBusyTime() 

    /**
     * Method deleteMaxConnCount
     * 
     */
    public void deleteMaxConnCount()
    {
        this._has_maxConnCount= false;
    } //-- void deleteMaxConnCount() 

    /**
     * Method deleteMaxIdleTime
     * 
     */
    public void deleteMaxIdleTime()
    {
        this._has_maxIdleTime= false;
    } //-- void deleteMaxIdleTime() 

    /**
     * Method deleteMinConnCount
     * 
     */
    public void deleteMinConnCount()
    {
        this._has_minConnCount= false;
    } //-- void deleteMinConnCount() 

    /**
     * Method deleteNagle
     * 
     */
    public void deleteNagle()
    {
        this._has_nagle= false;
    } //-- void deleteNagle() 

    /**
     * Method deleteReadTimeout
     * 
     */
    public void deleteReadTimeout()
    {
        this._has_readTimeout= false;
    } //-- void deleteReadTimeout() 

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
     * Method enumerateServer
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateServer()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_serverList.iterator());
    } //-- java.util.Enumeration enumerateServer() 

    /**
     * Returns the value of field 'aliveCheck'.
     * 
     * @return boolean
     * @return the value of field 'aliveCheck'.
     */
    public boolean getAliveCheck()
    {
        return this._aliveCheck;
    } //-- boolean getAliveCheck() 

    /**
     * Returns the value of field 'connectionTimeout'.
     * 
     * @return int
     * @return the value of field 'connectionTimeout'.
     */
    public int getConnectionTimeout()
    {
        return this._connectionTimeout;
    } //-- int getConnectionTimeout() 

    /**
     * Returns the value of field 'failback'.
     * 
     * @return boolean
     * @return the value of field 'failback'.
     */
    public boolean getFailback()
    {
        return this._failback;
    } //-- boolean getFailback() 

    /**
     * Returns the value of field 'failover'.
     * 
     * @return boolean
     * @return the value of field 'failover'.
     */
    public boolean getFailover()
    {
        return this._failover;
    } //-- boolean getFailover() 

    /**
     * Returns the value of field 'hashingAlg'.
     * 
     * @return int
     * @return the value of field 'hashingAlg'.
     */
    public int getHashingAlg()
    {
        return this._hashingAlg;
    } //-- int getHashingAlg() 

    /**
     * Returns the value of field 'id'.
     * 
     * @return String
     * @return the value of field 'id'.
     */
    public java.lang.String getId()
    {
        return this._id;
    } //-- java.lang.String getId() 

    /**
     * Returns the value of field 'initConnCount'.
     * 
     * @return int
     * @return the value of field 'initConnCount'.
     */
    public int getInitConnCount()
    {
        return this._initConnCount;
    } //-- int getInitConnCount() 

    /**
     * Returns the value of field 'maintSleep'.
     * 
     * @return int
     * @return the value of field 'maintSleep'.
     */
    public int getMaintSleep()
    {
        return this._maintSleep;
    } //-- int getMaintSleep() 

    /**
     * Returns the value of field 'maxBusyTime'.
     * 
     * @return int
     * @return the value of field 'maxBusyTime'.
     */
    public int getMaxBusyTime()
    {
        return this._maxBusyTime;
    } //-- int getMaxBusyTime() 

    /**
     * Returns the value of field 'maxConnCount'.
     * 
     * @return int
     * @return the value of field 'maxConnCount'.
     */
    public int getMaxConnCount()
    {
        return this._maxConnCount;
    } //-- int getMaxConnCount() 

    /**
     * Returns the value of field 'maxIdleTime'.
     * 
     * @return int
     * @return the value of field 'maxIdleTime'.
     */
    public int getMaxIdleTime()
    {
        return this._maxIdleTime;
    } //-- int getMaxIdleTime() 

    /**
     * Returns the value of field 'minConnCount'.
     * 
     * @return int
     * @return the value of field 'minConnCount'.
     */
    public int getMinConnCount()
    {
        return this._minConnCount;
    } //-- int getMinConnCount() 

    /**
     * Returns the value of field 'nagle'.
     * 
     * @return boolean
     * @return the value of field 'nagle'.
     */
    public boolean getNagle()
    {
        return this._nagle;
    } //-- boolean getNagle() 

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
     * Returns the value of field 'readTimeout'.
     * 
     * @return int
     * @return the value of field 'readTimeout'.
     */
    public int getReadTimeout()
    {
        return this._readTimeout;
    } //-- int getReadTimeout() 

    /**
     * Method getServer
     * 
     * 
     * 
     * @param index
     * @return Server
     */
    public com.cdoframework.cdolib.service.framework.schema.Server getServer(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _serverList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.cdoframework.cdolib.service.framework.schema.Server) _serverList.get(index);
    } //-- com.cdoframework.cdolib.service.framework.schema.Server getServer(int) 

    /**
     * Method getServer
     * 
     * 
     * 
     * @return Server
     */
    public com.cdoframework.cdolib.service.framework.schema.Server[] getServer()
    {
        int size = _serverList.size();
        com.cdoframework.cdolib.service.framework.schema.Server[] mArray = new com.cdoframework.cdolib.service.framework.schema.Server[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.cdoframework.cdolib.service.framework.schema.Server) _serverList.get(index);
        }
        return mArray;
    } //-- com.cdoframework.cdolib.service.framework.schema.Server[] getServer() 

    /**
     * Method getServerCount
     * 
     * 
     * 
     * @return int
     */
    public int getServerCount()
    {
        return _serverList.size();
    } //-- int getServerCount() 

    /**
     * Method hasAliveCheck
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasAliveCheck()
    {
        return this._has_aliveCheck;
    } //-- boolean hasAliveCheck() 

    /**
     * Method hasConnectionTimeout
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasConnectionTimeout()
    {
        return this._has_connectionTimeout;
    } //-- boolean hasConnectionTimeout() 

    /**
     * Method hasFailback
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasFailback()
    {
        return this._has_failback;
    } //-- boolean hasFailback() 

    /**
     * Method hasFailover
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasFailover()
    {
        return this._has_failover;
    } //-- boolean hasFailover() 

    /**
     * Method hasHashingAlg
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasHashingAlg()
    {
        return this._has_hashingAlg;
    } //-- boolean hasHashingAlg() 

    /**
     * Method hasInitConnCount
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasInitConnCount()
    {
        return this._has_initConnCount;
    } //-- boolean hasInitConnCount() 

    /**
     * Method hasMaintSleep
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasMaintSleep()
    {
        return this._has_maintSleep;
    } //-- boolean hasMaintSleep() 

    /**
     * Method hasMaxBusyTime
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasMaxBusyTime()
    {
        return this._has_maxBusyTime;
    } //-- boolean hasMaxBusyTime() 

    /**
     * Method hasMaxConnCount
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasMaxConnCount()
    {
        return this._has_maxConnCount;
    } //-- boolean hasMaxConnCount() 

    /**
     * Method hasMaxIdleTime
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasMaxIdleTime()
    {
        return this._has_maxIdleTime;
    } //-- boolean hasMaxIdleTime() 

    /**
     * Method hasMinConnCount
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasMinConnCount()
    {
        return this._has_minConnCount;
    } //-- boolean hasMinConnCount() 

    /**
     * Method hasNagle
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasNagle()
    {
        return this._has_nagle;
    } //-- boolean hasNagle() 

    /**
     * Method hasReadTimeout
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasReadTimeout()
    {
        return this._has_readTimeout;
    } //-- boolean hasReadTimeout() 

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
     * Method removeServer
     * 
     * 
     * 
     * @param vServer
     * @return boolean
     */
    public boolean removeServer(com.cdoframework.cdolib.service.framework.schema.Server vServer)
    {
        boolean removed = _serverList.remove(vServer);
        return removed;
    } //-- boolean removeServer(com.cdoframework.cdolib.service.framework.schema.Server) 

    /**
     * Sets the value of field 'aliveCheck'.
     * 
     * @param aliveCheck the value of field 'aliveCheck'.
     */
    public void setAliveCheck(boolean aliveCheck)
    {
        this._aliveCheck = aliveCheck;
        this._has_aliveCheck = true;
    } //-- void setAliveCheck(boolean) 

    /**
     * Sets the value of field 'connectionTimeout'.
     * 
     * @param connectionTimeout the value of field
     * 'connectionTimeout'.
     */
    public void setConnectionTimeout(int connectionTimeout)
    {
        this._connectionTimeout = connectionTimeout;
        this._has_connectionTimeout = true;
    } //-- void setConnectionTimeout(int) 

    /**
     * Sets the value of field 'failback'.
     * 
     * @param failback the value of field 'failback'.
     */
    public void setFailback(boolean failback)
    {
        this._failback = failback;
        this._has_failback = true;
    } //-- void setFailback(boolean) 

    /**
     * Sets the value of field 'failover'.
     * 
     * @param failover the value of field 'failover'.
     */
    public void setFailover(boolean failover)
    {
        this._failover = failover;
        this._has_failover = true;
    } //-- void setFailover(boolean) 

    /**
     * Sets the value of field 'hashingAlg'.
     * 
     * @param hashingAlg the value of field 'hashingAlg'.
     */
    public void setHashingAlg(int hashingAlg)
    {
        this._hashingAlg = hashingAlg;
        this._has_hashingAlg = true;
    } //-- void setHashingAlg(int) 

    /**
     * Sets the value of field 'id'.
     * 
     * @param id the value of field 'id'.
     */
    public void setId(java.lang.String id)
    {
        this._id = id;
    } //-- void setId(java.lang.String) 

    /**
     * Sets the value of field 'initConnCount'.
     * 
     * @param initConnCount the value of field 'initConnCount'.
     */
    public void setInitConnCount(int initConnCount)
    {
        this._initConnCount = initConnCount;
        this._has_initConnCount = true;
    } //-- void setInitConnCount(int) 

    /**
     * Sets the value of field 'maintSleep'.
     * 
     * @param maintSleep the value of field 'maintSleep'.
     */
    public void setMaintSleep(int maintSleep)
    {
        this._maintSleep = maintSleep;
        this._has_maintSleep = true;
    } //-- void setMaintSleep(int) 

    /**
     * Sets the value of field 'maxBusyTime'.
     * 
     * @param maxBusyTime the value of field 'maxBusyTime'.
     */
    public void setMaxBusyTime(int maxBusyTime)
    {
        this._maxBusyTime = maxBusyTime;
        this._has_maxBusyTime = true;
    } //-- void setMaxBusyTime(int) 

    /**
     * Sets the value of field 'maxConnCount'.
     * 
     * @param maxConnCount the value of field 'maxConnCount'.
     */
    public void setMaxConnCount(int maxConnCount)
    {
        this._maxConnCount = maxConnCount;
        this._has_maxConnCount = true;
    } //-- void setMaxConnCount(int) 

    /**
     * Sets the value of field 'maxIdleTime'.
     * 
     * @param maxIdleTime the value of field 'maxIdleTime'.
     */
    public void setMaxIdleTime(int maxIdleTime)
    {
        this._maxIdleTime = maxIdleTime;
        this._has_maxIdleTime = true;
    } //-- void setMaxIdleTime(int) 

    /**
     * Sets the value of field 'minConnCount'.
     * 
     * @param minConnCount the value of field 'minConnCount'.
     */
    public void setMinConnCount(int minConnCount)
    {
        this._minConnCount = minConnCount;
        this._has_minConnCount = true;
    } //-- void setMinConnCount(int) 

    /**
     * Sets the value of field 'nagle'.
     * 
     * @param nagle the value of field 'nagle'.
     */
    public void setNagle(boolean nagle)
    {
        this._nagle = nagle;
        this._has_nagle = true;
    } //-- void setNagle(boolean) 

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
     * Sets the value of field 'readTimeout'.
     * 
     * @param readTimeout the value of field 'readTimeout'.
     */
    public void setReadTimeout(int readTimeout)
    {
        this._readTimeout = readTimeout;
        this._has_readTimeout = true;
    } //-- void setReadTimeout(int) 

    /**
     * Method setServer
     * 
     * 
     * 
     * @param index
     * @param vServer
     */
    public void setServer(int index, com.cdoframework.cdolib.service.framework.schema.Server vServer)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _serverList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _serverList.set(index, vServer);
    } //-- void setServer(int, com.cdoframework.cdolib.service.framework.schema.Server) 

    /**
     * Method setServer
     * 
     * 
     * 
     * @param serverArray
     */
    public void setServer(com.cdoframework.cdolib.service.framework.schema.Server[] serverArray)
    {
        //-- copy array
        _serverList.clear();
        for (int i = 0; i < serverArray.length; i++) {
            _serverList.add(serverArray[i]);
        }
    } //-- void setServer(com.cdoframework.cdolib.service.framework.schema.Server) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return MemcacheType
     */
    public static com.cdoframework.cdolib.service.framework.schema.MemcacheType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.service.framework.schema.MemcacheType) Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.schema.MemcacheType.class, reader);
    } //-- com.cdoframework.cdolib.service.framework.schema.MemcacheType unmarshal(java.io.Reader) 

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

}
