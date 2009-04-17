/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "atributos_rol")
@NamedQueries({@NamedQuery(name = "AtributosRolEntity.findAll", query = "SELECT a FROM AtributosRolEntity a"), @NamedQuery(name = "AtributosRolEntity.findByAtributoId", query = "SELECT a FROM AtributosRolEntity a WHERE a.atributoId = :atributoId"), @NamedQuery(name = "AtributosRolEntity.findByNombre", query = "SELECT a FROM AtributosRolEntity a WHERE a.nombre = :nombre"), @NamedQuery(name = "AtributosRolEntity.findByDescripcion", query = "SELECT a FROM AtributosRolEntity a WHERE a.descripcion = :descripcion"), @NamedQuery(name = "AtributosRolEntity.findByObservaciones", query = "SELECT a FROM AtributosRolEntity a WHERE a.observaciones = :observaciones"), @NamedQuery(name = "AtributosRolEntity.findByDesde", query = "SELECT a FROM AtributosRolEntity a WHERE a.desde = :desde"), @NamedQuery(name = "AtributosRolEntity.findByHasta", query = "SELECT a FROM AtributosRolEntity a WHERE a.hasta = :hasta"), @NamedQuery(name = "AtributosRolEntity.findByAnulado", query = "SELECT a FROM AtributosRolEntity a WHERE a.anulado = :anulado"), @NamedQuery(name = "AtributosRolEntity.findByClave", query = "SELECT a FROM AtributosRolEntity a WHERE a.clave = :clave"), @NamedQuery(name = "AtributosRolEntity.findByObligatorio", query = "SELECT a FROM AtributosRolEntity a WHERE a.obligatorio = :obligatorio"), @NamedQuery(name = "AtributosRolEntity.findByTipoDato", query = "SELECT a FROM AtributosRolEntity a WHERE a.tipoDato = :tipoDato"), @NamedQuery(name = "AtributosRolEntity.findByLov", query = "SELECT a FROM AtributosRolEntity a WHERE a.lov = :lov"), @NamedQuery(name = "AtributosRolEntity.findByValidador", query = "SELECT a FROM AtributosRolEntity a WHERE a.validador = :validador"), @NamedQuery(name = "AtributosRolEntity.findByClaseLovAtributoId", query = "SELECT a FROM AtributosRolEntity a WHERE a.claseLovAtributoId = :claseLovAtributoId")})
public class AtributosRolEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "atributo_id")
    private Integer atributoId;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "observaciones")
    private String observaciones;
    @Basic(optional = false)
    @Column(name = "desde")
    @Temporal(TemporalType.DATE)
    private Date desde;
    @Column(name = "hasta")
    @Temporal(TemporalType.DATE)
    private Date hasta;
    @Column(name = "anulado")
    private Character anulado;
    @Basic(optional = false)
    @Column(name = "clave")
    private char clave;
    @Basic(optional = false)
    @Column(name = "obligatorio")
    private char obligatorio;
    @Basic(optional = false)
    @Column(name = "tipo_dato")
    private String tipoDato;
    @Column(name = "lov")
    private String lov;
    @Column(name = "validador")
    private String validador;
    @Column(name = "clase_lov_atributo_id")
    private Integer claseLovAtributoId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atributoId", fetch = FetchType.EAGER)
    private Set<AtributosEntidadEntity> atributosEntidadEntityCollection;
    @JoinColumns({@JoinColumn(name = "tipo_objeto", referencedColumnName = "tipo_objeto"), @JoinColumn(name = "nombre_objeto", referencedColumnName = "nombre_objeto")})
    @ManyToOne(fetch = FetchType.EAGER)
    private DiccionarioAplicacionEntity diccionarioAplicacionEntity;
    @JoinColumn(name = "rol", referencedColumnName = "rol")
    @ManyToOne(fetch = FetchType.EAGER)
    private RolEntidadEntity rol;
    @JoinColumn(name = "clase_atributo_rol_id", referencedColumnName = "clase_atributo_rol_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private ClaseAtributoRolEntity claseAtributoRolId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atributosRolEntity", fetch = FetchType.EAGER)
    private Set<AtributosConfiguracionEntity> atributosConfiguracionEntityCollection;

    public AtributosRolEntity() {
    }

    public AtributosRolEntity(Integer atributoId) {
        this.atributoId = atributoId;
    }

    public AtributosRolEntity(Integer atributoId, String nombre, Date desde, char clave, char obligatorio, String tipoDato) {
        this.atributoId = atributoId;
        this.nombre = nombre;
        this.desde = desde;
        this.clave = clave;
        this.obligatorio = obligatorio;
        this.tipoDato = tipoDato;
    }

    public Integer getAtributoId() {
        return atributoId;
    }

    public void setAtributoId(Integer atributoId) {
        this.atributoId = atributoId;
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

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }

    public Character getAnulado() {
        return anulado;
    }

    public void setAnulado(Character anulado) {
        this.anulado = anulado;
    }

    public char getClave() {
        return clave;
    }

    public void setClave(char clave) {
        this.clave = clave;
    }

    public char getObligatorio() {
        return obligatorio;
    }

    public void setObligatorio(char obligatorio) {
        this.obligatorio = obligatorio;
    }

    public String getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(String tipoDato) {
        this.tipoDato = tipoDato;
    }

    public String getLov() {
        return lov;
    }

    public void setLov(String lov) {
        this.lov = lov;
    }

    public String getValidador() {
        return validador;
    }

    public void setValidador(String validador) {
        this.validador = validador;
    }

    public Integer getClaseLovAtributoId() {
        return claseLovAtributoId;
    }

    public void setClaseLovAtributoId(Integer claseLovAtributoId) {
        this.claseLovAtributoId = claseLovAtributoId;
    }

    public Set<AtributosEntidadEntity> getAtributosEntidadEntityCollection() {
        return atributosEntidadEntityCollection;
    }

    public void setAtributosEntidadEntityCollection(Set<AtributosEntidadEntity> atributosEntidadEntityCollection) {
        this.atributosEntidadEntityCollection = atributosEntidadEntityCollection;
    }

    public DiccionarioAplicacionEntity getDiccionarioAplicacionEntity() {
        return diccionarioAplicacionEntity;
    }

    public void setDiccionarioAplicacionEntity(DiccionarioAplicacionEntity diccionarioAplicacionEntity) {
        this.diccionarioAplicacionEntity = diccionarioAplicacionEntity;
    }

    public RolEntidadEntity getRol() {
        return rol;
    }

    public void setRol(RolEntidadEntity rol) {
        this.rol = rol;
    }

    public ClaseAtributoRolEntity getClaseAtributoRolId() {
        return claseAtributoRolId;
    }

    public void setClaseAtributoRolId(ClaseAtributoRolEntity claseAtributoRolId) {
        this.claseAtributoRolId = claseAtributoRolId;
    }

    public Set<AtributosConfiguracionEntity> getAtributosConfiguracionEntityCollection() {
        return atributosConfiguracionEntityCollection;
    }

    public void setAtributosConfiguracionEntityCollection(Set<AtributosConfiguracionEntity> atributosConfiguracionEntityCollection) {
        this.atributosConfiguracionEntityCollection = atributosConfiguracionEntityCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (atributoId != null ? atributoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AtributosRolEntity)) {
            return false;
        }
        AtributosRolEntity other = (AtributosRolEntity) object;
        if ((this.atributoId == null && other.atributoId != null) || (this.atributoId != null && !this.atributoId.equals(other.atributoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.entities.AtributosRolEntity[atributoId=" + atributoId + "]";
    }

}
