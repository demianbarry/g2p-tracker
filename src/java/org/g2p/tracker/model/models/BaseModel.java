/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.model.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.g2p.tracker.model.daos.exceptions.IllegalOrphanException;
import org.g2p.tracker.model.daos.exceptions.NonexistentEntityException;
import org.g2p.tracker.model.daos.exceptions.RollbackFailureException;
import org.g2p.tracker.model.entities.BaseEntity;
import org.zkoss.lang.Strings;

/**
 *
 * @author Administrador
 */
public class BaseModel {

    protected BaseEntity selected;
    protected static List<BaseEntity> all;
    protected String queryString;
    protected String where;
    protected String orderBy;
    protected int offset;
    protected int maxResults;
    protected Map<String, ?> parameters;

    public BaseModel(Class entity) {
        setEntity(entity);
        all = new ArrayList<BaseEntity>(findEntities());
    }

    public BaseEntity getSelected() {
        return (BaseEntity) selected;
    }

    public void setSelected(BaseEntity todo) {
        this.selected = todo;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    public String getQueryString() {
        if (queryString != null) {
            return this.queryString;
        }
        return generateQueryString(this.where, this.orderBy);
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public Map<String, ?> getParameters() {
        return this.parameters;
    }

    public void setParameters(Map<String, ?> params) {
        this.parameters = params;
    }

    //-- DB access on the selected bean --//
    public void persist(boolean ownTx) throws RollbackFailureException, Exception {
        create(selected, ownTx);
    }

    public void merge(boolean ownTx) throws EntityNotFoundException, IllegalOrphanException, NonexistentEntityException, Exception {
        edit(selected, ownTx);
    }

    public void delete(boolean ownTx) throws EntityNotFoundException, IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        destroy(selected, ownTx);
    }

    public void setAll(List<BaseEntity> allEntities) {
        all = allEntities;
    }

    public List<BaseEntity> getAll() {
        return findEntities();
    }

    //-- overridable --//
    /** Generate query string */
    protected String generateQueryString(String where, String orderBy) {
        final StringBuffer sb = new StringBuffer(256);
        sb.append("FROM " + selected.getClass().getName());
        if (!Strings.isBlank(where)) {
            sb.append(" WHERE " + where);
        }
        if (!Strings.isBlank(orderBy)) {
            sb.append(" ORDER BY " + orderBy);
        }
        return sb.toString();
    }
    private Class entity = null;

    public Class getEntity() {
        return entity;
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

    public void create(BaseEntity entity, boolean ownTx) throws RollbackFailureException, NamingException, IllegalStateException, SecurityException, SystemException, Exception {
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

    public void destroy(BaseEntity entity, boolean ownTx) throws NamingException, IllegalStateException, SecurityException, SystemException, Exception {
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
            ex.printStackTrace();
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
            Query q = em.createQuery("select object(o) from "+this.entity.getName()+" as o");
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

    public void create(BaseEntity entity) throws RollbackFailureException, NamingException, IllegalStateException, SecurityException, SystemException, Exception {
        create(entity, true);
    }

    public void edit(BaseEntity entity) throws NonexistentEntityException, NamingException, IllegalStateException, SecurityException, SystemException, Exception {
        edit(entity, true);
    }

    public void destroy(BaseEntity entity) throws NamingException, IllegalStateException, SecurityException, SystemException, Exception {
        destroy(entity, true);
    }
}
