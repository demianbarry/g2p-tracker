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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "preferencias")
@NamedQueries({@NamedQuery(name = "PreferenciasEntity.findAll", query = "SELECT p FROM PreferenciasEntity p")})
public class PreferenciasEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "preferencia_id")
    private Integer preferenciaId;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "observaciones")
    private String observaciones;
    @Basic(optional = false)
    @Column(name = "default")
    private String default1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "preferenciasEntity", fetch = FetchType.EAGER)
    private Set<UsuarioPreferenciasEntity> usuarioPreferenciasEntityCollection;

    public PreferenciasEntity() {
    }

    public PreferenciasEntity(Integer preferenciaId) {
        this.preferenciaId = preferenciaId;
    }

    public PreferenciasEntity(Integer preferenciaId, String nombre, String default1) {
        this.preferenciaId = preferenciaId;
        this.nombre = nombre;
        this.default1 = default1;
    }

    public Integer getPreferenciaId() {
        return preferenciaId;
    }

    public void setPreferenciaId(Integer preferenciaId) {
        this.preferenciaId = preferenciaId;
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

    public String getDefault1() {
        return default1;
    }

    public void setDefault1(String default1) {
        this.default1 = default1;
    }

    public Set<UsuarioPreferenciasEntity> getUsuarioPreferenciasEntityCollection() {
        return usuarioPreferenciasEntityCollection;
    }

    public void setUsuarioPreferenciasEntityCollection(Set<UsuarioPreferenciasEntity> usuarioPreferenciasEntityCollection) {
        this.usuarioPreferenciasEntityCollection = usuarioPreferenciasEntityCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (preferenciaId != null ? preferenciaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreferenciasEntity)) {
            return false;
        }
        PreferenciasEntity other = (PreferenciasEntity) object;
        if ((this.preferenciaId == null && other.preferenciaId != null) || (this.preferenciaId != null && !this.preferenciaId.equals(other.preferenciaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.PreferenciasEntity[preferenciaId=" + preferenciaId + "]";
    }

}
