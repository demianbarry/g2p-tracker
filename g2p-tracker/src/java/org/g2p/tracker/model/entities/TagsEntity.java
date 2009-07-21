package org.g2p.tracker.model.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "tags")
@NamedQueries({
    @NamedQuery(name = "TagsEntity.findAll", query = "SELECT t FROM TagsEntity t ORDER BY t.tagIdGrupo"),
    @NamedQuery(name = "TagsEntity.findRootTags", query = "SELECT t FROM TagsEntity t WHERE t.tagIdGrupo IS NULL"),
    @NamedQuery(name = "TagsEntity.findByTagId", query = "SELECT t FROM TagsEntity t WHERE t.tagId = :tagId"),
    @NamedQuery(name = "TagsEntity.findByFather", query = "SELECT t FROM TagsEntity t WHERE t.tagIdGrupo.tagId = :tagId"),
    @NamedQuery(name = "TagsEntity.findByCriteria", query = "SELECT t FROM TagsEntity t WHERE t.tag LIKE :criteria OR t.descripcion LIKE :criteria"),
    @NamedQuery(name = "TagsEntity.findByTag", query = "SELECT t FROM TagsEntity t WHERE t.tag = :tag"),
    @NamedQuery(name = "TagsEntity.findByTrack", query = "SELECT t FROM TagsEntity t WHERE t.tagId IN (SELECT tpt.tagsPerTracksPK.tagId FROM TagsPerTracksEntity tpt WHERE tpt.tagsPerTracksPK.trackId = :trackId)")
})
public class TagsEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tag_id")
    private Integer tagId;
    @Basic(optional = false)
    @Column(name = "tag")
    private String tag;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "observaciones")
    private String observaciones;
    @OneToMany(mappedBy = "tagIdGrupo", fetch = FetchType.LAZY)
    private Set<TagsEntity> tagsEntityCollection;
    @JoinColumn(name = "tag_id_grupo", referencedColumnName = "tag_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TagsEntity tagIdGrupo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tag", fetch = FetchType.EAGER)
    private Set<TagsPerTracksEntity> tracksPerTagCollection;

    public Set<TagsPerTracksEntity> getTracksPerWorkerCollection() {
        return tracksPerTagCollection;
    }

    public void setTracksPerWorkerCollection(Set<TagsPerTracksEntity> tracksPerTagCollection) {
        this.tracksPerTagCollection = tracksPerTagCollection;
    }

    public TagsEntity() {
    }

    public TagsEntity(Integer tagId) {
        this.tagId = tagId;
    }

    public TagsEntity(Integer tagId, String tag) {
        this.tagId = tagId;
        this.tag = tag;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Set<TagsEntity> getTagsEntityCollection() {
        return tagsEntityCollection;
    }

    public void setTagsEntityCollection(Set<TagsEntity> tagsEntityCollection) {
        this.tagsEntityCollection = tagsEntityCollection;
    }

    public TagsEntity getTagIdGrupo() {
        return tagIdGrupo;
    }

    public void setTagIdGrupo(TagsEntity tagIdGrupo) {
        this.tagIdGrupo = tagIdGrupo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tagId != null ? tagId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TagsEntity)) {
            return false;
        }
        TagsEntity other = (TagsEntity) object;
        if ((this.tagId == null && other.tagId != null) || (this.tagId != null && !this.tagId.equals(other.tagId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.TagsEntity[tagId=" + tagId + "]";
    }

    @Override
    public Object getPK() {
        return getTagId();
    }
}
