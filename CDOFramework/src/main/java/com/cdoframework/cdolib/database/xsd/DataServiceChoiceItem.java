/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.3</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.database.xsd;

/**
 * 
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class DataServiceChoiceItem implements java.io.Serializable {

    /**
     * Field SQLTrans.
     */
    private com.cdoframework.cdolib.database.xsd.SQLTrans SQLTrans;

    /**
     * Field noSQLTrans.
     */
    private com.cdoframework.cdolib.database.xsd.NoSQLTrans noSQLTrans;

    public DataServiceChoiceItem() {
        super();
    }

    /**
     * Returns the value of field 'noSQLTrans'.
     * 
     * @return the value of field 'NoSQLTrans'.
     */
    public com.cdoframework.cdolib.database.xsd.NoSQLTrans getNoSQLTrans() {
        return this.noSQLTrans;
    }

    /**
     * Returns the value of field 'SQLTrans'.
     * 
     * @return the value of field 'SQLTrans'.
     */
    public com.cdoframework.cdolib.database.xsd.SQLTrans getSQLTrans() {
        return this.SQLTrans;
    }

    /**
     * Sets the value of field 'noSQLTrans'.
     * 
     * @param noSQLTrans the value of field 'noSQLTrans'.
     */
    public void setNoSQLTrans(final com.cdoframework.cdolib.database.xsd.NoSQLTrans noSQLTrans) {
        this.noSQLTrans = noSQLTrans;
    }

    /**
     * Sets the value of field 'SQLTrans'.
     * 
     * @param SQLTrans the value of field 'SQLTrans'.
     */
    public void setSQLTrans(final com.cdoframework.cdolib.database.xsd.SQLTrans SQLTrans) {
        this.SQLTrans = SQLTrans;
    }

}
