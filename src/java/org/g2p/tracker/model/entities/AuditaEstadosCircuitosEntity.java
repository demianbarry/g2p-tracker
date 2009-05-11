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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author nacho
 */
@Entity
@Table(name = "audita_estados_circuitos")
@NamedQueries({@NamedQuery(name = "AuditaEstadosCircuitosEntity.findAll", query = "SELECT a FROM AuditaEstadosCircuitosEntity a")})
public class AuditaEstadosCircuitosEntity extends BaseEntity implements Serializable {
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
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "a_estado")
    private String aEstado;
    @Column(name = "accion")
    private Integer accion;
    @Column(name = "circuito")
    private String circuito;
    @Column(name = "de_estado")
    private String deEstado;
    @JoinColumn(name = "accion_id", referencedColumnName = "accion_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private AccionesAppsEntity accionId;
    @JoinColumn(name = "circuito_id", referencedColumnName = "circuito_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private CircuitosEstadosEntity circuitoId;
    @JoinColumn(name = "estado_id_a", referencedColumnName = "estado_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private EstadosEntity estadoIdA;
    @JoinColumn(name = "estado_id_de", referencedColumnName = "estado_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private EstadosEntity estadoIdDe;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private WebsiteUsersEntity userId;

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

    public String getAEstado() {
        return aEstado;
    }

    public void setAEstado(String aEstado) {
        this.aEstado = aEstado;
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

    public String getDeEstado() {
        return deEstado;
    }

    public void setDeEstado(String deEstado) {
        this.deEstado = deEstado;
    }

    public AccionesAppsEntity getAccionId() {
        return accionId;
    }

    public void setAccionId(AccionesAppsEntity accionId) {
        this.accionId = accionId;
    }

    public CircuitosEstadosEntity getCircuitoId() {
        return circuitoId;
    }

    public void setCircuitoId(CircuitosEstadosEntity circuitoId) {
        this.circuitoId = circuitoId;
    }

    public EstadosEntity getEstadoIdA() {
        return estadoIdA;
    }

    public void setEstadoIdA(EstadosEntity estadoIdA) {
        this.estadoIdA = estadoIdA;
    }

    public EstadosEntity getEstadoIdDe() {
        return estadoIdDe;
    }

    public void setEstadoIdDe(EstadosEntity estadoIdDe) {
        this.estadoIdDe = estadoIdDe;
    }

    public WebsiteUsersEntity getUserId() {
        return userId;
    }

    public void setUserId(WebsiteUsersEntity userId) {
        this.userId = userId;
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
        return "org.g2p.tracker.model.entities.AuditaEstadosCircuitos[auditaId=" + auditaId + "]";
    }

    @Override
    public Object getPK() {
        return auditaId;
    }

}
