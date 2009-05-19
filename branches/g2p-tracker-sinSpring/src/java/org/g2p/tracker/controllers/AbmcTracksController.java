/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.controllers;

import org.zkoss.zul.Toolbarbutton;
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

/**
 *
 * @author nacho
 */
public class AbmcTracksController extends BaseController {

    protected Toolbarbutton termiandos;
    protected Toolbarbutton enCurso;
    protected Toolbarbutton pendientes;
    protected Toolbarbutton congelados;
    protected Toolbarbutton nuevoTrack;

    protected Component trackDetail;
    protected Component terminadosTrackView;
    protected Component enCuersoTrackView;
    protected Component pendientesTrackView;
    protected Component congeladosTrackView;
    protected Component nuevoTrackView;
    
    protected boolean terminadosTrackMode;
    protected boolean enCursoTrackMode;
    protected boolean pendientesTrackMode;
    protected boolean congeladosTrackMode;
    protected boolean nuevoTrackMode;


    public AbmcTracksController() {
        super(true);
    }

    public void onCreate$abmcTracksWin(Event event) {
        // Obtengo el DataBinder que instancia la página
        binder = (DataBinder) getVariable("binder", true);

        setNuevoTrackMode();
    }

    public boolean isNuevoTrackMode() {
        return nuevoTrackMode;
    }

    public boolean isTerminadosTrackMode() {
        return terminadosTrackMode;
    }

    public boolean isEnCursoTrackMode() {
        return enCursoTrackMode;
    }

    public boolean isPendientesTrackMode() {
        return pendientesTrackMode;
    }

    public boolean isCongeladosTrackMode() {
        return congeladosTrackMode;
    }

    protected void setNuevoTrackMode() {
        terminadosTrackMode = false;
        enCursoTrackMode = false;
        pendientesTrackMode = false;
        congeladosTrackMode = false;
        nuevoTrackMode = true;
    }

    protected void setTerminadosTrackMode() {
        terminadosTrackMode = true;
        enCursoTrackMode = false;
        pendientesTrackMode = false;
        congeladosTrackMode = false;
        nuevoTrackMode = false;
    }

    protected void setEnCursoTrackMode() {
        terminadosTrackMode = false;
        enCursoTrackMode = true;
        pendientesTrackMode = false;
        congeladosTrackMode = false;
        nuevoTrackMode = false;
    }

    protected void setPendientesTrackMode() {
        terminadosTrackMode = false;
        enCursoTrackMode = false;
        pendientesTrackMode = true;
        congeladosTrackMode = false;
        nuevoTrackMode = false;
    }

    protected void setCongeladosTrackMode() {
        terminadosTrackMode = false;
        enCursoTrackMode = false;
        pendientesTrackMode = false;
        congeladosTrackMode = true;
        nuevoTrackMode = false;
    }

    public void onClick$terminados (Event event) {
        setTerminadosTrackMode();
        refresh();
    }

    public void onClick$enCurso (Event event) {
        setEnCursoTrackMode();
        refresh();
    }

    public void onClick$pendientes (Event event) {
        setPendientesTrackMode();
        refresh();
    }

    public void onClick$congelados (Event event) {
        setCongeladosTrackMode();
        refresh();
    }

    public void onClick$nuevoTrack (Event event) {
        setNuevoTrackMode();
        refresh();
    }

    public void refresh () {
        binder.loadAttribute(trackDetail, "model"); //reload model to force refresh
        binder.loadComponent(trackDetail); //reload visible to force refresh
    }

}
