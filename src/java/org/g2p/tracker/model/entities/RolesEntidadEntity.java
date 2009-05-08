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
 * @author nacho
 */
@Entity
@Table(name = "roles_entidad")
@NamedQueries({@NamedQuery(name = "RolesEntidad.findAll", query = "SELECT r FROM RolesEntidad r")})
public class RolesEntidadEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RolesEntidadEntityPK rolesEntidadPK;
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
    @Column(name = "rol")
    private String rol;
    @JoinColumn(name = "entidad_id", referencedColumnName = "entidad_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EntidadExternaEntity entidadExterna;
    @JoinColumn(name = "rol_id", referencedColumnName = "rol", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RolEntidadEntity rolEntidad;
    @JoinColumn(name = "entidad_id", referencedColumnName = "entidad_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EntidadExternaEntity entidadExterna1;
    @JoinColumn(name = "rol_id", referencedColumnName = "rol", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RolEntidadEntity rolEntidad1;

    public RolesEntidadEntity() {
    }

    public RolesEntidadEntity(RolesEntidadEntityPK rolesEntidadPK) {
        this.rolesEntidadPK = rolesEntidadPK;
    }

    public RolesEntidadEntity(RolesEntidadEntityPK rolesEntidadPK, Date desde, char activo, char anulado) {
        this.rolesEntidadPK = rolesEntidadPK;
        this.desde = desde;
        this.activo = activo;
        this.anulado = anulado;
    }

    public RolesEntidadEntity(int entidadId, String rolId) {
        this.rolesEntidadPK = new RolesEntidadEntityPK(entidadId, rolId);
    }

    public RolesEntidadEntityPK getRolesEntidadPK() {
        return rolesEntidadPK;
    }

    public void setRolesEntidadPK(RolesEntidadEntityPK rolesEntidadPK) {
        this.rolesEntidadPK = rolesEntidadPK;
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public EntidadExternaEntity getEntidadExterna() {
        return entidadExterna;
    }

    public void setEntidadExterna(EntidadExternaEntity entidadExterna) {
        this.entidadExterna = entidadExterna;
    }

    public RolEntidadEntity getRolEntidad() {
        return rolEntidad;
    }

    public void setRolEntidad(RolEntidadEntity rolEntidad) {
        this.rolEntidad = rolEntidad;
    }

    public EntidadExternaEntity getEntidadExterna1() {
        return entidadExterna1;
    }

    public void setEntidadExterna1(EntidadExternaEntity entidadExterna1) {
        this.entidadExterna1 = entidadExterna1;
    }

    public RolEntidadEntity getRolEntidad1() {
        return rolEntidad1;
    }

    public void setRolEntidad1(RolEntidadEntity rolEntidad1) {
        this.rolEntidad1 = rolEntidad1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolesEntidadPK != null ? rolesEntidadPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolesEntidadEntity)) {
            return false;
        }
        RolesEntidadEntity other = (RolesEntidadEntity) object;
        if ((this.rolesEntidadPK == null && other.rolesEntidadPK != null) || (this.rolesEntidadPK != null && !this.rolesEntidadPK.equals(other.rolesEntidadPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.RolesEntidad[rolesEntidadPK=" + rolesEntidadPK + "]";
    }

    @Override
    public Object getPK() {
        return rolesEntidadPK;
    }

}
