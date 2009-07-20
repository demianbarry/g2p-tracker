/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.models;

import org.g2p.tracker.model.entities.StickyNotesEntity;

/**
 *
 * @author Administrador
 */
public class StickyModel extends BaseModel {

    public StickyModel() {
        super(StickyNotesEntity.class);
    }

    @Override
    public StickyNotesEntity getSelected() {
        return (StickyNotesEntity) super.getSelected();
    }


}
