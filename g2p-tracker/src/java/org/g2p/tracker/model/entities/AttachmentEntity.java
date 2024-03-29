package org.g2p.tracker.model.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "attachment")
@NamedQueries({@NamedQuery(name = "AttachmentEntity.findAll", query = "SELECT a FROM AttachmentEntity a")})
public class AttachmentEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AttachmentEntityPK attachmentEntityPK;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private WebsiteUsersEntity usuario;
    @JoinColumn(name = "track_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TracksEntity track;
    @JoinColumn(name = "documento_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DocumentosEntity documentEntity;

    public AttachmentEntity() {
    }

    public AttachmentEntity(AttachmentEntityPK attachmentEntityPK) {
        this.attachmentEntityPK = attachmentEntityPK;
    }

    public AttachmentEntity(AttachmentEntityPK attachmentEntityPK, Date fecha) {
        this.attachmentEntityPK = attachmentEntityPK;
        this.fecha = fecha;
    }

    public AttachmentEntity(int documentoId, int trackId) {
        this.attachmentEntityPK = new AttachmentEntityPK(documentoId, trackId);
    }

    public AttachmentEntityPK getAttachmentEntityPK() {
        return attachmentEntityPK;
    }

    public void setAttachmentEntityPK(AttachmentEntityPK attachmentEntityPK) {
        this.attachmentEntityPK = attachmentEntityPK;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public WebsiteUsersEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(WebsiteUsersEntity userId) {
        this.usuario = userId;
    }

    public TracksEntity getTrack() {
        return track;
    }

    public void setTrack(TracksEntity track) {
        this.track = track;
    }

    public DocumentosEntity getDocumentEntity() {
        return documentEntity;
    }

    public void setDocumentEntity(DocumentosEntity documentEntity) {
        this.documentEntity = documentEntity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (attachmentEntityPK != null ? attachmentEntityPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AttachmentEntity)) {
            return false;
        }
        AttachmentEntity other = (AttachmentEntity) object;
        if ((this.attachmentEntityPK == null && other.attachmentEntityPK != null) || (this.attachmentEntityPK != null && !this.attachmentEntityPK.equals(other.attachmentEntityPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.AttachmentEntity[attachmentEntityPK=" + attachmentEntityPK + "]";
    }

    @Override
    public Object getPK() {
        return getAttachmentEntityPK();
    }
}
