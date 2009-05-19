/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.controllers;

import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.g2p.tracker.model.entities.ProveedoresSsoEntity;
import org.g2p.tracker.model.entities.WebsiteUsersEntity;
import org.g2p.tracker.model.models.BaseModel;
import org.g2p.tracker.model.models.ProveedoresSSOModel;
import org.g2p.tracker.openid.LoginPreProcessor;
import org.g2p.tracker.utils.Crypt;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zkplus.databind.DataBinder;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Include;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
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
    private Textbox username;
    private Textbox password;

    public LoginPageController() {
        super(false);
        proveedoresSSOModel = new ProveedoresSSOModel();
    }

    public void onCreate$loginWin(Event event) {
        binder = (DataBinder) getVariable("binder", true);
        localLoginGrid.setVisible(false);
        username.setFocus(true);
    }

    public void onClick$okButton(Event event) {
        // Seteo el usuario y vuelvo a la HomePage

        String loginName = username.getValue();
        String passWord = password.getValue();

        try {
            passWord = Crypt.encryptPass(passWord);
        } catch (Exception ex) {
            showMessage("Error encriptando clave: ", ex);
        }

        Hashtable parameters = new Hashtable();
        parameters.put("loginName", loginName);
        parameters.put("loginPassword", passWord);

        List websiteUsersList = BaseModel.findEntities("WebsiteUsersEntity.findByLogin", parameters);

        if (websiteUsersList.size() != 0) {
            WebsiteUsersEntity websiteUser = (WebsiteUsersEntity) websiteUsersList.get(0);
            setUserIdInSession(websiteUser.getUserId());
            setUserNameInSession(websiteUser.getNombre() + " " + websiteUser.getApellido());
            ((Include) getDesktop().getAttribute(INCLUDE)).setSrc(HOME_PAGE);
            ((BasePageController) getDesktop().getAttribute(BASE_PAGE_CONTROLLER)).setNavBarItem(HOME_PAGE);
        } else {
            try {
                Messagebox.show("El usuario y/o la clave ingresada no son válidos, intente de nuevo.");
            } catch (InterruptedException ex) {
                showMessage("Error mostrando mensaje: ", ex);
            } finally {
                password.setValue(null);
                username.setFocus(true);
            }
        }


    }

    public void onClick$cancelButton(Event event) {
        // Vuelvo a la HomePage
        proveedoresList.setVisible(true);
        localLoginGrid.setVisible(false);
        localLoginButton.setVisible(true);
    }

    public void onSelect$proveedoresList(Event event) {
        try {
            String urlOpenidLogin = LoginPreProcessor.processRequest(getHttpRequest(), getHttpResponse(), ((ProveedoresSsoEntity) proveedoresSSOModel.getSelected()).getUrlDiscovery());
            Executions.sendRedirect(urlOpenidLogin);
        } catch (Exception ex) {
            showMessage("Excepcion: ", ex);
        }
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
        proveedoresList.setVisible(false);
    }
}
