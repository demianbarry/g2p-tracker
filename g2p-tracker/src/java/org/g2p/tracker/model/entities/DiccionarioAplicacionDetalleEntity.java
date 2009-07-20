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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author nacho
 */
@Entity
@Table(name = "diccionario_aplicacion_detalle")
@NamedQueries({@NamedQuery(name = "DiccionarioAplicacionDetalleEntity.findAll", query = "SELECT d FROM DiccionarioAplicacionDetalleEntity d")})
public class DiccionarioAplicacionDetalleEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_diccionario")
    private Integer idDiccionario;
    @Basic(optional = false)
    @Column(name = "tipo_detalle")
    private String tipoDetalle;
    @Basic(optional = false)
    @Column(name = "nombre_detalle")
    private String nombreDetalle;
    @JoinColumns({@JoinColumn(name = "tipo_objeto", referencedColumnName = "tipo_objeto"), @JoinColumn(name = "nombre_objeto", referencedColumnName = "nombre_objeto")})
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private DiccionarioAplicacionEntity diccionarioAplicacion;

    public DiccionarioAplicacionDetalleEntity() {
    }

    public DiccionarioAplicacionDetalleEntity(Integer idDiccionario) {
        this.idDiccionario = idDiccionario;
    }

    public DiccionarioAplicacionDetalleEntity(Integer idDiccionario, String tipoDetalle, String nombreDetalle) {
        this.idDiccionario = idDiccionario;
        this.tipoDetalle = tipoDetalle;
        this.nombreDetalle = nombreDetalle;
    }

    public Integer getIdDiccionario() {
        return idDiccionario;
    }

    public void setIdDiccionario(Integer idDiccionario) {
        this.idDiccionario = idDiccionario;
    }

    public String getTipoDetalle() {
        return tipoDetalle;
    }

    public void setTipoDetalle(String tipoDetalle) {
        this.tipoDetalle = tipoDetalle;
    }

    public String getNombreDetalle() {
        return nombreDetalle;
    }

    public void setNombreDetalle(String nombreDetalle) {
        this.nombreDetalle = nombreDetalle;
    }

    public DiccionarioAplicacionEntity getDiccionarioAplicacion() {
        return diccionarioAplicacion;
    }

    public void setDiccionarioAplicacion(DiccionarioAplicacionEntity diccionarioAplicacion) {
        this.diccionarioAplicacion = diccionarioAplicacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDiccionario != null ? idDiccionario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiccionarioAplicacionDetalleEntity)) {
            return false;
        }
        DiccionarioAplicacionDetalleEntity other = (DiccionarioAplicacionDetalleEntity) object;
        if ((this.idDiccionario == null && other.idDiccionario != null) || (this.idDiccionario != null && !this.idDiccionario.equals(other.idDiccionario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.DiccionarioAplicacionDetalle[idDiccionario=" + idDiccionario + "]";
    }

    @Override
    public Object getPK() {
        return idDiccionario;
    }

}
