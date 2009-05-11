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
 * @author nacho
 */
@Entity
@Table(name = "estados")
@NamedQueries({@NamedQuery(name = "EstadosEntity.findAll", query = "SELECT e FROM EstadosEntity e")})
public class EstadosEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "estado_id")
    private Integer estadoId;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "estado")
    private String estado;
    @Column(name = "circuito")
    private String circuito;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoIdA", fetch = FetchType.EAGER)
    private Set<AuditaEstadosCircuitosEntity> auditaEstadosCircuitosCollection;
    @JoinColumn(name = "circuito_id", referencedColumnName = "circuito_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private CircuitosEstadosEntity circuitoId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoIdDestino", fetch = FetchType.EAGER)
    private Set<TransicionEstadosEntity> transicionEstadosCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoId", fetch = FetchType.EAGER)
    private Set<TracksEntity> tracksCollection;

    public EstadosEntity() {
    }

    public EstadosEntity(Integer estadoId) {
        this.estadoId = estadoId;
    }

    public EstadosEntity(Integer estadoId, String nombre) {
        this.estadoId = estadoId;
        this.nombre = nombre;
    }

    public Integer getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Integer estadoId) {
        this.estadoId = estadoId;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public CircuitosEstadosEntity getCircuitoId() {
        return circuitoId;
    }

    public void setCircuitoId(CircuitosEstadosEntity circuitoId) {
        this.circuitoId = circuitoId;
    }

    public Set<TransicionEstadosEntity> getTransicionEstadosCollection() {
        return transicionEstadosCollection;
    }

    public void setTransicionEstadosCollection(Set<TransicionEstadosEntity> transicionEstadosCollection) {
        this.transicionEstadosCollection = transicionEstadosCollection;
    }

    public Set<TracksEntity> getTracksCollection() {
        return tracksCollection;
    }

    public void setTracksCollection(Set<TracksEntity> tracksCollection) {
        this.tracksCollection = tracksCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estadoId != null ? estadoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadosEntity)) {
            return false;
        }
        EstadosEntity other = (EstadosEntity) object;
        if ((this.estadoId == null && other.estadoId != null) || (this.estadoId != null && !this.estadoId.equals(other.estadoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.Estados[estadoId=" + estadoId + "]";
    }

    @Override
    public Object getPK() {
        return estadoId;
    }

}
