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
 * Class SimpleFieldTypeTypeType.
 * 
 * @version $Revision$ $Date$
 */
public class SimpleFieldTypeTypeType implements java.io.Serializable {


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
    public static final SimpleFieldTypeTypeType BYTE = new SimpleFieldTypeTypeType(BYTE_TYPE, "Byte");

    /**
     * The ByteArray type
     */
    public static final int BYTEARRAY_TYPE = 1;

    /**
     * The instance of the ByteArray type
     */
    public static final SimpleFieldTypeTypeType BYTEARRAY = new SimpleFieldTypeTypeType(BYTEARRAY_TYPE, "ByteArray");

    /**
     * The Short type
     */
    public static final int SHORT_TYPE = 2;

    /**
     * The instance of the Short type
     */
    public static final SimpleFieldTypeTypeType SHORT = new SimpleFieldTypeTypeType(SHORT_TYPE, "Short");

    /**
     * The ShortArray type
     */
    public static final int SHORTARRAY_TYPE = 3;

    /**
     * The instance of the ShortArray type
     */
    public static final SimpleFieldTypeTypeType SHORTARRAY = new SimpleFieldTypeTypeType(SHORTARRAY_TYPE, "ShortArray");

    /**
     * The Integer type
     */
    public static final int INTEGER_TYPE = 4;

    /**
     * The instance of the Integer type
     */
    public static final SimpleFieldTypeTypeType INTEGER = new SimpleFieldTypeTypeType(INTEGER_TYPE, "Integer");

    /**
     * The Long type
     */
    public static final int LONG_TYPE = 5;

    /**
     * The instance of the Long type
     */
    public static final SimpleFieldTypeTypeType LONG = new SimpleFieldTypeTypeType(LONG_TYPE, "Long");

    /**
     * The IntegerArray type
     */
    public static final int INTEGERARRAY_TYPE = 6;

    /**
     * The instance of the IntegerArray type
     */
    public static final SimpleFieldTypeTypeType INTEGERARRAY = new SimpleFieldTypeTypeType(INTEGERARRAY_TYPE, "IntegerArray");

    /**
     * The Float type
     */
    public static final int FLOAT_TYPE = 7;

    /**
     * The instance of the Float type
     */
    public static final SimpleFieldTypeTypeType FLOAT = new SimpleFieldTypeTypeType(FLOAT_TYPE, "Float");

    /**
     * The FloatArray type
     */
    public static final int FLOATARRAY_TYPE = 8;

    /**
     * The instance of the FloatArray type
     */
    public static final SimpleFieldTypeTypeType FLOATARRAY = new SimpleFieldTypeTypeType(FLOATARRAY_TYPE, "FloatArray");

    /**
     * The Double type
     */
    public static final int DOUBLE_TYPE = 9;

    /**
     * The instance of the Double type
     */
    public static final SimpleFieldTypeTypeType DOUBLE = new SimpleFieldTypeTypeType(DOUBLE_TYPE, "Double");

    /**
     * The DoubleArray type
     */
    public static final int DOUBLEARRAY_TYPE = 10;

    /**
     * The instance of the DoubleArray type
     */
    public static final SimpleFieldTypeTypeType DOUBLEARRAY = new SimpleFieldTypeTypeType(DOUBLEARRAY_TYPE, "DoubleArray");

    /**
     * The String type
     */
    public static final int STRING_TYPE = 11;

    /**
     * The instance of the String type
     */
    public static final SimpleFieldTypeTypeType STRING = new SimpleFieldTypeTypeType(STRING_TYPE, "String");

    /**
     * The StringArray type
     */
    public static final int STRINGARRAY_TYPE = 12;

    /**
     * The instance of the StringArray type
     */
    public static final SimpleFieldTypeTypeType STRINGARRAY = new SimpleFieldTypeTypeType(STRINGARRAY_TYPE, "StringArray");

    /**
     * The Date type
     */
    public static final int DATE_TYPE = 13;

    /**
     * The instance of the Date type
     */
    public static final SimpleFieldTypeTypeType DATE = new SimpleFieldTypeTypeType(DATE_TYPE, "Date");

    /**
     * The DateArray type
     */
    public static final int DATEARRAY_TYPE = 14;

    /**
     * The instance of the DateArray type
     */
    public static final SimpleFieldTypeTypeType DATEARRAY = new SimpleFieldTypeTypeType(DATEARRAY_TYPE, "DateArray");

    /**
     * The Time type
     */
    public static final int TIME_TYPE = 15;

    /**
     * The instance of the Time type
     */
    public static final SimpleFieldTypeTypeType TIME = new SimpleFieldTypeTypeType(TIME_TYPE, "Time");

    /**
     * The TimeArray type
     */
    public static final int TIMEARRAY_TYPE = 16;

    /**
     * The instance of the TimeArray type
     */
    public static final SimpleFieldTypeTypeType TIMEARRAY = new SimpleFieldTypeTypeType(TIMEARRAY_TYPE, "TimeArray");

    /**
     * The DateTime type
     */
    public static final int DATETIME_TYPE = 17;

    /**
     * The instance of the DateTime type
     */
    public static final SimpleFieldTypeTypeType DATETIME = new SimpleFieldTypeTypeType(DATETIME_TYPE, "DateTime");

    /**
     * The DateTimeArray type
     */
    public static final int DATETIMEARRAY_TYPE = 18;

    /**
     * The instance of the DateTimeArray type
     */
    public static final SimpleFieldTypeTypeType DATETIMEARRAY = new SimpleFieldTypeTypeType(DATETIMEARRAY_TYPE, "DateTimeArray");

    /**
     * The CDO type
     */
    public static final int CDO_TYPE = 19;

    /**
     * The instance of the CDO type
     */
    public static final SimpleFieldTypeTypeType CDO = new SimpleFieldTypeTypeType(CDO_TYPE, "CDO");

    /**
     * The CDOArray type
     */
    public static final int CDOARRAY_TYPE = 20;

    /**
     * The instance of the CDOArray type
     */
    public static final SimpleFieldTypeTypeType CDOARRAY = new SimpleFieldTypeTypeType(CDOARRAY_TYPE, "CDOArray");

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

    private SimpleFieldTypeTypeType(int type, java.lang.String value) 
     {
        super();
        this.type = type;
        this.stringValue = value;
    } //-- com.cdoframework.cdolib.database.dataservice.types.SimpleFieldTypeTypeType(int, java.lang.String)


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate
     * 
     * Returns an enumeration of all possible instances of
     * SimpleFieldTypeTypeType
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
     * Returns the type of this SimpleFieldTypeTypeType
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
        members.put("ByteArray", BYTEARRAY);
        members.put("Short", SHORT);
        members.put("ShortArray", SHORTARRAY);
        members.put("Integer", INTEGER);
        members.put("Long", LONG);
        members.put("IntegerArray", INTEGERARRAY);
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
     * Returns the String representation of this
     * SimpleFieldTypeTypeType
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
     * Returns a new SimpleFieldTypeTypeType based on the given
     * String value.
     * 
     * @param string
     * @return SimpleFieldTypeTypeType
     */
    public static com.cdoframework.cdolib.database.dataservice.types.SimpleFieldTypeTypeType valueOf(java.lang.String string)
    {
        java.lang.Object obj = null;
        if (string != null) obj = _memberTable.get(string);
        if (obj == null) {
            String err = "'" + string + "' is not a valid SimpleFieldTypeTypeType";
            throw new IllegalArgumentException(err);
        }
        return (SimpleFieldTypeTypeType) obj;
    } //-- com.cdoframework.cdolib.database.dataservice.types.SimpleFieldTypeTypeType valueOf(java.lang.String) 

}
