/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.model.entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author nacho
 */
@Entity
@Table(name = "workers_per_tracks")
@NamedQueries({@NamedQuery(name = "WorkersPerTracksEntity.findAll", query = "SELECT w FROM WorkersPerTracksEntity w")})
public class WorkersPerTracksEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected WorkersPerTracksEntityPK workersPerTracksPK;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "user_id")
    private WebsiteUsersEntity worker;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "track_id", referencedColumnName = "track_id")
    private TracksEntity track;

    public TracksEntity getTrack() {
        return track;
    }

    public void setTrack(TracksEntity track) {
        this.track = track;
    }

    public WebsiteUsersEntity getWorker() {
        return worker;
    }

    public void setWorker(WebsiteUsersEntity worker) {
        this.worker = worker;
    }

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
