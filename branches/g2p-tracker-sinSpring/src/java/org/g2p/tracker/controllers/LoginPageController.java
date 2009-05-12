/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.controllers;

import org.g2p.tracker.model.entities.ProveedoresSsoEntity;
import org.g2p.tracker.model.models.ProveedoresSSOModel;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zkplus.databind.DataBinder;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Include;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Toolbarbutton;

/**
 *
 * @author Administrador
 */
public class LoginPageController extends BaseController {

    private static final long serialVersionUID = -4738510223263568936L;
    protected ProveedoresSSOModel proveedoresSSOModel = null;
    private Grid localLoginGrid;
    private Listbox proveedoresList;
    private Toolbarbutton localLoginButton;

    public LoginPageController() {
        super(false);
        proveedoresSSOModel = new ProveedoresSSOModel();
    }

    public void onCreate$loginWin(Event event) {
        binder = (DataBinder) getVariable("binder", true);
        localLoginGrid.setVisible(false);
    }

    public void onClick$okButton(Event event) {
        // Seteo el usuario y vuelvo a la HomePage
        setUserIdInSession(4);
        setUserNameInSession("Juan Manuel");
        ((Include) getDesktop().getAttribute(INCLUDE)).setSrc(HOME_PAGE);
        ((BasePageController) getDesktop().getAttribute(BASE_PAGE_CONTROLLER)).setNavBarItem(HOME_PAGE);
    }

    public void onClick$cancelButton(Event event) {
        // Vuelvo a la HomePage
        //((Include) getDesktop().getAttribute(INCLUDE)).setSrc(HOME_PAGE);
        //((BasePageController) getDesktop().getAttribute(BASE_PAGE_CONTROLLER)).setNavBarItem(HOME_PAGE);
        localLoginGrid.setVisible(false);
        localLoginButton.setVisible(true);
    }

    public void onSelect$proveedoresList(Event event) {
        Executions.sendRedirect("/MainServ?DiscoveryURL=" + ((ProveedoresSsoEntity) proveedoresSSOModel.getSelected()).getUrlDiscovery());
    }

    public ProveedoresSSOModel getProveedoresSSOModel() {
        return proveedoresSSOModel;
    }

    public void setProveedoresSSOModel(ProveedoresSSOModel rolesModel) {
        this.proveedoresSSOModel = rolesModel;
    }

    public void onClick$localLoginButton(Event event) {
        localLoginButton.setVisible(false);
        localLoginGrid.setVisible(true);
    }
}
