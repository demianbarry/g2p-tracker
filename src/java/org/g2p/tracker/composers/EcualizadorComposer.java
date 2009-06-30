/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.composers;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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
import org.g2p.tracker.model.models.Ecualizable;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Bandpopup;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;

/**
 *
 * @author Administrador
 */
public class EcualizadorComposer extends BaseComposer implements Constants {

    Tree tagsTree;
    Bandbox tagsAdmin;
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


        if (self instanceof Bandbox) {
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

    private Ecualizable getModel() {
        return (Ecualizable) self.getDesktop().getAttribute("TAGGEABLE_MODEL");
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

    public void onChanging$ecualizador(InputEvent event) {
        String value = event.getValue();
        populateTagsTree(value);
        setCriteria(value);
        getDataBinder().loadAttribute(tagsList, "model");
        if(self instanceof Bandbox) {
            ((Bandbox)self).open();
        }
    }

    public void onClick$ecualizarButton(Event event) {
        Set selectedTags;
        Hashtable parameters = null;
        if (tagsList.isVisible()) {
            selectedTags = tagsList.getSelectedItems();
            parameters = new Hashtable();
        } else {
            selectedTags = tagsTree.getSelectedItems();
        }
        if (getModel() != null) {
            List<TagsEntity> tagsList = new ArrayList();
            if (selectedTags.size() > 0) {
                Object item;
                TagsEntity tag = null;
                Iterator tagsIterator = selectedTags.iterator();
                while (tagsIterator.hasNext()) {
                    try {
                        item = tagsIterator.next();
                        if (item instanceof Treeitem) {
                            tag = (TagsEntity) ((Treecell) ((Treeitem) item).getTreerow().getChildren().get(0)).getAttribute(TAG_ID);
                        } else {
                            parameters.clear();
                            parameters.put("tag", ((Listcell) ((Listitem) item).getChildren().get(0)).getLabel());
                            tag = (TagsEntity) (BaseModel.findEntities("TagsEntity.findByTag", parameters).get(0));
                        }
                        tagsList.add(tag);
                    } catch (Exception ex) {
                        showMessage("ERROR: ", ex);
                    }
                }
            }
            try {
                Events.sendEvent(
                        new Event("onEcualizar",
                        (Component) self.getDesktop().getAttribute("ECUALIZABLE_COMPONENT"),
                        getModel().ecualizarByTags(tagsList)));
            } catch (Exception ex) {
                showMessage("ERROR aplicando tags: ", ex);
            }
        }
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
