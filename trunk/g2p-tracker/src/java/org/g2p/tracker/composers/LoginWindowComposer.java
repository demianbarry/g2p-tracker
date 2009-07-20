/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.composers;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

/**
 *
 * @author Administrador
 */
public class LoginWindowComposer extends GenericForwardComposer {

    //AccesoMenuModel accesoMenuModel;
    private Window loginWin;
    private Textbox username;
    private Textbox password;
    private Session session;
    private Button okButton;


    public LoginWindowComposer() {
        session = Sessions.getCurrent();
    }



    public void onClick$okButton(Event event) {
        session.setAttribute("User", 1);
        session.setAttribute("UserName", "Juan Manuel");
        //loginWin.setVisible(false);
        Executions.sendRedirect("BasePage.zul");        
    }

    public void onOK$loginWin() {
        session.setAttribute("User", 1);
        session.setAttribute("UserName", "Juan Manuel");
        //loginWin.setVisible(false);
        Executions.sendRedirect("BasePage.zul");
    }
}


