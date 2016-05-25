/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.3</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.database.xsd.types;

/**
 * Enumeration SQLIfTypeType.
 * 
 * @version $Revision$ $Date$
 */
public enum SQLIfTypeType {


      //------------------/
     //- Enum Constants -/
    //------------------/

    /**
     * Constant BOOLEAN
     */
    BOOLEAN("Boolean"),
    /**
     * Constant BYTE
     */
    BYTE("Byte"),
    /**
     * Constant SHORT
     */
    SHORT("Short"),
    /**
     * Constant INTEGER
     */
    INTEGER("Integer"),
    /**
     * Constant LONG
     */
    LONG("Long"),
    /**
     * Constant FLOAT
     */
    FLOAT("Float"),
    /**
     * Constant DOUBLE
     */
    DOUBLE("Double"),
    /**
     * Constant STRING
     */
    STRING("String"),
    /**
     * Constant DATE
     */
    DATE("Date"),
    /**
     * Constant TIME
     */
    TIME("Time"),
    /**
     * Constant DATETIME
     */
    DATETIME("DateTime");
    /**
     * Field value.
     */
    private final java.lang.String value;

    /**
     * Field enumConstants.
     */
    private static final java.util.Map<java.lang.String, SQLIfTypeType> enumConstants = new java.util.HashMap<java.lang.String, SQLIfTypeType>();


    static {
        for (SQLIfTypeType c: SQLIfTypeType.values()) {
            SQLIfTypeType.enumConstants.put(c.value, c);
        }

    }

    private SQLIfTypeType(final java.lang.String value) {
        this.value = value;
    }

    /**
     * Method fromValue.
     * 
     * @param value
     * @return the constant for this value
     */
    public static com.cdoframework.cdolib.database.xsd.types.SQLIfTypeType fromValue(final java.lang.String value) {
        SQLIfTypeType c = SQLIfTypeType.enumConstants.get(value);
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
