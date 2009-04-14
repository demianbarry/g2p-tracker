/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.composers;

import org.g2p.tracker.model.models.AccesoMenuModel;
import org.g2p.tracker.model.models.MenuModel;
import org.springframework.context.annotation.Scope;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Vbox;


/**
 *
 * @author Administrador
 */
@Scope("idspace")
public class BaseLeftPageComposer implements Composer {

    AccesoMenuModel accesoMenuModel;

    MenuModel menuModel;

    protected Integer contador = 0;

    @Override
    public void doAfterCompose(Component div) throws Exception {
                        Vbox navBar = (Vbox) div.getFellow("navBar");
                        Toolbarbutton button;
                        

                        
                        button = new Toolbarbutton("Home");
                        button.setHref("/AbmcRoles.zul");
                        navBar.appendChild(button);



                        System.out.println("-----> CONTADOR: "+contador++);
    }



}