/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "acceso_menu")
@NamedQueries({
    @NamedQuery(name = "AccesoMenuEntity.findAll", query = "SELECT a FROM AccesoMenuEntity a"),
    @NamedQuery(name = "AccesoMenuEntity.findByUsuarioId", query = "SELECT a FROM AccesoMenuEntity a WHERE a.userId.userId = :userId")})
public class AccesoMenuEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "acceso_menu_id")
    private Integer accesoMenuId;
    @JoinColumn(name = "menu_id", referencedColumnName = "menu_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private MenuEntity menuId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private WebsiteUserEntity userId;
    @JoinColumn(name = "rol_id", referencedColumnName = "rol_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private RolesEntity rolId;

    public AccesoMenuEntity() {
    }

    public AccesoMenuEntity(Integer accesoMenuId) {
        this.accesoMenuId = accesoMenuId;
    }

    public Integer getAccesoMenuId() {
        return accesoMenuId;
    }

    public void setAccesoMenuId(Integer accesoMenuId) {
        this.accesoMenuId = accesoMenuId;
    }

    public MenuEntity getMenuId() {
        return menuId;
    }

    public void setMenuId(MenuEntity menuId) {
        this.menuId = menuId;
    }

    public WebsiteUserEntity getUserId() {
        return userId;
    }

    public void setUserId(WebsiteUserEntity userId) {
        this.userId = userId;
    }

    public RolesEntity getRolId() {
        return rolId;
    }

    public void setRolId(RolesEntity rolId) {
        this.rolId = rolId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accesoMenuId != null ? accesoMenuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccesoMenuEntity)) {
            return false;
        }
        AccesoMenuEntity other = (AccesoMenuEntity) object;
        if ((this.accesoMenuId == null && other.accesoMenuId != null) || (this.accesoMenuId != null && !this.accesoMenuId.equals(other.accesoMenuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.AccesoMenuEntity[accesoMenuId=" + accesoMenuId + "]";
    }

}
