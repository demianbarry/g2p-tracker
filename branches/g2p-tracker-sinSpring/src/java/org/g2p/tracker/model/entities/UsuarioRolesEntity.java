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
@Table(name = "usuario_roles")
@NamedQueries({@NamedQuery(name = "UsuarioRoles.findAll", query = "SELECT u FROM UsuarioRoles u")})
public class UsuarioRolesEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuarioRolesEntityPK usuarioRolesPK;
    @Basic(optional = false)
    @Column(name = "anulado")
    private char anulado;
    @Basic(optional = false)
    @Column(name = "desde")
    @Temporal(TemporalType.DATE)
    private Date desde;
    @Column(name = "hasta")
    @Temporal(TemporalType.DATE)
    private Date hasta;
    @Column(name = "OBJ_VERSION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date objVersion;

    public UsuarioRolesEntity() {
    }

    public UsuarioRolesEntity(UsuarioRolesEntityPK usuarioRolesPK) {
        this.usuarioRolesPK = usuarioRolesPK;
    }

    public UsuarioRolesEntity(UsuarioRolesEntityPK usuarioRolesPK, char anulado, Date desde) {
        this.usuarioRolesPK = usuarioRolesPK;
        this.anulado = anulado;
        this.desde = desde;
    }

    public UsuarioRolesEntity(int rolId, int userId) {
        this.usuarioRolesPK = new UsuarioRolesEntityPK(rolId, userId);
    }

    public UsuarioRolesEntityPK getUsuarioRolesEntityPK() {
        return usuarioRolesPK;
    }

    public void setUsuarioRolesEntityPK(UsuarioRolesEntityPK usuarioRolesPK) {
        this.usuarioRolesPK = usuarioRolesPK;
    }

    public char getAnulado() {
        return anulado;
    }

    public void setAnulado(char anulado) {
        this.anulado = anulado;
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

    public Date getObjVersion() {
        return objVersion;
    }

    public void setObjVersion(Date objVersion) {
        this.objVersion = objVersion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioRolesPK != null ? usuarioRolesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioRolesEntity)) {
            return false;
        }
        UsuarioRolesEntity other = (UsuarioRolesEntity) object;
        if ((this.usuarioRolesPK == null && other.usuarioRolesPK != null) || (this.usuarioRolesPK != null && !this.usuarioRolesPK.equals(other.usuarioRolesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.UsuarioRoles[usuarioRolesPK=" + usuarioRolesPK + "]";
    }

    @Override
    public Object getPK() {
        return usuarioRolesPK;
    }

}
