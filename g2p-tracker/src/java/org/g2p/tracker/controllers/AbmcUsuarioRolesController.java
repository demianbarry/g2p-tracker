/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.controllers;

import java.util.Collection;
import java.util.List;

import org.g2p.tracker.model.entities.RolesPerWebsiteUsersEntity;
import org.g2p.tracker.model.entities.RolesPerWebsiteUsersEntityPK;
import org.g2p.tracker.model.entities.WebsiteUsersEntity;
import org.g2p.tracker.model.models.RolesModel;
import org.g2p.tracker.model.models.RolesPerWebsiteUsersModel;
import org.g2p.tracker.model.models.WebsiteUserModel;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.KeyEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zkplus.databind.DataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;

public class AbmcUsuarioRolesController extends BaseController {

    private static final long serialVersionUID = -484488349926570947L;

    //Roles Model
    protected WebsiteUserModel websiteUserModel = null;
    protected RolesModel rolesModel = null;
    protected RolesPerWebsiteUsersModel rolesPerWebsiteUsersModel = null;

    //main control window
    protected Listbox usersList; //domain object summary list
    protected Listbox usuarioRolesList; //domain object summary list
    protected Component usuarioRolesDetail; //domain object detail

    //edit mode
    protected Component usuarioRolesEdit; //edit panel
    protected Label userId;
    protected Combobox usuarioRolIdCombobox;
    protected Datebox usuarioRolDesde;
    protected Datebox usuarioRolHasta;

    //buttons
    protected Button usuarioRolCreate; //new button
    protected Button usuarioRolUpdate; //edit button
    protected Button usuarioRolDelete; //delete button
    protected Button usuarioRolSave; //save button
    protected Button usuarioRolCancel; //cancel button

    public AbmcUsuarioRolesController() {
        super(true);
        websiteUserModel = new WebsiteUserModel();
        rolesModel = new RolesModel();
        rolesPerWebsiteUsersModel = new RolesPerWebsiteUsersModel();
    }

    //-- Initialization --//
    public void onCreate$abmcUsuarioRolesWin(Event event) {
        // Obtengo el DataBinder que instancia la página
        binder = (DataBinder) getVariable("binder", true);

        final List usersListModel = (List) usersList.getModel();

        if (!usersListModel.isEmpty()) {
            websiteUserModel.setSelected((WebsiteUsersEntity) usersListModel.get(0));
            binder.loadComponent(usuarioRolesList);
        }

        final List model = (List) usuarioRolesList.getModel();
        if (!model.isEmpty()) {
            websiteUserModel.setRolSelected((RolesPerWebsiteUsersEntity) model.get(0));
            binder.loadComponent(usuarioRolesDetail);
        }
        binder.loadComponent(usuarioRolesList);
        setFocus();
    }

    //Cuando selecciono un usuario de la lista muestro sus roles
    public void onSelect$usersList(Event event) {
        binder.loadComponent(usuarioRolesList);
    }

    //Cuando selecciono un rol del usuario muestro las fechas//
    public void onSelect$usuarioRolesList(Event event) {
        final int index = usuarioRolesList.getSelectedIndex();
        if (index >= 0) {
            _lastSelectedIndex = index;
            _create = false;
        }

        editMode = (editMode == true ? false : editMode);
        binder.loadComponent(usuarioRolesDetail); //reload visible to force refresh
    }

    //-- view mode control --//
    public void onCtrlKey$abmcUsuarioRolesWin(Event event) {
        final List items = usuarioRolesList.getItems();
        if (!items.isEmpty() && (!editMode || !_create)) {
            final int keycode = ((KeyEvent) event).getKeyCode();
            if (keycode == KeyEvent.DOWN || keycode == KeyEvent.UP) {
                //handle no selected item case
                if (usuarioRolesList.getSelectedIndex() < 0) { //no selected item
                    //try our best to guess one
                    if (_lastSelectedIndex >= 0) {
                        final int index = Math.min(items.size() - 1, _lastSelectedIndex);
                        usuarioRolesList.setSelectedIndex(index);
                        Events.sendEvent(new SelectEvent("onSelect", usuarioRolesList, usuarioRolesList.getSelectedItems()));
                    }
                }
                usuarioRolesList.focus();
            }
        }
    }

    public RolesModel getRolesPerWebsiteUsersModel() {
        return rolesModel;
    }

    public void setUsuarioRolesModel(RolesModel rolesModel) {
        this.rolesModel = rolesModel;
    }

    public void refreshModel() {
        binder.loadAttribute(usuarioRolesList, "model"); //reload model to force refresh
    }

    public boolean isViewMode() {
        return !editMode;
    }

    public boolean isNotSelected() {
        return this.websiteUserModel.getRolSelected() == null;
    }

    private void setFocus() {
        if (editMode) {
            usuarioRolIdCombobox.focus();
        } else {
            if (((Collection) usuarioRolesList.getModel()).isEmpty()) {
                //no result in list, focus on new button
                usuarioRolCreate.focus();
            } else {
                if (_create) {
                    usuarioRolCreate.focus();
                } else {
                    usuarioRolesList.focus();
                }
            }
        }
    }

    public void onClick$usuarioRolCreate(Event event) {
        if (isViewMode()) {
            //prepare a new UsuarioRolesEntity
            _tmpSelected = websiteUserModel.getRolSelected();
            _create = true;
            websiteUserModel.setRolSelected(new RolesPerWebsiteUsersEntity());
            websiteUserModel.getRolSelected().setRolesPerWebsiteUsersPK(new RolesPerWebsiteUsersEntityPK());
            websiteUserModel.getRolSelected().getRolesPerWebsiteUsersPK().setUserId(((WebsiteUsersEntity) websiteUserModel.getSelected()).getUserId());
            //switch to edit mode
            setEditMode(true);
        }
    }

    //-- edit mode control --//
    public void onClick$usuarioRolSave(Event event) {
        if (isEditMode()) {
            //validate
            validate();

            //save into bean
            binder.saveComponent(usuarioRolesEdit); //reload model to force refresh

            websiteUserModel.getRolSelected().getRolesPerWebsiteUsersPK().setRolId(websiteUserModel.getRolSelected().getRoles().getRolId());

            try {
                //store into db
                if (_create) {

                    System.out.println(" ---> rolID: " + this.websiteUserModel.getRolSelected().getRolesPerWebsiteUsersPK().getRolId() + "--- rolNombre: " + this.websiteUserModel.getRolSelected().getRoles().getRolId());

                    this.websiteUserModel.persistRol();

                } else {
                    this.websiteUserModel.mergeRol();
                }
            } catch (Exception ex) {
                showMessage("Ocurrió un error mientras intentaba modificar un ítem: ", ex);
                websiteUserModel.setRolSelected((RolesPerWebsiteUsersEntity) usuarioRolesList.getModel().getElementAt(0));
            }

            //refresh the usuarioRolesList
            refreshModel();
            //switch to view mode
            setEditMode(false);
        }
    }

    public void onOK$abmUsuarioRolesWin(Event event) {
        onClick$usuarioRolSave(event);
    }

    public void onClick$usuarioRolCancel(Event event) {
        if (isEditMode()) {
            //restore to original selected RolesEntity if cancel from new
            if (_create) {
                websiteUserModel.setRolSelected((RolesPerWebsiteUsersEntity) _tmpSelected);
                _tmpSelected = null;
            }

            //switch to view mode
            setEditMode(false);
        }
    }

    public void onCancel$abmcUsuarioRolesWin(Event event) {
        onClick$usuarioRolCancel(event);
    }

    public void onClick$usuarioRolUpdate(Event event) {
        if (isViewMode()) {
            if (websiteUserModel.getRolSelected() != null) {
                _create = false;
                //switch to edit mode
                setEditMode(true);
            }
        }

    }

    public void onClick$usuarioRolDelete(Event event) {
        if (isViewMode()) {
            if (websiteUserModel.getRolSelected() != null) {
                _create = false;

                newConfirmDelete().show();
            }
        }
    }

    //--To be override--//
    /** Validate the input field */
    protected void validate() {
        usuarioRolIdCombobox.getValue();
        usuarioRolDesde.getValue();
        usuarioRolHasta.getValue();
    }

    /** Get a instance of ConfirmDelete class */
    protected ConfirmDelete newConfirmDelete() {
        return new ConfirmDelete();
    }

    public RolesModel getRolesModel() {
        return rolesModel;
    }

    public void setRolesModel(RolesModel rolesModel) {
        this.rolesModel = rolesModel;
    }

    public WebsiteUserModel getWebsiteUserModel() {
        return websiteUserModel;
    }

    public void setWebsiteUserModel(WebsiteUserModel websiteUserModel) {
        this.websiteUserModel = websiteUserModel;
    }

    @Override
    public void setEditMode(boolean _editMode) {
        super.setEditMode(_editMode);
        binder.loadComponent(usuarioRolesDetail); //reload visible to force refresh
    }

    /** Delete Confirmation */
    protected class ConfirmDelete {

        /** Show the ConfirmDelete Messagebox */
        public void show() {
            try {
                Messagebox.show(getMessage(),
                        getTitle(),
                        Messagebox.YES + Messagebox.NO,
                        Messagebox.EXCLAMATION,
                        new org.zkoss.zk.ui.event.EventListener() {

                            public void onEvent(Event event) {
                                if ("onYes".equals(event.getName())) {
                                    doYes();
                                }
                            }
                        });
            } catch (InterruptedException ex) {
                showMessage("Ocurrió un error mostrando mensaje: ", ex);
            }
        }

        /** Operation when end user click Yes button in confirm delete Messagebox*/
        public void doYes() {
            try {
                websiteUserModel.deleteRol();
                usuarioRolCreate.focus();
            } catch (Exception ex) {
                showMessage("Ocurrió un error mientras intentaba eliminar un ítem: ", ex);
                rolesPerWebsiteUsersModel.setSelected((RolesPerWebsiteUsersEntity) usuarioRolesList.getModel().getElementAt(0));
            }
            websiteUserModel.setRolSelected(null);
            //refresh the usuarioRolesList
            refreshModel();
            //update the usuarioRolesDetail
            binder.loadComponent(usuarioRolesDetail); //reload visible to force refresh
            setFocus();
        }

        /** Returns title of confirm deleting Messagebox */
        public String getTitle() {
            return Labels.getLabel("app.delete.confirm.title");
        }

        /** Returns message for confirm deleting Messagbox */
        public String getMessage() {
            return Labels.getLabel("app.delete.confirm.message");
        }
    }
}
