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
 * Class NoSQLTransTypeItemTypeType.
 * 
 * @version $Revision$ $Date$
 */
public class NoSQLTransTypeItemTypeType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The Byte type
     */
    public static final int BYTE_TYPE = 0;

    /**
     * The instance of the Byte type
     */
    public static final NoSQLTransTypeItemTypeType BYTE = new NoSQLTransTypeItemTypeType(BYTE_TYPE, "Byte");

    /**
     * The Short type
     */
    public static final int SHORT_TYPE = 1;

    /**
     * The instance of the Short type
     */
    public static final NoSQLTransTypeItemTypeType SHORT = new NoSQLTransTypeItemTypeType(SHORT_TYPE, "Short");

    /**
     * The Integer type
     */
    public static final int INTEGER_TYPE = 2;

    /**
     * The instance of the Integer type
     */
    public static final NoSQLTransTypeItemTypeType INTEGER = new NoSQLTransTypeItemTypeType(INTEGER_TYPE, "Integer");

    /**
     * The Long type
     */
    public static final int LONG_TYPE = 3;

    /**
     * The instance of the Long type
     */
    public static final NoSQLTransTypeItemTypeType LONG = new NoSQLTransTypeItemTypeType(LONG_TYPE, "Long");

    /**
     * The Float type
     */
    public static final int FLOAT_TYPE = 4;

    /**
     * The instance of the Float type
     */
    public static final NoSQLTransTypeItemTypeType FLOAT = new NoSQLTransTypeItemTypeType(FLOAT_TYPE, "Float");

    /**
     * The Double type
     */
    public static final int DOUBLE_TYPE = 5;

    /**
     * The instance of the Double type
     */
    public static final NoSQLTransTypeItemTypeType DOUBLE = new NoSQLTransTypeItemTypeType(DOUBLE_TYPE, "Double");

    /**
     * The String type
     */
    public static final int STRING_TYPE = 6;

    /**
     * The instance of the String type
     */
    public static final NoSQLTransTypeItemTypeType STRING = new NoSQLTransTypeItemTypeType(STRING_TYPE, "String");

    /**
     * The Date type
     */
    public static final int DATE_TYPE = 7;

    /**
     * The instance of the Date type
     */
    public static final NoSQLTransTypeItemTypeType DATE = new NoSQLTransTypeItemTypeType(DATE_TYPE, "Date");

    /**
     * The Time type
     */
    public static final int TIME_TYPE = 8;

    /**
     * The instance of the Time type
     */
    public static final NoSQLTransTypeItemTypeType TIME = new NoSQLTransTypeItemTypeType(TIME_TYPE, "Time");

    /**
     * The DateTime type
     */
    public static final int DATETIME_TYPE = 9;

    /**
     * The instance of the DateTime type
     */
    public static final NoSQLTransTypeItemTypeType DATETIME = new NoSQLTransTypeItemTypeType(DATETIME_TYPE, "DateTime");

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

    private NoSQLTransTypeItemTypeType(int type, java.lang.String value) 
     {
        super();
        this.type = type;
        this.stringValue = value;
    } //-- com.cdoframework.cdolib.database.dataservice.types.NoSQLTransTypeItemTypeType(int, java.lang.String)


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate
     * 
     * Returns an enumeration of all possible instances of
     * NoSQLTransTypeItemTypeType
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
     * Returns the type of this NoSQLTransTypeItemTypeType
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
     * Returns the String representation of this
     * NoSQLTransTypeItemTypeType
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
     * Returns a new NoSQLTransTypeItemTypeType based on the given
     * String value.
     * 
     * @param string
     * @return NoSQLTransTypeItemTypeType
     */
    public static com.cdoframework.cdolib.database.dataservice.types.NoSQLTransTypeItemTypeType valueOf(java.lang.String string)
    {
        java.lang.Object obj = null;
        if (string != null) obj = _memberTable.get(string);
        if (obj == null) {
            String err = "'" + string + "' is not a valid NoSQLTransTypeItemTypeType";
            throw new IllegalArgumentException(err);
        }
        return (NoSQLTransTypeItemTypeType) obj;
    } //-- com.cdoframework.cdolib.database.dataservice.types.NoSQLTransTypeItemTypeType valueOf(java.lang.String) 

}
