/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.daos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import org.g2p.tracker.model.daos.exceptions.NonexistentEntityException;
import org.g2p.tracker.model.daos.exceptions.PreexistingEntityException;
import org.g2p.tracker.model.entities.RolesEntity;
import org.g2p.tracker.model.entities.UsuarioRolesEntity;
import org.g2p.tracker.model.entities.UsuarioRolesEntityPK;
import org.g2p.tracker.model.entities.WebsiteUserEntity;

/**
 *
 * @author Administrador
 */
public class UsuarioRolesEntityJpaController {

    public UsuarioRolesEntityJpaController() {
        emf = Persistence.createEntityManagerFactory("g2p-tracker-sinSpringPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UsuarioRolesEntity usuarioRolesEntity) throws PreexistingEntityException, Exception {
        if (usuarioRolesEntity.getUsuarioRolesEntityPK() == null) {
            usuarioRolesEntity.setUsuarioRolesEntityPK(new UsuarioRolesEntityPK());
        }
        usuarioRolesEntity.getUsuarioRolesEntityPK().setRolId(usuarioRolesEntity.getRolesEntity().getRolId());
        usuarioRolesEntity.getUsuarioRolesEntityPK().setUserId(usuarioRolesEntity.getWebsiteUserEntity().getUserId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RolesEntity rolesEntity = usuarioRolesEntity.getRolesEntity();
            if (rolesEntity != null) {
                rolesEntity = em.getReference(rolesEntity.getClass(), rolesEntity.getRolId());
                usuarioRolesEntity.setRolesEntity(rolesEntity);
            }
            WebsiteUserEntity websiteUserEntity = usuarioRolesEntity.getWebsiteUserEntity();
            if (websiteUserEntity != null) {
                websiteUserEntity = em.getReference(websiteUserEntity.getClass(), websiteUserEntity.getUserId());
                usuarioRolesEntity.setWebsiteUserEntity(websiteUserEntity);
            }
            em.persist(usuarioRolesEntity);
            if (rolesEntity != null) {
                rolesEntity.getUsuarioRolesEntityCollection().add(usuarioRolesEntity);
                rolesEntity = em.merge(rolesEntity);
            }
            if (websiteUserEntity != null) {
                websiteUserEntity.getUsuarioRolesEntityCollection().add(usuarioRolesEntity);
                websiteUserEntity = em.merge(websiteUserEntity);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuarioRolesEntity(usuarioRolesEntity.getUsuarioRolesEntityPK()) != null) {
                throw new PreexistingEntityException("UsuarioRolesEntity " + usuarioRolesEntity + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UsuarioRolesEntity usuarioRolesEntity) throws NonexistentEntityException, Exception {
        usuarioRolesEntity.getUsuarioRolesEntityPK().setRolId(usuarioRolesEntity.getRolesEntity().getRolId());
        usuarioRolesEntity.getUsuarioRolesEntityPK().setUserId(usuarioRolesEntity.getWebsiteUserEntity().getUserId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UsuarioRolesEntity persistentUsuarioRolesEntity = em.find(UsuarioRolesEntity.class, usuarioRolesEntity.getUsuarioRolesEntityPK());
            RolesEntity rolesEntityOld = persistentUsuarioRolesEntity.getRolesEntity();
            RolesEntity rolesEntityNew = usuarioRolesEntity.getRolesEntity();
            WebsiteUserEntity websiteUserEntityOld = persistentUsuarioRolesEntity.getWebsiteUserEntity();
            WebsiteUserEntity websiteUserEntityNew = usuarioRolesEntity.getWebsiteUserEntity();
            if (rolesEntityNew != null) {
                rolesEntityNew = em.getReference(rolesEntityNew.getClass(), rolesEntityNew.getRolId());
                usuarioRolesEntity.setRolesEntity(rolesEntityNew);
            }
            if (websiteUserEntityNew != null) {
                websiteUserEntityNew = em.getReference(websiteUserEntityNew.getClass(), websiteUserEntityNew.getUserId());
                usuarioRolesEntity.setWebsiteUserEntity(websiteUserEntityNew);
            }
            usuarioRolesEntity = em.merge(usuarioRolesEntity);
            if (rolesEntityOld != null && !rolesEntityOld.equals(rolesEntityNew)) {
                rolesEntityOld.getUsuarioRolesEntityCollection().remove(usuarioRolesEntity);
                rolesEntityOld = em.merge(rolesEntityOld);
            }
            if (rolesEntityNew != null && !rolesEntityNew.equals(rolesEntityOld)) {
                rolesEntityNew.getUsuarioRolesEntityCollection().add(usuarioRolesEntity);
                rolesEntityNew = em.merge(rolesEntityNew);
            }
            if (websiteUserEntityOld != null && !websiteUserEntityOld.equals(websiteUserEntityNew)) {
                websiteUserEntityOld.getUsuarioRolesEntityCollection().remove(usuarioRolesEntity);
                websiteUserEntityOld = em.merge(websiteUserEntityOld);
            }
            if (websiteUserEntityNew != null && !websiteUserEntityNew.equals(websiteUserEntityOld)) {
                websiteUserEntityNew.getUsuarioRolesEntityCollection().add(usuarioRolesEntity);
                websiteUserEntityNew = em.merge(websiteUserEntityNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                UsuarioRolesEntityPK id = usuarioRolesEntity.getUsuarioRolesEntityPK();
                if (findUsuarioRolesEntity(id) == null) {
                    throw new NonexistentEntityException("The usuarioRolesEntity with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(UsuarioRolesEntityPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UsuarioRolesEntity usuarioRolesEntity;
            try {
                usuarioRolesEntity = em.getReference(UsuarioRolesEntity.class, id);
                usuarioRolesEntity.getUsuarioRolesEntityPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuarioRolesEntity with id " + id + " no longer exists.", enfe);
            }
            RolesEntity rolesEntity = usuarioRolesEntity.getRolesEntity();
            if (rolesEntity != null) {
                rolesEntity.getUsuarioRolesEntityCollection().remove(usuarioRolesEntity);
                rolesEntity = em.merge(rolesEntity);
            }
            WebsiteUserEntity websiteUserEntity = usuarioRolesEntity.getWebsiteUserEntity();
            if (websiteUserEntity != null) {
                websiteUserEntity.getUsuarioRolesEntityCollection().remove(usuarioRolesEntity);
                websiteUserEntity = em.merge(websiteUserEntity);
            }
            em.remove(usuarioRolesEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UsuarioRolesEntity> findUsuarioRolesEntityEntities() {
        return findUsuarioRolesEntityEntities(true, -1, -1);
    }

    public List<UsuarioRolesEntity> findUsuarioRolesEntityEntities(int maxResults, int firstResult) {
        return findUsuarioRolesEntityEntities(false, maxResults, firstResult);
    }

    private List<UsuarioRolesEntity> findUsuarioRolesEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from UsuarioRolesEntity as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public UsuarioRolesEntity findUsuarioRolesEntity(UsuarioRolesEntityPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UsuarioRolesEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioRolesEntityCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from UsuarioRolesEntity as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
