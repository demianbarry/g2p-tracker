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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "estados")
@NamedQueries({@NamedQuery(name = "EstadosEntity.findAll", query = "SELECT e FROM EstadosEntity e"), @NamedQuery(name = "EstadosEntity.findByEstado", query = "SELECT e FROM EstadosEntity e WHERE e.estado = :estado"), @NamedQuery(name = "EstadosEntity.findByNombre", query = "SELECT e FROM EstadosEntity e WHERE e.nombre = :nombre"), @NamedQuery(name = "EstadosEntity.findByDescripcion", query = "SELECT e FROM EstadosEntity e WHERE e.descripcion = :descripcion"), @NamedQuery(name = "EstadosEntity.findByObservaciones", query = "SELECT e FROM EstadosEntity e WHERE e.observaciones = :observaciones")})
public class EstadosEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "observaciones")
    private String observaciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deEstado", fetch = FetchType.EAGER)
    private Set<AuditaEstadosCircuitosEntity> auditaEstadosCircuitosEntityCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aEstado", fetch = FetchType.EAGER)
    private Set<AuditaEstadosCircuitosEntity> auditaEstadosCircuitosEntityCollection1;
    @JoinColumn(name = "circuito", referencedColumnName = "circuito")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private CircuitosEstadosEntity circuito;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoDestino", fetch = FetchType.EAGER)
    private Set<TransicionEstadosEntity> transicionEstadosEntityCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadosEntity", fetch = FetchType.EAGER)
    private Set<TransicionEstadosEntity> transicionEstadosEntityCollection1;

    public EstadosEntity() {
    }

    public EstadosEntity(String estado) {
        this.estado = estado;
    }

    public EstadosEntity(String estado, String nombre) {
        this.estado = estado;
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public Set<AuditaEstadosCircuitosEntity> getAuditaEstadosCircuitosEntityCollection() {
        return auditaEstadosCircuitosEntityCollection;
    }

    public void setAuditaEstadosCircuitosEntityCollection(Set<AuditaEstadosCircuitosEntity> auditaEstadosCircuitosEntityCollection) {
        this.auditaEstadosCircuitosEntityCollection = auditaEstadosCircuitosEntityCollection;
    }

    public Set<AuditaEstadosCircuitosEntity> getAuditaEstadosCircuitosEntityCollection1() {
        return auditaEstadosCircuitosEntityCollection1;
    }

    public void setAuditaEstadosCircuitosEntityCollection1(Set<AuditaEstadosCircuitosEntity> auditaEstadosCircuitosEntityCollection1) {
        this.auditaEstadosCircuitosEntityCollection1 = auditaEstadosCircuitosEntityCollection1;
    }

    public CircuitosEstadosEntity getCircuito() {
        return circuito;
    }

    public void setCircuito(CircuitosEstadosEntity circuito) {
        this.circuito = circuito;
    }

    public Set<TransicionEstadosEntity> getTransicionEstadosEntityCollection() {
        return transicionEstadosEntityCollection;
    }

    public void setTransicionEstadosEntityCollection(Set<TransicionEstadosEntity> transicionEstadosEntityCollection) {
        this.transicionEstadosEntityCollection = transicionEstadosEntityCollection;
    }

    public Set<TransicionEstadosEntity> getTransicionEstadosEntityCollection1() {
        return transicionEstadosEntityCollection1;
    }

    public void setTransicionEstadosEntityCollection1(Set<TransicionEstadosEntity> transicionEstadosEntityCollection1) {
        this.transicionEstadosEntityCollection1 = transicionEstadosEntityCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estado != null ? estado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadosEntity)) {
            return false;
        }
        EstadosEntity other = (EstadosEntity) object;
        if ((this.estado == null && other.estado != null) || (this.estado != null && !this.estado.equals(other.estado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.entities.EstadosEntity[estado=" + estado + "]";
    }

}
