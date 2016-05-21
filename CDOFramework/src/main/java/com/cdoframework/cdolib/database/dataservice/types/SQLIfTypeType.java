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
 * Class SQLIfTypeType.
 * 
 * @version $Revision$ $Date$
 */
public class SQLIfTypeType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The Boolean type
     */
    public static final int BOOLEAN_TYPE = 0;

    /**
     * The instance of the Boolean type
     */
    public static final SQLIfTypeType BOOLEAN = new SQLIfTypeType(BOOLEAN_TYPE, "Boolean");

    /**
     * The Byte type
     */
    public static final int BYTE_TYPE = 1;

    /**
     * The instance of the Byte type
     */
    public static final SQLIfTypeType BYTE = new SQLIfTypeType(BYTE_TYPE, "Byte");

    /**
     * The Short type
     */
    public static final int SHORT_TYPE = 2;

    /**
     * The instance of the Short type
     */
    public static final SQLIfTypeType SHORT = new SQLIfTypeType(SHORT_TYPE, "Short");

    /**
     * The Integer type
     */
    public static final int INTEGER_TYPE = 3;

    /**
     * The instance of the Integer type
     */
    public static final SQLIfTypeType INTEGER = new SQLIfTypeType(INTEGER_TYPE, "Integer");

    /**
     * The Long type
     */
    public static final int LONG_TYPE = 4;

    /**
     * The instance of the Long type
     */
    public static final SQLIfTypeType LONG = new SQLIfTypeType(LONG_TYPE, "Long");

    /**
     * The Float type
     */
    public static final int FLOAT_TYPE = 5;

    /**
     * The instance of the Float type
     */
    public static final SQLIfTypeType FLOAT = new SQLIfTypeType(FLOAT_TYPE, "Float");

    /**
     * The Double type
     */
    public static final int DOUBLE_TYPE = 6;

    /**
     * The instance of the Double type
     */
    public static final SQLIfTypeType DOUBLE = new SQLIfTypeType(DOUBLE_TYPE, "Double");

    /**
     * The String type
     */
    public static final int STRING_TYPE = 7;

    /**
     * The instance of the String type
     */
    public static final SQLIfTypeType STRING = new SQLIfTypeType(STRING_TYPE, "String");

    /**
     * The Date type
     */
    public static final int DATE_TYPE = 8;

    /**
     * The instance of the Date type
     */
    public static final SQLIfTypeType DATE = new SQLIfTypeType(DATE_TYPE, "Date");

    /**
     * The Time type
     */
    public static final int TIME_TYPE = 9;

    /**
     * The instance of the Time type
     */
    public static final SQLIfTypeType TIME = new SQLIfTypeType(TIME_TYPE, "Time");

    /**
     * The DateTime type
     */
    public static final int DATETIME_TYPE = 10;

    /**
     * The instance of the DateTime type
     */
    public static final SQLIfTypeType DATETIME = new SQLIfTypeType(DATETIME_TYPE, "DateTime");

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

    private SQLIfTypeType(int type, java.lang.String value) 
     {
        super();
        this.type = type;
        this.stringValue = value;
    } //-- com.cdoframework.cdolib.database.dataservice.types.SQLIfTypeType(int, java.lang.String)


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate
     * 
     * Returns an enumeration of all possible instances of
     * SQLIfTypeType
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
     * Returns the type of this SQLIfTypeType
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
        members.put("Boolean", BOOLEAN);
        members.put("Byte", BYTE);
        members.put("Short", SHORT);
        members.put("Integer", INTEGER);
        members.put("Long", LONG);
        members.put("Float", FLOAT);
        members.put("Double", DOUBLE);
        members.put("String", STRING);
        members.put("Date", DATE);
        members.put("Time", TIME);
        members.put("DateTime", DATETIME);
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
     * Returns the String representation of this SQLIfTypeType
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
     * Returns a new SQLIfTypeType based on the given String value.
     * 
     * @param string
     * @return SQLIfTypeType
     */
    public static com.cdoframework.cdolib.database.dataservice.types.SQLIfTypeType valueOf(java.lang.String string)
    {
        java.lang.Object obj = null;
        if (string != null) obj = _memberTable.get(string);
        if (obj == null) {
            String err = "'" + string + "' is not a valid SQLIfTypeType";
            throw new IllegalArgumentException(err);
        }
        return (SQLIfTypeType) obj;
    } //-- com.cdoframework.cdolib.database.dataservice.types.SQLIfTypeType valueOf(java.lang.String) 

}
