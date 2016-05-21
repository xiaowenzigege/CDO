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
 * Class CacheURLTypeType.
 * 
 * @version $Revision$ $Date$
 */
public class CacheURLTypeType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The Common type
     */
    public static final int COMMON_TYPE = 0;

    /**
     * The instance of the Common type
     */
    public static final CacheURLTypeType COMMON = new CacheURLTypeType(COMMON_TYPE, "Common");

    /**
     * The Json type
     */
    public static final int JSON_TYPE = 1;

    /**
     * The instance of the Json type
     */
    public static final CacheURLTypeType JSON = new CacheURLTypeType(JSON_TYPE, "Json");

    /**
     * The XML type
     */
    public static final int XML_TYPE = 2;

    /**
     * The instance of the XML type
     */
    public static final CacheURLTypeType XML = new CacheURLTypeType(XML_TYPE, "XML");

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

    private CacheURLTypeType(int type, java.lang.String value) 
     {
        super();
        this.type = type;
        this.stringValue = value;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.types.CacheURLTypeType(int, java.lang.String)


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate
     * 
     * Returns an enumeration of all possible instances of
     * CacheURLTypeType
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
     * Returns the type of this CacheURLTypeType
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
        members.put("Common", COMMON);
        members.put("Json", JSON);
        members.put("XML", XML);
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
     * Returns the String representation of this CacheURLTypeType
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
     * Returns a new CacheURLTypeType based on the given String
     * value.
     * 
     * @param string
     * @return CacheURLTypeType
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.schema.types.CacheURLTypeType valueOf(java.lang.String string)
    {
        java.lang.Object obj = null;
        if (string != null) obj = _memberTable.get(string);
        if (obj == null) {
            String err = "'" + string + "' is not a valid CacheURLTypeType";
            throw new IllegalArgumentException(err);
        }
        return (CacheURLTypeType) obj;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.types.CacheURLTypeType valueOf(java.lang.String) 

}
