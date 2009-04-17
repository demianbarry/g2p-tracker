/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "lov_atributo")
@NamedQueries({@NamedQuery(name = "LovAtributoEntity.findAll", query = "SELECT l FROM LovAtributoEntity l"), @NamedQuery(name = "LovAtributoEntity.findByClaseLovAtributoId", query = "SELECT l FROM LovAtributoEntity l WHERE l.lovAtributoEntityPK.claseLovAtributoId = :claseLovAtributoId"), @NamedQuery(name = "LovAtributoEntity.findByValor", query = "SELECT l FROM LovAtributoEntity l WHERE l.lovAtributoEntityPK.valor = :valor"), @NamedQuery(name = "LovAtributoEntity.findByDescripcion", query = "SELECT l FROM LovAtributoEntity l WHERE l.descripcion = :descripcion")})
public class LovAtributoEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LovAtributoEntityPK lovAtributoEntityPK;
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "clase_lov_atributo_id", referencedColumnName = "clase_lov_atributo_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ClaseLovAtributoEntity claseLovAtributoEntity;

    public LovAtributoEntity() {
    }

    public LovAtributoEntity(LovAtributoEntityPK lovAtributoEntityPK) {
        this.lovAtributoEntityPK = lovAtributoEntityPK;
    }

    public LovAtributoEntity(int claseLovAtributoId, String valor) {
        this.lovAtributoEntityPK = new LovAtributoEntityPK(claseLovAtributoId, valor);
    }

    public LovAtributoEntityPK getLovAtributoEntityPK() {
        return lovAtributoEntityPK;
    }

    public void setLovAtributoEntityPK(LovAtributoEntityPK lovAtributoEntityPK) {
        this.lovAtributoEntityPK = lovAtributoEntityPK;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ClaseLovAtributoEntity getClaseLovAtributoEntity() {
        return claseLovAtributoEntity;
    }

    public void setClaseLovAtributoEntity(ClaseLovAtributoEntity claseLovAtributoEntity) {
        this.claseLovAtributoEntity = claseLovAtributoEntity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lovAtributoEntityPK != null ? lovAtributoEntityPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LovAtributoEntity)) {
            return false;
        }
        LovAtributoEntity other = (LovAtributoEntity) object;
        if ((this.lovAtributoEntityPK == null && other.lovAtributoEntityPK != null) || (this.lovAtributoEntityPK != null && !this.lovAtributoEntityPK.equals(other.lovAtributoEntityPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.entities.LovAtributoEntity[lovAtributoEntityPK=" + lovAtributoEntityPK + "]";
    }

}
