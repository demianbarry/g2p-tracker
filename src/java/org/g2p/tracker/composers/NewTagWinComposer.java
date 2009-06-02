/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.composers;

import org.g2p.tracker.controllers.Constants;
import org.g2p.tracker.model.models.TagsModel;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.DataBinder;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

/**
 *
 * @author Administrador
 */
public class NewTagWinComposer extends GenericForwardComposer implements Constants {

    TagsModel tagsModel;
    Component tagsAdmin;
    Window newTagWin;
    Textbox tagText;
    Textbox descripcionText;

    //ZK databinder
    protected DataBinder binder;

    public void setTagsModel(TagsModel tagsModel) {
        this.tagsModel = tagsModel;
    }

    public TagsModel getTagsModel() {
        return tagsModel;
    }

    public NewTagWinComposer() {
        tagsModel = new TagsModel();
    }

    public void onCreate$tagsAdmin(Event event) {
        binder = (DataBinder) tagsAdmin.getVariable("binder", true);
    }

    public void onClick$cancelarButton(Event event) {
        newTagWin.setVisible(false);
        tagText.setValue("");
        descripcionText.setValue("");
    }

    public void onClick$aceptarButton(Event event) {
        Events.echoEvent("onCreate", tagsAdmin, null);

        newTagWin.setVisible(false);
        tagText.setValue("");
        descripcionText.setValue("");
    }
}
