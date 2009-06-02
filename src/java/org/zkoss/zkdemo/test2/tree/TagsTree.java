/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zkoss.zkdemo.test2.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.g2p.tracker.controllers.Constants;
import org.g2p.tracker.model.models.Taggeable;
import org.g2p.tracker.model.entities.TagsEntity;
import org.g2p.tracker.model.entities.TagsPerTracksEntity;
import org.g2p.tracker.model.models.BaseModel;
import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.event.CheckEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zkplus.databind.DataBinder;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Window;

/**
 *
 * @author Administrador
 */
public class TagsTree extends Groupbox implements AfterCompose, Constants {

    String _name;
    Taggeable _model;
    Tree tagsTree;
    TagsTreeRenderer tagsTreeRenderer;
    Listbox tagsList;
    Textbox searchBox;
    TagsList tagsTreeList;
    Window newTagWin;
    Textbox tagText;
    Textbox descripcionText;
    Checkbox treeCheck;
    String criteria = null;

    public Taggeable getModel() {
        return _model;
    }

    public void setModel(Taggeable _model) {
        this._model = _model;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public void onClick$administrarButton(Event event) {
    }

    @Override
    public void afterCompose() {
        //wire variables
        Components.wireVariables(this, this);

        //auto forward
        Components.addForwards(this, this);
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }
    //ZK databinder
    protected DataBinder binder;

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

    public TagsTree() {
        tagsTreeRenderer = new TagsTreeRenderer();
        tagsTreeList = new TagsList();
    }

    public void onCreate$tagsAdmin(Event event) {
        binder = (DataBinder) getVariable("binder", true);
        tagsTree.setTreeitemRenderer(tagsTreeRenderer);
        setOpen(false);
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

    public void onChangingSearchBox(InputEvent event) {
        String value = event.getValue();
        populateTagsTree(value);
        setCriteria(value);
        binder.loadAttribute(tagsList, "model");
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

    public void onCheckTreeCheck(CheckEvent event) {
        tagsTree.setVisible(event.isChecked());
        tagsList.setVisible(!event.isChecked());


    }

    protected void showMessage(String msg) {
        showMessage(msg, null);
    }

    protected void showMessage(String msg, Exception ex) {
        try {
            Messagebox.show(msg + ex.getMessage());
            ex.printStackTrace();
        } catch (InterruptedException ex1) {
            System.out.println("ERROR MOSTRANDO MENSAJE: " + ex1.getMessage());
            ex1.printStackTrace();
        }
    }

    public void onClick$aplicarButton(Event event) {
        Set<Treeitem> selectedTags = tagsTree.getSelectedItems();

        if (selectedTags.size() > 0 && getModel() != null) {
            Treeitem treeitem;
            TagsEntity tag;
            Iterator<Treeitem> tagsIterator = selectedTags.iterator();
            List<TagsEntity> tagsList = new ArrayList();
            while (tagsIterator.hasNext()) {
                try {
                    treeitem = tagsIterator.next();
                    tag = (TagsEntity) ((Treecell) treeitem.getTreerow().getChildren().get(0)).getAttribute(TAG_ID);
                    tagsList.add(tag);
                } catch (Exception ex) {
                    showMessage("ERROR: ", ex);
                }
            }
            getModel().aplicarTags(tagsList);
        }
    }
}
