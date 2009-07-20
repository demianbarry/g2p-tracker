/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author nacho
 */
@Entity
@Table(name = "atributos_entidad")
@NamedQueries({@NamedQuery(name = "AtributosEntidadEntity.findAll", query = "SELECT a FROM AtributosEntidadEntity a")})
public class AtributosEntidadEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "atributo_entidad_id")
    private Integer atributoEntidadId;
    @Column(name = "valor")
    private String valor;
    @Column(name = "valor_entero")
    private Integer valorEntero;
    @Column(name = "valor_real")
    private Double valorReal;
    @Column(name = "valor_fecha")
    @Temporal(TemporalType.DATE)
    private Date valorFecha;
    @Column(name = "valor_logico")
    private Character valorLogico;
    @Column(name = "objeto_id")
    private Integer objetoId;
    @Column(name = "tipo_objeto")
    private String tipoObjeto;
    @Column(name = "nombre_objeto")
    private String nombreObjeto;
    @JoinColumn(name = "entidad_id", referencedColumnName = "entidad_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private EntidadExternaEntity entidadId;
    @JoinColumn(name = "atributo_id", referencedColumnName = "atributo_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private AtributosRolEntity atributoId;

    public AtributosEntidadEntity() {
    }

    public AtributosEntidadEntity(Integer atributoEntidadId) {
        this.atributoEntidadId = atributoEntidadId;
    }

    public Integer getAtributoEntidadId() {
        return atributoEntidadId;
    }

    public void setAtributoEntidadId(Integer atributoEntidadId) {
        this.atributoEntidadId = atributoEntidadId;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Integer getValorEntero() {
        return valorEntero;
    }

    public void setValorEntero(Integer valorEntero) {
        this.valorEntero = valorEntero;
    }

    public Double getValorReal() {
        return valorReal;
    }

    public void setValorReal(Double valorReal) {
        this.valorReal = valorReal;
    }

    public Date getValorFecha() {
        return valorFecha;
    }

    public void setValorFecha(Date valorFecha) {
        this.valorFecha = valorFecha;
    }

    public Character getValorLogico() {
        return valorLogico;
    }

    public void setValorLogico(Character valorLogico) {
        this.valorLogico = valorLogico;
    }

    public Integer getObjetoId() {
        return objetoId;
    }

    public void setObjetoId(Integer objetoId) {
        this.objetoId = objetoId;
    }

    public String getTipoObjeto() {
        return tipoObjeto;
    }

    public void setTipoObjeto(String tipoObjeto) {
        this.tipoObjeto = tipoObjeto;
    }

    public String getNombreObjeto() {
        return nombreObjeto;
    }

    public void setNombreObjeto(String nombreObjeto) {
        this.nombreObjeto = nombreObjeto;
    }

    public EntidadExternaEntity getEntidadId() {
        return entidadId;
    }

    public void setEntidadId(EntidadExternaEntity entidadId) {
        this.entidadId = entidadId;
    }

    public AtributosRolEntity getAtributoId() {
        return atributoId;
    }

    public void setAtributoId(AtributosRolEntity atributoId) {
        this.atributoId = atributoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (atributoEntidadId != null ? atributoEntidadId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AtributosEntidadEntity)) {
            return false;
        }
        AtributosEntidadEntity other = (AtributosEntidadEntity) object;
        if ((this.atributoEntidadId == null && other.atributoEntidadId != null) || (this.atributoEntidadId != null && !this.atributoEntidadId.equals(other.atributoEntidadId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.AtributosEntidad[atributoEntidadId=" + atributoEntidadId + "]";
    }

    @Override
    public Object getPK() {
        return atributoEntidadId;
    }

}
