/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
 * @author Administrador
 */
@Entity
@Table(name = "diccionario_aplicacion")
@NamedQueries({@NamedQuery(name = "DiccionarioAplicacionEntity.findAll", query = "SELECT d FROM DiccionarioAplicacionEntity d"), @NamedQuery(name = "DiccionarioAplicacionEntity.findByTipoObjeto", query = "SELECT d FROM DiccionarioAplicacionEntity d WHERE d.diccionarioAplicacionEntityPK.tipoObjeto = :tipoObjeto"), @NamedQuery(name = "DiccionarioAplicacionEntity.findByNombreObjeto", query = "SELECT d FROM DiccionarioAplicacionEntity d WHERE d.diccionarioAplicacionEntityPK.nombreObjeto = :nombreObjeto")})
public class DiccionarioAplicacionEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DiccionarioAplicacionEntityPK diccionarioAplicacionEntityPK;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "diccionarioAplicacionEntity", fetch = FetchType.EAGER)
    private Set<EsquemaConfiguracionEntity> esquemaConfiguracionEntityCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "diccionarioAplicacionEntity", fetch = FetchType.EAGER)
    private Set<DiccionarioAplicacionDetalleEntity> diccionarioAplicacionDetalleEntityCollection;
    @OneToMany(mappedBy = "diccionarioAplicacionEntity", fetch = FetchType.EAGER)
    private Set<AtributosRolEntity> atributosRolEntityCollection;

    public DiccionarioAplicacionEntity() {
    }

    public DiccionarioAplicacionEntity(DiccionarioAplicacionEntityPK diccionarioAplicacionEntityPK) {
        this.diccionarioAplicacionEntityPK = diccionarioAplicacionEntityPK;
    }

    public DiccionarioAplicacionEntity(String tipoObjeto, String nombreObjeto) {
        this.diccionarioAplicacionEntityPK = new DiccionarioAplicacionEntityPK(tipoObjeto, nombreObjeto);
    }

    public DiccionarioAplicacionEntityPK getDiccionarioAplicacionEntityPK() {
        return diccionarioAplicacionEntityPK;
    }

    public void setDiccionarioAplicacionEntityPK(DiccionarioAplicacionEntityPK diccionarioAplicacionEntityPK) {
        this.diccionarioAplicacionEntityPK = diccionarioAplicacionEntityPK;
    }

    public Set<EsquemaConfiguracionEntity> getEsquemaConfiguracionEntityCollection() {
        return esquemaConfiguracionEntityCollection;
    }

    public void setEsquemaConfiguracionEntityCollection(Set<EsquemaConfiguracionEntity> esquemaConfiguracionEntityCollection) {
        this.esquemaConfiguracionEntityCollection = esquemaConfiguracionEntityCollection;
    }

    public Set<DiccionarioAplicacionDetalleEntity> getDiccionarioAplicacionDetalleEntityCollection() {
        return diccionarioAplicacionDetalleEntityCollection;
    }

    public void setDiccionarioAplicacionDetalleEntityCollection(Set<DiccionarioAplicacionDetalleEntity> diccionarioAplicacionDetalleEntityCollection) {
        this.diccionarioAplicacionDetalleEntityCollection = diccionarioAplicacionDetalleEntityCollection;
    }

    public Set<AtributosRolEntity> getAtributosRolEntityCollection() {
        return atributosRolEntityCollection;
    }

    public void setAtributosRolEntityCollection(Set<AtributosRolEntity> atributosRolEntityCollection) {
        this.atributosRolEntityCollection = atributosRolEntityCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (diccionarioAplicacionEntityPK != null ? diccionarioAplicacionEntityPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiccionarioAplicacionEntity)) {
            return false;
        }
        DiccionarioAplicacionEntity other = (DiccionarioAplicacionEntity) object;
        if ((this.diccionarioAplicacionEntityPK == null && other.diccionarioAplicacionEntityPK != null) || (this.diccionarioAplicacionEntityPK != null && !this.diccionarioAplicacionEntityPK.equals(other.diccionarioAplicacionEntityPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.entities.DiccionarioAplicacionEntity[diccionarioAplicacionEntityPK=" + diccionarioAplicacionEntityPK + "]";
    }

}
