package org.g2p.tracker.model.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author nacho
 */
@Embeddable
public class TagsPerTracksEntityPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "tag_id")
    private int tagId;
    @Basic(optional = false)
    @Column(name = "track_id")
    private int trackId;

    public TagsPerTracksEntityPK() {
    }

    public TagsPerTracksEntityPK(int tagId, int trackId) {
        this.tagId = tagId;
        this.trackId = trackId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
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
        hash += (int) tagId;
        hash += (int) trackId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TagsPerTracksEntityPK)) {
            return false;
        }
        TagsPerTracksEntityPK other = (TagsPerTracksEntityPK) object;
        if (this.tagId != other.tagId) {
            return false;
        }
        if (this.trackId != other.trackId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.TagsPerTracksPK[tagId=" + tagId + ", trackId=" + trackId + "]";
    }

}
