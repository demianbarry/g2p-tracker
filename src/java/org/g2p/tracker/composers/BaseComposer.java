/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.composers;

import org.g2p.tracker.controllers.Constants;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Messagebox;

/**
 *
 * @author Administrador
 */
public class BaseComposer extends GenericForwardComposer implements Constants {
    protected void showMessage(String msg) {
        showMessage(msg,null);
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
}
