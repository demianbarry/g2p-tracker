/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author nacho
 */
@Entity
@Table(name = "website_users")
@NamedQueries({@NamedQuery(name = "WebsiteUsers.findAll", query = "SELECT w FROM WebsiteUsers w")})
public class WebsiteUsersEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @Column(name = "login_name")
    private String loginName;
    @Basic(optional = false)
    @Column(name = "login_password")
    private String loginPassword;
    @Column(name = "nivel_visibilidad")
    private String nivelVisibilidad;
    @Basic(optional = false)
    @Column(name = "nombre_completo")
    private String nombreCompleto;
    @Column(name = "email")
    private String email;
    @Column(name = "nro_legajo")
    private Integer nroLegajo;
    @Column(name = "nro_comprador")
    private Integer nroComprador;
    @Column(name = "OBJ_VERSION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date objVersion;
    @Basic(optional = false)
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId", fetch = FetchType.LAZY)
    private Set<AuditaEstadosCircuitosEntity> auditaEstadosCircuitosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId", fetch = FetchType.LAZY)
    private Set<PostsEntity> postsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "websiteUsers", fetch = FetchType.LAZY)
    private Set<UsuarioPreferenciasEntity> usuarioPreferenciasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "websiteUsers1", fetch = FetchType.LAZY)
    private Set<UsuarioPreferenciasEntity> usuarioPreferenciasCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "websiteUsers", fetch = FetchType.LAZY)
    private Set<RolesPerWebsiteUsersEntity> rolesPerWebsiteUsersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userIdOwner", fetch = FetchType.LAZY)
    private Set<TracksEntity> tracksCollection;
    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    private Set<AccesoMenuEntity> accesoMenuCollection;
    @OneToMany(mappedBy = "userId1", fetch = FetchType.LAZY)
    private Set<AccesoMenuEntity> accesoMenuCollection1;

    public WebsiteUsersEntity() {
    }

    public WebsiteUsersEntity(Integer userId) {
        this.userId = userId;
    }

    public WebsiteUsersEntity(Integer userId, String loginName, String loginPassword, String nombreCompleto, Date fechaNacimiento) {
        this.userId = userId;
        this.loginName = loginName;
        this.loginPassword = loginPassword;
        this.nombreCompleto = nombreCompleto;
        this.fechaNacimiento = fechaNacimiento;
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

    public Date getObjVersion() {
        return objVersion;
    }

    public void setObjVersion(Date objVersion) {
        this.objVersion = objVersion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Set<AuditaEstadosCircuitosEntity> getAuditaEstadosCircuitosCollection() {
        return auditaEstadosCircuitosCollection;
    }

    public void setAuditaEstadosCircuitosCollection(Set<AuditaEstadosCircuitosEntity> auditaEstadosCircuitosCollection) {
        this.auditaEstadosCircuitosCollection = auditaEstadosCircuitosCollection;
    }

    public Set<PostsEntity> getPostsCollection() {
        return postsCollection;
    }

    public void setPostsCollection(Set<PostsEntity> postsCollection) {
        this.postsCollection = postsCollection;
    }

    public Set<UsuarioPreferenciasEntity> getUsuarioPreferenciasCollection() {
        return usuarioPreferenciasCollection;
    }

    public void setUsuarioPreferenciasCollection(Set<UsuarioPreferenciasEntity> usuarioPreferenciasCollection) {
        this.usuarioPreferenciasCollection = usuarioPreferenciasCollection;
    }

    public Set<UsuarioPreferenciasEntity> getUsuarioPreferenciasCollection1() {
        return usuarioPreferenciasCollection1;
    }

    public void setUsuarioPreferenciasCollection1(Set<UsuarioPreferenciasEntity> usuarioPreferenciasCollection1) {
        this.usuarioPreferenciasCollection1 = usuarioPreferenciasCollection1;
    }

    public Set<RolesPerWebsiteUsersEntity> getRolesPerWebsiteUsersCollection() {
        return rolesPerWebsiteUsersCollection;
    }

    public void setRolesPerWebsiteUsersCollection(Set<RolesPerWebsiteUsersEntity> rolesPerWebsiteUsersCollection) {
        this.rolesPerWebsiteUsersCollection = rolesPerWebsiteUsersCollection;
    }

    public Set<TracksEntity> getTracksCollection() {
        return tracksCollection;
    }

    public void setTracksCollection(Set<TracksEntity> tracksCollection) {
        this.tracksCollection = tracksCollection;
    }

    public Set<AccesoMenuEntity> getAccesoMenuCollection() {
        return accesoMenuCollection;
    }

    public void setAccesoMenuCollection(Set<AccesoMenuEntity> accesoMenuCollection) {
        this.accesoMenuCollection = accesoMenuCollection;
    }

    public Set<AccesoMenuEntity> getAccesoMenuCollection1() {
        return accesoMenuCollection1;
    }

    public void setAccesoMenuCollection1(Set<AccesoMenuEntity> accesoMenuCollection1) {
        this.accesoMenuCollection1 = accesoMenuCollection1;
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
        if (!(object instanceof WebsiteUsersEntity)) {
            return false;
        }
        WebsiteUsersEntity other = (WebsiteUsersEntity) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.WebsiteUsers[userId=" + userId + "]";
    }

    @Override
    public Object getPK() {
        return userId;
    }

}