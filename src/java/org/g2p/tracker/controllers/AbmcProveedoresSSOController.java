/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.controllers;

import java.util.List;


import org.g2p.tracker.model.entities.ProveedoresSsoEntity;
import org.g2p.tracker.model.models.ProveedoresSSOModel;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
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

public class AbmcProveedoresSSOController extends BaseController implements AfterCompose {
    private static final long serialVersionUID = 5573714696520477239L;

    //Roles Model
    protected ProveedoresSSOModel proveedoresSSOModel = null;

    //main control window
    protected Listbox proveedoresList; //domain object summary list
    protected Component proveedorDetail; //domain object detail

    //edit mode
    protected Component proveedorEdit; //edit panel
    protected Label proveedorId;
    protected Textbox proveedorNombre;
    protected Textbox proveedorDescripcion;
    protected Textbox proveedorObservaciones;
    protected Textbox proveedorURLDiscovery;
    protected Textbox proveedorURLIcono;

    //buttons
    protected Button proveedorCreate; //new button
    protected Button proveedorUpdate; //edit button
    protected Button proveedorDelete; //delete button
    protected Button proveedorSave; //save button
    protected Button proveedorCancel; //cancel button

    public AbmcProveedoresSSOController() {
        super(true);
        proveedoresSSOModel = new ProveedoresSSOModel();
    }   

    public void onCreate$abmcProveedoresSSOWin(Event event) {
        // Obtengo el DataBinder que instancia la página
        binder = (DataBinder) getVariable("binder", true);

        final List model = (List) proveedoresList.getModel();

        if (!model.isEmpty()) {
            // si el model no está vacio, selecciono el primer registro y cargo el detalle
            proveedoresSSOModel.setSelected((ProveedoresSsoEntity) model.get(0));
            binder.loadComponent(proveedorDetail);
        }
        setFocus();
    }

    public ProveedoresSSOModel getProveedoresSSOModel() {
        return proveedoresSSOModel;
    }

    public void setProveedoresSSOModel(ProveedoresSSOModel rolesModel) {
        this.proveedoresSSOModel = rolesModel;
    }

        //-- view/edit mode --//
    public void setEditMode(boolean b) {
        editMode = b;
        switchMode();
    }

    public boolean isViewMode() {
        return !editMode;
    }

    public void refreshModel() {
        binder.loadAttribute(proveedoresList, "model"); //reload model to force refresh
    }   

    public boolean isNotSelected() {
        return this.proveedoresSSOModel.getSelected() == null;
    }

    private void switchMode() {
        binder.loadComponent(proveedorDetail); //reload visible to force refresh
        setFocus();
    }

    private void setFocus() {
        if (editMode) {
            proveedorNombre.focus();
        } else {
            if ((proveedoresList.getModel()).getSize() == 0) {
                //no result in list, focus on new button
                proveedorCreate.focus();
            } else {
                if (_create) {
                    proveedorCreate.focus();
                } else {
                    proveedoresList.focus();
                }
            }
        }
    }


    // Cada método asociado a un evento en particular se escribe como evento$idComponente(Event event)
    //-- view mode control --//
    public void onCtrlKey$abmcProveedoresSSOWin(Event event) {
        final List items = proveedoresList.getItems();
        if (!items.isEmpty() && (!editMode || !_create)) {
            final int keycode = ((KeyEvent) event).getKeyCode();
            if (keycode == KeyEvent.DOWN || keycode == KeyEvent.UP) {
                //handle no selected item case
                if (proveedoresList.getSelectedIndex() < 0) { //no selected item
                    //try our best to guess one
                    if (_lastSelectedIndex >= 0) {
                        final int index = Math.min(items.size() - 1, _lastSelectedIndex);
                        proveedoresList.setSelectedIndex(index);
                        Events.sendEvent(new SelectEvent("onSelect", proveedoresList, proveedoresList.getSelectedItems()));
                    }
                }
                proveedoresList.focus();
            }
        }

    }

    public void onSelect$proveedoresList(Event event) {
        final int index = proveedoresList.getSelectedIndex();
        if (index >= 0) {
            _lastSelectedIndex = index;
            _create = false;
        }
    }

    public void onClick$proveedorCreate(Event event) {
        if (isViewMode()) {
            //prepare a new ProveedoresSsoEntity
            _tmpSelected = proveedoresSSOModel.getSelected();
            _create = true;
            proveedoresSSOModel.setSelected(new ProveedoresSsoEntity());
            //switch to edit mode
            setEditMode(true);
        }
    }

    public void onClick$proveedorUpdate(Event event) {
        if (isViewMode()) {
            if (proveedoresSSOModel.getSelected() != null) {

                _create = false;

                //switch to edit mode
                setEditMode(true);

            }
        }
    }

    public void onClick$proveedorDelete(Event event) {
        if (isViewMode()) {
            if (proveedoresSSOModel.getSelected() != null) {
                _create = false;
                newConfirmDelete().show();
            }
        }
    }

    //-- edit mode control --//
    public void onClick$proveedorSave(Event event) {
        doSave(event);
    }

    public void onOk$abmcProveedoresSSOWin(Event event) {
        doSave(event);
    }

    public void doSave(Event event) {
        if (isEditMode()) {
            //validate
            validate();

            //save into bean
            binder.saveComponent(proveedorEdit); //reload model to force refresh

            try {
                //store into db
                proveedoresSSOModel.getUtx().begin();

                if (_create) {
                    this.proveedoresSSOModel.persist(false);
                } else {
                    this.proveedoresSSOModel.merge(false);
                }

                proveedoresSSOModel.getUtx().commit();
            } catch (Exception ex) {
                try {
                    showMessage("Ocurrió un error mientras se intentaba actualizar un rol: ", ex);
                    proveedoresSSOModel.getUtx().rollback();
                    proveedoresSSOModel.setSelected((ProveedoresSsoEntity) proveedoresList.getModel().getElementAt(0));
                } catch (Exception ex1) {
                    showMessage("Ocurrió un error mientras se intentaba hacer rollback de la operacion: ", ex);
                }
            } finally {
                //refresh the proveedoresList
                refreshModel();
            }
        }

        //switch to view mode
        setEditMode(false);

    }

    public void onClick$proveedorCancel(Event event) {
        doCancel(event);
    }

    public void onCancel$abmcProveedoresSSOWin(Event event) {
        doCancel(event);
    }

    public void doCancel(Event event) {
        if (isEditMode()) {
            //restore to original selected ProveedoresSsoEntity if cancel from new
            if (_create) {
                proveedoresSSOModel.setSelected(_tmpSelected);
                _tmpSelected =
                        null;
            }

//switch to view mode
            setEditMode(false);
        }

    }

    //--To be override--//
    /** Validate the input field */
    protected void validate() {
        proveedorNombre.getValue();
        proveedorDescripcion.getValue();
        proveedorObservaciones.getValue();
        proveedorURLDiscovery.getValue();
        proveedorURLIcono.getValue();
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
                proveedoresSSOModel.getUtx().begin();
                proveedoresSSOModel.delete(false);
                proveedoresSSOModel.getUtx().commit();
            } catch (Exception ex) {
                try {
                    showMessage("Ocurrió un error mientras se intentaba eliminar un rol: ", ex);
                    proveedoresSSOModel.getUtx().rollback();
                    proveedoresSSOModel.setSelected((ProveedoresSsoEntity) proveedoresList.getModel().getElementAt(0));
                } catch (Exception ex1) {
                    showMessage("Ocurrió un error mientras se intentaba hacer rollback de la operacion: ", ex1);
                }
            } finally {
                //refresh the proveedoresList
                refreshModel();
            }
            //update the proveedorDetail
            switchMode();
            proveedorCreate.focus();
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
