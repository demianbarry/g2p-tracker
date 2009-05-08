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
 * @author nacho
 */
@Entity
@Table(name = "rol_entidad")
@NamedQueries({@NamedQuery(name = "RolEntidad.findAll", query = "SELECT r FROM RolEntidad r")})
public class RolEntidadEntity extends BaseEntity implements Serializable {
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rolEntidad", fetch = FetchType.LAZY)
    private Set<RolesEntidadEntity> rolesEntidadCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rolEntidad1", fetch = FetchType.LAZY)
    private Set<RolesEntidadEntity> rolesEntidadCollection1;
    @OneToMany(mappedBy = "rol", fetch = FetchType.LAZY)
    private Set<AtributosRolEntity> atributosRolCollection;
    @OneToMany(mappedBy = "rol1", fetch = FetchType.LAZY)
    private Set<AtributosRolEntity> atributosRolCollection1;

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

    public Set<RolesEntidadEntity> getRolesEntidadCollection() {
        return rolesEntidadCollection;
    }

    public void setRolesEntidadCollection(Set<RolesEntidadEntity> rolesEntidadCollection) {
        this.rolesEntidadCollection = rolesEntidadCollection;
    }

    public Set<RolesEntidadEntity> getRolesEntidadCollection1() {
        return rolesEntidadCollection1;
    }

    public void setRolesEntidadCollection1(Set<RolesEntidadEntity> rolesEntidadCollection1) {
        this.rolesEntidadCollection1 = rolesEntidadCollection1;
    }

    public Set<AtributosRolEntity> getAtributosRolCollection() {
        return atributosRolCollection;
    }

    public void setAtributosRolCollection(Set<AtributosRolEntity> atributosRolCollection) {
        this.atributosRolCollection = atributosRolCollection;
    }

    public Set<AtributosRolEntity> getAtributosRolCollection1() {
        return atributosRolCollection1;
    }

    public void setAtributosRolCollection1(Set<AtributosRolEntity> atributosRolCollection1) {
        this.atributosRolCollection1 = atributosRolCollection1;
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
        return "org.g2p.tracker.model.entities.RolEntidad[rol=" + rol + "]";
    }

    @Override
    public Object getPK() {
        return rol;
    }

}
