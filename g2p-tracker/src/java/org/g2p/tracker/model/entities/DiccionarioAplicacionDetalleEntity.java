/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "diccionario_aplicacion_detalle")
@NamedQueries({@NamedQuery(name = "DiccionarioAplicacionDetalleEntity.findAll", query = "SELECT d FROM DiccionarioAplicacionDetalleEntity d"), @NamedQuery(name = "DiccionarioAplicacionDetalleEntity.findByTipoObjeto", query = "SELECT d FROM DiccionarioAplicacionDetalleEntity d WHERE d.diccionarioAplicacionDetalleEntityPK.tipoObjeto = :tipoObjeto"), @NamedQuery(name = "DiccionarioAplicacionDetalleEntity.findByNombreObjeto", query = "SELECT d FROM DiccionarioAplicacionDetalleEntity d WHERE d.diccionarioAplicacionDetalleEntityPK.nombreObjeto = :nombreObjeto"), @NamedQuery(name = "DiccionarioAplicacionDetalleEntity.findByTipoDetalle", query = "SELECT d FROM DiccionarioAplicacionDetalleEntity d WHERE d.diccionarioAplicacionDetalleEntityPK.tipoDetalle = :tipoDetalle"), @NamedQuery(name = "DiccionarioAplicacionDetalleEntity.findByNombreDetalle", query = "SELECT d FROM DiccionarioAplicacionDetalleEntity d WHERE d.diccionarioAplicacionDetalleEntityPK.nombreDetalle = :nombreDetalle")})
public class DiccionarioAplicacionDetalleEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DiccionarioAplicacionDetalleEntityPK diccionarioAplicacionDetalleEntityPK;
    @JoinColumns({@JoinColumn(name = "tipo_objeto", referencedColumnName = "tipo_objeto", insertable = false, updatable = false), @JoinColumn(name = "nombre_objeto", referencedColumnName = "nombre_objeto", insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private DiccionarioAplicacionEntity diccionarioAplicacionEntity;

    public DiccionarioAplicacionDetalleEntity() {
    }

    public DiccionarioAplicacionDetalleEntity(DiccionarioAplicacionDetalleEntityPK diccionarioAplicacionDetalleEntityPK) {
        this.diccionarioAplicacionDetalleEntityPK = diccionarioAplicacionDetalleEntityPK;
    }

    public DiccionarioAplicacionDetalleEntity(String tipoObjeto, String nombreObjeto, String tipoDetalle, String nombreDetalle) {
        this.diccionarioAplicacionDetalleEntityPK = new DiccionarioAplicacionDetalleEntityPK(tipoObjeto, nombreObjeto, tipoDetalle, nombreDetalle);
    }

    public DiccionarioAplicacionDetalleEntityPK getDiccionarioAplicacionDetalleEntityPK() {
        return diccionarioAplicacionDetalleEntityPK;
    }

    public void setDiccionarioAplicacionDetalleEntityPK(DiccionarioAplicacionDetalleEntityPK diccionarioAplicacionDetalleEntityPK) {
        this.diccionarioAplicacionDetalleEntityPK = diccionarioAplicacionDetalleEntityPK;
    }

    public DiccionarioAplicacionEntity getDiccionarioAplicacionEntity() {
        return diccionarioAplicacionEntity;
    }

    public void setDiccionarioAplicacionEntity(DiccionarioAplicacionEntity diccionarioAplicacionEntity) {
        this.diccionarioAplicacionEntity = diccionarioAplicacionEntity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (diccionarioAplicacionDetalleEntityPK != null ? diccionarioAplicacionDetalleEntityPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiccionarioAplicacionDetalleEntity)) {
            return false;
        }
        DiccionarioAplicacionDetalleEntity other = (DiccionarioAplicacionDetalleEntity) object;
        if ((this.diccionarioAplicacionDetalleEntityPK == null && other.diccionarioAplicacionDetalleEntityPK != null) || (this.diccionarioAplicacionDetalleEntityPK != null && !this.diccionarioAplicacionDetalleEntityPK.equals(other.diccionarioAplicacionDetalleEntityPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.entities.DiccionarioAplicacionDetalleEntity[diccionarioAplicacionDetalleEntityPK=" + diccionarioAplicacionDetalleEntityPK + "]";
    }

}
