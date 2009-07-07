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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author nacho
 */
@Entity
@Table(name = "attachment")
@NamedQueries({
    @NamedQuery(name = "AttachmentEntity.findAll", query = "SELECT a FROM AttachmentEntity a"),
    @NamedQuery(name = "AttachmentEntity.findAllByTrack", query = "SELECT a,d FROM AttachmentEntity a, DocumentosEntity d WHERE a.tracksEntity = :track AND d.idDocumento = a.attachmentPK.documentoId")
})
public class AttachmentEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AttachmentEntityPK attachmentPK;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private WebsiteUsersEntity usuario;
    @JoinColumn(name = "track_id", referencedColumnName = "track_id", insertable=false, updatable=false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private TracksEntity tracksEntity;
    @Column(name = "fecha")
    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @OneToOne(mappedBy = "idDocumento", fetch = FetchType.EAGER)
    private DocumentosEntity documentEntity;

    public DocumentosEntity getDocumentEntity() {
        return documentEntity;
    }

    public void setDocumentEntity(DocumentosEntity documentEntity) {
        this.documentEntity = documentEntity;
    }

    public AttachmentEntity() {
    }

    public AttachmentEntity(AttachmentEntityPK attachmentPK) {
        this.attachmentPK = attachmentPK;
    }

    public AttachmentEntity(int documentoId, int trackId) {
        this.attachmentPK = new AttachmentEntityPK(documentoId, trackId);
    }

    public AttachmentEntityPK getAttachmentPK() {
        return attachmentPK;
    }

    public void setAttachmentPK(AttachmentEntityPK attachmentPK) {
        this.attachmentPK = attachmentPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (attachmentPK != null ? attachmentPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AttachmentEntity)) {
            return false;
        }
        AttachmentEntity other = (AttachmentEntity) object;
        if ((this.attachmentPK == null && other.attachmentPK != null) || (this.attachmentPK != null && !this.attachmentPK.equals(other.attachmentPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.Attachment[attachmentPK=" + attachmentPK + "]";
    }

    @Override
    public Object getPK() {
        return attachmentPK;
    }

    public Date getFecha() {
        return fecha;
    }

    public WebsiteUsersEntity getUsuario() {
        return usuario;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setUsuario(WebsiteUsersEntity usuario) {
        this.usuario = usuario;
    }


    public TracksEntity getTracksEntity() {
        return tracksEntity;
    }

    public void setTracksEntity(TracksEntity tracksEntity) {
        this.tracksEntity = tracksEntity;
    }
}
