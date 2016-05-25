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
public class CreteriasTypeItem implements java.io.Serializable {

    /**
     * Internal choice value storage
     */
    private java.lang.Object _choiceValue;

    /**
     * Field creteria.
     */
    private com.cdoframework.cdolib.database.xsd.Creteria creteria;

    /**
     * Field creterias.
     */
    private com.cdoframework.cdolib.database.xsd.Creterias creterias;

    public CreteriasTypeItem() {
        super();
    }

    /**
     * Returns the value of field 'choiceValue'. The field
     * 'choiceValue' has the following description: Internal choice
     * value storage
     * 
     * @return the value of field 'ChoiceValue'.
     */
    public java.lang.Object getChoiceValue() {
        return this._choiceValue;
    }

    /**
     * Returns the value of field 'creteria'.
     * 
     * @return the value of field 'Creteria'.
     */
    public com.cdoframework.cdolib.database.xsd.Creteria getCreteria() {
        return this.creteria;
    }

    /**
     * Returns the value of field 'creterias'.
     * 
     * @return the value of field 'Creterias'.
     */
    public com.cdoframework.cdolib.database.xsd.Creterias getCreterias() {
        return this.creterias;
    }

    /**
     * Sets the value of field 'creteria'.
     * 
     * @param creteria the value of field 'creteria'.
     */
    public void setCreteria(final com.cdoframework.cdolib.database.xsd.Creteria creteria) {
        this.creteria = creteria;
        this._choiceValue = creteria;
    }

    /**
     * Sets the value of field 'creterias'.
     * 
     * @param creterias the value of field 'creterias'.
     */
    public void setCreterias(final com.cdoframework.cdolib.database.xsd.Creterias creterias) {
        this.creterias = creterias;
        this._choiceValue = creterias;
    }

}
