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
@Table(name = "configuracion")
@NamedQueries({@NamedQuery(name = "ConfiguracionEntity.findAll", query = "SELECT c FROM ConfiguracionEntity c"), @NamedQuery(name = "ConfiguracionEntity.findByConfiguracionId", query = "SELECT c FROM ConfiguracionEntity c WHERE c.configuracionId = :configuracionId"), @NamedQuery(name = "ConfiguracionEntity.findByNombre", query = "SELECT c FROM ConfiguracionEntity c WHERE c.nombre = :nombre"), @NamedQuery(name = "ConfiguracionEntity.findByDescripcion", query = "SELECT c FROM ConfiguracionEntity c WHERE c.descripcion = :descripcion"), @NamedQuery(name = "ConfiguracionEntity.findByObservaciones", query = "SELECT c FROM ConfiguracionEntity c WHERE c.observaciones = :observaciones"), @NamedQuery(name = "ConfiguracionEntity.findByCardinalidad", query = "SELECT c FROM ConfiguracionEntity c WHERE c.cardinalidad = :cardinalidad"), @NamedQuery(name = "ConfiguracionEntity.findByPrioridad", query = "SELECT c FROM ConfiguracionEntity c WHERE c.prioridad = :prioridad")})
public class ConfiguracionEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "configuracion_id")
    private Integer configuracionId;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "observaciones")
    private String observaciones;
    @Basic(optional = false)
    @Column(name = "cardinalidad")
    private int cardinalidad;
    @Basic(optional = false)
    @Column(name = "prioridad")
    private int prioridad;
    @JoinColumn(name = "esquema_configuracion_id", referencedColumnName = "esquema_configuracion_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private EsquemaConfiguracionEntity esquemaConfiguracionId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "configuracionEntity", fetch = FetchType.EAGER)
    private Set<AtributosConfiguracionEntity> atributosConfiguracionEntityCollection;

    public ConfiguracionEntity() {
    }

    public ConfiguracionEntity(Integer configuracionId) {
        this.configuracionId = configuracionId;
    }

    public ConfiguracionEntity(Integer configuracionId, String nombre, int cardinalidad, int prioridad) {
        this.configuracionId = configuracionId;
        this.nombre = nombre;
        this.cardinalidad = cardinalidad;
        this.prioridad = prioridad;
    }

    public Integer getConfiguracionId() {
        return configuracionId;
    }

    public void setConfiguracionId(Integer configuracionId) {
        this.configuracionId = configuracionId;
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

    public int getCardinalidad() {
        return cardinalidad;
    }

    public void setCardinalidad(int cardinalidad) {
        this.cardinalidad = cardinalidad;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public EsquemaConfiguracionEntity getEsquemaConfiguracionId() {
        return esquemaConfiguracionId;
    }

    public void setEsquemaConfiguracionId(EsquemaConfiguracionEntity esquemaConfiguracionId) {
        this.esquemaConfiguracionId = esquemaConfiguracionId;
    }

    public Set<AtributosConfiguracionEntity> getAtributosConfiguracionEntityCollection() {
        return atributosConfiguracionEntityCollection;
    }

    public void setAtributosConfiguracionEntityCollection(Set<AtributosConfiguracionEntity> atributosConfiguracionEntityCollection) {
        this.atributosConfiguracionEntityCollection = atributosConfiguracionEntityCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (configuracionId != null ? configuracionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConfiguracionEntity)) {
            return false;
        }
        ConfiguracionEntity other = (ConfiguracionEntity) object;
        if ((this.configuracionId == null && other.configuracionId != null) || (this.configuracionId != null && !this.configuracionId.equals(other.configuracionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.entities.ConfiguracionEntity[configuracionId=" + configuracionId + "]";
    }

}
