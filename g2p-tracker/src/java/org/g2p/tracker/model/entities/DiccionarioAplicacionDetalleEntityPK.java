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
public class DiccionarioAplicacionDetalleEntityPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "tipo_objeto")
    private String tipoObjeto;
    @Basic(optional = false)
    @Column(name = "nombre_objeto")
    private String nombreObjeto;
    @Basic(optional = false)
    @Column(name = "tipo_detalle")
    private String tipoDetalle;
    @Basic(optional = false)
    @Column(name = "nombre_detalle")
    private String nombreDetalle;

    public DiccionarioAplicacionDetalleEntityPK() {
    }

    public DiccionarioAplicacionDetalleEntityPK(String tipoObjeto, String nombreObjeto, String tipoDetalle, String nombreDetalle) {
        this.tipoObjeto = tipoObjeto;
        this.nombreObjeto = nombreObjeto;
        this.tipoDetalle = tipoDetalle;
        this.nombreDetalle = nombreDetalle;
    }

    public String getTipoObjeto() {
        return tipoObjeto;
    }

    public void setTipoObjeto(String tipoObjeto) {
        this.tipoObjeto = tipoObjeto;
    }

    public String getNombreObjeto() {
        return nombreObjeto;
    }

    public void setNombreObjeto(String nombreObjeto) {
        this.nombreObjeto = nombreObjeto;
    }

    public String getTipoDetalle() {
        return tipoDetalle;
    }

    public void setTipoDetalle(String tipoDetalle) {
        this.tipoDetalle = tipoDetalle;
    }

    public String getNombreDetalle() {
        return nombreDetalle;
    }

    public void setNombreDetalle(String nombreDetalle) {
        this.nombreDetalle = nombreDetalle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoObjeto != null ? tipoObjeto.hashCode() : 0);
        hash += (nombreObjeto != null ? nombreObjeto.hashCode() : 0);
        hash += (tipoDetalle != null ? tipoDetalle.hashCode() : 0);
        hash += (nombreDetalle != null ? nombreDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiccionarioAplicacionDetalleEntityPK)) {
            return false;
        }
        DiccionarioAplicacionDetalleEntityPK other = (DiccionarioAplicacionDetalleEntityPK) object;
        if ((this.tipoObjeto == null && other.tipoObjeto != null) || (this.tipoObjeto != null && !this.tipoObjeto.equals(other.tipoObjeto))) {
            return false;
        }
        if ((this.nombreObjeto == null && other.nombreObjeto != null) || (this.nombreObjeto != null && !this.nombreObjeto.equals(other.nombreObjeto))) {
            return false;
        }
        if ((this.tipoDetalle == null && other.tipoDetalle != null) || (this.tipoDetalle != null && !this.tipoDetalle.equals(other.tipoDetalle))) {
            return false;
        }
        if ((this.nombreDetalle == null && other.nombreDetalle != null) || (this.nombreDetalle != null && !this.nombreDetalle.equals(other.nombreDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.entities.DiccionarioAplicacionDetalleEntityPK[tipoObjeto=" + tipoObjeto + ", nombreObjeto=" + nombreObjeto + ", tipoDetalle=" + tipoDetalle + ", nombreDetalle=" + nombreDetalle + "]";
    }

}
