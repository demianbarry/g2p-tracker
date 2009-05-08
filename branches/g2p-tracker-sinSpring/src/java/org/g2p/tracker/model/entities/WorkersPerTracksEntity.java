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
@Table(name = "workers_per_tracks")
@NamedQueries({@NamedQuery(name = "WorkersPerTracks.findAll", query = "SELECT w FROM WorkersPerTracks w")})
public class WorkersPerTracksEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected WorkersPerTracksEntityPK workersPerTracksPK;

    public WorkersPerTracksEntity() {
    }

    public WorkersPerTracksEntity(WorkersPerTracksEntityPK workersPerTracksPK) {
        this.workersPerTracksPK = workersPerTracksPK;
    }

    public WorkersPerTracksEntity(int trackId, int userId) {
        this.workersPerTracksPK = new WorkersPerTracksEntityPK(trackId, userId);
    }

    public WorkersPerTracksEntityPK getWorkersPerTracksPK() {
        return workersPerTracksPK;
    }

    public void setWorkersPerTracksPK(WorkersPerTracksEntityPK workersPerTracksPK) {
        this.workersPerTracksPK = workersPerTracksPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (workersPerTracksPK != null ? workersPerTracksPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WorkersPerTracksEntity)) {
            return false;
        }
        WorkersPerTracksEntity other = (WorkersPerTracksEntity) object;
        if ((this.workersPerTracksPK == null && other.workersPerTracksPK != null) || (this.workersPerTracksPK != null && !this.workersPerTracksPK.equals(other.workersPerTracksPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.WorkersPerTracks[workersPerTracksPK=" + workersPerTracksPK + "]";
    }

    @Override
    public Object getPK() {
        return workersPerTracksPK;
    }

}
