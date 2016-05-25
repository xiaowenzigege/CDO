/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.3</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.service.framework.transfilter.xsd.types;

/**
 * Enumeration RequestKeyScopeType.
 * 
 * @version $Revision$ $Date$
 */
public enum RequestKeyScopeType {


      //------------------/
     //- Enum Constants -/
    //------------------/

    /**
     * Constant REQUEST
     */
    REQUEST("Request"),
    /**
     * Constant RESPONSE
     */
    RESPONSE("Response");
    /**
     * Field value.
     */
    private final java.lang.String value;

    /**
     * Field enumConstants.
     */
    private static final java.util.Map<java.lang.String, RequestKeyScopeType> enumConstants = new java.util.HashMap<java.lang.String, RequestKeyScopeType>();


    static {
        for (RequestKeyScopeType c: RequestKeyScopeType.values()) {
            RequestKeyScopeType.enumConstants.put(c.value, c);
        }

    }

    private RequestKeyScopeType(final java.lang.String value) {
        this.value = value;
    }

    /**
     * Method fromValue.
     * 
     * @param value
     * @return the constant for this value
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.xsd.types.RequestKeyScopeType fromValue(final java.lang.String value) {
        RequestKeyScopeType c = RequestKeyScopeType.enumConstants.get(value);
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
