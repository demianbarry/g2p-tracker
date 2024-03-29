package org.g2p.tracker.model.entities;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author nacho
 */
@Entity
@Table(name = "clase_lov_atributo")
@NamedQueries({@NamedQuery(name = "ClaseLovAtributoEntity.findAll", query = "SELECT c FROM ClaseLovAtributoEntity c")})
public class ClaseLovAtributoEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "clase_lov_atributo_id")
    private Integer claseLovAtributoId;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "observaciones")
    private String observaciones;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "claseLovAtributo", fetch = FetchType.LAZY)
    private LovAtributoEntity lovAtributo;

    public ClaseLovAtributoEntity() {
    }

    public ClaseLovAtributoEntity(Integer claseLovAtributoId) {
        this.claseLovAtributoId = claseLovAtributoId;
    }

    public ClaseLovAtributoEntity(Integer claseLovAtributoId, String nombre) {
        this.claseLovAtributoId = claseLovAtributoId;
        this.nombre = nombre;
    }

    public Integer getClaseLovAtributoId() {
        return claseLovAtributoId;
    }

    public void setClaseLovAtributoId(Integer claseLovAtributoId) {
        this.claseLovAtributoId = claseLovAtributoId;
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public LovAtributoEntity getLovAtributo() {
        return lovAtributo;
    }

    public void setLovAtributo(LovAtributoEntity lovAtributo) {
        this.lovAtributo = lovAtributo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claseLovAtributoId != null ? claseLovAtributoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClaseLovAtributoEntity)) {
            return false;
        }
        ClaseLovAtributoEntity other = (ClaseLovAtributoEntity) object;
        if ((this.claseLovAtributoId == null && other.claseLovAtributoId != null) || (this.claseLovAtributoId != null && !this.claseLovAtributoId.equals(other.claseLovAtributoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.ClaseLovAtributo[claseLovAtributoId=" + claseLovAtributoId + "]";
    }

    @Override
    public Object getPK() {
        return claseLovAtributoId;
    }

}
