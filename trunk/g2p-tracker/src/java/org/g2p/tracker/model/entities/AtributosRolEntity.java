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
 * @author nacho
 */
@Entity
@Table(name = "atributos_rol")
@NamedQueries({@NamedQuery(name = "AtributosRolEntity.findAll", query = "SELECT a FROM AtributosRolEntity a")})
public class AtributosRolEntity extends BaseEntity implements Serializable {
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atributoId", fetch = FetchType.LAZY)
    private Set<AtributosEntidadEntity> atributosEntidadCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atributosRol", fetch = FetchType.LAZY)
    private Set<AtributosConfiguracionEntity> atributosConfiguracionCollection;
    @JoinColumn(name = "clase_atributo_rol_id", referencedColumnName = "clase_atributo_rol_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ClaseAtributoRolEntity claseAtributoRolId;
    @JoinColumn(name = "rol", referencedColumnName = "rol")
    @ManyToOne(fetch = FetchType.LAZY)
    private RolEntidadEntity rol;
    @JoinColumns({@JoinColumn(name = "tipo_objeto", referencedColumnName = "tipo_objeto"), @JoinColumn(name = "nombre_objeto", referencedColumnName = "nombre_objeto")})
    @ManyToOne(fetch = FetchType.LAZY)
    private DiccionarioAplicacionEntity diccionarioAplicacion;

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

    public Set<AtributosEntidadEntity> getAtributosEntidadCollection() {
        return atributosEntidadCollection;
    }

    public void setAtributosEntidadCollection(Set<AtributosEntidadEntity> atributosEntidadCollection) {
        this.atributosEntidadCollection = atributosEntidadCollection;
    }

    public Set<AtributosConfiguracionEntity> getAtributosConfiguracionCollection() {
        return atributosConfiguracionCollection;
    }

    public void setAtributosConfiguracionCollection(Set<AtributosConfiguracionEntity> atributosConfiguracionCollection) {
        this.atributosConfiguracionCollection = atributosConfiguracionCollection;
    }

    public ClaseAtributoRolEntity getClaseAtributoRolId() {
        return claseAtributoRolId;
    }

    public void setClaseAtributoRolId(ClaseAtributoRolEntity claseAtributoRolId) {
        this.claseAtributoRolId = claseAtributoRolId;
    }

    public RolEntidadEntity getRol() {
        return rol;
    }

    public void setRol(RolEntidadEntity rol) {
        this.rol = rol;
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
        return "org.g2p.tracker.model.entities.AtributosRol[atributoId=" + atributoId + "]";
    }

    @Override
    public Object getPK() {
        return atributoId;
    }

}
