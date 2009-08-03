package org.g2p.tracker.model.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author nacho
 */
@Entity
@Table(name = "aplica_circuito")
@NamedQueries({@NamedQuery(name = "AplicaCircuitoEntity.findAll", query = "SELECT a FROM AplicaCircuitoEntity a")})
public class AplicaCircuitoEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "aplica_circuito_id")
    private Integer aplicaCircuitoId;
    @Basic(optional = false)
    @Column(name = "tipo_objeto")
    private String tipoObjeto;
    @Basic(optional = false)
    @Column(name = "nombre_objeto")
    private String nombreObjeto;
    @Basic(optional = false)
    @Column(name = "tipo_detalle")
    private String tipoDetalle;
    @Basic(optional = false)
    @Column(name = "nombre_detalle")
    private String nombreDetalle;
    @Column(name = "circuito")
    private String circuito;
    @JoinColumn(name = "circuito_id", referencedColumnName = "circuito_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CircuitosEstadosEntity circuitoId;

    public AplicaCircuitoEntity() {
    }

    public AplicaCircuitoEntity(Integer aplicaCircuitoId) {
        this.aplicaCircuitoId = aplicaCircuitoId;
    }

    public AplicaCircuitoEntity(Integer aplicaCircuitoId, String tipoObjeto, String nombreObjeto, String tipoDetalle, String nombreDetalle) {
        this.aplicaCircuitoId = aplicaCircuitoId;
        this.tipoObjeto = tipoObjeto;
        this.nombreObjeto = nombreObjeto;
        this.tipoDetalle = tipoDetalle;
        this.nombreDetalle = nombreDetalle;
    }

    public Integer getAplicaCircuitoId() {
        return aplicaCircuitoId;
    }

    public void setAplicaCircuitoId(Integer aplicaCircuitoId) {
        this.aplicaCircuitoId = aplicaCircuitoId;
    }

    public String getTipoObjeto() {
        return tipoObjeto;
    }

    public void setTipoObjeto(String tipoObjeto) {
        this.tipoObjeto = tipoObjeto;
    }

    public String getNombreObjeto() {
        return nombreObjeto;
    }

    public void setNombreObjeto(String nombreObjeto) {
        this.nombreObjeto = nombreObjeto;
    }

    public String getTipoDetalle() {
        return tipoDetalle;
    }

    public void setTipoDetalle(String tipoDetalle) {
        this.tipoDetalle = tipoDetalle;
    }

    public String getNombreDetalle() {
        return nombreDetalle;
    }

    public void setNombreDetalle(String nombreDetalle) {
        this.nombreDetalle = nombreDetalle;
    }

    public String getCircuito() {
        return circuito;
    }

    public void setCircuito(String circuito) {
        this.circuito = circuito;
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
        hash += (aplicaCircuitoId != null ? aplicaCircuitoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AplicaCircuitoEntity)) {
            return false;
        }
        AplicaCircuitoEntity other = (AplicaCircuitoEntity) object;
        if ((this.aplicaCircuitoId == null && other.aplicaCircuitoId != null) || (this.aplicaCircuitoId != null && !this.aplicaCircuitoId.equals(other.aplicaCircuitoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.AplicaCircuito[aplicaCircuitoId=" + aplicaCircuitoId + "]";
    }

    @Override
    public Object getPK() {
        return aplicaCircuitoId;
    }

}
