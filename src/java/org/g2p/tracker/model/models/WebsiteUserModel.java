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
import org.g2p.tracker.model.entities.BaseEntity;
import org.g2p.tracker.model.entities.RolesPerWebsiteUsersEntity;
import org.g2p.tracker.model.entities.RolesPerWebsiteUsersEntityPK;
import org.g2p.tracker.model.entities.WebsiteUsersEntity;

/**
 *
 * @author nacho
 */
public class WebsiteUserModel extends BaseModel {

    protected RolesPerWebsiteUsersModel rolesPerWebsiteUsersModel = new RolesPerWebsiteUsersModel();
    protected RolesPerWebsiteUsersEntity rolSelected;
    protected RolesModel rolesModel = new RolesModel();

    public WebsiteUserModel() {
        super(WebsiteUsersEntity.class);
    }

    public void persistRol(boolean ownTx) throws Exception {
        rolesPerWebsiteUsersModel.create(rolSelected, ownTx);
    }

    public void mergeRol(boolean ownTx) throws IllegalOrphanException, NonexistentEntityException, Exception {
        rolesPerWebsiteUsersModel.edit(rolSelected, ownTx);
    }

    public void deleteRol(boolean ownTx) throws IllegalOrphanException, NonexistentEntityException, NamingException, IllegalStateException, SecurityException, SystemException, Exception {
        rolesPerWebsiteUsersModel.destroy(rolSelected, ownTx);
    }

    public void persistRol() throws Exception {
        rolesPerWebsiteUsersModel.create(rolSelected);
    }

    public void mergeRol() throws IllegalOrphanException, NonexistentEntityException, Exception {
        rolesPerWebsiteUsersModel.edit(rolSelected);
    }

    public void deleteRol() throws IllegalOrphanException, NonexistentEntityException, NamingException, IllegalStateException, SecurityException, SystemException, Exception {
        rolesPerWebsiteUsersModel.destroy(rolSelected);
    }

    public void setRolSelected(RolesPerWebsiteUsersEntity rolSelected) {
        this.rolSelected = rolSelected;
    }

    public RolesPerWebsiteUsersEntity getRolSelected() {
        return rolSelected;
    }

    public List<BaseEntity> getUsuariosRoles() {
        if (selected != null) {
            Hashtable<String, Integer> queryParameters = new Hashtable<String, Integer>();
            queryParameters.put("userId", (Integer) selected.getPK());
            return findEntities("RolesPerWebsiteUsersEntity.findByUserId", queryParameters);
        }

        return null;
    }

    public List<BaseEntity> getRolesDisponibles() {
        if (selected != null && rolSelected != null) {
            Hashtable<String, Integer> queryParameters = new Hashtable<String, Integer>();
            queryParameters.put("userId", (Integer) selected.getPK());
            queryParameters.put("rolId", ((RolesPerWebsiteUsersEntityPK) rolSelected.getPK()).getRolId());
            return findEntities("RolesPerWebsiteUsersEntity.findByRolIdComplement", queryParameters);
        }

        return null;
    }
}
