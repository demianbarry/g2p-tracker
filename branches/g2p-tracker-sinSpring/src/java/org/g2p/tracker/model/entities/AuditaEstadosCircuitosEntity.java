/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "audita_estados_circuitos")
@NamedQueries({@NamedQuery(name = "AuditaEstadosCircuitosEntity.findAll", query = "SELECT a FROM AuditaEstadosCircuitosEntity a")})
public class AuditaEstadosCircuitosEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "audita_id")
    private Integer auditaId;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "nombre_tabla")
    private String nombreTabla;
    @Basic(optional = false)
    @Column(name = "registro_id")
    private int registroId;
    @Column(name = "host")
    private String host;
    @Lob
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "circuito", referencedColumnName = "circuito")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private CircuitosEstadosEntity circuito;
    @JoinColumn(name = "de_estado", referencedColumnName = "estado")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private EstadosEntity deEstado;
    @JoinColumn(name = "a_estado", referencedColumnName = "estado")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private EstadosEntity aEstado;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private WebsiteUserEntity userId;
    @JoinColumn(name = "accion", referencedColumnName = "accion")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private AccionesAppsEntity accion;

    public AuditaEstadosCircuitosEntity() {
    }

    public AuditaEstadosCircuitosEntity(Integer auditaId) {
        this.auditaId = auditaId;
    }

    public AuditaEstadosCircuitosEntity(Integer auditaId, Date fecha, String nombreTabla, int registroId) {
        this.auditaId = auditaId;
        this.fecha = fecha;
        this.nombreTabla = nombreTabla;
        this.registroId = registroId;
    }

    public Integer getAuditaId() {
        return auditaId;
    }

    public void setAuditaId(Integer auditaId) {
        this.auditaId = auditaId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombreTabla() {
        return nombreTabla;
    }

    public void setNombreTabla(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }

    public int getRegistroId() {
        return registroId;
    }

    public void setRegistroId(int registroId) {
        this.registroId = registroId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public CircuitosEstadosEntity getCircuito() {
        return circuito;
    }

    public void setCircuito(CircuitosEstadosEntity circuito) {
        this.circuito = circuito;
    }

    public EstadosEntity getDeEstado() {
        return deEstado;
    }

    public void setDeEstado(EstadosEntity deEstado) {
        this.deEstado = deEstado;
    }

    public EstadosEntity getAEstado() {
        return aEstado;
    }

    public void setAEstado(EstadosEntity aEstado) {
        this.aEstado = aEstado;
    }

    public WebsiteUserEntity getUserId() {
        return userId;
    }

    public void setUserId(WebsiteUserEntity userId) {
        this.userId = userId;
    }

    public AccionesAppsEntity getAccion() {
        return accion;
    }

    public void setAccion(AccionesAppsEntity accion) {
        this.accion = accion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (auditaId != null ? auditaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuditaEstadosCircuitosEntity)) {
            return false;
        }
        AuditaEstadosCircuitosEntity other = (AuditaEstadosCircuitosEntity) object;
        if ((this.auditaId == null && other.auditaId != null) || (this.auditaId != null && !this.auditaId.equals(other.auditaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.AuditaEstadosCircuitosEntity[auditaId=" + auditaId + "]";
    }

}
