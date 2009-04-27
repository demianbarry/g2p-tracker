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
@Table(name = "entidad_externa")
@NamedQueries({@NamedQuery(name = "EntidadExternaEntity.findAll", query = "SELECT e FROM EntidadExternaEntity e")})
public class EntidadExternaEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "entidad_id")
    private Integer entidadId;
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "observaciones")
    private String observaciones;
    @Basic(optional = false)
    @Column(name = "activo")
    private char activo;
    @Basic(optional = false)
    @Column(name = "anulado")
    private char anulado;
    @OneToMany(mappedBy = "entidadId", fetch = FetchType.EAGER)
    private Set<AtributosEntidadEntity> atributosEntidadEntityCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entidadExternaEntity", fetch = FetchType.EAGER)
    private Set<RolesEntidadEntity> rolesEntidadEntityCollection;

    public EntidadExternaEntity() {
    }

    public EntidadExternaEntity(Integer entidadId) {
        this.entidadId = entidadId;
    }

    public EntidadExternaEntity(Integer entidadId, String nombre, char activo, char anulado) {
        this.entidadId = entidadId;
        this.nombre = nombre;
        this.activo = activo;
        this.anulado = anulado;
    }

    public Integer getEntidadId() {
        return entidadId;
    }

    public void setEntidadId(Integer entidadId) {
        this.entidadId = entidadId;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public char getActivo() {
        return activo;
    }

    public void setActivo(char activo) {
        this.activo = activo;
    }

    public char getAnulado() {
        return anulado;
    }

    public void setAnulado(char anulado) {
        this.anulado = anulado;
    }

    public Set<AtributosEntidadEntity> getAtributosEntidadEntityCollection() {
        return atributosEntidadEntityCollection;
    }

    public void setAtributosEntidadEntityCollection(Set<AtributosEntidadEntity> atributosEntidadEntityCollection) {
        this.atributosEntidadEntityCollection = atributosEntidadEntityCollection;
    }

    public Set<RolesEntidadEntity> getRolesEntidadEntityCollection() {
        return rolesEntidadEntityCollection;
    }

    public void setRolesEntidadEntityCollection(Set<RolesEntidadEntity> rolesEntidadEntityCollection) {
        this.rolesEntidadEntityCollection = rolesEntidadEntityCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (entidadId != null ? entidadId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntidadExternaEntity)) {
            return false;
        }
        EntidadExternaEntity other = (EntidadExternaEntity) object;
        if ((this.entidadId == null && other.entidadId != null) || (this.entidadId != null && !this.entidadId.equals(other.entidadId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.EntidadExternaEntity[entidadId=" + entidadId + "]";
    }

}
