/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.controllers;

import java.util.Hashtable;
import java.util.Iterator;
import org.g2p.tracker.model.entities.AccesoMenuEntity;
import org.g2p.tracker.model.entities.BaseEntity;
import org.g2p.tracker.model.models.AccesoMenuModel;
import org.g2p.tracker.model.models.WebsiteUserModel;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Include;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Vbox;

public class BasePageController extends BaseController {

    protected Include include;
    protected Button switchButton;
    private Vbox navBar;
    private AccesoMenuModel accesoMenuModel;

    public BasePageController() {
        accesoMenuModel = new AccesoMenuModel();
    }

    public void onCreate$baseWin(Event event) {

        Session session = Sessions.getCurrent();
        session.setAttribute("User", "Juan");

        setNavBarItem();

    }

    public void setNavBarItem() {
        Toolbarbutton button;
        Hashtable<String, Integer> parameters = new Hashtable<String, Integer>();
        parameters.put("userId", 1);
        Iterator menues = accesoMenuModel.findEntities("AccesoMenuEntity.findByUsuarioId", parameters).iterator();

        while (menues.hasNext()) {

            navBar.appendChild(new Separator());

            AccesoMenuEntity menu = (AccesoMenuEntity) menues.next();
            button = new Toolbarbutton(menu.getMenuId().getNombre());
            button.setAttribute("page", menu.getMenuId().getUrl());
            navBar.appendChild(button);
            button.addEventListener("onClick", new EventListener() {

                @Override
                public void onEvent(Event arg0) throws Exception {
                    include.setSrc(arg0.getTarget().getAttribute("page").toString());
                }
            });
        }
    }
}
