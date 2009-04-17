/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "aplica_circuito")
@NamedQueries({@NamedQuery(name = "AplicaCircuitoEntity.findAll", query = "SELECT a FROM AplicaCircuitoEntity a"), @NamedQuery(name = "AplicaCircuitoEntity.findByTipoObjeto", query = "SELECT a FROM AplicaCircuitoEntity a WHERE a.aplicaCircuitoEntityPK.tipoObjeto = :tipoObjeto"), @NamedQuery(name = "AplicaCircuitoEntity.findByNombreObjeto", query = "SELECT a FROM AplicaCircuitoEntity a WHERE a.aplicaCircuitoEntityPK.nombreObjeto = :nombreObjeto"), @NamedQuery(name = "AplicaCircuitoEntity.findByTipoDetalle", query = "SELECT a FROM AplicaCircuitoEntity a WHERE a.aplicaCircuitoEntityPK.tipoDetalle = :tipoDetalle"), @NamedQuery(name = "AplicaCircuitoEntity.findByNombreDetalle", query = "SELECT a FROM AplicaCircuitoEntity a WHERE a.aplicaCircuitoEntityPK.nombreDetalle = :nombreDetalle")})
public class AplicaCircuitoEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AplicaCircuitoEntityPK aplicaCircuitoEntityPK;
    @JoinColumn(name = "circuito", referencedColumnName = "circuito")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private CircuitosEstadosEntity circuito;

    public AplicaCircuitoEntity() {
    }

    public AplicaCircuitoEntity(AplicaCircuitoEntityPK aplicaCircuitoEntityPK) {
        this.aplicaCircuitoEntityPK = aplicaCircuitoEntityPK;
    }

    public AplicaCircuitoEntity(String tipoObjeto, String nombreObjeto, String tipoDetalle, String nombreDetalle) {
        this.aplicaCircuitoEntityPK = new AplicaCircuitoEntityPK(tipoObjeto, nombreObjeto, tipoDetalle, nombreDetalle);
    }

    public AplicaCircuitoEntityPK getAplicaCircuitoEntityPK() {
        return aplicaCircuitoEntityPK;
    }

    public void setAplicaCircuitoEntityPK(AplicaCircuitoEntityPK aplicaCircuitoEntityPK) {
        this.aplicaCircuitoEntityPK = aplicaCircuitoEntityPK;
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
        hash += (aplicaCircuitoEntityPK != null ? aplicaCircuitoEntityPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AplicaCircuitoEntity)) {
            return false;
        }
        AplicaCircuitoEntity other = (AplicaCircuitoEntity) object;
        if ((this.aplicaCircuitoEntityPK == null && other.aplicaCircuitoEntityPK != null) || (this.aplicaCircuitoEntityPK != null && !this.aplicaCircuitoEntityPK.equals(other.aplicaCircuitoEntityPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.entities.AplicaCircuitoEntity[aplicaCircuitoEntityPK=" + aplicaCircuitoEntityPK + "]";
    }

}
