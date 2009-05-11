/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author nacho
 */
@Entity
@Table(name = "attachment")
@NamedQueries({@NamedQuery(name = "AttachmentEntity.findAll", query = "SELECT a FROM AttachmentEntity a")})
public class AttachmentEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AttachmentEntityPK attachmentPK;

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

}
