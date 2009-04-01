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
import javax.persistence.JoinColumns;
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
@Table(name = "esquema_configuracion")
@NamedQueries({@NamedQuery(name = "EsquemaConfiguracionEntity.findAll", query = "SELECT e FROM EsquemaConfiguracionEntity e"), @NamedQuery(name = "EsquemaConfiguracionEntity.findByEsquemaConfiguracionId", query = "SELECT e FROM EsquemaConfiguracionEntity e WHERE e.esquemaConfiguracionId = :esquemaConfiguracionId"), @NamedQuery(name = "EsquemaConfiguracionEntity.findByNombre", query = "SELECT e FROM EsquemaConfiguracionEntity e WHERE e.nombre = :nombre"), @NamedQuery(name = "EsquemaConfiguracionEntity.findByDescripcion", query = "SELECT e FROM EsquemaConfiguracionEntity e WHERE e.descripcion = :descripcion"), @NamedQuery(name = "EsquemaConfiguracionEntity.findByObservaciones", query = "SELECT e FROM EsquemaConfiguracionEntity e WHERE e.observaciones = :observaciones")})
public class EsquemaConfiguracionEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "esquema_configuracion_id")
    private Integer esquemaConfiguracionId;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "observaciones")
    private String observaciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "esquemaConfiguracionId", fetch = FetchType.EAGER)
    private Set<ConfiguracionEntity> configuracionEntityCollection;
    @JoinColumns({@JoinColumn(name = "tipo_objeto", referencedColumnName = "tipo_objeto"), @JoinColumn(name = "nombre_objeto", referencedColumnName = "nombre_objeto")})
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private DiccionarioAplicacionEntity diccionarioAplicacionEntity;

    public EsquemaConfiguracionEntity() {
    }

    public EsquemaConfiguracionEntity(Integer esquemaConfiguracionId) {
        this.esquemaConfiguracionId = esquemaConfiguracionId;
    }

    public EsquemaConfiguracionEntity(Integer esquemaConfiguracionId, String nombre) {
        this.esquemaConfiguracionId = esquemaConfiguracionId;
        this.nombre = nombre;
    }

    public Integer getEsquemaConfiguracionId() {
        return esquemaConfiguracionId;
    }

    public void setEsquemaConfiguracionId(Integer esquemaConfiguracionId) {
        this.esquemaConfiguracionId = esquemaConfiguracionId;
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

    public Set<ConfiguracionEntity> getConfiguracionEntityCollection() {
        return configuracionEntityCollection;
    }

    public void setConfiguracionEntityCollection(Set<ConfiguracionEntity> configuracionEntityCollection) {
        this.configuracionEntityCollection = configuracionEntityCollection;
    }

    public DiccionarioAplicacionEntity getDiccionarioAplicacionEntity() {
        return diccionarioAplicacionEntity;
    }

    public void setDiccionarioAplicacionEntity(DiccionarioAplicacionEntity diccionarioAplicacionEntity) {
        this.diccionarioAplicacionEntity = diccionarioAplicacionEntity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (esquemaConfiguracionId != null ? esquemaConfiguracionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EsquemaConfiguracionEntity)) {
            return false;
        }
        EsquemaConfiguracionEntity other = (EsquemaConfiguracionEntity) object;
        if ((this.esquemaConfiguracionId == null && other.esquemaConfiguracionId != null) || (this.esquemaConfiguracionId != null && !this.esquemaConfiguracionId.equals(other.esquemaConfiguracionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.entities.EsquemaConfiguracionEntity[esquemaConfiguracionId=" + esquemaConfiguracionId + "]";
    }

}
