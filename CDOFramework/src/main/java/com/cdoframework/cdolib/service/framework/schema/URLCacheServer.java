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
 * Class URLCacheServer.
 * 
 * @version $Revision$ $Date$
 */
public class URLCacheServer implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _id
     */
    private java.lang.String _id;

    /**
     * Field _classPath
     */
    private java.lang.String _classPath;

    /**
     * Field _parameterList
     */
    private java.util.ArrayList _parameterList;


      //----------------/
     //- Constructors -/
    //----------------/

    public URLCacheServer() 
     {
        super();
        _parameterList = new ArrayList();
    } //-- com.cdoframework.cdolib.service.framework.schema.URLCacheServer()


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
     * Method clearParameter
     * 
     */
    public void clearParameter()
    {
        _parameterList.clear();
    } //-- void clearParameter() 

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
     * Returns the value of field 'classPath'.
     * 
     * @return String
     * @return the value of field 'classPath'.
     */
    public java.lang.String getClassPath()
    {
        return this._classPath;
    } //-- java.lang.String getClassPath() 

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
     * Sets the value of field 'classPath'.
     * 
     * @param classPath the value of field 'classPath'.
     */
    public void setClassPath(java.lang.String classPath)
    {
        this._classPath = classPath;
    } //-- void setClassPath(java.lang.String) 

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
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return URLCacheServer
     */
    public static com.cdoframework.cdolib.service.framework.schema.URLCacheServer unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.service.framework.schema.URLCacheServer) Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.schema.URLCacheServer.class, reader);
    } //-- com.cdoframework.cdolib.service.framework.schema.URLCacheServer unmarshal(java.io.Reader) 

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
