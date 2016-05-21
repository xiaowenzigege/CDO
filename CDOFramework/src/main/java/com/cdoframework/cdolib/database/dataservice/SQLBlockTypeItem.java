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
 * Class SQLBlockTypeItem.
 * 
 * @version $Revision$ $Date$
 */
public class SQLBlockTypeItem implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Internal choice value storage
     */
    private java.lang.Object _choiceValue;

    /**
     * Field _outputSQL
     */
    private java.lang.String _outputSQL;

    /**
     * Field _outputField
     */
    private java.lang.String _outputField;

    /**
     * Field _SQLIf
     */
    private com.cdoframework.cdolib.database.dataservice.SQLIf _SQLIf;

    /**
     * Field _SQLFor
     */
    private com.cdoframework.cdolib.database.dataservice.SQLFor _SQLFor;


      //----------------/
     //- Constructors -/
    //----------------/

    public SQLBlockTypeItem() 
     {
        super();
    } //-- com.cdoframework.cdolib.database.dataservice.SQLBlockTypeItem()


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
     * Returns the value of field 'outputField'.
     * 
     * @return String
     * @return the value of field 'outputField'.
     */
    public java.lang.String getOutputField()
    {
        return this._outputField;
    } //-- java.lang.String getOutputField() 

    /**
     * Returns the value of field 'outputSQL'.
     * 
     * @return String
     * @return the value of field 'outputSQL'.
     */
    public java.lang.String getOutputSQL()
    {
        return this._outputSQL;
    } //-- java.lang.String getOutputSQL() 

    /**
     * Returns the value of field 'SQLFor'.
     * 
     * @return SQLFor
     * @return the value of field 'SQLFor'.
     */
    public com.cdoframework.cdolib.database.dataservice.SQLFor getSQLFor()
    {
        return this._SQLFor;
    } //-- com.cdoframework.cdolib.database.dataservice.SQLFor getSQLFor() 

    /**
     * Returns the value of field 'SQLIf'.
     * 
     * @return SQLIf
     * @return the value of field 'SQLIf'.
     */
    public com.cdoframework.cdolib.database.dataservice.SQLIf getSQLIf()
    {
        return this._SQLIf;
    } //-- com.cdoframework.cdolib.database.dataservice.SQLIf getSQLIf() 

    /**
     * Sets the value of field 'outputField'.
     * 
     * @param outputField the value of field 'outputField'.
     */
    public void setOutputField(java.lang.String outputField)
    {
        this._outputField = outputField;
        this._choiceValue = outputField;
    } //-- void setOutputField(java.lang.String) 

    /**
     * Sets the value of field 'outputSQL'.
     * 
     * @param outputSQL the value of field 'outputSQL'.
     */
    public void setOutputSQL(java.lang.String outputSQL)
    {
        this._outputSQL = outputSQL;
        this._choiceValue = outputSQL;
    } //-- void setOutputSQL(java.lang.String) 

    /**
     * Sets the value of field 'SQLFor'.
     * 
     * @param SQLFor the value of field 'SQLFor'.
     */
    public void setSQLFor(com.cdoframework.cdolib.database.dataservice.SQLFor SQLFor)
    {
        this._SQLFor = SQLFor;
        this._choiceValue = SQLFor;
    } //-- void setSQLFor(com.cdoframework.cdolib.database.dataservice.SQLFor) 

    /**
     * Sets the value of field 'SQLIf'.
     * 
     * @param SQLIf the value of field 'SQLIf'.
     */
    public void setSQLIf(com.cdoframework.cdolib.database.dataservice.SQLIf SQLIf)
    {
        this._SQLIf = SQLIf;
        this._choiceValue = SQLIf;
    } //-- void setSQLIf(com.cdoframework.cdolib.database.dataservice.SQLIf) 

}
