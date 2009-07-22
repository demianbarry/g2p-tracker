/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.model.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.g2p.tracker.model.entities.BaseEntity;
import org.g2p.tracker.model.entities.TagsEntity;
import org.g2p.tracker.model.entities.TracksEntity;

/**
 *
 * @author nacho
 */
public class TracksModel extends BaseModel implements Taggeable, Ecualizable {

    public TracksModel() {
        super(TracksEntity.class);
    }

    @Override
    public void aplicarTags(List<TagsEntity> tagsList) throws Exception {
        TracksEntity track = ((TracksEntity) getSelected());

        if (track != null) {
            track.getTagsEntityCollection().clear();
            Iterator<TagsEntity> tags = tagsList.iterator();
            TagsEntity tag = null;

            while (tags.hasNext()) {
                tag = tags.next();
                track.addTag(tag);
            }
        }
    }

    @Override
    public List getStoredTags() throws Exception {
        if (getSelected() != null) {
            return new ArrayList(getSelected().getTagsEntityCollection());
        }
        return null;
    }

    @Override
    public List<BaseEntity> ecualizarByTags(List<TagsEntity> tagsList, boolean and) throws Exception {
        if (tagsList == null || tagsList.size() == 0) {
            return getAll();
        }
        Iterator<TagsEntity> tagsIterator = tagsList.iterator();
        Iterator tracksIterator = getAll().iterator();
        List tracks = new ArrayList();
        TracksEntity track;

        getParameters().clear();

        while (tracksIterator.hasNext()) {
            track = (TracksEntity) tracksIterator.next();
            if (and) {
                if (track.getTagsEntityCollection().containsAll(tagsList) && !tracks.contains(track)) {
                    tracks.add(track);
                }
            } else {
                while (tagsIterator.hasNext()) {
                    if (track.getTagsEntityCollection().contains(tagsIterator.next()) && !tracks.contains(track)) {
                        tracks.add(track);
                    }
                }
            }
        }
        return tracks;
    }

    @Override
    public TracksEntity getSelected() {
        return (TracksEntity) super.getSelected();
    }
}
