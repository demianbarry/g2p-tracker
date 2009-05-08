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
 * @author nacho
 */
@Entity
@Table(name = "circuitos_estados")
@NamedQueries({@NamedQuery(name = "CircuitosEstados.findAll", query = "SELECT c FROM CircuitosEstados c")})
public class CircuitosEstadosEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "circuito_id")
    private Integer circuitoId;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "circuito")
    private String circuito;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "circuitoId", fetch = FetchType.LAZY)
    private Set<AuditaEstadosCircuitosEntity> auditaEstadosCircuitosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "circuitoId", fetch = FetchType.LAZY)
    private Set<EstadosEntity> estadosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "circuitoId", fetch = FetchType.LAZY)
    private Set<AplicaCircuitoEntity> aplicaCircuitoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "circuitoId", fetch = FetchType.LAZY)
    private Set<AccionesAppsEntity> accionesAppsCollection;

    public CircuitosEstadosEntity() {
    }

    public CircuitosEstadosEntity(Integer circuitoId) {
        this.circuitoId = circuitoId;
    }

    public CircuitosEstadosEntity(Integer circuitoId, String nombre) {
        this.circuitoId = circuitoId;
        this.nombre = nombre;
    }

    public Integer getCircuitoId() {
        return circuitoId;
    }

    public void setCircuitoId(Integer circuitoId) {
        this.circuitoId = circuitoId;
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

    public String getCircuito() {
        return circuito;
    }

    public void setCircuito(String circuito) {
        this.circuito = circuito;
    }

    public Set<AuditaEstadosCircuitosEntity> getAuditaEstadosCircuitosCollection() {
        return auditaEstadosCircuitosCollection;
    }

    public void setAuditaEstadosCircuitosCollection(Set<AuditaEstadosCircuitosEntity> auditaEstadosCircuitosCollection) {
        this.auditaEstadosCircuitosCollection = auditaEstadosCircuitosCollection;
    }

    public Set<EstadosEntity> getEstadosCollection() {
        return estadosCollection;
    }

    public void setEstadosCollection(Set<EstadosEntity> estadosCollection) {
        this.estadosCollection = estadosCollection;
    }

    public Set<AplicaCircuitoEntity> getAplicaCircuitoCollection() {
        return aplicaCircuitoCollection;
    }

    public void setAplicaCircuitoCollection(Set<AplicaCircuitoEntity> aplicaCircuitoCollection) {
        this.aplicaCircuitoCollection = aplicaCircuitoCollection;
    }

    public Set<AccionesAppsEntity> getAccionesAppsCollection() {
        return accionesAppsCollection;
    }

    public void setAccionesAppsCollection(Set<AccionesAppsEntity> accionesAppsCollection) {
        this.accionesAppsCollection = accionesAppsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (circuitoId != null ? circuitoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CircuitosEstadosEntity)) {
            return false;
        }
        CircuitosEstadosEntity other = (CircuitosEstadosEntity) object;
        if ((this.circuitoId == null && other.circuitoId != null) || (this.circuitoId != null && !this.circuitoId.equals(other.circuitoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.CircuitosEstados[circuitoId=" + circuitoId + "]";
    }

    @Override
    public Object getPK() {
        return circuitoId;
    }

}
