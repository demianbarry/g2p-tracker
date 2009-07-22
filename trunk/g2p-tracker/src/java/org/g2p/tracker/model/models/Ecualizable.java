/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.models;

import java.util.List;
import org.g2p.tracker.model.entities.BaseEntity;
import org.g2p.tracker.model.entities.TagsEntity;

/**
 *
 * @author Administrador
 */
public interface Ecualizable {
    public List<BaseEntity> ecualizarByTags(List<TagsEntity> tagsList, boolean and) throws Exception;
}
