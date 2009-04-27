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
public class LovAtributoEntityPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "clase_lov_atributo_id")
    private int claseLovAtributoId;
    @Basic(optional = false)
    @Column(name = "valor")
    private String valor;

    public LovAtributoEntityPK(int claseLovAtributoId, String valor) {
        this.claseLovAtributoId = claseLovAtributoId;
        this.valor = valor;
    }

    public int getClaseLovAtributoId() {
        return claseLovAtributoId;
    }

    public void setClaseLovAtributoId(int claseLovAtributoId) {
        this.claseLovAtributoId = claseLovAtributoId;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) claseLovAtributoId;
        hash += (valor != null ? valor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LovAtributoEntityPK)) {
            return false;
        }
        LovAtributoEntityPK other = (LovAtributoEntityPK) object;
        if (this.claseLovAtributoId != other.claseLovAtributoId) {
            return false;
        }
        if ((this.valor == null && other.valor != null) || (this.valor != null && !this.valor.equals(other.valor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.LovAtributoEntityPK[claseLovAtributoId=" + claseLovAtributoId + ", valor=" + valor + "]";
    }

}
