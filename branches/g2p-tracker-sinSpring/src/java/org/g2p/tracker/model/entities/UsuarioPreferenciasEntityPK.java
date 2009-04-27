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
public class UsuarioPreferenciasEntityPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @Column(name = "preferencia_id")
    private int preferenciaId;

    public UsuarioPreferenciasEntityPK(int userId, int preferenciaId) {
        this.userId = userId;
        this.preferenciaId = preferenciaId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPreferenciaId() {
        return preferenciaId;
    }

    public void setPreferenciaId(int preferenciaId) {
        this.preferenciaId = preferenciaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userId;
        hash += (int) preferenciaId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioPreferenciasEntityPK)) {
            return false;
        }
        UsuarioPreferenciasEntityPK other = (UsuarioPreferenciasEntityPK) object;
        if (this.userId != other.userId) {
            return false;
        }
        if (this.preferenciaId != other.preferenciaId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.UsuarioPreferenciasEntityPK[userId=" + userId + ", preferenciaId=" + preferenciaId + "]";
    }

}
