/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.servicebus.schema.types;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class CriteriaTypeOperatorType.
 * 
 * @version $Revision$ $Date$
 */
public class CriteriaTypeOperatorType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The = type
     */
    public static final int VALUE_0_TYPE = 0;

    /**
     * The instance of the = type
     */
    public static final CriteriaTypeOperatorType VALUE_0 = new CriteriaTypeOperatorType(VALUE_0_TYPE, "=");

    /**
     * The != type
     */
    public static final int VALUE_1_TYPE = 1;

    /**
     * The instance of the != type
     */
    public static final CriteriaTypeOperatorType VALUE_1 = new CriteriaTypeOperatorType(VALUE_1_TYPE, "!=");

    /**
     * The > type
     */
    public static final int VALUE_2_TYPE = 2;

    /**
     * The instance of the > type
     */
    public static final CriteriaTypeOperatorType VALUE_2 = new CriteriaTypeOperatorType(VALUE_2_TYPE, ">");

    /**
     * The >= type
     */
    public static final int VALUE_3_TYPE = 3;

    /**
     * The instance of the >= type
     */
    public static final CriteriaTypeOperatorType VALUE_3 = new CriteriaTypeOperatorType(VALUE_3_TYPE, ">=");

    /**
     * The < type
     */
    public static final int VALUE_4_TYPE = 4;

    /**
     * The instance of the < type
     */
    public static final CriteriaTypeOperatorType VALUE_4 = new CriteriaTypeOperatorType(VALUE_4_TYPE, "<");

    /**
     * The <= type
     */
    public static final int VALUE_5_TYPE = 5;

    /**
     * The instance of the <= type
     */
    public static final CriteriaTypeOperatorType VALUE_5 = new CriteriaTypeOperatorType(VALUE_5_TYPE, "<=");

    /**
     * The ISNULL type
     */
    public static final int VALUE_6_TYPE = 6;

    /**
     * The instance of the ISNULL type
     */
    public static final CriteriaTypeOperatorType VALUE_6 = new CriteriaTypeOperatorType(VALUE_6_TYPE, "ISNULL");

    /**
     * The ISNOTNULL type
     */
    public static final int VALUE_7_TYPE = 7;

    /**
     * The instance of the ISNOTNULL type
     */
    public static final CriteriaTypeOperatorType VALUE_7 = new CriteriaTypeOperatorType(VALUE_7_TYPE, "ISNOTNULL");

    /**
     * The MATCH type
     */
    public static final int VALUE_8_TYPE = 8;

    /**
     * The instance of the MATCH type
     */
    public static final CriteriaTypeOperatorType VALUE_8 = new CriteriaTypeOperatorType(VALUE_8_TYPE, "MATCH");

    /**
     * The NOTMATCH type
     */
    public static final int VALUE_9_TYPE = 9;

    /**
     * The instance of the NOTMATCH type
     */
    public static final CriteriaTypeOperatorType VALUE_9 = new CriteriaTypeOperatorType(VALUE_9_TYPE, "NOTMATCH");

    /**
     * The IN type
     */
    public static final int VALUE_10_TYPE = 10;

    /**
     * The instance of the IN type
     */
    public static final CriteriaTypeOperatorType VALUE_10 = new CriteriaTypeOperatorType(VALUE_10_TYPE, "IN");

    /**
     * The NOTIN type
     */
    public static final int VALUE_11_TYPE = 11;

    /**
     * The instance of the NOTIN type
     */
    public static final CriteriaTypeOperatorType VALUE_11 = new CriteriaTypeOperatorType(VALUE_11_TYPE, "NOTIN");

    /**
     * Field _memberTable
     */
    private static java.util.Hashtable _memberTable = init();

    /**
     * Field type
     */
    private int type = -1;

    /**
     * Field stringValue
     */
    private java.lang.String stringValue = null;


      //----------------/
     //- Constructors -/
    //----------------/

    private CriteriaTypeOperatorType(int type, java.lang.String value) 
     {
        super();
        this.type = type;
        this.stringValue = value;
    } //-- com.cdoframework.cdolib.servicebus.schema.types.CriteriaTypeOperatorType(int, java.lang.String)


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate
     * 
     * Returns an enumeration of all possible instances of
     * CriteriaTypeOperatorType
     * 
     * @return Enumeration
     */
    public static java.util.Enumeration enumerate()
    {
        return _memberTable.elements();
    } //-- java.util.Enumeration enumerate() 

    /**
     * Method getType
     * 
     * Returns the type of this CriteriaTypeOperatorType
     * 
     * @return int
     */
    public int getType()
    {
        return this.type;
    } //-- int getType() 

    /**
     * Method init
     * 
     * 
     * 
     * @return Hashtable
     */
    private static java.util.Hashtable init()
    {
        Hashtable members = new Hashtable();
        members.put("=", VALUE_0);
        members.put("!=", VALUE_1);
        members.put(">", VALUE_2);
        members.put(">=", VALUE_3);
        members.put("<", VALUE_4);
        members.put("<=", VALUE_5);
        members.put("ISNULL", VALUE_6);
        members.put("ISNOTNULL", VALUE_7);
        members.put("MATCH", VALUE_8);
        members.put("NOTMATCH", VALUE_9);
        members.put("IN", VALUE_10);
        members.put("NOTIN", VALUE_11);
        return members;
    } //-- java.util.Hashtable init() 

    /**
     * Method readResolve
     * 
     *  will be called during deserialization to replace the
     * deserialized object with the correct constant instance.
     * <br/>
     * 
     * @return Object
     */
    private java.lang.Object readResolve()
    {
        return valueOf(this.stringValue);
    } //-- java.lang.Object readResolve() 

    /**
     * Method toString
     * 
     * Returns the String representation of this
     * CriteriaTypeOperatorType
     * 
     * @return String
     */
    public java.lang.String toString()
    {
        return this.stringValue;
    } //-- java.lang.String toString() 

    /**
     * Method valueOf
     * 
     * Returns a new CriteriaTypeOperatorType based on the given
     * String value.
     * 
     * @param string
     * @return CriteriaTypeOperatorType
     */
    public static com.cdoframework.cdolib.servicebus.schema.types.CriteriaTypeOperatorType valueOf(java.lang.String string)
    {
        java.lang.Object obj = null;
        if (string != null) obj = _memberTable.get(string);
        if (obj == null) {
            String err = "'" + string + "' is not a valid CriteriaTypeOperatorType";
            throw new IllegalArgumentException(err);
        }
        return (CriteriaTypeOperatorType) obj;
    } //-- com.cdoframework.cdolib.servicebus.schema.types.CriteriaTypeOperatorType valueOf(java.lang.String) 

}
