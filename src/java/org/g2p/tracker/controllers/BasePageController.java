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
import org.zkoss.zul.Include;

import java.util.Hashtable;
import java.util.Iterator;
import org.g2p.tracker.model.models.BaseModel;
//import org.zkoss.zul.Menu;
import org.zkoss.zul.Panelchildren;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Vbox;

public class BasePageController extends BaseController implements EventListener {

    private static final long serialVersionUID = 144203921841206801L;
    protected Include include;
    private Vbox navBar;
    private Panelchildren panelChildren;
    Session session;

    public BasePageController() {
    }

    public void onCreate$baseWin(Event event) {
        session = Sessions.getCurrent();
        
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

        System.out.println("------------> "+session.getAttribute("User"));

        // Obtengo el userId de la sesión
        parameters.put("userId", session.getAttribute("User") != null ? session.getAttribute("User") : 0);

        parameters.put("GroupName", groupName);

        // Traigo todos los "hijos" de la página actual.
        Iterator menues = BaseModel.findEntities("AccesoMenuEntity.findByUsuarioId", parameters).iterator();

        // Si el menu actual tiene "hijos", re-armo el menu
        if (menues.hasNext()) {
            // Limpio la barra de navegación
            while (navBar.getChildren().size() != 0) {
                navBar.removeChild(navBar.getLastChild());
            }


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

                if (navBar.getChildren().size() > 0) {
                    Separator separator = new Separator();
                    separator.setBar(true);
                    navBar.appendChild(separator);
                }

                AccesoMenuEntity menu = (AccesoMenuEntity) menues.next();

                button = new Toolbarbutton(menu.getMenuId().getNombre());
                // agrego un atributo al botón para obtener luego en el listener
                // del evento la página a la cual tengo que redirigir el "include"
                button.setAttribute("page", menu.getMenuId().getUrl());
                button.addEventListener("onClick", this);
                button.setTooltiptext(menu.getMenuId().getDescripcion());
                //button.setOrient("vertical");
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
