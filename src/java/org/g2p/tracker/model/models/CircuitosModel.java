/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.models;

import java.util.Hashtable;
import java.util.List;
import javax.naming.NamingException;
import javax.transaction.SystemException;
import org.g2p.tracker.model.daos.exceptions.IllegalOrphanException;
import org.g2p.tracker.model.daos.exceptions.NonexistentEntityException;
import org.g2p.tracker.model.entities.AccionesAppsEntity;
import org.g2p.tracker.model.entities.BaseEntity;
import org.g2p.tracker.model.entities.CircuitosEstadosEntity;
import org.g2p.tracker.model.entities.EstadosEntity;

/**
 *
 * @author nacho
 */
public class CircuitosModel extends BaseModel {

    /*--------------------------------------------------Declaraciones--------------------------------------------------------*/

    //Models
    protected EstadosModel estadosModel = new EstadosModel();
    protected AccionesModel accionesModel = new AccionesModel();
    
    //Entitys
    protected EstadosEntity estadoSelected;
    protected AccionesAppsEntity accionSelected;


    //Constructor
    public CircuitosModel() {
        super(CircuitosEstadosEntity.class);
    }

    @Override
    public CircuitosEstadosEntity getSelected() {
        return (CircuitosEstadosEntity) super.getSelected();
    }

    /*--------------------------------------------------Metodos para manejar estados--------------------------------------------------------*/

    public void persistEstado(boolean ownTx) throws Exception {
        estadosModel.create(estadoSelected, ownTx);
    }

    public void mergeEstado(boolean ownTx) throws IllegalOrphanException, NonexistentEntityException, Exception {
        estadosModel.edit(estadoSelected, ownTx);
    }

    public void deleteEstado(boolean ownTx) throws IllegalOrphanException, NonexistentEntityException, NamingException, IllegalStateException, SecurityException, SystemException, Exception {
        estadosModel.destroy(estadoSelected, ownTx);
    }

    public void persistEstado() throws Exception {
        estadosModel.create(estadoSelected);
    }

    public void mergeEstado() throws IllegalOrphanException, NonexistentEntityException, Exception {
        estadosModel.edit(estadoSelected);
    }

    public void deleteEstado() throws IllegalOrphanException, NonexistentEntityException, NamingException, IllegalStateException, SecurityException, SystemException, Exception {
        estadosModel.destroy(estadoSelected);
    }

    public void setEstadoSelected(EstadosEntity estadoSelected) {
        this.estadoSelected = estadoSelected;
    }

    public EstadosEntity getEstadoSelected() {
        return estadoSelected;
    }

    public List<BaseEntity> getEstados() {
        if (selected != null && selected.getPK() != null) {
            Hashtable<String, Integer> queryParameters = new Hashtable<String, Integer>();
            queryParameters.put("circuitoId", (Integer) selected.getPK());
            return findEntities("EstadosEntity.findByCircuitoId", queryParameters);
        }

        return null;
    }


    /*--------------------------------------------------Metodos para manejar acciones--------------------------------------------------------*/

    public void persistAccion(boolean ownTx) throws Exception {
        accionesModel.create(accionSelected, ownTx);
    }

    public void mergeAccion(boolean ownTx) throws IllegalOrphanException, NonexistentEntityException, Exception {
        accionesModel.edit(accionSelected, ownTx);
    }

    public void deleteAccion(boolean ownTx) throws IllegalOrphanException, NonexistentEntityException, NamingException, IllegalStateException, SecurityException, SystemException, Exception {
        accionesModel.destroy(accionSelected, ownTx);
    }

    public void persistAccion() throws Exception {
        accionesModel.create(accionSelected);
    }

    public void mergeAccion() throws IllegalOrphanException, NonexistentEntityException, Exception {
        accionesModel.edit(accionSelected);
    }

    public void deleteAccion() throws IllegalOrphanException, NonexistentEntityException, NamingException, IllegalStateException, SecurityException, SystemException, Exception {
        accionesModel.destroy(accionSelected);
    }

    public void setAccionSelected(AccionesAppsEntity accionSelected) {
        this.accionSelected = accionSelected;
    }

    public AccionesAppsEntity getAccionSelected() {
        return accionSelected;
    }

    public List<BaseEntity> getAcciones() {
        if (selected != null && selected.getPK() != null) {
            Hashtable<String, Integer> queryParameters = new Hashtable<String, Integer>();
            queryParameters.put("circuitoId", (Integer) selected.getPK());
            return findEntities("AccionesAppsEntity.findByCircuitoId", queryParameters);
        }

        return null;
    }

}
