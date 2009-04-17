/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.models;

import java.util.List;
import org.g2p.tracker.model.entities.BaseEntity;

/**
 *
 * @author Administrador
 */
public class BaseModel {
    protected void resetStatus(List<BaseEntity> all) {
        for(int index = 0; index < all.size(); index++) {
            all.get(index).setStatus(BaseEntity.STATUS_NOT_MODIFIED);
        }
    }
}
