/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "roles_entidad")
@NamedQueries({@NamedQuery(name = "RolesEntidadEntity.findAll", query = "SELECT r FROM RolesEntidadEntity r"), @NamedQuery(name = "RolesEntidadEntity.findByDesde", query = "SELECT r FROM RolesEntidadEntity r WHERE r.desde = :desde"), @NamedQuery(name = "RolesEntidadEntity.findByHasta", query = "SELECT r FROM RolesEntidadEntity r WHERE r.hasta = :hasta"), @NamedQuery(name = "RolesEntidadEntity.findByActivo", query = "SELECT r FROM RolesEntidadEntity r WHERE r.activo = :activo"), @NamedQuery(name = "RolesEntidadEntity.findByAnulado", query = "SELECT r FROM RolesEntidadEntity r WHERE r.anulado = :anulado"), @NamedQuery(name = "RolesEntidadEntity.findByEntidadId", query = "SELECT r FROM RolesEntidadEntity r WHERE r.rolesEntidadEntityPK.entidadId = :entidadId"), @NamedQuery(name = "RolesEntidadEntity.findByRol", query = "SELECT r FROM RolesEntidadEntity r WHERE r.rolesEntidadEntityPK.rol = :rol")})
public class RolesEntidadEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RolesEntidadEntityPK rolesEntidadEntityPK;
    @Basic(optional = false)
    @Column(name = "desde")
    @Temporal(TemporalType.DATE)
    private Date desde;
    @Column(name = "hasta")
    @Temporal(TemporalType.DATE)
    private Date hasta;
    @Basic(optional = false)
    @Column(name = "activo")
    private char activo;
    @Basic(optional = false)
    @Column(name = "anulado")
    private char anulado;
    @JoinColumn(name = "entidad_id", referencedColumnName = "entidad_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private EntidadExternaEntity entidadExternaEntity;
    @JoinColumn(name = "rol", referencedColumnName = "rol", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private RolEntidadEntity rolEntidadEntity;

    public RolesEntidadEntity() {
    }

    public RolesEntidadEntity(RolesEntidadEntityPK rolesEntidadEntityPK) {
        this.rolesEntidadEntityPK = rolesEntidadEntityPK;
    }

    public RolesEntidadEntity(RolesEntidadEntityPK rolesEntidadEntityPK, Date desde, char activo, char anulado) {
        this.rolesEntidadEntityPK = rolesEntidadEntityPK;
        this.desde = desde;
        this.activo = activo;
        this.anulado = anulado;
    }

    public RolesEntidadEntity(int entidadId, String rol) {
        this.rolesEntidadEntityPK = new RolesEntidadEntityPK(entidadId, rol);
    }

    public RolesEntidadEntityPK getRolesEntidadEntityPK() {
        return rolesEntidadEntityPK;
    }

    public void setRolesEntidadEntityPK(RolesEntidadEntityPK rolesEntidadEntityPK) {
        this.rolesEntidadEntityPK = rolesEntidadEntityPK;
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

    public char getActivo() {
        return activo;
    }

    public void setActivo(char activo) {
        this.activo = activo;
    }

    public char getAnulado() {
        return anulado;
    }

    public void setAnulado(char anulado) {
        this.anulado = anulado;
    }

    public EntidadExternaEntity getEntidadExternaEntity() {
        return entidadExternaEntity;
    }

    public void setEntidadExternaEntity(EntidadExternaEntity entidadExternaEntity) {
        this.entidadExternaEntity = entidadExternaEntity;
    }

    public RolEntidadEntity getRolEntidadEntity() {
        return rolEntidadEntity;
    }

    public void setRolEntidadEntity(RolEntidadEntity rolEntidadEntity) {
        this.rolEntidadEntity = rolEntidadEntity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolesEntidadEntityPK != null ? rolesEntidadEntityPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolesEntidadEntity)) {
            return false;
        }
        RolesEntidadEntity other = (RolesEntidadEntity) object;
        if ((this.rolesEntidadEntityPK == null && other.rolesEntidadEntityPK != null) || (this.rolesEntidadEntityPK != null && !this.rolesEntidadEntityPK.equals(other.rolesEntidadEntityPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.entities.RolesEntidadEntity[rolesEntidadEntityPK=" + rolesEntidadEntityPK + "]";
    }

}
