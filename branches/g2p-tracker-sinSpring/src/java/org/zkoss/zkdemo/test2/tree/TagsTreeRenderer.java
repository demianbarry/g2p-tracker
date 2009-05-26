/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zkoss.zkdemo.test2.tree;

import java.util.ArrayList;
import java.util.Iterator;
import org.g2p.tracker.model.entities.TagsEntity;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;
import org.zkoss.zul.Treerow;

/**
 *
 * @author Administrador
 */
public class TagsTreeRenderer implements TreeitemRenderer {

    @Override
    public void render(Treeitem item, Object data) throws Exception {
        TagsEntity tag = null;
        TagsList tagsList = null;
        if (data instanceof TagsEntity) {
            tag = (TagsEntity) data;
        } else if(data instanceof TagsList){
            tagsList =  (TagsList) data ;
        }
        //TagsEntity tag = (TagsEntity)t.getData();
        //Construct treecells
        Treecell treecell = new Treecell(tag != null ? tag.getTag() : tagsList.getTag().getTag());
        Treerow tr = null;
        /*
         * Since only one treerow is allowed, if treerow is not null,
         * append treecells to it. If treerow is null, construct a new
         * treerow and attach it to item.
         */
        if (item.getTreerow() == null) {
            tr = new Treerow();
            tr.setParent(item);
        } else {
            tr = item.getTreerow();
            tr.getChildren().clear();
        }
        //Attach treecells to treerow
        treecell.setParent(tr);
        item.setOpen(true);
    }
}
