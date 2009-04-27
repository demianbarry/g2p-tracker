/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.controllers;

import org.g2p.tracker.model.entities.AccesoMenuEntity;

import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Include;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Vbox;

import java.util.Hashtable;
import java.util.Iterator;
import org.g2p.tracker.model.models.BaseModel;

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

        // Seteo
        session.setAttribute("User", 1);

        // Arranco en la HomePage
        setNavBarItem("HomePage.zul");
    }

    /**
     * Este método llena la barra de navegación recuperando la información contenida en la base de datos.
     *
     * @param groupName La página actual, a través de la cual se recuperan todos sus hijos
     */
    public void setNavBarItem(String groupName) {
        Toolbarbutton button;
        Hashtable parameters = new Hashtable();

        // Obtengo el userId de la sesión
        parameters.put("userId", session.getAttribute("User"));

        parameters.put("GroupName", groupName);

        // Traigo todos los "hijos" de la página actual.
        Iterator menues = BaseModel.findEntities("AccesoMenuEntity.findByUsuarioId", parameters).iterator();

        // Si el menu actual tiene "hijos", re-armo el menu
        if (menues.hasNext()) {
            // Limpio la barra de navegación
            navBar.getChildren().clear();

            // Agrego la referencia a la HomePage, si no estoy en la HomePage
            if (!"HomePage.zul".equalsIgnoreCase(groupName)) {
                button = new Toolbarbutton("Página principal");
                button.setAttribute("page", "HomePage.zul");
                navBar.appendChild(button);
                // Seteo el controlador como responsable de capturar el evento de click
                button.addEventListener("onClick", this);
            }

            // Por cada resultado recuperado, creo la referencia en la NavBar
            while (menues.hasNext()) {
                AccesoMenuEntity menu = (AccesoMenuEntity) menues.next();

                navBar.appendChild(new Separator());

                button = new Toolbarbutton(menu.getMenuId().getNombre());
                // agrego un atributo al botón para obtener luego en el listener
                // del evento la página a la cual tengo que redirigir el "include"
                button.setAttribute("page", menu.getMenuId().getUrl());
                button.addEventListener("onClick", this);
                navBar.appendChild(button);
            }
        }
    }

    @Override
    public void onEvent(Event arg0) throws Exception {
        // cambio el "include" recuperando el atributo "page" del botón
        include.setSrc(arg0.getTarget().getAttribute("page").toString());
        setNavBarItem(arg0.getTarget().getAttribute("page").toString());
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
