/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.daos;

import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.transaction.UserTransaction;
import org.g2p.tracker.model.daos.exceptions.IllegalOrphanException;
import org.g2p.tracker.model.daos.exceptions.NonexistentEntityException;
import org.g2p.tracker.model.daos.exceptions.PreexistingEntityException;
import org.g2p.tracker.model.daos.exceptions.RollbackFailureException;
import org.g2p.tracker.model.entities.CircuitosEstadosEntity;
import org.g2p.tracker.model.entities.AuditaEstadosCircuitosEntity;
import java.util.HashSet;
import java.util.Set;
import org.g2p.tracker.model.entities.EstadosEntity;
import org.g2p.tracker.model.entities.TransicionEstadosEntity;
import java.util.ArrayList;

/**
 *
 * @author Administrador
 */
public class EstadosEntityJpaController {
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "g2p-tracker-sinSpringPU")
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EstadosEntity estadosEntity) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (estadosEntity.getAuditaEstadosCircuitosEntityCollection() == null) {
            estadosEntity.setAuditaEstadosCircuitosEntityCollection(new HashSet<AuditaEstadosCircuitosEntity>());
        }
        if (estadosEntity.getAuditaEstadosCircuitosEntityCollection1() == null) {
            estadosEntity.setAuditaEstadosCircuitosEntityCollection1(new HashSet<AuditaEstadosCircuitosEntity>());
        }
        if (estadosEntity.getTransicionEstadosEntityCollection() == null) {
            estadosEntity.setTransicionEstadosEntityCollection(new HashSet<TransicionEstadosEntity>());
        }
        if (estadosEntity.getTransicionEstadosEntityCollection1() == null) {
            estadosEntity.setTransicionEstadosEntityCollection1(new HashSet<TransicionEstadosEntity>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CircuitosEstadosEntity circuito = estadosEntity.getCircuito();
            if (circuito != null) {
                circuito = em.getReference(circuito.getClass(), circuito.getCircuito());
                estadosEntity.setCircuito(circuito);
            }
            Set<AuditaEstadosCircuitosEntity> attachedAuditaEstadosCircuitosEntityCollection = new HashSet<AuditaEstadosCircuitosEntity>();
            for (AuditaEstadosCircuitosEntity auditaEstadosCircuitosEntityCollectionAuditaEstadosCircuitosEntityToAttach : estadosEntity.getAuditaEstadosCircuitosEntityCollection()) {
                auditaEstadosCircuitosEntityCollectionAuditaEstadosCircuitosEntityToAttach = em.getReference(auditaEstadosCircuitosEntityCollectionAuditaEstadosCircuitosEntityToAttach.getClass(), auditaEstadosCircuitosEntityCollectionAuditaEstadosCircuitosEntityToAttach.getAuditaId());
                attachedAuditaEstadosCircuitosEntityCollection.add(auditaEstadosCircuitosEntityCollectionAuditaEstadosCircuitosEntityToAttach);
            }
            estadosEntity.setAuditaEstadosCircuitosEntityCollection(attachedAuditaEstadosCircuitosEntityCollection);
            Set<AuditaEstadosCircuitosEntity> attachedAuditaEstadosCircuitosEntityCollection1 = new HashSet<AuditaEstadosCircuitosEntity>();
            for (AuditaEstadosCircuitosEntity auditaEstadosCircuitosEntityCollection1AuditaEstadosCircuitosEntityToAttach : estadosEntity.getAuditaEstadosCircuitosEntityCollection1()) {
                auditaEstadosCircuitosEntityCollection1AuditaEstadosCircuitosEntityToAttach = em.getReference(auditaEstadosCircuitosEntityCollection1AuditaEstadosCircuitosEntityToAttach.getClass(), auditaEstadosCircuitosEntityCollection1AuditaEstadosCircuitosEntityToAttach.getAuditaId());
                attachedAuditaEstadosCircuitosEntityCollection1.add(auditaEstadosCircuitosEntityCollection1AuditaEstadosCircuitosEntityToAttach);
            }
            estadosEntity.setAuditaEstadosCircuitosEntityCollection1(attachedAuditaEstadosCircuitosEntityCollection1);
            Set<TransicionEstadosEntity> attachedTransicionEstadosEntityCollection = new HashSet<TransicionEstadosEntity>();
            for (TransicionEstadosEntity transicionEstadosEntityCollectionTransicionEstadosEntityToAttach : estadosEntity.getTransicionEstadosEntityCollection()) {
                transicionEstadosEntityCollectionTransicionEstadosEntityToAttach = em.getReference(transicionEstadosEntityCollectionTransicionEstadosEntityToAttach.getClass(), transicionEstadosEntityCollectionTransicionEstadosEntityToAttach.getTransicionEstadosEntityPK());
                attachedTransicionEstadosEntityCollection.add(transicionEstadosEntityCollectionTransicionEstadosEntityToAttach);
            }
            estadosEntity.setTransicionEstadosEntityCollection(attachedTransicionEstadosEntityCollection);
            Set<TransicionEstadosEntity> attachedTransicionEstadosEntityCollection1 = new HashSet<TransicionEstadosEntity>();
            for (TransicionEstadosEntity transicionEstadosEntityCollection1TransicionEstadosEntityToAttach : estadosEntity.getTransicionEstadosEntityCollection1()) {
                transicionEstadosEntityCollection1TransicionEstadosEntityToAttach = em.getReference(transicionEstadosEntityCollection1TransicionEstadosEntityToAttach.getClass(), transicionEstadosEntityCollection1TransicionEstadosEntityToAttach.getTransicionEstadosEntityPK());
                attachedTransicionEstadosEntityCollection1.add(transicionEstadosEntityCollection1TransicionEstadosEntityToAttach);
            }
            estadosEntity.setTransicionEstadosEntityCollection1(attachedTransicionEstadosEntityCollection1);
            em.persist(estadosEntity);
            if (circuito != null) {
                circuito.getEstadosEntityCollection().add(estadosEntity);
                circuito = em.merge(circuito);
            }
            for (AuditaEstadosCircuitosEntity auditaEstadosCircuitosEntityCollectionAuditaEstadosCircuitosEntity : estadosEntity.getAuditaEstadosCircuitosEntityCollection()) {
                EstadosEntity oldDeEstadoOfAuditaEstadosCircuitosEntityCollectionAuditaEstadosCircuitosEntity = auditaEstadosCircuitosEntityCollectionAuditaEstadosCircuitosEntity.getDeEstado();
                auditaEstadosCircuitosEntityCollectionAuditaEstadosCircuitosEntity.setDeEstado(estadosEntity);
                auditaEstadosCircuitosEntityCollectionAuditaEstadosCircuitosEntity = em.merge(auditaEstadosCircuitosEntityCollectionAuditaEstadosCircuitosEntity);
                if (oldDeEstadoOfAuditaEstadosCircuitosEntityCollectionAuditaEstadosCircuitosEntity != null) {
                    oldDeEstadoOfAuditaEstadosCircuitosEntityCollectionAuditaEstadosCircuitosEntity.getAuditaEstadosCircuitosEntityCollection().remove(auditaEstadosCircuitosEntityCollectionAuditaEstadosCircuitosEntity);
                    oldDeEstadoOfAuditaEstadosCircuitosEntityCollectionAuditaEstadosCircuitosEntity = em.merge(oldDeEstadoOfAuditaEstadosCircuitosEntityCollectionAuditaEstadosCircuitosEntity);
                }
            }
            for (AuditaEstadosCircuitosEntity auditaEstadosCircuitosEntityCollection1AuditaEstadosCircuitosEntity : estadosEntity.getAuditaEstadosCircuitosEntityCollection1()) {
                EstadosEntity oldAEstadoOfAuditaEstadosCircuitosEntityCollection1AuditaEstadosCircuitosEntity = auditaEstadosCircuitosEntityCollection1AuditaEstadosCircuitosEntity.getAEstado();
                auditaEstadosCircuitosEntityCollection1AuditaEstadosCircuitosEntity.setAEstado(estadosEntity);
                auditaEstadosCircuitosEntityCollection1AuditaEstadosCircuitosEntity = em.merge(auditaEstadosCircuitosEntityCollection1AuditaEstadosCircuitosEntity);
                if (oldAEstadoOfAuditaEstadosCircuitosEntityCollection1AuditaEstadosCircuitosEntity != null) {
                    oldAEstadoOfAuditaEstadosCircuitosEntityCollection1AuditaEstadosCircuitosEntity.getAuditaEstadosCircuitosEntityCollection1().remove(auditaEstadosCircuitosEntityCollection1AuditaEstadosCircuitosEntity);
                    oldAEstadoOfAuditaEstadosCircuitosEntityCollection1AuditaEstadosCircuitosEntity = em.merge(oldAEstadoOfAuditaEstadosCircuitosEntityCollection1AuditaEstadosCircuitosEntity);
                }
            }
            for (TransicionEstadosEntity transicionEstadosEntityCollectionTransicionEstadosEntity : estadosEntity.getTransicionEstadosEntityCollection()) {
                EstadosEntity oldEstadoDestinoOfTransicionEstadosEntityCollectionTransicionEstadosEntity = transicionEstadosEntityCollectionTransicionEstadosEntity.getEstadoDestino();
                transicionEstadosEntityCollectionTransicionEstadosEntity.setEstadoDestino(estadosEntity);
                transicionEstadosEntityCollectionTransicionEstadosEntity = em.merge(transicionEstadosEntityCollectionTransicionEstadosEntity);
                if (oldEstadoDestinoOfTransicionEstadosEntityCollectionTransicionEstadosEntity != null) {
                    oldEstadoDestinoOfTransicionEstadosEntityCollectionTransicionEstadosEntity.getTransicionEstadosEntityCollection().remove(transicionEstadosEntityCollectionTransicionEstadosEntity);
                    oldEstadoDestinoOfTransicionEstadosEntityCollectionTransicionEstadosEntity = em.merge(oldEstadoDestinoOfTransicionEstadosEntityCollectionTransicionEstadosEntity);
                }
            }
            for (TransicionEstadosEntity transicionEstadosEntityCollection1TransicionEstadosEntity : estadosEntity.getTransicionEstadosEntityCollection1()) {
                EstadosEntity oldEstadosEntityOfTransicionEstadosEntityCollection1TransicionEstadosEntity = transicionEstadosEntityCollection1TransicionEstadosEntity.getEstadosEntity();
                transicionEstadosEntityCollection1TransicionEstadosEntity.setEstadosEntity(estadosEntity);
                transicionEstadosEntityCollection1TransicionEstadosEntity = em.merge(transicionEstadosEntityCollection1TransicionEstadosEntity);
                if (oldEstadosEntityOfTransicionEstadosEntityCollection1TransicionEstadosEntity != null) {
                    oldEstadosEntityOfTransicionEstadosEntityCollection1TransicionEstadosEntity.getTransicionEstadosEntityCollection1().remove(transicionEstadosEntityCollection1TransicionEstadosEntity);
                    oldEstadosEntityOfTransicionEstadosEntityCollection1TransicionEstadosEntity = em.merge(oldEstadosEntityOfTransicionEstadosEntityCollection1TransicionEstadosEntity);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findEstadosEntity(estadosEntity.getEstado()) != null) {
                throw new PreexistingEntityException("EstadosEntity " + estadosEntity + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EstadosEntity estadosEntity) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            EstadosEntity persistentEstadosEntity = em.find(EstadosEntity.class, estadosEntity.getEstado());
            CircuitosEstadosEntity circuitoOld = persistentEstadosEntity.getCircuito();
            CircuitosEstadosEntity circuitoNew = estadosEntity.getCircuito();
            Set<AuditaEstadosCircuitosEntity> auditaEstadosCircuitosEntityCollectionOld = persistentEstadosEntity.getAuditaEstadosCircuitosEntityCollection();
            Set<AuditaEstadosCircuitosEntity> auditaEstadosCircuitosEntityCollectionNew = estadosEntity.getAuditaEstadosCircuitosEntityCollection();
            Set<AuditaEstadosCircuitosEntity> auditaEstadosCircuitosEntityCollection1Old = persistentEstadosEntity.getAuditaEstadosCircuitosEntityCollection1();
            Set<AuditaEstadosCircuitosEntity> auditaEstadosCircuitosEntityCollection1New = estadosEntity.getAuditaEstadosCircuitosEntityCollection1();
            Set<TransicionEstadosEntity> transicionEstadosEntityCollectionOld = persistentEstadosEntity.getTransicionEstadosEntityCollection();
            Set<TransicionEstadosEntity> transicionEstadosEntityCollectionNew = estadosEntity.getTransicionEstadosEntityCollection();
            Set<TransicionEstadosEntity> transicionEstadosEntityCollection1Old = persistentEstadosEntity.getTransicionEstadosEntityCollection1();
            Set<TransicionEstadosEntity> transicionEstadosEntityCollection1New = estadosEntity.getTransicionEstadosEntityCollection1();
            List<String> illegalOrphanMessages = null;
            for (AuditaEstadosCircuitosEntity auditaEstadosCircuitosEntityCollectionOldAuditaEstadosCircuitosEntity : auditaEstadosCircuitosEntityCollectionOld) {
                if (!auditaEstadosCircuitosEntityCollectionNew.contains(auditaEstadosCircuitosEntityCollectionOldAuditaEstadosCircuitosEntity)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AuditaEstadosCircuitosEntity " + auditaEstadosCircuitosEntityCollectionOldAuditaEstadosCircuitosEntity + " since its deEstado field is not nullable.");
                }
            }
            for (AuditaEstadosCircuitosEntity auditaEstadosCircuitosEntityCollection1OldAuditaEstadosCircuitosEntity : auditaEstadosCircuitosEntityCollection1Old) {
                if (!auditaEstadosCircuitosEntityCollection1New.contains(auditaEstadosCircuitosEntityCollection1OldAuditaEstadosCircuitosEntity)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AuditaEstadosCircuitosEntity " + auditaEstadosCircuitosEntityCollection1OldAuditaEstadosCircuitosEntity + " since its AEstado field is not nullable.");
                }
            }
            for (TransicionEstadosEntity transicionEstadosEntityCollectionOldTransicionEstadosEntity : transicionEstadosEntityCollectionOld) {
                if (!transicionEstadosEntityCollectionNew.contains(transicionEstadosEntityCollectionOldTransicionEstadosEntity)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TransicionEstadosEntity " + transicionEstadosEntityCollectionOldTransicionEstadosEntity + " since its estadoDestino field is not nullable.");
                }
            }
            for (TransicionEstadosEntity transicionEstadosEntityCollection1OldTransicionEstadosEntity : transicionEstadosEntityCollection1Old) {
                if (!transicionEstadosEntityCollection1New.contains(transicionEstadosEntityCollection1OldTransicionEstadosEntity)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TransicionEstadosEntity " + transicionEstadosEntityCollection1OldTransicionEstadosEntity + " since its estadosEntity field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (circuitoNew != null) {
                circuitoNew = em.getReference(circuitoNew.getClass(), circuitoNew.getCircuito());
                estadosEntity.setCircuito(circuitoNew);
            }
            Set<AuditaEstadosCircuitosEntity> attachedAuditaEstadosCircuitosEntityCollectionNew = new HashSet<AuditaEstadosCircuitosEntity>();
            for (AuditaEstadosCircuitosEntity auditaEstadosCircuitosEntityCollectionNewAuditaEstadosCircuitosEntityToAttach : auditaEstadosCircuitosEntityCollectionNew) {
                auditaEstadosCircuitosEntityCollectionNewAuditaEstadosCircuitosEntityToAttach = em.getReference(auditaEstadosCircuitosEntityCollectionNewAuditaEstadosCircuitosEntityToAttach.getClass(), auditaEstadosCircuitosEntityCollectionNewAuditaEstadosCircuitosEntityToAttach.getAuditaId());
                attachedAuditaEstadosCircuitosEntityCollectionNew.add(auditaEstadosCircuitosEntityCollectionNewAuditaEstadosCircuitosEntityToAttach);
            }
            auditaEstadosCircuitosEntityCollectionNew = attachedAuditaEstadosCircuitosEntityCollectionNew;
            estadosEntity.setAuditaEstadosCircuitosEntityCollection(auditaEstadosCircuitosEntityCollectionNew);
            Set<AuditaEstadosCircuitosEntity> attachedAuditaEstadosCircuitosEntityCollection1New = new HashSet<AuditaEstadosCircuitosEntity>();
            for (AuditaEstadosCircuitosEntity auditaEstadosCircuitosEntityCollection1NewAuditaEstadosCircuitosEntityToAttach : auditaEstadosCircuitosEntityCollection1New) {
                auditaEstadosCircuitosEntityCollection1NewAuditaEstadosCircuitosEntityToAttach = em.getReference(auditaEstadosCircuitosEntityCollection1NewAuditaEstadosCircuitosEntityToAttach.getClass(), auditaEstadosCircuitosEntityCollection1NewAuditaEstadosCircuitosEntityToAttach.getAuditaId());
                attachedAuditaEstadosCircuitosEntityCollection1New.add(auditaEstadosCircuitosEntityCollection1NewAuditaEstadosCircuitosEntityToAttach);
            }
            auditaEstadosCircuitosEntityCollection1New = attachedAuditaEstadosCircuitosEntityCollection1New;
            estadosEntity.setAuditaEstadosCircuitosEntityCollection1(auditaEstadosCircuitosEntityCollection1New);
            Set<TransicionEstadosEntity> attachedTransicionEstadosEntityCollectionNew = new HashSet<TransicionEstadosEntity>();
            for (TransicionEstadosEntity transicionEstadosEntityCollectionNewTransicionEstadosEntityToAttach : transicionEstadosEntityCollectionNew) {
                transicionEstadosEntityCollectionNewTransicionEstadosEntityToAttach = em.getReference(transicionEstadosEntityCollectionNewTransicionEstadosEntityToAttach.getClass(), transicionEstadosEntityCollectionNewTransicionEstadosEntityToAttach.getTransicionEstadosEntityPK());
                attachedTransicionEstadosEntityCollectionNew.add(transicionEstadosEntityCollectionNewTransicionEstadosEntityToAttach);
            }
            transicionEstadosEntityCollectionNew = attachedTransicionEstadosEntityCollectionNew;
            estadosEntity.setTransicionEstadosEntityCollection(transicionEstadosEntityCollectionNew);
            Set<TransicionEstadosEntity> attachedTransicionEstadosEntityCollection1New = new HashSet<TransicionEstadosEntity>();
            for (TransicionEstadosEntity transicionEstadosEntityCollection1NewTransicionEstadosEntityToAttach : transicionEstadosEntityCollection1New) {
                transicionEstadosEntityCollection1NewTransicionEstadosEntityToAttach = em.getReference(transicionEstadosEntityCollection1NewTransicionEstadosEntityToAttach.getClass(), transicionEstadosEntityCollection1NewTransicionEstadosEntityToAttach.getTransicionEstadosEntityPK());
                attachedTransicionEstadosEntityCollection1New.add(transicionEstadosEntityCollection1NewTransicionEstadosEntityToAttach);
            }
            transicionEstadosEntityCollection1New = attachedTransicionEstadosEntityCollection1New;
            estadosEntity.setTransicionEstadosEntityCollection1(transicionEstadosEntityCollection1New);
            estadosEntity = em.merge(estadosEntity);
            if (circuitoOld != null && !circuitoOld.equals(circuitoNew)) {
                circuitoOld.getEstadosEntityCollection().remove(estadosEntity);
                circuitoOld = em.merge(circuitoOld);
            }
            if (circuitoNew != null && !circuitoNew.equals(circuitoOld)) {
                circuitoNew.getEstadosEntityCollection().add(estadosEntity);
                circuitoNew = em.merge(circuitoNew);
            }
            for (AuditaEstadosCircuitosEntity auditaEstadosCircuitosEntityCollectionNewAuditaEstadosCircuitosEntity : auditaEstadosCircuitosEntityCollectionNew) {
                if (!auditaEstadosCircuitosEntityCollectionOld.contains(auditaEstadosCircuitosEntityCollectionNewAuditaEstadosCircuitosEntity)) {
                    EstadosEntity oldDeEstadoOfAuditaEstadosCircuitosEntityCollectionNewAuditaEstadosCircuitosEntity = auditaEstadosCircuitosEntityCollectionNewAuditaEstadosCircuitosEntity.getDeEstado();
                    auditaEstadosCircuitosEntityCollectionNewAuditaEstadosCircuitosEntity.setDeEstado(estadosEntity);
                    auditaEstadosCircuitosEntityCollectionNewAuditaEstadosCircuitosEntity = em.merge(auditaEstadosCircuitosEntityCollectionNewAuditaEstadosCircuitosEntity);
                    if (oldDeEstadoOfAuditaEstadosCircuitosEntityCollectionNewAuditaEstadosCircuitosEntity != null && !oldDeEstadoOfAuditaEstadosCircuitosEntityCollectionNewAuditaEstadosCircuitosEntity.equals(estadosEntity)) {
                        oldDeEstadoOfAuditaEstadosCircuitosEntityCollectionNewAuditaEstadosCircuitosEntity.getAuditaEstadosCircuitosEntityCollection().remove(auditaEstadosCircuitosEntityCollectionNewAuditaEstadosCircuitosEntity);
                        oldDeEstadoOfAuditaEstadosCircuitosEntityCollectionNewAuditaEstadosCircuitosEntity = em.merge(oldDeEstadoOfAuditaEstadosCircuitosEntityCollectionNewAuditaEstadosCircuitosEntity);
                    }
                }
            }
            for (AuditaEstadosCircuitosEntity auditaEstadosCircuitosEntityCollection1NewAuditaEstadosCircuitosEntity : auditaEstadosCircuitosEntityCollection1New) {
                if (!auditaEstadosCircuitosEntityCollection1Old.contains(auditaEstadosCircuitosEntityCollection1NewAuditaEstadosCircuitosEntity)) {
                    EstadosEntity oldAEstadoOfAuditaEstadosCircuitosEntityCollection1NewAuditaEstadosCircuitosEntity = auditaEstadosCircuitosEntityCollection1NewAuditaEstadosCircuitosEntity.getAEstado();
                    auditaEstadosCircuitosEntityCollection1NewAuditaEstadosCircuitosEntity.setAEstado(estadosEntity);
                    auditaEstadosCircuitosEntityCollection1NewAuditaEstadosCircuitosEntity = em.merge(auditaEstadosCircuitosEntityCollection1NewAuditaEstadosCircuitosEntity);
                    if (oldAEstadoOfAuditaEstadosCircuitosEntityCollection1NewAuditaEstadosCircuitosEntity != null && !oldAEstadoOfAuditaEstadosCircuitosEntityCollection1NewAuditaEstadosCircuitosEntity.equals(estadosEntity)) {
                        oldAEstadoOfAuditaEstadosCircuitosEntityCollection1NewAuditaEstadosCircuitosEntity.getAuditaEstadosCircuitosEntityCollection1().remove(auditaEstadosCircuitosEntityCollection1NewAuditaEstadosCircuitosEntity);
                        oldAEstadoOfAuditaEstadosCircuitosEntityCollection1NewAuditaEstadosCircuitosEntity = em.merge(oldAEstadoOfAuditaEstadosCircuitosEntityCollection1NewAuditaEstadosCircuitosEntity);
                    }
                }
            }
            for (TransicionEstadosEntity transicionEstadosEntityCollectionNewTransicionEstadosEntity : transicionEstadosEntityCollectionNew) {
                if (!transicionEstadosEntityCollectionOld.contains(transicionEstadosEntityCollectionNewTransicionEstadosEntity)) {
                    EstadosEntity oldEstadoDestinoOfTransicionEstadosEntityCollectionNewTransicionEstadosEntity = transicionEstadosEntityCollectionNewTransicionEstadosEntity.getEstadoDestino();
                    transicionEstadosEntityCollectionNewTransicionEstadosEntity.setEstadoDestino(estadosEntity);
                    transicionEstadosEntityCollectionNewTransicionEstadosEntity = em.merge(transicionEstadosEntityCollectionNewTransicionEstadosEntity);
                    if (oldEstadoDestinoOfTransicionEstadosEntityCollectionNewTransicionEstadosEntity != null && !oldEstadoDestinoOfTransicionEstadosEntityCollectionNewTransicionEstadosEntity.equals(estadosEntity)) {
                        oldEstadoDestinoOfTransicionEstadosEntityCollectionNewTransicionEstadosEntity.getTransicionEstadosEntityCollection().remove(transicionEstadosEntityCollectionNewTransicionEstadosEntity);
                        oldEstadoDestinoOfTransicionEstadosEntityCollectionNewTransicionEstadosEntity = em.merge(oldEstadoDestinoOfTransicionEstadosEntityCollectionNewTransicionEstadosEntity);
                    }
                }
            }
            for (TransicionEstadosEntity transicionEstadosEntityCollection1NewTransicionEstadosEntity : transicionEstadosEntityCollection1New) {
                if (!transicionEstadosEntityCollection1Old.contains(transicionEstadosEntityCollection1NewTransicionEstadosEntity)) {
                    EstadosEntity oldEstadosEntityOfTransicionEstadosEntityCollection1NewTransicionEstadosEntity = transicionEstadosEntityCollection1NewTransicionEstadosEntity.getEstadosEntity();
                    transicionEstadosEntityCollection1NewTransicionEstadosEntity.setEstadosEntity(estadosEntity);
                    transicionEstadosEntityCollection1NewTransicionEstadosEntity = em.merge(transicionEstadosEntityCollection1NewTransicionEstadosEntity);
                    if (oldEstadosEntityOfTransicionEstadosEntityCollection1NewTransicionEstadosEntity != null && !oldEstadosEntityOfTransicionEstadosEntityCollection1NewTransicionEstadosEntity.equals(estadosEntity)) {
                        oldEstadosEntityOfTransicionEstadosEntityCollection1NewTransicionEstadosEntity.getTransicionEstadosEntityCollection1().remove(transicionEstadosEntityCollection1NewTransicionEstadosEntity);
                        oldEstadosEntityOfTransicionEstadosEntityCollection1NewTransicionEstadosEntity = em.merge(oldEstadosEntityOfTransicionEstadosEntityCollection1NewTransicionEstadosEntity);
                    }
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = estadosEntity.getEstado();
                if (findEstadosEntity(id) == null) {
                    throw new NonexistentEntityException("The estadosEntity with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            EstadosEntity estadosEntity;
            try {
                estadosEntity = em.getReference(EstadosEntity.class, id);
                estadosEntity.getEstado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadosEntity with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<AuditaEstadosCircuitosEntity> auditaEstadosCircuitosEntityCollectionOrphanCheck = estadosEntity.getAuditaEstadosCircuitosEntityCollection();
            for (AuditaEstadosCircuitosEntity auditaEstadosCircuitosEntityCollectionOrphanCheckAuditaEstadosCircuitosEntity : auditaEstadosCircuitosEntityCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This EstadosEntity (" + estadosEntity + ") cannot be destroyed since the AuditaEstadosCircuitosEntity " + auditaEstadosCircuitosEntityCollectionOrphanCheckAuditaEstadosCircuitosEntity + " in its auditaEstadosCircuitosEntityCollection field has a non-nullable deEstado field.");
            }
            Set<AuditaEstadosCircuitosEntity> auditaEstadosCircuitosEntityCollection1OrphanCheck = estadosEntity.getAuditaEstadosCircuitosEntityCollection1();
            for (AuditaEstadosCircuitosEntity auditaEstadosCircuitosEntityCollection1OrphanCheckAuditaEstadosCircuitosEntity : auditaEstadosCircuitosEntityCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This EstadosEntity (" + estadosEntity + ") cannot be destroyed since the AuditaEstadosCircuitosEntity " + auditaEstadosCircuitosEntityCollection1OrphanCheckAuditaEstadosCircuitosEntity + " in its auditaEstadosCircuitosEntityCollection1 field has a non-nullable AEstado field.");
            }
            Set<TransicionEstadosEntity> transicionEstadosEntityCollectionOrphanCheck = estadosEntity.getTransicionEstadosEntityCollection();
            for (TransicionEstadosEntity transicionEstadosEntityCollectionOrphanCheckTransicionEstadosEntity : transicionEstadosEntityCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This EstadosEntity (" + estadosEntity + ") cannot be destroyed since the TransicionEstadosEntity " + transicionEstadosEntityCollectionOrphanCheckTransicionEstadosEntity + " in its transicionEstadosEntityCollection field has a non-nullable estadoDestino field.");
            }
            Set<TransicionEstadosEntity> transicionEstadosEntityCollection1OrphanCheck = estadosEntity.getTransicionEstadosEntityCollection1();
            for (TransicionEstadosEntity transicionEstadosEntityCollection1OrphanCheckTransicionEstadosEntity : transicionEstadosEntityCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This EstadosEntity (" + estadosEntity + ") cannot be destroyed since the TransicionEstadosEntity " + transicionEstadosEntityCollection1OrphanCheckTransicionEstadosEntity + " in its transicionEstadosEntityCollection1 field has a non-nullable estadosEntity field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CircuitosEstadosEntity circuito = estadosEntity.getCircuito();
            if (circuito != null) {
                circuito.getEstadosEntityCollection().remove(estadosEntity);
                circuito = em.merge(circuito);
            }
            em.remove(estadosEntity);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EstadosEntity> findEstadosEntityEntities() {
        return findEstadosEntityEntities(true, -1, -1);
    }

    public List<EstadosEntity> findEstadosEntityEntities(int maxResults, int firstResult) {
        return findEstadosEntityEntities(false, maxResults, firstResult);
    }

    private List<EstadosEntity> findEstadosEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from EstadosEntity as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public EstadosEntity findEstadosEntity(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EstadosEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadosEntityCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from EstadosEntity as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
