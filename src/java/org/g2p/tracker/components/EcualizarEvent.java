/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.components;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;

/**
 *
 * @author Administrador
 */
public class EcualizarEvent extends Event {

    public EcualizarEvent(String name, Component target, Object data) {
        super(name, target, data);
    }

    public EcualizarEvent(String name, Component target) {
        super(name, target);
    }
}
