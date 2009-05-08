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
public class AtributosConfiguracionEntityPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "configuracion_id")
    private int configuracionId;
    @Basic(optional = false)
    @Column(name = "atributo_id")
    private int atributoId;

    public AtributosConfiguracionEntityPK() {
    }

    public AtributosConfiguracionEntityPK(int configuracionId, int atributoId) {
        this.configuracionId = configuracionId;
        this.atributoId = atributoId;
    }

    public int getConfiguracionId() {
        return configuracionId;
    }

    public void setConfiguracionId(int configuracionId) {
        this.configuracionId = configuracionId;
    }

    public int getAtributoId() {
        return atributoId;
    }

    public void setAtributoId(int atributoId) {
        this.atributoId = atributoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) configuracionId;
        hash += (int) atributoId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AtributosConfiguracionEntityPK)) {
            return false;
        }
        AtributosConfiguracionEntityPK other = (AtributosConfiguracionEntityPK) object;
        if (this.configuracionId != other.configuracionId) {
            return false;
        }
        if (this.atributoId != other.atributoId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.AtributosConfiguracionPK[configuracionId=" + configuracionId + ", atributoId=" + atributoId + "]";
    }

}
