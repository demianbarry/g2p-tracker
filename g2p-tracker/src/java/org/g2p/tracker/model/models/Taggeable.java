/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.models;

import java.util.List;
import org.g2p.tracker.model.entities.TagsEntity;

/**
 *
 * @author Administrador
 */
public interface Taggeable {
    public String getNombre();
    public List getStoredTags();
    public void aplicarTags(List<TagsEntity> tagsList) throws Exception;
}
