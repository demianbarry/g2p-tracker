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
@Table(name = "acciones_apps")
@NamedQueries({
    @NamedQuery(name = "AccionesAppsEntity.findAll", query = "SELECT a FROM AccionesAppsEntity a"),
    @NamedQuery(name = "AccionesAppsEntity.findByCircuitoId", query = "SELECT u FROM AccionesAppsEntity u WHERE u.circuitoId.circuitoId = :circuitoId")
})
public class AccionesAppsEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "accion_id")
    private Integer accionId;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "observaciones")
    private String observaciones;
    @Basic(optional = false)
    @Column(name = "manual")
    private char manual;
    @Column(name = "accion")
    private Integer accion;
    @Column(name = "circuito")
    private String circuito;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accionId", fetch = FetchType.LAZY)
    private Set<AuditaEstadosCircuitosEntity> auditaEstadosCircuitosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accionId", fetch = FetchType.LAZY)
    private Set<TransicionEstadosEntity> transicionEstadosCollection;
    @JoinColumn(name = "circuito_id", referencedColumnName = "circuito_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CircuitosEstadosEntity circuitoId;

    public AccionesAppsEntity() {
    }

    public AccionesAppsEntity(Integer accionId) {
        this.accionId = accionId;
    }

    public AccionesAppsEntity(Integer accionId, String nombre, char manual) {
        this.accionId = accionId;
        this.nombre = nombre;
        this.manual = manual;
    }

    public Integer getAccionId() {
        return accionId;
    }

    public void setAccionId(Integer accionId) {
        this.accionId = accionId;
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

    public char getManual() {
        return manual;
    }

    public void setManual(char manual) {
        this.manual = manual;
    }

    public Integer getAccion() {
        return accion;
    }

    public void setAccion(Integer accion) {
        this.accion = accion;
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

    public Set<TransicionEstadosEntity> getTransicionEstadosCollection() {
        return transicionEstadosCollection;
    }

    public void setTransicionEstadosCollection(Set<TransicionEstadosEntity> transicionEstadosCollection) {
        this.transicionEstadosCollection = transicionEstadosCollection;
    }

    public CircuitosEstadosEntity getCircuitoId() {
        return circuitoId;
    }

    public void setCircuitoId(CircuitosEstadosEntity circuitoId) {
        this.circuitoId = circuitoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accionId != null ? accionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccionesAppsEntity)) {
            return false;
        }
        AccionesAppsEntity other = (AccionesAppsEntity) object;
        if ((this.accionId == null && other.accionId != null) || (this.accionId != null && !this.accionId.equals(other.accionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.AccionesApps[accionId=" + accionId + "]";
    }

    @Override
    public Object getPK() {
        return accionId;
    }

}
