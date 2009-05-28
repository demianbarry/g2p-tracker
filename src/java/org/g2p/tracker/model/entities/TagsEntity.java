/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.model.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
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
 * @author nacho
 */
@Entity
@Table(name = "tags")
@NamedQueries({
    @NamedQuery(name = "TagsEntity.findAll", query = "SELECT t FROM TagsEntity t ORDER BY t.tagIdGrupo"),
    @NamedQuery(name = "TagsEntity.findRootTags", query = "SELECT t FROM TagsEntity t WHERE t.tagIdGrupo IS NULL"),
    @NamedQuery(name = "TagsEntity.findByTagId", query = "SELECT t FROM TagsEntity t WHERE t.tagId = :tagId"),
    @NamedQuery(name = "TagsEntity.findByFather", query = "SELECT t FROM TagsEntity t WHERE t.tagIdGrupo.tagId = :tagId"),
    @NamedQuery(name = "TagsEntity.findByCriteria", query = "SELECT t FROM TagsEntity t WHERE t.tag LIKE :criteria OR t.descripcion LIKE :criteria")
})
public class TagsEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Integer tagId;
    @Basic(optional = false)
    @Column(name = "tag")
    private String tag;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "observaciones")
    private String observaciones;
    @OneToMany(mappedBy = "tagIdGrupo", fetch = FetchType.EAGER)
    private Set<TagsEntity> tagsCollection;
    @JoinColumn(name = "tag_id_grupo", referencedColumnName = "tag_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private TagsEntity tagIdGrupo;

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

    public Set<TagsEntity> getTagsCollection() {
        return tagsCollection;
    }

    public void setTagsCollection(Set<TagsEntity> tagsCollection) {
        this.tagsCollection = tagsCollection;
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
        return "org.g2p.tracker.model.entities.Tags[tagId=" + tagId + "]";
    }

    @Override
    public Object getPK() {
        return tagId;
    }
}
