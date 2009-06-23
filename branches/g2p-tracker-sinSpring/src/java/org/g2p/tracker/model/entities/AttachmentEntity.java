/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
 * @author nacho
 */
@Entity
@Table(name = "attachment")
@NamedQueries({
    @NamedQuery(name = "AttachmentEntity.findAll", query = "SELECT a FROM AttachmentEntity a"),
    @NamedQuery(name = "AttachmentEntity.findAllByTrack", query = "SELECT a FROM AttachmentEntity a WHERE trackId = :track")
})
public class AttachmentEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AttachmentEntityPK attachmentPK;
    @JoinColumn(name = "prioridad_id", referencedColumnName = "prioridad_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private WebsiteUsersEntity usuario;

    @Column(name = "fecha")
    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

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

}
