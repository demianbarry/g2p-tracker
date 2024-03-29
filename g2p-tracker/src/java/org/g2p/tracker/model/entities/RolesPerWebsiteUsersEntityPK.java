package org.g2p.tracker.model.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author nacho
 */
@Embeddable
public class RolesPerWebsiteUsersEntityPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @Column(name = "rol_id")
    private int rolId;

    public RolesPerWebsiteUsersEntityPK() {
    }

    public RolesPerWebsiteUsersEntityPK(int userId, int rolId) {
        this.userId = userId;
        this.rolId = rolId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userId;
        hash += (int) rolId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolesPerWebsiteUsersEntityPK)) {
            return false;
        }
        RolesPerWebsiteUsersEntityPK other = (RolesPerWebsiteUsersEntityPK) object;
        if (this.userId != other.userId) {
            return false;
        }
        if (this.rolId != other.rolId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.RolesPerWebsiteUsersPK[userId=" + userId + ", rolId=" + rolId + "]";
    }

}
