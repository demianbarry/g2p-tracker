/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.composers;

import java.util.Iterator;
import java.util.Set;
import org.g2p.tracker.controllers.Constants;
import org.g2p.tracker.model.entities.TagsEntity;
import org.g2p.tracker.model.models.BaseModel;
import org.g2p.tracker.model.models.TagsModel;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkdemo.test2.tree.TagsList;
import org.zkoss.zkdemo.test2.tree.TagsTreeRenderer;
import org.zkoss.zkdemo.test2.tree.TreeModelA;
import org.zkoss.zkplus.databind.DataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Window;

/**
 *
 * @author Administrador
 */
public class TagsAdminComposer extends GenericForwardComposer implements Constants {

    TagsModel tagsModel;
    Tree tagsTree;
    TagsTreeRenderer tagsTreeRenderer;
    Listbox tagsList;
    Textbox searchBox;
    Component tagsAdmin;
    TagsList tagsTreeList;
    Window newTagWin;
    Textbox tagText;
    Textbox descripcionText;
    Button switchButton;

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
        ((Panel) tagsAdmin).setOpen(false);
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

    public void onChanging$searchBox(InputEvent event) {
        String value = event.getValue();
        populateTagsTree(value);
    }

    public void onClick$nuevoButton(Event event) {
        try {
            newTagWin.doModal();
        } catch (Exception ex) {
            try {
                Messagebox.show("Error: " + ex.getMessage());
                ex.printStackTrace();
            } catch (InterruptedException ex1) {
                System.out.println("ERROR MOSTRANDO MENSAJE: " + ex1.getMessage());
                ex1.printStackTrace();
            }
        }
    }

    public void onClick$cancelarButton(Event event) {
        newTagWin.setVisible(false);
        tagText.setValue("");
        descripcionText.setValue("");
    }

    public void onClick$aceptarButton(Event event) {
        Set<Treeitem> selectedTags = tagsTree.getSelectedItems();
        Treeitem treeitem;
        TagsEntity tagGrupo;
        TagsEntity tag;
        Iterator<Treeitem> tagsIterator = selectedTags.iterator();
        if (selectedTags.size() > 0) {
            while (tagsIterator.hasNext()) {
                try {
                    treeitem = tagsIterator.next();
                    tagGrupo = (TagsEntity) ((Treecell) treeitem.getTreerow().getChildren().get(0)).getAttribute(TAG_ID);
                    tag = new TagsEntity();
                    tag.setTagIdGrupo(tagGrupo);
                    tag.setTag(tagText.getValue() + " (" + tagGrupo.getTag() + ")");
                    tag.setDescripcion(descripcionText.getValue());
                    BaseModel.createEntity(tag, true);
                } catch (Exception ex) {
                    try {
                        Messagebox.show("Error: " + ex.getMessage());
                        ex.printStackTrace();
                    } catch (InterruptedException ex1) {
                        System.out.println("ERROR MOSTRANDO MENSAJE: " + ex1.getMessage());
                        ex1.printStackTrace();
                    }
                }
            }
        } else {
            try {
                tag = new TagsEntity();
                tag.setTag(tagText.getValue());
                tag.setDescripcion(descripcionText.getValue());
                BaseModel.createEntity(tag, true);
            } catch (Exception ex) {
                try {
                    Messagebox.show("Error: " + ex.getMessage());
                    ex.printStackTrace();
                } catch (InterruptedException ex1) {
                    System.out.println("ERROR MOSTRANDO MENSAJE: " + ex1.getMessage());
                    ex1.printStackTrace();
                }
            }
        }


        populateTagsTree(null);

        newTagWin.setVisible(false);

        tagText.setValue("");
        descripcionText.setValue("");
    }

    public void onClick$switchButton(Event event) {
        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBB   " + tagsTree.isVisible());
        tagsTree.setVisible(tagsList.isVisible());
        tagsList.setVisible(!tagsList.isVisible());
    }
}
