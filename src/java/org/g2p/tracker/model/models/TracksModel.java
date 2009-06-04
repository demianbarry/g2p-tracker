/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.model.models;

import java.util.Iterator;
import java.util.List;
import org.g2p.tracker.model.entities.TagsEntity;
import org.g2p.tracker.model.entities.TagsPerTracksEntity;
import org.g2p.tracker.model.entities.TracksEntity;

/**
 *
 * @author nacho
 */
public class TracksModel extends BaseModel implements Taggeable {

    public TracksModel() {
        super(TracksEntity.class);
    }

    @Override
    public String getNombre() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void aplicarTags(List<TagsEntity> tagsList) throws Exception {
        TracksEntity track = ((TracksEntity) getSelected());
        if (track != null) {
            TagsPerTracksEntity tagsPerTrack = null;
            Iterator<TagsEntity> tags = tagsList.iterator();
            while (tags.hasNext()) {
                tagsPerTrack = new TagsPerTracksEntity(
                        tags.next().getTagId(),
                        track.getTrackId());
                create(tagsPerTrack);
            }
        }
    }
}
