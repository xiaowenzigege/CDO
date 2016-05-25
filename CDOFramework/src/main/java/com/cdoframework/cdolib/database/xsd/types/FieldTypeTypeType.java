/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.3</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.database.xsd.types;

/**
 * Enumeration FieldTypeTypeType.
 * 
 * @version $Revision$ $Date$
 */
public enum FieldTypeTypeType {


      //------------------/
     //- Enum Constants -/
    //------------------/

    /**
     * Constant BOOLEAN
     */
    BOOLEAN("Boolean"),
    /**
     * Constant BOOLEANARRAY
     */
    BOOLEANARRAY("BooleanArray"),
    /**
     * Constant BYTE
     */
    BYTE("Byte"),
    /**
     * Constant BYTEARRAY
     */
    BYTEARRAY("ByteArray"),
    /**
     * Constant SHORT
     */
    SHORT("Short"),
    /**
     * Constant SHORTARRAY
     */
    SHORTARRAY("ShortArray"),
    /**
     * Constant INTEGER
     */
    INTEGER("Integer"),
    /**
     * Constant INTEGERARRAY
     */
    INTEGERARRAY("IntegerArray"),
    /**
     * Constant LONG
     */
    LONG("Long"),
    /**
     * Constant LONGARRAY
     */
    LONGARRAY("LongArray"),
    /**
     * Constant FLOAT
     */
    FLOAT("Float"),
    /**
     * Constant FLOATARRAY
     */
    FLOATARRAY("FloatArray"),
    /**
     * Constant DOUBLE
     */
    DOUBLE("Double"),
    /**
     * Constant DOUBLEARRAY
     */
    DOUBLEARRAY("DoubleArray"),
    /**
     * Constant STRING
     */
    STRING("String"),
    /**
     * Constant STRINGARRAY
     */
    STRINGARRAY("StringArray"),
    /**
     * Constant DATE
     */
    DATE("Date"),
    /**
     * Constant DATEARRAY
     */
    DATEARRAY("DateArray"),
    /**
     * Constant TIME
     */
    TIME("Time"),
    /**
     * Constant TIMEARRAY
     */
    TIMEARRAY("TimeArray"),
    /**
     * Constant DATETIME
     */
    DATETIME("DateTime"),
    /**
     * Constant DATETIMEARRAY
     */
    DATETIMEARRAY("DateTimeArray"),
    /**
     * Constant CDO
     */
    CDO("CDO"),
    /**
     * Constant CDOARRAY
     */
    CDOARRAY("CDOArray");
    /**
     * Field value.
     */
    private final java.lang.String value;

    /**
     * Field enumConstants.
     */
    private static final java.util.Map<java.lang.String, FieldTypeTypeType> enumConstants = new java.util.HashMap<java.lang.String, FieldTypeTypeType>();


    static {
        for (FieldTypeTypeType c: FieldTypeTypeType.values()) {
            FieldTypeTypeType.enumConstants.put(c.value, c);
        }

    }

    private FieldTypeTypeType(final java.lang.String value) {
        this.value = value;
    }

    /**
     * Method fromValue.
     * 
     * @param value
     * @return the constant for this value
     */
    public static com.cdoframework.cdolib.database.xsd.types.FieldTypeTypeType fromValue(final java.lang.String value) {
        FieldTypeTypeType c = FieldTypeTypeType.enumConstants.get(value);
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
