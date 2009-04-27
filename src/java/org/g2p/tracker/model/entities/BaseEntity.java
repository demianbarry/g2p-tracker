/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.entities;

/**
 *
 * @author Administrador
 */
@org.hibernate.annotations.Entity (
    optimisticLock=org.hibernate.annotations.OptimisticLockType.ALL,
    dynamicUpdate=true
)
public abstract class BaseEntity {
    public abstract Object getPK();
}
