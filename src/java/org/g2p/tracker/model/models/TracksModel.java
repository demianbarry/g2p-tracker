/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.model.models;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import org.g2p.tracker.model.entities.BaseEntity;
import org.g2p.tracker.model.entities.TagsEntity;
import org.g2p.tracker.model.entities.TagsPerTracksEntity;
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
        System.out.println("SELECTED "+getSelected());
        if (getSelected() != null) {
            Hashtable parameters = new Hashtable();
            parameters.put("trackId", ((TracksEntity) getSelected()).getTrackId());
            return BaseModel.findEntities("TagsEntity.findByTrack", parameters);
        }
        return null;
    }

    @Override
    public List<BaseEntity> ecualizarByTags(List<TagsEntity> tagsList) throws Exception {
        Iterator<TagsEntity> tagsIterator = tagsList.iterator();
        Iterator tracksIterator;
        List tracks = new ArrayList();
        TracksEntity track;

        getParameters().clear();

        while(tagsIterator.hasNext()) {
//            parameters.put("tagId", tagsIterator.next().getTagId());
//            tracksIterator = BaseModel.findEntities("TagsPerTracksEntity.findByTag", parameters).iterator();
//            while(tracksIterator.hasNext()) {
//                track = ((TagsPerTracksEntity) tracksIterator.next()).getTrack();
//                if(!tracks.contains(track)) {
//                    tracks.add(track);
//                }
//            }
        }
        return tracks;
    }
}
