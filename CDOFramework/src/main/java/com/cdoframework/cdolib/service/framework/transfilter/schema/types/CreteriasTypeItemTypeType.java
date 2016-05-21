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
 * Class CreteriasTypeItemTypeType.
 * 
 * @version $Revision$ $Date$
 */
public class CreteriasTypeItemTypeType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The AND type
     */
    public static final int AND_TYPE = 0;

    /**
     * The instance of the AND type
     */
    public static final CreteriasTypeItemTypeType AND = new CreteriasTypeItemTypeType(AND_TYPE, "AND");

    /**
     * The OR type
     */
    public static final int OR_TYPE = 1;

    /**
     * The instance of the OR type
     */
    public static final CreteriasTypeItemTypeType OR = new CreteriasTypeItemTypeType(OR_TYPE, "OR");

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

    private CreteriasTypeItemTypeType(int type, java.lang.String value) 
     {
        super();
        this.type = type;
        this.stringValue = value;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.types.CreteriasTypeItemTypeType(int, java.lang.String)


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate
     * 
     * Returns an enumeration of all possible instances of
     * CreteriasTypeItemTypeType
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
     * Returns the type of this CreteriasTypeItemTypeType
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
        members.put("AND", AND);
        members.put("OR", OR);
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
     * CreteriasTypeItemTypeType
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
     * Returns a new CreteriasTypeItemTypeType based on the given
     * String value.
     * 
     * @param string
     * @return CreteriasTypeItemTypeType
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.schema.types.CreteriasTypeItemTypeType valueOf(java.lang.String string)
    {
        java.lang.Object obj = null;
        if (string != null) obj = _memberTable.get(string);
        if (obj == null) {
            String err = "'" + string + "' is not a valid CreteriasTypeItemTypeType";
            throw new IllegalArgumentException(err);
        }
        return (CreteriasTypeItemTypeType) obj;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.types.CreteriasTypeItemTypeType valueOf(java.lang.String) 

}
