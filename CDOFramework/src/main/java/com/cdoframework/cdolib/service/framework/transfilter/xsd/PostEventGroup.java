/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.3</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.service.framework.transfilter.xsd;

/**
 * 
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class PostEventGroup implements java.io.Serializable {

    /**
     * Field syn.
     */
    private boolean syn = false;

    /**
     * Keeps track of whether primitive field syn has been set
     * already.
     */
    private boolean _hassyn;

    /**
     * Field requestCondition.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.RequestCondition requestCondition;

    /**
     * Field responseCondition.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.ResponseCondition responseCondition;

    private java.util.List<com.cdoframework.cdolib.service.framework.transfilter.xsd.For> _forList;

    public PostEventGroup() {
        super();
        this._forList = new java.util.ArrayList<com.cdoframework.cdolib.service.framework.transfilter.xsd.For>();
    }

    /**
     * 
     * 
     * @param vFor
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addFor(final com.cdoframework.cdolib.service.framework.transfilter.xsd.For vFor) throws java.lang.IndexOutOfBoundsException {
        this._forList.add(vFor);
    }

    /**
     * 
     * 
     * @param index
     * @param vFor
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addFor(final int index,final com.cdoframework.cdolib.service.framework.transfilter.xsd.For vFor) throws java.lang.IndexOutOfBoundsException {
        this._forList.add(index, vFor);
    }

    /**
     */
    public void deleteSyn() {
        this._hassyn= false;
    }

    /**
     * Method enumerateFor.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.service.framework.transfilter.xsd.For> enumerateFor() {
        return java.util.Collections.enumeration(this._forList);
    }

    /**
     * Method getFor.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.service.framework.transfilter.xsd.For
     * at the given index
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.For getFor(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._forList.size()) {
            throw new IndexOutOfBoundsException("getFor: Index value '" + index + "' not in range [0.." + (this._forList.size() - 1) + "]");
        }

        return _forList.get(index);
    }

    /**
     * Method getFor.Returns the contents of the collection in an
     * Array.  <p>Note:  Just in case the collection contents are
     * changing in another thread, we pass a 0-length Array of the
     * correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.For[] getFor() {
        com.cdoframework.cdolib.service.framework.transfilter.xsd.For[] array = new com.cdoframework.cdolib.service.framework.transfilter.xsd.For[0];
        return this._forList.toArray(array);
    }

    /**
     * Method getForCount.
     * 
     * @return the size of this collection
     */
    public int getForCount() {
        return this._forList.size();
    }

    /**
     * Returns the value of field 'requestCondition'.
     * 
     * @return the value of field 'RequestCondition'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.RequestCondition getRequestCondition() {
        return this.requestCondition;
    }

    /**
     * Returns the value of field 'responseCondition'.
     * 
     * @return the value of field 'ResponseCondition'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.ResponseCondition getResponseCondition() {
        return this.responseCondition;
    }

    /**
     * Returns the value of field 'syn'.
     * 
     * @return the value of field 'Syn'.
     */
    public boolean getSyn() {
        return this.syn;
    }

    /**
     * Method hasSyn.
     * 
     * @return true if at least one Syn has been added
     */
    public boolean hasSyn() {
        return this._hassyn;
    }

    /**
     * Returns the value of field 'syn'.
     * 
     * @return the value of field 'Syn'.
     */
    public boolean isSyn() {
        return this.syn;
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
     * Method iterateFor.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.service.framework.transfilter.xsd.For> iterateFor() {
        return this._forList.iterator();
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
    public void removeAllFor() {
        this._forList.clear();
    }

    /**
     * Method removeFor.
     * 
     * @param vFor
     * @return true if the object was removed from the collection.
     */
    public boolean removeFor(final com.cdoframework.cdolib.service.framework.transfilter.xsd.For vFor) {
        boolean removed = _forList.remove(vFor);
        return removed;
    }

    /**
     * Method removeForAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.For removeForAt(final int index) {
        java.lang.Object obj = this._forList.remove(index);
        return (com.cdoframework.cdolib.service.framework.transfilter.xsd.For) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vFor
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setFor(final int index,final com.cdoframework.cdolib.service.framework.transfilter.xsd.For vFor) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._forList.size()) {
            throw new IndexOutOfBoundsException("setFor: Index value '" + index + "' not in range [0.." + (this._forList.size() - 1) + "]");
        }

        this._forList.set(index, vFor);
    }

    /**
     * 
     * 
     * @param vForArray
     */
    public void setFor(final com.cdoframework.cdolib.service.framework.transfilter.xsd.For[] vForArray) {
        //-- copy array
        _forList.clear();

        for (int i = 0; i < vForArray.length; i++) {
                this._forList.add(vForArray[i]);
        }
    }

    /**
     * Sets the value of field 'requestCondition'.
     * 
     * @param requestCondition the value of field 'requestCondition'
     */
    public void setRequestCondition(final com.cdoframework.cdolib.service.framework.transfilter.xsd.RequestCondition requestCondition) {
        this.requestCondition = requestCondition;
    }

    /**
     * Sets the value of field 'responseCondition'.
     * 
     * @param responseCondition the value of field
     * 'responseCondition'.
     */
    public void setResponseCondition(final com.cdoframework.cdolib.service.framework.transfilter.xsd.ResponseCondition responseCondition) {
        this.responseCondition = responseCondition;
    }

    /**
     * Sets the value of field 'syn'.
     * 
     * @param syn the value of field 'syn'.
     */
    public void setSyn(final boolean syn) {
        this.syn = syn;
        this._hassyn = true;
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
     * com.cdoframework.cdolib.service.framework.transfilter.xsd.PostEventGroup
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.xsd.PostEventGroup unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.service.framework.transfilter.xsd.PostEventGroup) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.xsd.PostEventGroup.class, reader);
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
