/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
@Table(name = "transicion_estados")
@NamedQueries({@NamedQuery(name = "TransicionEstadosEntity.findAll", query = "SELECT t FROM TransicionEstadosEntity t")})
public class TransicionEstadosEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "transicion_id")
    private Integer transicionId;
    @Column(name = "prompt_accion")
    private String promptAccion;
    @Column(name = "validador")
    private String validador;
    @Column(name = "accion")
    private Integer accion;
    @Column(name = "estado_origen")
    private String estadoOrigen;
    @Column(name = "estado_destino")
    private String estadoDestino;
    @JoinColumn(name = "accion_id", referencedColumnName = "accion_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private AccionesAppsEntity accionId;
    @JoinColumn(name = "estado_id_destino", referencedColumnName = "estado_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private EstadosEntity estadoIdDestino;
    @JoinColumn(name = "estado_id_origen", referencedColumnName = "estado_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private EstadosEntity estadoIdOrigen;

    public TransicionEstadosEntity() {
    }

    public TransicionEstadosEntity(Integer transicionId) {
        this.transicionId = transicionId;
    }

    public Integer getTransicionId() {
        return transicionId;
    }

    public void setTransicionId(Integer transicionId) {
        this.transicionId = transicionId;
    }

    public String getPromptAccion() {
        return promptAccion;
    }

    public void setPromptAccion(String promptAccion) {
        this.promptAccion = promptAccion;
    }

    public String getValidador() {
        return validador;
    }

    public void setValidador(String validador) {
        this.validador = validador;
    }

    public Integer getAccion() {
        return accion;
    }

    public void setAccion(Integer accion) {
        this.accion = accion;
    }

    public String getEstadoOrigen() {
        return estadoOrigen;
    }

    public void setEstadoOrigen(String estadoOrigen) {
        this.estadoOrigen = estadoOrigen;
    }

    public String getEstadoDestino() {
        return estadoDestino;
    }

    public void setEstadoDestino(String estadoDestino) {
        this.estadoDestino = estadoDestino;
    }

    public AccionesAppsEntity getAccionId() {
        return accionId;
    }

    public void setAccionId(AccionesAppsEntity accionId) {
        this.accionId = accionId;
    }

    public EstadosEntity getEstadoIdDestino() {
        return estadoIdDestino;
    }

    public void setEstadoIdDestino(EstadosEntity estadoIdDestino) {
        this.estadoIdDestino = estadoIdDestino;
    }

    public EstadosEntity getEstadoIdOrigen() {
        return estadoIdOrigen;
    }

    public void setEstadoIdOrigen(EstadosEntity estadoIdOrigen) {
        this.estadoIdOrigen = estadoIdOrigen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transicionId != null ? transicionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransicionEstadosEntity)) {
            return false;
        }
        TransicionEstadosEntity other = (TransicionEstadosEntity) object;
        if ((this.transicionId == null && other.transicionId != null) || (this.transicionId != null && !this.transicionId.equals(other.transicionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.TransicionEstados[transicionId=" + transicionId + "]";
    }

    @Override
    public Object getPK() {
        return transicionId;
    }

}
