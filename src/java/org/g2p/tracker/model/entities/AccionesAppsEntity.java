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
@Table(name = "acciones_apps")
@NamedQueries({@NamedQuery(name = "AccionesAppsEntity.findAll", query = "SELECT a FROM AccionesAppsEntity a")})
public class AccionesAppsEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "accion")
    private Integer accion;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accion", fetch = FetchType.EAGER)
    private Set<AuditaEstadosCircuitosEntity> auditaEstadosCircuitosEntityCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accionesAppsEntity", fetch = FetchType.EAGER)
    private Set<TransicionEstadosEntity> transicionEstadosEntityCollection;
    @JoinColumn(name = "circuito", referencedColumnName = "circuito")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private CircuitosEstadosEntity circuito;

    public AccionesAppsEntity() {
    }

    public AccionesAppsEntity(Integer accion) {
        this.accion = accion;
    }

    public AccionesAppsEntity(Integer accion, String nombre, char manual) {
        this.accion = accion;
        this.nombre = nombre;
        this.manual = manual;
    }

    public Integer getAccion() {
        return accion;
    }

    public void setAccion(Integer accion) {
        this.accion = accion;
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

    public Set<AuditaEstadosCircuitosEntity> getAuditaEstadosCircuitosEntityCollection() {
        return auditaEstadosCircuitosEntityCollection;
    }

    public void setAuditaEstadosCircuitosEntityCollection(Set<AuditaEstadosCircuitosEntity> auditaEstadosCircuitosEntityCollection) {
        this.auditaEstadosCircuitosEntityCollection = auditaEstadosCircuitosEntityCollection;
    }

    public Set<TransicionEstadosEntity> getTransicionEstadosEntityCollection() {
        return transicionEstadosEntityCollection;
    }

    public void setTransicionEstadosEntityCollection(Set<TransicionEstadosEntity> transicionEstadosEntityCollection) {
        this.transicionEstadosEntityCollection = transicionEstadosEntityCollection;
    }

    public CircuitosEstadosEntity getCircuito() {
        return circuito;
    }

    public void setCircuito(CircuitosEstadosEntity circuito) {
        this.circuito = circuito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accion != null ? accion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccionesAppsEntity)) {
            return false;
        }
        AccionesAppsEntity other = (AccionesAppsEntity) object;
        if ((this.accion == null && other.accion != null) || (this.accion != null && !this.accion.equals(other.accion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.AccionesAppsEntity[accion=" + accion + "]";
    }

}
