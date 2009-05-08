/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author nacho
 */
@Entity
@Table(name = "clase_atributo_rol")
@NamedQueries({@NamedQuery(name = "ClaseAtributoRol.findAll", query = "SELECT c FROM ClaseAtributoRol c")})
public class ClaseAtributoRolEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "clase_atributo_rol_id")
    private Integer claseAtributoRolId;
    @Basic(optional = false)
    @Column(name = "etiqueta")
    private String etiqueta;
    @OneToMany(mappedBy = "claseAtributoRolId", fetch = FetchType.LAZY)
    private Set<AtributosRolEntity> atributosRolCollection;
    @OneToMany(mappedBy = "claseAtributoRolId1", fetch = FetchType.LAZY)
    private Set<AtributosRolEntity> atributosRolCollection1;

    public ClaseAtributoRolEntity() {
    }

    public ClaseAtributoRolEntity(Integer claseAtributoRolId) {
        this.claseAtributoRolId = claseAtributoRolId;
    }

    public ClaseAtributoRolEntity(Integer claseAtributoRolId, String etiqueta) {
        this.claseAtributoRolId = claseAtributoRolId;
        this.etiqueta = etiqueta;
    }

    public Integer getClaseAtributoRolId() {
        return claseAtributoRolId;
    }

    public void setClaseAtributoRolId(Integer claseAtributoRolId) {
        this.claseAtributoRolId = claseAtributoRolId;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public Set<AtributosRolEntity> getAtributosRolCollection() {
        return atributosRolCollection;
    }

    public void setAtributosRolCollection(Set<AtributosRolEntity> atributosRolCollection) {
        this.atributosRolCollection = atributosRolCollection;
    }

    public Set<AtributosRolEntity> getAtributosRolCollection1() {
        return atributosRolCollection1;
    }

    public void setAtributosRolCollection1(Set<AtributosRolEntity> atributosRolCollection1) {
        this.atributosRolCollection1 = atributosRolCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claseAtributoRolId != null ? claseAtributoRolId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClaseAtributoRolEntity)) {
            return false;
        }
        ClaseAtributoRolEntity other = (ClaseAtributoRolEntity) object;
        if ((this.claseAtributoRolId == null && other.claseAtributoRolId != null) || (this.claseAtributoRolId != null && !this.claseAtributoRolId.equals(other.claseAtributoRolId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.ClaseAtributoRol[claseAtributoRolId=" + claseAtributoRolId + "]";
    }

    @Override
    public Object getPK() {
        return claseAtributoRolId;
    }

}
