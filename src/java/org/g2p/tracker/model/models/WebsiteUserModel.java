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
import org.g2p.tracker.model.entities.UsuarioRolesEntity;
import org.g2p.tracker.model.entities.UsuarioRolesEntityPK;
import org.g2p.tracker.model.entities.WebsiteUserEntity;

/**
 *
 * @author nacho
 */
public class WebsiteUserModel extends BaseModel {

    protected UsuarioRolesModel usuarioRolesModel = new UsuarioRolesModel();
    protected UsuarioRolesEntity rolSelected;
    protected RolesModel rolesModel = new RolesModel();

    public WebsiteUserModel() {
        super(WebsiteUserEntity.class);
    }

    public void persistRol(boolean ownTx) throws Exception {
        usuarioRolesModel.create(rolSelected, ownTx);
    }

    public void mergeRol(boolean ownTx) throws IllegalOrphanException, NonexistentEntityException, Exception {
        usuarioRolesModel.edit(rolSelected, ownTx);
    }

    public void deleteRol(boolean ownTx) throws IllegalOrphanException, NonexistentEntityException, NamingException, IllegalStateException, SecurityException, SystemException, Exception {
        usuarioRolesModel.destroy(rolSelected, ownTx);
    }

    public void persistRol() throws Exception {
        usuarioRolesModel.create(rolSelected);
    }

    public void mergeRol() throws IllegalOrphanException, NonexistentEntityException, Exception {
        usuarioRolesModel.edit(rolSelected);
    }

    public void deleteRol() throws IllegalOrphanException, NonexistentEntityException, NamingException, IllegalStateException, SecurityException, SystemException, Exception {
        usuarioRolesModel.destroy(rolSelected);
    }

    public void setRolSelected(UsuarioRolesEntity rolSelected) {
        this.rolSelected = rolSelected;
    }

    public UsuarioRolesEntity getRolSelected() {
        return rolSelected;
    }

    public List<BaseEntity> getUsuariosRoles() {
        if (selected != null) {
            Hashtable<String, Integer> queryParameters = new Hashtable<String, Integer>();
            queryParameters.put("userId", (Integer) selected.getPK());
            return usuarioRolesModel.findEntities("UsuarioRolesEntity.findByUserId", queryParameters);
        }

        return null;
    }

    public List<BaseEntity> getRolesDisponibles() {
        if (selected != null && rolSelected != null) {
            Hashtable<String, Integer> queryParameters = new Hashtable<String, Integer>();
            queryParameters.put("userId", (Integer) selected.getPK());
            queryParameters.put("rolId", ((UsuarioRolesEntityPK) rolSelected.getPK()).getRolId());
            return findEntities("UsuarioRolesEntity.findByRolIdComplement", queryParameters);
        }

        return null;
    }
}
