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
 * Class ModifyFieldMethodType.
 * 
 * @version $Revision$ $Date$
 */
public class ModifyFieldMethodType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The inc type
     */
    public static final int INC_TYPE = 0;

    /**
     * The instance of the inc type
     */
    public static final ModifyFieldMethodType INC = new ModifyFieldMethodType(INC_TYPE, "inc");

    /**
     * The set type
     */
    public static final int SET_TYPE = 1;

    /**
     * The instance of the set type
     */
    public static final ModifyFieldMethodType SET = new ModifyFieldMethodType(SET_TYPE, "set");

    /**
     * The unset type
     */
    public static final int UNSET_TYPE = 2;

    /**
     * The instance of the unset type
     */
    public static final ModifyFieldMethodType UNSET = new ModifyFieldMethodType(UNSET_TYPE, "unset");

    /**
     * The push type
     */
    public static final int PUSH_TYPE = 3;

    /**
     * The instance of the push type
     */
    public static final ModifyFieldMethodType PUSH = new ModifyFieldMethodType(PUSH_TYPE, "push");

    /**
     * The pushAll type
     */
    public static final int PUSHALL_TYPE = 4;

    /**
     * The instance of the pushAll type
     */
    public static final ModifyFieldMethodType PUSHALL = new ModifyFieldMethodType(PUSHALL_TYPE, "pushAll");

    /**
     * The addToSet type
     */
    public static final int ADDTOSET_TYPE = 5;

    /**
     * The instance of the addToSet type
     */
    public static final ModifyFieldMethodType ADDTOSET = new ModifyFieldMethodType(ADDTOSET_TYPE, "addToSet");

    /**
     * The pop type
     */
    public static final int POP_TYPE = 6;

    /**
     * The instance of the pop type
     */
    public static final ModifyFieldMethodType POP = new ModifyFieldMethodType(POP_TYPE, "pop");

    /**
     * The pull type
     */
    public static final int PULL_TYPE = 7;

    /**
     * The instance of the pull type
     */
    public static final ModifyFieldMethodType PULL = new ModifyFieldMethodType(PULL_TYPE, "pull");

    /**
     * The pullAll type
     */
    public static final int PULLALL_TYPE = 8;

    /**
     * The instance of the pullAll type
     */
    public static final ModifyFieldMethodType PULLALL = new ModifyFieldMethodType(PULLALL_TYPE, "pullAll");

    /**
     * The setnull type
     */
    public static final int SETNULL_TYPE = 9;

    /**
     * The instance of the setnull type
     */
    public static final ModifyFieldMethodType SETNULL = new ModifyFieldMethodType(SETNULL_TYPE, "setnull");

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

    private ModifyFieldMethodType(int type, java.lang.String value) 
     {
        super();
        this.type = type;
        this.stringValue = value;
    } //-- com.cdoframework.cdolib.database.dataservice.types.ModifyFieldMethodType(int, java.lang.String)


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate
     * 
     * Returns an enumeration of all possible instances of
     * ModifyFieldMethodType
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
     * Returns the type of this ModifyFieldMethodType
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
        members.put("inc", INC);
        members.put("set", SET);
        members.put("unset", UNSET);
        members.put("push", PUSH);
        members.put("pushAll", PUSHALL);
        members.put("addToSet", ADDTOSET);
        members.put("pop", POP);
        members.put("pull", PULL);
        members.put("pullAll", PULLALL);
        members.put("setnull", SETNULL);
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
     * ModifyFieldMethodType
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
     * Returns a new ModifyFieldMethodType based on the given
     * String value.
     * 
     * @param string
     * @return ModifyFieldMethodType
     */
    public static com.cdoframework.cdolib.database.dataservice.types.ModifyFieldMethodType valueOf(java.lang.String string)
    {
        java.lang.Object obj = null;
        if (string != null) obj = _memberTable.get(string);
        if (obj == null) {
            String err = "'" + string + "' is not a valid ModifyFieldMethodType";
            throw new IllegalArgumentException(err);
        }
        return (ModifyFieldMethodType) obj;
    } //-- com.cdoframework.cdolib.database.dataservice.types.ModifyFieldMethodType valueOf(java.lang.String) 

}
