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

import java.io.Serializable;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class CreteriasTypeItem.
 * 
 * @version $Revision$ $Date$
 */
public class CreteriasTypeItem implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Internal choice value storage
     */
    private java.lang.Object _choiceValue;

    /**
     * Field _creteria
     */
    private com.cdoframework.cdolib.servicebus.schema.Creteria _creteria;

    /**
     * Field _creterias
     */
    private com.cdoframework.cdolib.servicebus.schema.Creterias _creterias;


      //----------------/
     //- Constructors -/
    //----------------/

    public CreteriasTypeItem() 
     {
        super();
    } //-- com.cdoframework.cdolib.servicebus.schema.CreteriasTypeItem()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'choiceValue'. The field
     * 'choiceValue' has the following description: Internal choice
     * value storage
     * 
     * @return Object
     * @return the value of field 'choiceValue'.
     */
    public java.lang.Object getChoiceValue()
    {
        return this._choiceValue;
    } //-- java.lang.Object getChoiceValue() 

    /**
     * Returns the value of field 'creteria'.
     * 
     * @return Creteria
     * @return the value of field 'creteria'.
     */
    public com.cdoframework.cdolib.servicebus.schema.Creteria getCreteria()
    {
        return this._creteria;
    } //-- com.cdoframework.cdolib.servicebus.schema.Creteria getCreteria() 

    /**
     * Returns the value of field 'creterias'.
     * 
     * @return Creterias
     * @return the value of field 'creterias'.
     */
    public com.cdoframework.cdolib.servicebus.schema.Creterias getCreterias()
    {
        return this._creterias;
    } //-- com.cdoframework.cdolib.servicebus.schema.Creterias getCreterias() 

    /**
     * Sets the value of field 'creteria'.
     * 
     * @param creteria the value of field 'creteria'.
     */
    public void setCreteria(com.cdoframework.cdolib.servicebus.schema.Creteria creteria)
    {
        this._creteria = creteria;
        this._choiceValue = creteria;
    } //-- void setCreteria(com.cdoframework.cdolib.servicebus.schema.Creteria) 

    /**
     * Sets the value of field 'creterias'.
     * 
     * @param creterias the value of field 'creterias'.
     */
    public void setCreterias(com.cdoframework.cdolib.servicebus.schema.Creterias creterias)
    {
        this._creterias = creterias;
        this._choiceValue = creterias;
    } //-- void setCreterias(com.cdoframework.cdolib.servicebus.schema.Creterias) 

}
