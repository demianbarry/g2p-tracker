/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author g2p
 */
@Entity
@Table(name = "documentos")
@NamedQueries({@NamedQuery(name = "DocumentosEntity.findAll", query = "SELECT d FROM DocumentosEntity d")})
public class DocumentosEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_documento")
    private Integer idDocumento;
    @Basic(optional = false)
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "descripción")
    private String descripción;
    @Basic(optional = false)
    @Column(name = "path")
    private String path;
    @Basic(optional = false)
    @Column(name = "version")
    private double version;
    @Column(name = "tipo")
    private String tipo;

    public DocumentosEntity() {
    }

    public DocumentosEntity(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }

    public DocumentosEntity(Integer idDocumento, String titulo, String path, double version) {
        this.idDocumento = idDocumento;
        this.titulo = titulo;
        this.path = path;
        this.version = version;
    }

    public Integer getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public double getDocumentVersion() {
        return version;
    }

    public void setDocumentVersion(double version) {
        this.version = version;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDocumento != null ? idDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentosEntity)) {
            return false;
        }
        DocumentosEntity other = (DocumentosEntity) object;
        if ((this.idDocumento == null && other.idDocumento != null) || (this.idDocumento != null && !this.idDocumento.equals(other.idDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.DocumentosEntity[idDocumento=" + idDocumento + "]";
    }

    @Override
    public Object getPK() {
        return getIdDocumento();
    }

}
