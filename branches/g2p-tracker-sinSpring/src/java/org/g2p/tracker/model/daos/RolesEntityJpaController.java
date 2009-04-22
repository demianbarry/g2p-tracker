/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.model.daos;

import java.util.List;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.g2p.tracker.model.daos.exceptions.NonexistentEntityException;
import org.g2p.tracker.model.daos.exceptions.RollbackFailureException;
import org.g2p.tracker.model.entities.RolesEntity;
import javax.naming.InitialContext;
import javax.persistence.Persistence;

/**
 *
 * @author Administrador
 */
public class RolesEntityJpaController {

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

    public void create(RolesEntity rolesEntity) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {

            em = getEntityManager();
            em.persist(rolesEntity);

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RolesEntity rolesEntity) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();

            try {
                findRolesEntity(rolesEntity.getRolId()).getRolId();
            } catch (NullPointerException enfe) {
                throw new NonexistentEntityException("El rol con el id " + rolesEntity.getRolId() + " fue eliminado por otro usuario.", enfe);
            }

            rolesEntity = em.merge(rolesEntity);

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(RolesEntity rolesEntity) throws Exception {
        EntityManager em = null;
        try {
            
            em = getEntityManager();                       
            em.remove(em.getReference(RolesEntity.class, rolesEntity.getRolId()));
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RolesEntity> findRolesEntityEntities() {
        return findRolesEntityEntities(true, -1, -1);
    }

    public List<RolesEntity> findRolesEntityEntities(int maxResults, int firstResult)  {
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

    public int getRolesEntityCount() throws NamingException, SystemException, NotSupportedException {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from RolesEntity as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
