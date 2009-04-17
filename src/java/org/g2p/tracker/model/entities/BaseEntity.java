/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.entities;

/**
 *
 * @author Administrador
 */
public class BaseEntity {
    int status;
    public static final int STATUS_NEW = 0;
    public static final int STATUS_MODIFIED = 1;
    public static final int STATUS_DELETED = 2;
    public static final int STATUS_NOT_MODIFIED = 3;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
