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
public class OnException implements java.io.Serializable {

    private java.util.List<com.cdoframework.cdolib.database.xsd.OnError> onErrorList;

    /**
     * Field _return.
     */
    private com.cdoframework.cdolib.database.xsd.Return _return;

    public OnException() {
        super();
        this.onErrorList = new java.util.ArrayList<com.cdoframework.cdolib.database.xsd.OnError>();
    }

    /**
     * 
     * 
     * @param vOnError
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addOnError(final com.cdoframework.cdolib.database.xsd.OnError vOnError) throws java.lang.IndexOutOfBoundsException {
        this.onErrorList.add(vOnError);
    }

    /**
     * 
     * 
     * @param index
     * @param vOnError
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addOnError(final int index,final com.cdoframework.cdolib.database.xsd.OnError vOnError) throws java.lang.IndexOutOfBoundsException {
        this.onErrorList.add(index, vOnError);
    }

    /**
     * Method enumerateOnError.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.database.xsd.OnError> enumerateOnError() {
        return java.util.Collections.enumeration(this.onErrorList);
    }

    /**
     * Method getOnError.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.database.xsd.OnError at the given
     * index
     */
    public com.cdoframework.cdolib.database.xsd.OnError getOnError(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.onErrorList.size()) {
            throw new IndexOutOfBoundsException("getOnError: Index value '" + index + "' not in range [0.." + (this.onErrorList.size() - 1) + "]");
        }

        return onErrorList.get(index);
    }

    /**
     * Method getOnError.Returns the contents of the collection in
     * an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.database.xsd.OnError[] getOnError() {
        com.cdoframework.cdolib.database.xsd.OnError[] array = new com.cdoframework.cdolib.database.xsd.OnError[0];
        return this.onErrorList.toArray(array);
    }

    /**
     * Method getOnErrorCount.
     * 
     * @return the size of this collection
     */
    public int getOnErrorCount() {
        return this.onErrorList.size();
    }

    /**
     * Returns the value of field 'return'.
     * 
     * @return the value of field 'Return'.
     */
    public com.cdoframework.cdolib.database.xsd.Return getReturn() {
        return this._return;
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
     * Method iterateOnError.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.database.xsd.OnError> iterateOnError() {
        return this.onErrorList.iterator();
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
    public void removeAllOnError() {
        this.onErrorList.clear();
    }

    /**
     * Method removeOnError.
     * 
     * @param vOnError
     * @return true if the object was removed from the collection.
     */
    public boolean removeOnError(final com.cdoframework.cdolib.database.xsd.OnError vOnError) {
        boolean removed = onErrorList.remove(vOnError);
        return removed;
    }

    /**
     * Method removeOnErrorAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.database.xsd.OnError removeOnErrorAt(final int index) {
        java.lang.Object obj = this.onErrorList.remove(index);
        return (com.cdoframework.cdolib.database.xsd.OnError) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vOnError
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setOnError(final int index,final com.cdoframework.cdolib.database.xsd.OnError vOnError) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.onErrorList.size()) {
            throw new IndexOutOfBoundsException("setOnError: Index value '" + index + "' not in range [0.." + (this.onErrorList.size() - 1) + "]");
        }

        this.onErrorList.set(index, vOnError);
    }

    /**
     * 
     * 
     * @param vOnErrorArray
     */
    public void setOnError(final com.cdoframework.cdolib.database.xsd.OnError[] vOnErrorArray) {
        //-- copy array
        onErrorList.clear();

        for (int i = 0; i < vOnErrorArray.length; i++) {
                this.onErrorList.add(vOnErrorArray[i]);
        }
    }

    /**
     * Sets the value of field 'return'.
     * 
     * @param _return
     * @param return the value of field 'return'.
     */
    public void setReturn(final com.cdoframework.cdolib.database.xsd.Return _return) {
        this._return = _return;
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
     * com.cdoframework.cdolib.database.xsd.OnException
     */
    public static com.cdoframework.cdolib.database.xsd.OnException unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.database.xsd.OnException) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.database.xsd.OnException.class, reader);
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
