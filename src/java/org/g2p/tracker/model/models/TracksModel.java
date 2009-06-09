/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.model.models;

import java.util.Hashtable;
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
            Hashtable parameters = new Hashtable();
            parameters.put("trackId", track.getTrackId());
            List tagsByTrack = BaseModel.findEntities("TagsEntity.findByTrack", parameters);

            Iterator<TagsEntity> tags = tagsList.iterator();
            TagsEntity tag = null;
            TagsPerTracksEntity tagPerTrack = null;

            while (tags.hasNext()) {
                tag = tags.next();
                if (!tagsByTrack.contains(tag)) {
                    tagPerTrack = new TagsPerTracksEntity(
                            tag.getTagId(),
                            track.getTrackId());
                    BaseModel.createEntity(tagPerTrack, true);
                }
            }

            tags = tagsByTrack.iterator();
            while (tags.hasNext()) {
                tag = tags.next();
                if (!tagsList.contains(tag)) {
                    tagPerTrack = new TagsPerTracksEntity(
                            tag.getTagId(),
                            track.getTrackId());
                    BaseModel.deleteEntity(tagPerTrack, true);
                }
            }

        }
    }

    @Override
    public List getStoredTags() {
        Hashtable parameters = new Hashtable();
        parameters.put("trackId", ((TracksEntity) getSelected()).getTrackId());
        return BaseModel.findEntities("TagsEntity.findByTrack", parameters);
    }
}
