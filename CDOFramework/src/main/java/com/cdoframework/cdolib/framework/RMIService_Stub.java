// Stub class generated by rmic, do not edit.
// Contents subject to change without notice.

package com.cdoframework.cdolib.framework;

public final class RMIService_Stub
    extends java.rmi.server.RemoteStub
    implements com.cdoframework.cdolib.framework.IRMIService, java.rmi.Remote
{
    private static final long serialVersionUID = 2;
    
    private static java.lang.reflect.Method $method_handleTrans_0;
    
    static {
	try {
	    $method_handleTrans_0 = com.cdoframework.cdolib.framework.IRMIService.class.getMethod("handleTrans", new java.lang.Class[] {com.cdoframework.cdolib.data.cdo.CDO.class});
	} catch (java.lang.NoSuchMethodException e) {
	    throw new java.lang.NoSuchMethodError(
		"stub class initialization failed");
	}
    }
    
    // constructors
    public RMIService_Stub(java.rmi.server.RemoteRef ref) {
	super(ref);
    }
    
    // methods from remote interfaces
    
    // implementation of handleTrans(CDO)
    public com.cdoframework.cdolib.data.cdo.CDO handleTrans(com.cdoframework.cdolib.data.cdo.CDO $param_CDO_1)
	throws java.rmi.RemoteException
    {
	try {
	    Object $result = ref.invoke(this, $method_handleTrans_0, new java.lang.Object[] {$param_CDO_1}, 4087504080812285065L);
	    return ((com.cdoframework.cdolib.data.cdo.CDO) $result);
	} catch (java.lang.RuntimeException e) {
	    throw e;
	} catch (java.rmi.RemoteException e) {
	    throw e;
	} catch (java.lang.Exception e) {
	    throw new java.rmi.UnexpectedException("undeclared checked exception", e);
	}
    }
}
