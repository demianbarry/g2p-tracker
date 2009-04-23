/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.model.daos;

import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.g2p.tracker.model.daos.exceptions.NonexistentEntityException;
import org.g2p.tracker.model.daos.exceptions.RollbackFailureException;
import org.g2p.tracker.model.entities.BaseEntity;

/**
 *
 * @author Administrador
 */
public class BaseDAO {

    public BaseDAO() {
    }

    private Class entity = null;

    public Class getEntity() {
        return entity;
    }

    public BaseDAO(Class entity) {
        this.entity = entity;
    }

    public void setEntity(Class entity) {
        this.entity = entity;
    }
    private UserTransaction utx = null;

    public UserTransaction getUtx() throws NamingException {
        if (utx == null) {
            utx = (UserTransaction) InitialContext.doLookup("UserTransaction");
        }
        return utx;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("g2p-tracker-sinSpringPU");
        }
        return emf.createEntityManager();
    }

    public void create(BaseEntity entity, boolean ownTx) throws RollbackFailureException, NamingException, IllegalStateException, SecurityException, SystemException, Exception  {
        EntityManager em = null;
        try {
            if (ownTx) {
                getUtx().begin();
            }

            em = getEntityManager();
            em.persist(entity);

            if (ownTx) {
                getUtx().commit();
            }

        } catch (Exception ex) {
            if (ownTx) {
                getUtx().rollback();
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BaseEntity entity, boolean ownTx) throws NonexistentEntityException, NamingException, IllegalStateException, SecurityException, SystemException, Exception {
        EntityManager em = null;
        try {
            if (ownTx) {
                getUtx().begin();
            }

            em = getEntityManager();

            try {
                findEntity(entity.getPK()).toString();
            } catch (NullPointerException enfe) {
                throw new NonexistentEntityException("El item con el id " + entity.getPK() + " fue eliminado por otro usuario.", enfe);
            }

            entity = em.merge(entity);

            if (ownTx) {
                getUtx().commit();
            }
        } catch (Exception ex) {
            if (ownTx) {
                getUtx().rollback();
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BaseEntity entity, boolean ownTx) throws NamingException, IllegalStateException, SecurityException, SystemException, Exception  {
                EntityManager em = null;
        try {
            if (ownTx) {
                getUtx().begin();
            }

            em = getEntityManager();
            em.remove(em.getReference(this.entity, entity.getPK()));

            if (ownTx) {
                getUtx().commit();
            }

        } catch (Exception ex) {
            if (ownTx) {
                getUtx().rollback();
            }       
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BaseEntity> findEntities() {
        return findEntities(true, -1, -1);
    }

    public List<BaseEntity> findEntities(int maxResults, int firstResult) {
        return findEntities(false, maxResults, firstResult);
    }

    private List<BaseEntity> findEntities(boolean all, int maxResults, int firstResult) {
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

    public BaseEntity findEntity(Object pk) {
        EntityManager em = getEntityManager();
        try {
            return (BaseEntity) em.find(entity, pk);
        } finally {
            em.close();
        }
    }

    public int getEntitiesCount() throws NamingException, SystemException, NotSupportedException {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from " + entity.getSimpleName() + " as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
