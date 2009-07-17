/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.g2p.tracker.utils.Fecha;

/**
 *
 * @author g2p
 */
@Entity
@Table(name = "tracks")
@NamedQueries({
    @NamedQuery(name = "TracksEntity.findAll", query = "SELECT t FROM TracksEntity t ORDER BY dummy"),
    @NamedQuery(name = "TracksEntity.findByTitulo", query = "SELECT t FROM TracksEntity t WHERE titulo = :titulo ORDER BY dummy"),
    @NamedQuery(name = "TracksEntity.findByUser", query = "SELECT t FROM TracksEntity t WHERE t.userIdOwner = :user OR :user MEMBER OF t.websiteUsersEntityCollection ORDER BY dummy")
})
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
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @Column(name = "complejidad")
    private Double complejidad;
    @Basic(optional = false)
    @Column(name = "orden")
    private String orden;
    @JoinTable(name = "workers_per_tracks", joinColumns = {@JoinColumn(name = "track_id", referencedColumnName = "track_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")})
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<WebsiteUsersEntity> websiteUsersEntityCollection;
    @OneToMany(mappedBy = "trackId", fetch = FetchType.EAGER)
    private Set<StickyNotesEntity> stickyNotesEntityCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trackId", fetch = FetchType.EAGER)
    private Set<PostsEntity> postsEntityCollection;
    @JoinColumn(name = "importancia_id", referencedColumnName = "importancia_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private ImportanciaEntity importanciaId;
    @JoinColumn(name = "estado_id", referencedColumnName = "estado_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private EstadosEntity estadoId;
    @JoinColumn(name = "prioridad_id", referencedColumnName = "prioridad_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private PrioridadesEntity prioridadId;
    @JoinColumn(name = "user_id_owner", referencedColumnName = "user_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private WebsiteUsersEntity userIdOwner;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "track", fetch = FetchType.EAGER)
    private Set<AttachmentEntity> attachmentEntityCollection;

    public TracksEntity() {
    }

    public TracksEntity(Integer trackId) {
        this.trackId = trackId;
    }

    public TracksEntity(Integer trackId, String descripcion, Date fechaCreacion) {
        this.trackId = trackId;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public Set<WebsiteUsersEntity> getWebsiteUsersEntityCollection() {
        return websiteUsersEntityCollection;
    }

    public void setWebsiteUsersEntityCollection(Set<WebsiteUsersEntity> websiteUsersEntityCollection) {
        this.websiteUsersEntityCollection = websiteUsersEntityCollection;
    }

    public Set<StickyNotesEntity> getStickyNotesEntityCollection() {
        //return new TreeSet(new ArrayList(stickyNotesEntityCollection));
        return stickyNotesEntityCollection;
    }

    public void setStickyNotesEntityCollection(Set<StickyNotesEntity> stickyNotesEntityCollection) {
        this.stickyNotesEntityCollection = stickyNotesEntityCollection;
    }

    public Set<PostsEntity> getPostsEntityCollection() {
        return postsEntityCollection;
    }

    public void setPostsEntityCollection(Set<PostsEntity> postsEntityCollection) {
        this.postsEntityCollection = postsEntityCollection;
    }

    public ImportanciaEntity getImportanciaId() {
        return importanciaId;
    }

    public void setImportanciaId(ImportanciaEntity importanciaId) {
        this.importanciaId = importanciaId;
    }

    public EstadosEntity getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(EstadosEntity estadoId) {
        this.estadoId = estadoId;
    }

    public PrioridadesEntity getPrioridadId() {
        return prioridadId;
    }

    public Double getComplejidad() {
        return complejidad;
    }

    public void setComplejidad(Double complejidad) {
        this.complejidad = complejidad;
    }

    public void setPrioridadId(PrioridadesEntity prioridadId) {
        this.prioridadId = prioridadId;
    }

    public WebsiteUsersEntity getUserIdOwner() {
        return userIdOwner;
    }

    public void setUserIdOwner(WebsiteUsersEntity userIdOwner) {
        this.userIdOwner = userIdOwner;
    }

    public Set<AttachmentEntity> getAttachmentEntityCollection() {
        return attachmentEntityCollection;
    }

    public void setAttachmentEntityCollection(Set<AttachmentEntity> attachmentEntityCollection) {
        this.attachmentEntityCollection = attachmentEntityCollection;
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
        if (getTrackId() == null || !getTrackId().equals(other.getTrackId())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.TracksEntity[trackId=" + trackId + "]";
    }

    @Override
    public Object getPK() {
        return getTrackId();
    }

    public void addWorker(WebsiteUsersEntity worker) {
        if (getWebsiteUsersEntityCollection() == null) {
            setWebsiteUsersEntityCollection(new HashSet());
        }
        getWebsiteUsersEntityCollection().add(worker);
        worker.getTracksEntityCollection().add(this);
    }

    public void removeWorker(WebsiteUsersEntity worker) {
        if (getWebsiteUsersEntityCollection() == null) {
            setWebsiteUsersEntityCollection(new HashSet());
        }
        getWebsiteUsersEntityCollection().remove(worker);
        worker.getTracksEntityCollection().remove(this);
    }

    public void addPost(PostsEntity post) {
        post.setTrackId(this);
        getPostsEntityCollection().add(post);
    }

    public Double getDummy() {
        final int EDAD_INFERIOR = 10;
        final int EDAD_SUPERIOR = 20;
        final int LIMITE_INFERIOR = -5;
        final int LIMITE_MEDIO = -2;
        final int LIMITE_SUPERIOR = 0;

            Fecha fechaActual = new Fecha();

            long edad = fechaActual.getDiff(fechaCreacion, Fecha.DIAS);
            long limite = fechaActual.getDiff(deadline, Fecha.DIAS);
            int factorAntiguedad = 1;
            float factorLimiteFecha = (float) 1.0;

            if (edad > EDAD_INFERIOR && edad <= EDAD_SUPERIOR){
                factorAntiguedad = 3;
            }
            else {
                if (edad > EDAD_SUPERIOR){
                    factorAntiguedad = 5;
                }
            }

            if (limite >= LIMITE_MEDIO && limite < LIMITE_SUPERIOR){
                factorLimiteFecha = (float) 0.75;
            }
            else {
                if (limite >= LIMITE_INFERIOR && limite < LIMITE_MEDIO){
                    factorLimiteFecha = (float) 0.5;
                }
                else {
                    if (limite < LIMITE_INFERIOR){
                        factorLimiteFecha = (float) 0.25;
                    }
                }
            }
//
        return complejidad * prioridadId.getPeso() * importanciaId.getPeso() * factorLimiteFecha * factorAntiguedad;

    }
}
