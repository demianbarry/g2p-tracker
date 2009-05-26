/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zkoss.zkdemo.test2.tree;

import java.util.ArrayList;
import org.g2p.tracker.model.entities.TagsEntity;

/**
 *
 * @author Administrador
 */
public class TagsList extends ArrayList {

    private static final long serialVersionUID = 8611088632578136337L;
    private TagsEntity tag;

    public TagsEntity getTag() {
        return tag;
    }

    public void setTag(TagsEntity tag) {
        this.tag = tag;
    }

    public boolean add(TagsEntity e, String criteria) {
        if (criteria != null && criteria.length() != 0) {
            if (e.getDescripcion().contains(criteria) || e.getTag().contains(criteria)) {
                return super.add(e);
            } else {
                return false;
            }
        } else {
            return super.add(e);
        }
    }
}
