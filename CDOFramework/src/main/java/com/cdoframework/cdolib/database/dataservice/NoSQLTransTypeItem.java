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

import java.io.Serializable;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class NoSQLTransTypeItem.
 * 
 * @version $Revision$ $Date$
 */
public class NoSQLTransTypeItem implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _noSQLTransTypeChoice
     */
    private com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoice _noSQLTransTypeChoice;

    /**
     * Field _onException
     */
    private com.cdoframework.cdolib.database.dataservice.OnException _onException;

    /**
     * Field _return
     */
    private com.cdoframework.cdolib.database.dataservice.Return _return;


      //----------------/
     //- Constructors -/
    //----------------/

    public NoSQLTransTypeItem() 
     {
        super();
    } //-- com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeItem()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'noSQLTransTypeChoice'.
     * 
     * @return NoSQLTransTypeChoice
     * @return the value of field 'noSQLTransTypeChoice'.
     */
    public com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoice getNoSQLTransTypeChoice()
    {
        return this._noSQLTransTypeChoice;
    } //-- com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoice getNoSQLTransTypeChoice() 

    /**
     * Returns the value of field 'onException'.
     * 
     * @return OnException
     * @return the value of field 'onException'.
     */
    public com.cdoframework.cdolib.database.dataservice.OnException getOnException()
    {
        return this._onException;
    } //-- com.cdoframework.cdolib.database.dataservice.OnException getOnException() 

    /**
     * Returns the value of field 'return'.
     * 
     * @return Return
     * @return the value of field 'return'.
     */
    public com.cdoframework.cdolib.database.dataservice.Return getReturn()
    {
        return this._return;
    } //-- com.cdoframework.cdolib.database.dataservice.Return getReturn() 

    /**
     * Sets the value of field 'noSQLTransTypeChoice'.
     * 
     * @param noSQLTransTypeChoice the value of field
     * 'noSQLTransTypeChoice'.
     */
    public void setNoSQLTransTypeChoice(com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoice noSQLTransTypeChoice)
    {
        this._noSQLTransTypeChoice = noSQLTransTypeChoice;
    } //-- void setNoSQLTransTypeChoice(com.cdoframework.cdolib.database.dataservice.NoSQLTransTypeChoice) 

    /**
     * Sets the value of field 'onException'.
     * 
     * @param onException the value of field 'onException'.
     */
    public void setOnException(com.cdoframework.cdolib.database.dataservice.OnException onException)
    {
        this._onException = onException;
    } //-- void setOnException(com.cdoframework.cdolib.database.dataservice.OnException) 

    /**
     * Sets the value of field 'return'.
     * 
     * @param _return
     * @param return the value of field 'return'.
     */
    public void setReturn(com.cdoframework.cdolib.database.dataservice.Return _return)
    {
        this._return = _return;
    } //-- void setReturn(com.cdoframework.cdolib.database.dataservice.Return) 

}
