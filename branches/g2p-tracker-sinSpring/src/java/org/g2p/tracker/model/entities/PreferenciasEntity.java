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
 * @author nacho
 */
@Entity
@Table(name = "preferencias")
@NamedQueries({@NamedQuery(name = "Preferencias.findAll", query = "SELECT p FROM Preferencias p")})
public class PreferenciasEntity extends BaseEntity implements Serializable {
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "preferencias", fetch = FetchType.LAZY)
    private Set<UsuarioPreferenciasEntity> usuarioPreferenciasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "preferencias1", fetch = FetchType.LAZY)
    private Set<UsuarioPreferenciasEntity> usuarioPreferenciasCollection1;

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

    public Set<UsuarioPreferenciasEntity> getUsuarioPreferenciasCollection() {
        return usuarioPreferenciasCollection;
    }

    public void setUsuarioPreferenciasCollection(Set<UsuarioPreferenciasEntity> usuarioPreferenciasCollection) {
        this.usuarioPreferenciasCollection = usuarioPreferenciasCollection;
    }

    public Set<UsuarioPreferenciasEntity> getUsuarioPreferenciasCollection1() {
        return usuarioPreferenciasCollection1;
    }

    public void setUsuarioPreferenciasCollection1(Set<UsuarioPreferenciasEntity> usuarioPreferenciasCollection1) {
        this.usuarioPreferenciasCollection1 = usuarioPreferenciasCollection1;
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
        return "org.g2p.tracker.model.entities.Preferencias[preferenciaId=" + preferenciaId + "]";
    }

    @Override
    public Object getPK() {
        return preferenciaId;
    }

}
