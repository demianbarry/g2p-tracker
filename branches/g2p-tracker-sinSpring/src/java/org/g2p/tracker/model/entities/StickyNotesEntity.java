/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author nacho
 */
@Entity
@Table(name = "sticky_notes")
@NamedQueries({@NamedQuery(name = "StickyNotes.findAll", query = "SELECT s FROM StickyNotes s")})
public class StickyNotesEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "sticky_note_id")
    private Integer stickyNoteId;
    @Basic(optional = false)
    @Column(name = "titulo")
    private String titulo;
    @Lob
    @Column(name = "contenido")
    private String contenido;
    @Basic(optional = false)
    @Column(name = "pegado")
    private boolean pegado;
    @JoinColumn(name = "track_id", referencedColumnName = "track_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TracksEntity trackId;

    public StickyNotesEntity() {
    }

    public StickyNotesEntity(Integer stickyNoteId) {
        this.stickyNoteId = stickyNoteId;
    }

    public StickyNotesEntity(Integer stickyNoteId, String titulo, boolean pegado) {
        this.stickyNoteId = stickyNoteId;
        this.titulo = titulo;
        this.pegado = pegado;
    }

    public Integer getStickyNoteId() {
        return stickyNoteId;
    }

    public void setStickyNoteId(Integer stickyNoteId) {
        this.stickyNoteId = stickyNoteId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public boolean getPegado() {
        return pegado;
    }

    public void setPegado(boolean pegado) {
        this.pegado = pegado;
    }

    public TracksEntity getTrackId() {
        return trackId;
    }

    public void setTrackId(TracksEntity trackId) {
        this.trackId = trackId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stickyNoteId != null ? stickyNoteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StickyNotesEntity)) {
            return false;
        }
        StickyNotesEntity other = (StickyNotesEntity) object;
        if ((this.stickyNoteId == null && other.stickyNoteId != null) || (this.stickyNoteId != null && !this.stickyNoteId.equals(other.stickyNoteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.StickyNotes[stickyNoteId=" + stickyNoteId + "]";
    }

    @Override
    public Object getPK() {
        return stickyNoteId;
    }

}
