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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "website_users", uniqueConstraints = {@UniqueConstraint(columnNames = {"login_name"}), @UniqueConstraint(columnNames = {"nombre", "fecha_nacimiento", "apellido"})})
@NamedQueries({
    @NamedQuery(name = "WebsiteUsersEntity.findByUserId", query = "SELECT w FROM WebsiteUsersEntity w WHERE w.userId = :userId"),
    @NamedQuery(name = "WebsiteUsersEntity.findAll", query = "SELECT w FROM WebsiteUsersEntity w"),
    @NamedQuery(name = "WebsiteUsersEntity.findByClaimedId", query = "SELECT w FROM WebsiteUsersEntity w WHERE w.userId = (SELECT wu.websiteUsersPerProveedoresOpenidEntityPK.userId FROM WebsiteUsersPerProveedoresOpenidEntity wu WHERE wu.claimedId LIKE :claimedId AND wu.fechaAsociacion IS NOT NULL)"),
    @NamedQuery(name = "WebsiteUsersEntity.findByNameAndBirthday", query = "SELECT w FROM WebsiteUsersEntity w WHERE CONCAT(w.nombre,w.apellido,w.fechaNacimiento) = :concat"),
    @NamedQuery(name = "WebsiteUsersEntity.findByLogin", query = "SELECT w FROM WebsiteUsersEntity w WHERE w.loginName = :loginName AND w.loginPassword = :loginPassword")
})
public class WebsiteUsersEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @Basic(optional = false)
    @Column(name = "login_name", nullable = false, length = 60)
    private String loginName;
    @Column(name = "login_password", length = 60)
    private String loginPassword;
    @Column(name = "nivel_visibilidad", length = 255)
    private String nivelVisibilidad;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 90)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "apellido", nullable = false, length = 90)
    private String apellido;
    @Column(name = "email", length = 90)
    private String email;
    @Column(name = "nro_legajo")
    private Integer nroLegajo;
    @Column(name = "nro_comprador")
    private Integer nroComprador;
    @Column(name = "OBJ_VERSION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date objVersion;
    @Basic(optional = false)
    @Column(name = "fecha_nacimiento", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;
    @JoinTable(name = "workers_per_tracks",
        joinColumns = {@JoinColumn(name = "user_id")},
        inverseJoinColumns = {@JoinColumn(name = "track_id")})
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<TracksEntity> tracksEntityCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId", fetch = FetchType.EAGER)
    private Set<AuditaEstadosCircuitosEntity> auditaEstadosCircuitosEntityCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userIdOwner", fetch = FetchType.EAGER)
    private Set<TracksEntity> tracksOwnedEntityCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId", fetch = FetchType.EAGER)
    private Set<PostsEntity> postsEntityCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "websiteUsers", fetch = FetchType.EAGER)
    private Set<UsuarioPreferenciasEntity> usuarioPreferenciasEntityCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "websiteUsers", fetch = FetchType.EAGER)
    private Set<RolesPerWebsiteUsersEntity> rolesPerWebsiteUsersEntityCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.EAGER)
    private Set<AttachmentEntity> attachmentEntityCollection;
    @OneToMany(mappedBy = "userId", fetch = FetchType.EAGER)
    private Set<AccesoMenuEntity> accesoMenuEntityCollection;

    public WebsiteUsersEntity() {
    }

    public WebsiteUsersEntity(Integer userId) {
        this.userId = userId;
    }

    public WebsiteUsersEntity(Integer userId, String loginName, String nombre, String apellido, Date fechaNacimiento) {
        this.userId = userId;
        this.loginName = loginName;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getApellidoNombre() {
        return getNombre() + " " +getApellido();
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    public Set<TracksEntity> getTracksEntityCollection() {
        return tracksEntityCollection;
    }

    public void setTracksEntityCollection(Set<TracksEntity> tracksEntityCollection) {
        this.tracksEntityCollection = tracksEntityCollection;
    }

    public Set<AuditaEstadosCircuitosEntity> getAuditaEstadosCircuitosEntityCollection() {
        return auditaEstadosCircuitosEntityCollection;
    }

    public void setAuditaEstadosCircuitosEntityCollection(Set<AuditaEstadosCircuitosEntity> auditaEstadosCircuitosEntityCollection) {
        this.auditaEstadosCircuitosEntityCollection = auditaEstadosCircuitosEntityCollection;
    }

    public Set<PostsEntity> getPostsEntityCollection() {
        return postsEntityCollection;
    }

    public void setPostsEntityCollection(Set<PostsEntity> postsEntityCollection) {
        this.postsEntityCollection = postsEntityCollection;
    }

    public Set<UsuarioPreferenciasEntity> getUsuarioPreferenciasEntityCollection() {
        return usuarioPreferenciasEntityCollection;
    }

    public void setUsuarioPreferenciasEntityCollection(Set<UsuarioPreferenciasEntity> usuarioPreferenciasEntityCollection) {
        this.usuarioPreferenciasEntityCollection = usuarioPreferenciasEntityCollection;
    }

    public Set<RolesPerWebsiteUsersEntity> getRolesPerWebsiteUsersEntityCollection() {
        return rolesPerWebsiteUsersEntityCollection;
    }

    public void setRolesPerWebsiteUsersEntityCollection(Set<RolesPerWebsiteUsersEntity> rolesPerWebsiteUsersEntityCollection) {
        this.rolesPerWebsiteUsersEntityCollection = rolesPerWebsiteUsersEntityCollection;
    }

    public Set<AttachmentEntity> getAttachmentEntityCollection() {
        return attachmentEntityCollection;
    }

    public void setAttachmentEntityCollection(Set<AttachmentEntity> attachmentEntityCollection) {
        this.attachmentEntityCollection = attachmentEntityCollection;
    }

    public Set<AccesoMenuEntity> getAccesoMenuEntityCollection() {
        return accesoMenuEntityCollection;
    }

    public void setAccesoMenuEntityCollection(Set<AccesoMenuEntity> accesoMenuEntityCollection) {
        this.accesoMenuEntityCollection = accesoMenuEntityCollection;
    }

    public Set<TracksEntity> getTracksOwnedEntityCollection() {
        return tracksOwnedEntityCollection;
    }

    public void setTracksOwnedEntityCollection(Set<TracksEntity> tracksOwnedEntityCollection) {
        this.tracksOwnedEntityCollection = tracksOwnedEntityCollection;
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
        return "org.g2p.tracker.model.entities.WebsiteUsersEntity[userId=" + userId + "]";
    }

    @Override
    public Object getPK() {
        return getUserId();
    }
}
