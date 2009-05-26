/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.openid;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Observer;
import java.util.Vector;
import javax.servlet.http.HttpSession;

/**
 * Variables globales
 *
 * Podría evolucionar hacia una fachada o algo parecido (o podría desaparecer)
 *
 * @author Cristian Pacheco
 */
public class Global {
    // clase singleton
    private static Global instance = new Global();


    private HttpSession sesion;

    private Global(){

    }

    /**
     * Clase Singleton
     * @return la referencia al único objeto existente
     */
    public static Global getInstance(){
        return instance;
    }

    public HttpSession getSesion() {
        return sesion;
    }

    public void setSesion(HttpSession sesion) {
        this.sesion = sesion;
    }
}
