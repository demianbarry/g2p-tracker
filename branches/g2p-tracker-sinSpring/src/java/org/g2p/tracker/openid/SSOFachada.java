/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.openid;

import java.util.Observer;

/**
 *
 * @author kristian
 */
public class SSOFachada {

    public SSOFachada(){

    }


public static void agregarObservadorUsuario(Observer observador){
    Global.getInstance().getSesion().setAttribute("observador", observador);
}
}
