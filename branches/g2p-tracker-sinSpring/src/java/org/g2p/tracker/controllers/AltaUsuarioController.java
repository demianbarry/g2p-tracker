/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.controllers;

import java.util.Date;
import java.util.Hashtable;
import org.g2p.tracker.model.entities.WebsiteUsersEntity;
import org.g2p.tracker.model.models.BaseModel;
import org.g2p.tracker.utils.Crypt;
import org.zkoss.zk.ui.Component;
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
        //validate
        validate();
        try {
            //store into db
            usuario.setLoginPassword(Crypt.encryptPass(usuario.getLoginPassword()));
            BaseModel.createEntity(usuario, true);
            Messagebox.show("Ud se ha registrado correctamente.");
            gotoHome();
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
    protected void validate() {
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

        Date fechaNacimiento = usuarioFechaNacimiento.getValue();

        Hashtable parameters = new Hashtable();
        parameters.put("concat", nombre + apellido + fechaNacimiento);

        if (BaseModel.findEntities("WebsiteUsersEntity.findByNameAndBirthday", parameters).size() != 0) {
            System.out.println("OUCH!");
        }
    }


}
