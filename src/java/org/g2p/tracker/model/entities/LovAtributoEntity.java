/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author nacho
 */
@Entity
@Table(name = "lov_atributo")
@NamedQueries({@NamedQuery(name = "LovAtributoEntity.findAll", query = "SELECT l FROM LovAtributoEntity l")})
public class LovAtributoEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "clase_lov_atributo_id")
    private Integer claseLovAtributoId;
    @Basic(optional = false)
    @Column(name = "valor")
    private String valor;
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "clase_lov_atributo_id", referencedColumnName = "clase_lov_atributo_id", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private ClaseLovAtributoEntity claseLovAtributo;

    public LovAtributoEntity() {
    }

    public LovAtributoEntity(Integer claseLovAtributoId) {
        this.claseLovAtributoId = claseLovAtributoId;
    }

    public LovAtributoEntity(Integer claseLovAtributoId, String valor) {
        this.claseLovAtributoId = claseLovAtributoId;
        this.valor = valor;
    }

    public Integer getClaseLovAtributoId() {
        return claseLovAtributoId;
    }

    public void setClaseLovAtributoId(Integer claseLovAtributoId) {
        this.claseLovAtributoId = claseLovAtributoId;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ClaseLovAtributoEntity getClaseLovAtributo() {
        return claseLovAtributo;
    }

    public void setClaseLovAtributo(ClaseLovAtributoEntity claseLovAtributo) {
        this.claseLovAtributo = claseLovAtributo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claseLovAtributoId != null ? claseLovAtributoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LovAtributoEntity)) {
            return false;
        }
        LovAtributoEntity other = (LovAtributoEntity) object;
        if ((this.claseLovAtributoId == null && other.claseLovAtributoId != null) || (this.claseLovAtributoId != null && !this.claseLovAtributoId.equals(other.claseLovAtributoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.LovAtributo[claseLovAtributoId=" + claseLovAtributoId + "]";
    }

    @Override
    public Object getPK() {
        return claseLovAtributoId;
    }

}
