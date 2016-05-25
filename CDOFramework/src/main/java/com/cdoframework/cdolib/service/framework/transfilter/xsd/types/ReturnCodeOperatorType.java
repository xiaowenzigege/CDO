/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.3</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.service.framework.transfilter.xsd.types;

/**
 * Enumeration ReturnCodeOperatorType.
 * 
 * @version $Revision$ $Date$
 */
public enum ReturnCodeOperatorType {


      //------------------/
     //- Enum Constants -/
    //------------------/

    /**
     * Constant VALUE_0
     */
    VALUE_0("="),
    /**
     * Constant VALUE_1
     */
    VALUE_1("!="),
    /**
     * Constant VALUE_2
     */
    VALUE_2(">"),
    /**
     * Constant VALUE_3
     */
    VALUE_3(">="),
    /**
     * Constant VALUE_4
     */
    VALUE_4("<"),
    /**
     * Constant VALUE_5
     */
    VALUE_5("<=");
    /**
     * Field value.
     */
    private final java.lang.String value;

    /**
     * Field enumConstants.
     */
    private static final java.util.Map<java.lang.String, ReturnCodeOperatorType> enumConstants = new java.util.HashMap<java.lang.String, ReturnCodeOperatorType>();


    static {
        for (ReturnCodeOperatorType c: ReturnCodeOperatorType.values()) {
            ReturnCodeOperatorType.enumConstants.put(c.value, c);
        }

    }

    private ReturnCodeOperatorType(final java.lang.String value) {
        this.value = value;
    }

    /**
     * Method fromValue.
     * 
     * @param value
     * @return the constant for this value
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.xsd.types.ReturnCodeOperatorType fromValue(final java.lang.String value) {
        ReturnCodeOperatorType c = ReturnCodeOperatorType.enumConstants.get(value);
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
