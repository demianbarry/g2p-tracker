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
 * @author nacho
 */
@Entity
@Table(name = "entidad_externa")
@NamedQueries({@NamedQuery(name = "EntidadExterna.findAll", query = "SELECT e FROM EntidadExterna e")})
public class EntidadExternaEntity extends BaseEntity implements Serializable {
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
    @OneToMany(mappedBy = "entidadId", fetch = FetchType.LAZY)
    private Set<AtributosEntidadEntity> atributosEntidadCollection;
    @OneToMany(mappedBy = "entidadId1", fetch = FetchType.LAZY)
    private Set<AtributosEntidadEntity> atributosEntidadCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entidadExterna", fetch = FetchType.LAZY)
    private Set<RolesEntidadEntity> rolesEntidadCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entidadExterna1", fetch = FetchType.LAZY)
    private Set<RolesEntidadEntity> rolesEntidadCollection1;

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

    public Set<AtributosEntidadEntity> getAtributosEntidadCollection() {
        return atributosEntidadCollection;
    }

    public void setAtributosEntidadCollection(Set<AtributosEntidadEntity> atributosEntidadCollection) {
        this.atributosEntidadCollection = atributosEntidadCollection;
    }

    public Set<AtributosEntidadEntity> getAtributosEntidadCollection1() {
        return atributosEntidadCollection1;
    }

    public void setAtributosEntidadCollection1(Set<AtributosEntidadEntity> atributosEntidadCollection1) {
        this.atributosEntidadCollection1 = atributosEntidadCollection1;
    }

    public Set<RolesEntidadEntity> getRolesEntidadCollection() {
        return rolesEntidadCollection;
    }

    public void setRolesEntidadCollection(Set<RolesEntidadEntity> rolesEntidadCollection) {
        this.rolesEntidadCollection = rolesEntidadCollection;
    }

    public Set<RolesEntidadEntity> getRolesEntidadCollection1() {
        return rolesEntidadCollection1;
    }

    public void setRolesEntidadCollection1(Set<RolesEntidadEntity> rolesEntidadCollection1) {
        this.rolesEntidadCollection1 = rolesEntidadCollection1;
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
        return "org.g2p.tracker.model.entities.EntidadExterna[entidadId=" + entidadId + "]";
    }

    @Override
    public Object getPK() {
        return entidadId;
    }

}
