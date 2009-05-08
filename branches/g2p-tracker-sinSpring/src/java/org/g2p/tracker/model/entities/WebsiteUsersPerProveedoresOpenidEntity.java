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
@Table(name = "website_users_per_proveedores_openid")
@NamedQueries({@NamedQuery(name = "WebsiteUsersPerProveedoresOpenid.findAll", query = "SELECT w FROM WebsiteUsersPerProveedoresOpenid w")})
public class WebsiteUsersPerProveedoresOpenidEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected WebsiteUsersPerProveedoresOpenidEntityPK websiteUsersPerProveedoresOpenidPK;
    @Basic(optional = false)
    @Column(name = "fecha_asociacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAsociacion;

    public WebsiteUsersPerProveedoresOpenidEntity() {
    }

    public WebsiteUsersPerProveedoresOpenidEntity(WebsiteUsersPerProveedoresOpenidEntityPK websiteUsersPerProveedoresOpenidPK) {
        this.websiteUsersPerProveedoresOpenidPK = websiteUsersPerProveedoresOpenidPK;
    }

    public WebsiteUsersPerProveedoresOpenidEntity(WebsiteUsersPerProveedoresOpenidEntityPK websiteUsersPerProveedoresOpenidPK, Date fechaAsociacion) {
        this.websiteUsersPerProveedoresOpenidPK = websiteUsersPerProveedoresOpenidPK;
        this.fechaAsociacion = fechaAsociacion;
    }

    public WebsiteUsersPerProveedoresOpenidEntity(int userId, int proveedorSsoId) {
        this.websiteUsersPerProveedoresOpenidPK = new WebsiteUsersPerProveedoresOpenidEntityPK(userId, proveedorSsoId);
    }

    public WebsiteUsersPerProveedoresOpenidEntityPK getWebsiteUsersPerProveedoresOpenidPK() {
        return websiteUsersPerProveedoresOpenidPK;
    }

    public void setWebsiteUsersPerProveedoresOpenidPK(WebsiteUsersPerProveedoresOpenidEntityPK websiteUsersPerProveedoresOpenidPK) {
        this.websiteUsersPerProveedoresOpenidPK = websiteUsersPerProveedoresOpenidPK;
    }

    public Date getFechaAsociacion() {
        return fechaAsociacion;
    }

    public void setFechaAsociacion(Date fechaAsociacion) {
        this.fechaAsociacion = fechaAsociacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (websiteUsersPerProveedoresOpenidPK != null ? websiteUsersPerProveedoresOpenidPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WebsiteUsersPerProveedoresOpenidEntity)) {
            return false;
        }
        WebsiteUsersPerProveedoresOpenidEntity other = (WebsiteUsersPerProveedoresOpenidEntity) object;
        if ((this.websiteUsersPerProveedoresOpenidPK == null && other.websiteUsersPerProveedoresOpenidPK != null) || (this.websiteUsersPerProveedoresOpenidPK != null && !this.websiteUsersPerProveedoresOpenidPK.equals(other.websiteUsersPerProveedoresOpenidPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.WebsiteUsersPerProveedoresOpenid[websiteUsersPerProveedoresOpenidPK=" + websiteUsersPerProveedoresOpenidPK + "]";
    }

    @Override
    public Object getPK() {
        return websiteUsersPerProveedoresOpenidPK;
    }

}
