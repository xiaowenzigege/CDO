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
 * Class CacheKeyTypeTypeType.
 * 
 * @version $Revision$ $Date$
 */
public class CacheKeyTypeTypeType implements java.io.Serializable {


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
    public static final CacheKeyTypeTypeType COMMON = new CacheKeyTypeTypeType(COMMON_TYPE, "Common");

    /**
     * The Json type
     */
    public static final int JSON_TYPE = 1;

    /**
     * The instance of the Json type
     */
    public static final CacheKeyTypeTypeType JSON = new CacheKeyTypeTypeType(JSON_TYPE, "Json");

    /**
     * The XML type
     */
    public static final int XML_TYPE = 2;

    /**
     * The instance of the XML type
     */
    public static final CacheKeyTypeTypeType XML = new CacheKeyTypeTypeType(XML_TYPE, "XML");

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

    private CacheKeyTypeTypeType(int type, java.lang.String value) 
     {
        super();
        this.type = type;
        this.stringValue = value;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.types.CacheKeyTypeTypeType(int, java.lang.String)


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate
     * 
     * Returns an enumeration of all possible instances of
     * CacheKeyTypeTypeType
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
     * Returns the type of this CacheKeyTypeTypeType
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
     * Returns the String representation of this
     * CacheKeyTypeTypeType
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
     * Returns a new CacheKeyTypeTypeType based on the given String
     * value.
     * 
     * @param string
     * @return CacheKeyTypeTypeType
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.schema.types.CacheKeyTypeTypeType valueOf(java.lang.String string)
    {
        java.lang.Object obj = null;
        if (string != null) obj = _memberTable.get(string);
        if (obj == null) {
            String err = "'" + string + "' is not a valid CacheKeyTypeTypeType";
            throw new IllegalArgumentException(err);
        }
        return (CacheKeyTypeTypeType) obj;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.types.CacheKeyTypeTypeType valueOf(java.lang.String) 

}
