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
import org.zkoss.zul.Label;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Vbox;

/**
 *
 * @author Administrador
 */
public class LoginBoxComposer extends GenericForwardComposer {

    private Vbox loginBox;

    private Toolbarbutton loginLabel;

    private Toolbarbutton logoutLabel;

    private Session session;

    public LoginBoxComposer() {
        session = Sessions.getCurrent();
    }

    @Override
    public void doAfterCompose(Component div) throws Exception {
                        super.doAfterCompose(div);
                        if(session.getAttribute("User") == null){
                            loginLabel.setVisible(true);
                            logoutLabel.setVisible(false);
                        } else {
                            logoutLabel.setVisible(true);
                            loginLabel.setVisible(false);
                        }
                        loginBox = (Vbox) div.getFellow("loginBox");
                        Integer userId = (Integer) Sessions.getCurrent().getAttribute("User");
                        if(userId != null && userId != 0) {
                            String userName = (String) Sessions.getCurrent().getAttribute("UserName");
                            Label label = new Label("Bienvenido a la aplicación, "+userName);
                            label.setStyle("float:left bottom");
                            loginBox.appendChild(label);
                        }
    }

    public void onClick$logoutLabel(Event event) {
        session.setAttribute("User", null);
        Executions.sendRedirect("BasePage.zul");
    }

}


