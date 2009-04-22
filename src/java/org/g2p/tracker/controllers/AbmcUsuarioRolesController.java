/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.controllers;

import java.util.Collection;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityNotFoundException;
import org.g2p.tracker.model.daos.exceptions.IllegalOrphanException;
import org.g2p.tracker.model.daos.exceptions.NonexistentEntityException;
import org.g2p.tracker.model.daos.exceptions.PreexistingEntityException;
import org.g2p.tracker.model.entities.RolesEntity;
import org.g2p.tracker.model.entities.UsuarioRolesEntity;
import org.g2p.tracker.model.entities.UsuarioRolesEntityPK;
import org.g2p.tracker.model.entities.WebsiteUserEntity;
import org.g2p.tracker.model.models.RolesModel;
import org.g2p.tracker.model.models.UsuarioRolesModel;
import org.g2p.tracker.model.models.WebsiteUserModel;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.KeyEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zkplus.databind.DataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class AbmcUsuarioRolesController extends Window implements AfterCompose {

    //ZK databinder
    protected DataBinder binder;

    //Roles Model
    protected WebsiteUserModel websiteUserModel = null;
    protected RolesModel rolesModel = null;
    protected UsuarioRolesModel usuarioRolesModel = null;

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

    //main control window
    protected Listbox usersList; //domain object summary list
    protected Listbox usuarioRolesList; //domain object summary list
    protected Component usuarioRolesDetail; //domain object detail

    //edit mode
    protected Component usuarioRolesEdit; //edit panel
    protected Label userId;
    protected Textbox usuarioRolId;
    protected Datebox usuarioRolDesde;
    protected Datebox usuarioRolHasta;

    //buttons
    protected Button usuarioRolCreate; //new button
    protected Button usuarioRolUpdate; //edit button
    protected Button usuarioRolDelete; //delete button
    protected Button usuarioRolSave; //save button
    protected Button usuarioRolCancel; //cancel button

    //@Resource
    //protected Vbox navBar;

    //operation transient state
    protected UsuarioRolesEntity _tmpSelected; //store original selected entity
    protected boolean _create; //when new a entity
    protected boolean _editMode; //switch to edit mode when doing editing(new/update)
    protected int _lastSelectedIndex = -1; //last selectedIndex before delete


    public AbmcUsuarioRolesController() {
        websiteUserModel = new WebsiteUserModel();
        rolesModel = new RolesModel();
        usuarioRolesModel = new UsuarioRolesModel();
    }

    @Override
    public void afterCompose() {

        //wire variables
        Components.wireVariables(this, this);

        //auto forward
        Components.addForwards(this, this);

    }

    //-- Initialization --//
    //@On("abmcUsuarioRolesWin.onCreate")
    public void onCreate$abmcUsuarioRolesWin(Event event) {
        // Obtengo el DataBinder que instancia la página
        binder = (DataBinder) getVariable("binder", true);

        final List usersListModel = (List) usersList.getModel();

        if (!usersListModel.isEmpty()) {
            websiteUserModel.setSelected((WebsiteUserEntity) usersListModel.get(0));
            binder.loadComponent(usuarioRolesDetail);
        }

        final List model = (List) usuarioRolesList.getModel();
        if (!model.isEmpty()) {
            rolesModel.setSelected((RolesEntity) model.get(0));
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
    }

    //-- view mode control --//
    //@On("abmcUsuarioRolesWin.onCtrlKey")
    public void onCtrlKey$abmcUsuarioRolesWin(Event event) {
        final List items = usuarioRolesList.getItems();
        if (!items.isEmpty() && (!_editMode || !_create)) {
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

    public RolesModel getUsuarioRolesModel() {
        return rolesModel;
    }

    public void setUsuarioRolesModel(RolesModel rolesModel) {
        this.rolesModel = rolesModel;
    }

    public void refreshModel() {
        binder.loadAll();
        usersList.selectItem(usersList.getSelectedItem());
        binder.loadComponent(usuarioRolesList);
        binder.loadAttribute(usuarioRolesList, "model"); //reload model to force refresh
    }

    //-- view/edit mode --//
    public void setEditMode(boolean b) {
        _editMode = b;
        switchMode();
    }

    public boolean isViewMode() {
        return !_editMode;
    }

    public boolean isEditMode() {
        return _editMode;
    }

    public boolean isCreate() {
        return _create;
    }

    public boolean isNotSelected() {
        return this.rolesModel.getSelected() == null;
    }

    private void switchMode() {
        refreshModel();
        binder.loadComponent(usuarioRolesDetail); //reload visible to force refresh
        setFocus();
    }

    private void setFocus() {
        if (_editMode) {
            usuarioRolId.focus();
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

  
    //@On("usuarioRolCreate.onClick")
    public void onClick$usuarioRolCreate(Event event) {
        if (isViewMode()) {
            //prepare a new UsuarioRolesEntity
            _tmpSelected = websiteUserModel.getRolSelected();
            _create = true;
            websiteUserModel.setRolSelected(new UsuarioRolesEntity());
            websiteUserModel.getRolSelected().setUsuarioRolesEntityPK(new UsuarioRolesEntityPK());
            websiteUserModel.getRolSelected().getUsuarioRolesEntityPK().setUserId(websiteUserModel.getSelected().getUserId());
            //switch to edit mode
            setEditMode(true);
        }
    }

    //-- edit mode control --//
    //@On("usuarioRolSave.onClick,abmcUsuarioRolesWin.onOK")
    public void onClick$usuarioRolSave(Event event) {
        if (isEditMode()) {
            //validate
            validate();

            //save into bean
            binder.saveComponent(usuarioRolesEdit); //reload model to force refresh

            //store into db
            if (_create) {
                try {
                    this.websiteUserModel.persistRol();
                } catch (PreexistingEntityException ex) {
                    Logger.getLogger(AbmcUsuarioRolesController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(AbmcUsuarioRolesController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    try {
                        this.websiteUserModel.mergeRol();
                    } catch (IllegalOrphanException ex) {
                        Logger.getLogger(AbmcUsuarioRolesController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NonexistentEntityException ex) {
                        Logger.getLogger(AbmcUsuarioRolesController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(AbmcUsuarioRolesController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (EntityNotFoundException e1) {
                    try {
                        Messagebox.show(getUpdateDeletedMessage());
                    } catch (InterruptedException e2) {
                        //ignore
                    }
                }
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

    //On("usuarioRolCancel.onClick,abmcUsuarioRolesWin.onCancel")
    public void onClick$usuarioRolCancel(Event event) {
        if (isEditMode()) {
            //restore to original selected RolesEntity if cancel from new
            if (_create) {
                websiteUserModel.setRolSelected(_tmpSelected);
                _tmpSelected = null;
            }

            //switch to view mode
            setEditMode(false);
        }
    }

    public void onCancel$abmcUsuarioRolesWin(Event event){
        onClick$usuarioRolCancel(event);
    }

    //@On("usuarioRolUpdate.onClick")
    public void onClick$usuarioRolUpdate(Event event) {

        if (isViewMode()) {
            if (websiteUserModel.getRolSelected() != null) {

                _create = false;

                //switch to edit mode
                setEditMode(true);

            }
        }
//        Toolbarbutton button = new Toolbarbutton("Hola");
//        button.setHref("/AbmcRoles.zul");
//        navBar.appendChild(button);
    }

    //@On("usuarioRolDelete.onClick")
    public void onClick$usuarioRolDelete(Event event) {
        if (isViewMode()) {
            if (websiteUserModel.getRolSelected() != null) {
                _create = false;

                newConfirmDelete().show();
            }
        }
    }

    //-- sorting --//
    /*@On("rolNameSort.onSort,rolDateSort.onSort,rolPrioritySort.onSort")
    public void doSort(Event event) {
    final Listheader lh = (Listheader) event.getTarget();
    final String sortDirection = lh.getSortDirection(); //original direction
    if ("ascending".equals(sortDirection)) {
    final Comparator cmpr = lh.getSortDescending();
    if (cmpr instanceof FieldComparator) {
    final String orderBy = ((FieldComparator) cmpr).getOrderBy();
    usuariosRolesModel.setOrderBy(orderBy); //update query string
    }
    } else if ("descending".equals(sortDirection) || "natural".equals(sortDirection) || Strings.isBlank(sortDirection)) {
    final Comparator cmpr = lh.getSortAscending();
    if (cmpr instanceof FieldComparator) {
    final String orderBy = ((FieldComparator) cmpr).getOrderBy();
    usuariosRolesModel.setOrderBy(orderBy); //update query string
    }
    }
    }*/

    
    //--To be override--//
    /** Validate the input field */
    protected void validate() {
        usuarioRolId.getValue();
        usuarioRolDesde.getValue();
        usuarioRolHasta.getValue();
    }

    /** The info message when end user trying to update a "deleted" entity. */
    protected String getUpdateDeletedMessage() {
        return "Cannot find the selected item, might have been deleted by others.";
    }

    /** Get a instance of ConfirmDelete class */
    protected ConfirmDelete newConfirmDelete() {
        return new ConfirmDelete();
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
                System.out.println("HOLAAAAAAAAA");
            }
        }

        /** Operation when end user click Yes button in confirm delete Messagebox*/
        public void doYes() {
            try {
                websiteUserModel.deleteRol();
                usuarioRolCreate.focus();
            } catch (IllegalOrphanException ex) {
                Logger.getLogger(AbmcUsuarioRolesController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(AbmcUsuarioRolesController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (EntityNotFoundException e) {
                System.out.println("HOLAAAAAAAAA1");
            }
            websiteUserModel.setRolSelected(null);
            //refresh the usuarioRolesList
            refreshModel();
            //update the usuarioRolesDetail
            switchMode();
        }

        /** Returns title of confirm deleting Messagebox */
        public String getTitle() {
            return "Are you sure?";
        }

        /** Returns message for confirm deleting Messagbox */
        public String getMessage() {
            return "Are you sure you want to delete the selected item?";
        }
    }
}
