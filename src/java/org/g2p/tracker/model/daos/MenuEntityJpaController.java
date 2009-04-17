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
import org.g2p.tracker.model.entities.AccesoMenuEntity;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import org.g2p.tracker.model.entities.MenuEntity;

/**
 *
 * @author Administrador
 */
public class MenuEntityJpaController {

    public MenuEntityJpaController() {
        emf = Persistence.createEntityManagerFactory("g2p-tracker-sinSpringPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MenuEntity menuEntity) {
        if (menuEntity.getAccesoMenuEntityCollection() == null) {
            menuEntity.setAccesoMenuEntityCollection(new HashSet<AccesoMenuEntity>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<AccesoMenuEntity> attachedAccesoMenuEntityCollection = new HashSet<AccesoMenuEntity>();
            for (AccesoMenuEntity accesoMenuEntityCollectionAccesoMenuEntityToAttach : menuEntity.getAccesoMenuEntityCollection()) {
                accesoMenuEntityCollectionAccesoMenuEntityToAttach = em.getReference(accesoMenuEntityCollectionAccesoMenuEntityToAttach.getClass(), accesoMenuEntityCollectionAccesoMenuEntityToAttach.getAccesoMenuId());
                attachedAccesoMenuEntityCollection.add(accesoMenuEntityCollectionAccesoMenuEntityToAttach);
            }
            menuEntity.setAccesoMenuEntityCollection(attachedAccesoMenuEntityCollection);
            em.persist(menuEntity);
            for (AccesoMenuEntity accesoMenuEntityCollectionAccesoMenuEntity : menuEntity.getAccesoMenuEntityCollection()) {
                MenuEntity oldMenuIdOfAccesoMenuEntityCollectionAccesoMenuEntity = accesoMenuEntityCollectionAccesoMenuEntity.getMenuId();
                accesoMenuEntityCollectionAccesoMenuEntity.setMenuId(menuEntity);
                accesoMenuEntityCollectionAccesoMenuEntity = em.merge(accesoMenuEntityCollectionAccesoMenuEntity);
                if (oldMenuIdOfAccesoMenuEntityCollectionAccesoMenuEntity != null) {
                    oldMenuIdOfAccesoMenuEntityCollectionAccesoMenuEntity.getAccesoMenuEntityCollection().remove(accesoMenuEntityCollectionAccesoMenuEntity);
                    oldMenuIdOfAccesoMenuEntityCollectionAccesoMenuEntity = em.merge(oldMenuIdOfAccesoMenuEntityCollectionAccesoMenuEntity);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MenuEntity menuEntity) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MenuEntity persistentMenuEntity = em.find(MenuEntity.class, menuEntity.getMenuId());
            Set<AccesoMenuEntity> accesoMenuEntityCollectionOld = persistentMenuEntity.getAccesoMenuEntityCollection();
            Set<AccesoMenuEntity> accesoMenuEntityCollectionNew = menuEntity.getAccesoMenuEntityCollection();
            List<String> illegalOrphanMessages = null;
            for (AccesoMenuEntity accesoMenuEntityCollectionOldAccesoMenuEntity : accesoMenuEntityCollectionOld) {
                if (!accesoMenuEntityCollectionNew.contains(accesoMenuEntityCollectionOldAccesoMenuEntity)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AccesoMenuEntity " + accesoMenuEntityCollectionOldAccesoMenuEntity + " since its menuId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Set<AccesoMenuEntity> attachedAccesoMenuEntityCollectionNew = new HashSet<AccesoMenuEntity>();
            for (AccesoMenuEntity accesoMenuEntityCollectionNewAccesoMenuEntityToAttach : accesoMenuEntityCollectionNew) {
                accesoMenuEntityCollectionNewAccesoMenuEntityToAttach = em.getReference(accesoMenuEntityCollectionNewAccesoMenuEntityToAttach.getClass(), accesoMenuEntityCollectionNewAccesoMenuEntityToAttach.getAccesoMenuId());
                attachedAccesoMenuEntityCollectionNew.add(accesoMenuEntityCollectionNewAccesoMenuEntityToAttach);
            }
            accesoMenuEntityCollectionNew = attachedAccesoMenuEntityCollectionNew;
            menuEntity.setAccesoMenuEntityCollection(accesoMenuEntityCollectionNew);
            menuEntity = em.merge(menuEntity);
            for (AccesoMenuEntity accesoMenuEntityCollectionNewAccesoMenuEntity : accesoMenuEntityCollectionNew) {
                if (!accesoMenuEntityCollectionOld.contains(accesoMenuEntityCollectionNewAccesoMenuEntity)) {
                    MenuEntity oldMenuIdOfAccesoMenuEntityCollectionNewAccesoMenuEntity = accesoMenuEntityCollectionNewAccesoMenuEntity.getMenuId();
                    accesoMenuEntityCollectionNewAccesoMenuEntity.setMenuId(menuEntity);
                    accesoMenuEntityCollectionNewAccesoMenuEntity = em.merge(accesoMenuEntityCollectionNewAccesoMenuEntity);
                    if (oldMenuIdOfAccesoMenuEntityCollectionNewAccesoMenuEntity != null && !oldMenuIdOfAccesoMenuEntityCollectionNewAccesoMenuEntity.equals(menuEntity)) {
                        oldMenuIdOfAccesoMenuEntityCollectionNewAccesoMenuEntity.getAccesoMenuEntityCollection().remove(accesoMenuEntityCollectionNewAccesoMenuEntity);
                        oldMenuIdOfAccesoMenuEntityCollectionNewAccesoMenuEntity = em.merge(oldMenuIdOfAccesoMenuEntityCollectionNewAccesoMenuEntity);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = menuEntity.getMenuId();
                if (findMenuEntity(id) == null) {
                    throw new NonexistentEntityException("The menuEntity with id " + id + " no longer exists.");
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
            MenuEntity menuEntity;
            try {
                menuEntity = em.getReference(MenuEntity.class, id);
                menuEntity.getMenuId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The menuEntity with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<AccesoMenuEntity> accesoMenuEntityCollectionOrphanCheck = menuEntity.getAccesoMenuEntityCollection();
            for (AccesoMenuEntity accesoMenuEntityCollectionOrphanCheckAccesoMenuEntity : accesoMenuEntityCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This MenuEntity (" + menuEntity + ") cannot be destroyed since the AccesoMenuEntity " + accesoMenuEntityCollectionOrphanCheckAccesoMenuEntity + " in its accesoMenuEntityCollection field has a non-nullable menuId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(menuEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MenuEntity> findMenuEntityEntities() {
        return findMenuEntityEntities(true, -1, -1);
    }

    public List<MenuEntity> findMenuEntityEntities(int maxResults, int firstResult) {
        return findMenuEntityEntities(false, maxResults, firstResult);
    }

    private List<MenuEntity> findMenuEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from MenuEntity as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public MenuEntity findMenuEntity(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MenuEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getMenuEntityCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from MenuEntity as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
