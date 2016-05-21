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
 * Class DataServiceItem.
 * 
 * @version $Revision$ $Date$
 */
public class DataServiceItem implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Internal choice value storage
     */
    private java.lang.Object _choiceValue;

    /**
     * Field _bigTableGroup
     */
    private com.cdoframework.cdolib.database.dataservice.BigTableGroup _bigTableGroup;

    /**
     * Field _SQLTrans
     */
    private com.cdoframework.cdolib.database.dataservice.SQLTrans _SQLTrans;

    /**
     * Field _noSQLTrans
     */
    private com.cdoframework.cdolib.database.dataservice.NoSQLTrans _noSQLTrans;


      //----------------/
     //- Constructors -/
    //----------------/

    public DataServiceItem() 
     {
        super();
    } //-- com.cdoframework.cdolib.database.dataservice.DataServiceItem()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'bigTableGroup'.
     * 
     * @return BigTableGroup
     * @return the value of field 'bigTableGroup'.
     */
    public com.cdoframework.cdolib.database.dataservice.BigTableGroup getBigTableGroup()
    {
        return this._bigTableGroup;
    } //-- com.cdoframework.cdolib.database.dataservice.BigTableGroup getBigTableGroup() 

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
     * Returns the value of field 'noSQLTrans'.
     * 
     * @return NoSQLTrans
     * @return the value of field 'noSQLTrans'.
     */
    public com.cdoframework.cdolib.database.dataservice.NoSQLTrans getNoSQLTrans()
    {
        return this._noSQLTrans;
    } //-- com.cdoframework.cdolib.database.dataservice.NoSQLTrans getNoSQLTrans() 

    /**
     * Returns the value of field 'SQLTrans'.
     * 
     * @return SQLTrans
     * @return the value of field 'SQLTrans'.
     */
    public com.cdoframework.cdolib.database.dataservice.SQLTrans getSQLTrans()
    {
        return this._SQLTrans;
    } //-- com.cdoframework.cdolib.database.dataservice.SQLTrans getSQLTrans() 

    /**
     * Sets the value of field 'bigTableGroup'.
     * 
     * @param bigTableGroup the value of field 'bigTableGroup'.
     */
    public void setBigTableGroup(com.cdoframework.cdolib.database.dataservice.BigTableGroup bigTableGroup)
    {
        this._bigTableGroup = bigTableGroup;
        this._choiceValue = bigTableGroup;
    } //-- void setBigTableGroup(com.cdoframework.cdolib.database.dataservice.BigTableGroup) 

    /**
     * Sets the value of field 'noSQLTrans'.
     * 
     * @param noSQLTrans the value of field 'noSQLTrans'.
     */
    public void setNoSQLTrans(com.cdoframework.cdolib.database.dataservice.NoSQLTrans noSQLTrans)
    {
        this._noSQLTrans = noSQLTrans;
        this._choiceValue = noSQLTrans;
    } //-- void setNoSQLTrans(com.cdoframework.cdolib.database.dataservice.NoSQLTrans) 

    /**
     * Sets the value of field 'SQLTrans'.
     * 
     * @param SQLTrans the value of field 'SQLTrans'.
     */
    public void setSQLTrans(com.cdoframework.cdolib.database.dataservice.SQLTrans SQLTrans)
    {
        this._SQLTrans = SQLTrans;
        this._choiceValue = SQLTrans;
    } //-- void setSQLTrans(com.cdoframework.cdolib.database.dataservice.SQLTrans) 

}
