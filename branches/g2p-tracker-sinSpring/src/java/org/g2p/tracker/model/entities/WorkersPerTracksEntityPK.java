/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class WorkersPerTracksEntityPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "track_id")
    private int trackId;
    @Basic(optional = false)
    @Column(name = "user_id")
    private int userId;

    public WorkersPerTracksEntityPK() {
    }

    public WorkersPerTracksEntityPK(int trackId, int userId) {
        this.trackId = trackId;
        this.userId = userId;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) trackId;
        hash += (int) userId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WorkersPerTracksEntityPK)) {
            return false;
        }
        WorkersPerTracksEntityPK other = (WorkersPerTracksEntityPK) object;
        if (this.trackId != other.trackId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.WorkersPerTracksPK[trackId=" + trackId + ", userId=" + userId + "]";
    }

}
