/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "menu")
@NamedQueries({
    @NamedQuery(name = "MenuEntity.findAll", query = "SELECT m FROM MenuEntity m"),
    @NamedQuery(name = "MenuEntity.findByGroup", query = "SELECT m FROM MenuEntity m WHERE m.grupo = :GroupName")})
public class MenuEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "menu_id")
    private Integer menuId;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "url")
    private String url;
    @Basic(optional = false)
    @Column(name = "grupo")
    private String grupo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "menuId", fetch = FetchType.EAGER)
    private Set<AccesoMenuEntity> accesoMenuEntityCollection;

    public MenuEntity() {
    }

    public MenuEntity(Integer menuId) {
        this.menuId = menuId;
    }

    public MenuEntity(Integer menuId, String nombre, String grupo) {
        this.menuId = menuId;
        this.nombre = nombre;
        this.grupo = grupo;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public Set<AccesoMenuEntity> getAccesoMenuEntityCollection() {
        return accesoMenuEntityCollection;
    }

    public void setAccesoMenuEntityCollection(Set<AccesoMenuEntity> accesoMenuEntityCollection) {
        this.accesoMenuEntityCollection = accesoMenuEntityCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (menuId != null ? menuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MenuEntity)) {
            return false;
        }
        MenuEntity other = (MenuEntity) object;
        if ((this.menuId == null && other.menuId != null) || (this.menuId != null && !this.menuId.equals(other.menuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.MenuEntity[menuId=" + menuId + "]";
    }

}
