package org.g2p.tracker.model.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Administrador
 */
@Embeddable
public class AttachmentEntityPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "documento_id")
    private int documentoId;
    @Basic(optional = false)
    @Column(name = "track_id")
    private int trackId;

    public AttachmentEntityPK() {
    }

    public AttachmentEntityPK(int documentoId, int trackId) {
        this.documentoId = documentoId;
        this.trackId = trackId;
    }

    public int getDocumentoId() {
        return documentoId;
    }

    public void setDocumentoId(int documentoId) {
        this.documentoId = documentoId;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) documentoId;
        hash += (int) trackId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AttachmentEntityPK)) {
            return false;
        }
        AttachmentEntityPK other = (AttachmentEntityPK) object;
        if (this.documentoId != other.documentoId) {
            return false;
        }
        if (this.trackId != other.trackId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.AttachmentEntityPK[documentoId=" + documentoId + ", trackId=" + trackId + "]";
    }

}
