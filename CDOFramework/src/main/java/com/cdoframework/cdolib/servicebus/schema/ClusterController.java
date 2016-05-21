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
import java.io.Writer;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

/**
 * Class ClusterController.
 * 
 * @version $Revision$ $Date$
 */
public class ClusterController implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _dataGroupId
     */
    private java.lang.String _dataGroupId;

    /**
     * Field _maxDeadSecond
     */
    private int _maxDeadSecond = 5;

    /**
     * keeps track of state for field: _maxDeadSecond
     */
    private boolean _has_maxDeadSecond;

    /**
     * Field _pulseSecond
     */
    private int _pulseSecond = 2;

    /**
     * keeps track of state for field: _pulseSecond
     */
    private boolean _has_pulseSecond;


      //----------------/
     //- Constructors -/
    //----------------/

    public ClusterController() 
     {
        super();
    } //-- com.cdoframework.cdolib.servicebus.schema.ClusterController()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method deleteMaxDeadSecond
     * 
     */
    public void deleteMaxDeadSecond()
    {
        this._has_maxDeadSecond= false;
    } //-- void deleteMaxDeadSecond() 

    /**
     * Method deletePulseSecond
     * 
     */
    public void deletePulseSecond()
    {
        this._has_pulseSecond= false;
    } //-- void deletePulseSecond() 

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
     * Returns the value of field 'maxDeadSecond'.
     * 
     * @return int
     * @return the value of field 'maxDeadSecond'.
     */
    public int getMaxDeadSecond()
    {
        return this._maxDeadSecond;
    } //-- int getMaxDeadSecond() 

    /**
     * Returns the value of field 'pulseSecond'.
     * 
     * @return int
     * @return the value of field 'pulseSecond'.
     */
    public int getPulseSecond()
    {
        return this._pulseSecond;
    } //-- int getPulseSecond() 

    /**
     * Method hasMaxDeadSecond
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasMaxDeadSecond()
    {
        return this._has_maxDeadSecond;
    } //-- boolean hasMaxDeadSecond() 

    /**
     * Method hasPulseSecond
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasPulseSecond()
    {
        return this._has_pulseSecond;
    } //-- boolean hasPulseSecond() 

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
     * Sets the value of field 'dataGroupId'.
     * 
     * @param dataGroupId the value of field 'dataGroupId'.
     */
    public void setDataGroupId(java.lang.String dataGroupId)
    {
        this._dataGroupId = dataGroupId;
    } //-- void setDataGroupId(java.lang.String) 

    /**
     * Sets the value of field 'maxDeadSecond'.
     * 
     * @param maxDeadSecond the value of field 'maxDeadSecond'.
     */
    public void setMaxDeadSecond(int maxDeadSecond)
    {
        this._maxDeadSecond = maxDeadSecond;
        this._has_maxDeadSecond = true;
    } //-- void setMaxDeadSecond(int) 

    /**
     * Sets the value of field 'pulseSecond'.
     * 
     * @param pulseSecond the value of field 'pulseSecond'.
     */
    public void setPulseSecond(int pulseSecond)
    {
        this._pulseSecond = pulseSecond;
        this._has_pulseSecond = true;
    } //-- void setPulseSecond(int) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return ClusterController
     */
    public static com.cdoframework.cdolib.servicebus.schema.ClusterController unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.cdoframework.cdolib.servicebus.schema.ClusterController) Unmarshaller.unmarshal(com.cdoframework.cdolib.servicebus.schema.ClusterController.class, reader);
    } //-- com.cdoframework.cdolib.servicebus.schema.ClusterController unmarshal(java.io.Reader) 

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
