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
import org.g2p.tracker.model.daos.exceptions.IllegalOrphanException;
import org.g2p.tracker.model.daos.exceptions.NonexistentEntityException;
import org.g2p.tracker.model.entities.AuditaEstadosCircuitosEntity;
import java.util.HashSet;
import java.util.Set;
import org.g2p.tracker.model.entities.UsuarioPreferenciasEntity;
import org.g2p.tracker.model.entities.UsuarioRolesEntity;
import org.g2p.tracker.model.entities.AccesoMenuEntity;
import java.util.ArrayList;
import org.g2p.tracker.model.entities.WebsiteUserEntity;

/**
 *
 * @author Administrador
 */
public class WebsiteUserEntityJpaController {

    public WebsiteUserEntityJpaController() {
        emf = Persistence.createEntityManagerFactory("g2p-tracker-sinSpringPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(WebsiteUserEntity websiteUserEntity) {
        if (websiteUserEntity.getAuditaEstadosCircuitosEntityCollection() == null) {
            websiteUserEntity.setAuditaEstadosCircuitosEntityCollection(new HashSet<AuditaEstadosCircuitosEntity>());
        }
        if (websiteUserEntity.getUsuarioPreferenciasEntityCollection() == null) {
            websiteUserEntity.setUsuarioPreferenciasEntityCollection(new HashSet<UsuarioPreferenciasEntity>());
        }
        if (websiteUserEntity.getUsuarioRolesEntityCollection() == null) {
            websiteUserEntity.setUsuarioRolesEntityCollection(new HashSet<UsuarioRolesEntity>());
        }
        if (websiteUserEntity.getAccesoMenuEntityCollection() == null) {
            websiteUserEntity.setAccesoMenuEntityCollection(new HashSet<AccesoMenuEntity>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<AuditaEstadosCircuitosEntity> attachedAuditaEstadosCircuitosEntityCollection = new HashSet<AuditaEstadosCircuitosEntity>();
            for (AuditaEstadosCircuitosEntity auditaEstadosCircuitosEntityCollectionAuditaEstadosCircuitosEntityToAttach : websiteUserEntity.getAuditaEstadosCircuitosEntityCollection()) {
                auditaEstadosCircuitosEntityCollectionAuditaEstadosCircuitosEntityToAttach = em.getReference(auditaEstadosCircuitosEntityCollectionAuditaEstadosCircuitosEntityToAttach.getClass(), auditaEstadosCircuitosEntityCollectionAuditaEstadosCircuitosEntityToAttach.getAuditaId());
                attachedAuditaEstadosCircuitosEntityCollection.add(auditaEstadosCircuitosEntityCollectionAuditaEstadosCircuitosEntityToAttach);
            }
            websiteUserEntity.setAuditaEstadosCircuitosEntityCollection(attachedAuditaEstadosCircuitosEntityCollection);
            Set<UsuarioPreferenciasEntity> attachedUsuarioPreferenciasEntityCollection = new HashSet<UsuarioPreferenciasEntity>();
            for (UsuarioPreferenciasEntity usuarioPreferenciasEntityCollectionUsuarioPreferenciasEntityToAttach : websiteUserEntity.getUsuarioPreferenciasEntityCollection()) {
                usuarioPreferenciasEntityCollectionUsuarioPreferenciasEntityToAttach = em.getReference(usuarioPreferenciasEntityCollectionUsuarioPreferenciasEntityToAttach.getClass(), usuarioPreferenciasEntityCollectionUsuarioPreferenciasEntityToAttach.getUsuarioPreferenciasEntityPK());
                attachedUsuarioPreferenciasEntityCollection.add(usuarioPreferenciasEntityCollectionUsuarioPreferenciasEntityToAttach);
            }
            websiteUserEntity.setUsuarioPreferenciasEntityCollection(attachedUsuarioPreferenciasEntityCollection);
            Set<UsuarioRolesEntity> attachedUsuarioRolesEntityCollection = new HashSet<UsuarioRolesEntity>();
            for (UsuarioRolesEntity usuarioRolesEntityCollectionUsuarioRolesEntityToAttach : websiteUserEntity.getUsuarioRolesEntityCollection()) {
                usuarioRolesEntityCollectionUsuarioRolesEntityToAttach = em.getReference(usuarioRolesEntityCollectionUsuarioRolesEntityToAttach.getClass(), usuarioRolesEntityCollectionUsuarioRolesEntityToAttach.getUsuarioRolesEntityPK());
                attachedUsuarioRolesEntityCollection.add(usuarioRolesEntityCollectionUsuarioRolesEntityToAttach);
            }
            websiteUserEntity.setUsuarioRolesEntityCollection(attachedUsuarioRolesEntityCollection);
            Set<AccesoMenuEntity> attachedAccesoMenuEntityCollection = new HashSet<AccesoMenuEntity>();
            for (AccesoMenuEntity accesoMenuEntityCollectionAccesoMenuEntityToAttach : websiteUserEntity.getAccesoMenuEntityCollection()) {
                accesoMenuEntityCollectionAccesoMenuEntityToAttach = em.getReference(accesoMenuEntityCollectionAccesoMenuEntityToAttach.getClass(), accesoMenuEntityCollectionAccesoMenuEntityToAttach.getAccesoMenuId());
                attachedAccesoMenuEntityCollection.add(accesoMenuEntityCollectionAccesoMenuEntityToAttach);
            }
            websiteUserEntity.setAccesoMenuEntityCollection(attachedAccesoMenuEntityCollection);
            em.persist(websiteUserEntity);
            for (AuditaEstadosCircuitosEntity auditaEstadosCircuitosEntityCollectionAuditaEstadosCircuitosEntity : websiteUserEntity.getAuditaEstadosCircuitosEntityCollection()) {
                WebsiteUserEntity oldUserIdOfAuditaEstadosCircuitosEntityCollectionAuditaEstadosCircuitosEntity = auditaEstadosCircuitosEntityCollectionAuditaEstadosCircuitosEntity.getUserId();
                auditaEstadosCircuitosEntityCollectionAuditaEstadosCircuitosEntity.setUserId(websiteUserEntity);
                auditaEstadosCircuitosEntityCollectionAuditaEstadosCircuitosEntity = em.merge(auditaEstadosCircuitosEntityCollectionAuditaEstadosCircuitosEntity);
                if (oldUserIdOfAuditaEstadosCircuitosEntityCollectionAuditaEstadosCircuitosEntity != null) {
                    oldUserIdOfAuditaEstadosCircuitosEntityCollectionAuditaEstadosCircuitosEntity.getAuditaEstadosCircuitosEntityCollection().remove(auditaEstadosCircuitosEntityCollectionAuditaEstadosCircuitosEntity);
                    oldUserIdOfAuditaEstadosCircuitosEntityCollectionAuditaEstadosCircuitosEntity = em.merge(oldUserIdOfAuditaEstadosCircuitosEntityCollectionAuditaEstadosCircuitosEntity);
                }
            }
            for (UsuarioPreferenciasEntity usuarioPreferenciasEntityCollectionUsuarioPreferenciasEntity : websiteUserEntity.getUsuarioPreferenciasEntityCollection()) {
                WebsiteUserEntity oldWebsiteUserEntityOfUsuarioPreferenciasEntityCollectionUsuarioPreferenciasEntity = usuarioPreferenciasEntityCollectionUsuarioPreferenciasEntity.getWebsiteUserEntity();
                usuarioPreferenciasEntityCollectionUsuarioPreferenciasEntity.setWebsiteUserEntity(websiteUserEntity);
                usuarioPreferenciasEntityCollectionUsuarioPreferenciasEntity = em.merge(usuarioPreferenciasEntityCollectionUsuarioPreferenciasEntity);
                if (oldWebsiteUserEntityOfUsuarioPreferenciasEntityCollectionUsuarioPreferenciasEntity != null) {
                    oldWebsiteUserEntityOfUsuarioPreferenciasEntityCollectionUsuarioPreferenciasEntity.getUsuarioPreferenciasEntityCollection().remove(usuarioPreferenciasEntityCollectionUsuarioPreferenciasEntity);
                    oldWebsiteUserEntityOfUsuarioPreferenciasEntityCollectionUsuarioPreferenciasEntity = em.merge(oldWebsiteUserEntityOfUsuarioPreferenciasEntityCollectionUsuarioPreferenciasEntity);
                }
            }
            for (UsuarioRolesEntity usuarioRolesEntityCollectionUsuarioRolesEntity : websiteUserEntity.getUsuarioRolesEntityCollection()) {
                WebsiteUserEntity oldWebsiteUserEntityOfUsuarioRolesEntityCollectionUsuarioRolesEntity = usuarioRolesEntityCollectionUsuarioRolesEntity.getWebsiteUserEntity();
                usuarioRolesEntityCollectionUsuarioRolesEntity.setWebsiteUserEntity(websiteUserEntity);
                usuarioRolesEntityCollectionUsuarioRolesEntity = em.merge(usuarioRolesEntityCollectionUsuarioRolesEntity);
                if (oldWebsiteUserEntityOfUsuarioRolesEntityCollectionUsuarioRolesEntity != null) {
                    oldWebsiteUserEntityOfUsuarioRolesEntityCollectionUsuarioRolesEntity.getUsuarioRolesEntityCollection().remove(usuarioRolesEntityCollectionUsuarioRolesEntity);
                    oldWebsiteUserEntityOfUsuarioRolesEntityCollectionUsuarioRolesEntity = em.merge(oldWebsiteUserEntityOfUsuarioRolesEntityCollectionUsuarioRolesEntity);
                }
            }
            for (AccesoMenuEntity accesoMenuEntityCollectionAccesoMenuEntity : websiteUserEntity.getAccesoMenuEntityCollection()) {
                WebsiteUserEntity oldUserIdOfAccesoMenuEntityCollectionAccesoMenuEntity = accesoMenuEntityCollectionAccesoMenuEntity.getUserId();
                accesoMenuEntityCollectionAccesoMenuEntity.setUserId(websiteUserEntity);
                accesoMenuEntityCollectionAccesoMenuEntity = em.merge(accesoMenuEntityCollectionAccesoMenuEntity);
                if (oldUserIdOfAccesoMenuEntityCollectionAccesoMenuEntity != null) {
                    oldUserIdOfAccesoMenuEntityCollectionAccesoMenuEntity.getAccesoMenuEntityCollection().remove(accesoMenuEntityCollectionAccesoMenuEntity);
                    oldUserIdOfAccesoMenuEntityCollectionAccesoMenuEntity = em.merge(oldUserIdOfAccesoMenuEntityCollectionAccesoMenuEntity);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(WebsiteUserEntity websiteUserEntity) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            WebsiteUserEntity persistentWebsiteUserEntity = em.find(WebsiteUserEntity.class, websiteUserEntity.getUserId());
            Set<AuditaEstadosCircuitosEntity> auditaEstadosCircuitosEntityCollectionOld = persistentWebsiteUserEntity.getAuditaEstadosCircuitosEntityCollection();
            Set<AuditaEstadosCircuitosEntity> auditaEstadosCircuitosEntityCollectionNew = websiteUserEntity.getAuditaEstadosCircuitosEntityCollection();
            Set<UsuarioPreferenciasEntity> usuarioPreferenciasEntityCollectionOld = persistentWebsiteUserEntity.getUsuarioPreferenciasEntityCollection();
            Set<UsuarioPreferenciasEntity> usuarioPreferenciasEntityCollectionNew = websiteUserEntity.getUsuarioPreferenciasEntityCollection();
            Set<UsuarioRolesEntity> usuarioRolesEntityCollectionOld = persistentWebsiteUserEntity.getUsuarioRolesEntityCollection();
            Set<UsuarioRolesEntity> usuarioRolesEntityCollectionNew = websiteUserEntity.getUsuarioRolesEntityCollection();
            Set<AccesoMenuEntity> accesoMenuEntityCollectionOld = persistentWebsiteUserEntity.getAccesoMenuEntityCollection();
            Set<AccesoMenuEntity> accesoMenuEntityCollectionNew = websiteUserEntity.getAccesoMenuEntityCollection();
            List<String> illegalOrphanMessages = null;
            for (AuditaEstadosCircuitosEntity auditaEstadosCircuitosEntityCollectionOldAuditaEstadosCircuitosEntity : auditaEstadosCircuitosEntityCollectionOld) {
                if (!auditaEstadosCircuitosEntityCollectionNew.contains(auditaEstadosCircuitosEntityCollectionOldAuditaEstadosCircuitosEntity)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AuditaEstadosCircuitosEntity " + auditaEstadosCircuitosEntityCollectionOldAuditaEstadosCircuitosEntity + " since its userId field is not nullable.");
                }
            }
            for (UsuarioPreferenciasEntity usuarioPreferenciasEntityCollectionOldUsuarioPreferenciasEntity : usuarioPreferenciasEntityCollectionOld) {
                if (!usuarioPreferenciasEntityCollectionNew.contains(usuarioPreferenciasEntityCollectionOldUsuarioPreferenciasEntity)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UsuarioPreferenciasEntity " + usuarioPreferenciasEntityCollectionOldUsuarioPreferenciasEntity + " since its websiteUserEntity field is not nullable.");
                }
            }
            for (UsuarioRolesEntity usuarioRolesEntityCollectionOldUsuarioRolesEntity : usuarioRolesEntityCollectionOld) {
                if (!usuarioRolesEntityCollectionNew.contains(usuarioRolesEntityCollectionOldUsuarioRolesEntity)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UsuarioRolesEntity " + usuarioRolesEntityCollectionOldUsuarioRolesEntity + " since its websiteUserEntity field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Set<AuditaEstadosCircuitosEntity> attachedAuditaEstadosCircuitosEntityCollectionNew = new HashSet<AuditaEstadosCircuitosEntity>();
            for (AuditaEstadosCircuitosEntity auditaEstadosCircuitosEntityCollectionNewAuditaEstadosCircuitosEntityToAttach : auditaEstadosCircuitosEntityCollectionNew) {
                auditaEstadosCircuitosEntityCollectionNewAuditaEstadosCircuitosEntityToAttach = em.getReference(auditaEstadosCircuitosEntityCollectionNewAuditaEstadosCircuitosEntityToAttach.getClass(), auditaEstadosCircuitosEntityCollectionNewAuditaEstadosCircuitosEntityToAttach.getAuditaId());
                attachedAuditaEstadosCircuitosEntityCollectionNew.add(auditaEstadosCircuitosEntityCollectionNewAuditaEstadosCircuitosEntityToAttach);
            }
            auditaEstadosCircuitosEntityCollectionNew = attachedAuditaEstadosCircuitosEntityCollectionNew;
            websiteUserEntity.setAuditaEstadosCircuitosEntityCollection(auditaEstadosCircuitosEntityCollectionNew);
            Set<UsuarioPreferenciasEntity> attachedUsuarioPreferenciasEntityCollectionNew = new HashSet<UsuarioPreferenciasEntity>();
            for (UsuarioPreferenciasEntity usuarioPreferenciasEntityCollectionNewUsuarioPreferenciasEntityToAttach : usuarioPreferenciasEntityCollectionNew) {
                usuarioPreferenciasEntityCollectionNewUsuarioPreferenciasEntityToAttach = em.getReference(usuarioPreferenciasEntityCollectionNewUsuarioPreferenciasEntityToAttach.getClass(), usuarioPreferenciasEntityCollectionNewUsuarioPreferenciasEntityToAttach.getUsuarioPreferenciasEntityPK());
                attachedUsuarioPreferenciasEntityCollectionNew.add(usuarioPreferenciasEntityCollectionNewUsuarioPreferenciasEntityToAttach);
            }
            usuarioPreferenciasEntityCollectionNew = attachedUsuarioPreferenciasEntityCollectionNew;
            websiteUserEntity.setUsuarioPreferenciasEntityCollection(usuarioPreferenciasEntityCollectionNew);
            Set<UsuarioRolesEntity> attachedUsuarioRolesEntityCollectionNew = new HashSet<UsuarioRolesEntity>();
            for (UsuarioRolesEntity usuarioRolesEntityCollectionNewUsuarioRolesEntityToAttach : usuarioRolesEntityCollectionNew) {
                usuarioRolesEntityCollectionNewUsuarioRolesEntityToAttach = em.getReference(usuarioRolesEntityCollectionNewUsuarioRolesEntityToAttach.getClass(), usuarioRolesEntityCollectionNewUsuarioRolesEntityToAttach.getUsuarioRolesEntityPK());
                attachedUsuarioRolesEntityCollectionNew.add(usuarioRolesEntityCollectionNewUsuarioRolesEntityToAttach);
            }
            usuarioRolesEntityCollectionNew = attachedUsuarioRolesEntityCollectionNew;
            websiteUserEntity.setUsuarioRolesEntityCollection(usuarioRolesEntityCollectionNew);
            Set<AccesoMenuEntity> attachedAccesoMenuEntityCollectionNew = new HashSet<AccesoMenuEntity>();
            for (AccesoMenuEntity accesoMenuEntityCollectionNewAccesoMenuEntityToAttach : accesoMenuEntityCollectionNew) {
                accesoMenuEntityCollectionNewAccesoMenuEntityToAttach = em.getReference(accesoMenuEntityCollectionNewAccesoMenuEntityToAttach.getClass(), accesoMenuEntityCollectionNewAccesoMenuEntityToAttach.getAccesoMenuId());
                attachedAccesoMenuEntityCollectionNew.add(accesoMenuEntityCollectionNewAccesoMenuEntityToAttach);
            }
            accesoMenuEntityCollectionNew = attachedAccesoMenuEntityCollectionNew;
            websiteUserEntity.setAccesoMenuEntityCollection(accesoMenuEntityCollectionNew);
            websiteUserEntity = em.merge(websiteUserEntity);
            for (AuditaEstadosCircuitosEntity auditaEstadosCircuitosEntityCollectionNewAuditaEstadosCircuitosEntity : auditaEstadosCircuitosEntityCollectionNew) {
                if (!auditaEstadosCircuitosEntityCollectionOld.contains(auditaEstadosCircuitosEntityCollectionNewAuditaEstadosCircuitosEntity)) {
                    WebsiteUserEntity oldUserIdOfAuditaEstadosCircuitosEntityCollectionNewAuditaEstadosCircuitosEntity = auditaEstadosCircuitosEntityCollectionNewAuditaEstadosCircuitosEntity.getUserId();
                    auditaEstadosCircuitosEntityCollectionNewAuditaEstadosCircuitosEntity.setUserId(websiteUserEntity);
                    auditaEstadosCircuitosEntityCollectionNewAuditaEstadosCircuitosEntity = em.merge(auditaEstadosCircuitosEntityCollectionNewAuditaEstadosCircuitosEntity);
                    if (oldUserIdOfAuditaEstadosCircuitosEntityCollectionNewAuditaEstadosCircuitosEntity != null && !oldUserIdOfAuditaEstadosCircuitosEntityCollectionNewAuditaEstadosCircuitosEntity.equals(websiteUserEntity)) {
                        oldUserIdOfAuditaEstadosCircuitosEntityCollectionNewAuditaEstadosCircuitosEntity.getAuditaEstadosCircuitosEntityCollection().remove(auditaEstadosCircuitosEntityCollectionNewAuditaEstadosCircuitosEntity);
                        oldUserIdOfAuditaEstadosCircuitosEntityCollectionNewAuditaEstadosCircuitosEntity = em.merge(oldUserIdOfAuditaEstadosCircuitosEntityCollectionNewAuditaEstadosCircuitosEntity);
                    }
                }
            }
            for (UsuarioPreferenciasEntity usuarioPreferenciasEntityCollectionNewUsuarioPreferenciasEntity : usuarioPreferenciasEntityCollectionNew) {
                if (!usuarioPreferenciasEntityCollectionOld.contains(usuarioPreferenciasEntityCollectionNewUsuarioPreferenciasEntity)) {
                    WebsiteUserEntity oldWebsiteUserEntityOfUsuarioPreferenciasEntityCollectionNewUsuarioPreferenciasEntity = usuarioPreferenciasEntityCollectionNewUsuarioPreferenciasEntity.getWebsiteUserEntity();
                    usuarioPreferenciasEntityCollectionNewUsuarioPreferenciasEntity.setWebsiteUserEntity(websiteUserEntity);
                    usuarioPreferenciasEntityCollectionNewUsuarioPreferenciasEntity = em.merge(usuarioPreferenciasEntityCollectionNewUsuarioPreferenciasEntity);
                    if (oldWebsiteUserEntityOfUsuarioPreferenciasEntityCollectionNewUsuarioPreferenciasEntity != null && !oldWebsiteUserEntityOfUsuarioPreferenciasEntityCollectionNewUsuarioPreferenciasEntity.equals(websiteUserEntity)) {
                        oldWebsiteUserEntityOfUsuarioPreferenciasEntityCollectionNewUsuarioPreferenciasEntity.getUsuarioPreferenciasEntityCollection().remove(usuarioPreferenciasEntityCollectionNewUsuarioPreferenciasEntity);
                        oldWebsiteUserEntityOfUsuarioPreferenciasEntityCollectionNewUsuarioPreferenciasEntity = em.merge(oldWebsiteUserEntityOfUsuarioPreferenciasEntityCollectionNewUsuarioPreferenciasEntity);
                    }
                }
            }
            for (UsuarioRolesEntity usuarioRolesEntityCollectionNewUsuarioRolesEntity : usuarioRolesEntityCollectionNew) {
                if (!usuarioRolesEntityCollectionOld.contains(usuarioRolesEntityCollectionNewUsuarioRolesEntity)) {
                    WebsiteUserEntity oldWebsiteUserEntityOfUsuarioRolesEntityCollectionNewUsuarioRolesEntity = usuarioRolesEntityCollectionNewUsuarioRolesEntity.getWebsiteUserEntity();
                    usuarioRolesEntityCollectionNewUsuarioRolesEntity.setWebsiteUserEntity(websiteUserEntity);
                    usuarioRolesEntityCollectionNewUsuarioRolesEntity = em.merge(usuarioRolesEntityCollectionNewUsuarioRolesEntity);
                    if (oldWebsiteUserEntityOfUsuarioRolesEntityCollectionNewUsuarioRolesEntity != null && !oldWebsiteUserEntityOfUsuarioRolesEntityCollectionNewUsuarioRolesEntity.equals(websiteUserEntity)) {
                        oldWebsiteUserEntityOfUsuarioRolesEntityCollectionNewUsuarioRolesEntity.getUsuarioRolesEntityCollection().remove(usuarioRolesEntityCollectionNewUsuarioRolesEntity);
                        oldWebsiteUserEntityOfUsuarioRolesEntityCollectionNewUsuarioRolesEntity = em.merge(oldWebsiteUserEntityOfUsuarioRolesEntityCollectionNewUsuarioRolesEntity);
                    }
                }
            }
            for (AccesoMenuEntity accesoMenuEntityCollectionOldAccesoMenuEntity : accesoMenuEntityCollectionOld) {
                if (!accesoMenuEntityCollectionNew.contains(accesoMenuEntityCollectionOldAccesoMenuEntity)) {
                    accesoMenuEntityCollectionOldAccesoMenuEntity.setUserId(null);
                    accesoMenuEntityCollectionOldAccesoMenuEntity = em.merge(accesoMenuEntityCollectionOldAccesoMenuEntity);
                }
            }
            for (AccesoMenuEntity accesoMenuEntityCollectionNewAccesoMenuEntity : accesoMenuEntityCollectionNew) {
                if (!accesoMenuEntityCollectionOld.contains(accesoMenuEntityCollectionNewAccesoMenuEntity)) {
                    WebsiteUserEntity oldUserIdOfAccesoMenuEntityCollectionNewAccesoMenuEntity = accesoMenuEntityCollectionNewAccesoMenuEntity.getUserId();
                    accesoMenuEntityCollectionNewAccesoMenuEntity.setUserId(websiteUserEntity);
                    accesoMenuEntityCollectionNewAccesoMenuEntity = em.merge(accesoMenuEntityCollectionNewAccesoMenuEntity);
                    if (oldUserIdOfAccesoMenuEntityCollectionNewAccesoMenuEntity != null && !oldUserIdOfAccesoMenuEntityCollectionNewAccesoMenuEntity.equals(websiteUserEntity)) {
                        oldUserIdOfAccesoMenuEntityCollectionNewAccesoMenuEntity.getAccesoMenuEntityCollection().remove(accesoMenuEntityCollectionNewAccesoMenuEntity);
                        oldUserIdOfAccesoMenuEntityCollectionNewAccesoMenuEntity = em.merge(oldUserIdOfAccesoMenuEntityCollectionNewAccesoMenuEntity);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = websiteUserEntity.getUserId();
                if (findWebsiteUserEntity(id) == null) {
                    throw new NonexistentEntityException("The websiteUserEntity with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            WebsiteUserEntity websiteUserEntity;
            try {
                websiteUserEntity = em.getReference(WebsiteUserEntity.class, id);
                websiteUserEntity.getUserId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The websiteUserEntity with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<AuditaEstadosCircuitosEntity> auditaEstadosCircuitosEntityCollectionOrphanCheck = websiteUserEntity.getAuditaEstadosCircuitosEntityCollection();
            for (AuditaEstadosCircuitosEntity auditaEstadosCircuitosEntityCollectionOrphanCheckAuditaEstadosCircuitosEntity : auditaEstadosCircuitosEntityCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This WebsiteUserEntity (" + websiteUserEntity + ") cannot be destroyed since the AuditaEstadosCircuitosEntity " + auditaEstadosCircuitosEntityCollectionOrphanCheckAuditaEstadosCircuitosEntity + " in its auditaEstadosCircuitosEntityCollection field has a non-nullable userId field.");
            }
            Set<UsuarioPreferenciasEntity> usuarioPreferenciasEntityCollectionOrphanCheck = websiteUserEntity.getUsuarioPreferenciasEntityCollection();
            for (UsuarioPreferenciasEntity usuarioPreferenciasEntityCollectionOrphanCheckUsuarioPreferenciasEntity : usuarioPreferenciasEntityCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This WebsiteUserEntity (" + websiteUserEntity + ") cannot be destroyed since the UsuarioPreferenciasEntity " + usuarioPreferenciasEntityCollectionOrphanCheckUsuarioPreferenciasEntity + " in its usuarioPreferenciasEntityCollection field has a non-nullable websiteUserEntity field.");
            }
            Set<UsuarioRolesEntity> usuarioRolesEntityCollectionOrphanCheck = websiteUserEntity.getUsuarioRolesEntityCollection();
            for (UsuarioRolesEntity usuarioRolesEntityCollectionOrphanCheckUsuarioRolesEntity : usuarioRolesEntityCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This WebsiteUserEntity (" + websiteUserEntity + ") cannot be destroyed since the UsuarioRolesEntity " + usuarioRolesEntityCollectionOrphanCheckUsuarioRolesEntity + " in its usuarioRolesEntityCollection field has a non-nullable websiteUserEntity field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Set<AccesoMenuEntity> accesoMenuEntityCollection = websiteUserEntity.getAccesoMenuEntityCollection();
            for (AccesoMenuEntity accesoMenuEntityCollectionAccesoMenuEntity : accesoMenuEntityCollection) {
                accesoMenuEntityCollectionAccesoMenuEntity.setUserId(null);
                accesoMenuEntityCollectionAccesoMenuEntity = em.merge(accesoMenuEntityCollectionAccesoMenuEntity);
            }
            em.remove(websiteUserEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<WebsiteUserEntity> findWebsiteUserEntityEntities() {
        return findWebsiteUserEntityEntities(true, -1, -1);
    }

    public List<WebsiteUserEntity> findWebsiteUserEntityEntities(int maxResults, int firstResult) {
        return findWebsiteUserEntityEntities(false, maxResults, firstResult);
    }

    private List<WebsiteUserEntity> findWebsiteUserEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from WebsiteUserEntity as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public WebsiteUserEntity findWebsiteUserEntity(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(WebsiteUserEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getWebsiteUserEntityCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from WebsiteUserEntity as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
