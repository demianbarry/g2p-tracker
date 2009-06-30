/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.components;

import java.util.List;
import org.g2p.tracker.model.entities.BaseEntity;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;

/**
 *
 * @author Administrador
 */
public class EcualizarEvent extends Event {

    private List<BaseEntity> entities;

    public List<BaseEntity> getEntities() {
        return entities;
    }

    public void setEntities(List<BaseEntity> entities) {
        this.entities = entities;
    }

    public EcualizarEvent(String name, Component target, Object data) {
        super(name, target, data);
    }

    public EcualizarEvent(String name, Component target, List<BaseEntity> entities) {
        super(name, target);
        setEntities(entities);
    }

    public String getQuery() {
        return (String) getData();
    }
}
