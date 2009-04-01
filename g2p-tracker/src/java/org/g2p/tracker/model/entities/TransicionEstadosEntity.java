/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.entities;

import java.io.Serializable;
import javax.persistence.Column;
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
@Table(name = "transicion_estados")
@NamedQueries({@NamedQuery(name = "TransicionEstadosEntity.findAll", query = "SELECT t FROM TransicionEstadosEntity t"), @NamedQuery(name = "TransicionEstadosEntity.findByEstadoOrigen", query = "SELECT t FROM TransicionEstadosEntity t WHERE t.transicionEstadosEntityPK.estadoOrigen = :estadoOrigen"), @NamedQuery(name = "TransicionEstadosEntity.findByAccion", query = "SELECT t FROM TransicionEstadosEntity t WHERE t.transicionEstadosEntityPK.accion = :accion"), @NamedQuery(name = "TransicionEstadosEntity.findByPromptAccion", query = "SELECT t FROM TransicionEstadosEntity t WHERE t.promptAccion = :promptAccion"), @NamedQuery(name = "TransicionEstadosEntity.findByValidador", query = "SELECT t FROM TransicionEstadosEntity t WHERE t.validador = :validador")})
public class TransicionEstadosEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TransicionEstadosEntityPK transicionEstadosEntityPK;
    @Column(name = "prompt_accion")
    private String promptAccion;
    @Column(name = "validador")
    private String validador;
    @JoinColumn(name = "accion", referencedColumnName = "accion", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private AccionesAppsEntity accionesAppsEntity;
    @JoinColumn(name = "estado_destino", referencedColumnName = "estado")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private EstadosEntity estadoDestino;
    @JoinColumn(name = "estado_origen", referencedColumnName = "estado", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private EstadosEntity estadosEntity;

    public TransicionEstadosEntity() {
    }

    public TransicionEstadosEntity(TransicionEstadosEntityPK transicionEstadosEntityPK) {
        this.transicionEstadosEntityPK = transicionEstadosEntityPK;
    }

    public TransicionEstadosEntity(String estadoOrigen, int accion) {
        this.transicionEstadosEntityPK = new TransicionEstadosEntityPK(estadoOrigen, accion);
    }

    public TransicionEstadosEntityPK getTransicionEstadosEntityPK() {
        return transicionEstadosEntityPK;
    }

    public void setTransicionEstadosEntityPK(TransicionEstadosEntityPK transicionEstadosEntityPK) {
        this.transicionEstadosEntityPK = transicionEstadosEntityPK;
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

    public AccionesAppsEntity getAccionesAppsEntity() {
        return accionesAppsEntity;
    }

    public void setAccionesAppsEntity(AccionesAppsEntity accionesAppsEntity) {
        this.accionesAppsEntity = accionesAppsEntity;
    }

    public EstadosEntity getEstadoDestino() {
        return estadoDestino;
    }

    public void setEstadoDestino(EstadosEntity estadoDestino) {
        this.estadoDestino = estadoDestino;
    }

    public EstadosEntity getEstadosEntity() {
        return estadosEntity;
    }

    public void setEstadosEntity(EstadosEntity estadosEntity) {
        this.estadosEntity = estadosEntity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transicionEstadosEntityPK != null ? transicionEstadosEntityPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransicionEstadosEntity)) {
            return false;
        }
        TransicionEstadosEntity other = (TransicionEstadosEntity) object;
        if ((this.transicionEstadosEntityPK == null && other.transicionEstadosEntityPK != null) || (this.transicionEstadosEntityPK != null && !this.transicionEstadosEntityPK.equals(other.transicionEstadosEntityPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.entities.TransicionEstadosEntity[transicionEstadosEntityPK=" + transicionEstadosEntityPK + "]";
    }

}
