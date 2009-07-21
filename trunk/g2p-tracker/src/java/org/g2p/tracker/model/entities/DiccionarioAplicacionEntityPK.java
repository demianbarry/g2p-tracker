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
public class DiccionarioAplicacionEntityPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "tipo_objeto")
    private String tipoObjeto;
    @Basic(optional = false)
    @Column(name = "nombre_objeto")
    private String nombreObjeto;

    public DiccionarioAplicacionEntityPK() {
    }

    public DiccionarioAplicacionEntityPK(String tipoObjeto, String nombreObjeto) {
        this.tipoObjeto = tipoObjeto;
        this.nombreObjeto = nombreObjeto;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoObjeto != null ? tipoObjeto.hashCode() : 0);
        hash += (nombreObjeto != null ? nombreObjeto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiccionarioAplicacionEntityPK)) {
            return false;
        }
        DiccionarioAplicacionEntityPK other = (DiccionarioAplicacionEntityPK) object;
        if ((this.tipoObjeto == null && other.tipoObjeto != null) || (this.tipoObjeto != null && !this.tipoObjeto.equals(other.tipoObjeto))) {
            return false;
        }
        if ((this.nombreObjeto == null && other.nombreObjeto != null) || (this.nombreObjeto != null && !this.nombreObjeto.equals(other.nombreObjeto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.DiccionarioAplicacionPK[tipoObjeto=" + tipoObjeto + ", nombreObjeto=" + nombreObjeto + "]";
    }

}
