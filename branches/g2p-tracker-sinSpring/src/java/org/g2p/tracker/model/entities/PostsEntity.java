/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "posts")
@NamedQueries({
    @NamedQuery(name = "PostsEntity.findAll", query = "SELECT p FROM PostsEntity p"),
    @NamedQuery(name = "PostsEntity.findByTrack", query = "SELECT p FROM PostsEntity p WHERE (p.trackId.titulo = :titulo) ORDER BY fechaCreacion :direccion")
})
public class PostsEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "post_id")
    private Integer postId;
    @Basic(optional = false)
    @Lob
    @Column(name = "contenido")
    private String contenido;
    @Basic(optional = false)
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private WebsiteUsersEntity userId;
    @JoinColumn(name = "track_id", referencedColumnName = "track_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private TracksEntity trackId;

    public PostsEntity() {
    }

    public PostsEntity(Integer postId) {
        this.postId = postId;
    }

    public PostsEntity(Integer postId, String contenido, Date fechaCreacion) {
        this.postId = postId;
        this.contenido = contenido;
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public WebsiteUsersEntity getUserId() {
        return userId;
    }

    public void setUserId(WebsiteUsersEntity userId) {
        this.userId = userId;
    }

    public TracksEntity getTrackId() {
        return trackId;
    }

    public void setTrackId(TracksEntity trackId) {
        this.trackId = trackId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (postId != null ? postId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PostsEntity)) {
            return false;
        }
        PostsEntity other = (PostsEntity) object;
        if ((this.postId == null && other.postId != null) || (this.postId != null && !this.postId.equals(other.postId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.Posts[postId=" + postId + "]";
    }

    @Override
    public Object getPK() {
        return postId;
    }

}
