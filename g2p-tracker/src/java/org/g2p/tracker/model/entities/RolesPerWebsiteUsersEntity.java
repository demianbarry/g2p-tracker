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
@Table(name = "roles_per_website_users")
@NamedQueries({
    @NamedQuery(name = "RolesPerWebsiteUsersEntity.findAll", query = "SELECT u FROM RolesPerWebsiteUsersEntity u"),
    @NamedQuery(name = "RolesPerWebsiteUsersEntity.findByUserId", query = "SELECT u FROM RolesPerWebsiteUsersEntity u WHERE u.rolesPerWebsiteUsersPK.userId = :userId"),
    @NamedQuery(name = "RolesPerWebsiteUsersEntity.findByRolIdComplement", query = "SELECT r FROM RolesEntity r WHERE r.rolId NOT IN (SELECT u.rolesPerWebsiteUsersPK.rolId FROM RolesPerWebsiteUsersEntity u WHERE u.rolesPerWebsiteUsersPK.userId = :userId) OR r.rolId = :rolId")})
public class RolesPerWebsiteUsersEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RolesPerWebsiteUsersEntityPK rolesPerWebsiteUsersPK;
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
    @Column(name = "OBJ_VERSION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date objVersion;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private WebsiteUsersEntity websiteUsers;
    @JoinColumn(name = "rol_id", referencedColumnName = "rol_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RolesEntity roles;

    public RolesPerWebsiteUsersEntity() {
    }

    public RolesPerWebsiteUsersEntity(RolesPerWebsiteUsersEntityPK rolesPerWebsiteUsersPK) {
        this.rolesPerWebsiteUsersPK = rolesPerWebsiteUsersPK;
    }

    public RolesPerWebsiteUsersEntity(RolesPerWebsiteUsersEntityPK rolesPerWebsiteUsersPK, Date desde, char anulado) {
        this.rolesPerWebsiteUsersPK = rolesPerWebsiteUsersPK;
        this.desde = desde;
        this.anulado = anulado;
    }

    public RolesPerWebsiteUsersEntity(int userId, int rolId) {
        this.rolesPerWebsiteUsersPK = new RolesPerWebsiteUsersEntityPK(userId, rolId);
    }

    public RolesPerWebsiteUsersEntityPK getRolesPerWebsiteUsersPK() {
        return rolesPerWebsiteUsersPK;
    }

    public void setRolesPerWebsiteUsersPK(RolesPerWebsiteUsersEntityPK rolesPerWebsiteUsersPK) {
        this.rolesPerWebsiteUsersPK = rolesPerWebsiteUsersPK;
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

    public Date getObjVersion() {
        return objVersion;
    }

    public void setObjVersion(Date objVersion) {
        this.objVersion = objVersion;
    }

    public WebsiteUsersEntity getWebsiteUsers() {
        return websiteUsers;
    }

    public void setWebsiteUsers(WebsiteUsersEntity websiteUsers) {
        this.websiteUsers = websiteUsers;
    }

    public RolesEntity getRoles() {
        return roles;
    }

    public void setRoles(RolesEntity roles) {
        this.roles = roles;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolesPerWebsiteUsersPK != null ? rolesPerWebsiteUsersPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolesPerWebsiteUsersEntity)) {
            return false;
        }
        RolesPerWebsiteUsersEntity other = (RolesPerWebsiteUsersEntity) object;
        if ((this.rolesPerWebsiteUsersPK == null && other.rolesPerWebsiteUsersPK != null) || (this.rolesPerWebsiteUsersPK != null && !this.rolesPerWebsiteUsersPK.equals(other.rolesPerWebsiteUsersPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.RolesPerWebsiteUsers[rolesPerWebsiteUsersPK=" + rolesPerWebsiteUsersPK + "]";
    }

    @Override
    public Object getPK() {
        return rolesPerWebsiteUsersPK;
    }

}
