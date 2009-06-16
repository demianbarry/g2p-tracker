/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.model.entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author nacho
 */
@Entity
@Table(name = "tags_per_tracks")
@NamedQueries({
    @NamedQuery(name = "TagsPerTracksEntity.findAll", query = "SELECT t FROM TagsPerTracksEntity t"),
    @NamedQuery(name = "TagsPerTracksEntity.findByTrack", query = "SELECT t FROM TagsPerTracksEntity t WHERE t.tagsPerTracksPK.trackId = :trackId"),
    @NamedQuery(name = "TagsPerTracksEntity.findByTag", query = "SELECT t FROM TagsPerTracksEntity t WHERE t.tagsPerTracksPK.tagId = :tagId")})
public class TagsPerTracksEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TagsPerTracksEntityPK tagsPerTracksPK;
    @JoinColumn(name = "track_id", referencedColumnName = "track_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TracksEntity track;
    @JoinColumn(name = "tag_id", referencedColumnName = "tag_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TagsEntity tag;

    public TagsEntity getTag() {
        return tag;
    }

    public void setTag(TagsEntity tag) {
        this.tag = tag;
    }

    public TracksEntity getTrack() {
        return track;
    }

    public void setTrack(TracksEntity track) {
        this.track = track;
    }

    public TagsPerTracksEntity() {
    }

    public TagsPerTracksEntity(TagsPerTracksEntityPK tagsPerTracksPK) {
        this.tagsPerTracksPK = tagsPerTracksPK;
    }

    public TagsPerTracksEntity(int tagId, int trackId) {
        this.tagsPerTracksPK = new TagsPerTracksEntityPK(tagId, trackId);
    }

    public TagsPerTracksEntityPK getTagsPerTracksPK() {
        return tagsPerTracksPK;
    }

    public void setTagsPerTracksPK(TagsPerTracksEntityPK tagsPerTracksPK) {
        this.tagsPerTracksPK = tagsPerTracksPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tagsPerTracksPK != null ? tagsPerTracksPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TagsPerTracksEntity)) {
            return false;
        }
        TagsPerTracksEntity other = (TagsPerTracksEntity) object;
        if ((this.tagsPerTracksPK == null && other.tagsPerTracksPK != null) || (this.tagsPerTracksPK != null && !this.tagsPerTracksPK.equals(other.tagsPerTracksPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.TagsPerTracks[tagsPerTracksPK=" + tagsPerTracksPK + "]";
    }

    @Override
    public Object getPK() {
        return tagsPerTracksPK;
    }
}
