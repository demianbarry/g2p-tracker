/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.model.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 *
 * @author Administrador
 */
@Entity
@org.hibernate.annotations.Entity(
    optimisticLock = org.hibernate.annotations.OptimisticLockType.ALL,
    dynamicUpdate=true,
    dynamicInsert=true)
@Table(name = "roles")
@NamedQueries({@NamedQuery(name = "RolesEntity.findAll", query = "SELECT r FROM RolesEntity r"), @NamedQuery(name = "RolesEntity.findByRolId", query = "SELECT r FROM RolesEntity r WHERE r.rolId = :rolId"), @NamedQuery(name = "RolesEntity.findByNombre", query = "SELECT r FROM RolesEntity r WHERE r.nombre = :nombre"), @NamedQuery(name = "RolesEntity.findByDescripcion", query = "SELECT r FROM RolesEntity r WHERE r.descripcion = :descripcion"), @NamedQuery(name = "RolesEntity.findByObservaciones", query = "SELECT r FROM RolesEntity r WHERE r.observaciones = :observaciones")})
public class RolesEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rol_id")
    private Integer rolId;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "observaciones")
    private String observaciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rolesEntity", fetch = FetchType.EAGER)
    private Set<UsuarioRolesEntity> usuarioRolesEntityCollection;
    @OneToMany(mappedBy = "rolId", fetch = FetchType.EAGER)
    private Set<AccesoMenuEntity> accesoMenuEntityCollection;
    @Version
    @Column(name = "OBJ_VERSION")
    private Timestamp version;

    public Timestamp getVersion() {
        return version;
    }

    public void setVersion(Timestamp version) {
        this.version = version;
    }

    public RolesEntity() {
    }

    public RolesEntity(Integer rolId) {
        this.rolId = rolId;
    }

    public RolesEntity(Integer rolId, String nombre) {
        this.rolId = rolId;
        this.nombre = nombre;
    }

    public Integer getRolId() {
        return rolId;
    }

    public void setRolId(Integer rolId) {
        this.rolId = rolId;
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

    public Set<UsuarioRolesEntity> getUsuarioRolesEntityCollection() {
        return usuarioRolesEntityCollection;
    }

    public void setUsuarioRolesEntityCollection(Set<UsuarioRolesEntity> usuarioRolesEntityCollection) {
        this.usuarioRolesEntityCollection = usuarioRolesEntityCollection;
    }

    public Set<AccesoMenuEntity> getAccesoMenuEntityCollection() {
        return accesoMenuEntityCollection;
    }

    public void setAccesoMenuEntityCollection(Set<AccesoMenuEntity> accesoMenuEntityCollection) {
        this.accesoMenuEntityCollection = accesoMenuEntityCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolId != null ? rolId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolesEntity)) {
            return false;
        }
        RolesEntity other = (RolesEntity) object;
        if ((this.rolId == null && other.rolId != null) || (this.rolId != null && !this.rolId.equals(other.rolId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.entities.RolesEntity[rolId=" + rolId + "]";
    }

    @Override
    public Object getPK() {
        return getRolId();
    }
}
