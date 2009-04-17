/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "clase_lov_atributo")
@NamedQueries({@NamedQuery(name = "ClaseLovAtributoEntity.findAll", query = "SELECT c FROM ClaseLovAtributoEntity c"), @NamedQuery(name = "ClaseLovAtributoEntity.findByClaseLovAtributoId", query = "SELECT c FROM ClaseLovAtributoEntity c WHERE c.claseLovAtributoId = :claseLovAtributoId"), @NamedQuery(name = "ClaseLovAtributoEntity.findByNombre", query = "SELECT c FROM ClaseLovAtributoEntity c WHERE c.nombre = :nombre"), @NamedQuery(name = "ClaseLovAtributoEntity.findByDescripcion", query = "SELECT c FROM ClaseLovAtributoEntity c WHERE c.descripcion = :descripcion"), @NamedQuery(name = "ClaseLovAtributoEntity.findByObservaciones", query = "SELECT c FROM ClaseLovAtributoEntity c WHERE c.observaciones = :observaciones")})
public class ClaseLovAtributoEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "clase_lov_atributo_id")
    private Integer claseLovAtributoId;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "observaciones")
    private String observaciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "claseLovAtributoEntity", fetch = FetchType.EAGER)
    private Set<LovAtributoEntity> lovAtributoEntityCollection;

    public ClaseLovAtributoEntity() {
    }

    public ClaseLovAtributoEntity(Integer claseLovAtributoId) {
        this.claseLovAtributoId = claseLovAtributoId;
    }

    public ClaseLovAtributoEntity(Integer claseLovAtributoId, String nombre) {
        this.claseLovAtributoId = claseLovAtributoId;
        this.nombre = nombre;
    }

    public Integer getClaseLovAtributoId() {
        return claseLovAtributoId;
    }

    public void setClaseLovAtributoId(Integer claseLovAtributoId) {
        this.claseLovAtributoId = claseLovAtributoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Set<LovAtributoEntity> getLovAtributoEntityCollection() {
        return lovAtributoEntityCollection;
    }

    public void setLovAtributoEntityCollection(Set<LovAtributoEntity> lovAtributoEntityCollection) {
        this.lovAtributoEntityCollection = lovAtributoEntityCollection;
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
        if (!(object instanceof ClaseLovAtributoEntity)) {
            return false;
        }
        ClaseLovAtributoEntity other = (ClaseLovAtributoEntity) object;
        if ((this.claseLovAtributoId == null && other.claseLovAtributoId != null) || (this.claseLovAtributoId != null && !this.claseLovAtributoId.equals(other.claseLovAtributoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.entities.ClaseLovAtributoEntity[claseLovAtributoId=" + claseLovAtributoId + "]";
    }

}
