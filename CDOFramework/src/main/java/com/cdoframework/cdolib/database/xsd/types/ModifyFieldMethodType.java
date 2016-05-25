/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.3</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.database.xsd.types;

/**
 * Enumeration ModifyFieldMethodType.
 * 
 * @version $Revision$ $Date$
 */
public enum ModifyFieldMethodType {


      //------------------/
     //- Enum Constants -/
    //------------------/

    /**
     * Constant INC
     */
    INC("inc"),
    /**
     * Constant SET
     */
    SET("set"),
    /**
     * Constant UNSET
     */
    UNSET("unset"),
    /**
     * Constant PUSH
     */
    PUSH("push"),
    /**
     * Constant PUSHALL
     */
    PUSHALL("pushAll"),
    /**
     * Constant ADDTOSET
     */
    ADDTOSET("addToSet"),
    /**
     * Constant POP
     */
    POP("pop"),
    /**
     * Constant PULL
     */
    PULL("pull"),
    /**
     * Constant PULLALL
     */
    PULLALL("pullAll"),
    /**
     * Constant SETNULL
     */
    SETNULL("setnull");
    /**
     * Field value.
     */
    private final java.lang.String value;

    /**
     * Field enumConstants.
     */
    private static final java.util.Map<java.lang.String, ModifyFieldMethodType> enumConstants = new java.util.HashMap<java.lang.String, ModifyFieldMethodType>();


    static {
        for (ModifyFieldMethodType c: ModifyFieldMethodType.values()) {
            ModifyFieldMethodType.enumConstants.put(c.value, c);
        }

    }

    private ModifyFieldMethodType(final java.lang.String value) {
        this.value = value;
    }

    /**
     * Method fromValue.
     * 
     * @param value
     * @return the constant for this value
     */
    public static com.cdoframework.cdolib.database.xsd.types.ModifyFieldMethodType fromValue(final java.lang.String value) {
        ModifyFieldMethodType c = ModifyFieldMethodType.enumConstants.get(value);
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
