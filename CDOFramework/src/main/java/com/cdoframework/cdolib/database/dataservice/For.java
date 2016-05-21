/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.database.dataservice;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

/**
 * Class For.
 * 
 * @version $Revision$ $Date$
 */
public class For extends com.cdoframework.cdolib.database.dataservice.BlockType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _indexId
     */
    private java.lang.String _indexId;

    /**
     * Field _fromIndex
     */
    private java.lang.String _fromIndex;

    /**
     * Field _count
     */
    private java.lang.String _count;


      //----------------/
     //- Constructors -/
    //----------------/

    public For() 
     {
        super();
    } //-- com.cdoframework.cdolib.database.dataservice.For()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'count'.
     * 
     * @return String
     * @return the value of field 'count'.
     */
    public java.lang.String getCount()
    {
        return this._count;
    } //-- java.lang.String getCount() 

    /**
     * Returns the value of field 'fromIndex'.
     * 
     * @return String
     * @return the value of field 'fromIndex'.
     */
    public java.lang.String getFromIndex()
    {
        return this._fromIndex;
    } //-- java.lang.String getFromIndex() 

    /**
     * Returns the value of field 'indexId'.
     * 
     * @return String
     * @return the value of field 'indexId'.
     */
    public java.lang.String getIndexId()
    {
        return this._indexId;
    } //-- java.lang.String getIndexId() 

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
     * Sets the value of field 'count'.
     * 
     * @param count the value of field 'count'.
     */
    public void setCount(java.lang.String count)
    {
        this._count = count;
    } //-- void setCount(java.lang.String) 

    /**
     * Sets the value of field 'fromIndex'.
     * 
     * @param fromIndex the value of field 'fromIndex'.
     */
    public void setFromIndex(java.lang.String fromIndex)
    {
        this._fromIndex = fromIndex;
    } //-- void setFromIndex(java.lang.String) 

    /**
     * Sets the value of field 'indexId'.
     * 
     * @param indexId the value of field 'indexId'.
     */
    public void setIndexId(java.lang.String indexId)
    {
        this._indexId = indexId;
    } //-- void setIndexId(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return BlockType
     */
    public static com.cdoframework.cdolib.database.dataservice.BlockType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.database.dataservice.BlockType) Unmarshaller.unmarshal(com.cdoframework.cdolib.database.dataservice.For.class, reader);
    } //-- com.cdoframework.cdolib.database.dataservice.BlockType unmarshal(java.io.Reader) 

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
