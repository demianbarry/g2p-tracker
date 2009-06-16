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
import org.g2p.tracker.model.models.Taggeable;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Window;

/**
 *
 * @author Administrador
 */
public class TagsAdminComposer extends BaseComposer implements Constants {

    Tree tagsTree;
    Bandbox tagsAdmin;
    TagsTreeRenderer tagsTreeRenderer;
    Listbox tagsList;
    Textbox searchBox;
    TagsList tagsTreeList;
    Window newTagWin;
    Textbox tagText;
    Textbox descripcionText;
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
            ((Bandbox) self).setOpen(false);
            populateTagsTree(null);
        }

        setSelectedItems();
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

    public void onChanging$tagsAdmin(InputEvent event) {
        String value = event.getValue();
        populateTagsTree(value);
        setCriteria(value);
        getDataBinder().loadAttribute(tagsList, "model");
        setSelectedItems();
    }

    public void onOpen$tagsAdmin(Event event){
        if(self instanceof Bandbox)
            setSelectedItems();
    }

    public void onClick$nuevoButton(Event event) {
        try {
            newTagWin.doModal();
        } catch (Exception ex) {
            showMessage("ERROR: ", ex);
        }
    }

    public void onClick$cancelarButton(Event event) {
        newTagWin.setVisible(false);
        tagText.setValue("");
        descripcionText.setValue("");
    }

    public void onClick$aceptarButton(Event event) {
        String tag = tagText.getValue();
        String descripcion = descripcionText.getValue();

        if (tag == null || descripcion == null) {
            return;
        }

        Set<Treeitem> selectedTags = tagsTree.getSelectedItems();
        Treeitem treeitem;
        TagsEntity tagGrupo;
        TagsEntity tagEntity;
        Iterator<Treeitem> tagsIterator = selectedTags.iterator();

        if (selectedTags.size() > 0 && treeCheck.isChecked()) {
            while (tagsIterator.hasNext()) {
                try {
                    treeitem = tagsIterator.next();
                    tagGrupo = (TagsEntity) ((Treecell) treeitem.getTreerow().getChildren().get(0)).getAttribute(TAG_ID);
                    tagEntity = new TagsEntity();
                    tagEntity.setTagIdGrupo(tagGrupo);
                    tagEntity.setTag(tag + " (" + tagGrupo.getTag() + ")");
                    tagEntity.setDescripcion(descripcion);
                    BaseModel.createEntity(tagEntity, true);
                } catch (Exception ex) {
                    showMessage("ERROR: ", ex);
                }
            }
        } else {
            try {
                tagEntity = new TagsEntity();
                tagEntity.setTag(tag);
                tagEntity.setDescripcion(descripcion);
                BaseModel.createEntity(tagEntity, true);
            } catch (Exception ex) {
                showMessage("ERROR: ", ex);
            }
        }


        populateTagsTree(null);

        newTagWin.setVisible(false);

        tagText.setValue("");
        descripcionText.setValue("");
    }

    public void onClick$aplicarButton(Event event) {
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
                getModel().aplicarTags(tagsList);
            } catch (Exception ex) {
                showMessage("ERROR aplicando tags: ", ex);
            }

        }
        if (self instanceof Bandbox) {
            Events.sendEvent(new Event("onTag", (Component) self.getDesktop().getAttribute("TAGGEABLE_COMPONENT")));
        }
    }

    public void onCheck$treeCheck(CheckEvent event) {
        tagsTree.setVisible(event.isChecked());
        tagsList.setVisible(!event.isChecked());
        setSelectedItems();
    }

    protected void showMessage(String msg) {
        showMessage(msg, null);
    }

    private void setSelectedItems() {
        tagsTree.clearSelection();
        tagsList.clearSelection();
        System.out.println("---------> "+self);
        if (self instanceof Bandbox && getModel().getStoredTags() != null) {
            Iterator it = getModel().getStoredTags().iterator();
            List<String> tagNames = new ArrayList();
            while (it.hasNext()) {
                tagNames.add(((TagsEntity) it.next()).getTag());
            }
            List items = null;
            List selectedItems = new ArrayList();

            int i = 0;

            if (tagsList.isVisible()) {
                items = new ArrayList(tagsList.getItems());
                while (items.size() > i++) {
                    if (tagNames.contains(((Listcell) ((Listitem) items.get(i - 1)).getChildren().get(0)).getLabel())) {
                        selectedItems.add(items.get(i - 1));
                        ((Listitem) items.get(i - 1)).setSelected(true);
                    }
                }
            } else {
                items = new ArrayList(tagsTree.getItems());
                while (items.size() > i++) {
                    if (tagNames.contains(((Treecell) ((Treeitem) items.get(i - 1)).getTreerow().getChildren().get(0)).getLabel())) {
                        ((Treeitem) items.get(i - 1)).setSelected(true);
                    }
                }
            }
        }
    }
}
