
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.controllers;

//~--- non-JDK imports --------------------------------------------------------
import org.g2p.tracker.model.entities.AccesoMenuEntity;
import org.g2p.tracker.model.models.AccesoMenuModel;

import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Include;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Vbox;

//~--- JDK imports ------------------------------------------------------------

import java.util.Hashtable;
import java.util.Iterator;

public class BasePageController extends BaseController implements EventListener {

    private static final long serialVersionUID = 144203921841206801L;
    protected Include include;
    private Vbox navBar;
    protected Button switchButton;
    Session session;

    public BasePageController() {
    }

    public void onCreate$baseWin(Event event) {
        session = Sessions.getCurrent();

        session.setAttribute("User", 1);

        setNavBarItem("HomePage.zul");
    }

    public void setNavBarItem(String groupName) {
        Toolbarbutton button;
        Hashtable parameters = new Hashtable();

        parameters.put("userId", session.getAttribute("User"));
        parameters.put("GroupName", groupName);

        Iterator menues = AccesoMenuModel.findEntities("AccesoMenuEntity.findByUsuarioId", parameters).iterator();

        navBar.getChildren().clear();
        while (menues.hasNext()) {
            navBar.appendChild(new Separator());

            AccesoMenuEntity menu = (AccesoMenuEntity) menues.next();

            button = new Toolbarbutton(menu.getMenuId().getNombre());
            button.setAttribute("page", menu.getMenuId().getUrl());
            button.setAttribute("groupName", menu.getMenuId().getNombre());
            navBar.appendChild(button);
            button.addEventListener("onClick", this);
        }
    }

    @Override
    public void onEvent(Event arg0) throws Exception {
        include.setSrc(arg0.getTarget().getAttribute("page").toString());
        setNavBarItem(arg0.getTarget().getAttribute("groupName").toString());
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
