/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.3</a>, using an XML
 * Schema.
 * $Id$
 */

package com.cdoframework.cdolib.service.framework.transfilter.xsd.descriptors;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import com.cdoframework.cdolib.service.framework.transfilter.xsd.PostTransaction;

/**
 * 
 * 
 * @version $Revision$ $Date$
 */
public class PostTransactionDescriptor extends org.exolab.castor.xml.util.XMLClassDescriptorImpl {

    /**
     * Field _elementDefinition.
     */
    private boolean _elementDefinition;

    /**
     * Field _nsPrefix.
     */
    private java.lang.String _nsPrefix;

    /**
     * Field _nsURI.
     */
    private java.lang.String _nsURI;

    /**
     * Field _xmlName.
     */
    private java.lang.String _xmlName;

    /**
     * Field _identity.
     */
    private org.exolab.castor.xml.XMLFieldDescriptor _identity;

    public PostTransactionDescriptor() {
        super();
        _xmlName = "PostTransaction";
        _elementDefinition = true;

        //-- set grouping compositor
        setCompositorAsSequence();
        org.exolab.castor.xml.util.XMLFieldDescriptorImpl  desc           = null;
        org.exolab.castor.mapping.FieldHandler             handler        = null;
        org.exolab.castor.xml.FieldValidator               fieldValidator = null;
        //-- initialize attribute descriptors

        //-- syn
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.Boolean.TYPE, "syn", "Syn", org.exolab.castor.xml.NodeType.Attribute);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            @Override
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                PostTransaction target = (PostTransaction) object;
                if (!target.hasSyn()) { return null; }
                return (target.getSyn() ? java.lang.Boolean.TRUE : java.lang.Boolean.FALSE);
            }
            @Override
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    PostTransaction target = (PostTransaction) object;
                    // if null, use delete method for optional primitives 
                    if (value == null) {
                        target.deleteSyn();
                        return;
                    }
                    target.setSyn( ((java.lang.Boolean) value).booleanValue());
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            @Override
            @SuppressWarnings("unused")
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("boolean");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);

        //-- validation code for: syn
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
            org.exolab.castor.xml.validators.BooleanValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.BooleanValidator();
            fieldValidator.setValidator(typeValidator);
        }
        desc.setValidator(fieldValidator);
        //-- initialize element descriptors

        //-- returnCode
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(com.cdoframework.cdolib.service.framework.transfilter.xsd.ReturnCode.class, "returnCode", "ReturnCode", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            @Override
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                PostTransaction target = (PostTransaction) object;
                return target.getReturnCode();
            }
            @Override
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    PostTransaction target = (PostTransaction) object;
                    target.setReturnCode( (com.cdoframework.cdolib.service.framework.transfilter.xsd.ReturnCode) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            @Override
            @SuppressWarnings("unused")
            public java.lang.Object newInstance(java.lang.Object parent) {
                return new com.cdoframework.cdolib.service.framework.transfilter.xsd.ReturnCode();
            }
        };
        desc.setSchemaType("com.cdoframework.cdolib.service.framework.transfilter.xsd.ReturnCode");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);

        //-- validation code for: returnCode
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
        }
        desc.setValidator(fieldValidator);
        //-- requestCondition
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(com.cdoframework.cdolib.service.framework.transfilter.xsd.RequestCondition.class, "requestCondition", "RequestCondition", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            @Override
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                PostTransaction target = (PostTransaction) object;
                return target.getRequestCondition();
            }
            @Override
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    PostTransaction target = (PostTransaction) object;
                    target.setRequestCondition( (com.cdoframework.cdolib.service.framework.transfilter.xsd.RequestCondition) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            @Override
            @SuppressWarnings("unused")
            public java.lang.Object newInstance(java.lang.Object parent) {
                return new com.cdoframework.cdolib.service.framework.transfilter.xsd.RequestCondition();
            }
        };
        desc.setSchemaType("com.cdoframework.cdolib.service.framework.transfilter.xsd.RequestCondition");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);

        //-- validation code for: requestCondition
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
        }
        desc.setValidator(fieldValidator);
        //-- responseCondition
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(com.cdoframework.cdolib.service.framework.transfilter.xsd.ResponseCondition.class, "responseCondition", "ResponseCondition", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            @Override
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                PostTransaction target = (PostTransaction) object;
                return target.getResponseCondition();
            }
            @Override
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    PostTransaction target = (PostTransaction) object;
                    target.setResponseCondition( (com.cdoframework.cdolib.service.framework.transfilter.xsd.ResponseCondition) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            @Override
            @SuppressWarnings("unused")
            public java.lang.Object newInstance(java.lang.Object parent) {
                return new com.cdoframework.cdolib.service.framework.transfilter.xsd.ResponseCondition();
            }
        };
        desc.setSchemaType("com.cdoframework.cdolib.service.framework.transfilter.xsd.ResponseCondition");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);

        //-- validation code for: responseCondition
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
        }
        desc.setValidator(fieldValidator);
        //-- transactionList
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(com.cdoframework.cdolib.service.framework.transfilter.xsd.Transaction.class, "transactionList", "Transaction", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            @Override
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                PostTransaction target = (PostTransaction) object;
                return target.getTransaction();
            }
            @Override
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    PostTransaction target = (PostTransaction) object;
                    target.addTransaction( (com.cdoframework.cdolib.service.framework.transfilter.xsd.Transaction) value);
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public void resetValue(Object object) throws IllegalStateException, IllegalArgumentException {
                try {
                    PostTransaction target = (PostTransaction) object;
                    target.removeAllTransaction();
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            @Override
            @SuppressWarnings("unused")
            public java.lang.Object newInstance(java.lang.Object parent) {
                return new com.cdoframework.cdolib.service.framework.transfilter.xsd.Transaction();
            }
        };
        desc.setSchemaType("list");
        desc.setComponentType("com.cdoframework.cdolib.service.framework.transfilter.xsd.Transaction");
        desc.setHandler(handler);
        desc.setRequired(true);
        desc.setMultivalued(true);
        addFieldDescriptor(desc);
        addSequenceElement(desc);

        //-- validation code for: transactionList
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        fieldValidator.setMinOccurs(1);
        { //-- local scope
        }
        desc.setValidator(fieldValidator);
    }

    /**
     * Method getAccessMode.
     * 
     * @return the access mode specified for this class.
     */
    @Override()
    public org.exolab.castor.mapping.AccessMode getAccessMode() {
        return null;
    }

    /**
     * Method getIdentity.
     * 
     * @return the identity field, null if this class has no
     * identity.
     */
    @Override()
    public org.exolab.castor.mapping.FieldDescriptor getIdentity() {
        return _identity;
    }

    /**
     * Method getJavaClass.
     * 
     * @return the Java class represented by this descriptor.
     */
    @Override()
    public java.lang.Class getJavaClass() {
        return com.cdoframework.cdolib.service.framework.transfilter.xsd.PostTransaction.class;
    }

    /**
     * Method getNameSpacePrefix.
     * 
     * @return the namespace prefix to use when marshaling as XML.
     */
    @Override()
    public java.lang.String getNameSpacePrefix() {
        return _nsPrefix;
    }

    /**
     * Method getNameSpaceURI.
     * 
     * @return the namespace URI used when marshaling and
     * unmarshaling as XML.
     */
    @Override()
    public java.lang.String getNameSpaceURI() {
        return _nsURI;
    }

    /**
     * Method getValidator.
     * 
     * @return a specific validator for the class described by this
     * ClassDescriptor.
     */
    @Override()
    public org.exolab.castor.xml.TypeValidator getValidator() {
        return this;
    }

    /**
     * Method getXMLName.
     * 
     * @return the XML Name for the Class being described.
     */
    @Override()
    public java.lang.String getXMLName() {
        return _xmlName;
    }

    /**
     * Method isElementDefinition.
     * 
     * @return true if XML schema definition of this Class is that
     * of a global
     * element or element with anonymous type definition.
     */
    public boolean isElementDefinition() {
        return _elementDefinition;
    }

}
