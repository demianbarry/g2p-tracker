/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "rol_entidad")
@NamedQueries({@NamedQuery(name = "RolEntidadEntity.findAll", query = "SELECT r FROM RolEntidadEntity r"), @NamedQuery(name = "RolEntidadEntity.findByRol", query = "SELECT r FROM RolEntidadEntity r WHERE r.rol = :rol"), @NamedQuery(name = "RolEntidadEntity.findByNombre", query = "SELECT r FROM RolEntidadEntity r WHERE r.nombre = :nombre"), @NamedQuery(name = "RolEntidadEntity.findByDescripcion", query = "SELECT r FROM RolEntidadEntity r WHERE r.descripcion = :descripcion"), @NamedQuery(name = "RolEntidadEntity.findByObservaciones", query = "SELECT r FROM RolEntidadEntity r WHERE r.observaciones = :observaciones"), @NamedQuery(name = "RolEntidadEntity.findByDesde", query = "SELECT r FROM RolEntidadEntity r WHERE r.desde = :desde"), @NamedQuery(name = "RolEntidadEntity.findByHasta", query = "SELECT r FROM RolEntidadEntity r WHERE r.hasta = :hasta"), @NamedQuery(name = "RolEntidadEntity.findByAnulado", query = "SELECT r FROM RolEntidadEntity r WHERE r.anulado = :anulado")})
public class RolEntidadEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "rol")
    private String rol;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "observaciones")
    private String observaciones;
    @Basic(optional = false)
    @Column(name = "desde")
    @Temporal(TemporalType.DATE)
    private Date desde;
    @Column(name = "hasta")
    @Temporal(TemporalType.DATE)
    private Date hasta;
    @Basic(optional = false)
    @Column(name = "anulado")
    private char anulado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rolEntidadEntity", fetch = FetchType.EAGER)
    private Set<RolesEntidadEntity> rolesEntidadEntityCollection;
    @OneToMany(mappedBy = "rol", fetch = FetchType.EAGER)
    private Set<AtributosRolEntity> atributosRolEntityCollection;

    public RolEntidadEntity() {
    }

    public RolEntidadEntity(String rol) {
        this.rol = rol;
    }

    public RolEntidadEntity(String rol, String nombre, Date desde, char anulado) {
        this.rol = rol;
        this.nombre = nombre;
        this.desde = desde;
        this.anulado = anulado;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
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

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }

    public char getAnulado() {
        return anulado;
    }

    public void setAnulado(char anulado) {
        this.anulado = anulado;
    }

    public Set<RolesEntidadEntity> getRolesEntidadEntityCollection() {
        return rolesEntidadEntityCollection;
    }

    public void setRolesEntidadEntityCollection(Set<RolesEntidadEntity> rolesEntidadEntityCollection) {
        this.rolesEntidadEntityCollection = rolesEntidadEntityCollection;
    }

    public Set<AtributosRolEntity> getAtributosRolEntityCollection() {
        return atributosRolEntityCollection;
    }

    public void setAtributosRolEntityCollection(Set<AtributosRolEntity> atributosRolEntityCollection) {
        this.atributosRolEntityCollection = atributosRolEntityCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rol != null ? rol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolEntidadEntity)) {
            return false;
        }
        RolEntidadEntity other = (RolEntidadEntity) object;
        if ((this.rol == null && other.rol != null) || (this.rol != null && !this.rol.equals(other.rol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.entities.RolEntidadEntity[rol=" + rol + "]";
    }

}
