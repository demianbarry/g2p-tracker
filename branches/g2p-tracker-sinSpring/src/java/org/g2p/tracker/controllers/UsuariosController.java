/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.controllers;

import java.util.Observable;
import java.util.Observer;
import javax.servlet.http.HttpServletRequest;
import org.g2p.tracker.openid.SSOFachada;

/**
 *
 * @author kristian
 */
public class UsuariosController implements Observer{

    public UsuariosController(){
        SSOFachada.agregarObservadorUsuario(this);
    }

   
    @Override
    public void update(Observable arg0, Object arg1) {
        HttpServletRequest req = (HttpServletRequest) arg1;

        // si hay que registrar al usuario
        if (new Boolean(req.getSession().getAttribute("registrar").toString())){
                System.out.println("#############   registrando usuario... ##################");
        }
        else {
            System.out.println("#############   usuario logueado ##################");
        }
    }
}
