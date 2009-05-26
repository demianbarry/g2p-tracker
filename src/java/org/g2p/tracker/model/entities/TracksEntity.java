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
import javax.persistence.ManyToOne;
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
@Table(name = "tracks")
@NamedQueries({@NamedQuery(name = "TracksEntity.findAll", query = "SELECT t FROM TracksEntity t")})
public class TracksEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "track_id")
    private Integer trackId;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "observaciones")
    private String observaciones;
    @Basic(optional = false)
    @Column(name = "importancia")
    private int importancia;
    @Basic(optional = false)
    @Column(name = "prioridad")
    private int prioridad;
    @Basic(optional = false)
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "fecha_estimada_realizacion")
    @Temporal(TemporalType.DATE)
    private Date fechaEstimadaRealizacion;
    @Column(name = "deadline")
    @Temporal(TemporalType.DATE)
    private Date deadline;
    @Column(name = "fecha_realizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRealizacion;
    @OneToMany(mappedBy = "trackId", fetch = FetchType.EAGER)
    private Set<StickyNotesEntity> stickyNotesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trackId", fetch = FetchType.EAGER)
    private Set<PostsEntity> postsCollection;
    @JoinColumn(name = "estado_id", referencedColumnName = "estado_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private EstadosEntity estadoId;
    @JoinColumn(name = "user_id_owner", referencedColumnName = "user_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private WebsiteUsersEntity userIdOwner;
    @Basic(optional = false)
    @Column(name = "titulo")
    private String titulo;

    public TracksEntity() {
    }

    public TracksEntity(Integer trackId) {
        this.trackId = trackId;
    }

    public TracksEntity(Integer trackId, String descripcion, int importancia, int prioridad, Date fechaCreacion, String titulo) {
        this.trackId = trackId;
        this.descripcion = descripcion;
        this.importancia = importancia;
        this.prioridad = prioridad;
        this.fechaCreacion = fechaCreacion;
        this.titulo = titulo;
    }

    public Integer getTrackId() {
        return trackId;
    }

    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getImportancia() {
        return importancia;
    }

    public void setImportancia(int importancia) {
        this.importancia = importancia;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaEstimadaRealizacion() {
        return fechaEstimadaRealizacion;
    }

    public void setFechaEstimadaRealizacion(Date fechaEstimadaRealizacion) {
        this.fechaEstimadaRealizacion = fechaEstimadaRealizacion;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(Date fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public Set<StickyNotesEntity> getStickyNotesCollection() {
        return stickyNotesCollection;
    }

    public void setStickyNotesCollection(Set<StickyNotesEntity> stickyNotesCollection) {
        this.stickyNotesCollection = stickyNotesCollection;
    }

    public Set<PostsEntity> getPostsCollection() {
        return postsCollection;
    }

    public void setPostsCollection(Set<PostsEntity> postsCollection) {
        this.postsCollection = postsCollection;
    }

    public EstadosEntity getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(EstadosEntity estadoId) {
        this.estadoId = estadoId;
    }

    public WebsiteUsersEntity getUserIdOwner() {
        return userIdOwner;
    }

    public void setUserIdOwner(WebsiteUsersEntity userIdOwner) {
        this.userIdOwner = userIdOwner;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (trackId != null ? trackId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TracksEntity)) {
            return false;
        }
        TracksEntity other = (TracksEntity) object;
        if ((this.trackId == null && other.trackId != null) || (this.trackId != null && !this.trackId.equals(other.trackId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.Tracks[trackId=" + trackId + "]";
    }

    @Override
    public Object getPK() {
        return trackId;
    }

}
