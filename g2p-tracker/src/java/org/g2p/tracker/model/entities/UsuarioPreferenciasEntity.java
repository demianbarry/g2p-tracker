/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "usuario_preferencias")
@NamedQueries({@NamedQuery(name = "UsuarioPreferenciasEntity.findAll", query = "SELECT u FROM UsuarioPreferenciasEntity u"), @NamedQuery(name = "UsuarioPreferenciasEntity.findByUserId", query = "SELECT u FROM UsuarioPreferenciasEntity u WHERE u.usuarioPreferenciasEntityPK.userId = :userId"), @NamedQuery(name = "UsuarioPreferenciasEntity.findByPreferenciaId", query = "SELECT u FROM UsuarioPreferenciasEntity u WHERE u.usuarioPreferenciasEntityPK.preferenciaId = :preferenciaId"), @NamedQuery(name = "UsuarioPreferenciasEntity.findByValor", query = "SELECT u FROM UsuarioPreferenciasEntity u WHERE u.valor = :valor")})
public class UsuarioPreferenciasEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuarioPreferenciasEntityPK usuarioPreferenciasEntityPK;
    @Basic(optional = false)
    @Column(name = "valor")
    private String valor;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private WebsiteUserEntity websiteUserEntity;
    @JoinColumn(name = "preferencia_id", referencedColumnName = "preferencia_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private PreferenciasEntity preferenciasEntity;

    public UsuarioPreferenciasEntity() {
    }

    public UsuarioPreferenciasEntity(UsuarioPreferenciasEntityPK usuarioPreferenciasEntityPK) {
        this.usuarioPreferenciasEntityPK = usuarioPreferenciasEntityPK;
    }

    public UsuarioPreferenciasEntity(UsuarioPreferenciasEntityPK usuarioPreferenciasEntityPK, String valor) {
        this.usuarioPreferenciasEntityPK = usuarioPreferenciasEntityPK;
        this.valor = valor;
    }

    public UsuarioPreferenciasEntity(int userId, int preferenciaId) {
        this.usuarioPreferenciasEntityPK = new UsuarioPreferenciasEntityPK(userId, preferenciaId);
    }

    public UsuarioPreferenciasEntityPK getUsuarioPreferenciasEntityPK() {
        return usuarioPreferenciasEntityPK;
    }

    public void setUsuarioPreferenciasEntityPK(UsuarioPreferenciasEntityPK usuarioPreferenciasEntityPK) {
        this.usuarioPreferenciasEntityPK = usuarioPreferenciasEntityPK;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public WebsiteUserEntity getWebsiteUserEntity() {
        return websiteUserEntity;
    }

    public void setWebsiteUserEntity(WebsiteUserEntity websiteUserEntity) {
        this.websiteUserEntity = websiteUserEntity;
    }

    public PreferenciasEntity getPreferenciasEntity() {
        return preferenciasEntity;
    }

    public void setPreferenciasEntity(PreferenciasEntity preferenciasEntity) {
        this.preferenciasEntity = preferenciasEntity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioPreferenciasEntityPK != null ? usuarioPreferenciasEntityPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioPreferenciasEntity)) {
            return false;
        }
        UsuarioPreferenciasEntity other = (UsuarioPreferenciasEntity) object;
        if ((this.usuarioPreferenciasEntityPK == null && other.usuarioPreferenciasEntityPK != null) || (this.usuarioPreferenciasEntityPK != null && !this.usuarioPreferenciasEntityPK.equals(other.usuarioPreferenciasEntityPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.entities.UsuarioPreferenciasEntity[usuarioPreferenciasEntityPK=" + usuarioPreferenciasEntityPK + "]";
    }

}
