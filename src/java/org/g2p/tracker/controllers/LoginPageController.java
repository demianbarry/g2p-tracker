/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.controllers;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Include;

/**
 *
 * @author Administrador
 */
public class LoginPageController extends BaseController {

    public LoginPageController() {
        super(false);
    }

    /*public LoginPageController() {
    super(false);
    }*/
    private static final long serialVersionUID = -4738510223263568936L;

    public void onClick$okButton(Event event) {
        // Seteo el usuario y vuelvo a la HomePage
        setUserIdInSession(1);
        setUserNameInSession("Juan Manuel");
        ((Include) getDesktop().getAttribute(INCLUDE)).setSrc(HOME_PAGE);
        ((BasePageController) getDesktop().getAttribute(BASE_PAGE_CONTROLLER)).setNavBarItem(HOME_PAGE);
    }

    public void onClick$cancelButton(Event event) {
        // Vuelvo a la HomePage
        ((Include) getDesktop().getAttribute(INCLUDE)).setSrc(HOME_PAGE);
        ((BasePageController) getDesktop().getAttribute(BASE_PAGE_CONTROLLER)).setNavBarItem(HOME_PAGE);
    }
}
