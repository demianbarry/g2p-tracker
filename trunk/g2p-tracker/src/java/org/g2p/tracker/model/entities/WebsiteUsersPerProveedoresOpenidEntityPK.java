package org.g2p.tracker.model.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Administrador
 */
@Embeddable
public class WebsiteUsersPerProveedoresOpenidEntityPK implements Serializable {
    private static final long serialVersionUID = 4133614297038866621L;
    @Basic(optional = false)
    @Column(name = "user_id", nullable = false)
    private int userId;
    @Basic(optional = false)
    @Column(name = "proveedor_sso_id", nullable = false)
    private int proveedorSsoId;

    public WebsiteUsersPerProveedoresOpenidEntityPK() {
    }

    public WebsiteUsersPerProveedoresOpenidEntityPK(int userId, int proveedorSsoId) {
        this.userId = userId;
        this.proveedorSsoId = proveedorSsoId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProveedorSsoId() {
        return proveedorSsoId;
    }

    public void setProveedorSsoId(int proveedorSsoId) {
        this.proveedorSsoId = proveedorSsoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userId;
        hash += (int) proveedorSsoId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WebsiteUsersPerProveedoresOpenidEntityPK)) {
            return false;
        }
        WebsiteUsersPerProveedoresOpenidEntityPK other = (WebsiteUsersPerProveedoresOpenidEntityPK) object;
        if (this.userId != other.userId) {
            return false;
        }
        if (this.proveedorSsoId != other.proveedorSsoId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.WebsiteUsersPerProveedoresOpenidEntityPK[userId=" + userId + ", proveedorSsoId=" + proveedorSsoId + "]";
    }

}
