/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.controllers;

import java.util.Date;
import org.g2p.tracker.model.entities.EstadosEntity;
import org.g2p.tracker.model.entities.ImportanciaEntity;
import org.g2p.tracker.model.entities.PrioridadesEntity;
import org.g2p.tracker.model.entities.TracksEntity;
import org.g2p.tracker.model.entities.WebsiteUsersEntity;
import org.zkoss.zul.Listbox;
import org.g2p.tracker.model.models.EstadosModel;
import org.g2p.tracker.model.models.ImportanciaModel;
import org.g2p.tracker.model.models.PrioridadesModel;
import org.g2p.tracker.model.models.TracksModel;
import org.g2p.tracker.model.models.WebsiteUserModel;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zkplus.databind.DataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Vbox;

/**
 *
 * @author nacho
 */
public class AbmcTracksController extends BaseController {

    protected WebsiteUserModel websiteUserModel = null;
    protected WebsiteUserModel websiteUserModelTrabajador = null;
    protected EstadosModel estadosModel = null;
    protected PrioridadesModel prioridadesModel = null;
    protected ImportanciaModel importanciaModel = null;
    protected TracksModel trackModel = null;
    protected Toolbar trackToolbar;
    protected Button nuevoTrack;
    protected Button guardarTrack;
    protected Button cancelarAltaTrack;
    protected Button editarTrack;
    protected Listbox tracksList;
    protected TracksEntity tracks;
    //Campos del track
    protected Textbox titulo;
    protected Textbox descripcion;
    protected Textbox observaciones;
    protected Combobox propietario;
    protected Combobox trabajador;
    protected Datebox fechaCreacion;
    protected Datebox fechaEstimadaRealizacion;
    protected Datebox fechaLimite;
    protected Datebox fechaRealizacion;
    protected Combobox estado;
    protected Combobox prioridad;
    protected Combobox importancia;
    protected Button addUser;
    //protected Button deleteUser;
    protected Component trackDetail;
    protected Component nuevoTrackView;
    protected Component listTrackView;
    protected boolean nuevoTrackMode;
    protected boolean listMode;
    protected boolean editMode;
    protected boolean trabajadorDos;
    Vbox workersBox;

    public AbmcTracksController() {
        super(true);
        websiteUserModel = new WebsiteUserModel();
        websiteUserModelTrabajador = new WebsiteUserModel();
        prioridadesModel = new PrioridadesModel();
        importanciaModel = new ImportanciaModel();
        estadosModel = new EstadosModel();
        trackModel = new TracksModel();
    }

    public WebsiteUserModel getWebsiteUserModel() {
        return websiteUserModel;
    }

    public void setWebsiteUserModel(WebsiteUserModel websiteUserModel) {
        this.websiteUserModel = websiteUserModel;
    }

    public WebsiteUserModel getWebsiteUserModelTrabajador() {
        return websiteUserModelTrabajador;
    }

    public void setWebsiteUserModelTrabajador(WebsiteUserModel websiteUserModelTrabajador) {
        this.websiteUserModelTrabajador = websiteUserModelTrabajador;
    }

    public EstadosModel getEstadosModel() {
        return estadosModel;
    }

    public void setEstadosModel(EstadosModel estadosModel) {
        this.estadosModel = estadosModel;
    }

    public PrioridadesModel getPrioridadesModel() {
        return prioridadesModel;
    }

    public void setPrioridadesModel(PrioridadesModel prioridadesModel) {
        this.prioridadesModel = prioridadesModel;
    }

    public ImportanciaModel getImportanciaModel() {
        return importanciaModel;
    }

    public void setImportanciaModel(ImportanciaModel importanciaModel) {
        this.importanciaModel = importanciaModel;
    }

    public TracksModel getTrackModel() {
        return trackModel;
    }

    public void setTrackModel(TracksModel trackModel) {
        this.trackModel = trackModel;
    }

    public void onCreate$abmcTracksWin(Event event) {
        // Obtengo el DataBinder que instancia la página
        binder = (DataBinder) getVariable("binder", true);

        setListMode(true);
        trabajadorDos = false;

        refresh();
    }

    public boolean isListMode() {
        return listMode;
    }

    protected void setListMode(boolean list) {
        nuevoTrackView.setVisible(!list);
        listTrackView.setVisible(list);
        cancelarAltaTrack.setVisible(!list);
        guardarTrack.setVisible(!list);
        nuevoTrack.setVisible(list);
        editarTrack.setVisible(list);
        listMode = list;
    }

    protected void nuevoTrack() {
        trackModel.setSelected(new TracksEntity());
        titulo.setText("");
        descripcion.setText("");
        observaciones.setText("");
        websiteUserModel.setSelected(null);
        websiteUserModelTrabajador.setSelected(null);
        trabajador.setSelectedIndex(1);
        ((TracksEntity) trackModel.getSelected()).setFechaCreacion(new Date());
        fechaEstimadaRealizacion.setText("");
        fechaLimite.setText("");
        fechaRealizacion.setText("");
        estadosModel.setSelected(estadosModel.getAll().get(0));
        prioridadesModel.setSelected(prioridadesModel.getAll().get(3));
        importanciaModel.setSelected(importanciaModel.getAll().get(3));
    }

    public void onClick$nuevoTrack(Event event) {
        nuevoTrack();
    }

    public void onClick$cancelarAltaTrack(Event event) {
        setListMode(true);
        refresh();
    }

    public void onClick$guardarTrack(Event event) {
        //save into bean
        binder.saveComponent(trackDetail); //reload model to force refresh

        ((TracksEntity) trackModel.getSelected()).setTitulo(titulo.getValue());
        ((TracksEntity) trackModel.getSelected()).setDescripcion(descripcion.getValue());
        ((TracksEntity) trackModel.getSelected()).setObservaciones(observaciones.getValue());
        ((TracksEntity) trackModel.getSelected()).setUserIdOwner((WebsiteUsersEntity) websiteUserModel.getSelected());
        //((TracksEntity)trackModel.getSelected()).setFechaCreacion(fechaCreacion.getValue());
        ((TracksEntity) trackModel.getSelected()).setDeadline(fechaLimite.getValue());
        ((TracksEntity) trackModel.getSelected()).setFechaEstimadaRealizacion(fechaEstimadaRealizacion.getValue());
        ((TracksEntity) trackModel.getSelected()).setFechaRealizacion(fechaRealizacion.getValue());
        ((TracksEntity) trackModel.getSelected()).setEstadoId((EstadosEntity) estadosModel.getSelected());
        ((TracksEntity) trackModel.getSelected()).setPrioridadId((PrioridadesEntity) prioridadesModel.getSelected());
        ((TracksEntity) trackModel.getSelected()).setImportanciaId((ImportanciaEntity) importanciaModel.getSelected());

        try {
            //store into db

            if (trackModel.getSelected().getPK() != null) {
                trackModel.merge(true);
            } else {
                trackModel.persist(true);
            }

            showMessage("El track se guardo correctamente");

        } catch (Exception ex) {
            try {
                showMessage("Ocurrió un error mientras se intentaba crear el track: ", ex);
                trackModel.getUtx().rollback();
            } catch (Exception ex1) {
                showMessage("Ocurrió un error mientras se intentaba hacer rollback de la operacion: ", ex);
            }
        } finally {
            //refresh the rolesList
            setListMode(true);
            refresh();
        }
    }

    /*public void onClick$addUser (Event event) {
    Hbox box = new Hbox();
    Combobox combo = new Combobox();
    Comboitem item = new Comboitem();
    Button button = new Button();

    box.setId("worker"+workersBox.getC);

    box.appendChild(combo);
    workersBox.appendChild(box);

    refresh();
    }*/
    public void onClick$deleteUser(Event event) {
        trabajadorDos = false;
        refresh();
    }

    public void refresh() {
        binder.loadAttribute(trackDetail, "model"); //reload model to force refresh
        binder.loadComponent(trackDetail); //reload visible to force refresh
    }

    public void onClick$editarTrack(ForwardEvent event) {
        setListMode(false);
    }

    public void onSelect$tracksList(Event event) {
    }

    public void onEcualizar$ecualizador(Event event) {
        System.out.println("ECUALIZAR");
    }
}
