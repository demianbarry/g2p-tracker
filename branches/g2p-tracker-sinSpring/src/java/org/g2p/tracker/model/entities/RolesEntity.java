/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.entities;

import org.g2p.tracker.model.models.Taggeable;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "roles")
@NamedQueries({@NamedQuery(name = "RolesEntity.findAll", query = "SELECT r FROM RolesEntity r")})
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
    @Column(name = "OBJ_VERSION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date objVersion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<RolesPerWebsiteUsersEntity> rolesPerWebsiteUsersEntityCollection;
    @OneToMany(mappedBy = "rolId", fetch = FetchType.EAGER)
    private Set<AccesoMenuEntity> accesoMenuEntityCollection;

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

    public Date getObjVersion() {
        return objVersion;
    }

    public void setObjVersion(Date objVersion) {
        this.objVersion = objVersion;
    }

    public Set<RolesPerWebsiteUsersEntity> getRolesPerWebsiteUsersEntityCollection() {
        return rolesPerWebsiteUsersEntityCollection;
    }

    public void setRolesPerWebsiteUsersEntityCollection(Set<RolesPerWebsiteUsersEntity> rolesPerWebsiteUsersEntityCollection) {
        this.rolesPerWebsiteUsersEntityCollection = rolesPerWebsiteUsersEntityCollection;
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
        return "org.g2p.tracker.model.entities.RolesEntity[rolId=" + rolId + "]";
    }

    @Override
    public Object getPK() {
        return getRolId();
    }


}