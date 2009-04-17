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
 * @author Administrador
 */
@Embeddable
public class RolesEntidadEntityPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "entidad_id")
    private int entidadId;
    @Basic(optional = false)
    @Column(name = "rol")
    private String rol;

    public RolesEntidadEntityPK() {
    }

    public RolesEntidadEntityPK(int entidadId, String rol) {
        this.entidadId = entidadId;
        this.rol = rol;
    }

    public int getEntidadId() {
        return entidadId;
    }

    public void setEntidadId(int entidadId) {
        this.entidadId = entidadId;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) entidadId;
        hash += (rol != null ? rol.hashCode() : 0);
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
        if ((this.rol == null && other.rol != null) || (this.rol != null && !this.rol.equals(other.rol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.entities.RolesEntidadEntityPK[entidadId=" + entidadId + ", rol=" + rol + "]";
    }

}
