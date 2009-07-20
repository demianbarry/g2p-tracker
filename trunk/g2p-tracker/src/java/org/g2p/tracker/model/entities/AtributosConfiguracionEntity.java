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
 * @author nacho
 */
@Entity
@Table(name = "atributos_configuracion")
@NamedQueries({@NamedQuery(name = "AtributosConfiguracionEntity.findAll", query = "SELECT a FROM AtributosConfiguracionEntity a")})
public class AtributosConfiguracionEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AtributosConfiguracionEntityPK atributosConfiguracionPK;
    @Basic(optional = false)
    @Column(name = "valor")
    private String valor;
    @Basic(optional = false)
    @Column(name = "valor_hasta")
    private String valorHasta;
    @JoinColumn(name = "configuracion_id", referencedColumnName = "configuracion_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ConfiguracionEntity configuracion;
    @JoinColumn(name = "atributo_id", referencedColumnName = "atributo_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private AtributosRolEntity atributosRol;

    public AtributosConfiguracionEntity() {
    }

    public AtributosConfiguracionEntity(AtributosConfiguracionEntityPK atributosConfiguracionPK) {
        this.atributosConfiguracionPK = atributosConfiguracionPK;
    }

    public AtributosConfiguracionEntity(AtributosConfiguracionEntityPK atributosConfiguracionPK, String valor, String valorHasta) {
        this.atributosConfiguracionPK = atributosConfiguracionPK;
        this.valor = valor;
        this.valorHasta = valorHasta;
    }

    public AtributosConfiguracionEntity(int configuracionId, int atributoId) {
        this.atributosConfiguracionPK = new AtributosConfiguracionEntityPK(configuracionId, atributoId);
    }

    public AtributosConfiguracionEntityPK getAtributosConfiguracionPK() {
        return atributosConfiguracionPK;
    }

    public void setAtributosConfiguracionPK(AtributosConfiguracionEntityPK atributosConfiguracionPK) {
        this.atributosConfiguracionPK = atributosConfiguracionPK;
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

    public ConfiguracionEntity getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(ConfiguracionEntity configuracion) {
        this.configuracion = configuracion;
    }

    public AtributosRolEntity getAtributosRol() {
        return atributosRol;
    }

    public void setAtributosRol(AtributosRolEntity atributosRol) {
        this.atributosRol = atributosRol;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (atributosConfiguracionPK != null ? atributosConfiguracionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AtributosConfiguracionEntity)) {
            return false;
        }
        AtributosConfiguracionEntity other = (AtributosConfiguracionEntity) object;
        if ((this.atributosConfiguracionPK == null && other.atributosConfiguracionPK != null) || (this.atributosConfiguracionPK != null && !this.atributosConfiguracionPK.equals(other.atributosConfiguracionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.AtributosConfiguracion[atributosConfiguracionPK=" + atributosConfiguracionPK + "]";
    }

    @Override
    public Object getPK() {
        return atributosConfiguracionPK;
    }

}
