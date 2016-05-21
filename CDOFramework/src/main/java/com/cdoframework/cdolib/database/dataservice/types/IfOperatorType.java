/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.database.dataservice.types;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class IfOperatorType.
 * 
 * @version $Revision$ $Date$
 */
public class IfOperatorType implements java.io.Serializable {


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
    public static final IfOperatorType VALUE_0 = new IfOperatorType(VALUE_0_TYPE, "=");

    /**
     * The != type
     */
    public static final int VALUE_1_TYPE = 1;

    /**
     * The instance of the != type
     */
    public static final IfOperatorType VALUE_1 = new IfOperatorType(VALUE_1_TYPE, "!=");

    /**
     * The > type
     */
    public static final int VALUE_2_TYPE = 2;

    /**
     * The instance of the > type
     */
    public static final IfOperatorType VALUE_2 = new IfOperatorType(VALUE_2_TYPE, ">");

    /**
     * The >= type
     */
    public static final int VALUE_3_TYPE = 3;

    /**
     * The instance of the >= type
     */
    public static final IfOperatorType VALUE_3 = new IfOperatorType(VALUE_3_TYPE, ">=");

    /**
     * The < type
     */
    public static final int VALUE_4_TYPE = 4;

    /**
     * The instance of the < type
     */
    public static final IfOperatorType VALUE_4 = new IfOperatorType(VALUE_4_TYPE, "<");

    /**
     * The <= type
     */
    public static final int VALUE_5_TYPE = 5;

    /**
     * The instance of the <= type
     */
    public static final IfOperatorType VALUE_5 = new IfOperatorType(VALUE_5_TYPE, "<=");

    /**
     * The IS type
     */
    public static final int VALUE_6_TYPE = 6;

    /**
     * The instance of the IS type
     */
    public static final IfOperatorType VALUE_6 = new IfOperatorType(VALUE_6_TYPE, "IS");

    /**
     * The ISNOT type
     */
    public static final int VALUE_7_TYPE = 7;

    /**
     * The instance of the ISNOT type
     */
    public static final IfOperatorType VALUE_7 = new IfOperatorType(VALUE_7_TYPE, "ISNOT");

    /**
     * The MATCH type
     */
    public static final int VALUE_8_TYPE = 8;

    /**
     * The instance of the MATCH type
     */
    public static final IfOperatorType VALUE_8 = new IfOperatorType(VALUE_8_TYPE, "MATCH");

    /**
     * The NOTMATCH type
     */
    public static final int VALUE_9_TYPE = 9;

    /**
     * The instance of the NOTMATCH type
     */
    public static final IfOperatorType VALUE_9 = new IfOperatorType(VALUE_9_TYPE, "NOTMATCH");

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

    private IfOperatorType(int type, java.lang.String value) 
     {
        super();
        this.type = type;
        this.stringValue = value;
    } //-- com.cdoframework.cdolib.database.dataservice.types.IfOperatorType(int, java.lang.String)


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate
     * 
     * Returns an enumeration of all possible instances of
     * IfOperatorType
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
     * Returns the type of this IfOperatorType
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
        members.put("IS", VALUE_6);
        members.put("ISNOT", VALUE_7);
        members.put("MATCH", VALUE_8);
        members.put("NOTMATCH", VALUE_9);
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
     * Returns the String representation of this IfOperatorType
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
     * Returns a new IfOperatorType based on the given String
     * value.
     * 
     * @param string
     * @return IfOperatorType
     */
    public static com.cdoframework.cdolib.database.dataservice.types.IfOperatorType valueOf(java.lang.String string)
    {
        java.lang.Object obj = null;
        if (string != null) obj = _memberTable.get(string);
        if (obj == null) {
            String err = "'" + string + "' is not a valid IfOperatorType";
            throw new IllegalArgumentException(err);
        }
        return (IfOperatorType) obj;
    } //-- com.cdoframework.cdolib.database.dataservice.types.IfOperatorType valueOf(java.lang.String) 

}
