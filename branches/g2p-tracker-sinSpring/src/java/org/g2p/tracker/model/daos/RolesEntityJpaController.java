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
import org.g2p.tracker.model.entities.RolesEntity;
import org.g2p.tracker.model.entities.UsuarioRolesEntity;
import java.util.HashSet;
import java.util.Set;
import org.g2p.tracker.model.entities.AccesoMenuEntity;
import java.util.ArrayList;

/**
 *
 * @author Administrador
 */
public class RolesEntityJpaController {

    public RolesEntityJpaController() {
        emf = Persistence.createEntityManagerFactory("g2p-tracker-sinSpringPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RolesEntity rolesEntity) {
        if (rolesEntity.getUsuarioRolesEntityCollection() == null) {
            rolesEntity.setUsuarioRolesEntityCollection(new HashSet<UsuarioRolesEntity>());
        }
        if (rolesEntity.getAccesoMenuEntityCollection() == null) {
            rolesEntity.setAccesoMenuEntityCollection(new HashSet<AccesoMenuEntity>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<UsuarioRolesEntity> attachedUsuarioRolesEntityCollection = new HashSet<UsuarioRolesEntity>();
            for (UsuarioRolesEntity usuarioRolesEntityCollectionUsuarioRolesEntityToAttach : rolesEntity.getUsuarioRolesEntityCollection()) {
                usuarioRolesEntityCollectionUsuarioRolesEntityToAttach = em.getReference(usuarioRolesEntityCollectionUsuarioRolesEntityToAttach.getClass(), usuarioRolesEntityCollectionUsuarioRolesEntityToAttach.getUsuarioRolesEntityPK());
                attachedUsuarioRolesEntityCollection.add(usuarioRolesEntityCollectionUsuarioRolesEntityToAttach);
            }
            rolesEntity.setUsuarioRolesEntityCollection(attachedUsuarioRolesEntityCollection);
            Set<AccesoMenuEntity> attachedAccesoMenuEntityCollection = new HashSet<AccesoMenuEntity>();
            for (AccesoMenuEntity accesoMenuEntityCollectionAccesoMenuEntityToAttach : rolesEntity.getAccesoMenuEntityCollection()) {
                accesoMenuEntityCollectionAccesoMenuEntityToAttach = em.getReference(accesoMenuEntityCollectionAccesoMenuEntityToAttach.getClass(), accesoMenuEntityCollectionAccesoMenuEntityToAttach.getAccesoMenuId());
                attachedAccesoMenuEntityCollection.add(accesoMenuEntityCollectionAccesoMenuEntityToAttach);
            }
            rolesEntity.setAccesoMenuEntityCollection(attachedAccesoMenuEntityCollection);
            em.persist(rolesEntity);
            for (UsuarioRolesEntity usuarioRolesEntityCollectionUsuarioRolesEntity : rolesEntity.getUsuarioRolesEntityCollection()) {
                RolesEntity oldRolesEntityOfUsuarioRolesEntityCollectionUsuarioRolesEntity = usuarioRolesEntityCollectionUsuarioRolesEntity.getRolesEntity();
                usuarioRolesEntityCollectionUsuarioRolesEntity.setRolesEntity(rolesEntity);
                usuarioRolesEntityCollectionUsuarioRolesEntity = em.merge(usuarioRolesEntityCollectionUsuarioRolesEntity);
                if (oldRolesEntityOfUsuarioRolesEntityCollectionUsuarioRolesEntity != null) {
                    oldRolesEntityOfUsuarioRolesEntityCollectionUsuarioRolesEntity.getUsuarioRolesEntityCollection().remove(usuarioRolesEntityCollectionUsuarioRolesEntity);
                    oldRolesEntityOfUsuarioRolesEntityCollectionUsuarioRolesEntity = em.merge(oldRolesEntityOfUsuarioRolesEntityCollectionUsuarioRolesEntity);
                }
            }
            for (AccesoMenuEntity accesoMenuEntityCollectionAccesoMenuEntity : rolesEntity.getAccesoMenuEntityCollection()) {
                RolesEntity oldRolIdOfAccesoMenuEntityCollectionAccesoMenuEntity = accesoMenuEntityCollectionAccesoMenuEntity.getRolId();
                accesoMenuEntityCollectionAccesoMenuEntity.setRolId(rolesEntity);
                accesoMenuEntityCollectionAccesoMenuEntity = em.merge(accesoMenuEntityCollectionAccesoMenuEntity);
                if (oldRolIdOfAccesoMenuEntityCollectionAccesoMenuEntity != null) {
                    oldRolIdOfAccesoMenuEntityCollectionAccesoMenuEntity.getAccesoMenuEntityCollection().remove(accesoMenuEntityCollectionAccesoMenuEntity);
                    oldRolIdOfAccesoMenuEntityCollectionAccesoMenuEntity = em.merge(oldRolIdOfAccesoMenuEntityCollectionAccesoMenuEntity);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RolesEntity rolesEntity) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RolesEntity persistentRolesEntity = em.find(RolesEntity.class, rolesEntity.getRolId());
            Set<UsuarioRolesEntity> usuarioRolesEntityCollectionOld = persistentRolesEntity.getUsuarioRolesEntityCollection();
            Set<UsuarioRolesEntity> usuarioRolesEntityCollectionNew = rolesEntity.getUsuarioRolesEntityCollection();
            Set<AccesoMenuEntity> accesoMenuEntityCollectionOld = persistentRolesEntity.getAccesoMenuEntityCollection();
            Set<AccesoMenuEntity> accesoMenuEntityCollectionNew = rolesEntity.getAccesoMenuEntityCollection();
            List<String> illegalOrphanMessages = null;
            for (UsuarioRolesEntity usuarioRolesEntityCollectionOldUsuarioRolesEntity : usuarioRolesEntityCollectionOld) {
                if (!usuarioRolesEntityCollectionNew.contains(usuarioRolesEntityCollectionOldUsuarioRolesEntity)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UsuarioRolesEntity " + usuarioRolesEntityCollectionOldUsuarioRolesEntity + " since its rolesEntity field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Set<UsuarioRolesEntity> attachedUsuarioRolesEntityCollectionNew = new HashSet<UsuarioRolesEntity>();
            for (UsuarioRolesEntity usuarioRolesEntityCollectionNewUsuarioRolesEntityToAttach : usuarioRolesEntityCollectionNew) {
                usuarioRolesEntityCollectionNewUsuarioRolesEntityToAttach = em.getReference(usuarioRolesEntityCollectionNewUsuarioRolesEntityToAttach.getClass(), usuarioRolesEntityCollectionNewUsuarioRolesEntityToAttach.getUsuarioRolesEntityPK());
                attachedUsuarioRolesEntityCollectionNew.add(usuarioRolesEntityCollectionNewUsuarioRolesEntityToAttach);
            }
            usuarioRolesEntityCollectionNew = attachedUsuarioRolesEntityCollectionNew;
            rolesEntity.setUsuarioRolesEntityCollection(usuarioRolesEntityCollectionNew);
            Set<AccesoMenuEntity> attachedAccesoMenuEntityCollectionNew = new HashSet<AccesoMenuEntity>();
            for (AccesoMenuEntity accesoMenuEntityCollectionNewAccesoMenuEntityToAttach : accesoMenuEntityCollectionNew) {
                accesoMenuEntityCollectionNewAccesoMenuEntityToAttach = em.getReference(accesoMenuEntityCollectionNewAccesoMenuEntityToAttach.getClass(), accesoMenuEntityCollectionNewAccesoMenuEntityToAttach.getAccesoMenuId());
                attachedAccesoMenuEntityCollectionNew.add(accesoMenuEntityCollectionNewAccesoMenuEntityToAttach);
            }
            accesoMenuEntityCollectionNew = attachedAccesoMenuEntityCollectionNew;
            rolesEntity.setAccesoMenuEntityCollection(accesoMenuEntityCollectionNew);
            em.refresh(rolesEntity);
            rolesEntity = em.merge(rolesEntity);
            for (UsuarioRolesEntity usuarioRolesEntityCollectionNewUsuarioRolesEntity : usuarioRolesEntityCollectionNew) {
                if (!usuarioRolesEntityCollectionOld.contains(usuarioRolesEntityCollectionNewUsuarioRolesEntity)) {
                    RolesEntity oldRolesEntityOfUsuarioRolesEntityCollectionNewUsuarioRolesEntity = usuarioRolesEntityCollectionNewUsuarioRolesEntity.getRolesEntity();
                    usuarioRolesEntityCollectionNewUsuarioRolesEntity.setRolesEntity(rolesEntity);
                    usuarioRolesEntityCollectionNewUsuarioRolesEntity = em.merge(usuarioRolesEntityCollectionNewUsuarioRolesEntity);
                    if (oldRolesEntityOfUsuarioRolesEntityCollectionNewUsuarioRolesEntity != null && !oldRolesEntityOfUsuarioRolesEntityCollectionNewUsuarioRolesEntity.equals(rolesEntity)) {
                        oldRolesEntityOfUsuarioRolesEntityCollectionNewUsuarioRolesEntity.getUsuarioRolesEntityCollection().remove(usuarioRolesEntityCollectionNewUsuarioRolesEntity);
                        oldRolesEntityOfUsuarioRolesEntityCollectionNewUsuarioRolesEntity = em.merge(oldRolesEntityOfUsuarioRolesEntityCollectionNewUsuarioRolesEntity);
                    }
                }
            }
            for (AccesoMenuEntity accesoMenuEntityCollectionOldAccesoMenuEntity : accesoMenuEntityCollectionOld) {
                if (!accesoMenuEntityCollectionNew.contains(accesoMenuEntityCollectionOldAccesoMenuEntity)) {
                    accesoMenuEntityCollectionOldAccesoMenuEntity.setRolId(null);
                    accesoMenuEntityCollectionOldAccesoMenuEntity = em.merge(accesoMenuEntityCollectionOldAccesoMenuEntity);
                }
            }
            for (AccesoMenuEntity accesoMenuEntityCollectionNewAccesoMenuEntity : accesoMenuEntityCollectionNew) {
                if (!accesoMenuEntityCollectionOld.contains(accesoMenuEntityCollectionNewAccesoMenuEntity)) {
                    RolesEntity oldRolIdOfAccesoMenuEntityCollectionNewAccesoMenuEntity = accesoMenuEntityCollectionNewAccesoMenuEntity.getRolId();
                    accesoMenuEntityCollectionNewAccesoMenuEntity.setRolId(rolesEntity);
                    accesoMenuEntityCollectionNewAccesoMenuEntity = em.merge(accesoMenuEntityCollectionNewAccesoMenuEntity);
                    if (oldRolIdOfAccesoMenuEntityCollectionNewAccesoMenuEntity != null && !oldRolIdOfAccesoMenuEntityCollectionNewAccesoMenuEntity.equals(rolesEntity)) {
                        oldRolIdOfAccesoMenuEntityCollectionNewAccesoMenuEntity.getAccesoMenuEntityCollection().remove(accesoMenuEntityCollectionNewAccesoMenuEntity);
                        oldRolIdOfAccesoMenuEntityCollectionNewAccesoMenuEntity = em.merge(oldRolIdOfAccesoMenuEntityCollectionNewAccesoMenuEntity);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rolesEntity.getRolId();
                if (findRolesEntity(id) == null) {
                    throw new NonexistentEntityException("The rolesEntity with id " + id + " no longer exists.");
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
            RolesEntity rolesEntity;
            try {
                rolesEntity = em.getReference(RolesEntity.class, id);
                rolesEntity.getRolId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rolesEntity with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<UsuarioRolesEntity> usuarioRolesEntityCollectionOrphanCheck = rolesEntity.getUsuarioRolesEntityCollection();
            for (UsuarioRolesEntity usuarioRolesEntityCollectionOrphanCheckUsuarioRolesEntity : usuarioRolesEntityCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This RolesEntity (" + rolesEntity + ") cannot be destroyed since the UsuarioRolesEntity " + usuarioRolesEntityCollectionOrphanCheckUsuarioRolesEntity + " in its usuarioRolesEntityCollection field has a non-nullable rolesEntity field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Set<AccesoMenuEntity> accesoMenuEntityCollection = rolesEntity.getAccesoMenuEntityCollection();
            for (AccesoMenuEntity accesoMenuEntityCollectionAccesoMenuEntity : accesoMenuEntityCollection) {
                accesoMenuEntityCollectionAccesoMenuEntity.setRolId(null);
                accesoMenuEntityCollectionAccesoMenuEntity = em.merge(accesoMenuEntityCollectionAccesoMenuEntity);
            }
            em.remove(rolesEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RolesEntity> findRolesEntityEntities() {
        return findRolesEntityEntities(true, -1, -1);
    }

    public List<RolesEntity> findRolesEntityEntities(int maxResults, int firstResult) {
        return findRolesEntityEntities(false, maxResults, firstResult);
    }

    private List<RolesEntity> findRolesEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from RolesEntity as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public RolesEntity findRolesEntity(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RolesEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getRolesEntityCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from RolesEntity as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
