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
public class TransicionEstadosEntityPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "estado_origen")
    private String estadoOrigen;
    @Basic(optional = false)
    @Column(name = "accion")
    private int accion;

    public TransicionEstadosEntityPK(String estadoOrigen, int accion) {
        this.estadoOrigen = estadoOrigen;
        this.accion = accion;
    }

    public String getEstadoOrigen() {
        return estadoOrigen;
    }

    public void setEstadoOrigen(String estadoOrigen) {
        this.estadoOrigen = estadoOrigen;
    }

    public int getAccion() {
        return accion;
    }

    public void setAccion(int accion) {
        this.accion = accion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estadoOrigen != null ? estadoOrigen.hashCode() : 0);
        hash += (int) accion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransicionEstadosEntityPK)) {
            return false;
        }
        TransicionEstadosEntityPK other = (TransicionEstadosEntityPK) object;
        if ((this.estadoOrigen == null && other.estadoOrigen != null) || (this.estadoOrigen != null && !this.estadoOrigen.equals(other.estadoOrigen))) {
            return false;
        }
        if (this.accion != other.accion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.TransicionEstadosEntityPK[estadoOrigen=" + estadoOrigen + ", accion=" + accion + "]";
    }

}
