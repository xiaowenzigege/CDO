/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.3</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.database.xsd;

import java.io.StringReader;

import org.apache.log4j.Logger;

/**
 * 
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class BigTableConfig implements java.io.Serializable {

    private java.util.List<com.cdoframework.cdolib.database.xsd.BigTableGroup> bigTableGroupList;

    public BigTableConfig() {
        super();
        this.bigTableGroupList = new java.util.ArrayList<com.cdoframework.cdolib.database.xsd.BigTableGroup>();
    }

    /**
     * 
     * 
     * @param vBigTableGroup
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addBigTableGroup(final com.cdoframework.cdolib.database.xsd.BigTableGroup vBigTableGroup) throws java.lang.IndexOutOfBoundsException {
        this.bigTableGroupList.add(vBigTableGroup);
    }

    /**
     * 
     * 
     * @param index
     * @param vBigTableGroup
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addBigTableGroup(final int index,final com.cdoframework.cdolib.database.xsd.BigTableGroup vBigTableGroup) throws java.lang.IndexOutOfBoundsException {
        this.bigTableGroupList.add(index, vBigTableGroup);
    }

    /**
     * Method enumerateBigTableGroup.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.database.xsd.BigTableGroup> enumerateBigTableGroup() {
        return java.util.Collections.enumeration(this.bigTableGroupList);
    }

    /**
     * Method getBigTableGroup.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.database.xsd.BigTableGroup at the
     * given index
     */
    public com.cdoframework.cdolib.database.xsd.BigTableGroup getBigTableGroup(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.bigTableGroupList.size()) {
            throw new IndexOutOfBoundsException("getBigTableGroup: Index value '" + index + "' not in range [0.." + (this.bigTableGroupList.size() - 1) + "]");
        }

        return bigTableGroupList.get(index);
    }

    /**
     * Method getBigTableGroup.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.database.xsd.BigTableGroup[] getBigTableGroup() {
        com.cdoframework.cdolib.database.xsd.BigTableGroup[] array = new com.cdoframework.cdolib.database.xsd.BigTableGroup[0];
        return this.bigTableGroupList.toArray(array);
    }

    /**
     * Method getBigTableGroupCount.
     * 
     * @return the size of this collection
     */
    public int getBigTableGroupCount() {
        return this.bigTableGroupList.size();
    }

    /**
     * Method isValid.
     * 
     * @return true if this object is valid according to the schema
     */
    public boolean isValid() {
        try {
            validate();
        } catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    }

    /**
     * Method iterateBigTableGroup.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.database.xsd.BigTableGroup> iterateBigTableGroup() {
        return this.bigTableGroupList.iterator();
    }

    /**
     * 
     * 
     * @param out
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void marshal(final java.io.Writer out) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Marshaller.marshal(this, out);
    }

    /**
     * 
     * 
     * @param handler
     * @throws java.io.IOException if an IOException occurs during
     * marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     */
    public void marshal(final org.xml.sax.ContentHandler handler) throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Marshaller.marshal(this, handler);
    }

    /**
     */
    public void removeAllBigTableGroup() {
        this.bigTableGroupList.clear();
    }

    /**
     * Method removeBigTableGroup.
     * 
     * @param vBigTableGroup
     * @return true if the object was removed from the collection.
     */
    public boolean removeBigTableGroup(final com.cdoframework.cdolib.database.xsd.BigTableGroup vBigTableGroup) {
        boolean removed = bigTableGroupList.remove(vBigTableGroup);
        return removed;
    }

    /**
     * Method removeBigTableGroupAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.database.xsd.BigTableGroup removeBigTableGroupAt(final int index) {
        java.lang.Object obj = this.bigTableGroupList.remove(index);
        return (com.cdoframework.cdolib.database.xsd.BigTableGroup) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vBigTableGroup
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setBigTableGroup(final int index,final com.cdoframework.cdolib.database.xsd.BigTableGroup vBigTableGroup) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.bigTableGroupList.size()) {
            throw new IndexOutOfBoundsException("setBigTableGroup: Index value '" + index + "' not in range [0.." + (this.bigTableGroupList.size() - 1) + "]");
        }

        this.bigTableGroupList.set(index, vBigTableGroup);
    }

    /**
     * 
     * 
     * @param vBigTableGroupArray
     */
    public void setBigTableGroup(final com.cdoframework.cdolib.database.xsd.BigTableGroup[] vBigTableGroupArray) {
        //-- copy array
        bigTableGroupList.clear();

        for (int i = 0; i < vBigTableGroupArray.length; i++) {
                this.bigTableGroupList.add(vBigTableGroupArray[i]);
        }
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled
     * com.cdoframework.cdolib.database.xsd.BigTableConfig
     */
    public static com.cdoframework.cdolib.database.xsd.BigTableConfig unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.database.xsd.BigTableConfig) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.database.xsd.BigTableConfig.class, reader);
    }

    /**
     * 
     * 
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void validate() throws org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    }
    
    //------------------------------manual code----------------------
    static Logger logger = Logger.getLogger(BigTableConfig.class);
    /**
     * ****************************************************************************************
     * 从一个XML字件串解析成BusinessManager对象
     * @param strXML
     * @return
     */
    public static BigTableConfig   fromXML(java.lang.String strXML) throws Exception
    {
    	StringReader reader=null;
    	try
    	{
    		reader=new StringReader(strXML);
    		BigTableConfig bigTableConfig = (BigTableConfig)BigTableConfig.unmarshal(reader);
    		return bigTableConfig;
    	}
    	finally
    	{
    		if(reader!=null)
    		{
    			try
    			{
    				reader.close();
    			}
    			catch(Exception e)
    			{//TODO 日志
    				logger.error("bigTableConfig fromXML:"+e.getMessage(),e);
    			}
    		}
    	}
    } 
}
