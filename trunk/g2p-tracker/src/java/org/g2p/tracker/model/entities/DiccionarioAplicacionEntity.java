package org.g2p.tracker.model.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author nacho
 */
@Entity
@Table(name = "diccionario_aplicacion")
@NamedQueries({@NamedQuery(name = "DiccionarioAplicacionEntity.findAll", query = "SELECT d FROM DiccionarioAplicacionEntity d")})
public class DiccionarioAplicacionEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DiccionarioAplicacionEntityPK diccionarioAplicacionPK;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "diccionarioAplicacion", fetch = FetchType.EAGER)
    private Set<EsquemaConfiguracionEntity> esquemaConfiguracionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "diccionarioAplicacion", fetch = FetchType.EAGER)
    private Set<DiccionarioAplicacionDetalleEntity> diccionarioAplicacionDetalleCollection;
    @OneToMany(mappedBy = "diccionarioAplicacion", fetch = FetchType.EAGER)
    private Set<AtributosRolEntity> atributosRolCollection;

    public DiccionarioAplicacionEntity() {
    }

    public DiccionarioAplicacionEntity(DiccionarioAplicacionEntityPK diccionarioAplicacionPK) {
        this.diccionarioAplicacionPK = diccionarioAplicacionPK;
    }

    public DiccionarioAplicacionEntity(String tipoObjeto, String nombreObjeto) {
        this.diccionarioAplicacionPK = new DiccionarioAplicacionEntityPK(tipoObjeto, nombreObjeto);
    }

    public DiccionarioAplicacionEntityPK getDiccionarioAplicacionPK() {
        return diccionarioAplicacionPK;
    }

    public void setDiccionarioAplicacionPK(DiccionarioAplicacionEntityPK diccionarioAplicacionPK) {
        this.diccionarioAplicacionPK = diccionarioAplicacionPK;
    }

    public Set<EsquemaConfiguracionEntity> getEsquemaConfiguracionCollection() {
        return esquemaConfiguracionCollection;
    }

    public void setEsquemaConfiguracionCollection(Set<EsquemaConfiguracionEntity> esquemaConfiguracionCollection) {
        this.esquemaConfiguracionCollection = esquemaConfiguracionCollection;
    }

    public Set<DiccionarioAplicacionDetalleEntity> getDiccionarioAplicacionDetalleCollection() {
        return diccionarioAplicacionDetalleCollection;
    }

    public void setDiccionarioAplicacionDetalleCollection(Set<DiccionarioAplicacionDetalleEntity> diccionarioAplicacionDetalleCollection) {
        this.diccionarioAplicacionDetalleCollection = diccionarioAplicacionDetalleCollection;
    }

    public Set<AtributosRolEntity> getAtributosRolCollection() {
        return atributosRolCollection;
    }

    public void setAtributosRolCollection(Set<AtributosRolEntity> atributosRolCollection) {
        this.atributosRolCollection = atributosRolCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (diccionarioAplicacionPK != null ? diccionarioAplicacionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiccionarioAplicacionEntity)) {
            return false;
        }
        DiccionarioAplicacionEntity other = (DiccionarioAplicacionEntity) object;
        if ((this.diccionarioAplicacionPK == null && other.diccionarioAplicacionPK != null) || (this.diccionarioAplicacionPK != null && !this.diccionarioAplicacionPK.equals(other.diccionarioAplicacionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.DiccionarioAplicacion[diccionarioAplicacionPK=" + diccionarioAplicacionPK + "]";
    }

    @Override
    public Object getPK() {
        return diccionarioAplicacionPK;
    }

}
