/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.controllers;

import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import org.g2p.tracker.model.entities.ProveedoresSsoEntity;
import org.g2p.tracker.model.entities.WebsiteUsersEntity;
import org.g2p.tracker.model.entities.WebsiteUsersPerProveedoresOpenidEntity;
import org.g2p.tracker.model.entities.WebsiteUsersPerProveedoresOpenidEntityPK;
import org.g2p.tracker.model.models.BaseModel;
import org.g2p.tracker.openid.LoginPreProcessor;
import org.g2p.tracker.utils.Crypt;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;

import org.zkoss.zk.ui.ext.AfterCompose;


import org.zkoss.zul.Button;

import org.zkoss.zul.Datebox;

import org.zkoss.zul.Include;

import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;

public class AltaUsuarioController extends BaseController implements AfterCompose {

    private static final long serialVersionUID = -8499815584100190240L;

    //Roles Model
    protected WebsiteUsersEntity usuario = null;

    public WebsiteUsersEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(WebsiteUsersEntity usuario) {
        this.usuario = usuario;
    }

    //main control window
    protected Component usuarioDetail; //domain object detail

    //edit mode
    protected Component usuarioEdit; //edit panel
    protected Textbox usuarioLoginName;
    protected Textbox usuarioLoginPassword;
    protected Row usuarioLoginPassRow;
    protected Textbox usuarioNombre;
    protected Textbox usuarioApellido;
    protected Datebox usuarioFechaNacimiento;

    //buttons
    protected Button usuarioSave; //save button
    protected Button usuarioCancel; //cancel button

    public AltaUsuarioController() {
        super(false);
        usuario = new WebsiteUsersEntity();
    }

    public void onCreate$altaUsuarioWin(Event event) {
        if (getSession().getAttribute(CLAIMED_ID) == null) {
            usuarioLoginPassRow.setVisible(true);
        } else {
            usuarioLoginPassRow.setVisible(false);
        }
        setFocus();
    }

    public boolean isViewMode() {
        return !editMode;
    }

    private void setFocus() {
        usuarioLoginName.focus();
    }


    //-- edit mode control --//
    public void onClick$usuarioSave(Event event) {
        doSave(event);
    }

    public void onOk$altaUsuarioWin(Event event) {
        doSave(event);
    }

    public void doSave(Event event) {
        try {
            //validate
            validate();
            //store into db
            if (usuarioLoginPassRow.isVisible()) {
                usuario.setLoginPassword(Crypt.encryptPass(usuario.getLoginPassword()));
            }
            BaseModel.createEntity(usuario, true);

            if (getSession().getAttribute(CLAIMED_ID) != null) {
                Hashtable parameters = new Hashtable();
                parameters.put("userId", usuario.getUserId());

                WebsiteUsersEntity user = (WebsiteUsersEntity) BaseModel.findEntities("WebsiteUsersEntity.findByUserId", parameters).get(0);

                WebsiteUsersPerProveedoresOpenidEntity userPerProveedor = new WebsiteUsersPerProveedoresOpenidEntity();
                userPerProveedor.setClaimedId((String) getSession().getAttribute(CLAIMED_ID));
                userPerProveedor.setWebsiteUsersPerProveedoresOpenidEntityPK(new WebsiteUsersPerProveedoresOpenidEntityPK(user.getUserId(), (Integer) getSession().getAttribute(PROVEEDOR_SSO_ID)));
                BaseModel.createEntity(userPerProveedor, true);

                getSession().removeAttribute(PROVEEDOR_SSO_ID);
                getSession().removeAttribute(CLAIMED_ID);

                setUserInSession(usuario);
                setUserNameInSession(usuario.getNombre() + " " + usuario.getApellido());
            }

            Messagebox.show("Ud se ha registrado correctamente.");
            Executions.sendRedirect("/");
        } catch (Exception ex) {
            showMessage("Ocurrió un error mientras se intentaba crear el usuario: ", ex);
        }
    }

    public void onClick$usuarioCancel(Event event) {
        doCancel(event);
    }

    public void onCancel$altaUsuarioWin(Event event) {
        doCancel(event);
    }

    public void doCancel(Event event) {
        ((Include) getDesktop().getAttribute(INCLUDE)).setSrc(HOME_PAGE);
    }

    //--To be override--//
    /** Validate the input field */
    protected void validate() throws Exception {
        String loginName = usuarioLoginName.getValue();


        String password = null;
        if (usuarioLoginPassRow.isVisible()) {
            try {
                password = usuarioLoginPassword.getValue();

            } catch (Exception ex) {
                showMessage("Exception: ", ex);
            }
        }

        String nombre = usuarioNombre.getValue();
        String apellido = usuarioApellido.getValue();

        if (getSession().getAttribute(CLAIMED_ID) != null) {

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(usuarioFechaNacimiento.getValue());

            String date = String.format("%1$tY-%1$tm-%1$td 00:00:00", calendar);

            Hashtable parameters = new Hashtable();
            parameters.put("concat", nombre + apellido + date);

            if (BaseModel.findEntities("WebsiteUsersEntity.findByNameAndBirthday", parameters).size() != 0) {
                int option = Messagebox.show("Vaya! Sus datos están registrados en la Base de Datos. " +
                        "¿Desea asociar la cuenta OpenID utilizada con su perfil? " +
                        "Si acepta le será requerido que se identifique mediante otra de sus cuentas OpenId habilitadas, o mediante su cuenta local.", "Usuario ya registrado", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION);

                if (option == Messagebox.YES) {

                    WebsiteUsersEntity user = (WebsiteUsersEntity) BaseModel.findEntities("WebsiteUsersEntity.findByNameAndBirthday", parameters).get(0);

                    WebsiteUsersPerProveedoresOpenidEntity userPerProveedor = new WebsiteUsersPerProveedoresOpenidEntity();
                    userPerProveedor.setClaimedId((String) getSession().getAttribute(CLAIMED_ID));
                    userPerProveedor.setWebsiteUsersPerProveedoresOpenidEntityPK(new WebsiteUsersPerProveedoresOpenidEntityPK(user.getUserId(), (Integer) getSession().getAttribute(PROVEEDOR_SSO_ID)));
                    BaseModel.createEntity(userPerProveedor, true);

                    getSession().removeAttribute(PROVEEDOR_SSO_ID);
                    getSession().removeAttribute(CLAIMED_ID);

                    parameters.clear();
                    parameters.put("userId", user.getUserId());
                    List proveedoresSSO = BaseModel.findEntities("ProveedoresSsoEntity.findByUserId", parameters);

                    if (proveedoresSSO.size() != 0) {
                        ProveedoresSsoEntity proveedorSSO = (ProveedoresSsoEntity) proveedoresSSO.get(0);
                        try {
                            String urlOpenidLogin = LoginPreProcessor.processRequest(getHttpRequest(), getHttpResponse(), proveedorSSO.getUrlDiscovery());
                            Executions.sendRedirect(urlOpenidLogin);
                        } catch (Exception ex) {
                            showMessage("Excepcion: ", ex);
                        }
                    } else {
                        ((Include) getDesktop().getAttribute(INCLUDE)).setSrc(LOGIN_PAGE);
                    }
                }
            } else {
            }
        }
    }
}
