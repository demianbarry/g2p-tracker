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
import org.g2p.tracker.model.entities.RolesEntity;
import org.g2p.tracker.model.entities.WebsiteUserEntity;
import org.g2p.tracker.model.models.RolesModel;
import org.g2p.tracker.model.models.WebsiteUserModel;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.KeyEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zkplus.databind.DataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class AbmcUsuarioRolesController {

    //ZK databinder
    protected DataBinder binder;

    //Roles Model
    protected WebsiteUserModel websiteUserModel = null;
    protected RolesModel usuariosRolesModel;

    //main control window
    protected Window abmcUsuarioRolesWin; //main window
    protected Listbox usersList; //domain object summary list
    protected Listbox usuarioRolesList; //domain object summary list
    protected Component usuarioRolesDetail; //domain object detail

    //edit mode
    protected Component usuarioRolesEdit; //edit panel
    protected Label usuarioRolId;
    protected Textbox usuarioRolNombre;
    protected Textbox usuarioRolDescripcion;
    protected Textbox usuarioRolObservaciones;

    //buttons
    protected Button usuarioRolCreate; //new button
    protected Button usuarioRolUpdate; //edit button
    protected Button usuarioRolDelete; //delete button
    protected Button usuarioRolSave; //save button
    protected Button usuarioRolCancel; //cancel button

    //@Resource
    //protected Vbox navBar;

    //operation transient state
    protected RolesEntity _tmpSelected; //store original selected entity
    protected boolean _create; //when new a entity
    protected boolean _editMode; //switch to edit mode when doing editing(new/update)
    protected int _lastSelectedIndex = -1; //last selectedIndex before delete

    public AbmcUsuarioRolesController() {
    }

    public RolesModel getUsuarioRolesModel() {
        return usuariosRolesModel;
    }

    public void setUsuarioRolesModel(RolesModel rolesModel) {
        this.usuariosRolesModel = rolesModel;
    }

    public void refreshModel() {
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
        return this.usuariosRolesModel.getSelected() == null;
    }

    private void switchMode() {
        binder.loadComponent(usuarioRolesDetail); //reload visible to force refresh
        setFocus();
    }

    private void setFocus() {
        if (_editMode) {
            usuarioRolNombre.focus();
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

    //-- Initialization --//
    //@On("abmcUsuarioRolesWin.onCreate")
    public void init() {
        binder = (DataBinder) abmcUsuarioRolesWin.getVariable("binder", true);
        final List usersListModel = (List) usersList.getModel();
        if (!usersListModel.isEmpty()) {
            websiteUserModel.setSelected((WebsiteUserEntity) usersListModel.get(0));
//            binder.loadComponent(usuarioRolesDetail);
        }

        final List model = (List) usuarioRolesList.getModel();
        if (!model.isEmpty()) {
            usuariosRolesModel.setSelected((RolesEntity) model.get(0));
            binder.loadComponent(usuarioRolesDetail);
        }
        binder.loadComponent(usuarioRolesList);
        setFocus();
    }

    //-- view mode control --//
    //@On("abmcUsuarioRolesWin.onCtrlKey")
    public void doCtrlKey(Event event) {
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

    //@On("usuarioRolesList.onSelect")
    public void doSelect(Event event) {
        final int index = usuarioRolesList.getSelectedIndex();
        if (index >= 0) {
            _lastSelectedIndex = index;
            _create = false;
        }
    }

    //@On("usuarioRolCreate.onClick")
    public void doCreate(Event event) {
        if (isViewMode()) {
            //prepare a new RolesEntity
            _tmpSelected = usuariosRolesModel.getSelected();
            _create = true;
            usuariosRolesModel.setSelected(new RolesEntity());
            //switch to edit mode
            setEditMode(true);
        }
    }

    //@On("usuarioRolUpdate.onClick")
    public void doUpdate(Event event) {
        if (isViewMode()) {
            if (usuariosRolesModel.getSelected() != null) {

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
    public void doDelete(Event event) {
        if (isViewMode()) {
            if (usuariosRolesModel.getSelected() != null) {
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

    //-- edit mode control --//
    //@On("usuarioRolSave.onClick,abmcUsuarioRolesWin.onOK")
    public void doSave(Event event) {
        if (isEditMode()) {
            //validate
            validate();

            //save into bean
            binder.saveComponent(usuarioRolesEdit); //reload model to force refresh

            //store into db
            if (_create) {
                this.usuariosRolesModel.persist();
            } else {
                try {
                    try {
                        this.usuariosRolesModel.merge();
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

    //On("usuarioRolCancel.onClick,abmcUsuarioRolesWin.onCancel")
    public void doCancel(Event event) {
        if (isEditMode()) {
            //restore to original selected RolesEntity if cancel from new
            if (_create) {
                usuariosRolesModel.setSelected(_tmpSelected);
                _tmpSelected = null;
            }

            //switch to view mode
            setEditMode(false);
        }
    }

    //--To be override--//
    /** Validate the input field */
    protected void validate() {
        usuarioRolNombre.getValue();
        usuarioRolDescripcion.getValue();
        usuarioRolObservaciones.getValue();
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
                usuariosRolesModel.delete();
                usuarioRolCreate.focus();
            } catch (IllegalOrphanException ex) {
                Logger.getLogger(AbmcUsuarioRolesController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(AbmcUsuarioRolesController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (EntityNotFoundException e) {
                System.out.println("HOLAAAAAAAAA1");
            }
            usuariosRolesModel.setSelected(null);
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
