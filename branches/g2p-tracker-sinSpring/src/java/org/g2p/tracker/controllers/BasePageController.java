/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import org.g2p.tracker.model.entities.AccesoMenuEntity;

import org.g2p.tracker.openid.NoAutentificadoException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Include;

import java.util.Hashtable;
import java.util.Iterator;
import org.g2p.tracker.model.models.BaseModel;
//import org.zkoss.zul.Menu;
import org.g2p.tracker.openid.LoginPostProcessor;
import org.zkoss.zul.Label;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Vbox;

public class BasePageController extends BaseController {

    private static final long serialVersionUID = 144203921841206801L;
    protected Include include;
    private Vbox navBar;
    private Toolbarbutton loginLabel;
    private Toolbarbutton logoutLabel;
    private Toolbarbutton registerLabel;
    private Vbox loginBox;

    public BasePageController() {
        super(false);
    }

    public void onCreate$baseWin(Event event) {
        try {
            getDesktop().setAttribute(INCLUDE, include);
            getDesktop().setAttribute(BASE_PAGE_CONTROLLER, this);
            // Muestro/oculto los links de login/logout según corresponda
            if (getUserIdFromSession() == null) {
                loginLabel.setVisible(true);
                logoutLabel.setVisible(false);
                registerLabel.setVisible(true);
            } else {
                logoutLabel.setVisible(true);
                loginLabel.setVisible(false);
                registerLabel.setVisible(false);
            }

            if (getUserIdFromSession() == null && getSession().getAttribute(CLAIMED_ID) == null) {
                LoginPostProcessor.processRequest(getHttpRequest(), getHttpResponse());
            } else {
                if (getUserIdFromSession() == null) {
                    include.setSrc("AltaUsuario.zul");
                }
            }

        } catch (NoAutentificadoException ex) {
            System.out.println("NoAutentificadoException: " + ex.getMessage());
        } catch (ServletException ex) {
            System.out.println("ServletException: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("IOException: " + ex.getMessage());
        } catch (NullPointerException ex) {
            System.out.println("NullPointerException: " + ex.getMessage());
        } finally {
            // Arranco en la HomePage
            setNavBarItem(HOME_PAGE);
        }
    }

    /**
     * Este método llena la barra de navegación recuperando la información contenida en la base de datos.
     *
     * @param groupName La página actual, a través de la cual se recuperan todos sus hijos
     */
    public void setNavBarItem(String groupName) {
        AccesoMenuEntity menu;
        Toolbarbutton button;
        Hashtable parameters;
        Iterator menues;

        doLoginLogout();

        // Si estoy mostrando la HomePage limpio la barra
        if (HOME_PAGE.equals(groupName)) {
            while (navBar.getChildren().size() != 0) {
                navBar.removeChild(navBar.getLastChild());
            }
            // Agrego la referencia a la HomePage,
            button = new Toolbarbutton("Página principal");
            navBar.appendChild(button);
            // Seteo el controlador como responsable de capturar el evento de click
            button.addEventListener("onClick", new EventListener() {

                @Override
                public void onEvent(Event arg0) throws Exception {
                    if (getUserIdFromSession() == null) {
                        include.setSrc(LOGIN_PAGE);
                    } else {
                        include.setSrc(HOME_PAGE);
                    }
                    setNavBarItem(HOME_PAGE);
                }
            });
        }

        parameters = new Hashtable();

        // Obtengo el userId de la sesión
        parameters.put("userId", getUserIdFromSession() != null ? getUserIdFromSession() : 0);

        parameters.put("GroupName", groupName);

        // Traigo todos los "hijos" de la página actual.
        menues = BaseModel.findEntities("AccesoMenuEntity.findByUsuarioId", parameters).iterator();

        // Si el menu actual tiene "hijos", re-armo el menu
        if (menues.hasNext()) {
            // Limpio la barra de navegación
            while (navBar.getChildren().size() != 0) {
                navBar.removeChild(navBar.getLastChild());
            }
            // Agrego la referencia a la HomePage,
            button = new Toolbarbutton("Página principal");
            button.setAttribute("page", HOME_PAGE);
            navBar.appendChild(button);
            // Seteo el controlador como responsable de capturar el evento de click
            button.addEventListener("onClick", new EventListener() {

                @Override
                public void onEvent(Event arg0) throws Exception {
                    include.setSrc(arg0.getTarget().getAttribute("page").toString());
                    setNavBarItem(arg0.getTarget().getAttribute("page").toString());
                }
            });

        }

        // Por cada resultado recuperado, creo la referencia en la NavBar
        while (menues.hasNext()) {
            System.out.println("----->");
            if (navBar.getChildren().size() > 0) {
                Separator separator = new Separator();
                separator.setBar(true);
                navBar.appendChild(separator);
            }

            menu = (AccesoMenuEntity) menues.next();

            button = new Toolbarbutton(menu.getMenuId().getNombre());
            // agrego un atributo al botón para obtener luego en el listener
            // del evento la página a la cual tengo que redirigir el "include"
            button.setAttribute("page", menu.getMenuId().getUrl());
            button.addEventListener("onClick", new EventListener() {

                @Override
                public void onEvent(Event arg0) throws Exception {
                    include.setSrc(arg0.getTarget().getAttribute("page").toString());
                    setNavBarItem(arg0.getTarget().getAttribute("page").toString());
                }
            });
            button.setTooltiptext(menu.getMenuId().getDescripcion());
            navBar.appendChild(button);
        }
    }

    public void onClick$loginLabel(Event event) {
        include.setSrc(LOGIN_PAGE);
        setNavBarItem(HOME_PAGE);
    }

    public void onClick$logoutLabel(Event event) {
        setUserIdInSession(null);
        include.setSrc(LOGIN_PAGE);
        setNavBarItem(HOME_PAGE);
    }

    public void onClick$registerLabel(Event event) {
        include.setSrc(REGISTER_PAGE);
        setNavBarItem(HOME_PAGE);
    }

    public void doLoginLogout() {
        // Muestro/oculto el mensaje de bienvenida para el usuario según corresponda
        Integer userId = getUserIdFromSession();
        if (userId != null && userId != 0) {
            logoutLabel.setVisible(true);
            loginLabel.setVisible(false);
            registerLabel.setVisible(false);
            if (!loginBox.hasFellow("welcomeMessage")) {
                String userName = getUserNameFromSession();
                Label label = new Label("Bienvenido a la aplicación, " + userName);
                label.setId("welcomeMessage");
                label.setStyle("float:left bottom");
                loginBox.appendChild(label);
            }
        } else {
            logoutLabel.setVisible(false);
            registerLabel.setVisible(true);
            loginLabel.setVisible(true);
            if (loginBox.hasFellow("welcomeMessage")) {
                loginBox.removeChild(loginBox.getFellow("welcomeMessage"));
            }
        }

    }
}
