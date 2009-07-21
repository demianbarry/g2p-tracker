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
@Table(name = "prioridades")
@NamedQueries({@NamedQuery(name = "PrioridadesEntity.findAll", query = "SELECT p FROM PrioridadesEntity p")})
public class PrioridadesEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "prioridad_id")
    private Integer prioridadId;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "peso")
    private Integer peso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prioridadId", fetch = FetchType.EAGER)
    private Set<TracksEntity> tracksEntityCollection;

    public PrioridadesEntity() {
    }

    public PrioridadesEntity(Integer prioridadId) {
        this.prioridadId = prioridadId;
    }

    public PrioridadesEntity(Integer prioridadId, String nombre) {
        this.prioridadId = prioridadId;
        this.nombre = nombre;
    }

    public Integer getPrioridadId() {
        return prioridadId;
    }

    public void setPrioridadId(Integer prioridadId) {
        this.prioridadId = prioridadId;
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
        hash += (prioridadId != null ? prioridadId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrioridadesEntity)) {
            return false;
        }
        PrioridadesEntity other = (PrioridadesEntity) object;
        if ((this.prioridadId == null && other.prioridadId != null) || (this.prioridadId != null && !this.prioridadId.equals(other.prioridadId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.PrioridadesEntity[prioridadId=" + prioridadId + "]";
    }

    @Override
    public Object getPK() {
        return getPrioridadId();
    }

}
