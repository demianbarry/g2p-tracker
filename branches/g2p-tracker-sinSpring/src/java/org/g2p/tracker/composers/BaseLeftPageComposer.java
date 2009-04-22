/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.composers;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Vbox;

/**
 *
 * @author Administrador
 */
public class BaseLeftPageComposer implements Composer {

    //AccesoMenuModel accesoMenuModel;

    //MenuModel menuModel;

    private Vbox navBar;

    protected Integer contador = 0;

    @Override
    public void doAfterCompose(Component div) throws Exception {
                        navBar = (Vbox) div.getFellow("navBar");

                        Toolbarbutton button;

                        Session session = Sessions.getCurrent();
                        session.setAttribute("User", "Juan");
                       
                        button = new Toolbarbutton("Home");
                        button.setHref("/AbmcRoles.zul");
                        navBar.appendChild(button);
    }

}


