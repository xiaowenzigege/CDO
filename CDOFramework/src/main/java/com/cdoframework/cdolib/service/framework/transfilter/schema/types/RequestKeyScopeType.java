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
 * Class RequestKeyScopeType.
 * 
 * @version $Revision$ $Date$
 */
public class RequestKeyScopeType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The Request type
     */
    public static final int REQUEST_TYPE = 0;

    /**
     * The instance of the Request type
     */
    public static final RequestKeyScopeType REQUEST = new RequestKeyScopeType(REQUEST_TYPE, "Request");

    /**
     * The Response type
     */
    public static final int RESPONSE_TYPE = 1;

    /**
     * The instance of the Response type
     */
    public static final RequestKeyScopeType RESPONSE = new RequestKeyScopeType(RESPONSE_TYPE, "Response");

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

    private RequestKeyScopeType(int type, java.lang.String value) 
     {
        super();
        this.type = type;
        this.stringValue = value;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.types.RequestKeyScopeType(int, java.lang.String)


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate
     * 
     * Returns an enumeration of all possible instances of
     * RequestKeyScopeType
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
     * Returns the type of this RequestKeyScopeType
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
        members.put("Request", REQUEST);
        members.put("Response", RESPONSE);
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
     * RequestKeyScopeType
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
     * Returns a new RequestKeyScopeType based on the given String
     * value.
     * 
     * @param string
     * @return RequestKeyScopeType
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.schema.types.RequestKeyScopeType valueOf(java.lang.String string)
    {
        java.lang.Object obj = null;
        if (string != null) obj = _memberTable.get(string);
        if (obj == null) {
            String err = "'" + string + "' is not a valid RequestKeyScopeType";
            throw new IllegalArgumentException(err);
        }
        return (RequestKeyScopeType) obj;
    } //-- com.cdoframework.cdolib.service.framework.transfilter.schema.types.RequestKeyScopeType valueOf(java.lang.String) 

}
