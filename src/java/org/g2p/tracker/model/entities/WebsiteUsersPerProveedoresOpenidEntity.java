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
 * @author Administrador
 */
@Entity
@Table(name = "website_users_per_proveedores_openid")
@NamedQueries({@NamedQuery(name = "WebsiteUsersPerProveedoresOpenidEntity.findAll", query = "SELECT w FROM WebsiteUsersPerProveedoresOpenidEntity w"),
    @NamedQuery(name = "WebsiteUsersPerProveedoresOpenidEntity.findByUserId", query = "SELECT w FROM WebsiteUsersPerProveedoresOpenidEntity w WHERE w.websiteUsersPerProveedoresOpenidEntityPK.userId = :userId"),
    @NamedQuery(name = "WebsiteUsersPerProveedoresOpenidEntity.findByClaimedId", query = "SELECT w FROM WebsiteUsersPerProveedoresOpenidEntity w WHERE w.claimedId = :claimedId")})
public class WebsiteUsersPerProveedoresOpenidEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected WebsiteUsersPerProveedoresOpenidEntityPK websiteUsersPerProveedoresOpenidEntityPK;
    @Basic(optional = false)
    @Column(name = "fecha_asociacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAsociacion;
    @Basic(optional = false)
    @Column(name = "claimed_id")
    private String claimedId;

    public WebsiteUsersPerProveedoresOpenidEntity() {
    }

    public WebsiteUsersPerProveedoresOpenidEntity(WebsiteUsersPerProveedoresOpenidEntityPK websiteUsersPerProveedoresOpenidEntityPK) {
        this.websiteUsersPerProveedoresOpenidEntityPK = websiteUsersPerProveedoresOpenidEntityPK;
    }

    public WebsiteUsersPerProveedoresOpenidEntity(WebsiteUsersPerProveedoresOpenidEntityPK websiteUsersPerProveedoresOpenidEntityPK, Date fechaAsociacion, String claimedId) {
        this.websiteUsersPerProveedoresOpenidEntityPK = websiteUsersPerProveedoresOpenidEntityPK;
        this.fechaAsociacion = fechaAsociacion;
        this.claimedId = claimedId;
    }

    public WebsiteUsersPerProveedoresOpenidEntity(int userId, int proveedorSsoId) {
        this.websiteUsersPerProveedoresOpenidEntityPK = new WebsiteUsersPerProveedoresOpenidEntityPK(userId, proveedorSsoId);
    }

    public WebsiteUsersPerProveedoresOpenidEntityPK getWebsiteUsersPerProveedoresOpenidEntityPK() {
        return websiteUsersPerProveedoresOpenidEntityPK;
    }

    public void setWebsiteUsersPerProveedoresOpenidEntityPK(WebsiteUsersPerProveedoresOpenidEntityPK websiteUsersPerProveedoresOpenidEntityPK) {
        this.websiteUsersPerProveedoresOpenidEntityPK = websiteUsersPerProveedoresOpenidEntityPK;
    }

    public Date getFechaAsociacion() {
        return fechaAsociacion;
    }

    public void setFechaAsociacion(Date fechaAsociacion) {
        this.fechaAsociacion = fechaAsociacion;
    }

    public String getClaimedId() {
        return claimedId;
    }

    public void setClaimedId(String claimedId) {
        this.claimedId = claimedId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (websiteUsersPerProveedoresOpenidEntityPK != null ? websiteUsersPerProveedoresOpenidEntityPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WebsiteUsersPerProveedoresOpenidEntity)) {
            return false;
        }
        WebsiteUsersPerProveedoresOpenidEntity other = (WebsiteUsersPerProveedoresOpenidEntity) object;
        if ((this.websiteUsersPerProveedoresOpenidEntityPK == null && other.websiteUsersPerProveedoresOpenidEntityPK != null) || (this.websiteUsersPerProveedoresOpenidEntityPK != null && !this.websiteUsersPerProveedoresOpenidEntityPK.equals(other.websiteUsersPerProveedoresOpenidEntityPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.WebsiteUsersPerProveedoresOpenidEntity[websiteUsersPerProveedoresOpenidEntityPK=" + websiteUsersPerProveedoresOpenidEntityPK + "]";
    }
}
