/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.entities;

import java.io.Serializable;
import java.sql.Timestamp;
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
import javax.persistence.Version;

/**
 *
 * @author Administrador
 */
@Entity
@org.hibernate.annotations.Entity(
    optimisticLock = org.hibernate.annotations.OptimisticLockType.ALL,
    dynamicUpdate=true,
    dynamicInsert=true)
@Table(name = "website_user")
@NamedQueries({@NamedQuery(name = "WebsiteUserEntity.findAll", query = "SELECT w FROM WebsiteUserEntity w"), @NamedQuery(name = "WebsiteUserEntity.findByUserId", query = "SELECT w FROM WebsiteUserEntity w WHERE w.userId = :userId"), @NamedQuery(name = "WebsiteUserEntity.findByLoginName", query = "SELECT w FROM WebsiteUserEntity w WHERE w.loginName = :loginName"), @NamedQuery(name = "WebsiteUserEntity.findByLoginPassword", query = "SELECT w FROM WebsiteUserEntity w WHERE w.loginPassword = :loginPassword"), @NamedQuery(name = "WebsiteUserEntity.findByNivelVisibilidad", query = "SELECT w FROM WebsiteUserEntity w WHERE w.nivelVisibilidad = :nivelVisibilidad"), @NamedQuery(name = "WebsiteUserEntity.findByNombreCompleto", query = "SELECT w FROM WebsiteUserEntity w WHERE w.nombreCompleto = :nombreCompleto"), @NamedQuery(name = "WebsiteUserEntity.findByEmail", query = "SELECT w FROM WebsiteUserEntity w WHERE w.email = :email"), @NamedQuery(name = "WebsiteUserEntity.findByNroLegajo", query = "SELECT w FROM WebsiteUserEntity w WHERE w.nroLegajo = :nroLegajo"), @NamedQuery(name = "WebsiteUserEntity.findByNroComprador", query = "SELECT w FROM WebsiteUserEntity w WHERE w.nroComprador = :nroComprador")})
public class WebsiteUserEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @Column(name = "login_name")
    private String loginName;
    @Column(name = "login_password")
    private String loginPassword;
    @Column(name = "nivel_visibilidad")
    private String nivelVisibilidad;
    @Column(name = "nombre_completo")
    private String nombreCompleto;
    @Column(name = "email")
    private String email;
    @Column(name = "nro_legajo")
    private Integer nroLegajo;
    @Column(name = "nro_comprador")
    private Integer nroComprador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId", fetch = FetchType.EAGER)
    private Set<AuditaEstadosCircuitosEntity> auditaEstadosCircuitosEntityCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "websiteUserEntity", fetch = FetchType.EAGER)
    private Set<UsuarioPreferenciasEntity> usuarioPreferenciasEntityCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "websiteUserEntity", fetch = FetchType.EAGER)
    private Set<UsuarioRolesEntity> usuarioRolesEntityCollection;
    @OneToMany(mappedBy = "userId", fetch = FetchType.EAGER)
    private Set<AccesoMenuEntity> accesoMenuEntityCollection;
    @Version
    @Column(name = "OBJ_VERSION")
    private Timestamp version;

    public WebsiteUserEntity() {
    }

    public WebsiteUserEntity(Integer userId) {
        this.userId = userId;
    }

    public WebsiteUserEntity(Integer userId, String loginName, String loginPassword, String nivelVisibilidad) {
        this.userId = userId;
        this.loginName = loginName;
        this.loginPassword = loginPassword;
        this.nivelVisibilidad = nivelVisibilidad;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getNivelVisibilidad() {
        return nivelVisibilidad;
    }

    public void setNivelVisibilidad(String nivelVisibilidad) {
        this.nivelVisibilidad = nivelVisibilidad;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getNroLegajo() {
        return nroLegajo;
    }

    public void setNroLegajo(Integer nroLegajo) {
        this.nroLegajo = nroLegajo;
    }

    public Integer getNroComprador() {
        return nroComprador;
    }

    public void setNroComprador(Integer nroComprador) {
        this.nroComprador = nroComprador;
    }

    public Set<AuditaEstadosCircuitosEntity> getAuditaEstadosCircuitosEntityCollection() {
        return auditaEstadosCircuitosEntityCollection;
    }

    public void setAuditaEstadosCircuitosEntityCollection(Set<AuditaEstadosCircuitosEntity> auditaEstadosCircuitosEntityCollection) {
        this.auditaEstadosCircuitosEntityCollection = auditaEstadosCircuitosEntityCollection;
    }

    public Set<UsuarioPreferenciasEntity> getUsuarioPreferenciasEntityCollection() {
        return usuarioPreferenciasEntityCollection;
    }

    public void setUsuarioPreferenciasEntityCollection(Set<UsuarioPreferenciasEntity> usuarioPreferenciasEntityCollection) {
        this.usuarioPreferenciasEntityCollection = usuarioPreferenciasEntityCollection;
    }

    public Set<UsuarioRolesEntity> getUsuarioRolesEntityCollection() {
        return usuarioRolesEntityCollection;
    }

    public void setUsuarioRolesEntityCollection(Set<UsuarioRolesEntity> usuarioRolesEntityCollection) {
        this.usuarioRolesEntityCollection = usuarioRolesEntityCollection;
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
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WebsiteUserEntity)) {
            return false;
        }
        WebsiteUserEntity other = (WebsiteUserEntity) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.entities.WebsiteUserEntity[userId=" + userId + "]";
    }

    @Override
    public Object getPK() {
        return getUserId();
    }

}
