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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "circuitos_estados")
@NamedQueries({@NamedQuery(name = "CircuitosEstadosEntity.findAll", query = "SELECT c FROM CircuitosEstadosEntity c"), @NamedQuery(name = "CircuitosEstadosEntity.findByCircuito", query = "SELECT c FROM CircuitosEstadosEntity c WHERE c.circuito = :circuito"), @NamedQuery(name = "CircuitosEstadosEntity.findByNombre", query = "SELECT c FROM CircuitosEstadosEntity c WHERE c.nombre = :nombre"), @NamedQuery(name = "CircuitosEstadosEntity.findByDescripcion", query = "SELECT c FROM CircuitosEstadosEntity c WHERE c.descripcion = :descripcion"), @NamedQuery(name = "CircuitosEstadosEntity.findByObservaciones", query = "SELECT c FROM CircuitosEstadosEntity c WHERE c.observaciones = :observaciones")})
public class CircuitosEstadosEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "circuito")
    private String circuito;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "observaciones")
    private String observaciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "circuito", fetch = FetchType.EAGER)
    private Set<AuditaEstadosCircuitosEntity> auditaEstadosCircuitosEntityCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "circuito", fetch = FetchType.EAGER)
    private Set<EstadosEntity> estadosEntityCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "circuito", fetch = FetchType.EAGER)
    private Set<AplicaCircuitoEntity> aplicaCircuitoEntityCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "circuito", fetch = FetchType.EAGER)
    private Set<AccionesAppsEntity> accionesAppsEntityCollection;

    public CircuitosEstadosEntity() {
    }

    public CircuitosEstadosEntity(String circuito) {
        this.circuito = circuito;
    }

    public CircuitosEstadosEntity(String circuito, String nombre) {
        this.circuito = circuito;
        this.nombre = nombre;
    }

    public String getCircuito() {
        return circuito;
    }

    public void setCircuito(String circuito) {
        this.circuito = circuito;
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

    public Set<EstadosEntity> getEstadosEntityCollection() {
        return estadosEntityCollection;
    }

    public void setEstadosEntityCollection(Set<EstadosEntity> estadosEntityCollection) {
        this.estadosEntityCollection = estadosEntityCollection;
    }

    public Set<AplicaCircuitoEntity> getAplicaCircuitoEntityCollection() {
        return aplicaCircuitoEntityCollection;
    }

    public void setAplicaCircuitoEntityCollection(Set<AplicaCircuitoEntity> aplicaCircuitoEntityCollection) {
        this.aplicaCircuitoEntityCollection = aplicaCircuitoEntityCollection;
    }

    public Set<AccionesAppsEntity> getAccionesAppsEntityCollection() {
        return accionesAppsEntityCollection;
    }

    public void setAccionesAppsEntityCollection(Set<AccionesAppsEntity> accionesAppsEntityCollection) {
        this.accionesAppsEntityCollection = accionesAppsEntityCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (circuito != null ? circuito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CircuitosEstadosEntity)) {
            return false;
        }
        CircuitosEstadosEntity other = (CircuitosEstadosEntity) object;
        if ((this.circuito == null && other.circuito != null) || (this.circuito != null && !this.circuito.equals(other.circuito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.entities.CircuitosEstadosEntity[circuito=" + circuito + "]";
    }

}
