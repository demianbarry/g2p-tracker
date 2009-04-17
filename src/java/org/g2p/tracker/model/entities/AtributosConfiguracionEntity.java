/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.entities;

import java.io.Serializable;
import javax.persistence.Basic;
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
@Table(name = "atributos_configuracion")
@NamedQueries({@NamedQuery(name = "AtributosConfiguracionEntity.findAll", query = "SELECT a FROM AtributosConfiguracionEntity a"), @NamedQuery(name = "AtributosConfiguracionEntity.findByConfiguracionId", query = "SELECT a FROM AtributosConfiguracionEntity a WHERE a.atributosConfiguracionEntityPK.configuracionId = :configuracionId"), @NamedQuery(name = "AtributosConfiguracionEntity.findByAtributoId", query = "SELECT a FROM AtributosConfiguracionEntity a WHERE a.atributosConfiguracionEntityPK.atributoId = :atributoId"), @NamedQuery(name = "AtributosConfiguracionEntity.findByValor", query = "SELECT a FROM AtributosConfiguracionEntity a WHERE a.valor = :valor"), @NamedQuery(name = "AtributosConfiguracionEntity.findByValorHasta", query = "SELECT a FROM AtributosConfiguracionEntity a WHERE a.valorHasta = :valorHasta")})
public class AtributosConfiguracionEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AtributosConfiguracionEntityPK atributosConfiguracionEntityPK;
    @Basic(optional = false)
    @Column(name = "valor")
    private String valor;
    @Basic(optional = false)
    @Column(name = "valor_hasta")
    private String valorHasta;
    @JoinColumn(name = "configuracion_id", referencedColumnName = "configuracion_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ConfiguracionEntity configuracionEntity;
    @JoinColumn(name = "atributo_id", referencedColumnName = "atributo_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private AtributosRolEntity atributosRolEntity;

    public AtributosConfiguracionEntity() {
    }

    public AtributosConfiguracionEntity(AtributosConfiguracionEntityPK atributosConfiguracionEntityPK) {
        this.atributosConfiguracionEntityPK = atributosConfiguracionEntityPK;
    }

    public AtributosConfiguracionEntity(AtributosConfiguracionEntityPK atributosConfiguracionEntityPK, String valor, String valorHasta) {
        this.atributosConfiguracionEntityPK = atributosConfiguracionEntityPK;
        this.valor = valor;
        this.valorHasta = valorHasta;
    }

    public AtributosConfiguracionEntity(int configuracionId, int atributoId) {
        this.atributosConfiguracionEntityPK = new AtributosConfiguracionEntityPK(configuracionId, atributoId);
    }

    public AtributosConfiguracionEntityPK getAtributosConfiguracionEntityPK() {
        return atributosConfiguracionEntityPK;
    }

    public void setAtributosConfiguracionEntityPK(AtributosConfiguracionEntityPK atributosConfiguracionEntityPK) {
        this.atributosConfiguracionEntityPK = atributosConfiguracionEntityPK;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getValorHasta() {
        return valorHasta;
    }

    public void setValorHasta(String valorHasta) {
        this.valorHasta = valorHasta;
    }

    public ConfiguracionEntity getConfiguracionEntity() {
        return configuracionEntity;
    }

    public void setConfiguracionEntity(ConfiguracionEntity configuracionEntity) {
        this.configuracionEntity = configuracionEntity;
    }

    public AtributosRolEntity getAtributosRolEntity() {
        return atributosRolEntity;
    }

    public void setAtributosRolEntity(AtributosRolEntity atributosRolEntity) {
        this.atributosRolEntity = atributosRolEntity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (atributosConfiguracionEntityPK != null ? atributosConfiguracionEntityPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AtributosConfiguracionEntity)) {
            return false;
        }
        AtributosConfiguracionEntity other = (AtributosConfiguracionEntity) object;
        if ((this.atributosConfiguracionEntityPK == null && other.atributosConfiguracionEntityPK != null) || (this.atributosConfiguracionEntityPK != null && !this.atributosConfiguracionEntityPK.equals(other.atributosConfiguracionEntityPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.entities.AtributosConfiguracionEntity[atributosConfiguracionEntityPK=" + atributosConfiguracionEntityPK + "]";
    }

}
