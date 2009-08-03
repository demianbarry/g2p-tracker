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
@NamedQueries({
    @NamedQuery(name = "EstadosEntity.findAll", query = "SELECT e FROM EstadosEntity e"),
    @NamedQuery(name = "EstadosEntity.findByCircuitoId", query = "SELECT u FROM EstadosEntity u WHERE u.circuitoId.circuitoId = :circuitoId")
})

public class EstadosEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "circuito")
    private String circuito;
    @Column(name = "estado")
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoIdDe", fetch = FetchType.LAZY)
    private Set<AuditaEstadosCircuitosEntity> auditaEstadosCircuitosEntityCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoIdA", fetch = FetchType.LAZY)
    private Set<AuditaEstadosCircuitosEntity> auditaEstadosCircuitosEntityCollection1;
    @JoinColumn(name = "circuito_id", referencedColumnName = "circuito_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CircuitosEstadosEntity circuitoId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoIdOrigen", fetch = FetchType.LAZY)
    private Set<TransicionEstadosEntity> transicionEstadosEntityCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoIdDestino", fetch = FetchType.LAZY)
    private Set<TransicionEstadosEntity> transicionEstadosEntityCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoId", fetch = FetchType.LAZY)
    private Set<TracksEntity> tracksEntityCollection;

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

    public String getCircuito() {
        return circuito;
    }

    public void setCircuito(String circuito) {
        this.circuito = circuito;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public CircuitosEstadosEntity getCircuitoId() {
        return circuitoId;
    }

    public void setCircuitoId(CircuitosEstadosEntity circuitoId) {
        this.circuitoId = circuitoId;
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

    public Set<TracksEntity> getTracksEntityCollection() {
        return tracksEntityCollection;
    }

    public void setTracksEntityCollection(Set<TracksEntity> tracksEntityCollection) {
        this.tracksEntityCollection = tracksEntityCollection;
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
        return "org.g2p.tracker.model.entities.EstadosEntity[estadoId=" + estadoId + "]";
    }

    @Override
    public Object getPK() {
        return getEstadoId();
    }

}
