/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.controllers;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.LockModeType;
import org.g2p.tracker.model.entities.RolesEntity;
import org.g2p.tracker.model.models.RolesModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.lang.Strings;
import org.zkoss.spring.context.annotation.For;
import org.zkoss.spring.context.annotation.On;
import org.zkoss.spring.jpa.EntityNotFoundException;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.KeyEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zkplus.databind.DataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.FieldComparator;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

@Scope("idspace")
@Controller
@For("abmcRolesWin")
public class AbmcRolesController {

    protected static Set<Integer> rolesEditando = Collections.synchronizedSet(new HashSet<Integer>());

    //ZK databinder
    protected DataBinder binder;

    //Roles Model
    @Resource
    protected RolesModel rolesModel = null;

    //main control window
    @Resource
    protected Window abmcRolesWin; //main window
    @Resource
    protected Listbox rolesList; //domain object summary list
    @Resource
    protected Component rolesDetail; //domain object detail

    //edit mode
    @Resource
    protected Component rolesEdit; //edit panel
    @Resource
    protected Label rolId;
    @Resource
    protected Textbox rolNombre;
    @Resource
    protected Textbox rolDescripcion;
    @Resource
    protected Textbox rolObservaciones;

    //buttons
    @Resource
    protected Button rolCreate; //new button
    @Resource
    protected Button rolUpdate; //edit button
    @Resource
    protected Button rolDelete; //delete button
    @Resource
    protected Button rolSave; //save button
    @Resource
    protected Button rolCancel; //cancel button

    //operation transient state
    protected RolesEntity _tmpSelected; //store original selected entity
    protected boolean _create; //when new a entity
    protected boolean _editMode; //switch to edit mode when doing editing(new/update)
    protected int _lastSelectedIndex = -1; //last selectedIndex before delete

    public AbmcRolesController() {
    }

    public RolesModel getModel() {
        return rolesModel;
    }

    public void setModel(RolesModel rolesModel) {
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
            if (((List) rolesList.getModel()).isEmpty()) {
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

    //-- Initialization --//
    @On("abmcRolesWin.onCreate")
    public void init() {
        binder = (DataBinder) abmcRolesWin.getVariable("binder", true);
        final List model = (List) rolesList.getModel();
        if (!model.isEmpty()) {
            rolesModel.setSelected((RolesEntity) model.get(0));
            binder.loadComponent(rolesDetail);
        }
        setFocus();
    }

    //-- view mode control --//
    @On("abmcRolesWin.onCtrlKey")
    public void doCtrlKey(Event event) {
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

    @On("rolesList.onSelect")
    public void doSelect(Event event) {
        final int index = rolesList.getSelectedIndex();
        if (index >= 0) {
            _lastSelectedIndex = index;
            _create = false;
        }
    }

    @On("rolCreate.onClick")
    public void doCreate(Event event) {
        if (isViewMode()) {
            //prepare a new RolesEntity
            _tmpSelected = rolesModel.getSelected();
            _create = true;
            rolesModel.setSelected(new RolesEntity());
            //switch to edit mode
            setEditMode(true);
        }
    }

    @On("rolUpdate.onClick")
    public void doUpdate(Event event) {
        if (isViewMode()) {
            if (rolesModel.getSelected() != null) {

                _create = false;

                //switch to edit mode
                setEditMode(true);

            }
        }
    }

    @On("rolDelete.onClick")
    public void doDelete(Event event) {
        if (isViewMode()) {
            if (rolesModel.getSelected() != null) {
                _create = false;

                newConfirmDelete().show();
            }
        }
    }

    //-- sorting --//
    @On("rolNameSort.onSort,rolDateSort.onSort,rolPrioritySort.onSort")
    public void doSort(Event event) {
        final Listheader lh = (Listheader) event.getTarget();
        final String sortDirection = lh.getSortDirection(); //original direction
        if ("ascending".equals(sortDirection)) {
            final Comparator cmpr = lh.getSortDescending();
            if (cmpr instanceof FieldComparator) {
                final String orderBy = ((FieldComparator) cmpr).getOrderBy();
                rolesModel.setOrderBy(orderBy); //update query string
            }
        } else if ("descending".equals(sortDirection) || "natural".equals(sortDirection) || Strings.isBlank(sortDirection)) {
            final Comparator cmpr = lh.getSortAscending();
            if (cmpr instanceof FieldComparator) {
                final String orderBy = ((FieldComparator) cmpr).getOrderBy();
                rolesModel.setOrderBy(orderBy); //update query string
            }
        }
    }

    //-- edit mode control --//
    @On("rolSave.onClick,abmcRolesWin.onOK")
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
                    this.rolesModel.merge();
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

    @On("rolCancel.onClick,abmcRolesWin.onCancel")
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
