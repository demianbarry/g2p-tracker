/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author nacho
 */
@Embeddable
public class UsuarioRolesEntityPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "rol_id")
    private int rolId;
    @Basic(optional = false)
    @Column(name = "user_id")
    private int userId;

    public UsuarioRolesEntityPK() {
    }

    public UsuarioRolesEntityPK(int rolId, int userId) {
        this.rolId = rolId;
        this.userId = userId;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) rolId;
        hash += (int) userId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioRolesEntityPK)) {
            return false;
        }
        UsuarioRolesEntityPK other = (UsuarioRolesEntityPK) object;
        if (this.rolId != other.rolId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.UsuarioRolesPK[rolId=" + rolId + ", userId=" + userId + "]";
    }

}
