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
public class RolesEntidadEntityPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "entidad_id")
    private int entidadId;
    @Basic(optional = false)
    @Column(name = "rol_id")
    private String rolId;

    public RolesEntidadEntityPK() {
    }

    public RolesEntidadEntityPK(int entidadId, String rolId) {
        this.entidadId = entidadId;
        this.rolId = rolId;
    }

    public int getEntidadId() {
        return entidadId;
    }

    public void setEntidadId(int entidadId) {
        this.entidadId = entidadId;
    }

    public String getRolId() {
        return rolId;
    }

    public void setRolId(String rolId) {
        this.rolId = rolId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) entidadId;
        hash += (rolId != null ? rolId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolesEntidadEntityPK)) {
            return false;
        }
        RolesEntidadEntityPK other = (RolesEntidadEntityPK) object;
        if (this.entidadId != other.entidadId) {
            return false;
        }
        if ((this.rolId == null && other.rolId != null) || (this.rolId != null && !this.rolId.equals(other.rolId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.RolesEntidadPK[entidadId=" + entidadId + ", rolId=" + rolId + "]";
    }

}
