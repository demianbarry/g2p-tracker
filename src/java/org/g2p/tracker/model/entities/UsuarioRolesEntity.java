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
@Table(name = "usuario_roles")
@NamedQueries({@NamedQuery(name = "UsuarioRolesEntity.findAll", query = "SELECT u FROM UsuarioRolesEntity u"), @NamedQuery(name = "UsuarioRolesEntity.findByUserId", query = "SELECT u FROM UsuarioRolesEntity u WHERE u.usuarioRolesEntityPK.userId = :userId"), @NamedQuery(name = "UsuarioRolesEntity.findByRolId", query = "SELECT u FROM UsuarioRolesEntity u WHERE u.usuarioRolesEntityPK.rolId = :rolId"), @NamedQuery(name = "UsuarioRolesEntity.findByDesde", query = "SELECT u FROM UsuarioRolesEntity u WHERE u.desde = :desde"), @NamedQuery(name = "UsuarioRolesEntity.findByHasta", query = "SELECT u FROM UsuarioRolesEntity u WHERE u.hasta = :hasta"), @NamedQuery(name = "UsuarioRolesEntity.findByAnulado", query = "SELECT u FROM UsuarioRolesEntity u WHERE u.anulado = :anulado")})
public class UsuarioRolesEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuarioRolesEntityPK usuarioRolesEntityPK;
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
    @JoinColumn(name = "rol_id", referencedColumnName = "rol_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private RolesEntity rolesEntity;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private WebsiteUserEntity websiteUserEntity;

    public UsuarioRolesEntity() {
    }

    public UsuarioRolesEntity(UsuarioRolesEntityPK usuarioRolesEntityPK) {
        this.usuarioRolesEntityPK = usuarioRolesEntityPK;
    }

    public UsuarioRolesEntity(UsuarioRolesEntityPK usuarioRolesEntityPK, Date desde, char anulado) {
        this.usuarioRolesEntityPK = usuarioRolesEntityPK;
        this.desde = desde;
        this.anulado = anulado;
    }

    public UsuarioRolesEntity(int userId, int rolId) {
        this.usuarioRolesEntityPK = new UsuarioRolesEntityPK(userId, rolId);
    }

    public UsuarioRolesEntityPK getUsuarioRolesEntityPK() {
        return usuarioRolesEntityPK;
    }

    public void setUsuarioRolesEntityPK(UsuarioRolesEntityPK usuarioRolesEntityPK) {
        this.usuarioRolesEntityPK = usuarioRolesEntityPK;
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

    public RolesEntity getRolesEntity() {
        return rolesEntity;
    }

    public void setRolesEntity(RolesEntity rolesEntity) {
        this.rolesEntity = rolesEntity;
    }

    public WebsiteUserEntity getWebsiteUserEntity() {
        return websiteUserEntity;
    }

    public void setWebsiteUserEntity(WebsiteUserEntity websiteUserEntity) {
        this.websiteUserEntity = websiteUserEntity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioRolesEntityPK != null ? usuarioRolesEntityPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioRolesEntity)) {
            return false;
        }
        UsuarioRolesEntity other = (UsuarioRolesEntity) object;
        if ((this.usuarioRolesEntityPK == null && other.usuarioRolesEntityPK != null) || (this.usuarioRolesEntityPK != null && !this.usuarioRolesEntityPK.equals(other.usuarioRolesEntityPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.entities.UsuarioRolesEntity[usuarioRolesEntityPK=" + usuarioRolesEntityPK + "]";
    }

}
