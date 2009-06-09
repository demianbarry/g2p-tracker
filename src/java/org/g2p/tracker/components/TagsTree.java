/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.components;

import org.g2p.tracker.controllers.Constants;
import org.g2p.tracker.model.models.Taggeable;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.impl.XulElement;

/**
 *
 * @author Administrador
 */
public class TagsTree extends XulElement implements Constants, AfterCompose {

    String _name;
    private Taggeable _model;

    public Taggeable getModel() {
        System.out.println("GET: " + _model);
        return _model;
    }

    public void setModel(Taggeable _model) {
        if (_model != null && this._model != _model) {
            getDesktop().setAttribute("MODEL", _model);
            this._model = _model;
        }
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    @Override
    public void afterCompose() {
        getDesktop().setAttribute("COMPONENT", this);
    }
}
