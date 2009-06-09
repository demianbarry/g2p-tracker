/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.composers;

import org.g2p.tracker.controllers.Constants;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.DataBinder;
import org.zkoss.zul.Messagebox;

/**
 *
 * @author Administrador
 */
public class BaseComposer extends GenericForwardComposer implements Constants {

    //ZK databinder
    protected DataBinder binder;

    protected void showMessage(String msg) {
        showMessage(msg, null);
    }

    protected void showMessage(String msg, Exception ex) {
        try {
            Messagebox.show(msg + ex.getMessage());
            ex.printStackTrace();
        } catch (InterruptedException ex1) {
            System.out.println("ERROR MOSTRANDO MENSAJE: " + ex1.getMessage());
            ex1.printStackTrace();
        }
    }

    protected DataBinder getDataBinder() {
        if (binder == null) {
            binder = (DataBinder) self.getVariable("binder", false);
        }
        if (binder == null) {
            binder = new DataBinder();
        }
        return binder;
    }
}
