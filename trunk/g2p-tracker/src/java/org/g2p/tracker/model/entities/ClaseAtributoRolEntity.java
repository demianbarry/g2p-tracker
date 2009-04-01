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
 * @author Administrador
 */
@Entity
@Table(name = "clase_atributo_rol")
@NamedQueries({@NamedQuery(name = "ClaseAtributoRolEntity.findAll", query = "SELECT c FROM ClaseAtributoRolEntity c"), @NamedQuery(name = "ClaseAtributoRolEntity.findByClaseAtributoRolId", query = "SELECT c FROM ClaseAtributoRolEntity c WHERE c.claseAtributoRolId = :claseAtributoRolId"), @NamedQuery(name = "ClaseAtributoRolEntity.findByEtiqueta", query = "SELECT c FROM ClaseAtributoRolEntity c WHERE c.etiqueta = :etiqueta")})
public class ClaseAtributoRolEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "clase_atributo_rol_id")
    private Integer claseAtributoRolId;
    @Basic(optional = false)
    @Column(name = "etiqueta")
    private String etiqueta;
    @OneToMany(mappedBy = "claseAtributoRolId", fetch = FetchType.EAGER)
    private Set<AtributosRolEntity> atributosRolEntityCollection;

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

    public Set<AtributosRolEntity> getAtributosRolEntityCollection() {
        return atributosRolEntityCollection;
    }

    public void setAtributosRolEntityCollection(Set<AtributosRolEntity> atributosRolEntityCollection) {
        this.atributosRolEntityCollection = atributosRolEntityCollection;
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
        return "org.g2p.tracker.entities.ClaseAtributoRolEntity[claseAtributoRolId=" + claseAtributoRolId + "]";
    }

}
