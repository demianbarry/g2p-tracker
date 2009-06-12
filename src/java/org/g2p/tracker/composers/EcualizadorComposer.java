/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.composers;

import java.util.Iterator;
import java.util.List;
import org.g2p.tracker.controllers.Constants;
import org.g2p.tracker.model.entities.TagsEntity;
import org.g2p.tracker.model.models.BaseModel;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.CheckEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.InputEvent;
import org.g2p.tracker.components.TagsList;
import org.g2p.tracker.components.TagsTreeRenderer;
import org.g2p.tracker.components.TreeModelA;
import org.g2p.tracker.model.models.Taggeable;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Tree;

/**
 *
 * @author Administrador
 */
public class EcualizadorComposer extends BaseComposer implements Constants {

    Tree tagsTree;
    Groupbox tagsAdmin;
    TagsTreeRenderer tagsTreeRenderer;
    Listbox tagsList;
    Textbox searchBox;
    TagsList tagsTreeList;
    Checkbox treeCheck;
    String criteria = null;

    public void onClick$administrarButton(Event event) {
    }

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        tagsTreeRenderer = new TagsTreeRenderer();
        tagsTreeList = new TagsList();


        if (self instanceof Groupbox) {
            tagsTree.setTreeitemRenderer(tagsTreeRenderer);
            populateTagsTree(null);
        }
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public List getTags() {
        List tagsList = BaseModel.findEntities("TagsEntity.findAll", null);
        TagsList tags = new TagsList();

        TagsEntity tag;
        for (int i = 0; i < tagsList.size(); i++) {
            tag = (TagsEntity) tagsList.get(i);
            tags.add(tag, criteria);
        }

        return tags;
    }

    private Taggeable getModel() {
        return (Taggeable) self.getDesktop().getAttribute("TAGGEABLE_MODEL");
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
        Iterator tags = rootTag.getTagsEntityCollection().iterator();
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

    public void onChanging$searchBox(InputEvent event) {
        String value = event.getValue();
        populateTagsTree(value);
        setCriteria(value);
        getDataBinder().loadAttribute(tagsList, "model");
    }

    public void onClick$ecualizarButton(Event event) {
        Events.sendEvent(new Event("onEcualizar", (Component) self.getDesktop().getAttribute("ECUALIZABLE_COMPONENT")));
    }

    public void onCheck$treeCheck(CheckEvent event) {
        tagsTree.setVisible(event.isChecked());
        tagsList.setVisible(!event.isChecked());
        tagsTree.clearSelection();
        tagsList.clearSelection();
    }

    protected void showMessage(String msg) {
        showMessage(msg, null);
    }
}
