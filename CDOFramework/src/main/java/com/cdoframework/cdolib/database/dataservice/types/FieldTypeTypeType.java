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
 * Class FieldTypeTypeType.
 * 
 * @version $Revision$ $Date$
 */
public class FieldTypeTypeType implements java.io.Serializable {


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
    public static final FieldTypeTypeType BOOLEAN = new FieldTypeTypeType(BOOLEAN_TYPE, "Boolean");

    /**
     * The BooleanArray type
     */
    public static final int BOOLEANARRAY_TYPE = 1;

    /**
     * The instance of the BooleanArray type
     */
    public static final FieldTypeTypeType BOOLEANARRAY = new FieldTypeTypeType(BOOLEANARRAY_TYPE, "BooleanArray");

    /**
     * The Byte type
     */
    public static final int BYTE_TYPE = 2;

    /**
     * The instance of the Byte type
     */
    public static final FieldTypeTypeType BYTE = new FieldTypeTypeType(BYTE_TYPE, "Byte");

    /**
     * The ByteArray type
     */
    public static final int BYTEARRAY_TYPE = 3;

    /**
     * The instance of the ByteArray type
     */
    public static final FieldTypeTypeType BYTEARRAY = new FieldTypeTypeType(BYTEARRAY_TYPE, "ByteArray");

    /**
     * The Short type
     */
    public static final int SHORT_TYPE = 4;

    /**
     * The instance of the Short type
     */
    public static final FieldTypeTypeType SHORT = new FieldTypeTypeType(SHORT_TYPE, "Short");

    /**
     * The ShortArray type
     */
    public static final int SHORTARRAY_TYPE = 5;

    /**
     * The instance of the ShortArray type
     */
    public static final FieldTypeTypeType SHORTARRAY = new FieldTypeTypeType(SHORTARRAY_TYPE, "ShortArray");

    /**
     * The Integer type
     */
    public static final int INTEGER_TYPE = 6;

    /**
     * The instance of the Integer type
     */
    public static final FieldTypeTypeType INTEGER = new FieldTypeTypeType(INTEGER_TYPE, "Integer");

    /**
     * The IntegerArray type
     */
    public static final int INTEGERARRAY_TYPE = 7;

    /**
     * The instance of the IntegerArray type
     */
    public static final FieldTypeTypeType INTEGERARRAY = new FieldTypeTypeType(INTEGERARRAY_TYPE, "IntegerArray");

    /**
     * The Long type
     */
    public static final int LONG_TYPE = 8;

    /**
     * The instance of the Long type
     */
    public static final FieldTypeTypeType LONG = new FieldTypeTypeType(LONG_TYPE, "Long");

    /**
     * The LongArray type
     */
    public static final int LONGARRAY_TYPE = 9;

    /**
     * The instance of the LongArray type
     */
    public static final FieldTypeTypeType LONGARRAY = new FieldTypeTypeType(LONGARRAY_TYPE, "LongArray");

    /**
     * The Float type
     */
    public static final int FLOAT_TYPE = 10;

    /**
     * The instance of the Float type
     */
    public static final FieldTypeTypeType FLOAT = new FieldTypeTypeType(FLOAT_TYPE, "Float");

    /**
     * The FloatArray type
     */
    public static final int FLOATARRAY_TYPE = 11;

    /**
     * The instance of the FloatArray type
     */
    public static final FieldTypeTypeType FLOATARRAY = new FieldTypeTypeType(FLOATARRAY_TYPE, "FloatArray");

    /**
     * The Double type
     */
    public static final int DOUBLE_TYPE = 12;

    /**
     * The instance of the Double type
     */
    public static final FieldTypeTypeType DOUBLE = new FieldTypeTypeType(DOUBLE_TYPE, "Double");

    /**
     * The DoubleArray type
     */
    public static final int DOUBLEARRAY_TYPE = 13;

    /**
     * The instance of the DoubleArray type
     */
    public static final FieldTypeTypeType DOUBLEARRAY = new FieldTypeTypeType(DOUBLEARRAY_TYPE, "DoubleArray");

    /**
     * The String type
     */
    public static final int STRING_TYPE = 14;

    /**
     * The instance of the String type
     */
    public static final FieldTypeTypeType STRING = new FieldTypeTypeType(STRING_TYPE, "String");

    /**
     * The StringArray type
     */
    public static final int STRINGARRAY_TYPE = 15;

    /**
     * The instance of the StringArray type
     */
    public static final FieldTypeTypeType STRINGARRAY = new FieldTypeTypeType(STRINGARRAY_TYPE, "StringArray");

    /**
     * The Date type
     */
    public static final int DATE_TYPE = 16;

    /**
     * The instance of the Date type
     */
    public static final FieldTypeTypeType DATE = new FieldTypeTypeType(DATE_TYPE, "Date");

    /**
     * The DateArray type
     */
    public static final int DATEARRAY_TYPE = 17;

    /**
     * The instance of the DateArray type
     */
    public static final FieldTypeTypeType DATEARRAY = new FieldTypeTypeType(DATEARRAY_TYPE, "DateArray");

    /**
     * The Time type
     */
    public static final int TIME_TYPE = 18;

    /**
     * The instance of the Time type
     */
    public static final FieldTypeTypeType TIME = new FieldTypeTypeType(TIME_TYPE, "Time");

    /**
     * The TimeArray type
     */
    public static final int TIMEARRAY_TYPE = 19;

    /**
     * The instance of the TimeArray type
     */
    public static final FieldTypeTypeType TIMEARRAY = new FieldTypeTypeType(TIMEARRAY_TYPE, "TimeArray");

    /**
     * The DateTime type
     */
    public static final int DATETIME_TYPE = 20;

    /**
     * The instance of the DateTime type
     */
    public static final FieldTypeTypeType DATETIME = new FieldTypeTypeType(DATETIME_TYPE, "DateTime");

    /**
     * The DateTimeArray type
     */
    public static final int DATETIMEARRAY_TYPE = 21;

    /**
     * The instance of the DateTimeArray type
     */
    public static final FieldTypeTypeType DATETIMEARRAY = new FieldTypeTypeType(DATETIMEARRAY_TYPE, "DateTimeArray");

    /**
     * The CDO type
     */
    public static final int CDO_TYPE = 22;

    /**
     * The instance of the CDO type
     */
    public static final FieldTypeTypeType CDO = new FieldTypeTypeType(CDO_TYPE, "CDO");

    /**
     * The CDOArray type
     */
    public static final int CDOARRAY_TYPE = 23;

    /**
     * The instance of the CDOArray type
     */
    public static final FieldTypeTypeType CDOARRAY = new FieldTypeTypeType(CDOARRAY_TYPE, "CDOArray");

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

    private FieldTypeTypeType(int type, java.lang.String value) 
     {
        super();
        this.type = type;
        this.stringValue = value;
    } //-- com.cdoframework.cdolib.database.dataservice.types.FieldTypeTypeType(int, java.lang.String)


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate
     * 
     * Returns an enumeration of all possible instances of
     * FieldTypeTypeType
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
     * Returns the type of this FieldTypeTypeType
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
        members.put("BooleanArray", BOOLEANARRAY);
        members.put("Byte", BYTE);
        members.put("ByteArray", BYTEARRAY);
        members.put("Short", SHORT);
        members.put("ShortArray", SHORTARRAY);
        members.put("Integer", INTEGER);
        members.put("IntegerArray", INTEGERARRAY);
        members.put("Long", LONG);
        members.put("LongArray", LONGARRAY);
        members.put("Float", FLOAT);
        members.put("FloatArray", FLOATARRAY);
        members.put("Double", DOUBLE);
        members.put("DoubleArray", DOUBLEARRAY);
        members.put("String", STRING);
        members.put("StringArray", STRINGARRAY);
        members.put("Date", DATE);
        members.put("DateArray", DATEARRAY);
        members.put("Time", TIME);
        members.put("TimeArray", TIMEARRAY);
        members.put("DateTime", DATETIME);
        members.put("DateTimeArray", DATETIMEARRAY);
        members.put("CDO", CDO);
        members.put("CDOArray", CDOARRAY);
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
     * Returns the String representation of this FieldTypeTypeType
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
     * Returns a new FieldTypeTypeType based on the given String
     * value.
     * 
     * @param string
     * @return FieldTypeTypeType
     */
    public static com.cdoframework.cdolib.database.dataservice.types.FieldTypeTypeType valueOf(java.lang.String string)
    {
        java.lang.Object obj = null;
        if (string != null) obj = _memberTable.get(string);
        if (obj == null) {
            String err = "'" + string + "' is not a valid FieldTypeTypeType";
            throw new IllegalArgumentException(err);
        }
        return (FieldTypeTypeType) obj;
    } //-- com.cdoframework.cdolib.database.dataservice.types.FieldTypeTypeType valueOf(java.lang.String) 

}
