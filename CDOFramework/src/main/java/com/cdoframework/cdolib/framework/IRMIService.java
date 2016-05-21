/**
 * 
 * $Header: /CVSData/Frank/CVSROOT/CDOForum/CDOFrameBase/Source/com/cdoframework/cdolib/framework/IRMIService.java,v 1.1 2008/04/12 13:44:36 Frank Exp $
 * $Log: IRMIService.java,v $
 * Revision 1.1  2008/04/12 13:44:36  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2008/03/05 01:34:07  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/12/15 10:06:09  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/08/31 07:08:26  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/08/29 07:46:25  Frank
 * *** empty log message ***
 *
 * Revision 1.1  2007/08/08 09:32:18  Frank
 * *** empty log message ***
 *
 * Revision 1.3  2006/08/22 02:35:09  linshihong
 * *** empty log message ***
 *

 *
 */

package com.cdoframework.cdolib.framework;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.cdoframework.cdolib.data.cdo.CDO;


public interface IRMIService extends Remote
{	
	CDO handleTrans(CDO cvRequest) throws RemoteException;
}

