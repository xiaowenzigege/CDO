/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.3</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.database.xsd;

/**
 * 
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class Return implements java.io.Serializable {

    /**
     * Field code.
     */
    private int code;

    /**
     * Keeps track of whether primitive field code has been set
     * already.
     */
    private boolean _hascode;

    /**
     * Field info.
     */
    private java.lang.String info;

    /**
     * Field text.
     */
    private java.lang.String text = "OK";

    private java.util.List<com.cdoframework.cdolib.database.xsd.ReturnItem> returnItemList;

    public Return() {
        super();
        setText("OK");
        this.returnItemList = new java.util.ArrayList<com.cdoframework.cdolib.database.xsd.ReturnItem>();
    }

    /**
     * 
     * 
     * @param vReturnItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addReturnItem(final com.cdoframework.cdolib.database.xsd.ReturnItem vReturnItem) throws java.lang.IndexOutOfBoundsException {
        this.returnItemList.add(vReturnItem);
    }

    /**
     * 
     * 
     * @param index
     * @param vReturnItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addReturnItem(final int index,final com.cdoframework.cdolib.database.xsd.ReturnItem vReturnItem) throws java.lang.IndexOutOfBoundsException {
        this.returnItemList.add(index, vReturnItem);
    }

    /**
     */
    public void deleteCode() {
        this._hascode= false;
    }

    /**
     * Method enumerateReturnItem.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.database.xsd.ReturnItem> enumerateReturnItem() {
        return java.util.Collections.enumeration(this.returnItemList);
    }

    /**
     * Returns the value of field 'code'.
     * 
     * @return the value of field 'Code'.
     */
    public int getCode() {
        return this.code;
    }

    /**
     * Returns the value of field 'info'.
     * 
     * @return the value of field 'Info'.
     */
    public java.lang.String getInfo() {
        return this.info;
    }

    /**
     * Method getReturnItem.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.database.xsd.ReturnItem at the given
     * index
     */
    public com.cdoframework.cdolib.database.xsd.ReturnItem getReturnItem(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.returnItemList.size()) {
            throw new IndexOutOfBoundsException("getReturnItem: Index value '" + index + "' not in range [0.." + (this.returnItemList.size() - 1) + "]");
        }

        return returnItemList.get(index);
    }

    /**
     * Method getReturnItem.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.database.xsd.ReturnItem[] getReturnItem() {
        com.cdoframework.cdolib.database.xsd.ReturnItem[] array = new com.cdoframework.cdolib.database.xsd.ReturnItem[0];
        return this.returnItemList.toArray(array);
    }

    /**
     * Method getReturnItemCount.
     * 
     * @return the size of this collection
     */
    public int getReturnItemCount() {
        return this.returnItemList.size();
    }

    /**
     * Returns the value of field 'text'.
     * 
     * @return the value of field 'Text'.
     */
    public java.lang.String getText() {
        return this.text;
    }

    /**
     * Method hasCode.
     * 
     * @return true if at least one Code has been added
     */
    public boolean hasCode() {
        return this._hascode;
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
     * Method iterateReturnItem.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.database.xsd.ReturnItem> iterateReturnItem() {
        return this.returnItemList.iterator();
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
    public void removeAllReturnItem() {
        this.returnItemList.clear();
    }

    /**
     * Method removeReturnItem.
     * 
     * @param vReturnItem
     * @return true if the object was removed from the collection.
     */
    public boolean removeReturnItem(final com.cdoframework.cdolib.database.xsd.ReturnItem vReturnItem) {
        boolean removed = returnItemList.remove(vReturnItem);
        return removed;
    }

    /**
     * Method removeReturnItemAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.database.xsd.ReturnItem removeReturnItemAt(final int index) {
        java.lang.Object obj = this.returnItemList.remove(index);
        return (com.cdoframework.cdolib.database.xsd.ReturnItem) obj;
    }

    /**
     * Sets the value of field 'code'.
     * 
     * @param code the value of field 'code'.
     */
    public void setCode(final int code) {
        this.code = code;
        this._hascode = true;
    }

    /**
     * Sets the value of field 'info'.
     * 
     * @param info the value of field 'info'.
     */
    public void setInfo(final java.lang.String info) {
        this.info = info;
    }

    /**
     * 
     * 
     * @param index
     * @param vReturnItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setReturnItem(final int index,final com.cdoframework.cdolib.database.xsd.ReturnItem vReturnItem) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.returnItemList.size()) {
            throw new IndexOutOfBoundsException("setReturnItem: Index value '" + index + "' not in range [0.." + (this.returnItemList.size() - 1) + "]");
        }

        this.returnItemList.set(index, vReturnItem);
    }

    /**
     * 
     * 
     * @param vReturnItemArray
     */
    public void setReturnItem(final com.cdoframework.cdolib.database.xsd.ReturnItem[] vReturnItemArray) {
        //-- copy array
        returnItemList.clear();

        for (int i = 0; i < vReturnItemArray.length; i++) {
                this.returnItemList.add(vReturnItemArray[i]);
        }
    }

    /**
     * Sets the value of field 'text'.
     * 
     * @param text the value of field 'text'.
     */
    public void setText(final java.lang.String text) {
        this.text = text;
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
     * com.cdoframework.cdolib.database.xsd.Return
     */
    public static com.cdoframework.cdolib.database.xsd.Return unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.database.xsd.Return) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.database.xsd.Return.class, reader);
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

}
