/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.models;

import javax.naming.NamingException;
import javax.transaction.SystemException;
import org.g2p.tracker.model.daos.exceptions.IllegalOrphanException;
import org.g2p.tracker.model.daos.exceptions.NonexistentEntityException;
import org.g2p.tracker.model.entities.AccionesAppsEntity;
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

    public void mergeRol() throws IllegalOrphanException, NonexistentEntityException, Exception {
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

    /*public List<BaseEntity> getEstados() {
        if (selected != null) {
            Hashtable<String, Integer> queryParameters = new Hashtable<String, Integer>();
            queryParameters.put("userId", (Integer) selected.getPK());
            return findEntities("RolesPerWebsiteUsersEntity.findByUserId", queryParameters);
        }

        return null;
    }

    public List<BaseEntity> getEstadosDisponibles() {
        if (selected != null && rolSelected != null) {
            Hashtable<String, Integer> queryParameters = new Hashtable<String, Integer>();
            queryParameters.put("userId", (Integer) selected.getPK());
            queryParameters.put("rolId", ((RolesPerWebsiteUsersEntityPK) rolSelected.getPK()).getRolId());
            return findEntities("RolesPerWebsiteUsersEntity.findByRolIdComplement", queryParameters);
        }

        return null;
    }*/

}
