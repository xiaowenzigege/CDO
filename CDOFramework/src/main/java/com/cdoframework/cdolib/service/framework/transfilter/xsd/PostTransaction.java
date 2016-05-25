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
public class PostTransaction implements java.io.Serializable {

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
     * Field returnCode.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.ReturnCode returnCode;

    /**
     * Field requestCondition.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.RequestCondition requestCondition;

    /**
     * Field responseCondition.
     */
    private com.cdoframework.cdolib.service.framework.transfilter.xsd.ResponseCondition responseCondition;

    private java.util.List<com.cdoframework.cdolib.service.framework.transfilter.xsd.Transaction> transactionList;

    public PostTransaction() {
        super();
        this.transactionList = new java.util.ArrayList<com.cdoframework.cdolib.service.framework.transfilter.xsd.Transaction>();
    }

    /**
     * 
     * 
     * @param vTransaction
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addTransaction(final com.cdoframework.cdolib.service.framework.transfilter.xsd.Transaction vTransaction) throws java.lang.IndexOutOfBoundsException {
        this.transactionList.add(vTransaction);
    }

    /**
     * 
     * 
     * @param index
     * @param vTransaction
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addTransaction(final int index,final com.cdoframework.cdolib.service.framework.transfilter.xsd.Transaction vTransaction) throws java.lang.IndexOutOfBoundsException {
        this.transactionList.add(index, vTransaction);
    }

    /**
     */
    public void deleteSyn() {
        this._hassyn= false;
    }

    /**
     * Method enumerateTransaction.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends com.cdoframework.cdolib.service.framework.transfilter.xsd.Transaction> enumerateTransaction() {
        return java.util.Collections.enumeration(this.transactionList);
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
     * Returns the value of field 'returnCode'.
     * 
     * @return the value of field 'ReturnCode'.
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.ReturnCode getReturnCode() {
        return this.returnCode;
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
     * Method getTransaction.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * com.cdoframework.cdolib.service.framework.transfilter.xsd.Transaction
     * at the given index
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.Transaction getTransaction(final int index) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.transactionList.size()) {
            throw new IndexOutOfBoundsException("getTransaction: Index value '" + index + "' not in range [0.." + (this.transactionList.size() - 1) + "]");
        }

        return transactionList.get(index);
    }

    /**
     * Method getTransaction.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.Transaction[] getTransaction() {
        com.cdoframework.cdolib.service.framework.transfilter.xsd.Transaction[] array = new com.cdoframework.cdolib.service.framework.transfilter.xsd.Transaction[0];
        return this.transactionList.toArray(array);
    }

    /**
     * Method getTransactionCount.
     * 
     * @return the size of this collection
     */
    public int getTransactionCount() {
        return this.transactionList.size();
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
     * Method iterateTransaction.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends com.cdoframework.cdolib.service.framework.transfilter.xsd.Transaction> iterateTransaction() {
        return this.transactionList.iterator();
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
    public void removeAllTransaction() {
        this.transactionList.clear();
    }

    /**
     * Method removeTransaction.
     * 
     * @param vTransaction
     * @return true if the object was removed from the collection.
     */
    public boolean removeTransaction(final com.cdoframework.cdolib.service.framework.transfilter.xsd.Transaction vTransaction) {
        boolean removed = transactionList.remove(vTransaction);
        return removed;
    }

    /**
     * Method removeTransactionAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public com.cdoframework.cdolib.service.framework.transfilter.xsd.Transaction removeTransactionAt(final int index) {
        java.lang.Object obj = this.transactionList.remove(index);
        return (com.cdoframework.cdolib.service.framework.transfilter.xsd.Transaction) obj;
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
     * Sets the value of field 'returnCode'.
     * 
     * @param returnCode the value of field 'returnCode'.
     */
    public void setReturnCode(final com.cdoframework.cdolib.service.framework.transfilter.xsd.ReturnCode returnCode) {
        this.returnCode = returnCode;
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
     * 
     * 
     * @param index
     * @param vTransaction
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setTransaction(final int index,final com.cdoframework.cdolib.service.framework.transfilter.xsd.Transaction vTransaction) throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this.transactionList.size()) {
            throw new IndexOutOfBoundsException("setTransaction: Index value '" + index + "' not in range [0.." + (this.transactionList.size() - 1) + "]");
        }

        this.transactionList.set(index, vTransaction);
    }

    /**
     * 
     * 
     * @param vTransactionArray
     */
    public void setTransaction(final com.cdoframework.cdolib.service.framework.transfilter.xsd.Transaction[] vTransactionArray) {
        //-- copy array
        transactionList.clear();

        for (int i = 0; i < vTransactionArray.length; i++) {
                this.transactionList.add(vTransactionArray[i]);
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
     * com.cdoframework.cdolib.service.framework.transfilter.xsd.PostTransaction
     */
    public static com.cdoframework.cdolib.service.framework.transfilter.xsd.PostTransaction unmarshal(final java.io.Reader reader) throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.cdoframework.cdolib.service.framework.transfilter.xsd.PostTransaction) org.exolab.castor.xml.Unmarshaller.unmarshal(com.cdoframework.cdolib.service.framework.transfilter.xsd.PostTransaction.class, reader);
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
