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
@Table(name = "tags_per_tracks")
@NamedQueries({
    @NamedQuery(name = "TagsPerTracksEntity.findAll", query = "SELECT t FROM TagsPerTracksEntity t"),
    @NamedQuery(name = "TagsPerTracksEntity.findByTrack", query = "SELECT t FROM TagsPerTracksEntity t WHERE t.tagsPerTracksPK.trackId = :trackId")})
public class TagsPerTracksEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TagsPerTracksEntityPK tagsPerTracksPK;

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
