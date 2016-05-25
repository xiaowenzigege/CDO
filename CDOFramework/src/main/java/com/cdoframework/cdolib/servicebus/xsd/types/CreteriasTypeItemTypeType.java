/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.3</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.servicebus.xsd.types;

/**
 * Enumeration CreteriasTypeItemTypeType.
 * 
 * @version $Revision$ $Date$
 */
public enum CreteriasTypeItemTypeType {


      //------------------/
     //- Enum Constants -/
    //------------------/

    /**
     * Constant AND
     */
    AND("AND"),
    /**
     * Constant OR
     */
    OR("OR");
    /**
     * Field value.
     */
    private final java.lang.String value;

    /**
     * Field enumConstants.
     */
    private static final java.util.Map<java.lang.String, CreteriasTypeItemTypeType> enumConstants = new java.util.HashMap<java.lang.String, CreteriasTypeItemTypeType>();


    static {
        for (CreteriasTypeItemTypeType c: CreteriasTypeItemTypeType.values()) {
            CreteriasTypeItemTypeType.enumConstants.put(c.value, c);
        }

    }

    private CreteriasTypeItemTypeType(final java.lang.String value) {
        this.value = value;
    }

    /**
     * Method fromValue.
     * 
     * @param value
     * @return the constant for this value
     */
    public static com.cdoframework.cdolib.servicebus.xsd.types.CreteriasTypeItemTypeType fromValue(final java.lang.String value) {
        CreteriasTypeItemTypeType c = CreteriasTypeItemTypeType.enumConstants.get(value);
        if (c != null) {
            return c;
        }
        throw new IllegalArgumentException(value);
    }

    /**
     * 
     * 
     * @param value
     */
    public void setValue(final java.lang.String value) {
    }

    /**
     * Method toString.
     * 
     * @return the value of this constant
     */
    public java.lang.String toString() {
        return this.value;
    }

    /**
     * Method value.
     * 
     * @return the value of this constant
     */
    public java.lang.String value() {
        return this.value;
    }

}
