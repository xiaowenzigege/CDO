/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.service.framework.transfilter.schema.types;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class ReturnCodeOperatorType.
 * 
 * @version $Revision$ $Date$
 */
public class ReturnCodeOperatorType implements java.io.Serializable {


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
    public static final ReturnCodeOperatorType VALUE_0 = new ReturnCodeOperatorType(VALUE_0_TYPE, "=");

    /**
     * The != type
     */
    public static final int VALUE_1_TYPE = 1;

    /**
     * The instance of the != type
     */
    public static final ReturnCodeOperatorType VALUE_1 = new ReturnCodeOperatorType(VALUE_1_TYPE, "!=");

    /**
     * The > type
     */
    public static final int VALUE_2_TYPE = 2;

    /**
     * The instance of the > type
     */
    public static final ReturnCodeOperatorType VALUE_2 = new ReturnCodeOperatorType(VALUE_2_TYPE, ">");

    /**
     * The >= type
     */
    public static final int VALUE_3_TYPE = 3;

    /**
     * The instance of the >= type
     */
    public static final ReturnCodeOperatorType VALUE_3 = new ReturnCodeOperatorType(VALUE_3_TYPE, ">=");

    /**
     * The < type
     */
    public static final int VALUE_4_TYPE = 4;

    /**
     * The instance of the < type
     */
    public static final ReturnCodeOperatorType VALUE_4 = new ReturnCodeOperatorType(VALUE_4_TYPE, "<");

    /**
     * The <= type
     */
    public static final int VALUE_5_TYPE = 5;

    /**
     * The instance of the <= type
     */
    public static final ReturnCodeOperatorType VALUE_5 = new ReturnCodeOperatorType(VALUE_5_TYPE, "<=");

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

    private ReturnCodeOperatorType(int type, java.lang.String value) 
     {
        super();
        this.type = type;
        this.stringValue = value;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.types.ReturnCodeOperatorType(int, java.lang.String)


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate
     * 
     * Returns an enumeration of all possible instances of
     * ReturnCodeOperatorType
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
     * Returns the type of this ReturnCodeOperatorType
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
     * ReturnCodeOperatorType
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
     * Returns a new ReturnCodeOperatorType based on the given
     * String value.
     * 
     * @param string
     * @return ReturnCodeOperatorType
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.schema.types.ReturnCodeOperatorType valueOf(java.lang.String string)
    {
        java.lang.Object obj = null;
        if (string != null) obj = _memberTable.get(string);
        if (obj == null) {
            String err = "'" + string + "' is not a valid ReturnCodeOperatorType";
            throw new IllegalArgumentException(err);
        }
        return (ReturnCodeOperatorType) obj;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.types.ReturnCodeOperatorType valueOf(java.lang.String) 

}
