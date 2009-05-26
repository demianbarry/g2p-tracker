/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.composers;

import java.util.Iterator;
import java.util.Set;
import org.g2p.tracker.model.entities.TagsEntity;
import org.g2p.tracker.model.models.BaseModel;
import org.g2p.tracker.model.models.TagsModel;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkdemo.test2.tree.TagsList;
import org.zkoss.zkdemo.test2.tree.TagsTreeRenderer;
import org.zkoss.zkdemo.test2.tree.TreeModelA;
import org.zkoss.zkplus.databind.DataBinder;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Tree;

/**
 *
 * @author Administrador
 */
public class TagsAdminComposer extends GenericForwardComposer {

    TagsModel tagsModel;
    Tree tagsTree;
    TagsTreeRenderer tagsTreeRenderer;
    Textbox searchBox;
    Component tagsAdmin;
    TagsList tagsTreeList;

    //ZK databinder
    protected DataBinder binder;

    public void setTagsModel(TagsModel tagsModel) {
        this.tagsModel = tagsModel;
    }

    public TagsModel getTagsModel() {
        return tagsModel;
    }

    public TagsAdminComposer() {
        tagsModel = new TagsModel();
        tagsTreeRenderer = new TagsTreeRenderer();
        tagsTreeList = new TagsList();
    }

    public void onCreate$tagsAdmin(Event event) {
        binder = (DataBinder) tagsAdmin.getVariable("binder", true);
        tagsTree.setTreeitemRenderer(tagsTreeRenderer);
        populateTagsTree(null);
    }

    private void populateTagsTree(String criteria) {
        tagsTreeList.clear();
        Iterator tags = BaseModel.findEntities("TagsEntity.findRootTags", null).iterator();
        TagsEntity tag;

        while (tags.hasNext()) {
            tag = (TagsEntity) tags.next();
            TagsList tagsByFather = getNodeTags(tag, criteria);
            if (tagsByFather.size() == 0) {
                tagsTreeList.add(tag, criteria);
            } else {
                tagsByFather.setTag(tag);
                tagsTreeList.add(tagsByFather);
            }
        }


        tagsTree.setModel(new TreeModelA(tagsTreeList));
    }

    private TagsList getNodeTags(TagsEntity rootTag, String criteria) {
        Iterator tags = rootTag.getTagsCollection().iterator();
        TagsEntity tag;
        TagsList tagsList = new TagsList();

        while (tags.hasNext()) {
            tag = (TagsEntity) tags.next();
            TagsList tagsByFather = getNodeTags(tag, criteria);

            if (tagsByFather.size() == 0) {
                tagsList.add(tag, criteria);
            } else {
                tagsList.add(tagsByFather);
            }
        }
        tagsList.setTag(rootTag);
        return tagsList;
    }

    public void onChange$searchBox(Event event) {
        String value = searchBox.getValue();
        populateTagsTree(value);
        searchBox.focus();
    }
}
