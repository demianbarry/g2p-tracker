package org.g2p.tracker.model.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author nacho
 */
@Entity
@Table(name = "proveedores_sso")
@NamedQueries({
    @NamedQuery(name = "ProveedoresSsoEntity.findAll", query = "SELECT p FROM ProveedoresSsoEntity p"),
    @NamedQuery(name = "ProveedoresSsoEntity.findByUserId", query = "SELECT p FROM ProveedoresSsoEntity p WHERE p.proveedorSsoId = (SELECT w.websiteUsersPerProveedoresOpenidEntityPK.proveedorSsoId FROM WebsiteUsersPerProveedoresOpenidEntity w WHERE w.websiteUsersPerProveedoresOpenidEntityPK.userId = :userId AND w.fechaAsociacion IS NOT NULL)")
})
public class ProveedoresSsoEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "proveedor_sso_id")
    private Integer proveedorSsoId;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "observaciones")
    private String observaciones;
    @Basic(optional = false)
    @Lob
    @Column(name = "url_discovery")
    private String urlDiscovery;
    @Lob
    @Column(name = "url_icono")
    private String urlIcono;

    public ProveedoresSsoEntity() {
    }

    public ProveedoresSsoEntity(Integer proveedorSsoId) {
        this.proveedorSsoId = proveedorSsoId;
    }

    public ProveedoresSsoEntity(Integer proveedorSsoId, String nombre, String urlDiscovery) {
        this.proveedorSsoId = proveedorSsoId;
        this.nombre = nombre;
        this.urlDiscovery = urlDiscovery;
    }

    public Integer getProveedorSsoId() {
        return proveedorSsoId;
    }

    public void setProveedorSsoId(Integer proveedorSsoId) {
        this.proveedorSsoId = proveedorSsoId;
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

    public String getUrlDiscovery() {
        return urlDiscovery;
    }

    public void setUrlDiscovery(String urlDiscovery) {
        this.urlDiscovery = urlDiscovery;
    }

    public String getUrlIcono() {
        return urlIcono;
    }

    public void setUrlIcono(String urlIcono) {
        this.urlIcono = urlIcono;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proveedorSsoId != null ? proveedorSsoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProveedoresSsoEntity)) {
            return false;
        }
        ProveedoresSsoEntity other = (ProveedoresSsoEntity) object;
        if ((this.proveedorSsoId == null && other.proveedorSsoId != null) || (this.proveedorSsoId != null && !this.proveedorSsoId.equals(other.proveedorSsoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.ProveedoresSso[proveedorSsoId=" + proveedorSsoId + "]";
    }

    @Override
    public Object getPK() {
        return proveedorSsoId;
    }
}
