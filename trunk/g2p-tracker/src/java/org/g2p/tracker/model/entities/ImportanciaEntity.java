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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author nacho
 */
@Entity
@Table(name = "importancia")
@NamedQueries({@NamedQuery(name = "ImportanciaEntity.findAll", query = "SELECT i FROM ImportanciaEntity i")})
public class ImportanciaEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "importancia_id")
    private Integer importanciaId;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "peso")
    private Integer peso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "importanciaId", fetch = FetchType.LAZY)
    private Set<TracksEntity> tracksEntityCollection;

    public ImportanciaEntity() {
    }

    public ImportanciaEntity(Integer importanciaId) {
        this.importanciaId = importanciaId;
    }

    public ImportanciaEntity(Integer importanciaId, String nombre) {
        this.importanciaId = importanciaId;
        this.nombre = nombre;
    }

    public Integer getImportanciaId() {
        return importanciaId;
    }

    public void setImportanciaId(Integer importanciaId) {
        this.importanciaId = importanciaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<TracksEntity> getTracksEntityCollection() {
        return tracksEntityCollection;
    }

    public void setTracksEntityCollection(Set<TracksEntity> tracksEntityCollection) {
        this.tracksEntityCollection = tracksEntityCollection;
    }


    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (importanciaId != null ? importanciaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ImportanciaEntity)) {
            return false;
        }
        ImportanciaEntity other = (ImportanciaEntity) object;
        if ((this.importanciaId == null && other.importanciaId != null) || (this.importanciaId != null && !this.importanciaId.equals(other.importanciaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.ImportanciaEntity[importanciaId=" + importanciaId + "]";
    }

    @Override
    public Object getPK() {
        return getImportanciaId();
    }

}
