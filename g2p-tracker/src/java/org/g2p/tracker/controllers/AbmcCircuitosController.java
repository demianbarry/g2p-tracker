/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.g2p.tracker.model.entities.AccionesAppsEntity;
import org.g2p.tracker.model.models.CircuitosModel;
import org.g2p.tracker.model.entities.CircuitosEstadosEntity;
import org.g2p.tracker.model.entities.EstadosEntity;
import org.g2p.tracker.model.entities.TransicionEstadosEntity;
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
    protected Component listCircuitoView;
    protected Component editCircuitoView;
    protected Component listEstadosView;
    protected Component editEstadosView;
    protected Component listAccionesView;
    protected Component editAccionesView;
    protected Component listTransicionesView;
    protected Component editTransicionesView;

    //Listbox
    protected Listbox circuitosList;
    protected Listbox estadosList;
    protected Listbox accionesList;
    protected Listbox transicionesList;

    //Botones
    protected Button nuevoCircuito;
    protected Button editarCircuito;
    protected Button eliminarCircuito;
    protected Button guardarCircuito;
    protected Button cancelarCircuito;
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
    protected Button nuevaTransicion;
    protected Button editarTransicion;
    protected Button eliminarTransicion;
    protected Button guardarTransicion;
    protected Button cancelarTransicion;

    //Campos de Circuitos
    protected Textbox nombreCircuito;
    protected Textbox descripcionCircuito;
    protected Textbox observacionesCircuito;

    //Banderas
    protected Boolean circuitoNuevo;
    protected Boolean estadoNuevo;
    protected Boolean accionNueva;
    protected Boolean transicionNueva;


    /*--------------------------------------------------Getter y Setters--------------------------------------------------------*/

    public CircuitosModel getCircuitosModel() {
        return circuitosModel;
    }

    public void setCircuitosModel(CircuitosModel circuitosModel) {
        this.circuitosModel = circuitosModel;
    }


    /*--------------------------------------------------Metodos generales--------------------------------------------------------*/

    //Constructor
    public AbmcCircuitosController() {
        super(true);
        circuitosModel = new CircuitosModel();
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

    public void setTransicionesListMode(boolean mode){
        if (mode) {
            listTransicionesView.setVisible(true);
            editTransicionesView.setVisible(false);
        } else {
            listTransicionesView.setVisible(false);
            editTransicionesView.setVisible(true);
        }
    }

    public void changeTransicionesMode() {
        if (listTransicionesView.isVisible()) {
            setTransicionesListMode(false);
        } else {
            setTransicionesListMode(true);
        }
    }

    protected void ocultarEstadosAcciones() {
        listEstadosView.setVisible(false);
        editEstadosView.setVisible(false);
        listAccionesView.setVisible(false);
        editAccionesView.setVisible(false);
    }

    protected void ocultarTransiciones() {
        listTransicionesView.setVisible(false);
        editTransicionesView.setVisible(false);
    }

    protected void comprobarEstadosAcciones() {
        if (estadosList.getItemCount() >= 2 && accionesList.getItemCount() >= 1) {
            setTransicionesListMode(false);
            refresh();
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

    protected void nuevaTransicion() {
        circuitosModel.setTransicionSelected(new TransicionEstadosEntity());
        refresh();
    }

    public void refresh() {
        circuitosModel.refreshAll();
        binder.loadComponent(circuitoDetail); //reload visible to force refresh
        binder.loadAttribute(circuitoDetail, "model"); //reload model to force refresh
    }

    public void refreshEstados() {
        binder.loadComponent(listEstadosView); //reload visible to force refresh
        binder.loadAttribute(listEstadosView, "model"); //reload model to force refresh
        binder.loadComponent(editEstadosView); //reload visible to force refresh
        binder.loadAttribute(editEstadosView, "model"); //reload model to force refresh
    }

    public void refreshAcciones() {
        binder.loadComponent(listAccionesView); //reload visible to force refresh
        binder.loadAttribute(listAccionesView, "model"); //reload model to force refresh
        binder.loadComponent(editAccionesView); //reload visible to force refresh
        binder.loadAttribute(editAccionesView, "model"); //reload model to force refresh
    }

    public void refreshTransiciones() {
        binder.loadComponent(listTransicionesView); //reload visible to force refresh
        binder.loadAttribute(listTransicionesView, "model"); //reload model to force refresh
        binder.loadComponent(editTransicionesView); //reload visible to force refresh
        binder.loadAttribute(editTransicionesView, "model"); //reload model to force refresh
    }

    public void botonesCircuitos(Boolean nuevo, Boolean editar, Boolean eliminar, Boolean guardar, Boolean cancelar) {
        nuevoCircuito.setVisible(nuevo);
        editarCircuito.setVisible(editar);
        eliminarCircuito.setVisible(eliminar);
        guardarCircuito.setVisible(guardar);
        cancelarCircuito.setVisible(cancelar);
    }

    public void doSaveCircuito(Event event) {
                   
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
            circuitosModel.refreshAll();
            binder.loadAttribute(estadosList, "model");
            binder.loadAttribute(accionesList, "model");
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
            botonesCircuitos(true, true, true, false, false);
            refresh();
        } catch (Exception ex) {
            Logger.getLogger(AbmcTracksController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onClick$nuevoCircuito(Event event) {
        circuitoNuevo = true;
        estadoNuevo = true;
        accionNueva = true;
        transicionNueva = true;
        botonesCircuitos(false, false,false, false, true);
        setCircuitoListMode(false);
        setEstadoListMode(false);
        setAccionesListMode(false);
        setTransicionesListMode(true);
        nuevoCircuito();
        nuevoEstado();
        nuevaAccion();
        ocultarEstadosAcciones();
        ocultarTransiciones();
        refresh();
    }
    
    public void onClick$editarCircuito(Event event) {
        circuitoNuevo = false;
        estadoNuevo = false;
        accionNueva = false;
        transicionNueva = false;
        botonesCircuitos(false, false,false, true, true);
        setCircuitoListMode(false);
        setEstadoListMode(true);
        setAccionesListMode(true);
        setTransicionesListMode(true);
        refresh();
    }

    public void onClick$guardarCircuito(Event event) {
        botonesCircuitos(true, true, true, false, false);
        doSaveCircuito(event);
        setCircuitoListMode(true);
        refresh();
    }

    public void onClick$eliminarCircuito (Event event) {

    }

    public void onClick$cancelarCircuito(Event event) {
        botonesCircuitos(true, true, true, false, false);
        setCircuitoListMode(true);
        refresh();
    }
    
    public void onBlur$nombreCircuito(Event event) {
        if (circuitoNuevo && nombreCircuito.getValue() != null) {
            /*CircuitosEstadosEntity circuito = circuitosModel.getSelected();

            circuito.setNombre(circuitosModel.getSelected().getNombre());*/
            botonesCircuitos(false, false, false, true, true);
            doSaveCircuito(event);
            circuitoNuevo = false;
            setEstadoListMode(true);
            setAccionesListMode(true);
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
        comprobarEstadosAcciones();
        refreshEstados();
    }

    public void onClick$nuevoEstado(Event event) {
        estadoNuevo = true;
        circuitosModel.setEstadoSelected(new EstadosEntity());
        changeEstadoMode();
        refreshEstados();
    }

    public void onClick$editarEstado(Event event) {
        estadoNuevo = false;
        changeEstadoMode();
        refreshEstados();
    }

    public void onClick$cancelarEstado(Event event) {
        setEstadoListMode(true);
        refreshEstados();
    }

    public void onClick$eliminarEstado(Event event) {
        try {
            circuitosModel.deleteEstado();
        } catch (Exception ex) {
            showMessage("Ocurrió un error mientras intentaba eliminar un ítem: ", ex);
        }
        circuitosModel.setEstadoSelected(null);
        refreshEstados();
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
        comprobarEstadosAcciones();
        refreshAcciones();
    }

    public void onClick$nuevaAccion(Event event) {
        accionNueva = true;
        circuitosModel.setAccionSelected(new AccionesAppsEntity());
        changeAccionesMode();
        refreshAcciones();
    }

    public void onClick$editarAccion(Event event) {
        accionNueva = false;
        changeAccionesMode();
        refreshAcciones();
    }

    public void onClick$cancelarAccion(Event event) {
        setAccionesListMode(true);
        refreshAcciones();
    }

    public void onClick$eliminarAccion(Event event) {
        try {
            circuitosModel.deleteAccion();
        } catch (Exception ex) {
            showMessage("Ocurrió un error mientras intentaba eliminar un ítem: ", ex);
        }
        circuitosModel.setAccionSelected(null);
        refreshAcciones();
    }

    /*--------------------------------------------------Transiciones--------------------------------------------------------*/

    public void onClick$guardarTransicion(Event event) {

        try {
            if (transicionNueva) {
                circuitosModel.getTransicionSelected().setCircuitoId(circuitosModel.getSelected());
                circuitosModel.persistTransicion();
            } else {
                circuitosModel.mergeTransicion();
            }
        } catch (Exception ex) {
            showMessage("Ocurrió un error mientras se intentaba guardar la transicion: ", ex);
        }

        changeTransicionesMode();
        binder.saveAll();
        binder.loadAttribute(transicionesList, "model");
        refreshTransiciones();
    }

    public void onClick$nuevaTransicion(Event event) {
        transicionNueva = true;
        circuitosModel.setTransicionSelected(new TransicionEstadosEntity());
        changeTransicionesMode();
        refreshTransiciones();
    }

    public void onClick$editarTransicion(Event event) {
        transicionNueva = false;
        changeTransicionesMode();
        refreshTransiciones();
    }

    public void onClick$cancelarTransicion(Event event) {
        setTransicionesListMode(true);
        refreshTransiciones();
    }

    public void onClick$eliminarTransicion(Event event) {
        try {
            circuitosModel.deleteTransicion();
        } catch (Exception ex) {
            showMessage("Ocurrió un error mientras intentaba eliminar un ítem: ", ex);
        }
        circuitosModel.setTransicionSelected(null);
        refreshTransiciones();
    }

}
