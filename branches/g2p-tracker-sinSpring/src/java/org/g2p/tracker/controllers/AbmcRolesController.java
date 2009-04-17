/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.controllers;

import java.util.List;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityNotFoundException;
import org.g2p.tracker.model.daos.exceptions.IllegalOrphanException;
import org.g2p.tracker.model.daos.exceptions.NonexistentEntityException;
import org.g2p.tracker.model.entities.RolesEntity;
import org.g2p.tracker.model.models.RolesModel;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.KeyEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zkplus.databind.DataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class AbmcRolesController extends Window implements AfterCompose {

    //ZK databinder
    protected DataBinder binder;

    //Roles Model
    protected RolesModel rolesModel = null;

    //main control window
    protected Listbox rolesList; //domain object summary list
    protected Component rolesDetail; //domain object detail

    //edit mode
    protected Component rolesEdit; //edit panel
    protected Label rolId;
    protected Textbox rolNombre;
    protected Textbox rolDescripcion;
    protected Textbox rolObservaciones;

    //buttons
    protected Button rolCreate; //new button
    protected Button rolUpdate; //edit button
    protected Button rolDelete; //delete button
    protected Button rolSave; //save button
    protected Button rolCancel; //cancel button

    //operation transient state
    protected RolesEntity _tmpSelected; //store original selected entity
    protected boolean _create; //when new a entity
    protected boolean _editMode; //switch to edit mode when doing editing(new/update)
    protected int _lastSelectedIndex = -1; //last selectedIndex before delete

    public AbmcRolesController() {
        rolesModel = new RolesModel();
    }

    @Override
    public void afterCompose() {

        //wire variables
        Components.wireVariables(this, this);

        //auto forward
        Components.addForwards(this, this);

    }

    public void onCreate$abmcRolesWin(Event event) {
        // Obtengo el DataBinder que instancia la página
        binder = (DataBinder) getVariable("binder", true);

        final List model = (List) rolesList.getModel();

        if (!model.isEmpty()) {
            // si el model no está vacio, selecciono el primer registro y cargo el detalle
            rolesModel.setSelected((RolesEntity) model.get(0));
            binder.loadComponent(rolesDetail);
        }
        setFocus();
    }

    public RolesModel getRolesModel() {
        return rolesModel;
    }

    public void setRolesModel(RolesModel rolesModel) {
        this.rolesModel = rolesModel;
    }

    public void refreshModel() {
        binder.loadAttribute(rolesList, "model"); //reload model to force refresh
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
        binder.loadComponent(rolesDetail); //reload visible to force refresh
        setFocus();
    }

    private void setFocus() {
        if (_editMode) {
            rolNombre.focus();
        } else {
            if ((rolesList.getModel()).getSize() == 0) {
                //no result in list, focus on new button
                rolCreate.focus();
            } else {
                if (_create) {
                    rolCreate.focus();
                } else {
                    rolesList.focus();
                }
            }
        }
    }


    // Cada método asociado a un evento en particular se escribe como evento$idComponente(Event event)
    //-- view mode control --//
    public void onCtrlKey$abmcRolesWin(Event event) {
        final List items = rolesList.getItems();
        if (!items.isEmpty() && (!_editMode || !_create)) {
            final int keycode = ((KeyEvent) event).getKeyCode();
            if (keycode == KeyEvent.DOWN || keycode == KeyEvent.UP) {
                //handle no selected item case
                if (rolesList.getSelectedIndex() < 0) { //no selected item
                    //try our best to guess one
                    if (_lastSelectedIndex >= 0) {
                        final int index = Math.min(items.size() - 1, _lastSelectedIndex);
                        rolesList.setSelectedIndex(index);
                        Events.sendEvent(new SelectEvent("onSelect", rolesList, rolesList.getSelectedItems()));
                    }
                }
                rolesList.focus();
            }
        }
    }

    public void onSelect$rolesList(Event event) {
        final int index = rolesList.getSelectedIndex();
        if (index >= 0) {
            _lastSelectedIndex = index;
            _create = false;
        }
    }

    public void onClick$rolCreate(Event event) {
        if (isViewMode()) {
            //prepare a new RolesEntity
            _tmpSelected = rolesModel.getSelected();
            _create = true;
            rolesModel.setSelected(new RolesEntity());
            //switch to edit mode
            setEditMode(true);
        }
    }

    public void onClick$rolUpdate(Event event) {
        if (isViewMode()) {
            if (rolesModel.getSelected() != null) {

                _create = false;

                //switch to edit mode
                setEditMode(true);

            }
        }
    }

    public void onClick$rolDelete(Event event) {
        if (isViewMode()) {
            if (rolesModel.getSelected() != null) {
                _create = false;

                newConfirmDelete().show();
            }
        }
    }

    //-- edit mode control --//
    public void onClick$rolSave(Event event) {
        doSave(event);
    }

    public void onOk$abmcRolesWin(Event event) {
        doSave(event);
    }

    public void doSave(Event event) {
        if (isEditMode()) {
            //validate
            validate();

            //save into bean
            binder.saveComponent(rolesEdit); //reload model to force refresh

            //store into db
            if (_create) {
                this.rolesModel.persist();
            } else {
                try {
                    try {
                        this.rolesModel.merge();
                    } catch (IllegalOrphanException ex) {
                        Logger.getLogger(AbmcRolesController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NonexistentEntityException ex) {
                        Logger.getLogger(AbmcRolesController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(AbmcRolesController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (EntityNotFoundException e1) {
                    try {
                        Messagebox.show(getUpdateDeletedMessage());
                    } catch (InterruptedException e2) {
                        //ignore
                    }
                }
            }

            //refresh the rolesList
            refreshModel();
            //switch to view mode
            setEditMode(false);
        }
    }

    public void onClick$rolCancel(Event event) {
        doCancel(event);
    }

    public void onCancel$abmcRolesWin(Event event) {
        doCancel(event);
    }

    public void doCancel(Event event) {
        if (isEditMode()) {
            //restore to original selected RolesEntity if cancel from new
            if (_create) {
                rolesModel.setSelected(_tmpSelected);
                _tmpSelected = null;
            }

            //switch to view mode
            setEditMode(false);
        }
    }

    //--To be override--//
    /** Validate the input field */
    protected void validate() {
        rolNombre.getValue();
        rolDescripcion.getValue();
        rolObservaciones.getValue();
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
                //ignore
            }
        }

        /** Operation when end user click Yes button in confirm delete Messagebox*/
        public void doYes() {
            try {
                rolesModel.delete();
                rolCreate.focus();
            } catch (IllegalOrphanException ex) {
                Logger.getLogger(AbmcRolesController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(AbmcRolesController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (EntityNotFoundException e) {
                //ignore, already deleted by others
            }
            rolesModel.setSelected(null);
            //refresh the rolesList
            refreshModel();
            //update the rolesDetail
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
