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
 * Class SQLTransTransFlagType.
 * 
 * @version $Revision$ $Date$
 */
public class SQLTransTransFlagType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The 0 type
     */
    public static final int VALUE_0_TYPE = 0;

    /**
     * The instance of the 0 type
     */
    public static final SQLTransTransFlagType VALUE_0 = new SQLTransTransFlagType(VALUE_0_TYPE, "0");

    /**
     * The 1 type
     */
    public static final int VALUE_1_TYPE = 1;

    /**
     * The instance of the 1 type
     */
    public static final SQLTransTransFlagType VALUE_1 = new SQLTransTransFlagType(VALUE_1_TYPE, "1");

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

    private SQLTransTransFlagType(int type, java.lang.String value) 
     {
        super();
        this.type = type;
        this.stringValue = value;
    } //-- com.cdoframework.cdolib.database.dataservice.types.SQLTransTransFlagType(int, java.lang.String)


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate
     * 
     * Returns an enumeration of all possible instances of
     * SQLTransTransFlagType
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
     * Returns the type of this SQLTransTransFlagType
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
        members.put("0", VALUE_0);
        members.put("1", VALUE_1);
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
     * SQLTransTransFlagType
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
     * Returns a new SQLTransTransFlagType based on the given
     * String value.
     * 
     * @param string
     * @return SQLTransTransFlagType
     */
    public static com.cdoframework.cdolib.database.dataservice.types.SQLTransTransFlagType valueOf(java.lang.String string)
    {
        java.lang.Object obj = null;
        if (string != null) obj = _memberTable.get(string);
        if (obj == null) {
            String err = "'" + string + "' is not a valid SQLTransTransFlagType";
            throw new IllegalArgumentException(err);
        }
        return (SQLTransTransFlagType) obj;
    } //-- com.cdoframework.cdolib.database.dataservice.types.SQLTransTransFlagType valueOf(java.lang.String) 

}
