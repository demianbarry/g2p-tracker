/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.g2p.tracker.model.entities.AccionesAppsEntity;
import org.g2p.tracker.model.models.AccionesModel;
import org.g2p.tracker.model.models.CircuitosModel;
import org.g2p.tracker.model.models.EstadosModel;
import org.g2p.tracker.model.entities.CircuitosEstadosEntity;
import org.g2p.tracker.model.entities.EstadosEntity;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zkplus.databind.DataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

/**
 *
 * @author nacho
 */
public class AbmcCircuitosController extends BaseController {


    /*--------------------------------------------------Declaraciones--------------------------------------------------------*/

    //Models
    protected CircuitosModel circuitosModel = null;
    /*protected EstadosModel estadosModel = null;
    protected AccionesModel accionesModel = null;*/

    //Vistas
    protected Component circuitoDetail;
    protected Component estadosAccionesTransiciones;
    protected Component listCircuitoView;
    protected Component editCircuitoView;
    protected Component listEstadosView;
    protected Component editEstadosView;
    protected Component listAccionesView;
    protected Component editAccionesView;

    //Listbox
    protected Listbox circuitosList;
    protected Listbox estadosList;
    protected Listbox accionesList;

    //Botones
    protected Button nuevoCircuito;
    protected Button nuevoEstado;
    protected Button editarEstado;
    protected Button eliminarEstado;
    protected Button guardarEstado;
    protected Button cancelarEstado;
    protected Button nuevaAccion;
    protected Button editarAccion;
    protected Button eliminarAccion;
    protected Button guardarAccion;
    protected Button cancelarAccion;

    //Campos de Circuitos
    protected Textbox nombreCircuito;
    protected Textbox descripcionCircuito;
    protected Textbox observacionesCircuito;

    //Banderas
    protected Boolean circuitoNuevo;
    protected Boolean estadoNuevo;
    protected Boolean accionNueva;


    /*--------------------------------------------------Getter y Setters--------------------------------------------------------*/

    public CircuitosModel getCircuitosModel() {
        return circuitosModel;
    }

    public void setCircuitosModel(CircuitosModel circuitosModel) {
        this.circuitosModel = circuitosModel;
    }

    /*public AccionesModel getAccionesModel() {
        return accionesModel;
    }

    public void setAccionesModel(AccionesModel accionesModel) {
        this.accionesModel = accionesModel;
    }

    public EstadosModel getEstadosModel() {
        return estadosModel;
    }

    public void setEstadosModel(EstadosModel estadosModel) {
        this.estadosModel = estadosModel;
    }*/


    /*--------------------------------------------------Metodos generales--------------------------------------------------------*/

    //Constructor
    public AbmcCircuitosController() {
        super(true);
        circuitosModel = new CircuitosModel();
        /*estadosModel = new EstadosModel();
        accionesModel = new AccionesModel();*/
    }

    public void setCircuitoListMode(boolean mode){
        if (mode) {
            listCircuitoView.setVisible(true);
            editCircuitoView.setVisible(false);
        } else {
            listCircuitoView.setVisible(false);
            editCircuitoView.setVisible(true);
        }
    }

    public void changeCircuitoMode() {
        if (listCircuitoView.isVisible()) {
            setCircuitoListMode(false);
        } else {
            setCircuitoListMode(true);
        }
    }

    public void setEstadoListMode(boolean mode){
        if (mode) {
            listEstadosView.setVisible(true);
            editEstadosView.setVisible(false);
        } else {
            listEstadosView.setVisible(false);
            editEstadosView.setVisible(true);
        }
    }

    public void changeEstadoMode() {
        if (listEstadosView.isVisible()) {
            setEstadoListMode(false);
        } else {
            setEstadoListMode(true);
        }
    }

    public void setAccionesListMode(boolean mode){
        if (mode) {
            listAccionesView.setVisible(true);
            editAccionesView.setVisible(false);
        } else {
            listAccionesView.setVisible(false);
            editAccionesView.setVisible(true);
        }
    }

    public void changeAccionesMode() {
        if (listAccionesView.isVisible()) {
            setAccionesListMode(false);
        } else {
            setAccionesListMode(true);
        }
    }

    protected void nuevoCircuito() {
        circuitosModel.setSelected(new CircuitosEstadosEntity());
        refresh();
    }

    protected void nuevoEstado() {
        circuitosModel.setEstadoSelected(new EstadosEntity());
        refresh();
    }

    protected void nuevaAccion() {
        circuitosModel.setAccionSelected(new AccionesAppsEntity());
        refresh();
    }

    public void refresh() {
        binder.loadComponent(circuitoDetail); //reload visible to force refresh
        binder.loadAttribute(circuitoDetail, "model"); //reload model to force refresh
    }

    public void doSaveCircuito(Event event) {
                   
        //save into bean
        binder.saveComponent(editCircuitoView); //reload model to force refresh

        try {
            //store into db
            circuitosModel.getUtx().begin();

            if (_create) {
                this.circuitosModel.persist(false);
            } else {
                this.circuitosModel.merge(false);
            }

            circuitosModel.getUtx().commit();
        } catch (Exception ex) {
            try {
                showMessage("Ocurrió un error mientras se intentaba guardar el circuito: ", ex);
                circuitosModel.getUtx().rollback();
                circuitosModel.setSelected((CircuitosEstadosEntity) circuitosList.getModel().getElementAt(0));
            } catch (Exception ex1) {
                showMessage("Ocurrió un error mientras se intentaba hacer rollback de la operacion: ", ex);
            }
        } finally {
            //refresh the rolesList
            refresh();
        }
    }


    /*--------------------------------------------------LISTENERS--------------------------------------------------------*/

    /*--------------------------------------------------Circuitos--------------------------------------------------------*/

    public void onCreate$abmcCircuitosWin(Event event) {
        try {
            // Obtengo el DataBinder que instancia la página
            binder = (DataBinder) getVariable("binder", true);
            setCircuitoListMode(true);
            estadosAccionesTransiciones.setVisible(false);
            refresh();
        } catch (Exception ex) {
            Logger.getLogger(AbmcTracksController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onClick$nuevoCircuito(Event event) {
        circuitoNuevo = true;
        estadoNuevo = true;
        accionNueva = true;
        changeCircuitoMode();
        setEstadoListMode(false);
        setAccionesListMode(false);
        nuevoCircuito();
        nuevoEstado();
        nuevaAccion();
        refresh();
    }
    
    public void onBlur$nombreCircuito(Event event) {
        if (circuitoNuevo && nombreCircuito.getValue() != null) {
            /*CircuitosEstadosEntity circuito = circuitosModel.getSelected();

            circuito.setNombre(circuitosModel.getSelected().getNombre());*/

            try {
                //store into db
                circuitosModel.beginTransaction();

                if (circuitosModel.getSelected().getPK() != null) {
                    circuitosModel.edit(circuitosModel.getSelected(), false);
                } else {
                    circuitosModel.create(circuitosModel.getSelected(), false);
                }

                circuitosModel.commitTransaction();
                //showMessage("El circuito se guardo correctamente. Circuito_ID: " + circuitosModel.getSelected().getPK());

            } catch (Exception ex) {
                try {
                    showMessage("Ocurrió un error mientras se intentaba crear el circuito: " + ex.getClass(), ex);
                    circuitosModel.rollbackTransaction();
                } catch (Exception ex1) {
                    showMessage("Ocurrió un error mientras se intentaba hacer rollback de la operacion: " + ex1.getClass(), ex);
                }
            } finally {
                //refresh the r
                estadosAccionesTransiciones.setVisible(true);
                circuitosModel.refreshAll();
                binder.loadAttribute(estadosList, "model");
                binder.loadAttribute(accionesList, "model");
                circuitoNuevo = false;
                refresh();
            }
        }
    }

    /*--------------------------------------------------Estados--------------------------------------------------------*/

    public void onClick$guardarEstado(Event event) {

        try {
            if (estadoNuevo) {
                circuitosModel.getEstadoSelected().setCircuitoId(circuitosModel.getSelected());
                circuitosModel.persistEstado();
            } else {
                circuitosModel.mergeEstado();
            }
        } catch (Exception ex) {
            showMessage("Ocurrió un error mientras se intentaba guardar el estado: ", ex);
        }
        
        changeEstadoMode();
        binder.saveAll();
        binder.loadAttribute(estadosList, "model");
        refresh();
    }

    public void onClick$nuevoEstado(Event event) {
        estadoNuevo = true;
        circuitosModel.setEstadoSelected(new EstadosEntity());
        changeEstadoMode();
        refresh();
    }

    public void onClick$editarEstado(Event event) {
        estadoNuevo = false;
        changeEstadoMode();
        refresh();
    }

    public void onClick$cancelarEstado(Event event) {
        changeEstadoMode();
        refresh();
    }

    public void onClick$eliminarEstado(Event event) {
        try {
                circuitosModel.deleteEstado();
            } catch (Exception ex) {
                showMessage("Ocurrió un error mientras intentaba eliminar un ítem: ", ex);
            }
            circuitosModel.setEstadoSelected(null);
            refresh();
    }

    /*--------------------------------------------------Acciones--------------------------------------------------------*/

    public void onClick$guardarAccion(Event event) {

        try {
            if (accionNueva) {
                circuitosModel.getAccionSelected().setCircuitoId(circuitosModel.getSelected());
                circuitosModel.persistAccion();
            } else {
                circuitosModel.mergeAccion();
            }
        } catch (Exception ex) {
            showMessage("Ocurrió un error mientras se intentaba guardar la accion: ", ex);
        }

        changeAccionesMode();
        binder.saveAll();
        binder.loadAttribute(accionesList, "model");
        refresh();
    }

    public void onClick$nuevaAccion(Event event) {
        accionNueva = true;
        circuitosModel.setAccionSelected(new AccionesAppsEntity());
        changeAccionesMode();
        refresh();
    }

    public void onClick$editarAccion(Event event) {
        accionNueva = false;
        changeAccionesMode();
        refresh();
    }

    public void onClick$cancelarAccion(Event event) {
        changeAccionesMode();
        refresh();
    }

    public void onClick$eliminarAccion(Event event) {
        try {
                circuitosModel.deleteAccion();
            } catch (Exception ex) {
                showMessage("Ocurrió un error mientras intentaba eliminar un ítem: ", ex);
            }
            circuitosModel.setAccionSelected(null);
            refresh();
    }

}
