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
 * Class DataServiceChoiceItem.
 * 
 * @version $Revision$ $Date$
 */
public class DataServiceChoiceItem implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

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

    public DataServiceChoiceItem() 
     {
        super();
    } //-- com.cdoframework.cdolib.database.dataservice.DataServiceChoiceItem()


      //-----------/
     //- Methods -/
    //-----------/

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
     * Sets the value of field 'noSQLTrans'.
     * 
     * @param noSQLTrans the value of field 'noSQLTrans'.
     */
    public void setNoSQLTrans(com.cdoframework.cdolib.database.dataservice.NoSQLTrans noSQLTrans)
    {
        this._noSQLTrans = noSQLTrans;
    } //-- void setNoSQLTrans(com.cdoframework.cdolib.database.dataservice.NoSQLTrans) 

    /**
     * Sets the value of field 'SQLTrans'.
     * 
     * @param SQLTrans the value of field 'SQLTrans'.
     */
    public void setSQLTrans(com.cdoframework.cdolib.database.dataservice.SQLTrans SQLTrans)
    {
        this._SQLTrans = SQLTrans;
    } //-- void setSQLTrans(com.cdoframework.cdolib.database.dataservice.SQLTrans) 

}
