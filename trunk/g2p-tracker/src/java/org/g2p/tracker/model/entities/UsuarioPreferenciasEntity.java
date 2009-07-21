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
 * @author nacho
 */
@Entity
@Table(name = "usuario_preferencias")
@NamedQueries({@NamedQuery(name = "UsuarioPreferenciasEntity.findAll", query = "SELECT u FROM UsuarioPreferenciasEntity u")})
public class UsuarioPreferenciasEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuarioPreferenciasEntityPK usuarioPreferenciasPK;
    @Basic(optional = false)
    @Column(name = "valor")
    private String valor;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private WebsiteUsersEntity websiteUsers;
    @JoinColumn(name = "preferencia_id", referencedColumnName = "preferencia_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private PreferenciasEntity preferencias;

    public UsuarioPreferenciasEntity() {
    }

    public UsuarioPreferenciasEntity(UsuarioPreferenciasEntityPK usuarioPreferenciasPK) {
        this.usuarioPreferenciasPK = usuarioPreferenciasPK;
    }

    public UsuarioPreferenciasEntity(UsuarioPreferenciasEntityPK usuarioPreferenciasPK, String valor) {
        this.usuarioPreferenciasPK = usuarioPreferenciasPK;
        this.valor = valor;
    }

    public UsuarioPreferenciasEntity(int userId, int preferenciaId) {
        this.usuarioPreferenciasPK = new UsuarioPreferenciasEntityPK(userId, preferenciaId);
    }

    public UsuarioPreferenciasEntityPK getUsuarioPreferenciasPK() {
        return usuarioPreferenciasPK;
    }

    public void setUsuarioPreferenciasPK(UsuarioPreferenciasEntityPK usuarioPreferenciasPK) {
        this.usuarioPreferenciasPK = usuarioPreferenciasPK;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public WebsiteUsersEntity getWebsiteUsers() {
        return websiteUsers;
    }

    public void setWebsiteUsers(WebsiteUsersEntity websiteUsers) {
        this.websiteUsers = websiteUsers;
    }

    public PreferenciasEntity getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(PreferenciasEntity preferencias) {
        this.preferencias = preferencias;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioPreferenciasPK != null ? usuarioPreferenciasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioPreferenciasEntity)) {
            return false;
        }
        UsuarioPreferenciasEntity other = (UsuarioPreferenciasEntity) object;
        if ((this.usuarioPreferenciasPK == null && other.usuarioPreferenciasPK != null) || (this.usuarioPreferenciasPK != null && !this.usuarioPreferenciasPK.equals(other.usuarioPreferenciasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.g2p.tracker.model.entities.UsuarioPreferencias[usuarioPreferenciasPK=" + usuarioPreferenciasPK + "]";
    }

    @Override
    public Object getPK() {
        return usuarioPreferenciasPK;
    }

}
